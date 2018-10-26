package com.zl.service.api;

import com.zl.dto.UserDTO;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/25
 * @description: ${description}
 **/
public interface IUserService {

    /**
     * 保存用户信息
     *
     * @param userDTO @link 用户信息
     * @return 是否保存成功
     */
    public Boolean saveUser(UserDTO userDTO);

    /**
     * 根据用户编码获取用户信息
     *
     * @param code 用户编码
     * @return
     */
    public UserDTO getUserInfo(String code) throws Exception;
}
