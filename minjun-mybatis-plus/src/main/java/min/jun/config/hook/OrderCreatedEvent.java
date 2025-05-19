package min.jun.config.hook;

import lombok.extern.slf4j.Slf4j;
import min.jun.algo.domain.entity.Algorithm;


@Slf4j
public class OrderCreatedEvent extends BaseEvent<Algorithm> {

    public OrderCreatedEvent(Algorithm data, String msgType, String uniqueKey) {
        super(data, msgType, uniqueKey);
    }
}