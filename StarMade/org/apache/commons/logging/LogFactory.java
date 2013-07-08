package org.apache.commons.logging;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public abstract class LogFactory
{
  public static final String PRIORITY_KEY = "priority";
  public static final String TCCL_KEY = "use_tccl";
  public static final String FACTORY_PROPERTY = "org.apache.commons.logging.LogFactory";
  public static final String FACTORY_DEFAULT = "org.apache.commons.logging.impl.LogFactoryImpl";
  public static final String FACTORY_PROPERTIES = "commons-logging.properties";
  protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
  public static final String DIAGNOSTICS_DEST_PROPERTY = "org.apache.commons.logging.diagnostics.dest";
  private static PrintStream diagnosticsStream = null;
  private static String diagnosticPrefix;
  public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "org.apache.commons.logging.LogFactory.HashtableImpl";
  private static final String WEAK_HASHTABLE_CLASSNAME = "org.apache.commons.logging.impl.WeakHashtable";
  private static ClassLoader thisClassLoader;
  protected static Hashtable factories = null;
  protected static LogFactory nullClassLoaderFactory = null;
  
  public abstract Object getAttribute(String paramString);
  
  public abstract String[] getAttributeNames();
  
  public abstract Log getInstance(Class paramClass)
    throws LogConfigurationException;
  
  public abstract Log getInstance(String paramString)
    throws LogConfigurationException;
  
  public abstract void release();
  
  public abstract void removeAttribute(String paramString);
  
  public abstract void setAttribute(String paramString, Object paramObject);
  
  private static final Hashtable createFactoryStore()
  {
    Hashtable result = null;
    String storeImplementationClass;
    try
    {
      storeImplementationClass = getSystemProperty("org.apache.commons.logging.LogFactory.HashtableImpl", null);
    }
    catch (SecurityException local_ex)
    {
      String storeImplementationClass;
      storeImplementationClass = null;
    }
    if (storeImplementationClass == null) {
      storeImplementationClass = "org.apache.commons.logging.impl.WeakHashtable";
    }
    try
    {
      Class local_ex = Class.forName(storeImplementationClass);
      result = (Hashtable)local_ex.newInstance();
    }
    catch (Throwable local_ex)
    {
      if (!"org.apache.commons.logging.impl.WeakHashtable".equals(storeImplementationClass)) {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
        } else {
          System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
        }
      }
    }
    if (result == null) {
      result = new Hashtable();
    }
    return result;
  }
  
  private static String trim(String src)
  {
    if (src == null) {
      return null;
    }
    return src.trim();
  }
  
  public static LogFactory getFactory()
    throws LogConfigurationException
  {
    ClassLoader contextClassLoader = getContextClassLoaderInternal();
    if ((contextClassLoader == null) && (isDiagnosticsEnabled())) {
      logDiagnostic("Context classloader is null.");
    }
    LogFactory factory = getCachedFactory(contextClassLoader);
    if (factory != null) {
      return factory;
    }
    if (isDiagnosticsEnabled())
    {
      logDiagnostic("[LOOKUP] LogFactory implementation requested for the first time for context classloader " + objectId(contextClassLoader));
      logHierarchy("[LOOKUP] ", contextClassLoader);
    }
    Properties props = getConfigurationFile(contextClassLoader, "commons-logging.properties");
    ClassLoader baseClassLoader = contextClassLoader;
    if (props != null)
    {
      String useTCCLStr = props.getProperty("use_tccl");
      if ((useTCCLStr != null) && (!Boolean.valueOf(useTCCLStr).booleanValue())) {
        baseClassLoader = thisClassLoader;
      }
    }
    if (isDiagnosticsEnabled()) {
      logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
    }
    try
    {
      String useTCCLStr = getSystemProperty("org.apache.commons.logging.LogFactory", null);
      if (useTCCLStr != null)
      {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("[LOOKUP] Creating an instance of LogFactory class '" + useTCCLStr + "' as specified by system property " + "org.apache.commons.logging.LogFactory");
        }
        factory = newFactory(useTCCLStr, baseClassLoader, contextClassLoader);
      }
      else if (isDiagnosticsEnabled())
      {
        logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
      }
    }
    catch (SecurityException useTCCLStr)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(useTCCLStr.getMessage()) + "]. Trying alternative implementations...");
      }
    }
    catch (RuntimeException useTCCLStr)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [" + trim(useTCCLStr.getMessage()) + "] as specified by a system property.");
      }
      throw useTCCLStr;
    }
    if (factory == null)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
      }
      try
      {
        InputStream useTCCLStr = getResourceAsStream(contextClassLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
        if (useTCCLStr != null)
        {
          BufferedReader local_rd;
          try
          {
            local_rd = new BufferedReader(new InputStreamReader(useTCCLStr, "UTF-8"));
          }
          catch (UnsupportedEncodingException local_e)
          {
            BufferedReader local_rd;
            local_rd = new BufferedReader(new InputStreamReader(useTCCLStr));
          }
          String local_e = local_rd.readLine();
          local_rd.close();
          if ((local_e != null) && (!"".equals(local_e)))
          {
            if (isDiagnosticsEnabled()) {
              logDiagnostic("[LOOKUP]  Creating an instance of LogFactory class " + local_e + " as specified by file '" + "META-INF/services/org.apache.commons.logging.LogFactory" + "' which was present in the path of the context" + " classloader.");
            }
            factory = newFactory(local_e, baseClassLoader, contextClassLoader);
          }
        }
        else if (isDiagnosticsEnabled())
        {
          logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
        }
      }
      catch (Exception useTCCLStr)
      {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(useTCCLStr.getMessage()) + "]. Trying alternative implementations...");
        }
      }
    }
    if (factory == null) {
      if (props != null)
      {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
        }
        String useTCCLStr = props.getProperty("org.apache.commons.logging.LogFactory");
        if (useTCCLStr != null)
        {
          if (isDiagnosticsEnabled()) {
            logDiagnostic("[LOOKUP] Properties file specifies LogFactory subclass '" + useTCCLStr + "'");
          }
          factory = newFactory(useTCCLStr, baseClassLoader, contextClassLoader);
        }
        else if (isDiagnosticsEnabled())
        {
          logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
        }
      }
      else if (isDiagnosticsEnabled())
      {
        logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
      }
    }
    if (factory == null)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader).");
      }
      factory = newFactory("org.apache.commons.logging.impl.LogFactoryImpl", thisClassLoader, contextClassLoader);
    }
    if (factory != null)
    {
      cacheFactory(contextClassLoader, factory);
      if (props != null)
      {
        Enumeration useTCCLStr = props.propertyNames();
        while (useTCCLStr.hasMoreElements())
        {
          String local_rd = (String)useTCCLStr.nextElement();
          String local_e = props.getProperty(local_rd);
          factory.setAttribute(local_rd, local_e);
        }
      }
    }
    return factory;
  }
  
  public static Log getLog(Class clazz)
    throws LogConfigurationException
  {
    return getFactory().getInstance(clazz);
  }
  
  public static Log getLog(String name)
    throws LogConfigurationException
  {
    return getFactory().getInstance(name);
  }
  
  public static void release(ClassLoader classLoader)
  {
    if (isDiagnosticsEnabled()) {
      logDiagnostic("Releasing factory for classloader " + objectId(classLoader));
    }
    synchronized (factories)
    {
      if (classLoader == null)
      {
        if (nullClassLoaderFactory != null)
        {
          nullClassLoaderFactory.release();
          nullClassLoaderFactory = null;
        }
      }
      else
      {
        LogFactory factory = (LogFactory)factories.get(classLoader);
        if (factory != null)
        {
          factory.release();
          factories.remove(classLoader);
        }
      }
    }
  }
  
  public static void releaseAll()
  {
    if (isDiagnosticsEnabled()) {
      logDiagnostic("Releasing factory for all classloaders.");
    }
    synchronized (factories)
    {
      Enumeration elements = factories.elements();
      while (elements.hasMoreElements())
      {
        LogFactory element = (LogFactory)elements.nextElement();
        element.release();
      }
      factories.clear();
      if (nullClassLoaderFactory != null)
      {
        nullClassLoaderFactory.release();
        nullClassLoaderFactory = null;
      }
    }
  }
  
  protected static ClassLoader getClassLoader(Class clazz)
  {
    try
    {
      return clazz.getClassLoader();
    }
    catch (SecurityException local_ex)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("Unable to get classloader for class '" + clazz + "' due to security restrictions - " + local_ex.getMessage());
      }
      throw local_ex;
    }
  }
  
  protected static ClassLoader getContextClassLoader()
    throws LogConfigurationException
  {
    return directGetContextClassLoader();
  }
  
  private static ClassLoader getContextClassLoaderInternal()
    throws LogConfigurationException
  {
    (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        return LogFactory.directGetContextClassLoader();
      }
    });
  }
  
  protected static ClassLoader directGetContextClassLoader()
    throws LogConfigurationException
  {
    ClassLoader classLoader = null;
    try
    {
      Method method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
      try
      {
        classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
      }
      catch (IllegalAccessException local_e)
      {
        throw new LogConfigurationException("Unexpected IllegalAccessException", local_e);
      }
      catch (InvocationTargetException local_e)
      {
        if (!(local_e.getTargetException() instanceof SecurityException)) {
          throw new LogConfigurationException("Unexpected InvocationTargetException", local_e.getTargetException());
        }
      }
    }
    catch (NoSuchMethodException method)
    {
      classLoader = getClassLoader(LogFactory.class);
    }
    return classLoader;
  }
  
  private static LogFactory getCachedFactory(ClassLoader contextClassLoader)
  {
    LogFactory factory = null;
    if (contextClassLoader == null) {
      factory = nullClassLoaderFactory;
    } else {
      factory = (LogFactory)factories.get(contextClassLoader);
    }
    return factory;
  }
  
  private static void cacheFactory(ClassLoader classLoader, LogFactory factory)
  {
    if (factory != null) {
      if (classLoader == null) {
        nullClassLoaderFactory = factory;
      } else {
        factories.put(classLoader, factory);
      }
    }
  }
  
  protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader, ClassLoader contextClassLoader)
    throws LogConfigurationException
  {
    Object result = AccessController.doPrivileged(new PrivilegedAction()
    {
      private final String val$factoryClass;
      private final ClassLoader val$classLoader;
      
      public Object run()
      {
        return LogFactory.createFactory(this.val$factoryClass, this.val$classLoader);
      }
    });
    if ((result instanceof LogConfigurationException))
    {
      LogConfigurationException local_ex = (LogConfigurationException)result;
      if (isDiagnosticsEnabled()) {
        logDiagnostic("An error occurred while loading the factory class:" + local_ex.getMessage());
      }
      throw local_ex;
    }
    if (isDiagnosticsEnabled()) {
      logDiagnostic("Created object " + objectId(result) + " to manage classloader " + objectId(contextClassLoader));
    }
    return (LogFactory)result;
  }
  
  protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader)
  {
    return newFactory(factoryClass, classLoader, null);
  }
  
  protected static Object createFactory(String factoryClass, ClassLoader classLoader)
  {
    Class logFactoryClass = null;
    try
    {
      if (classLoader != null) {
        try
        {
          logFactoryClass = classLoader.loadClass(factoryClass);
          if (LogFactory.class.isAssignableFrom(logFactoryClass))
          {
            if (isDiagnosticsEnabled()) {
              logDiagnostic("Loaded class " + logFactoryClass.getName() + " from classloader " + objectId(classLoader));
            }
          }
          else if (isDiagnosticsEnabled())
          {
            logDiagnostic("Factory class " + logFactoryClass.getName() + " loaded from classloader " + objectId(logFactoryClass.getClassLoader()) + " does not extend '" + LogFactory.class.getName() + "' as loaded by this classloader.");
            logHierarchy("[BAD CL TREE] ", classLoader);
          }
          return (LogFactory)logFactoryClass.newInstance();
        }
        catch (ClassNotFoundException local_ex)
        {
          if (classLoader == thisClassLoader)
          {
            if (isDiagnosticsEnabled()) {
              logDiagnostic("Unable to locate any class called '" + factoryClass + "' via classloader " + objectId(classLoader));
            }
            throw local_ex;
          }
        }
        catch (NoClassDefFoundError local_ex)
        {
          if (classLoader == thisClassLoader)
          {
            if (isDiagnosticsEnabled()) {
              logDiagnostic("Class '" + factoryClass + "' cannot be loaded" + " via classloader " + objectId(classLoader) + " - it depends on some other class that cannot" + " be found.");
            }
            throw local_ex;
          }
        }
        catch (ClassCastException local_ex)
        {
          if (classLoader == thisClassLoader)
          {
            boolean implementsLogFactory = implementsLogFactory(logFactoryClass);
            String msg = "The application has specified that a custom LogFactory implementation should be used but Class '" + factoryClass + "' cannot be converted to '" + LogFactory.class.getName() + "'. ";
            if (implementsLogFactory) {
              msg = msg + "The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. " + "Background can be found in http://commons.apache.org/logging/tech.html. " + "If you have not explicitly specified a custom LogFactory then it is likely that " + "the container has set one without your knowledge. " + "In this case, consider using the commons-logging-adapters.jar file or " + "specifying the standard LogFactory from the command line. ";
            } else {
              msg = msg + "Please check the custom implementation. ";
            }
            msg = msg + "Help can be found @http://commons.apache.org/logging/troubleshooting.html.";
            if (isDiagnosticsEnabled()) {
              logDiagnostic(msg);
            }
            ClassCastException local_ex1 = new ClassCastException(msg);
            throw local_ex1;
          }
        }
      }
      if (isDiagnosticsEnabled()) {
        logDiagnostic("Unable to load factory class via classloader " + objectId(classLoader) + " - trying the classloader associated with this LogFactory.");
      }
      logFactoryClass = Class.forName(factoryClass);
      return (LogFactory)logFactoryClass.newInstance();
    }
    catch (Exception local_ex)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("Unable to create LogFactory instance.");
      }
      if ((logFactoryClass != null) && (!LogFactory.class.isAssignableFrom(logFactoryClass))) {
        return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", local_ex);
      }
      return new LogConfigurationException(local_ex);
    }
  }
  
  private static boolean implementsLogFactory(Class logFactoryClass)
  {
    boolean implementsLogFactory = false;
    if (logFactoryClass != null) {
      try
      {
        ClassLoader logFactoryClassLoader = logFactoryClass.getClassLoader();
        if (logFactoryClassLoader == null)
        {
          logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
        }
        else
        {
          logHierarchy("[CUSTOM LOG FACTORY] ", logFactoryClassLoader);
          Class factoryFromCustomLoader = Class.forName("org.apache.commons.logging.LogFactory", false, logFactoryClassLoader);
          implementsLogFactory = factoryFromCustomLoader.isAssignableFrom(logFactoryClass);
          if (implementsLogFactory) {
            logDiagnostic("[CUSTOM LOG FACTORY] " + logFactoryClass.getName() + " implements LogFactory but was loaded by an incompatible classloader.");
          } else {
            logDiagnostic("[CUSTOM LOG FACTORY] " + logFactoryClass.getName() + " does not implement LogFactory.");
          }
        }
      }
      catch (SecurityException logFactoryClassLoader)
      {
        logDiagnostic("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + logFactoryClassLoader.getMessage());
      }
      catch (LinkageError logFactoryClassLoader)
      {
        logDiagnostic("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + logFactoryClassLoader.getMessage());
      }
      catch (ClassNotFoundException logFactoryClassLoader)
      {
        logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
      }
    }
    return implementsLogFactory;
  }
  
  private static InputStream getResourceAsStream(ClassLoader loader, String name)
  {
    (InputStream)AccessController.doPrivileged(new PrivilegedAction()
    {
      private final ClassLoader val$loader;
      private final String val$name;
      
      public Object run()
      {
        if (this.val$loader != null) {
          return this.val$loader.getResourceAsStream(this.val$name);
        }
        return ClassLoader.getSystemResourceAsStream(this.val$name);
      }
    });
  }
  
  private static Enumeration getResources(ClassLoader loader, String name)
  {
    PrivilegedAction action = new PrivilegedAction()
    {
      private final ClassLoader val$loader;
      private final String val$name;
      
      public Object run()
      {
        try
        {
          if (this.val$loader != null) {
            return this.val$loader.getResources(this.val$name);
          }
          return ClassLoader.getSystemResources(this.val$name);
        }
        catch (IOException local_e)
        {
          if (LogFactory.isDiagnosticsEnabled()) {
            LogFactory.logDiagnostic("Exception while trying to find configuration file " + this.val$name + ":" + local_e.getMessage());
          }
          return null;
        }
        catch (NoSuchMethodError local_e) {}
        return null;
      }
    };
    Object result = AccessController.doPrivileged(action);
    return (Enumeration)result;
  }
  
  private static Properties getProperties(URL url)
  {
    PrivilegedAction action = new PrivilegedAction()
    {
      private final URL val$url;
      
      public Object run()
      {
        try
        {
          InputStream stream = this.val$url.openStream();
          if (stream != null)
          {
            Properties props = new Properties();
            props.load(stream);
            stream.close();
            return props;
          }
        }
        catch (IOException stream)
        {
          if (LogFactory.isDiagnosticsEnabled()) {
            LogFactory.logDiagnostic("Unable to read URL " + this.val$url);
          }
        }
        return null;
      }
    };
    return (Properties)AccessController.doPrivileged(action);
  }
  
  private static final Properties getConfigurationFile(ClassLoader classLoader, String fileName)
  {
    Properties props = null;
    double priority = 0.0D;
    URL propsUrl = null;
    try
    {
      Enumeration urls = getResources(classLoader, fileName);
      if (urls == null) {
        return null;
      }
      while (urls.hasMoreElements())
      {
        URL url = (URL)urls.nextElement();
        Properties newProps = getProperties(url);
        if (newProps != null) {
          if (props == null)
          {
            propsUrl = url;
            props = newProps;
            String priorityStr = props.getProperty("priority");
            priority = 0.0D;
            if (priorityStr != null) {
              priority = Double.parseDouble(priorityStr);
            }
            if (isDiagnosticsEnabled()) {
              logDiagnostic("[LOOKUP] Properties file found at '" + url + "'" + " with priority " + priority);
            }
          }
          else
          {
            String priorityStr = newProps.getProperty("priority");
            double newPriority = 0.0D;
            if (priorityStr != null) {
              newPriority = Double.parseDouble(priorityStr);
            }
            if (newPriority > priority)
            {
              if (isDiagnosticsEnabled()) {
                logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " overrides file at '" + propsUrl + "'" + " with priority " + priority);
              }
              propsUrl = url;
              props = newProps;
              priority = newPriority;
            }
            else if (isDiagnosticsEnabled())
            {
              logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " does not override file at '" + propsUrl + "'" + " with priority " + priority);
            }
          }
        }
      }
    }
    catch (SecurityException urls)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("SecurityException thrown while trying to find/read config files.");
      }
    }
    if (isDiagnosticsEnabled()) {
      if (props == null) {
        logDiagnostic("[LOOKUP] No properties file of name '" + fileName + "' found.");
      } else {
        logDiagnostic("[LOOKUP] Properties file of name '" + fileName + "' found at '" + propsUrl + '"');
      }
    }
    return props;
  }
  
  private static String getSystemProperty(String key, String def)
    throws SecurityException
  {
    (String)AccessController.doPrivileged(new PrivilegedAction()
    {
      private final String val$key;
      private final String val$def;
      
      public Object run()
      {
        return System.getProperty(this.val$key, this.val$def);
      }
    });
  }
  
  private static void initDiagnostics()
  {
    try
    {
      String dest = getSystemProperty("org.apache.commons.logging.diagnostics.dest", null);
      if (dest == null) {
        return;
      }
    }
    catch (SecurityException local_ex)
    {
      return;
    }
    String dest;
    if (dest.equals("STDOUT")) {
      diagnosticsStream = System.out;
    } else if (dest.equals("STDERR")) {
      diagnosticsStream = System.err;
    } else {
      try
      {
        FileOutputStream local_ex = new FileOutputStream(dest, true);
        diagnosticsStream = new PrintStream(local_ex);
      }
      catch (IOException local_ex)
      {
        return;
      }
    }
    String local_ex;
    try
    {
      ClassLoader classLoader = thisClassLoader;
      String local_ex;
      if (thisClassLoader == null) {
        local_ex = "BOOTLOADER";
      } else {
        local_ex = objectId(classLoader);
      }
    }
    catch (SecurityException classLoader)
    {
      String local_ex;
      local_ex = "UNKNOWN";
    }
    diagnosticPrefix = "[LogFactory from " + local_ex + "] ";
  }
  
  protected static boolean isDiagnosticsEnabled()
  {
    return diagnosticsStream != null;
  }
  
  private static final void logDiagnostic(String msg)
  {
    if (diagnosticsStream != null)
    {
      diagnosticsStream.print(diagnosticPrefix);
      diagnosticsStream.println(msg);
      diagnosticsStream.flush();
    }
  }
  
  protected static final void logRawDiagnostic(String msg)
  {
    if (diagnosticsStream != null)
    {
      diagnosticsStream.println(msg);
      diagnosticsStream.flush();
    }
  }
  
  private static void logClassLoaderEnvironment(Class clazz)
  {
    if (!isDiagnosticsEnabled()) {
      return;
    }
    try
    {
      logDiagnostic("[ENV] Extension directories (java.ext.dir): " + System.getProperty("java.ext.dir"));
      logDiagnostic("[ENV] Application classpath (java.class.path): " + System.getProperty("java.class.path"));
    }
    catch (SecurityException local_ex)
    {
      logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
    }
    String local_ex = clazz.getName();
    try
    {
      classLoader = getClassLoader(clazz);
    }
    catch (SecurityException local_ex1)
    {
      ClassLoader classLoader;
      logDiagnostic("[ENV] Security forbids determining the classloader for " + local_ex);
      return;
    }
    ClassLoader classLoader;
    logDiagnostic("[ENV] Class " + local_ex + " was loaded via classloader " + objectId(classLoader));
    logHierarchy("[ENV] Ancestry of classloader which loaded " + local_ex + " is ", classLoader);
  }
  
  private static void logHierarchy(String prefix, ClassLoader classLoader)
  {
    if (!isDiagnosticsEnabled()) {
      return;
    }
    if (classLoader != null)
    {
      String classLoaderString = classLoader.toString();
      logDiagnostic(prefix + objectId(classLoader) + " == '" + classLoaderString + "'");
    }
    try
    {
      systemClassLoader = ClassLoader.getSystemClassLoader();
    }
    catch (SecurityException classLoaderString)
    {
      ClassLoader systemClassLoader;
      logDiagnostic(prefix + "Security forbids determining the system classloader.");
      return;
    }
    ClassLoader systemClassLoader;
    if (classLoader != null)
    {
      StringBuffer classLoaderString = new StringBuffer(prefix + "ClassLoader tree:");
      do
      {
        classLoaderString.append(objectId(classLoader));
        if (classLoader == systemClassLoader) {
          classLoaderString.append(" (SYSTEM) ");
        }
        try
        {
          classLoader = classLoader.getParent();
        }
        catch (SecurityException local_ex)
        {
          classLoaderString.append(" --> SECRET");
          break;
        }
        classLoaderString.append(" --> ");
      } while (classLoader != null);
      classLoaderString.append("BOOT");
      logDiagnostic(classLoaderString.toString());
    }
  }
  
  public static String objectId(Object local_o)
  {
    if (local_o == null) {
      return "null";
    }
    return local_o.getClass().getName() + "@" + System.identityHashCode(local_o);
  }
  
  static
  {
    thisClassLoader = getClassLoader(LogFactory.class);
    initDiagnostics();
    logClassLoaderEnvironment(LogFactory.class);
    factories = createFactoryStore();
    if (isDiagnosticsEnabled()) {
      logDiagnostic("BOOTSTRAP COMPLETED");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.logging.LogFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */