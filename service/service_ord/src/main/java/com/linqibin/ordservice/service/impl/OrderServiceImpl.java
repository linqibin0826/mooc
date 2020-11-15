package com.linqibin.ordservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.commonutils.Result;
import com.linqibin.commonutils.ResultCode;
import com.linqibin.ordservice.client.CourseClient;
import com.linqibin.ordservice.client.UserClient;
import com.linqibin.ordservice.entity.OrderTable;
import com.linqibin.ordservice.mapper.OrderTableMapper;
import com.linqibin.ordservice.service.OrderTableService;
import com.linqibin.ordservice.utils.OrderNoUtil;
import com.linqibin.servicebase.exceptionHandler.MyException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Hugh&you
 * @since 2020-11-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderTableMapper, OrderTable> implements OrderTableService {

    private CourseClient courseClient;

    @Autowired
    public void setCourseClient(CourseClient courseClient) {
        this.courseClient = courseClient;
    }

    private UserClient userClient;

    @Autowired
    public void setUserClient(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public String createOrder(String courseId, String userId) {
        Result courseResult = courseClient.getFrontCourseInfoById(courseId);
        HashMap<String, Object> courseInfo = (HashMap<String, Object>)courseResult.getData().get("data");
        HashMap<String, Object> course = (HashMap<String, Object>)courseInfo.get("courseInfo");
        OrderTable order = new OrderTable();
        order.setCourseTitle(String.valueOf(course.get("title")));
        order.setCourseCover(String.valueOf(course.get("cover")));
        order.setCourseId(courseId);
        order.setTeacherName(String.valueOf(course.get("teacherName")));
        order.setTotalFee(BigDecimal.valueOf(Double.valueOf(String.valueOf(course.get("price")))));
        Result userResult = userClient.getUserById(userId);
        HashMap<String, Object> userInfo = (HashMap<String, Object>)userResult.getData().get("user");


        order.setEmail(String.valueOf(userInfo.get("email")));
        order.setNickname(String.valueOf(userInfo.get("nickName")));
        order.setUserId(String.valueOf(userInfo.get("id")));
        order.setPayType(1);
        order.setStatus(0);
        order.setOrderNo(OrderNoUtil.getOrderNo());

        int insert = baseMapper.insert(order);
        if (insert > 0) {
            return order.getOrderNo();
        } else {
            throw new MyException(ResultCode.ERROR, "订单生成失败");
        }
    }

    @Override
    public OrderTable getByOrderNo(String orderNo) {
        QueryWrapper<OrderTable> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        OrderTable orderInfo = baseMapper.selectOne(wrapper);
        return orderInfo;
    }
}
