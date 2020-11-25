package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysUserLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户登录日志表
 */
@Mapper
public interface SysUserLoginLogDao extends BaseMapper<SysUserLoginLog> {

    /**
     * 清空
     *
     * @return
     */
    int clean();
}
