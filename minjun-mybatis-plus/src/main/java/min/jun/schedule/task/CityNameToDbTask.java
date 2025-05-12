package min.jun.schedule.task;//package com.hualala.auth.config.schedule.task;
//
//import com.alibaba.nacos.common.utils.UuidUtils;
//import com.hualala.app.shop.grpc.ShopBaseInfoData;
//import com.hualala.auth.config.schedule.TaskIDEnum;
//import com.hualala.auth.log.DcLog;
//import com.hualala.auth.module.account.dao.ShopDao;
//import com.hualala.auth.module.account.model.AuthShop;
//import com.hualala.auth.remote.ShopBaseRemote;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * @author Administrator
// */
//@Service
//public class CityNameToDbTask extends AbstractScheduleTask {
//
//    @Autowired
//    private ShopDao shopDao;
//    @Autowired
//    private ShopBaseRemote shopBaseRemote;
//    @Value("${spring.profiles.active}")
//    private String active;
//    private static final long loopNum = 99999L;
//
//
//    private AtomicLong count = new AtomicLong(0);
//
//
//    @Override
//    public void execTask() {
//        if (!StringUtils.equals("pre", active)) {
//            return;
//        }
//
//        long shopAndIDFirst = 1L;
//        for (; count.get() < loopNum; count.getAndIncrement()) {
//
//
//            try {
//                Thread.sleep(new Random().nextInt(1000));
//            } catch (InterruptedException e) {
//            }
//            List<AuthShop> authShops = shopDao.queryShopNoCity(shopAndIDFirst);
//            if (CollectionUtils.isEmpty(authShops) || count.get() >= loopNum) {
//                DcLog.info("CityNameToDbTask执行完成");
//                // 取消任务
//                //CompleteScheduleConfig.cancel(this.taskID());
//                return;
//            }
//
//            shopAndIDFirst = authShops.get(authShops.size() - 1).getId();
//
//            for (AuthShop authShop : authShops) {
//                if (StringUtils.contains(authShop.getCityName(), "北京")
//                        || StringUtils.contains(authShop.getProvinceName(), "北京")
//                        || StringUtils.contains(authShop.getProvinceName(), "-")
//                        || StringUtils.contains(authShop.getCityName(), "-")) {
//
//                    try {
//                        ShopBaseInfoData.Shop shop = shopBaseRemote.queryOneShopNoException(UuidUtils.generateUuid(), authShop.getGroupID(), authShop.getShopID());
//                        if (null != shop) {
//                            shopDao.updateCity(shop.getNewCityID(), shop.getNewCityName(), shop.getNewProvinceName(), shop.getNewProvinceID(),
//                                    shop.getNewDistrictName(), shop.getNewDistrictID(), authShop.getId());
//                        }
//                    } catch (Exception e) {
//                        DcLog.error("auth-005-001", "查询店铺异常;shopID:{};异常：{}", authShop.getShopID(), e);
//                    }
//
//                    try {
//                        Thread.sleep(new Random().nextInt(1000));
//                    } catch (InterruptedException e) {
//                    }
//                }
//            }
//        }
//        count = new AtomicLong(0);
//    }
//
//    @Override
//    public String taskID() {
//        return TaskIDEnum.FillCityNameTask.getTaskID();
//    }
//
//    @Override
//    public boolean openFlag() {
//        return false;
//    }
//
//    /**
//     * 间隔 1min 检查一次
//     *
//     * @return
//     */
//    @Override
//    public String cronExpression() {
//        return "1 20 22 * * ?";
//    }
//
//}
