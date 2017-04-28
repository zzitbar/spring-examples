package com.coderme.mq.queue;

import com.coderme.mq.queue.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * Created By Administrator
 * Date:2017/4/27
 * Time:17:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-root.xml"})
public class MQTest {

    @Resource(name = "demoQueueDestination")
    private Destination demoQueueDestination;
    @Resource
    private ProducerService producerService;

    @Test
    public void test() {
        producerService.sendMessage("aaaaa");
    }
}
