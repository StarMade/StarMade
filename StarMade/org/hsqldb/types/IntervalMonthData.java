package org.hsqldb.types;

import org.hsqldb.error.Error;

public class IntervalMonthData
{
  public final long units;
  
  public static IntervalMonthData newIntervalYear(long paramLong, IntervalType paramIntervalType)
  {
    return new IntervalMonthData(paramLong * 12L, paramIntervalType);
  }
  
  public static IntervalMonthData newIntervalMonth(long paramLong, IntervalType paramIntervalType)
  {
    return new IntervalMonthData(paramLong, paramIntervalType);
  }
  
  public IntervalMonthData(long paramLong, IntervalType paramIntervalType)
  {
    if (paramLong >= paramIntervalType.getIntervalValueLimit()) {
      throw Error.error(3406);
    }
    if (paramIntervalType.typeCode == 101) {
      paramLong -= paramLong % 12L;
    }
    this.units = paramLong;
  }
  
  public IntervalMonthData(long paramLong)
  {
    this.units = paramLong;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof IntervalMonthData)) {
      return this.units == ((IntervalMonthData)paramObject).units;
    }
    return false;
  }
  
  public int hashCode()
  {
    return (int)this.units;
  }
  
  public int compareTo(IntervalMonthData paramIntervalMonthData)
  {
    long l = this.units - paramIntervalMonthData.units;
    if (l == 0L) {
      return 0;
    }
    return l > 0L ? 1 : -1;
  }
  
  public String toString()
  {
    return Type.SQL_INTERVAL_MONTH_MAX_PRECISION.convertToString(this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.IntervalMonthData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */