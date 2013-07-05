/*     */ package org.apache.commons.lang3.reflect;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import org.apache.commons.lang3.ClassUtils;
/*     */ 
/*     */ public class FieldUtils
/*     */ {
/*     */   public static Field getField(Class<?> cls, String fieldName)
/*     */   {
/*  57 */     Field field = getField(cls, fieldName, false);
/*  58 */     MemberUtils.setAccessibleWorkaround(field);
/*  59 */     return field;
/*     */   }
/*     */ 
/*     */   public static Field getField(Class<?> cls, String fieldName, boolean forceAccess)
/*     */   {
/*  75 */     if (cls == null) {
/*  76 */       throw new IllegalArgumentException("The class must not be null");
/*     */     }
/*  78 */     if (fieldName == null) {
/*  79 */       throw new IllegalArgumentException("The field name must not be null");
/*     */     }
/*     */ 
/*  95 */     for (Class acls = cls; acls != null; acls = acls.getSuperclass()) {
/*     */       try {
/*  97 */         Field field = acls.getDeclaredField(fieldName);
/*     */ 
/* 100 */         if (!Modifier.isPublic(field.getModifiers())) {
/* 101 */           if (forceAccess)
/* 102 */             field.setAccessible(true);
/*     */           else {
/* 104 */             continue;
/*     */           }
/*     */         }
/* 107 */         return field;
/*     */       }
/*     */       catch (NoSuchFieldException ex)
/*     */       {
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 115 */     Field match = null;
/* 116 */     for (Class class1 : ClassUtils.getAllInterfaces(cls))
/*     */       try {
/* 118 */         Field test = class1.getField(fieldName);
/* 119 */         if (match != null) {
/* 120 */           throw new IllegalArgumentException("Reference to field " + fieldName + " is ambiguous relative to " + cls + "; a matching field exists on two or more implemented interfaces.");
/*     */         }
/*     */ 
/* 123 */         match = test;
/*     */       }
/*     */       catch (NoSuchFieldException ex)
/*     */       {
/*     */       }
/* 128 */     return match;
/*     */   }
/*     */ 
/*     */   public static Field getDeclaredField(Class<?> cls, String fieldName)
/*     */   {
/* 141 */     return getDeclaredField(cls, fieldName, false);
/*     */   }
/*     */ 
/*     */   public static Field getDeclaredField(Class<?> cls, String fieldName, boolean forceAccess)
/*     */   {
/* 156 */     if (cls == null) {
/* 157 */       throw new IllegalArgumentException("The class must not be null");
/*     */     }
/* 159 */     if (fieldName == null) {
/* 160 */       throw new IllegalArgumentException("The field name must not be null");
/*     */     }
/*     */     try
/*     */     {
/* 164 */       Field field = cls.getDeclaredField(fieldName);
/* 165 */       if (!MemberUtils.isAccessible(field)) {
/* 166 */         if (forceAccess)
/* 167 */           field.setAccessible(true);
/*     */         else {
/* 169 */           return null;
/*     */         }
/*     */       }
/* 172 */       return field;
/*     */     }
/*     */     catch (NoSuchFieldException e) {
/*     */     }
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */   public static Object readStaticField(Field field)
/*     */     throws IllegalAccessException
/*     */   {
/* 187 */     return readStaticField(field, false);
/*     */   }
/*     */ 
/*     */   public static Object readStaticField(Field field, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 200 */     if (field == null) {
/* 201 */       throw new IllegalArgumentException("The field must not be null");
/*     */     }
/* 203 */     if (!Modifier.isStatic(field.getModifiers())) {
/* 204 */       throw new IllegalArgumentException("The field '" + field.getName() + "' is not static");
/*     */     }
/* 206 */     return readField(field, (Object)null, forceAccess);
/*     */   }
/*     */ 
/*     */   public static Object readStaticField(Class<?> cls, String fieldName)
/*     */     throws IllegalAccessException
/*     */   {
/* 218 */     return readStaticField(cls, fieldName, false);
/*     */   }
/*     */ 
/*     */   public static Object readStaticField(Class<?> cls, String fieldName, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 234 */     Field field = getField(cls, fieldName, forceAccess);
/* 235 */     if (field == null) {
/* 236 */       throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls);
/*     */     }
/*     */ 
/* 239 */     return readStaticField(field, false);
/*     */   }
/*     */ 
/*     */   public static Object readDeclaredStaticField(Class<?> cls, String fieldName)
/*     */     throws IllegalAccessException
/*     */   {
/* 253 */     return readDeclaredStaticField(cls, fieldName, false);
/*     */   }
/*     */ 
/*     */   public static Object readDeclaredStaticField(Class<?> cls, String fieldName, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 271 */     Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 272 */     if (field == null) {
/* 273 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/*     */     }
/*     */ 
/* 276 */     return readStaticField(field, false);
/*     */   }
/*     */ 
/*     */   public static Object readField(Field field, Object target)
/*     */     throws IllegalAccessException
/*     */   {
/* 288 */     return readField(field, target, false);
/*     */   }
/*     */ 
/*     */   public static Object readField(Field field, Object target, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 302 */     if (field == null) {
/* 303 */       throw new IllegalArgumentException("The field must not be null");
/*     */     }
/* 305 */     if ((forceAccess) && (!field.isAccessible()))
/* 306 */       field.setAccessible(true);
/*     */     else {
/* 308 */       MemberUtils.setAccessibleWorkaround(field);
/*     */     }
/* 310 */     return field.get(target);
/*     */   }
/*     */ 
/*     */   public static Object readField(Object target, String fieldName)
/*     */     throws IllegalAccessException
/*     */   {
/* 322 */     return readField(target, fieldName, false);
/*     */   }
/*     */ 
/*     */   public static Object readField(Object target, String fieldName, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 337 */     if (target == null) {
/* 338 */       throw new IllegalArgumentException("target object must not be null");
/*     */     }
/* 340 */     Class cls = target.getClass();
/* 341 */     Field field = getField(cls, fieldName, forceAccess);
/* 342 */     if (field == null) {
/* 343 */       throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls);
/*     */     }
/*     */ 
/* 346 */     return readField(field, target);
/*     */   }
/*     */ 
/*     */   public static Object readDeclaredField(Object target, String fieldName)
/*     */     throws IllegalAccessException
/*     */   {
/* 358 */     return readDeclaredField(target, fieldName, false);
/*     */   }
/*     */ 
/*     */   public static Object readDeclaredField(Object target, String fieldName, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 376 */     if (target == null) {
/* 377 */       throw new IllegalArgumentException("target object must not be null");
/*     */     }
/* 379 */     Class cls = target.getClass();
/* 380 */     Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 381 */     if (field == null) {
/* 382 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/*     */     }
/*     */ 
/* 385 */     return readField(field, target);
/*     */   }
/*     */ 
/*     */   public static void writeStaticField(Field field, Object value)
/*     */     throws IllegalAccessException
/*     */   {
/* 396 */     writeStaticField(field, value, false);
/*     */   }
/*     */ 
/*     */   public static void writeStaticField(Field field, Object value, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 410 */     if (field == null) {
/* 411 */       throw new IllegalArgumentException("The field must not be null");
/*     */     }
/* 413 */     if (!Modifier.isStatic(field.getModifiers())) {
/* 414 */       throw new IllegalArgumentException("The field '" + field.getName() + "' is not static");
/*     */     }
/* 416 */     writeField(field, (Object)null, value, forceAccess);
/*     */   }
/*     */ 
/*     */   public static void writeStaticField(Class<?> cls, String fieldName, Object value)
/*     */     throws IllegalAccessException
/*     */   {
/* 428 */     writeStaticField(cls, fieldName, value, false);
/*     */   }
/*     */ 
/*     */   public static void writeStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 444 */     Field field = getField(cls, fieldName, forceAccess);
/* 445 */     if (field == null) {
/* 446 */       throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls);
/*     */     }
/*     */ 
/* 449 */     writeStaticField(field, value);
/*     */   }
/*     */ 
/*     */   public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value)
/*     */     throws IllegalAccessException
/*     */   {
/* 462 */     writeDeclaredStaticField(cls, fieldName, value, false);
/*     */   }
/*     */ 
/*     */   public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 478 */     Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 479 */     if (field == null) {
/* 480 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/*     */     }
/*     */ 
/* 483 */     writeField(field, (Object)null, value);
/*     */   }
/*     */ 
/*     */   public static void writeField(Field field, Object target, Object value)
/*     */     throws IllegalAccessException
/*     */   {
/* 495 */     writeField(field, target, value, false);
/*     */   }
/*     */ 
/*     */   public static void writeField(Field field, Object target, Object value, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 511 */     if (field == null) {
/* 512 */       throw new IllegalArgumentException("The field must not be null");
/*     */     }
/* 514 */     if ((forceAccess) && (!field.isAccessible()))
/* 515 */       field.setAccessible(true);
/*     */     else {
/* 517 */       MemberUtils.setAccessibleWorkaround(field);
/*     */     }
/* 519 */     field.set(target, value);
/*     */   }
/*     */ 
/*     */   public static void writeField(Object target, String fieldName, Object value)
/*     */     throws IllegalAccessException
/*     */   {
/* 531 */     writeField(target, fieldName, value, false);
/*     */   }
/*     */ 
/*     */   public static void writeField(Object target, String fieldName, Object value, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 547 */     if (target == null) {
/* 548 */       throw new IllegalArgumentException("target object must not be null");
/*     */     }
/* 550 */     Class cls = target.getClass();
/* 551 */     Field field = getField(cls, fieldName, forceAccess);
/* 552 */     if (field == null) {
/* 553 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/*     */     }
/*     */ 
/* 556 */     writeField(field, target, value);
/*     */   }
/*     */ 
/*     */   public static void writeDeclaredField(Object target, String fieldName, Object value)
/*     */     throws IllegalAccessException
/*     */   {
/* 568 */     writeDeclaredField(target, fieldName, value, false);
/*     */   }
/*     */ 
/*     */   public static void writeDeclaredField(Object target, String fieldName, Object value, boolean forceAccess)
/*     */     throws IllegalAccessException
/*     */   {
/* 584 */     if (target == null) {
/* 585 */       throw new IllegalArgumentException("target object must not be null");
/*     */     }
/* 587 */     Class cls = target.getClass();
/* 588 */     Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 589 */     if (field == null) {
/* 590 */       throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/*     */     }
/*     */ 
/* 593 */     writeField(field, target, value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.reflect.FieldUtils
 * JD-Core Version:    0.6.2
 */