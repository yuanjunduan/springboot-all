package min.jun;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@MapperScan({"min.jun.**.mapper"})
@SpringBootApplication(scanBasePackages = {"min.jun"})
public class MinJunMybatisApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(MinJunMybatisApplication.class, args);
        log.info("EvaluateApplication start success .......");
    }
}
