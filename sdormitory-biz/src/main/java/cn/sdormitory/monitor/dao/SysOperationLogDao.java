package cn.sdormitory.monitor.dao;

import cn.sdormitory.monitor.entity.SysOperationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: SysOperationLogDao
 */
@Mapper
public interface SysOperationLogDao extends BaseMapper<SysOperationLog> {

    /**
     * 清空
     *
     * @return
     */
    void clean();
}
