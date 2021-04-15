package com.wjc.gateway.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oracrle.common.exception.AdminUserException;
import com.wjc.gateway.entity.AdminUser;
import com.wjc.gateway.mapper.AdminUserMapper;
import com.wjc.gateway.service.IAdminUserRoleService;
import com.wjc.gateway.service.IAdminUserService;
import com.wjc.gateway.service.IUserPositionService;
import com.wjc.gateway.util.JwtUtil;
import com.wjc.gateway.vo.AdminUserSaveVo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.oracrle.common.constants.ResultCodeEnum.*;


/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements IAdminUserService {
    @Autowired
    private IAdminUserRoleService adminUserRoleService;
    @Autowired
    private IUserPositionService userPositionService;

    @Override
    public AdminUser queryByUserId(Long userId) {
        return lambdaQuery().eq(AdminUser::getUserId, userId).one();
    }

    @Override
    public AdminUser queryUserByToken() {
        String userId = JwtUtil.getParam("userId");
        return queryByUserId(Long.valueOf(userId));
    }

    @Override
    public AdminUser queryByUserName(String username) {
        return lambdaQuery().eq(AdminUser::getUsername, username).one();
    }

    @Override
    public void saveAdminUser(AdminUser adminUser) {
        save(adminUser);
    }

    @Override
    public void updateAdminUser(AdminUser adminUser) {
        updateById(adminUser);
    }

    @Override
    public boolean verifyMsgCode(String mobile, String code) {
        return true;
    }

    @Override
    public boolean verifyImgCode(String key, String code) {
        return true;
    }

    @Override
    public String getMsgCode(String username, String key, String imgCode) {
        boolean check = verifyImgCode(key, imgCode);
        if(!check){
            throw new AdminUserException(MSD_CODE_ERROR.getCode(), MSD_CODE_ERROR.getValue());
        }

        return RandomStringUtils.randomNumeric(6);
    }

    @Override
    public void registerAdminUser(String username, String password, String msgCode) {

        if(!verifyMsgCode(username, msgCode)){
            throw new AdminUserException(MSD_CODE_ERROR.getCode(), MSD_CODE_ERROR.getValue());
        }
        AdminUser old = queryByUserName(username);
        if(null != old){
            throw new AdminUserException(ADMIN_USER_EXISTS.getCode(), ADMIN_USER_EXISTS.getValue());
        }

        AdminUser adminUser = new AdminUser(LocalDateTime.now(), username, null, null, 1, 1, 1);
        saveAdminUser(adminUser);
    }

    @Override
    public Map<String, Object> login(String username, String password, String key, String msgCode) {
        AdminUser old = queryByUserName(username);
        if(null == old){
            throw new AdminUserException(ADMIN_USER_NOT_EXISTS.getCode(), ADMIN_USER_NOT_EXISTS.getValue());
        }

        if(StringUtils.isNotBlank(password)){
        }else if(StringUtils.isNotBlank(msgCode)){
            if(!verifyMsgCode(username, msgCode)){
                throw new AdminUserException(MSD_CODE_ERROR.getCode(), MSD_CODE_ERROR.getValue());
            }
        }

        Map<String, Object> map = new HashMap<>();
        String token = JwtUtil.createToken(username, old.getUserId());
        String refreshToken = JwtUtil.createRefreshToekn(username);
        map.put("token", token);
        map.put("refreshToken", refreshToken);

        return map;
    }

    @Override
    public void resetPassword(String username, String password, String key, String msgCode) {
        AdminUser old = queryByUserName(username);
        if(null == old){
            throw new AdminUserException(ADMIN_USER_NOT_EXISTS.getCode(), ADMIN_USER_NOT_EXISTS.getValue());
        }

        if(!verifyMsgCode(username, msgCode)){
            throw new AdminUserException(MSD_CODE_ERROR.getCode(), MSD_CODE_ERROR.getValue());
        }

        updateAdminUser(old);
    }

    @Override
    public void createAdminUser(AdminUserSaveVo vo) {

        String json = JSON.toJSONString(vo);
        AdminUser adminUser = JSON.parseObject(json, AdminUser.class);
        Long[] positionIds = vo.getPositionIds();
        Long shopId = vo.getShopId();
        Long roleId = vo.getRoleId();
        Long parentId = Long.parseLong(JwtUtil.getParam("userId"));
        AdminUser old = queryByUserId(parentId);

        adminUser.setIsTrial(old.getIsTrial());
        adminUser.setCreateTime(LocalDateTime.now());

        saveAdminUser(adminUser);
        Long userId = adminUser.getUserId();
        adminUserRoleService.saveAdminUserRole(userId, roleId);
        userPositionService.saveUserPosition(userId, shopId, positionIds);
    }

}
