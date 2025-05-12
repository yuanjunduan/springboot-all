package min.jun.hippo4j.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    /**
     * 一个自定义的 OpenAPI 对象
     *
     * @return 一个自定义的 OpenAPI 对象
     */
    final String securitySchemeName = "Authorization";
//    @Bean
//    public OpenAPI customOpenAPI1() {
//        final String securitySchemeName = "Authorization";
//        return new OpenAPI()
//                .info(new Info().title("My API").version("v1"))
//
//                .components(new Components()
//                        .addSecuritySchemes(securitySchemeName,
//                                new SecurityScheme()
//                                        .name(securitySchemeName)
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("Bearer")
//                                        .bearerFormat("JWT")
//                                        .in(SecurityScheme.In.HEADER)));
//    }
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        // 设置 spring security jwt accessToken 认证的请求头 Authorization: Bearer xxx.xxx.xxx
                        .addSecuritySchemes("openApiSecurityScheme",
                                new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                                .scheme("Bearer")))
                // 设置标题、版本等信息
                .info(new Info()
                        .title("Fast Alden权限管理系统")
                        .version("0.0.1-SNAPSHOT")
                        .description("")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
