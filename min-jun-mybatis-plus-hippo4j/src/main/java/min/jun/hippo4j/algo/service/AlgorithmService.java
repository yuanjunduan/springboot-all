package min.jun.hippo4j.algo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import min.jun.hippo4j.algo.domain.criteria.AlgorithmQueryCriteria;
import min.jun.hippo4j.algo.domain.entity.Algorithm;


public interface AlgorithmService extends IService<Algorithm> {
    Object queryListPage(AlgorithmQueryCriteria criteria);
    Object queryListPageChangeTableName(AlgorithmQueryCriteria criteria);

    void saveBatch(Integer size);

    public void updateBatch(Integer size);
}
