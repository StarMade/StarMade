package org.hsqldb.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class JDBCCallableStatement
  extends JDBCPreparedStatement
  implements CallableStatement
{
  private IntValueHashMap parameterNameMap = new IntValueHashMap();
  private boolean wasNullValue;
  
  public synchronized void registerOutParameter(int paramInt1, int paramInt2)
    throws SQLException
  {
    checkGetParameterIndex(paramInt1);
    if (this.parameterModes[(--paramInt1)] == 1) {
      throw Util.invalidArgument();
    }
  }
  
  public synchronized void registerOutParameter(int paramInt1, int paramInt2, int paramInt3)
    throws SQLException
  {
    registerOutParameter(paramInt1, paramInt2);
  }
  
  public synchronized boolean wasNull()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return this.wasNullValue;
  }
  
  public synchronized String getString(int paramInt)
    throws SQLException
  {
    return (String)getColumnInType(paramInt, Type.SQL_VARCHAR);
  }
  
  public synchronized boolean getBoolean(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_BOOLEAN);
    return localObject == null ? false : ((Boolean)localObject).booleanValue();
  }
  
  public synchronized byte getByte(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.TINYINT);
    return localObject == null ? 0 : ((Number)localObject).byteValue();
  }
  
  public synchronized short getShort(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_SMALLINT);
    return localObject == null ? 0 : ((Number)localObject).shortValue();
  }
  
  public synchronized int getInt(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_INTEGER);
    return localObject == null ? 0 : ((Number)localObject).intValue();
  }
  
  public synchronized long getLong(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_BIGINT);
    return localObject == null ? 0L : ((Number)localObject).longValue();
  }
  
  public synchronized float getFloat(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_DOUBLE);
    return localObject == null ? 0.0F : ((Number)localObject).floatValue();
  }
  
  public synchronized double getDouble(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_DOUBLE);
    return localObject == null ? 0.0D : ((Number)localObject).doubleValue();
  }
  
  /**
   * @deprecated
   */
  public synchronized BigDecimal getBigDecimal(int paramInt1, int paramInt2)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if (paramInt2 < 0) {
      throw Util.outOfRangeArgument();
    }
    BigDecimal localBigDecimal = getBigDecimal(paramInt1);
    if (localBigDecimal != null) {
      localBigDecimal = localBigDecimal.setScale(paramInt2, 1);
    }
    return localBigDecimal;
  }
  
  public synchronized byte[] getBytes(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_VARBINARY);
    if (localObject == null) {
      return null;
    }
    return ((BinaryData)localObject).getBytes();
  }
  
  public synchronized Date getDate(int paramInt)
    throws SQLException
  {
    TimestampData localTimestampData = (TimestampData)getColumnInType(paramInt, Type.SQL_DATE);
    if (localTimestampData == null) {
      return null;
    }
    return (Date)Type.SQL_DATE.convertSQLToJava(this.session, localTimestampData);
  }
  
  public synchronized Time getTime(int paramInt)
    throws SQLException
  {
    TimeData localTimeData = (TimeData)getColumnInType(paramInt, Type.SQL_TIME);
    if (localTimeData == null) {
      return null;
    }
    return (Time)Type.SQL_TIME.convertSQLToJava(this.session, localTimeData);
  }
  
  public synchronized Timestamp getTimestamp(int paramInt)
    throws SQLException
  {
    TimestampData localTimestampData = (TimestampData)getColumnInType(paramInt, Type.SQL_TIMESTAMP);
    if (localTimestampData == null) {
      return null;
    }
    return (Timestamp)Type.SQL_TIMESTAMP.convertSQLToJava(this.session, localTimestampData);
  }
  
  public synchronized Object getObject(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    Type localType = this.parameterTypes[(paramInt - 1)];
    switch (localType.typeCode)
    {
    case 50: 
      return getArray(paramInt);
    case 91: 
      return getDate(paramInt);
    case 92: 
    case 94: 
      return getTime(paramInt);
    case 93: 
    case 95: 
      return getTimestamp(paramInt);
    case 60: 
    case 61: 
      return getBytes(paramInt);
    case 14: 
      boolean bool = getBoolean(paramInt);
      return bool ? Boolean.TRUE : wasNull() ? null : Boolean.FALSE;
    case 40: 
      return getClob(paramInt);
    case 30: 
      return getBlob(paramInt);
    case 1111: 
    case 2000: 
      Object localObject = getColumnInType(paramInt, localType);
      if (localObject == null) {
        return null;
      }
      try
      {
        return ((JavaObjectData)localObject).getObject();
      }
      catch (HsqlException localHsqlException)
      {
        throw Util.sqlException(localHsqlException);
      }
    }
    return getColumnInType(paramInt, localType);
  }
  
  public synchronized BigDecimal getBigDecimal(int paramInt)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    Object localObject = this.parameterMetaData.columnTypes[(paramInt - 1)];
    switch (((Type)localObject).typeCode)
    {
    case 2: 
    case 3: 
      break;
    case -6: 
    case 4: 
    case 5: 
    case 25: 
      localObject = Type.SQL_DECIMAL;
      break;
    case 8: 
    default: 
      localObject = Type.SQL_DECIMAL_DEFAULT;
    }
    return (BigDecimal)getColumnInType(paramInt, (Type)localObject);
  }
  
  public Object getObject(int paramInt, Map<String, Class<?>> paramMap)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    throw Util.notSupported();
  }
  
  public Ref getRef(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    throw Util.notSupported();
  }
  
  public synchronized Blob getBlob(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    Type localType = this.parameterMetaData.columnTypes[(paramInt - 1)];
    Object localObject = getColumnInType(paramInt, localType);
    if (localObject == null) {
      return null;
    }
    if ((localObject instanceof BlobDataID)) {
      return new JDBCBlobClient(this.session, (BlobDataID)localObject);
    }
    throw Util.sqlException(5561);
  }
  
  public synchronized Clob getClob(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    Type localType = this.parameterMetaData.columnTypes[(paramInt - 1)];
    Object localObject = getColumnInType(paramInt, localType);
    if (localObject == null) {
      return null;
    }
    if ((localObject instanceof ClobDataID)) {
      return new JDBCClobClient(this.session, (ClobDataID)localObject);
    }
    throw Util.sqlException(5561);
  }
  
  public Array getArray(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    Type localType = this.parameterMetaData.columnTypes[(paramInt - 1)];
    if (!localType.isArrayType()) {
      throw Util.sqlException(5561);
    }
    Object[] arrayOfObject = (Object[])this.parameterValues[(paramInt - 1)];
    if (arrayOfObject == null) {
      return null;
    }
    return new JDBCArray(arrayOfObject, localType.collectionBaseType(), localType, this.connection);
  }
  
  public synchronized Date getDate(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
    TimestampData localTimestampData = (TimestampData)getColumnInType(paramInt, Type.SQL_DATE);
    if (localTimestampData == null) {
      return null;
    }
    long l = localTimestampData.getSeconds() * 1000L;
    if (paramCalendar != null) {
      l = HsqlDateTime.convertMillisToCalendar(paramCalendar, l);
    }
    return new Date(l);
  }
  
  public synchronized Time getTime(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
    TimeData localTimeData = (TimeData)getColumnInType(paramInt, Type.SQL_TIME);
    if (localTimeData == null) {
      return null;
    }
    long l = DateTimeType.normaliseTime(localTimeData.getSeconds()) * 1000;
    if (!this.parameterMetaData.columnTypes[(--paramInt)].isDateTimeTypeWithZone())
    {
      Calendar localCalendar = paramCalendar == null ? this.session.getCalendar() : paramCalendar;
      l = HsqlDateTime.convertMillisToCalendar(localCalendar, l);
      l = HsqlDateTime.getNormalisedTime(l);
    }
    return new Time(l);
  }
  
  public synchronized Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
    TimestampData localTimestampData = (TimestampData)getColumnInType(paramInt, Type.SQL_TIMESTAMP);
    if (localTimestampData == null) {
      return null;
    }
    long l = localTimestampData.getSeconds() * 1000L;
    if (!this.parameterMetaData.columnTypes[(--paramInt)].isDateTimeTypeWithZone())
    {
      localObject = paramCalendar == null ? this.session.getCalendar() : paramCalendar;
      if (paramCalendar != null) {
        l = HsqlDateTime.convertMillisToCalendar((Calendar)localObject, l);
      }
    }
    Object localObject = new Timestamp(l);
    ((Timestamp)localObject).setNanos(localTimestampData.getNanos());
    return localObject;
  }
  
  public synchronized void registerOutParameter(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
    registerOutParameter(paramInt1, paramInt2);
  }
  
  public synchronized void registerOutParameter(String paramString, int paramInt)
    throws SQLException
  {
    registerOutParameter(findParameterIndex(paramString), paramInt);
  }
  
  public synchronized void registerOutParameter(String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
    registerOutParameter(findParameterIndex(paramString), paramInt1);
  }
  
  public synchronized void registerOutParameter(String paramString1, int paramInt, String paramString2)
    throws SQLException
  {
    registerOutParameter(findParameterIndex(paramString1), paramInt);
  }
  
  public URL getURL(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    throw Util.notSupported();
  }
  
  public void setURL(String paramString, URL paramURL)
    throws SQLException
  {
    setURL(findParameterIndex(paramString), paramURL);
  }
  
  public synchronized void setNull(String paramString, int paramInt)
    throws SQLException
  {
    setNull(findParameterIndex(paramString), paramInt);
  }
  
  public synchronized void setBoolean(String paramString, boolean paramBoolean)
    throws SQLException
  {
    setBoolean(findParameterIndex(paramString), paramBoolean);
  }
  
  public synchronized void setByte(String paramString, byte paramByte)
    throws SQLException
  {
    setByte(findParameterIndex(paramString), paramByte);
  }
  
  public synchronized void setShort(String paramString, short paramShort)
    throws SQLException
  {
    setShort(findParameterIndex(paramString), paramShort);
  }
  
  public synchronized void setInt(String paramString, int paramInt)
    throws SQLException
  {
    setInt(findParameterIndex(paramString), paramInt);
  }
  
  public synchronized void setLong(String paramString, long paramLong)
    throws SQLException
  {
    setLong(findParameterIndex(paramString), paramLong);
  }
  
  public synchronized void setFloat(String paramString, float paramFloat)
    throws SQLException
  {
    setFloat(findParameterIndex(paramString), paramFloat);
  }
  
  public synchronized void setDouble(String paramString, double paramDouble)
    throws SQLException
  {
    setDouble(findParameterIndex(paramString), paramDouble);
  }
  
  public synchronized void setBigDecimal(String paramString, BigDecimal paramBigDecimal)
    throws SQLException
  {
    setBigDecimal(findParameterIndex(paramString), paramBigDecimal);
  }
  
  public synchronized void setString(String paramString1, String paramString2)
    throws SQLException
  {
    setString(findParameterIndex(paramString1), paramString2);
  }
  
  public synchronized void setBytes(String paramString, byte[] paramArrayOfByte)
    throws SQLException
  {
    setBytes(findParameterIndex(paramString), paramArrayOfByte);
  }
  
  public synchronized void setDate(String paramString, Date paramDate)
    throws SQLException
  {
    setDate(findParameterIndex(paramString), paramDate);
  }
  
  public synchronized void setTime(String paramString, Time paramTime)
    throws SQLException
  {
    setTime(findParameterIndex(paramString), paramTime);
  }
  
  public synchronized void setTimestamp(String paramString, Timestamp paramTimestamp)
    throws SQLException
  {
    setTimestamp(findParameterIndex(paramString), paramTimestamp);
  }
  
  public synchronized void setAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
    throws SQLException
  {
    setAsciiStream(findParameterIndex(paramString), paramInputStream, paramInt);
  }
  
  public synchronized void setBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
    throws SQLException
  {
    setBinaryStream(findParameterIndex(paramString), paramInputStream, paramInt);
  }
  
  public synchronized void setObject(String paramString, Object paramObject, int paramInt1, int paramInt2)
    throws SQLException
  {
    setObject(findParameterIndex(paramString), paramObject, paramInt1, paramInt2);
  }
  
  public synchronized void setObject(String paramString, Object paramObject, int paramInt)
    throws SQLException
  {
    setObject(findParameterIndex(paramString), paramObject, paramInt);
  }
  
  public synchronized void setObject(String paramString, Object paramObject)
    throws SQLException
  {
    setObject(findParameterIndex(paramString), paramObject);
  }
  
  public synchronized void setCharacterStream(String paramString, Reader paramReader, int paramInt)
    throws SQLException
  {
    setCharacterStream(findParameterIndex(paramString), paramReader, paramInt);
  }
  
  public synchronized void setDate(String paramString, Date paramDate, Calendar paramCalendar)
    throws SQLException
  {
    setDate(findParameterIndex(paramString), paramDate, paramCalendar);
  }
  
  public synchronized void setTime(String paramString, Time paramTime, Calendar paramCalendar)
    throws SQLException
  {
    setTime(findParameterIndex(paramString), paramTime, paramCalendar);
  }
  
  public synchronized void setTimestamp(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
    throws SQLException
  {
    setTimestamp(findParameterIndex(paramString), paramTimestamp, paramCalendar);
  }
  
  public synchronized void setNull(String paramString1, int paramInt, String paramString2)
    throws SQLException
  {
    setNull(findParameterIndex(paramString1), paramInt, paramString2);
  }
  
  public synchronized String getString(String paramString)
    throws SQLException
  {
    return getString(findParameterIndex(paramString));
  }
  
  public synchronized boolean getBoolean(String paramString)
    throws SQLException
  {
    return getBoolean(findParameterIndex(paramString));
  }
  
  public synchronized byte getByte(String paramString)
    throws SQLException
  {
    return getByte(findParameterIndex(paramString));
  }
  
  public synchronized short getShort(String paramString)
    throws SQLException
  {
    return getShort(findParameterIndex(paramString));
  }
  
  public synchronized int getInt(String paramString)
    throws SQLException
  {
    return getInt(findParameterIndex(paramString));
  }
  
  public synchronized long getLong(String paramString)
    throws SQLException
  {
    return getLong(findParameterIndex(paramString));
  }
  
  public synchronized float getFloat(String paramString)
    throws SQLException
  {
    return getFloat(findParameterIndex(paramString));
  }
  
  public synchronized double getDouble(String paramString)
    throws SQLException
  {
    return getDouble(findParameterIndex(paramString));
  }
  
  public synchronized byte[] getBytes(String paramString)
    throws SQLException
  {
    return getBytes(findParameterIndex(paramString));
  }
  
  public synchronized Date getDate(String paramString)
    throws SQLException
  {
    return getDate(findParameterIndex(paramString));
  }
  
  public synchronized Time getTime(String paramString)
    throws SQLException
  {
    return getTime(findParameterIndex(paramString));
  }
  
  public synchronized Timestamp getTimestamp(String paramString)
    throws SQLException
  {
    return getTimestamp(findParameterIndex(paramString));
  }
  
  public synchronized Object getObject(String paramString)
    throws SQLException
  {
    return getObject(findParameterIndex(paramString));
  }
  
  public synchronized BigDecimal getBigDecimal(String paramString)
    throws SQLException
  {
    return getBigDecimal(findParameterIndex(paramString));
  }
  
  public synchronized Object getObject(String paramString, Map paramMap)
    throws SQLException
  {
    return getObject(findParameterIndex(paramString), paramMap);
  }
  
  public synchronized Ref getRef(String paramString)
    throws SQLException
  {
    return getRef(findParameterIndex(paramString));
  }
  
  public synchronized Blob getBlob(String paramString)
    throws SQLException
  {
    return getBlob(findParameterIndex(paramString));
  }
  
  public synchronized Clob getClob(String paramString)
    throws SQLException
  {
    return getClob(findParameterIndex(paramString));
  }
  
  public synchronized Array getArray(String paramString)
    throws SQLException
  {
    return getArray(findParameterIndex(paramString));
  }
  
  public synchronized Date getDate(String paramString, Calendar paramCalendar)
    throws SQLException
  {
    return getDate(findParameterIndex(paramString), paramCalendar);
  }
  
  public synchronized Time getTime(String paramString, Calendar paramCalendar)
    throws SQLException
  {
    return getTime(findParameterIndex(paramString), paramCalendar);
  }
  
  public synchronized Timestamp getTimestamp(String paramString, Calendar paramCalendar)
    throws SQLException
  {
    return getTimestamp(findParameterIndex(paramString), paramCalendar);
  }
  
  public URL getURL(String paramString)
    throws SQLException
  {
    return getURL(findParameterIndex(paramString));
  }
  
  public RowId getRowId(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    throw Util.notSupported();
  }
  
  public synchronized RowId getRowId(String paramString)
    throws SQLException
  {
    return getRowId(findParameterIndex(paramString));
  }
  
  public synchronized void setRowId(String paramString, RowId paramRowId)
    throws SQLException
  {
    super.setRowId(findParameterIndex(paramString), paramRowId);
  }
  
  public synchronized void setNString(String paramString1, String paramString2)
    throws SQLException
  {
    super.setNString(findParameterIndex(paramString1), paramString2);
  }
  
  public synchronized void setNCharacterStream(String paramString, Reader paramReader, long paramLong)
    throws SQLException
  {
    super.setNCharacterStream(findParameterIndex(paramString), paramReader, paramLong);
  }
  
  public synchronized void setNClob(String paramString, NClob paramNClob)
    throws SQLException
  {
    super.setNClob(findParameterIndex(paramString), paramNClob);
  }
  
  public synchronized void setClob(String paramString, Reader paramReader, long paramLong)
    throws SQLException
  {
    super.setClob(findParameterIndex(paramString), paramReader, paramLong);
  }
  
  public synchronized void setBlob(String paramString, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    super.setBlob(findParameterIndex(paramString), paramInputStream, paramLong);
  }
  
  public synchronized void setNClob(String paramString, Reader paramReader, long paramLong)
    throws SQLException
  {
    super.setNClob(findParameterIndex(paramString), paramReader, paramLong);
  }
  
  public NClob getNClob(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    throw Util.notSupported();
  }
  
  public synchronized NClob getNClob(String paramString)
    throws SQLException
  {
    return getNClob(findParameterIndex(paramString));
  }
  
  public synchronized void setSQLXML(String paramString, SQLXML paramSQLXML)
    throws SQLException
  {
    super.setSQLXML(findParameterIndex(paramString), paramSQLXML);
  }
  
  public SQLXML getSQLXML(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    throw Util.notSupported();
  }
  
  public synchronized SQLXML getSQLXML(String paramString)
    throws SQLException
  {
    return getSQLXML(findParameterIndex(paramString));
  }
  
  public String getNString(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    throw Util.notSupported();
  }
  
  public synchronized String getNString(String paramString)
    throws SQLException
  {
    return getNString(findParameterIndex(paramString));
  }
  
  public Reader getNCharacterStream(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    throw Util.notSupported();
  }
  
  public synchronized Reader getNCharacterStream(String paramString)
    throws SQLException
  {
    return getNCharacterStream(findParameterIndex(paramString));
  }
  
  public Reader getCharacterStream(int paramInt)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    Type localType = this.parameterMetaData.columnTypes[(paramInt - 1)];
    Object localObject = getColumnInType(paramInt, localType);
    if (localObject == null) {
      return null;
    }
    if ((localObject instanceof ClobDataID)) {
      return ((ClobDataID)localObject).getCharacterStream(this.session);
    }
    if ((localObject instanceof Clob)) {
      return ((Clob)localObject).getCharacterStream();
    }
    if ((localObject instanceof String)) {
      return new StringReader((String)localObject);
    }
    throw Util.sqlException(5561);
  }
  
  public synchronized Reader getCharacterStream(String paramString)
    throws SQLException
  {
    return getCharacterStream(findParameterIndex(paramString));
  }
  
  public synchronized void setBlob(String paramString, Blob paramBlob)
    throws SQLException
  {
    super.setBlob(findParameterIndex(paramString), paramBlob);
  }
  
  public synchronized void setClob(String paramString, Clob paramClob)
    throws SQLException
  {
    super.setClob(findParameterIndex(paramString), paramClob);
  }
  
  public synchronized void setAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    if (paramLong > 2147483647L)
    {
      String str = "Maximum ASCII input octet length exceeded: " + paramLong;
      throw Util.sqlException(422, str);
    }
    setAsciiStream(paramString, paramInputStream, (int)paramLong);
  }
  
  public synchronized void setBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    if (paramLong > 2147483647L)
    {
      String str = "Maximum Binary input octet length exceeded: " + paramLong;
      throw Util.sqlException(422, str);
    }
    setBinaryStream(paramString, paramInputStream, (int)paramLong);
  }
  
  public synchronized void setCharacterStream(String paramString, Reader paramReader, long paramLong)
    throws SQLException
  {
    if (paramLong > 2147483647L)
    {
      String str = "Maximum character input length exceeded: " + paramLong;
      throw Util.sqlException(422, str);
    }
    setCharacterStream(paramString, paramReader, (int)paramLong);
  }
  
  public synchronized void setAsciiStream(String paramString, InputStream paramInputStream)
    throws SQLException
  {
    super.setAsciiStream(findParameterIndex(paramString), paramInputStream);
  }
  
  public synchronized void setBinaryStream(String paramString, InputStream paramInputStream)
    throws SQLException
  {
    super.setBinaryStream(findParameterIndex(paramString), paramInputStream);
  }
  
  public synchronized void setCharacterStream(String paramString, Reader paramReader)
    throws SQLException
  {
    super.setCharacterStream(findParameterIndex(paramString), paramReader);
  }
  
  public synchronized void setNCharacterStream(String paramString, Reader paramReader)
    throws SQLException
  {
    super.setNCharacterStream(findParameterIndex(paramString), paramReader);
  }
  
  public synchronized void setClob(String paramString, Reader paramReader)
    throws SQLException
  {
    super.setClob(findParameterIndex(paramString), paramReader);
  }
  
  public synchronized void setBlob(String paramString, InputStream paramInputStream)
    throws SQLException
  {
    super.setBlob(findParameterIndex(paramString), paramInputStream);
  }
  
  public synchronized void setNClob(String paramString, Reader paramReader)
    throws SQLException
  {
    super.setNClob(findParameterIndex(paramString), paramReader);
  }
  
  public <T> T getObject(int paramInt, Class<T> paramClass)
    throws SQLException
  {
    return getObject(paramInt);
  }
  
  public <T> T getObject(String paramString, Class<T> paramClass)
    throws SQLException
  {
    return getObject(findParameterIndex(paramString), paramClass);
  }
  
  public JDBCCallableStatement(JDBCConnection paramJDBCConnection, String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws HsqlException, SQLException
  {
    super(paramJDBCConnection, paramString, paramInt1, paramInt2, paramInt3, 2, null, null);
    if (this.parameterMetaData != null)
    {
      String[] arrayOfString = this.parameterMetaData.columnLabels;
      for (int i = 0; i < arrayOfString.length; i++)
      {
        String str = arrayOfString[i];
        if ((str != null) && (str.length() != 0)) {
          this.parameterNameMap.put(str, i);
        }
      }
    }
  }
  
  void fetchResult()
    throws SQLException
  {
    super.fetchResult();
    if (this.resultIn.getType() == 43)
    {
      Object[] arrayOfObject = this.resultIn.getParameterData();
      for (int i = 0; i < this.parameterValues.length; i++) {
        this.parameterValues[i] = arrayOfObject[i];
      }
    }
  }
  
  int findParameterIndex(String paramString)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    int i = this.parameterNameMap.get(paramString, -1);
    if (i >= 0) {
      return i + 1;
    }
    throw Util.sqlException(421, paramString);
  }
  
  public synchronized void close()
    throws SQLException
  {
    if (isClosed()) {
      return;
    }
    this.parameterNameMap = null;
    super.close();
  }
  
  private Object getColumnInType(int paramInt, Type paramType)
    throws SQLException
  {
    checkGetParameterIndex(paramInt);
    Type localType = this.parameterTypes[(--paramInt)];
    Object localObject = this.parameterValues[paramInt];
    if (trackNull(localObject)) {
      return null;
    }
    if (localType.typeCode != paramType.typeCode) {
      try
      {
        localObject = paramType.convertToTypeJDBC(this.session, localObject, localType);
      }
      catch (HsqlException localHsqlException1)
      {
        String str1 = "instance of " + localObject.getClass().getName();
        String str2 = "from SQL type " + localType.getNameString() + " to " + paramType.getJDBCClassName() + ", value: " + str1;
        HsqlException localHsqlException2 = Error.error(5561, str2);
        throw Util.sqlException(localHsqlException2, localHsqlException1);
      }
    }
    return localObject;
  }
  
  private boolean trackNull(Object paramObject)
  {
    return this.wasNullValue = paramObject == null ? 1 : 0;
  }
  
  public void closeOnCompletion()
    throws SQLException
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public boolean isCloseOnCompletion()
    throws SQLException
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public synchronized ResultSet executeQuery()
    throws SQLException
  {
    fetchResult();
    ResultSet localResultSet = getResultSet();
    if (localResultSet != null) {
      return localResultSet;
    }
    if (getMoreResults()) {
      return getResultSet();
    }
    throw Util.sqlException(1254);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCCallableStatement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */