package org.hsqldb.rowio;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import org.hsqldb.Row;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.persist.TextFileSettings;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class RowOutputText
  extends RowOutputBase
{
  protected String fieldSep;
  protected String varSep;
  protected String longvarSep;
  private boolean fieldSepEnd;
  private boolean varSepEnd;
  private boolean longvarSepEnd;
  private String nextSep = "";
  private boolean nextSepEnd;
  protected boolean allQuoted;
  private String encoding;
  
  public RowOutputText(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
  {
    initTextDatabaseRowOutput(paramString1, paramString2, paramString3, paramBoolean, paramString4);
  }
  
  private void initTextDatabaseRowOutput(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
  {
    if (paramString1.endsWith("\n"))
    {
      this.fieldSepEnd = true;
      paramString1 = paramString1.substring(0, paramString1.length() - 1);
    }
    if (paramString2.endsWith("\n"))
    {
      this.varSepEnd = true;
      paramString2 = paramString2.substring(0, paramString2.length() - 1);
    }
    if (paramString3.endsWith("\n"))
    {
      this.longvarSepEnd = true;
      paramString3 = paramString3.substring(0, paramString3.length() - 1);
    }
    this.fieldSep = paramString1;
    this.varSep = paramString2;
    this.longvarSep = paramString3;
    this.allQuoted = paramBoolean;
    this.encoding = paramString4;
  }
  
  public void writeEnd()
  {
    if (this.nextSepEnd) {
      writeBytes(this.nextSep);
    }
    writeBytes(TextFileSettings.NL);
  }
  
  public void writeSize(int paramInt)
  {
    this.nextSep = "";
    this.nextSepEnd = false;
  }
  
  public void writeType(int paramInt) {}
  
  public void writeString(String paramString)
  {
    paramString = checkConvertString(paramString, this.fieldSep);
    if (paramString == null) {
      return;
    }
    byte[] arrayOfByte = getBytes(paramString);
    write(arrayOfByte, 0, arrayOfByte.length);
    this.nextSep = this.fieldSep;
    this.nextSepEnd = this.fieldSepEnd;
  }
  
  protected void writeVarString(String paramString)
  {
    paramString = checkConvertString(paramString, this.varSep);
    if (paramString == null) {
      return;
    }
    byte[] arrayOfByte = getBytes(paramString);
    write(arrayOfByte, 0, arrayOfByte.length);
    this.nextSep = this.varSep;
    this.nextSepEnd = this.varSepEnd;
  }
  
  protected void writeLongVarString(String paramString)
  {
    paramString = checkConvertString(paramString, this.longvarSep);
    if (paramString == null) {
      return;
    }
    byte[] arrayOfByte = getBytes(paramString);
    write(arrayOfByte, 0, arrayOfByte.length);
    this.nextSep = this.longvarSep;
    this.nextSepEnd = this.longvarSepEnd;
  }
  
  protected String checkConvertString(String paramString1, String paramString2)
  {
    if ((paramString1.indexOf('\n') != -1) || (paramString1.indexOf('\r') != -1)) {
      throw new IllegalArgumentException(Error.getMessage(485));
    }
    if (paramString1.indexOf(paramString2) != -1) {
      return null;
    }
    return paramString1;
  }
  
  private byte[] getBytes(String paramString)
  {
    byte[] arrayOfByte = null;
    try
    {
      arrayOfByte = paramString.getBytes(this.encoding);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      arrayOfByte = paramString.getBytes();
    }
    return arrayOfByte;
  }
  
  protected void writeByteArray(byte[] paramArrayOfByte)
  {
    ensureRoom(paramArrayOfByte.length * 2);
    StringConverter.writeHexBytes(getBuffer(), this.count, paramArrayOfByte);
    this.count += paramArrayOfByte.length * 2;
  }
  
  public void writeShort(int paramInt)
  {
    writeInt(paramInt);
  }
  
  public void writeInt(int paramInt)
  {
    writeBytes(Integer.toString(paramInt));
    this.nextSep = this.fieldSep;
    this.nextSepEnd = this.fieldSepEnd;
  }
  
  public void writeIntData(int paramInt1, int paramInt2)
  {
    throw Error.runtimeError(201, "RowOutputText");
  }
  
  public void writeLong(long paramLong)
  {
    throw Error.runtimeError(201, "RowOutputText");
  }
  
  protected void writeFieldType(Type paramType)
  {
    writeBytes(this.nextSep);
    switch (paramType.typeCode)
    {
    case 12: 
    case 100: 
      this.nextSep = this.varSep;
      this.nextSepEnd = this.varSepEnd;
      break;
    default: 
      this.nextSep = this.fieldSep;
      this.nextSepEnd = this.fieldSepEnd;
    }
  }
  
  protected void writeNull(Type paramType)
  {
    writeFieldType(paramType);
  }
  
  protected void writeChar(String paramString, Type paramType)
  {
    switch (paramType.typeCode)
    {
    case 1: 
      writeString(paramString);
      return;
    case 12: 
    case 100: 
      writeVarString(paramString);
      return;
    }
    writeLongVarString(paramString);
  }
  
  protected void writeSmallint(Number paramNumber)
  {
    writeString(paramNumber.toString());
  }
  
  protected void writeInteger(Number paramNumber)
  {
    writeString(paramNumber.toString());
  }
  
  protected void writeBigint(Number paramNumber)
  {
    writeString(paramNumber.toString());
  }
  
  protected void writeReal(Double paramDouble)
  {
    writeString(paramDouble.toString());
  }
  
  protected void writeDecimal(BigDecimal paramBigDecimal, Type paramType)
  {
    writeString(paramType.convertToString(paramBigDecimal));
  }
  
  protected void writeBoolean(Boolean paramBoolean)
  {
    writeString(paramBoolean.toString());
  }
  
  protected void writeDate(TimestampData paramTimestampData, Type paramType)
  {
    writeString(paramType.convertToString(paramTimestampData));
  }
  
  protected void writeTime(TimeData paramTimeData, Type paramType)
  {
    writeString(paramType.convertToString(paramTimeData));
  }
  
  protected void writeTimestamp(TimestampData paramTimestampData, Type paramType)
  {
    writeString(paramType.convertToString(paramTimestampData));
  }
  
  protected void writeYearMonthInterval(IntervalMonthData paramIntervalMonthData, Type paramType)
  {
    writeBytes(paramType.convertToString(paramIntervalMonthData));
  }
  
  protected void writeDaySecondInterval(IntervalSecondData paramIntervalSecondData, Type paramType)
  {
    writeBytes(paramType.convertToString(paramIntervalSecondData));
  }
  
  protected void writeOther(JavaObjectData paramJavaObjectData)
  {
    byte[] arrayOfByte = paramJavaObjectData.getBytes();
    writeByteArray(arrayOfByte);
  }
  
  protected void writeBit(BinaryData paramBinaryData)
  {
    String str = StringConverter.byteArrayToBitString(paramBinaryData.getBytes(), (int)paramBinaryData.bitLength(null));
    writeString(str);
  }
  
  protected void writeBinary(BinaryData paramBinaryData)
  {
    writeByteArray(paramBinaryData.getBytes());
  }
  
  protected void writeClob(ClobData paramClobData, Type paramType)
  {
    writeString(Long.toString(paramClobData.getId()));
  }
  
  protected void writeBlob(BlobData paramBlobData, Type paramType)
  {
    writeString(Long.toString(paramBlobData.getId()));
  }
  
  protected void writeArray(Object[] paramArrayOfObject, Type paramType)
  {
    throw Error.runtimeError(201, "RowOutputText");
  }
  
  public int getSize(Row paramRow)
  {
    reset();
    try
    {
      writeSize(0);
      writeData(paramRow, paramRow.getTable().getColumnTypes());
      writeEnd();
    }
    catch (Exception localException)
    {
      reset();
    }
    int i = size();
    reset();
    return i;
  }
  
  public int getStorageSize(int paramInt)
  {
    return paramInt;
  }
  
  public RowOutputInterface duplicate()
  {
    throw Error.runtimeError(201, "RowOutputText");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.rowio.RowOutputText
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */