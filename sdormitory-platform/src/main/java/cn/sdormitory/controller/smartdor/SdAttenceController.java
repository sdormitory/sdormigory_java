package cn.sdormitory.controller.smartdor;

import cn.sdormitory.basedata.service.BStudentService;
import cn.sdormitory.basedata.vo.BStudentVo;
import cn.sdormitory.common.annotation.IgnoreAuth;
import cn.sdormitory.common.annotation.SysLog;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.enums.BusinessType;
import cn.sdormitory.smartdor.entity.SdAttence;
import cn.sdormitory.smartdor.entity.SdHygiene;
import cn.sdormitory.smartdor.service.SdAttenceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created By ruanteng
 * DateTime：2020/11/27
 */
@RestController
@Api(tags = "Smartdor-sdAttence=> 考勤管理")
@RequestMapping("/smartdor/sdAttence")
public class SdAttenceController {


    @Autowired
    private SdAttenceService sdAttenceService;


    @ApiOperation("list => 查询考勤人员列表")
    @PreAuthorize("@ss.hasPermi('smartdor:sdattence:query')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SdAttence>> list(@RequestParam Map<String, Object> params) throws ParseException {
        return CommonResult.success(sdAttenceService.getPage(params));
    }


    /**
     * 创建考勤信息信息
     * @throws ParseException
     */
    @IgnoreAuth
    @ApiOperation("=> 创建考勤信息")
    //@PreAuthorize("@ss.hasPermi('smartdor:sdattence:add')")
    @PostMapping("/setRecordCallback")
    public void setRecordCallback() throws ParseException {
        sdAttenceService.create();
    }


    @ApiOperation("deleteByIds/{ids} => 删除考勤信息")
    @PreAuthorize("@ss.hasPermi('smartdor:sdattence:remove')")
    @SysLog(title = "删除考勤信息", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable String[] ids) {
        int count = sdAttenceService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    /**
     * 定时考勤
     */
    @Scheduled(cron = "59 29 21 * * * ")
    public void attendance() throws ParseException {
        sdAttenceService.create();
    }



}
