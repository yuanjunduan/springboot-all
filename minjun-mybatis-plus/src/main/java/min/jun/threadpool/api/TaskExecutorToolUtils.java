package min.jun.threadpool.api;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.jun.threadpool.taskExecutor.ToolUtils;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ExecutorService;


@Slf4j
@RequiredArgsConstructor
@Component
public class TaskExecutorToolUtils {

    @PostConstruct
    public void initTasks() {
        TaskScheduler taskScheduler = ToolUtils.getTaskScheduler("TaskExecutorToolUtils");

        //首次延迟10秒,间隔10s执行一次
        int initialDelay = 1 * 10 * 1000;
        DateTime startTime = DateUtil.offsetMillisecond(new Date(), initialDelay);
        taskScheduler.scheduleWithFixedDelay(new DemoTask(), startTime, 1000 * 10);
    }


    private static class DemoTask implements Runnable {
        @Override
        public void run() {
            log.info("TaskExecutorToolUtils start");
        }
    }
}