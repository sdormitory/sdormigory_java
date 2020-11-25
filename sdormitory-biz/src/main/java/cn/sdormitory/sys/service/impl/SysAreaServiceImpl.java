package cn.sdormitory.sys.service.impl;

import cn.sdormitory.common.utils.BeanUtils;
import cn.sdormitory.common.utils.TreeUtils;
import cn.sdormitory.sys.dao.SysAreaDao;
import cn.sdormitory.sys.entity.SysArea;
import cn.sdormitory.sys.service.SysAreaService;
import cn.sdormitory.sys.vo.SysAreaResult;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 地区表
 */
@Service("sysAreaService")
public class SysAreaServiceImpl extends ServiceImpl<SysAreaDao, SysArea> implements SysAreaService {

    @Override
    public List<SysAreaResult> allList() {
        List<SysAreaResult> mainCategoryList = new ArrayList<>();
        TreeUtils treeUtils = new TreeUtils();
        List<SysArea> list = list();
        if (CollectionUtil.isNotEmpty(list)) {
            List<SysAreaResult> results = BeanUtils.transformList(SysAreaResult.class, list);
            mainCategoryList = treeUtils.menuList(results);
        }

        return mainCategoryList;
    }
}