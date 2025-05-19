package min.jun.user.api;

import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.jun.algo.mapper.AlgorithmMapper;
import min.jun.exception.BusinessException;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "用户", description = "用户")
@RequestMapping("/api/1.1/user")
public class UserApi {

    private final AlgorithmMapper algorithmMapper;

    @Trace(operationName = "consumer总入口")
    // 此注解创建的Span Type=Local。operationName指定该Span名（在Skywalking界面展示为Endpoint）,若不指定则使用方法名
//    @Tags({@org.apache.skywalking.apm.toolkit.trace.Tag(key = "orderid", value = "arg[0]"), @org.apache.skywalking.apm.toolkit.trace.Tag(key = "return", value = "returnedObj")})
    @PostMapping("/login")
    public String login() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @PostMapping("/throw")
    public void login2() {
        algorithmMapper.customerThrow();
        throw new BusinessException("", "");
    }

}
