package min.jun.hippo4j.algo.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


//"算法描述表")
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("vcs_algorithm_desc")
public class AlgorithmDesc extends BaseEntity {
    @Schema(description = "ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联算法Id")
    public Long algoId;

    @Schema(description = "芯片型号")
    private String chipType;

    @Schema(description = "服务架构")
    private String architecture;

    @Schema(description = "镜像端口")
    private Integer imagePort;

    @Schema(description = "边车Id")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    public Long sidecarId;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "预分配量")
    private Integer preAllotment;

    @Schema(description = "动态分配最大量")
    private Integer maxAllotment;
}
