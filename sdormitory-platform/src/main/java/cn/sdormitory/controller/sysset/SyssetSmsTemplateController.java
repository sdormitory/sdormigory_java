package cn.sdormitory.controller.sysset;

import cn.sdormitory.common.annotation.SysLog;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.enums.BusinessType;
import cn.sdormitory.sysset.entity.SyssetSmsTemplate;
import cn.sdormitory.sysset.service.SyssetSmsTemplateService;
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
 * @创建时间：2020/11/18 14:42
 * @version：V1.0
 */
@RestController
@Api(tags = "Sysset-smstemplate=> 短信模板管理")
@RequestMapping("/sysset/smstemplate")
public class SyssetSmsTemplateController {
    @Autowired
    private SyssetSmsTemplateService syssetSmsTemplateService;

    @ApiOperation("list => 分页获取短信模板列表")
    @PreAuthorize("@ss.hasPermi('sysset:smstemplate:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SyssetSmsTemplate>> list(@RequestParam Map<String, Object> params) {
        IPage<SyssetSmsTemplate> page = syssetSmsTemplateService.getPage(params);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("{id} => 短信模板信息")
    @PreAuthorize("@ss.hasPermi('sysset:smstemplate:query')")
    @GetMapping("/{id}")
    public CommonResult<SyssetSmsTemplate> info(@PathVariable("id") Long id) {
        return CommonResult.success(syssetSmsTemplateService.getSyssetSmsTemplateById(id));
    }

    @ApiOperation("create => 新建短信模板信息")
    @PreAuthorize("@ss.hasPermi('sysset:smstemplate:add')")
    @SysLog(title = "短信模板管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody SyssetSmsTemplate syssetSmsTemplate) {
        SyssetSmsTemplate dbSyssetSmsTemplateInfo = syssetSmsTemplateService.getBySmsTitle(syssetSmsTemplate.getSmsTitle());
        if (dbSyssetSmsTemplateInfo != null && !Objects.equals(dbSyssetSmsTemplateInfo.getId(), syssetSmsTemplate.getId())) {
            return CommonResult.failed("该短信模板已存在");
        }
        int count = syssetSmsTemplateService.create(syssetSmsTemplate);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 修改短信模板信息")
    @PreAuthorize("@ss.hasPermi('sysset:smstemplate:edit')")
    @SysLog(title = "短信模板管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SyssetSmsTemplate syssetSmsTemplate) {
        SyssetSmsTemplate dbSyssetSmsTemplateInfo = syssetSmsTemplateService.getBySmsTitle(syssetSmsTemplate.getSmsTitle());
        if (dbSyssetSmsTemplateInfo != null && !Objects.equals(dbSyssetSmsTemplateInfo.getId(), syssetSmsTemplate.getId())) {
            return CommonResult.failed("该短信模板已存在");
        }
        int count = syssetSmsTemplateService.update(id, syssetSmsTemplate);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("deleteByIds/{ids} => 删除指定短信模板信息")
    @PreAuthorize("@ss.hasPermi('sysset:smstemplate:remove')")
    @SysLog(title = "短信模板管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = syssetSmsTemplateService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改短信模板状态")
    @PreAuthorize("@ss.hasPermi('sysset:smstemplate:edit')")
    @SysLog(title = "短信模板管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = syssetSmsTemplateService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
