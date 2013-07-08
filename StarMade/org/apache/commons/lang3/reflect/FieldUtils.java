package org.apache.commons.lang3.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.ClassUtils;

public class FieldUtils
{
  public static Field getField(Class<?> cls, String fieldName)
  {
    Field field = getField(cls, fieldName, false);
    MemberUtils.setAccessibleWorkaround(field);
    return field;
  }
  
  public static Field getField(Class<?> cls, String fieldName, boolean forceAccess)
  {
    if (cls == null) {
      throw new IllegalArgumentException("The class must not be null");
    }
    if (fieldName == null) {
      throw new IllegalArgumentException("The field name must not be null");
    }
    Class<?> acls = cls;
    while (acls != null) {
      try
      {
        Field field = acls.getDeclaredField(fieldName);
        if (!Modifier.isPublic(field.getModifiers())) {
          if (forceAccess) {
            field.setAccessible(true);
          } else {
            break label73;
          }
        }
        return field;
      }
      catch (NoSuchFieldException field)
      {
        label73:
        acls = acls.getSuperclass();
      }
    }
    Field acls = null;
    Iterator field = ClassUtils.getAllInterfaces(cls).iterator();
    while (field.hasNext())
    {
      Class<?> class1 = (Class)field.next();
      try
      {
        Field test = class1.getField(fieldName);
        if (acls != null) {
          throw new IllegalArgumentException("Reference to field " + fieldName + " is ambiguous relative to " + cls + "; a matching field exists on two or more implemented interfaces.");
        }
        acls = test;
      }
      catch (NoSuchFieldException test) {}
    }
    return acls;
  }
  
  public static Field getDeclaredField(Class<?> cls, String fieldName)
  {
    return getDeclaredField(cls, fieldName, false);
  }
  
  public static Field getDeclaredField(Class<?> cls, String fieldName, boolean forceAccess)
  {
    if (cls == null) {
      throw new IllegalArgumentException("The class must not be null");
    }
    if (fieldName == null) {
      throw new IllegalArgumentException("The field name must not be null");
    }
    try
    {
      Field field = cls.getDeclaredField(fieldName);
      if (!MemberUtils.isAccessible(field)) {
        if (forceAccess) {
          field.setAccessible(true);
        } else {
          return null;
        }
      }
      return field;
    }
    catch (NoSuchFieldException field) {}
    return null;
  }
  
  public static Object readStaticField(Field field)
    throws IllegalAccessException
  {
    return readStaticField(field, false);
  }
  
  public static Object readStaticField(Field field, boolean forceAccess)
    throws IllegalAccessException
  {
    if (field == null) {
      throw new IllegalArgumentException("The field must not be null");
    }
    if (!Modifier.isStatic(field.getModifiers())) {
      throw new IllegalArgumentException("The field '" + field.getName() + "' is not static");
    }
    return readField(field, (Object)null, forceAccess);
  }
  
  public static Object readStaticField(Class<?> cls, String fieldName)
    throws IllegalAccessException
  {
    return readStaticField(cls, fieldName, false);
  }
  
