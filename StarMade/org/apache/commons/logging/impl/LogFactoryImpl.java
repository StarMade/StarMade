/*    1:     */package org.apache.commons.logging.impl;
/*    2:     */
/*    3:     */import java.lang.reflect.Constructor;
/*    4:     */import java.lang.reflect.InvocationTargetException;
/*    5:     */import java.lang.reflect.Method;
/*    6:     */import java.net.URL;
/*    7:     */import java.security.AccessController;
/*    8:     */import java.security.PrivilegedAction;
/*    9:     */import java.util.Enumeration;
/*   10:     */import java.util.Hashtable;
/*   11:     */import java.util.Vector;
/*   12:     */import org.apache.commons.logging.Log;
/*   13:     */import org.apache.commons.logging.LogConfigurationException;
/*   14:     */import org.apache.commons.logging.LogFactory;
/*   15:     */
/*   76:     */public class LogFactoryImpl
/*   77:     */  extends LogFactory
/*   78:     */{
/*   79:     */  private static final String LOGGING_IMPL_LOG4J_LOGGER = "org.apache.commons.logging.impl.Log4JLogger";
/*   80:     */  private static final String LOGGING_IMPL_JDK14_LOGGER = "org.apache.commons.logging.impl.Jdk14Logger";
/*   81:     */  private static final String LOGGING_IMPL_LUMBERJACK_LOGGER = "org.apache.commons.logging.impl.Jdk13LumberjackLogger";
/*   82:     */  private static final String LOGGING_IMPL_SIMPLE_LOGGER = "org.apache.commons.logging.impl.SimpleLog";
/*   83:     */  private static final String PKG_IMPL = "org.apache.commons.logging.impl.";
/*   84:  84 */  private static final int PKG_LEN = "org.apache.commons.logging.impl.".length();
/*   85:     */  
/*   86:     */  public static final String LOG_PROPERTY = "org.apache.commons.logging.Log";
/*   87:     */  
/*   88:     */  protected static final String LOG_PROPERTY_OLD = "org.apache.commons.logging.log";
/*   89:     */  public static final String ALLOW_FLAWED_CONTEXT_PROPERTY = "org.apache.commons.logging.Log.allowFlawedContext";
/*   90:     */  public static final String ALLOW_FLAWED_DISCOVERY_PROPERTY = "org.apache.commons.logging.Log.allowFlawedDiscovery";
/*   91:     */  public static final String ALLOW_FLAWED_HIERARCHY_PROPERTY = "org.apache.commons.logging.Log.allowFlawedHierarchy";
/*   92:     */  
/*   93:     */  public LogFactoryImpl()
/*   94:     */  {
/*   95:  95 */    initDiagnostics();
/*   96:  96 */    if (isDiagnosticsEnabled()) {
/*   97:  97 */      logDiagnostic("Instance created.");
/*   98:     */    }
/*   99:     */  }
/*  100:     */  
/*  176: 176 */  private static final String[] classesToDiscover = { "org.apache.commons.logging.impl.Log4JLogger", "org.apache.commons.logging.impl.Jdk14Logger", "org.apache.commons.logging.impl.Jdk13LumberjackLogger", "org.apache.commons.logging.impl.SimpleLog" };
/*  177:     */  
/*  190: 190 */  private boolean useTCCL = true;
/*  191:     */  
/*  196:     */  private String diagnosticPrefix;
/*  197:     */  
/*  201: 201 */  protected Hashtable attributes = new Hashtable();
/*  202:     */  
/*  208: 208 */  protected Hashtable instances = new Hashtable();
/*  209:     */  
/*  216:     */  private String logClassName;
/*  217:     */  
/*  224: 224 */  protected Constructor logConstructor = null;
/*  225:     */  
/*  230: 230 */  protected Class[] logConstructorSignature = { String.class };
/*  231:     */  
/*  238: 238 */  protected Method logMethod = null;
/*  239:     */  
/*  244: 244 */  protected Class[] logMethodSignature = { LogFactory.class };
/*  245:     */  
/*  251:     */  private boolean allowFlawedContext;
/*  252:     */  
/*  258:     */  private boolean allowFlawedDiscovery;
/*  259:     */  
/*  264:     */  private boolean allowFlawedHierarchy;
/*  265:     */  
/*  271:     */  public Object getAttribute(String name)
/*  272:     */  {
/*  273: 273 */    return this.attributes.get(name);
/*  274:     */  }
/*  275:     */  
/*  283:     */  public String[] getAttributeNames()
/*  284:     */  {
/*  285: 285 */    Vector names = new Vector();
/*  286: 286 */    Enumeration keys = this.attributes.keys();
/*  287: 287 */    while (keys.hasMoreElements()) {
/*  288: 288 */      names.addElement((String)keys.nextElement());
/*  289:     */    }
/*  290: 290 */    String[] results = new String[names.size()];
/*  291: 291 */    for (int i = 0; i < results.length; i++) {
/*  292: 292 */      results[i] = ((String)names.elementAt(i));
/*  293:     */    }
/*  294: 294 */    return results;
/*  295:     */  }
/*  296:     */  
/*  307:     */  public Log getInstance(Class clazz)
/*  308:     */    throws LogConfigurationException
/*  309:     */  {
/*  310: 310 */    return getInstance(clazz.getName());
/*  311:     */  }
/*  312:     */  
/*  331:     */  public Log getInstance(String name)
/*  332:     */    throws LogConfigurationException
/*  333:     */  {
/*  334: 334 */    Log instance = (Log)this.instances.get(name);
/*  335: 335 */    if (instance == null) {
/*  336: 336 */      instance = newInstance(name);
/*  337: 337 */      this.instances.put(name, instance);
/*  338:     */    }
/*  339: 339 */    return instance;
/*  340:     */  }
/*  341:     */  
/*  352:     */  public void release()
/*  353:     */  {
/*  354: 354 */    logDiagnostic("Releasing all known loggers");
/*  355: 355 */    this.instances.clear();
/*  356:     */  }
/*  357:     */  
/*  365:     */  public void removeAttribute(String name)
/*  366:     */  {
/*  367: 367 */    this.attributes.remove(name);
/*  368:     */  }
/*  369:     */  
/*  396:     */  public void setAttribute(String name, Object value)
/*  397:     */  {
/*  398: 398 */    if (this.logConstructor != null) {
/*  399: 399 */      logDiagnostic("setAttribute: call too late; configuration already performed.");
/*  400:     */    }
/*  401:     */    
/*  402: 402 */    if (value == null) {
/*  403: 403 */      this.attributes.remove(name);
/*  404:     */    } else {
/*  405: 405 */      this.attributes.put(name, value);
/*  406:     */    }
/*  407:     */    
/*  408: 408 */    if (name.equals("use_tccl")) {
/*  409: 409 */      this.useTCCL = Boolean.valueOf(value.toString()).booleanValue();
/*  410:     */    }
/*  411:     */  }
/*  412:     */  
/*  425:     */  protected static ClassLoader getContextClassLoader()
/*  426:     */    throws LogConfigurationException
/*  427:     */  {
/*  428: 428 */    return LogFactory.getContextClassLoader();
/*  429:     */  }
/*  430:     */  
/*  435:     */  protected static boolean isDiagnosticsEnabled()
/*  436:     */  {
/*  437: 437 */    return LogFactory.isDiagnosticsEnabled();
/*  438:     */  }
/*  439:     */  
/*  445:     */  protected static ClassLoader getClassLoader(Class clazz)
/*  446:     */  {
/*  447: 447 */    return LogFactory.getClassLoader(clazz);
/*  448:     */  }
/*  449:     */  
/*  473:     */  private void initDiagnostics()
/*  474:     */  {
/*  475: 475 */    Class clazz = getClass();
/*  476: 476 */    ClassLoader classLoader = getClassLoader(clazz);
/*  477:     */    String classLoaderName;
/*  478:     */    try { String classLoaderName;
/*  479: 479 */      if (classLoader == null) {
/*  480: 480 */        classLoaderName = "BOOTLOADER";
/*  481:     */      } else
/*  482: 482 */        classLoaderName = LogFactory.objectId(classLoader);
/*  483:     */    } catch (SecurityException e) {
/*  484:     */      String classLoaderName;
/*  485: 485 */      classLoaderName = "UNKNOWN";
/*  486:     */    }
/*  487: 487 */    this.diagnosticPrefix = ("[LogFactoryImpl@" + System.identityHashCode(this) + " from " + classLoaderName + "] ");
/*  488:     */  }
/*  489:     */  
/*  497:     */  protected void logDiagnostic(String msg)
/*  498:     */  {
/*  499: 499 */    if (isDiagnosticsEnabled()) {
/*  500: 500 */      LogFactory.logRawDiagnostic(this.diagnosticPrefix + msg);
/*  501:     */    }
/*  502:     */  }
/*  503:     */  
/*  508:     */  /**
/*  509:     */   * @deprecated
/*  510:     */   */
/*  511:     */  protected String getLogClassName()
/*  512:     */  {
/*  513: 513 */    if (this.logClassName == null) {
/*  514: 514 */      discoverLogImplementation(getClass().getName());
/*  515:     */    }
/*  516:     */    
/*  517: 517 */    return this.logClassName;
/*  518:     */  }
/*  519:     */  
/*  534:     */  /**
/*  535:     */   * @deprecated
/*  536:     */   */
/*  537:     */  protected Constructor getLogConstructor()
/*  538:     */    throws LogConfigurationException
/*  539:     */  {
/*  540: 540 */    if (this.logConstructor == null) {
/*  541: 541 */      discoverLogImplementation(getClass().getName());
/*  542:     */    }
/*  543:     */    
/*  544: 544 */    return this.logConstructor;
/*  545:     */  }
/*  546:     */  
/*  550:     */  /**
/*  551:     */   * @deprecated
/*  552:     */   */
/*  553:     */  protected boolean isJdk13LumberjackAvailable()
/*  554:     */  {
/*  555: 555 */    return isLogLibraryAvailable("Jdk13Lumberjack", "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
/*  556:     */  }
/*  557:     */  
/*  566:     */  /**
/*  567:     */   * @deprecated
/*  568:     */   */
/*  569:     */  protected boolean isJdk14Available()
/*  570:     */  {
/*  571: 571 */    return isLogLibraryAvailable("Jdk14", "org.apache.commons.logging.impl.Jdk14Logger");
/*  572:     */  }
/*  573:     */  
/*  579:     */  /**
/*  580:     */   * @deprecated
/*  581:     */   */
/*  582:     */  protected boolean isLog4JAvailable()
/*  583:     */  {
/*  584: 584 */    return isLogLibraryAvailable("Log4J", "org.apache.commons.logging.impl.Log4JLogger");
/*  585:     */  }
/*  586:     */  
/*  598:     */  protected Log newInstance(String name)
/*  599:     */    throws LogConfigurationException
/*  600:     */  {
/*  601: 601 */    Log instance = null;
/*  602:     */    try {
/*  603: 603 */      if (this.logConstructor == null) {
/*  604: 604 */        instance = discoverLogImplementation(name);
/*  605:     */      }
/*  606:     */      else {
/*  607: 607 */        Object[] params = { name };
/*  608: 608 */        instance = (Log)this.logConstructor.newInstance(params);
/*  609:     */      }
/*  610:     */      
/*  611: 611 */      if (this.logMethod != null) {
/*  612: 612 */        Object[] params = { this };
/*  613: 613 */        this.logMethod.invoke(instance, params);
/*  614:     */      }
/*  615:     */      
/*  616: 616 */      return instance;
/*  619:     */    }
/*  620:     */    catch (LogConfigurationException lce)
/*  621:     */    {
/*  623: 623 */      throw lce;
/*  625:     */    }
/*  626:     */    catch (InvocationTargetException e)
/*  627:     */    {
/*  628: 628 */      Throwable c = e.getTargetException();
/*  629: 629 */      if (c != null) {
/*  630: 630 */        throw new LogConfigurationException(c);
/*  631:     */      }
/*  632: 632 */      throw new LogConfigurationException(e);
/*  634:     */    }
/*  635:     */    catch (Throwable t)
/*  636:     */    {
/*  637: 637 */      throw new LogConfigurationException(t);
/*  638:     */    }
/*  639:     */  }
/*  640:     */  
/*  661:     */  private static ClassLoader getContextClassLoaderInternal()
/*  662:     */    throws LogConfigurationException
/*  663:     */  {
/*  664: 664 */    (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
/*  665:     */    {
/*  666:     */      public Object run() {
/*  667: 667 */        return LogFactoryImpl.access$000();
/*  668:     */      }
/*  669:     */    });
/*  670:     */  }
/*  671:     */  
/*  680:     */  private static String getSystemProperty(String key, String def)
/*  681:     */    throws SecurityException
/*  682:     */  {
/*  683: 683 */    (String)AccessController.doPrivileged(new PrivilegedAction() { private final String val$key;
/*  684:     */      private final String val$def;
/*  685:     */      
/*  686: 686 */      public Object run() { return System.getProperty(this.val$key, this.val$def); }
/*  687:     */    });
/*  688:     */  }
/*  689:     */  
/*  696:     */  private ClassLoader getParentClassLoader(ClassLoader cl)
/*  697:     */  {
/*  698:     */    try
/*  699:     */    {
/*  700: 700 */      (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
/*  701:     */        private final ClassLoader val$cl;
/*  702:     */        
/*  703: 703 */        public Object run() { return this.val$cl.getParent(); }
/*  704:     */      });
/*  705:     */    }
/*  706:     */    catch (SecurityException ex) {
/*  707: 707 */      logDiagnostic("[SECURITY] Unable to obtain parent classloader"); }
/*  708: 708 */    return null;
/*  709:     */  }
/*  710:     */  
/*  717:     */  private boolean isLogLibraryAvailable(String name, String classname)
/*  718:     */  {
/*  719: 719 */    if (isDiagnosticsEnabled()) {
/*  720: 720 */      logDiagnostic("Checking for '" + name + "'.");
/*  721:     */    }
/*  722:     */    try {
/*  723: 723 */      Log log = createLogFromClass(classname, getClass().getName(), false);
/*  724:     */      
/*  728: 728 */      if (log == null) {
/*  729: 729 */        if (isDiagnosticsEnabled()) {
/*  730: 730 */          logDiagnostic("Did not find '" + name + "'.");
/*  731:     */        }
/*  732: 732 */        return false;
/*  733:     */      }
/*  734: 734 */      if (isDiagnosticsEnabled()) {
/*  735: 735 */        logDiagnostic("Found '" + name + "'.");
/*  736:     */      }
/*  737: 737 */      return true;
/*  738:     */    }
/*  739:     */    catch (LogConfigurationException e) {
/*  740: 740 */      if (isDiagnosticsEnabled())
/*  741: 741 */        logDiagnostic("Logging system '" + name + "' is available but not useable.");
/*  742:     */    }
/*  743: 743 */    return false;
/*  744:     */  }
/*  745:     */  
/*  757:     */  private String getConfigurationValue(String property)
/*  758:     */  {
/*  759: 759 */    if (isDiagnosticsEnabled()) {
/*  760: 760 */      logDiagnostic("[ENV] Trying to get configuration for item " + property);
/*  761:     */    }
/*  762:     */    
/*  763: 763 */    Object valueObj = getAttribute(property);
/*  764: 764 */    if (valueObj != null) {
/*  765: 765 */      if (isDiagnosticsEnabled()) {
/*  766: 766 */        logDiagnostic("[ENV] Found LogFactory attribute [" + valueObj + "] for " + property);
/*  767:     */      }
/*  768: 768 */      return valueObj.toString();
/*  769:     */    }
/*  770:     */    
/*  771: 771 */    if (isDiagnosticsEnabled()) {
/*  772: 772 */      logDiagnostic("[ENV] No LogFactory attribute found for " + property);
/*  773:     */    }
/*  774:     */    
/*  778:     */    try
/*  779:     */    {
/*  780: 780 */      String value = getSystemProperty(property, null);
/*  781: 781 */      if (value != null) {
/*  782: 782 */        if (isDiagnosticsEnabled()) {
/*  783: 783 */          logDiagnostic("[ENV] Found system property [" + value + "] for " + property);
/*  784:     */        }
/*  785: 785 */        return value;
/*  786:     */      }
/*  787:     */      
/*  788: 788 */      if (isDiagnosticsEnabled()) {
/*  789: 789 */        logDiagnostic("[ENV] No system property found for property " + property);
/*  790:     */      }
/*  791:     */    } catch (SecurityException e) {
/*  792: 792 */      if (isDiagnosticsEnabled()) {
/*  793: 793 */        logDiagnostic("[ENV] Security prevented reading system property " + property);
/*  794:     */      }
/*  795:     */    }
/*  796:     */    
/*  797: 797 */    if (isDiagnosticsEnabled()) {
/*  798: 798 */      logDiagnostic("[ENV] No configuration defined for item " + property);
/*  799:     */    }
/*  800:     */    
/*  801: 801 */    return null;
/*  802:     */  }
/*  803:     */  
/*  807:     */  private boolean getBooleanConfiguration(String key, boolean dflt)
/*  808:     */  {
/*  809: 809 */    String val = getConfigurationValue(key);
/*  810: 810 */    if (val == null)
/*  811: 811 */      return dflt;
/*  812: 812 */    return Boolean.valueOf(val).booleanValue();
/*  813:     */  }
/*  814:     */  
/*  821:     */  private void initConfiguration()
/*  822:     */  {
/*  823: 823 */    this.allowFlawedContext = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedContext", true);
/*  824: 824 */    this.allowFlawedDiscovery = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedDiscovery", true);
/*  825: 825 */    this.allowFlawedHierarchy = getBooleanConfiguration("org.apache.commons.logging.Log.allowFlawedHierarchy", true);
/*  826:     */  }
/*  827:     */  
/*  838:     */  private Log discoverLogImplementation(String logCategory)
/*  839:     */    throws LogConfigurationException
/*  840:     */  {
/*  841: 841 */    if (isDiagnosticsEnabled()) {
/*  842: 842 */      logDiagnostic("Discovering a Log implementation...");
/*  843:     */    }
/*  844:     */    
/*  845: 845 */    initConfiguration();
/*  846:     */    
/*  847: 847 */    Log result = null;
/*  848:     */    
/*  850: 850 */    String specifiedLogClassName = findUserSpecifiedLogClassName();
/*  851:     */    
/*  852: 852 */    if (specifiedLogClassName != null) {
/*  853: 853 */      if (isDiagnosticsEnabled()) {
/*  854: 854 */        logDiagnostic("Attempting to load user-specified log class '" + specifiedLogClassName + "'...");
/*  855:     */      }
/*  856:     */      
/*  858: 858 */      result = createLogFromClass(specifiedLogClassName, logCategory, true);
/*  859:     */      
/*  861: 861 */      if (result == null) {
/*  862: 862 */        StringBuffer messageBuffer = new StringBuffer("User-specified log class '");
/*  863: 863 */        messageBuffer.append(specifiedLogClassName);
/*  864: 864 */        messageBuffer.append("' cannot be found or is not useable.");
/*  865:     */        
/*  868: 868 */        if (specifiedLogClassName != null) {
/*  869: 869 */          informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.Log4JLogger");
/*  870: 870 */          informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.Jdk14Logger");
/*  871: 871 */          informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.Jdk13LumberjackLogger");
/*  872: 872 */          informUponSimilarName(messageBuffer, specifiedLogClassName, "org.apache.commons.logging.impl.SimpleLog");
/*  873:     */        }
/*  874: 874 */        throw new LogConfigurationException(messageBuffer.toString());
/*  875:     */      }
/*  876:     */      
/*  877: 877 */      return result;
/*  878:     */    }
/*  879:     */    
/*  908: 908 */    if (isDiagnosticsEnabled()) {
/*  909: 909 */      logDiagnostic("No user-specified Log implementation; performing discovery using the standard supported logging implementations...");
/*  910:     */    }
/*  911:     */    
/*  913: 913 */    for (int i = 0; (i < classesToDiscover.length) && (result == null); i++) {
/*  914: 914 */      result = createLogFromClass(classesToDiscover[i], logCategory, true);
/*  915:     */    }
/*  916:     */    
/*  917: 917 */    if (result == null) {
/*  918: 918 */      throw new LogConfigurationException("No suitable Log implementation");
/*  919:     */    }
/*  920:     */    
/*  922: 922 */    return result;
/*  923:     */  }
/*  924:     */  
/*  933:     */  private void informUponSimilarName(StringBuffer messageBuffer, String name, String candidate)
/*  934:     */  {
/*  935: 935 */    if (name.equals(candidate))
/*  936:     */    {
/*  938: 938 */      return;
/*  939:     */    }
/*  940:     */    
/*  944: 944 */    if (name.regionMatches(true, 0, candidate, 0, PKG_LEN + 5)) {
/*  945: 945 */      messageBuffer.append(" Did you mean '");
/*  946: 946 */      messageBuffer.append(candidate);
/*  947: 947 */      messageBuffer.append("'?");
/*  948:     */    }
/*  949:     */  }
/*  950:     */  
/*  959:     */  private String findUserSpecifiedLogClassName()
/*  960:     */  {
/*  961: 961 */    if (isDiagnosticsEnabled()) {
/*  962: 962 */      logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.Log'");
/*  963:     */    }
/*  964: 964 */    String specifiedClass = (String)getAttribute("org.apache.commons.logging.Log");
/*  965:     */    
/*  966: 966 */    if (specifiedClass == null) {
/*  967: 967 */      if (isDiagnosticsEnabled()) {
/*  968: 968 */        logDiagnostic("Trying to get log class from attribute 'org.apache.commons.logging.log'");
/*  969:     */      }
/*  970:     */      
/*  971: 971 */      specifiedClass = (String)getAttribute("org.apache.commons.logging.log");
/*  972:     */    }
/*  973:     */    
/*  974: 974 */    if (specifiedClass == null) {
/*  975: 975 */      if (isDiagnosticsEnabled()) {
/*  976: 976 */        logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.Log'");
/*  977:     */      }
/*  978:     */      try
/*  979:     */      {
/*  980: 980 */        specifiedClass = getSystemProperty("org.apache.commons.logging.Log", null);
/*  981:     */      } catch (SecurityException e) {
/*  982: 982 */        if (isDiagnosticsEnabled()) {
/*  983: 983 */          logDiagnostic("No access allowed to system property 'org.apache.commons.logging.Log' - " + e.getMessage());
/*  984:     */        }
/*  985:     */      }
/*  986:     */    }
/*  987:     */    
/*  989: 989 */    if (specifiedClass == null) {
/*  990: 990 */      if (isDiagnosticsEnabled()) {
/*  991: 991 */        logDiagnostic("Trying to get log class from system property 'org.apache.commons.logging.log'");
/*  992:     */      }
/*  993:     */      try
/*  994:     */      {
/*  995: 995 */        specifiedClass = getSystemProperty("org.apache.commons.logging.log", null);
/*  996:     */      } catch (SecurityException e) {
/*  997: 997 */        if (isDiagnosticsEnabled()) {
/*  998: 998 */          logDiagnostic("No access allowed to system property 'org.apache.commons.logging.log' - " + e.getMessage());
/*  999:     */        }
/* 1000:     */      }
/* 1001:     */    }
/* 1002:     */    
/* 1007:1007 */    if (specifiedClass != null) {
/* 1008:1008 */      specifiedClass = specifiedClass.trim();
/* 1009:     */    }
/* 1010:     */    
/* 1011:1011 */    return specifiedClass;
/* 1012:     */  }
/* 1013:     */  
/* 1036:     */  private Log createLogFromClass(String logAdapterClassName, String logCategory, boolean affectState)
/* 1037:     */    throws LogConfigurationException
/* 1038:     */  {
/* 1039:1039 */    if (isDiagnosticsEnabled()) {
/* 1040:1040 */      logDiagnostic("Attempting to instantiate '" + logAdapterClassName + "'");
/* 1041:     */    }
/* 1042:     */    
/* 1043:1043 */    Object[] params = { logCategory };
/* 1044:1044 */    Log logAdapter = null;
/* 1045:1045 */    Constructor constructor = null;
/* 1046:     */    
/* 1047:1047 */    Class logAdapterClass = null;
/* 1048:1048 */    ClassLoader currentCL = getBaseClassLoader();
/* 1049:     */    
/* 1051:     */    for (;;)
/* 1052:     */    {
/* 1053:1053 */      logDiagnostic("Trying to load '" + logAdapterClassName + "' from classloader " + LogFactory.objectId(currentCL));
/* 1054:     */      
/* 1057:     */      try
/* 1058:     */      {
/* 1059:1059 */        if (isDiagnosticsEnabled())
/* 1060:     */        {
/* 1065:1065 */          String resourceName = logAdapterClassName.replace('.', '/') + ".class";
/* 1066:1066 */          URL url; URL url; if (currentCL != null) {
/* 1067:1067 */            url = currentCL.getResource(resourceName);
/* 1068:     */          } else {
/* 1069:1069 */            url = ClassLoader.getSystemResource(resourceName + ".class");
/* 1070:     */          }
/* 1071:     */          
/* 1072:1072 */          if (url == null) {
/* 1073:1073 */            logDiagnostic("Class '" + logAdapterClassName + "' [" + resourceName + "] cannot be found.");
/* 1074:     */          } else {
/* 1075:1075 */            logDiagnostic("Class '" + logAdapterClassName + "' was found at '" + url + "'");
/* 1076:     */          }
/* 1077:     */        }
/* 1078:     */        
/* 1079:1079 */        Class c = null;
/* 1080:     */        try {
/* 1081:1081 */          c = Class.forName(logAdapterClassName, true, currentCL);
/* 1083:     */        }
/* 1084:     */        catch (ClassNotFoundException originalClassNotFoundException)
/* 1085:     */        {
/* 1086:1086 */          String msg = "" + originalClassNotFoundException.getMessage();
/* 1087:1087 */          logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via classloader " + LogFactory.objectId(currentCL) + ": " + msg.trim());
/* 1088:     */          
/* 1100:     */          try
/* 1101:     */          {
/* 1102:1102 */            c = Class.forName(logAdapterClassName);
/* 1103:     */          }
/* 1104:     */          catch (ClassNotFoundException secondaryClassNotFoundException) {
/* 1105:1105 */            msg = "" + secondaryClassNotFoundException.getMessage();
/* 1106:1106 */            logDiagnostic("The log adapter '" + logAdapterClassName + "' is not available via the LogFactoryImpl class classloader: " + msg.trim());
/* 1107:     */            
/* 1111:1111 */            break;
/* 1112:     */          }
/* 1113:     */        }
/* 1114:     */        
/* 1115:1115 */        constructor = c.getConstructor(this.logConstructorSignature);
/* 1116:1116 */        Object o = constructor.newInstance(params);
/* 1117:     */        
/* 1122:1122 */        if ((o instanceof Log)) {
/* 1123:1123 */          logAdapterClass = c;
/* 1124:1124 */          logAdapter = (Log)o;
/* 1125:1125 */          break;
/* 1126:     */        }
/* 1127:     */        
/* 1138:1138 */        handleFlawedHierarchy(currentCL, c);
/* 1141:     */      }
/* 1142:     */      catch (NoClassDefFoundError e)
/* 1143:     */      {
/* 1145:1145 */        String msg = "" + e.getMessage();
/* 1146:1146 */        logDiagnostic("The log adapter '" + logAdapterClassName + "' is missing dependencies when loaded via classloader " + LogFactory.objectId(currentCL) + ": " + msg.trim());
/* 1147:     */        
/* 1153:1153 */        break;
/* 1156:     */      }
/* 1157:     */      catch (ExceptionInInitializerError e)
/* 1158:     */      {
/* 1161:1161 */        String msg = "" + e.getMessage();
/* 1162:1162 */        logDiagnostic("The log adapter '" + logAdapterClassName + "' is unable to initialize itself when loaded via classloader " + LogFactory.objectId(currentCL) + ": " + msg.trim());
/* 1163:     */        
/* 1169:1169 */        break;
/* 1170:     */      }
/* 1171:     */      catch (LogConfigurationException e)
/* 1172:     */      {
/* 1173:1173 */        throw e;
/* 1175:     */      }
/* 1176:     */      catch (Throwable t)
/* 1177:     */      {
/* 1178:1178 */        handleFlawedDiscovery(logAdapterClassName, currentCL, t);
/* 1179:     */      }
/* 1180:     */      
/* 1181:1181 */      if (currentCL == null) {
/* 1182:     */        break;
/* 1183:     */      }
/* 1184:     */      
/* 1187:1187 */      currentCL = getParentClassLoader(currentCL);
/* 1188:     */    }
/* 1189:     */    
/* 1190:1190 */    if ((logAdapter != null) && (affectState))
/* 1191:     */    {
/* 1192:1192 */      this.logClassName = logAdapterClassName;
/* 1193:1193 */      this.logConstructor = constructor;
/* 1194:     */      
/* 1195:     */      try
/* 1196:     */      {
/* 1197:1197 */        this.logMethod = logAdapterClass.getMethod("setLogFactory", this.logMethodSignature);
/* 1198:     */        
/* 1199:1199 */        logDiagnostic("Found method setLogFactory(LogFactory) in '" + logAdapterClassName + "'");
/* 1200:     */      }
/* 1201:     */      catch (Throwable t) {
/* 1202:1202 */        this.logMethod = null;
/* 1203:1203 */        logDiagnostic("[INFO] '" + logAdapterClassName + "' from classloader " + LogFactory.objectId(currentCL) + " does not declare optional method " + "setLogFactory(LogFactory)");
/* 1204:     */      }
/* 1205:     */      
/* 1210:1210 */      logDiagnostic("Log adapter '" + logAdapterClassName + "' from classloader " + LogFactory.objectId(logAdapterClass.getClassLoader()) + " has been selected for use.");
/* 1211:     */    }
/* 1212:     */    
/* 1216:1216 */    return logAdapter;
/* 1217:     */  }
/* 1218:     */  
/* 1236:     */  private ClassLoader getBaseClassLoader()
/* 1237:     */    throws LogConfigurationException
/* 1238:     */  {
/* 1239:1239 */    ClassLoader thisClassLoader = getClassLoader(LogFactoryImpl.class);
/* 1240:     */    
/* 1241:1241 */    if (!this.useTCCL) {
/* 1242:1242 */      return thisClassLoader;
/* 1243:     */    }
/* 1244:     */    
/* 1245:1245 */    ClassLoader contextClassLoader = getContextClassLoaderInternal();
/* 1246:     */    
/* 1247:1247 */    ClassLoader baseClassLoader = getLowestClassLoader(contextClassLoader, thisClassLoader);
/* 1248:     */    
/* 1250:1250 */    if (baseClassLoader == null)
/* 1251:     */    {
/* 1255:1255 */      if (this.allowFlawedContext) {
/* 1256:1256 */        if (isDiagnosticsEnabled()) {
/* 1257:1257 */          logDiagnostic("[WARNING] the context classloader is not part of a parent-child relationship with the classloader that loaded LogFactoryImpl.");
/* 1258:     */        }
/* 1259:     */        
/* 1265:1265 */        return contextClassLoader;
/* 1266:     */      }
/* 1267:     */      
/* 1268:1268 */      throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
/* 1269:     */    }
/* 1270:     */    
/* 1275:1275 */    if (baseClassLoader != contextClassLoader)
/* 1276:     */    {
/* 1281:1281 */      if (this.allowFlawedContext) {
/* 1282:1282 */        if (isDiagnosticsEnabled()) {
/* 1283:1283 */          logDiagnostic("Warning: the context classloader is an ancestor of the classloader that loaded LogFactoryImpl; it should be the same or a descendant. The application using commons-logging should ensure the context classloader is used correctly.");
/* 1285:     */        }
/* 1286:     */        
/* 1288:     */      }
/* 1289:     */      else
/* 1290:     */      {
/* 1291:1291 */        throw new LogConfigurationException("Bad classloader hierarchy; LogFactoryImpl was loaded via a classloader that is not related to the current context classloader.");
/* 1292:     */      }
/* 1293:     */    }
/* 1294:     */    
/* 1298:1298 */    return baseClassLoader;
/* 1299:     */  }
/* 1300:     */  
/* 1312:     */  private ClassLoader getLowestClassLoader(ClassLoader c1, ClassLoader c2)
/* 1313:     */  {
/* 1314:1314 */    if (c1 == null) {
/* 1315:1315 */      return c2;
/* 1316:     */    }
/* 1317:1317 */    if (c2 == null) {
/* 1318:1318 */      return c1;
/* 1319:     */    }
/* 1320:     */    
/* 1323:1323 */    ClassLoader current = c1;
/* 1324:1324 */    while (current != null) {
/* 1325:1325 */      if (current == c2)
/* 1326:1326 */        return c1;
/* 1327:1327 */      current = current.getParent();
/* 1328:     */    }
/* 1329:     */    
/* 1331:1331 */    current = c2;
/* 1332:1332 */    while (current != null) {
/* 1333:1333 */      if (current == c1)
/* 1334:1334 */        return c2;
/* 1335:1335 */      current = current.getParent();
/* 1336:     */    }
/* 1337:     */    
/* 1338:1338 */    return null;
/* 1339:     */  }
/* 1340:     */  
/* 1358:     */  private void handleFlawedDiscovery(String logAdapterClassName, ClassLoader classLoader, Throwable discoveryFlaw)
/* 1359:     */  {
/* 1360:1360 */    if (isDiagnosticsEnabled()) {
/* 1361:1361 */      logDiagnostic("Could not instantiate Log '" + logAdapterClassName + "' -- " + discoveryFlaw.getClass().getName() + ": " + discoveryFlaw.getLocalizedMessage());
/* 1362:     */      
/* 1366:1366 */      if ((discoveryFlaw instanceof InvocationTargetException))
/* 1367:     */      {
/* 1370:1370 */        InvocationTargetException ite = (InvocationTargetException)discoveryFlaw;
/* 1371:1371 */        Throwable cause = ite.getTargetException();
/* 1372:1372 */        if (cause != null) {
/* 1373:1373 */          logDiagnostic("... InvocationTargetException: " + cause.getClass().getName() + ": " + cause.getLocalizedMessage());
/* 1374:     */          
/* 1377:1377 */          if ((cause instanceof ExceptionInInitializerError)) {
/* 1378:1378 */            ExceptionInInitializerError eiie = (ExceptionInInitializerError)cause;
/* 1379:1379 */            Throwable cause2 = eiie.getException();
/* 1380:1380 */            if (cause2 != null) {
/* 1381:1381 */              logDiagnostic("... ExceptionInInitializerError: " + cause2.getClass().getName() + ": " + cause2.getLocalizedMessage());
/* 1382:     */            }
/* 1383:     */          }
/* 1384:     */        }
/* 1385:     */      }
/* 1386:     */    }
/* 1387:     */    
/* 1390:1390 */    if (!this.allowFlawedDiscovery) {
/* 1391:1391 */      throw new LogConfigurationException(discoveryFlaw);
/* 1392:     */    }
/* 1393:     */  }
/* 1394:     */  
/* 1422:     */  private void handleFlawedHierarchy(ClassLoader badClassLoader, Class badClass)
/* 1423:     */    throws LogConfigurationException
/* 1424:     */  {
/* 1425:1425 */    boolean implementsLog = false;
/* 1426:1426 */    String logInterfaceName = Log.class.getName();
/* 1427:1427 */    Class[] interfaces = badClass.getInterfaces();
/* 1428:1428 */    for (int i = 0; i < interfaces.length; i++) {
/* 1429:1429 */      if (logInterfaceName.equals(interfaces[i].getName())) {
/* 1430:1430 */        implementsLog = true;
/* 1431:1431 */        break;
/* 1432:     */      }
/* 1433:     */    }
/* 1434:     */    
/* 1435:1435 */    if (implementsLog)
/* 1436:     */    {
/* 1438:1438 */      if (isDiagnosticsEnabled()) {
/* 1439:     */        try {
/* 1440:1440 */          ClassLoader logInterfaceClassLoader = getClassLoader(Log.class);
/* 1441:1441 */          logDiagnostic("Class '" + badClass.getName() + "' was found in classloader " + LogFactory.objectId(badClassLoader) + ". It is bound to a Log interface which is not" + " the one loaded from classloader " + LogFactory.objectId(logInterfaceClassLoader));
/* 1444:     */        }
/* 1445:     */        catch (Throwable t)
/* 1446:     */        {
/* 1449:1449 */          logDiagnostic("Error while trying to output diagnostics about bad class '" + badClass + "'");
/* 1450:     */        }
/* 1451:     */      }
/* 1452:     */      
/* 1455:1455 */      if (!this.allowFlawedHierarchy) {
/* 1456:1456 */        StringBuffer msg = new StringBuffer();
/* 1457:1457 */        msg.append("Terminating logging for this context ");
/* 1458:1458 */        msg.append("due to bad log hierarchy. ");
/* 1459:1459 */        msg.append("You have more than one version of '");
/* 1460:1460 */        msg.append(Log.class.getName());
/* 1461:1461 */        msg.append("' visible.");
/* 1462:1462 */        if (isDiagnosticsEnabled()) {
/* 1463:1463 */          logDiagnostic(msg.toString());
/* 1464:     */        }
/* 1465:1465 */        throw new LogConfigurationException(msg.toString());
/* 1466:     */      }
/* 1467:     */      
/* 1468:1468 */      if (isDiagnosticsEnabled()) {
/* 1469:1469 */        StringBuffer msg = new StringBuffer();
/* 1470:1470 */        msg.append("Warning: bad log hierarchy. ");
/* 1471:1471 */        msg.append("You have more than one version of '");
/* 1472:1472 */        msg.append(Log.class.getName());
/* 1473:1473 */        msg.append("' visible.");
/* 1474:1474 */        logDiagnostic(msg.toString());
/* 1475:     */      }
/* 1476:     */    }
/* 1477:     */    else {
/* 1478:1478 */      if (!this.allowFlawedDiscovery) {
/* 1479:1479 */        StringBuffer msg = new StringBuffer();
/* 1480:1480 */        msg.append("Terminating logging for this context. ");
/* 1481:1481 */        msg.append("Log class '");
/* 1482:1482 */        msg.append(badClass.getName());
/* 1483:1483 */        msg.append("' does not implement the Log interface.");
/* 1484:1484 */        if (isDiagnosticsEnabled()) {
/* 1485:1485 */          logDiagnostic(msg.toString());
/* 1486:     */        }
/* 1487:     */        
/* 1488:1488 */        throw new LogConfigurationException(msg.toString());
/* 1489:     */      }
/* 1490:     */      
/* 1491:1491 */      if (isDiagnosticsEnabled()) {
/* 1492:1492 */        StringBuffer msg = new StringBuffer();
/* 1493:1493 */        msg.append("[WARNING] Log class '");
/* 1494:1494 */        msg.append(badClass.getName());
/* 1495:1495 */        msg.append("' does not implement the Log interface.");
/* 1496:1496 */        logDiagnostic(msg.toString());
/* 1497:     */      }
/* 1498:     */    }
/* 1499:     */  }
/* 1500:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.LogFactoryImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */