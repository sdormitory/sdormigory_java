package cn.sdormitory.controller.report;

import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.api.CommonResult;
import cn.sdormitory.report.service.AttenceStatisticsService;
import cn.sdormitory.report.vo.AttenceStatisticsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/12/3 19:18
 * @version：V1.0
 */
@RestController
@Api(tags = "Report-AttenceStatistics=> 考勤统计")
@RequestMapping("/report/attencestatistics")
public class AttenceStatisticsController {
    @Autowired
    private AttenceStatisticsService attenceStatisticsService;

    @ApiOperation("list => 查询正常出勤率统计列表")
    @PreAuthorize("@ss.hasPermi('report:attencestatistics:list')")
    @GetMapping(value = "/list")
    public CommonResult<CommonPage<AttenceStatisticsVO>> list(@RequestParam Map<String, Object> params) {
        String accDate=(String)params.get("accessDate");
        String accessDate="";
        if("".equals(accDate) || accDate==null){
            Date date = new Date();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            String date1=fmt.format(date);
            accessDate=date1;
        }else{
            accessDate=accDate;
        }
        List<AttenceStatisticsVO> taskTotalVoList = attenceStatisticsService.getAttenceStaList(accessDate,Integer.parseInt((String)params.get("pageNum")),Integer.parseInt((String)params.get("pageSize")));
        CommonPage<AttenceStatisticsVO> commonPage = new CommonPage<AttenceStatisticsVO>();
        commonPage.setList(taskTotalVoList);
        commonPage.setTotal((long) taskTotalVoList.size());
        return CommonResult.success(commonPage);

    }


}
