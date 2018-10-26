//package com.zl.controller.user.controller;
//
//import com.zl.dto.ResponseEntity;
//import com.zl.service.api.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
///**
// * @author zhuolin
// * @program: cooperator
// * @date 2018/10/25
// * @description: ${description}
// **/
//@RestController
//@RequestMapping(value = "/user")
//public class UserController {
//
//    @Autowired
//    IUserService userService;
//
//    @RequestMapping(value = "/getUser")
//    public ResponseEntity getUser(String code) {
//        try {
//            return ResponseEntity.newSuccess(userService.getUserInfo(code));
//        } catch (Exception e) {
//            return ResponseEntity.newFaild(e.getMessage());
//        }
//    }
//}
