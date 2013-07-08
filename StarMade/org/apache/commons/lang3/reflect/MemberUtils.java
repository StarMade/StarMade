package org.apache.commons.lang3.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.ClassUtils;

abstract class MemberUtils
{
  private static final int ACCESS_TEST = 7;
  private static final Class<?>[] ORDERED_PRIMITIVE_TYPES = { Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE };
  
  static void setAccessibleWorkaround(AccessibleObject local_o)
  {
    if ((local_o == null) || (local_o.isAccessible())) {
      return;
    }
    Member local_m = (Member)local_o;
    if ((Modifier.isPublic(local_m.getModifiers())) && (isPackageAccess(local_m.getDeclaringClass().getModifiers()))) {
      try
      {
        local_o.setAccessible(true);
      }
      catch (SecurityException local_e) {}
    }
  }
  
  static boolean isPackageAccess(int modifiers)
  {
    return (modifiers & 0x7) == 0;
  }
  
  static boolean isAccessible(Member local_m)
  {
    return (local_m != null) && (Modifier.isPublic(local_m.getModifiers())) && (!local_m.isSynthetic());
  }
  
  static int compareParameterTypes(Class<?>[] left, Class<?>[] right, Class<?>[] actual)
  {
    float leftCost = getTotalTransformationCost(actual, left);
    float rightCost = getTotalTransformationCost(actual, right);
    return rightCost < leftCost ? 1 : leftCost < rightCost ? -1 : 0;
  }
  
  private static float getTotalTransformationCost(Class<?>[] srcArgs, Class<?>[] destArgs)
  {
    float totalCost = 0.0F;
    for (int local_i = 0; local_i < srcArgs.length; local_i++)
    {
      Class<?> srcClass = srcArgs[local_i];
      Class<?> destClass = destArgs[local_i];
      totalCost += getObjectTransformationCost(srcClass, destClass);
    }
    return totalCost;
  }
  
  private static float getObjectTransformationCost(Class<?> srcClass, Class<?> destClass)
  {
    if (destClass.isPrimitive()) {
      return getPrimitivePromotionCost(srcClass, destClass);
    }
    float cost = 0.0F;
    while ((srcClass != null) && (!destClass.equals(srcClass)))
    {
      if ((destClass.isInterface()) && (ClassUtils.isAssignable(srcClass, destClass)))
      {
        cost += 0.25F;
        break;
      }
      cost += 1.0F;
      srcClass = srcClass.getSuperclass();
    }
    if (srcClass == null) {
      cost += 1.5F;
    }
    return cost;
  }
  
  private static float getPrimitivePromotionCost(Class<?> srcClass, Class<?> destClass)
  {
    float cost = 0.0F;
    Class<?> cls = srcClass;
    if (!cls.isPrimitive())
    {
      cost += 0.1F;
      cls = ClassUtils.wrapperToPrimitive(cls);
    }
    for (int local_i = 0; (cls != destClass) && (local_i < ORDERED_PRIMITIVE_TYPES.length); local_i++) {
      if (cls == ORDERED_PRIMITIVE_TYPES[local_i])
      {
        cost += 0.1F;
        if (local_i < ORDERED_PRIMITIVE_TYPES.length - 1) {
          cls = ORDERED_PRIMITIVE_TYPES[(local_i + 1)];
        }
      }
    }
    return cost;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.reflect.MemberUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */