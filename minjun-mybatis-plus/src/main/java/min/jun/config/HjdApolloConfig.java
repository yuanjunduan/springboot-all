//package min.jun.config;
//
//import com.alibaba.nacos.api.config.annotation.NacosValue;
//import lombok.Data;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.SortedMap;
//
///**
// * @author Administrator
// */
//@Configuration
//@Data
//public class HjdApolloConfig {
//
//
//    //=========================nacos=====================================
//
//    public static Set<Long> dalMultiShopList = new HashSet<>();
//    public static Long dalID;
//
//
//    @NacosValue(value = "#{'${hjd.nacos.dal.multi.shop:}'.split(',')}", autoRefreshed = true)
//    public void setDalMultiShopList(Set<Long> dalMultiShopList) {
//        HjdApolloConfig.dalMultiShopList = dalMultiShopList;
//    }
//
//    @NacosValue(value = "${hjd.nacos.dal.dalID:1259}", autoRefreshed = true)
//    public void setDalID(Long dalID) {
//        HjdApolloConfig.dalID = dalID;
//    }
//
//
//    //=========================apollo=====================================
//
//    public static Set<Long> usedGroupIDSet = new HashSet<>();
//
//    @NacosValue(value = "#{'${hjd.nacos.used.groupID:288288}'.split(',')}", autoRefreshed = true)
//    public void setUsedGroupIDSet(Set<Long> usedGroupIDSet) {
//        HjdApolloConfig.usedGroupIDSet = usedGroupIDSet;
//    }
//
//    public static String shopImageUrl = "";
//
//    @NacosValue(value = "${hjd.nacos.shopImageUrl:group2/M00/D7/DB/wKgVT1uXgfTB5787AB3vM3go__k960.JPG}", autoRefreshed = true)
//    public void setShopImageUrl(String shopImageUrl) {
//        HjdApolloConfig.shopImageUrl = shopImageUrl;
//    }
//
//
//    public static Set<Long> agentWhiteSelectShopSet = new HashSet<>();
//
//    @NacosValue(value = "#{'${hjd.nacos.whiteSelectShop.agentIDs:1032}'.split(',')}", autoRefreshed = true)
//    public void setAgentWhiteSelectShopList(Set<Long> agentWhiteSelectShopSet) {
//        HjdApolloConfig.agentWhiteSelectShopSet = agentWhiteSelectShopSet;
//    }
//
//    public static String agentTestSelectKey = "";
//
//    @NacosValue(value = "${hjd.nacos.agentTestSelectKey:18501370281}", autoRefreshed = true)
//    public void setAgentTestSelectKey(String agentTestSelectKey) {
//        HjdApolloConfig.agentTestSelectKey = agentTestSelectKey;
//    }
//
//    public static Long agentInitShopID;
//
//    @NacosValue(value = "${hjd.nacos.agentInitShopID:76946622}", autoRefreshed = true)
//    public void setAgentInitShopID(Long agentInitShopID) {
//        HjdApolloConfig.agentInitShopID = agentInitShopID;
//    }
//
//    public static boolean agentCompatibleOld;
//
//    @NacosValue(value = "${hjd.nacos.agentCompatibleOld:true}", autoRefreshed = true)
//    public void setAgentCompatibleOld(boolean agentCompatibleOld) {
//        HjdApolloConfig.agentCompatibleOld = agentCompatibleOld;
//    }
//
//    /**
//     * 慧接单配送渠道提示文案
//     */
//
//    public static SortedMap<String, String> smsTemplate;
//
//    @NacosValue(value = "#{${hjd.nacos.smsTemplate}}", autoRefreshed = true)
//    public void setSmsTemplate(SortedMap<String, String> smsTemplate) {
//        HjdApolloConfig.smsTemplate = smsTemplate;
//    }
//
//
//
//    public static String sendDynamicCode = "";
//
//    @NacosValue(value = "${hjd.nacos.sendDynamicCode:https://dohko.login.hualala.com/sendDynamicCode}", autoRefreshed = true)
//    public void setSendDynamicCode(String sendDynamicCode) {
//        HjdApolloConfig.sendDynamicCode = sendDynamicCode;
//    }
//
//
//
//    public static String loginAjaxDynamicCodeLite = "";
//
//    @NacosValue(value = "${hjd.nacos.loginAjaxDynamicCodeLite:https://dohko.login.hualala.com/loginAjaxDynamicCodeLite}", autoRefreshed = true)
//    public void setLoginAjaxDynamicCodeLite(String loginAjaxDynamicCodeLite) {
//        HjdApolloConfig.loginAjaxDynamicCodeLite = loginAjaxDynamicCodeLite;
//    }
//
//    public static String loginDoubleUser = "";
//
//    @NacosValue(value = "${hjd.nacos.loginDoubleUser:https://dohko.login.hualala.com/loginDoubleUser}", autoRefreshed = true)
//    public void setLoginDoubleUser(String loginDoubleUser) {
//        HjdApolloConfig.loginDoubleUser = loginDoubleUser;
//    }
//
//
//    public static String getUserInfoByToken = "";
//
//    @NacosValue(value = "${hjd.nacos.getUserInfoByToken:https://dohko.login.hualala.com/getUserInfoByToken?token=}", autoRefreshed = true)
//    public void setGetUserInfoByToken(String getUserInfoByToken) {
//        HjdApolloConfig.getUserInfoByToken = getUserInfoByToken;
//    }
//
//
//    @NacosValue(value = "${hjd.nacos.wxminiGroupID:255255}", autoRefreshed = true)
//    public void setWxminiGroupID(String wxminiGroupID) {
//        HjdApolloConfig.wxminiGroupID = wxminiGroupID;
//    }
//
//    public static String wxminiGroupID;
//
//    public static boolean queryFlag;
//
//    @NacosValue(value = "${hjd.nacos.queryFlag:true}", autoRefreshed = true)
//    public void setQueryFlag(boolean queryFlag) {
//        HjdApolloConfig.queryFlag = queryFlag;
//    }
//
//    public static Long agentID;
//    @NacosValue(value = "${hjd.nacos.agentID:1954}", autoRefreshed = true)
//    public void setAgentID(Long agentID) {
//        HjdApolloConfig.agentID = agentID;
//    }
//    public static Long primaryAgentID;
//    @NacosValue(value = "${hjd.nacos.primaryAgentID:1954}", autoRefreshed = true)
//    public void setPrimaryAgentID(Long primaryAgentID) {
//        HjdApolloConfig.primaryAgentID = primaryAgentID;
//    }
//
//    public static Long agentUserID;
//    @NacosValue(value = "${hjd.nacos.agentUserID:101059}", autoRefreshed = true)
//    public void setAgentUserID(Long agentUserID) {
//        HjdApolloConfig.agentUserID = agentUserID;
//    }
//    public static Long primaryAgentUserID;
//    @NacosValue(value = "${hjd.nacos.primaryAgentUserID:101059}", autoRefreshed = true)
//    public void setPrimaryAgentUserID(Long primaryAgentUserID) {
//        HjdApolloConfig.primaryAgentUserID = primaryAgentUserID;
//    }
//    public static String hllName;
//    @NacosValue(value = "${hjd.nacos.hllName:哗啦啦}", autoRefreshed = true)
//    public void setHllName(String hllName) {
//        HjdApolloConfig.hllName = hllName;
//    }
//
//    public static boolean registerSwitchOpenFlag;
//    @NacosValue(value = "${hjd.nacos.registerSwitchCloseFlag:false}", autoRefreshed = true)
//    public void setRegisterSwitchOpenFlag(boolean registerSwitchOpenFlag) {
//        HjdApolloConfig.registerSwitchOpenFlag = registerSwitchOpenFlag;
//    }
//}
