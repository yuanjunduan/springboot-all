package min.jun.threadpool.taskExecutor.demo;

import min.jun.threadpool.executorService.ThreadPoolMonitor;
import min.jun.threadpool.taskExecutor.TaskExecutorMdcWrapper;
import min.jun.threadpool.taskExecutor.ToolUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author Administrator
 * 全局共用线程池实现类
 */
@Configuration
public class TaskExecutorDemo {


    @Bean("taskExecutor1")
    public Executor taskExecutor1() {
        ThreadPoolTaskExecutor taskExecutor = new TaskExecutorMdcWrapper();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(1);
        taskExecutor.setQueueCapacity(0);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("taskExecutor1-");
        taskExecutor.setAwaitTerminationSeconds(120);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

}
