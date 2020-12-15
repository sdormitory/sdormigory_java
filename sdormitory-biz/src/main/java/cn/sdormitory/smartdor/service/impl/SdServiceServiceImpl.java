package cn.sdormitory.smartdor.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.basedata.entity.BClass;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.common.utils.PropertiesUtils;
import cn.sdormitory.request.HttpRequest;
import cn.sdormitory.smartdor.dao.SdDeviceDao;
import cn.sdormitory.smartdor.entity.SdDevice;
import cn.sdormitory.smartdor.service.SdDeviceService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * create By: Zhou Yinsen
 * date: 11/28/2020 11:00 AM
 * description:
 */
@Slf4j
@Service("sdDeviceService")
public class SdServiceServiceImpl extends ServiceImpl<SdDeviceDao, SdDevice> implements SdDeviceService {


    static String ip = null;
    static String key = null;

    static {
        ip = PropertiesUtils.get("device.properties","sdormitory.device1.ip");
        key = PropertiesUtils.get("device.properties","sdormitory.device1.key");
    }

    @Override
    public IPage<SdDevice> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String deviceName = (String) params.get("deviceName");

        LambdaQueryWrapper<SdDevice> wrapper = new LambdaQueryWrapper<>();


        if (StrUtil.isNotEmpty(deviceName)) {
            wrapper.eq(SdDevice::getDeviceName, deviceName);
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public SdDevice getSdDeviceById(Long id) {
        return baseMapper.getSdDeviceByID(id);
    }

    @Override
    public boolean getSdDeviceByIP(String deviceIpAddress) {
        return baseMapper.getSdDeviceByIP(deviceIpAddress,null) == null ? true : false;
    }

    @Override
    public int create(SdDevice sdDevice) {
        sdDevice.setDeviceNo(sdDevice.getSn());
        sdDevice.setDeviceName(sdDevice.getName());
        sdDevice.setCreateTime(new Date());
        return this.baseMapper.insert(sdDevice);
    }

    @Override
    public int update(SdDevice sdDevice) {
        sdDevice.setModifyTime(new Date());
        return this.baseMapper.updateById(sdDevice);
    }

    @Override
    public String getDeviceInfo() {
        String object = HttpRequest.sendGet(ip+"/getDeviceInfo","key="+key);
        return object;
    }



    @Override
    public String setDeviceInfo(SdDevice sdDevice) {
        List pairs = this.getListDevice(sdDevice);
        String object = HttpRequest.sendPost(sdDevice.getDeviceIpAddress()+"/setDeviceInfo",pairs);
        return object;
    }

    /**
     * 封装 参数
     * @param sdDevice
     * @return
     */
    private List getListDevice(SdDevice sdDevice){

        List pairs = new ArrayList();
        pairs.add(new BasicNameValuePair("cameraDetectType",sdDevice.getCameraDetectType()));
        pairs.add(new BasicNameValuePair("faceFeaturePairNumber",""+sdDevice.getFaceFeaturePairNumber()));
        pairs.add(new BasicNameValuePair("faceFeaturePairSuccessOrFailWaitTime",""+sdDevice.getFaceFeaturePairSuccessOrFailWaitTime()));
        pairs.add(new BasicNameValuePair("openDoorType",sdDevice.getOpenDoorType()));
        pairs.add(new BasicNameValuePair("openDoorContinueTime",""+sdDevice.getOpenDoorContinueTime()));
        pairs.add(new BasicNameValuePair("doorType",sdDevice.getDoorType()));
        pairs.add(new BasicNameValuePair("idCardFaceFeaturePairNumber",""+sdDevice.getIdCardFaceFeaturePairNumber()));
        pairs.add(new BasicNameValuePair("appWelcomeMsg",sdDevice.getAppWelcomeMsg()));
        pairs.add(new BasicNameValuePair("deviceSoundSize",""+sdDevice.getDeviceSoundSize()));
        pairs.add(new BasicNameValuePair("deviceDefendTime",sdDevice.getDeviceDefendTime()));
        pairs.add(new BasicNameValuePair("deviceName",sdDevice.getDeviceName()));
        pairs.add(new BasicNameValuePair("tipsPairFail",sdDevice.getTipsPairFail()));
        pairs.add(new BasicNameValuePair("picQualityRate",""+sdDevice.getPicQualityRate()));
        pairs.add(new BasicNameValuePair("beginRecoDistance",""+sdDevice.getBeginRecoDistance()));
        pairs.add(new BasicNameValuePair("pairSuccessOpenDoor",sdDevice.getPairSuccessOpenDoor()));
        pairs.add(new BasicNameValuePair("fillLightTimes",sdDevice.getFillLightTimes()));
        pairs.add(new BasicNameValuePair("lowPowerTimes",sdDevice.getLowPowerTimes()));
        pairs.add(new BasicNameValuePair("recognitionSwitch",sdDevice.getRecognitionSwitch()));

        return pairs;
    }

    @Override
    public int updateStatus(Long id, String status) {
        SdDevice sdDevice = new SdDevice();
        sdDevice.setId(id);
        sdDevice.setStatus(status);
        return this.baseMapper.updateById(sdDevice);
    }
}
