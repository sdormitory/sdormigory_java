package cn.sdormitory.smartdor.service.impl;

import cn.sdormitory.basedata.entity.BClass;
import cn.sdormitory.basedata.entity.BDormitory;
import cn.sdormitory.basedata.entity.BStudent;
import cn.sdormitory.basedata.service.BClassService;
import cn.sdormitory.basedata.service.BDormitoryService;
import cn.sdormitory.basedata.service.BStudentService;
import cn.sdormitory.basedata.vo.BStudentVo;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.common.utils.DateTimeUtils;
import cn.sdormitory.common.utils.PropertiesUtils;
import cn.sdormitory.common.utils.SmsSendTemplate;
import cn.sdormitory.request.HttpRequest;
import cn.sdormitory.smartdor.dao.OriginalRecordDao;
import cn.sdormitory.smartdor.entity.OriginalRecord;
import cn.sdormitory.smartdor.entity.SdAttence;
import cn.sdormitory.smartdor.entity.SdDevice;
import cn.sdormitory.smartdor.service.OriginalRecordService;
import cn.sdormitory.smartdor.service.SdAttenceService;
import cn.sdormitory.smartdor.service.SdDeviceService;
import cn.sdormitory.sys.entity.SysUser;
import cn.sdormitory.sys.service.SysUserService;
import cn.sdormitory.sysset.entity.SyssetSmsTemplate;
import cn.sdormitory.sysset.service.SyssetAttenceRuleService;
import cn.sdormitory.sysset.service.SyssetSmsTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Autowired
    private SdAttenceService sdAttenceService;

    @Autowired
    private SyssetSmsTemplateService syssetSmsTemplateService;

    @Autowired
    private BClassService bClassService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BDormitoryService bDormitoryService;

    @Autowired
    private SdDeviceService sdDeviceService;

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

        //SdDevice sdDevice = sdDeviceService.getSdDeviceByIP();

        //获取该学生信息
        BStudent bStudent = bStudentService.getByStudentNo(vo.getPersonId());

        if (bStudent == null) {

            return 0;

        }

        OriginalRecord originalRecord = new OriginalRecord();

        originalRecord.setStudentNo(vo.getPersonId());

        originalRecord.setAccessDate(DateTimeUtils.dateTimeFormat(vo.getTs()));

        originalRecord.setDeviceNo(vo.getDeviceSn());

        originalRecord.setCreateTime(new Date());

        originalRecord.setAttenceStatus(syssetAttenceRuleService.getByAttenceRuleByTime(DateTimeUtils.dateTimeFormat(vo.getTs())));

        //当过闸的时间晚于正常考勤的最后时间直接将此过闸信息当做考勤
        if (new Date().getHours() >= CommonConstant.ATTENDANCE_TIME_INT) {

            SdAttence sdAttence = new SdAttence();

            sdAttence.setDeviceNo(originalRecord.getDeviceNo());

            sdAttence.setAccessDate(originalRecord.getAccessDate());

            sdAttence.setStudentNo(originalRecord.getStudentNo());

            sdAttence.setAttenceStatus(CommonConstant.ATTENCE_STATUS_LATENESS);

            sdAttence.setCreateTime(new Date());
            //将流水信息当做考勤插入
            sdAttenceService.insert(sdAttence);

            BClass bClass = bClassService.getBClassById(bStudent.getClassId());

            BDormitory bDormitory = bDormitoryService.getBDormitoryById(Long.valueOf(bStudent.getBdormitoryId()));

            SysUser sysUser = sysUserService.getUserById(bClass.getClassTeacherId());

            SysUser sysUser1 = sysUserService.getUserById(bDormitory.getId());

            SyssetSmsTemplate syssetSmsTemplate = syssetSmsTemplateService.getBySmsTypee(CommonConstant.SMS_TEMPLATE_TYPE_ATTENCE);

            String text = syssetSmsTemplate.getSmsContent().replace(CommonConstant.SMS_TEMPLATE_STR, bStudent.getStudentName());

            SmsSendTemplate.sms(bStudent.getParentPhone(), text);

            SmsSendTemplate.sms(sysUser.getPhone(), text);

            SmsSendTemplate.sms(sysUser1.getPhone(), text);

        }

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

        return this.baseMapper.listAll();

    }

    @Override
    public void removeRecord(double ts) {

        /**
         * 统计缺勤人员
         */
        sdAttenceService.statisticsLackStu();

        String key = PropertiesUtils.get("device.properties", "sdormitory.device1.key");

        String ip = PropertiesUtils.get("device.properties", "sdormitory.device1.ip");

        HttpRequest.sendPost(ip + "/removeRecord?key=" + key + "&ts=" + ts, null);

    }

    @Override
    public String listRecordByNumber(Integer number, Integer offset, Integer dbtype) {

        String key = PropertiesUtils.get("device.properties", "sdormitory.device1.key");

        String ip = PropertiesUtils.get("device.properties", "sdormitory.device1.ip");

        String object = HttpRequest.sendGet(ip + "/listRecordByNumber?key=" + key + "&dbtype=" + dbtype + "&number=" + number + "&offset=" + offset, null);

        return object;

    }


}
