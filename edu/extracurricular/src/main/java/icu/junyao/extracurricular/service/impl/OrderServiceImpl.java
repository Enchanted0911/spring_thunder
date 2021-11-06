package icu.junyao.extracurricular.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import icu.junyao.common.entity.R;
import icu.junyao.extracurricular.constant.CommonConstants;
import icu.junyao.extracurricular.entity.Order;
import icu.junyao.extracurricular.mapper.OrderMapper;
import icu.junyao.extracurricular.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public Boolean ifBought(String userId, String courseId) {
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = Wrappers.lambdaQuery();
        orderLambdaQueryWrapper.eq(Order::getCourseId, courseId)
                .eq(Order::getMemberId, userId)
                .eq(Order::getStatus, CommonConstants.BOUGHT_STATUS);

        return super.count(orderLambdaQueryWrapper) > 0;
    }
}
