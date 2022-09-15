package com.example.service.impl;

import com.example.entity.Account;
import com.example.repo.AccountRepository;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void createAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(new BCryptPasswordEncoder().encode(password));
        accountRepository.save(account);
    }
}
