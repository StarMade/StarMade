/*    1:     */package org.apache.commons.lang3;
/*    2:     */
/*    3:     */import java.lang.reflect.Method;
/*    4:     */import java.lang.reflect.Modifier;
/*    5:     */import java.util.ArrayList;
/*    6:     */import java.util.HashMap;
/*    7:     */import java.util.HashSet;
/*    8:     */import java.util.LinkedHashSet;
/*    9:     */import java.util.List;
/*   10:     */import java.util.Map;
/*   11:     */
/*   50:     */public class ClassUtils
/*   51:     */{
/*   52:     */  public static final char PACKAGE_SEPARATOR_CHAR = '.';
/*   53:  53 */  public static final String PACKAGE_SEPARATOR = String.valueOf('.');
/*   54:     */  
/*   58:     */  public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
/*   59:     */  
/*   63:  63 */  public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
/*   64:     */  
/*   68:  68 */  private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap();
/*   69:     */  
/*   78:     */  private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;
/*   79:     */  
/*   88:     */  private static final Map<String, String> abbreviationMap;
/*   89:     */  
/*   98:     */  private static final Map<String, String> reverseAbbreviationMap;
/*   99:     */  
/*  109:     */  private static void addAbbreviation(String primitive, String abbreviation)
/*  110:     */  {
/*  111: 111 */    abbreviationMap.put(primitive, abbreviation);
/*  112: 112 */    reverseAbbreviationMap.put(abbreviation, primitive);
/*  113:     */  }
/*  114:     */  
/*  115:     */  static
/*  116:     */  {
/*  117:  70 */    primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
/*  118:  71 */    primitiveWrapperMap.put(Byte.TYPE, Byte.class);
/*  119:  72 */    primitiveWrapperMap.put(Character.TYPE, Character.class);
/*  120:  73 */    primitiveWrapperMap.put(Short.TYPE, Short.class);
/*  121:  74 */    primitiveWrapperMap.put(Integer.TYPE, Integer.class);
/*  122:  75 */    primitiveWrapperMap.put(Long.TYPE, Long.class);
/*  123:  76 */    primitiveWrapperMap.put(Double.TYPE, Double.class);
/*  124:  77 */    primitiveWrapperMap.put(Float.TYPE, Float.class);
/*  125:  78 */    primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
/*  126:     */    
/*  131:  84 */    wrapperPrimitiveMap = new HashMap();
/*  132:     */    
/*  133:  86 */    for (Class<?> primitiveClass : primitiveWrapperMap.keySet()) {
/*  134:  87 */      Class<?> wrapperClass = (Class)primitiveWrapperMap.get(primitiveClass);
/*  135:  88 */      if (!primitiveClass.equals(wrapperClass)) {
/*  136:  89 */        wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
/*  137:     */      }
/*  138:     */    }
/*  139:     */    
/*  144:  97 */    abbreviationMap = new HashMap();
/*  145:     */    
/*  149: 102 */    reverseAbbreviationMap = new HashMap();
/*  150:     */    
/*  166: 119 */    addAbbreviation("int", "I");
/*  167: 120 */    addAbbreviation("boolean", "Z");
/*  168: 121 */    addAbbreviation("float", "F");
/*  169: 122 */    addAbbreviation("long", "J");
/*  170: 123 */    addAbbreviation("short", "S");
/*  171: 124 */    addAbbreviation("byte", "B");
/*  172: 125 */    addAbbreviation("double", "D");
/*  173: 126 */    addAbbreviation("char", "C");
/*  174:     */  }
/*  175:     */  
/*  196:     */  public static String getShortClassName(Object object, String valueIfNull)
/*  197:     */  {
/*  198: 151 */    if (object == null) {
/*  199: 152 */      return valueIfNull;
/*  200:     */    }
/*  201: 154 */    return getShortClassName(object.getClass());
/*  202:     */  }
/*  203:     */  
/*  213:     */  public static String getShortClassName(Class<?> cls)
/*  214:     */  {
/*  215: 168 */    if (cls == null) {
/*  216: 169 */      return "";
/*  217:     */    }
/*  218: 171 */    return getShortClassName(cls.getName());
/*  219:     */  }
/*  220:     */  
/*  232:     */  public static String getShortClassName(String className)
/*  233:     */  {
/*  234: 187 */    if (className == null) {
/*  235: 188 */      return "";
/*  236:     */    }
/*  237: 190 */    if (className.length() == 0) {
/*  238: 191 */      return "";
/*  239:     */    }
/*  240:     */    
/*  241: 194 */    StringBuilder arrayPrefix = new StringBuilder();
/*  242:     */    
/*  244: 197 */    if (className.startsWith("[")) {
/*  245: 198 */      while (className.charAt(0) == '[') {
/*  246: 199 */        className = className.substring(1);
/*  247: 200 */        arrayPrefix.append("[]");
/*  248:     */      }
/*  249:     */      
/*  250: 203 */      if ((className.charAt(0) == 'L') && (className.charAt(className.length() - 1) == ';')) {
/*  251: 204 */        className = className.substring(1, className.length() - 1);
/*  252:     */      }
/*  253:     */    }
/*  254:     */    
/*  255: 208 */    if (reverseAbbreviationMap.containsKey(className)) {
/*  256: 209 */      className = (String)reverseAbbreviationMap.get(className);
/*  257:     */    }
/*  258:     */    
/*  259: 212 */    int lastDotIdx = className.lastIndexOf('.');
/*  260: 213 */    int innerIdx = className.indexOf('$', lastDotIdx == -1 ? 0 : lastDotIdx + 1);
/*  261:     */    
/*  262: 215 */    String out = className.substring(lastDotIdx + 1);
/*  263: 216 */    if (innerIdx != -1) {
/*  264: 217 */      out = out.replace('$', '.');
/*  265:     */    }
/*  266: 219 */    return out + arrayPrefix;
/*  267:     */  }
/*  268:     */  
/*  276:     */  public static String getSimpleName(Class<?> cls)
/*  277:     */  {
/*  278: 231 */    if (cls == null) {
/*  279: 232 */      return "";
/*  280:     */    }
/*  281: 234 */    return cls.getSimpleName();
/*  282:     */  }
/*  283:     */  
/*  292:     */  public static String getSimpleName(Object object, String valueIfNull)
/*  293:     */  {
/*  294: 247 */    if (object == null) {
/*  295: 248 */      return valueIfNull;
/*  296:     */    }
/*  297: 250 */    return getSimpleName(object.getClass());
/*  298:     */  }
/*  299:     */  
/*  308:     */  public static String getPackageName(Object object, String valueIfNull)
/*  309:     */  {
/*  310: 263 */    if (object == null) {
/*  311: 264 */      return valueIfNull;
/*  312:     */    }
/*  313: 266 */    return getPackageName(object.getClass());
/*  314:     */  }
/*  315:     */  
/*  321:     */  public static String getPackageName(Class<?> cls)
/*  322:     */  {
/*  323: 276 */    if (cls == null) {
/*  324: 277 */      return "";
/*  325:     */    }
/*  326: 279 */    return getPackageName(cls.getName());
/*  327:     */  }
/*  328:     */  
/*  337:     */  public static String getPackageName(String className)
/*  338:     */  {
/*  339: 292 */    if ((className == null) || (className.length() == 0)) {
/*  340: 293 */      return "";
/*  341:     */    }
/*  342:     */    
/*  344: 297 */    while (className.charAt(0) == '[') {
/*  345: 298 */      className = className.substring(1);
/*  346:     */    }
/*  347:     */    
/*  348: 301 */    if ((className.charAt(0) == 'L') && (className.charAt(className.length() - 1) == ';')) {
/*  349: 302 */      className = className.substring(1);
/*  350:     */    }
/*  351:     */    
/*  352: 305 */    int i = className.lastIndexOf('.');
/*  353: 306 */    if (i == -1) {
/*  354: 307 */      return "";
/*  355:     */    }
/*  356: 309 */    return className.substring(0, i);
/*  357:     */  }
/*  358:     */  
/*  367:     */  public static List<Class<?>> getAllSuperclasses(Class<?> cls)
/*  368:     */  {
/*  369: 322 */    if (cls == null) {
/*  370: 323 */      return null;
/*  371:     */    }
/*  372: 325 */    List<Class<?>> classes = new ArrayList();
/*  373: 326 */    Class<?> superclass = cls.getSuperclass();
/*  374: 327 */    while (superclass != null) {
/*  375: 328 */      classes.add(superclass);
/*  376: 329 */      superclass = superclass.getSuperclass();
/*  377:     */    }
/*  378: 331 */    return classes;
/*  379:     */  }
/*  380:     */  
/*  393:     */  public static List<Class<?>> getAllInterfaces(Class<?> cls)
/*  394:     */  {
/*  395: 348 */    if (cls == null) {
/*  396: 349 */      return null;
/*  397:     */    }
/*  398:     */    
/*  399: 352 */    LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet();
/*  400: 353 */    getAllInterfaces(cls, interfacesFound);
/*  401:     */    
/*  402: 355 */    return new ArrayList(interfacesFound);
/*  403:     */  }
/*  404:     */  
/*  410:     */  private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> interfacesFound)
/*  411:     */  {
/*  412: 365 */    while (cls != null) {
/*  413: 366 */      Class<?>[] interfaces = cls.getInterfaces();
/*  414:     */      
/*  415: 368 */      for (Class<?> i : interfaces) {
/*  416: 369 */        if (interfacesFound.add(i)) {
/*  417: 370 */          getAllInterfaces(i, interfacesFound);
/*  418:     */        }
/*  419:     */      }
/*  420:     */      
/*  421: 374 */      cls = cls.getSuperclass();
/*  422:     */    }
/*  423:     */  }
/*  424:     */  
/*  438:     */  public static List<Class<?>> convertClassNamesToClasses(List<String> classNames)
/*  439:     */  {
/*  440: 393 */    if (classNames == null) {
/*  441: 394 */      return null;
/*  442:     */    }
/*  443: 396 */    List<Class<?>> classes = new ArrayList(classNames.size());
/*  444: 397 */    for (String className : classNames) {
/*  445:     */      try {
/*  446: 399 */        classes.add(Class.forName(className));
/*  447:     */      } catch (Exception ex) {
/*  448: 401 */        classes.add(null);
/*  449:     */      }
/*  450:     */    }
/*  451: 404 */    return classes;
/*  452:     */  }
/*  453:     */  
/*  465:     */  public static List<String> convertClassesToClassNames(List<Class<?>> classes)
/*  466:     */  {
/*  467: 420 */    if (classes == null) {
/*  468: 421 */      return null;
/*  469:     */    }
/*  470: 423 */    List<String> classNames = new ArrayList(classes.size());
/*  471: 424 */    for (Class<?> cls : classes) {
/*  472: 425 */      if (cls == null) {
/*  473: 426 */        classNames.add(null);
/*  474:     */      } else {
/*  475: 428 */        classNames.add(cls.getName());
/*  476:     */      }
/*  477:     */    }
/*  478: 431 */    return classNames;
/*  479:     */  }
/*  480:     */  
/*  518:     */  public static boolean isAssignable(Class<?>[] classArray, Class<?>... toClassArray)
/*  519:     */  {
/*  520: 473 */    return isAssignable(classArray, toClassArray, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
/*  521:     */  }
/*  522:     */  
/*  554:     */  public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray, boolean autoboxing)
/*  555:     */  {
/*  556: 509 */    if (!ArrayUtils.isSameLength(classArray, toClassArray)) {
/*  557: 510 */      return false;
/*  558:     */    }
/*  559: 512 */    if (classArray == null) {
/*  560: 513 */      classArray = ArrayUtils.EMPTY_CLASS_ARRAY;
/*  561:     */    }
/*  562: 515 */    if (toClassArray == null) {
/*  563: 516 */      toClassArray = ArrayUtils.EMPTY_CLASS_ARRAY;
/*  564:     */    }
/*  565: 518 */    for (int i = 0; i < classArray.length; i++) {
/*  566: 519 */      if (!isAssignable(classArray[i], toClassArray[i], autoboxing)) {
/*  567: 520 */        return false;
/*  568:     */      }
/*  569:     */    }
/*  570: 523 */    return true;
/*  571:     */  }
/*  572:     */  
/*  582:     */  public static boolean isPrimitiveOrWrapper(Class<?> type)
/*  583:     */  {
/*  584: 537 */    if (type == null) {
/*  585: 538 */      return false;
/*  586:     */    }
/*  587: 540 */    return (type.isPrimitive()) || (isPrimitiveWrapper(type));
/*  588:     */  }
/*  589:     */  
/*  599:     */  public static boolean isPrimitiveWrapper(Class<?> type)
/*  600:     */  {
/*  601: 554 */    return wrapperPrimitiveMap.containsKey(type);
/*  602:     */  }
/*  603:     */  
/*  634:     */  public static boolean isAssignable(Class<?> cls, Class<?> toClass)
/*  635:     */  {
/*  636: 589 */    return isAssignable(cls, toClass, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
/*  637:     */  }
/*  638:     */  
/*  665:     */  public static boolean isAssignable(Class<?> cls, Class<?> toClass, boolean autoboxing)
/*  666:     */  {
/*  667: 620 */    if (toClass == null) {
/*  668: 621 */      return false;
/*  669:     */    }
/*  670:     */    
/*  671: 624 */    if (cls == null) {
/*  672: 625 */      return !toClass.isPrimitive();
/*  673:     */    }
/*  674:     */    
/*  675: 628 */    if (autoboxing) {
/*  676: 629 */      if ((cls.isPrimitive()) && (!toClass.isPrimitive())) {
/*  677: 630 */        cls = primitiveToWrapper(cls);
/*  678: 631 */        if (cls == null) {
/*  679: 632 */          return false;
/*  680:     */        }
/*  681:     */      }
/*  682: 635 */      if ((toClass.isPrimitive()) && (!cls.isPrimitive())) {
/*  683: 636 */        cls = wrapperToPrimitive(cls);
/*  684: 637 */        if (cls == null) {
/*  685: 638 */          return false;
/*  686:     */        }
/*  687:     */      }
/*  688:     */    }
/*  689: 642 */    if (cls.equals(toClass)) {
/*  690: 643 */      return true;
/*  691:     */    }
/*  692: 645 */    if (cls.isPrimitive()) {
/*  693: 646 */      if (!toClass.isPrimitive()) {
/*  694: 647 */        return false;
/*  695:     */      }
/*  696: 649 */      if (Integer.TYPE.equals(cls)) {
/*  697: 650 */        return (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*  698:     */      }
/*  699:     */      
/*  701: 654 */      if (Long.TYPE.equals(cls)) {
/*  702: 655 */        return (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*  703:     */      }
/*  704:     */      
/*  705: 658 */      if (Boolean.TYPE.equals(cls)) {
/*  706: 659 */        return false;
/*  707:     */      }
/*  708: 661 */      if (Double.TYPE.equals(cls)) {
/*  709: 662 */        return false;
/*  710:     */      }
/*  711: 664 */      if (Float.TYPE.equals(cls)) {
/*  712: 665 */        return Double.TYPE.equals(toClass);
/*  713:     */      }
/*  714: 667 */      if (Character.TYPE.equals(cls)) {
/*  715: 668 */        return (Integer.TYPE.equals(toClass)) || (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*  716:     */      }
/*  717:     */      
/*  720: 673 */      if (Short.TYPE.equals(cls)) {
/*  721: 674 */        return (Integer.TYPE.equals(toClass)) || (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*  722:     */      }
/*  723:     */      
/*  726: 679 */      if (Byte.TYPE.equals(cls)) {
/*  727: 680 */        return (Short.TYPE.equals(toClass)) || (Integer.TYPE.equals(toClass)) || (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*  728:     */      }
/*  729:     */      
/*  734: 687 */      return false;
/*  735:     */    }
/*  736: 689 */    return toClass.isAssignableFrom(cls);
/*  737:     */  }
/*  738:     */  
/*  750:     */  public static Class<?> primitiveToWrapper(Class<?> cls)
/*  751:     */  {
/*  752: 705 */    Class<?> convertedClass = cls;
/*  753: 706 */    if ((cls != null) && (cls.isPrimitive())) {
/*  754: 707 */      convertedClass = (Class)primitiveWrapperMap.get(cls);
/*  755:     */    }
/*  756: 709 */    return convertedClass;
/*  757:     */  }
/*  758:     */  
/*  768:     */  public static Class<?>[] primitivesToWrappers(Class<?>... classes)
/*  769:     */  {
/*  770: 723 */    if (classes == null) {
/*  771: 724 */      return null;
/*  772:     */    }
/*  773:     */    
/*  774: 727 */    if (classes.length == 0) {
/*  775: 728 */      return classes;
/*  776:     */    }
/*  777:     */    
/*  778: 731 */    Class<?>[] convertedClasses = new Class[classes.length];
/*  779: 732 */    for (int i = 0; i < classes.length; i++) {
/*  780: 733 */      convertedClasses[i] = primitiveToWrapper(classes[i]);
/*  781:     */    }
/*  782: 735 */    return convertedClasses;
/*  783:     */  }
/*  784:     */  
/*  800:     */  public static Class<?> wrapperToPrimitive(Class<?> cls)
/*  801:     */  {
/*  802: 755 */    return (Class)wrapperPrimitiveMap.get(cls);
/*  803:     */  }
/*  804:     */  
/*  818:     */  public static Class<?>[] wrappersToPrimitives(Class<?>... classes)
/*  819:     */  {
/*  820: 773 */    if (classes == null) {
/*  821: 774 */      return null;
/*  822:     */    }
/*  823:     */    
/*  824: 777 */    if (classes.length == 0) {
/*  825: 778 */      return classes;
/*  826:     */    }
/*  827:     */    
/*  828: 781 */    Class<?>[] convertedClasses = new Class[classes.length];
/*  829: 782 */    for (int i = 0; i < classes.length; i++) {
/*  830: 783 */      convertedClasses[i] = wrapperToPrimitive(classes[i]);
/*  831:     */    }
/*  832: 785 */    return convertedClasses;
/*  833:     */  }
/*  834:     */  
/*  843:     */  public static boolean isInnerClass(Class<?> cls)
/*  844:     */  {
/*  845: 798 */    return (cls != null) && (cls.getEnclosingClass() != null);
/*  846:     */  }
/*  847:     */  
/*  854:     */  public static Class<?> getClass(ClassLoader classLoader, String className, boolean initialize)
/*  855:     */    throws ClassNotFoundException
/*  856:     */  {
/*  857:     */    try
/*  858:     */    {
/*  859:     */      Class<?> clazz;
/*  860:     */      
/*  866: 819 */      if (abbreviationMap.containsKey(className)) {
/*  867: 820 */        String clsName = "[" + (String)abbreviationMap.get(className);
/*  868: 821 */        clazz = Class.forName(clsName, initialize, classLoader).getComponentType();
/*  869:     */      }
/*  870: 823 */      return Class.forName(toCanonicalName(className), initialize, classLoader);
/*  872:     */    }
/*  873:     */    catch (ClassNotFoundException ex)
/*  874:     */    {
/*  875: 828 */      int lastDotIndex = className.lastIndexOf('.');
/*  876:     */      
/*  877: 830 */      if (lastDotIndex != -1) {
/*  878:     */        try {
/*  879: 832 */          return getClass(classLoader, className.substring(0, lastDotIndex) + '$' + className.substring(lastDotIndex + 1), initialize);
/*  880:     */        }
/*  881:     */        catch (ClassNotFoundException ex2) {}
/*  882:     */      }
/*  883:     */      
/*  887: 840 */      throw ex;
/*  888:     */    }
/*  889:     */  }
/*  890:     */  
/*  901:     */  public static Class<?> getClass(ClassLoader classLoader, String className)
/*  902:     */    throws ClassNotFoundException
/*  903:     */  {
/*  904: 857 */    return getClass(classLoader, className, true);
/*  905:     */  }
/*  906:     */  
/*  916:     */  public static Class<?> getClass(String className)
/*  917:     */    throws ClassNotFoundException
/*  918:     */  {
/*  919: 872 */    return getClass(className, true);
/*  920:     */  }
/*  921:     */  
/*  931:     */  public static Class<?> getClass(String className, boolean initialize)
/*  932:     */    throws ClassNotFoundException
/*  933:     */  {
/*  934: 887 */    ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
/*  935: 888 */    ClassLoader loader = contextCL == null ? ClassUtils.class.getClassLoader() : contextCL;
/*  936: 889 */    return getClass(loader, className, initialize);
/*  937:     */  }
/*  938:     */  
/*  962:     */  public static Method getPublicMethod(Class<?> cls, String methodName, Class<?>... parameterTypes)
/*  963:     */    throws SecurityException, NoSuchMethodException
/*  964:     */  {
/*  965: 918 */    Method declaredMethod = cls.getMethod(methodName, parameterTypes);
/*  966: 919 */    if (Modifier.isPublic(declaredMethod.getDeclaringClass().getModifiers())) {
/*  967: 920 */      return declaredMethod;
/*  968:     */    }
/*  969:     */    
/*  970: 923 */    List<Class<?>> candidateClasses = new ArrayList();
/*  971: 924 */    candidateClasses.addAll(getAllInterfaces(cls));
/*  972: 925 */    candidateClasses.addAll(getAllSuperclasses(cls));
/*  973:     */    
/*  974: 927 */    for (Class<?> candidateClass : candidateClasses) {
/*  975: 928 */      if (Modifier.isPublic(candidateClass.getModifiers()))
/*  976:     */      {
/*  977:     */        Method candidateMethod;
/*  978:     */        try
/*  979:     */        {
/*  980: 933 */          candidateMethod = candidateClass.getMethod(methodName, parameterTypes);
/*  981:     */        } catch (NoSuchMethodException ex) {}
/*  982: 935 */        continue;
/*  983:     */        
/*  984: 937 */        if (Modifier.isPublic(candidateMethod.getDeclaringClass().getModifiers())) {
/*  985: 938 */          return candidateMethod;
/*  986:     */        }
/*  987:     */      }
/*  988:     */    }
/*  989: 942 */    throw new NoSuchMethodException("Can't find a public method for " + methodName + " " + ArrayUtils.toString(parameterTypes));
/*  990:     */  }
/*  991:     */  
/*  999:     */  private static String toCanonicalName(String className)
/* 1000:     */  {
/* 1001: 954 */    className = StringUtils.deleteWhitespace(className);
/* 1002: 955 */    if (className == null)
/* 1003: 956 */      throw new NullPointerException("className must not be null.");
/* 1004: 957 */    if (className.endsWith("[]")) {
/* 1005: 958 */      StringBuilder classNameBuffer = new StringBuilder();
/* 1006: 959 */      while (className.endsWith("[]")) {
/* 1007: 960 */        className = className.substring(0, className.length() - 2);
/* 1008: 961 */        classNameBuffer.append("[");
/* 1009:     */      }
/* 1010: 963 */      String abbreviation = (String)abbreviationMap.get(className);
/* 1011: 964 */      if (abbreviation != null) {
/* 1012: 965 */        classNameBuffer.append(abbreviation);
/* 1013:     */      } else {
/* 1014: 967 */        classNameBuffer.append("L").append(className).append(";");
/* 1015:     */      }
/* 1016: 969 */      className = classNameBuffer.toString();
/* 1017:     */    }
/* 1018: 971 */    return className;
/* 1019:     */  }
/* 1020:     */  
/* 1030:     */  public static Class<?>[] toClass(Object... array)
/* 1031:     */  {
/* 1032: 985 */    if (array == null)
/* 1033: 986 */      return null;
/* 1034: 987 */    if (array.length == 0) {
/* 1035: 988 */      return ArrayUtils.EMPTY_CLASS_ARRAY;
/* 1036:     */    }
/* 1037: 990 */    Class<?>[] classes = new Class[array.length];
/* 1038: 991 */    for (int i = 0; i < array.length; i++) {
/* 1039: 992 */      classes[i] = (array[i] == null ? null : array[i].getClass());
/* 1040:     */    }
/* 1041: 994 */    return classes;
/* 1042:     */  }
/* 1043:     */  
/* 1053:     */  public static String getShortCanonicalName(Object object, String valueIfNull)
/* 1054:     */  {
/* 1055:1008 */    if (object == null) {
/* 1056:1009 */      return valueIfNull;
/* 1057:     */    }
/* 1058:1011 */    return getShortCanonicalName(object.getClass().getName());
/* 1059:     */  }
/* 1060:     */  
/* 1067:     */  public static String getShortCanonicalName(Class<?> cls)
/* 1068:     */  {
/* 1069:1022 */    if (cls == null) {
/* 1070:1023 */      return "";
/* 1071:     */    }
/* 1072:1025 */    return getShortCanonicalName(cls.getName());
/* 1073:     */  }
/* 1074:     */  
/* 1083:     */  public static String getShortCanonicalName(String canonicalName)
/* 1084:     */  {
/* 1085:1038 */    return getShortClassName(getCanonicalName(canonicalName));
/* 1086:     */  }
/* 1087:     */  
/* 1097:     */  public static String getPackageCanonicalName(Object object, String valueIfNull)
/* 1098:     */  {
/* 1099:1052 */    if (object == null) {
/* 1100:1053 */      return valueIfNull;
/* 1101:     */    }
/* 1102:1055 */    return getPackageCanonicalName(object.getClass().getName());
/* 1103:     */  }
/* 1104:     */  
/* 1111:     */  public static String getPackageCanonicalName(Class<?> cls)
/* 1112:     */  {
/* 1113:1066 */    if (cls == null) {
/* 1114:1067 */      return "";
/* 1115:     */    }
/* 1116:1069 */    return getPackageCanonicalName(cls.getName());
/* 1117:     */  }
/* 1118:     */  
/* 1128:     */  public static String getPackageCanonicalName(String canonicalName)
/* 1129:     */  {
/* 1130:1083 */    return getPackageName(getCanonicalName(canonicalName));
/* 1131:     */  }
/* 1132:     */  
/* 1148:     */  private static String getCanonicalName(String className)
/* 1149:     */  {
/* 1150:1103 */    className = StringUtils.deleteWhitespace(className);
/* 1151:1104 */    if (className == null) {
/* 1152:1105 */      return null;
/* 1153:     */    }
/* 1154:1107 */    int dim = 0;
/* 1155:1108 */    while (className.startsWith("[")) {
/* 1156:1109 */      dim++;
/* 1157:1110 */      className = className.substring(1);
/* 1158:     */    }
/* 1159:1112 */    if (dim < 1) {
/* 1160:1113 */      return className;
/* 1161:     */    }
/* 1162:1115 */    if (className.startsWith("L")) {
/* 1163:1116 */      className = className.substring(1, className.endsWith(";") ? className.length() - 1 : className.length());
/* 1168:     */    }
/* 1169:1122 */    else if (className.length() > 0) {
/* 1170:1123 */      className = (String)reverseAbbreviationMap.get(className.substring(0, 1));
/* 1171:     */    }
/* 1172:     */    
/* 1173:1126 */    StringBuilder canonicalClassNameBuffer = new StringBuilder(className);
/* 1174:1127 */    for (int i = 0; i < dim; i++) {
/* 1175:1128 */      canonicalClassNameBuffer.append("[]");
/* 1176:     */    }
/* 1177:1130 */    return canonicalClassNameBuffer.toString();
/* 1178:     */  }
/* 1179:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.ClassUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */