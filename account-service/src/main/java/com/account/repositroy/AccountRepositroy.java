package com.account.repositroy;

import com.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositroy extends JpaRepository<Account, Integer> {

    List<Account> findByUserId(Long userId);

}
