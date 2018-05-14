package com.coderme.mq.nospring.producer;

import com.coderme.mq.nospring.ConnectionFactory;
import com.coderme.mq.nospring.consumer.QueueConsumer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Created By Administrator
 * Date:2018/5/14
 * Time:14:16
 */
public class QueueProducer {

    @Test
    public void testProducer() {
        new QueueProducer().producer("test-queue1");
    }

    @Test
    public void testConsume() {
        new QueueConsumer().consume("test-queue1");
    }

    public void producer(String disname) {
        Connection connection = null;
        try {
            //创建一个链接工厂
            ActiveMQConnectionFactory connectionFactory = ConnectionFactory.create();
            //从工厂中创建一个链接
            connection = connectionFactory.createConnection();
            connection.start();
            ////创建一个会话（这里通过参数可以设置事务的级别），用于发送和接受消息，是单线程的，支持事务的
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            //创建一个消息队列
            Queue queue = session.createQueue(disname);
            //创建消息生产者
            MessageProducer producer = session.createProducer(queue);
            //设置持久化/非持久化，如果为NON_PERSISTENT，则重启后消息会丢失，如果为PERSISTENT则会持久化到kahadb/leveldb/jdbc
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage message = session.createTextMessage("这是文本消息示例");
            //发送消息
            producer.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}