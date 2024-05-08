package com.rabbitmqproducer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmqproducer.entity.Dog;
import com.rabbitmqproducer.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@SpringBootApplication
@RestController
public class RabbitMqProducerApplication {

    private final static String DOG_QUEUE = "dog_queue";
    private final static String USER_QUEUE = "user_queue";

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProducerApplication.class, args);
    }

    @GetMapping("/send/data")
    public void sendSomeData() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        int random = new Random().nextInt(10);
        String message, queueName;

        if(random % 3 == 0) {
            User user = new User(1, "Jan", 21);
            message = new Gson().toJson(user);
            queueName = USER_QUEUE;
        }
        else{
            Dog dog = new Dog(1, "Burek", 4, "Owczarek");
            message = new Gson().toJson(dog);
            queueName = DOG_QUEUE;
        }

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent: '" + message + "'");
        }
    }

}
