package cn.sdormitory.common.constant;


/**
 * 公共常量
 */
public class CommonConstant {

    public static final String AJAX_REQUEST_HEADER_KEY = "x-rjft-request";

    public static final String AJAX_REQUEST_HEADER_VALUE = "ajax";

    public static final String AJAX_REQUEST_TOKEN_KEY = "Authorization";

    /**
     * app原生页请求
     */
    public static final String AJAX_NATIVE_HEADER_KEY = "x-rjft-native";

    public static final String AJAX_NATIVE_HEADER_VALUE = "native";


    /**
     * 超级管理员ID
     */
    public static final long SUPER_ADMIN_ID = 1L;

    /**
     * 系统菜单最大id
     */
    public static final int SYS_MENU_MAX_ID = 1;
    /**
     * 默认角色
     */
    public static final long DEFAULT_ROLE = 1L;

    /**
     * 匿名用户
     */
    public static final String ANONYMOUS_USER = "anonymousUser";

    /**
     * 订单生成规则
     */
    public static final String NUMBER_GEN_KEY_ORDER = "number_gen_key_order";

    /**
     * 退货单生成规则
     */
    public static final String NUMBER_GEN_KEY_ORDER_RETURN = "number_gen_key_order_return";

    /**
     * 业务类型前缀 【支付宝支付唯一编码前缀】
     */
    public static final String ALI_PAY_PREFIX = "ALI";
    /**
     * 业务类型前缀 【微信支付唯一编码前缀】
     */
    public static final String WEIXIN_PAY_PREFIX = "WX";


    /** 数据权限过滤 */
    public static final String SQL_FILTER = "sql_filter";

    /** 有效状态 */
    public static final String VALID_STATUS = "1";

    /** 总扣分 */
    public static final double TOTALPDEDUCT_VALUE = 0;

    /** 总得分 */
    public static final double TOTALSCORE_VALUE = 100;

    /** 每天考勤时间*/
    public static final String ATTENDANCE_TIME ="22:00:00";

    /** 考勤状态----->成功 */
    public static final String  ATTENCE_STATUS_SUCCEED="1";

    /** 考勤状态----->缺勤 */
    public static final String  ATTENCE_STATUS_LACK="2";

    /** 考勤状态----->晚归 */
    public static final String  ATTENCE_STATUS_LATENESS="3";

    /** 考勤状态----->请假 */
    public static final String  ATTENCE_STATUS_VACATE="4";

    /** 短信模换板中需要替的字符串*/
    public static final String SMS_TEMPLATE_STR="{student}";
}
