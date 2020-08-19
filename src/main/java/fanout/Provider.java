package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 11:06
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //将通道声明指定的交换机
        //参数1代表交换机的名字，参数2代表交换机的类型，fanout代表广播类型
        channel.exchangeDeclare("logs","fanout");
        //发送消息
        channel.basicPublish("logs","",null,"fanout type message".getBytes());
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
