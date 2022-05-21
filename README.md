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
