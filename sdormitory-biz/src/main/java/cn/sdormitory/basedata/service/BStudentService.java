package cn.sdormitory.basedata.service;

import cn.sdormitory.basedata.entity.BStudent;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.io.InputStream;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/10 16:22
 * @version：V1.0
 */
public interface BStudentService {
    /**
     * 分页获取数据
     *
     * @param params
     * @return
     */
    public IPage<BStudent> getPage(Map<String, Object> params);

    /**
     * 根据id 获取学员信息
     * @param id
     * @return
     */
    public BStudent getBStudentById(Long id);

    /**
     * 新建学员信息
     *
     * @param bStudent
     * @return
     */
    int create(BStudent bStudent);

    /**
     * 删除指定学员信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改指定学员信息
     *
     * @param id
     * @param bStudent
     * @return
     */
    int update(Long id, BStudent bStudent);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteByIds(String[] ids);

    /**
     * 修改学员状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id, String status);

    /**
     * 根据学号获取学员信息
     *
     * @param studentNo 学号
     */
    BStudent getByStudentNo(String studentNo);


    /**
     * 创建过闸人信息
     * @param bStudent 学生信息
     */
    JSONObject setPerson(BStudent bStudent);


    /**
     * 获取过闸人信息
     * @param id    人员id
     * @return
     */
    JSONObject getPerson( String id);


    /**
     *  获取批量过闸人人员信息
     * @param number 批量大小（最大50）
     * @param offset 从0开始至最大结束
     * @return
     */
    JSONObject listPersonByNumber(int number,int offset);

    /**
     * 根据id删除过闸人员信息
     * @param id    人员id
     * @return
     */
    JSONObject removePerson( String [] id);


    /**
     *获取批量过闸流水信息
     * @param number
     * @param offset
     * @param dbtype
     * @return
     */
    JSONObject listRecordByNumber(Integer number,Integer offset,Integer dbtype);


    /**
     * 删除过闸流水日志
     * @param ts
     * @return
     */
    JSON removeRecord(double ts);

}
