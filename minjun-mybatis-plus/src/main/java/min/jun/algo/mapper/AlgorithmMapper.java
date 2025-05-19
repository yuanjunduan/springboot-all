package min.jun.algo.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import min.jun.algo.domain.entity.Algorithm;
import min.jun.config.datasource.plugins.CustomerMPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface AlgorithmMapper extends CustomerMPJBaseMapper<Algorithm> {

    @Select("select * from ddd")
    void customerThrow();
}
