package com.linqibin.ordservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linqibin.ordservice.entity.OrderTable;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-11-10
 */
public interface OrderTableService extends IService<OrderTable> {

    String createOrder(String courseId, String userId);

    OrderTable getByOrderNo(String orderNo);
}
