/*   1:    */package org.apache.commons.logging.impl;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.lang.reflect.InvocationTargetException;
/*   5:    */import java.lang.reflect.Method;
/*   6:    */import javax.servlet.ServletContextEvent;
/*   7:    */import javax.servlet.ServletContextListener;
/*   8:    */import org.apache.commons.logging.LogFactory;
/*   9:    */
/*  51:    */public class ServletContextCleaner
/*  52:    */  implements ServletContextListener
/*  53:    */{
/*  54: 54 */  private Class[] RELEASE_SIGNATURE = { ClassLoader.class };
/*  55:    */  
/*  60:    */  public void contextDestroyed(ServletContextEvent sce)
/*  61:    */  {
/*  62: 62 */    ClassLoader tccl = Thread.currentThread().getContextClassLoader();
/*  63:    */    
/*  64: 64 */    Object[] params = new Object[1];
/*  65: 65 */    params[0] = tccl;
/*  66:    */    
/*  97: 97 */    ClassLoader loader = tccl;
/*  98: 98 */    while (loader != null)
/*  99:    */    {
/* 100:    */      try
/* 101:    */      {
/* 103:103 */        Class logFactoryClass = loader.loadClass("org.apache.commons.logging.LogFactory");
/* 104:104 */        Method releaseMethod = logFactoryClass.getMethod("release", this.RELEASE_SIGNATURE);
/* 105:105 */        releaseMethod.invoke(null, params);
/* 106:106 */        loader = logFactoryClass.getClassLoader().getParent();
/* 107:    */      }
/* 108:    */      catch (ClassNotFoundException ex)
/* 109:    */      {
/* 110:110 */        loader = null;
/* 111:    */      }
/* 112:    */      catch (NoSuchMethodException ex) {
/* 113:113 */        System.err.println("LogFactory instance found which does not support release method!");
/* 114:114 */        loader = null;
/* 115:    */      }
/* 116:    */      catch (IllegalAccessException ex) {
/* 117:117 */        System.err.println("LogFactory instance found which is not accessable!");
/* 118:118 */        loader = null;
/* 119:    */      }
/* 120:    */      catch (InvocationTargetException ex) {
/* 121:121 */        System.err.println("LogFactory instance release method failed!");
/* 122:122 */        loader = null;
/* 123:    */      }
/* 124:    */    }
/* 125:    */    
/* 129:129 */    LogFactory.release(tccl);
/* 130:    */  }
/* 131:    */  
/* 132:    */  public void contextInitialized(ServletContextEvent sce) {}
/* 133:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.ServletContextCleaner
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */