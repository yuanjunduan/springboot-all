package min.jun.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // 启用 Java 8 时间模块，支持 LocalDateTime / LocalDate 等
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        // 定义统一的时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // 注册 LocalDateTime 序列化器
        javaTimeModule.addSerializer(java.time.LocalDateTime.class,
                new LocalDateTimeSerializer(formatter));

        // 如果你也需要处理 LocalDate / LocalTime / ZonedDateTime，也可以加上
        // javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(formatter));
        // javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(formatter));

        // 创建 ObjectMapper，并启用上述模块
        ObjectMapper mapper = builder
                .createXmlMapper(false)
                .modules(javaTimeModule) // 注册 Java 8 时间模块
                .build();

        // Long -> String，防止前端精度丢失
        SimpleModule longToStringModule = new SimpleModule();
        longToStringModule.addSerializer(Long.class, ToStringSerializer.instance);
        longToStringModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        mapper.registerModule(longToStringModule);

        // 忽略反序列化时的未知字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 禁用时间戳格式输出（LocalDateTime 等不会变成 long 类型）
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 设置默认的 Date 格式（兼容旧的 java.util.Date 类型）
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        mapper.setDateFormat(sdf);

        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        return mapper;
    }
}
