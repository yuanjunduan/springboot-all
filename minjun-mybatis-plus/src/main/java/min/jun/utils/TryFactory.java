package min.jun.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author Administrator
 */
@Slf4j
public class TryFactory {

    /**
     * * 重试
     * * @param retryTimeInterval  重试间隔
     * * @param retryTimes     重试次数
     * * @param method    重试执行方法
     * * @param <R>       重试执行方法返回类型
     *  e.g 1
     *  TryFactory.execAndRetry(() -> redisClient.set(RedisKey.registerShopV1(shopID), DateTimeUtil.getCurrentSecTime()), 1, 100L);
     *
     *
     *   e.g 2
     *    shopParamsExecutor.execute(() -> TryFactory.execAndRetry(() -> {
     *        try {
     *        } catch (Exception e) {
     *            throw new RuntimeException(e);
     *        }
     *    }, 2, 2000L));
     */
    public static void execAndRetry(Runnable r, Integer retryTimes, Long retryTimeInterval) {
        if (retryTimeInterval == null) {
            retryTimeInterval = 100L;
        }
        if (retryTimes == null) {
            retryTimes = 2;
        }
        while (retryTimes >= 0) {
            try {
                r.run();
            } catch (Exception e) {
                retryTimes--;

                if (retryTimes > 0) {
                    sleep(retryTimeInterval);
                }
                if (retryTimes < 0) {
                        log.error("auth-016-008 重试异常 ",e);
                    throw new RuntimeException(e);
                }
                continue;
            }
            break;
        }
    }

    public static <T> T execAndRetry(Supplier<T> s, Integer times, Long timeout) throws Exception {
        if (timeout == null) {
            timeout = 100L;
        }
        if (times == null) {
            times = 0;
        } else {
            times = times - 1;
        }

        T t = null;
        while (times >= 0) {
            try {
                t = s.get();
            } catch (Exception e) {
                if (times > 0) {
                    sleep(timeout);
                }
                times--;
                if (times < 0) {
                    throw new Exception(e);
                }
                continue;
            }
            break;
        }
        return t;
    }


    public static void sleep(Long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
        }
    }
}
