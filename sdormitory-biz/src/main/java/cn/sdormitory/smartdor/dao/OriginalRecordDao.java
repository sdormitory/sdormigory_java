package cn.sdormitory.smartdor.dao;



import cn.sdormitory.smartdor.entity.OriginalRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Created By ruanteng
 *  DateTime：2020/12/7
 */

@Mapper
public interface OriginalRecordDao extends BaseMapper<OriginalRecord> {

    /**
     * 查询考勤流水列表
     * @param date 当前日期
     * @param currIndex 当前页
     * @param pageSize  页面大小
     * @return
     */
    List<OriginalRecord> getList(@Param("date")String date, @Param("currIndex")Integer currIndex, @Param("pageSize") Integer pageSize);

    /**
     * 总记录数
     * @param date 当前日期
     * @return
     */
    int getListCount(@Param("date")String date);


    /**
     * 查询所有人当天最后一次考勤记录
     * @return
     */
    List<OriginalRecord> list();


}
