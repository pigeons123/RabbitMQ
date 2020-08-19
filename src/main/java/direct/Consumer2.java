package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 12:26
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName="logs_direct";
        //声明交换机与交换机的类型
        channel.exchangeDeclare(exchangeName,"direct");
        //创建临时的队列
        String queue = channel.queueDeclare().getQueue();
        //临时队列与交互机的绑定
        channel.queueBind(queue,exchangeName,"info");
        channel.queueBind(queue,exchangeName,"error");
        channel.queueBind(queue,exchangeName,"warning");
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2："+new String(body));
            }
        });
    }
}
