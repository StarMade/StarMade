/*    1:     */package org.apache.commons.logging;
/*    2:     */
/*    3:     */import java.io.BufferedReader;
/*    4:     */import java.io.FileOutputStream;
/*    5:     */import java.io.IOException;
/*    6:     */import java.io.InputStream;
/*    7:     */import java.io.InputStreamReader;
/*    8:     */import java.io.PrintStream;
/*    9:     */import java.io.UnsupportedEncodingException;
/*   10:     */import java.lang.reflect.InvocationTargetException;
/*   11:     */import java.lang.reflect.Method;
/*   12:     */import java.net.URL;
/*   13:     */import java.security.AccessController;
/*   14:     */import java.security.PrivilegedAction;
/*   15:     */import java.util.Enumeration;
/*   16:     */import java.util.Hashtable;
/*   17:     */import java.util.Properties;
/*   18:     */
/*  139:     */public abstract class LogFactory
/*  140:     */{
/*  141:     */  public static final String PRIORITY_KEY = "priority";
/*  142:     */  public static final String TCCL_KEY = "use_tccl";
/*  143:     */  public static final String FACTORY_PROPERTY = "org.apache.commons.logging.LogFactory";
/*  144:     */  public static final String FACTORY_DEFAULT = "org.apache.commons.logging.impl.LogFactoryImpl";
/*  145:     */  public static final String FACTORY_PROPERTIES = "commons-logging.properties";
/*  146:     */  protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
/*  147:     */  public static final String DIAGNOSTICS_DEST_PROPERTY = "org.apache.commons.logging.diagnostics.dest";
/*  148: 148 */  private static PrintStream diagnosticsStream = null;
/*  149:     */  
/*  181:     */  private static String diagnosticPrefix;
/*  182:     */  
/*  213:     */  public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "org.apache.commons.logging.LogFactory.HashtableImpl";
/*  214:     */  
/*  245:     */  private static final String WEAK_HASHTABLE_CLASSNAME = "org.apache.commons.logging.impl.WeakHashtable";
/*  246:     */  
/*  277:     */  private static ClassLoader thisClassLoader;
/*  278:     */  
/*  309: 309 */  protected static Hashtable factories = null;
/*  310:     */  
/*  325: 325 */  protected static LogFactory nullClassLoaderFactory = null;
/*  326:     */  
/*  327:     */  public abstract Object getAttribute(String paramString);
/*  328:     */  
/*  329:     */  public abstract String[] getAttributeNames();
/*  330:     */  
/*  331:     */  public abstract Log getInstance(Class paramClass) throws LogConfigurationException;
/*  332:     */  
/*  333:     */  public abstract Log getInstance(String paramString) throws LogConfigurationException;
/*  334:     */  
/*  335:     */  public abstract void release();
/*  336:     */  
/*  337:     */  public abstract void removeAttribute(String paramString);
/*  338:     */  
/*  339:     */  public abstract void setAttribute(String paramString, Object paramObject);
/*  340:     */  
/*  341:     */  private static final Hashtable createFactoryStore()
/*  342:     */  {
/*  343: 343 */    Hashtable result = null;
/*  344:     */    String storeImplementationClass;
/*  345:     */    try {
/*  346: 346 */      storeImplementationClass = getSystemProperty("org.apache.commons.logging.LogFactory.HashtableImpl", null);
/*  347:     */    }
/*  348:     */    catch (SecurityException ex) {
/*  349:     */      String storeImplementationClass;
/*  350: 350 */      storeImplementationClass = null;
/*  351:     */    }
/*  352:     */    
/*  353: 353 */    if (storeImplementationClass == null) {
/*  354: 354 */      storeImplementationClass = "org.apache.commons.logging.impl.WeakHashtable";
/*  355:     */    }
/*  356:     */    try {
/*  357: 357 */      Class implementationClass = Class.forName(storeImplementationClass);
/*  358: 358 */      result = (Hashtable)implementationClass.newInstance();
/*  359:     */    }
/*  360:     */    catch (Throwable t)
/*  361:     */    {
/*  362: 362 */      if (!"org.apache.commons.logging.impl.WeakHashtable".equals(storeImplementationClass))
/*  363:     */      {
/*  364: 364 */        if (isDiagnosticsEnabled())
/*  365:     */        {
/*  366: 366 */          logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
/*  367:     */        }
/*  368:     */        else
/*  369:     */        {
/*  370: 370 */          System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
/*  371:     */        }
/*  372:     */      }
/*  373:     */    }
/*  374: 374 */    if (result == null) {
/*  375: 375 */      result = new Hashtable();
/*  376:     */    }
/*  377: 377 */    return result;
/*  378:     */  }
/*  379:     */  
/*  383:     */  private static String trim(String src)
/*  384:     */  {
/*  385: 385 */    if (src == null) {
/*  386: 386 */      return null;
/*  387:     */    }
/*  388: 388 */    return src.trim();
/*  389:     */  }
/*  390:     */  
/*  420:     */  public static LogFactory getFactory()
/*  421:     */    throws LogConfigurationException
/*  422:     */  {
/*  423: 423 */    ClassLoader contextClassLoader = getContextClassLoaderInternal();
/*  424:     */    
/*  425: 425 */    if (contextClassLoader == null)
/*  426:     */    {
/*  429: 429 */      if (isDiagnosticsEnabled()) {
/*  430: 430 */        logDiagnostic("Context classloader is null.");
/*  431:     */      }
/*  432:     */    }
/*  433:     */    
/*  435: 435 */    LogFactory factory = getCachedFactory(contextClassLoader);
/*  436: 436 */    if (factory != null) {
/*  437: 437 */      return factory;
/*  438:     */    }
/*  439:     */    
/*  440: 440 */    if (isDiagnosticsEnabled()) {
/*  441: 441 */      logDiagnostic("[LOOKUP] LogFactory implementation requested for the first time for context classloader " + objectId(contextClassLoader));
/*  442:     */      
/*  444: 444 */      logHierarchy("[LOOKUP] ", contextClassLoader);
/*  445:     */    }
/*  446:     */    
/*  457: 457 */    Properties props = getConfigurationFile(contextClassLoader, "commons-logging.properties");
/*  458:     */    
/*  461: 461 */    ClassLoader baseClassLoader = contextClassLoader;
/*  462: 462 */    if (props != null) {
/*  463: 463 */      String useTCCLStr = props.getProperty("use_tccl");
/*  464: 464 */      if (useTCCLStr != null)
/*  465:     */      {
/*  467: 467 */        if (!Boolean.valueOf(useTCCLStr).booleanValue())
/*  468:     */        {
/*  475: 475 */          baseClassLoader = thisClassLoader;
/*  476:     */        }
/*  477:     */      }
/*  478:     */    }
/*  479:     */    
/*  482: 482 */    if (isDiagnosticsEnabled()) {
/*  483: 483 */      logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
/*  484:     */    }
/*  485:     */    
/*  487:     */    try
/*  488:     */    {
/*  489: 489 */      String factoryClass = getSystemProperty("org.apache.commons.logging.LogFactory", null);
/*  490: 490 */      if (factoryClass != null) {
/*  491: 491 */        if (isDiagnosticsEnabled()) {
/*  492: 492 */          logDiagnostic("[LOOKUP] Creating an instance of LogFactory class '" + factoryClass + "' as specified by system property " + "org.apache.commons.logging.LogFactory");
/*  493:     */        }
/*  494:     */        
/*  497: 497 */        factory = newFactory(factoryClass, baseClassLoader, contextClassLoader);
/*  498:     */      }
/*  499: 499 */      else if (isDiagnosticsEnabled()) {
/*  500: 500 */        logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
/*  501:     */      }
/*  502:     */      
/*  503:     */    }
/*  504:     */    catch (SecurityException e)
/*  505:     */    {
/*  506: 506 */      if (isDiagnosticsEnabled()) {
/*  507: 507 */        logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(e.getMessage()) + "]. Trying alternative implementations...");
/*  510:     */      }
/*  511:     */      
/*  515:     */    }
/*  516:     */    catch (RuntimeException e)
/*  517:     */    {
/*  520: 520 */      if (isDiagnosticsEnabled()) {
/*  521: 521 */        logDiagnostic("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [" + trim(e.getMessage()) + "] as specified by a system property.");
/*  522:     */      }
/*  523:     */      
/*  527: 527 */      throw e;
/*  528:     */    }
/*  529:     */    
/*  537: 537 */    if (factory == null) {
/*  538: 538 */      if (isDiagnosticsEnabled()) {
/*  539: 539 */        logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
/*  540:     */      }
/*  541:     */      
/*  542:     */      try
/*  543:     */      {
/*  544: 544 */        InputStream is = getResourceAsStream(contextClassLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
/*  545:     */        
/*  547: 547 */        if (is != null)
/*  548:     */        {
/*  549:     */          BufferedReader rd;
/*  550:     */          try
/*  551:     */          {
/*  552: 552 */            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
/*  553:     */          } catch (UnsupportedEncodingException e) { BufferedReader rd;
/*  554: 554 */            rd = new BufferedReader(new InputStreamReader(is));
/*  555:     */          }
/*  556:     */          
/*  557: 557 */          String factoryClassName = rd.readLine();
/*  558: 558 */          rd.close();
/*  559:     */          
/*  560: 560 */          if ((factoryClassName != null) && (!"".equals(factoryClassName)))
/*  561:     */          {
/*  562: 562 */            if (isDiagnosticsEnabled()) {
/*  563: 563 */              logDiagnostic("[LOOKUP]  Creating an instance of LogFactory class " + factoryClassName + " as specified by file '" + "META-INF/services/org.apache.commons.logging.LogFactory" + "' which was present in the path of the context" + " classloader.");
/*  564:     */            }
/*  565:     */            
/*  569: 569 */            factory = newFactory(factoryClassName, baseClassLoader, contextClassLoader);
/*  570:     */          }
/*  571:     */          
/*  572:     */        }
/*  573: 573 */        else if (isDiagnosticsEnabled()) {
/*  574: 574 */          logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
/*  576:     */        }
/*  577:     */        
/*  579:     */      }
/*  580:     */      catch (Exception ex)
/*  581:     */      {
/*  583: 583 */        if (isDiagnosticsEnabled()) {
/*  584: 584 */          logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(ex.getMessage()) + "]. Trying alternative implementations...");
/*  585:     */        }
/*  586:     */      }
/*  587:     */    }
/*  588:     */    
/*  597: 597 */    if (factory == null) {
/*  598: 598 */      if (props != null) {
/*  599: 599 */        if (isDiagnosticsEnabled()) {
/*  600: 600 */          logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
/*  601:     */        }
/*  602:     */        
/*  605: 605 */        String factoryClass = props.getProperty("org.apache.commons.logging.LogFactory");
/*  606: 606 */        if (factoryClass != null) {
/*  607: 607 */          if (isDiagnosticsEnabled()) {
/*  608: 608 */            logDiagnostic("[LOOKUP] Properties file specifies LogFactory subclass '" + factoryClass + "'");
/*  609:     */          }
/*  610:     */          
/*  612: 612 */          factory = newFactory(factoryClass, baseClassLoader, contextClassLoader);
/*  615:     */        }
/*  616: 616 */        else if (isDiagnosticsEnabled()) {
/*  617: 617 */          logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
/*  618:     */        }
/*  619:     */        
/*  621:     */      }
/*  622: 622 */      else if (isDiagnosticsEnabled()) {
/*  623: 623 */        logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
/*  624:     */      }
/*  625:     */    }
/*  626:     */    
/*  633: 633 */    if (factory == null) {
/*  634: 634 */      if (isDiagnosticsEnabled()) {
/*  635: 635 */        logDiagnostic("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader).");
/*  636:     */      }
/*  637:     */      
/*  650: 650 */      factory = newFactory("org.apache.commons.logging.impl.LogFactoryImpl", thisClassLoader, contextClassLoader);
/*  651:     */    }
/*  652:     */    
/*  653: 653 */    if (factory != null)
/*  654:     */    {
/*  657: 657 */      cacheFactory(contextClassLoader, factory);
/*  658:     */      
/*  659: 659 */      if (props != null) {
/*  660: 660 */        Enumeration names = props.propertyNames();
/*  661: 661 */        while (names.hasMoreElements()) {
/*  662: 662 */          String name = (String)names.nextElement();
/*  663: 663 */          String value = props.getProperty(name);
/*  664: 664 */          factory.setAttribute(name, value);
/*  665:     */        }
/*  666:     */      }
/*  667:     */    }
/*  668:     */    
/*  669: 669 */    return factory;
/*  670:     */  }
/*  671:     */  
/*  682:     */  public static Log getLog(Class clazz)
/*  683:     */    throws LogConfigurationException
/*  684:     */  {
/*  685: 685 */    return getFactory().getInstance(clazz);
/*  686:     */  }
/*  687:     */  
/*  701:     */  public static Log getLog(String name)
/*  702:     */    throws LogConfigurationException
/*  703:     */  {
/*  704: 704 */    return getFactory().getInstance(name);
/*  705:     */  }
/*  706:     */  
/*  717:     */  public static void release(ClassLoader classLoader)
/*  718:     */  {
/*  719: 719 */    if (isDiagnosticsEnabled()) {
/*  720: 720 */      logDiagnostic("Releasing factory for classloader " + objectId(classLoader));
/*  721:     */    }
/*  722: 722 */    synchronized (factories) {
/*  723: 723 */      if (classLoader == null) {
/*  724: 724 */        if (nullClassLoaderFactory != null) {
/*  725: 725 */          nullClassLoaderFactory.release();
/*  726: 726 */          nullClassLoaderFactory = null;
/*  727:     */        }
/*  728:     */      } else {
/*  729: 729 */        LogFactory factory = (LogFactory)factories.get(classLoader);
/*  730: 730 */        if (factory != null) {
/*  731: 731 */          factory.release();
/*  732: 732 */          factories.remove(classLoader);
/*  733:     */        }
/*  734:     */      }
/*  735:     */    }
/*  736:     */  }
/*  737:     */  
/*  748:     */  public static void releaseAll()
/*  749:     */  {
/*  750: 750 */    if (isDiagnosticsEnabled()) {
/*  751: 751 */      logDiagnostic("Releasing factory for all classloaders.");
/*  752:     */    }
/*  753: 753 */    synchronized (factories) {
/*  754: 754 */      Enumeration elements = factories.elements();
/*  755: 755 */      while (elements.hasMoreElements()) {
/*  756: 756 */        LogFactory element = (LogFactory)elements.nextElement();
/*  757: 757 */        element.release();
/*  758:     */      }
/*  759: 759 */      factories.clear();
/*  760:     */      
/*  761: 761 */      if (nullClassLoaderFactory != null) {
/*  762: 762 */        nullClassLoaderFactory.release();
/*  763: 763 */        nullClassLoaderFactory = null;
/*  764:     */      }
/*  765:     */    }
/*  766:     */  }
/*  767:     */  
/*  797:     */  protected static ClassLoader getClassLoader(Class clazz)
/*  798:     */  {
/*  799:     */    try
/*  800:     */    {
/*  801: 801 */      return clazz.getClassLoader();
/*  802:     */    } catch (SecurityException ex) {
/*  803: 803 */      if (isDiagnosticsEnabled()) {
/*  804: 804 */        logDiagnostic("Unable to get classloader for class '" + clazz + "' due to security restrictions - " + ex.getMessage());
/*  805:     */      }
/*  806:     */      
/*  808: 808 */      throw ex;
/*  809:     */    }
/*  810:     */  }
/*  811:     */  
/*  833:     */  protected static ClassLoader getContextClassLoader()
/*  834:     */    throws LogConfigurationException
/*  835:     */  {
/*  836: 836 */    return directGetContextClassLoader();
/*  837:     */  }
/*  838:     */  
/*  856:     */  private static ClassLoader getContextClassLoaderInternal()
/*  857:     */    throws LogConfigurationException
/*  858:     */  {
/*  859: 859 */    (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
/*  860:     */    {
/*  861:     */      public Object run() {
/*  862: 862 */        return LogFactory.directGetContextClassLoader();
/*  863:     */      }
/*  864:     */    });
/*  865:     */  }
/*  866:     */  
/*  889:     */  protected static ClassLoader directGetContextClassLoader()
/*  890:     */    throws LogConfigurationException
/*  891:     */  {
/*  892: 892 */    ClassLoader classLoader = null;
/*  893:     */    
/*  894:     */    try
/*  895:     */    {
/*  896: 896 */      Method method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
/*  897:     */      
/*  899:     */      try
/*  900:     */      {
/*  901: 901 */        classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
/*  902:     */      }
/*  903:     */      catch (IllegalAccessException e) {
/*  904: 904 */        throw new LogConfigurationException("Unexpected IllegalAccessException", e);
/*  913:     */      }
/*  914:     */      catch (InvocationTargetException e)
/*  915:     */      {
/*  923: 923 */        if (!(e.getTargetException() instanceof SecurityException))
/*  924:     */        {
/*  928: 928 */          throw new LogConfigurationException("Unexpected InvocationTargetException", e.getTargetException());
/*  929:     */        }
/*  930:     */      }
/*  931:     */    }
/*  932:     */    catch (NoSuchMethodException e)
/*  933:     */    {
/*  934: 934 */      classLoader = getClassLoader(LogFactory.class);
/*  935:     */    }
/*  936:     */    
/*  951: 951 */    return classLoader;
/*  952:     */  }
/*  953:     */  
/*  968:     */  private static LogFactory getCachedFactory(ClassLoader contextClassLoader)
/*  969:     */  {
/*  970: 970 */    LogFactory factory = null;
/*  971:     */    
/*  972: 972 */    if (contextClassLoader == null)
/*  973:     */    {
/*  977: 977 */      factory = nullClassLoaderFactory;
/*  978:     */    } else {
/*  979: 979 */      factory = (LogFactory)factories.get(contextClassLoader);
/*  980:     */    }
/*  981:     */    
/*  982: 982 */    return factory;
/*  983:     */  }
/*  984:     */  
/*  998:     */  private static void cacheFactory(ClassLoader classLoader, LogFactory factory)
/*  999:     */  {
/* 1000:1000 */    if (factory != null) {
/* 1001:1001 */      if (classLoader == null) {
/* 1002:1002 */        nullClassLoaderFactory = factory;
/* 1003:     */      } else {
/* 1004:1004 */        factories.put(classLoader, factory);
/* 1005:     */      }
/* 1006:     */    }
/* 1007:     */  }
/* 1008:     */  
/* 1059:     */  protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader, ClassLoader contextClassLoader)
/* 1060:     */    throws LogConfigurationException
/* 1061:     */  {
/* 1062:1062 */    Object result = AccessController.doPrivileged(new PrivilegedAction() { private final String val$factoryClass;
/* 1063:     */      private final ClassLoader val$classLoader;
/* 1064:     */      
/* 1065:1065 */      public Object run() { return LogFactory.createFactory(this.val$factoryClass, this.val$classLoader); }
/* 1066:     */    });
/* 1067:     */    
/* 1069:1069 */    if ((result instanceof LogConfigurationException)) {
/* 1070:1070 */      LogConfigurationException ex = (LogConfigurationException)result;
/* 1071:1071 */      if (isDiagnosticsEnabled()) {
/* 1072:1072 */        logDiagnostic("An error occurred while loading the factory class:" + ex.getMessage());
/* 1073:     */      }
/* 1074:     */      
/* 1076:1076 */      throw ex;
/* 1077:     */    }
/* 1078:1078 */    if (isDiagnosticsEnabled()) {
/* 1079:1079 */      logDiagnostic("Created object " + objectId(result) + " to manage classloader " + objectId(contextClassLoader));
/* 1080:     */    }
/* 1081:     */    
/* 1083:1083 */    return (LogFactory)result;
/* 1084:     */  }
/* 1085:     */  
/* 1101:     */  protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader)
/* 1102:     */  {
/* 1103:1103 */    return newFactory(factoryClass, classLoader, null);
/* 1104:     */  }
/* 1105:     */  
/* 1121:     */  protected static Object createFactory(String factoryClass, ClassLoader classLoader)
/* 1122:     */  {
/* 1123:1123 */    Class logFactoryClass = null;
/* 1124:     */    try {
/* 1125:1125 */      if (classLoader != null)
/* 1126:     */      {
/* 1128:     */        try
/* 1129:     */        {
/* 1131:1131 */          logFactoryClass = classLoader.loadClass(factoryClass);
/* 1132:1132 */          if (LogFactory.class.isAssignableFrom(logFactoryClass)) {
/* 1133:1133 */            if (isDiagnosticsEnabled()) {
/* 1134:1134 */              logDiagnostic("Loaded class " + logFactoryClass.getName() + " from classloader " + objectId(classLoader));
/* 1141:     */            }
/* 1142:     */            
/* 1149:     */          }
/* 1150:1150 */          else if (isDiagnosticsEnabled()) {
/* 1151:1151 */            logDiagnostic("Factory class " + logFactoryClass.getName() + " loaded from classloader " + objectId(logFactoryClass.getClassLoader()) + " does not extend '" + LogFactory.class.getName() + "' as loaded by this classloader.");
/* 1152:     */            
/* 1156:1156 */            logHierarchy("[BAD CL TREE] ", classLoader);
/* 1157:     */          }
/* 1158:     */          
/* 1160:1160 */          return (LogFactory)logFactoryClass.newInstance();
/* 1161:     */        }
/* 1162:     */        catch (ClassNotFoundException ex) {
/* 1163:1163 */          if (classLoader == thisClassLoader)
/* 1164:     */          {
/* 1165:1165 */            if (isDiagnosticsEnabled()) {
/* 1166:1166 */              logDiagnostic("Unable to locate any class called '" + factoryClass + "' via classloader " + objectId(classLoader));
/* 1167:     */            }
/* 1168:     */            
/* 1170:1170 */            throw ex;
/* 1171:     */          }
/* 1172:     */        }
/* 1173:     */        catch (NoClassDefFoundError e) {
/* 1174:1174 */          if (classLoader == thisClassLoader)
/* 1175:     */          {
/* 1176:1176 */            if (isDiagnosticsEnabled()) {
/* 1177:1177 */              logDiagnostic("Class '" + factoryClass + "' cannot be loaded" + " via classloader " + objectId(classLoader) + " - it depends on some other class that cannot" + " be found.");
/* 1178:     */            }
/* 1179:     */            
/* 1183:1183 */            throw e;
/* 1184:     */          }
/* 1185:     */        }
/* 1186:     */        catch (ClassCastException e) {
/* 1187:1187 */          if (classLoader == thisClassLoader)
/* 1188:     */          {
/* 1193:1193 */            boolean implementsLogFactory = implementsLogFactory(logFactoryClass);
/* 1194:     */            
/* 1200:1200 */            String msg = "The application has specified that a custom LogFactory implementation should be used but Class '" + factoryClass + "' cannot be converted to '" + LogFactory.class.getName() + "'. ";
/* 1201:     */            
/* 1204:1204 */            if (implementsLogFactory) {
/* 1205:1205 */              msg = msg + "The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. " + "Background can be found in http://commons.apache.org/logging/tech.html. " + "If you have not explicitly specified a custom LogFactory then it is likely that " + "the container has set one without your knowledge. " + "In this case, consider using the commons-logging-adapters.jar file or " + "specifying the standard LogFactory from the command line. ";
/* 1208:     */            }
/* 1209:     */            else
/* 1210:     */            {
/* 1212:1212 */              msg = msg + "Please check the custom implementation. ";
/* 1213:     */            }
/* 1214:1214 */            msg = msg + "Help can be found @http://commons.apache.org/logging/troubleshooting.html.";
/* 1215:     */            
/* 1216:1216 */            if (isDiagnosticsEnabled()) {
/* 1217:1217 */              logDiagnostic(msg);
/* 1218:     */            }
/* 1219:     */            
/* 1220:1220 */            ClassCastException ex = new ClassCastException(msg);
/* 1221:1221 */            throw ex;
/* 1222:     */          }
/* 1223:     */        }
/* 1224:     */      }
/* 1225:     */      
/* 1255:1255 */      if (isDiagnosticsEnabled()) {
/* 1256:1256 */        logDiagnostic("Unable to load factory class via classloader " + objectId(classLoader) + " - trying the classloader associated with this LogFactory.");
/* 1257:     */      }
/* 1258:     */      
/* 1261:1261 */      logFactoryClass = Class.forName(factoryClass);
/* 1262:1262 */      return (LogFactory)logFactoryClass.newInstance();
/* 1263:     */    }
/* 1264:     */    catch (Exception e) {
/* 1265:1265 */      if (isDiagnosticsEnabled()) {
/* 1266:1266 */        logDiagnostic("Unable to create LogFactory instance.");
/* 1267:     */      }
/* 1268:1268 */      if ((logFactoryClass != null) && (!LogFactory.class.isAssignableFrom(logFactoryClass)))
/* 1269:     */      {
/* 1271:1271 */        return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", e);
/* 1272:     */      }
/* 1273:     */      
/* 1276:1276 */      return new LogConfigurationException(e);
/* 1277:     */    }
/* 1278:     */  }
/* 1279:     */  
/* 1291:     */  private static boolean implementsLogFactory(Class logFactoryClass)
/* 1292:     */  {
/* 1293:1293 */    boolean implementsLogFactory = false;
/* 1294:1294 */    if (logFactoryClass != null) {
/* 1295:     */      try {
/* 1296:1296 */        ClassLoader logFactoryClassLoader = logFactoryClass.getClassLoader();
/* 1297:1297 */        if (logFactoryClassLoader == null) {
/* 1298:1298 */          logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
/* 1299:     */        } else {
/* 1300:1300 */          logHierarchy("[CUSTOM LOG FACTORY] ", logFactoryClassLoader);
/* 1301:1301 */          Class factoryFromCustomLoader = Class.forName("org.apache.commons.logging.LogFactory", false, logFactoryClassLoader);
/* 1302:     */          
/* 1303:1303 */          implementsLogFactory = factoryFromCustomLoader.isAssignableFrom(logFactoryClass);
/* 1304:1304 */          if (implementsLogFactory) {
/* 1305:1305 */            logDiagnostic("[CUSTOM LOG FACTORY] " + logFactoryClass.getName() + " implements LogFactory but was loaded by an incompatible classloader.");
/* 1306:     */          }
/* 1307:     */          else {
/* 1308:1308 */            logDiagnostic("[CUSTOM LOG FACTORY] " + logFactoryClass.getName() + " does not implement LogFactory.");
/* 1309:     */          }
/* 1310:     */          
/* 1311:     */        }
/* 1312:     */        
/* 1314:     */      }
/* 1315:     */      catch (SecurityException e)
/* 1316:     */      {
/* 1318:1318 */        logDiagnostic("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + e.getMessage());
/* 1322:     */      }
/* 1323:     */      catch (LinkageError e)
/* 1324:     */      {
/* 1328:1328 */        logDiagnostic("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + e.getMessage());
/* 1333:     */      }
/* 1334:     */      catch (ClassNotFoundException e)
/* 1335:     */      {
/* 1339:1339 */        logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
/* 1340:     */      }
/* 1341:     */    }
/* 1342:     */    
/* 1343:1343 */    return implementsLogFactory;
/* 1344:     */  }
/* 1345:     */  
/* 1353:     */  private static InputStream getResourceAsStream(ClassLoader loader, String name)
/* 1354:     */  {
/* 1355:1355 */    (InputStream)AccessController.doPrivileged(new PrivilegedAction() { private final ClassLoader val$loader;
/* 1356:     */      private final String val$name;
/* 1357:     */      
/* 1358:1358 */      public Object run() { if (this.val$loader != null) {
/* 1359:1359 */          return this.val$loader.getResourceAsStream(this.val$name);
/* 1360:     */        }
/* 1361:1361 */        return ClassLoader.getSystemResourceAsStream(this.val$name);
/* 1362:     */      }
/* 1363:     */    });
/* 1364:     */  }
/* 1365:     */  
/* 1381:     */  private static Enumeration getResources(ClassLoader loader, String name)
/* 1382:     */  {
/* 1383:1383 */    PrivilegedAction action = new PrivilegedAction() {
/* 1384:     */      private final ClassLoader val$loader;
/* 1385:     */      private final String val$name;
/* 1386:     */      
/* 1387:1387 */      public Object run() { try { if (this.val$loader != null) {
/* 1388:1388 */            return this.val$loader.getResources(this.val$name);
/* 1389:     */          }
/* 1390:1390 */          return ClassLoader.getSystemResources(this.val$name);
/* 1391:     */        }
/* 1392:     */        catch (IOException e) {
/* 1393:1393 */          if (LogFactory.isDiagnosticsEnabled()) {
/* 1394:1394 */            LogFactory.logDiagnostic("Exception while trying to find configuration file " + this.val$name + ":" + e.getMessage());
/* 1395:     */          }
/* 1396:     */          
/* 1398:1398 */          return null;
/* 1399:     */        }
/* 1400:     */        catch (NoSuchMethodError e) {}
/* 1401:     */        
/* 1403:1403 */        return null;
/* 1404:     */      }
/* 1405:     */      
/* 1406:1406 */    };
/* 1407:1407 */    Object result = AccessController.doPrivileged(action);
/* 1408:1408 */    return (Enumeration)result;
/* 1409:     */  }
/* 1410:     */  
/* 1418:     */  private static Properties getProperties(URL url)
/* 1419:     */  {
/* 1420:1420 */    PrivilegedAction action = new PrivilegedAction() {
/* 1421:     */      private final URL val$url;
/* 1422:     */      
/* 1423:     */      public Object run() {
/* 1424:1424 */        try { InputStream stream = this.val$url.openStream();
/* 1425:1425 */          if (stream != null) {
/* 1426:1426 */            Properties props = new Properties();
/* 1427:1427 */            props.load(stream);
/* 1428:1428 */            stream.close();
/* 1429:1429 */            return props;
/* 1430:     */          }
/* 1431:     */        } catch (IOException e) {
/* 1432:1432 */          if (LogFactory.isDiagnosticsEnabled()) {
/* 1433:1433 */            LogFactory.logDiagnostic("Unable to read URL " + this.val$url);
/* 1434:     */          }
/* 1435:     */        }
/* 1436:     */        
/* 1437:1437 */        return null;
/* 1438:     */      }
/* 1439:1439 */    };
/* 1440:1440 */    return (Properties)AccessController.doPrivileged(action);
/* 1441:     */  }
/* 1442:     */  
/* 1463:     */  private static final Properties getConfigurationFile(ClassLoader classLoader, String fileName)
/* 1464:     */  {
/* 1465:1465 */    Properties props = null;
/* 1466:1466 */    double priority = 0.0D;
/* 1467:1467 */    URL propsUrl = null;
/* 1468:     */    try {
/* 1469:1469 */      Enumeration urls = getResources(classLoader, fileName);
/* 1470:     */      
/* 1471:1471 */      if (urls == null) {
/* 1472:1472 */        return null;
/* 1473:     */      }
/* 1474:     */      
/* 1475:1475 */      while (urls.hasMoreElements()) {
/* 1476:1476 */        URL url = (URL)urls.nextElement();
/* 1477:     */        
/* 1478:1478 */        Properties newProps = getProperties(url);
/* 1479:1479 */        if (newProps != null) {
/* 1480:1480 */          if (props == null) {
/* 1481:1481 */            propsUrl = url;
/* 1482:1482 */            props = newProps;
/* 1483:1483 */            String priorityStr = props.getProperty("priority");
/* 1484:1484 */            priority = 0.0D;
/* 1485:1485 */            if (priorityStr != null) {
/* 1486:1486 */              priority = Double.parseDouble(priorityStr);
/* 1487:     */            }
/* 1488:     */            
/* 1489:1489 */            if (isDiagnosticsEnabled()) {
/* 1490:1490 */              logDiagnostic("[LOOKUP] Properties file found at '" + url + "'" + " with priority " + priority);
/* 1491:     */            }
/* 1492:     */          }
/* 1493:     */          else
/* 1494:     */          {
/* 1495:1495 */            String newPriorityStr = newProps.getProperty("priority");
/* 1496:1496 */            double newPriority = 0.0D;
/* 1497:1497 */            if (newPriorityStr != null) {
/* 1498:1498 */              newPriority = Double.parseDouble(newPriorityStr);
/* 1499:     */            }
/* 1500:     */            
/* 1501:1501 */            if (newPriority > priority) {
/* 1502:1502 */              if (isDiagnosticsEnabled()) {
/* 1503:1503 */                logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " overrides file at '" + propsUrl + "'" + " with priority " + priority);
/* 1504:     */              }
/* 1505:     */              
/* 1510:1510 */              propsUrl = url;
/* 1511:1511 */              props = newProps;
/* 1512:1512 */              priority = newPriority;
/* 1513:     */            }
/* 1514:1514 */            else if (isDiagnosticsEnabled()) {
/* 1515:1515 */              logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " does not override file at '" + propsUrl + "'" + " with priority " + priority);
/* 1516:     */            }
/* 1517:     */            
/* 1518:     */          }
/* 1519:     */          
/* 1520:     */        }
/* 1521:     */        
/* 1522:     */      }
/* 1523:     */      
/* 1524:     */    }
/* 1525:     */    catch (SecurityException e)
/* 1526:     */    {
/* 1527:1527 */      if (isDiagnosticsEnabled()) {
/* 1528:1528 */        logDiagnostic("SecurityException thrown while trying to find/read config files.");
/* 1529:     */      }
/* 1530:     */    }
/* 1531:     */    
/* 1532:1532 */    if (isDiagnosticsEnabled()) {
/* 1533:1533 */      if (props == null) {
/* 1534:1534 */        logDiagnostic("[LOOKUP] No properties file of name '" + fileName + "' found.");
/* 1535:     */      }
/* 1536:     */      else
/* 1537:     */      {
/* 1538:1538 */        logDiagnostic("[LOOKUP] Properties file of name '" + fileName + "' found at '" + propsUrl + '"');
/* 1539:     */      }
/* 1540:     */    }
/* 1541:     */    
/* 1544:1544 */    return props;
/* 1545:     */  }
/* 1546:     */  
/* 1555:     */  private static String getSystemProperty(String key, String def)
/* 1556:     */    throws SecurityException
/* 1557:     */  {
/* 1558:1558 */    (String)AccessController.doPrivileged(new PrivilegedAction() { private final String val$key;
/* 1559:     */      private final String val$def;
/* 1560:     */      
/* 1561:1561 */      public Object run() { return System.getProperty(this.val$key, this.val$def); }
/* 1562:     */    });
/* 1563:     */  }
/* 1564:     */  
/* 1571:     */  private static void initDiagnostics()
/* 1572:     */  {
/* 1573:     */    try
/* 1574:     */    {
/* 1575:1575 */      String dest = getSystemProperty("org.apache.commons.logging.diagnostics.dest", null);
/* 1576:1576 */      if (dest == null) {
/* 1577:     */        return;
/* 1578:     */      }
/* 1579:     */    }
/* 1580:     */    catch (SecurityException ex)
/* 1581:     */    {
/* 1582:     */      return;
/* 1583:     */    }
/* 1584:     */    String dest;
/* 1585:1585 */    if (dest.equals("STDOUT")) {
/* 1586:1586 */      diagnosticsStream = System.out;
/* 1587:1587 */    } else if (dest.equals("STDERR")) {
/* 1588:1588 */      diagnosticsStream = System.err;
/* 1589:     */    } else {
/* 1590:     */      try
/* 1591:     */      {
/* 1592:1592 */        FileOutputStream fos = new FileOutputStream(dest, true);
/* 1593:1593 */        diagnosticsStream = new PrintStream(fos);
/* 1594:     */      }
/* 1595:     */      catch (IOException ex)
/* 1596:     */      {
/* 1597:     */        return;
/* 1598:     */      }
/* 1599:     */    }
/* 1600:     */    
/* 1604:     */    String classLoaderName;
/* 1605:     */    
/* 1609:     */    try
/* 1610:     */    {
/* 1611:1611 */      ClassLoader classLoader = thisClassLoader;
/* 1612:1612 */      String classLoaderName; if (thisClassLoader == null) {
/* 1613:1613 */        classLoaderName = "BOOTLOADER";
/* 1614:     */      } else
/* 1615:1615 */        classLoaderName = objectId(classLoader);
/* 1616:     */    } catch (SecurityException e) {
/* 1617:     */      String classLoaderName;
/* 1618:1618 */      classLoaderName = "UNKNOWN";
/* 1619:     */    }
/* 1620:1620 */    diagnosticPrefix = "[LogFactory from " + classLoaderName + "] ";
/* 1621:     */  }
/* 1622:     */  
/* 1631:     */  protected static boolean isDiagnosticsEnabled()
/* 1632:     */  {
/* 1633:1633 */    return diagnosticsStream != null;
/* 1634:     */  }
/* 1635:     */  
/* 1653:     */  private static final void logDiagnostic(String msg)
/* 1654:     */  {
/* 1655:1655 */    if (diagnosticsStream != null) {
/* 1656:1656 */      diagnosticsStream.print(diagnosticPrefix);
/* 1657:1657 */      diagnosticsStream.println(msg);
/* 1658:1658 */      diagnosticsStream.flush();
/* 1659:     */    }
/* 1660:     */  }
/* 1661:     */  
/* 1667:     */  protected static final void logRawDiagnostic(String msg)
/* 1668:     */  {
/* 1669:1669 */    if (diagnosticsStream != null) {
/* 1670:1670 */      diagnosticsStream.println(msg);
/* 1671:1671 */      diagnosticsStream.flush();
/* 1672:     */    }
/* 1673:     */  }
/* 1674:     */  
/* 1691:     */  private static void logClassLoaderEnvironment(Class clazz)
/* 1692:     */  {
/* 1693:1693 */    if (!isDiagnosticsEnabled()) {
/* 1694:1694 */      return;
/* 1695:     */    }
/* 1696:     */    
/* 1699:     */    try
/* 1700:     */    {
/* 1701:1701 */      logDiagnostic("[ENV] Extension directories (java.ext.dir): " + System.getProperty("java.ext.dir"));
/* 1702:1702 */      logDiagnostic("[ENV] Application classpath (java.class.path): " + System.getProperty("java.class.path"));
/* 1703:     */    } catch (SecurityException ex) {
/* 1704:1704 */      logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
/* 1705:     */    }
/* 1706:     */    
/* 1707:1707 */    String className = clazz.getName();
/* 1708:     */    
/* 1709:     */    try
/* 1710:     */    {
/* 1711:1711 */      classLoader = getClassLoader(clazz);
/* 1712:     */    } catch (SecurityException ex) {
/* 1713:     */      ClassLoader classLoader;
/* 1714:1714 */      logDiagnostic("[ENV] Security forbids determining the classloader for " + className); return;
/* 1715:     */    }
/* 1716:     */    
/* 1717:     */    ClassLoader classLoader;
/* 1718:     */    
/* 1719:1719 */    logDiagnostic("[ENV] Class " + className + " was loaded via classloader " + objectId(classLoader));
/* 1720:     */    
/* 1722:1722 */    logHierarchy("[ENV] Ancestry of classloader which loaded " + className + " is ", classLoader);
/* 1723:     */  }
/* 1724:     */  
/* 1731:     */  private static void logHierarchy(String prefix, ClassLoader classLoader)
/* 1732:     */  {
/* 1733:1733 */    if (!isDiagnosticsEnabled()) {
/* 1734:1734 */      return;
/* 1735:     */    }
/* 1736:     */    
/* 1737:1737 */    if (classLoader != null) {
/* 1738:1738 */      String classLoaderString = classLoader.toString();
/* 1739:1739 */      logDiagnostic(prefix + objectId(classLoader) + " == '" + classLoaderString + "'");
/* 1740:     */    }
/* 1741:     */    try
/* 1742:     */    {
/* 1743:1743 */      systemClassLoader = ClassLoader.getSystemClassLoader();
/* 1744:     */    } catch (SecurityException ex) { ClassLoader systemClassLoader;
/* 1745:1745 */      logDiagnostic(prefix + "Security forbids determining the system classloader."); return;
/* 1746:     */    }
/* 1747:     */    
/* 1748:     */    ClassLoader systemClassLoader;
/* 1749:1749 */    if (classLoader != null) {
/* 1750:1750 */      StringBuffer buf = new StringBuffer(prefix + "ClassLoader tree:");
/* 1751:     */      do {
/* 1752:1752 */        buf.append(objectId(classLoader));
/* 1753:1753 */        if (classLoader == systemClassLoader) {
/* 1754:1754 */          buf.append(" (SYSTEM) ");
/* 1755:     */        }
/* 1756:     */        try
/* 1757:     */        {
/* 1758:1758 */          classLoader = classLoader.getParent();
/* 1759:     */        } catch (SecurityException ex) {
/* 1760:1760 */          buf.append(" --> SECRET");
/* 1761:1761 */          break;
/* 1762:     */        }
/* 1763:     */        
/* 1764:1764 */        buf.append(" --> ");
/* 1765:1765 */      } while (classLoader != null);
/* 1766:1766 */      buf.append("BOOT");
/* 1767:     */      
/* 1770:1770 */      logDiagnostic(buf.toString());
/* 1771:     */    }
/* 1772:     */  }
/* 1773:     */  
/* 1785:     */  public static String objectId(Object o)
/* 1786:     */  {
/* 1787:1787 */    if (o == null) {
/* 1788:1788 */      return "null";
/* 1789:     */    }
/* 1790:1790 */    return o.getClass().getName() + "@" + System.identityHashCode(o);
/* 1791:     */  }
/* 1792:     */  
/* 1814:     */  static
/* 1815:     */  {
/* 1816:1816 */    thisClassLoader = getClassLoader(LogFactory.class);
/* 1817:1817 */    initDiagnostics();
/* 1818:1818 */    logClassLoaderEnvironment(LogFactory.class);
/* 1819:1819 */    factories = createFactoryStore();
/* 1820:1820 */    if (isDiagnosticsEnabled()) {
/* 1821:1821 */      logDiagnostic("BOOTSTRAP COMPLETED");
/* 1822:     */    }
/* 1823:     */  }
/* 1824:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.LogFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */