package min.jun.config.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource.druid")
@Data
public class DruidConfig {

    private String driverClassName;

    private String url;

    private String dbType;

    private String username;

    private String password;

    private int initialSize;

    private boolean keepAlive;

    private int maxActive;

    private long maxEvictableIdleTimeMillis;

    private long minEvictableIdleTimeMillis;

    private long maxWait;

    private int minIdle;

    private long phyTimeoutMillis;

    private boolean poolPreparedStatements;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean testWhileIdle;

    private long timeBetweenEvictionRunsMillis;

    private String validationQuery;

    private String filters;
    private int maxPoolPreparedStatementPerConnectionSize;
    private boolean useGlobalDataSourceStat;
    private String connectionProperties;
    private StatViewServlet StatViewServlet;

    @Data
    public static class StatViewServlet {
        private boolean enabled;
        private String urlPattern;
        private String allow;
        private String deny;
        private String loginUsername;
        private String loginPassword;
        private String resetEnable;
    }
}
