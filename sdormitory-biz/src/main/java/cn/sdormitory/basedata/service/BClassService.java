package cn.sdormitory.basedata.service;

import cn.sdormitory.basedata.entity.BClass;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/2 17:19
 * @version：V1.0
 */
public interface BClassService {
    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    public IPage<BClass> getPage(Map<String, Object> params);

    /**
     * 根据id 获取班级信息
     * @param id
     * @return
     */
    public BClass getBClassById(Long id);

    /**
     * 新建班级信息
     *
     * @param bClass
     * @return
     */
    int create(BClass bClass);

    /**
     * 删除指定班级信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定班级信息
     *
     * @param id
     * @param bClass
     * @return
     */
    int update(Long id, BClass bClass);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改班级状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 根据班级名称获取班级信息
     *
     * @param className 部件名称
     */
    BClass getByClassName(String className);

    /**
     * 获取所有的班级列表
     *
     * @return
     */
    List<BClass> getListAll();

    List<BClass> getBClassList(String className,String status,String classTeacherName,int pageNum,int pageSize);

}
