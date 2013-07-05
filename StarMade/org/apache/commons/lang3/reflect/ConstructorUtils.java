/*     */ package org.apache.commons.lang3.reflect;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Modifier;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.ClassUtils;
/*     */ 
/*     */ public class ConstructorUtils
/*     */ {
/*     */   public static <T> T invokeConstructor(Class<T> cls, Object[] args)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
/*     */   {
/*  81 */     if (args == null) {
/*  82 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/*  84 */     Class[] parameterTypes = new Class[args.length];
/*  85 */     for (int i = 0; i < args.length; i++) {
/*  86 */       parameterTypes[i] = args[i].getClass();
/*     */     }
/*  88 */     return invokeConstructor(cls, args, parameterTypes);
/*     */   }
/*     */ 
/*     */   public static <T> T invokeConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
/*     */   {
/* 113 */     if (parameterTypes == null) {
/* 114 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/*     */     }
/* 116 */     if (args == null) {
/* 117 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 119 */     Constructor ctor = getMatchingAccessibleConstructor(cls, parameterTypes);
/* 120 */     if (ctor == null) {
/* 121 */       throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
/*     */     }
/*     */ 
/* 124 */     return ctor.newInstance(args);
/*     */   }
/*     */ 
/*     */   public static <T> T invokeExactConstructor(Class<T> cls, Object[] args)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
/*     */   {
/* 148 */     if (args == null) {
/* 149 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 151 */     int arguments = args.length;
/* 152 */     Class[] parameterTypes = new Class[arguments];
/* 153 */     for (int i = 0; i < arguments; i++) {
/* 154 */       parameterTypes[i] = args[i].getClass();
/*     */     }
/* 156 */     return invokeExactConstructor(cls, args, parameterTypes);
/*     */   }
/*     */ 
/*     */   public static <T> T invokeExactConstructor(Class<T> cls, Object[] args, Class<?>[] parameterTypes)
/*     */     throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
/*     */   {
/* 181 */     if (args == null) {
/* 182 */       args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*     */     }
/* 184 */     if (parameterTypes == null) {
/* 185 */       parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/*     */     }
/* 187 */     Constructor ctor = getAccessibleConstructor(cls, parameterTypes);
/* 188 */     if (ctor == null) {
/* 189 */       throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
/*     */     }
/*     */ 
/* 192 */     return ctor.newInstance(args);
/*     */   }
/*     */ 
/*     */   public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?>[] parameterTypes)
/*     */   {
/*     */     try
/*     */     {
/* 212 */       return getAccessibleConstructor(cls.getConstructor(parameterTypes)); } catch (NoSuchMethodException e) {
/*     */     }
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */   public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> ctor)
/*     */   {
/* 229 */     return (MemberUtils.isAccessible(ctor)) && (Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) ? ctor : null;
/*     */   }
/*     */ 
/*     */   public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>[] parameterTypes)
/*     */   {
/*     */     try
/*     */     {
/* 255 */       Constructor ctor = cls.getConstructor(parameterTypes);
/* 256 */       MemberUtils.setAccessibleWorkaround(ctor);
/* 257 */       return ctor;
/*     */     }
/*     */     catch (NoSuchMethodException e) {
/* 260 */       Constructor result = null;
/*     */ 
/* 265 */       Constructor[] ctors = cls.getConstructors();
/*     */ 
/* 268 */       for (Constructor ctor : ctors)
/*     */       {
/* 270 */         if (ClassUtils.isAssignable(parameterTypes, ctor.getParameterTypes(), true))
/*     */         {
/* 272 */           ctor = getAccessibleConstructor(ctor);
/* 273 */           if (ctor != null) {
/* 274 */             MemberUtils.setAccessibleWorkaround(ctor);
/* 275 */             if ((result == null) || (MemberUtils.compareParameterTypes(ctor.getParameterTypes(), result.getParameterTypes(), parameterTypes) < 0))
/*     */             {
/* 280 */               Constructor constructor = ctor;
/* 281 */               result = constructor;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 286 */       return result;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.reflect.ConstructorUtils
 * JD-Core Version:    0.6.2
 */