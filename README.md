# Spring Setter Dependency Injection using XML

**1. Introduction**

In this article, we are going to explore about core concept of Spring (Dependency Injection). In particular, we’ll learn about the types of Bean Injection in Spring.

Before going deep types of Bean Injection, let's first know what Dependency Injection is.

**2. Dependency Injection**

Dependency Injection or DI (earlier known as Inversion of Control) says that "Don't call us, we'll call you."

In a typical application where there are many classes, interaction between classes are done manually by creating the object of the required class and use it wherever required. Since it is done manually it is hard to maintain the code with similar objects all over the application and accommodation of new type of object leads to change of code again.

When applying DI, the objects are given their dependencies at creation time by some external entity that coordinates each object in the system. In other words, dependencies are injected into objects.

Spring was naturally born for solving the Dependency Management Issue and maintaining those dependency in one particular file (beans.xml). This file is loaded on server start up and all the beans are created and injected during that time. This solves the problem of manually creating objects all over the application.

**3. How Beans are Injected?**

There are different ways of injecting this bean:

Constructor Injection.
Setter Injection.

Before we describe in detail about the above injection methods we will first add the below dependency in maven pom.xml

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.0.3.RELEASE</version>
</dependency>
```

**3.1 Constructor Injection**

Constructor-based DI is accomplished when the container invokes a class constructor with a number of arguments, each representing a dependency on the other class.

Using XML Configuration:

```
public class EmployeeService
{
   private IEmployeeDAO employeeDao;
   public EmployeeService(IEmployeeDAO employeeDao){
      this.employeeDao = employeeDao;
   }
}
```
```
<bean id="EmployeeService" class="com.baeldung.spring.service.EmployeeService">
   <constructor-arg>
      <bean class="com.baeldung.spring.dao.impl.EmployeeDAO" />
   </constructor-arg>
</bean>
```
(or)
```
<bean id="EmployeeService" class="com.baeldung.spring.service.EmployeeService">
   <constructor-arg ref="employeeDAO"/>
</bean>
<bean id="employeeDAO" class="com.baeldung.spring.dao.impl.EmployeeDAO"/>
```
Using Annotations:
```
@Configuration
@ComponentScan("com.baeldung.spring")
public class ApplicationConfig { 
    @Bean
    public IEmployeeDAO employeeDao() {
        return new EmployeeDAO();
    }
}
```
```
@Service
public class EmployeeService {
    private IEmployeeDAO employeeDao; 
    @Autowired
    public EmployeeService(IEmployeeDAO employeeDao) {
        this.employeeDao= employeeDao;
    }
}
```

**3.2 Setter Injection**

Setter-based DI is accomplished by the container calling setter methods on your beans after invoking a no-argument constructor or no-argument static factory method to instantiate your bean.

Using XML Configuration:
```
public class EmployeeService
{
   private IEmployeeDAO employeeDao;
   public setEmployeeDao(IEmployeeDAO employeeDao){
      this.employeeDao = employeeDao;
   }
}
```
```
<bean id="EmployeeService" class="com.baeldung.service.EmployeeService">
   <property name="employeeDao ref="employeeDAO"/>
</bean>

<bean id="employeeDAO" class="com.baeldung.dao.impl.EmployeeDAO"/>
```
Using Annotations:
```
@Configuration
@ComponentScan("com.baeldung.spring")
public class ApplicationConfig {
   @Bean
   public IEmployeeDAO employeeDao() {
      return new EmployeeDAO();
   }
}
```
```
@Service
public class EmployeeService {
    private IEmployeeDAO employeeDao;
    @Autowired
    public setEmployeeDao(IEmployeeDAO employeeDao){
        this.employeeDao = employeeDao;
    }
}
```
**4. Testing**

By loading the beans.xml in which the beans are configured in that xml:

ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
EmployeeService employeeService = context.getBean(EmployeeService.class);

By loading ApplicationConfig.class which uses annotation based configuration:

ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
EmployeeService employeeService = context.getBean(EmployeeService.class);

**5. Conclusion**

This tutorial has showcased the Contractor-Based Dependency Injection and Setter-Based Dependency Injection using Spring framework.

