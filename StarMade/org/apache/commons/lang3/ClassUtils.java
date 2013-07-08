package org.apache.commons.lang3;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassUtils
{
  public static final char PACKAGE_SEPARATOR_CHAR = '.';
  public static final String PACKAGE_SEPARATOR = String.valueOf('.');
  public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
  public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
  private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap();
  private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;
  private static final Map<String, String> abbreviationMap;
  private static final Map<String, String> reverseAbbreviationMap;
  
  private static void addAbbreviation(String primitive, String abbreviation)
  {
    abbreviationMap.put(primitive, abbreviation);
    reverseAbbreviationMap.put(abbreviation, primitive);
  }
  
  public static String getShortClassName(Object object, String valueIfNull)
  {
    if (object == null) {
      return valueIfNull;
    }
    return getShortClassName(object.getClass());
  }
  
  public static String getShortClassName(Class<?> cls)
  {
    if (cls == null) {
      return "";
    }
    return getShortClassName(cls.getName());
  }
  
  public static String getShortClassName(String className)
  {
    if (className == null) {
      return "";
    }
    if (className.length() == 0) {
      return "";
    }
    StringBuilder arrayPrefix = new StringBuilder();
    if (className.startsWith("["))
    {
      while (className.charAt(0) == '[')
      {
        className = className.substring(1);
        arrayPrefix.append("[]");
      }
      if ((className.charAt(0) == 'L') && (className.charAt(className.length() - 1) == ';')) {
        className = className.substring(1, className.length() - 1);
      }
    }
    if (reverseAbbreviationMap.containsKey(className)) {
      className = (String)reverseAbbreviationMap.get(className);
    }
    int lastDotIdx = className.lastIndexOf('.');
    int innerIdx = className.indexOf('$', lastDotIdx == -1 ? 0 : lastDotIdx + 1);
    String out = className.substring(lastDotIdx + 1);
    if (innerIdx != -1) {
      out = out.replace('$', '.');
    }
    return out + arrayPrefix;
  }
  
  public static String getSimpleName(Class<?> cls)
  {
    if (cls == null) {
      return "";
    }
    return cls.getSimpleName();
  }
  
  public static String getSimpleName(Object object, String valueIfNull)
  {
    if (object == null) {
      return valueIfNull;
    }
    return getSimpleName(object.getClass());
  }
  
  public static String getPackageName(Object object, String valueIfNull)
  {
    if (object == null) {
      return valueIfNull;
    }
    return getPackageName(object.getClass());
  }
  
  public static String getPackageName(Class<?> cls)
  {
    if (cls == null) {
      return "";
    }
    return getPackageName(cls.getName());
  }
  
  public static String getPackageName(String className)
  {
    if ((className == null) || (className.length() == 0)) {
      return "";
    }
    while (className.charAt(0) == '[') {
      className = className.substring(1);
    }
    if ((className.charAt(0) == 'L') && (className.charAt(className.length() - 1) == ';')) {
      className = className.substring(1);
    }
    int local_i = className.lastIndexOf('.');
    if (local_i == -1) {
      return "";
    }
    return className.substring(0, local_i);
  }
  
  public static List<Class<?>> getAllSuperclasses(Class<?> cls)
  {
    if (cls == null) {
      return null;
    }
    List<Class<?>> classes = new ArrayList();
    for (Class<?> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
      classes.add(superclass);
    }
    return classes;
  }
  
  public static List<Class<?>> getAllInterfaces(Class<?> cls)
  {
    if (cls == null) {
      return null;
    }
    LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet();
    getAllInterfaces(cls, interfacesFound);
    return new ArrayList(interfacesFound);
  }
  
  private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> interfacesFound)
  {
    while (cls != null)
    {
      Class<?>[] interfaces = cls.getInterfaces();
      for (Class<?> local_i : interfaces) {
        if (interfacesFound.add(local_i)) {
          getAllInterfaces(local_i, interfacesFound);
        }
      }
      cls = cls.getSuperclass();
    }
  }
  
  public static List<Class<?>> convertClassNamesToClasses(List<String> classNames)
  {
    if (classNames == null) {
      return null;
    }
    List<Class<?>> classes = new ArrayList(classNames.size());
    Iterator local_i$ = classNames.iterator();
    while (local_i$.hasNext())
    {
      String className = (String)local_i$.next();
      try
      {
        classes.add(Class.forName(className));
      }
      catch (Exception local_ex)
      {
        classes.add(null);
      }
    }
    return classes;
  }
  
  public static List<String> convertClassesToClassNames(List<Class<?>> classes)
  {
    if (classes == null) {
      return null;
    }
    List<String> classNames = new ArrayList(classes.size());
    Iterator local_i$ = classes.iterator();
    while (local_i$.hasNext())
    {
      Class<?> cls = (Class)local_i$.next();
      if (cls == null) {
        classNames.add(null);
      } else {
        classNames.add(cls.getName());
      }
    }
    return classNames;
  }
  
  public static boolean isAssignable(Class<?>[] classArray, Class<?>... toClassArray)
  {
    return isAssignable(classArray, toClassArray, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
  }
  
  public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray, boolean autoboxing)
  {
    if (!ArrayUtils.isSameLength(classArray, toClassArray)) {
      return false;
    }
    if (classArray == null) {
      classArray = ArrayUtils.EMPTY_CLASS_ARRAY;
    }
    if (toClassArray == null) {
      toClassArray = ArrayUtils.EMPTY_CLASS_ARRAY;
    }
    for (int local_i = 0; local_i < classArray.length; local_i++) {
      if (!isAssignable(classArray[local_i], toClassArray[local_i], autoboxing)) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isPrimitiveOrWrapper(Class<?> type)
  {
    if (type == null) {
      return false;
    }
    return (type.isPrimitive()) || (isPrimitiveWrapper(type));
  }
  
  public static boolean isPrimitiveWrapper(Class<?> type)
  {
    return wrapperPrimitiveMap.containsKey(type);
  }
  
  public static boolean isAssignable(Class<?> cls, Class<?> toClass)
  {
    return isAssignable(cls, toClass, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
  }
  
  public static boolean isAssignable(Class<?> cls, Class<?> toClass, boolean autoboxing)
  {
    if (toClass == null) {
      return false;
    }
    if (cls == null) {
      return !toClass.isPrimitive();
    }
    if (autoboxing)
    {
      if ((cls.isPrimitive()) && (!toClass.isPrimitive()))
      {
        cls = primitiveToWrapper(cls);
        if (cls == null) {
          return false;
        }
      }
      if ((toClass.isPrimitive()) && (!cls.isPrimitive()))
      {
        cls = wrapperToPrimitive(cls);
        if (cls == null) {
          return false;
        }
      }
    }
    if (cls.equals(toClass)) {
      return true;
    }
    if (cls.isPrimitive())
    {
      if (!toClass.isPrimitive()) {
        return false;
      }
      if (Integer.TYPE.equals(cls)) {
        return (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
      }
      if (Long.TYPE.equals(cls)) {
        return (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
      }
      if (Boolean.TYPE.equals(cls)) {
        return false;
      }
      if (Double.TYPE.equals(cls)) {
        return false;
      }
      if (Float.TYPE.equals(cls)) {
        return Double.TYPE.equals(toClass);
      }
      if (Character.TYPE.equals(cls)) {
        return (Integer.TYPE.equals(toClass)) || (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
      }
      if (Short.TYPE.equals(cls)) {
        return (Integer.TYPE.equals(toClass)) || (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
      }
      if (Byte.TYPE.equals(cls)) {
        return (Short.TYPE.equals(toClass)) || (Integer.TYPE.equals(toClass)) || (Long.TYPE.equals(toClass)) || (Float.TYPE.equals(toClass)) || (Double.TYPE.equals(toClass));
      }
      return false;
    }
    return toClass.isAssignableFrom(cls);
  }
  
  public static Class<?> primitiveToWrapper(Class<?> cls)
  {
    Class<?> convertedClass = cls;
    if ((cls != null) && (cls.isPrimitive())) {
      convertedClass = (Class)primitiveWrapperMap.get(cls);
    }
    return convertedClass;
  }
  
  public static Class<?>[] primitivesToWrappers(Class<?>... classes)
  {
    if (classes == null) {
      return null;
    }
    if (classes.length == 0) {
      return classes;
    }
    Class<?>[] convertedClasses = new Class[classes.length];
    for (int local_i = 0; local_i < classes.length; local_i++) {
      convertedClasses[local_i] = primitiveToWrapper(classes[local_i]);
    }
    return convertedClasses;
  }
  
  public static Class<?> wrapperToPrimitive(Class<?> cls)
  {
    return (Class)wrapperPrimitiveMap.get(cls);
  }
  
  public static Class<?>[] wrappersToPrimitives(Class<?>... classes)
  {
    if (classes == null) {
      return null;
    }
    if (classes.length == 0) {
      return classes;
    }
    Class<?>[] convertedClasses = new Class[classes.length];
    for (int local_i = 0; local_i < classes.length; local_i++) {
      convertedClasses[local_i] = wrapperToPrimitive(classes[local_i]);
    }
    return convertedClasses;
  }
  
  public static boolean isInnerClass(Class<?> cls)
  {
    return (cls != null) && (cls.getEnclosingClass() != null);
  }
  
  public static Class<?> getClass(ClassLoader classLoader, String className, boolean initialize)
    throws ClassNotFoundException
  {
    try
    {
      Class<?> clazz;
      Class<?> clazz;
      if (abbreviationMap.containsKey(className))
      {
        String clsName = "[" + (String)abbreviationMap.get(className);
        clazz = Class.forName(clsName, initialize, classLoader).getComponentType();
      }
      else
      {
        clazz = Class.forName(toCanonicalName(className), initialize, classLoader);
      }
      return clazz;
    }
    catch (ClassNotFoundException clazz)
    {
      int clsName = className.lastIndexOf('.');
      if (clsName != -1) {
        try
        {
          return getClass(classLoader, className.substring(0, clsName) + '$' + className.substring(clsName + 1), initialize);
        }
        catch (ClassNotFoundException ex2) {}
      }
      throw clazz;
    }
  }
  
  public static Class<?> getClass(ClassLoader classLoader, String className)
    throws ClassNotFoundException
  {
    return getClass(classLoader, className, true);
  }
  
  public static Class<?> getClass(String className)
    throws ClassNotFoundException
  {
    return getClass(className, true);
  }
  
  public static Class<?> getClass(String className, boolean initialize)
    throws ClassNotFoundException
  {
    ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
    ClassLoader loader = contextCL == null ? ClassUtils.class.getClassLoader() : contextCL;
    return getClass(loader, className, initialize);
  }
  
  public static Method getPublicMethod(Class<?> cls, String methodName, Class<?>... parameterTypes)
    throws SecurityException, NoSuchMethodException
  {
    Method declaredMethod = cls.getMethod(methodName, parameterTypes);
    if (Modifier.isPublic(declaredMethod.getDeclaringClass().getModifiers())) {
      return declaredMethod;
    }
    List<Class<?>> candidateClasses = new ArrayList();
    candidateClasses.addAll(getAllInterfaces(cls));
    candidateClasses.addAll(getAllSuperclasses(cls));
    Iterator local_i$ = candidateClasses.iterator();
    while (local_i$.hasNext())
    {
      Class<?> candidateClass = (Class)local_i$.next();
      if (Modifier.isPublic(candidateClass.getModifiers()))
      {
        Method candidateMethod;
        try
        {
          candidateMethod = candidateClass.getMethod(methodName, parameterTypes);
        }
        catch (NoSuchMethodException local_ex) {}
        continue;
        if (Modifier.isPublic(candidateMethod.getDeclaringClass().getModifiers())) {
          return candidateMethod;
        }
      }
    }
    throw new NoSuchMethodException("Can't find a public method for " + methodName + " " + ArrayUtils.toString(parameterTypes));
  }
  
  private static String toCanonicalName(String className)
  {
    className = StringUtils.deleteWhitespace(className);
    if (className == null) {
      throw new NullPointerException("className must not be null.");
    }
    if (className.endsWith("[]"))
    {
      StringBuilder classNameBuffer = new StringBuilder();
      while (className.endsWith("[]"))
      {
        className = className.substring(0, className.length() - 2);
        classNameBuffer.append("[");
      }
      String abbreviation = (String)abbreviationMap.get(className);
      if (abbreviation != null) {
        classNameBuffer.append(abbreviation);
      } else {
        classNameBuffer.append("L").append(className).append(";");
      }
      className = classNameBuffer.toString();
    }
    return className;
  }
  
  public static Class<?>[] toClass(Object... array)
  {
    if (array == null) {
      return null;
    }
    if (array.length == 0) {
      return ArrayUtils.EMPTY_CLASS_ARRAY;
    }
    Class<?>[] classes = new Class[array.length];
    for (int local_i = 0; local_i < array.length; local_i++) {
      classes[local_i] = (array[local_i] == null ? null : array[local_i].getClass());
    }
    return classes;
  }
  
  public static String getShortCanonicalName(Object object, String valueIfNull)
  {
    if (object == null) {
      return valueIfNull;
    }
    return getShortCanonicalName(object.getClass().getName());
  }
  
  public static String getShortCanonicalName(Class<?> cls)
  {
    if (cls == null) {
      return "";
    }
    return getShortCanonicalName(cls.getName());
  }
  
  public static String getShortCanonicalName(String canonicalName)
  {
    return getShortClassName(getCanonicalName(canonicalName));
  }
  
  public static String getPackageCanonicalName(Object object, String valueIfNull)
  {
    if (object == null) {
      return valueIfNull;
    }
    return getPackageCanonicalName(object.getClass().getName());
  }
  
  public static String getPackageCanonicalName(Class<?> cls)
  {
    if (cls == null) {
      return "";
    }
    return getPackageCanonicalName(cls.getName());
  }
  
  public static String getPackageCanonicalName(String canonicalName)
  {
    return getPackageName(getCanonicalName(canonicalName));
  }
  
  private static String getCanonicalName(String className)
  {
    className = StringUtils.deleteWhitespace(className);
    if (className == null) {
      return null;
    }
    int dim = 0;
    while (className.startsWith("["))
    {
      dim++;
      className = className.substring(1);
    }
    if (dim < 1) {
      return className;
    }
    if (className.startsWith("L")) {
      className = className.substring(1, className.endsWith(";") ? className.length() - 1 : className.length());
    } else if (className.length() > 0) {
      className = (String)reverseAbbreviationMap.get(className.substring(0, 1));
    }
    StringBuilder canonicalClassNameBuffer = new StringBuilder(className);
    for (int local_i = 0; local_i < dim; local_i++) {
      canonicalClassNameBuffer.append("[]");
    }
    return canonicalClassNameBuffer.toString();
  }
  
  static
  {
    primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
    primitiveWrapperMap.put(Byte.TYPE, Byte.class);
    primitiveWrapperMap.put(Character.TYPE, Character.class);
    primitiveWrapperMap.put(Short.TYPE, Short.class);
    primitiveWrapperMap.put(Integer.TYPE, Integer.class);
    primitiveWrapperMap.put(Long.TYPE, Long.class);
    primitiveWrapperMap.put(Double.TYPE, Double.class);
    primitiveWrapperMap.put(Float.TYPE, Float.class);
    primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
    wrapperPrimitiveMap = new HashMap();
    Iterator local_i$ = primitiveWrapperMap.keySet().iterator();
    while (local_i$.hasNext())
    {
      Class<?> primitiveClass = (Class)local_i$.next();
      Class<?> wrapperClass = (Class)primitiveWrapperMap.get(primitiveClass);
      if (!primitiveClass.equals(wrapperClass)) {
        wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
      }
    }
    abbreviationMap = new HashMap();
    reverseAbbreviationMap = new HashMap();
    addAbbreviation("int", "I");
    addAbbreviation("boolean", "Z");
    addAbbreviation("float", "F");
    addAbbreviation("long", "J");
    addAbbreviation("short", "S");
    addAbbreviation("byte", "B");
    addAbbreviation("double", "D");
    addAbbreviation("char", "C");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.ClassUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */