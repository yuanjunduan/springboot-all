package min.jun.config.hook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

@Slf4j
public class MqMessageCommitHook extends TransactionSynchronizationAdapter {

    private final Runnable action;

    public MqMessageCommitHook(Runnable action) {
        this.action = action;
    }

    @Override
    public void afterCommit() {
        try {
            //todo 这里可以修改为异步处理
            action.run();
        } catch (Exception e) {
            // 记录日志或进行补偿处理
            log.error("Failed to execute afterCommit logic", e);
        }
    }


    @Override
    public void beforeCommit(boolean readOnly) {
        log.error("================beforeCommit============");
    }

    @Override
    public void flush() {
        log.error("================flush============");
    }

    @Override
    public void beforeCompletion() {
        log.error("================beforeCompletion============");
    }

    @Override
    public void afterCompletion(int status) {
        if (TransactionSynchronization.STATUS_COMMITTED == status) {
            log.info("=========afterCompletion======事务提交==============");
        } else if (TransactionSynchronization.STATUS_ROLLED_BACK == status) {
            log.info("========afterCompletion=========事务回滚============");
        }
    }

}