package min.jun.algo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.jun.algo.domain.entity.AlgorithmDesc;
import min.jun.algo.service.AlgorithmDescService;
import min.jun.algo.mapper.AlgorithmDescMapper;
import org.springframework.stereotype.Service;


@Slf4j
@AllArgsConstructor
@Service
public class AlgorithmDescServiceImpl extends ServiceImpl<AlgorithmDescMapper, AlgorithmDesc> implements AlgorithmDescService {
}