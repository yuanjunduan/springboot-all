package min.jun.config.hook;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderEventListener {


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    @Async  // 异步执行
    public <T> void onApplicationEvent(BaseEvent<T> event) {
        MessageSender.sendMQMessage(event);
    }
}