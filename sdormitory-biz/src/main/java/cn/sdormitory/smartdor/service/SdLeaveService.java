package cn.sdormitory.smartdor.service;

import cn.sdormitory.smartdor.entity.SdLeave;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/27 17:41
 * @version：V1.0
 */
public interface SdLeaveService {
    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    public IPage<SdLeave> getPage(Map<String, Object> params);

    /**
     * 根据id 获取请假信息
     * @param id
     * @return
     */
    public SdLeave getSdLeaveById(Long id);


    /**
     * 删除指定请假信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定请假信息
     *
     * @param id
     * @param sdLeave
     * @return
     */
    int update(Long id, SdLeave sdLeave);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);


    /**
     * 班主任审核通过
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatusApprove(Long id, String status);
}
