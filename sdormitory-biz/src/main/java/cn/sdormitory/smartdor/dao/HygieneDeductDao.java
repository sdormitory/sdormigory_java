package cn.sdormitory.smartdor.dao;

import cn.sdormitory.smartdor.entity.HygieneDeduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/20 14:40
 * @version：V1.0
 */
@Mapper
public interface HygieneDeductDao extends BaseMapper<HygieneDeduct> {
    /**
     * 根据卫生表id 批量添加扣分项
     * @param hygieneId
     * @param deductIdList
     */
    void insertHygieneAndHygieneDeduct(@Param("hygieneId") Long hygieneId, @Param("deductIdList") List<Long> deductIdList);
}
