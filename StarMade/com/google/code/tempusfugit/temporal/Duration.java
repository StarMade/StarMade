package com.google.code.tempusfugit.temporal;

import java.util.concurrent.TimeUnit;

public class Duration
  implements Comparable<Duration>
{
  private final Long value;
  private final TimeUnit unit;
  
  private Duration(Long value, TimeUnit unit)
  {
    this.value = value;
    this.unit = unit;
  }
  
  public static Duration seconds(long seconds)
  {
    validate(seconds, TimeUnit.SECONDS);
    return new Duration(Long.valueOf(seconds), TimeUnit.SECONDS);
  }
  
  public static Duration millis(long millis)
  {
    validate(millis, TimeUnit.MILLISECONDS);
    return new Duration(Long.valueOf(millis), TimeUnit.MILLISECONDS);
  }
  
  public static Duration minutes(long minutes)
  {
    long seconds = minutes * 60L;
    validate(seconds, TimeUnit.SECONDS);
    return new Duration(Long.valueOf(seconds), TimeUnit.SECONDS);
  }
  
  public static Duration hours(long hours)
  {
    return minutes(hours * 60L);
  }
  
  public static Duration days(long days)
  {
    return hours(days * 24L);
  }
  
  private static void validate(long value, TimeUnit unit)
  {
    Duration duration = new Duration(Long.valueOf(value), unit);
    if (duration.inMillis() == 9223372036854775807L) {
      throw new IllegalArgumentException();
    }
  }
  
  public long inMillis()
  {
    return this.unit.toMillis(this.value.longValue());
  }
  
  public long inSeconds()
  {
    return this.unit.toSeconds(this.value.longValue());
  }
  
  public long inMinutes()
  {
    return this.unit.toSeconds(this.value.longValue()) / 60L;
  }
  
  public long inHours()
  {
    return inMinutes() / 60L;
  }
  
  public long inDays()
  {
    return inHours() / 24L;
  }
  
  public Duration plus(Duration duration)
  {
    return millis(duration.inMillis() + inMillis());
  }
  
  public Duration minus(Duration duration)
  {
    return millis(inMillis() - duration.inMillis());
  }
  
  public Boolean greaterThan(Duration duration)
  {
    return Boolean.valueOf(inMillis() > duration.inMillis());
  }
  
  public Boolean lessThan(Duration duration)
  {
    return Boolean.valueOf(inMillis() < duration.inMillis());
  }
  
  public int hashCode()
  {
    return new Long(this.unit.toMillis(this.value.longValue())).hashCode();
  }
  
  public boolean equals(Object local_o)
  {
    if (local_o == null) {
      return false;
    }
    if (!local_o.getClass().getName().equals(Duration.class.getName())) {
      return false;
    }
    Duration other = (Duration)local_o;
    return other.unit.toMillis(other.value.longValue()) == this.unit.toMillis(this.value.longValue());
  }
  
  public String toString()
  {
    return "Duration " + this.value + " " + this.unit;
  }
  
  public int compareTo(Duration other)
  {
    return this.value.compareTo(other.value);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.temporal.Duration
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */