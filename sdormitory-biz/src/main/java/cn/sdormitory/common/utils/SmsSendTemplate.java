package cn.sdormitory.common.utils;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/27 14:11
 * @version：V1.0
 * 发送短信工具类
 */
public class SmsSendTemplate {
    public static String sms(String phone,String content) {
        HashMap<String, Object> result = null;
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
        restAPI.init("app.cloopen.com", "8883");
        // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
        restAPI.setAccount("8a216da8701eb7c101703ea5ef590d6b", "12ccd97e27bb499fb54070cc6e68c7f4");
        // 请使用管理控制台中已创建应用的APPID。
        restAPI.setAppId("8a216da874af5fff01751a88822c21cd");
        result = restAPI.sendTemplateSMS(phone, "1", new String[]{content});
        return result.toString();
    }
}
