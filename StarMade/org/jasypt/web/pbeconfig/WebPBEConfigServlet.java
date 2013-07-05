/*     */ package org.jasypt.web.pbeconfig;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.encryption.pbe.config.WebPBEConfig;
/*     */ 
/*     */ public final class WebPBEConfigServlet extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = -7201635392816652667L;
/*     */ 
/*     */   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
/*     */     throws ServletException, IOException
/*     */   {
/*  93 */     execute(req, resp);
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*     */   {
/*  98 */     execute(req, resp);
/*     */   }
/*     */ 
/*     */   private void execute(HttpServletRequest req, HttpServletResponse resp)
/*     */     throws ServletException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 106 */       WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
/*     */ 
/* 108 */       if (registry.isWebConfigurationDone())
/*     */       {
/* 111 */         writeResponse(WebPBEConfigHtmlUtils.createConfigurationDoneHtml(), resp);
/*     */       }
/*     */       else
/*     */       {
/* 117 */         String settingFlag = req.getParameter("jasyptPwSetting");
/*     */ 
/* 119 */         if (CommonUtils.isEmpty(settingFlag))
/*     */         {
/* 122 */           writeResponse(WebPBEConfigHtmlUtils.createInputFormHtml(req, false), resp);
/*     */         }
/*     */         else
/*     */         {
/* 133 */           List configs = registry.getConfigs();
/* 134 */           Iterator configsIter = configs.iterator();
/* 135 */           int i = 0;
/* 136 */           int valid = 0;
/* 137 */           while (configsIter.hasNext())
/*     */           {
/* 139 */             WebPBEConfig config = (WebPBEConfig)configsIter.next();
/*     */ 
/* 141 */             String validation = req.getParameter("jasyptVa" + i);
/*     */ 
/* 143 */             String password = req.getParameter("jasyptPw" + i);
/*     */ 
/* 145 */             String retypedPassword = req.getParameter("jasyptRPw" + i);
/*     */ 
/* 148 */             if ((!CommonUtils.isEmpty(validation)) && (!CommonUtils.isEmpty(password)) && (password.equals(retypedPassword)) && (config.getValidationWord().equals(validation)))
/*     */             {
/* 157 */               valid++;
/*     */             }
/*     */ 
/* 160 */             i++;
/*     */           }
/*     */ 
/* 164 */           SimpleDateFormat dateFormat = new SimpleDateFormat();
/* 165 */           Calendar now = Calendar.getInstance();
/*     */ 
/* 167 */           if (valid < configs.size())
/*     */           {
/* 173 */             getServletContext().log("Failed attempt to set PBE Configuration from " + req.getRemoteAddr() + " [" + dateFormat.format(now.getTime()) + "]");
/*     */ 
/* 178 */             writeResponse(WebPBEConfigHtmlUtils.createInputFormHtml(req, true), resp);
/*     */           }
/*     */           else
/*     */           {
/* 188 */             configsIter = configs.iterator();
/* 189 */             i = 0;
/* 190 */             while (configsIter.hasNext()) {
/* 191 */               WebPBEConfig config = (WebPBEConfig)configsIter.next();
/* 192 */               String password = req.getParameter("jasyptPw" + i);
/*     */ 
/* 194 */               config.setPassword(password);
/* 195 */               i++;
/*     */             }
/*     */ 
/* 198 */             registry.setWebConfigurationDone(true);
/*     */ 
/* 200 */             getServletContext().log("PBE Configuration succesfully set from " + req.getRemoteAddr() + " [" + dateFormat.format(now.getTime()) + "]");
/*     */ 
/* 205 */             writeResponse(WebPBEConfigHtmlUtils.createConfigurationDoneHtml(), resp);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 216 */       getServletContext().log("Exception raised during servlet execution", e);
/*     */ 
/* 218 */       throw e;
/*     */     } catch (Throwable t) {
/* 220 */       getServletContext().log("Exception raised during servlet execution", t);
/*     */ 
/* 222 */       throw new ServletException(t);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void writeResponse(String html, HttpServletResponse response)
/*     */     throws IOException
/*     */   {
/* 231 */     PrintWriter printWriter = response.getWriter();
/* 232 */     printWriter.write(html);
/* 233 */     printWriter.flush();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigServlet
 * JD-Core Version:    0.6.2
 */