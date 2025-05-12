//package min.jun.hippo4j.threadpool.api;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.dromara.dynamictp.core.executor.DtpExecutor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//@Tag(name = "线程", description = "线程")
//@RequestMapping("/api/1.1/thread")
//public class ThreadApi {
//
//
//    @GetMapping("/start")
//    @Operation(summary = "线程启动", description = "线程启动")
//    public void start() throws InterruptedException {
//
//        task();
//
//    }
//
//    public void task() throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            dtpExecutor1.execute(() -> {
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                log.info("i am dtpExecutor1 task {}", dtpExecutor1.getThreadPoolAliasName());
//            });
//
//            dtpExecutor2.execute(() -> {
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                log.info("i am dtpExecutor2 task {}", dtpExecutor1.getThreadPoolAliasName());
//            });
//        }
//    }
//
//}