### spring 自定义标签使用
1. 创建一个需要扩展的组件，如 `com.coderme.example1.customtag.MyBean`
2. 定义 XSD 文件描述组件内容
3. 创建解析类，实现 `AbstractSingleBeanDefinitionParser` 接口，该类的作用是解析自定义的 XSD 文件
    
    
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
4. 创建注册类，继承自 `NamespaceHandlerSupport` ，实现很简单，遇到 `<mybean:mybean` 开头的标签，让 `MyBeanDefinitionParser` 类去解析


    public class MyBeanHandler extends NamespaceHandlerSupport {
    
        //初始化
        // 表示解析配置文件遇到<mybean 开头的定义让 MyBeanDefinitionParser 去解析
        public void init() {
            registerBeanDefinitionParser("mybean", new MyBeanDefinitionParser());
        }
    }
5. 编写 `spring.handlers` 和 `spring.schemas` 文件，这两个文件需要在 `/META-INF/` 文件夹下（默认spring是从这个文件夹下寻找这个两个文件）

到此，自定义标签的配置就完成了，加载自定义标签的大致流程为：

    遇到自定义标签 -> 到 `spring.handlers` 和 `spring.schemas` 文件寻找对应的 handler 和 XSD ，默认位置为 `/META-INF/` 文件夹下，进而找到对应想 handler 类和解析 XSD 的类，从而完成自定义标签的解析。

下面我们将测试刚才所写的自定义标签。

创建配置文件：

    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:myns="http://www.coderme.cn/schema/mybean"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
            http://www.coderme.cn/schema/mybean http://www.coderme.cn/schema/mybean.xsd">
    
        <myns:mybean id="testBean" userid="123" username="zhangsan" usercode="001"/>
    </beans>
   
创建测试类：

    public class CustomTagTest {
    
        @Test
        public void test() {
            ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
            MyBean myBean = (MyBean) ac.getBean("testBean");
            System.out.println(ReflectionToStringBuilder.toString(myBean, ToStringStyle.MULTI_LINE_STYLE));
        }
    }
    
运行后，将会打印出意料中的结果：
    
    信息: Loading XML bean definitions from class path resource [spring.xml]
    com.coderme.example1.customtag.MyBean@8ec38f8[
      userid=123
      username=zhangsan
      usercode=001
    ]
    
    Process finished with exit code 0