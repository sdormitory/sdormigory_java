package cn.sdormitory.controller.basedata;

import cn.sdormitory.basedata.entity.BDormitory;
import cn.sdormitory.basedata.service.BDormitoryService;
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

import java.util.*;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/7 20:35
 * @version：V1.0
 */
@RestController
@Api(tags = "Basedata-bdormitory=> 宿舍管理")
@RequestMapping("/basedata/bdormitory")
public class BDormitoryController {
    @Autowired
    private BDormitoryService bDormitoryService;

    @ApiOperation("list => 根据几栋或几楼或宿舍号或状态分页获取宿舍列表")
    @PreAuthorize("@ss.hasPermi('basedata:bdormitory:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<BDormitory>> list(@RequestParam Map<String, Object> params) {
        IPage<BDormitory> page = bDormitoryService.getPage(params);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("{id} => 宿舍信息")
    @PreAuthorize("@ss.hasPermi('basedata:bdormitory:query')")
    @GetMapping("/{id}")
    public CommonResult<BDormitory> info(@PathVariable("id") Long id) {
        return CommonResult.success(bDormitoryService.getBDormitoryById(id));
    }

    @ApiOperation("create => 新建宿舍信息")
    @PreAuthorize("@ss.hasPermi('basedata:bdormitory:add')")
    @SysLog(title = "宿舍管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody BDormitory bDormitory) {
        BDormitory dbDormitoryInfo = bDormitoryService.getByDormitoryInfo(bDormitory.getBuildingNo(),bDormitory.getStorey(),bDormitory.getDormitoryNo());
        if (dbDormitoryInfo != null && !Objects.equals(dbDormitoryInfo.getId(), bDormitory.getId())) {
            return CommonResult.failed("该宿舍已存在");
        }
        int count = bDormitoryService.create(bDormitory);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("update/{id} => 修改宿舍信息")
    @PreAuthorize("@ss.hasPermi('basedata:bdormitory:edit')")
    @SysLog(title = "宿舍管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @RequestBody BDormitory bDormitory) {
        BDormitory dbDormitoryInfo = bDormitoryService.getByDormitoryInfo(bDormitory.getBuildingNo(),bDormitory.getStorey(),bDormitory.getDormitoryNo());
        if (dbDormitoryInfo != null && !Objects.equals(dbDormitoryInfo.getId(), bDormitory.getId())) {
            return CommonResult.failed("该宿舍已存在");
        }
        int count = bDormitoryService.update(id, bDormitory);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("deleteByIds/{ids} => 删除指定宿舍信息")
    @PreAuthorize("@ss.hasPermi('basedata:bdormitory:remove')")
    @SysLog(title = "宿舍管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = bDormitoryService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("update/status/{id} => 修改宿舍状态")
    @PreAuthorize("@ss.hasPermi('basedata:bdormitory:edit')")
    @SysLog(title = "宿舍管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/status/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, String status) {
        int count = bDormitoryService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("listAll => 获取所有宿舍列表")
    @GetMapping(value = "/listAll")
    public CommonResult<List<BDormitory>> listAll() {
        List<BDormitory> bDormitoryList = bDormitoryService.getListAll();
        return CommonResult.success(bDormitoryList);
    }

    @ApiOperation("listAll => 根据宿舍栋号和楼层查询宿舍信息")
    @GetMapping(value = "/getBDormitoryListByCol")
    public CommonResult<List<BDormitory>> getBDormitoryListByCol(String buildingNo,String storey) {
        List<BDormitory> bDormitoryList = bDormitoryService.getBDormitoryListByCol(buildingNo,storey);
        System.out.println("bDormitoryList Size :     "+bDormitoryList.size());
        return CommonResult.success(bDormitoryList);
    }



}
