package org.hsqldb.rowio;

import java.io.EOFException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class RowInputBinary
  extends RowInputBase
  implements RowInputInterface
{
  private RowOutputBinary out;
  
  public RowInputBinary(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
  }
  
  public RowInputBinary(RowOutputBinary paramRowOutputBinary)
  {
    super(paramRowOutputBinary.getBuffer());
    this.out = paramRowOutputBinary;
  }
  
  public int readType()
    throws IOException
  {
    return readShort();
  }
  
  public String readString()
    throws IOException
  {
    int i = readInt();
    String str = StringConverter.readUTF(this.buffer, this.pos, i);
    str = ValuePool.getString(str);
    this.pos += i;
    return str;
  }
  
  public boolean readNull()
    throws IOException
  {
    int i = readByte();
    return i == 0;
  }
  
  protected String readChar(Type paramType)
    throws IOException
  {
    return readString();
  }
  
  protected Integer readSmallint()
    throws IOException
  {
    return ValuePool.getInt(readShort());
  }
  
  protected Integer readInteger()
    throws IOException
  {
    return ValuePool.getInt(readInt());
  }
  
  protected Long readBigint()
    throws IOException
  {
    return ValuePool.getLong(readLong());
  }
  
  protected Double readReal()
    throws IOException
  {
    return ValuePool.getDouble(readLong());
  }
  
  protected BigDecimal readDecimal(Type paramType)
    throws IOException
  {
    byte[] arrayOfByte = readByteArray();
    int i = readInt();
    BigInteger localBigInteger = new BigInteger(arrayOfByte);
    return ValuePool.getBigDecimal(new BigDecimal(localBigInteger, i));
  }
  
  protected Boolean readBoole()
    throws IOException
  {
    return readBoolean() ? Boolean.TRUE : Boolean.FALSE;
  }
  
  protected TimeData readTime(Type paramType)
    throws IOException
  {
    if (paramType.typeCode == 92) {
      return new TimeData(readInt(), readInt(), 0);
    }
    return new TimeData(readInt(), readInt(), readInt());
  }
  
  protected TimestampData readDate(Type paramType)
    throws IOException
  {
    long l = readLong();
    return new TimestampData(l);
  }
  
  protected TimestampData readTimestamp(Type paramType)
    throws IOException
  {
    if (paramType.typeCode == 93) {
      return new TimestampData(readLong(), readInt());
    }
    return new TimestampData(readLong(), readInt(), readInt());
  }
  
  protected IntervalMonthData readYearMonthInterval(Type paramType)
    throws IOException
  {
    long l = readLong();
    return new IntervalMonthData(l, (IntervalType)paramType);
  }
  
  protected IntervalSecondData readDaySecondInterval(Type paramType)
    throws IOException
  {
    long l = readLong();
    int i = readInt();
    return new IntervalSecondData(l, i, (IntervalType)paramType);
  }
  
  protected Object readOther()
    throws IOException
  {
    return new JavaObjectData(readByteArray());
  }
  
  protected BinaryData readBit()
    throws IOException
  {
    int i = readInt();
    byte[] arrayOfByte = new byte[(i + 7) / 8];
    readFully(arrayOfByte);
    return BinaryData.getBitData(arrayOfByte, i);
  }
  
  protected BinaryData readBinary()
    throws IOException
  {
    return new BinaryData(readByteArray(), false);
  }
  
  protected ClobData readClob()
    throws IOException
  {
    long l = super.readLong();
    return new ClobDataID(l);
  }
  
  protected BlobData readBlob()
    throws IOException
  {
    long l = super.readLong();
    return new BlobDataID(l);
  }
  
  protected Object[] readArray(Type paramType)
    throws IOException
  {
    paramType = paramType.collectionBaseType();
    int i = readInt();
    Object[] arrayOfObject = new Object[i];
    for (int j = 0; j < i; j++) {
      arrayOfObject[j] = readData(paramType);
    }
    return arrayOfObject;
  }
  
  public int[] readIntArray()
    throws IOException
  {
    int i = readInt();
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++) {
      if (!readNull()) {
        arrayOfInt[j] = readInt();
      }
    }
    return arrayOfInt;
  }
  
  public Object[] readData(Type[] paramArrayOfType)
    throws IOException
  {
    return super.readData(paramArrayOfType);
  }
  
  public byte[] readByteArray()
    throws IOException
  {
    byte[] arrayOfByte = new byte[readInt()];
    readFully(arrayOfByte);
    return arrayOfByte;
  }
  
  public char[] readCharArray()
    throws IOException
  {
    char[] arrayOfChar = new char[readInt()];
    if (this.count - this.pos < arrayOfChar.length)
    {
      this.pos = this.count;
      throw new EOFException();
    }
    for (int i = 0; i < arrayOfChar.length; i++)
    {
      int j = this.buffer[(this.pos++)] & 0xFF;
      int k = this.buffer[(this.pos++)] & 0xFF;
      arrayOfChar[i] = ((char)((j << 8) + k));
    }
    return arrayOfChar;
  }
  
  public void resetRow(int paramInt)
  {
    if (this.out != null)
    {
      this.out.reset(paramInt);
      this.buffer = this.out.getBuffer();
    }
    super.reset();
  }
  
  public void resetRow(long paramLong, int paramInt)
    throws IOException
  {
    if (this.out != null)
    {
      this.out.reset(paramInt);
      this.buffer = this.out.getBuffer();
    }
    super.resetRow(paramLong, paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.rowio.RowInputBinary
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */