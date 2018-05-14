package com.coderme.mq.queue;

import javax.annotation.Resource;
import javax.jms.*;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;
       
      /**
       * 向指定队列发送消息
       */
      public void sendMessage(Destination destination, final String msg) {
        System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);
        jmsTemplate.send(destination, new MessageCreator() {
          public Message createMessage(Session session) throws JMSException {
            return session.createTextMessage(msg);
          }
        });
      }
     
    /**
     * 向默认队列发送消息
     */
      public void sendMessage(final String msg) {
//        jmsTemplate.setDefaultDestination(new ActiveMQQueue("ActiveMQQueue"));
        String destination =  jmsTemplate.getDefaultDestination().toString();
        System.out.println("向队列" +destination+ "发送了消息------------" + msg);
        jmsTemplate.send(new MessageCreator() {
          public Message createMessage(Session session) throws JMSException {
            TextMessage textMessage = session.createTextMessage(msg);
//            textMessage.setJMSReplyTo(responseDestination);
            return textMessage;
          }
        });
     
      }
    
}