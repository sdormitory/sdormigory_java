package cn.sdormitory.smartdor.service;

import cn.sdormitory.basedata.vo.BStudentVo;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.smartdor.entity.SdAttence;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.text.ParseException;
import java.util.Map;

/**
 * Created By ruanteng
 * DateTime：2020/11/27
 */
public interface SdAttenceService {

    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    CommonPage<SdAttence> getPage(Map<String, Object> params);

    /**
     * 添加考勤记录
     * @param vo
     * @return
     */
    int create(BStudentVo vo) throws ParseException;

    /**
     * 删除考勤记录
     */
    int delete(String [] id);


    /**
     * 总记录条数
     */
    int getCount(Map<String, Object> params);




}
