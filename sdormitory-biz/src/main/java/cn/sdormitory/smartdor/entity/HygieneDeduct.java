package cn.sdormitory.smartdor.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/20 14:30
 * @version：V1.0
 * 宿舍卫生扣分项关联表
 */
@Data
@TableName("hygiene_deduct")
public class HygieneDeduct implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     *
     */
    private Long hygieneId;
    /**
     *
     */
    private Long deductId;
}
