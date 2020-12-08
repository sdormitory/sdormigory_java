package cn.sdormitory.sysset.service;

import cn.sdormitory.sysset.entity.SyssetAttenceRule;
import cn.sdormitory.sysset.entity.SyssetHygieneDeduct;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/26 16:08
 * @version：V1.0
 */
public interface SyssetAttenceRuleService {
    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    public IPage<SyssetAttenceRule> getPage(Map<String, Object> params);

    /**
     * 根据id 获取考勤规则信息
     * @param id
     * @return
     */
    public SyssetAttenceRule getSyssetAttenceRuleById(Long id);

    /**
     * 新建考勤规则信息
     *
     * @param syssetAttenceRule
     * @return
     */
    int create(SyssetAttenceRule syssetAttenceRule);

    /**
     * 删除指定考勤规则信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定考勤规则信息
     *
     * @param id
     * @param syssetAttenceRule
     * @return
     */
    int update(Long id, SyssetAttenceRule syssetAttenceRule);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改考勤规则信息状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 根据考勤规则名称获取考勤规则信息
     *
     * @param attenceRuleName 考勤规则名称
     */
    SyssetAttenceRule getByAttenceRuleName(String attenceRuleName);

    /**
     * 获取所有的考勤规则信息
     *
     * @return
     */
    List<SyssetAttenceRule> getListAll();

    /**
     * 根据当前时间返回考勤状态
     * @param date 当前时间
     * @return
     */
    String getByAttenceRuleByTime(Date date) throws ParseException;
}
