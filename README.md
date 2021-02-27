![spring](https://user-images.githubusercontent.com/36560845/108955840-04f26380-7699-11eb-8acf-a64857a132c4.png)


## Spring Modules
### Core Container
* Core container consists of following 5 modules.
  - spring-core
  - spring-beans
  - spring-context
  - spring-context-support
  - spring-expression
* spring-core and spring-beans provide the IoC and Dependency Injection features.
* spring-context inherits features from spirng-bean and adds supports for I18N,event propagation,resource loading. spring-context also supports Java EE features i.e. EJB, JMX and basic remoting. **ApplicationContext** interface is the focal point of spring-context module.
* spring-context-support provides support for integrating common third party libraries into spring application context in particular, for caching (EhCache, JCache) and scheduling (commonJ, Quartz).
* spring-expression provides powerful expression language for querying and manipulating an object graph at runtime.
* org.springframework.beans and org.springframework.context --> basis for IoC container.
* BeanFactory interface provides configuration framework and basic functionality
* ApplicationContext (sub interface of BeanFactory) adds more enterprise-specific functionality.
* **org.springframework.context.ApplicationContext** represents the **IoC container.**
* IoC container instantiates, configures and assembles beans.
* Container instantiation: 

```ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"services.xml","daos.xml"});```

services.xml:
```
<beans>
  <bean id="petStore" class="org.spring.services.PetStoreServiceImpl">
    <property name="accountDao" ref="accountDao" />
    <property name="itemDao" ref="itemDao" />
  </bean>
</beans>
```

daos.xml:
```
<beans>
  <bean id="accountDao" class="org.spring.dao.AccountDao"> ... </bean>
  <bean id="itemdao" class="org.spring.dao.ItemDao"> ... </bean>
</beans>
```

* Using the container:
```
ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"services.xml","daos.xml"});
PetStoreService petStoreService = context.getBean("petStore",PetStoreService.class) ; 
List<String> userList = petStoreService.getUserNameList() ; 
```

* Within container, beans are represented as **BeanDefinition** objects. **BeanDefinition** objects contain following metadata:
  - A package-qualified class name
  - Bean behavioral configuration elements i.e. scope, lifecycle, callbacks etc.
  - Dependencies or collaborator beans
  - Various config settings i.e. number of connectinos to use in a bean, connection pool limit etc
* **Registration of beans defined by user, not container:** 
    - ApplicationContext implementations also permit the registration of existing objects that are created outside the container, by users. This is done by accessing the ApplicationContext’s BeanFactory via the method getBeanFactory() which returns the BeanFactory implementation **DefaultListableBeanFactory**.
    - DefaultListableBeanFactory supports this registration through the methods registerSingleton(..) and registerBeanDefinition(..).
* **Naming beans**
    - bean id uniqueness is enforced by the container, not by XML parsers. If no name or id is provided, container generates a unique name for that bean.
    - How it generates unique name: className: AccountDao  ==> beanName: accountDao. className: ABclass ==> beanName: ABclass
* **Inner Bean**
In the following scenario, lets say, we know that pointB pointC aren't going to be used anywhere. Only this triangle bean will use these two beans. So, there's no need to reference then as an external bean. So, embed these two beans inside triangle bean. But, as pointA is going to be used across all over the application i.e., other beans may use it, we've defined this as an external bean. 

* **Bean alias**
We can give multiple names for a bean using alias. 
* **idref** 
We can restrict ref to only refer to an id of a bean. It should be nested inside the property.
  ```
   <beans>
    <bean id="triangle" class="org.spring.Triangle">  --------> **[context.getBean("triangle")**
      <property name="pointA" ref="zeroPoint"/> --------------> **[ref can point to an id, name or alias]**
      <!-- id ref 
      <property name="pointA">
        <idref="zeropoint" />---------------------------------> ** [ref is now restricted to point to only id] **
      </property>
      -->
      <property name="pointB">
        <!-- inner bean -->
        <bean class="org.spring.Point">
          <property name="x" value="20"/>
          <property name="y" value="30"/>
        </bean>
      </property>
      
      <property name="pointC">
        <!-- inner bean -->
        <bean class="org.spring.Point">
          <property name="x" value="40"/>
          <property name="y" value="50"/>
        </bean>
      </property>
    </bean>
    
    <bean id="zeroPoint" class="org.spring.Point">
      <property name="x" value="0" />
      <property name="y" value="0" />
    </bean>
    
    <alias name="triangle" alias="triangle-alias"/> -----------> **[we can call this bean by, context.getBean("triangle-alias")]**
   </beans>
  ```
* **Instantiating Beans**
IoC container can instantiate a bean by using following 3 ways:
- Constructor
```
<bean id="exampleBean" class="com.moshiur.ExampleBean" />
<bean id="exampleBeanTwo" class="com.moshiur.ExampleBeanTwo" />
```
- A static factory method
```
server.xml:
------------
<bean id="clientService" class="com.moshiur.ClientService" factory-method="createInstance" />

ClientService.java:
-------------------
public class ClientService {
  private static ClientService clientService ; 
  private ClientService() { .... }
  //static factory method
  public static ClientService createInstance() {
    if(clientService == null) {
      clientService = new ClientService() ; 
     }
     return clientService ; 
    } 
  
```
- Instance factory method
```
 server.xml
-----------
<!-- the factory bean, which contains a method called createInstance() -->
<bean id="serviceLocator" class="examples.DefaultServiceLocator">
<!-- inject any dependencies required by this locator bean -->
</bean>


<!-- the bean to be created via the factory bean -->
<bean id="clientService"
factory-bean="serviceLocator"
factory-method="createClientServiceInstance"/>

 DefaultServiceLocator.java:
------------------------------
public class DefaultServiceLocator {
  private static ClientService clientService = new ClientServiceImpl();
  private DefaultServiceLocator() {}
  public ClientService createClientServiceInstance() {
  return clientService;
  }
}
```
