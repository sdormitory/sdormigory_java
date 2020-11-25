package cn.sdormitory.monitor.service;

import cn.sdormitory.monitor.entity.SysOperationLog;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * ClassName: SysOperationLogService
 */
public interface SysOperationLogService {
    /**
     * 新增操作日志
     *
     * @param log 操作日志对象
     */
    void insertLog(SysOperationLog log);

    /**
     * 分页获取
     *
     * @param sysOperationLog
     * @param pageSize
     * @param pageNum
     * @return
     */
    IPage<SysOperationLog> getPage(SysOperationLog sysOperationLog, Integer pageSize, Integer pageNum);

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
    void clean();

    /**
     * 导出（不分页）
     *
     * @param sysOperationLog
     * @return
     */
    List<SysOperationLog> getExportList(SysOperationLog sysOperationLog);
}
