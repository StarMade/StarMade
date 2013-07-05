/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.lang3.exception.CloneFailedException;
/*     */ import org.apache.commons.lang3.mutable.MutableInt;
/*     */ 
/*     */ public class ObjectUtils
/*     */ {
/*  61 */   public static final Null NULL = new Null();
/*     */ 
/*     */   public static <T> T defaultIfNull(T object, T defaultValue)
/*     */   {
/*  94 */     return object != null ? object : defaultValue;
/*     */   }
/*     */ 
/*     */   public static <T> T firstNonNull(T[] values)
/*     */   {
/* 120 */     if (values != null) {
/* 121 */       for (Object val : values) {
/* 122 */         if (val != null) {
/* 123 */           return val;
/*     */         }
/*     */       }
/*     */     }
/* 127 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean equals(Object object1, Object object2)
/*     */   {
/* 152 */     if (object1 == object2) {
/* 153 */       return true;
/*     */     }
/* 155 */     if ((object1 == null) || (object2 == null)) {
/* 156 */       return false;
/*     */     }
/* 158 */     return object1.equals(object2);
/*     */   }
/*     */ 
/*     */   public static boolean notEqual(Object object1, Object object2)
/*     */   {
/* 181 */     return !equals(object1, object2);
/*     */   }
/*     */ 
/*     */   public static int hashCode(Object obj)
/*     */   {
/* 199 */     return obj == null ? 0 : obj.hashCode();
/*     */   }
/*     */ 
/*     */   public static int hashCodeMulti(Object[] objects)
/*     */   {
/* 223 */     int hash = 1;
/* 224 */     if (objects != null) {
/* 225 */       for (Object object : objects) {
/* 226 */         hash = hash * 31 + hashCode(object);
/*     */       }
/*     */     }
/* 229 */     return hash;
/*     */   }
/*     */ 
/*     */   public static String identityToString(Object object)
/*     */   {
/* 251 */     if (object == null) {
/* 252 */       return null;
/*     */     }
/* 254 */     StringBuffer buffer = new StringBuffer();
/* 255 */     identityToString(buffer, object);
/* 256 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   public static void identityToString(StringBuffer buffer, Object object)
/*     */   {
/* 275 */     if (object == null) {
/* 276 */       throw new NullPointerException("Cannot get the toString of a null identity");
/*     */     }
/* 278 */     buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
/*     */   }
/*     */ 
/*     */   public static String toString(Object obj)
/*     */   {
/* 303 */     return obj == null ? "" : obj.toString();
/*     */   }
/*     */ 
/*     */   public static String toString(Object obj, String nullStr)
/*     */   {
/* 326 */     return obj == null ? nullStr : obj.toString();
/*     */   }
/*     */ 
/*     */   public static <T extends Comparable<? super T>> T min(T[] values)
/*     */   {
/* 345 */     Comparable result = null;
/* 346 */     if (values != null) {
/* 347 */       for (Comparable value : values) {
/* 348 */         if (compare(value, result, true) < 0) {
/* 349 */           result = value;
/*     */         }
/*     */       }
/*     */     }
/* 353 */     return result;
/*     */   }
/*     */ 
/*     */   public static <T extends Comparable<? super T>> T max(T[] values)
/*     */   {
/* 370 */     Comparable result = null;
/* 371 */     if (values != null) {
/* 372 */       for (Comparable value : values) {
/* 373 */         if (compare(value, result, false) > 0) {
/* 374 */           result = value;
/*     */         }
/*     */       }
/*     */     }
/* 378 */     return result;
/*     */   }
/*     */ 
/*     */   public static <T extends Comparable<? super T>> int compare(T c1, T c2)
/*     */   {
/* 392 */     return compare(c1, c2, false);
/*     */   }
/*     */ 
/*     */   public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean nullGreater)
/*     */   {
/* 409 */     if (c1 == c2)
/* 410 */       return 0;
/* 411 */     if (c1 == null)
/* 412 */       return nullGreater ? 1 : -1;
/* 413 */     if (c2 == null) {
/* 414 */       return nullGreater ? -1 : 1;
/*     */     }
/* 416 */     return c1.compareTo(c2);
/*     */   }
/*     */ 
/*     */   public static <T extends Comparable<? super T>> T median(T[] items)
/*     */   {
/* 430 */     Validate.notEmpty(items);
/* 431 */     Validate.noNullElements(items);
/* 432 */     TreeSet sort = new TreeSet();
/* 433 */     Collections.addAll(sort, items);
/*     */ 
/* 435 */     Comparable result = (Comparable)sort.toArray()[((sort.size() - 1) / 2)];
/* 436 */     return result;
/*     */   }
/*     */ 
/*     */   public static <T> T median(Comparator<T> comparator, T[] items)
/*     */   {
/* 451 */     Validate.notEmpty(items, "null/empty items", new Object[0]);
/* 452 */     Validate.noNullElements(items);
/* 453 */     Validate.notNull(comparator, "null comparator", new Object[0]);
/* 454 */     TreeSet sort = new TreeSet(comparator);
/* 455 */     Collections.addAll(sort, items);
/*     */ 
/* 457 */     Object result = sort.toArray()[((sort.size() - 1) / 2)];
/* 458 */     return result;
/*     */   }
/*     */ 
/*     */   public static <T> T mode(T[] items)
/*     */   {
/* 472 */     if (ArrayUtils.isNotEmpty(items)) {
/* 473 */       HashMap occurrences = new HashMap(items.length);
/* 474 */       for (Object t : items) {
/* 475 */         MutableInt count = (MutableInt)occurrences.get(t);
/* 476 */         if (count == null)
/* 477 */           occurrences.put(t, new MutableInt(1));
/*     */         else {
/* 479 */           count.increment();
/*     */         }
/*     */       }
/* 482 */       Object result = null;
/* 483 */       int max = 0;
/* 484 */       for (Map.Entry e : occurrences.entrySet()) {
/* 485 */         int cmp = ((MutableInt)e.getValue()).intValue();
/* 486 */         if (cmp == max) {
/* 487 */           result = null;
/* 488 */         } else if (cmp > max) {
/* 489 */           max = cmp;
/* 490 */           result = e.getKey();
/*     */         }
/*     */       }
/* 493 */       return result;
/*     */     }
/* 495 */     return null;
/*     */   }
/*     */ 
/*     */   public static <T> T clone(T obj)
/*     */   {
/* 510 */     if ((obj instanceof Cloneable))
/*     */     {
/*     */       Object result;
/* 512 */       if (obj.getClass().isArray()) {
/* 513 */         Class componentType = obj.getClass().getComponentType();
/*     */         Object result;
/* 514 */         if (!componentType.isPrimitive()) {
/* 515 */           result = ((Object[])obj).clone();
/*     */         } else {
/* 517 */           int length = Array.getLength(obj);
/* 518 */           Object result = Array.newInstance(componentType, length);
/* 519 */           while (length-- > 0)
/* 520 */             Array.set(result, length, Array.get(obj, length));
/*     */         }
/*     */       }
/*     */       else {
/*     */         try {
/* 525 */           Method clone = obj.getClass().getMethod("clone", new Class[0]);
/* 526 */           result = clone.invoke(obj, new Object[0]);
/*     */         } catch (NoSuchMethodException e) {
/* 528 */           throw new CloneFailedException("Cloneable type " + obj.getClass().getName() + " has no clone method", e);
/*     */         }
/*     */         catch (IllegalAccessException e)
/*     */         {
/* 532 */           throw new CloneFailedException("Cannot clone Cloneable type " + obj.getClass().getName(), e);
/*     */         }
/*     */         catch (InvocationTargetException e) {
/* 535 */           throw new CloneFailedException("Exception cloning Cloneable type " + obj.getClass().getName(), e.getCause());
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 540 */       Object checked = result;
/* 541 */       return checked;
/*     */     }
/*     */ 
/* 544 */     return null;
/*     */   }
/*     */ 
/*     */   public static <T> T cloneIfPossible(T obj)
/*     */   {
/* 564 */     Object clone = clone(obj);
/* 565 */     return clone == null ? obj : clone;
/*     */   }
/*     */ 
/*     */   public static class Null
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = 7092611880189329093L;
/*     */ 
/*     */     private Object readResolve()
/*     */     {
/* 604 */       return ObjectUtils.NULL;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.ObjectUtils
 * JD-Core Version:    0.6.2
 */