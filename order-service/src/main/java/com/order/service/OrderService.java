package com.order.service;

import com.codingapi.txlcn.tc.annotation.TxTransaction;
import com.order.entity.Order;
import com.order.repository.OrderRepository;
import com.order.vo.AccountVo;
import com.order.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/save")
    @Transactional
    @TxTransaction
    public String saveOrder() throws Exception {
        Integer price = 2300;
        Long userId = 1L;
        Order order = new Order();
        order.setGoodsName("iphone 11");
        order.setPrice(price);
        order.setUsername("zhangsan");
        order.setUserId(userId);
        orderRepository.save(order);

        //追加积分
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //String url = "http://localhost:8087/users/updateIntegral?";
        String url = "http://USER-SERVICE/users/updateIntegral?";

        UserVo userVo = new UserVo();
        userVo.setId(userId);
        userVo.setIntegral(price);
        HttpEntity<UserVo> entity = new HttpEntity<UserVo>(userVo, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        System.out.println("request users result : " + responseEntity.getBody().toString());

        //减少账号金额
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        //String url2 = "http://localhost:8088/accounts/lessenAmount";
        String url2 = "http://ACCOUNT-SERVICE/accounts/lessenAmount";

        AccountVo accountVo = new AccountVo();
        accountVo.setUserId(userId);
        accountVo.setAmount(price);
        HttpEntity<AccountVo> entity2 = new HttpEntity<AccountVo>(accountVo, headers2);
        ResponseEntity<String> responseEntity2 = restTemplate.postForEntity(url2, entity2, String.class);
        System.out.println("request accounts result : " + responseEntity2.getBody().toString());

        return "success";
    }


}
