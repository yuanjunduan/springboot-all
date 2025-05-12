package min.jun.schedule.task;//package com.hualala.auth.config.schedule;
//
//import com.hualala.auth.utils.log.ElkBean;
//import com.hualala.auth.utils.log.ElkLogger;
//import org.springframework.stereotype.Service;
//
//import static com.hualala.auth.config.schedule.CompleteScheduleConfig.getAddTask;
//
///**
// * @author Administrator
// */
//@Service
//public class ApolloGroupCheckReset extends AbstractScheduleTask {
//
//    @Override
//    public void execTask() {
//        ElkLogger.info(ElkBean.builder()
//                .uniqueKey("ApolloGroupCheckReset")
//                .methodDesc("InitTaskRunner start")
//                .build());
//
//        CompleteScheduleConfig.reset(getAddTask(TaskIDEnum.ApolloGroupCheckReset.getTaskID()));
//    }
//
//    @Override
//    public String taskID() {
//        return TaskIDEnum.ApolloGroupCheckReset.getTaskID();
//    }
//
//    @Override
//    public boolean openFlag() {
//        return false;
//    }
//
//    @Override
//    public String cronExpression() {
//        return "* * 1 * * ?";
//    }
//
//}
