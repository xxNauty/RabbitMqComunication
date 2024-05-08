package com.rabbitmqconsumer.thread;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmqconsumer.entity.User;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

public class UserConsumer implements Runnable {

    private final static String QUEUE_NAME = "user_queue";

    @SneakyThrows //w pewnym stopniu zastępuje try...catch
    @Override
    public void run() {
        System.out.println("user consumer started");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.print(" [x] Received message: ");
            User user = new Gson().fromJson(message, User.class);
            System.out.println(user.toString());
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }


}
