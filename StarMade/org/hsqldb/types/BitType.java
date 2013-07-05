package org.hsqldb.types;

import org.hsqldb.Scanner;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.store.BitMap;

public final class BitType extends BinaryType
{
  static final long maxBitPrecision = 1024L;

  public BitType(int paramInt, long paramLong)
  {
    super(paramInt, paramLong);
  }

  public int displaySize()
  {
    return (int)this.precision;
  }

  public int getJDBCTypeCode()
  {
    return -7;
  }

  public Class getJDBCClass()
  {
    return [B.class;
  }

  public String getJDBCClassName()
  {
    return "[B";
  }

  public int getSQLGenericTypeCode()
  {
    return this.typeCode;
  }

  public String getNameString()
  {
    return this.typeCode == 14 ? "BIT" : "BIT VARYING";
  }

  public String getDefinition()
  {
    if (this.precision == 0L)
      return getNameString();
    StringBuffer localStringBuffer = new StringBuffer(16);
    localStringBuffer.append(getNameString());
    localStringBuffer.append('(');
    localStringBuffer.append(this.precision);
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }

  public boolean isBitType()
  {
    return true;
  }

  public long getMaxPrecision()
  {
    return 1024L;
  }

  public boolean requiresPrecision()
  {
    return this.typeCode == 15;
  }

  public Type getAggregateType(Type paramType)
  {
    if (paramType == null)
      return this;
    if (paramType == SQL_ALL_TYPES)
      return this;
    if (this.typeCode == paramType.typeCode)
      return this.precision >= paramType.precision ? this : paramType;
    switch (paramType.typeCode)
    {
    case 14:
      return this.precision >= paramType.precision ? this : getBitType(this.typeCode, paramType.precision);
    case 15:
      return paramType.precision >= this.precision ? paramType : getBitType(paramType.typeCode, this.precision);
    case 30:
    case 60:
    case 61:
      return paramType;
    }
    throw Error.error(5562);
  }

  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    if (paramInt != 36)
      return getAggregateType(paramType);
    long l = this.precision + paramType.precision;
    Object localObject;
    switch (paramType.typeCode)
    {
    case 0:
      return this;
    case 14:
      localObject = this;
      break;
    case 15:
      localObject = paramType;
      break;
    case 30:
    case 60:
    case 61:
      return paramType.getCombinedType(paramSession, this, paramInt);
    default:
      throw Error.error(5562);
    }
    if (l > 1024L)
    {
      if (this.typeCode == 14)
        throw Error.error(5570);
      l = 1024L;
    }
    return getBitType(((Type)localObject).typeCode, l);
  }

  public int compare(Session paramSession, Object paramObject1, Object paramObject2)
  {
    int i = super.compare(paramSession, paramObject1, paramObject2);
    if ((i == 0) && (paramObject1 != null))
    {
      if (((BinaryData)paramObject1).bitLength(null) == ((BinaryData)paramObject2).bitLength(null))
        return 0;
      return ((BinaryData)paramObject1).bitLength(null) > ((BinaryData)paramObject2).bitLength(null) ? 1 : -1;
    }
    return i;
  }

  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    return castOrConvertToType(null, paramObject, this, false);
  }

  public Object castToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, true);
  }

  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, false);
  }

  Object castOrConvertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType, boolean paramBoolean)
  {
    if (paramObject == null)
      return null;
    Object localObject;
    switch (paramType.typeCode)
    {
    case 1:
    case 12:
      localObject = paramSessionInterface.getScanner().convertToBit((String)paramObject);
      paramType = getBitType(15, ((BlobData)localObject).length(paramSessionInterface));
      break;
    case 14:
    case 15:
    case 30:
    case 60:
    case 61:
      localObject = (BlobData)paramObject;
      break;
    case 16:
      if (this.precision != 1L)
        throw Error.error(3471);
      if (((Boolean)paramObject).booleanValue())
        return BinaryData.singleBitOne;
      return BinaryData.singleBitZero;
    case -6:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 25:
      if (this.precision != 1L)
        throw Error.error(3471);
      if (((NumberType)paramType).compareToZero(paramObject) == 0)
        return BinaryData.singleBitZero;
      return BinaryData.singleBitOne;
    case -5:
    case -4:
    case -3:
    case -2:
    case -1:
    case 0:
    case 9:
    case 10:
    case 11:
    case 13:
    case 17:
    case 18:
    case 19:
    case 20:
    case 21:
    case 22:
    case 23:
    case 24:
    case 26:
    case 27:
    case 28:
    case 29:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 39:
    case 40:
    case 41:
    case 42:
    case 43:
    case 44:
    case 45:
    case 46:
    case 47:
    case 48:
    case 49:
    case 50:
    case 51:
    case 52:
    case 53:
    case 54:
    case 55:
    case 56:
    case 57:
    case 58:
    case 59:
    default:
      throw Error.error(3471);
    }
    if (((BlobData)localObject).bitLength(paramSessionInterface) > this.precision)
    {
      if (!paramBoolean)
        throw Error.error(3401);
      paramSessionInterface.addWarning(Error.error(1004));
    }
    int i = (int)((this.precision + 7L) / 8L);
    if (paramType.typeCode == 30)
    {
      arrayOfByte = ((BlobData)localObject).getBytes(paramSessionInterface, 0L, i);
      localObject = new BinaryData(arrayOfByte, this.precision);
    }
    switch (this.typeCode)
    {
    case 14:
      if (((BlobData)localObject).bitLength(paramSessionInterface) == this.precision)
        return localObject;
      if (((BlobData)localObject).length(paramSessionInterface) > i)
      {
        arrayOfByte = ((BlobData)localObject).getBytes(paramSessionInterface, 0L, i);
        localObject = new BinaryData(arrayOfByte, this.precision);
      }
      else if (((BlobData)localObject).length(paramSessionInterface) <= i)
      {
        arrayOfByte = (byte[])ArrayUtil.resizeArray(((BlobData)localObject).getBytes(), i);
        localObject = new BinaryData(arrayOfByte, this.precision);
      }
      break;
    case 15:
      if (((BlobData)localObject).bitLength(paramSessionInterface) <= this.precision)
        return localObject;
      if (((BlobData)localObject).length(paramSessionInterface) > i)
      {
        arrayOfByte = ((BlobData)localObject).getBytes(paramSessionInterface, 0L, i);
        localObject = new BinaryData(arrayOfByte, this.precision);
      }
      break;
    default:
      throw Error.error(3471);
    }
    byte[] arrayOfByte = ((BlobData)localObject).getBytes();
    for (int j = (int)this.precision; j < ((BlobData)localObject).length(paramSessionInterface) * 8L; j++)
      BitMap.unset(arrayOfByte, j);
    return localObject;
  }

  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null)
      return paramObject;
    if ((paramObject instanceof byte[]))
    {
      BinaryData localBinaryData = new BinaryData((byte[])paramObject, ((byte[])paramObject).length);
      return convertToTypeLimits(paramSessionInterface, localBinaryData);
    }
    if ((paramObject instanceof BinaryData))
      return convertToTypeLimits(paramSessionInterface, paramObject);
    if ((paramObject instanceof String))
      return convertToType(paramSessionInterface, paramObject, Type.SQL_VARCHAR);
    if ((paramObject instanceof Boolean))
      return convertToType(paramSessionInterface, paramObject, Type.SQL_BOOLEAN);
    if ((paramObject instanceof Integer))
      return convertToType(paramSessionInterface, paramObject, Type.SQL_INTEGER);
    if ((paramObject instanceof Long))
      return convertToType(paramSessionInterface, paramObject, Type.SQL_BIGINT);
    throw Error.error(3471);
  }

  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject)
  {
    return convertToDefaultType(paramSessionInterface, paramObject);
  }

  public String convertToString(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return StringConverter.byteArrayToBitString(((BinaryData)paramObject).getBytes(), (int)((BinaryData)paramObject).bitLength(null));
  }

  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null)
      return "NULL";
    return StringConverter.byteArrayToSQLBitString(((BinaryData)paramObject).getBytes(), (int)((BinaryData)paramObject).bitLength(null));
  }

  public boolean canConvertFrom(Type paramType)
  {
    return (paramType.typeCode == 0) || (paramType.isBinaryType()) || ((this.precision == 1L) && ((paramType.isIntegralType()) || (paramType.isBooleanType()))) || (paramType.isCharacterType());
  }

  public long position(SessionInterface paramSessionInterface, BlobData paramBlobData1, BlobData paramBlobData2, Type paramType, long paramLong)
  {
    if ((paramBlobData1 == null) || (paramBlobData2 == null))
      return -1L;
    long l = paramBlobData1.bitLength(paramSessionInterface);
    if (paramLong + l > paramBlobData1.bitLength(paramSessionInterface))
      return -1L;
    throw Error.runtimeError(201, "BitType");
  }

  public BlobData substring(SessionInterface paramSessionInterface, BlobData paramBlobData, long paramLong1, long paramLong2, boolean paramBoolean)
  {
    long l2 = paramBlobData.bitLength(paramSessionInterface);
    long l1;
    if (paramBoolean)
      l1 = paramLong1 + paramLong2;
    else
      l1 = l2 > paramLong1 ? l2 : paramLong1;
    if (l1 < paramLong1)
      throw Error.error(3431);
    if ((paramLong1 > l1) || (l1 < 0L))
    {
      paramLong1 = 0L;
      l1 = 0L;
    }
    if (paramLong1 < 0L)
      paramLong1 = 0L;
    if (l1 > l2)
      l1 = l2;
    paramLong2 = l1 - paramLong1;
    byte[] arrayOfByte1 = paramBlobData.getBytes();
    byte[] arrayOfByte2 = new byte[(int)(paramLong2 + 7L) / 8];
    for (int i = (int)paramLong1; i < l1; i++)
      if (BitMap.isSet(arrayOfByte1, i))
        BitMap.set(arrayOfByte2, i - (int)paramLong1);
    return new BinaryData(arrayOfByte2, paramLong2);
  }

  int getRightTrimSize(BinaryData paramBinaryData)
  {
    int i = (int)paramBinaryData.bitLength(null) - 1;
    byte[] arrayOfByte = paramBinaryData.getBytes();
    while ((i >= 0) && (!BitMap.isSet(arrayOfByte, i)))
      i--;
    return i + 1;
  }

  public BlobData overlay(Session paramSession, BlobData paramBlobData1, BlobData paramBlobData2, long paramLong1, long paramLong2, boolean paramBoolean)
  {
    if ((paramBlobData1 == null) || (paramBlobData2 == null))
      return null;
    if (!paramBoolean)
      paramLong2 = paramBlobData2.bitLength(paramSession);
    switch (this.typeCode)
    {
    case 14:
    case 15:
      byte[] arrayOfByte1 = (byte[])ArrayUtil.duplicateArray(paramBlobData1.getBytes());
      byte[] arrayOfByte2 = paramBlobData2.getBytes();
      int i = 0;
      int j = (int)paramLong1;
      while (i < paramLong2)
      {
        int k = 8;
        if (paramLong2 - j < 8L)
          k = (int)paramLong2 - j;
        BitMap.overlay(arrayOfByte1, j, arrayOfByte2[i], k);
        j += 8;
        i++;
      }
      BinaryData localBinaryData = new BinaryData(arrayOfByte1, paramBlobData1.bitLength(paramSession));
      return localBinaryData;
    }
    throw Error.runtimeError(201, "BitType");
  }

  public Object concat(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramObject2 == null))
      return null;
    long l = ((BlobData)paramObject1).bitLength(paramSession) + ((BlobData)paramObject2).bitLength(paramSession);
    if (l > 2147483647L)
      throw Error.error(1000);
    byte[] arrayOfByte1 = ((BlobData)paramObject1).getBytes();
    byte[] arrayOfByte2 = ((BlobData)paramObject2).getBytes();
    int i = (int)((BlobData)paramObject1).bitLength(paramSession);
    int j = (int)((BlobData)paramObject2).bitLength(paramSession);
    byte[] arrayOfByte3 = new byte[(int)(l + 7L) / 8];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
    for (int k = 0; k < j; k++)
      if (BitMap.isSet(arrayOfByte2, k))
        BitMap.set(arrayOfByte3, i + k);
    return new BinaryData(arrayOfByte3, l);
  }

  public static BinaryType getBitType(int paramInt, long paramLong)
  {
    switch (paramInt)
    {
    case 14:
    case 15:
      return new BitType(paramInt, paramLong);
    }
    throw Error.runtimeError(201, "BitType");
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.BitType
 * JD-Core Version:    0.6.2
 */