package min.jun.algo.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.github.yulichang.base.MPJBaseMapper;
import min.jun.algo.domain.entity.AlgorithmDesc;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface AlgorithmDescMapper extends MPJBaseMapper<AlgorithmDesc> {
}
