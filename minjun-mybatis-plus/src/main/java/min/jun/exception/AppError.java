package min.jun.exception;

/**
 * 慧接单所有错误信息  全部以002002开头
 *
 * @author Administrator
 */
public enum AppError {
    /**
     * 用户没有登录或授权
     */
    UNAUTHORIZED_ACCESSED("4001", "unauthorized"),

    /**
     * 用户不存在
     */
    USER_NOTEXIST("4002", "当前信息尚未完善，请先完善信息噢~"),

    /**
     * 内部异常
     */
    INNER_ERROR("880000", "请稍后重试"),
    /**
     * 数据库操作异常
     */
    BAD_SQL_EXCEPTION("880001", "数据库操作异常"),
    /**
     * GRPC异常
     */
    GRPC_EXCEPTION("880002", "GRPC异常"),

    /**
     * 参数异常异常
     */
    PARAMS_EXCEPTION("002002001", "参数异常"),

    /**
     * 暂无数据
     */
    NO_DATA_EXCEPTION("880004", "暂无数据"),
    /**
     *
     */
    NO_TYPE_EXCEPTION("880005", "暂不支持该类型"),
    /**
     *
     */
    ILLEGAL_REQUEST_EXCEPTION("880006", "非法请求"),
    /**
     *
     */
    BIZ_EXCEPTION("002002999", "业务异常"),

    BUSINESS_EXCEPTION("002002001", "参数异常");


    private String code;
    private String msg;

    AppError(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
