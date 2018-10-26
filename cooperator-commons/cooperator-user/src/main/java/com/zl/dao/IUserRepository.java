package com.zl.dao;

import com.zl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/25
 * @description: ${description}
 **/
public interface IUserRepository extends JpaRepository<User, Long> {

}
