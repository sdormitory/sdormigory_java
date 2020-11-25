package cn.sdormitory.sys.service;

import cn.sdormitory.sys.entity.SysCountry;

import java.util.List;

/**
 * 世界国旗图标
 */
public interface SysCountryService {

    /**
     * 获取全部世界国旗图标
     *
     * @return
     */
    List<SysCountry> allList();
}

