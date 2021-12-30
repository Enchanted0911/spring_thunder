package icu.junyao.acl.service;

import icu.junyao.acl.entity.AclUser;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.acl.req.*;
import icu.junyao.acl.res.AclUserDetailRes;
import icu.junyao.common.entity.PageResult;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-04
 */
public interface AclUserService extends IService<AclUser> {

    /**
     * 新增管理用户
     *
     * @param aclUserReq {@link AclUserReq}
     */
    void saveAclUser(AclUserReq aclUserReq);

    /**
     * 分页获取管理用户, 可根据用户名模糊搜索
     *
     * @param pageUserReq  {@link PageUserReq}
     * @return 当前页的内容和总条数
     */
    PageResult<AclUser> pageUser(PageUserReq pageUserReq);

    /**
     * 修改用户信息
     *
     * @param aclUserEditReq {@link AclUserEditReq}
     */
    void updateUser(AclUserEditReq aclUserEditReq);

    /**
     * 删除单个用户, 注意解除用户和角色的绑定
     *
     * @param id 用户id
     */
    void removeUser(String id);

    /**
     * 删除多个用户, 操作同删除单个用户
     *
     * @param idList 待删除用户id集合
     */
    void removeBatchUser(List<String> idList);

    /**
     * 获取用户详情
     *
     * @param id 用户id
     * @return {@link AclUserDetailRes}
     */
    AclUserDetailRes userDetails(String id);

    /**
     * 修改个人信息
     *
     * @param aclUserEditReq {@link AclUserEditReq}
     */
    void updateUserSelf(AclUserEditReq aclUserEditReq);

    /**
     * 修改密码
     *
     * @param passwordReq {@link PasswordReq}
     */
    void updatePassword(PasswordReq passwordReq);

    /**
     * 获取当前用户的信息
     *
     * @return {@link AclUserDetailRes}
     */
    AclUserDetailRes currentUserInfo();
}
