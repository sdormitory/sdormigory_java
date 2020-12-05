package cn.sdormitory.basedata.vo;

import cn.sdormitory.common.constant.CommonConstant;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created By ruanteng
 * DateTime：2020/11/29
 */
@Data
public class BStudentVo {

    private String deviceSn;

    private String personId;

    private double ts;

    private String name;

    private String code;

    private String visitor;

    private String passType;

    private String photo;

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String str = simpleDateFormat.format(new Date())+" "+ CommonConstant.ATTENDANCE_TIME;
        int i = simpleDateFormat.parse("2020-12-6 10:55:55").compareTo(simpleDateFormat2.parse(str));
        System.out.println(i);




    }
}
