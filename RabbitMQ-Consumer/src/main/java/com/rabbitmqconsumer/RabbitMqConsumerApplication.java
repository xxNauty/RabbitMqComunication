package com.rabbitmqconsumer;

import com.rabbitmqconsumer.thread.CatConsumer;
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
        Thread catConsumer = new Thread(new CatConsumer());

        userConsumer.start();
        dogConsumer.start();
        catConsumer.start();

        System.out.println(userConsumer.getId());
        System.out.println(dogConsumer.getId());
    }

}
