package com.example.controller;

import com.example.entity.resp.RestBean;
import com.example.service.AccountService;
import com.example.service.VerifyService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@Api(tags = "账户验证接口", description = "包括用户登录、注册、验证码请求等操作。")
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private VerifyService verifyService;

    @Autowired
    private AccountService accountService;


    @ApiResponses({
            @ApiResponse(code = 200, message = "邮件发送成功"),
            @ApiResponse(code = 500, message = "邮件发送失败")   //不同返回状态码描述
    })
    @ApiOperation("请求邮件验证码")
    @RequestMapping("/verify-code")
    public RestBean<Void> verifyCode(@ApiParam("邮箱地址") @RequestParam("email") String email){
        try {
            verifyService.sendVerifyCode(email);
            return new RestBean<>(200, "邮箱发送成功!");
        } catch (Exception e) {
            return new RestBean<>(500, "邮箱发送失败!");
        }
    }

    @PostMapping(value = "/register")
    public RestBean<Void> register(String username, String password, String email, String verify) {
        if (verifyService.doVerify(email, verify)) {
            accountService.createAccount(username, password);
            return new RestBean<>(200, "注册成功!");
        } else {
            return new RestBean<>(403, "注册失败,验证码填写错误！");
        }
    }

    @ApiIgnore
    @PostMapping("/login-success")
    public RestBean<Void> loginSuccess() {
        return new RestBean<>(200, "登录成功");
    }

    @ApiIgnore
    @GetMapping("/logout-success")
    public RestBean<Void> logoutSuccess() {
        return new RestBean<>(200, "退出成功");
    }

    @PostMapping("/login-failure")
    public RestBean<Void> loginFailure() {
        return new RestBean<>(403, "登录失败,用户名或密码错误!");
    }

    @GetMapping("/access-deny")
    public RestBean<Void> accessDeny() {
        return new RestBean<>(401, "未验证,请先进行登录");
    }
}
