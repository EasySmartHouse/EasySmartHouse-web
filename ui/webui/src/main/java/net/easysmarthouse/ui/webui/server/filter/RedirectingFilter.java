/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.server.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author mirash
 */
public class RedirectingFilter implements Filter {

    private final Logger log = Logger.getLogger(RedirectingFilter.class.getName());
    private static final String HOME_PAGE = "webui.html";
    private ServletContext servletContext;
    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        this.contextPath = servletContext.getContextPath();
        log.debug("Start with context path: " + contextPath);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String url = httpRequest.getRequestURL().toString();
        //to prevent conflict with security filter 
        if (!url.isEmpty() && (url.endsWith(contextPath) || url.endsWith(contextPath + "/"))) {

            if (!httpResponse.isCommitted()) {
                log.debug("Redirecting to main page: " + HOME_PAGE);
                httpResponse.sendRedirect(HOME_PAGE);
                httpResponse.flushBuffer();
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
