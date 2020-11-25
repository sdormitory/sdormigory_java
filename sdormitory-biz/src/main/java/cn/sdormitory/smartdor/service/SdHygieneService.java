package cn.sdormitory.smartdor.service;

import cn.sdormitory.smartdor.entity.SdHygiene;
import cn.sdormitory.sysset.entity.SyssetHygieneDeduct;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/20 14:16
 * @version：V1.0
 */
public interface SdHygieneService {
    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    public IPage<SdHygiene> getPage(Map<String, Object> params);

    /**
     * 根据id 获取卫生信息
     * @param id
     * @return
     */
    public SdHygiene getSdHygieneById(Long id);

    /**
     * 新建卫生信息
     *
     * @param sdHygiene
     * @return
     */
    int create(SdHygiene sdHygiene);

    /**
     * 删除指定卫生信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定卫生信息
     *
     * @param id
     * @param sdHygiene
     * @return
     */
    int update(Long id, SdHygiene sdHygiene);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);


    /**
     * 根据卫生检查日期+宿舍表ID获取卫生信息
     *
     * @param checkDate 卫生检查日期
     * @param bdormitoryId 宿舍表ID
     */
    SdHygiene getByCheckDateAndBdId(String checkDate,Long bdormitoryId);

    /**
     * 创建卫生及卫生项
     *
     * @param sdHygiene
     * @return
     */
    int insertSdHygieneAndDeduct(SdHygiene sdHygiene);


    /**
     * 创建卫生及卫生项
     *
     * @param sdHygiene
     * @return
     */
    int updateSdHygieneAndDeduct(SdHygiene sdHygiene);
}
