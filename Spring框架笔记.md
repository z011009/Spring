# 1.Spring笔记

## 1.1、简介

* Spring：春天   给软件行业带来了春天

* Rod Johnson，Spring Framework创始人，著名作者。很难想象Rod Johnson的学历，真的让好多人大吃一惊，他是[悉尼大学](https://baike.so.com/doc/5406852-5644740.html)的博士，然而他的专业不是计算机，而是音乐学。

* Spring理念：使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架

  ![image-20191228112411540](https://github.com/z011009/Spring/blob/master/images/image-20191228112411540.png)

* SSM：Spring+SpringMVC+MyBatis

* SSH：Struts2+Spring+Hibernate

官网：https://spring.io/projects/spring-framework#overview

官网下载地址：https://repo.spring.io/release/org/springframework/spring

GitHub：https://github.com/spring-projects/spring-framework

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>
```

## 1.2、优点

* ###### Spring是一个开源的免费的框架（容器）

* ###### Spring是一个轻量级的、非入侵式的框架

* ###### 控制反转IOC，面向切面编程AOP

* ###### 支持事务的处理，对框架整合的支持

#### 总结一句话：Spring就是一个轻量级的控制反转IOC和面向切面编程AOP的框架

## 1.3、组成

![image-20200112095212696](E:\Typora2\笔记图片存放处\image-20200112095212696.png)

## 1.4、扩展

在spring官网有这个介绍：现代化的java开发！说白了就是基于spring的开发

![image-20200112095428289](https://github.com/z011009/Spring/blob/master/images/image-20200112095428289.png)

* **Spring Boot**
  * 一个快速开发的脚手架
  * 基于SpringBoot可以快速的开发单个微服务
  * 约定大于配置

* **Spring Cloud**
  * Spring Cloud是基于Spring Boot实现的

**因为现在大多数公司都在使用SpringBoot进行快速开发，学习springboot的前提，需要完全掌握Spring及springmvc！承上启下的作用！**



**弊端：发展太久，违背原本理念！配置繁琐，人称“配置地狱”**

# 2.IOC

## 2.1、IOC理论指导

1.UserDao接口



2.UserDaoImpl实现类



3.UserService业务接口



4.UserServiceImpl业务实现



##### 在我们之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求去修改源代码！

##### 代码量很大，修改一次的代价就十分的昂贵！

我们使用一个Set接口来实现，已经发生了革命性的变化

```java
private UserDao userDao;

// 利用set进行动态实现值的注入
public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
}
```

* 之前，程序是主动创建对象！控制权在程序员手上！
* 使用了set之后，程序不再具有主动性，而是变成了被动的接受对象！

这种思想，从本质上解决了问题，我们程序员不用再去管理对象的创建了。系统的耦合性大大降低~，可以更加的专注业务的实现上！这就是IOC的原型！

![image-20200112221521480](https://github.com/z011009/Spring/blob/master/images/image-20200112221521480.png)

###### IOC本质分析探究：https://www.cnblogs.com/hellokuangshen/p/11249253.html

## 2.2、IOC本质

**控制反转IoC(Inversion of Control)，是一种设计思想，DI(依赖注入)是实现IoC的一种方法**，也有人认为DI只是IoC的另一种说法。没有IoC的程序中 , 我们使用面向对象编程 , 对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是：获得依赖对象的方式反转了。



采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection,DI）。**

# 3.HelloSpring

### 实体类

```java
@Data
public class Hello {
    private  String str;
}
```

### beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--使用spring来创建对象，在spring这些都称为Bean
    类型 变量名=new 类型();
    Hello hello=new Hello();
    id=变量名   class=new的对象
    property=给对象中的属性设置值
    bean=对象  new Hello();
    -->
    <bean id="hello" class="cn.spring.pojo.Hello">
        <property name="str" value="Spring"/>
    </bean>
</beans>
```

### 测试类

```java
public class MyTest {
    @Test
    public void Test1(){
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象现在都在spring中的管理了，我们要使用，直接去里面取出来即可
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }
}
```

## 3.1、思考问题

- Hello 对象是谁创建的 ?

  hello 对象是由Spring创建的 

- Hello 对象的属性是怎么设置的 ?

  hello 对象的属性是由Spring容器设置的 , 

这个过程就叫控制反转 : 

控制 : 谁来控制对象的创建 , 传统应用程序的对象是由程序本身控制创建的 , 使用Spring后 , 对象是由Spring来创建的 .

反转 : 程序本身不创建对象 , 而变成被动的接收对象 .

依赖注入 : 就是利用set方法来进行注入的.

IOC是一种编程思想 , 由主动的编程变成被动的接收 . 

可以通过newClassPathXmlApplicationContext去浏览一下底层源码 .

**OK , 到了现在 , 我们彻底不用再程序中去改动了 , 要实现不同的操作 , 只需要在xml配置文件中进行修改 , 所谓的IoC,一句话搞定 : 对象由Spring 来创建 , 管理 , 装配 !**

# 4.IOC创建对象的方式

1.使用无参构造创建对象，默认！

2.假设我们要使用有参构造创建对象

* 下标赋值

  ```xml
  <bean id="user" class="com.ioc.pojo.User">
      <constructor-arg index="0" value="kl说java"/>
  </bean>
  ```

* 类型

  ```xml
  <bean id="user" class="com.ioc.pojo.User">
      <constructor-arg type="java.lang.String" value="java" />
  </bean>
  ```

* 参数名

  ```xml
  <bean id="user" class="com.ioc.pojo.User">
      <constructor-arg name="name" value="java" />
  </bean>
  ```



#### 总结：在配置文件加载的时候，容器中管理的对象就已经初始化了!

# 5.Spring配置

## 5.1、别名

```xml
<!-- 别名，如果添加了别名，我们也可以使用别名获取到这个对象-->
<alias name="user" alias="user2"></alias>
```

## 5.2、Bean的配置

```xml
<!-- 
    id:bean的唯一标识符，也就是相当于我们学的对象名
    class：bean对象所对应的的全限定名
    name：也是别名 ,而且可以同时取多个别名  可以用 (，空格 ;) 来分隔
    -->
<bean id="userT" class="com.ioc.pojo.UserT" name="userT2,u2 u3;">
    <property name="name" value="java"></property>
</bean>
```

## 5.3、import

#### 这个import，一般用于团队开发使用，他可以将多个配置文件，导入合并

假设，现在的项目中有多个人开发，这三个人复制不同的类开发，不同的类需要注册在不同的bean中，我们可以

利用import将所有人的benas.xml合并为一个总的

* 张三
* 李四
* 王五
* applicationContext.xml

```xml
<import resource="beans2.xml"/>
<import resource="beans.xml"/>
```

使用的时候，直接使用总的配置就可以了

# 6.依赖注入

## 6.1、构造器注入

###### 4.IOC创建对象的方式

## 6.2、Set方式注入【重点】

* 依赖注入：Set注入！

  * 依赖：bean对象的创建依赖容器
  * 注入：bean对象中的所有属性，由容器来注入
* 
  
##### 【环境搭建】
  
1.复杂类型
  
```java
  @Data
  public class Address {
      private  String address;
  }
  ```
  
  2.真实测试对象
  
  ```java
  @Data
  public class Student {
      private String name;
      private Address address;
    private String[] books;
      private List<String> hobbies;
    private Map<String, String> card;
      private Set<String> games;
      private Properties info;
  }
  ```
  
  3.beans.xml
  
  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
      <bean id="student" class="com.ioc.pojo.Student">
        <!--1.普通值的注入-->
          <property name="name" value="张三"></property>
      </bean>
  </beans>
  ```
  
  4.测试类
  
  ```java
  public class MyTest {
      public static void main(String[] args) {
          ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
          Student student = (Student) context.getBean("student");
        System.out.println(student.getName());
      }
}
  ```

##### 完善注入信息:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="address" class="com.ioc.pojo.Address">
        <property name="address" value="杭州" />
    </bean>
    <bean id="student" class="com.ioc.pojo.Student">
        <!--1.普通值的注入-->
        <property name="name" value="张三" />
        <!--2.Bean注入，ref-->
        <property name="address" ref="address"/>
        <!--3.数组注入-->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>三国</value>
                <value>西游记</value>
                <value>水浒传</value>
            </array>
        </property>
        <!--4.List注入-->
        <property name="hobbies">
            <list>
                <value>听歌</value>
                <value>玩游戏</value>
                <value>敲代码</value>
            </list>
        </property>
        <!--5.map注入-->
        <property name="card">
            <map>
                <entry key="身份证" value="11111111"></entry>
                <entry key="银行卡" value="22222222"></entry>
            </map>
        </property>
        <!--6.set注入-->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>王者</value>
                <value>吃鸡</value>
            </set>
        </property>
        <!--7.null值注入-->
        <property name="wife">
            <null/>
        </property>
        <!--8.Properties注入-->
        <property name="info">
            <props>
                <prop key="学号">20190525</prop>
                <prop key="性别">男</prop>
                <prop key="姓名">小名</prop>
            </props>
        </property>
    </bean>
</beans>
```

## 6.3、拓展方式注入

#### 我们可以使用p命令空间和c命令空间进行注入

官网解释：

![image-20191229161222916](https://github.com/z011009/Spring/blob/master/images/image-20191229161222916.png)

#### 使用：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--p命名空间注入，可以直接注入属性的值：property-->
    <bean id="user" class="com.ioc.pojo.User" p:name="admin" p:age="18"/>
    <!--c命名空间注入，通过构造器注入属性的值：construct-args-->
    <bean id="user2" class="com.ioc.pojo.User" c:age="18" c:name="admin2"/>
</beans>
```

#### 测试：

```java
 @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userBean.xml");
        User user = (User) context.getBean("user2");
        System.out.println(user.toString());
    }
```

#### 注意点：p命名和c命名空间不能直接使用，需要导入xml约束

```xml
 xmlns:p="http://www.springframework.org/schema/p"
 xmlns:c="http://www.springframework.org/schema/c"
```

## 6.4、Bean的作用域(Scopes)

![image-20191229165317101](E:\Typora2\笔记图片存放处\image-20191229165317101.png)

#### 1.单例模式（Spring默认机制）

```xml
<bean id="user2" class="com.ioc.pojo.User" c:age="18" c:name="admin2" 
      scope="singleton"/>
```

#### 2.原型模式: 每次从容器中get的时候，都会产生一个新对象！

```xml
<bean id="user2" class="com.ioc.pojo.User" c:age="18" c:name="admin2" 
      scope="prototype"/>
```

#### 3.其余的request，session，application，这些只能在web开发中使用

# 7.Bean的自动装配

* ##### 自动装配是Spring满足bean依赖的一种方式！

* ##### Spring会在上下文中自动寻找，并自动给bean装配属性

### 在Spring中有三种自动装配的方式：

1.在xml中显示配置

2.在java中显示配置

**3.隐式的自动装配Bean【重要】**

## 7.1、测试

1.环境搭建

* 一个人有两个宠物



## 7.2、ByName自动装配

```xml
<!--
    byName：会自动在容器上下文中查找，和自己对象set方法后的值对应的bean id
    -->
<bean id="people" class="com.autowried.pojo.People" autowire="byName">
    <property name="name" value="无垢"/>
</bean>
```

## 7.3、ByType自动装配

```xml
<bean class="com.autowried.pojo.Cat"/>
<bean class="com.autowried.pojo.Dog"/>
<!--
    byName：会自动在容器上下文中查找，和自己对象set方法后的值对应的bean id
    byType：会自动在容器上下文中查找，和自己对象属性类型相同的bean
    -->
<bean id="people" class="com.autowried.pojo.People" autowire="byType">
    <property name="name" value="无垢"/>
</bean>
```

小结：

* byname的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性和set方法的值一致！
* bytype的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型一致！

## 7.4、使用注解实现自动装配

#### jdk1.5支持的注解，spring2.5就支持注解了

The introduction of annotation-based configuration raised the question of whether this approach is “better” than XML

#### 要使用注解须知：

1.导入约束.context约束

2.配置注解的支持 <context:annotation-config/> 【重要】

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <context:annotation-config/>
</beans>
```

##### @Autowired

直接在属性上使用即可！也可以在set方法上使用

使用Autowired我们可以不用编写set方法了，前提是你这个自动装配的属性在IOC（Spring）容器中且符合名字byName！

科普：

```xml
@Nullable 字段标记这个属性，可以为null
```

```xml
public @interface Autowired {
    boolean required() default true;
}
```

测试代码

```java
//如果定义required属性为false，说明这个对象可以为null
@Autowired(required = false)
private Cat cat;
@Autowired
private Dog dog;
private String name;
```

如果@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解【@Autowired】完成的时候，我们可以使用@Qualifier(value="xxx")去配置@Autowired的使用，指定一个唯一的bean对象注入！

```java
@Data
public class People {
    @Autowired
    @Qualifier("dog111")
    private Dog dog;
    @Autowired
    @Qualifier("cat111")
    private Cat cat;
    private String name;
}
```

**Java自带的注解：@Resource注解（了解即可，开发还是推荐用@Autowired）**

小结：

##### @Resource和@Autowired的区别：

* 都是用来自动装配的，都可以放在属性字段上
* @Autowired默认通过byType的方式实现，默认情况下必须要求依赖对象必须存在，如果要允许null值，可以设置它的required属性为false
* @Resource默认通过ByName的方式实现，如果找不到名字，则通过byType实现！如果两个都找不到的情况下，就报错。
* 执行顺序不同：
  * **@Autowired默认通过byType的方式实现**
  * @Resource默认通过ByName的方式实现

# 8.使用注解开发

**在spring4之后，要使用注解开发，必须保证aop的包导入了**

![image-20191230143456050](https://github.com/z011009/Spring/blob/master/images/image-20191230143456050.png)

**主要注解需要导入context约束，提供注解的支持**】

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">
    <context:annotation-config/>
</beans>
```

### 1.Bean

### 2.属性如何注入

```java
//等价于一个bean
@Component
public class User {
    //相当于<pro... name="name" value="admin1">
    @Value("admin1")
    public  String name;
    public void setName(String name) {
        this.name = name;
    }
}
```

### 3.衍生的注解

@Component有几个衍生注解，我们在web开发中，会按照mvc三层架构分层

* dao【@Repository】
* service【@Service】
* controller【@Controller】
* 这四个注解功能都一样，都是蒋某个类注册到spring中，装配bean

### 4.自动装配

``` 
-@Autowired:自动装配通过类型.名字
如果Autowired不能唯一自动装配上属性，则需要通过@Qualifier(Value="xxx")
-@Nullable:字段标记了这个注解，说明这个字段可以为null
-@Resource：自动装配通过名字.类型
```

### 5.作用域

```java
//等价于一个bean
@Component
@Scope("singleton")
public class User {
    public String name;
    
    //相当于<pro... name="name" value="admin1">
    @Value("admin1")
    public void setName(String name) {
        this.name = name;
    }
}
```

### 6.小结

#### xml和注解

* xml 更加万能，适用于任何场景，维护简单方便
* 注解 不是自己类使用不了，维护相当复杂

#### xml与注解最佳实践

* xml用来管理bean
* 注解只负责完成属性的注入
* 我们在使用的过程中，只需要注意一个问题，必须让注解生效，就需要开启注解支持

```xml
<!--指定扫描包，这个包下的注解就会生效-->
<context:component-scan base-package="cn.wugou.pojo" />
<context:annotation-config/>
```

# 9.使用JAVA的方式配置Spring

**我们现在要完全不使用spring的xml配置，全权交给java来做！**

**javaConfig是spring的子项目，spring4之后，他成为了核心功能**

![image-20200113205617944](https://github.com/z011009/Spring/blob/master/images/image-20200113205617944.png)

### 1.实体类

```java
@Data //lombok的一个注解，使用这个注解自动生成get，set，toString等...
@Component //就是说明这个类被Spring接管了，注册到容器中
public class User {
    @Value("无垢")//属性注入值
    private String name;
}
```

### 2.配置类

```java
//这个也会被spring容器托管，注册到容器中，因为他本是也是个@Component，
//@Configuration 代表这是一个配置类，就和我们之前看的beans.xml
@Configuration
@ComponentScan("cn.wugou.pojo") //相当于<context:component-scan base-package="" />
@Import(wugouConfig2.class)  //相当于<import resource="" />
public class wugouConfig {
    //注册一个bean，@Bean相当于bean标签
    //这个方法的方法名，相当于bean标签中id属性
    //这个方法的返回值，相当于bean标签中class属性
    @Bean
    public User user(){
        return new User();
    }
}
```

### 3.测试类

```java
public class MyTest {
    @Test
    public void test1() {
        //如果完全使用了配置类的方式，我们就只能通过AnnotationConfig上下文来获取容器，通过配置类的class对象加载！
        ApplicationContext context = new AnnotationConfigApplicationContext(wugouConfig.class);
        
        User user = context.getBean("user", User.class);
        System.out.println(user.getName());
    }
}
```

# 10.代理模式

**为什么要去学习代理模式？** 

因为代理模式就是SpringAOP的底层！【SpringAOP和springMVC】

代理模式分两种:

* **静态代理**
* **动态代理**

![image-20200113212343063](https://github.com/z011009/Spring/blob/master/images/image-20200113212343063.png)

## 10.1、静态代理

#### 角色分析

* 抽象角色：一般会使用接口或抽象类来解决
* 真实角色：被代理的角色
* 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
* 客户：访问代理对象的人

#### 代码步骤：

1.接口

```java
//租房
public interface Rent {
    public void rent();
}
```

2.真实角色

```java
//房东
public class Host implements Rent{

    public void rent() {
        System.out.println("房东要出租房子");
    }
}
```

3.代理角色

```java
public class Proxy implements  Rent{
    private Host host;

    public Proxy() {
    }
    public Proxy(Host host) {
        this.host = host;
    }

    public void rent() {
        seeHouse();
        host.rent();
        fare();
        hetong();
    }

    //看房
    public void seeHouse(){
        System.out.println("中介带你去看房");
    }
    //收中介费
    public void fare(){
        System.out.println("收中介费");
    }
      //签合同
    public void hetong(){
        System.out.println("签租赁合同");
    }
}
```

4.客户端访问代理角色

```java
public class Client {
    public static void main(String[] args) {
        //房中要租房子
        Host host=new Host();
        //代理，中介帮房东租房子，但是呢?代理一般会有附属操作
        Proxy proxy = new Proxy(host);
        //你不用面对房东，直接找中介租房即可！
        proxy.rent();
    }
}
```



#### 代理模式的好处：

* 可以使真实角色的操作更加纯粹，不用去关注一些公务的业务
* 公共也就是交给代理角色！实现了业务的分工
* 公共业务发生扩展的时候，方便集中管理！

#### 缺点：

* 一个真实角色就会产生一个代理角色，代码量会翻倍-开发效率会变低

## 10.2、加深理解

#### 1.代码

##### 业务接口

```java
public interface UserService {
    public void select();
    public void delete();
    public void update();
    public void insert();
}
```

##### 业务实现

```java
//真实对象
public class UserServiceImpl implements UserService {
    @Override
    public void select() {
        System.out.println("查询");
    }
    @Override
    public void delete() {
        System.out.println("删除");
    }
    @Override
    public void update() {
        System.out.println("更新");
    }
    @Override
    public void insert() {
        System.out.println("添加");
    }
    //1.改动原有的业务代码，在公司中是大忌
}
```

##### 代理类

```java
public class UserServiceProxy implements UserService {
    private UserServiceImpl userService;
    
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Override
    public void select() {
        log("select");
        userService.select();
    }
    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }
    @Override
    public void update() {
        log("update");
        userService.update();
    }
    @Override
    public void insert() {
        log("insert");
        userService.insert();
    }
    
    //日志方法
    public void log(String msg) {
        System.out.println("使用了" + msg + "方法");
    }
}
```

##### 客户

```java
public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);
        proxy.insert();
    }
}
```

#### 2.聊聊AOP

![image-20200113223526280](https://github.com/z011009/Spring/blob/master/images/image-20200113223526280.png)

## 10.3、动态代理

* 动态代理和静态代理角色一样
* 动态代理的代理类是动态生成的，不是我们直接写好的
* 动态代理分两大类：基于接口的动态代理，基于类的动态代理
  * 基于接口--jdk动态代理【我们在这里使用】
  * 基于类--cglib
  * java字节码实现：javasist

需要了解两个类：Proxy，InvocationHandler：调用处理程序

动态代理的好处：

* 可以使真实角色的操作更加纯粹，不用去关注一些公务的业务
* 公共也就是交给代理角色！实现了业务的分工
* 公共业务发生扩展的时候，方便集中管理！
* 一个动态代理类代理的是一个接口，一般就是对应的一类业务
* 一个动态代理可以代理多个类，只要是实现了同一个接口即可！

# 11.AOP

## 11.1.什么是AOP

AOP为Aspect Oriented Programming的缩写，意为:[面向切面编程](https://baike.so.com/doc/7533542-7807635.html)，通过[预编译](https://baike.so.com/doc/1299139-1373590.html)方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是[OOP](https://baike.so.com/doc/6222793-6436101.html)的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是[函数式编程](https://baike.so.com/doc/6534519-6748257.html)的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的[耦合度](https://baike.so.com/doc/1051405-1112171.html)降低，提高程序的可重用性，同时提高了开发的效率。

![image-20200114144111747](https://github.com/z011009/Spring/blob/master/images/image-20200114144111747.png)

## 11.2.Aop在Spring中的作用

![image-20200114144018013](https://github.com/z011009/Spring/blob/master/images/image-20200114144018013.png)

![image-20200114144029353](https://github.com/z011009/Spring/blob/master/images/image-20200114144029353.png)

![image-20200114144038228](https://github.com/z011009/Spring/blob/master/images/image-20200114144038228.png11.3.使用Spring实现AOP

【重点】使用AOP织入，需要导入一个依赖包！

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
```



