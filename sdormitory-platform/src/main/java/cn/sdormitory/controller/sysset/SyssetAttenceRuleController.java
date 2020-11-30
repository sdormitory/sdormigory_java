package cn.sdormitory.controller.sysset;

import cn.sdormitory.common.annotation.SysLog;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.enums.BusinessType;
import cn.sdormitory.sysset.entity.SyssetAttenceRule;
import cn.sdormitory.sysset.service.SyssetAttenceRuleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.*;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/26 16:19
 * @version：V1.0
 */
@RestController
@Api(tags = "Sysset-attencerule=> 考勤规则管理")
@RequestMapping("/sysset/attencerule")
public class SyssetAttenceRuleController {
    @Autowired
    private SyssetAttenceRuleService syssetAttenceRuleService;

    @ApiOperation("list => 分页获取考勤规则列表")
    @PreAuthorize("@ss.hasPermi('sysset:attencerule:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SyssetAttenceRule>> list(@RequestParam Map<String, Object> params) {
        IPage<SyssetAttenceRule> page = syssetAttenceRuleService.getPage(params);
        List<SyssetAttenceRule> attenceRuleList=page.getRecords();
        System.out.println("11111111111  "+attenceRuleList.size());
        if(attenceRuleList!=null && attenceRuleList.size()>0){
            for (SyssetAttenceRule syssetAttenceRule:attenceRuleList) {
                if(syssetAttenceRule.getAttenceDay()!=null && !"".equals(syssetAttenceRule.getAttenceDay())&&!"[]".equals(syssetAttenceRule.getAttenceDay())){
                    String str=syssetAttenceRule.getAttenceDay();
                    String sbstr="";
                    if(str.contains("1")){
                        sbstr=sbstr+"星期一,";
                    }
                    if(str.contains("2")){
                        sbstr=sbstr+"星期二,";
                    }
                    if(str.contains("3")){
                        sbstr=sbstr+"星期三,";
                    }
                    if(str.contains("4")){
                        sbstr=sbstr+"星期四,";
                    }
                    if(str.contains("5")){
                        sbstr=sbstr+"星期五,";
                    }
                    if(str.contains("6")){
                        sbstr=sbstr+"星期六,";
                    }
                    if(str.contains("7")){
                        sbstr=sbstr+"星期天,";
                    }
                    syssetAttenceRule.setAttenceDay(sbstr.substring(0,sbstr.length()-1));
                }else if("[]".equals(syssetAttenceRule.getAttenceDay())){
                    syssetAttenceRule.setAttenceDay("");
                }

            }
            page.setRecords(attenceRuleList);
        }

        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("{id} => 考勤规则信息")
    @PreAuthorize("@ss.hasPermi('sysset:attencerule:query')")
    @GetMapping("/{id}")
    public CommonResult<SyssetAttenceRule> info(@PathVariable("id") Long id) {
        return CommonResult.success(syssetAttenceRuleService.getSyssetAttenceRuleById(id));
    }

    @ApiOperation("create => 新建考勤规则信息")
    @PreAuthorize("@ss.hasPermi('sysset:attencerule:add')")
    @SysLog(title = "考勤规则管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody SyssetAttenceRule syssetAttenceRule) {
        SyssetAttenceRule dbSyssetAttenceRuleInfo = syssetAttenceRuleService.getByAttenceRuleName(syssetAttenceRule.getAttenceRuleName());
        if (dbSyssetAttenceRuleInfo != null && !Objects.equals(dbSyssetAttenceRuleInfo.getId(), dbSyssetAttenceRuleInfo.getId())) {
            return CommonResult.failed("该考勤规则名称已存在");
        }
        int count = syssetAttenceRuleService.create(syssetAttenceRule);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 修改考勤规则信息")
    @PreAuthorize("@ss.hasPermi('sysset:attencerule:edit')")
    @SysLog(title = "考勤规则管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SyssetAttenceRule syssetAttenceRule) {
        SyssetAttenceRule dbSyssetAttenceRuleInfo = syssetAttenceRuleService.getByAttenceRuleName(syssetAttenceRule.getAttenceRuleName());
        if (dbSyssetAttenceRuleInfo != null && !Objects.equals(dbSyssetAttenceRuleInfo.getId(), dbSyssetAttenceRuleInfo.getId())) {
            return CommonResult.failed("该考勤规则名称已存在");
        }
        int count = syssetAttenceRuleService.update(id, syssetAttenceRule);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("deleteByIds/{ids} => 删除指定考勤规则信息")
    @PreAuthorize("@ss.hasPermi('sysset:attencerule:remove')")
    @SysLog(title = "考勤规则管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = syssetAttenceRuleService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改考勤规则状态")
    @PreAuthorize("@ss.hasPermi('sysset:attencerule:edit')")
    @SysLog(title = "考勤规则管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = syssetAttenceRuleService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("listAll => 获取所有有效考勤规则列表")
    @GetMapping(value = "/listAll")
    public CommonResult<List<SyssetAttenceRule>> listAll() {
        List<SyssetAttenceRule> syssetAttenceRuleList = syssetAttenceRuleService.getListAll();
        return CommonResult.success(syssetAttenceRuleList);
    }


}
