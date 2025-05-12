package min.jun.hippo4j.algo.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

//"算法规格表")
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("vcs_algorithm_spec")
public class AlgorithmSpec extends BaseEntity {
    @Schema(description = "ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "关联算法ID")
    private Long algoId;

    @Schema(description = "算法描述Id")
    private Long descId;

    @Schema(description = "cpu核数")
    private Integer cpu;

    @Schema(description = "内存大小")
    private Integer memory;

    @Schema(description = "卡数量")
    private Integer card;

    @Schema(description = "单卡最小显存")
    private Float minGraMemory;

    @Schema(description = "性能")
    private Integer performance;

    @Schema(description = "单位")
    private Integer unit;

    private String extensionParam; // JSON 字段作为字符串存储

    @TableField(exist = false)
    private String algoCode;
}
