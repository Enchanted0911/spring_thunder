package icu.junyao.extracurricular.service.impl;

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

}
