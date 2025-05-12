package min.jun.threadpool.executorService;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 继承ThreadPoolExecutor类，覆盖了shutdown(), shutdownNow(), beforeExecute() 和 afterExecute()
 * 方法来统计线程池的执行情况
 *
 * @author Administrator
 */
@Slf4j
public class ThreadPoolMonitor extends ThreadPoolExecutor {

    /**
     * 保存任务开始执行的时间，当任务结束时，用任务结束时间减去开始时间计算任务执行时间
     */
    private final ConcurrentHashMap<String, Date> startTimes;
    /**
     * 线程池名称，一般以业务名称命名，方便区分
     */
    @Getter
    private String poolName;

    /**
     * 调用父类的构造方法，并初始化HashMap和线程池名称
     *
     * @param corePoolSize    线程池核心线程数
     * @param maximumPoolSize 线程池最大线程数
     * @param keepAliveTime   线程的最大空闲时间
     * @param unit            空闲时间的单位
     * @param workQueue       保存被提交任务的队列
     * @param poolName        线程池名称
     */
    public ThreadPoolMonitor(int corePoolSize,
                             int maximumPoolSize,
                             long keepAliveTime, TimeUnit unit,
                             BlockingQueue<Runnable> workQueue,
                             String poolName,
                             RejectedExecutionHandler rejectedExecutionHandler) {

        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new EventThreadFactory(poolName), poolName, rejectedExecutionHandler);
    }


    /**
     * 调用父类的构造方法，并初始化HashMap和线程池名称
     *
     * @param corePoolSize    线程池核心线程数
     * @param maximumPoolSize 线程池最大线程数
     * @param keepAliveTime   线程的最大空闲时间
     * @param unit            空闲时间的单位
     * @param workQueue       保存被提交任务的队列
     * @param threadFactory   线程工厂
     * @param poolName        线程池名称
     */
    public ThreadPoolMonitor(int corePoolSize,
                             int maximumPoolSize,
                             long keepAliveTime, TimeUnit unit,
                             BlockingQueue<Runnable> workQueue,
                             ThreadFactory threadFactory,
                             String poolName,
                             RejectedExecutionHandler rejectedExecutionHandler) {

        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new EventThreadFactory(poolName), rejectedExecutionHandler);
        this.startTimes = new ConcurrentHashMap<>();
        this.poolName = poolName;
    }

    /**
     * 创建固定线程池，代码源于Executors.newFixedThreadPool方法，这里增加了poolName
     *
     * @param nThreads 线程数量
     * @param poolName 线程池名称
     * @return ExecutorService对象
     */
    public static ExecutorService newFixedThreadPool(int nThreads, String poolName) {
        return new ThreadPoolMonitor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), poolName, new AbortPolicy());
    }

    /**
     * 创建缓存型线程池，代码源于Executors.newCachedThreadPool方法，这里增加了poolName
     *
     * @param poolName 线程池名称
     * @return ExecutorService对象
     */
    public static ExecutorService newCachedThreadPool(String poolName) {
        return new ThreadPoolMonitor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), poolName, new AbortPolicy());
    }


    /**
     * 线程池延迟关闭时（等待线程池里的任务都执行完毕），统计线程池情况
     */
    @Override
    public void shutdown() {
        // 统计已执行任务、正在执行任务、未执行任务数量
        log.info("【thread shutdown】{} Going to shutdown. Executed tasks: {}, Running tasks: {}, Pending tasks: {}", this.poolName, this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
        super.shutdown();
    }

    /**
     * 线程池立即关闭时，统计线程池情况
     */
    @Override
    public List<Runnable> shutdownNow() {
        log.info("【thread shutdownNow】check shop deliver plat error {} Going to immediately shutdown. Executed tasks: {}, Running tasks: {}, Pending tasks: {}", this.poolName, this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
        return super.shutdownNow();
    }


    /**
     * 任务执行之前，记录任务开始时间
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        startTimes.put(r.hashCode() + "", new Date());
    }

    @Override
    public void execute(@NotNull Runnable command) {
        super.execute(new MdcRunner(command));
    }

    /**
     * 任务执行之后，计算任务结束时间
     * <p>
     * 在 afterExecute 方法中需要注意，需要调用 ConcurrentHashMap 的 remove 方法移除并返回任务的开始时间信息，而不是调用 get 方法，
     * 因为在高并发情况下，线程池里要执行的任务很多，如果只获取值不移除的话，会使 ConcurrentHashMap 越来越大，引发内存泄漏或溢出问题
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Date startDate = startTimes.remove(StringUtils.EMPTY + r.hashCode());
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        // 统计任务耗时、初始线程数、核心线程数、正在执行的任务数量、
        // 已完成任务数量、任务总数、队列里缓存的任务数量、池中存在的最大线程数、
        // 最大允许的线程数、线程空闲时间、线程池是否关闭、线程池是否终止

        String threadInfo = String.format("afterExecute poolName:%s " +
                        "PoolSize: %s, CorePoolSize: %s, Active: %s, " +
                        "Completed: %s, Task: %s, Queue: %s, LargestPoolSize: %s, " +
                        "MaximumPoolSize: %s,  KeepAliveTime: %s, isShutdown: %s, isTerminated: %s",
                this.poolName, this.getPoolSize(), this.getCorePoolSize(), this.getActiveCount(),
                this.getCompletedTaskCount(), this.getTaskCount(), this.getQueue().size(), this.getLargestPoolSize(),
                this.getMaximumPoolSize(), this.getKeepAliveTime(TimeUnit.MILLISECONDS), this.isShutdown(), this.isTerminated());

        if (null != t) {
            log.error("【thread afterExecute】{};异常", threadInfo, t);
        }

        if (diff < 200 || this.getPoolSize() < 10) {
            return;
        }
        log.info("【thread afterExecute】{}", threadInfo);
    }


    /**
     * 生成线程池所用的线程，只是改写了线程池默认的线程工厂，传入线程池名称，便于问题追踪
     */
    static class EventThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        public final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        /**
         * 初始化线程工厂
         *
         * @param poolName 线程池名称
         */
        EventThreadFactory(String poolName) {
            SecurityManager s = System.getSecurityManager();
            group = Objects.nonNull(s) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = String.format("%s-pool-%s-thread", poolName, poolNumber.getAndIncrement());
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + "-" + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }

            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }

            return t;
        }
    }

    public static class MyCallerRunsPolicy extends CallerRunsPolicy {
        public MyCallerRunsPolicy() {
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {

            recordLogThreadErr(r, e);
            super.rejectedExecution(r, e);
        }
    }

    public static class MyAbortPolicy extends AbortPolicy {
        MyAbortPolicy() {
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            recordLogThreadErr(r, e);
            super.rejectedExecution(r, e);
        }
    }

    private static void recordLogThreadErr(Runnable r, ThreadPoolExecutor e) {
        String poolName = StringUtils.EMPTY;
        if (e instanceof ThreadPoolMonitor) {
            ThreadPoolMonitor threadPoolMonitor = (ThreadPoolMonitor) e;
            poolName = threadPoolMonitor.getPoolName();
        }

        String msg = String.format("Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), " +
                        " Task: %d (completed: %d)," +
                        " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)",
                poolName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                e.getTaskCount(), e.getCompletedTaskCount(),
                e.isShutdown(), e.isTerminated(), e.isTerminating());
        log.error("【thread recordLogThreadErr】{} 异常 {}", msg, e);
    }

}
