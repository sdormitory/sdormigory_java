package cn.sdormitory.sysset.service;

import cn.sdormitory.sysset.entity.SyssetSmsTemplate;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/18 14:26
 * @version：V1.0
 */
public interface SyssetSmsTemplateService {
    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    public IPage<SyssetSmsTemplate> getPage(Map<String, Object> params);

    /**
     * 根据id 获取短信模板信息
     * @param id
     * @return
     */
    public SyssetSmsTemplate getSyssetSmsTemplateById(Long id);

    /**
     * 新建短信模板信息
     *
     * @param syssetSmsTemplate
     * @return
     */
    int create(SyssetSmsTemplate syssetSmsTemplate);

    /**
     * 删除指定短信模板信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定短信模板信息
     *
     * @param id
     * @param syssetSmsTemplate
     * @return
     */
    int update(Long id, SyssetSmsTemplate syssetSmsTemplate);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改短信模板信息状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 根据短信模板标题获取短信模板信息
     *
     * @param smsTitle 部件名称
     */
    SyssetSmsTemplate getBySmsTitle(String smsTitle);

    /**
     * 根据模板类型获取短信模板信息
     * @param smsType
     * @return
     */
     SyssetSmsTemplate getBySmsTypee(int smsType);
}
