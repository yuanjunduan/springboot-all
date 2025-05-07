package min.jun.config.datasource;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数传递辅助类
 */
public class RequestDataHelper {


    public static final String TABLE_PRE = "table_pre";

    /**
     * 请求参数存取
     */
    private static final ThreadLocal<Map<String, Object>> REQUEST_DATA = new ThreadLocal<>();

    /**
     * 设置请求参数
     */
    public static void setRequestData(String key, Object value) {
        Map<String, Object> dataMap = getRequestData();
        if (dataMap == null) {
            dataMap = new HashMap<>();
            REQUEST_DATA.set(dataMap);
        }
        dataMap.put(key, value);
    }

    public static void setRequestData(Map<String, Object> requestData) {
        Map<String, Object> dataMap = getRequestData();
        if (CollectionUtils.isNotEmpty(dataMap)) {

        }
        REQUEST_DATA.set(requestData);
    }

    /**
     * 获取请求参数
     *
     * @param param 请求参数
     * @return 请求参数 MAP 对象
     */
    public static <T> T getRequestData(String param) {
        Map<String, Object> dataMap = getRequestData();
        if (CollectionUtils.isNotEmpty(dataMap)) {
            return (T) dataMap.get(param);
        }
        return null;
    }

    /**
     * 获取请求参数
     *
     * @return 请求参数 MAP 对象
     */
    public static Map<String, Object> getRequestData() {
        return REQUEST_DATA.get();
    }

    /**
     * 删除请求参数
     */
    public static void removeRequestData() {
        REQUEST_DATA.remove();
    }
}
