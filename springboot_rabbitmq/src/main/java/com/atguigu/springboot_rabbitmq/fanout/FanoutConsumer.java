package com.atguigu.springboot_rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 15:55
 */
@Component
public class FanoutConsumer {
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,//创建临时队列
            exchange = @Exchange(value = "logs",type = "fanout")//绑定交换机
    )})
    public void receive(String message){
        System.out.println("message1:"+message);
    }
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,//创建临时队列
            exchange = @Exchange(value = "logs",type = "fanout")//绑定交换机
    )})
    public void receive2(String message){
        System.out.println("message2:"+message);
    }
}
