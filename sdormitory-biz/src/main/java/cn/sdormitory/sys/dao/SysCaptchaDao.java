package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysCaptcha;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图片验证码
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptcha> {
	
}
