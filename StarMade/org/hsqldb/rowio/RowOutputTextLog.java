package org.hsqldb.rowio;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import org.hsqldb.Row;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class RowOutputTextLog
  extends RowOutputBase
{
  static byte[] BYTES_NULL;
  static byte[] BYTES_TRUE;
  static byte[] BYTES_FALSE;
  static byte[] BYTES_AND;
  static byte[] BYTES_IS;
  static byte[] BYTES_ARRAY;
  public static final int MODE_DELETE = 1;
  public static final int MODE_INSERT = 0;
  private boolean isWritten;
  private int logMode;
  private boolean noSeparators;
  
  public void setMode(int paramInt)
  {
    this.logMode = paramInt;
  }
  
  protected void writeFieldPrefix()
  {
    if ((!this.noSeparators) && (this.logMode == 1) && (this.isWritten)) {
      write(BYTES_AND);
    }
  }
  
  protected void writeChar(String paramString, Type paramType)
  {
    write(39);
    StringConverter.stringToUnicodeBytes(this, paramString, true);
    write(39);
  }
  
  protected void writeReal(Double paramDouble)
  {
    writeBytes(Type.SQL_DOUBLE.convertToSQLString(paramDouble));
  }
  
  protected void writeSmallint(Number paramNumber)
  {
    writeBytes(paramNumber.toString());
  }
  
  public void writeEnd() {}
  
  protected void writeBit(BinaryData paramBinaryData)
  {
    ensureRoom((int)(paramBinaryData.length(null) * 8L + 2L));
    write(39);
    String str = StringConverter.byteArrayToBitString(paramBinaryData.getBytes(), (int)paramBinaryData.bitLength(null));
    writeBytes(str);
    write(39);
  }
  
  protected void writeBinary(BinaryData paramBinaryData)
  {
    ensureRoom((int)(paramBinaryData.length(null) * 2L + 2L));
    write(39);
    StringConverter.writeHexBytes(getBuffer(), this.count, paramBinaryData.getBytes());
    this.count = ((int)(this.count + paramBinaryData.length(null) * 2L));
    write(39);
  }
  
  protected void writeClob(ClobData paramClobData, Type paramType)
  {
    writeBytes(Long.toString(paramClobData.getId()));
  }
  
  protected void writeBlob(BlobData paramBlobData, Type paramType)
  {
    writeBytes(Long.toString(paramBlobData.getId()));
  }
  
  protected void writeArray(Object[] paramArrayOfObject, Type paramType)
  {
    paramType = paramType.collectionBaseType();
    this.noSeparators = true;
    write(BYTES_ARRAY);
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      if (i > 0) {
        write(44);
      }
      writeData(paramType, paramArrayOfObject[i]);
    }
    write(93);
    this.noSeparators = false;
  }
  
  public void writeType(int paramInt) {}
  
  public void writeSize(int paramInt) {}
  
  public int getSize(Row paramRow)
  {
    return 0;
  }
  
  public int getStorageSize(int paramInt)
  {
    return paramInt;
  }
  
  protected void writeInteger(Number paramNumber)
  {
    writeBytes(paramNumber.toString());
  }
  
  protected void writeBigint(Number paramNumber)
  {
    writeBytes(paramNumber.toString());
  }
  
  protected void writeNull(Type paramType)
  {
    if (!this.noSeparators)
    {
      if (this.logMode == 1) {
        write(BYTES_IS);
      } else if (this.isWritten) {
        write(44);
      }
      this.isWritten = true;
    }
    write(BYTES_NULL);
  }
  
  protected void writeOther(JavaObjectData paramJavaObjectData)
  {
    ensureRoom(paramJavaObjectData.getBytesLength() * 2 + 2);
    write(39);
    StringConverter.writeHexBytes(getBuffer(), this.count, paramJavaObjectData.getBytes());
    this.count += paramJavaObjectData.getBytesLength() * 2;
    write(39);
  }
  
  public void writeString(String paramString)
  {
    StringConverter.stringToUnicodeBytes(this, paramString, false);
  }
  
  protected void writeBoolean(Boolean paramBoolean)
  {
    write(paramBoolean.booleanValue() ? BYTES_TRUE : BYTES_FALSE);
  }
  
  protected void writeDecimal(BigDecimal paramBigDecimal, Type paramType)
  {
    writeBytes(paramType.convertToSQLString(paramBigDecimal));
  }
  
  protected void writeFieldType(Type paramType)
  {
    if (!this.noSeparators)
    {
      if (this.logMode == 1) {
        write(61);
      } else if (this.isWritten) {
        write(44);
      }
      this.isWritten = true;
    }
  }
  
  public void writeLong(long paramLong)
  {
    writeBytes(Long.toString(paramLong));
  }
  
  public void writeIntData(int paramInt1, int paramInt2) {}
  
  protected void writeTime(TimeData paramTimeData, Type paramType)
  {
    write(39);
    writeBytes(paramType.convertToString(paramTimeData));
    write(39);
  }
  
  protected void writeDate(TimestampData paramTimestampData, Type paramType)
  {
    write(39);
    writeBytes(paramType.convertToString(paramTimestampData));
    write(39);
  }
  
  protected void writeTimestamp(TimestampData paramTimestampData, Type paramType)
  {
    write(39);
    writeBytes(paramType.convertToString(paramTimestampData));
    write(39);
  }
  
  protected void writeYearMonthInterval(IntervalMonthData paramIntervalMonthData, Type paramType)
  {
    write(39);
    writeBytes(paramType.convertToString(paramIntervalMonthData));
    write(39);
  }
  
  protected void writeDaySecondInterval(IntervalSecondData paramIntervalSecondData, Type paramType)
  {
    write(39);
    writeBytes(paramType.convertToString(paramIntervalSecondData));
    write(39);
  }
  
  public void writeShort(int paramInt)
  {
    writeBytes(Integer.toString(paramInt));
  }
  
  public void writeInt(int paramInt)
  {
    writeBytes(Integer.toString(paramInt));
  }
  
  public void reset()
  {
    super.reset();
    this.isWritten = false;
  }
  
  public RowOutputInterface duplicate()
  {
    throw Error.runtimeError(201, "RowOutputText");
  }
  
  static
  {
    try
    {
      BYTES_NULL = "NULL".getBytes("ISO-8859-1");
      BYTES_TRUE = "TRUE".getBytes("ISO-8859-1");
      BYTES_FALSE = "FALSE".getBytes("ISO-8859-1");
      BYTES_AND = " AND ".getBytes("ISO-8859-1");
      BYTES_IS = " IS ".getBytes("ISO-8859-1");
      BYTES_ARRAY = " ARRAY[".getBytes("ISO-8859-1");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Error.runtimeError(201, "RowOutputTextLog");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.rowio.RowOutputTextLog
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */