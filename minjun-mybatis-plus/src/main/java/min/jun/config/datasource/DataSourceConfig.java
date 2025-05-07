package min.jun.config.datasource;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(DruidConfig druidConfig) throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        log.info("dataSource: {}", druidConfig);
        BeanUtils.copyProperties(druidConfig, datasource);
        try {

            // 设置 dbType
            if (!StrUtil.equals(druidConfig.getDbType(), DbType.MYSQL.getDb())) {
                datasource.setDbType(druidConfig.getDbType());
            }

//            if (!StringUtils.equals(activeEnv, "prod")) {
//                //  打开removeAbandoned功能 -->
//                datasource.setRemoveAbandoned(true);
//                // 关闭abanded连接时输出错误日志 -->
//                datasource.setLogAbandoned(true);
//                // 1800秒，也就是30分钟  如果连接超过30分钟未关闭，就会被强行回收
//                datasource.setRemoveAbandonedTimeout(1800);
//            }

            datasource.setKeepAlive(true);
            Class.forName(druidConfig.getDriverClassName());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        datasource.init();
        return datasource;
    }

    /**
     * 去除监控页面底部广告
     */
//    @Bean
//    public FilterRegistrationBean removeDruidAdFilterRegistrationBean(DruidConfig properties) {
//        // 获取web监控页面的参数
//        DruidConfig.StatViewServlet config = properties.getStatViewServlet();
//        // 提取common.js的配置路径
//        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
//        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
//
//        final String filePath = "support/http/resources/js/common.js";
//
//        // 创建filter进行过滤
//        Filter filter =
//                new Filter() {
//                    @Override
//                    public void init(FilterConfig filterConfig) throws ServletException {
//                    }
//
//                    @Override
//                    public void doFilter(
//                            ServletRequest request, ServletResponse response, FilterChain chain)
//                            throws IOException, ServletException {
//                        chain.doFilter(request, response);
//                        // 重置缓冲区，响应头不会被重置
//                        response.resetBuffer();
//                        // 获取common.js
//                        String text = Utils.readFromResource(filePath);
//                        // 正则替换banner, 除去底部的广告信息
//                        text = text.replaceAll("<a.*?banner\"></a><br/>", "");
//                        text = text.replaceAll("powered.*?shrek.wang</a>", "");
//                        response.getWriter().write(text);
//                    }
//
//                    @Override
//                    public void destroy() {
//                    }
//                };
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(filter);
//        registrationBean.addUrlPatterns(commonJsPattern);
//        return registrationBean;
//    }

}
