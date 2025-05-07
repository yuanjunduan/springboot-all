package min.jun.hander;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;


/*
 * 描述：  mybatisplus自动填充添加/更新时间字段
 */
@Slf4j
@Component
public class CustomerObjectHandler implements MetaObjectHandler {

    /**
     * insert操作时要填充的字段
     * 使用示例: @TableField(fill = FieldFill.INSERT)
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //根据属性名字设置要填充的值
        this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, LocalDateTime.now());
        String createBy = UserContext.get() == null ? "" : UserContext.get().getUsername();
        String updateBy = createBy;

        this.strictInsertFill(metaObject, "createBy", String.class, createBy);
        this.strictInsertFill(metaObject, "updateBy", String.class, updateBy);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * update操作时要填充的字段
     * 使用示例: @TableField(fill = FieldFill.INSERT_UPDATE)
     * 注意事项: 无法获取到泛型AlgoHosts, 自动填充将失效
     * hostsService.update(new UpdateWrapper<AlgoHosts>().eq("id",6).set("algorithm_config",""));
     * 解决方法:
     * hostsService.update(new AlgoHosts(),new UpdateWrapper<AlgoHosts>().eq("id",6).set("algorithm_config",""));
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedAt", new Date(), metaObject);
        String updateBy = UserContext.get() == null ? "" : UserContext.get().getUsername();

        if (StrUtil.isEmpty(updateBy)) {
            Object createBy = this.getFieldValByName("createBy", metaObject);
            if (createBy != null) {
                updateBy = createBy.toString();
            }
        }
        this.setFieldValByName("updateBy", updateBy, metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
