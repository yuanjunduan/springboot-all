package min.jun.hippo4j.config;


import cn.hutool.json.JSONUtil;
import min.jun.hippo4j.common.ApiResult;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@RestControllerAdvice(basePackages = "min.jun")
public class RespAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 如果接口返回的类型本身就是Result那就没有必要进行额外的操作，返回false
        if (returnType.getGenericParameterType().equals(ApiResult.class)) {
            return false;
        }
        // 对类或者方法上面注解了@RestController 或者 @ResponseBody 的方法统一处理
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), RestController.class) || returnType.hasMethodAnnotation(ResponseBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 防止重复包裹的问题出现
        if (body instanceof ApiResult) {
            return body;
        }
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            // 将数据包装在Result里后，再转换为json字符串响应给前端
            return JSONUtil.toJsonStr(ApiResult.ok(body));
        }
        return ApiResult.ok(body);
    }
}
