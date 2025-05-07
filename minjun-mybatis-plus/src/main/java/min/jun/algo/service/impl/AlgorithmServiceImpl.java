package min.jun.algo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.jun.algo.domain.criteria.AlgorithmQueryCriteria;
import min.jun.algo.domain.entity.Algorithm;
import min.jun.algo.domain.entity.AlgorithmDesc;
import min.jun.algo.domain.mapstruct.AlgorithmMapstruct;
import min.jun.algo.domain.vo.AlgorithmVo;
import min.jun.algo.mapper.AlgorithmMapper;
import min.jun.algo.service.AlgorithmService;
import min.jun.utils.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@AllArgsConstructor
@Service
public class AlgorithmServiceImpl extends ServiceImpl<AlgorithmMapper, Algorithm> implements AlgorithmService {

    private final AlgorithmMapstruct algorithmMapstruct;

    @Override
    public Object queryListPage(AlgorithmQueryCriteria algoQuery) {
        MPJLambdaWrapper<Algorithm> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.selectAll(Algorithm.class);

//        if (ObjectUtil.isNotEmpty(algoQuery.getArchitecture()) || ObjectUtil.isNotEmpty(algoQuery.getChipType())) {
////            queryWrapper.selectAs(AlgorithmDesc::getImagePort, AlgorithmAuthAlgo::getAlgoName);
//            queryWrapper.leftJoin(AlgorithmDesc.class, AlgorithmDesc::getAlgoId, Algorithm::getId);
//            queryWrapper.groupBy(AlgorithmDesc::getAlgoId);
//        }

//        queryWrapper.eq(ObjectUtil.isNotEmpty(algoQuery.getServiceType()), Algorithm::getServiceType, algoQuery.getServiceType());
//        queryWrapper.in(ObjectUtil.isNotEmpty(algoQuery.getArchitecture()), AlgorithmDesc::getArchitecture, algoQuery.getArchitecture());
        queryWrapper.eq(ObjectUtil.isNotEmpty(algoQuery.getStatus()), Algorithm::getStatus, algoQuery.getStatus());
//        queryWrapper.in(ObjectUtil.isNotEmpty(algoQuery.getVideoAlgo()), Algorithm::getVideoAlgo, algoQuery.getVideoAlgo());
//        if (StrUtil.isNotEmpty(algoQuery.getBlurry())) {
//            queryWrapper.and(wrapper -> wrapper
//                    .like(Algorithm::getAlgoCode, algoQuery.getBlurry())
//                    .or()
//                    .like(Algorithm::getAlgoName, algoQuery.getBlurry())
//            );
//        }
        //排序处理
//        queryWrapper.orderByDesc(ObjectUtil.isNotEmpty(algoQuery.getSortField()), algoQuery.getSortField());
        queryWrapper.orderByDesc(Algorithm::getId);

        //方案一
//        Page<Algorithm> page = this.getBaseMapper().selectPage(new Page<>(algoQuery.getPage(), algoQuery.getSize()), queryWrapper);
//        PageResult<AlgorithmVo> result = new PageResult<>();
//        result.setTotalElements(page.getTotal());
//        result.setContent(algorithmMapstruct.toVo(page.getRecords()));

        //方案二
        Page<AlgorithmVo> algorithmVoPage = this.getBaseMapper().selectJoinPage(new Page<>(algoQuery.getPage(), algoQuery.getSize()), AlgorithmVo.class, queryWrapper);
        return new PageResult<>(algorithmVoPage);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveBatch(Integer size) {
        ArrayList<Algorithm> objects = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Algorithm algorithm = new Algorithm();
            algorithm.setAlgoCode(i + "");
            algorithm.setAlgoName("name" + i);
            algorithm.setServiceNum(222);
            algorithm.setAlgoApiDoc("xxxxxxxxxxxxxxxxxxxxx");
            algorithm.setAlgoDescribe("xxxxxxxxxxxxxxxxxxxxx");
            algorithm.setAlgoDeclare("xxxxxxxxxxxxxxxxxxxxx");
            algorithm.setAlgoDescribeDetail("xxxxxxxxxxxxxxxxxxxxx");
            algorithm.setAlgoType("1");
            algorithm.setAlgoVersion("xxxxxxxxxxxxxxxxxxxxx");
            algorithm.setAlgoOwner("xxxxxxxxxxxxxxxxx");
            algorithm.setStatus(99);
            algorithm.setViewNum(9999);
            algorithm.setUpdateBy("ddddd");
            algorithm.setCreateBy("ddddd");
            objects.add(algorithm);
        }
        this.getBaseMapper().InsertBatchEntity(objects);
//       this.getBaseMapper().insertBatchSomeColumn(objects);
//        boolean b = saveBatch(objects, 5);
    }


    public void update(Algorithm algorithm) {
        LambdaUpdateWrapper<Algorithm> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(Algorithm::getAlgoDescribe, algorithm.getAlgoDescribe())
                .eq(Algorithm::getId, algorithm.getId());
//        this.getBaseMapper().update(null, updateWrapper);
        update(updateWrapper);
    }


    @Override
    public Object queryListPageChangeTableName(AlgorithmQueryCriteria algoQuery) {
        MPJLambdaWrapper<Algorithm> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper.selectAll(Algorithm.class);

        queryWrapper.leftJoin(AlgorithmDesc.class, AlgorithmDesc::getAlgoId, Algorithm::getId);
        queryWrapper.eq(ObjectUtil.isNotEmpty(algoQuery.getStatus()), Algorithm::getStatus, algoQuery.getStatus());

        queryWrapper.orderByDesc(Algorithm::getId);

        //方案一
        Page<Algorithm> page = this.getBaseMapper().selectPage(new Page<>(algoQuery.getPage(), algoQuery.getSize()), queryWrapper);
        PageResult<AlgorithmVo> result = new PageResult<>();
        result.setTotalElements(page.getTotal());
        result.setContent(algorithmMapstruct.toVo(page.getRecords()));



        return result;

        //方案二
//        Page<AlgorithmVo> algorithmVoPage = this.getBaseMapper().selectJoinPage(new Page<>(algoQuery.getPage(), algoQuery.getSize()), AlgorithmVo.class, queryWrapper);
//
//        return new PageResult<>(algorithmVoPage);

    }


}