package min.jun.threadpool.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Executor;


@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "线程", description = "线程")
@RequestMapping("/api/1.1/taskExecutor")
public class TaskExecutorApi {


    @Resource(name = "taskExecutor1")
    private Executor taskExecutor1;

    @GetMapping("/start")
    @Operation(summary = "线程启动", description = "线程启动")
    public void start() throws InterruptedException {

        taskExecutor1.execute(() -> {
            try {
                log.info("taskExecutor1 start");
                Thread.sleep(1000 * 2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.info("taskExecutor1 end");
            }
        });
    }

}