package com.coderme.example1.parser;

import com.coderme.example1.customtag.MyBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created By coderme
 * Date:2017/4/14
 * Time:14:00
 */
public class MyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    //定义element对应的类
    @Override
    protected Class<?> getBeanClass(Element element) {
        return MyBean.class;
    }

    //从 element 中解析并提取对应的元素
    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String userid = element.getAttribute("userid");
        String username = element.getAttribute("username");
        String usercode = element.getAttribute("usercode");

        //将提取到的数据放到 BeanDefinitionBuilder 中
        if (StringUtils.hasText(userid)) {
            builder.addPropertyValue("userid", userid);
        }
        if (StringUtils.hasText(username)) {
            builder.addPropertyValue("username", username);
        }
        if (StringUtils.hasText(usercode)) {
            builder.addPropertyValue("usercode", usercode);
        }
    }
}
