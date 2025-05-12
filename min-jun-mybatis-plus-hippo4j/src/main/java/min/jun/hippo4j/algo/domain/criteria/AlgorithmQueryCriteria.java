package min.jun.hippo4j.algo.domain.criteria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import min.jun.hippo4j.utils.PageRequest;

@Data
public class AlgorithmQueryCriteria extends PageRequest {

    // 平台架构
//    private List<String> architecture;

    // 上架状态
    @Schema(description = "上架状态",defaultValue = "1")
    private String status;

    //租户id
//    private Long tenantId;

    //服务类型
//    private String serviceType;

    /**
     * 是否是视频流算法，1是图片流，2 是视频流，0是通用
     */
//    private List<Integer> videoAlgo;
}
