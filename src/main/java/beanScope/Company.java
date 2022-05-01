package beanScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class Company {

    @Autowired
    private Employee employee;

    public Company() {
        System.out.println("Company object created");
    }

    @Lookup // spring's cglib will override this method at runtime and will return a Employee object.
    abstract public Employee createEmployee() ;

    public Employee getEmployee() {
        return createEmployee() ;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
