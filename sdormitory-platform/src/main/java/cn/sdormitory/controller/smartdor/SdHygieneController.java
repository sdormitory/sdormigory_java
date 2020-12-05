package cn.sdormitory.controller.smartdor;

import cn.sdormitory.common.annotation.SysLog;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.common.enums.BusinessType;
import cn.sdormitory.smartdor.entity.SdHygiene;
import cn.sdormitory.smartdor.service.HygieneDeductService;
import cn.sdormitory.smartdor.service.SdHygieneService;
import cn.sdormitory.sysset.service.SyssetHygieneDeductService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/20 16:26
 * @version：V1.0
 */
@RestController
@Api(tags = "Smartdor-sdhygiene=> 班级管理")
@RequestMapping("/smartdor/sdhygiene")
public class SdHygieneController {
    @Autowired
    private SdHygieneService sdHygieneService;
    @Autowired
    private HygieneDeductService hygieneDeductService;
    @Autowired
    private SyssetHygieneDeductService syssetHygieneDeductService;


    @ApiOperation("list => 查询卫生列表信息")
    @PreAuthorize("@ss.hasPermi('smartdor:sdhygiene:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<SdHygiene>> list(@RequestParam Map<String, Object> params) {
        IPage<SdHygiene> page = sdHygieneService.getPage(params);
        page.getRecords().stream().forEach(sdhygiene -> {
            List<Long> deductIdList = hygieneDeductService.listDeductIdByHygieneId(sdhygiene.getId());
            sdhygiene.setDeductIds(deductIdList);
        });
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("info/{id} => 卫生信息")
    @PreAuthorize("@ss.hasPermi('smartdor:sdhygiene:query')")
    @GetMapping("/{id}")
    public CommonResult<SdHygiene> info(@PathVariable("id") Long id) {
        SdHygiene sdHygiene = sdHygieneService.getSdHygieneById(id);
        if (id != null) {
            //获取对应的卫生项列表
            List<Long> deductIdList = hygieneDeductService.listDeductIdByHygieneId(id);
            if(deductIdList!=null && deductIdList.size()>0){
                sdHygiene.setDeductIds(deductIdList);
            }

        }
        return CommonResult.success(sdHygiene);
    }

    @ApiOperation("update/{id} => 修改指定卫生信息")
    @PreAuthorize("@ss.hasPermi('smartdor:sdhygiene:edit')")
    @SysLog(title = "卫生管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Long id, @RequestBody SdHygiene sdHygiene) {
        SdHygiene dbSdHygieneInfo = sdHygieneService.getByCheckDateAndBdId(sdHygiene.getCheckDate(),sdHygiene.getBdormitoryId());
        if (dbSdHygieneInfo != null && !Objects.equals(dbSdHygieneInfo.getId(), sdHygiene.getId())) {
            return CommonResult.failed("该卫生信息已存在");
        }
        int count = 0;
        if(sdHygiene.getDeductIds()!=null) {
            int deductIdsCount = sdHygiene.getDeductIds().size();
            double pdeduct = 0;
            double totalPdeduct = 0;
            if (deductIdsCount > 0) {
                List<Long> deductIds = sdHygiene.getDeductIds();
                for (Long deductId : deductIds) {
                    pdeduct = syssetHygieneDeductService.getSyssetHygieneDeductById(deductId).getDeductValue();
                    totalPdeduct += pdeduct;
                }
                sdHygiene.setTotalPdeduct(totalPdeduct);
                sdHygiene.setTotalScore(CommonConstant.TOTALSCORE_VALUE - totalPdeduct);
                count = sdHygieneService.updateSdHygieneAndDeduct(sdHygiene);
            } else {
                sdHygiene.setTotalPdeduct(CommonConstant.TOTALPDEDUCT_VALUE);
                sdHygiene.setTotalScore(CommonConstant.TOTALSCORE_VALUE);
                count = sdHygieneService.update(id, sdHygiene);
            }
        }
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("create => 创建卫生信息")
    @PreAuthorize("@ss.hasPermi('smartdor:sdhygiene:add')")
    @SysLog(title = "卫生管理", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public CommonResult<Integer> create(@RequestBody SdHygiene sdHygiene, Principal principal) {
        SdHygiene dbSdHygieneInfo = sdHygieneService.getByCheckDateAndBdId(sdHygiene.getCheckDate(),sdHygiene.getBdormitoryId());
        if (dbSdHygieneInfo != null && !Objects.equals(dbSdHygieneInfo.getId(), sdHygiene.getId())) {
            return CommonResult.failed("该卫生信息已存在");
        }
        sdHygiene.setCreateBy(principal.getName());
        int count=0;
        if(sdHygiene.getDeductIds()!=null) {
            int deductIdsCount = sdHygiene.getDeductIds().size();
            double pdeduct = 0;
            double totalPdeduct = 0;
            if (deductIdsCount > 0) {
                List<Long> deductIds = sdHygiene.getDeductIds();
                for (Long deductId : deductIds) {
                    pdeduct = syssetHygieneDeductService.getSyssetHygieneDeductById(deductId).getDeductValue();
                    totalPdeduct += pdeduct;
                }
                sdHygiene.setTotalPdeduct(totalPdeduct);
                sdHygiene.setTotalScore(CommonConstant.TOTALSCORE_VALUE - totalPdeduct);
                count = sdHygieneService.insertSdHygieneAndDeduct(sdHygiene);
            } else {
                sdHygiene.setTotalPdeduct(CommonConstant.TOTALPDEDUCT_VALUE);
                sdHygiene.setTotalScore(CommonConstant.TOTALSCORE_VALUE);
                count = sdHygieneService.create(sdHygiene);
            }
        }
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("deleteByIds/{ids} => 删除指定卫生信息")
    @PreAuthorize("@ss.hasPermi('smartdor:sdhygiene:remove')")
    @SysLog(title = "卫生管理", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/deleteByIds/{ids}")
    public CommonResult<Integer> deleteByIds(@PathVariable Long[] ids) {
        int count = sdHygieneService.deleteByIds(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
