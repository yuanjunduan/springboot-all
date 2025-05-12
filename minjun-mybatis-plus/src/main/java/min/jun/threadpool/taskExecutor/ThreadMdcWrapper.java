package min.jun.threadpool.taskExecutor;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * traceId跟踪: 包装Runnable/Callable MDC: 与当前线程绑定的哈希表,作用域:当前线程范围内
 *
 * @author xiangjun
 * @date 2021/1/2 10:35
 */
public class ThreadMdcWrapper {
    public final static String TRACE_ID = "traceId";
    public final static String SUB_TRACE_ID = "subtraceId";

    public static void putTraceID(String traceID) {
        if (StringUtils.isEmpty(MDC.get(TRACE_ID))) {
            MDC.put(TRACE_ID, StringUtils.isNotBlank(traceID) ? traceID : getTraceId());
        }
    }

    public static void putSubTraceID(String traceID) {
        MDC.put(SUB_TRACE_ID, StringUtils.isNotBlank(traceID) ? traceID : getTraceId());
    }


    public static String putIfAbsent() {
        String traceId = "";
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            traceId = request.getHeader(TRACE_ID);
            if (StringUtils.isBlank(traceId)) {
                traceId = ThreadMdcWrapper.getTraceId();
                MDC.put(TRACE_ID, traceId);
            }
        } else {
            traceId = MDC.get(TRACE_ID);
            if (StringUtils.isBlank(traceId)) {
                traceId = getTraceId();
                MDC.put(TRACE_ID, traceId);
            }
        }
        return traceId;
    }

    public static String getTraceIdFromMDC() {
        String traceId = MDC.get(TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = "traceId";
        }
        return traceId;
    }

    public static String getTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static void clear() {
        MDC.clear();
    }


}
