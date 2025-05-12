//package min.jun.hippo4j.threadpool;
//
//import org.dromara.dynamictp.core.executor.DtpExecutor;
//import org.dromara.dynamictp.core.support.DynamicTp;
//import org.dromara.dynamictp.core.support.ThreadPoolBuilder;
//import org.dromara.dynamictp.core.support.ThreadPoolCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//import static org.dromara.dynamictp.common.em.QueueTypeEnum.SYNCHRONOUS_QUEUE;
//import static org.dromara.dynamictp.common.em.QueueTypeEnum.VARIABLE_LINKED_BLOCKING_QUEUE;
//import static org.dromara.dynamictp.common.em.RejectedTypeEnum.CALLER_RUNS_POLICY;
//
//@Configuration
//public class ThreadPoolExecutorConfig {
//
//    @DynamicTp("commonExecutor")
//    @Bean
//    public ThreadPoolExecutor commonExecutor() {
//        return (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
//    }
////
////    @Bean
////    public DtpExecutor dtpExecutor1() {
////        return ThreadPoolCreator.createDynamicFast("dtpExecutor1");
////    }
//
////    @Bean
////    public DtpExecutor ioIntensiveExecutor() {
////        return ThreadPoolBuilder.newBuilder()
////                .threadPoolName("ioIntensiveExecutor")
////                .corePoolSize(2)
////                .maximumPoolSize(5)
////                .queueCapacity(2048).eager()
////                .buildDynamic();
////    }
//
//    @Bean
//    public ThreadPoolExecutor dtpExecutor2() {
//        return ThreadPoolBuilder.newBuilder()
//                .threadPoolName("dtpExecutor2")
//                .threadFactory("dtpExecutor2")
//                .corePoolSize(1)
//                .maximumPoolSize(3)
//                .keepAliveTime(15000)
//                .timeUnit(TimeUnit.MILLISECONDS)
//                .workQueue(VARIABLE_LINKED_BLOCKING_QUEUE.getName(), null, false, null)
//                .rejectedExecutionHandler(CALLER_RUNS_POLICY.getName())
//                .waitForTasksToCompleteOnShutdown(true)
//                .awaitTerminationSeconds(5)
//                .buildDynamic();
//    }
//}
