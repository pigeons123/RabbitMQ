package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Rain
 * @version 1.0
 * @date 2020/8/19 0:45
 */
public class Provider {
    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
       /* //创建连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq的主机名
        connectionFactory.setHost("192.168.0.20");
        //设置rabbitmq的端口号
        connectionFactory.setPort(5672);
        //设置rabbtmq的虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名与密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        //获取连接对象
        Connection connection = connectionFactory.newConnection();*/
        Connection connection = RabbitMQUtils.getConnection();
        //获取连接中的通道
        Channel channel = connection.createChannel();
        //通道绑定对应的消息队列
        //参数1.队列名称 如果队列不存在则会自动进行创建
        //参数2 定义队列是否要持久化 true持久化队列 false 不持久化队列
        //参数3 是否独占队列 true独占队列 false不独占队列
        //参数4 是否在消费完成后自动删除队列 true自动删除 false 不自动删除
        //参数5 额外参数
        //将第二个参数设置为true,则rabbitmq在重启的时候队列是不会丢失的
        channel.queueDeclare("hello", false, false, false, null);
        //发布消息
        //参数1 交换机名称
        //参数2 队列名称
        //参数3 传递消息的额外设置
        //参数4 消息的具体内容
        //MessageProperties.PERSISTENT_TEXT_PLAIN,如果队列中有消息的话，在消息没有被消费的时候，重启rabbitmq,消息仍旧是可以恢复的
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
