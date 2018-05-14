package com.coderme.mq.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener{

    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("---------消息消费---------");

            System.out.println("消息内容:\t" + tm.getText());
            System.out.println("消息ID:\t" + tm.getJMSMessageID());
            System.out.println("消息Destination:\t" + tm.getJMSDestination());
            System.out.println("---------更多信息---------");
            System.out.println("-------------------------");
            //手动确认
//            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}