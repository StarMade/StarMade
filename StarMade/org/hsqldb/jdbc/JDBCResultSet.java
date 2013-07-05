package org.hsqldb.jdbc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import org.hsqldb.ColumnBase;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.StringInputStream;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class JDBCResultSet
  implements ResultSet
{
  private RowSetNavigator navigator;
  protected ResultMetaData resultMetaData;
  private boolean translateTTIType;
  private int columnCount;
  private boolean wasNullValue;
  private ResultSetMetaData resultSetMetaData;
  private IntValueHashMap columnMap;
  private SQLWarning rootWarning;
  JDBCStatementBase statement;
  SessionInterface session;
  JDBCConnection connection;
  boolean isScrollable;
  boolean isReadOnly;
  boolean isUpdatable;
  boolean isInsertable;
  int rsProperties;
  int fetchSize;
  boolean autoClose;
  public Result result;
  public static final int FETCH_FORWARD = 1000;
  public static final int FETCH_REVERSE = 1001;
  public static final int FETCH_UNKNOWN = 1002;
  public static final int TYPE_FORWARD_ONLY = 1003;
  public static final int TYPE_SCROLL_INSENSITIVE = 1004;
  public static final int TYPE_SCROLL_SENSITIVE = 1005;
  public static final int CONCUR_READ_ONLY = 1007;
  public static final int CONCUR_UPDATABLE = 1008;
  public static final int HOLD_CURSORS_OVER_COMMIT = 1;
  public static final int CLOSE_CURSORS_AT_COMMIT = 2;
  JDBCPreparedStatement preparedStatement;
  boolean isRowUpdated;
  boolean isOnInsertRow;
  int currentUpdateRowNumber;

  public boolean next()
    throws SQLException
  {
    checkClosed();
    this.rootWarning = null;
    return this.navigator.next();
  }

  public void close()
    throws SQLException
  {
    if (this.navigator == null)
      return;
    if (ResultProperties.isHeld(this.rsProperties))
      this.session.closeNavigator(this.navigator.getId());
    else
      this.navigator.release();
    this.navigator = null;
    if ((this.autoClose) && (this.statement != null))
      this.statement.close();
  }

  public boolean wasNull()
    throws SQLException
  {
    checkClosed();
    return this.wasNullValue;
  }

  public String getString(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
    if (localType.typeCode == 40)
    {
      ClobDataID localClobDataID = (ClobDataID)getColumnInType(paramInt, localType);
      if (localClobDataID == null)
        return null;
      long l = localClobDataID.length(this.session);
      if (l > 2147483647L)
        Util.throwError(Error.error(5561));
      return localClobDataID.getSubString(this.session, 0L, (int)l);
    }
    return (String)getColumnInType(paramInt, Type.SQL_VARCHAR);
  }

  public boolean getBoolean(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_BOOLEAN);
    return localObject == null ? false : ((Boolean)localObject).booleanValue();
  }

  public byte getByte(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.TINYINT);
    return localObject == null ? 0 : ((Number)localObject).byteValue();
  }

  public short getShort(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_SMALLINT);
    return localObject == null ? 0 : ((Number)localObject).shortValue();
  }

  public int getInt(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_INTEGER);
    return localObject == null ? 0 : ((Number)localObject).intValue();
  }

  public long getLong(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_BIGINT);
    return localObject == null ? 0L : ((Number)localObject).longValue();
  }

  public float getFloat(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_DOUBLE);
    return localObject == null ? 0.0F : ((Number)localObject).floatValue();
  }

  public double getDouble(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_DOUBLE);
    return localObject == null ? 0.0D : ((Number)localObject).doubleValue();
  }

  /** @deprecated */
  public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
    throws SQLException
  {
    if (paramInt2 < 0)
      throw Util.outOfRangeArgument();
    BigDecimal localBigDecimal = getBigDecimal(paramInt1);
    if (localBigDecimal != null)
      localBigDecimal = localBigDecimal.setScale(paramInt2, 1);
    return localBigDecimal;
  }

  public byte[] getBytes(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
    if (localType.typeCode == 30)
    {
      localObject = (BlobDataID)getColumnInType(paramInt, localType);
      if (localObject == null)
        return null;
      long l = ((BlobDataID)localObject).length(this.session);
      if (l > 2147483647L)
        Util.throwError(Error.error(5561));
      return ((BlobDataID)localObject).getBytes(this.session, 0L, (int)l);
    }
    Object localObject = getColumnInType(paramInt, Type.SQL_VARBINARY);
    if (localObject == null)
      return null;
    return ((BinaryData)localObject).getBytes();
  }

  public Date getDate(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_DATE);
    if (localObject == null)
      return null;
    return (Date)Type.SQL_DATE.convertSQLToJava(this.session, localObject);
  }

  public Time getTime(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_TIME);
    if (localObject == null)
      return null;
    return (Time)Type.SQL_TIME.convertSQLToJava(this.session, localObject);
  }

  public Timestamp getTimestamp(int paramInt)
    throws SQLException
  {
    Object localObject = getColumnInType(paramInt, Type.SQL_TIMESTAMP);
    if (localObject == null)
      return null;
    return (Timestamp)Type.SQL_TIMESTAMP.convertSQLToJava(this.session, localObject);
  }

  public InputStream getAsciiStream(int paramInt)
    throws SQLException
  {
    String str = getString(paramInt);
    if (str == null)
      return null;
    try
    {
      return new ByteArrayInputStream(str.getBytes("US-ASCII"));
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  /** @deprecated */
  public InputStream getUnicodeStream(int paramInt)
    throws SQLException
  {
    String str = getString(paramInt);
    if (str == null)
      return null;
    return new StringInputStream(str);
  }

  public InputStream getBinaryStream(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
    Object localObject = getColumnInType(paramInt, localType);
    if (localObject == null)
      return null;
    if ((localObject instanceof BlobDataID))
      return ((BlobDataID)localObject).getBinaryStream(this.session);
    if ((localObject instanceof Blob))
      return ((Blob)localObject).getBinaryStream();
    if ((localObject instanceof BinaryData))
    {
      byte[] arrayOfByte = getBytes(paramInt);
      return new ByteArrayInputStream(arrayOfByte);
    }
    throw Util.sqlException(5561);
  }

  public String getString(String paramString)
    throws SQLException
  {
    return getString(findColumn(paramString));
  }

  public boolean getBoolean(String paramString)
    throws SQLException
  {
    return getBoolean(findColumn(paramString));
  }

  public byte getByte(String paramString)
    throws SQLException
  {
    return getByte(findColumn(paramString));
  }

  public short getShort(String paramString)
    throws SQLException
  {
    return getShort(findColumn(paramString));
  }

  public int getInt(String paramString)
    throws SQLException
  {
    return getInt(findColumn(paramString));
  }

  public long getLong(String paramString)
    throws SQLException
  {
    return getLong(findColumn(paramString));
  }

  public float getFloat(String paramString)
    throws SQLException
  {
    return getFloat(findColumn(paramString));
  }

  public double getDouble(String paramString)
    throws SQLException
  {
    return getDouble(findColumn(paramString));
  }

  /** @deprecated */
  public BigDecimal getBigDecimal(String paramString, int paramInt)
    throws SQLException
  {
    return getBigDecimal(findColumn(paramString), paramInt);
  }

  public byte[] getBytes(String paramString)
    throws SQLException
  {
    return getBytes(findColumn(paramString));
  }

  public Date getDate(String paramString)
    throws SQLException
  {
    return getDate(findColumn(paramString));
  }

  public Time getTime(String paramString)
    throws SQLException
  {
    return getTime(findColumn(paramString));
  }

  public Timestamp getTimestamp(String paramString)
    throws SQLException
  {
    return getTimestamp(findColumn(paramString));
  }

  public InputStream getAsciiStream(String paramString)
    throws SQLException
  {
    return getAsciiStream(findColumn(paramString));
  }

  /** @deprecated */
  public InputStream getUnicodeStream(String paramString)
    throws SQLException
  {
    return getUnicodeStream(findColumn(paramString));
  }

  public InputStream getBinaryStream(String paramString)
    throws SQLException
  {
    return getBinaryStream(findColumn(paramString));
  }

  public SQLWarning getWarnings()
    throws SQLException
  {
    checkClosed();
    return this.rootWarning;
  }

  public void clearWarnings()
    throws SQLException
  {
    checkClosed();
    this.rootWarning = null;
  }

  public String getCursorName()
    throws SQLException
  {
    checkClosed();
    if (this.result == null)
      return "";
    return this.result.getMainString();
  }

  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    checkClosed();
    if (this.resultSetMetaData == null)
      this.resultSetMetaData = new JDBCResultSetMetaData(this.resultMetaData, this.isUpdatable, this.isInsertable, this.connection);
    return this.resultSetMetaData;
  }

  public Object getObject(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
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
      if (localObject == null)
        return null;
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

  public Object getObject(String paramString)
    throws SQLException
  {
    return getObject(findColumn(paramString));
  }

  public int findColumn(String paramString)
    throws SQLException
  {
    checkClosed();
    if (paramString == null)
      throw Util.nullArgument();
    if (this.columnMap != null)
    {
      i = this.columnMap.get(paramString, -1);
      if (i != -1)
        return i;
    }
    String[] arrayOfString = this.resultMetaData.columnLabels;
    int i = -1;
    for (int j = 0; j < this.columnCount; j++)
      if (paramString.equalsIgnoreCase(arrayOfString[j]))
      {
        i = j;
        break;
      }
    ColumnBase[] arrayOfColumnBase = this.resultMetaData.columns;
    int k;
    if (i < 0)
      for (k = 0; k < this.columnCount; k++)
        if (paramString.equalsIgnoreCase(arrayOfColumnBase[k].getNameString()))
        {
          i = k;
          break;
        }
    if (i < 0)
    {
      k = paramString.indexOf(46);
      if (k < 0)
        throw Util.sqlException(421, paramString);
      for (int m = 0; m < this.columnCount; m++)
      {
        String str1 = arrayOfColumnBase[m].getTableNameString();
        if ((str1 != null) && (str1.length() != 0))
        {
          String str2 = arrayOfColumnBase[m].getNameString();
          if (paramString.equalsIgnoreCase(str1 + '.' + str2))
          {
            i = m;
            break;
          }
          String str3 = arrayOfColumnBase[m].getSchemaNameString();
          if ((str3 != null) && (str3.length() != 0))
          {
            String str4 = str3 + '.' + str1 + '.' + str2;
            if (paramString.equalsIgnoreCase(str4))
            {
              i = m;
              break;
            }
          }
        }
      }
    }
    if (i < 0)
      throw Util.sqlException(421, paramString);
    i++;
    if (this.columnMap == null)
      this.columnMap = new IntValueHashMap();
    this.columnMap.put(paramString, i);
    return i;
  }

  public Reader getCharacterStream(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
    Object localObject = getColumnInType(paramInt, localType);
    if (localObject == null)
      return null;
    if ((localObject instanceof ClobDataID))
      return ((ClobDataID)localObject).getCharacterStream(this.session);
    if ((localObject instanceof Clob))
      return ((Clob)localObject).getCharacterStream();
    if ((localObject instanceof String))
      return new StringReader((String)localObject);
    throw Util.sqlException(5561);
  }

  public Reader getCharacterStream(String paramString)
    throws SQLException
  {
    return getCharacterStream(findColumn(paramString));
  }

  public BigDecimal getBigDecimal(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Object localObject = this.resultMetaData.columnTypes[(paramInt - 1)];
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

  public BigDecimal getBigDecimal(String paramString)
    throws SQLException
  {
    return getBigDecimal(findColumn(paramString));
  }

  public boolean isBeforeFirst()
    throws SQLException
  {
    checkClosed();
    if (this.isOnInsertRow)
      return false;
    return this.navigator.isBeforeFirst();
  }

  public boolean isAfterLast()
    throws SQLException
  {
    checkClosed();
    if (this.isOnInsertRow)
      return false;
    return this.navigator.isAfterLast();
  }

  public boolean isFirst()
    throws SQLException
  {
    checkClosed();
    if (this.isOnInsertRow)
      return false;
    return this.navigator.isFirst();
  }

  public boolean isLast()
    throws SQLException
  {
    checkClosed();
    if (this.isOnInsertRow)
      return false;
    return this.navigator.isLast();
  }

  public void beforeFirst()
    throws SQLException
  {
    checkClosed();
    checkNotForwardOnly();
    if ((this.isOnInsertRow) || (this.isRowUpdated))
      throw Util.sqlExceptionSQL(3604);
    this.navigator.beforeFirst();
  }

  public void afterLast()
    throws SQLException
  {
    checkClosed();
    checkNotForwardOnly();
    if ((this.isOnInsertRow) || (this.isRowUpdated))
      throw Util.sqlExceptionSQL(3604);
    this.navigator.afterLast();
  }

  public boolean first()
    throws SQLException
  {
    checkClosed();
    checkNotForwardOnly();
    if ((this.isOnInsertRow) || (this.isRowUpdated))
      throw Util.sqlExceptionSQL(3604);
    return this.navigator.first();
  }

  public boolean last()
    throws SQLException
  {
    checkClosed();
    checkNotForwardOnly();
    if ((this.isOnInsertRow) || (this.isRowUpdated))
      throw Util.sqlExceptionSQL(3604);
    return this.navigator.last();
  }

  public int getRow()
    throws SQLException
  {
    checkClosed();
    if (this.navigator.isAfterLast())
      return 0;
    return this.navigator.getRowNumber() + 1;
  }

  public boolean absolute(int paramInt)
    throws SQLException
  {
    checkClosed();
    checkNotForwardOnly();
    if ((this.isOnInsertRow) || (this.isRowUpdated))
      throw Util.sqlExceptionSQL(3604);
    if (paramInt > 0)
      paramInt--;
    else if (paramInt == 0)
      return this.navigator.beforeFirst();
    return this.navigator.absolute(paramInt);
  }

  public boolean relative(int paramInt)
    throws SQLException
  {
    checkClosed();
    checkNotForwardOnly();
    if ((this.isOnInsertRow) || (this.isRowUpdated))
      throw Util.sqlExceptionSQL(3604);
    return this.navigator.relative(paramInt);
  }

  public boolean previous()
    throws SQLException
  {
    checkClosed();
    checkNotForwardOnly();
    if ((this.isOnInsertRow) || (this.isRowUpdated))
      throw Util.sqlExceptionSQL(3604);
    this.rootWarning = null;
    return this.navigator.previous();
  }

  public void setFetchDirection(int paramInt)
    throws SQLException
  {
    checkClosed();
    switch (paramInt)
    {
    case 1000:
      break;
    case 1001:
      checkNotForwardOnly();
      break;
    case 1002:
      checkNotForwardOnly();
      break;
    default:
      throw Util.notSupported();
    }
  }

  public int getFetchDirection()
    throws SQLException
  {
    checkClosed();
    return 1000;
  }

  public void setFetchSize(int paramInt)
    throws SQLException
  {
    if (paramInt < 0)
      throw Util.outOfRangeArgument();
  }

  public int getFetchSize()
    throws SQLException
  {
    checkClosed();
    return this.fetchSize;
  }

  public int getType()
    throws SQLException
  {
    checkClosed();
    return ResultProperties.getJDBCScrollability(this.rsProperties);
  }

  public int getConcurrency()
    throws SQLException
  {
    checkClosed();
    return ResultProperties.getJDBCConcurrency(this.rsProperties);
  }

  public boolean rowUpdated()
    throws SQLException
  {
    checkClosed();
    return this.isRowUpdated;
  }

  public boolean rowInserted()
    throws SQLException
  {
    checkClosed();
    return false;
  }

  public boolean rowDeleted()
    throws SQLException
  {
    checkClosed();
    return false;
  }

  public void updateNull(int paramInt)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, null);
  }

  public void updateBoolean(int paramInt, boolean paramBoolean)
    throws SQLException
  {
    Boolean localBoolean = paramBoolean ? Boolean.TRUE : Boolean.FALSE;
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, localBoolean);
  }

  public void updateByte(int paramInt, byte paramByte)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setIntParameter(paramInt, paramByte);
  }

  public void updateShort(int paramInt, short paramShort)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setIntParameter(paramInt, paramShort);
  }

  public void updateInt(int paramInt1, int paramInt2)
    throws SQLException
  {
    startUpdate(paramInt1);
    this.preparedStatement.setIntParameter(paramInt1, paramInt2);
  }

  public void updateLong(int paramInt, long paramLong)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setLongParameter(paramInt, paramLong);
  }

  public void updateFloat(int paramInt, float paramFloat)
    throws SQLException
  {
    Double localDouble = new Double(paramFloat);
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, localDouble);
  }

  public void updateDouble(int paramInt, double paramDouble)
    throws SQLException
  {
    Double localDouble = new Double(paramDouble);
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, localDouble);
  }

  public void updateBigDecimal(int paramInt, BigDecimal paramBigDecimal)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramBigDecimal);
  }

  public void updateString(int paramInt, String paramString)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramString);
  }

  public void updateBytes(int paramInt, byte[] paramArrayOfByte)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramArrayOfByte);
  }

  public void updateDate(int paramInt, Date paramDate)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramDate);
  }

  public void updateTime(int paramInt, Time paramTime)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramTime);
  }

  public void updateTimestamp(int paramInt, Timestamp paramTimestamp)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramTimestamp);
  }

  public void updateAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
    throws SQLException
  {
    startUpdate(paramInt1);
    this.preparedStatement.setAsciiStream(paramInt1, paramInputStream, paramInt2);
  }

  public void updateBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
    throws SQLException
  {
    startUpdate(paramInt1);
    this.preparedStatement.setBinaryStream(paramInt1, paramInputStream, paramInt2);
  }

  public void updateCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
    throws SQLException
  {
    startUpdate(paramInt1);
    this.preparedStatement.setCharacterStream(paramInt1, paramReader, paramInt2);
  }

  public void updateObject(int paramInt1, Object paramObject, int paramInt2)
    throws SQLException
  {
    startUpdate(paramInt1);
    this.preparedStatement.setObject(paramInt1, paramObject, 0, paramInt2);
  }

  public void updateObject(int paramInt, Object paramObject)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramObject);
  }

  public void updateNull(String paramString)
    throws SQLException
  {
    updateNull(findColumn(paramString));
  }

  public void updateBoolean(String paramString, boolean paramBoolean)
    throws SQLException
  {
    updateBoolean(findColumn(paramString), paramBoolean);
  }

  public void updateByte(String paramString, byte paramByte)
    throws SQLException
  {
    updateByte(findColumn(paramString), paramByte);
  }

  public void updateShort(String paramString, short paramShort)
    throws SQLException
  {
    updateShort(findColumn(paramString), paramShort);
  }

  public void updateInt(String paramString, int paramInt)
    throws SQLException
  {
    updateInt(findColumn(paramString), paramInt);
  }

  public void updateLong(String paramString, long paramLong)
    throws SQLException
  {
    updateLong(findColumn(paramString), paramLong);
  }

  public void updateFloat(String paramString, float paramFloat)
    throws SQLException
  {
    updateFloat(findColumn(paramString), paramFloat);
  }

  public void updateDouble(String paramString, double paramDouble)
    throws SQLException
  {
    updateDouble(findColumn(paramString), paramDouble);
  }

  public void updateBigDecimal(String paramString, BigDecimal paramBigDecimal)
    throws SQLException
  {
    updateBigDecimal(findColumn(paramString), paramBigDecimal);
  }

  public void updateString(String paramString1, String paramString2)
    throws SQLException
  {
    updateString(findColumn(paramString1), paramString2);
  }

  public void updateBytes(String paramString, byte[] paramArrayOfByte)
    throws SQLException
  {
    updateBytes(findColumn(paramString), paramArrayOfByte);
  }

  public void updateDate(String paramString, Date paramDate)
    throws SQLException
  {
    updateDate(findColumn(paramString), paramDate);
  }

  public void updateTime(String paramString, Time paramTime)
    throws SQLException
  {
    updateTime(findColumn(paramString), paramTime);
  }

  public void updateTimestamp(String paramString, Timestamp paramTimestamp)
    throws SQLException
  {
    updateTimestamp(findColumn(paramString), paramTimestamp);
  }

  public void updateAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
    throws SQLException
  {
    updateAsciiStream(findColumn(paramString), paramInputStream, paramInt);
  }

  public void updateBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
    throws SQLException
  {
    updateBinaryStream(findColumn(paramString), paramInputStream, paramInt);
  }

  public void updateCharacterStream(String paramString, Reader paramReader, int paramInt)
    throws SQLException
  {
    updateCharacterStream(findColumn(paramString), paramReader, paramInt);
  }

  public void updateObject(String paramString, Object paramObject, int paramInt)
    throws SQLException
  {
    updateObject(findColumn(paramString), paramObject, paramInt);
  }

  public void updateObject(String paramString, Object paramObject)
    throws SQLException
  {
    updateObject(findColumn(paramString), paramObject);
  }

  public void insertRow()
    throws SQLException
  {
    performInsert();
  }

  public void updateRow()
    throws SQLException
  {
    performUpdate();
  }

  public void deleteRow()
    throws SQLException
  {
    performDelete();
  }

  public void refreshRow()
    throws SQLException
  {
    clearUpdates();
  }

  public void cancelRowUpdates()
    throws SQLException
  {
    clearUpdates();
  }

  public void moveToInsertRow()
    throws SQLException
  {
    startInsert();
  }

  public void moveToCurrentRow()
    throws SQLException
  {
    endInsert();
  }

  public Statement getStatement()
    throws SQLException
  {
    checkClosed();
    return (Statement)this.statement;
  }

  public Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
    return getObject(paramInt);
  }

  public Ref getRef(int paramInt)
    throws SQLException
  {
    throw Util.notSupported();
  }

  public Blob getBlob(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
    Object localObject1 = getColumnInType(paramInt, localType);
    if (localObject1 == null)
      return null;
    Object localObject2;
    if ((localObject1 instanceof BlobDataID))
    {
      localObject2 = new JDBCBlobClient(this.session, (BlobDataID)localObject1);
      if ((this.isUpdatable) && (this.resultMetaData.colIndexes[(paramInt - 1)] > 0) && (this.resultMetaData.columns[(paramInt - 1)].isWriteable()))
        ((JDBCBlobClient)localObject2).setWritable(this, paramInt - 1);
      return localObject2;
    }
    if ((localObject1 instanceof Blob))
      return (Blob)localObject1;
    if ((localObject1 instanceof BinaryData))
    {
      localObject2 = getBytes(paramInt);
      return new JDBCBlob((byte[])localObject2);
    }
    throw Util.sqlException(5561);
  }

  public Clob getClob(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
    Object localObject = getColumnInType(paramInt, localType);
    if (localObject == null)
      return null;
    if ((localObject instanceof ClobDataID))
    {
      JDBCClobClient localJDBCClobClient = new JDBCClobClient(this.session, (ClobDataID)localObject);
      if ((this.isUpdatable) && (this.resultMetaData.colIndexes[(paramInt - 1)] > 0) && (this.resultMetaData.columns[(paramInt - 1)].isWriteable()))
        localJDBCClobClient.setWritable(this, paramInt - 1);
      return localJDBCClobClient;
    }
    if ((localObject instanceof Clob))
      return (Clob)localObject;
    if ((localObject instanceof String))
      return new JDBCClob((String)localObject);
    throw Util.sqlException(5561);
  }

  public Array getArray(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
    Object[] arrayOfObject = (Object[])getCurrent()[(paramInt - 1)];
    if (!localType.isArrayType())
      throw Util.sqlException(5561);
    if (trackNull(arrayOfObject))
      return null;
    return new JDBCArray(arrayOfObject, localType.collectionBaseType(), localType, this.connection);
  }

  public Object getObject(String paramString, Map paramMap)
    throws SQLException
  {
    return getObject(findColumn(paramString), paramMap);
  }

  public Ref getRef(String paramString)
    throws SQLException
  {
    return getRef(findColumn(paramString));
  }

  public Blob getBlob(String paramString)
    throws SQLException
  {
    return getBlob(findColumn(paramString));
  }

  public Clob getClob(String paramString)
    throws SQLException
  {
    return getClob(findColumn(paramString));
  }

  public Array getArray(String paramString)
    throws SQLException
  {
    return getArray(findColumn(paramString));
  }

  public Date getDate(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
    TimestampData localTimestampData = (TimestampData)getColumnInType(paramInt, Type.SQL_DATE);
    if (localTimestampData == null)
      return null;
    long l = localTimestampData.getSeconds() * 1000L;
    if (paramCalendar != null)
      l = HsqlDateTime.convertMillisToCalendar(paramCalendar, l);
    return new Date(l);
  }

  public Date getDate(String paramString, Calendar paramCalendar)
    throws SQLException
  {
    return getDate(findColumn(paramString), paramCalendar);
  }

  public Time getTime(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
    TimeData localTimeData = (TimeData)getColumnInType(paramInt, Type.SQL_TIME);
    if (localTimeData == null)
      return null;
    long l = DateTimeType.normaliseTime(localTimeData.getSeconds()) * 1000;
    if (!this.resultMetaData.columnTypes[(--paramInt)].isDateTimeTypeWithZone())
    {
      Calendar localCalendar = paramCalendar == null ? this.session.getCalendar() : paramCalendar;
      l = HsqlDateTime.convertMillisToCalendar(localCalendar, l);
      l = HsqlDateTime.getNormalisedTime(l);
    }
    return new Time(l);
  }

  public Time getTime(String paramString, Calendar paramCalendar)
    throws SQLException
  {
    return getTime(findColumn(paramString), paramCalendar);
  }

  public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
    throws SQLException
  {
    TimestampData localTimestampData = (TimestampData)getColumnInType(paramInt, Type.SQL_TIMESTAMP);
    if (localTimestampData == null)
      return null;
    long l = localTimestampData.getSeconds() * 1000L;
    if (!this.resultMetaData.columnTypes[(--paramInt)].isDateTimeTypeWithZone())
    {
      localObject = paramCalendar == null ? this.session.getCalendar() : paramCalendar;
      if (paramCalendar != null)
        l = HsqlDateTime.convertMillisToCalendar((Calendar)localObject, l);
    }
    Object localObject = new Timestamp(l);
    ((Timestamp)localObject).setNanos(localTimestampData.getNanos());
    return localObject;
  }

  public Timestamp getTimestamp(String paramString, Calendar paramCalendar)
    throws SQLException
  {
    return getTimestamp(findColumn(paramString), paramCalendar);
  }

  public URL getURL(int paramInt)
    throws SQLException
  {
    throw Util.notSupported();
  }

  public URL getURL(String paramString)
    throws SQLException
  {
    throw Util.notSupported();
  }

  public void updateRef(int paramInt, Ref paramRef)
    throws SQLException
  {
    throw Util.notSupported();
  }

  public void updateRef(String paramString, Ref paramRef)
    throws SQLException
  {
    throw Util.notSupported();
  }

  public void updateBlob(int paramInt, Blob paramBlob)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setBlobParameter(paramInt, paramBlob);
  }

  public void updateBlob(String paramString, Blob paramBlob)
    throws SQLException
  {
    int i = findColumn(paramString);
    updateBlob(i, paramBlob);
  }

  public void updateClob(int paramInt, Clob paramClob)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setClobParameter(paramInt, paramClob);
  }

  public void updateClob(String paramString, Clob paramClob)
    throws SQLException
  {
    int i = findColumn(paramString);
    updateClob(i, paramClob);
  }

  public void updateArray(int paramInt, Array paramArray)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setParameter(paramInt, paramArray);
  }

  public void updateArray(String paramString, Array paramArray)
    throws SQLException
  {
    int i = findColumn(paramString);
    updateArray(i, paramArray);
  }

  public RowId getRowId(int paramInt)
    throws SQLException
  {
    throw Util.notSupported();
  }

  public RowId getRowId(String paramString)
    throws SQLException
  {
    throw Util.notSupported();
  }

  public void updateRowId(int paramInt, RowId paramRowId)
    throws SQLException
  {
    throw Util.notSupported();
  }

  public void updateRowId(String paramString, RowId paramRowId)
    throws SQLException
  {
    throw Util.notSupported();
  }

  public int getHoldability()
    throws SQLException
  {
    checkClosed();
    return ResultProperties.getJDBCHoldability(this.rsProperties);
  }

  public boolean isClosed()
    throws SQLException
  {
    return this.navigator == null;
  }

  public void updateNString(int paramInt, String paramString)
    throws SQLException
  {
    updateString(paramInt, paramString);
  }

  public void updateNString(String paramString1, String paramString2)
    throws SQLException
  {
    updateString(paramString1, paramString2);
  }

  public void updateNClob(int paramInt, NClob paramNClob)
    throws SQLException
  {
    updateClob(paramInt, paramNClob);
  }

  public void updateNClob(String paramString, NClob paramNClob)
    throws SQLException
  {
    updateClob(paramString, paramNClob);
  }

  public NClob getNClob(int paramInt)
    throws SQLException
  {
    String str = getString(paramInt);
    return str == null ? null : new JDBCNClob(str);
  }

  public NClob getNClob(String paramString)
    throws SQLException
  {
    return getNClob(findColumn(paramString));
  }

  public SQLXML getSQLXML(int paramInt)
    throws SQLException
  {
    checkColumn(paramInt);
    int i = this.resultMetaData.columnTypes[(paramInt - 1)].typeCode;
    Object localObject2;
    Object localObject1;
    switch (i)
    {
    case 137:
      localObject2 = getObject(paramInt);
      if (localObject2 == null)
        localObject1 = null;
      else if ((localObject2 instanceof SQLXML))
        localObject1 = (SQLXML)localObject2;
      else
        throw Util.notSupported();
      break;
    case 40:
      localObject2 = getClob(paramInt);
      if (localObject2 == null)
        localObject1 = null;
      else
        localObject1 = new JDBCSQLXML(((Clob)localObject2).getCharacterStream());
      break;
    case 1:
    case 12:
    case 100:
      localObject2 = getCharacterStream(paramInt);
      if (localObject2 == null)
        localObject1 = null;
      else
        localObject1 = new JDBCSQLXML((Reader)localObject2);
      break;
    case -9:
    case -8:
      localObject2 = getNCharacterStream(paramInt);
      if (localObject2 == null)
        localObject1 = null;
      else
        localObject1 = new JDBCSQLXML((Reader)localObject2);
      break;
    case 30:
      localObject2 = getBlob(paramInt);
      if (localObject2 == null)
        localObject1 = null;
      else
        localObject1 = new JDBCSQLXML(((Blob)localObject2).getBinaryStream());
      break;
    case 60:
    case 61:
      localObject2 = getBinaryStream(paramInt);
      if (localObject2 == null)
        localObject1 = null;
      else
        localObject1 = new JDBCSQLXML((InputStream)localObject2);
      break;
    case 1111:
    case 2000:
      localObject2 = getObject(paramInt);
      if (localObject2 == null)
      {
        localObject1 = null;
      }
      else if ((localObject2 instanceof SQLXML))
      {
        localObject1 = (SQLXML)localObject2;
      }
      else if ((localObject2 instanceof String))
      {
        localObject1 = new JDBCSQLXML((String)localObject2);
      }
      else if ((localObject2 instanceof byte[]))
      {
        localObject1 = new JDBCSQLXML((byte[])localObject2);
      }
      else
      {
        Object localObject3;
        if ((localObject2 instanceof Blob))
        {
          localObject3 = (Blob)localObject2;
          localObject1 = new JDBCSQLXML(((Blob)localObject3).getBinaryStream());
        }
        else if ((localObject2 instanceof Clob))
        {
          localObject3 = (Clob)localObject2;
          localObject1 = new JDBCSQLXML(((Clob)localObject3).getCharacterStream());
        }
        else
        {
          throw Util.notSupported();
        }
      }
      break;
    default:
      throw Util.notSupported();
    }
    return localObject1;
  }

  public SQLXML getSQLXML(String paramString)
    throws SQLException
  {
    return getSQLXML(findColumn(paramString));
  }

  public void updateSQLXML(int paramInt, SQLXML paramSQLXML)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setSQLXML(paramInt, paramSQLXML);
  }

  public void updateSQLXML(String paramString, SQLXML paramSQLXML)
    throws SQLException
  {
    updateSQLXML(findColumn(paramString), paramSQLXML);
  }

  public String getNString(int paramInt)
    throws SQLException
  {
    return getString(paramInt);
  }

  public String getNString(String paramString)
    throws SQLException
  {
    return getString(findColumn(paramString));
  }

  public Reader getNCharacterStream(int paramInt)
    throws SQLException
  {
    return getCharacterStream(paramInt);
  }

  public Reader getNCharacterStream(String paramString)
    throws SQLException
  {
    return getCharacterStream(findColumn(paramString));
  }

  public void updateNCharacterStream(int paramInt, Reader paramReader, long paramLong)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setCharacterStream(paramInt, paramReader, paramLong);
  }

  public void updateNCharacterStream(String paramString, Reader paramReader, long paramLong)
    throws SQLException
  {
    updateCharacterStream(paramString, paramReader, paramLong);
  }

  public void updateAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setAsciiStream(paramInt, paramInputStream, paramLong);
  }

  public void updateBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setBinaryStream(paramInt, paramInputStream, paramLong);
  }

  public void updateCharacterStream(int paramInt, Reader paramReader, long paramLong)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setCharacterStream(paramInt, paramReader, paramLong);
  }

  public void updateAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setAsciiStream(i, paramInputStream, paramLong);
  }

  public void updateBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setBinaryStream(i, paramInputStream, paramLong);
  }

  public void updateCharacterStream(String paramString, Reader paramReader, long paramLong)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setCharacterStream(i, paramReader, paramLong);
  }

  public void updateBlob(int paramInt, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setBlob(paramInt, paramInputStream, paramLong);
  }

  public void updateBlob(String paramString, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setBlob(i, paramInputStream, paramLong);
  }

  public void updateClob(int paramInt, Reader paramReader, long paramLong)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setClob(paramInt, paramReader, paramLong);
  }

  public void updateClob(String paramString, Reader paramReader, long paramLong)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setClob(i, paramReader, paramLong);
  }

  public void updateNClob(int paramInt, Reader paramReader, long paramLong)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setClob(paramInt, paramReader, paramLong);
  }

  public void updateNClob(String paramString, Reader paramReader, long paramLong)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setClob(i, paramReader, paramLong);
  }

  public void updateNCharacterStream(int paramInt, Reader paramReader)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setCharacterStream(paramInt, paramReader);
  }

  public void updateNCharacterStream(String paramString, Reader paramReader)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setCharacterStream(i, paramReader);
  }

  public void updateAsciiStream(int paramInt, InputStream paramInputStream)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setAsciiStream(paramInt, paramInputStream);
  }

  public void updateBinaryStream(int paramInt, InputStream paramInputStream)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setBinaryStream(paramInt, paramInputStream);
  }

  public void updateCharacterStream(int paramInt, Reader paramReader)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setCharacterStream(paramInt, paramReader);
  }

  public void updateAsciiStream(String paramString, InputStream paramInputStream)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setAsciiStream(i, paramInputStream);
  }

  public void updateBinaryStream(String paramString, InputStream paramInputStream)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setBinaryStream(i, paramInputStream);
  }

  public void updateCharacterStream(String paramString, Reader paramReader)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setCharacterStream(i, paramReader);
  }

  public void updateBlob(int paramInt, InputStream paramInputStream)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setBlob(paramInt, paramInputStream);
  }

  public void updateBlob(String paramString, InputStream paramInputStream)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setBlob(i, paramInputStream);
  }

  public void updateClob(int paramInt, Reader paramReader)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setClob(paramInt, paramReader);
  }

  public void updateClob(String paramString, Reader paramReader)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setClob(i, paramReader);
  }

  public void updateNClob(int paramInt, Reader paramReader)
    throws SQLException
  {
    startUpdate(paramInt);
    this.preparedStatement.setClob(paramInt, paramReader);
  }

  public void updateNClob(String paramString, Reader paramReader)
    throws SQLException
  {
    int i = findColumn(paramString);
    startUpdate(i);
    this.preparedStatement.setClob(i, paramReader);
  }

  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
    if (isWrapperFor(paramClass))
      return this;
    throw Util.invalidArgument("iface: " + paramClass);
  }

  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
    return (paramClass != null) && (paramClass.isAssignableFrom(getClass()));
  }

  public <T> T getObject(int paramInt, Class<T> paramClass)
    throws SQLException
  {
    return getObject(paramInt);
  }

  public <T> T getObject(String paramString, Class<T> paramClass)
    throws SQLException
  {
    return getObject(findColumn(paramString), paramClass);
  }

  protected Object[] getCurrent()
    throws SQLException
  {
    RowSetNavigator localRowSetNavigator = this.navigator;
    if (localRowSetNavigator == null)
      throw Util.sqlException(3601);
    if (localRowSetNavigator.isEmpty())
      throw Util.sqlException(3603, 70);
    if (localRowSetNavigator.isBeforeFirst())
      throw Util.sqlException(3603, 71);
    if (localRowSetNavigator.isAfterLast())
      throw Util.sqlException(3603, 72);
    Object[] arrayOfObject = localRowSetNavigator.getCurrent();
    if (arrayOfObject == null)
      throw Util.sqlException(3601);
    return arrayOfObject;
  }

  private void checkClosed()
    throws SQLException
  {
    if (this.navigator == null)
      throw Util.sqlException(3601);
  }

  protected void checkColumn(int paramInt)
    throws SQLException
  {
    if (this.navigator == null)
      throw Util.sqlException(3601);
    if ((paramInt < 1) || (paramInt > this.columnCount))
      throw Util.sqlException(421, String.valueOf(paramInt));
  }

  protected boolean trackNull(Object paramObject)
  {
    return this.wasNullValue = paramObject == null ? 1 : 0;
  }

  protected Object getColumnInType(int paramInt, Type paramType)
    throws SQLException
  {
    Object[] arrayOfObject = getCurrent();
    checkColumn(paramInt);
    Type localType = this.resultMetaData.columnTypes[(--paramInt)];
    Object localObject = arrayOfObject[paramInt];
    if (trackNull(localObject))
      return null;
    if ((this.translateTTIType) && (paramType.isIntervalType()))
      paramType = ((IntervalType)paramType).getCharacterType();
    if (localType.typeCode != paramType.typeCode)
      try
      {
        localObject = paramType.convertToTypeJDBC(this.session, localObject, localType);
      }
      catch (Exception localException)
      {
        String str1 = "instance of " + localObject.getClass().getName();
        String str2 = "from SQL type " + localType.getNameString() + " to " + paramType.getJDBCClassName() + ", value: " + str1;
        Util.throwError(Error.error(5561, str2));
      }
    return localObject;
  }

  private void checkNotForwardOnly()
    throws SQLException
  {
    if (!this.isScrollable)
      throw Util.notSupported();
  }

  private void checkUpdatable()
    throws SQLException
  {
    checkClosed();
    if (!this.isUpdatable)
      throw Util.notUpdatableColumn();
  }

  private void checkUpdatable(int paramInt)
    throws SQLException
  {
    checkClosed();
    checkColumn(paramInt);
    if (!this.isUpdatable)
      throw Util.notUpdatableColumn();
    if (this.resultMetaData.colIndexes[(--paramInt)] == -1)
      throw Util.notUpdatableColumn();
    if (!this.resultMetaData.columns[paramInt].isWriteable())
      throw Util.notUpdatableColumn();
  }

  void startUpdate(int paramInt)
    throws SQLException
  {
    checkUpdatable(paramInt);
    if (this.currentUpdateRowNumber != this.navigator.getRowNumber())
      this.preparedStatement.clearParameters();
    this.currentUpdateRowNumber = this.navigator.getRowNumber();
    this.isRowUpdated = true;
  }

  private void clearUpdates()
    throws SQLException
  {
    checkUpdatable();
    this.preparedStatement.clearParameters();
    this.isRowUpdated = false;
  }

  private void startInsert()
    throws SQLException
  {
    checkUpdatable();
    this.isOnInsertRow = true;
  }

  private void endInsert()
    throws SQLException
  {
    checkUpdatable();
    this.preparedStatement.clearParameters();
    this.isOnInsertRow = false;
  }

  private void performUpdate()
    throws SQLException
  {
    this.preparedStatement.parameterValues[this.columnCount] = getCurrent()[this.columnCount];
    for (int i = 0; i < this.columnCount; i++)
    {
      int j = this.preparedStatement.parameterSet[i] != null ? 1 : 0;
      this.preparedStatement.resultOut.metaData.columnTypes[i] = (j != 0 ? this.preparedStatement.parameterTypes[i] : Type.SQL_ALL_TYPES);
    }
    this.preparedStatement.resultOut.setActionType(81);
    this.preparedStatement.fetchResult();
    this.preparedStatement.clearParameters();
    this.rootWarning = this.preparedStatement.getWarnings();
    this.preparedStatement.clearWarnings();
    this.isRowUpdated = false;
  }

  private void performInsert()
    throws SQLException
  {
    checkUpdatable();
    for (int i = 0; i < this.columnCount; i++)
    {
      int j = this.preparedStatement.parameterSet[i] != null ? 1 : 0;
      if (j == 0)
        throw Util.sqlException(3606);
      this.preparedStatement.resultOut.metaData.columnTypes[i] = this.preparedStatement.parameterTypes[i];
    }
    this.preparedStatement.resultOut.setActionType(50);
    this.preparedStatement.fetchResult();
    this.preparedStatement.clearParameters();
    this.rootWarning = this.preparedStatement.getWarnings();
    this.preparedStatement.clearWarnings();
  }

  private void performDelete()
    throws SQLException
  {
    checkUpdatable();
    this.preparedStatement.parameterValues[this.columnCount] = getCurrent()[this.columnCount];
    this.preparedStatement.resultOut.metaData.columnTypes[this.columnCount] = this.resultMetaData.columnTypes[this.columnCount];
    this.preparedStatement.resultOut.setActionType(18);
    this.preparedStatement.fetchResult();
    this.preparedStatement.clearParameters();
    this.rootWarning = this.preparedStatement.getWarnings();
    this.preparedStatement.clearWarnings();
  }

  RowSetNavigator getNavigator()
  {
    return this.navigator;
  }

  void setNavigator(RowSetNavigator paramRowSetNavigator)
  {
    this.navigator = paramRowSetNavigator;
  }

  public JDBCResultSet(JDBCConnection paramJDBCConnection, JDBCStatementBase paramJDBCStatementBase, Result paramResult, ResultMetaData paramResultMetaData)
  {
    this.session = (paramJDBCConnection == null ? null : paramJDBCConnection.sessionProxy);
    this.statement = paramJDBCStatementBase;
    this.result = paramResult;
    this.connection = paramJDBCConnection;
    this.rsProperties = paramResult.rsProperties;
    this.navigator = paramResult.getNavigator();
    this.resultMetaData = paramResultMetaData;
    this.columnCount = this.resultMetaData.getColumnCount();
    this.isScrollable = ResultProperties.isScrollable(this.rsProperties);
    if (ResultProperties.isUpdatable(this.rsProperties))
    {
      this.isUpdatable = true;
      this.isInsertable = true;
      for (int i = 0; i < paramResultMetaData.colIndexes.length; i++)
        if (paramResultMetaData.colIndexes[i] < 0)
        {
          this.isInsertable = false;
          break;
        }
      this.preparedStatement = new JDBCPreparedStatement(paramJDBCStatementBase.connection, this.result);
    }
    if ((paramJDBCConnection != null) && (paramJDBCConnection.clientProperties != null))
      this.translateTTIType = paramJDBCConnection.clientProperties.isPropertyTrue("jdbc.translate_tti_types");
  }

  public JDBCResultSet(JDBCConnection paramJDBCConnection, Result paramResult, ResultMetaData paramResultMetaData)
  {
    this.session = (paramJDBCConnection == null ? null : paramJDBCConnection.sessionProxy);
    this.result = paramResult;
    this.connection = paramJDBCConnection;
    this.rsProperties = 0;
    this.navigator = paramResult.getNavigator();
    this.resultMetaData = paramResultMetaData;
    this.columnCount = this.resultMetaData.getColumnCount();
    if ((paramJDBCConnection != null) && (paramJDBCConnection.clientProperties != null))
      this.translateTTIType = paramJDBCConnection.clientProperties.isPropertyTrue("jdbc.translate_tti_types");
  }

  public static JDBCResultSet newJDBCResultSet(Result paramResult, ResultMetaData paramResultMetaData)
  {
    return new JDBCResultSetBasic(paramResult, paramResultMetaData);
  }

  static class JDBCResultSetBasic extends JDBCResultSet
  {
    JDBCResultSetBasic(Result paramResult, ResultMetaData paramResultMetaData)
    {
      super(paramResult, paramResultMetaData);
    }

    protected Object getColumnInType(int paramInt, Type paramType)
      throws SQLException
    {
      Object[] arrayOfObject = getCurrent();
      checkColumn(paramInt);
      Type localType = this.resultMetaData.columnTypes[(--paramInt)];
      Object localObject = arrayOfObject[paramInt];
      if (trackNull(localObject))
        return null;
      if (localType.typeCode != paramType.typeCode)
        Util.throwError(Error.error(5561));
      return localObject;
    }

    public Date getDate(int paramInt)
      throws SQLException
    {
      return (Date)getColumnInType(paramInt, Type.SQL_DATE);
    }

    public Time getTime(int paramInt)
      throws SQLException
    {
      return (Time)getColumnInType(paramInt, Type.SQL_DATE);
    }

    public Timestamp getTimestamp(int paramInt)
      throws SQLException
    {
      return (Timestamp)getColumnInType(paramInt, Type.SQL_DATE);
    }

    public InputStream getBinaryStream(int paramInt)
      throws SQLException
    {
      throw Util.notSupported();
    }

    public Reader getCharacterStream(int paramInt)
      throws SQLException
    {
      throw Util.notSupported();
    }

    public Blob getBlob(int paramInt)
      throws SQLException
    {
      checkColumn(paramInt);
      Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
      Object localObject = getColumnInType(paramInt, localType);
      if (localObject == null)
        return null;
      if ((localObject instanceof Blob))
        return (Blob)localObject;
      if ((localObject instanceof byte[]))
        return new JDBCBlob((byte[])localObject);
      throw Util.sqlException(5561);
    }

    public Clob getClob(int paramInt)
      throws SQLException
    {
      checkColumn(paramInt);
      Type localType = this.resultMetaData.columnTypes[(paramInt - 1)];
      Object localObject = getColumnInType(paramInt, localType);
      if (localObject == null)
        return null;
      if ((localObject instanceof Clob))
        return (Clob)localObject;
      if ((localObject instanceof String))
        return new JDBCClob((String)localObject);
      throw Util.sqlException(5561);
    }

    public Time getTime(int paramInt, Calendar paramCalendar)
      throws SQLException
    {
      throw Util.notSupported();
    }

    public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
      throws SQLException
    {
      throw Util.notSupported();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCResultSet
 * JD-Core Version:    0.6.2
 */