  public static Object readStaticField(Class<?> cls, String fieldName, boolean forceAccess)
    throws IllegalAccessException
  {
    Field field = getField(cls, fieldName, forceAccess);
    if (field == null) {
      throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls);
    }
    return readStaticField(field, false);
  }
  
  public static Object readDeclaredStaticField(Class<?> cls, String fieldName)
    throws IllegalAccessException
  {
    return readDeclaredStaticField(cls, fieldName, false);
  }
  
  public static Object readDeclaredStaticField(Class<?> cls, String fieldName, boolean forceAccess)
    throws IllegalAccessException
  {
    Field field = getDeclaredField(cls, fieldName, forceAccess);
    if (field == null) {
      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
    }
    return readStaticField(field, false);
  }
  
  public static Object readField(Field field, Object target)
    throws IllegalAccessException
  {
    return readField(field, target, false);
  }
  
  public static Object readField(Field field, Object target, boolean forceAccess)
    throws IllegalAccessException
  {
    if (field == null) {
      throw new IllegalArgumentException("The field must not be null");
    }
    if ((forceAccess) && (!field.isAccessible())) {
      field.setAccessible(true);
    } else {
      MemberUtils.setAccessibleWorkaround(field);
    }
    return field.get(target);
  }
  
  public static Object readField(Object target, String fieldName)
    throws IllegalAccessException
  {
    return readField(target, fieldName, false);
  }
  
  public static Object readField(Object target, String fieldName, boolean forceAccess)
    throws IllegalAccessException
  {
    if (target == null) {
      throw new IllegalArgumentException("target object must not be null");
    }
    Class<?> cls = target.getClass();
    Field field = getField(cls, fieldName, forceAccess);
    if (field == null) {
      throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls);
    }
    return readField(field, target);
  }
  
  public static Object readDeclaredField(Object target, String fieldName)
    throws IllegalAccessException
  {
    return readDeclaredField(target, fieldName, false);
  }
  
  public static Object readDeclaredField(Object target, String fieldName, boolean forceAccess)
    throws IllegalAccessException
  {
    if (target == null) {
      throw new IllegalArgumentException("target object must not be null");
    }
    Class<?> cls = target.getClass();
    Field field = getDeclaredField(cls, fieldName, forceAccess);
    if (field == null) {
      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
    }
    return readField(field, target);
  }
  
  public static void writeStaticField(Field field, Object value)
    throws IllegalAccessException
  {
    writeStaticField(field, value, false);
  }
  
  public static void writeStaticField(Field field, Object value, boolean forceAccess)
    throws IllegalAccessException
  {
    if (field == null) {
      throw new IllegalArgumentException("The field must not be null");
    }
    if (!Modifier.isStatic(field.getModifiers())) {
      throw new IllegalArgumentException("The field '" + field.getName() + "' is not static");
    }
    writeField(field, (Object)null, value, forceAccess);
  }
  
  public static void writeStaticField(Class<?> cls, String fieldName, Object value)
    throws IllegalAccessException
  {
    writeStaticField(cls, fieldName, value, false);
  }
  
  public static void writeStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess)
    throws IllegalAccessException
  {
    Field field = getField(cls, fieldName, forceAccess);
    if (field == null) {
      throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls);
    }
    writeStaticField(field, value);
  }
  
  public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value)
    throws IllegalAccessException
  {
    writeDeclaredStaticField(cls, fieldName, value, false);
  }
  
  public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess)
    throws IllegalAccessException
  {
    Field field = getDeclaredField(cls, fieldName, forceAccess);
    if (field == null) {
      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
    }
    writeField(field, (Object)null, value);
  }
  
  public static void writeField(Field field, Object target, Object value)
    throws IllegalAccessException
  {
    writeField(field, target, value, false);
  }
  
  public static void writeField(Field field, Object target, Object value, boolean forceAccess)
    throws IllegalAccessException
  {
    if (field == null) {
      throw new IllegalArgumentException("The field must not be null");
    }
    if ((forceAccess) && (!field.isAccessible())) {
      field.setAccessible(true);
    } else {
      MemberUtils.setAccessibleWorkaround(field);
    }
    field.set(target, value);
  }
  
  public static void writeField(Object target, String fieldName, Object value)
    throws IllegalAccessException
  {
    writeField(target, fieldName, value, false);
  }
  
  public static void writeField(Object target, String fieldName, Object value, boolean forceAccess)
    throws IllegalAccessException
  {
    if (target == null) {
      throw new IllegalArgumentException("target object must not be null");
    }
    Class<?> cls = target.getClass();
    Field field = getField(cls, fieldName, forceAccess);
    if (field == null) {
      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
    }
    writeField(field, target, value);
  }
  
  public static void writeDeclaredField(Object target, String fieldName, Object value)
    throws IllegalAccessException
  {
    writeDeclaredField(target, fieldName, value, false);
  }
  
  public static void writeDeclaredField(Object target, String fieldName, Object value, boolean forceAccess)
    throws IllegalAccessException
  {
    if (target == null) {
      throw new IllegalArgumentException("target object must not be null");
    }
    Class<?> cls = target.getClass();
    Field field = getDeclaredField(cls, fieldName, forceAccess);
    if (field == null) {
      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
    }
    writeField(field, target, value);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.reflect.FieldUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */