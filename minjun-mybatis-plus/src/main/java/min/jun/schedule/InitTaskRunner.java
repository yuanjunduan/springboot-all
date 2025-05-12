package min.jun.schedule;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * @author Administrator
 */
@Component
@Slf4j
public class InitTaskRunner implements CommandLineRunner {

    @Autowired
    private CompleteScheduleConfig config;

    @Override
    public void run(String... strings) {
        log.info("【InitTaskRunner】start");
        config.init();
        log.info("【InitTaskRunner】end");
    }
}
