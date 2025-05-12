package min.jun.hippo4j.config.swagger;//package min.jun.config.swagger;
//
//import org.springdoc.core.customizers.OpenApiCustomiser;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import io.swagger.v3.oas.models.OpenAPI;
//import org.springdoc.core.customizers.OpenApiCustomiser;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//import io.swagger.v3.oas.models.OpenAPI;
//import org.springdoc.core.customizers.OpenApiCustomiser;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class SwaggerUiConfig {
//    @Bean
//    public OpenApiCustomiser swaggerUiConfiguration() {
//        return openApi -> {
//            String customJs = "console.log('Hello from custom JS');";
//
//            if (openApi.getExtensions() == null) {
//                openApi.setExtensions(new HashMap<>());
//            }
//
//            // ✅ 正确的 key 是 x-springdoc-js
//            openApi.getExtensions().put("x-springdoc-js", escapeJavaScript(customJs));
//        };
//    }
//
//    private String escapeJavaScript(String script) {
//        return script.replace("\n", "\\n").replace("\"", "\\\"");
//    }
////
////    @Bean
////    public OpenApiCustomiser swaggerUiConfiguration() {
////        return openApi -> {
////            String customJs = "window.onload = function() {\n" +
////                    "  const ui = window.ui;\n" +
////                    "  fetch('/api/1.1/user/login', {\n" +
////                    "    method: 'POST',\n" +
////                    "    headers: {\n" +
////                    "      'Content-Type': 'application/json'\n" +
////                    "    },\n" +
////                    "    body: JSON.stringify({username: 'admin', password: 'password'})\n" +
////                    "  })\n" +
////                    "  .then(response => response.json())\n" +
////                    "  .then(data => {\n" +
////                    "    const token = data.data;\n" +
////                    "    if (token) {\n" +
////                    "      ui.preauthorizeApiKey('Authorization', 'Bearer ' + token);\n" +
////                    "    }\n" +
////                    "  })\n" +
////                    "  .catch(err => console.error('Token 获取失败:', err));\n" +
////                    "};";
////
////            // 确保 extensions 不为 null
////            if (openApi.getExtensions() == null) {
////                openApi.setExtensions(new HashMap<>());
////            }
////
////            // ✅ 使用正确的 key 名称：x-springdoc-js
////            openApi.getExtensions().put("x-springdoc-js", escapeJavaScript(customJs));
////        };
////    }
////
////    private String escapeJavaScript(String script) {
////        return script.replace("\n", "\\n").replace("\"", "\\\"");
////    }
//}