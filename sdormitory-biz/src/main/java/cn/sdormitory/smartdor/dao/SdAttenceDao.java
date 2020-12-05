package cn.sdormitory.smartdor.dao;

import cn.sdormitory.smartdor.entity.SdAttence;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  Created By ruanteng
 *  DateTime：2020/11/27
 */
@Mapper
public interface SdAttenceDao extends BaseMapper<SdAttence> {

    List<SdAttence> getList(@Param("date")String date,@Param("currIndex")Integer currIndex,@Param("pageSize") Integer pageSize);

   int getListCount(@Param("date")String date);

}

