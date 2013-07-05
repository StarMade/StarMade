package org.hsqldb.rowio;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class RowOutputBinary180 extends RowOutputBinary
{
  Calendar tempCalDefault = new GregorianCalendar();

  public RowOutputBinary180(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }

  protected void writeDate(TimestampData paramTimestampData, Type paramType)
  {
    long l = paramTimestampData.getSeconds() * 1000L;
    l = HsqlDateTime.convertMillisToCalendar(this.tempCalDefault, l);
    writeLong(l);
  }

  protected void writeTime(TimeData paramTimeData, Type paramType)
  {
    if (paramType.typeCode == 92)
    {
      long l = paramTimeData.getSeconds() * 1000L;
      l = HsqlDateTime.convertMillisToCalendar(this.tempCalDefault, l);
      writeLong(l);
    }
    else
    {
      writeInt(paramTimeData.getSeconds());
      writeInt(paramTimeData.getNanos());
      writeInt(paramTimeData.getZone());
    }
  }

  protected void writeTimestamp(TimestampData paramTimestampData, Type paramType)
  {
    if (paramType.typeCode == 93)
    {
      long l = paramTimestampData.getSeconds() * 1000L;
      l = HsqlDateTime.convertMillisToCalendar(this.tempCalDefault, l);
      writeLong(l);
      writeInt(paramTimestampData.getNanos());
    }
    else
    {
      writeLong(paramTimestampData.getSeconds());
      writeInt(paramTimestampData.getNanos());
      writeInt(paramTimestampData.getZone());
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.rowio.RowOutputBinary180
 * JD-Core Version:    0.6.2
 */