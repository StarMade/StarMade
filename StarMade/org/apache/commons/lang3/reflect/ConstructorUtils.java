/*   1:    */package org.apache.commons.lang3.reflect;
/*   2:    */
/*   3:    */import java.lang.reflect.Constructor;
/*   4:    */import java.lang.reflect.InvocationTargetException;
/*   5:    */import java.lang.reflect.Modifier;
/*   6:    */import org.apache.commons.lang3.ArrayUtils;
/*   7:    */import org.apache.commons.lang3.ClassUtils;
/*   8:    */
/*  76:    */public class ConstructorUtils
/*  77:    */{
/*  78:    */  public static <T> T invokeConstructor(Class<T> cls, Object... args)
/*  79:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
/*  80:    */  {
/*  81: 81 */    if (args == null) {
/*  82: 82 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*  83:    */    }
/*  84: 84 */    Class<?>[] parameterTypes = new Class[args.length];
/*  85: 85 */    for (int i = 0; i < args.length; i++) {
/*  86: 86 */      parameterTypes[i] = args[i].getClass();
/*  87:    */    }
/*  88: 88 */    return invokeConstructor(cls, args, parameterTypes);
/*  89:    */  }
/*  90:    */  
/* 110:    */  public static <T> T invokeConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes)
/* 111:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
/* 112:    */  {
/* 113:113 */    if (parameterTypes == null) {
/* 114:114 */      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/* 115:    */    }
/* 116:116 */    if (args == null) {
/* 117:117 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 118:    */    }
/* 119:119 */    Constructor<T> ctor = getMatchingAccessibleConstructor(cls, parameterTypes);
/* 120:120 */    if (ctor == null) {
/* 121:121 */      throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
/* 122:    */    }
/* 123:    */    
/* 124:124 */    return ctor.newInstance(args);
/* 125:    */  }
/* 126:    */  
/* 145:    */  public static <T> T invokeExactConstructor(Class<T> cls, Object... args)
/* 146:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
/* 147:    */  {
/* 148:148 */    if (args == null) {
/* 149:149 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 150:    */    }
/* 151:151 */    int arguments = args.length;
/* 152:152 */    Class<?>[] parameterTypes = new Class[arguments];
/* 153:153 */    for (int i = 0; i < arguments; i++) {
/* 154:154 */      parameterTypes[i] = args[i].getClass();
/* 155:    */    }
/* 156:156 */    return invokeExactConstructor(cls, args, parameterTypes);
/* 157:    */  }
/* 158:    */  
/* 178:    */  public static <T> T invokeExactConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes)
/* 179:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
/* 180:    */  {
/* 181:181 */    if (args == null) {
/* 182:182 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 183:    */    }
/* 184:184 */    if (parameterTypes == null) {
/* 185:185 */      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/* 186:    */    }
/* 187:187 */    Constructor<T> ctor = getAccessibleConstructor(cls, parameterTypes);
/* 188:188 */    if (ctor == null) {
/* 189:189 */      throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
/* 190:    */    }
/* 191:    */    
/* 192:192 */    return ctor.newInstance(args);
/* 193:    */  }
/* 194:    */  
/* 208:    */  public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes)
/* 209:    */  {
/* 210:    */    try
/* 211:    */    {
/* 212:212 */      return getAccessibleConstructor(cls.getConstructor(parameterTypes));
/* 213:    */    } catch (NoSuchMethodException e) {}
/* 214:214 */    return null;
/* 215:    */  }
/* 216:    */  
/* 227:    */  public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> ctor)
/* 228:    */  {
/* 229:229 */    return (MemberUtils.isAccessible(ctor)) && (Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) ? ctor : null;
/* 230:    */  }
/* 231:    */  
/* 251:    */  public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes)
/* 252:    */  {
/* 253:    */    try
/* 254:    */    {
/* 255:255 */      Constructor<T> ctor = cls.getConstructor(parameterTypes);
/* 256:256 */      MemberUtils.setAccessibleWorkaround(ctor);
/* 257:257 */      return ctor;
/* 258:    */    }
/* 259:    */    catch (NoSuchMethodException e) {
/* 260:260 */      Constructor<T> result = null;
/* 261:    */      
/* 265:265 */      Constructor<?>[] ctors = cls.getConstructors();
/* 266:    */      
/* 268:268 */      for (Constructor<?> ctor : ctors)
/* 269:    */      {
/* 270:270 */        if (ClassUtils.isAssignable(parameterTypes, ctor.getParameterTypes(), true))
/* 271:    */        {
/* 272:272 */          ctor = getAccessibleConstructor(ctor);
/* 273:273 */          if (ctor != null) {
/* 274:274 */            MemberUtils.setAccessibleWorkaround(ctor);
/* 275:275 */            if ((result == null) || (MemberUtils.compareParameterTypes(ctor.getParameterTypes(), result.getParameterTypes(), parameterTypes) < 0))
/* 276:    */            {
/* 280:280 */              Constructor<T> constructor = ctor;
/* 281:281 */              result = constructor;
/* 282:    */            }
/* 283:    */          }
/* 284:    */        }
/* 285:    */      }
/* 286:286 */      return result;
/* 287:    */    }
/* 288:    */  }
/* 289:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.reflect.ConstructorUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */