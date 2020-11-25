package cn.sdormitory.basedata.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.basedata.dao.BClassDao;
import cn.sdormitory.basedata.entity.BClass;
import cn.sdormitory.basedata.service.BClassService;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.sys.entity.SysUser;
import cn.sdormitory.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/2 17:20
 * @version：V1.0
 */
@Slf4j
@Service("bClassService")
public class BClassServiceImpl extends ServiceImpl<BClassDao, BClass> implements BClassService {
    @Resource
    private BClassService bClassService;
    @Resource
    private BClassDao bClassDao;
    @Resource
    private SysUserService sysUserService;

    @Override
    public IPage<BClass> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String className = (String) params.get("className");
        String status = (String) params.get("status");
        String classTeacherName = (String) params.get("classTeacherName");

        LambdaQueryWrapper<BClass> wrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotEmpty(className)) {
            wrapper.eq(BClass::getClassName, className);
        }
        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(BClass::getStatus, status);
        }
        if(StrUtil.isNotEmpty(classTeacherName)){
            SysUser sysUser=sysUserService.getByUserName(classTeacherName);
            wrapper.eq(BClass::getClassTeacherId, sysUser.getId());
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public BClass getBClassById(Long id) {
        return getById(id);
    }

    @Override
    public int create(BClass bClass) {
        return this.baseMapper.insert(bClass);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, BClass bClass) {
        bClass.setId(id);
        return this.baseMapper.updateById(bClass);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateStatus(Long id, String status) {
        BClass bClass = new BClass();
        bClass.setId(id);
        bClass.setStatus(status);
        return this.baseMapper.updateById(bClass);
    }

    @Override
    public BClass getByClassName(String className) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<BClass>().eq(BClass::getClassName, className));
    }

    @Override
    public List<BClass> getListAll() {
        return list(new LambdaQueryWrapper<BClass>().eq(BClass::getStatus,CommonConstant.VALID_STATUS));
    }

    @Override
    public List<BClass> getBClassList(String className,String status,String classTeacherName,int pageNum,int pageSize) {
        int currIndex=(pageNum-1)*pageSize;
        return bClassDao.getBClassList(className,status,classTeacherName,currIndex,pageSize);
    }
}
