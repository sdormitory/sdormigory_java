package cn.sdormitory.smartdor.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * Created By ruanteng
 * DateTime：2020/12/9
 * 考勤异常的学生信息
 */
@Data
public class SdAttenceVo implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 宿舍详细地址地址
     */
    private String dorAddress;


}
