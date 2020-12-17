package cn.sdormitory.smartdor.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.smartdor.dao.SdLeaveDao;
import cn.sdormitory.smartdor.entity.SdLeave;
import cn.sdormitory.smartdor.service.SdLeaveService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/27 17:44
 * @version：V1.0
 */
@Slf4j
@Service("sdLeaveService")
public class SdLeaveServiceImpl  extends ServiceImpl<SdLeaveDao, SdLeave> implements SdLeaveService {
    @Override
    public IPage<SdLeave> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String studentNo = (String) params.get("studentNo");
        String studentPhone = (String) params.get("studentPhone");
        String leaveType = (String) params.get("leaveType");
        String status = (String) params.get("status");

        LambdaQueryWrapper<SdLeave> wrapper = new LambdaQueryWrapper<>();


        if (StrUtil.isNotEmpty(studentNo)) {
            wrapper.eq(SdLeave::getStudentNo, studentNo);
        }
        if (StrUtil.isNotEmpty(studentPhone)) {
            wrapper.eq(SdLeave::getStudentPhone, studentPhone);
        }
        if (StrUtil.isNotEmpty(leaveType)) {
            wrapper.eq(SdLeave::getLeaveType, leaveType);
        }
        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(SdLeave::getStatus, status);
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public SdLeave getSdLeaveById(Long id) {
        return getById(id);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, SdLeave sdLeave) {
        sdLeave.setId(id);
        return this.baseMapper.updateById(sdLeave);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateStatusApprove(Long id, String status) {
        SdLeave sdLeave= new SdLeave();
        sdLeave.setId(id);
        sdLeave.setStatus(status);
        return this.baseMapper.updateById(sdLeave);
    }

    @Override
    public int insert(SdLeave sdLeave) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        sdLeave.setCreateTime(new Date());
        sdLeave.setStatus("1");
        System.out.println(format.format(sdLeave.getCreateTime()));
        System.out.println(sdLeave.getStudentNo());
        if(selectByTimeAndNo(format.format(sdLeave.getCreateTime()),sdLeave.getStudentNo()) == 0) {
            return this.baseMapper.insert(sdLeave);
        } else {
            return -1;
        }
    }

    @Override
    public Integer selectByTimeAndNo(String time, String studentNo) {
        return this.baseMapper.selectByTimeAndNo(time,studentNo);
    }
}
