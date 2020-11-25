package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 后台用户表
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
    List<SysUser> getClassTeacherList();

}
