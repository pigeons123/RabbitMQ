package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 9:30
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道对象

        Channel channel = connection.createChannel();
        //通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);
        //生产消息
        for (int i = 0; i <20 ; i++) {
            channel.basicPublish("","work",null,(i+"hello work queue").getBytes());
        }
        //关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
