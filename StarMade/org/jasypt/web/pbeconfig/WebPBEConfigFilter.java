/*    */ package org.jasypt.web.pbeconfig;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ 
/*    */ public final class WebPBEConfigFilter
/*    */   implements Filter
/*    */ {
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 65 */     WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
/* 66 */     if (registry.isWebConfigurationDone())
/*    */     {
/* 68 */       chain.doFilter(request, response);
/*    */     }
/*    */     else {
/* 71 */       PrintWriter printWriter = response.getWriter();
/* 72 */       printWriter.write(WebPBEConfigHtmlUtils.createNotInitializedHtml());
/* 73 */       printWriter.flush();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void init(FilterConfig filterConfig)
/*    */     throws ServletException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigFilter
 * JD-Core Version:    0.6.2
 */