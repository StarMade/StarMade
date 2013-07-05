/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*     */ import org.apache.commons.lang3.builder.ToStringStyle;
/*     */ 
/*     */ public class AnnotationUtils
/*     */ {
/*  51 */   private static final ToStringStyle TO_STRING_STYLE = new ToStringStyle()
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */ 
/*     */     protected String getShortClassName(Class<?> cls)
/*     */     {
/*  73 */       Class annotationType = null;
/*  74 */       for (Class iface : ClassUtils.getAllInterfaces(cls)) {
/*  75 */         if (Annotation.class.isAssignableFrom(iface))
/*     */         {
/*  78 */           Class found = iface;
/*  79 */           annotationType = found;
/*  80 */           break;
/*     */         }
/*     */       }
/*  83 */       return new StringBuilder(annotationType == null ? "" : annotationType.getName()).insert(0, '@').toString();
/*     */     }
/*     */ 
/*     */     protected void appendDetail(StringBuffer buffer, String fieldName, Object value)
/*     */     {
/*  92 */       if ((value instanceof Annotation)) {
/*  93 */         value = AnnotationUtils.toString((Annotation)value);
/*     */       }
/*  95 */       super.appendDetail(buffer, fieldName, value);
/*     */     }
/*  51 */   };
/*     */ 
/*     */   public static boolean equals(Annotation a1, Annotation a2)
/*     */   {
/* 123 */     if (a1 == a2) {
/* 124 */       return true;
/*     */     }
/* 126 */     if ((a1 == null) || (a2 == null)) {
/* 127 */       return false;
/*     */     }
/* 129 */     Class type = a1.annotationType();
/* 130 */     Class type2 = a2.annotationType();
/* 131 */     Validate.notNull(type, "Annotation %s with null annotationType()", new Object[] { a1 });
/* 132 */     Validate.notNull(type2, "Annotation %s with null annotationType()", new Object[] { a2 });
/* 133 */     if (!type.equals(type2))
/* 134 */       return false;
/*     */     try
/*     */     {
/* 137 */       for (Method m : type.getDeclaredMethods())
/* 138 */         if ((m.getParameterTypes().length == 0) && (isValidAnnotationMemberType(m.getReturnType())))
/*     */         {
/* 140 */           Object v1 = m.invoke(a1, new Object[0]);
/* 141 */           Object v2 = m.invoke(a2, new Object[0]);
/* 142 */           if (!memberEquals(m.getReturnType(), v1, v2))
/* 143 */             return false;
/*     */         }
/*     */     }
/*     */     catch (IllegalAccessException ex)
/*     */     {
/* 148 */       return false;
/*     */     } catch (InvocationTargetException ex) {
/* 150 */       return false;
/*     */     }
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */   public static int hashCode(Annotation a)
/*     */   {
/* 168 */     int result = 0;
/* 169 */     Class type = a.annotationType();
/* 170 */     for (Method m : type.getDeclaredMethods()) {
/*     */       try {
/* 172 */         Object value = m.invoke(a, new Object[0]);
/* 173 */         if (value == null) {
/* 174 */           throw new IllegalStateException(String.format("Annotation method %s returned null", new Object[] { m }));
/*     */         }
/*     */ 
/* 177 */         result += hashMember(m.getName(), value);
/*     */       } catch (RuntimeException ex) {
/* 179 */         throw ex;
/*     */       } catch (Exception ex) {
/* 181 */         throw new RuntimeException(ex);
/*     */       }
/*     */     }
/* 184 */     return result;
/*     */   }
/*     */ 
/*     */   public static String toString(Annotation a)
/*     */   {
/* 196 */     ToStringBuilder builder = new ToStringBuilder(a, TO_STRING_STYLE);
/* 197 */     for (Method m : a.annotationType().getDeclaredMethods())
/* 198 */       if (m.getParameterTypes().length <= 0)
/*     */       {
/*     */         try
/*     */         {
/* 202 */           builder.append(m.getName(), m.invoke(a, new Object[0]));
/*     */         } catch (RuntimeException ex) {
/* 204 */           throw ex;
/*     */         } catch (Exception ex) {
/* 206 */           throw new RuntimeException(ex);
/*     */         }
/*     */       }
/* 209 */     return builder.build();
/*     */   }
/*     */ 
/*     */   public static boolean isValidAnnotationMemberType(Class<?> type)
/*     */   {
/* 224 */     if (type == null) {
/* 225 */       return false;
/*     */     }
/* 227 */     if (type.isArray()) {
/* 228 */       type = type.getComponentType();
/*     */     }
/* 230 */     return (type.isPrimitive()) || (type.isEnum()) || (type.isAnnotation()) || (String.class.equals(type)) || (Class.class.equals(type));
/*     */   }
/*     */ 
/*     */   private static int hashMember(String name, Object value)
/*     */   {
/* 243 */     int part1 = name.hashCode() * 127;
/* 244 */     if (value.getClass().isArray()) {
/* 245 */       return part1 ^ arrayMemberHash(value.getClass().getComponentType(), value);
/*     */     }
/* 247 */     if ((value instanceof Annotation)) {
/* 248 */       return part1 ^ hashCode((Annotation)value);
/*     */     }
/* 250 */     return part1 ^ value.hashCode();
/*     */   }
/*     */ 
/*     */   private static boolean memberEquals(Class<?> type, Object o1, Object o2)
/*     */   {
/* 264 */     if (o1 == o2) {
/* 265 */       return true;
/*     */     }
/* 267 */     if ((o1 == null) || (o2 == null)) {
/* 268 */       return false;
/*     */     }
/* 270 */     if (type.isArray()) {
/* 271 */       return arrayMemberEquals(type.getComponentType(), o1, o2);
/*     */     }
/* 273 */     if (type.isAnnotation()) {
/* 274 */       return equals((Annotation)o1, (Annotation)o2);
/*     */     }
/* 276 */     return o1.equals(o2);
/*     */   }
/*     */ 
/*     */   private static boolean arrayMemberEquals(Class<?> componentType, Object o1, Object o2)
/*     */   {
/* 288 */     if (componentType.isAnnotation()) {
/* 289 */       return annotationArrayMemberEquals((Annotation[])o1, (Annotation[])o2);
/*     */     }
/* 291 */     if (componentType.equals(Byte.TYPE)) {
/* 292 */       return Arrays.equals((byte[])o1, (byte[])o2);
/*     */     }
/* 294 */     if (componentType.equals(Short.TYPE)) {
/* 295 */       return Arrays.equals((short[])o1, (short[])o2);
/*     */     }
/* 297 */     if (componentType.equals(Integer.TYPE)) {
/* 298 */       return Arrays.equals((int[])o1, (int[])o2);
/*     */     }
/* 300 */     if (componentType.equals(Character.TYPE)) {
/* 301 */       return Arrays.equals((char[])o1, (char[])o2);
/*     */     }
/* 303 */     if (componentType.equals(Long.TYPE)) {
/* 304 */       return Arrays.equals((long[])o1, (long[])o2);
/*     */     }
/* 306 */     if (componentType.equals(Float.TYPE)) {
/* 307 */       return Arrays.equals((float[])o1, (float[])o2);
/*     */     }
/* 309 */     if (componentType.equals(Double.TYPE)) {
/* 310 */       return Arrays.equals((double[])o1, (double[])o2);
/*     */     }
/* 312 */     if (componentType.equals(Boolean.TYPE)) {
/* 313 */       return Arrays.equals((boolean[])o1, (boolean[])o2);
/*     */     }
/* 315 */     return Arrays.equals((Object[])o1, (Object[])o2);
/*     */   }
/*     */ 
/*     */   private static boolean annotationArrayMemberEquals(Annotation[] a1, Annotation[] a2)
/*     */   {
/* 326 */     if (a1.length != a2.length) {
/* 327 */       return false;
/*     */     }
/* 329 */     for (int i = 0; i < a1.length; i++) {
/* 330 */       if (!equals(a1[i], a2[i])) {
/* 331 */         return false;
/*     */       }
/*     */     }
/* 334 */     return true;
/*     */   }
/*     */ 
/*     */   private static int arrayMemberHash(Class<?> componentType, Object o)
/*     */   {
/* 345 */     if (componentType.equals(Byte.TYPE)) {
/* 346 */       return Arrays.hashCode((byte[])o);
/*     */     }
/* 348 */     if (componentType.equals(Short.TYPE)) {
/* 349 */       return Arrays.hashCode((short[])o);
/*     */     }
/* 351 */     if (componentType.equals(Integer.TYPE)) {
/* 352 */       return Arrays.hashCode((int[])o);
/*     */     }
/* 354 */     if (componentType.equals(Character.TYPE)) {
/* 355 */       return Arrays.hashCode((char[])o);
/*     */     }
/* 357 */     if (componentType.equals(Long.TYPE)) {
/* 358 */       return Arrays.hashCode((long[])o);
/*     */     }
/* 360 */     if (componentType.equals(Float.TYPE)) {
/* 361 */       return Arrays.hashCode((float[])o);
/*     */     }
/* 363 */     if (componentType.equals(Double.TYPE)) {
/* 364 */       return Arrays.hashCode((double[])o);
/*     */     }
/* 366 */     if (componentType.equals(Boolean.TYPE)) {
/* 367 */       return Arrays.hashCode((boolean[])o);
/*     */     }
/* 369 */     return Arrays.hashCode((Object[])o);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.AnnotationUtils
 * JD-Core Version:    0.6.2
 */