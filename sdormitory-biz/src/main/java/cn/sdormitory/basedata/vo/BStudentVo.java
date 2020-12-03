package cn.sdormitory.basedata.vo;

import lombok.Data;

/**
 * Created By ruanteng
 * DateTime：2020/11/29
 */
@Data
public class BStudentVo {

    /**
     * 人员ID
     */
    private String personID;

    /**
     * 过闸时间
     */
    private double ts;

    /**
     * 名称
     */
    private String name;

    /**
     * 证件类型
     */
    private  int passType;

    /**
     * 照片
     */
    private String photo;

    /**
     * 是否为访问者
     */
    private boolean visitor;


}
