package min.jun.threadpool.taskExecutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static min.jun.threadpool.taskExecutor.TaskSchedulerMdcWrapper.wrap;

/**
 * traceId跟踪: 将父线程的MDC内容复制给子线程
 */
@Slf4j
public class TaskExecutorMdcWrapper extends ThreadPoolTaskExecutor {
	
	private void showThreadPoolInfo(String prefix){
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        log.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
            this.getThreadNamePrefix(),
            prefix,
            threadPoolExecutor.getTaskCount(),
            threadPoolExecutor.getCompletedTaskCount(),
            threadPoolExecutor.getActiveCount(),
            threadPoolExecutor.getQueue().size());
    }

    @Override
    public void execute(Runnable task) {
    	showThreadPoolInfo("1. do execute");
        super.execute(wrap(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
    	showThreadPoolInfo("2. do execute");
        super.execute(wrap(task), startTimeout);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
    	showThreadPoolInfo("1. do submit");
        return super.submit(wrap(task));
    }

    @Override
    public Future<?> submit(Runnable task) {
    	showThreadPoolInfo("2. do submit");
        return super.submit(wrap(task));
    }
}
