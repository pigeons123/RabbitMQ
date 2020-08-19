package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 14:19
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机与交换机类型
        channel.exchangeDeclare("topics","topic");
        //发布消息
        String routekey="user.save.findAll";
        channel.basicPublish("topics",routekey,null,("这是topic动态路由模型，routerkey:["+routekey+"]").getBytes());
        //关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
