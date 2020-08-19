package com.atguigu.springboot_rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 16:52
 */
@Component
public class TopicConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(type = "topic",name = "topics"),
                    key = {"user.save","user.*"}
            )
    })
    public void receive1(String message){
        System.out.println("messgae1="+message);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(type = "topic",name = "topics"),
                    key = {"order.#","produce.#","user.*"}
            )
    })
    public void receive2(String message){
        System.out.println("messgae2="+message);
    }
}
