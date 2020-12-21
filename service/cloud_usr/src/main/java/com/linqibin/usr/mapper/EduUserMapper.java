package com.linqibin.usr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linqibin.usr.entity.EduUser;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-29
 */
public interface EduUserMapper extends BaseMapper<EduUser> {

    Integer getRegisterCount(String date);
}
