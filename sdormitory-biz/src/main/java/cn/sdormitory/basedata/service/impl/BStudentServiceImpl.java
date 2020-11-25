package cn.sdormitory.basedata.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.basedata.dao.BStudentDao;
import cn.sdormitory.basedata.entity.BStudent;
import cn.sdormitory.basedata.service.BStudentService;
import cn.sdormitory.common.constant.CommonConstant;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/10 16:30
 * @version：V1.0
 */
@Slf4j
@Service("bStudentService")
public class BStudentServiceImpl extends ServiceImpl<BStudentDao, BStudent> implements BStudentService{

    @Override
    public IPage<BStudent> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String studentName = (String) params.get("studentName");
        String studentNo = (String) params.get("studentNo");
        String className = (String) params.get("className");
        String buildingNo = (String) params.get("buildingNo");
        String storey = (String) params.get("storey");
        String dormitoryNo = (String) params.get("dormitoryNo");
        String status = (String) params.get("status");

        LambdaQueryWrapper<BStudent> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(studentName)) {
            wrapper.eq(BStudent::getStudentName, studentName);
        }
        if (StrUtil.isNotEmpty(studentNo)) {
            wrapper.eq(BStudent::getStudentNo, studentNo);
        }
        if (StrUtil.isNotEmpty(className)) {
            wrapper.eq(BStudent::getClassName, className);
        }
        if (StrUtil.isNotEmpty(buildingNo)) {
            wrapper.eq(BStudent::getBuildingNo, buildingNo);
        }
        if (StrUtil.isNotEmpty(storey)) {
            wrapper.eq(BStudent::getStorey, storey);
        }
        if (StrUtil.isNotEmpty(dormitoryNo)) {
            wrapper.eq(BStudent::getDormitoryNo, dormitoryNo);
        }
        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(BStudent::getStatus, status);
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public BStudent getBStudentById(Long id) {
        return getById(id);
    }

    @Override
    public int create(BStudent bStudent) {
        return this.baseMapper.insert(bStudent);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, BStudent bStudent) {
        bStudent.setId(id);
        return this.baseMapper.updateById(bStudent);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateStatus(Long id, String status) {
        BStudent bStudent = new BStudent();
        bStudent.setId(id);
        bStudent.setStatus(status);
        return this.baseMapper.updateById(bStudent);
    }

    @Override
    public BStudent getByStudentNo(String studentNo) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<BStudent>().eq(BStudent::getStudentNo, studentNo));
    }
}
