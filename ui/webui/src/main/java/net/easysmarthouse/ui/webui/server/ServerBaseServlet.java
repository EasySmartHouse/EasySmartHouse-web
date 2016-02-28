/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author rusakovich
 */
public class ServerBaseServlet extends RemoteServiceServlet {

    private void initServerContext() {
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        //for populate existing bean instances that Spring does not control the lifecycle of
        AutowireCapableBeanFactory beanFactory = wac.getAutowireCapableBeanFactory();
        beanFactory.autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
        beanFactory.initializeBean(this, this.getClass().getName());
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        initServerContext();
    }

}
