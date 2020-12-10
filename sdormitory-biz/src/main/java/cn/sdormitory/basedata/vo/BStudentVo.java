package cn.sdormitory.basedata.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * Created By ruanteng
 * DateTime：2020/11/29
 */
@Data
public class BStudentVo  implements Serializable {

    private String deviceSn;

    private String personId;

    private double ts;

    private String name;

    private String code;

    private String visitor;

    private String passType;

    private String photo;


}
