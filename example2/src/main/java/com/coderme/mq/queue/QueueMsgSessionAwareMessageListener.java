package com.coderme.mq.queue;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created By Administrator
 * Date:2018/5/14
 * Time:15:28
 */
@Component("queueMsgSessionAwareMessageListener")
public class QueueMsgSessionAwareMessageListener implements SessionAwareMessageListener {

    public void onMessage(Message message, Session session) throws JMSException {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMsgSessionAwareMessageListener监听到了文本消息：\t"
                    + tm.getText());
            //do something ...
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}