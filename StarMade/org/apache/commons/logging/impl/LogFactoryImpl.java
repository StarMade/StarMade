package org.apache.commons.logging.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

public class LogFactoryImpl
  extends LogFactory
{
  private static final String LOGGING_IMPL_LOG4J_LOGGER = "org.apache.commons.logging.impl.Log4JLogger";
  private static final String LOGGING_IMPL_JDK14_LOGGER = "org.apache.commons.logging.impl.Jdk14Logger";
  private static final String LOGGING_IMPL_LUMBERJACK_LOGGER = "org.apache.commons.logging.impl.Jdk13LumberjackLogger";
  private static final String LOGGING_IMPL_SIMPLE_LOGGER = "org.apache.commons.logging.impl.SimpleLog";
  private static final String PKG_IMPL = "org.apache.commons.logging.impl.";
  private static final int PKG_LEN = "org.apache.commons.logging.impl.".length();
  public static final String LOG_PROPERTY = "org.apache.commons.logging.Log";
  protected static final String LOG_PROPERTY_OLD = "org.apache.commons.logging.log";
  public static final String ALLOW_FLAWED_CONTEXT_PROPERTY = "org.apache.commons.logging.Log.allowFlawedContext";
  public static final String ALLOW_FLAWED_DISCOVERY_PROPERTY = "org.apache.commons.logging.Log.allowFlawedDiscovery";
  public static final String ALLOW_FLAWED_HIERARCHY_PROPERTY = "org.apache.commons.logging.Log.allowFlawedHierarchy";
  private static final String[] classesToDiscover = { "org.apache.commons.logging.impl.Log4JLogger", "org.apache.commons.logging.impl.Jdk14Logger", "org.apache.commons.logging.impl.Jdk13LumberjackLogger", "org.apache.commons.logging.impl.SimpleLog" };
  private boolean useTCCL = true;
  private String diagnosticPrefix;
  protected Hashtable attributes = new Hashtable();
  protected Hashtable instances = new Hashtable();
  private String logClassName;
  protected Constructor logConstructor = null;
  protected Class[] logConstructorSignature = { String.class };
  protected Method logMethod = null;
  protected Class[] logMethodSignature = { LogFactory.class };
  private boolean allowFlawedContext;
  private boolean allowFlawedDiscovery;
  private boolean allowFlawedHierarchy;
  
  public LogFactoryImpl()
  {
    initDiagnostics();
    if (isDiagnosticsEnabled()) {
      logDiagnostic("Instance created.");
    }
  }
  
  public Object getAttribute(String name)
  {
    return this.attributes.get(name);
  }
  
  public String[] getAttributeNames()
  {
    Vector names = new Vector();
    Enumeration keys = this.attributes.keys();
    while (keys.hasMoreElements()) {
      names.addElement((String)keys.nextElement());
    }
    String[] results = new String[names.size()];
    for (int local_i = 0; local_i < results.length; local_i++) {
      results[local_i] = ((String)names.elementAt(local_i));
    }
    return results;
  }
  
  public Log getInstance(Class clazz)
    throws LogConfigurationException
  {
    return getInstance(clazz.getName());
  }
  
  public Log getInstance(String name)
    throws LogConfigurationException
  {
    Log instance = (Log)this.instances.get(name);
    if (instance == null)
    {
      instance = newInstance(name);
      this.instances.put(name, instance);
    }
    return instance;
  }
  
  public void release()
  {
    logDiagnostic("Releasing all known loggers");
    this.instances.clear();
  }
  
  public void removeAttribute(String name)
  {
    this.attributes.remove(name);
  }
  
  public void setAttribute(String name, Object value)
  {
    if (this.logConstructor != null) {
      logDiagnostic("setAttribute: call too late; configuration already performed.");
    }
    if (value == null) {
      this.attributes.remove(name);
    } else {
      this.attributes.put(name, value);
    }
    if (name.equals("use_tccl")) {
      this.useTCCL = Boolean.valueOf(value.toString()).booleanValue();
    }
  }
  
  protected static ClassLoader getContextClassLoader()
    throws LogConfigurationException
  {
    return LogFactory.getContextClassLoader();
  }
  
  protected static boolean isDiagnosticsEnabled()
  {
    return LogFactory.isDiagnosticsEnabled();
  }
  
  protected static ClassLoader getClassLoader(Class clazz)
  {
    return LogFactory.getClassLoader(clazz);
  }
  
  private void initDiagnostics()
  {
    Class clazz = getClass();
    ClassLoader classLoader = getClassLoader(clazz);
    String classLoaderName;
    try
    {
      String classLoaderName;
      if (classLoader == null) {
        classLoaderName = "BOOTLOADER";
      } else {
        classLoaderName = LogFactory.objectId(classLoader);
      }
    }
    catch (SecurityException local_e)
    {
      String classLoaderName;
      classLoaderName = "UNKNOWN";
    }
    this.diagnosticPrefix = ("[LogFactoryImpl@" + System.identityHashCode(this) + " from " + classLoaderName + "] ");
  }
  
  protected void logDiagnostic(String msg)
  {
    if (isDiagnosticsEnabled()) {
      LogFactory.logRawDiagnostic(this.diagnosticPrefix + msg);
    }
  }
  
  /**
   * @deprecated
   */
  protected String getLogClassName()
  {
    if (this.logClassName == null) {
      discoverLogImplementation(getClass().getName());
    }
    return this.logClassName;
  }
  
  /**
   * @deprecated
   */
  protected Constructor getLogConstructor()
    throws LogConfigurationException
  {
    if (this.logConstructor == null) {
      discoverLogImplementation(getClass().getName());
    }
    return this.logConstructor;
  }
  
  /**
   * @deprecated
   */
  protected boolean isJdk13LumberjackAvailable()
  {
    return isLogLibraryAvailable("Jdk13Lumberjack", "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
  }
  
  /**
   * @deprecated
   */
  protected boolean isJdk14Available()
  {
    return isLogLibraryAvailable("Jdk14", "org.apache.commons.logging.impl.Jdk14Logger");
  }
  
  /**
   * @deprecated
   */
  protected boolean isLog4JAvailable()
  {
    return isLogLibraryAvailable("Log4J", "org.apache.commons.logging.impl.Log4JLogger");
  }
  
  protected Log newInstance(String name)
    throws LogConfigurationException
  {
    Log instance = null;
    try
    {
      if (this.logConstructor == null)
      {
        instance = discoverLogImplementation(name);
      }
      else
      {
        Object[] params = { name };
        instance = (Log)this.logConstructor.newInstance(params);
      }
      if (this.logMethod != null)
      {
        Object[] params = { this };
        this.logMethod.invoke(instance, params);
      }
      return instance;
    }
    catch (LogConfigurationException params)
    {
      throw params;
    }
    catch (InvocationTargetException params)
    {
      Throwable local_c = params.getTargetException();
      if (local_c != null) {
        throw new LogConfigurationException(local_c);
      }
      throw new LogConfigurationException(params);
    }
    catch (Throwable params)
    {
      throw new LogConfigurationException(params);
    }
  }
  
  private static ClassLoader getContextClassLoaderInternal()
    throws LogConfigurationException
  {
    (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        return LogFactoryImpl.access$000();
      }
    });
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
  
  private ClassLoader getParentClassLoader(ClassLoader local_cl)
  {
    try
    {
      (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
      {
        private final ClassLoader val$cl;
        
        public Object run()
        {
          return this.val$cl.getParent();
        }
      });
    }
    catch (SecurityException local_ex)
    {
      logDiagnostic("[SECURITY] Unable to obtain parent classloader");
    }
    return null;
  }
  
  private boolean isLogLibraryAvailable(String name, String classname)
  {
    if (isDiagnosticsEnabled()) {
      logDiagnostic("Checking for '" + name + "'.");
    }
    try
    {
      Log log = createLogFromClass(classname, getClass().getName(), false);
      if (log == null)
      {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("Did not find '" + name + "'.");
        }
        return false;
      }
      if (isDiagnosticsEnabled()) {
        logDiagnostic("Found '" + name + "'.");
      }
      return true;
    }
    catch (LogConfigurationException log)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("Logging system '" + name + "' is available but not useable.");
      }
    }
    return false;
  }
  
  private String getConfigurationValue(String property)
  {
    if (isDiagnosticsEnabled()) {
      logDiagnostic("[ENV] Trying to get configuration for item " + property);
    }
    Object valueObj = getAttribute(property);
    if (valueObj != null)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("[ENV] Found LogFactory attribute [" + valueObj + "] for " + property);
      }
      return valueObj.toString();
    }
    if (isDiagnosticsEnabled()) {
      logDiagnostic("[ENV] No LogFactory attribute found for " + property);
    }
    try
    {
      String value = getSystemProperty(property, null);
      if (value != null)
      {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("[ENV] Found system property [" + value + "] for " + property);
        }
        return value;
      }
      if (isDiagnosticsEnabled()) {
        logDiagnostic("[ENV] No system property found for property " + property);
      }
    }
    catch (SecurityException value)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("[ENV] Security prevented reading system property " + property);
      }
    }
    if (isDiagnosticsEnabled()) {
      logDiagnostic("[ENV] No configuration defined for item " + property);
    }
    return null;
  }
  
  private boolean getBooleanConfiguration(String key, boolean dflt)
  {
    String val = getConfigurationValue(key);
    if (val == null) {
      return dflt;
    }
    return Boolean.valueOf(val).booleanValue();
  }
  
  private void initConfiguration()
  {
    this.allowFlawedContext = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedContext", true);
    this.allowFlawedDiscovery = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedDiscovery", true);
    this.allowFlawedHierarchy = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedHierarchy", true);
  }
  
  private Log discoverLogImplementation(String logCategory)
    throws LogConfigurationException
  {
    if (isDiagnosticsEnabled()) {
      logDiagnostic("Discovering a Log implementation...");
    }
    initConfiguration();
    Log result = null;
    String specifiedLogClassName = findUserSpecifiedLogClassName();
    if (specifiedLogClassName != null)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("Attempting to load user-specified log class '" + specifiedLogClassName + "'...");
      }
      result = createLogFromClass(specifiedLogClassName, logCategory, true);
      if (result == null)
      {
        StringBuffer messageBuffer = new StringBuffer("User-specified log class '");
        messageBuffer.append(specifiedLogClassName);
        messageBuffer.append("' cannot be found or is not useable.");
        if (specifiedLogClassName != null)
        {
          informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.Log4JLogger");
          informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.Jdk14Logger");
          informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
          informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.SimpleLog");
        }
        throw new LogConfigurationException(messageBuffer.toString());
      }
      return result;
    }
    if (isDiagnosticsEnabled()) {
      logDiagnostic("No user-specified Log implementation; performing discovery using the standard supported logging implementations...");
    }
    for (int messageBuffer = 0; (messageBuffer < classesToDiscover.length) && (result == null); messageBuffer++) {
      result = createLogFromClass(classesToDiscover[messageBuffer], logCategory, true);
    }
    if (result == null) {
      throw new LogConfigurationException("No suitable Log implementation");
    }
    return result;
  }
  
  private void informUponSimilarName(StringBuffer messageBuffer, String name, String candidate)
  {
    if (name.equals(candidate)) {
      return;
    }
    if (name.regionMatches(true, 0, candidate, 0, PKG_LEN + 5))
    {
      messageBuffer.append(" Did you mean '");
      messageBuffer.append(candidate);
      messageBuffer.append("'?");
    }
  }
  
  private String findUserSpecifiedLogClassName()
  {
    if (isDiagnosticsEnabled()) {
      logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.Log'");
    }
    String specifiedClass = (String)getAttribute("org.apache.commons.logging.Log");
    if (specifiedClass == null)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.log'");
      }
      specifiedClass = (String)getAttribute("org.apache.commons.logging.log");
    }
    if (specifiedClass == null)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.Log'");
      }
      try
      {
        specifiedClass = getSystemProperty("org.apache.commons.logging.Log", null);
      }
      catch (SecurityException local_e)
      {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("No access allowed to system property 'org.apache.commons.logging.Log' - " + local_e.getMessage());
        }
      }
    }
    if (specifiedClass == null)
    {
      if (isDiagnosticsEnabled()) {
        logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.log'");
      }
      try
      {
        specifiedClass = getSystemProperty("org.apache.commons.logging.log", null);
      }
      catch (SecurityException local_e)
      {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("No access allowed to system property 'org.apache.commons.logging.log' - " + local_e.getMessage());
        }
      }
    }
    if (specifiedClass != null) {
      specifiedClass = specifiedClass.trim();
    }
    return specifiedClass;
  }
  
  private Log createLogFromClass(String logAdapterClassName, String logCategory, boolean affectState)
    throws LogConfigurationException
  {
    if (isDiagnosticsEnabled()) {
      logDiagnostic("Attempting to instantiate '" + logAdapterClassName + "'");
    }
    Object[] params = { logCategory };
    Log logAdapter = null;
    Constructor constructor = null;
    Class logAdapterClass = null;
    for (ClassLoader currentCL = getBaseClassLoader();; currentCL = getParentClassLoader(currentCL))
    {
      logDiagnostic("Trying to load '" + logAdapterClassName + "' from classloader " + LogFactory.objectId(currentCL));
      try
      {
        if (isDiagnosticsEnabled())
        {
          String resourceName = logAdapterClassName.replace('.', '/') + ".class";
          URL url;
          URL url;
          if (currentCL != null) {
            url = currentCL.getResource(resourceName);
          } else {
            url = ClassLoader.getSystemResource(resourceName + ".class");
          }
          if (url == null) {
            logDiagnostic("Class '" + logAdapterClassName + "' [" + resourceName + "] cannot be found.");
          } else {
            logDiagnostic("Class '" + logAdapterClassName + "' was found at '" + url + "'");
          }
        }
        Class url = null;
        try
        {
          url = Class.forName(logAdapterClassName, true, currentCL);
        }
        catch (ClassNotFoundException resourceName)
        {
          String msg = "" + resourceName.getMessage();
          logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via classloader " + LogFactory.objectId(currentCL) + ": " + msg.trim());
          try
          {
            url = Class.forName(logAdapterClassName);
          }
          catch (ClassNotFoundException secondaryClassNotFoundException)
          {
            msg = "" + secondaryClassNotFoundException.getMessage();
            logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via the LogFactoryImpl class classloader: " + msg.trim());
            break;
          }
        }
        constructor = url.getConstructor(this.logConstructorSignature);
        Object resourceName = constructor.newInstance(params);
        if ((resourceName instanceof Log))
        {
          logAdapterClass = url;
          logAdapter = (Log)resourceName;
          break;
        }
        handleFlawedHierarchy(currentCL, url);
      }
      catch (NoClassDefFoundError url)
      {
        String resourceName = "" + url.getMessage();
        logDiagnostic("The log adapter '" + logAdapterClassName + "' is missing dependencies when loaded via classloader " + LogFactory.objectId(currentCL) + ": " + resourceName.trim());
        break;
      }
      catch (ExceptionInInitializerError url)
      {
        String resourceName = "" + url.getMessage();
        logDiagnostic("The log adapter '" + logAdapterClassName + "' is unable to initialize itself when loaded via classloader " + LogFactory.objectId(currentCL) + ": " + resourceName.trim());
        break;
      }
      catch (LogConfigurationException url)
      {
        throw url;
      }
      catch (Throwable url)
      {
        handleFlawedDiscovery(logAdapterClassName, currentCL, url);
      }
      if (currentCL == null) {
        break;
      }
    }
    if ((logAdapter != null) && (affectState))
    {
      this.logClassName = logAdapterClassName;
      this.logConstructor = constructor;
      try
      {
        this.logMethod = logAdapterClass.getMethod("setLogFactory", this.logMethodSignature);
        logDiagnostic("Found method setLogFactory(LogFactory) in '" + logAdapterClassName + "'");
      }
      catch (Throwable url)
      {
        this.logMethod = null;
        logDiagnostic("[INFO] '" + logAdapterClassName + "' from classloader " + LogFactory.objectId(currentCL) + " does not declare optional method " + "setLogFactory(LogFactory)");
      }
      logDiagnostic("Log adapter '" + logAdapterClassName + "' from classloader " + LogFactory.objectId(logAdapterClass.getClassLoader()) + " has been selected for use.");
    }
    return logAdapter;
  }
  
  private ClassLoader getBaseClassLoader()
    throws LogConfigurationException
  {
    ClassLoader thisClassLoader = getClassLoader(LogFactoryImpl.class);
    if (!this.useTCCL) {
      return thisClassLoader;
    }
    ClassLoader contextClassLoader = getContextClassLoaderInternal();
    ClassLoader baseClassLoader = getLowestClassLoader(contextClassLoader, thisClassLoader);
    if (baseClassLoader == null)
    {
      if (this.allowFlawedContext)
      {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("[WARNING] the context classloader is not part of a parent-child relationship with the classloader that loaded LogFactoryImpl.");
        }
        return contextClassLoader;
      }
      throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
    }
    if (baseClassLoader != contextClassLoader) {
      if (this.allowFlawedContext)
      {
        if (isDiagnosticsEnabled()) {
          logDiagnostic("Warning: the context classloader is an ancestor of the classloader that loaded LogFactoryImpl; it should be the same or a descendant. The application using commons-logging should ensure the context classloader is used correctly.");
        }
      }
      else {
        throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
      }
    }
    return baseClassLoader;
  }
  
  private ClassLoader getLowestClassLoader(ClassLoader local_c1, ClassLoader local_c2)
  {
    if (local_c1 == null) {
      return local_c2;
    }
    if (local_c2 == null) {
      return local_c1;
    }
    for (ClassLoader current = local_c1; current != null; current = current.getParent()) {
      if (current == local_c2) {
        return local_c1;
      }
    }
    for (current = local_c2; current != null; current = current.getParent()) {
      if (current == local_c1) {
        return local_c2;
      }
    }
    return null;
  }
  
  private void handleFlawedDiscovery(String logAdapterClassName, ClassLoader classLoader, Throwable discoveryFlaw)
  {
    if (isDiagnosticsEnabled())
    {
      logDiagnostic("Could not instantiate Log '" + logAdapterClassName + "' -- " + discoveryFlaw.getClass().getName() + ": " + discoveryFlaw.getLocalizedMessage());
      if ((discoveryFlaw instanceof InvocationTargetException))
      {
        InvocationTargetException ite = (InvocationTargetException)discoveryFlaw;
        Throwable cause = ite.getTargetException();
        if (cause != null)
        {
          logDiagnostic("... InvocationTargetException: " + cause.getClass().getName() + ": " + cause.getLocalizedMessage());
          if ((cause instanceof ExceptionInInitializerError))
          {
            ExceptionInInitializerError eiie = (ExceptionInInitializerError)cause;
            Throwable cause2 = eiie.getException();
            if (cause2 != null) {
              logDiagnostic("... ExceptionInInitializerError: " + cause2.getClass().getName() + ": " + cause2.getLocalizedMessage());
            }
          }
        }
      }
    }
    if (!this.allowFlawedDiscovery) {
      throw new LogConfigurationException(discoveryFlaw);
    }
  }
  
  private void handleFlawedHierarchy(ClassLoader badClassLoader, Class badClass)
    throws LogConfigurationException
  {
    boolean implementsLog = false;
    String logInterfaceName = Log.class.getName();
    Class[] interfaces = badClass.getInterfaces();
    for (int local_i = 0; local_i < interfaces.length; local_i++) {
      if (logInterfaceName.equals(interfaces[local_i].getName()))
      {
        implementsLog = true;
        break;
      }
    }
    if (implementsLog)
    {
      if (isDiagnosticsEnabled()) {
        try
        {
          ClassLoader local_i = getClassLoader(Log.class);
          logDiagnostic("Class '" + badClass.getName() + "' was found in classloader " + LogFactory.objectId(badClassLoader) + ". It is bound to a Log interface which is not" + " the one loaded from classloader " + LogFactory.objectId(local_i));
        }
        catch (Throwable local_i)
        {
          logDiagnostic("Error while trying to output diagnostics about bad class '" + badClass + "'");
        }
      }
      if (!this.allowFlawedHierarchy)
      {
        StringBuffer local_i = new StringBuffer();
        local_i.append("Terminating logging for this context ");
        local_i.append("due to bad log hierarchy. ");
        local_i.append("You have more than one version of '");
        local_i.append(Log.class.getName());
        local_i.append("' visible.");
        if (isDiagnosticsEnabled()) {
          logDiagnostic(local_i.toString());
        }
        throw new LogConfigurationException(local_i.toString());
      }
      if (isDiagnosticsEnabled())
      {
        StringBuffer local_i = new StringBuffer();
        local_i.append("Warning: bad log hierarchy. ");
        local_i.append("You have more than one version of '");
        local_i.append(Log.class.getName());
        local_i.append("' visible.");
        logDiagnostic(local_i.toString());
      }
    }
    else
    {
      if (!this.allowFlawedDiscovery)
      {
        StringBuffer local_i = new StringBuffer();
        local_i.append("Terminating logging for this context. ");
        local_i.append("Log class '");
        local_i.append(badClass.getName());
        local_i.append("' does not implement the Log interface.");
        if (isDiagnosticsEnabled()) {
          logDiagnostic(local_i.toString());
        }
        throw new LogConfigurationException(local_i.toString());
      }
      if (isDiagnosticsEnabled())
      {
        StringBuffer local_i = new StringBuffer();
        local_i.append("[WARNING] Log class '");
        local_i.append(badClass.getName());
        local_i.append("' does not implement the Log interface.");
        logDiagnostic(local_i.toString());
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.logging.impl.LogFactoryImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */