package cn.sdormitory.sysset.service;

import cn.sdormitory.sysset.entity.SyssetHygieneDeduct;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/19 16:43
 * @version：V1.0
 */
public interface SyssetHygieneDeductService {
    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    public IPage<SyssetHygieneDeduct> getPage(Map<String, Object> params);

    /**
     * 根据id 获取卫生扣分项信息
     * @param id
     * @return
     */
    public SyssetHygieneDeduct getSyssetHygieneDeductById(Long id);

    /**
     * 新建卫生扣分项信息
     *
     * @param syssetHygieneDeduct
     * @return
     */
    int create(SyssetHygieneDeduct syssetHygieneDeduct);

    /**
     * 删除指定卫生扣分项信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定卫生扣分项信息
     *
     * @param id
     * @param syssetHygieneDeduct
     * @return
     */
    int update(Long id, SyssetHygieneDeduct syssetHygieneDeduct);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改卫生扣分项信息状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 根据扣分项获取卫生扣分项信息
     *
     * @param deductOption 扣分项
     */
    SyssetHygieneDeduct getByDeductOption(String deductOption);

    /**
     * 获取所有的卫生扣分项
     *
     * @return
     */
    List<SyssetHygieneDeduct> getListAll();
}
