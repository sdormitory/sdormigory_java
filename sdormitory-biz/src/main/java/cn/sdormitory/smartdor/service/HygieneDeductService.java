package cn.sdormitory.smartdor.service;

import cn.sdormitory.smartdor.entity.HygieneDeduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/20 14:43
 * @version：V1.0
 */
public interface HygieneDeductService extends IService<HygieneDeduct> {
    /**
     * 根据卫生表ID，获取对应扣分项ID列表
     *
     * @param hygieneId 卫生id
     * @return 扣分项id列表
     */
    List<Long> listDeductIdByHygieneId(Long hygieneId);

    /**
     * 根据卫生id 批量添加卫生扣分项关系
     * @param hygieneId
     * @param deductIdList
     */
    void insertHygieneAndHygieneDeduct(Long hygieneId, List<Long> deductIdList);

    /**
     * 删除历史扣分项创建新的扣分项
     * @param hygieneId
     * @param deductIdList
     * @return
     */
    void delAndCreateHygiene(Long hygieneId, List<Long> deductIdList);
}
