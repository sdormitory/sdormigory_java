package cn.sdormitory.controller.basedata;

import cn.sdormitory.basedata.entity.BClass;
import cn.sdormitory.basedata.service.BClassService;
import cn.sdormitory.common.annotation.SysLog;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.enums.BusinessType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/3 14:41
 * @version：V1.0
 */
@RestController
@Api(tags = "Basedata-bclass=> 班级管理")
@RequestMapping("/basedata/bclass")
public class BClassController {
    @Autowired
    private BClassService bClassService;

    @ApiOperation("list => 根据班级名称或状态分页获取班级列表")
    @PreAuthorize("@ss.hasPermi('basedata:bclass:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<BClass>> list(@RequestParam Map<String, Object> params) {
        List<BClass> bClassList=bClassService.getBClassList((String)params.get("className"),(String)params.get("status"),(String)params.get("classTeacherName"),Integer.parseInt((String)params.get("pageNum")),Integer.parseInt((String)params.get("pageSize")));
        IPage<BClass> page = bClassService.getPage(params);
        page.setRecords(bClassList);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("{id} => 班级信息")
    @PreAuthorize("@ss.hasPermi('basedata:bclass:query')")
    @GetMapping("/{id}")
    public CommonResult<BClass> info(@PathVariable("id") Long id) {
        return CommonResult.success(bClassService.getBClassById(id));
    }

    @ApiOperation("create => 新建班级信息")
    @PreAuthorize("@ss.hasPermi('basedata:bclass:add')")
    @SysLog(title = "班级管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody BClass bClass) {
        BClass dbClassNameInfo = bClassService.getByClassName(bClass.getClassName());
        if (dbClassNameInfo != null && !Objects.equals(dbClassNameInfo.getId(), bClass.getId())) {
            return CommonResult.failed("该班级已存在");
        }
        int count = bClassService.create(bClass);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/{id} => 修改班级信息")
    @PreAuthorize("@ss.hasPermi('basedata:bclass:edit')")
    @SysLog(title = "班级管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody BClass bClass) {
        BClass dbClassNameInfo = bClassService.getByClassName(bClass.getClassName());
        if (dbClassNameInfo != null && !Objects.equals(dbClassNameInfo.getId(), bClass.getId())) {
            return CommonResult.failed("该班级已存在");
        }
        int count = bClassService.update(id, bClass);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("deleteByIds/{ids} => 删除指定班级信息")
    @PreAuthorize("@ss.hasPermi('basedata:bclass:remove')")
    @SysLog(title = "班级管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = bClassService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改班级状态")
    @PreAuthorize("@ss.hasPermi('basedata:bclass:edit')")
    @SysLog(title = "班级管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = bClassService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("listAll => 获取所有有效班级列表")
    @GetMapping(value = "/listAll")
    public CommonResult<List<BClass>> listAll() {
        List<BClass> bClassList = bClassService.getListAll();
        return CommonResult.success(bClassList);
    }
}
