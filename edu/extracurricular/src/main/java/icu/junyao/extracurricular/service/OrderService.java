package icu.junyao.extracurricular.service;

import icu.junyao.common.entity.R;
import icu.junyao.extracurricular.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
public interface OrderService extends IService<Order> {

    /**
     * 查询用户是否购买课程
     *
     * @param userId 用户id
     * @param courseId 课程id
     * @return true表示购买
     */
    Boolean ifBought(String userId, String courseId);
}
