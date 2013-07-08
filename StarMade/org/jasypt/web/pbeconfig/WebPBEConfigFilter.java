/*  1:   */package org.jasypt.web.pbeconfig;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.PrintWriter;
/*  5:   */import javax.servlet.Filter;
/*  6:   */import javax.servlet.FilterChain;
/*  7:   */import javax.servlet.FilterConfig;
/*  8:   */import javax.servlet.ServletException;
/*  9:   */import javax.servlet.ServletRequest;
/* 10:   */import javax.servlet.ServletResponse;
/* 11:   */
/* 59:   */public final class WebPBEConfigFilter
/* 60:   */  implements Filter
/* 61:   */{
/* 62:   */  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/* 63:   */    throws IOException, ServletException
/* 64:   */  {
/* 65:65 */    WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
/* 66:66 */    if (registry.isWebConfigurationDone())
/* 67:   */    {
/* 68:68 */      chain.doFilter(request, response);
/* 69:   */    }
/* 70:   */    else {
/* 71:71 */      PrintWriter printWriter = response.getWriter();
/* 72:72 */      printWriter.write(WebPBEConfigHtmlUtils.createNotInitializedHtml());
/* 73:73 */      printWriter.flush();
/* 74:   */    }
/* 75:   */  }
/* 76:   */  
/* 77:   */  public void init(FilterConfig filterConfig)
/* 78:   */    throws ServletException
/* 79:   */  {}
/* 80:   */  
/* 81:   */  public void destroy() {}
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigFilter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */