package org.hsqldb.rowio;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.hsqldb.Row;
import org.hsqldb.TableBase;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class RowOutputBinary extends RowOutputBase
{
  public static final int INT_STORE_SIZE = 4;
  int storageSize;
  final int scale;
  final int mask;

  public RowOutputBinary(int paramInt1, int paramInt2)
  {
    super(paramInt1);
    this.scale = paramInt2;
    this.mask = (paramInt2 - 1 ^ 0xFFFFFFFF);
  }

  public RowOutputBinary(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
    this.scale = 1;
    this.mask = (this.scale - 1 ^ 0xFFFFFFFF);
  }

  public void writeIntData(int paramInt1, int paramInt2)
  {
    int i = this.count;
    this.count = paramInt2;
    writeInt(paramInt1);
    if (this.count < i)
      this.count = i;
  }

  public void writeData(Row paramRow, Type[] paramArrayOfType)
  {
    super.writeData(paramRow, paramArrayOfType);
  }

  public void writeEnd()
  {
    if (this.count > this.storageSize)
      Error.runtimeError(201, "RowOutputBinary");
    while (this.count < this.storageSize)
      write(0);
  }

  public void writeSize(int paramInt)
  {
    this.storageSize = paramInt;
    writeInt(paramInt);
  }

  public void writeType(int paramInt)
  {
    writeShort(paramInt);
  }

  public void writeString(String paramString)
  {
    int i = this.count;
    writeInt(0);
    if ((paramString != null) && (paramString.length() != 0))
    {
      StringConverter.stringToUTFBytes(paramString, this);
      writeIntData(this.count - i - 4, i);
    }
  }

  public int getSize(Row paramRow)
  {
    Object[] arrayOfObject = paramRow.getData();
    Type[] arrayOfType = paramRow.getTable().getColumnTypes();
    int i = paramRow.getTable().getDataColumnCount();
    return 4 + getSize(arrayOfObject, i, arrayOfType);
  }

  public int getStorageSize(int paramInt)
  {
    return paramInt + this.scale - 1 & this.mask;
  }

  public void writeFieldType(Type paramType)
  {
    write(1);
  }

  public void writeNull(Type paramType)
  {
    write(0);
  }

  protected void writeChar(String paramString, Type paramType)
  {
    writeString(paramString);
  }

  protected void writeSmallint(Number paramNumber)
  {
    writeShort(paramNumber.intValue());
  }

  protected void writeInteger(Number paramNumber)
  {
    writeInt(paramNumber.intValue());
  }

  protected void writeBigint(Number paramNumber)
  {
    writeLong(paramNumber.longValue());
  }

  protected void writeReal(Double paramDouble)
  {
    writeLong(Double.doubleToLongBits(paramDouble.doubleValue()));
  }

  protected void writeDecimal(BigDecimal paramBigDecimal, Type paramType)
  {
    int i = paramBigDecimal.scale();
    BigInteger localBigInteger = JavaSystem.unscaledValue(paramBigDecimal);
    byte[] arrayOfByte = localBigInteger.toByteArray();
    writeByteArray(arrayOfByte);
    writeInt(i);
  }

  protected void writeBoolean(Boolean paramBoolean)
  {
    write(paramBoolean.booleanValue() ? 1 : 0);
  }

  protected void writeDate(TimestampData paramTimestampData, Type paramType)
  {
    writeLong(paramTimestampData.getSeconds());
  }

  protected void writeTime(TimeData paramTimeData, Type paramType)
  {
    writeInt(paramTimeData.getSeconds());
    writeInt(paramTimeData.getNanos());
    if (paramType.typeCode == 94)
      writeInt(paramTimeData.getZone());
  }

  protected void writeTimestamp(TimestampData paramTimestampData, Type paramType)
  {
    writeLong(paramTimestampData.getSeconds());
    writeInt(paramTimestampData.getNanos());
    if (paramType.typeCode == 95)
      writeInt(paramTimestampData.getZone());
  }

  protected void writeYearMonthInterval(IntervalMonthData paramIntervalMonthData, Type paramType)
  {
    writeLong(paramIntervalMonthData.units);
  }

  protected void writeDaySecondInterval(IntervalSecondData paramIntervalSecondData, Type paramType)
  {
    writeLong(paramIntervalSecondData.getSeconds());
    writeInt(paramIntervalSecondData.getNanos());
  }

  protected void writeOther(JavaObjectData paramJavaObjectData)
  {
    writeByteArray(paramJavaObjectData.getBytes());
  }

  protected void writeBit(BinaryData paramBinaryData)
  {
    writeInt((int)paramBinaryData.bitLength(null));
    write(paramBinaryData.getBytes(), 0, paramBinaryData.getBytes().length);
  }

  protected void writeBinary(BinaryData paramBinaryData)
  {
    writeByteArray(paramBinaryData.getBytes());
  }

  protected void writeClob(ClobData paramClobData, Type paramType)
  {
    writeLong(paramClobData.getId());
  }

  protected void writeBlob(BlobData paramBlobData, Type paramType)
  {
    writeLong(paramBlobData.getId());
  }

  protected void writeArray(Object[] paramArrayOfObject, Type paramType)
  {
    paramType = paramType.collectionBaseType();
    writeInt(paramArrayOfObject.length);
    for (int i = 0; i < paramArrayOfObject.length; i++)
      writeData(paramType, paramArrayOfObject[i]);
  }

  public void writeArray(int[] paramArrayOfInt)
  {
    writeInt(paramArrayOfInt.length);
    for (int i = 0; i < paramArrayOfInt.length; i++)
    {
      write(1);
      writeInt(paramArrayOfInt[i]);
    }
  }

  public void writeByteArray(byte[] paramArrayOfByte)
  {
    writeInt(paramArrayOfByte.length);
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void writeCharArray(char[] paramArrayOfChar)
  {
    writeInt(paramArrayOfChar.length);
    write(paramArrayOfChar, 0, paramArrayOfChar.length);
  }

  public int getSize(int[] paramArrayOfInt)
  {
    return 4 + paramArrayOfInt.length * 5;
  }

  public int getSize(Object[] paramArrayOfObject, int paramInt, Type[] paramArrayOfType)
  {
    int i = 0;
    for (int j = 0; j < paramInt; j++)
    {
      Object localObject = paramArrayOfObject[j];
      i += getSize(localObject, paramArrayOfType[j]);
    }
    return i;
  }

  private int getSize(Object paramObject, Type paramType)
  {
    int i = 1;
    if (paramObject == null)
      return i;
    Object localObject;
    switch (paramType.typeCode)
    {
    case 0:
      break;
    case 1:
    case 12:
    case 100:
      i += 4;
      i += StringConverter.getUTFSize((String)paramObject);
      break;
    case -6:
    case 5:
      i += 2;
      break;
    case 4:
      i += 4;
      break;
    case 6:
    case 7:
    case 8:
    case 25:
      i += 8;
      break;
    case 2:
    case 3:
      i += 8;
      BigDecimal localBigDecimal = (BigDecimal)paramObject;
      BigInteger localBigInteger = JavaSystem.unscaledValue(localBigDecimal);
      i += localBigInteger.toByteArray().length;
      break;
    case 16:
      i++;
      break;
    case 91:
      i += 8;
      break;
    case 92:
      i += 8;
      break;
    case 94:
      i += 12;
      break;
    case 93:
      i += 12;
      break;
    case 95:
      i += 16;
      break;
    case 101:
    case 102:
    case 107:
      i += 8;
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
      i += 12;
      break;
    case 60:
    case 61:
      i += 4;
      i = (int)(i + ((BinaryData)paramObject).length(null));
      break;
    case 14:
    case 15:
      i += 4;
      i = (int)(i + ((BinaryData)paramObject).length(null));
      break;
    case 30:
    case 40:
      i += 8;
      break;
    case 50:
      i += 4;
      localObject = (Object[])paramObject;
      paramType = paramType.collectionBaseType();
      for (int j = 0; j < localObject.length; j++)
        i += getSize(localObject[j], paramType);
      break;
    case 1111:
      localObject = (JavaObjectData)paramObject;
      i += 4;
      i += ((JavaObjectData)localObject).getBytesLength();
      break;
    default:
      throw Error.runtimeError(201, "RowOutputBinary");
    }
    return i;
  }

  public void ensureRoom(int paramInt)
  {
    super.ensureRoom(paramInt);
  }

  public void reset()
  {
    super.reset();
    this.storageSize = 0;
  }

  public void reset(int paramInt)
  {
    super.reset(paramInt);
    this.storageSize = 0;
  }

  public void reset(byte[] paramArrayOfByte)
  {
    super.reset(paramArrayOfByte);
    this.storageSize = 0;
  }

  public RowOutputInterface duplicate()
  {
    return new RowOutputBinary(128, this.scale);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.rowio.RowOutputBinary
 * JD-Core Version:    0.6.2
 */