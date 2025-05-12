package min.jun.schedule.task;


import lombok.extern.slf4j.Slf4j;
import min.jun.schedule.TaskIDEnum;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class DBShopIdToRedisTask extends AbstractScheduleTask {


    @Override
    public void execTask() {
        log.info("before【DBShopIdToRedisTask】 add start ");
        //todo deal with self
        //XXXXXXXXXXXXXXXX
        log.info("after【DBShopIdToRedisTask】 all end");

        //正常的使用应该是一个触发的地方，不满足条件了 就应该终止
        // 取消任务
        //        CompleteScheduleConfig.cancel(TaskIDEnum.ApolloGroupCheckReset.getTaskID());
        //重新添加
        //        CompleteScheduleConfig.reset(getAddTask(TaskIDEnum.ApolloGroupCheckReset.getTaskID()));
    }

    @Override
    public String taskID() {
        return TaskIDEnum.QUERY_SHOP_DB_TO_REDIS.getTaskID();
    }

    @Override
    public boolean openFlag() {
        return false;
    }

    /**
     * 间隔30s 检查一次
     *
     * @return
     */
    @Override
    public String cronExpression() {
        return "0 0/30 * * * ?";
    }

}
