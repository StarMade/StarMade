package org.hsqldb.types;

import java.sql.Blob;
import org.hsqldb.Database;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCBlobClient;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.persist.LobManager;

public final class BlobType extends BinaryType
{
  public static final long maxBlobPrecision = 1099511627776L;
  public static final int defaultBlobSize = 16777216;

  public BlobType(long paramLong)
  {
    super(30, paramLong);
  }

  public int displaySize()
  {
    return this.precision > 2147483647L ? 2147483647 : (int)this.precision;
  }

  public int getJDBCTypeCode()
  {
    return 2004;
  }

  public Class getJDBCClass()
  {
    return Blob.class;
  }

  public String getJDBCClassName()
  {
    return "java.sql.Blob";
  }

  public String getNameString()
  {
    return "BLOB";
  }

  public String getFullNameString()
  {
    return "BINARY LARGE OBJECT";
  }

  public String getDefinition()
  {
    long l = this.precision;
    String str = null;
    if (this.precision % 1073741824L == 0L)
    {
      l = this.precision / 1073741824L;
      str = "G";
    }
    else if (this.precision % 1048576L == 0L)
    {
      l = this.precision / 1048576L;
      str = "M";
    }
    else if (this.precision % 1024L == 0L)
    {
      l = this.precision / 1024L;
      str = "K";
    }
    StringBuffer localStringBuffer = new StringBuffer(16);
    localStringBuffer.append(getNameString());
    localStringBuffer.append('(');
    localStringBuffer.append(l);
    if (str != null)
      localStringBuffer.append(str);
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }

  public boolean acceptsPrecision()
  {
    return true;
  }

  public boolean requiresPrecision()
  {
    return false;
  }

  public long getMaxPrecision()
  {
    return 1099511627776L;
  }

  public boolean isBinaryType()
  {
    return true;
  }

  public boolean isLobType()
  {
    return true;
  }

  public int compare(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2)
      return 0;
    if (paramObject1 == null)
      return -1;
    if (paramObject2 == null)
      return 1;
    if ((paramObject2 instanceof BinaryData))
      return paramSession.database.lobManager.compare((BlobData)paramObject1, ((BlobData)paramObject2).getBytes());
    return paramSession.database.lobManager.compare((BlobData)paramObject1, (BlobData)paramObject2);
  }

  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    return paramObject;
  }

  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null)
      return null;
    if (paramType.typeCode == 30)
      return paramObject;
    if ((paramType.typeCode == 60) || (paramType.typeCode == 61))
    {
      BlobData localBlobData = (BlobData)paramObject;
      BlobDataID localBlobDataID = paramSessionInterface.createBlob(localBlobData.length(paramSessionInterface));
      localBlobDataID.setBytes(paramSessionInterface, 0L, localBlobData.getBytes());
      return localBlobDataID;
    }
    throw Error.error(5561);
  }

  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null)
      return paramObject;
    if ((paramObject instanceof byte[]))
      return new BinaryData((byte[])paramObject, false);
    throw Error.error(5561);
  }

  public String convertToString(Object paramObject)
  {
    if (paramObject == null)
      return null;
    byte[] arrayOfByte = ((BlobData)paramObject).getBytes();
    return StringConverter.byteArrayToHexString(arrayOfByte);
  }

  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null)
      return "NULL";
    byte[] arrayOfByte = ((BlobData)paramObject).getBytes();
    return StringConverter.byteArrayToSQLHexString(arrayOfByte);
  }

  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof JDBCBlobClient))
      return ((JDBCBlobClient)paramObject).getBlob();
    throw Error.error(5561);
  }

  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof BlobDataID))
    {
      BlobDataID localBlobDataID = (BlobDataID)paramObject;
      return new JDBCBlobClient(paramSessionInterface, localBlobDataID);
    }
    throw Error.error(5561);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.BlobType
 * JD-Core Version:    0.6.2
 */