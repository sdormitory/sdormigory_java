package cn.sdormitory.sys.service;

import cn.sdormitory.sys.entity.SysUserLoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 后台用户登录日志表
 */
public interface SysUserLoginLogService {

    /**
     * 创建用户登陆历史
     *
     * @param sysUserLoginLog
     * @return
     */
    int create(SysUserLoginLog sysUserLoginLog);

    /**
     * 根据关键字删除key
     *
     * @param key
     */
    boolean deleteByKey(String key);

    /**
     * 分页获取日志信息
     *
     * @param loginLog
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<SysUserLoginLog> getPage(SysUserLoginLog loginLog, Integer pageSize, Integer pageNum);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 清空
     *
     * @return
     */
    int clean();

    /**
     * 导出
     *
     * @param loginLog
     * @return
     */
    List<SysUserLoginLog> getExportList(SysUserLoginLog loginLog);
}

