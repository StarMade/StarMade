/*   1:    */package org.apache.commons.lang3.reflect;
/*   2:    */
/*   3:    */import java.lang.reflect.AccessibleObject;
/*   4:    */import java.lang.reflect.Member;
/*   5:    */import java.lang.reflect.Modifier;
/*   6:    */import org.apache.commons.lang3.ClassUtils;
/*   7:    */
/*  36:    */abstract class MemberUtils
/*  37:    */{
/*  38:    */  private static final int ACCESS_TEST = 7;
/*  39: 39 */  private static final Class<?>[] ORDERED_PRIMITIVE_TYPES = { Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE };
/*  40:    */  
/*  53:    */  static void setAccessibleWorkaround(AccessibleObject o)
/*  54:    */  {
/*  55: 55 */    if ((o == null) || (o.isAccessible())) {
/*  56: 56 */      return;
/*  57:    */    }
/*  58: 58 */    Member m = (Member)o;
/*  59: 59 */    if ((Modifier.isPublic(m.getModifiers())) && (isPackageAccess(m.getDeclaringClass().getModifiers()))) {
/*  60:    */      try
/*  61:    */      {
/*  62: 62 */        o.setAccessible(true);
/*  63:    */      }
/*  64:    */      catch (SecurityException e) {}
/*  65:    */    }
/*  66:    */  }
/*  67:    */  
/*  73:    */  static boolean isPackageAccess(int modifiers)
/*  74:    */  {
/*  75: 75 */    return (modifiers & 0x7) == 0;
/*  76:    */  }
/*  77:    */  
/*  82:    */  static boolean isAccessible(Member m)
/*  83:    */  {
/*  84: 84 */    return (m != null) && (Modifier.isPublic(m.getModifiers())) && (!m.isSynthetic());
/*  85:    */  }
/*  86:    */  
/*  98:    */  static int compareParameterTypes(Class<?>[] left, Class<?>[] right, Class<?>[] actual)
/*  99:    */  {
/* 100:100 */    float leftCost = getTotalTransformationCost(actual, left);
/* 101:101 */    float rightCost = getTotalTransformationCost(actual, right);
/* 102:102 */    return rightCost < leftCost ? 1 : leftCost < rightCost ? -1 : 0;
/* 103:    */  }
/* 104:    */  
/* 111:    */  private static float getTotalTransformationCost(Class<?>[] srcArgs, Class<?>[] destArgs)
/* 112:    */  {
/* 113:113 */    float totalCost = 0.0F;
/* 114:114 */    for (int i = 0; i < srcArgs.length; i++)
/* 115:    */    {
/* 116:116 */      Class<?> srcClass = srcArgs[i];
/* 117:117 */      Class<?> destClass = destArgs[i];
/* 118:118 */      totalCost += getObjectTransformationCost(srcClass, destClass);
/* 119:    */    }
/* 120:120 */    return totalCost;
/* 121:    */  }
/* 122:    */  
/* 130:    */  private static float getObjectTransformationCost(Class<?> srcClass, Class<?> destClass)
/* 131:    */  {
/* 132:132 */    if (destClass.isPrimitive()) {
/* 133:133 */      return getPrimitivePromotionCost(srcClass, destClass);
/* 134:    */    }
/* 135:135 */    float cost = 0.0F;
/* 136:136 */    while ((srcClass != null) && (!destClass.equals(srcClass))) {
/* 137:137 */      if ((destClass.isInterface()) && (ClassUtils.isAssignable(srcClass, destClass)))
/* 138:    */      {
/* 143:143 */        cost += 0.25F;
/* 144:144 */        break;
/* 145:    */      }
/* 146:146 */      cost += 1.0F;
/* 147:147 */      srcClass = srcClass.getSuperclass();
/* 148:    */    }
/* 149:    */    
/* 153:153 */    if (srcClass == null) {
/* 154:154 */      cost += 1.5F;
/* 155:    */    }
/* 156:156 */    return cost;
/* 157:    */  }
/* 158:    */  
/* 165:    */  private static float getPrimitivePromotionCost(Class<?> srcClass, Class<?> destClass)
/* 166:    */  {
/* 167:167 */    float cost = 0.0F;
/* 168:168 */    Class<?> cls = srcClass;
/* 169:169 */    if (!cls.isPrimitive())
/* 170:    */    {
/* 171:171 */      cost += 0.1F;
/* 172:172 */      cls = ClassUtils.wrapperToPrimitive(cls);
/* 173:    */    }
/* 174:174 */    for (int i = 0; (cls != destClass) && (i < ORDERED_PRIMITIVE_TYPES.length); i++) {
/* 175:175 */      if (cls == ORDERED_PRIMITIVE_TYPES[i]) {
/* 176:176 */        cost += 0.1F;
/* 177:177 */        if (i < ORDERED_PRIMITIVE_TYPES.length - 1) {
/* 178:178 */          cls = ORDERED_PRIMITIVE_TYPES[(i + 1)];
/* 179:    */        }
/* 180:    */      }
/* 181:    */    }
/* 182:182 */    return cost;
/* 183:    */  }
/* 184:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.reflect.MemberUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */