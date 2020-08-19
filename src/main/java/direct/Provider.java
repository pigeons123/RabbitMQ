package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 11:43
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取连接通道
        Channel channel = connection.createChannel();
        //通过通道声明交换机 参数1：交换机名称，参数2：路由模式
        String exchangeName="logs_direct";
        channel.exchangeDeclare(exchangeName,"direct");
        //发送消息
        String routeingkey="warning";
        channel.basicPublish(exchangeName,routeingkey,null,("这是direct发布的基于route key:["+routeingkey+"]").getBytes());
        //关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
