package beanScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanScopeDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("beanScope") ;
        PrototypeDemo prototypeDemo1 = applicationContext.getBean("prototypeDemo", PrototypeDemo.class);
        PrototypeDemo prototypeDemo2 = applicationContext.getBean("prototypeDemo", PrototypeDemo.class);
        if(prototypeDemo1 == prototypeDemo2)
            System.out.println("same..");
        else System.out.println("not same");


        // ---------- singleton -------------
//        SingletonDemo obj1 = applicationContext.getBean("singletonDemo", SingletonDemo.class);
//        SingletonDemo obj2 = applicationContext.getBean("singletonDemo", SingletonDemo.class);
//        System.out.println("obj1: "+obj1+" --- obj2: "+obj2);
//        if(obj1 == obj2)
//            System.out.println("same object..");
    }
}
