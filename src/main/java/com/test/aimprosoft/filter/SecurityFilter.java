package com.test.aimprosoft.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String pathToPage = httpServletRequest.getServletPath();

        if(httpServletRequest.getSession().getAttribute("user")==null
                && !pathToPage.equals("/registration")
                && !pathToPage.equals("/registration.jsp")
                && !pathToPage.equals("/login.jsp")
                && !pathToPage.equals("/login"))
        {
            httpServletResponse.sendRedirect(filterConfig.getServletContext().getContextPath()+"/login.jsp");

        }else  chain.doFilter(request, response);


    }
}
