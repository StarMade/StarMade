/*     */ package com.google.code.tempusfugit.temporal;
/*     */ 
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ public class Duration
/*     */   implements Comparable<Duration>
/*     */ {
/*     */   private final Long value;
/*     */   private final TimeUnit unit;
/*     */ 
/*     */   private Duration(Long value, TimeUnit unit)
/*     */   {
/*  27 */     this.value = value;
/*  28 */     this.unit = unit;
/*     */   }
/*     */ 
/*     */   public static Duration seconds(long seconds) {
/*  32 */     validate(seconds, TimeUnit.SECONDS);
/*  33 */     return new Duration(Long.valueOf(seconds), TimeUnit.SECONDS);
/*     */   }
/*     */ 
/*     */   public static Duration millis(long millis) {
/*  37 */     validate(millis, TimeUnit.MILLISECONDS);
/*  38 */     return new Duration(Long.valueOf(millis), TimeUnit.MILLISECONDS);
/*     */   }
/*     */ 
/*     */   public static Duration minutes(long minutes) {
/*  42 */     long seconds = minutes * 60L;
/*  43 */     validate(seconds, TimeUnit.SECONDS);
/*  44 */     return new Duration(Long.valueOf(seconds), TimeUnit.SECONDS);
/*     */   }
/*     */ 
/*     */   public static Duration hours(long hours) {
/*  48 */     return minutes(hours * 60L);
/*     */   }
/*     */ 
/*     */   public static Duration days(long days) {
/*  52 */     return hours(days * 24L);
/*     */   }
/*     */ 
/*     */   private static void validate(long value, TimeUnit unit) {
/*  56 */     Duration duration = new Duration(Long.valueOf(value), unit);
/*  57 */     if (duration.inMillis() == 9223372036854775807L)
/*  58 */       throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */   public long inMillis() {
/*  62 */     return this.unit.toMillis(this.value.longValue());
/*     */   }
/*     */ 
/*     */   public long inSeconds() {
/*  66 */     return this.unit.toSeconds(this.value.longValue());
/*     */   }
/*     */ 
/*     */   public long inMinutes() {
/*  70 */     return this.unit.toSeconds(this.value.longValue()) / 60L;
/*     */   }
/*     */ 
/*     */   public long inHours() {
/*  74 */     return inMinutes() / 60L;
/*     */   }
/*     */ 
/*     */   public long inDays() {
/*  78 */     return inHours() / 24L;
/*     */   }
/*     */ 
/*     */   public Duration plus(Duration duration) {
/*  82 */     return millis(duration.inMillis() + inMillis());
/*     */   }
/*     */ 
/*     */   public Duration minus(Duration duration) {
/*  86 */     return millis(inMillis() - duration.inMillis());
/*     */   }
/*     */ 
/*     */   public Boolean greaterThan(Duration duration) {
/*  90 */     return Boolean.valueOf(inMillis() > duration.inMillis());
/*     */   }
/*     */ 
/*     */   public Boolean lessThan(Duration duration) {
/*  94 */     return Boolean.valueOf(inMillis() < duration.inMillis());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  99 */     return new Long(this.unit.toMillis(this.value.longValue())).hashCode();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 104 */     if (o == null)
/* 105 */       return false;
/* 106 */     if (!o.getClass().getName().equals(Duration.class.getName()))
/* 107 */       return false;
/* 108 */     Duration other = (Duration)o;
/* 109 */     return other.unit.toMillis(other.value.longValue()) == this.unit.toMillis(this.value.longValue());
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 113 */     return "Duration " + this.value + " " + this.unit;
/*     */   }
/*     */ 
/*     */   public int compareTo(Duration other) {
/* 117 */     return this.value.compareTo(other.value);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.Duration
 * JD-Core Version:    0.6.2
 */