package cn.sdormitory.basedata.service;

import cn.sdormitory.basedata.entity.BDormitory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/7 20:17
 * @version：V1.0
 */
public interface BDormitoryService {
    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    public IPage<BDormitory> getPage(Map<String, Object> params);

    /**
     * 根据id 获取宿舍信息
     * @param id
     * @return
     */
    public BDormitory getBDormitoryById(Long id);

    /**
     * 新建宿舍信息
     *
     * @param bDormitory
     * @return
     */
    int create(BDormitory bDormitory);

    /**
     * 删除指定宿舍信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定宿舍信息
     *
     * @param id
     * @param bDormitory
     * @return
     */
    int update(Long id, BDormitory bDormitory);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 修改宿舍状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 根据几栋+楼层+宿舍号获取宿舍信息
     *
     * @param buildingNo 几栋
     * @param storey 楼层
     * @param dormitoryNo 宿舍号
     */
    BDormitory getByDormitoryInfo(String buildingNo,String storey,String dormitoryNo);

    /**
     * 获取所有的宿舍列表
     *
     * @return
     */
    List<BDormitory> getListAll();

    //根据宿舍栋号和楼层查询宿舍信息
    List<BDormitory> getBDormitoryListByCol(@Param("buildingNo")String buildingNo, @Param("storey")String storey);



}
