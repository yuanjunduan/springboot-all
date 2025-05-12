package min.jun.threadpool.taskExecutor;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

import static min.jun.threadpool.taskExecutor.ThreadMdcWrapper.putIfAbsent;

/**
 * traceId跟踪: 将父线程的MDC内容复制给子线程
 * @author xiangjun
 * @date 2021/1/2 10:35
 */
public class TaskSchedulerMdcWrapper extends ThreadPoolTaskScheduler {

    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        super.execute(wrap(task), startTimeout);
    }

    @Override
    public <T> Future<T> submit(@NotNull Callable<T> task) {
        return super.submit(wrap(task));
    }

    @Override
    public Future<?> submit(@NotNull Runnable task) {
        return super.submit(wrap(task));
    }

    @Override
    public ScheduledFuture<?> schedule(@NotNull Runnable task, @NotNull Trigger trigger) {
        return super.schedule(wrap(task), trigger);
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable task, @NotNull Date startTime) {
        return super.schedule(wrap(task), startTime);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(@NotNull Runnable task, @NotNull Date startTime, long period) {
        return super.scheduleAtFixedRate(wrap(task), startTime, period);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(@NotNull Runnable task, long period) {
        return super.scheduleAtFixedRate(wrap(task), period);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
        return super.scheduleWithFixedDelay(wrap(task), startTime, delay);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        return super.scheduleWithFixedDelay(wrap(task), delay);
    }

    public static <T> Callable<T> wrap(final Callable<T> task) {
        return wrap(task, MDC.getCopyOfContextMap());
    }

    public static Runnable wrap(final Runnable task) {
        return wrap(task, MDC.getCopyOfContextMap());
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return TtlCallable.get(() -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            putIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        });
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return TtlRunnable.get(() -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            putIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        });

    }
}
