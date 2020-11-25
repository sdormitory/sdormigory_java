package cn.sdormitory.basedata;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendDeviceMsg1 {
    public String send()throws  Exception{
        HttpClient client= new DefaultHttpClient();
//        HttpPost request = new HttpPost("http://192.168.0.101:8088/setPerson?id=123");
        HttpPost request = new HttpPost("http://192.168.0.101:8088/setPerson");
        List pairs = new ArrayList();


        File file = new File("E:\\dormitorycode\\陈梦平.jpg");
        FileInputStream inputFile = new FileInputStream(file);
        String base64;
        byte[] buffer = new byte[(int)file.length()];
        System.out.println("buffer.size() : "+buffer.length);
        inputFile.read(buffer);
        inputFile.close();

        base64 = Base64.encodeBase64String(buffer);


        pairs.add(new BasicNameValuePair("key","abc"));
//        pairs.add(new BasicNameValuePair("id","2f481cc8-7edf-46d7-a749-63714a8074e1"));
        pairs.add(new BasicNameValuePair("id","23456"));
        pairs.add(new BasicNameValuePair("name","陈梦平2"));
        pairs.add(new BasicNameValuePair("IC_NO","1234567891"));
        pairs.add(new BasicNameValuePair("ID_NO","1234567890ABCDED"));
        pairs.add(new BasicNameValuePair("photo",base64));
        pairs.add(new BasicNameValuePair("passCount","10000"));
        pairs.add(new BasicNameValuePair("startTs","-1"));
        pairs.add(new BasicNameValuePair("endTs","-1"));
        pairs.add(new BasicNameValuePair("visitor","false"));


        net.sf.json.JSONObject object = null;
        try {

            Map<String,Object> map = new HashMap<String,Object>();
            request.setEntity(new UrlEncodedFormEntity(pairs));
            HttpResponse resp = client.execute(request);

            HttpEntity entity = resp.getEntity();
            if(entity!=null){
                String result = EntityUtils.toString(entity,"UTF-8");//解析返回数据

                object = net.sf.json.JSONObject.fromObject(result);
                System.out.println(object);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return object.toString();
    }

    public static void main(String[] args)throws Exception{
        SendDeviceMsg1 msg1 = new SendDeviceMsg1();
        msg1.send();

    }
}
