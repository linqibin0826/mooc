package com.linqibin.ordservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.ordservice.entity.PayLog;
import com.linqibin.ordservice.mapper.PayLogMapper;
import com.linqibin.ordservice.service.PayLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-11-10
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
