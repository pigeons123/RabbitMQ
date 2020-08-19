package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 12:18
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName="logs_direct";
        //通道声明交换机以及交换的类型
        channel.exchangeDeclare(exchangeName,"direct");
        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        //基于route key绑定队列和交换机
        channel.queueBind(queue,exchangeName,"error");
        //获取消费者的消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}
