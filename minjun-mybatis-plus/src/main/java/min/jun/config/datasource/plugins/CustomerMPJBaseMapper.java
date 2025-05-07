package min.jun.config.datasource.plugins;

import com.github.yulichang.base.MPJBaseMapper;
import min.jun.algo.domain.entity.Algorithm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMPJBaseMapper<T> extends MPJBaseMapper<T> {

    int insertBatchSomeColumn(@Param("list") List<Algorithm> entityList);

    int InsertBatchEntity(@Param("list") List<Algorithm> entityList);
}
