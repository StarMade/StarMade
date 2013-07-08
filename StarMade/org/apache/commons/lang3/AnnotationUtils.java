package org.apache.commons.lang3;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AnnotationUtils
{
  private static final ToStringStyle TO_STRING_STYLE = new ToStringStyle()
  {
    private static final long serialVersionUID = 1L;
    
    protected String getShortClassName(Class<?> cls)
    {
      Class<? extends Annotation> annotationType = null;
      Iterator local_i$ = ClassUtils.getAllInterfaces(cls).iterator();
      while (local_i$.hasNext())
      {
        Class<?> iface = (Class)local_i$.next();
        if (Annotation.class.isAssignableFrom(iface))
        {
          Class<? extends Annotation> found = iface;
          annotationType = found;
          break;
        }
      }
      return new StringBuilder(annotationType == null ? "" : annotationType.getName()).insert(0, '@').toString();
    }
    
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value)
    {
      if ((value instanceof Annotation)) {
        value = AnnotationUtils.toString((Annotation)value);
      }
      super.appendDetail(buffer, fieldName, value);
    }
  };
  
  public static boolean equals(Annotation local_a1, Annotation local_a2)
  {
    if (local_a1 == local_a2) {
      return true;
    }
    if ((local_a1 == null) || (local_a2 == null)) {
      return false;
    }
    Class<? extends Annotation> type = local_a1.annotationType();
    Class<? extends Annotation> type2 = local_a2.annotationType();
    Validate.notNull(type, "Annotation %s with null annotationType()", new Object[] { local_a1 });
    Validate.notNull(type2, "Annotation %s with null annotationType()", new Object[] { local_a2 });
    if (!type.equals(type2)) {
      return false;
    }
    try
    {
      for (Method local_m : type.getDeclaredMethods()) {
        if ((local_m.getParameterTypes().length == 0) && (isValidAnnotationMemberType(local_m.getReturnType())))
        {
          Object local_v1 = local_m.invoke(local_a1, new Object[0]);
          Object local_v2 = local_m.invoke(local_a2, new Object[0]);
          if (!memberEquals(local_m.getReturnType(), local_v1, local_v2)) {
            return false;
          }
        }
      }
    }
    catch (IllegalAccessException arr$)
    {
      return false;
    }
    catch (InvocationTargetException arr$)
    {
      return false;
    }
    return true;
  }
  
  public static int hashCode(Annotation local_a)
  {
    int result = 0;
    Class<? extends Annotation> type = local_a.annotationType();
    for (Method local_m : type.getDeclaredMethods()) {
      try
      {
        Object value = local_m.invoke(local_a, new Object[0]);
        if (value == null) {
          throw new IllegalStateException(String.format("Annotation method %s returned null", new Object[] { local_m }));
        }
        result += hashMember(local_m.getName(), value);
      }
      catch (RuntimeException value)
      {
        throw value;
      }
      catch (Exception value)
      {
        throw new RuntimeException(value);
      }
    }
    return result;
  }
  
  public static String toString(Annotation local_a)
  {
    ToStringBuilder builder = new ToStringBuilder(local_a, TO_STRING_STYLE);
    for (Method local_m : local_a.annotationType().getDeclaredMethods()) {
      if (local_m.getParameterTypes().length <= 0) {
        try
        {
          builder.append(local_m.getName(), local_m.invoke(local_a, new Object[0]));
        }
        catch (RuntimeException local_ex)
        {
          throw local_ex;
        }
        catch (Exception local_ex)
        {
          throw new RuntimeException(local_ex);
        }
      }
    }
    return builder.build();
  }
  
  public static boolean isValidAnnotationMemberType(Class<?> type)
  {
    if (type == null) {
      return false;
    }
    if (type.isArray()) {
      type = type.getComponentType();
    }
    return (type.isPrimitive()) || (type.isEnum()) || (type.isAnnotation()) || (String.class.equals(type)) || (Class.class.equals(type));
  }
  
  private static int hashMember(String name, Object value)
  {
    int part1 = name.hashCode() * 127;
    if (value.getClass().isArray()) {
      return part1 ^ arrayMemberHash(value.getClass().getComponentType(), value);
    }
    if ((value instanceof Annotation)) {
      return part1 ^ hashCode((Annotation)value);
    }
    return part1 ^ value.hashCode();
  }
  
  private static boolean memberEquals(Class<?> type, Object local_o1, Object local_o2)
  {
    if (local_o1 == local_o2) {
      return true;
    }
    if ((local_o1 == null) || (local_o2 == null)) {
      return false;
    }
    if (type.isArray()) {
      return arrayMemberEquals(type.getComponentType(), local_o1, local_o2);
    }
    if (type.isAnnotation()) {
      return equals((Annotation)local_o1, (Annotation)local_o2);
    }
    return local_o1.equals(local_o2);
  }
  
  private static boolean arrayMemberEquals(Class<?> componentType, Object local_o1, Object local_o2)
  {
    if (componentType.isAnnotation()) {
      return annotationArrayMemberEquals((Annotation[])local_o1, (Annotation[])local_o2);
    }
    if (componentType.equals(Byte.TYPE)) {
      return Arrays.equals((byte[])local_o1, (byte[])local_o2);
    }
    if (componentType.equals(Short.TYPE)) {
      return Arrays.equals((short[])local_o1, (short[])local_o2);
    }
    if (componentType.equals(Integer.TYPE)) {
      return Arrays.equals((int[])local_o1, (int[])local_o2);
    }
    if (componentType.equals(Character.TYPE)) {
      return Arrays.equals((char[])local_o1, (char[])local_o2);
    }
    if (componentType.equals(Long.TYPE)) {
      return Arrays.equals((long[])local_o1, (long[])local_o2);
    }
    if (componentType.equals(Float.TYPE)) {
      return Arrays.equals((float[])local_o1, (float[])local_o2);
    }
    if (componentType.equals(Double.TYPE)) {
      return Arrays.equals((double[])local_o1, (double[])local_o2);
    }
    if (componentType.equals(Boolean.TYPE)) {
      return Arrays.equals((boolean[])local_o1, (boolean[])local_o2);
    }
    return Arrays.equals((Object[])local_o1, (Object[])local_o2);
  }
  
  private static boolean annotationArrayMemberEquals(Annotation[] local_a1, Annotation[] local_a2)
  {
    if (local_a1.length != local_a2.length) {
      return false;
    }
    for (int local_i = 0; local_i < local_a1.length; local_i++) {
      if (!equals(local_a1[local_i], local_a2[local_i])) {
        return false;
      }
    }
    return true;
  }
  
  private static int arrayMemberHash(Class<?> componentType, Object local_o)
  {
    if (componentType.equals(Byte.TYPE)) {
      return Arrays.hashCode((byte[])local_o);
    }
    if (componentType.equals(Short.TYPE)) {
      return Arrays.hashCode((short[])local_o);
    }
    if (componentType.equals(Integer.TYPE)) {
      return Arrays.hashCode((int[])local_o);
    }
    if (componentType.equals(Character.TYPE)) {
      return Arrays.hashCode((char[])local_o);
    }
    if (componentType.equals(Long.TYPE)) {
      return Arrays.hashCode((long[])local_o);
    }
    if (componentType.equals(Float.TYPE)) {
      return Arrays.hashCode((float[])local_o);
    }
    if (componentType.equals(Double.TYPE)) {
      return Arrays.hashCode((double[])local_o);
    }
    if (componentType.equals(Boolean.TYPE)) {
      return Arrays.hashCode((boolean[])local_o);
    }
    return Arrays.hashCode((Object[])local_o);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.AnnotationUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */