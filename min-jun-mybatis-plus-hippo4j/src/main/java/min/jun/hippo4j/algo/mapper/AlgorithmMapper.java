package min.jun.hippo4j.algo.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;

import min.jun.hippo4j.algo.domain.entity.Algorithm;
import min.jun.hippo4j.config.datasource.plugins.CustomerMPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface AlgorithmMapper extends CustomerMPJBaseMapper<Algorithm> {

}
