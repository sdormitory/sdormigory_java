package cn.sdormitory.request;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/23 12:10
 * @version：V1.0
 */
public class HttpRequest {
    public static String sendGet(String url, String urlParam) {
        String result = "";
        BufferedReader in = null;

        try {
            String urlNameString = url + "?" + urlParam;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 请求 post 方式
     * @param url
     * @param pairs
     * @return
     */
    public static String sendPost(String url, List<BasicNameValuePair> pairs) {
        HttpClient client= new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type","application/json;charset=utf-8");
        request.addHeader("Accept","application/json");
        net.sf.json.JSONObject object = null;
        try {
            Map<String,Object> map = new HashMap<>();
            request.setEntity(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
            HttpResponse resp = client.execute(request);
            HttpEntity entity = resp.getEntity();
            if(entity!=null){
                String result = EntityUtils.toString(entity,"UTF-8");//解析返回数据
                object = net.sf.json.JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return object.toString();
    }


    public static void main(String[] args){
       String url="http://192.168.0.101:8088/getDeviceInfo";
       String key="abc";
       StringBuffer stringBuffer = new StringBuffer();
       stringBuffer.append("key="+key);
       String result=HttpRequest.sendGet(url,stringBuffer.toString());
       System.out.println(result);
    }

}
