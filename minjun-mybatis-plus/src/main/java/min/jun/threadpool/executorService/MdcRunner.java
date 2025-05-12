package min.jun.threadpool.executorService;

import org.slf4j.MDC;

import java.util.Map;

/**
 * @author Administrator
 */
public class MdcRunner implements Runnable {

    protected Runnable command;
    public Map<String, String> superMap;

    public MdcRunner(Runnable command) {
        this.command = command;
        this.superMap = MDC.getCopyOfContextMap();
    }

    @Override
    public final void run() {
        Map<String, String> childMap = MDC.getCopyOfContextMap();
        try {
            if (superMap == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(superMap);
            }
            command.run();
        } catch (Throwable e) {
            //多线程代码中 如果catch 这里代码就不会走了
            throw e;
        } finally {
            if (childMap == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(childMap);
            }
        }
    }
}
