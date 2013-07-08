package org.hsqldb.rowio;

import java.io.IOException;
import java.math.BigDecimal;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlByteArrayInputStream;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

abstract class RowInputBase
  extends HsqlByteArrayInputStream
{
  static final int NO_POS = -1;
  protected long filePos = -1L;
  protected int size;
  
  RowInputBase()
  {
    this(new byte[4]);
  }
  
  RowInputBase(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
    this.size = paramArrayOfByte.length;
  }
  
  public long getPos()
  {
    if (this.filePos == -1L) {}
    return this.filePos;
  }
  
  public int getSize()
  {
    return this.size;
  }
  
  public abstract int readType()
    throws IOException;
  
  public abstract String readString()
    throws IOException;
  
  protected abstract boolean readNull()
    throws IOException;
  
  protected abstract String readChar(Type paramType)
    throws IOException;
  
  protected abstract Integer readSmallint()
    throws IOException;
  
  protected abstract Integer readInteger()
    throws IOException;
  
  protected abstract Long readBigint()
    throws IOException;
  
  protected abstract Double readReal()
    throws IOException;
  
  protected abstract BigDecimal readDecimal(Type paramType)
    throws IOException;
  
  protected abstract Boolean readBoole()
    throws IOException;
  
  protected abstract TimeData readTime(Type paramType)
    throws IOException;
  
  protected abstract TimestampData readDate(Type paramType)
    throws IOException;
  
  protected abstract TimestampData readTimestamp(Type paramType)
    throws IOException;
  
  protected abstract IntervalMonthData readYearMonthInterval(Type paramType)
    throws IOException;
  
  protected abstract IntervalSecondData readDaySecondInterval(Type paramType)
    throws IOException;
  
  protected abstract Object readOther()
    throws IOException;
  
  protected abstract BinaryData readBinary()
    throws IOException, HsqlException;
  
  protected abstract BinaryData readBit()
    throws IOException;
  
  protected abstract ClobData readClob()
    throws IOException;
  
  protected abstract BlobData readBlob()
    throws IOException;
  
  protected abstract Object[] readArray(Type paramType)
    throws IOException;
  
  public Object[] readData(Type[] paramArrayOfType)
    throws IOException
  {
    int i = paramArrayOfType.length;
    Object[] arrayOfObject = new Object[i];
    for (int j = 0; j < i; j++)
    {
      Type localType = paramArrayOfType[j];
      arrayOfObject[j] = readData(localType);
    }
    return arrayOfObject;
  }
  
  public Object readData(Type paramType)
    throws IOException
  {
    Object localObject = null;
    if (readNull()) {
      return null;
    }
    switch (paramType.typeCode)
    {
    case 0: 
      break;
    case 1: 
    case 12: 
    case 100: 
      localObject = readChar(paramType);
      break;
    case -6: 
    case 5: 
      localObject = readSmallint();
      break;
    case 4: 
      localObject = readInteger();
      break;
    case 25: 
      localObject = readBigint();
      break;
    case 6: 
    case 7: 
    case 8: 
      localObject = readReal();
      break;
    case 2: 
    case 3: 
      localObject = readDecimal(paramType);
      break;
    case 91: 
      localObject = readDate(paramType);
      break;
    case 92: 
    case 94: 
      localObject = readTime(paramType);
      break;
    case 93: 
    case 95: 
      localObject = readTimestamp(paramType);
      break;
    case 101: 
    case 102: 
    case 107: 
      localObject = readYearMonthInterval(paramType);
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
      localObject = readDaySecondInterval(paramType);
      break;
    case 16: 
      localObject = readBoole();
      break;
    case 1111: 
      localObject = readOther();
      break;
    case 40: 
      localObject = readClob();
      break;
    case 30: 
      localObject = readBlob();
      break;
    case 50: 
      localObject = readArray(paramType);
      break;
    case 60: 
    case 61: 
      localObject = readBinary();
      break;
    case 14: 
    case 15: 
      localObject = readBit();
      break;
    default: 
      throw Error.runtimeError(201, "RowInputBase - " + paramType.getNameString());
    }
    return localObject;
  }
  
  public void resetRow(long paramLong, int paramInt)
    throws IOException
  {
    this.mark = 0;
    reset();
    if (this.buffer.length < paramInt) {
      this.buffer = new byte[paramInt];
    }
    this.filePos = paramLong;
    this.size = (this.count = paramInt);
    this.pos = 4;
    this.buffer[0] = ((byte)(paramInt >>> 24 & 0xFF));
    this.buffer[1] = ((byte)(paramInt >>> 16 & 0xFF));
    this.buffer[2] = ((byte)(paramInt >>> 8 & 0xFF));
    this.buffer[3] = ((byte)(paramInt >>> 0 & 0xFF));
  }
  
  public byte[] getBuffer()
  {
    return this.buffer;
  }
  
  public int skipBytes(int paramInt)
    throws IOException
  {
    throw Error.runtimeError(201, "RowInputBase");
  }
  
  public String readLine()
    throws IOException
  {
    throw Error.runtimeError(201, "RowInputBase");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.rowio.RowInputBase
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */