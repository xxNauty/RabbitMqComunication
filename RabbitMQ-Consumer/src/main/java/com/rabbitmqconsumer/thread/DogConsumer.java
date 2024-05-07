package com.rabbitmqconsumer.thread;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmqconsumer.entity.Dog;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

public class DogConsumer implements Runnable {

    private final static String QUEUE_NAME = "dog_queue";

    @SneakyThrows
    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.print(" [x] Received message: ");
            Dog dog = new Gson().fromJson(message, Dog.class);
            System.out.println(dog.toString());
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
