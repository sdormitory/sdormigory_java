package cn.sdormitory.smartdor.service.impl;

import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import cn.sdormitory.basedata.entity.BStudent;
import cn.sdormitory.basedata.service.BStudentService;
import cn.sdormitory.basedata.vo.BStudentVo;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.common.utils.DateTimeUtils;
import cn.sdormitory.common.utils.SmsSendTemplate;
import cn.sdormitory.smartdor.dao.SdAttenceDao;
import cn.sdormitory.smartdor.entity.OriginalRecord;
import cn.sdormitory.smartdor.entity.SdAttence;
import cn.sdormitory.smartdor.service.OriginalRecordService;
import cn.sdormitory.smartdor.service.SdAttenceService;
import cn.sdormitory.sysset.entity.SyssetSmsTemplate;
import cn.sdormitory.sysset.service.SyssetAttenceRuleService;
import cn.sdormitory.sysset.service.SyssetSmsTemplateService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created By ruanteng
 * DateTime：2020/11/27
 */
@Slf4j
@Service("sdAttenceServiceImpl")
public class SdAttenceServiceImpl extends ServiceImpl<SdAttenceDao, SdAttence> implements SdAttenceService {

    @Autowired
    private BStudentService bStudentService;

    @Autowired
    private OriginalRecordService originalRecordService;


    @Autowired
    private SyssetSmsTemplateService syssetSmsTemplateService;

    @Override
    public CommonPage<SdAttence> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));
        String dateStr = String.valueOf(params.get("checkDate"));
        if ("null".equals(dateStr)) {
            dateStr = null;
        }
        List<SdAttence> list = this.baseMapper.getList(dateStr, (pageNum - 1) * pageSize, pageSize);
        CommonPage commonPage = new CommonPage();
        commonPage.setList(list);
        commonPage.setPageNum(pageNum);
        commonPage.setPageSize(pageSize);
        long i = Long.valueOf(this.baseMapper.getListCount(dateStr));
        commonPage.setTotal(i);

        return commonPage;


    }

    @Override
    public void create(BStudentVo vo) throws ParseException {
        BStudent bStudent = bStudentService.getByStudentNo(vo.getPersonId());
        if(bStudent == null)
            return;
        List<OriginalRecord> list = originalRecordService.list();
        list.stream().forEach(a->{
            SdAttence sdAttence = new SdAttence();
            if(a.getAccessDate()==null){
                sdAttence.setAttenceStatus("2");
            }else{
                sdAttence.setAttenceStatus(a.getAttenceStatus());
            }
            sdAttence.setCreateTime(new Date());
            sdAttence.setStudentNo(a.getStudentNo());
            sdAttence.setDeviceNo(a.getDeviceNo());
            sdAttence.setAccessDate(a.getAccessDate());
            SyssetSmsTemplate syssetSmsTemplate = syssetSmsTemplateService.getSyssetSmsTemplateById(1L);
            if ("2".equals(sdAttence.getAttenceStatus())) {
                String text = syssetSmsTemplate.getSmsContent().replace("{student}", bStudent.getStudentName());
                SmsSendTemplate.sms(bStudent.getParentPhone(), text);
            }
            this.baseMapper.insert(sdAttence);
        });
    }

    @Override
    public int delete(String[] id) {
        int count = 0;
        try {
            bStudentService.removePerson(id);
            count = this.baseMapper.deleteBatchIds(Arrays.asList(id));
        } catch (Exception e) {
            e.printStackTrace();
            count = 0;
        }
        return count;

    }

    @Override
    public int getCount(Map<String, Object> params) {
        String dateStr = String.valueOf(params.get("checkDate"));
        if ("null".equals(dateStr)) {
            dateStr = null;
        }
        int count = this.baseMapper.getListCount(dateStr);
        return count;
    }


}
