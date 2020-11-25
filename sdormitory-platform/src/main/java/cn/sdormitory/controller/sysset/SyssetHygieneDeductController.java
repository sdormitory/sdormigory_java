package cn.sdormitory.controller.sysset;

import cn.sdormitory.common.annotation.SysLog;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.enums.BusinessType;
import cn.sdormitory.sysset.entity.SyssetHygieneDeduct;
import cn.sdormitory.sysset.service.SyssetHygieneDeductService;
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
 * @创建时间：2020/11/19 16:59
 * @version：V1.0
 */
@RestController
@Api(tags = "Sysset-hygienededuct=> 卫生扣分管理")
@RequestMapping("/sysset/hygienededuct")
public class SyssetHygieneDeductController {
    @Autowired
    private SyssetHygieneDeductService syssetHygieneDeductService;

    @ApiOperation("list => 分页获取卫生扣分列表")
    @PreAuthorize("@ss.hasPermi('sysset:hygienededuct:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SyssetHygieneDeduct>> list(@RequestParam Map<String, Object> params) {
        IPage<SyssetHygieneDeduct> page = syssetHygieneDeductService.getPage(params);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("{id} => 卫生扣分信息")
    @PreAuthorize("@ss.hasPermi('sysset:hygienededuct:query')")
    @GetMapping("/{id}")
    public CommonResult<SyssetHygieneDeduct> info(@PathVariable("id") Long id) {
        return CommonResult.success(syssetHygieneDeductService.getSyssetHygieneDeductById(id));
    }

    @ApiOperation("create => 新建卫生扣分信息")
    @PreAuthorize("@ss.hasPermi('sysset:hygienededuct:add')")
    @SysLog(title = "卫生扣分管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody SyssetHygieneDeduct syssetHygieneDeduct) {
        SyssetHygieneDeduct dbSyssetHygieneDeductInfo = syssetHygieneDeductService.getByDeductOption(syssetHygieneDeduct.getDeductOption());
        if (dbSyssetHygieneDeductInfo != null && !Objects.equals(dbSyssetHygieneDeductInfo.getId(), syssetHygieneDeduct.getId())) {
            return CommonResult.failed("该卫生扣分项已存在");
        }
        int count = syssetHygieneDeductService.create(syssetHygieneDeduct);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 修改卫生扣分信息")
    @PreAuthorize("@ss.hasPermi('sysset:hygienededuct:edit')")
    @SysLog(title = "卫生扣分管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody SyssetHygieneDeduct syssetHygieneDeduct) {
        SyssetHygieneDeduct dbSyssetHygieneDeductInfo = syssetHygieneDeductService.getByDeductOption(syssetHygieneDeduct.getDeductOption());
        if (dbSyssetHygieneDeductInfo != null && !Objects.equals(dbSyssetHygieneDeductInfo.getId(), syssetHygieneDeduct.getId())) {
            return CommonResult.failed("该卫生扣分项已存在");
        }
        int count = syssetHygieneDeductService.update(id, syssetHygieneDeduct);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("deleteByIds/{ids} => 删除指定卫生扣分信息")
    @PreAuthorize("@ss.hasPermi('sysset:hygienededuct:remove')")
    @SysLog(title = "卫生扣分管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = syssetHygieneDeductService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改卫生扣分状态")
    @PreAuthorize("@ss.hasPermi('sysset:hygienededuct:edit')")
    @SysLog(title = "卫生扣分管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = syssetHygieneDeductService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("listAll => 获取所有有效卫生扣分项列表")
    @GetMapping(value = "/listAll")
    public CommonResult<List<SyssetHygieneDeduct>> listAll() {
        List<SyssetHygieneDeduct> bClassList = syssetHygieneDeductService.getListAll();
        return CommonResult.success(bClassList);
    }
}
