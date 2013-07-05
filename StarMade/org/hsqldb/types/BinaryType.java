package org.hsqldb.types;

import org.hsqldb.Scanner;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.StringConverter;

public class BinaryType extends Type
{
  static final long maxBinaryPrecision = 2147483647L;

  protected BinaryType(int paramInt, long paramLong)
  {
    super(61, paramInt, paramLong, 0);
  }

  public int displaySize()
  {
    return this.precision > 2147483647L ? 2147483647 : (int)this.precision;
  }

  public int getJDBCTypeCode()
  {
    return this.typeCode == 60 ? -2 : -3;
  }

  public Class getJDBCClass()
  {
    return [B.class;
  }

  public String getJDBCClassName()
  {
    return "[B";
  }

  public String getNameString()
  {
    return this.typeCode == 60 ? "BINARY" : "VARBINARY";
  }

  public String getNameFullString()
  {
    return this.typeCode == 60 ? "BINARY" : "BINARY VARYING";
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

  public boolean isBinaryType()
  {
    return true;
  }

  public boolean acceptsPrecision()
  {
    return true;
  }

  public long getMaxPrecision()
  {
    return 2147483647L;
  }

  public boolean requiresPrecision()
  {
    return this.typeCode == 61;
  }

  public int precedenceDegree(Type paramType)
  {
    if (paramType.typeCode == this.typeCode)
      return 0;
    if (!paramType.isBinaryType())
      return -2147483648;
    switch (this.typeCode)
    {
    case 14:
    case 15:
      return -2147483648;
    case 60:
      return paramType.typeCode == 30 ? 4 : 2;
    case 61:
      return paramType.typeCode == 30 ? 4 : 2;
    case 30:
      return paramType.typeCode == 60 ? -4 : -2;
    }
    throw Error.runtimeError(201, "BinaryType");
  }

  public Type getAggregateType(Type paramType)
  {
    if (paramType == null)
      return this;
    if (paramType == SQL_ALL_TYPES)
      return this;
    if (this.typeCode == paramType.typeCode)
      return this.precision >= paramType.precision ? this : paramType;
    if (paramType.isCharacterType())
      return paramType.getAggregateType(this);
    switch (paramType.typeCode)
    {
    case 14:
    case 15:
      long l = (paramType.precision + 7L) / 8L;
      return this.precision >= l ? this : getBinaryType(this.typeCode, l);
    case 60:
      return this.precision >= paramType.precision ? this : getBinaryType(this.typeCode, paramType.precision);
    case 61:
      if (this.typeCode == 30)
        return this.precision >= paramType.precision ? this : getBinaryType(this.typeCode, paramType.precision);
      return paramType.precision >= this.precision ? paramType : getBinaryType(paramType.typeCode, this.precision);
    case 30:
      return paramType.precision >= this.precision ? paramType : getBinaryType(paramType.typeCode, this.precision);
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
    case 15:
      l = this.precision + (paramType.precision + 7L) / 8L;
      localObject = this;
      break;
    case 60:
      localObject = this;
      break;
    case 61:
      localObject = this.typeCode == 30 ? this : paramType;
      break;
    case 30:
      localObject = paramType;
      break;
    default:
      throw Error.error(5561);
    }
    if (l > 2147483647L)
    {
      if (this.typeCode == 60)
        throw Error.error(5570);
      if (this.typeCode == 61)
        l = 2147483647L;
    }
    return getBinaryType(((Type)localObject).typeCode, l);
  }

  public int compare(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2)
      return 0;
    if (paramObject1 == null)
      return -1;
    if (paramObject2 == null)
      return 1;
    if (((paramObject1 instanceof BinaryData)) && ((paramObject2 instanceof BinaryData)))
    {
      byte[] arrayOfByte1 = ((BinaryData)paramObject1).getBytes();
      byte[] arrayOfByte2 = ((BinaryData)paramObject2).getBytes();
      int i = arrayOfByte1.length > arrayOfByte2.length ? arrayOfByte2.length : arrayOfByte1.length;
      for (int j = 0; j < i; j++)
        if (arrayOfByte1[j] != arrayOfByte2[j])
          return (arrayOfByte1[j] & 0xFF) > (arrayOfByte2[j] & 0xFF) ? 1 : -1;
      if (arrayOfByte1.length == arrayOfByte2.length)
        return 0;
      if (this.typeCode == 60)
      {
        if (arrayOfByte1.length > arrayOfByte2.length)
        {
          for (j = arrayOfByte2.length; j < arrayOfByte1.length; j++)
            if (arrayOfByte1[j] != 0)
              return 1;
          return 0;
        }
        for (j = arrayOfByte1.length; j < arrayOfByte2.length; j++)
          if (arrayOfByte2[j] != 0)
            return -1;
        return 0;
      }
      return arrayOfByte1.length > arrayOfByte2.length ? 1 : -1;
    }
    throw Error.runtimeError(201, "BinaryType");
  }

  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    return castOrConvertToType(paramSessionInterface, paramObject, this, false);
  }

