package schutze.hades.spring.test.provider.configuration.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import lombok.val;
import schutze.hades.spring.test.provider.configuration.spring.SpringAppConfiguration;
import schutze.hades.spring.test.provider.configuration.spring.SpringServletConfiguration;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Create the 'root' Spring application context
        val springAppContext = new AnnotationConfigWebApplicationContext();
        springAppContext.register(SpringAppConfiguration.class);

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(springAppContext));

        // Create the dispatcher servlet's Spring application context
        val springServletContext = new AnnotationConfigWebApplicationContext();
        springServletContext.register(SpringServletConfiguration.class);

        // Register and map the dispatcher servlet
        val springDispatcher = servletContext.addServlet("springDispatcher",
                new DispatcherServlet(springServletContext));
        springDispatcher.setLoadOnStartup(1);
        springDispatcher.addMapping("/");
    }
}
