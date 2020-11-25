package cn.sdormitory.basedata.service;

import cn.sdormitory.basedata.entity.BStudent;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/10 16:22
 * @version：V1.0
 */
public interface BStudentService {
    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    public IPage<BStudent> getPage(Map<String, Object> params);

    /**
     * 根据id 获取学员信息
     * @param id
     * @return
     */
    public BStudent getBStudentById(Long id);

    /**
     * 新建学员信息
     *
     * @param bStudent
     * @return
     */
    int create(BStudent bStudent);

    /**
     * 删除指定学员信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定学员信息
     *
     * @param id
     * @param bStudent
     * @return
     */
    int update(Long id, BStudent bStudent);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改学员状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 根据学号获取学员信息
     *
     * @param studentNo 学号
     */
    BStudent getByStudentNo(String studentNo);
}
