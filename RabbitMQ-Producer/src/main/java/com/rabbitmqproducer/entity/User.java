package com.rabbitmqproducer.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
