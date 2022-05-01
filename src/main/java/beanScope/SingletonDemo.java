package beanScope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("singletonDemo")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) // default scope for spring beans are singleton.
public class SingletonDemo {
}
