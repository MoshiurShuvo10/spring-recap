package controller;

import Pojo.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Test {
    @Autowired
    MyBean myBean ;

    @RequestMapping("/getname")
    public void test(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.getWriter().println("old name: "+myBean.getName());
        myBean.setName("Moshiur Rahman");
        httpServletResponse.getWriter().println("new name: "+myBean.getName());
    }
}
