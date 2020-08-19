package com.atguigu.test;

import com.atguigu.springboot_rabbitmq.SpringbootRabbitmqApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 14:57
 */
@SpringBootTest(classes = SpringbootRabbitmqApplication.class)
public class TestRabbitMQ {
    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //helloworld的模型
    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","hello world");
    }
    //work模型
    @Test
    public void testWork(){
        for (int i = 0; i <10 ; i++) {
            rabbitTemplate.convertAndSend("work","work模型"+i);
        }
    }
    //fanout广播形式
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs","","Fanout模型发送的消息");
    }
    //route 路由模式
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("directs","error","发送info的key的路由信息");
    }
    //Topics 订阅模式
    @Test
    public void testTopics(){
        rabbitTemplate.convertAndSend("topics","user.save","user.save路由消息");
    }
}
