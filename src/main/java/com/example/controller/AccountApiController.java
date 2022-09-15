package com.example.controller;


import com.example.entity.Account;
import com.example.entity.resp.RestBean;
import com.example.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/user")
public class AccountApiController {

    @Autowired
    private AccountRepository repository;

    @RequestMapping("/info")
    public RestBean<Account> info(HttpSession session) {
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Account account = repository.findAccountByUsername(context.getAuthentication().getName());
        account.setPassword("");
        return new RestBean<>(200, "请求成功", account);
    }
}
