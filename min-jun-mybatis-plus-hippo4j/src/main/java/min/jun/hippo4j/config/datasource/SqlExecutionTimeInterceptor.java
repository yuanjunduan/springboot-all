package min.jun.hippo4j.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
@Component
@Slf4j
public class SqlExecutionTimeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            // 获取StatementHandler实例
            Object target = invocation.getTarget();
            MetaObject metaObject = SystemMetaObject.forObject(target);

            // 获取SQL语句
            String sql = (String) metaObject.getValue("delegate.boundSql.sql");
            Object parameterObject = metaObject.getValue("delegate.boundSql.parameterObject");

            // 计算耗时
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            // 打印SQL和参数以及执行时间
            log.info("Execution Time: {} ms SQL: {}  parameterObject:{}", executionTime, sql, parameterObject);
//            long endTime = System.currentTimeMillis();
//            long time = endTime - startTime;
//            if (time > 10 * 1) {
//                StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//                log.info("SQL Execute Time: " + time + "ms\nSQL: " + statementHandler.getBoundSql().getSql());
//            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}