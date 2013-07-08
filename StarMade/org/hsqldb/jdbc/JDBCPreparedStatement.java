package org.hsqldb.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.BatchUpdateException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.CharArrayWriter;
import org.hsqldb.lib.CountdownInputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.BlobInputStream;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.ClobInputStream;
import org.hsqldb.types.JavaObjectData;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class JDBCPreparedStatement
  extends JDBCStatementBase
  implements PreparedStatement
{
  boolean poolable = true;
  protected Object[] parameterValues;
  protected Boolean[] parameterSet;
  protected Type[] parameterTypes;
  protected byte[] parameterModes;
  protected long[] streamLengths;
  protected boolean hasStreams;
  protected boolean hasLOBs;
  protected boolean isBatch;
  protected ResultMetaData resultMetaData;
  protected ResultMetaData parameterMetaData;
  protected JDBCResultSetMetaData resultSetMetaData;
  protected Object pmd;
  protected String sql;
  protected long statementID;
  protected int statementRetType;
  protected final boolean isResult;
  protected SessionInterface session;
  
  public synchronized ResultSet executeQuery()
    throws SQLException
  {
    if (this.statementRetType != 2) {
      checkStatementType(2);
    }
    fetchResult();
    return getResultSet();
  }
  
  public synchronized int executeUpdate()
    throws SQLException
  {
    if (this.statementRetType != 1) {
      checkStatementType(1);
    }
    fetchResult();
    return this.resultIn.getUpdateCount();
  }
  
  public synchronized void setNull(int paramInt1, int paramInt2)
    throws SQLException
  {
    setParameter(paramInt1, null);
  }
  
  public synchronized void setBoolean(int paramInt, boolean paramBoolean)
    throws SQLException
  {
    Boolean localBoolean = paramBoolean ? Boolean.TRUE : Boolean.FALSE;
    setParameter(paramInt, localBoolean);
  }
  
  public synchronized void setByte(int paramInt, byte paramByte)
    throws SQLException
  {
    setIntParameter(paramInt, paramByte);
  }
  
  public synchronized void setShort(int paramInt, short paramShort)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    checkSetParameterIndex(paramInt);
    if (this.parameterTypes[(paramInt - 1)].typeCode == 5)
    {
      this.parameterValues[(--paramInt)] = Integer.valueOf(paramShort);
      this.parameterSet[paramInt] = Boolean.TRUE;
      return;
    }
    setIntParameter(paramInt, paramShort);
  }
  
  public synchronized void setInt(int paramInt1, int paramInt2)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    checkSetParameterIndex(paramInt1);
    if (this.parameterTypes[(paramInt1 - 1)].typeCode == 4)
    {
      this.parameterValues[(--paramInt1)] = Integer.valueOf(paramInt2);
      this.parameterSet[paramInt1] = Boolean.TRUE;
      return;
    }
    setIntParameter(paramInt1, paramInt2);
  }
  
  public synchronized void setLong(int paramInt, long paramLong)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    checkSetParameterIndex(paramInt);
    if (this.parameterTypes[(paramInt - 1)].typeCode == 25)
    {
      this.parameterValues[(--paramInt)] = Long.valueOf(paramLong);
      this.parameterSet[paramInt] = Boolean.TRUE;
      return;
    }
    setLongParameter(paramInt, paramLong);
  }
  
  public synchronized void setFloat(int paramInt, float paramFloat)
    throws SQLException
  {
    setDouble(paramInt, paramFloat);
  }
  
  public synchronized void setDouble(int paramInt, double paramDouble)
    throws SQLException
  {
    Double localDouble = new Double(paramDouble);
    setParameter(paramInt, localDouble);
  }
  
  public synchronized void setBigDecimal(int paramInt, BigDecimal paramBigDecimal)
    throws SQLException
  {
    setParameter(paramInt, paramBigDecimal);
  }
  
  public synchronized void setString(int paramInt, String paramString)
    throws SQLException
  {
    setParameter(paramInt, paramString);
  }
  
  public synchronized void setBytes(int paramInt, byte[] paramArrayOfByte)
    throws SQLException
  {
    setParameter(paramInt, paramArrayOfByte);
  }
  
  public synchronized void setDate(int paramInt, Date paramDate)
    throws SQLException
  {
    setParameter(paramInt, paramDate);
  }
  
  public synchronized void setTime(int paramInt, Time paramTime)
    throws SQLException
  {
    setParameter(paramInt, paramTime);
  }
  
  public synchronized void setTimestamp(int paramInt, Timestamp paramTimestamp)
    throws SQLException
  {
    setParameter(paramInt, paramTimestamp);
  }
  
  public synchronized void setAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
    throws SQLException
  {
    setAsciiStream(paramInt1, paramInputStream, paramInt2);
  }
  
  /**
   * @deprecated
   */
  public synchronized void setUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2)
    throws SQLException
  {
    checkSetParameterIndex(paramInt1);
    Object localObject = null;
    if (paramInputStream == null) {
      throw Util.nullArgument("x");
    }
    String str = "UTF8";
    StringWriter localStringWriter = new StringWriter();
    try
    {
      CountdownInputStream localCountdownInputStream = new CountdownInputStream(paramInputStream);
      InputStreamReader localInputStreamReader = new InputStreamReader(localCountdownInputStream, str);
      char[] arrayOfChar = new char[1024];
      localCountdownInputStream.setCount(paramInt2);
      int i;
      while (-1 != (i = localInputStreamReader.read(arrayOfChar))) {
        localStringWriter.write(arrayOfChar, 0, i);
      }
    }
    catch (IOException localIOException)
    {
      throw Util.sqlException(401, localIOException.toString(), localIOException);
    }
    setParameter(paramInt1, localStringWriter.toString());
  }
  
  public synchronized void setBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
    throws SQLException
  {
    setBinaryStream(paramInt1, paramInputStream, paramInt2);
  }
  
  public synchronized void clearParameters()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    ArrayUtil.fillArray(this.parameterValues, null);
    ArrayUtil.fillArray(this.parameterSet, null);
    ArrayUtil.clearArray(74, this.streamLengths, 0, this.streamLengths.length);
  }
  
  public synchronized void setObject(int paramInt1, Object paramObject, int paramInt2, int paramInt3)
    throws SQLException
  {
    if ((paramObject instanceof InputStream)) {
      setBinaryStream(paramInt1, (InputStream)paramObject, paramInt3);
    } else if ((paramObject instanceof Reader)) {
      setCharacterStream(paramInt1, (Reader)paramObject, paramInt3);
    } else {
      setObject(paramInt1, paramObject);
    }
  }
  
  public synchronized void setObject(int paramInt1, Object paramObject, int paramInt2)
    throws SQLException
  {
    setObject(paramInt1, paramObject);
  }
  
  public synchronized void setObject(int paramInt, Object paramObject)
    throws SQLException
  {
    setParameter(paramInt, paramObject);
  }
  
  public synchronized boolean execute()
    throws SQLException
  {
    fetchResult();
    return this.statementRetType == 2;
  }
  
  public synchronized void addBatch()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    checkParametersSet();
    if (!this.isBatch)
    {
      this.resultOut.setBatchedPreparedExecuteRequest();
      this.isBatch = true;
    }
    try
    {
      performPreExecute();
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
    int i = this.parameterValues.length;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(this.parameterValues, 0, arrayOfObject, 0, i);
    this.resultOut.addBatchedPreparedExecuteRequest(arrayOfObject);
  }
  
  public synchronized void setCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
    throws SQLException
  {
    setCharacterStream(paramInt1, paramReader, paramInt2);
  }
  
  public void setRef(int paramInt, Ref paramRef)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public synchronized void setBlob(int paramInt, Blob paramBlob)
    throws SQLException
  {
    checkSetParameterIndex(paramInt);
    Type localType = this.parameterTypes[(paramInt - 1)];
    switch (localType.typeCode)
    {
    case 60: 
    case 61: 
      setBlobForBinaryParameter(paramInt, paramBlob);
      return;
    case 30: 
      setBlobParameter(paramInt, paramBlob);
      break;
    default: 
      throw Util.invalidArgument();
    }
  }
  
  private void setBlobForBinaryParameter(int paramInt, Blob paramBlob)
    throws SQLException
  {
    if ((paramBlob instanceof JDBCBlob))
    {
      setParameter(paramInt, ((JDBCBlob)paramBlob).data());
      return;
    }
    if (paramBlob == null)
    {
      setParameter(paramInt, null);
      return;
    }
    long l = paramBlob.length();
    Object localObject;
    if (l > 2147483647L)
    {
      localObject = "Maximum Blob input octet length exceeded: " + l;
      throw Util.sqlException(422, (String)localObject);
    }
    try
    {
      localObject = paramBlob.getBinaryStream();
      HsqlByteArrayOutputStream localHsqlByteArrayOutputStream = new HsqlByteArrayOutputStream((InputStream)localObject, (int)l);
      setParameter(paramInt, localHsqlByteArrayOutputStream.toByteArray());
    }
    catch (Throwable localThrowable)
    {
      throw Util.sqlException(422, localThrowable.toString(), localThrowable);
    }
  }
  
  public synchronized void setClob(int paramInt, Clob paramClob)
    throws SQLException
  {
    checkSetParameterIndex(paramInt);
    Type localType = this.parameterTypes[(paramInt - 1)];
    switch (localType.typeCode)
    {
    case 1: 
    case 12: 
      setClobForStringParameter(paramInt, paramClob);
      return;
    case 40: 
      setClobParameter(paramInt, paramClob);
      return;
    }
    throw Util.invalidArgument();
  }
  
  private void setClobForStringParameter(int paramInt, Clob paramClob)
    throws SQLException
  {
    if ((paramClob instanceof JDBCClob))
    {
      setParameter(paramInt, ((JDBCClob)paramClob).data());
      return;
    }
    if (paramClob == null)
    {
      setParameter(paramInt, null);
      return;
    }
    long l = paramClob.length();
    Object localObject;
    if (l > 2147483647L)
    {
      localObject = "Max Clob input character length exceeded: " + l;
      throw Util.sqlException(422, (String)localObject);
    }
    try
    {
      localObject = paramClob.getCharacterStream();
      CharArrayWriter localCharArrayWriter = new CharArrayWriter((Reader)localObject, (int)l);
      setParameter(paramInt, localCharArrayWriter.toString());
    }
    catch (Throwable localThrowable)
    {
      throw Util.sqlException(401, localThrowable.toString(), localThrowable);
    }
  }
  
  public synchronized void setArray(int paramInt, Array paramArray)
    throws SQLException
  {
    checkParameterIndex(paramInt);
    Type localType1 = this.parameterMetaData.columnTypes[(paramInt - 1)];
    if (!localType1.isArrayType()) {
      throw Util.sqlException(5561);
    }
    if (paramArray == null)
    {
      setParameter(paramInt, null);
      return;
    }
    Object[] arrayOfObject1 = null;
    if ((paramArray instanceof JDBCArray))
    {
      arrayOfObject1 = (Object[])((JDBCArray)paramArray).getArrayInternal();
    }
    else
    {
      Object localObject = paramArray.getArray();
      if ((localObject instanceof Object[]))
      {
        Type localType2 = localType1.collectionBaseType();
        Object[] arrayOfObject2 = (Object[])localObject;
        arrayOfObject1 = new Object[arrayOfObject2.length];
        for (int i = 0; i < arrayOfObject1.length; i++) {
          arrayOfObject1[i] = localType2.convertJavaToSQL(this.session, arrayOfObject2[i]);
        }
      }
      else
      {
        throw Util.notSupported();
      }
    }
    this.parameterValues[(paramInt - 1)] = arrayOfObject1;
    this.parameterSet[(paramInt - 1)] = Boolean.TRUE;
  }
  
  public synchronized ResultSetMetaData getMetaData()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if (this.statementRetType != 2) {
      return null;
    }
    if (this.resultSetMetaData == null)
    {
      boolean bool1 = ResultProperties.isUpdatable(this.rsProperties);
      boolean bool2 = bool1;
      if (bool2) {
        for (int i = 0; i < this.resultMetaData.colIndexes.length; i++) {
          if (this.resultMetaData.colIndexes[i] < 0)
          {
            bool2 = false;
            break;
          }
        }
      }
      this.resultSetMetaData = new JDBCResultSetMetaData(this.resultMetaData, bool1, bool2, this.connection);
    }
    return this.resultSetMetaData;
  }
  
  public synchronized void setDate(int paramInt, Date paramDate, Calendar paramCalendar)
    throws SQLException
  {
    checkSetParameterIndex(paramInt);
    int i = paramInt - 1;
    if (paramDate == null)
    {
      this.parameterValues[i] = null;
      this.parameterSet[i] = Boolean.TRUE;
      return;
    }
    Type localType = this.parameterTypes[i];
    Calendar localCalendar = paramCalendar == null ? this.session.getCalendar() : paramCalendar;
    long l = HsqlDateTime.convertMillisFromCalendar(localCalendar, paramDate.getTime());
    l = HsqlDateTime.getNormalisedDate(l);
    switch (localType.typeCode)
    {
    case 91: 
    case 93: 
      this.parameterValues[i] = new TimestampData(l / 1000L);
      break;
    case 95: 
      int j = HsqlDateTime.getZoneMillis(localCalendar, l);
      this.parameterValues[i] = new TimestampData(l / 1000L, 0, j / 1000);
      break;
    case 92: 
    case 94: 
    default: 
      throw Util.sqlException(5561);
    }
    this.parameterSet[i] = Boolean.TRUE;
  }
  
  public synchronized void setTime(int paramInt, Time paramTime, Calendar paramCalendar)
    throws SQLException
  {
    checkSetParameterIndex(paramInt);
    int i = paramInt - 1;
    if (paramTime == null)
    {
      this.parameterValues[i] = null;
      this.parameterSet[i] = Boolean.TRUE;
      return;
    }
    Type localType = this.parameterTypes[i];
    long l = paramTime.getTime();
    int j = 0;
    Calendar localCalendar = paramCalendar == null ? this.session.getCalendar() : paramCalendar;
    l = HsqlDateTime.convertMillisFromCalendar(localCalendar, l);
    l = HsqlDateTime.convertToNormalisedTime(l);
    switch (localType.typeCode)
    {
    case 92: 
      break;
    case 94: 
      j = HsqlDateTime.getZoneMillis(localCalendar, l);
      break;
    default: 
      throw Util.sqlException(5561);
    }
    this.parameterValues[i] = new TimeData((int)(l / 1000L), 0, j / 1000);
    this.parameterSet[i] = Boolean.TRUE;
  }
  
  public synchronized void setTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar)
    throws SQLException
  {
    checkSetParameterIndex(paramInt);
    int i = paramInt - 1;
    if (paramTimestamp == null)
    {
      this.parameterValues[i] = null;
      this.parameterSet[i] = Boolean.TRUE;
      return;
    }
    Type localType = this.parameterTypes[i];
    long l = paramTimestamp.getTime();
    int j = 0;
    Calendar localCalendar = paramCalendar == null ? this.session.getCalendar() : paramCalendar;
    l = HsqlDateTime.convertMillisFromCalendar(localCalendar, l);
    switch (localType.typeCode)
    {
    case 95: 
      j = HsqlDateTime.getZoneMillis(localCalendar, l);
    case 93: 
      this.parameterValues[i] = new TimestampData(l / 1000L, paramTimestamp.getNanos(), j / 1000);
      break;
    case 92: 
      l = HsqlDateTime.getNormalisedTime(l);
      this.parameterValues[i] = new TimeData((int)(l / 1000L), paramTimestamp.getNanos(), 0);
      break;
    case 94: 
      j = HsqlDateTime.getZoneMillis(localCalendar, l);
      this.parameterValues[i] = new TimeData((int)(l / 1000L), paramTimestamp.getNanos(), j / 1000);
      break;
    case 91: 
      l = HsqlDateTime.getNormalisedDate(l);
      this.parameterValues[i] = new TimestampData(l / 1000L);
      break;
    default: 
      throw Util.sqlException(5561);
    }
    this.parameterSet[i] = Boolean.TRUE;
  }
  
  public synchronized void setNull(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
    setParameter(paramInt1, null);
  }
  
  public synchronized int[] executeBatch()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    checkStatementType(1);
    if (!this.isBatch) {
      throw Util.sqlExceptionSQL(1256);
    }
    this.generatedResult = null;
    int i = this.resultOut.getNavigator().getSize();
    this.resultIn = null;
    try
    {
      this.resultIn = this.session.execute(this.resultOut);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
    finally
    {
      performPostExecute();
      this.resultOut.getNavigator().clear();
      this.isBatch = false;
    }
    if (this.resultIn.mode == 2) {
      throw Util.sqlException(this.resultIn);
    }
    RowSetNavigator localRowSetNavigator = this.resultIn.getNavigator();
    int[] arrayOfInt = new int[localRowSetNavigator.getSize()];
    for (int j = 0; j < arrayOfInt.length; j++)
    {
      Object[] arrayOfObject = (Object[])localRowSetNavigator.getNext();
      arrayOfInt[j] = ((Integer)arrayOfObject[0]).intValue();
    }
    if (arrayOfInt.length != i)
    {
      if (this.errorResult == null) {
        throw new BatchUpdateException(arrayOfInt);
      }
      this.errorResult.getMainString();
      throw new BatchUpdateException(this.errorResult.getMainString(), this.errorResult.getSubString(), this.errorResult.getErrorCode(), arrayOfInt);
    }
    return arrayOfInt;
  }
  
  public void setEscapeProcessing(boolean paramBoolean)
    throws SQLException
  {
    checkClosed();
  }
  
  public void addBatch(String paramString)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public synchronized ResultSet executeQuery(String paramString)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public boolean execute(String paramString)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public int executeUpdate(String paramString)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public synchronized void close()
    throws SQLException
  {
    if (isClosed()) {
      return;
    }
    closeResultData();
    Object localObject = null;
    try
    {
      if (!this.connection.isClosed) {
        this.session.execute(Result.newFreeStmtRequest(this.statementID));
      }
    }
    catch (HsqlException localHsqlException)
    {
      localObject = localHsqlException;
    }
    this.parameterValues = null;
    this.parameterSet = null;
    this.parameterTypes = null;
    this.parameterModes = null;
    this.resultMetaData = null;
    this.parameterMetaData = null;
    this.resultSetMetaData = null;
    this.pmd = null;
    this.connection = null;
    this.session = null;
    this.resultIn = null;
    this.resultOut = null;
    this.isClosed = true;
    if (localObject != null) {
      throw Util.sqlException(localObject);
    }
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(super.toString());
    String str = this.sql;
    Object[] arrayOfObject = this.parameterValues;
    if ((str == null) || (arrayOfObject == null))
    {
      localStringBuffer.append("[closed]");
      return localStringBuffer.toString();
    }
    localStringBuffer.append("[sql=[").append(str).append("]");
    if (arrayOfObject.length > 0)
    {
      localStringBuffer.append(", parameters=[");
      for (int i = 0; i < arrayOfObject.length; i++)
      {
        localStringBuffer.append('[');
        localStringBuffer.append(arrayOfObject[i]);
        localStringBuffer.append("], ");
      }
      localStringBuffer.setLength(localStringBuffer.length() - 2);
      localStringBuffer.append(']');
    }
    localStringBuffer.append(']');
    return localStringBuffer.toString();
  }
  
  public void setURL(int paramInt, URL paramURL)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public synchronized ParameterMetaData getParameterMetaData()
    throws SQLException
  {
    checkClosed();
    if (this.pmd == null) {
      this.pmd = new JDBCParameterMetaData(this.connection, this.parameterMetaData);
    }
    return (ParameterMetaData)this.pmd;
  }
  
  public int executeUpdate(String paramString, int paramInt)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public boolean execute(String paramString, int paramInt)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public int executeUpdate(String paramString, int[] paramArrayOfInt)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public boolean execute(String paramString, int[] paramArrayOfInt)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public int executeUpdate(String paramString, String[] paramArrayOfString)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public boolean execute(String paramString, String[] paramArrayOfString)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public synchronized boolean getMoreResults(int paramInt)
    throws SQLException
  {
    return super.getMoreResults(paramInt);
  }
  
  public synchronized ResultSet getGeneratedKeys()
    throws SQLException
  {
    return getGeneratedResultSet();
  }
  
  public synchronized int getResultSetHoldability()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return ResultProperties.getJDBCHoldability(this.rsProperties);
  }
  
  public synchronized boolean isClosed()
  {
    return this.isClosed;
  }
  
  public void setRowId(int paramInt, RowId paramRowId)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public synchronized void setNString(int paramInt, String paramString)
    throws SQLException
  {
    setString(paramInt, paramString);
  }
  
  public synchronized void setNCharacterStream(int paramInt, Reader paramReader, long paramLong)
    throws SQLException
  {
    setCharacterStream(paramInt, paramReader, paramLong);
  }
  
  public synchronized void setNClob(int paramInt, NClob paramNClob)
    throws SQLException
  {
    setClob(paramInt, paramNClob);
  }
  
  public synchronized void setClob(int paramInt, Reader paramReader, long paramLong)
    throws SQLException
  {
    setCharacterStream(paramInt, paramReader, paramLong);
  }
  
  public synchronized void setBlob(int paramInt, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    setBinaryStream(paramInt, paramInputStream, paramLong);
  }
  
  public synchronized void setNClob(int paramInt, Reader paramReader, long paramLong)
    throws SQLException
  {
    setClob(paramInt, paramReader, paramLong);
  }
  
  public void setSQLXML(int paramInt, SQLXML paramSQLXML)
    throws SQLException
  {
    throw Util.notSupported();
  }
  
  public synchronized void setAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    if (paramLong < 0L) {
      throw Util.sqlException(423, "length: " + paramLong);
    }
    setAscStream(paramInt, paramInputStream, paramLong);
  }
  
  void setAscStream(int paramInt, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    if (paramLong > 2147483647L) {
      Util.sqlException(3401);
    }
    if (paramInputStream == null) {
      throw Util.nullArgument("x");
    }
    try
    {
      String str = StringConverter.inputStreamToString(paramInputStream, "US-ASCII");
      if ((paramLong >= 0L) && (str.length() > paramLong)) {
        str = str.substring(0, (int)paramLong);
      }
      setParameter(paramInt, str);
    }
    catch (IOException localIOException)
    {
      throw Util.sqlException(422, null, localIOException);
    }
  }
  
  public synchronized void setBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    if (paramLong < 0L) {
      throw Util.sqlException(423, "length: " + paramLong);
    }
    setBinStream(paramInt, paramInputStream, paramLong);
  }
  
  private void setBinStream(int paramInt, InputStream paramInputStream, long paramLong)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if (this.parameterTypes[(paramInt - 1)].typeCode == 30)
    {
      setBlobParameter(paramInt, paramInputStream, paramLong);
      return;
    }
    Object localObject;
    if (paramLong > 2147483647L)
    {
      localObject = "Maximum Blob input length exceeded: " + paramLong;
      throw Util.sqlException(422, (String)localObject);
    }
    try
    {
      if (paramLong < 0L) {
        localObject = new HsqlByteArrayOutputStream(paramInputStream);
      } else {
        localObject = new HsqlByteArrayOutputStream(paramInputStream, (int)paramLong);
      }
      setParameter(paramInt, ((HsqlByteArrayOutputStream)localObject).toByteArray());
    }
    catch (Throwable localThrowable)
    {
      throw Util.sqlException(422, localThrowable.toString(), localThrowable);
    }
  }
  
  public synchronized void setCharacterStream(int paramInt, Reader paramReader, long paramLong)
    throws SQLException
  {
    if (paramLong < 0L) {
      throw Util.sqlException(423, "length: " + paramLong);
    }
    setCharStream(paramInt, paramReader, paramLong);
  }
  
  private void setCharStream(int paramInt, Reader paramReader, long paramLong)
    throws SQLException
  {
    checkSetParameterIndex(paramInt);
    if (this.parameterTypes[(paramInt - 1)].typeCode == 40)
    {
      setClobParameter(paramInt, paramReader, paramLong);
      return;
    }
    Object localObject;
    if (paramLong > 2147483647L)
    {
      localObject = "Maximum Clob input length exceeded: " + paramLong;
      throw Util.sqlException(422, (String)localObject);
    }
    try
    {
      if (paramLong < 0L) {
        localObject = new CharArrayWriter(paramReader);
      } else {
        localObject = new CharArrayWriter(paramReader, (int)paramLong);
      }
      setParameter(paramInt, ((CharArrayWriter)localObject).toString());
    }
    catch (Throwable localThrowable)
    {
      throw Util.sqlException(422, localThrowable.toString(), localThrowable);
    }
  }
  
  public void setAsciiStream(int paramInt, InputStream paramInputStream)
    throws SQLException
  {
    setAscStream(paramInt, paramInputStream, -1L);
  }
  
  public synchronized void setBinaryStream(int paramInt, InputStream paramInputStream)
    throws SQLException
  {
    setBinStream(paramInt, paramInputStream, -1L);
  }
  
  public void setCharacterStream(int paramInt, Reader paramReader)
    throws SQLException
  {
    setCharStream(paramInt, paramReader, -1L);
  }
  
  public void setNCharacterStream(int paramInt, Reader paramReader)
    throws SQLException
  {
    setCharStream(paramInt, paramReader, -1L);
  }
  
  public void setClob(int paramInt, Reader paramReader)
    throws SQLException
  {
    setCharStream(paramInt, paramReader, -1L);
  }
  
  public void setBlob(int paramInt, InputStream paramInputStream)
    throws SQLException
  {
    setBinStream(paramInt, paramInputStream, -1L);
  }
  
  public void setNClob(int paramInt, Reader paramReader)
    throws SQLException
  {
    setCharStream(paramInt, paramReader, -1L);
  }
  
  public synchronized int getMaxFieldSize()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return 0;
  }
  
  public synchronized void setMaxFieldSize(int paramInt)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if (paramInt < 0) {
      throw Util.outOfRangeArgument();
    }
  }
  
  public synchronized int getMaxRows()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return this.maxRows;
  }
  
  public synchronized void setMaxRows(int paramInt)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if (paramInt < 0) {
      throw Util.outOfRangeArgument();
    }
    this.maxRows = paramInt;
  }
  
  public synchronized int getQueryTimeout()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return 0;
  }
  
  public synchronized void setQueryTimeout(int paramInt)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if ((paramInt < 0) || (paramInt > 32767)) {
      throw Util.outOfRangeArgument();
    }
    this.queryTimeout = paramInt;
  }
  
  public void cancel()
    throws SQLException
  {
    checkClosed();
  }
  
  public synchronized SQLWarning getWarnings()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return this.rootWarning;
  }
  
  public synchronized void clearWarnings()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    this.rootWarning = null;
  }
  
  public void setCursorName(String paramString)
    throws SQLException
  {
    checkClosed();
  }
  
  public synchronized ResultSet getResultSet()
    throws SQLException
  {
    return super.getResultSet();
  }
  
  public synchronized int getUpdateCount()
    throws SQLException
  {
    return super.getUpdateCount();
  }
  
  public synchronized boolean getMoreResults()
    throws SQLException
  {
    return getMoreResults(1);
  }
  
  public synchronized void setFetchDirection(int paramInt)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if ((paramInt != 1000) && (paramInt != 1001) && (paramInt != 1002)) {
      throw Util.notSupported();
    }
    this.fetchDirection = paramInt;
  }
  
  public synchronized int getFetchDirection()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return this.fetchDirection;
  }
  
  public synchronized void setFetchSize(int paramInt)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if (paramInt < 0) {
      throw Util.outOfRangeArgument();
    }
    this.fetchSize = paramInt;
  }
  
  public synchronized int getFetchSize()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return this.fetchSize;
  }
  
  public synchronized int getResultSetConcurrency()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return ResultProperties.getJDBCConcurrency(this.rsProperties);
  }
  
  public synchronized int getResultSetType()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return ResultProperties.getJDBCScrollability(this.rsProperties);
  }
  
  public synchronized void clearBatch()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if (this.isBatch) {
      this.resultOut.getNavigator().clear();
    }
  }
  
  public synchronized Connection getConnection()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return this.connection;
  }
  
  public synchronized void setPoolable(boolean paramBoolean)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    this.poolable = paramBoolean;
  }
  
  public synchronized boolean isPoolable()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    return this.poolable;
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
    if (isWrapperFor(paramClass)) {
      return this;
    }
    throw Util.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
    return (paramClass != null) && (paramClass.isAssignableFrom(getClass()));
  }
  
  JDBCPreparedStatement(JDBCConnection paramJDBCConnection, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, String[] paramArrayOfString)
    throws HsqlException, SQLException
  {
    this.isResult = false;
    this.connection = paramJDBCConnection;
    this.connectionIncarnation = this.connection.incarnation;
    this.session = paramJDBCConnection.sessionProxy;
    paramString = paramJDBCConnection.nativeSQL(paramString);
    int[] arrayOfInt = null;
    if (paramArrayOfInt != null)
    {
      arrayOfInt = new int[paramArrayOfInt.length];
      for (i = 0; i < paramArrayOfInt.length; i++) {
        paramArrayOfInt[i] -= 1;
      }
    }
    this.resultOut = Result.newPrepareStatementRequest();
    int i = ResultProperties.getValueForJDBC(paramInt1, paramInt2, paramInt3);
    this.resultOut.setPrepareOrExecuteProperties(paramString, 0, 0, 0, this.queryTimeout, i, paramInt4, paramArrayOfInt, paramArrayOfString);
    Result localResult1 = this.session.execute(this.resultOut);
    if (localResult1.mode == 2) {
      throw Util.sqlException(localResult1);
    }
    this.rootWarning = null;
    Result localResult2 = localResult1;
    while (localResult2.getChainedResult() != null)
    {
      localResult2 = localResult2.getUnlinkChainedResult();
      if (localResult2.isWarning())
      {
        SQLWarning localSQLWarning = Util.sqlWarning(localResult2);
        if (this.rootWarning == null) {
          this.rootWarning = localSQLWarning;
        } else {
          this.rootWarning.setNextWarning(localSQLWarning);
        }
      }
    }
    this.connection.setWarnings(this.rootWarning);
    this.statementID = localResult1.getStatementID();
    this.statementRetType = localResult1.getStatementType();
    this.resultMetaData = localResult1.metaData;
    this.parameterMetaData = localResult1.parameterMetaData;
    this.parameterTypes = this.parameterMetaData.getParameterTypes();
    this.parameterModes = this.parameterMetaData.paramModes;
    this.rsProperties = localResult1.rsProperties;
    int j = this.parameterMetaData.getColumnCount();
    this.parameterValues = new Object[j];
    this.parameterSet = new Boolean[j];
    this.streamLengths = new long[j];
    for (int k = 0; k < j; k++) {
      if (this.parameterTypes[k].isLobType())
      {
        this.hasLOBs = true;
        break;
      }
    }
    this.resultOut = Result.newPreparedExecuteRequest(this.parameterTypes, this.statementID);
    this.resultOut.setStatement(localResult1.getStatement());
    this.sql = paramString;
  }
  
  JDBCPreparedStatement(JDBCConnection paramJDBCConnection, Result paramResult)
  {
    this.isResult = true;
    this.connection = paramJDBCConnection;
    this.connectionIncarnation = this.connection.incarnation;
    this.session = paramJDBCConnection.sessionProxy;
    int i = paramResult.metaData.getExtendedColumnCount();
    this.parameterMetaData = paramResult.metaData;
    this.parameterTypes = paramResult.metaData.columnTypes;
    this.parameterModes = new byte[i];
    this.parameterValues = new Object[i];
    this.parameterSet = new Boolean[i];
    this.streamLengths = new long[i];
    for (int j = 0; j < i; j++)
    {
      this.parameterModes[j] = 1;
      if (this.parameterTypes[j].isLobType()) {
        this.hasLOBs = true;
      }
    }
    this.resultOut = Result.newUpdateResultRequest(this.parameterTypes, paramResult.getResultId());
  }
  
  protected void checkStatementType(int paramInt)
    throws SQLException
  {
    if (paramInt != this.statementRetType)
    {
      if (this.statementRetType == 1) {
        throw Util.sqlException(1254);
      }
      throw Util.sqlException(1253);
    }
  }
  
  protected void checkParameterIndex(int paramInt)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    if ((paramInt < 1) || (paramInt > this.parameterValues.length))
    {
      String str = "parameter index out of range: " + paramInt;
      throw Util.outOfRangeArgument(str);
    }
  }
  
  protected void checkSetParameterIndex(int paramInt)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    String str;
    if ((paramInt < 1) || (paramInt > this.parameterValues.length))
    {
      str = "parameter index out of range: " + paramInt;
      throw Util.outOfRangeArgument(str);
    }
    if (this.parameterModes[(paramInt - 1)] == 4)
    {
      str = "Not IN or INOUT mode for parameter: " + paramInt;
      throw Util.invalidArgument(str);
    }
  }
  
  protected void checkGetParameterIndex(int paramInt)
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    String str;
    if ((paramInt < 1) || (paramInt > this.parameterValues.length))
    {
      str = "parameter index out of range: " + paramInt;
      throw Util.outOfRangeArgument(str);
    }
    int i = this.parameterModes[(paramInt - 1)];
    switch (i)
    {
    case 0: 
    case 2: 
    case 4: 
      break;
    case 1: 
    case 3: 
    default: 
      str = "Not OUT or INOUT mode: " + i + " for parameter: " + paramInt;
      throw Util.invalidArgument(str);
    }
  }
  
  private void checkParametersSet()
    throws SQLException
  {
    if (this.isResult) {
      return;
    }
    for (int i = 0; i < this.parameterSet.length; i++) {
      if ((this.parameterModes[i] != 4) && (this.parameterSet[i] == null)) {
        throw Util.sqlException(424);
      }
    }
  }
  
  void setParameter(int paramInt, Object paramObject)
    throws SQLException
  {
    checkSetParameterIndex(paramInt);
    paramInt--;
    if (paramObject == null)
    {
      this.parameterValues[paramInt] = null;
      this.parameterSet[paramInt] = Boolean.TRUE;
      return;
    }
    Type localType1 = this.parameterTypes[paramInt];
    switch (localType1.typeCode)
    {
    case 1111: 
      try
      {
        if ((paramObject instanceof Serializable))
        {
          paramObject = new JavaObjectData((Serializable)paramObject);
          break label831;
        }
      }
      catch (HsqlException localHsqlException1)
      {
        Util.throwError(localHsqlException1);
      }
      Util.throwError(Error.error(5563));
    case 14: 
    case 15: 
      try
      {
        if ((paramObject instanceof Boolean))
        {
          paramObject = localType1.convertToDefaultType(this.session, paramObject);
          break label831;
        }
        if ((paramObject instanceof Integer))
        {
          paramObject = localType1.convertToDefaultType(this.session, paramObject);
          break label831;
        }
        if ((paramObject instanceof byte[]))
        {
          paramObject = localType1.convertToDefaultType(this.session, paramObject);
          break label831;
        }
        if ((paramObject instanceof String))
        {
          paramObject = localType1.convertToDefaultType(this.session, paramObject);
          break label831;
        }
      }
      catch (HsqlException localHsqlException2)
      {
        Util.throwError(localHsqlException2);
      }
      Util.throwError(Error.error(5563));
    case 60: 
    case 61: 
      if ((paramObject instanceof byte[]))
      {
        paramObject = new BinaryData((byte[])paramObject, !this.connection.isNetConn);
      }
      else
      {
        try
        {
          if ((paramObject instanceof String))
          {
            paramObject = localType1.convertToDefaultType(this.session, paramObject);
            break label831;
          }
        }
        catch (HsqlException localHsqlException3)
        {
          Util.throwError(localHsqlException3);
        }
        Util.throwError(Error.error(5563));
      }
      break;
    case 50: 
      if ((paramObject instanceof Array))
      {
        setArray(paramInt + 1, (Array)paramObject);
        return;
      }
      if ((paramObject instanceof ArrayList)) {
        paramObject = ((ArrayList)paramObject).toArray();
      }
      if ((paramObject instanceof Object[]))
      {
        Type localType2 = localType1.collectionBaseType();
        Object[] arrayOfObject1 = (Object[])paramObject;
        Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
        for (int i = 0; i < arrayOfObject2.length; i++) {
          arrayOfObject2[i] = localType2.convertJavaToSQL(this.session, arrayOfObject1[i]);
        }
        paramObject = arrayOfObject2;
        break label831;
      }
      Util.throwError(Error.error(5563));
    case 30: 
      setBlobParameter(paramInt + 1, paramObject);
      return;
    case 40: 
      setClobParameter(paramInt + 1, paramObject);
      return;
    case 91: 
    case 92: 
    case 93: 
    case 94: 
    case 95: 
      try
      {
        if ((paramObject instanceof String)) {
          paramObject = localType1.convertToType(this.session, paramObject, Type.SQL_VARCHAR);
        } else {
          paramObject = localType1.convertJavaToSQL(this.session, paramObject);
        }
      }
      catch (HsqlException localHsqlException4)
      {
        Util.throwError(localHsqlException4);
      }
    case -6: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 25: 
      try
      {
        if ((paramObject instanceof String))
        {
          paramObject = localType1.convertToType(this.session, paramObject, Type.SQL_VARCHAR);
        }
        else
        {
          if ((paramObject instanceof Boolean))
          {
            boolean bool = ((Boolean)paramObject).booleanValue();
            paramObject = bool ? Integer.valueOf(1) : Integer.valueOf(0);
          }
          paramObject = localType1.convertToDefaultType(this.session, paramObject);
        }
      }
      catch (HsqlException localHsqlException5)
      {
        Util.throwError(localHsqlException5);
      }
    case 12: 
      if ((paramObject instanceof String)) {
        break label831;
      }
      paramObject = localType1.convertToDefaultType(this.session, paramObject);
      break;
    case 1: 
      if (localType1.precision == 1L) {
        if ((paramObject instanceof Character)) {
          paramObject = new String(new char[] { ((Character)paramObject).charValue() });
        } else if ((paramObject instanceof Boolean)) {
          paramObject = ((Boolean)paramObject).booleanValue() ? "1" : "0";
        }
      }
      break;
    }
    try
    {
      paramObject = localType1.convertToDefaultType(this.session, paramObject);
    }
    catch (HsqlException localHsqlException6)
    {
      Util.throwError(localHsqlException6);
    }
    label831:
    this.parameterValues[paramInt] = paramObject;
    this.parameterSet[paramInt] = Boolean.TRUE;
  }
  
  void setClobParameter(int paramInt, Object paramObject)
    throws SQLException
  {
    setClobParameter(paramInt, paramObject, 0L);
  }
  
  void setClobParameter(int paramInt, Object paramObject, long paramLong)
    throws SQLException
  {
    Object localObject;
    if ((paramObject instanceof JDBCClobClient))
    {
      localObject = (JDBCClobClient)paramObject;
      if (!((JDBCClobClient)localObject).session.getDatabaseUniqueName().equals(this.session.getDatabaseUniqueName()))
      {
        paramLong = ((JDBCClobClient)localObject).length();
        Reader localReader = ((JDBCClobClient)localObject).getCharacterStream();
        this.parameterValues[(paramInt - 1)] = localReader;
        this.streamLengths[(paramInt - 1)] = paramLong;
        this.parameterSet[(paramInt - 1)] = Boolean.FALSE;
        return;
      }
      this.parameterValues[(paramInt - 1)] = paramObject;
      this.parameterSet[(paramInt - 1)] = Boolean.TRUE;
      return;
    }
    if ((paramObject instanceof Clob))
    {
      this.parameterValues[(paramInt - 1)] = paramObject;
      this.parameterSet[(paramInt - 1)] = Boolean.TRUE;
      return;
    }
    if ((paramObject instanceof ClobInputStream))
    {
      localObject = (ClobInputStream)paramObject;
      if (((ClobInputStream)localObject).session.getDatabaseUniqueName().equals(this.session.getDatabaseUniqueName())) {
        throw Util.sqlException(423, "invalid Reader");
      }
      this.parameterValues[(paramInt - 1)] = paramObject;
      this.streamLengths[(paramInt - 1)] = paramLong;
      this.parameterSet[(paramInt - 1)] = Boolean.FALSE;
      return;
    }
    if ((paramObject instanceof Reader))
    {
      this.parameterValues[(paramInt - 1)] = paramObject;
      this.streamLengths[(paramInt - 1)] = paramLong;
      this.parameterSet[(paramInt - 1)] = Boolean.FALSE;
      return;
    }
    if ((paramObject instanceof String))
    {
      localObject = new JDBCClob((String)paramObject);
      this.parameterValues[(paramInt - 1)] = localObject;
      this.parameterSet[(paramInt - 1)] = Boolean.valueOf(false);
      return;
    }
    throw Util.invalidArgument();
  }
  
  void setBlobParameter(int paramInt, Object paramObject)
    throws SQLException
  {
    setBlobParameter(paramInt, paramObject, 0L);
  }
  
  void setBlobParameter(int paramInt, Object paramObject, long paramLong)
    throws SQLException
  {
    Object localObject;
    if ((paramObject instanceof JDBCBlobClient))
    {
      localObject = (JDBCBlobClient)paramObject;
      if (!((JDBCBlobClient)localObject).session.getDatabaseUniqueName().equals(this.session.getDatabaseUniqueName()))
      {
        paramLong = ((JDBCBlobClient)localObject).length();
        InputStream localInputStream = ((JDBCBlobClient)localObject).getBinaryStream();
        this.parameterValues[(paramInt - 1)] = localInputStream;
        this.streamLengths[(paramInt - 1)] = paramLong;
        this.parameterSet[(paramInt - 1)] = Boolean.FALSE;
        return;
      }
      this.parameterValues[(paramInt - 1)] = paramObject;
      this.parameterSet[(paramInt - 1)] = Boolean.TRUE;
      return;
    }
    if ((paramObject instanceof Blob))
    {
      this.parameterValues[(paramInt - 1)] = paramObject;
      this.parameterSet[(paramInt - 1)] = Boolean.FALSE;
      return;
    }
    if ((paramObject instanceof BlobInputStream))
    {
      localObject = (BlobInputStream)paramObject;
      if (((BlobInputStream)localObject).session.getDatabaseUniqueName().equals(this.session.getDatabaseUniqueName())) {
        throw Util.sqlException(423, "invalid Reader");
      }
      this.parameterValues[(paramInt - 1)] = paramObject;
      this.streamLengths[(paramInt - 1)] = paramLong;
      this.parameterSet[(paramInt - 1)] = Boolean.FALSE;
      return;
    }
    if ((paramObject instanceof InputStream))
    {
      this.parameterValues[(paramInt - 1)] = paramObject;
      this.streamLengths[(paramInt - 1)] = paramLong;
      this.parameterSet[(paramInt - 1)] = Boolean.FALSE;
      return;
    }
    if ((paramObject instanceof byte[]))
    {
      localObject = new JDBCBlob((byte[])paramObject);
      this.parameterValues[(paramInt - 1)] = localObject;
      this.parameterSet[(paramInt - 1)] = Boolean.TRUE;
      return;
    }
    throw Util.invalidArgument();
  }
  
  void setIntParameter(int paramInt1, int paramInt2)
    throws SQLException
  {
    checkSetParameterIndex(paramInt1);
    int i = this.parameterTypes[(paramInt1 - 1)].typeCode;
    Object localObject;
    switch (i)
    {
    case -6: 
    case 4: 
    case 5: 
      localObject = Integer.valueOf(paramInt2);
      this.parameterValues[(paramInt1 - 1)] = localObject;
      this.parameterSet[(paramInt1 - 1)] = Boolean.TRUE;
      break;
    case 25: 
      localObject = Long.valueOf(paramInt2);
      this.parameterValues[(paramInt1 - 1)] = localObject;
      this.parameterSet[(paramInt1 - 1)] = Boolean.TRUE;
      break;
    case 60: 
    case 61: 
    case 1111: 
      throw Util.sqlException(Error.error(5563));
    default: 
      setParameter(paramInt1, new Integer(paramInt2));
    }
  }
  
  void setLongParameter(int paramInt, long paramLong)
    throws SQLException
  {
    checkSetParameterIndex(paramInt);
    int i = this.parameterTypes[(paramInt - 1)].typeCode;
    switch (i)
    {
    case 25: 
      Long localLong = new Long(paramLong);
      this.parameterValues[(paramInt - 1)] = localLong;
      this.parameterSet[(paramInt - 1)] = Boolean.TRUE;
      break;
    case 60: 
    case 61: 
    case 1111: 
      throw Util.sqlException(Error.error(5563));
    default: 
      setParameter(paramInt, new Long(paramLong));
    }
  }
  
  private void performPreExecute()
    throws SQLException, HsqlException
  {
    if (!this.hasLOBs) {
      return;
    }
    for (int i = 0; i < this.parameterValues.length; i++)
    {
      Object localObject1 = this.parameterValues[i];
      if (localObject1 != null)
      {
        Object localObject2;
        long l1;
        long l2;
        ResultLob localResultLob1;
        Object localObject3;
        ResultLob localResultLob2;
        if (this.parameterTypes[i].typeCode == 30)
        {
          localObject2 = null;
          if ((localObject1 instanceof JDBCBlobClient))
          {
            localObject2 = ((JDBCBlobClient)localObject1).blob;
            l1 = ((BlobDataID)localObject2).getId();
          }
          else if ((localObject1 instanceof Blob))
          {
            l2 = ((Blob)localObject1).length();
            localObject2 = this.session.createBlob(l2);
            l1 = ((BlobDataID)localObject2).getId();
            InputStream localInputStream = ((Blob)localObject1).getBinaryStream();
            localResultLob1 = ResultLob.newLobCreateBlobRequest(this.session.getId(), l1, localInputStream, l2);
            this.session.allocateResultLob(localResultLob1, null);
            this.resultOut.addLobResult(localResultLob1);
          }
          else if ((localObject1 instanceof InputStream))
          {
            l2 = this.streamLengths[i];
            long l3 = l2 > 0L ? l2 : 0L;
            localObject2 = this.session.createBlob(l3);
            l1 = ((BlobDataID)localObject2).getId();
            localObject3 = (InputStream)localObject1;
            localResultLob2 = ResultLob.newLobCreateBlobRequest(this.session.getId(), l1, (InputStream)localObject3, l2);
            this.session.allocateResultLob(localResultLob2, null);
            this.resultOut.addLobResult(localResultLob2);
          }
          else if ((localObject1 instanceof BlobDataID))
          {
            localObject2 = (BlobDataID)localObject1;
          }
          this.parameterValues[i] = localObject2;
        }
        else if (this.parameterTypes[i].typeCode == 40)
        {
          localObject2 = null;
          if ((localObject1 instanceof JDBCClobClient))
          {
            localObject2 = ((JDBCClobClient)localObject1).clob;
            l1 = ((ClobDataID)localObject2).getId();
          }
          else if ((localObject1 instanceof Clob))
          {
            l2 = ((Clob)localObject1).length();
            Reader localReader = ((Clob)localObject1).getCharacterStream();
            localObject2 = this.session.createClob(l2);
            l1 = ((ClobDataID)localObject2).getId();
            localResultLob1 = ResultLob.newLobCreateClobRequest(this.session.getId(), l1, localReader, l2);
            this.session.allocateResultLob(localResultLob1, null);
            this.resultOut.addLobResult(localResultLob1);
          }
          else if ((localObject1 instanceof Reader))
          {
            l2 = this.streamLengths[i];
            long l4 = l2 > 0L ? l2 : 0L;
            localObject2 = this.session.createClob(l4);
            l1 = ((ClobDataID)localObject2).getId();
            localObject3 = (Reader)localObject1;
            localResultLob2 = ResultLob.newLobCreateClobRequest(this.session.getId(), l1, (Reader)localObject3, l2);
            this.session.allocateResultLob(localResultLob2, null);
            this.resultOut.addLobResult(localResultLob2);
          }
          else if ((localObject1 instanceof ClobDataID))
          {
            localObject2 = (ClobDataID)localObject1;
          }
          this.parameterValues[i] = localObject2;
        }
      }
    }
  }
  
  void fetchResult()
    throws SQLException
  {
    if ((this.isClosed) || (this.connection.isClosed)) {
      checkClosed();
    }
    closeResultData();
    checkParametersSet();
    if (this.isBatch) {
      throw Util.sqlExceptionSQL(1255);
    }
    if (this.isResult) {
      this.resultOut.setPreparedResultUpdateProperties(this.parameterValues);
    } else {
      this.resultOut.setPreparedExecuteProperties(this.parameterValues, this.maxRows, this.fetchSize, this.rsProperties);
    }
    try
    {
      performPreExecute();
      this.resultIn = this.session.execute(this.resultOut);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
    finally
    {
      performPostExecute();
    }
    if (this.resultIn.mode == 2) {
      throw Util.sqlException(this.resultIn);
    }
    if (this.resultIn.isData()) {
      this.currentResultSet = new JDBCResultSet(this.connection, this, this.resultIn, this.resultIn.metaData);
    } else if (this.statementRetType == 2) {
      getMoreResults();
    }
  }
  
  boolean isAnyParameterSet()
  {
    for (int i = 0; i < this.parameterValues.length; i++) {
      if (this.parameterSet[i] != null) {
        return true;
      }
    }
    return false;
  }
  
  void performPostExecute()
    throws SQLException
  {
    super.performPostExecute();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCPreparedStatement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */