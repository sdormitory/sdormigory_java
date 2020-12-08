package cn.sdormitory.smartdor.dao;

import cn.sdormitory.smartdor.entity.OriginalRecord;
import cn.sdormitory.smartdor.entity.SdAttence;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *  Created By ruanteng
 *  DateTime：2020/11/27
 */
@Mapper
public interface SdAttenceDao extends BaseMapper<SdAttence> {

    /**
     * 获取指定日期的考勤信息
     * @param date
     * @param currIndex
     * @param pageSize
     * @return
     */
    List<SdAttence> getList(@Param("date")String date,@Param("currIndex")Integer currIndex,@Param("pageSize") Integer pageSize);

    /**
     * 获取总记录条数
     * @param date
     * @return
     */
   int getListCount(@Param("date")String date);

}

