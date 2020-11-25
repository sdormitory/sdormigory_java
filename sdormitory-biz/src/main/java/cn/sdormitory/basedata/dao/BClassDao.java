package cn.sdormitory.basedata.dao;

import cn.sdormitory.basedata.entity.BClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/2 17:17
 * @version：V1.0
 */
@Mapper
public interface BClassDao  extends BaseMapper<BClass> {
    List<BClass> getBClassList(@Param("className")String className, @Param("status")String status,@Param("classTeacherName")String classTeacherName,@Param("currIndex")int currIndex,@Param("pageSize")int pageSize);
}
