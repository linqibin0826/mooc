package com.linqibin.ordservice.controller;


import com.linqibin.commonutils.JwtUtils;
import com.linqibin.commonutils.Result;
import com.linqibin.ordservice.entity.OrderTable;
import com.linqibin.ordservice.service.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Hugh&you
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/ordservice/order")
public class OrderController {

    private OrderTableService orderService;

    @Autowired
    public void setOrderService(OrderTableService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId,
                              HttpServletRequest request) {
        String userId = JwtUtils.getIdByJwtToken(request);
        String orderNo = orderService.createOrder(courseId, userId);
        return Result.ok().data("orderNo", orderNo);
    }

    @GetMapping("/getOrderInfoByOrderNo/{orderNo}")
    public Result getOrderInfoByOrderNo(@PathVariable String orderNo) {
        OrderTable orderInfo = orderService.getByOrderNo(orderNo);
        return Result.ok().data("item", orderInfo);
    }
}

