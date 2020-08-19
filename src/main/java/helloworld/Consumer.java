package helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 1:05
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
   /*     ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.0.20");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        //创建一个连接对象
        Connection connection = connectionFactory.newConnection();*/
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,false,null);

        //消费消息
        //参数1：消费队列的消息 队列名称
        //参数2：开启消息的自动确认机制
        //参数3：消费时的回调函数
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            //最后一个参数，从消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
     /*   channel.close();
        connection.close();*/
    }
}
