package com.atguigu.springboot_rabbitmq.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 16:02
 */
@Component
public class RouteConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建队列
                    exchange = @Exchange(value = "directs",type = "direct"),//指定交换机的名称与路由
                    key={"info","error","warn"}
            )
    })
    public void receive1(String message){
        System.out.println("message1:"+message);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建队列
                    exchange = @Exchange(value = "directs",type = "direct"),//指定交换机的名称与路由
                    key={"error"}
            )
    })
    public void receive2(String message){
        System.out.println("message2:"+message);
    }
}
