package beanScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanScopeDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("beanScope") ;
        Company company1 = applicationContext.getBean("company",Company.class) ;
        System.out.println("first call "+company1.getEmployee());
        System.out.println("second call "+company1.getEmployee());
    }
}
