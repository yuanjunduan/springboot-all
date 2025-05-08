package min.jun.algo.api;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.jun.algo.domain.criteria.AlgorithmQueryCriteria;
import min.jun.algo.service.AlgorithmService;
import min.jun.config.datasource.RequestDataHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
//@Api(tags = "算法", description = "算法")
@Tag(name = "算法", description = "算法")
@RequestMapping("/api/1.1/algo")
public class AlgorithmApi {

    private final AlgorithmService algorithmService;

    //    @VcsLog(module = MODEL_NAME, desc = "分页查询任务列表")
    @GetMapping("/list")
    @Operation(summary = "分页查询任务列表", description = "分页查询任务列表")
//    @ApiOperation("")
    public Object queryTaskList(AlgorithmQueryCriteria criteria) {
        RequestDataHelper.setRequestData(RequestDataHelper.TABLE_PRE, 1);
        return algorithmService.queryListPage(criteria);
    }

    @GetMapping("/list/tableName")
    @Operation(summary = "ChangeTableName", description = "ChangeTableName")
//    @ApiOperation("")
    public Object tableName(AlgorithmQueryCriteria criteria) {
        RequestDataHelper.setRequestData(RequestDataHelper.TABLE_PRE, 1);
        return algorithmService.queryListPageChangeTableName(criteria);
    }


    @GetMapping("/save/{size}")
    @Operation(summary = "批量保存", description = "批量保存")
    public void saveBatch(@PathVariable Integer size) {
        RequestDataHelper.setRequestData(RequestDataHelper.TABLE_PRE, 1);
        algorithmService.saveBatch(size);
    }

    @GetMapping("/update/{size}")
    @Operation(summary = "批量保存", description = "批量保存")
    public void updateBatch(@PathVariable Integer size) {
        algorithmService.updateBatch(size);
    }
}
