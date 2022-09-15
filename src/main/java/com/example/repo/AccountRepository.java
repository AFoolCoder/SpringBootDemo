package com.example.repo;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByUsername(String username);
}
