package com.account.service;

import com.account.entity.Account;
import com.account.repositroy.AccountRepositroy;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Service
public class AccountService {

    @Autowired
    private AccountRepositroy accountRepositroy;

    @RequestMapping(value = "/lessenAmount", method = RequestMethod.POST)
    @Transactional
    @TxTransaction
    public String lessenAmount(@RequestBody Account account) {
        List<Account> accounts = accountRepositroy.findByUserId(account.getUserId());
        if (accounts.size() > 0 && accounts.get(0).getAmount() > account.getAmount()) {
            Account accountUpdate = accounts.get(0);
            accountUpdate.setAmount(accountUpdate.getAmount() - account.getAmount());
            accountRepositroy.save(accountUpdate);
            return "success";
        } else {
            throw new RuntimeException("账号余额不足");
        }

    }

}
