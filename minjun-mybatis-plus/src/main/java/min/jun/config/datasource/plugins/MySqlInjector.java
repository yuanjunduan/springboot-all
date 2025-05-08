package min.jun.config.datasource.plugins;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.github.yulichang.injector.MPJSqlInjector;

import java.util.List;

/**
 * 自定义Sql注入
 *
 * @author: yh
 * @date: 2022/8/30
 */
public class MySqlInjector extends MPJSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        //默认数据库默认值不生效 需要在实体类设置
        methodList.add(new InsertBatchSomeColumn());
        // 添加你的批量插入方法
        methodList.add(new MysqlInsertAllBatch());
        methodList.add(new MysqlUpdateBatchById());
        return methodList;
    }
}
