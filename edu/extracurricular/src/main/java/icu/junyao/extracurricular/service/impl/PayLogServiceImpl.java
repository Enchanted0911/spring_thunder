package icu.junyao.extracurricular.service.impl;

import icu.junyao.extracurricular.entity.PayLog;
import icu.junyao.extracurricular.mapper.PayLogMapper;
import icu.junyao.extracurricular.service.PayLogService;
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
