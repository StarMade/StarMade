/*      */ package org.apache.commons.logging.impl;
/*      */ 
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.URL;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogConfigurationException;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ 
/*      */ public class LogFactoryImpl extends LogFactory
/*      */ {
/*      */   private static final String LOGGING_IMPL_LOG4J_LOGGER = "org.apache.commons.logging.impl.Log4JLogger";
/*      */   private static final String LOGGING_IMPL_JDK14_LOGGER = "org.apache.commons.logging.impl.Jdk14Logger";
/*      */   private static final String LOGGING_IMPL_LUMBERJACK_LOGGER = "org.apache.commons.logging.impl.Jdk13LumberjackLogger";
/*      */   private static final String LOGGING_IMPL_SIMPLE_LOGGER = "org.apache.commons.logging.impl.SimpleLog";
/*      */   private static final String PKG_IMPL = "org.apache.commons.logging.impl.";
/*   84 */   private static final int PKG_LEN = "org.apache.commons.logging.impl.".length();
/*      */   public static final String LOG_PROPERTY = "org.apache.commons.logging.Log";
/*      */   protected static final String LOG_PROPERTY_OLD = "org.apache.commons.logging.log";
/*      */   public static final String ALLOW_FLAWED_CONTEXT_PROPERTY = "org.apache.commons.logging.Log.allowFlawedContext";
/*      */   public static final String ALLOW_FLAWED_DISCOVERY_PROPERTY = "org.apache.commons.logging.Log.allowFlawedDiscovery";
/*      */   public static final String ALLOW_FLAWED_HIERARCHY_PROPERTY = "org.apache.commons.logging.Log.allowFlawedHierarchy";
/*  176 */   private static final String[] classesToDiscover = { "org.apache.commons.logging.impl.Log4JLogger", "org.apache.commons.logging.impl.Jdk14Logger", "org.apache.commons.logging.impl.Jdk13LumberjackLogger", "org.apache.commons.logging.impl.SimpleLog" };
/*      */ 
/*  190 */   private boolean useTCCL = true;
/*      */   private String diagnosticPrefix;
/*  201 */   protected Hashtable attributes = new Hashtable();
/*      */ 
/*  208 */   protected Hashtable instances = new Hashtable();
/*      */   private String logClassName;
/*  224 */   protected Constructor logConstructor = null;
/*      */ 
/*  230 */   protected Class[] logConstructorSignature = { String.class };
/*      */ 
/*  238 */   protected Method logMethod = null;
/*      */ 
/*  244 */   protected Class[] logMethodSignature = { LogFactory.class };
/*      */   private boolean allowFlawedContext;
/*      */   private boolean allowFlawedDiscovery;
/*      */   private boolean allowFlawedHierarchy;
/*      */ 
/*      */   public LogFactoryImpl()
/*      */   {
/*   95 */     initDiagnostics();
/*   96 */     if (isDiagnosticsEnabled())
/*   97 */       logDiagnostic("Instance created.");
/*      */   }
/*      */ 
/*      */   public Object getAttribute(String name)
/*      */   {
/*  273 */     return this.attributes.get(name);
/*      */   }
/*      */ 
/*      */   public String[] getAttributeNames()
/*      */   {
/*  285 */     Vector names = new Vector();
/*  286 */     Enumeration keys = this.attributes.keys();
/*  287 */     while (keys.hasMoreElements()) {
/*  288 */       names.addElement((String)keys.nextElement());
/*      */     }
/*  290 */     String[] results = new String[names.size()];
/*  291 */     for (int i = 0; i < results.length; i++) {
/*  292 */       results[i] = ((String)names.elementAt(i));
/*      */     }
/*  294 */     return results;
/*      */   }
/*      */ 
/*      */   public Log getInstance(Class clazz)
/*      */     throws LogConfigurationException
/*      */   {
/*  310 */     return getInstance(clazz.getName());
/*      */   }
/*      */ 
/*      */   public Log getInstance(String name)
/*      */     throws LogConfigurationException
/*      */   {
/*  334 */     Log instance = (Log)this.instances.get(name);
/*  335 */     if (instance == null) {
/*  336 */       instance = newInstance(name);
/*  337 */       this.instances.put(name, instance);
/*      */     }
/*  339 */     return instance;
/*      */   }
/*      */ 
/*      */   public void release()
/*      */   {
/*  354 */     logDiagnostic("Releasing all known loggers");
/*  355 */     this.instances.clear();
/*      */   }
/*      */ 
/*      */   public void removeAttribute(String name)
/*      */   {
/*  367 */     this.attributes.remove(name);
/*      */   }
/*      */ 
/*      */   public void setAttribute(String name, Object value)
/*      */   {
/*  398 */     if (this.logConstructor != null) {
/*  399 */       logDiagnostic("setAttribute: call too late; configuration already performed.");
/*      */     }
/*      */ 
/*  402 */     if (value == null)
/*  403 */       this.attributes.remove(name);
/*      */     else {
/*  405 */       this.attributes.put(name, value);
/*      */     }
/*      */ 
/*  408 */     if (name.equals("use_tccl"))
/*  409 */       this.useTCCL = Boolean.valueOf(value.toString()).booleanValue();
/*      */   }
/*      */ 
/*      */   protected static ClassLoader getContextClassLoader()
/*      */     throws LogConfigurationException
/*      */   {
/*  428 */     return LogFactory.getContextClassLoader();
/*      */   }
/*      */ 
/*      */   protected static boolean isDiagnosticsEnabled()
/*      */   {
/*  437 */     return LogFactory.isDiagnosticsEnabled();
/*      */   }
/*      */ 
/*      */   protected static ClassLoader getClassLoader(Class clazz)
/*      */   {
/*  447 */     return LogFactory.getClassLoader(clazz);
/*      */   }
/*      */ 
/*      */   private void initDiagnostics()
/*      */   {
/*  475 */     Class clazz = getClass();
/*  476 */     ClassLoader classLoader = getClassLoader(clazz);
/*      */     String classLoaderName;
/*      */     try
/*      */     {
/*      */       String classLoaderName;
/*  479 */       if (classLoader == null)
/*  480 */         classLoaderName = "BOOTLOADER";
/*      */       else
/*  482 */         classLoaderName = LogFactory.objectId(classLoader);
/*      */     }
/*      */     catch (SecurityException e)
/*      */     {
/*      */       String classLoaderName;
/*  485 */       classLoaderName = "UNKNOWN";
/*      */     }
/*  487 */     this.diagnosticPrefix = ("[LogFactoryImpl@" + System.identityHashCode(this) + " from " + classLoaderName + "] ");
/*      */   }
/*      */ 
/*      */   protected void logDiagnostic(String msg)
/*      */   {
/*  499 */     if (isDiagnosticsEnabled())
/*  500 */       LogFactory.logRawDiagnostic(this.diagnosticPrefix + msg);
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   protected String getLogClassName()
/*      */   {
/*  513 */     if (this.logClassName == null) {
/*  514 */       discoverLogImplementation(getClass().getName());
/*      */     }
/*      */ 
/*  517 */     return this.logClassName;
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   protected Constructor getLogConstructor()
/*      */     throws LogConfigurationException
/*      */   {
/*  540 */     if (this.logConstructor == null) {
/*  541 */       discoverLogImplementation(getClass().getName());
/*      */     }
/*      */ 
/*  544 */     return this.logConstructor;
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   protected boolean isJdk13LumberjackAvailable()
/*      */   {
/*  555 */     return isLogLibraryAvailable("Jdk13Lumberjack", "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   protected boolean isJdk14Available()
/*      */   {
/*  571 */     return isLogLibraryAvailable("Jdk14", "org.apache.commons.logging.impl.Jdk14Logger");
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   protected boolean isLog4JAvailable()
/*      */   {
/*  584 */     return isLogLibraryAvailable("Log4J", "org.apache.commons.logging.impl.Log4JLogger");
/*      */   }
/*      */ 
/*      */   protected Log newInstance(String name)
/*      */     throws LogConfigurationException
/*      */   {
/*  601 */     Log instance = null;
/*      */     try {
/*  603 */       if (this.logConstructor == null) {
/*  604 */         instance = discoverLogImplementation(name);
/*      */       }
/*      */       else {
/*  607 */         Object[] params = { name };
/*  608 */         instance = (Log)this.logConstructor.newInstance(params);
/*      */       }
/*      */ 
/*  611 */       if (this.logMethod != null) {
/*  612 */         Object[] params = { this };
/*  613 */         this.logMethod.invoke(instance, params);
/*      */       }
/*      */ 
/*  616 */       return instance;
/*      */     }
/*      */     catch (LogConfigurationException lce)
/*      */     {
/*  623 */       throw lce;
/*      */     }
/*      */     catch (InvocationTargetException e)
/*      */     {
/*  628 */       Throwable c = e.getTargetException();
/*  629 */       if (c != null) {
/*  630 */         throw new LogConfigurationException(c);
/*      */       }
/*  632 */       throw new LogConfigurationException(e);
/*      */     }
/*      */     catch (Throwable t)
/*      */     {
/*  637 */       throw new LogConfigurationException(t);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static ClassLoader getContextClassLoaderInternal()
/*      */     throws LogConfigurationException
/*      */   {
/*  664 */     return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
/*      */     {
/*      */       public Object run() {
/*  667 */         return LogFactoryImpl.access$000();
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private static String getSystemProperty(String key, String def)
/*      */     throws SecurityException
/*      */   {
/*  683 */     return (String)AccessController.doPrivileged(new PrivilegedAction() { private final String val$key;
/*      */       private final String val$def;
/*      */ 
/*  686 */       public Object run() { return System.getProperty(this.val$key, this.val$def); }
/*      */ 
/*      */     });
/*      */   }
/*      */ 
/*      */   private ClassLoader getParentClassLoader(ClassLoader cl)
/*      */   {
/*      */     try
/*      */     {
/*  700 */       return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
/*      */         private final ClassLoader val$cl;
/*      */ 
/*  703 */         public Object run() { return this.val$cl.getParent(); }
/*      */       });
/*      */     }
/*      */     catch (SecurityException ex) {
/*  707 */       logDiagnostic("[SECURITY] Unable to obtain parent classloader");
/*  708 */     }return null;
/*      */   }
/*      */ 
/*      */   private boolean isLogLibraryAvailable(String name, String classname)
/*      */   {
/*  719 */     if (isDiagnosticsEnabled())
/*  720 */       logDiagnostic("Checking for '" + name + "'.");
/*      */     try
/*      */     {
/*  723 */       Log log = createLogFromClass(classname, getClass().getName(), false);
/*      */ 
/*  728 */       if (log == null) {
/*  729 */         if (isDiagnosticsEnabled()) {
/*  730 */           logDiagnostic("Did not find '" + name + "'.");
/*      */         }
/*  732 */         return false;
/*      */       }
/*  734 */       if (isDiagnosticsEnabled()) {
/*  735 */         logDiagnostic("Found '" + name + "'.");
/*      */       }
/*  737 */       return true;
/*      */     }
/*      */     catch (LogConfigurationException e) {
/*  740 */       if (isDiagnosticsEnabled())
/*  741 */         logDiagnostic("Logging system '" + name + "' is available but not useable.");
/*      */     }
/*  743 */     return false;
/*      */   }
/*      */ 
/*      */   private String getConfigurationValue(String property)
/*      */   {
/*  759 */     if (isDiagnosticsEnabled()) {
/*  760 */       logDiagnostic("[ENV] Trying to get configuration for item " + property);
/*      */     }
/*      */ 
/*  763 */     Object valueObj = getAttribute(property);
/*  764 */     if (valueObj != null) {
/*  765 */       if (isDiagnosticsEnabled()) {
/*  766 */         logDiagnostic("[ENV] Found LogFactory attribute [" + valueObj + "] for " + property);
/*      */       }
/*  768 */       return valueObj.toString();
/*      */     }
/*      */ 
/*  771 */     if (isDiagnosticsEnabled()) {
/*  772 */       logDiagnostic("[ENV] No LogFactory attribute found for " + property);
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/*  780 */       String value = getSystemProperty(property, null);
/*  781 */       if (value != null) {
/*  782 */         if (isDiagnosticsEnabled()) {
/*  783 */           logDiagnostic("[ENV] Found system property [" + value + "] for " + property);
/*      */         }
/*  785 */         return value;
/*      */       }
/*      */ 
/*  788 */       if (isDiagnosticsEnabled())
/*  789 */         logDiagnostic("[ENV] No system property found for property " + property);
/*      */     }
/*      */     catch (SecurityException e) {
/*  792 */       if (isDiagnosticsEnabled()) {
/*  793 */         logDiagnostic("[ENV] Security prevented reading system property " + property);
/*      */       }
/*      */     }
/*      */ 
/*  797 */     if (isDiagnosticsEnabled()) {
/*  798 */       logDiagnostic("[ENV] No configuration defined for item " + property);
/*      */     }
/*      */ 
/*  801 */     return null;
/*      */   }
/*      */ 
/*      */   private boolean getBooleanConfiguration(String key, boolean dflt)
/*      */   {
/*  809 */     String val = getConfigurationValue(key);
/*  810 */     if (val == null)
/*  811 */       return dflt;
/*  812 */     return Boolean.valueOf(val).booleanValue();
/*      */   }
/*      */ 
/*      */   private void initConfiguration()
/*      */   {
/*  823 */     this.allowFlawedContext = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedContext", true);
/*  824 */     this.allowFlawedDiscovery = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedDiscovery", true);
/*  825 */     this.allowFlawedHierarchy = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedHierarchy", true);
/*      */   }
/*      */ 
/*      */   private Log discoverLogImplementation(String logCategory)
/*      */     throws LogConfigurationException
/*      */   {
/*  841 */     if (isDiagnosticsEnabled()) {
/*  842 */       logDiagnostic("Discovering a Log implementation...");
/*      */     }
/*      */ 
/*  845 */     initConfiguration();
/*      */ 
/*  847 */     Log result = null;
/*      */ 
/*  850 */     String specifiedLogClassName = findUserSpecifiedLogClassName();
/*      */ 
/*  852 */     if (specifiedLogClassName != null) {
/*  853 */       if (isDiagnosticsEnabled()) {
/*  854 */         logDiagnostic("Attempting to load user-specified log class '" + specifiedLogClassName + "'...");
/*      */       }
/*      */ 
/*  858 */       result = createLogFromClass(specifiedLogClassName, logCategory, true);
/*      */ 
/*  861 */       if (result == null) {
/*  862 */         StringBuffer messageBuffer = new StringBuffer("User-specified log class '");
/*  863 */         messageBuffer.append(specifiedLogClassName);
/*  864 */         messageBuffer.append("' cannot be found or is not useable.");
/*      */ 
/*  868 */         if (specifiedLogClassName != null) {
/*  869 */           informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.Log4JLogger");
/*  870 */           informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.Jdk14Logger");
/*  871 */           informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
/*  872 */           informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.SimpleLog");
/*      */         }
/*  874 */         throw new LogConfigurationException(messageBuffer.toString());
/*      */       }
/*      */ 
/*  877 */       return result;
/*      */     }
/*      */ 
/*  908 */     if (isDiagnosticsEnabled()) {
/*  909 */       logDiagnostic("No user-specified Log implementation; performing discovery using the standard supported logging implementations...");
/*      */     }
/*      */ 
/*  913 */     for (int i = 0; (i < classesToDiscover.length) && (result == null); i++) {
/*  914 */       result = createLogFromClass(classesToDiscover[i], logCategory, true);
/*      */     }
/*      */ 
/*  917 */     if (result == null) {
/*  918 */       throw new LogConfigurationException("No suitable Log implementation");
/*      */     }
/*      */ 
/*  922 */     return result;
/*      */   }
/*      */ 
/*      */   private void informUponSimilarName(StringBuffer messageBuffer, String name, String candidate)
/*      */   {
/*  935 */     if (name.equals(candidate))
/*      */     {
/*  938 */       return;
/*      */     }
/*      */ 
/*  944 */     if (name.regionMatches(true, 0, candidate, 0, PKG_LEN + 5)) {
/*  945 */       messageBuffer.append(" Did you mean '");
/*  946 */       messageBuffer.append(candidate);
/*  947 */       messageBuffer.append("'?");
/*      */     }
/*      */   }
/*      */ 
/*      */   private String findUserSpecifiedLogClassName()
/*      */   {
/*  961 */     if (isDiagnosticsEnabled()) {
/*  962 */       logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.Log'");
/*      */     }
/*  964 */     String specifiedClass = (String)getAttribute("org.apache.commons.logging.Log");
/*      */ 
/*  966 */     if (specifiedClass == null) {
/*  967 */       if (isDiagnosticsEnabled()) {
/*  968 */         logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.log'");
/*      */       }
/*      */ 
/*  971 */       specifiedClass = (String)getAttribute("org.apache.commons.logging.log");
/*      */     }
/*      */ 
/*  974 */     if (specifiedClass == null) {
/*  975 */       if (isDiagnosticsEnabled()) {
/*  976 */         logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.Log'");
/*      */       }
/*      */       try
/*      */       {
/*  980 */         specifiedClass = getSystemProperty("org.apache.commons.logging.Log", null);
/*      */       } catch (SecurityException e) {
/*  982 */         if (isDiagnosticsEnabled()) {
/*  983 */           logDiagnostic("No access allowed to system property 'org.apache.commons.logging.Log' - " + e.getMessage());
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  989 */     if (specifiedClass == null) {
/*  990 */       if (isDiagnosticsEnabled()) {
/*  991 */         logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.log'");
/*      */       }
/*      */       try
/*      */       {
/*  995 */         specifiedClass = getSystemProperty("org.apache.commons.logging.log", null);
/*      */       } catch (SecurityException e) {
/*  997 */         if (isDiagnosticsEnabled()) {
/*  998 */           logDiagnostic("No access allowed to system property 'org.apache.commons.logging.log' - " + e.getMessage());
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1007 */     if (specifiedClass != null) {
/* 1008 */       specifiedClass = specifiedClass.trim();
/*      */     }
/*      */ 
/* 1011 */     return specifiedClass;
/*      */   }
/*      */ 
/*      */   private Log createLogFromClass(String logAdapterClassName, String logCategory, boolean affectState)
/*      */     throws LogConfigurationException
/*      */   {
/* 1039 */     if (isDiagnosticsEnabled()) {
/* 1040 */       logDiagnostic("Attempting to instantiate '" + logAdapterClassName + "'");
/*      */     }
/*      */ 
/* 1043 */     Object[] params = { logCategory };
/* 1044 */     Log logAdapter = null;
/* 1045 */     Constructor constructor = null;
/*      */ 
/* 1047 */     Class logAdapterClass = null;
/* 1048 */     ClassLoader currentCL = getBaseClassLoader();
/*      */     while (true)
/*      */     {
/* 1053 */       logDiagnostic("Trying to load '" + logAdapterClassName + "' from classloader " + LogFactory.objectId(currentCL));
/*      */       try
/*      */       {
/* 1059 */         if (isDiagnosticsEnabled())
/*      */         {
/* 1065 */           String resourceName = logAdapterClassName.replace('.', '/') + ".class";
/*      */           URL url;
/*      */           URL url;
/* 1066 */           if (currentCL != null)
/* 1067 */             url = currentCL.getResource(resourceName);
/*      */           else {
/* 1069 */             url = ClassLoader.getSystemResource(resourceName + ".class");
/*      */           }
/*      */ 
/* 1072 */           if (url == null)
/* 1073 */             logDiagnostic("Class '" + logAdapterClassName + "' [" + resourceName + "] cannot be found.");
/*      */           else {
/* 1075 */             logDiagnostic("Class '" + logAdapterClassName + "' was found at '" + url + "'");
/*      */           }
/*      */         }
/*      */ 
/* 1079 */         Class c = null;
/*      */         try {
/* 1081 */           c = Class.forName(logAdapterClassName, true, currentCL);
/*      */         }
/*      */         catch (ClassNotFoundException originalClassNotFoundException)
/*      */         {
/* 1086 */           String msg = "" + originalClassNotFoundException.getMessage();
/* 1087 */           logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via classloader " + LogFactory.objectId(currentCL) + ": " + msg.trim());
/*      */           try
/*      */           {
/* 1102 */             c = Class.forName(logAdapterClassName);
/*      */           }
/*      */           catch (ClassNotFoundException secondaryClassNotFoundException) {
/* 1105 */             msg = "" + secondaryClassNotFoundException.getMessage();
/* 1106 */             logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via the LogFactoryImpl class classloader: " + msg.trim());
/*      */ 
/* 1111 */             break;
/*      */           }
/*      */         }
/*      */ 
/* 1115 */         constructor = c.getConstructor(this.logConstructorSignature);
/* 1116 */         Object o = constructor.newInstance(params);
/*      */ 
/* 1122 */         if ((o instanceof Log)) {
/* 1123 */           logAdapterClass = c;
/* 1124 */           logAdapter = (Log)o;
/* 1125 */           break;
/*      */         }
/*      */ 
/* 1138 */         handleFlawedHierarchy(currentCL, c);
/*      */       }
/*      */       catch (NoClassDefFoundError e)
/*      */       {
/* 1145 */         String msg = "" + e.getMessage();
/* 1146 */         logDiagnostic("The log adapter '" + logAdapterClassName + "' is missing dependencies when loaded via classloader " + LogFactory.objectId(currentCL) + ": " + msg.trim());
/*      */ 
/* 1153 */         break;
/*      */       }
/*      */       catch (ExceptionInInitializerError e)
/*      */       {
/* 1161 */         String msg = "" + e.getMessage();
/* 1162 */         logDiagnostic("The log adapter '" + logAdapterClassName + "' is unable to initialize itself when loaded via classloader " + LogFactory.objectId(currentCL) + ": " + msg.trim());
/*      */ 
/* 1169 */         break;
/*      */       }
/*      */       catch (LogConfigurationException e)
/*      */       {
/* 1173 */         throw e;
/*      */       }
/*      */       catch (Throwable t)
/*      */       {
/* 1178 */         handleFlawedDiscovery(logAdapterClassName, currentCL, t);
/*      */       }
/*      */ 
/* 1181 */       if (currentCL == null)
/*      */       {
/*      */         break;
/*      */       }
/*      */ 
/* 1187 */       currentCL = getParentClassLoader(currentCL);
/*      */     }
/*      */ 
/* 1190 */     if ((logAdapter != null) && (affectState))
/*      */     {
/* 1192 */       this.logClassName = logAdapterClassName;
/* 1193 */       this.logConstructor = constructor;
/*      */       try
/*      */       {
/* 1197 */         this.logMethod = logAdapterClass.getMethod("setLogFactory", this.logMethodSignature);
/*      */ 
/* 1199 */         logDiagnostic("Found method setLogFactory(LogFactory) in '" + logAdapterClassName + "'");
/*      */       }
/*      */       catch (Throwable t) {
/* 1202 */         this.logMethod = null;
/* 1203 */         logDiagnostic("[INFO] '" + logAdapterClassName + "' from classloader " + LogFactory.objectId(currentCL) + " does not declare optional method " + "setLogFactory(LogFactory)");
/*      */       }
/*      */ 
/* 1210 */       logDiagnostic("Log adapter '" + logAdapterClassName + "' from classloader " + LogFactory.objectId(logAdapterClass.getClassLoader()) + " has been selected for use.");
/*      */     }
/*      */ 
/* 1216 */     return logAdapter;
/*      */   }
/*      */ 
/*      */   private ClassLoader getBaseClassLoader()
/*      */     throws LogConfigurationException
/*      */   {
/* 1239 */     ClassLoader thisClassLoader = getClassLoader(LogFactoryImpl.class);
/*      */ 
/* 1241 */     if (!this.useTCCL) {
/* 1242 */       return thisClassLoader;
/*      */     }
/*      */ 
/* 1245 */     ClassLoader contextClassLoader = getContextClassLoaderInternal();
/*      */ 
/* 1247 */     ClassLoader baseClassLoader = getLowestClassLoader(contextClassLoader, thisClassLoader);
/*      */ 
/* 1250 */     if (baseClassLoader == null)
/*      */     {
/* 1255 */       if (this.allowFlawedContext) {
/* 1256 */         if (isDiagnosticsEnabled()) {
/* 1257 */           logDiagnostic("[WARNING] the context classloader is not part of a parent-child relationship with the classloader that loaded LogFactoryImpl.");
/*      */         }
/*      */ 
/* 1265 */         return contextClassLoader;
/*      */       }
/*      */ 
/* 1268 */       throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
/*      */     }
/*      */ 
/* 1275 */     if (baseClassLoader != contextClassLoader)
/*      */     {
/* 1281 */       if (this.allowFlawedContext) {
/* 1282 */         if (isDiagnosticsEnabled()) {
/* 1283 */           logDiagnostic("Warning: the context classloader is an ancestor of the classloader that loaded LogFactoryImpl; it should be the same or a descendant. The application using commons-logging should ensure the context classloader is used correctly.");
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1291 */         throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1298 */     return baseClassLoader;
/*      */   }
/*      */ 
/*      */   private ClassLoader getLowestClassLoader(ClassLoader c1, ClassLoader c2)
/*      */   {
/* 1314 */     if (c1 == null) {
/* 1315 */       return c2;
/*      */     }
/* 1317 */     if (c2 == null) {
/* 1318 */       return c1;
/*      */     }
/*      */ 
/* 1323 */     ClassLoader current = c1;
/* 1324 */     while (current != null) {
/* 1325 */       if (current == c2)
/* 1326 */         return c1;
/* 1327 */       current = current.getParent();
/*      */     }
/*      */ 
/* 1331 */     current = c2;
/* 1332 */     while (current != null) {
/* 1333 */       if (current == c1)
/* 1334 */         return c2;
/* 1335 */       current = current.getParent();
/*      */     }
/*      */ 
/* 1338 */     return null;
/*      */   }
/*      */ 
/*      */   private void handleFlawedDiscovery(String logAdapterClassName, ClassLoader classLoader, Throwable discoveryFlaw)
/*      */   {
/* 1360 */     if (isDiagnosticsEnabled()) {
/* 1361 */       logDiagnostic("Could not instantiate Log '" + logAdapterClassName + "' -- " + discoveryFlaw.getClass().getName() + ": " + discoveryFlaw.getLocalizedMessage());
/*      */ 
/* 1366 */       if ((discoveryFlaw instanceof InvocationTargetException))
/*      */       {
/* 1370 */         InvocationTargetException ite = (InvocationTargetException)discoveryFlaw;
/* 1371 */         Throwable cause = ite.getTargetException();
/* 1372 */         if (cause != null) {
/* 1373 */           logDiagnostic("... InvocationTargetException: " + cause.getClass().getName() + ": " + cause.getLocalizedMessage());
/*      */ 
/* 1377 */           if ((cause instanceof ExceptionInInitializerError)) {
/* 1378 */             ExceptionInInitializerError eiie = (ExceptionInInitializerError)cause;
/* 1379 */             Throwable cause2 = eiie.getException();
/* 1380 */             if (cause2 != null) {
/* 1381 */               logDiagnostic("... ExceptionInInitializerError: " + cause2.getClass().getName() + ": " + cause2.getLocalizedMessage());
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1390 */     if (!this.allowFlawedDiscovery)
/* 1391 */       throw new LogConfigurationException(discoveryFlaw);
/*      */   }
/*      */ 
/*      */   private void handleFlawedHierarchy(ClassLoader badClassLoader, Class badClass)
/*      */     throws LogConfigurationException
/*      */   {
/* 1425 */     boolean implementsLog = false;
/* 1426 */     String logInterfaceName = Log.class.getName();
/* 1427 */     Class[] interfaces = badClass.getInterfaces();
/* 1428 */     for (int i = 0; i < interfaces.length; i++) {
/* 1429 */       if (logInterfaceName.equals(interfaces[i].getName())) {
/* 1430 */         implementsLog = true;
/* 1431 */         break;
/*      */       }
/*      */     }
/*      */ 
/* 1435 */     if (implementsLog)
/*      */     {
/* 1438 */       if (isDiagnosticsEnabled()) {
/*      */         try {
/* 1440 */           ClassLoader logInterfaceClassLoader = getClassLoader(Log.class);
/* 1441 */           logDiagnostic("Class '" + badClass.getName() + "' was found in classloader " + LogFactory.objectId(badClassLoader) + ". It is bound to a Log interface which is not" + " the one loaded from classloader " + LogFactory.objectId(logInterfaceClassLoader));
/*      */         }
/*      */         catch (Throwable t)
/*      */         {
/* 1449 */           logDiagnostic("Error while trying to output diagnostics about bad class '" + badClass + "'");
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1455 */       if (!this.allowFlawedHierarchy) {
/* 1456 */         StringBuffer msg = new StringBuffer();
/* 1457 */         msg.append("Terminating logging for this context ");
/* 1458 */         msg.append("due to bad log hierarchy. ");
/* 1459 */         msg.append("You have more than one version of '");
/* 1460 */         msg.append(Log.class.getName());
/* 1461 */         msg.append("' visible.");
/* 1462 */         if (isDiagnosticsEnabled()) {
/* 1463 */           logDiagnostic(msg.toString());
/*      */         }
/* 1465 */         throw new LogConfigurationException(msg.toString());
/*      */       }
/*      */ 
/* 1468 */       if (isDiagnosticsEnabled()) {
/* 1469 */         StringBuffer msg = new StringBuffer();
/* 1470 */         msg.append("Warning: bad log hierarchy. ");
/* 1471 */         msg.append("You have more than one version of '");
/* 1472 */         msg.append(Log.class.getName());
/* 1473 */         msg.append("' visible.");
/* 1474 */         logDiagnostic(msg.toString());
/*      */       }
/*      */     }
/*      */     else {
/* 1478 */       if (!this.allowFlawedDiscovery) {
/* 1479 */         StringBuffer msg = new StringBuffer();
/* 1480 */         msg.append("Terminating logging for this context. ");
/* 1481 */         msg.append("Log class '");
/* 1482 */         msg.append(badClass.getName());
/* 1483 */         msg.append("' does not implement the Log interface.");
/* 1484 */         if (isDiagnosticsEnabled()) {
/* 1485 */           logDiagnostic(msg.toString());
/*      */         }
/*      */ 
/* 1488 */         throw new LogConfigurationException(msg.toString());
/*      */       }
/*      */ 
/* 1491 */       if (isDiagnosticsEnabled()) {
/* 1492 */         StringBuffer msg = new StringBuffer();
/* 1493 */         msg.append("[WARNING] Log class '");
/* 1494 */         msg.append(badClass.getName());
/* 1495 */         msg.append("' does not implement the Log interface.");
/* 1496 */         logDiagnostic(msg.toString());
/*      */       }
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.LogFactoryImpl
 * JD-Core Version:    0.6.2
 */