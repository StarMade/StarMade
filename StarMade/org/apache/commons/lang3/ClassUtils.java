/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ 
/*      */ public class ClassUtils
/*      */ {
/*      */   public static final char PACKAGE_SEPARATOR_CHAR = '.';
/*   53 */   public static final String PACKAGE_SEPARATOR = String.valueOf('.');
/*      */   public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
/*   63 */   public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
/*      */ 
/*   68 */   private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap();
/*      */   private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;
/*      */   private static final Map<String, String> abbreviationMap;
/*      */   private static final Map<String, String> reverseAbbreviationMap;
/*      */ 
/*      */   private static void addAbbreviation(String primitive, String abbreviation)
/*      */   {
/*  111 */     abbreviationMap.put(primitive, abbreviation);
/*  112 */     reverseAbbreviationMap.put(abbreviation, primitive);
/*      */   }
/*      */ 
/*      */   public static String getShortClassName(Object object, String valueIfNull)
/*      */   {
/*  151 */     if (object == null) {
/*  152 */       return valueIfNull;
/*      */     }
/*  154 */     return getShortClassName(object.getClass());
/*      */   }
/*      */ 
/*      */   public static String getShortClassName(Class<?> cls)
/*      */   {
/*  168 */     if (cls == null) {
/*  169 */       return "";
/*      */     }
/*  171 */     return getShortClassName(cls.getName());
/*      */   }
/*      */ 
/*      */   public static String getShortClassName(String className)
/*      */   {
/*  187 */     if (className == null) {
/*  188 */       return "";
/*      */     }
/*  190 */     if (className.length() == 0) {
/*  191 */       return "";
/*      */     }
/*      */ 
/*  194 */     StringBuilder arrayPrefix = new StringBuilder();
/*      */ 
/*  197 */     if (className.startsWith("[")) {
/*  198 */       while (className.charAt(0) == '[') {
/*  199 */         className = className.substring(1);
/*  200 */         arrayPrefix.append("[]");
/*      */       }
/*      */ 
/*  203 */       if ((className.charAt(0) == 'L') && (className.charAt(className.length() - 1) == ';')) {
/*  204 */         className = className.substring(1, className.length() - 1);
/*      */       }
/*      */     }
/*      */ 
/*  208 */     if (reverseAbbreviationMap.containsKey(className)) {
/*  209 */       className = (String)reverseAbbreviationMap.get(className);
/*      */     }
/*      */ 
/*  212 */     int lastDotIdx = className.lastIndexOf('.');
/*  213 */     int innerIdx = className.indexOf('$', lastDotIdx == -1 ? 0 : lastDotIdx + 1);
/*      */ 
/*  215 */     String out = className.substring(lastDotIdx + 1);
/*  216 */     if (innerIdx != -1) {
/*  217 */       out = out.replace('$', '.');
/*      */     }
/*  219 */     return out + arrayPrefix;
/*      */   }
/*      */ 
/*      */   public static String getSimpleName(Class<?> cls)
/*      */   {
/*  231 */     if (cls == null) {
/*  232 */       return "";
/*      */     }
/*  234 */     return cls.getSimpleName();
/*      */   }
/*      */ 
/*      */   public static String getSimpleName(Object object, String valueIfNull)
/*      */   {
/*  247 */     if (object == null) {
/*  248 */       return valueIfNull;
/*      */     }
/*  250 */     return getSimpleName(object.getClass());
/*      */   }
/*      */ 
/*      */   public static String getPackageName(Object object, String valueIfNull)
/*      */   {
/*  263 */     if (object == null) {
/*  264 */       return valueIfNull;
/*      */     }
/*  266 */     return getPackageName(object.getClass());
/*      */   }
/*      */ 
/*      */   public static String getPackageName(Class<?> cls)
/*      */   {
/*  276 */     if (cls == null) {
/*  277 */       return "";
/*      */     }
/*  279 */     return getPackageName(cls.getName());
/*      */   }
/*      */ 
/*      */   public static String getPackageName(String className)
/*      */   {
/*  292 */     if ((className == null) || (className.length() == 0)) {
/*  293 */       return "";
/*      */     }
/*      */ 
/*  297 */     while (className.charAt(0) == '[') {
/*  298 */       className = className.substring(1);
/*      */     }
/*      */ 
/*  301 */     if ((className.charAt(0) == 'L') && (className.charAt(className.length() - 1) == ';')) {
/*  302 */       className = className.substring(1);
/*      */     }
/*      */ 
/*  305 */     int i = className.lastIndexOf('.');
/*  306 */     if (i == -1) {
/*  307 */       return "";
/*      */     }
/*  309 */     return className.substring(0, i);
/*      */   }
/*      */ 
/*      */   public static List<Class<?>> getAllSuperclasses(Class<?> cls)
/*      */   {
/*  322 */     if (cls == null) {
/*  323 */       return null;
/*      */     }
/*  325 */     List classes = new ArrayList();
/*  326 */     Class superclass = cls.getSuperclass();
/*  327 */     while (superclass != null) {
/*  328 */       classes.add(superclass);
/*  329 */       superclass = superclass.getSuperclass();
/*      */     }
/*  331 */     return classes;
/*      */   }
/*      */ 
/*      */   public static List<Class<?>> getAllInterfaces(Class<?> cls)
/*      */   {
/*  348 */     if (cls == null) {
/*  349 */       return null;
/*      */     }
/*      */ 
/*  352 */     LinkedHashSet interfacesFound = new LinkedHashSet();
/*  353 */     getAllInterfaces(cls, interfacesFound);
/*      */ 
/*  355 */     return new ArrayList(interfacesFound);
/*      */   }
/*      */ 
/*      */   private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> interfacesFound)
/*      */   {
/*  365 */     while (cls != null) {
/*  366 */       Class[] interfaces = cls.getInterfaces();
/*      */ 
/*  368 */       for (Class i : interfaces) {
/*  369 */         if (interfacesFound.add(i)) {
/*  370 */           getAllInterfaces(i, interfacesFound);
/*      */         }
/*      */       }
/*      */ 
/*  374 */       cls = cls.getSuperclass();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static List<Class<?>> convertClassNamesToClasses(List<String> classNames)
/*      */   {
/*  393 */     if (classNames == null) {
/*  394 */       return null;
/*      */     }
/*  396 */     List classes = new ArrayList(classNames.size());
/*  397 */     for (String className : classNames) {
/*      */       try {
/*  399 */         classes.add(Class.forName(className));
/*      */       } catch (Exception ex) {
/*  401 */         classes.add(null);
/*      */       }
/*      */     }
/*  404 */     return classes;
/*      */   }
/*      */ 
/*      */   public static List<String> convertClassesToClassNames(List<Class<?>> classes)
/*      */   {
/*  420 */     if (classes == null) {
/*  421 */       return null;
/*      */     }
/*  423 */     List classNames = new ArrayList(classes.size());
/*  424 */     for (Class cls : classes) {
/*  425 */       if (cls == null)
/*  426 */         classNames.add(null);
/*      */       else {
/*  428 */         classNames.add(cls.getName());
/*      */       }
/*      */     }
/*  431 */     return classNames;
/*      */   }
/*      */ 
/*      */   public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray)
/*      */   {
/*  473 */     return isAssignable(classArray, toClassArray, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
/*      */   }
/*      */ 
/*      */   public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray, boolean autoboxing)
/*      */   {
/*  509 */     if (!ArrayUtils.isSameLength(classArray, toClassArray)) {
/*  510 */       return false;
/*      */     }
/*  512 */     if (classArray == null) {
/*  513 */       classArray = ArrayUtils.EMPTY_CLASS_ARRAY;
/*      */     }
/*  515 */     if (toClassArray == null) {
/*  516 */       toClassArray = ArrayUtils.EMPTY_CLASS_ARRAY;
/*      */     }
/*  518 */     for (int i = 0; i < classArray.length; i++) {
/*  519 */       if (!isAssignable(classArray[i], toClassArray[i], autoboxing)) {
/*  520 */         return false;
/*      */       }
/*      */     }
/*  523 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isPrimitiveOrWrapper(Class<?> type)
/*      */   {
/*  537 */     if (type == null) {
/*  538 */       return false;
/*      */     }
/*  540 */     return (type.isPrimitive()) || (isPrimitiveWrapper(type));
/*      */   }
/*      */ 
/*      */   public static boolean isPrimitiveWrapper(Class<?> type)
/*      */   {
/*  554 */     return wrapperPrimitiveMap.containsKey(type);
/*      */   }
/*      */ 
/*      */   public static boolean isAssignable(Class<?> cls, Class<?> toClass)
/*      */   {
/*  589 */     return isAssignable(cls, toClass, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
/*      */   }
/*      */ 
/*      */   public static boolean isAssignable(Class<?> cls, Class<?> toClass, boolean autoboxing)
/*      */   {
/*  620 */     if (toClass == null) {
/*  621 */       return false;
/*      */     }
/*      */ 
/*  624 */     if (cls == null) {
/*  625 */       return !toClass.isPrimitive();
/*      */     }
/*      */ 
/*  628 */     if (autoboxing) {
/*  629 */       if ((cls.isPrimitive()) && (!toClass.isPrimitive())) {
/*  630 */         cls = primitiveToWrapper(cls);
/*  631 */         if (cls == null) {
/*  632 */           return false;
/*      */         }
/*      */       }
/*  635 */       if ((toClass.isPrimitive()) && (!cls.isPrimitive())) {
/*  636 */         cls = wrapperToPrimitive(cls);
/*  637 */         if (cls == null) {
/*  638 */           return false;
/*      */         }
/*      */       }
/*      */     }
/*  642 */     if (cls.equals(toClass)) {
/*  643 */       return true;
/*      */     }
/*  645 */     if (cls.isPrimitive()) {
/*  646 */       if (!toClass.isPrimitive()) {
/*  647 */         return false;
/*      */       }
/*  649 */       if (Integer.TYPE.equals(cls)) {
/*  650 */         return (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*      */       }
/*      */ 
/*  654 */       if (Long.TYPE.equals(cls)) {
/*  655 */         return (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*      */       }
/*      */ 
/*  658 */       if (Boolean.TYPE.equals(cls)) {
/*  659 */         return false;
/*      */       }
/*  661 */       if (Double.TYPE.equals(cls)) {
/*  662 */         return false;
/*      */       }
/*  664 */       if (Float.TYPE.equals(cls)) {
/*  665 */         return Double.TYPE.equals(toClass);
/*      */       }
/*  667 */       if (Character.TYPE.equals(cls)) {
/*  668 */         return (Integer.TYPE.equals(toClass)) || (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*      */       }
/*      */ 
/*  673 */       if (Short.TYPE.equals(cls)) {
/*  674 */         return (Integer.TYPE.equals(toClass)) || (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*      */       }
/*      */ 
/*  679 */       if (Byte.TYPE.equals(cls)) {
/*  680 */         return (Short.TYPE.equals(toClass)) || (Integer.TYPE.equals(toClass)) || (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
/*      */       }
/*      */ 
/*  687 */       return false;
/*      */     }
/*  689 */     return toClass.isAssignableFrom(cls);
/*      */   }
/*      */ 
/*      */   public static Class<?> primitiveToWrapper(Class<?> cls)
/*      */   {
/*  705 */     Class convertedClass = cls;
/*  706 */     if ((cls != null) && (cls.isPrimitive())) {
/*  707 */       convertedClass = (Class)primitiveWrapperMap.get(cls);
/*      */     }
/*  709 */     return convertedClass;
/*      */   }
/*      */ 
/*      */   public static Class<?>[] primitivesToWrappers(Class<?>[] classes)
/*      */   {
/*  723 */     if (classes == null) {
/*  724 */       return null;
/*      */     }
/*      */ 
/*  727 */     if (classes.length == 0) {
/*  728 */       return classes;
/*      */     }
/*      */ 
/*  731 */     Class[] convertedClasses = new Class[classes.length];
/*  732 */     for (int i = 0; i < classes.length; i++) {
/*  733 */       convertedClasses[i] = primitiveToWrapper(classes[i]);
/*      */     }
/*  735 */     return convertedClasses;
/*      */   }
/*      */ 
/*      */   public static Class<?> wrapperToPrimitive(Class<?> cls)
/*      */   {
/*  755 */     return (Class)wrapperPrimitiveMap.get(cls);
/*      */   }
/*      */ 
/*      */   public static Class<?>[] wrappersToPrimitives(Class<?>[] classes)
/*      */   {
/*  773 */     if (classes == null) {
/*  774 */       return null;
/*      */     }
/*      */ 
/*  777 */     if (classes.length == 0) {
/*  778 */       return classes;
/*      */     }
/*      */ 
/*  781 */     Class[] convertedClasses = new Class[classes.length];
/*  782 */     for (int i = 0; i < classes.length; i++) {
/*  783 */       convertedClasses[i] = wrapperToPrimitive(classes[i]);
/*      */     }
/*  785 */     return convertedClasses;
/*      */   }
/*      */ 
/*      */   public static boolean isInnerClass(Class<?> cls)
/*      */   {
/*  798 */     return (cls != null) && (cls.getEnclosingClass() != null);
/*      */   }
/*      */ 
/*      */   public static Class<?> getClass(ClassLoader classLoader, String className, boolean initialize)
/*      */     throws ClassNotFoundException
/*      */   {
/*      */     try
/*      */     {
/*      */       Class clazz;
/*  819 */       if (abbreviationMap.containsKey(className)) {
/*  820 */         String clsName = "[" + (String)abbreviationMap.get(className);
/*  821 */         clazz = Class.forName(clsName, initialize, classLoader).getComponentType();
/*      */       }
/*  823 */       return Class.forName(toCanonicalName(className), initialize, classLoader);
/*      */     }
/*      */     catch (ClassNotFoundException ex)
/*      */     {
/*  828 */       int lastDotIndex = className.lastIndexOf('.');
/*      */ 
/*  830 */       if (lastDotIndex != -1) {
/*      */         try {
/*  832 */           return getClass(classLoader, className.substring(0, lastDotIndex) + '$' + className.substring(lastDotIndex + 1), initialize);
/*      */         }
/*      */         catch (ClassNotFoundException ex2)
/*      */         {
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  840 */       throw ex;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static Class<?> getClass(ClassLoader classLoader, String className)
/*      */     throws ClassNotFoundException
/*      */   {
/*  857 */     return getClass(classLoader, className, true);
/*      */   }
/*      */ 
/*      */   public static Class<?> getClass(String className)
/*      */     throws ClassNotFoundException
/*      */   {
/*  872 */     return getClass(className, true);
/*      */   }
/*      */ 
/*      */   public static Class<?> getClass(String className, boolean initialize)
/*      */     throws ClassNotFoundException
/*      */   {
/*  887 */     ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
/*  888 */     ClassLoader loader = contextCL == null ? ClassUtils.class.getClassLoader() : contextCL;
/*  889 */     return getClass(loader, className, initialize);
/*      */   }
/*      */ 
/*      */   public static Method getPublicMethod(Class<?> cls, String methodName, Class<?>[] parameterTypes)
/*      */     throws SecurityException, NoSuchMethodException
/*      */   {
/*  918 */     Method declaredMethod = cls.getMethod(methodName, parameterTypes);
/*  919 */     if (Modifier.isPublic(declaredMethod.getDeclaringClass().getModifiers())) {
/*  920 */       return declaredMethod;
/*      */     }
/*      */ 
/*  923 */     List candidateClasses = new ArrayList();
/*  924 */     candidateClasses.addAll(getAllInterfaces(cls));
/*  925 */     candidateClasses.addAll(getAllSuperclasses(cls));
/*      */ 
/*  927 */     for (Class candidateClass : candidateClasses) {
/*  928 */       if (Modifier.isPublic(candidateClass.getModifiers()))
/*      */       {
/*      */         Method candidateMethod;
/*      */         try
/*      */         {
/*  933 */           candidateMethod = candidateClass.getMethod(methodName, parameterTypes); } catch (NoSuchMethodException ex) {
/*      */         }
/*  935 */         continue;
/*      */ 
/*  937 */         if (Modifier.isPublic(candidateMethod.getDeclaringClass().getModifiers())) {
/*  938 */           return candidateMethod;
/*      */         }
/*      */       }
/*      */     }
/*  942 */     throw new NoSuchMethodException("Can't find a public method for " + methodName + " " + ArrayUtils.toString(parameterTypes));
/*      */   }
/*      */ 
/*      */   private static String toCanonicalName(String className)
/*      */   {
/*  954 */     className = StringUtils.deleteWhitespace(className);
/*  955 */     if (className == null)
/*  956 */       throw new NullPointerException("className must not be null.");
/*  957 */     if (className.endsWith("[]")) {
/*  958 */       StringBuilder classNameBuffer = new StringBuilder();
/*  959 */       while (className.endsWith("[]")) {
/*  960 */         className = className.substring(0, className.length() - 2);
/*  961 */         classNameBuffer.append("[");
/*      */       }
/*  963 */       String abbreviation = (String)abbreviationMap.get(className);
/*  964 */       if (abbreviation != null)
/*  965 */         classNameBuffer.append(abbreviation);
/*      */       else {
/*  967 */         classNameBuffer.append("L").append(className).append(";");
/*      */       }
/*  969 */       className = classNameBuffer.toString();
/*      */     }
/*  971 */     return className;
/*      */   }
/*      */ 
/*      */   public static Class<?>[] toClass(Object[] array)
/*      */   {
/*  985 */     if (array == null)
/*  986 */       return null;
/*  987 */     if (array.length == 0) {
/*  988 */       return ArrayUtils.EMPTY_CLASS_ARRAY;
/*      */     }
/*  990 */     Class[] classes = new Class[array.length];
/*  991 */     for (int i = 0; i < array.length; i++) {
/*  992 */       classes[i] = (array[i] == null ? null : array[i].getClass());
/*      */     }
/*  994 */     return classes;
/*      */   }
/*      */ 
/*      */   public static String getShortCanonicalName(Object object, String valueIfNull)
/*      */   {
/* 1008 */     if (object == null) {
/* 1009 */       return valueIfNull;
/*      */     }
/* 1011 */     return getShortCanonicalName(object.getClass().getName());
/*      */   }
/*      */ 
/*      */   public static String getShortCanonicalName(Class<?> cls)
/*      */   {
/* 1022 */     if (cls == null) {
/* 1023 */       return "";
/*      */     }
/* 1025 */     return getShortCanonicalName(cls.getName());
/*      */   }
/*      */ 
/*      */   public static String getShortCanonicalName(String canonicalName)
/*      */   {
/* 1038 */     return getShortClassName(getCanonicalName(canonicalName));
/*      */   }
/*      */ 
/*      */   public static String getPackageCanonicalName(Object object, String valueIfNull)
/*      */   {
/* 1052 */     if (object == null) {
/* 1053 */       return valueIfNull;
/*      */     }
/* 1055 */     return getPackageCanonicalName(object.getClass().getName());
/*      */   }
/*      */ 
/*      */   public static String getPackageCanonicalName(Class<?> cls)
/*      */   {
/* 1066 */     if (cls == null) {
/* 1067 */       return "";
/*      */     }
/* 1069 */     return getPackageCanonicalName(cls.getName());
/*      */   }
/*      */ 
/*      */   public static String getPackageCanonicalName(String canonicalName)
/*      */   {
/* 1083 */     return getPackageName(getCanonicalName(canonicalName));
/*      */   }
/*      */ 
/*      */   private static String getCanonicalName(String className)
/*      */   {
/* 1103 */     className = StringUtils.deleteWhitespace(className);
/* 1104 */     if (className == null) {
/* 1105 */       return null;
/*      */     }
/* 1107 */     int dim = 0;
/* 1108 */     while (className.startsWith("[")) {
/* 1109 */       dim++;
/* 1110 */       className = className.substring(1);
/*      */     }
/* 1112 */     if (dim < 1) {
/* 1113 */       return className;
/*      */     }
/* 1115 */     if (className.startsWith("L")) {
/* 1116 */       className = className.substring(1, className.endsWith(";") ? className.length() - 1 : className.length());
/*      */     }
/* 1122 */     else if (className.length() > 0) {
/* 1123 */       className = (String)reverseAbbreviationMap.get(className.substring(0, 1));
/*      */     }
/*      */ 
/* 1126 */     StringBuilder canonicalClassNameBuffer = new StringBuilder(className);
/* 1127 */     for (int i = 0; i < dim; i++) {
/* 1128 */       canonicalClassNameBuffer.append("[]");
/*      */     }
/* 1130 */     return canonicalClassNameBuffer.toString();
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*   70 */     primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
/*   71 */     primitiveWrapperMap.put(Byte.TYPE, Byte.class);
/*   72 */     primitiveWrapperMap.put(Character.TYPE, Character.class);
/*   73 */     primitiveWrapperMap.put(Short.TYPE, Short.class);
/*   74 */     primitiveWrapperMap.put(Integer.TYPE, Integer.class);
/*   75 */     primitiveWrapperMap.put(Long.TYPE, Long.class);
/*   76 */     primitiveWrapperMap.put(Double.TYPE, Double.class);
/*   77 */     primitiveWrapperMap.put(Float.TYPE, Float.class);
/*   78 */     primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
/*      */ 
/*   84 */     wrapperPrimitiveMap = new HashMap();
/*      */ 
/*   86 */     for (Class primitiveClass : primitiveWrapperMap.keySet()) {
/*   87 */       Class wrapperClass = (Class)primitiveWrapperMap.get(primitiveClass);
/*   88 */       if (!primitiveClass.equals(wrapperClass)) {
/*   89 */         wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*   97 */     abbreviationMap = new HashMap();
/*      */ 
/*  102 */     reverseAbbreviationMap = new HashMap();
/*      */ 
/*  119 */     addAbbreviation("int", "I");
/*  120 */     addAbbreviation("boolean", "Z");
/*  121 */     addAbbreviation("float", "F");
/*  122 */     addAbbreviation("long", "J");
/*  123 */     addAbbreviation("short", "S");
/*  124 */     addAbbreviation("byte", "B");
/*  125 */     addAbbreviation("double", "D");
/*  126 */     addAbbreviation("char", "C");
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.ClassUtils
 * JD-Core Version:    0.6.2
 */