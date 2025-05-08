package min.jun.config.datasource.plugins;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class MysqlUpdateBatchById extends AbstractMethod {

    public MysqlUpdateBatchById() {
        super("updateBatchById");
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        final String sql = "<script>" + buildUpdateSql(tableInfo) + "</script>";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, methodName, sqlSource);
    }


    /**
     *
     * 该语法不太懂
     *
     * UPDATE vcs_algorithm
     * SET
     *     algo_code = CASE id
     *         WHEN ? THEN ?
     *         WHEN ? THEN ?
     *     END,
     *     algo_name = CASE id
     *         WHEN ? THEN ?
     *         WHEN ? THEN ?
     *     END
     * WHERE id IN (?, ?)
     */
    private String buildUpdateSql(TableInfo tableInfo) {
        StringBuilder updateSql = new StringBuilder();

        // 开始 UPDATE 语句
        updateSql.append("UPDATE ").append(tableInfo.getTableName()).append(" SET ");

        // 动态添加 SET 部分
        for (TableFieldInfo field : tableInfo.getFieldList()) {
            if (!field.isLogicDelete() && !field.isVersion()) { // 跳过逻辑删除和版本控制字段
                updateSql.append(field.getColumn()).append(" = CASE id ");

                updateSql.append("<foreach collection=\"list\" item=\"item\">");
                updateSql.append("WHEN #{item.").append(tableInfo.getKeyProperty()).append("} THEN ");
                updateSql.append("<if test=\"item.").append(field.getProperty()).append(" != null\">");
                updateSql.append("#{item.").append(field.getProperty()).append("}");
                updateSql.append("</if>");
                updateSql.append("<if test=\"item.").append(field.getProperty()).append(" == null\">");
                updateSql.append(field.getColumn()); // 如果为null，则保留原值
                updateSql.append("</if>");
                updateSql.append("</foreach>");

                updateSql.append(" ELSE ").append(field.getColumn()).append(" END,");
            }
        }

        // 删除最后一个逗号
        if (updateSql.charAt(updateSql.length() - 1) == ',') {
            updateSql.deleteCharAt(updateSql.length() - 1);
        }

        // 添加 WHERE 子句
        updateSql.append(" WHERE id IN ");
        updateSql.append("<foreach collection=\"list\" item=\"item\" open=\"(\" close=\")\" separator=\",\">")
                .append("#{item.").append(tableInfo.getKeyProperty()).append("}")
                .append("</foreach>");

        return updateSql.toString();
    }


    /**
     *
     */

//    private String buildUpdateSql(TableInfo tableInfo) {
//        StringBuilder updateSql = new StringBuilder();
//
//        // 开始 foreach 循环
//        updateSql.append("<foreach collection=\"list\" item=\"item\" separator=\";\">");
//
//        updateSql.append("UPDATE ").append(tableInfo.getTableName()).append(" ");
//        updateSql.append("<set>");
//
//        // 主键字段
//        String keyProperty = tableInfo.getKeyProperty();
//        String keyColumn = tableInfo.getKeyColumn();
//
//        // 字段列表
//        for (TableFieldInfo field : tableInfo.getFieldList()) {
//            String property = field.getProperty();
//            String column = field.getColumn();
//            updateSql.append(String.format(
//                    "<if test=\"item.%s != null\">%s = #{item.%s},</if>", property, column, property));
//        }
//
//        // 结束 set 和 where 条件
//        updateSql.append("</set>");
//        updateSql.append(" WHERE ").append(keyColumn).append(" = #{item.").append(keyProperty).append("}");
//
//        // 结束 foreach
//        updateSql.append("</foreach>");
//
//        return updateSql.toString();
//    }
}