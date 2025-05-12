package min.jun.threadpool.taskExecutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@EnableAsync
@Configuration
public class TaskPoolConfig implements AsyncConfigurer {
    /**
     * 异步任务线程池:适用于异步任务
     */
    @Primary
    @Override
    @Bean("edge.taskExecutor")
    public Executor getAsyncExecutor() {
        //traceId跟踪: 包装Runnable/Callable
        ThreadPoolTaskExecutor taskExecutor = new TaskExecutorMdcWrapper();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setQueueCapacity(50);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadFactory(new ThreadFactory() {
            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "min.jun-" + +threadNumber.getAndIncrement());
                //设置优先级, 默认5
                thread.setPriority(1);
                return thread;
            }
        });
        taskExecutor.setAwaitTerminationSeconds(120);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}