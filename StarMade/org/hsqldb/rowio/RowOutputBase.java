package org.hsqldb.rowio;

import java.math.BigDecimal;
import org.hsqldb.ColumnSchema;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Row;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

abstract class RowOutputBase
  extends HsqlByteArrayOutputStream
  implements RowOutputInterface
{
  public static final int CACHED_ROW_160 = 0;
  public static final int CACHED_ROW_170 = 1;
  protected boolean skipSystemId = false;
  
  public RowOutputBase() {}
  
  public RowOutputBase(int paramInt)
  {
    super(paramInt);
  }
  
  public RowOutputBase(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
  }
  
  public abstract void writeEnd();
  
  public abstract void writeSize(int paramInt);
  
  public abstract void writeType(int paramInt);
  
  public abstract void writeIntData(int paramInt1, int paramInt2);
  
  public abstract void writeString(String paramString);
  
  protected void writeFieldPrefix() {}
  
  protected abstract void writeFieldType(Type paramType);
  
  protected abstract void writeNull(Type paramType);
  
  protected abstract void writeChar(String paramString, Type paramType);
  
  protected abstract void writeSmallint(Number paramNumber);
  
  protected abstract void writeInteger(Number paramNumber);
  
  protected abstract void writeBigint(Number paramNumber);
  
  protected abstract void writeReal(Double paramDouble);
  
  protected abstract void writeDecimal(BigDecimal paramBigDecimal, Type paramType);
  
  protected abstract void writeBoolean(Boolean paramBoolean);
  
  protected abstract void writeDate(TimestampData paramTimestampData, Type paramType);
  
  protected abstract void writeTime(TimeData paramTimeData, Type paramType);
  
  protected abstract void writeTimestamp(TimestampData paramTimestampData, Type paramType);
  
  protected abstract void writeYearMonthInterval(IntervalMonthData paramIntervalMonthData, Type paramType);
  
  protected abstract void writeDaySecondInterval(IntervalSecondData paramIntervalSecondData, Type paramType);
  
  protected abstract void writeOther(JavaObjectData paramJavaObjectData);
  
  protected abstract void writeBit(BinaryData paramBinaryData);
  
  protected abstract void writeBinary(BinaryData paramBinaryData);
  
  protected abstract void writeClob(ClobData paramClobData, Type paramType);
  
  protected abstract void writeBlob(BlobData paramBlobData, Type paramType);
  
  protected abstract void writeArray(Object[] paramArrayOfObject, Type paramType);
  
  public void writeData(Row paramRow, Type[] paramArrayOfType)
  {
    writeData(paramArrayOfType.length, paramArrayOfType, paramRow.getData(), null, null);
  }
  
  public void writeData(int paramInt, Type[] paramArrayOfType, Object[] paramArrayOfObject, HashMappedList paramHashMappedList, int[] paramArrayOfInt)
  {
    int i = (paramArrayOfInt != null) && (paramArrayOfInt.length != 0) ? 1 : 0;
    int j = i != 0 ? paramArrayOfInt.length : paramInt;
    for (int k = 0; k < j; k++)
    {
      int m = i != 0 ? paramArrayOfInt[k] : k;
      Object localObject = paramArrayOfObject[m];
      Type localType = paramArrayOfType[m];
      if (paramHashMappedList != null)
      {
        ColumnSchema localColumnSchema = (ColumnSchema)paramHashMappedList.get(m);
        writeFieldPrefix();
        writeString(localColumnSchema.getName().statementName);
      }
      writeData(localType, localObject);
    }
  }
  
  public void writeData(Type paramType, Object paramObject)
  {
    if (paramObject == null)
    {
      writeNull(paramType);
      return;
    }
    writeFieldType(paramType);
    switch (paramType.typeCode)
    {
    case 0: 
      break;
    case 1: 
    case 12: 
    case 100: 
      writeChar((String)paramObject, paramType);
      break;
    case -6: 
    case 5: 
      writeSmallint((Number)paramObject);
      break;
    case 4: 
      writeInteger((Number)paramObject);
      break;
    case 25: 
      writeBigint((Number)paramObject);
      break;
    case 6: 
    case 7: 
    case 8: 
      writeReal((Double)paramObject);
      break;
    case 2: 
    case 3: 
      writeDecimal((BigDecimal)paramObject, paramType);
      break;
    case 16: 
      writeBoolean((Boolean)paramObject);
      break;
    case 91: 
      writeDate((TimestampData)paramObject, paramType);
      break;
    case 92: 
    case 94: 
      writeTime((TimeData)paramObject, paramType);
      break;
    case 93: 
    case 95: 
      writeTimestamp((TimestampData)paramObject, paramType);
      break;
    case 101: 
    case 102: 
    case 107: 
      writeYearMonthInterval((IntervalMonthData)paramObject, paramType);
      break;
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
      writeDaySecondInterval((IntervalSecondData)paramObject, paramType);
      break;
    case 1111: 
      writeOther((JavaObjectData)paramObject);
      break;
    case 30: 
      writeBlob((BlobData)paramObject, paramType);
      break;
    case 40: 
      writeClob((ClobData)paramObject, paramType);
      break;
    case 50: 
      writeArray((Object[])paramObject, paramType);
      break;
    case 60: 
    case 61: 
      writeBinary((BinaryData)paramObject);
      break;
    case 14: 
    case 15: 
      writeBit((BinaryData)paramObject);
      break;
    default: 
      throw Error.runtimeError(201, "RowOutputBase - " + paramType.getNameString());
    }
  }
  
  public HsqlByteArrayOutputStream getOutputStream()
  {
    return this;
  }
  
  public abstract RowOutputInterface duplicate();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.rowio.RowOutputBase
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */