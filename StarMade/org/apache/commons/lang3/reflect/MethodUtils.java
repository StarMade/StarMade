/*     */ package org.apache.commons.lang3.reflect;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.ClassUtils;
/*     */ 
/*     */ public class MethodUtils
/*     */ {
/*     */   public static Object invokeMethod(Object object, String methodName, Object[] args)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/*     */   {
/*  86 */     if (args == null) {
/*  87 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/*  89 */     int arguments = args.length;
/*  90 */     Class[] parameterTypes = new Class[arguments];
/*  91 */     for (int i = 0; i < arguments; i++) {
/*  92 */       parameterTypes[i] = args[i].getClass();
/*     */     }
/*  94 */     return invokeMethod(object, methodName, args, parameterTypes);
/*     */   }
/*     */ 
/*     */   public static Object invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 120 */     if (parameterTypes == null) {
/* 121 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/*     */     }
/* 123 */     if (args == null) {
/* 124 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 126 */     Method method = getMatchingAccessibleMethod(object.getClass(), methodName, parameterTypes);
/*     */ 
/* 128 */     if (method == null) {
/* 129 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
/*     */     }
/*     */ 
/* 133 */     return method.invoke(object, args);
/*     */   }
/*     */ 
/*     */   public static Object invokeExactMethod(Object object, String methodName, Object[] args)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 157 */     if (args == null) {
/* 158 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 160 */     int arguments = args.length;
/* 161 */     Class[] parameterTypes = new Class[arguments];
/* 162 */     for (int i = 0; i < arguments; i++) {
/* 163 */       parameterTypes[i] = args[i].getClass();
/*     */     }
/* 165 */     return invokeExactMethod(object, methodName, args, parameterTypes);
/*     */   }
/*     */ 
/*     */   public static Object invokeExactMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 191 */     if (args == null) {
/* 192 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 194 */     if (parameterTypes == null) {
/* 195 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/*     */     }
/* 197 */     Method method = getAccessibleMethod(object.getClass(), methodName, parameterTypes);
/*     */ 
/* 199 */     if (method == null) {
/* 200 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
/*     */     }
/*     */ 
/* 204 */     return method.invoke(object, args);
/*     */   }
/*     */ 
/*     */   public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 230 */     if (args == null) {
/* 231 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 233 */     if (parameterTypes == null) {
/* 234 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/*     */     }
/* 236 */     Method method = getAccessibleMethod(cls, methodName, parameterTypes);
/* 237 */     if (method == null) {
/* 238 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
/*     */     }
/*     */ 
/* 241 */     return method.invoke(null, args);
/*     */   }
/*     */ 
/*     */   public static Object invokeStaticMethod(Class<?> cls, String methodName, Object[] args)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 271 */     if (args == null) {
/* 272 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 274 */     int arguments = args.length;
/* 275 */     Class[] parameterTypes = new Class[arguments];
/* 276 */     for (int i = 0; i < arguments; i++) {
/* 277 */       parameterTypes[i] = args[i].getClass();
/*     */     }
/* 279 */     return invokeStaticMethod(cls, methodName, args, parameterTypes);
/*     */   }
/*     */ 
/*     */   public static Object invokeStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 308 */     if (parameterTypes == null) {
/* 309 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/*     */     }
/* 311 */     if (args == null) {
/* 312 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 314 */     Method method = getMatchingAccessibleMethod(cls, methodName, parameterTypes);
/*     */ 
/* 316 */     if (method == null) {
/* 317 */       throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
/*     */     }
/*     */ 
/* 320 */     return method.invoke(null, args);
/*     */   }
/*     */ 
/*     */   public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object[] args)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 344 */     if (args == null) {
/* 345 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 347 */     int arguments = args.length;
/* 348 */     Class[] parameterTypes = new Class[arguments];
/* 349 */     for (int i = 0; i < arguments; i++) {
/* 350 */       parameterTypes[i] = args[i].getClass();
/*     */     }
/* 352 */     return invokeExactStaticMethod(cls, methodName, args, parameterTypes);
/*     */   }
/*     */ 
/*     */   public static Method getAccessibleMethod(Class<?> cls, String methodName, Class<?>[] parameterTypes)
/*     */   {
/*     */     try
/*     */     {
/* 370 */       return getAccessibleMethod(cls.getMethod(methodName, parameterTypes));
/*     */     } catch (NoSuchMethodException e) {
/*     */     }
/* 373 */     return null;
/*     */   }
/*     */ 
/*     */   public static Method getAccessibleMethod(Method method)
/*     */   {
/* 386 */     if (!MemberUtils.isAccessible(method)) {
/* 387 */       return null;
/*     */     }
/*     */ 
/* 390 */     Class cls = method.getDeclaringClass();
/* 391 */     if (Modifier.isPublic(cls.getModifiers())) {
/* 392 */       return method;
/*     */     }
/* 394 */     String methodName = method.getName();
/* 395 */     Class[] parameterTypes = method.getParameterTypes();
/*     */ 
/* 398 */     method = getAccessibleMethodFromInterfaceNest(cls, methodName, parameterTypes);
/*     */ 
/* 402 */     if (method == null) {
/* 403 */       method = getAccessibleMethodFromSuperclass(cls, methodName, parameterTypes);
/*     */     }
/*     */ 
/* 406 */     return method;
/*     */   }
/*     */ 
/*     */   private static Method getAccessibleMethodFromSuperclass(Class<?> cls, String methodName, Class<?>[] parameterTypes)
/*     */   {
/* 421 */     Class parentClass = cls.getSuperclass();
/* 422 */     while (parentClass != null) {
/* 423 */       if (Modifier.isPublic(parentClass.getModifiers())) {
/*     */         try {
/* 425 */           return parentClass.getMethod(methodName, parameterTypes);
/*     */         } catch (NoSuchMethodException e) {
/* 427 */           return null;
/*     */         }
/*     */       }
/* 430 */       parentClass = parentClass.getSuperclass();
/*     */     }
/* 432 */     return null;
/*     */   }
/*     */ 
/*     */   private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, String methodName, Class<?>[] parameterTypes)
/*     */   {
/* 452 */     Method method = null;
/*     */ 
/* 455 */     for (; cls != null; cls = cls.getSuperclass())
/*     */     {
/* 458 */       Class[] interfaces = cls.getInterfaces();
/* 459 */       for (int i = 0; i < interfaces.length; i++)
/*     */       {
/* 461 */         if (Modifier.isPublic(interfaces[i].getModifiers()))
/*     */         {
/*     */           try
/*     */           {
/* 466 */             method = interfaces[i].getDeclaredMethod(methodName, parameterTypes);
/*     */           }
/*     */           catch (NoSuchMethodException e)
/*     */           {
/*     */           }
/*     */ 
/* 474 */           if (method != null)
/*     */           {
/*     */             break;
/*     */           }
/* 478 */           method = getAccessibleMethodFromInterfaceNest(interfaces[i], methodName, parameterTypes);
/*     */ 
/* 480 */           if (method != null)
/*     */             break;
/*     */         }
/*     */       }
/*     */     }
/* 485 */     return method;
/*     */   }
/*     */ 
/*     */   public static Method getMatchingAccessibleMethod(Class<?> cls, String methodName, Class<?>[] parameterTypes)
/*     */   {
/*     */     try
/*     */     {
/* 511 */       Method method = cls.getMethod(methodName, parameterTypes);
/* 512 */       MemberUtils.setAccessibleWorkaround(method);
/* 513 */       return method;
/*     */     }
/*     */     catch (NoSuchMethodException e)
/*     */     {
/* 517 */       Method bestMatch = null;
/* 518 */       Method[] methods = cls.getMethods();
/* 519 */       for (Method method : methods)
/*     */       {
/* 521 */         if ((method.getName().equals(methodName)) && (ClassUtils.isAssignable(parameterTypes, method.getParameterTypes(), true)))
/*     */         {
/* 523 */           Method accessibleMethod = getAccessibleMethod(method);
/* 524 */           if ((accessibleMethod != null) && ((bestMatch == null) || (MemberUtils.compareParameterTypes(accessibleMethod.getParameterTypes(), bestMatch.getParameterTypes(), parameterTypes) < 0)))
/*     */           {
/* 528 */             bestMatch = accessibleMethod;
/*     */           }
/*     */         }
/*     */       }
/* 532 */       if (bestMatch != null) {
/* 533 */         MemberUtils.setAccessibleWorkaround(bestMatch);
/*     */       }
/* 535 */       return bestMatch;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.reflect.MethodUtils
 * JD-Core Version:    0.6.2
 */