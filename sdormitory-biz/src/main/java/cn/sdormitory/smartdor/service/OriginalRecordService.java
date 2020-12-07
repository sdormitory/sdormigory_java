package cn.sdormitory.smartdor.service;



import cn.sdormitory.basedata.vo.BStudentVo;
import cn.sdormitory.common.api.CommonPage;
import cn.sdormitory.smartdor.entity.OriginalRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  Created By ruanteng
 *  DateTime：2020/12/7
 */

public interface OriginalRecordService  extends IService<OriginalRecord> {


    /**
     * 分页获取过闸流水数据
     *
     * @param params
     * @return
     */
    CommonPage<OriginalRecord> getPage(Map<String, Object> params);

    /**
     * 添加过闸流水记录
     * @param vo
     * @return
     */
    int create(BStudentVo vo) throws ParseException;

    /**
     * 删除过闸流水记录
     */
    int delete(String [] id);


    /**
     * 总记录条数
     */
    int getCount(Map<String, Object> params);

    /**
     * 返回当天所有学生最后一次过闸时间
     * @return
     */
    List<OriginalRecord> getListByDate();


}
