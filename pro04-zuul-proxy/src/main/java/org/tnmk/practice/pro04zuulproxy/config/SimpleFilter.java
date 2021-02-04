package org.tnmk.practice.pro04zuulproxy.config;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

@Component
public class SimpleFilter extends ZuulFilter {

  private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()){
      String headerName = headerNames.nextElement();
      ctx.addZuulRequestHeader(headerName, request.getHeader(headerName));
    }

    log.info("Original request: {} {}", request.getMethod(), request.getRequestURL().toString());

    return null;
  }

}