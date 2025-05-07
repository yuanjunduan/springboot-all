package min.jun.common;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * 重写API接口的基础返回类
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String success = "000";
    private static final String ErrCode = "000_1";

    private String code;
    private String msg;
    private T data;
 
    @JsonIgnore
    public boolean isSuccess() {
        return StrUtil.equals(this.getCode(), success);
    }

    /**
     * 成功(code=0)
     * 示例: R.ok("登录成功")
     */
    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult<T>(success, "", data);
    }

    public static <T> ApiResult<T> fail(String code, String msg) {
        return new ApiResult<T>(code, msg, null);
    }

    public static <T> ApiResult<T> fail(String msg) {
        return new ApiResult<T>(ErrCode, msg, null);
    }


    public void to(ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter();) {
            String message = JSONUtil.toJsonStr(this);
            response.setBufferSize(message.getBytes(StandardCharsets.UTF_8).length);
            response.flushBuffer();
            out.flush();
        } catch (Exception e) {
            log.error("输出JSON出错", e);
        }
    }
}
