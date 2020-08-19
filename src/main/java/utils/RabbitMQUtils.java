package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 1:30
 */
public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq的主机名
        connectionFactory.setHost("192.168.0.20");
        //设置rabbitmq的端口号
        connectionFactory.setPort(5672);
        //设置rabbtmq的虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名与密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }
    //提供连接对象的方法
    public static Connection getConnection() {
        //创建连接工厂对象
        try {
            //获取连接对象
            Connection connection = connectionFactory.newConnection();
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
    //关闭通道和关闭连接的方法
    public static void closeConnectionAndChannel(Channel channel,Connection connection){
        if (channel!=null){
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
