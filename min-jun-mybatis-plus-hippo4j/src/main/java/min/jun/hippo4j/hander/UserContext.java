package min.jun.hippo4j.hander;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class UserContext {

    private String token;
    private String username;
    private String password;
    private String name;
    private String traceId;

    //当前线程(包括子线程以及线程池的中的子线程)的上下文
    private static ThreadLocal<UserContext> content = new TransmittableThreadLocal<>();

    public static UserContext get() {
        return content.get();
    }

    public static void set(UserContext context) {
        content.set(context);
    }

    public static void remove() {
        content.remove();
    }

}


