package cn.sdormitory.controller.smartdor;

import cn.sdormitory.basedata.entity.BDormitory;
import cn.sdormitory.basedata.entity.BStudent;
import cn.sdormitory.basedata.service.BDormitoryService;
import cn.sdormitory.basedata.service.BStudentService;
import cn.sdormitory.common.annotation.SysLog;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.enums.BusinessType;
import cn.sdormitory.common.utils.SmsSendTemplate;
import cn.sdormitory.smartdor.entity.SdLeave;
import cn.sdormitory.smartdor.service.SdLeaveService;
import cn.sdormitory.sys.entity.SysUser;
import cn.sdormitory.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/27 21:54
 * @version：V1.0
 */
@RestController
@Api(tags = "Smartdor-sdleave=> 请假管理")
@RequestMapping("/smartdor/sdleave")
public class SdLeaveController {
    @Autowired
    private SdLeaveService sdLeaveService;
    @Autowired
    private BStudentService bStudentService;
    @Autowired
    private BDormitoryService bDormitoryService;
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("list => 查询请假列表信息")
    @PreAuthorize("@ss.hasPermi('smartdor:sdleave:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SdLeave>> list(@RequestParam Map<String, Object> params) {
        IPage<SdLeave> page = sdLeaveService.getPage(params);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("info/{id} => 请假信息")
    @PreAuthorize("@ss.hasPermi('smartdor:sdleave:query')")
    @GetMapping("/{id}")
    public CommonResult<SdLeave> info(@PathVariable("id") Long id) {
        SdLeave sdLeave = sdLeaveService.getSdLeaveById(id);
        return CommonResult.success(sdLeave);
    }

    @ApiOperation("update/approvestatus/{id} => 班主任审核请假申请")
    @PreAuthorize("@ss.hasPermi('smartdor:sdleave:approval')")
    @SysLog(title = "请假管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/approvestatus/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = sdLeaveService.updateStatusApprove(id, status);
        if (count > 0) {
            //请假信息审核通过后后发送短信给对应的学生、家长、宿管老师
            SdLeave sdLeave=sdLeaveService.getSdLeaveById(id);
            String content=sdLeave.getStudentName()+"申请的请假审批已通过!";
            SmsSendTemplate.sms(sdLeave.getStudentPhone(),content);
            BStudent bStudent=bStudentService.getByStudentNo(sdLeave.getStudentNo());
            SmsSendTemplate.sms(bStudent.getParentPhone(),content);
            BDormitory bDormitory=bDormitoryService.getBDormitoryById(Long.parseLong(bStudent.getBdormitoryId()));
            SysUser sysUser=sysUserService.getUserById(bDormitory.getDormitoryTeacherId());
            SmsSendTemplate.sms(sysUser.getPhone(),content);

            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 驳回请假信息")
    @PreAuthorize("@ss.hasPermi('smartdor:sdleave:reject')")
    @SysLog(title = "请假管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/reject/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SdLeave sdLeave) {
        int count = sdLeaveService.update(id, sdLeave);
        if (count > 0) {
            //驳回请假信息后发送短信给对应的学生及家长
            SdLeave sdLeave1=sdLeaveService.getSdLeaveById(id);
            String content=sdLeave.getStudentName()+"申请的请假审批被驳回，驳回原因：";
            SmsSendTemplate.sms(sdLeave1.getStudentPhone(),content+sdLeave.getTeacherDesc());
            BStudent bStudent=bStudentService.getByStudentNo(sdLeave1.getStudentNo());
            SmsSendTemplate.sms(bStudent.getParentPhone(),content+sdLeave.getTeacherDesc());

            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
