package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 9:37
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
        //每一次只能消费一个消息
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);
        //参数1：队列名称
        //参数2：消息自动确认 true 消费者自动向rabbitmq确认消息消费
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者2："+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
