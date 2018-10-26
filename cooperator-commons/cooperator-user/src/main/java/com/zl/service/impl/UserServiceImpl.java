package com.zl.service.impl;

import com.zl.dao.IUserRepository;
import com.zl.dto.UserDTO;
import com.zl.model.User;
import com.zl.service.api.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/25
 * @description: ${description}
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public Boolean saveUser(UserDTO userDTO) {

        return null;
    }

    @Override
    public UserDTO getUserInfo(String code) throws Exception {
        if (StringUtils.isEmpty(code)) {
            throw new Exception("用户编码不能为空");
        }
        User user = new User();
        user.setCode(code);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("deleted", "updateTime", "createTime", "deleted");
        Example example = Example.of(user, exampleMatcher);
        Optional optional = userRepository.findOne(example);
        if (!optional.isPresent()) {
            throw new Exception("获取用户信息失败");
        }
        UserDTO result = new UserDTO();
        BeanUtils.copyProperties(user, result);
        return result;
    }
}
