package com.rabbitmqconsumer;

import com.rabbitmqconsumer.thread.DogConsumer;
import com.rabbitmqconsumer.thread.UserConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqConsumerApplication {

    public static void main(String[] args){
        SpringApplication.run(RabbitMqConsumerApplication.class, args);

        Thread userConsumer = new Thread(new UserConsumer());
        Thread dogConsumer = new Thread(new DogConsumer());

        userConsumer.start();
        dogConsumer.start();
    }

}
