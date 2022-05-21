# spring-recap
### Different ways to create bean
  1.  config file (beans.xml) <hr>
  ```
   <bean id="object" class="com.mrahman.Developer" />
  ```
  2. Using @Component above a class <hr>
 
     - main.java:
       ``` 
       ApplicationContext applicationContext = new ClasspathApplicationContext("beans.xml") ; 
       Developer dev = applicationContext.getBean("dev",Developer.class) ; 
       ```

      - beans.xml:
        ```
        <context:annotation-config> // enabling annotation support
        <context:component-scan base-package="com.mrahman.Developer" />
        ```
      - Developer.java:
        ```
        @Component
        public class Developer {}
        ```
       - We can replace beans.xml with a java class annotated with @Configuration like the following:
         ```
         @Configuration
         @ComponentScan(basepackages="com.example")
         public class ConfigFile {}
         ```
        - If we use @Configuration , we'll have to use ```AnnotationConfigApplicationContext``` instead of ```ClasspathApplicationContext```
  3. Using @Bean <hr>
     -  If we do not want to use @Component, we can use @Bean annotation to create beans. As we have replace our beans.xml with a configuration class, we'll have to define this bean inside the configuration class. In the following code, **method name is the bean name**. **No need to use @Component above the Developer class** .
     - Developer.java
       ```
       public class Developer {}
       ```
     
     - MyConfig.java
       ```
       @Configuration
       public class MyConfig {
       
       @Bean
        public Developer developerBean() {
          return new Developer() ; 
        }
       }
       ```
      - Main.java
        ```
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CollegeConfig.class) ;
        Developer dev = applicationContext.getBean("developerBean",Developer.class) ;
        ```
## Spring MVC
### Setup Dispatcher Servlet
   - web.xml
      ```
	  <servlet>
        <servlet-name>front-controller</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	  </servlet>
	  <servlet-mapping>
		<servlet-name>front-controller</servlet-name>
		<url-pattern>/</url-pattern>
	  </servlet-mapping>
      ```
### When Dispatcher Servlet will be initialized?
   - If we see the log of our application, we won't see any log related to the initialization of the front-controller. If we put  ```<load-on-startup>1</load-on-startup>``` to ```web.xml``` , then the dispatcher servlet will be initialized during the startup of the tomcat server. Otherwise, if we don't mention the ```<load-on-startup>1</load-on-startup>``` in ```web.xml```, then the dispatcher servelet will be initialized when the first request comes to the server. 
   - Dispatcher servlet will find for another xml file inside ```WEB-INF``` folder for getting initialized. The format of the file is ```frontController-servlet.xml```.This xml file is similiar to the bean config file of spring core. Here, we need to mention the component-scan.

### Creating Controllers
   - Let's create a controller like the following,
	  ```
	  	@Controller 
		@RequestMapping(value = "/test")
		public class Test {
			@ResponseBody 
			@GetMapping("/")
			public String getName() {
				return "shuvo" ;
			}
		}
	  ```
  - Here, @Controller is as same as @Component. Test class will be work as a component. So, we'll have to specifiy this class to spring config file i.e. ```front-controller-servlet.xml```. Otherwise, this class will not be taken care by IOC container and if we hit the /test url, we will get a 404 error. So, we specify the base package to ```front-controller-servlet.xml``` file like ```<context:component-scan base-package="com.mrahman.controller" />``` 

### Web Container | WebApplicationContext
   - When we start our mvc application, spring searches for the config file named ```front-controller-servlet.xml``` . By reading this file, spring creates a web container named **WebApplicationContext**. In standalone applications using core spring, we used to create IOC container named **ApplicationContext**, but in spring mvc, we create a web container named **WebApplicationContext**. But, It is not our responsibility to start and stop the **WebApplicationContext**, it's taken care of by spring. 
	```	public interface WebApplicationContext extends ApplicationContext```
	
### How to change dispatcher servlet context configuration file name | Custom dispatcher servlet context configuration file name
   - We can customize the dispatcher servlet context configuration file name using ```contextConfigLocation``` as ```<init-param>``` in ```web.xml``` like the following:
     ```
	 <servlet>
        <servlet-name>front-controller</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/my-customized-front-controller-config.xml</param-value>
		</init-param>
	 </servlet>
	 <servlet-mapping>
		<servlet-name>front-controller</servlet-name>
		<url-pattern>/</url-pattern>
	 </servlet-mapping>
	 ```
	 
### Spring MVC ViewResolver
