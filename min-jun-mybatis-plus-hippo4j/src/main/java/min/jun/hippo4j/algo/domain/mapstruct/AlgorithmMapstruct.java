package min.jun.hippo4j.algo.domain.mapstruct;


import min.jun.hippo4j.algo.domain.dto.AlgorithmDto;
import min.jun.hippo4j.algo.domain.entity.Algorithm;
import min.jun.hippo4j.algo.domain.vo.AlgorithmVo;
import min.jun.hippo4j.config.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlgorithmMapstruct extends BaseMapper<AlgorithmDto, Algorithm> {

    AlgorithmVo toVo(Algorithm entity);

    List<AlgorithmVo> toVo(List<Algorithm> entity);
}
