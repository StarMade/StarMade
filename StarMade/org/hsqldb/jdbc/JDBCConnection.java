package org.hsqldb.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import org.hsqldb.ClientConnection;
import org.hsqldb.ClientConnectionHTTP;
import org.hsqldb.DatabaseManager;
import org.hsqldb.DatabaseURL;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.types.Type;

public class JDBCConnection
  implements Connection
{
  int rsHoldability = 1;
  HsqlProperties connProperties;
  HsqlProperties clientProperties;
  SessionInterface sessionProxy;
  boolean isInternal;
  protected boolean isNetConn;
  boolean isClosed;
  private SQLWarning rootWarning;
  private final Object rootWarning_mutex = new Object();
  private int savepointIDSequence;
  int incarnation;
  boolean isPooled;
  JDBCConnectionEventListener poolEventListener;

  public synchronized Statement createStatement()
    throws SQLException
  {
    checkClosed();
    int i = ResultProperties.getValueForJDBC(1003, 1007, this.rsHoldability);
    JDBCStatement localJDBCStatement = new JDBCStatement(this, i);
    return localJDBCStatement;
  }

  public synchronized PreparedStatement prepareStatement(String paramString)
    throws SQLException
  {
    checkClosed();
    try
    {
      return new JDBCPreparedStatement(this, paramString, 1003, 1007, this.rsHoldability, 2, null, null);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized CallableStatement prepareCall(String paramString)
    throws SQLException
  {
    checkClosed();
    try
    {
      JDBCCallableStatement localJDBCCallableStatement = new JDBCCallableStatement(this, paramString, 1003, 1007, this.rsHoldability);
      return localJDBCCallableStatement;
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized String nativeSQL(String paramString)
    throws SQLException
  {
    checkClosed();
    if ((paramString == null) || (paramString.length() == 0) || (paramString.indexOf(123) == -1))
      return paramString;
    int i = 0;
    int j = 0;
    int k = paramString.length();
    int m = 0;
    StringBuffer localStringBuffer = null;
    int n = 0;
    for (int i1 = 0; i1 < k; i1++)
    {
      int i2 = paramString.charAt(i1);
      switch (j)
      {
      case 0:
        if (i2 == 39)
        {
          j = 1;
        }
        else if (i2 == 34)
        {
          j = 2;
        }
        else if (i2 == 123)
        {
          if (localStringBuffer == null)
            localStringBuffer = new StringBuffer(paramString.length());
          localStringBuffer.append(paramString.substring(n, i1));
          i1 = onStartEscapeSequence(paramString, localStringBuffer, i1);
          n = i1;
          i = 1;
          m++;
          j = 3;
        }
        break;
      case 1:
      case 4:
        if (i2 == 39)
          j--;
        break;
      case 2:
      case 5:
        if (i2 == 34)
          j -= 2;
        break;
      case 3:
        if (i2 == 39)
        {
          j = 4;
        }
        else if (i2 == 34)
        {
          j = 5;
        }
        else if (i2 == 125)
        {
          localStringBuffer.append(paramString.substring(n, i1));
          localStringBuffer.append(' ');
          i1++;
          n = i1;
          i = 1;
          m--;
          j = m == 0 ? 0 : 3;
        }
        else if (i2 == 123)
        {
          localStringBuffer.append(paramString.substring(n, i1));
          i1 = onStartEscapeSequence(paramString, localStringBuffer, i1);
          n = i1;
          i = 1;
          m++;
          j = 3;
        }
        break;
      }
    }
    if (i == 0)
      return paramString;
    localStringBuffer.append(paramString.substring(n, paramString.length()));
    return localStringBuffer.toString();
  }

  public synchronized void setAutoCommit(boolean paramBoolean)
    throws SQLException
  {
    checkClosed();
    try
    {
      this.sessionProxy.setAutoCommit(paramBoolean);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized boolean getAutoCommit()
    throws SQLException
  {
    checkClosed();
    try
    {
      return this.sessionProxy.isAutoCommit();
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized void commit()
    throws SQLException
  {
    checkClosed();
    try
    {
      this.sessionProxy.commit(false);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized void rollback()
    throws SQLException
  {
    checkClosed();
    try
    {
      this.sessionProxy.rollback(false);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized void close()
    throws SQLException
  {
    if ((this.isInternal) || (this.isClosed))
      return;
    this.isClosed = true;
    this.rootWarning = null;
    this.connProperties = null;
    if (this.isPooled)
    {
      if (this.poolEventListener != null)
      {
        this.poolEventListener.connectionClosed();
        this.poolEventListener = null;
      }
    }
    else if (this.sessionProxy != null)
    {
      this.sessionProxy.close();
      this.sessionProxy = null;
    }
  }

  public synchronized boolean isClosed()
    throws SQLException
  {
    return this.isClosed;
  }

  public synchronized DatabaseMetaData getMetaData()
    throws SQLException
  {
    checkClosed();
    return new JDBCDatabaseMetaData(this);
  }

  public synchronized void setReadOnly(boolean paramBoolean)
    throws SQLException
  {
    checkClosed();
    try
    {
      this.sessionProxy.setReadOnlyDefault(paramBoolean);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized boolean isReadOnly()
    throws SQLException
  {
    checkClosed();
    try
    {
      return this.sessionProxy.isReadOnlyDefault();
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized void setCatalog(String paramString)
    throws SQLException
  {
    checkClosed();
    try
    {
      this.sessionProxy.setAttribute(3, paramString);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized String getCatalog()
    throws SQLException
  {
    checkClosed();
    try
    {
      return (String)this.sessionProxy.getAttribute(3);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized void setTransactionIsolation(int paramInt)
    throws SQLException
  {
    checkClosed();
    switch (paramInt)
    {
    case 1:
    case 2:
    case 4:
    case 8:
      break;
    case 3:
    case 5:
    case 6:
    case 7:
    default:
      throw Util.invalidArgument();
    }
    try
    {
      this.sessionProxy.setIsolationDefault(paramInt);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized int getTransactionIsolation()
    throws SQLException
  {
    checkClosed();
    try
    {
      return this.sessionProxy.getIsolation();
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized SQLWarning getWarnings()
    throws SQLException
  {
    checkClosed();
    return this.rootWarning;
  }

  public synchronized void clearWarnings()
    throws SQLException
  {
    checkClosed();
    this.rootWarning = null;
  }

  public synchronized Statement createStatement(int paramInt1, int paramInt2)
    throws SQLException
  {
    checkClosed();
    int i = ResultProperties.getValueForJDBC(paramInt1, paramInt2, this.rsHoldability);
    return new JDBCStatement(this, i);
  }

  public synchronized PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
    checkClosed();
    try
    {
      int i = this.rsHoldability;
      if (paramInt2 == 1008)
        i = 2;
      return new JDBCPreparedStatement(this, paramString, paramInt1, paramInt2, i, 2, null, null);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
    checkClosed();
    try
    {
      return new JDBCCallableStatement(this, paramString, paramInt1, paramInt2, this.rsHoldability);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized Map<String, Class<?>> getTypeMap()
    throws SQLException
  {
    checkClosed();
    return new HashMap();
  }

  public synchronized void setTypeMap(Map<String, Class<?>> paramMap)
    throws SQLException
  {
    checkClosed();
    throw Util.notSupported();
  }

  public synchronized void setHoldability(int paramInt)
    throws SQLException
  {
    checkClosed();
    switch (paramInt)
    {
    case 1:
    case 2:
      break;
    default:
      throw Util.invalidArgument();
    }
    this.rsHoldability = paramInt;
  }

  public synchronized int getHoldability()
    throws SQLException
  {
    checkClosed();
    return this.rsHoldability;
  }

  public synchronized Savepoint setSavepoint()
    throws SQLException
  {
    checkClosed();
    if (getAutoCommit())
      throw Util.sqlException(4821);
    JDBCSavepoint localJDBCSavepoint = new JDBCSavepoint(this);
    try
    {
      this.sessionProxy.savepoint(localJDBCSavepoint.name);
    }
    catch (HsqlException localHsqlException)
    {
      Util.throwError(localHsqlException);
    }
    return localJDBCSavepoint;
  }

  public synchronized Savepoint setSavepoint(String paramString)
    throws SQLException
  {
    checkClosed();
    if (getAutoCommit())
      throw Util.sqlException(4821);
    if (paramString == null)
      throw Util.nullArgument();
    if (paramString.startsWith("SYSTEM_SAVEPOINT_"))
      throw Util.invalidArgument();
    try
    {
      this.sessionProxy.savepoint(paramString);
    }
    catch (HsqlException localHsqlException)
    {
      Util.throwError(localHsqlException);
    }
    return new JDBCSavepoint(paramString, this);
  }

  public synchronized void rollback(Savepoint paramSavepoint)
    throws SQLException
  {
    checkClosed();
    if (paramSavepoint == null)
      throw Util.nullArgument();
    String str;
    if (!(paramSavepoint instanceof JDBCSavepoint))
    {
      str = Error.getMessage(4821);
      throw Util.invalidArgument(str);
    }
    JDBCSavepoint localJDBCSavepoint = (JDBCSavepoint)paramSavepoint;
    if (localJDBCSavepoint.name == null)
    {
      str = Error.getMessage(4821);
      throw Util.invalidArgument(str);
    }
    if (this != localJDBCSavepoint.connection)
    {
      str = Error.getMessage(4821);
      throw Util.invalidArgument(str);
    }
    if (getAutoCommit())
    {
      localJDBCSavepoint.name = null;
      localJDBCSavepoint.connection = null;
      throw Util.sqlException(4821);
    }
    try
    {
      this.sessionProxy.rollbackToSavepoint(localJDBCSavepoint.name);
      localJDBCSavepoint.connection = null;
      localJDBCSavepoint.name = null;
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized void releaseSavepoint(Savepoint paramSavepoint)
    throws SQLException
  {
    checkClosed();
    if (paramSavepoint == null)
      throw Util.nullArgument();
    String str;
    if (!(paramSavepoint instanceof JDBCSavepoint))
    {
      str = Error.getMessage(4821);
      throw Util.invalidArgument(str);
    }
    JDBCSavepoint localJDBCSavepoint = (JDBCSavepoint)paramSavepoint;
    if (localJDBCSavepoint.name == null)
    {
      str = Error.getMessage(4821);
      throw Util.invalidArgument(str);
    }
    if (this != localJDBCSavepoint.connection)
    {
      str = Error.getMessage(4821);
      throw Util.invalidArgument(str);
    }
    if (getAutoCommit())
    {
      localJDBCSavepoint.name = null;
      localJDBCSavepoint.connection = null;
      throw Util.sqlException(4821);
    }
    try
    {
      this.sessionProxy.releaseSavepoint(localJDBCSavepoint.name);
      localJDBCSavepoint.connection = null;
      localJDBCSavepoint.name = null;
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized Statement createStatement(int paramInt1, int paramInt2, int paramInt3)
    throws SQLException
  {
    checkClosed();
    int i = ResultProperties.getValueForJDBC(paramInt1, paramInt2, paramInt3);
    return new JDBCStatement(this, i);
  }

  public synchronized PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws SQLException
  {
    checkClosed();
    try
    {
      return new JDBCPreparedStatement(this, paramString, paramInt1, paramInt2, paramInt3, 2, null, null);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws SQLException
  {
    checkClosed();
    try
    {
      return new JDBCCallableStatement(this, paramString, paramInt1, paramInt2, paramInt3);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized PreparedStatement prepareStatement(String paramString, int paramInt)
    throws SQLException
  {
    checkClosed();
    try
    {
      if ((paramInt != 1) && (paramInt != 2))
        throw Util.invalidArgument("autoGeneratedKeys");
      return new JDBCPreparedStatement(this, paramString, 1003, 1007, this.rsHoldability, paramInt, null, null);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized PreparedStatement prepareStatement(String paramString, int[] paramArrayOfInt)
    throws SQLException
  {
    checkClosed();
    try
    {
      return new JDBCPreparedStatement(this, paramString, 1003, 1007, this.rsHoldability, 21, paramArrayOfInt, null);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public synchronized PreparedStatement prepareStatement(String paramString, String[] paramArrayOfString)
    throws SQLException
  {
    checkClosed();
    try
    {
      return new JDBCPreparedStatement(this, paramString, 1003, 1007, this.rsHoldability, 11, null, paramArrayOfString);
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public Clob createClob()
    throws SQLException
  {
    checkClosed();
    return new JDBCClob();
  }

  public Blob createBlob()
    throws SQLException
  {
    checkClosed();
    return new JDBCBlob();
  }

  public NClob createNClob()
    throws SQLException
  {
    checkClosed();
    return new JDBCNClob();
  }

  public SQLXML createSQLXML()
    throws SQLException
  {
    checkClosed();
    return new JDBCSQLXML();
  }

  public boolean isValid(int paramInt)
    throws SQLException
  {
    if (paramInt < 0)
      throw Util.outOfRangeArgument("timeout: " + paramInt);
    if (this.isInternal)
      return true;
    if (!this.isNetConn)
      return !isClosed();
    if (isClosed())
      return false;
    final boolean[] arrayOfBoolean = { true };
    Thread local1 = new Thread()
    {
      public void run()
      {
        try
        {
          JDBCConnection.this.getMetaData().getDatabaseMajorVersion();
        }
        catch (Throwable localThrowable)
        {
          arrayOfBoolean[0] = false;
        }
      }
    };
    if (paramInt > 60)
      paramInt = 60;
    paramInt *= 1000;
    try
    {
      local1.start();
      long l = System.currentTimeMillis();
      local1.join(paramInt);
      try
      {
        local1.setContextClassLoader(null);
      }
      catch (Throwable localThrowable2)
      {
      }
      if (paramInt == 0)
        return arrayOfBoolean[0];
      return (arrayOfBoolean[0] != 0) && (System.currentTimeMillis() - l < paramInt);
    }
    catch (Throwable localThrowable1)
    {
    }
    return false;
  }

  public void setClientInfo(String paramString1, String paramString2)
    throws SQLClientInfoException
  {
    SQLClientInfoException localSQLClientInfoException = new SQLClientInfoException();
    localSQLClientInfoException.initCause(Util.notSupported());
    throw localSQLClientInfoException;
  }

  public void setClientInfo(Properties paramProperties)
    throws SQLClientInfoException
  {
    if ((!this.isClosed) && ((paramProperties == null) || (paramProperties.isEmpty())))
      return;
    SQLClientInfoException localSQLClientInfoException = new SQLClientInfoException();
    if (this.isClosed)
      localSQLClientInfoException.initCause(Util.connectionClosedException());
    else
      localSQLClientInfoException.initCause(Util.notSupported());
    throw localSQLClientInfoException;
  }

  public String getClientInfo(String paramString)
    throws SQLException
  {
    checkClosed();
    return null;
  }

  public Properties getClientInfo()
    throws SQLException
  {
    checkClosed();
    return null;
  }

  public Array createArrayOf(String paramString, Object[] paramArrayOfObject)
    throws SQLException
  {
    checkClosed();
    if (paramString == null)
      throw Util.nullArgument();
    paramString = paramString.toUpperCase();
    int i = Type.getTypeNr(paramString);
    if (i < 0)
      throw Util.invalidArgument(paramString);
    Type localType = Type.getDefaultType(i);
    if ((localType.isArrayType()) || (localType.isLobType()) || (localType.isRowType()))
      throw Util.invalidArgument(paramString);
    Object[] arrayOfObject = new Object[paramArrayOfObject.length];
    try
    {
      for (int j = 0; j < paramArrayOfObject.length; j++)
      {
        Object localObject = localType.convertJavaToSQL(this.sessionProxy, paramArrayOfObject[j]);
        arrayOfObject[j] = localType.convertToTypeLimits(this.sessionProxy, localObject);
      }
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
    return new JDBCArray(arrayOfObject, localType, this);
  }

  public Struct createStruct(String paramString, Object[] paramArrayOfObject)
    throws SQLException
  {
    checkClosed();
    throw Util.notSupported();
  }

  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
    checkClosed();
    if (isWrapperFor(paramClass))
      return this;
    throw Util.invalidArgument("iface: " + paramClass);
  }

  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
    checkClosed();
    return (paramClass != null) && (paramClass.isAssignableFrom(getClass()));
  }

  public void setSchema(String paramString)
    throws SQLException
  {
    checkClosed();
    if (paramString == null)
      Util.nullArgument("schema");
    else if (paramString.length() == 0)
      Util.invalidArgument("Zero-length schema");
    else
      new JDBCDatabaseMetaData(this).setConnectionDefaultSchema(paramString);
  }

  public String getSchema()
    throws SQLException
  {
    checkClosed();
    return new JDBCDatabaseMetaData(this).getConnectionDefaultSchema();
  }

  public void abort(Executor paramExecutor)
    throws SQLException
  {
    if (paramExecutor == null)
      throw Util.nullArgument("executor");
    close();
  }

  public void setNetworkTimeout(Executor paramExecutor, int paramInt)
    throws SQLException
  {
    checkClosed();
    throw Util.notSupported();
  }

  public int getNetworkTimeout()
    throws SQLException
  {
    return 0;
  }

  public JDBCConnection(HsqlProperties paramHsqlProperties)
    throws SQLException
  {
    String str1 = paramHsqlProperties.getProperty("user");
    String str2 = paramHsqlProperties.getProperty("password");
    String str3 = paramHsqlProperties.getProperty("connection_type");
    String str4 = paramHsqlProperties.getProperty("host");
    int i = paramHsqlProperties.getIntegerProperty("port", 0);
    String str5 = paramHsqlProperties.getProperty("path");
    String str6 = paramHsqlProperties.getProperty("database");
    boolean bool = (str3 == "hsqls://") || (str3 == "https://");
    if (str1 == null)
      str1 = "SA";
    if (str2 == null)
      str2 = "";
    Calendar localCalendar = Calendar.getInstance();
    int j = HsqlDateTime.getZoneSeconds(localCalendar);
    try
    {
      if (DatabaseURL.isInProcessDatabaseType(str3))
      {
        this.sessionProxy = DatabaseManager.newSession(str3, str6, str1, str2, paramHsqlProperties, null, j);
      }
      else if ((str3 == "hsql://") || (str3 == "hsqls://"))
      {
        this.sessionProxy = new ClientConnection(str4, i, str5, str6, bool, str1, str2, j);
        this.isNetConn = true;
      }
      else if ((str3 == "http://") || (str3 == "https://"))
      {
        this.sessionProxy = new ClientConnectionHTTP(str4, i, str5, str6, bool, str1, str2, j);
        this.isNetConn = true;
      }
      else
      {
        throw Util.invalidArgument(str3);
      }
      this.sessionProxy.setJDBCConnection(this);
      this.connProperties = paramHsqlProperties;
      this.clientProperties = this.sessionProxy.getClientProperties();
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(localHsqlException);
    }
  }

  public JDBCConnection(SessionInterface paramSessionInterface)
  {
    this.isInternal = true;
    this.sessionProxy = paramSessionInterface;
  }

  public JDBCConnection(JDBCConnection paramJDBCConnection, JDBCConnectionEventListener paramJDBCConnectionEventListener)
  {
    this.sessionProxy = paramJDBCConnection.sessionProxy;
    this.connProperties = paramJDBCConnection.connProperties;
    this.clientProperties = paramJDBCConnection.clientProperties;
    this.isPooled = true;
    this.poolEventListener = paramJDBCConnectionEventListener;
  }

  protected void finalize()
  {
    try
    {
      close();
    }
    catch (SQLException localSQLException)
    {
    }
  }

  synchronized int getSavepointID()
  {
    return this.savepointIDSequence++;
  }

  synchronized String getURL()
    throws SQLException
  {
    checkClosed();
    return this.isInternal ? this.sessionProxy.getInternalConnectionURL() : this.connProperties.getProperty("url");
  }

  synchronized void checkClosed()
    throws SQLException
  {
    if (this.isClosed)
      throw Util.connectionClosedException();
  }

  void addWarning(SQLWarning paramSQLWarning)
  {
    synchronized (this.rootWarning_mutex)
    {
      if (this.rootWarning == null)
        this.rootWarning = paramSQLWarning;
      else
        this.rootWarning.setNextWarning(paramSQLWarning);
    }
  }

  void setWarnings(SQLWarning paramSQLWarning)
  {
    synchronized (this.rootWarning_mutex)
    {
      this.rootWarning = paramSQLWarning;
    }
  }

  public void reset()
    throws SQLException
  {
    try
    {
      this.incarnation += 1;
      this.sessionProxy.resetSession();
    }
    catch (HsqlException localHsqlException)
    {
      throw Util.sqlException(1305, localHsqlException.getMessage(), localHsqlException);
    }
  }

  public void closeFully()
  {
    try
    {
      close();
    }
    catch (Throwable localThrowable1)
    {
    }
    try
    {
      if (this.sessionProxy != null)
      {
        this.sessionProxy.close();
        this.sessionProxy = null;
      }
    }
    catch (Throwable localThrowable2)
    {
    }
  }

  public SessionInterface getSession()
  {
    return this.sessionProxy;
  }

  private int onStartEscapeSequence(String paramString, StringBuffer paramStringBuffer, int paramInt)
    throws SQLException
  {
    paramStringBuffer.append(' ');
    paramInt++;
    paramInt = StringUtil.skipSpaces(paramString, paramInt);
    if ((paramString.regionMatches(true, paramInt, "fn ", 0, 3)) || (paramString.regionMatches(true, paramInt, "oj ", 0, 3)))
    {
      paramInt += 2;
    }
    else if (paramString.regionMatches(true, paramInt, "ts ", 0, 3))
    {
      paramStringBuffer.append("TIMESTAMP");
      paramInt += 2;
    }
    else if (paramString.regionMatches(true, paramInt, "d ", 0, 2))
    {
      paramStringBuffer.append("DATE");
      paramInt++;
    }
    else if (paramString.regionMatches(true, paramInt, "t ", 0, 2))
    {
      paramStringBuffer.append("TIME");
      paramInt++;
    }
    else if (paramString.regionMatches(true, paramInt, "call ", 0, 5))
    {
      paramStringBuffer.append("CALL");
      paramInt += 4;
    }
    else if (paramString.regionMatches(true, paramInt, "?= call ", 0, 8))
    {
      paramStringBuffer.append("CALL");
      paramInt += 7;
    }
    else if (paramString.regionMatches(true, paramInt, "? = call ", 0, 8))
    {
      paramStringBuffer.append("CALL");
      paramInt += 8;
    }
    else if (paramString.regionMatches(true, paramInt, "escape ", 0, 7))
    {
      paramInt += 6;
    }
    else
    {
      paramInt--;
      throw Util.sqlException(Error.error(425, paramString.substring(paramInt)));
    }
    return paramInt;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCConnection
 * JD-Core Version:    0.6.2
 */