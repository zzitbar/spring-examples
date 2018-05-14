package com.coderme.mq.queue;

import org.springframework.stereotype.Component;

/**
 * Created By Administrator
 * Date:2018/5/14
 * Time:15:39
 */
@Component("QueueMsgMessageListenerAdapter")
public class QueueMsgMessageListenerAdapter {

    public String receiveMessage(String message) {
        System.out.println("QueueMsgMessageListenerAdapter监听到了文本消息：\t"
                + message);
        return "response 消息";
    }
}