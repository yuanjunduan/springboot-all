package min.jun.config.datasource.plugins;

import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMPJBaseMapper<T> extends MPJBaseMapper<T> {

    int insertBatchSomeColumn(@Param("list") List<T> entityList);

    int InsertBatchEntity(@Param("list") List<T> entityList);

    int updateBatchById(@Param("list") List<T> entityList);
}
