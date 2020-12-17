package cn.sdormitory.smartdor.dao;

import cn.sdormitory.basedata.entity.BStudent;
import cn.sdormitory.smartdor.entity.OriginalRecord;
import cn.sdormitory.smartdor.entity.SdAttence;
import cn.sdormitory.smartdor.vo.DormitoryAttenceVo;
import cn.sdormitory.smartdor.vo.SdAttenceVo;
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

    /**
     * 获得缺勤学生信息
     * @return
     */
    List<SdAttenceVo> listAbsenceStudent(Map<String,Object> map);

    /**
     * 获得每个宿舍的考勤信息
     * @param map
     * @return
     */
   List<DormitoryAttenceVo> dormitoryAttenceVos(Map<String,Object> map);

    /**
     * 获得当天缺勤的学生
     * @return
     */
   List<SdAttenceVo> getLackStu();

}

