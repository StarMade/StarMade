/*   1:    */package org.jasypt.web.pbeconfig;
/*   2:    */
/*   3:    */import javax.servlet.ServletContext;
/*   4:    */import javax.servlet.ServletContextEvent;
/*   5:    */import javax.servlet.ServletContextListener;
/*   6:    */import org.jasypt.commons.CommonUtils;
/*   7:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*   8:    */
/*  69:    */public final class WebPBEInitializationContextListener
/*  70:    */  implements ServletContextListener
/*  71:    */{
/*  72:    */  public static final String INIT_PARAM_INITIALIZER_CLASS_NAME = "webPBEInitializerClassName";
/*  73:    */  
/*  74:    */  public void contextDestroyed(ServletContextEvent sce) {}
/*  75:    */  
/*  76:    */  public void contextInitialized(ServletContextEvent sce)
/*  77:    */  {
/*  78: 78 */    String className = sce.getServletContext().getInitParameter("webPBEInitializerClassName");
/*  79:    */    
/*  82: 82 */    if (CommonUtils.isEmpty(className)) {
/*  83: 83 */      throw new EncryptionInitializationException("webPBEInitializerClassName context initialization parameter not set in web.xml");
/*  84:    */    }
/*  85:    */    
/*  88: 88 */    Class initializerClass = null;
/*  89:    */    try {
/*  90: 90 */      initializerClass = Thread.currentThread().getContextClassLoader().loadClass(className);
/*  91:    */    }
/*  92:    */    catch (ClassNotFoundException e) {
/*  93: 93 */      throw new EncryptionInitializationException(e);
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    if (!WebPBEInitializer.class.isAssignableFrom(initializerClass)) {
/*  97: 97 */      throw new EncryptionInitializationException("Class " + className + " does not implement interface " + WebPBEInitializer.class.getName());
/*  98:    */    }
/*  99:    */    
/* 102:102 */    WebPBEInitializer initializer = null;
/* 103:    */    try {
/* 104:104 */      initializer = (WebPBEInitializer)initializerClass.newInstance();
/* 105:    */    }
/* 106:    */    catch (InstantiationException e) {
/* 107:107 */      throw new EncryptionInitializationException(e);
/* 108:    */    } catch (IllegalAccessException e) {
/* 109:109 */      throw new EncryptionInitializationException(e);
/* 110:    */    }
/* 111:    */    
/* 113:113 */    initializer.initializeWebPBEConfigs();
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEInitializationContextListener
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */