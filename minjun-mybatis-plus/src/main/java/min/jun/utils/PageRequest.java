package min.jun.utils;

//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分页请求
 */
@Data
public class PageRequest {

    /**
     * 模糊查询参数
     */
//   @Schema(description = "模糊查询字段")
//    private String blurry;

    /**
     * 当前页号
     */
    @Schema(description = "当前页号",defaultValue = "1")
    private Integer page = 1;

    /**
     * 页面大小
     */
    @Schema(description = "页面大小",defaultValue = "20")
    private Integer size = 20;


//    @Schema(description = "排序字段，逗号前是排序字段，逗号后为升序还是降序 asc,des")
//    private String sort;

    /**
     * 排序字段
     */
//    private String sortField;

}
