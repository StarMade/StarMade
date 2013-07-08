/*   1:    */package com.google.code.tempusfugit.temporal;
/*   2:    */
/*   3:    */import java.util.concurrent.TimeUnit;
/*   4:    */
/*  19:    */public class Duration
/*  20:    */  implements Comparable<Duration>
/*  21:    */{
/*  22:    */  private final Long value;
/*  23:    */  private final TimeUnit unit;
/*  24:    */  
/*  25:    */  private Duration(Long value, TimeUnit unit)
/*  26:    */  {
/*  27: 27 */    this.value = value;
/*  28: 28 */    this.unit = unit;
/*  29:    */  }
/*  30:    */  
/*  31:    */  public static Duration seconds(long seconds) {
/*  32: 32 */    validate(seconds, TimeUnit.SECONDS);
/*  33: 33 */    return new Duration(Long.valueOf(seconds), TimeUnit.SECONDS);
/*  34:    */  }
/*  35:    */  
/*  36:    */  public static Duration millis(long millis) {
/*  37: 37 */    validate(millis, TimeUnit.MILLISECONDS);
/*  38: 38 */    return new Duration(Long.valueOf(millis), TimeUnit.MILLISECONDS);
/*  39:    */  }
/*  40:    */  
/*  41:    */  public static Duration minutes(long minutes) {
/*  42: 42 */    long seconds = minutes * 60L;
/*  43: 43 */    validate(seconds, TimeUnit.SECONDS);
/*  44: 44 */    return new Duration(Long.valueOf(seconds), TimeUnit.SECONDS);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public static Duration hours(long hours) {
/*  48: 48 */    return minutes(hours * 60L);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public static Duration days(long days) {
/*  52: 52 */    return hours(days * 24L);
/*  53:    */  }
/*  54:    */  
/*  55:    */  private static void validate(long value, TimeUnit unit) {
/*  56: 56 */    Duration duration = new Duration(Long.valueOf(value), unit);
/*  57: 57 */    if (duration.inMillis() == 9223372036854775807L)
/*  58: 58 */      throw new IllegalArgumentException();
/*  59:    */  }
/*  60:    */  
/*  61:    */  public long inMillis() {
/*  62: 62 */    return this.unit.toMillis(this.value.longValue());
/*  63:    */  }
/*  64:    */  
/*  65:    */  public long inSeconds() {
/*  66: 66 */    return this.unit.toSeconds(this.value.longValue());
/*  67:    */  }
/*  68:    */  
/*  69:    */  public long inMinutes() {
/*  70: 70 */    return this.unit.toSeconds(this.value.longValue()) / 60L;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public long inHours() {
/*  74: 74 */    return inMinutes() / 60L;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public long inDays() {
/*  78: 78 */    return inHours() / 24L;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public Duration plus(Duration duration) {
/*  82: 82 */    return millis(duration.inMillis() + inMillis());
/*  83:    */  }
/*  84:    */  
/*  85:    */  public Duration minus(Duration duration) {
/*  86: 86 */    return millis(inMillis() - duration.inMillis());
/*  87:    */  }
/*  88:    */  
/*  89:    */  public Boolean greaterThan(Duration duration) {
/*  90: 90 */    return Boolean.valueOf(inMillis() > duration.inMillis());
/*  91:    */  }
/*  92:    */  
/*  93:    */  public Boolean lessThan(Duration duration) {
/*  94: 94 */    return Boolean.valueOf(inMillis() < duration.inMillis());
/*  95:    */  }
/*  96:    */  
/*  97:    */  public int hashCode()
/*  98:    */  {
/*  99: 99 */    return new Long(this.unit.toMillis(this.value.longValue())).hashCode();
/* 100:    */  }
/* 101:    */  
/* 102:    */  public boolean equals(Object o)
/* 103:    */  {
/* 104:104 */    if (o == null)
/* 105:105 */      return false;
/* 106:106 */    if (!o.getClass().getName().equals(Duration.class.getName()))
/* 107:107 */      return false;
/* 108:108 */    Duration other = (Duration)o;
/* 109:109 */    return other.unit.toMillis(other.value.longValue()) == this.unit.toMillis(this.value.longValue());
/* 110:    */  }
/* 111:    */  
/* 112:    */  public String toString() {
/* 113:113 */    return "Duration " + this.value + " " + this.unit;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public int compareTo(Duration other) {
/* 117:117 */    return this.value.compareTo(other.value);
/* 118:    */  }
/* 119:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.temporal.Duration
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */