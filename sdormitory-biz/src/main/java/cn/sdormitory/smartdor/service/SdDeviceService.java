package cn.sdormitory.smartdor.service;

import cn.sdormitory.smartdor.entity.SdDevice;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * create By: Zhou Yinsen
 * date: 11/28/2020 10:51 AM
 * description:
 */
public interface SdDeviceService {

    /**
     * 分页数据获取
     * @return
     */
    IPage<SdDevice> getPage(Map<String, Object> params) ;

    /**
     * 根据id 获取设备信息
     * @param id
     * @return
     */
   SdDevice getSdDeviceById(Long id);


    /**
     * 查询IP是否存在
     * @param deviceIpAddress
     * @return true : 不存在 , false : 存在
     */
    boolean getSdDeviceByIP(String deviceIpAddress);

    /**
     * 新增设备信息
     * @param sdDevice
     * @return
     */
    int create(SdDevice sdDevice);

    /**
     * 修改指定设备信息
     * @param sdDevice
     * @return
     */
    int update(SdDevice sdDevice);

    /**
     * 获取设备信息
     * @return
     */
    String getDeviceInfo();

    /**
     * 设置设备基础信息
     * @return
     */
    String setDeviceInfo(SdDevice sdDevice);


     /** 修改设备状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 根据设备号查询设备信息
     * @param deviceNo
     * @return
     */
    SdDevice getDeviceByNo(String deviceNo);




}
