package com.coderme.example1.main;

import com.coderme.example1.customtag.MyBean;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ReflectionUtils;

/**
 * Created By Administrator
 * Date:2017/4/14
 * Time:14:18
 */
public class CustomTagTest {

    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        MyBean myBean = (MyBean) ac.getBean("testBean");
        System.out.println(ReflectionToStringBuilder.toString(myBean, ToStringStyle.MULTI_LINE_STYLE));
    }
}
