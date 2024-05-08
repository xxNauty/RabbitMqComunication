package com.rabbitmqconsumer.thread;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmqconsumer.entity.Cat;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

public class CatConsumer implements Runnable {

    private final static String QUEUE_NAME = "cat_queue";

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("cat consumer started");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.print(" [x] Received message: ");
            Cat cat = new Gson().fromJson(message, Cat.class);
            System.out.println(cat.toString());
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
