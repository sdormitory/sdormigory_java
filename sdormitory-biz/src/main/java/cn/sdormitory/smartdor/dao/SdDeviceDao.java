package cn.sdormitory.smartdor.dao;


import cn.sdormitory.smartdor.entity.SdDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create By: Zhou Yinsen
 * date: 11/28/2020 10:42 AM
 * description:
 */
@Mapper
public interface SdDeviceDao extends BaseMapper<SdDevice> {

    /**
     * 查询IP是否已经存在
     * @param deviceIpAddress
     * @return
     */
    SdDevice getSdDeviceByIP(@Param("deviceIpAddress") String deviceIpAddress);


    /**
     * 根据ID查询设备详细信息
     * @param id
     * @return
     */
    SdDevice getSdDeviceByID(@Param("id") Long id);



}
