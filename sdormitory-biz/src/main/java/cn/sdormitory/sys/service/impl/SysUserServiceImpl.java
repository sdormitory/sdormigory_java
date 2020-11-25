package cn.sdormitory.sys.service.impl;

import cn.sdormitory.basedata.dao.BClassDao;
import cn.sdormitory.common.annotation.DataScope;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.common.utils.JwtTokenUtil;
import cn.sdormitory.common.utils.SysUserUtils;
import cn.sdormitory.sys.dao.SysUserDao;
import cn.sdormitory.sys.entity.SysUser;
import cn.sdormitory.sys.service.OnlineUserService;
import cn.sdormitory.sys.service.SysUserRoleService;
import cn.sdormitory.sys.service.SysUserService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 后台用户表
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private OnlineUserService onlineUserService;
    @Resource
    private SysUserDao sysUserDao;


    @Override
    public SysUser getByUserName(String username) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    public String login(String username, String password, HttpServletRequest request) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails.getUsername());

            SysUser sysUser = SysUserUtils.getSysUser();
            // 保存在线信息
            onlineUserService.save(sysUser, token, jwtTokenUtil.getExpiration(), request);

            boolean singleLogin = false;

            if (singleLogin) {
                //踢掉之前已经登录的token
                onlineUserService.checkLoginOnUser(sysUser.getUsername(), token);
            }
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(jwtTokenUtil.getTokenHead().length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public SysUser getUserById(Long id) {
        return getById(id);
    }


    @Override
    public int update(Long id, SysUser user) {
        user.setId(id);
        return this.baseMapper.updateById(user);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    @DataScope(userId = "id")
    public IPage<SysUser> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String username = (String) params.get("username");
        String phone = (String) params.get("phone");
        String deptId = (String) params.get("deptId");
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        String status = (String) params.get("status");

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(username)) {
            wrapper.like(SysUser::getUsername, username);
        }
        if (StrUtil.isNotEmpty(phone)) {
            wrapper.like(SysUser::getPhone, phone);
        }
        if (StrUtil.isNotEmpty(deptId)) {
            wrapper.apply("(dept_id = " + deptId + " OR dept_id IN ( SELECT t.id FROM sys_dept t WHERE FIND_IN_SET ( " + deptId + " , ancestors ) ))");
        }
        if (StrUtil.isNotEmpty(beginTime)) {
            wrapper.gt(SysUser::getCreateTime, beginTime);
        }

        if (StrUtil.isNotEmpty(endTime)) {
            wrapper.lt(SysUser::getCreateTime, endTime);
        }
        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(SysUser::getStatus, status);
        }
        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public int updateUserAndRole(SysUser user) {
        //先删除用户与角色关系
        sysUserRoleService.delAndCreateRole(user.getId(), user.getRoleIds());
        // 更新用户
        return this.baseMapper.updateById(user);
    }

    @Override
    public int updatePasswordByUserId(Long userId, String newPassword) {
        SysUser user = new SysUser();
        user.setPassword(newPassword);
        user.setId(userId);
        return this.baseMapper.updateById(user);
    }

    @Override
    public int updateStatus(Long id, String status) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setStatus(status);
        return this.baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUserAndRole(SysUser user) {
        int result = this.baseMapper.insert(user);
        //保存用户与角色关系
        sysUserRoleService.insertUserAndUserRole(user.getId(), user.getRoleIds());
        return result;
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<SysUser> getExportList(Map<String, Object> params) {

        String username = (String) params.get("username");
        String phone = (String) params.get("phone");
        String deptId = (String) params.get("deptId");
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        String status = (String) params.get("status");

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(username)) {
            wrapper.like(SysUser::getUsername, username);
        }
        if (StrUtil.isNotEmpty(phone)) {
            wrapper.like(SysUser::getPhone, phone);
        }
        if (StrUtil.isNotEmpty(deptId)) {
            wrapper.apply("(dept_id = " + deptId + " OR dept_id IN ( SELECT t.id FROM sys_dept t WHERE FIND_IN_SET ( " + deptId + " , ancestors ) ))");
        }
        if (StrUtil.isNotEmpty(beginTime)) {
            wrapper.gt(SysUser::getCreateTime, beginTime);
        }

        if (StrUtil.isNotEmpty(endTime)) {
            wrapper.lt(SysUser::getCreateTime, endTime);
        }
        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(SysUser::getStatus, status);
        }
        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return list(wrapper);
    }

    @Override
    public List<SysUser> getListAll() {
        return list(new LambdaQueryWrapper<SysUser>());
    }

    @Override
    public List<SysUser> getClassTeacherList() {
        return sysUserDao.getClassTeacherList();
    }


}