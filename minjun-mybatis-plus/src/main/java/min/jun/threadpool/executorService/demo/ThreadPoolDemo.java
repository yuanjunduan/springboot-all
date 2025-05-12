package min.jun.threadpool.executorService.demo;

import min.jun.threadpool.executorService.ThreadPoolMonitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * 全局共用线程池实现类
 */
@Configuration
public class ThreadPoolDemo {

    /**
     * 系统通用线程池
     *
     * @return cache线程池
     */
    @Bean
    public ExecutorService shopParamsExecutor() {
        return new ThreadPoolMonitor(
                2,
                100,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                "shopParams-pool",
                new ThreadPoolMonitor.MyCallerRunsPolicy()
        );
    }

}
