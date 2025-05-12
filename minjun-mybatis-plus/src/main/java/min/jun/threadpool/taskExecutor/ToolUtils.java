package min.jun.threadpool.taskExecutor;

import com.alibaba.ttl.TtlRunnable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 工具类
 */
@Slf4j
public class ToolUtils {

    /**
     * 定时任务线程池
     * prefix示例: AlgoInstall
     */
    public static TaskScheduler getTaskScheduler(String prefix) {
        //#1.创建定时任务线程池,也可使用注解方式(@EnableAsync,@Scheduled)
        ThreadPoolTaskScheduler taskScheduler = new TaskSchedulerMdcWrapper();
        taskScheduler.setPoolSize(1);
        //如果超过这个时间还没有销毁就强制销毁,以确保应用最后能够被关闭
        taskScheduler.setAwaitTerminationSeconds(60);
        //等待所有任务都完成后,再销毁其他的Bean,如:数据库连接池对象
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskScheduler.setThreadNamePrefix(StringUtils.appendIfMissing(prefix, "-"));
        taskScheduler.setThreadPriority(1);
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskScheduler.initialize();
        return taskScheduler;
    }

}
