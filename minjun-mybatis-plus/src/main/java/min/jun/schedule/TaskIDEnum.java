package min.jun.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Administrator
 */
@Getter
@AllArgsConstructor
public enum TaskIDEnum {
    ApolloGroupCheck("ApolloGroupCheck", ""),
    ApolloGroupCheckReset("ApolloGroupCheckReset", ""),
    FillCityNameTask("fillCityNameTask", ""),
    QUERY_SHOP_DB_TO_REDIS("QUERY_SHOP_DB_TO_REDIS", "");

    private String taskID;
    private String desc;
}
