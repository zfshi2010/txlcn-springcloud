package com.user.service;

import com.codingapi.txlcn.tc.annotation.TxTransaction;
import com.user.entity.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/updateIntegral",method = RequestMethod.POST)
    @Transactional
    @TxTransaction
    public String updateIntegral(@RequestBody User user) throws Exception {
        User userUpdate = userRepository.getOne(user.getId());
        userUpdate.setIntegral(userUpdate.getIntegral() + user.getIntegral());
        userRepository.save(userUpdate);
        return "success";
    }

}
