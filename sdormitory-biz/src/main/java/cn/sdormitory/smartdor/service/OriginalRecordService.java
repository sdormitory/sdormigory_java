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
 * Created By ruanteng
 * DateTime：2020/12/7
 */

public interface OriginalRecordService extends IService<OriginalRecord> {


    /**
     * 分页获取过闸流水数据
     *
     * @param params
     * @return
     */
    CommonPage<OriginalRecord> getPage(Map<String, Object> params);

    /**
     * 添加过闸流水记录
     *
     * @param vo
     * @return
     */
    int create(BStudentVo vo) throws ParseException;

    /**
     * 删除过闸流水记录
     */
    int delete(String[] id);


    /**
     * 总记录条数
     */
    int getCount(Map<String, Object> params);

    /**
     * 返回当天所有学生最后一次过闸时间
     *
     * @return
     */
    List<OriginalRecord> getListByDate();

    /**
     * 删除过闸流水日志
     *
     * @param ts
     * @return
     */
    void removeRecord(double ts);

    /**
     * 获取批量过闸流水信息
     *
     * @param number
     * @param offset
     * @param dbtype
     * @return
     */
    String listRecordByNumber(Integer number, Integer offset, Integer dbtype);



}
