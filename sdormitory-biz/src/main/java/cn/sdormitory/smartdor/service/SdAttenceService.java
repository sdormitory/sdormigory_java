package cn.sdormitory.smartdor.service;

import cn.sdormitory.basedata.entity.BStudent;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.smartdor.entity.SdAttence;
import cn.sdormitory.smartdor.vo.DormitoryAttenceVo;
import cn.sdormitory.smartdor.vo.SdAttenceVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created By ruanteng
 * DateTime：2020/11/27
 */
public interface SdAttenceService {

    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    CommonPage<SdAttence> getPage(Map<String, Object> params);

    /**
     * 定时添加考勤记录
     * @return
     */
    void create();

    /**
     * 删除考勤记录
     */
    int delete(String [] id);

    /**
     * 总记录条数
     */
    int getCount(Map<String, Object> params);

    /**
     * 添加考勤
     * @param sdAttence
     * @return
     */
    int insert(SdAttence sdAttence);

    /**
     * 获得缺勤学生信息
     * @return
     */
    CommonPage<SdAttenceVo> listAbsenceStudent(Map<String,Object> map);

    /**
     * 查询每个宿舍的详细考勤信息
     * @param map
     * @return
     */
    CommonPage<DormitoryAttenceVo> listAbsenceDormitory(Map<String,Object> map);

    void statisticsLackStu();

    List<SdAttenceVo> getLackStu();
}
