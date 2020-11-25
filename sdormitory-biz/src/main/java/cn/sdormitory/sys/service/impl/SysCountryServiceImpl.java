package cn.sdormitory.sys.service.impl;

import cn.sdormitory.sys.entity.SysCountry;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.sdormitory.sys.dao.SysCountryDao;
import cn.sdormitory.sys.service.SysCountryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 世界国旗图标
 */
@Service("sysCountryService")
public class SysCountryServiceImpl extends ServiceImpl<SysCountryDao, SysCountry> implements SysCountryService {


    @Override
    public List<SysCountry> allList() {
        return list();
    }
}