package com.wjc.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjc.gateway.entity.AdminUser;
import com.wjc.gateway.vo.AdminUserSaveVo;

import java.util.Map;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author wjc
 * @since 2020-09-10
 */
public interface IAdminUserService extends IService<AdminUser> {

    AdminUser queryByUserId(Long userId);

    AdminUser queryUserByToken();

    AdminUser queryByUserName(String username);

    void saveAdminUser(AdminUser adminUser);

    void updateAdminUser(AdminUser adminUser);

    boolean verifyMsgCode(String mobile, String code);

    boolean verifyImgCode(String key, String code);

    String getMsgCode(String username, String key, String imgCode);

    void registerAdminUser(String username, String password, String msgCode);

    Map<String, Object> login(String username, String password, String key, String msgCode);

    void resetPassword(String username, String password, String key, String msgCode);

    void createAdminUser(AdminUserSaveVo vo);
}
