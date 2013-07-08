package org.apache.commons.lang3.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

public class MethodUtils
{
  public static Object invokeMethod(Object object, String methodName, Object... args)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    int arguments = args.length;
    Class<?>[] parameterTypes = new Class[arguments];
    for (int local_i = 0; local_i < arguments; local_i++) {
      parameterTypes[local_i] = args[local_i].getClass();
    }
    return invokeMethod(object, methodName, args, parameterTypes);
  }
  
  public static Object invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    if (parameterTypes == null) {
      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
    }
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    Method method = getMatchingAccessibleMethod(object.getClass(), methodName, parameterTypes);
    if (method == null) {
      throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
    }
    return method.invoke(object, args);
  }
  
  public static Object invokeExactMethod(Object object, String methodName, Object... args)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    int arguments = args.length;
    Class<?>[] parameterTypes = new Class[arguments];
    for (int local_i = 0; local_i < arguments; local_i++) {
      parameterTypes[local_i] = args[local_i].getClass();
    }
    return invokeExactMethod(object, methodName, args, parameterTypes);
  }
  
  public static Object invokeExactMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    if (parameterTypes == null) {
      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
    }
    Method method = getAccessibleMethod(object.getClass(), methodName, parameterTypes);
    if (method == null) {
      throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
    }
    return method.invoke(object, args);
  }
  
  public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    if (parameterTypes == null) {
      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
    }
    Method method = getAccessibleMethod(cls, methodName, parameterTypes);
    if (method == null) {
      throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
    }
    return method.invoke(null, args);
  }
  
  public static Object invokeStaticMethod(Class<?> cls, String methodName, Object... args)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    int arguments = args.length;
    Class<?>[] parameterTypes = new Class[arguments];
    for (int local_i = 0; local_i < arguments; local_i++) {
      parameterTypes[local_i] = args[local_i].getClass();
    }
    return invokeStaticMethod(cls, methodName, args, parameterTypes);
  }
  
  public static Object invokeStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    if (parameterTypes == null) {
      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
    }
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    Method method = getMatchingAccessibleMethod(cls, methodName, parameterTypes);
    if (method == null) {
      throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
    }
    return method.invoke(null, args);
  }
  
  public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object... args)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    int arguments = args.length;
    Class<?>[] parameterTypes = new Class[arguments];
    for (int local_i = 0; local_i < arguments; local_i++) {
      parameterTypes[local_i] = args[local_i].getClass();
    }
    return invokeExactStaticMethod(cls, methodName, args, parameterTypes);
  }
  
  public static Method getAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes)
  {
    try
    {
      return getAccessibleMethod(cls.getMethod(methodName, parameterTypes));
    }
    catch (NoSuchMethodException local_e) {}
    return null;
  }
  
  public static Method getAccessibleMethod(Method method)
  {
    if (!MemberUtils.isAccessible(method)) {
      return null;
    }
    Class<?> cls = method.getDeclaringClass();
    if (Modifier.isPublic(cls.getModifiers())) {
      return method;
    }
    String methodName = method.getName();
    Class<?>[] parameterTypes = method.getParameterTypes();
    method = getAccessibleMethodFromInterfaceNest(cls, methodName, parameterTypes);
    if (method == null) {
      method = getAccessibleMethodFromSuperclass(cls, methodName, parameterTypes);
    }
    return method;
  }
  
  private static Method getAccessibleMethodFromSuperclass(Class<?> cls, String methodName, Class<?>... parameterTypes)
  {
    for (Class<?> parentClass = cls.getSuperclass(); parentClass != null; parentClass = parentClass.getSuperclass()) {
      if (Modifier.isPublic(parentClass.getModifiers())) {
        try
        {
          return parentClass.getMethod(methodName, parameterTypes);
        }
        catch (NoSuchMethodException local_e)
        {
          return null;
        }
      }
    }
    return null;
  }
  
  private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, String methodName, Class<?>... parameterTypes)
  {
    Method method = null;
    while (cls != null)
    {
      Class<?>[] interfaces = cls.getInterfaces();
      for (int local_i = 0; local_i < interfaces.length; local_i++) {
        if (Modifier.isPublic(interfaces[local_i].getModifiers()))
        {
          try
          {
            method = interfaces[local_i].getDeclaredMethod(methodName, parameterTypes);
          }
          catch (NoSuchMethodException local_e) {}
          if (method != null) {
            break;
          }
          method = getAccessibleMethodFromInterfaceNest(interfaces[local_i], methodName, parameterTypes);
          if (method != null) {
            break;
          }
        }
      }
      cls = cls.getSuperclass();
    }
    return method;
  }
  
  public static Method getMatchingAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes)
  {
    try
    {
      Method method = cls.getMethod(methodName, parameterTypes);
      MemberUtils.setAccessibleWorkaround(method);
      return method;
    }
    catch (NoSuchMethodException method)
    {
      Method method = null;
      Method[] methods = cls.getMethods();
      for (Method method : methods) {
        if ((method.getName().equals(methodName)) && (ClassUtils.isAssignable(parameterTypes, method.getParameterTypes(), true)))
        {
          Method accessibleMethod = getAccessibleMethod(method);
          if ((accessibleMethod != null) && ((method == null) || (MemberUtils.compareParameterTypes(accessibleMethod.getParameterTypes(), method.getParameterTypes(), parameterTypes) < 0))) {
            method = accessibleMethod;
          }
        }
      }
      if (method != null) {
        MemberUtils.setAccessibleWorkaround(method);
      }
      return method;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.reflect.MethodUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */