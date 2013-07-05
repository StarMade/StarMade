package org.hsqldb.types;

public class TimestampData
{
  final long seconds;
  final int nanos;
  final int zone;

  public TimestampData(long paramLong)
  {
    this.seconds = paramLong;
    this.nanos = 0;
    this.zone = 0;
  }

  public TimestampData(long paramLong, int paramInt)
  {
    this.seconds = paramLong;
    this.nanos = paramInt;
    this.zone = 0;
  }

  public TimestampData(long paramLong, int paramInt1, int paramInt2)
  {
    this.seconds = paramLong;
    this.nanos = paramInt1;
    this.zone = paramInt2;
  }

  public long getSeconds()
  {
    return this.seconds;
  }

  public int getNanos()
  {
    return this.nanos;
  }

  public int getZone()
  {
    return this.zone;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof TimestampData))
      return (this.seconds == ((TimestampData)paramObject).seconds) && (this.nanos == ((TimestampData)paramObject).nanos) && (this.zone == ((TimestampData)paramObject).zone);
    return false;
  }

  public int hashCode()
  {
    return (int)this.seconds ^ this.nanos;
  }

  public int compareTo(TimestampData paramTimestampData)
  {
    long l = this.seconds - paramTimestampData.seconds;
    if (l == 0L)
    {
      l = this.nanos - paramTimestampData.nanos;
      if (l == 0L)
        return 0;
    }
    return l > 0L ? 1 : -1;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.TimestampData
 * JD-Core Version:    0.6.2
 */