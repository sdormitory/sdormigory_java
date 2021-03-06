package cn.sdormitory.basedata.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.basedata.dao.BStudentDao;
import cn.sdormitory.basedata.entity.BStudent;
import cn.sdormitory.basedata.service.BStudentService;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.common.utils.PropertiesUtils;
import cn.sdormitory.request.HttpRequest;
import cn.sdormitory.smartdor.dao.OriginalRecordDao;
import cn.sdormitory.smartdor.service.OriginalRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/10 16:30
 * @version：V1.0
 */
@Slf4j
@Service("bStudentService")
public class BStudentServiceImpl extends ServiceImpl<BStudentDao, BStudent> implements BStudentService{


    @Resource
    private OriginalRecordDao originalRecordDao;


    @Override
    public IPage<BStudent> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String studentName = (String) params.get("studentName");
        String studentNo = (String) params.get("studentNo");
        String className = (String) params.get("className");
        String buildingNo = (String) params.get("buildingNo");
        String storey = (String) params.get("storey");
        String dormitoryNo = (String) params.get("dormitoryNo");
        String status = (String) params.get("status");

        LambdaQueryWrapper<BStudent> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(studentName)) {
            wrapper.eq(BStudent::getStudentName, studentName);
        }
        if (StrUtil.isNotEmpty(studentNo)) {
            wrapper.eq(BStudent::getStudentNo, studentNo);
        }
        if (StrUtil.isNotEmpty(className)) {
            wrapper.eq(BStudent::getClassName, className);
        }
        if (StrUtil.isNotEmpty(buildingNo)) {
            wrapper.eq(BStudent::getBuildingNo, buildingNo);
        }
        if (StrUtil.isNotEmpty(storey)) {
            wrapper.eq(BStudent::getStorey, storey);
        }
        if (StrUtil.isNotEmpty(dormitoryNo)) {
            wrapper.eq(BStudent::getDormitoryNo, dormitoryNo);
        }
        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(BStudent::getStatus, status);
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public BStudent getBStudentById(Long id) {
        return getById(id);
    }

    @Override
    public int create(BStudent bStudent) {
        int count=0;
        count = this.baseMapper.insert(bStudent);
        if(count>0){
            try {
                this.setPerson(bStudent);
            }catch (Exception e){
                e.printStackTrace();
                count=0;
            }
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        this.removePersonById(id.toString());
        originalRecordDao.deleteById(id);
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, BStudent bStudent) {
        bStudent.setId(id);
        return this.baseMapper.updateById(bStudent);
    }

    @Override
    public int deleteByIds(String[] ids) {
        List<String> list = new ArrayList<>();
        List<BStudent> bStudents = this.baseMapper.selectBatchIds(Arrays.asList(ids));
        bStudents.stream().forEach(a->{
            list.add(a.getStudentNo());
        });
        this.removePerson(list.toArray());
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateStatus(Long id, String status) {
        BStudent bStudent = new BStudent();
        bStudent.setId(id);
        bStudent.setStatus(status);
        return this.baseMapper.updateById(bStudent);
    }

    @Override
    public BStudent getByStudentNo(String studentNo) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<BStudent>().eq(BStudent::getStudentNo, studentNo));
    }

    @Override
    public String setPerson(BStudent bStudent) {
        String key = PropertiesUtils.get("device.properties", "sdormitory.device1.key");
        String ip = PropertiesUtils.get("device.properties","sdormitory.device1.ip");
        List pairs = new ArrayList();
        String base64;
        base64 = Base64.encodeBase64String(bStudent.getPhoto());
        pairs.add(new BasicNameValuePair("key",key));
        pairs.add(new BasicNameValuePair("id",bStudent.getStudentNo()));
        pairs.add(new BasicNameValuePair("name",bStudent.getStudentName()));
        pairs.add(new BasicNameValuePair("IC_NO",bStudent.getStudentNo()));
        pairs.add(new BasicNameValuePair("ID_NO",bStudent.getIdcard()));
        pairs.add(new BasicNameValuePair("photo",base64));
        pairs.add(new BasicNameValuePair("passCount","10000"));
        pairs.add(new BasicNameValuePair("startTs","-1"));
        pairs.add(new BasicNameValuePair("endTs","-1"));
        pairs.add(new BasicNameValuePair("visitor","false"));
        String object = HttpRequest.sendPost(ip+"/setPerson",pairs);
        return object;
    }

    @Override
    public String getPerson( String id) {
        String key = PropertiesUtils.get("device.properties", "sdormitory.device1.key");
        String ip = PropertiesUtils.get("device.properties","sdormitory.device1.ip");
        String object = HttpRequest.sendGetParameterFree(ip+"/getPerson?key="+key+"&id="+id);
        return object;
    }

    @Override
    public String listPersonByNumber(int number, int offset) {
        String key = PropertiesUtils.get("device.properties", "sdormitory.device1.key");
        String ip = PropertiesUtils.get("device.properties","sdormitory.device1.ip");
        String object = HttpRequest.sendGetParameterFree(ip+"/listPersonByNumber?key="+key+"&number="+number+"&offset="+offset);
        return object;
    }

    @Override
    public String removePerson(Object [] id) {
        String key = PropertiesUtils.get("device.properties", "sdormitory.device1.key");
        String ip = PropertiesUtils.get("device.properties","sdormitory.device1.ip");
        String object = HttpRequest.sendPostParameterFree(ip+"/removePerson?key="+key+"&id="+id);
        return object;
    }

    @Override
    public String removePersonById(String id) {
        String key = PropertiesUtils.get("device.properties", "sdormitory.device1.key");
        String ip = PropertiesUtils.get("device.properties","sdormitory.device1.ip");
        String object = HttpRequest.sendPostParameterFree(ip+"/removePerson?key="+key+"&id="+id);
        return object;
    }


}
