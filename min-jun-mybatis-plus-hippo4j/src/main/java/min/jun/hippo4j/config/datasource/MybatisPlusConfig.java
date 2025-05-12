package min.jun.hippo4j.config.datasource;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

import min.jun.hippo4j.config.datasource.plugins.MySqlInjector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(@Value("${spring.datasource.druid.db-type}") String dbType) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();


        // ===================动态修改表名====一定要放到分页插件前面 【PaginationInnerInterceptor】========
        // ===================动态修改表名====一定要放到分页插件前面 【PaginationInnerInterceptor】========
        // ===================动态修改表名====一定要放到分页插件前面  【PaginationInnerInterceptor】========
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        dynamicTableNameInnerInterceptor.setTableNameHandler(new TableNameHandler() {
            @Override
            public String dynamicTableName(String sql, String tableName) {

                Object tablePre = RequestDataHelper.getRequestData(RequestDataHelper.TABLE_PRE);
                if (tablePre instanceof Integer) {
                    tableName = tableName + "_" + tablePre;
                }

                return tableName;
            }
        });
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);

        // 分页设置
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.getDbType(dbType));
        paginationInterceptor.setMaxLimit(300L);
        // //溢出总页数处理 默认查询第一页
// 		paginationInterceptor.setOverflow(true);
        interceptor.addInnerInterceptor(paginationInterceptor);


        return interceptor;
    }

    @Bean
    public ISqlInjector iSqlInjector() {
        return new MySqlInjector();
    }


}