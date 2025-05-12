package min.jun.hippo4j.api;

import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "用户", description = "用户")
@RequestMapping("/api/1.1/user")
public class UserApi {


    @PostMapping("/login")
    public String login() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
