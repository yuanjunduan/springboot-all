### 自定义批量插入
public class MySqlInjector extends MPJSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        //默认数据库默认值不生效 需要在实体类设置
        methodList.add(new InsertBatchSomeColumn());
        // 添加你的批量插入方法
        methodList.add(new MysqlInsertAllBatch());
        return methodList;
    }
}

### 分页插件、动态修改表名
#### 动态修改表名====一定要放到分页插件前面  否则在selectPage 时 count计数表名不变
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


## skywalking

## skywalking agent
https://archive.apache.org/dist/skywalking/java-agent/


### skywalking server
https://archive.apache.org/dist/skywalking/