package com.coolinternet.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DemoFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(DemoFilter.class);
    @Override
    public String filterType()
    {
        return "pre";
    }
    @Override
    public int filterOrder()
    {
        return 0;
    }
    @Override
    public boolean shouldFilter()
    {
        return true;
    }
    @Override
    public Object run()
    {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String string = String.format("%s >>> %s",request.getMethod(),request.getRequestURL().toString());
        log.info(string);
        return null;
    }
}
