package com.rabbitmqsecondproducer.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dog {
    private int id;
    private String name;
    private int age;
    private String breed;

    public Dog(int id, String name, int age, String breed) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                '}';
    }
}
