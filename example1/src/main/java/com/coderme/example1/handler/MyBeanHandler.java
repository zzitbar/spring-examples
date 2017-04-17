package com.coderme.example1.handler;

import com.coderme.example1.parser.MyBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created By Administrator
 * Date:2017/4/14
 * Time:14:09
 */
public class MyBeanHandler extends NamespaceHandlerSupport {

    //初始化
    // 表示解析配置文件遇到<mybean 开头的定义让 MyBeanDefinitionParser 去解析
    public void init() {
        registerBeanDefinitionParser("mybean", new MyBeanDefinitionParser());
    }
}
