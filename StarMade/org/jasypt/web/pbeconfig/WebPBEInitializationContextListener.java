/*     */ package org.jasypt.web.pbeconfig;
/*     */ 
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.ServletContextEvent;
/*     */ import javax.servlet.ServletContextListener;
/*     */ import org.jasypt.commons.CommonUtils;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ 
/*     */ public final class WebPBEInitializationContextListener
/*     */   implements ServletContextListener
/*     */ {
/*     */   public static final String INIT_PARAM_INITIALIZER_CLASS_NAME = "webPBEInitializerClassName";
/*     */ 
/*     */   public void contextDestroyed(ServletContextEvent sce)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void contextInitialized(ServletContextEvent sce)
/*     */   {
/*  78 */     String className = sce.getServletContext().getInitParameter("webPBEInitializerClassName");
/*     */ 
/*  82 */     if (CommonUtils.isEmpty(className)) {
/*  83 */       throw new EncryptionInitializationException("webPBEInitializerClassName context initialization parameter not set in web.xml");
/*     */     }
/*     */ 
/*  88 */     Class initializerClass = null;
/*     */     try {
/*  90 */       initializerClass = Thread.currentThread().getContextClassLoader().loadClass(className);
/*     */     }
/*     */     catch (ClassNotFoundException e) {
/*  93 */       throw new EncryptionInitializationException(e);
/*     */     }
/*     */ 
/*  96 */     if (!WebPBEInitializer.class.isAssignableFrom(initializerClass)) {
/*  97 */       throw new EncryptionInitializationException("Class " + className + " does not implement interface " + WebPBEInitializer.class.getName());
/*     */     }
/*     */ 
/* 102 */     WebPBEInitializer initializer = null;
/*     */     try {
/* 104 */       initializer = (WebPBEInitializer)initializerClass.newInstance();
/*     */     }
/*     */     catch (InstantiationException e) {
/* 107 */       throw new EncryptionInitializationException(e);
/*     */     } catch (IllegalAccessException e) {
/* 109 */       throw new EncryptionInitializationException(e);
/*     */     }
/*     */ 
/* 113 */     initializer.initializeWebPBEConfigs();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEInitializationContextListener
 * JD-Core Version:    0.6.2
 */