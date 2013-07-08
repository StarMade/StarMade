package org.jasypt.web.pbeconfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.jasypt.commons.CommonUtils;
import org.jasypt.exceptions.EncryptionInitializationException;

public final class WebPBEInitializationContextListener
  implements ServletContextListener
{
  public static final String INIT_PARAM_INITIALIZER_CLASS_NAME = "webPBEInitializerClassName";
  
  public void contextDestroyed(ServletContextEvent sce) {}
  
  public void contextInitialized(ServletContextEvent sce)
  {
    String className = sce.getServletContext().getInitParameter("webPBEInitializerClassName");
    if (CommonUtils.isEmpty(className)) {
      throw new EncryptionInitializationException("webPBEInitializerClassName context initialization parameter not set in web.xml");
    }
    Class initializerClass = null;
    try
    {
      initializerClass = Thread.currentThread().getContextClassLoader().loadClass(className);
    }
    catch (ClassNotFoundException local_e)
    {
      throw new EncryptionInitializationException(local_e);
    }
    if (!WebPBEInitializer.class.isAssignableFrom(initializerClass)) {
      throw new EncryptionInitializationException("Class " + className + " does not implement interface " + WebPBEInitializer.class.getName());
    }
    WebPBEInitializer local_e = null;
    try
    {
      local_e = (WebPBEInitializer)initializerClass.newInstance();
    }
    catch (InstantiationException local_e1)
    {
      throw new EncryptionInitializationException(local_e1);
    }
    catch (IllegalAccessException local_e1)
    {
      throw new EncryptionInitializationException(local_e1);
    }
    local_e.initializeWebPBEConfigs();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEInitializationContextListener
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */