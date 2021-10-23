package icu.junyao.back.service.impl;

import icu.junyao.back.entity.PayLog;
import icu.junyao.back.mapper.PayLogMapper;
import icu.junyao.back.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
