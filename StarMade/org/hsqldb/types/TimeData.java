package org.hsqldb.types;

public class TimeData
{
  final int zone;
  final int seconds;
  final int nanos;

  public TimeData(int paramInt1, int paramInt2, int paramInt3)
  {
    while (paramInt1 < 0)
      paramInt1 += 86400;
    if (paramInt1 > 86400)
      paramInt1 %= 86400;
    this.zone = paramInt3;
    this.seconds = paramInt1;
    this.nanos = paramInt2;
  }

  public TimeData(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, 0);
  }

  public int getSeconds()
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
    if ((paramObject instanceof TimeData))
      return (this.seconds == ((TimeData)paramObject).seconds) && (this.nanos == ((TimeData)paramObject).nanos) && (this.zone == ((TimeData)paramObject).zone);
    return false;
  }

  public int hashCode()
  {
    return this.seconds ^ this.nanos;
  }

  public int compareTo(TimeData paramTimeData)
  {
    long l = this.seconds - paramTimeData.seconds;
    if (l == 0L)
    {
      l = this.nanos - paramTimeData.nanos;
      if (l == 0L)
        return 0;
    }
    return l > 0L ? 1 : -1;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.TimeData
 * JD-Core Version:    0.6.2
 */