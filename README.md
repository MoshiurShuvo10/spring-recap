# spring-recap
## Different ways to create bean
  1.  config file (beans.xml)
  ```
   <bean id="object" class="com.mrahman.Developer" />
  ```
  2. Using @Component above a class
 
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
  3. Using @Bean
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
## Setup Dispatcher Servlet
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
## When Dispatcher Servlet will be initialized? | load-on-startup
   - If we see the log of our application, we won't see any log related to the initialization of the front-controller. If we put  ```<load-on-startup>1</load-on-startup>``` to ```web.xml``` , then the dispatcher servlet will be initialized during the startup of the tomcat server. Otherwise, if we don't mention the ```<load-on-startup>1</load-on-startup>``` in ```web.xml```, then the dispatcher servelet will be initialized when the first request comes to the server starting with the url pattern of dispatcher servlet. 
   - Dispatcher servlet will find for another xml file inside ```WEB-INF``` folder for getting initialized. The format of the file is ```frontController-servlet.xml```.This xml file is similiar to the bean config file of spring core. Here, we need to mention the component-scan.

## Creating Controllers
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

## @ResponseBody
   - If this annotation is not specified above the method name, spring will expect the return type as a view name. But, we want to print "shuvo" as output in webpage. so, we need to use @ResponseBody to view this as output. 

## Web Container | WebApplicationContext
   - When we start our mvc application, spring searches for the config file named ```front-controller-servlet.xml``` . By reading this file, spring creates a web container named **WebApplicationContext**. In standalone applications using core spring, we used to create IOC container named **ApplicationContext**, but in spring mvc, we create a web container named **WebApplicationContext**. But, It is not our responsibility to start and stop the **WebApplicationContext**, it's taken care of by spring. 
	```	public interface WebApplicationContext extends ApplicationContext```
	
## How to change dispatcher servlet context configuration file name | Custom dispatcher servlet context configuration file name
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
	 
## Spring MVC ViewResolver
   - Configure view resolver to dispatcher servlet config xml file
     ```
     <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver" >
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
     </bean>
     ```     
## Is ```web.xml``` mandatory for our web application?
   - ```web.xml``` is the configuration file for web application and it is automatically picked up by tomcat. If we use servlet 3.0 and onwards, ```web.xml``` is not mandatory. we can use a java configuration in replacement of ```web.xml```

## Spring MVC Java based configuration | No XML
## Replacing ```web.xml``` with java config | XmlWebApplicationContext | WebApplicationInitializer
   - If we want to use java based configuration i.e. a java class in replacement of ```web.xml```, we need to implement an interface named ```WebApplicationInitializer```. This interface has a method named ```onStartup(ServletContext servletContext)```. This method is automatically called by tomcat. So, we need to create dispatcher servlet inside this method and register the dispatcher servlet with servlet context. 
   ```
   public class WebXml implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("this is the web.xml replacement. This class will be automatically picked up by server and " +
                "this method will be called by tomcat.");

        XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext() ;
        xmlWebApplicationContext.setConfigLocation("classpath:myconfig.xml");

        DispatcherServlet dispatcherServlet = new DispatcherServlet(xmlWebApplicationContext) ;
        ServletRegistration.Dynamic frontController = servletContext.addServlet("front-controller", dispatcherServlet);
        frontController.setLoadOnStartup(1);
        frontController.addMapping("/") ;
    }
}
   ```

## Replacing dispatcher-servlet.xml with Java config | AnnotationConfigWebApplicationContext | WebApplicationInitializer
   - Replacement of web.xml
   ```
   public class WebXml implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("this is the web.xml replacement. This class will be automatically picked up by server and " +
                "this method will be called by tomcat.");

        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext() ;
        annotationConfigWebApplicationContext.register(SpringConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext) ;
        ServletRegistration.Dynamic frontController = servletContext.addServlet("front-controller", dispatcherServlet);
        frontController.setLoadOnStartup(1);
        frontController.addMapping("/") ;
    }
}
   ```
   - Replacement of dispatcher-servlet.xml
   ```
	@Configuration
	@ComponentScan(basePackages = "controller")
	public class SpringConfig {

		@Bean
		public InternalResourceViewResolver internalResourceViewResolver() {
			InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver() ;
			internalResourceViewResolver.setPrefix("/WEB-INF/views/");
			internalResourceViewResolver.setSuffix(".jsp");
			return internalResourceViewResolver ;
		}
	}
   ```
