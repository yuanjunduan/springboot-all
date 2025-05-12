package min.jun.schedule.task;//package com.hualala.auth.config.schedule.task;
//
//import com.hualala.auth.config.apollo.HjdApolloConfig;
//import com.hualala.auth.config.schedule.CompleteScheduleConfig;
//import com.hualala.auth.config.schedule.TaskIDEnum;
//import com.hualala.auth.log.HllLog1;
//import com.hualala.auth.module.account.dao.ShopDao;
//import com.hualala.auth.utils.JsonUtils;
//import com.hualala.auth.utils.TimeUtil;
//import com.hualala.auth.utils.WarningHelp;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * @author Administrator
// */
//@Service
//public class ApolloGroupCheck extends AbstractScheduleTask {
//
//    @Autowired
//    private ShopDao shopDao;
//
//    @Override
//    public void execTask() {
//
////        Set<Long> dbGroupID = shopDao.queryAllGroupIDExist();
////        Set<Long> apolloGroupID = HjdApolloConfig.usedGroupIDSet;
////
////        HllLog1.info("【ApolloGroupCheck】:request:DB:{};Apollo: response:{}",JsonUtils.toJSONString(dbGroupID), JsonUtils.toJSONString(apolloGroupID));
////
////        Set<Long> reduceGroup = dbGroupID.stream().filter(item -> !apolloGroupID.contains(item)).collect(Collectors.toSet());
////        if (CollectionUtils.isEmpty(reduceGroup)) {
////
////            // 取消任务
////            CompleteScheduleConfig.cancel(this.taskID());
////            return;
////        }
////        WarningHelp.warning("需要设置白名单" + TimeUtil.currentMinTime(), JsonUtils.toJSONString(reduceGroup));
//    }
//
//    @Override
//    public String taskID() {
//        return TaskIDEnum.ApolloGroupCheck.getTaskID();
//    }
//
//    @Override
//    public boolean openFlag() {
//        return false;
//    }
//
//    /**
//     * 间隔 30s 检查一次
//     *
//     * @return
//     */
//    @Override
//    public String cronExpression() {
//        return "0 0 0-12 * * ?";
//    }
//
//}
