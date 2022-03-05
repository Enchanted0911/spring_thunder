package icu.junyao.classroom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import icu.junyao.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * acl_user_teacher表
 * </p>
 *
 * @author johnson
 * @since 2022-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_acl_user_teacher")
@ApiModel(value="acl_user_teacher对象", description="acl_user_teacher表")
public class AclUserTeacher extends BaseEntity {
    private String aclUserId;
    private String teacherId;
}
