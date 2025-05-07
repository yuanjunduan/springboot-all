package min.jun.algo.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.github.yulichang.base.MPJBaseMapper;
import min.jun.algo.domain.entity.AlgorithmDesc;
import min.jun.algo.domain.entity.AlgorithmSpec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface AlgorithmSpecMapper extends MPJBaseMapper<AlgorithmDesc> {


    @Select("<script>"
            + "select * from vcs_algorithm_spec where id in ("
            + "<foreach collection='ids' item='item' separator=','>"
            + " #{item}"
            + "</foreach>"
            + ")"
            + "</script>")
    List<AlgorithmSpec> findByIds(@Param("ids") List<Long> ids);
}
