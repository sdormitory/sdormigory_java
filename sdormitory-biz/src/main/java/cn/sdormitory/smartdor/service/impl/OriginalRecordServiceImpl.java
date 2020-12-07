package cn.sdormitory.smartdor.service.impl;

import cn.sdormitory.basedata.entity.BStudent;
import cn.sdormitory.basedata.service.BStudentService;
import cn.sdormitory.basedata.vo.BStudentVo;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.utils.DateTimeUtils;
import cn.sdormitory.smartdor.dao.OriginalRecordDao;
import cn.sdormitory.smartdor.entity.OriginalRecord;
import cn.sdormitory.smartdor.service.OriginalRecordService;
import cn.sdormitory.sysset.service.SyssetAttenceRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created By ruanteng
 * DateTime：2020/12/7
 */
@Service("originalRecordService")
public class OriginalRecordServiceImpl extends ServiceImpl<OriginalRecordDao, OriginalRecord> implements OriginalRecordService {


    @Autowired
    private BStudentService bStudentService;

    @Autowired
    private SyssetAttenceRuleService syssetAttenceRuleService;


    @Override
    public CommonPage<OriginalRecord> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));
        String dateStr = String.valueOf(params.get("checkDate"));
        if ("null".equals(dateStr)) {
            dateStr = null;
        }
        List<OriginalRecord> list = this.baseMapper.getList(dateStr, (pageNum - 1) * pageSize, pageSize);
        CommonPage commonPage = new CommonPage();
        commonPage.setList(list);
        commonPage.setPageNum(pageNum);
        commonPage.setPageSize(pageSize);
        long i = Long.valueOf(this.baseMapper.getListCount(dateStr));
        commonPage.setTotal(i);
        return commonPage;


    }

    @Override
    public int create(BStudentVo vo) throws ParseException {
        BStudent bStudent = bStudentService.getByStudentNo(vo.getPersonId());
        if(bStudent==null){
            return 0;
        }
        OriginalRecord originalRecord = new OriginalRecord();
        originalRecord.setStudentNo(vo.getPersonId());
        originalRecord.setAccessDate(DateTimeUtils.dateTimeFormat(vo.getTs()));
        originalRecord.setDeviceNo(vo.getDeviceSn());
        originalRecord.setCreateTime(new Date());
        originalRecord.setAttenceStatus(syssetAttenceRuleService.getByAttenceRuleByTime(DateTimeUtils.dateTimeFormat(vo.getTs())));
       /* SdAttence sdAttence = new SdAttence();
        SyssetSmsTemplate syssetSmsTemplate = syssetSmsTemplateService.getSyssetSmsTemplateById(1L);
        if ("2".equals(sdAttence.getAttenceStatus())) {
            String text = syssetSmsTemplate.getSmsContent().replace("{student}", bStudent.getStudentName());
            SmsSendTemplate.sms(bStudent.getParentPhone(), text);
        }
        int i = this.baseMapper.insert(sdAttence);*/
        return this.baseMapper.insert(originalRecord);
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

    @Override
    public List<OriginalRecord> getListByDate() {
        return this.baseMapper.list();
    }


}