方式一：使用Spring的AOP接口【主要是springAPI接口实现】

方式二：自定义来实现AOP【主要是切面定义】

方式三：使用注解实现！

# 12.整合mybatis

步骤

1.导入相关jar包

* junit
* mybatis
* mysql数据库
* Spring相关的
* aop织入
* mybatis-spring【new】

2.编写配置文件

3.测试



## 12.1.回忆MyBatis

1.编写实体类

2.编写核心配置文件

3.编写接口

4.编写Mapper.xml

5.测试

## 12.2.MyBatis-Spring

1.编写数据源配置

2.sqlSessionFactory

3.sqlSessionTemplate

4.需要给接口加实现类

5.将自己写的实现类，注入到spring中

6.测试

# 13.声明式事务

## 1.回顾事务

* 把一组业务当成一个业务来做，要么成功，要么失败
* 事务在项目开发中，十分的重要，涉及到数据的一致性，不能马虎!
* 确保完整性和一致性

#### 事务ACID原则：

* 原子性
* 一致性
* 隔离性
  * 多个业务可能操作同一资源，防止数据损坏
* 持久性
  * 事务一旦提交，无论系统发生什么问题，结果都不会被影响，被持久化的写到存储器中

## 2.Spring中的事务管理

* 声明式事务：AOP
* 编程式事务：需要在代码中，进行事务的管理

#### 思考：为什么需要事务？

* 如果不配置事务，可能存在数据提交不一致的情况
* 如果我们不在spring中去配置声明式事务，我们需要在代码中手动配置事务
* 事务在项目的开发中十分重要，设计到了数据的一致性和完整性问题，不容马虎

