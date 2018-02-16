package com.test.aimprosoft.servlet;

import com.test.aimprosoft.inject.InjectParser;
import com.test.aimprosoft.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;

public class DependencyInjectionServlet extends HttpServlet {

    private static final String APPLICATION_CONTEXT_PATH = "/applicationContext.xml";

    @Override
    public void init() throws ServletException {

        try {
            InjectParser appContext = new InjectParser();
            appContext.init(APPLICATION_CONTEXT_PATH);
            Field[] fields = this.getClass().getDeclaredFields();
            for(Field field: fields){
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                if(annotation != null){
                    String beanName = annotation.value();
                    Object bean = appContext.newInstance(beanName);
                    if(bean == null){
                        throw new Exception("Bean not found by name: "+beanName);
                    }
                    field.set(this, bean);
                }
            }

        }catch (Exception e){
            throw new ServletException("Can not inject");
        }
    }
}
