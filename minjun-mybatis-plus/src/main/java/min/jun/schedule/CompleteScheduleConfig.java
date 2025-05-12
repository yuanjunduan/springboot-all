package min.jun.schedule;


import cn.hutool.core.exceptions.ValidateException;
import min.jun.schedule.task.AbstractScheduleTask;
import min.jun.threadpool.taskExecutor.TaskSchedulerMdcWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;

import static min.jun.exception.AppError.SCHEDULE_TASK_EXIST;


/**
 * @author Administrator
 */
@Configuration
@EnableScheduling
public class CompleteScheduleConfig {


    private static Map<String, ScheduledFuture<?>> scheduledFutureMap = new ConcurrentHashMap<>();
    public static Map<String, AbstractScheduleTask> taskMap = new ConcurrentHashMap<>();
    private static ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private List<AbstractScheduleTask> taskList;


    @Autowired
    public void CompleteScheduleConfig(ThreadPoolTaskScheduler component) {
        CompleteScheduleConfig.threadPoolTaskScheduler = component;
    }

    void init() {

        taskList.forEach(k -> {
            boolean existTask = scheduledFutureMap.containsKey(k.taskID());
            if (existTask) {
                throw new ValidateException(SCHEDULE_TASK_EXIST.getCode(), String.format(SCHEDULE_TASK_EXIST.getMsg(), k.taskID()));
            }
            taskMap.putIfAbsent(k.taskID(), k);

            if (StringUtils.isBlank(k.cronExpression())) {
                throw new ValidateException(SCHEDULE_TASK_EXIST.getCode(), String.format("cron 表达式为空 %s", k.taskID()));
            }

            Trigger trigger = getTrigger(k.cronExpression());
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(k, trigger);
            scheduledFutureMap.put(k.taskID(), scheduledFuture);
        });
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        TaskSchedulerMdcWrapper executor = new TaskSchedulerMdcWrapper();
        executor.setPoolSize(2);
        executor.setRemoveOnCancelPolicy(true);
        executor.setThreadNamePrefix("CompleteScheduleConfig-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    private static Trigger getTrigger(String cron) {
        return triggerContext -> {
            //将Cron 0/1 * * * * ? 输入取得下一次执行的时间
            CronTrigger trigger = new CronTrigger(cron);
            return trigger.nextExecutionTime(triggerContext);
        };
    }

    /**
     * 取消定时任务
     */
    public static void cancel(String taskID) {

        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(taskID);

        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(Boolean.TRUE);
        }
        scheduledFutureMap.remove(taskID);
    }

    /**
     * 编辑
     */
    public static void reset(AbstractScheduleTask task) {
        cancel(task.taskID());
        addTask(task);
    }


    /**
     * 变更任务间隔，再次启动
     **/
    public static void addTask(AbstractScheduleTask task) {
        if (scheduledFutureMap.containsKey(task.taskID())) {
            return;
        }

        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(task, getTrigger(task.cronExpression()));
        scheduledFutureMap.put(task.taskID(), scheduledFuture);
    }

    public static AbstractScheduleTask getAddTask(String taskID) {
        return Optional.ofNullable(CompleteScheduleConfig.taskMap.get(taskID))
                .orElseThrow(() -> new ValidateException(SCHEDULE_TASK_EXIST.getCode(), String.format("该有该任务ID %s", taskID)));
    }
}
