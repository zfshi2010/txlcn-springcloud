package com.account.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    private Long userId;

}
