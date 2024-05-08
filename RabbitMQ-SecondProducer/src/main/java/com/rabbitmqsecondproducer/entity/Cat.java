package com.rabbitmqsecondproducer.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cat {
    private int id;
    private String name;
    private int age;
    private String breed;
    private int lives;

    public Cat(int id, String name, int age, String breed, int lives) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", lives=" + lives +
                '}';
    }
}
