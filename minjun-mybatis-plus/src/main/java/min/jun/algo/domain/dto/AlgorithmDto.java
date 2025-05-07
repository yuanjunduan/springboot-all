package min.jun.algo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AlgorithmDto {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "算法编码")
    public String algoCode;

    @Schema(description = "算法名称")
    public String algoName;

    @Schema(description = "算法版本")
    public String algoVersion;

    @Schema(description = "算法厂家")
    public String algoOwner;

    @Schema(description = "算法类型")
    public String algoType;

    @Schema(description = "算法描述")
    public String algoDescribe;

    @Schema(description = "算法声明")
    public String algoDeclare;

    @Schema(description = "封面路径")
    public String algoCoverPath;

    @Schema(description = "上架状态，已发布，未发布")
    public Integer status;

    @Schema(description = "算法详细描述")
    private String algoDescribeDetail;

    @Schema(description = "上架时间")
    public Date deployTime;

    @Schema(description = "服务量")
    private Integer serviceNum;

    @Schema(description = "浏览量")
    private Integer viewNum;

    @Schema(description = "申请状态")
    private Integer applyStatus;

    @Schema(description = "算法图片列表")
    public List<String> photos;

    @Schema(description = "服务类型")
    private String serviceType;

    @Schema(description = "算法API文档")
    private String algoApiDoc;

    @Schema(description = "业务类型")
    private String businessType;

    @Schema(description = "是否是视频流算法，1是图片流，2 是视频流，0是通用")
    private Integer videoAlgo;

}
