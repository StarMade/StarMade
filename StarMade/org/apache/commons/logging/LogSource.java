package org.apache.commons.logging;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Set;
import org.apache.commons.logging.impl.NoOpLog;

/**
 * @deprecated
 */
public class LogSource
{
  protected static Hashtable logs = new Hashtable();
  protected static boolean log4jIsAvailable = false;
  protected static boolean jdk14IsAvailable = false;
  protected static Constructor logImplctor = null;
  
  public static void setLogImplementation(String classname)
    throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException
  {
    try
    {
      Class logclass = Class.forName(classname);
      Class[] argtypes = new Class[1];
      argtypes[0] = "".getClass();
      logImplctor = logclass.getConstructor(argtypes);
    }
    catch (Throwable logclass)
    {
      logImplctor = null;
    }
  }
  
  public static void setLogImplementation(Class logclass)
    throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException
  {
    Class[] argtypes = new Class[1];
    argtypes[0] = "".getClass();
    logImplctor = logclass.getConstructor(argtypes);
  }
  
  public static Log getInstance(String name)
  {
    Log log = (Log)logs.get(name);
    if (null == log)
    {
      log = makeNewLogInstance(name);
      logs.put(name, log);
    }
    return log;
  }
  
  public static Log getInstance(Class clazz)
  {
    return getInstance(clazz.getName());
  }
  
  public static Log makeNewLogInstance(String name)
  {
    Log log = null;
    try
    {
      Object[] args = new Object[1];
      args[0] = name;
      log = (Log)logImplctor.newInstance(args);
    }
    catch (Throwable args)
    {
      log = null;
    }
    if (null == log) {
      log = new NoOpLog(name);
    }
    return log;
  }
  
  public static String[] getLogNames()
  {
    return (String[])logs.keySet().toArray(new String[logs.size()]);
  }
  
  static
  {
    try
    {
      if (null != Class.forName("org.apache.log4j.Logger")) {
        log4jIsAvailable = true;
      } else {
        log4jIsAvailable = false;
      }
    }
    catch (Throwable local_t)
    {
      log4jIsAvailable = false;
    }
    try
    {
      if ((null != Class.forName("java.util.logging.Logger")) && (null != Class.forName("org.apache.commons.logging.impl.Jdk14Logger"))) {
        jdk14IsAvailable = true;
      } else {
        jdk14IsAvailable = false;
      }
    }
    catch (Throwable local_t)
    {
      jdk14IsAvailable = false;
    }
    String local_t = null;
    try
    {
      local_t = System.getProperty("org.apache.commons.logging.log");
      if (local_t == null) {
        local_t = System.getProperty("org.apache.commons.logging.Log");
      }
    }
    catch (Throwable local_t1) {}
    if (local_t != null) {
      try
      {
        setLogImplementation(local_t);
      }
      catch (Throwable local_t1)
      {
        try
        {
          setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
        }
        catch (Throwable local_u) {}
      }
    } else {
      try
      {
        if (log4jIsAvailable) {
          setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
        } else if (jdk14IsAvailable) {
          setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
        } else {
          setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
        }
      }
      catch (Throwable local_t1)
      {
        try
        {
          setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
        }
        catch (Throwable local_u) {}
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.logging.LogSource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */