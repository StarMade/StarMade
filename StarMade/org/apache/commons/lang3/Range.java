/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public final class Range<T>
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final Comparator<T> comparator;
/*     */   private final T minimum;
/*     */   private final T maximum;
/*     */   private transient int hashCode;
/*     */   private transient String toString;
/*     */ 
/*     */   public static <T extends Comparable<T>> Range<T> is(T element)
/*     */   {
/*  76 */     return between(element, element, null);
/*     */   }
/*     */ 
/*     */   public static <T> Range<T> is(T element, Comparator<T> comparator)
/*     */   {
/*  94 */     return between(element, element, comparator);
/*     */   }
/*     */ 
/*     */   public static <T extends Comparable<T>> Range<T> between(T fromInclusive, T toInclusive)
/*     */   {
/* 114 */     return between(fromInclusive, toInclusive, null);
/*     */   }
/*     */ 
/*     */   public static <T> Range<T> between(T fromInclusive, T toInclusive, Comparator<T> comparator)
/*     */   {
/* 135 */     return new Range(fromInclusive, toInclusive, comparator);
/*     */   }
/*     */ 
/*     */   private Range(T element1, T element2, Comparator<T> comparator)
/*     */   {
/* 147 */     if ((element1 == null) || (element2 == null)) {
/* 148 */       throw new IllegalArgumentException("Elements in a range must not be null: element1=" + element1 + ", element2=" + element2);
/*     */     }
/*     */ 
/* 151 */     if (comparator == null) {
/* 152 */       comparator = ComparableComparator.INSTANCE;
/*     */     }
/* 154 */     if (comparator.compare(element1, element2) < 1) {
/* 155 */       this.minimum = element1;
/* 156 */       this.maximum = element2;
/*     */     } else {
/* 158 */       this.minimum = element2;
/* 159 */       this.maximum = element1;
/*     */     }
/* 161 */     this.comparator = comparator;
/*     */   }
/*     */ 
/*     */   public T getMinimum()
/*     */   {
/* 173 */     return this.minimum;
/*     */   }
/*     */ 
/*     */   public T getMaximum()
/*     */   {
/* 182 */     return this.maximum;
/*     */   }
/*     */ 
/*     */   public Comparator<T> getComparator()
/*     */   {
/* 194 */     return this.comparator;
/*     */   }
/*     */ 
/*     */   public boolean isNaturalOrdering()
/*     */   {
/* 206 */     return this.comparator == ComparableComparator.INSTANCE;
/*     */   }
/*     */ 
/*     */   public boolean contains(T element)
/*     */   {
/* 219 */     if (element == null) {
/* 220 */       return false;
/*     */     }
/* 222 */     return (this.comparator.compare(element, this.minimum) > -1) && (this.comparator.compare(element, this.maximum) < 1);
/*     */   }
/*     */ 
/*     */   public boolean isAfter(T element)
/*     */   {
/* 232 */     if (element == null) {
/* 233 */       return false;
/*     */     }
/* 235 */     return this.comparator.compare(element, this.minimum) < 0;
/*     */   }
/*     */ 
/*     */   public boolean isStartedBy(T element)
/*     */   {
/* 245 */     if (element == null) {
/* 246 */       return false;
/*     */     }
/* 248 */     return this.comparator.compare(element, this.minimum) == 0;
/*     */   }
/*     */ 
/*     */   public boolean isEndedBy(T element)
/*     */   {
/* 258 */     if (element == null) {
/* 259 */       return false;
/*     */     }
/* 261 */     return this.comparator.compare(element, this.maximum) == 0;
/*     */   }
/*     */ 
/*     */   public boolean isBefore(T element)
/*     */   {
/* 271 */     if (element == null) {
/* 272 */       return false;
/*     */     }
/* 274 */     return this.comparator.compare(element, this.maximum) > 0;
/*     */   }
/*     */ 
/*     */   public int elementCompareTo(T element)
/*     */   {
/* 288 */     if (element == null)
/*     */     {
/* 290 */       throw new NullPointerException("Element is null");
/*     */     }
/* 292 */     if (isAfter(element))
/* 293 */       return -1;
/* 294 */     if (isBefore(element)) {
/* 295 */       return 1;
/*     */     }
/* 297 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean containsRange(Range<T> otherRange)
/*     */   {
/* 314 */     if (otherRange == null) {
/* 315 */       return false;
/*     */     }
/* 317 */     return (contains(otherRange.minimum)) && (contains(otherRange.maximum));
/*     */   }
/*     */ 
/*     */   public boolean isAfterRange(Range<T> otherRange)
/*     */   {
/* 331 */     if (otherRange == null) {
/* 332 */       return false;
/*     */     }
/* 334 */     return isAfter(otherRange.maximum);
/*     */   }
/*     */ 
/*     */   public boolean isOverlappedBy(Range<T> otherRange)
/*     */   {
/* 350 */     if (otherRange == null) {
/* 351 */       return false;
/*     */     }
/* 353 */     return (otherRange.contains(this.minimum)) || (otherRange.contains(this.maximum)) || (contains(otherRange.minimum));
/*     */   }
/*     */ 
/*     */   public boolean isBeforeRange(Range<T> otherRange)
/*     */   {
/* 368 */     if (otherRange == null) {
/* 369 */       return false;
/*     */     }
/* 371 */     return isBefore(otherRange.minimum);
/*     */   }
/*     */ 
/*     */   public Range<T> intersectionWith(Range<T> other)
/*     */   {
/* 382 */     if (!isOverlappedBy(other)) {
/* 383 */       throw new IllegalArgumentException(String.format("Cannot calculate intersection with non-overlapping range %s", new Object[] { other }));
/*     */     }
/*     */ 
/* 386 */     if (equals(other)) {
/* 387 */       return this;
/*     */     }
/* 389 */     Object min = getComparator().compare(this.minimum, other.minimum) < 0 ? other.minimum : this.minimum;
/* 390 */     Object max = getComparator().compare(this.maximum, other.maximum) < 0 ? this.maximum : other.maximum;
/* 391 */     return between(min, max, getComparator());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 408 */     if (obj == this)
/* 409 */       return true;
/* 410 */     if ((obj == null) || (obj.getClass() != getClass())) {
/* 411 */       return false;
/*     */     }
/*     */ 
/* 414 */     Range range = (Range)obj;
/* 415 */     return (this.minimum.equals(range.minimum)) && (this.maximum.equals(range.maximum));
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 427 */     int result = this.hashCode;
/* 428 */     if (this.hashCode == 0) {
/* 429 */       result = 17;
/* 430 */       result = 37 * result + getClass().hashCode();
/* 431 */       result = 37 * result + this.minimum.hashCode();
/* 432 */       result = 37 * result + this.maximum.hashCode();
/* 433 */       this.hashCode = result;
/*     */     }
/* 435 */     return result;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 447 */     String result = this.toString;
/* 448 */     if (result == null) {
/* 449 */       StringBuilder buf = new StringBuilder(32);
/* 450 */       buf.append('[');
/* 451 */       buf.append(this.minimum);
/* 452 */       buf.append("..");
/* 453 */       buf.append(this.maximum);
/* 454 */       buf.append(']');
/* 455 */       result = buf.toString();
/* 456 */       this.toString = result;
/*     */     }
/* 458 */     return result;
/*     */   }
/*     */ 
/*     */   public String toString(String format)
/*     */   {
/* 474 */     return String.format(format, new Object[] { this.minimum, this.maximum, this.comparator });
/*     */   }
/*     */ 
/*     */   private static enum ComparableComparator
/*     */     implements Comparator
/*     */   {
/* 480 */     INSTANCE;
/*     */ 
/*     */     public int compare(Object obj1, Object obj2)
/*     */     {
/* 489 */       return ((Comparable)obj1).compareTo(obj2);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.Range
 * JD-Core Version:    0.6.2
 */