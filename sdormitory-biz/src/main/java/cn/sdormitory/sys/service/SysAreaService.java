package cn.sdormitory.sys.service;

import cn.sdormitory.sys.vo.SysAreaResult;

import java.util.List;

/**
 * 地区表
 */
public interface SysAreaService {

    /**
     * 地区
     *
     * @return
     */
    List<SysAreaResult> allList();
}

