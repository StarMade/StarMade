package org.hsqldb.rowio;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class RowInputBinary180 extends RowInputBinary
{
  Calendar tempCalDefault = new GregorianCalendar();

  public RowInputBinary180(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
  }

  protected TimeData readTime(Type paramType)
    throws IOException
  {
    if (paramType.typeCode == 92)
    {
      long l = readLong();
      l = HsqlDateTime.convertMillisFromCalendar(this.tempCalDefault, l);
      l = HsqlDateTime.getNormalisedTime(l);
      return new TimeData((int)(l / 1000L), 0, 0);
    }
    return new TimeData(readInt(), readInt(), readInt());
  }

  protected TimestampData readDate(Type paramType)
    throws IOException
  {
    long l = readLong();
    l = HsqlDateTime.convertMillisFromCalendar(this.tempCalDefault, l);
    l = HsqlDateTime.getNormalisedDate(l);
    return new TimestampData(l / 1000L);
  }

  protected TimestampData readTimestamp(Type paramType)
    throws IOException
  {
    if (paramType.typeCode == 93)
    {
      long l = readLong();
      int i = readInt();
      l = HsqlDateTime.convertMillisFromCalendar(this.tempCalDefault, l);
      return new TimestampData(l / 1000L, i);
    }
    return new TimestampData(readLong(), readInt(), readInt());
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.rowio.RowInputBinary180
 * JD-Core Version:    0.6.2
 */