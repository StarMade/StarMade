package org.apache.commons.lang3.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

public class ConstructorUtils
{
  public static <T> T invokeConstructor(Class<T> cls, Object... args)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
  {
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    Class<?>[] parameterTypes = new Class[args.length];
    for (int local_i = 0; local_i < args.length; local_i++) {
      parameterTypes[local_i] = args[local_i].getClass();
    }
    return invokeConstructor(cls, args, parameterTypes);
  }
  
  public static <T> T invokeConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
  {
    if (parameterTypes == null) {
      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
    }
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    Constructor<T> ctor = getMatchingAccessibleConstructor(cls, parameterTypes);
    if (ctor == null) {
      throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
    }
    return ctor.newInstance(args);
  }
  
  public static <T> T invokeExactConstructor(Class<T> cls, Object... args)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
  {
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    int arguments = args.length;
    Class<?>[] parameterTypes = new Class[arguments];
    for (int local_i = 0; local_i < arguments; local_i++) {
      parameterTypes[local_i] = args[local_i].getClass();
    }
    return invokeExactConstructor(cls, args, parameterTypes);
  }
  
  public static <T> T invokeExactConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
  {
    if (args == null) {
      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
    }
    if (parameterTypes == null) {
      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
    }
    Constructor<T> ctor = getAccessibleConstructor(cls, parameterTypes);
    if (ctor == null) {
      throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
    }
    return ctor.newInstance(args);
  }
  
  public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes)
  {
    try
    {
      return getAccessibleConstructor(cls.getConstructor(parameterTypes));
    }
    catch (NoSuchMethodException local_e) {}
    return null;
  }
  
  public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> ctor)
  {
    return (MemberUtils.isAccessible(ctor)) && (Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) ? ctor : null;
  }
  
  public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes)
  {
    try
    {
      Constructor<T> ctor = cls.getConstructor(parameterTypes);
      MemberUtils.setAccessibleWorkaround(ctor);
      return ctor;
    }
    catch (NoSuchMethodException ctor)
    {
      Constructor<T> ctor = null;
      Constructor<?>[] ctors = cls.getConstructors();
      for (Constructor<?> ctor : ctors) {
        if (ClassUtils.isAssignable(parameterTypes, ctor.getParameterTypes(), true))
        {
          ctor = getAccessibleConstructor(ctor);
          if (ctor != null)
          {
            MemberUtils.setAccessibleWorkaround(ctor);
            if ((ctor == null) || (MemberUtils.compareParameterTypes(ctor.getParameterTypes(), ctor.getParameterTypes(), parameterTypes) < 0))
            {
              Constructor<T> constructor = ctor;
              ctor = constructor;
            }
          }
        }
      }
      return ctor;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.reflect.ConstructorUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */