package beanScope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("prototypeDemo")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) // every time call for bean will return different beans..
public class PrototypeDemo {
}