  public Object castToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, true);
  }

  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, false);
  }

  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof byte[]))
      return new BinaryData((byte[])paramObject, true);
    throw Error.error(5561);
  }

  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null)
      return null;
    return ((BlobData)paramObject).getBytes();
  }

  Object castOrConvertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType, boolean paramBoolean)
  {
    if (paramObject == null)
      return null;
    Object localObject;
    switch (paramType.typeCode)
    {
    case 40:
      paramObject = Type.SQL_VARCHAR.convertToType(paramSessionInterface, paramObject, paramType);
    case 1:
    case 12:
      localObject = paramSessionInterface.getScanner().convertToBinary((String)paramObject);
      paramType = getBinaryType(61, ((BlobData)localObject).length(paramSessionInterface));
      break;
    case 14:
      localObject = (BlobData)paramObject;
      paramType = getBinaryType(61, ((BlobData)localObject).length(paramSessionInterface));
      break;
    case 30:
    case 60:
    case 61:
      localObject = (BlobData)paramObject;
      break;
    default:
      throw Error.error(3471);
    }
    if (this.precision == 0L)
      return localObject;
    if ((((BlobData)localObject).length(paramSessionInterface) > this.precision) && (((BlobData)localObject).nonZeroLength(paramSessionInterface) > this.precision))
    {
      if (!paramBoolean)
        throw Error.error(3401);
      paramSessionInterface.addWarning(Error.error(1004));
    }
    if (paramType.typeCode == 30)
    {
      long l = ((BlobData)localObject).length(paramSessionInterface);
      if (l > this.precision)
        throw Error.error(3471);
      byte[] arrayOfByte2 = ((BlobData)localObject).getBytes(paramSessionInterface, 0L, (int)l);
      localObject = new BinaryData(arrayOfByte2, false);
    }
    byte[] arrayOfByte1;
    switch (this.typeCode)
    {
    case 60:
      if (((BlobData)localObject).length(paramSessionInterface) > this.precision)
      {
        arrayOfByte1 = ((BlobData)localObject).getBytes(paramSessionInterface, 0L, (int)this.precision);
        localObject = new BinaryData(arrayOfByte1, false);
      }
      else if (((BlobData)localObject).length(paramSessionInterface) < this.precision)
      {
        arrayOfByte1 = (byte[])ArrayUtil.resizeArray(((BlobData)localObject).getBytes(), (int)this.precision);
        localObject = new BinaryData(arrayOfByte1, false);
      }
      return localObject;
    case 61:
      if (((BlobData)localObject).length(paramSessionInterface) > this.precision)
      {
        arrayOfByte1 = ((BlobData)localObject).getBytes(paramSessionInterface, 0L, (int)this.precision);
        localObject = new BinaryData(arrayOfByte1, false);
      }
      return localObject;
    }
    throw Error.error(3471);
  }

  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null)
      return paramObject;
    if ((paramObject instanceof byte[]))
      return new BinaryData((byte[])paramObject, false);
    if ((paramObject instanceof BinaryData))
      return paramObject;
    if ((paramObject instanceof String))
      return castOrConvertToType(paramSessionInterface, paramObject, Type.SQL_VARCHAR, false);
    throw Error.error(3471);
  }

  public String convertToString(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return StringConverter.byteArrayToHexString(((BlobData)paramObject).getBytes());
  }

  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null)
      return "NULL";
    return StringConverter.byteArrayToSQLHexString(((BinaryData)paramObject).getBytes());
  }

  public boolean canConvertFrom(Type paramType)
  {
    return (paramType.typeCode == 0) || (paramType.isBinaryType()) || (paramType.isCharacterType());
  }

  public int canMoveFrom(Type paramType)
  {
    if (paramType == this)
      return 0;
    if (!paramType.isBinaryType())
      return -1;
    switch (this.typeCode)
    {
    case 61:
      if (paramType.typeCode == this.typeCode)
        return this.precision >= paramType.precision ? 0 : 1;
      if (paramType.typeCode == 60)
        return this.precision >= paramType.precision ? 0 : -1;
      return -1;
    case 30:
      if (paramType.typeCode == this.typeCode)
        return this.precision >= paramType.precision ? 0 : 1;
      return -1;
    case 14:
    case 60:
      return (paramType.typeCode == this.typeCode) && (this.precision == paramType.precision) ? 0 : -1;
    case 15:
      return (paramType.typeCode == this.typeCode) && (this.precision >= paramType.precision) ? 0 : -1;
    }
    return -1;
  }

  public long position(SessionInterface paramSessionInterface, BlobData paramBlobData1, BlobData paramBlobData2, Type paramType, long paramLong)
  {
    if ((paramBlobData1 == null) || (paramBlobData2 == null))
      return -1L;
    long l = paramBlobData1.length(paramSessionInterface);
    if (paramLong + l > paramBlobData1.length(paramSessionInterface))
      return -1L;
    return paramBlobData1.position(paramSessionInterface, paramBlobData2, paramLong);
  }

  public BlobData substring(SessionInterface paramSessionInterface, BlobData paramBlobData, long paramLong1, long paramLong2, boolean paramBoolean)
  {
    long l2 = paramBlobData.length(paramSessionInterface);
    long l1;
    if (paramBoolean)
      l1 = paramLong1 + paramLong2;
    else
      l1 = l2 > paramLong1 ? l2 : paramLong1;
    if (paramLong1 > l1)
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
    byte[] arrayOfByte = paramBlobData.getBytes(paramSessionInterface, paramLong1, (int)paramLong2);
    return new BinaryData(arrayOfByte, false);
  }

  int getRightTrimSize(BlobData paramBlobData)
  {
    byte[] arrayOfByte = paramBlobData.getBytes();
    int i = arrayOfByte.length;
    i--;
    while ((i >= 0) && (arrayOfByte[i] == 0))
      i--;
    i++;
    return i;
  }

  public BlobData trim(Session paramSession, BlobData paramBlobData, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBlobData == null)
      return null;
    long l = paramBlobData.length(paramSession);
    if (l > 2147483647L)
      throw Error.error(3460);
    byte[] arrayOfByte1 = paramBlobData.getBytes(paramSession, 0L, (int)l);
    int i = arrayOfByte1.length;
    if (paramBoolean2)
    {
      i--;
      while ((i >= 0) && (arrayOfByte1[i] == paramInt))
        i--;
      i++;
    }
    int j = 0;
    if (paramBoolean1)
      while ((j < i) && (arrayOfByte1[j] == paramInt))
        j++;
    byte[] arrayOfByte2 = arrayOfByte1;
    if ((j != 0) || (i != arrayOfByte1.length))
    {
      arrayOfByte2 = new byte[i - j];
      System.arraycopy(arrayOfByte1, j, arrayOfByte2, 0, i - j);
    }
    if (this.typeCode == 30)
    {
      BlobDataID localBlobDataID = paramSession.createBlob(arrayOfByte2.length);
      localBlobDataID.setBytes(paramSession, 0L, arrayOfByte2);
      return localBlobDataID;
    }
    return new BinaryData(arrayOfByte2, arrayOfByte2 == arrayOfByte1);
  }

  public BlobData overlay(Session paramSession, BlobData paramBlobData1, BlobData paramBlobData2, long paramLong1, long paramLong2, boolean paramBoolean)
  {
    if ((paramBlobData1 == null) || (paramBlobData2 == null))
      return null;
    if (!paramBoolean)
      paramLong2 = paramBlobData2.length(paramSession);
    Object localObject;
    switch (this.typeCode)
    {
    case 60:
    case 61:
      localObject = new BinaryData(paramSession, substring(paramSession, paramBlobData1, 0L, paramLong1, true), paramBlobData2);
      localObject = new BinaryData(paramSession, (BlobData)localObject, substring(paramSession, paramBlobData1, paramLong1 + paramLong2, 0L, false));
      return localObject;
    case 30:
      localObject = substring(paramSession, paramBlobData1, 0L, paramLong1, false).getBytes();
      long l = paramBlobData1.length(paramSession) + paramBlobData2.length(paramSession) - paramLong2;
      BlobDataID localBlobDataID = paramSession.createBlob(l);
      localBlobDataID.setBytes(paramSession, 0L, (byte[])localObject);
      localBlobDataID.setBytes(paramSession, localBlobDataID.length(paramSession), paramBlobData2.getBytes());
      localObject = substring(paramSession, paramBlobData1, paramLong1 + paramLong2, 0L, false).getBytes();
      localBlobDataID.setBytes(paramSession, localBlobDataID.length(paramSession), (byte[])localObject);
      return localBlobDataID;
    }
    throw Error.runtimeError(201, "BinaryType");
  }

  public Object concat(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramObject2 == null))
      return null;
    long l = ((BlobData)paramObject1).length(paramSession) + ((BlobData)paramObject2).length(paramSession);
    if (l > this.precision)
      throw Error.error(3401);
    if (this.typeCode == 30)
    {
      BlobDataID localBlobDataID = paramSession.createBlob(l);
      localBlobDataID.setBytes(paramSession, 0L, (BlobData)paramObject1, 0L, ((BlobData)paramObject1).length(paramSession));
      localBlobDataID.setBytes(paramSession, ((BlobData)paramObject1).length(paramSession), (BlobData)paramObject2, 0L, ((BlobData)paramObject2).length(paramSession));
      return localBlobDataID;
    }
    return new BinaryData(paramSession, (BlobData)paramObject1, (BlobData)paramObject2);
  }

  public static BinaryType getBinaryType(int paramInt, long paramLong)
  {
    switch (paramInt)
    {
    case 60:
    case 61:
      return new BinaryType(paramInt, paramLong);
    case 30:
      return new BlobType(paramLong);
    }
    throw Error.runtimeError(201, "BinaryType");
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.BinaryType
 * JD-Core Version:    0.6.2
 */