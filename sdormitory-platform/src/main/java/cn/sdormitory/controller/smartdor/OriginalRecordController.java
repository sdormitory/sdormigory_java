package cn.sdormitory.controller.smartdor;

import cn.sdormitory.basedata.vo.BStudentVo;
import cn.sdormitory.common.annotation.IgnoreAuth;
import cn.sdormitory.common.annotation.SysLog;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.enums.BusinessType;
import cn.sdormitory.smartdor.entity.OriginalRecord;
import cn.sdormitory.smartdor.entity.SdAttence;
import cn.sdormitory.smartdor.service.OriginalRecordService;
import cn.sdormitory.smartdor.service.SdAttenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

/**
 * Created By ruanteng
 * DateTime：2020/12/7
 */
@RestController
@Api(tags = "Smartdor-sdAttence=> 过闸流水")
@RequestMapping("/smartdor/originalrecord")
public class OriginalRecordController {


    @Autowired
    private OriginalRecordService originalRecordService;

    @ApiOperation("list => 查询过闸流水人员列表")
    @PreAuthorize("@ss.hasPermi('smartdor:sdattence:query')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<OriginalRecord>> list(@RequestParam Map<String, Object> params) throws ParseException {
        return CommonResult.success(originalRecordService.getPage(params));
    }

    /**
     * 创建过闸流水
     * @param vo
     * @throws ParseException
     */
    @IgnoreAuth
    @ApiOperation("=> 创建考勤信息")
    //@PreAuthorize("@ss.hasPermi('smartdor:sdattence:add')")
    @PostMapping("/setRecordCallback")
    public void setRecordCallback(BStudentVo vo) throws ParseException {
        originalRecordService.create(vo);
    }

    @ApiOperation("deleteByIds/{ids} => 删除人员流水信息（数据库）")
    @PreAuthorize("@ss.hasPermi('smartdor:sdattence:remove')")
    @SysLog(title = "删除人员流水信息", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable String[] ids) {
        int count = originalRecordService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 定时删除过闸流水（闸机）
     */
    @Scheduled(cron = "59 59 23 * * * ")
    public void removeRecord(){
        originalRecordService.removeRecord(System.currentTimeMillis());
    }

}
