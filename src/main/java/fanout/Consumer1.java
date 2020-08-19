package fanout;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 11:12
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        //1.获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        //2.通道绑定交换机
        channel.exchangeDeclare("logs","fanout");
        //3.临时队列
        String queue = channel.queueDeclare().getQueue();
        //4.绑定交换机与队列
        channel.queueBind(queue,"logs","");
        //5.消费消息
        channel.basicConsume(queue,true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}
