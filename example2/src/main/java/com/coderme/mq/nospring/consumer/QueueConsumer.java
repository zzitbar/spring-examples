package com.coderme.mq.nospring.consumer;

import com.coderme.mq.nospring.ConnectionFactory;
import com.coderme.mq.nospring.producer.QueueProducer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Created By Administrator
 * Date:2018/5/14
 * Time:14:15
 */
public class QueueConsumer {

    public void consume(String disname) {
        Connection connection = null;
        try {
            //创建一个链接工厂
            ActiveMQConnectionFactory connectionFactory = ConnectionFactory.create();
            //从工厂中创建一个链接
            connection = connectionFactory.createConnection();
            connection.start();
            ////创建一个会话（这里通过参数可以设置事务的级别），用于发送和接受消息，是单线程的，支持事务的
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个消息队列
            Queue queue = session.createQueue(disname);
            //创建消息生产者
            MessageConsumer consumer = session.createConsumer(queue);
            while(true){
                Thread.sleep(1000);
                Message message = consumer.receive();
                if(message!=null) {
                    if (message instanceof TextMessage) {
                        System.out.println(Thread.currentThread().getName()+": Consumer:我是消费者，我正在消费Msg"+((TextMessage) message).getText()+"--->");
                    }
//                    message.acknowledge();
                }else {
                    break;
                }
            }

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