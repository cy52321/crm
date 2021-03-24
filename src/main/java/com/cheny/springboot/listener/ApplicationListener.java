package com.cheny.springboot.listener;


import com.cheny.springboot.domain.DicType;
import com.cheny.springboot.domain.DicValue;
import com.cheny.springboot.service.impl.DicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ApplicationListener implements ServletContextListener {

    @Autowired
    private DicServiceImpl dicService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext application=sce.getServletContext();
        WebApplicationContextUtils.getRequiredWebApplicationContext(application).getAutowireCapableBeanFactory().autowireBean(this);
        /*Map<String, List<DicValue>> map=new HashMap<>();*/
        List<DicType> list1=dicService.getTypeList();
        for(DicType dicType:list1){
            application.setAttribute(dicType.getCode(),dicService.getListByCode(dicType.getCode()));
        }
    }
}
