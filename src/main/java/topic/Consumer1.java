package topic;

import com.rabbitmq.client.*;
import com.rabbitmq.tools.json.JSONUtil;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 14:24
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机以及交换机类型
        channel.exchangeDeclare("topics","topic");
        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定队列与交换机，动态分配符的形式route key
        channel.queueBind(queue,"topics","user.*");
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}
