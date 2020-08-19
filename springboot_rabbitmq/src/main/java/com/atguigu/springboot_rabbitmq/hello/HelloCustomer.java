package com.atguigu.springboot_rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 15:05
 */
@Component //持久化 非独占 自动删除
@RabbitListener(queuesToDeclare = @Queue(value = "hello",durable = "false",autoDelete = "true"))
public class HelloCustomer {
    @RabbitHandler
    public void receive(String message){
        System.out.println("message="+message);
    }
}
