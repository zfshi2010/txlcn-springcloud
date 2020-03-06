package com.order.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="order_info")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String goodsName;

    @Column(nullable = false)
    private Integer price;



}
