package min.jun.hippo4j.algo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import min.jun.hippo4j.algo.domain.entity.AlgorithmDesc;
import min.jun.hippo4j.algo.mapper.AlgorithmDescMapper;
import min.jun.hippo4j.algo.service.AlgorithmDescService;
import org.springframework.stereotype.Service;


@Slf4j
@AllArgsConstructor
@Service
public class AlgorithmDescServiceImpl extends ServiceImpl<AlgorithmDescMapper, AlgorithmDesc> implements AlgorithmDescService {
}