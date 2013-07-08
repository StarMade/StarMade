package org.hsqldb;

import java.io.InputStream;
import java.util.Calendar;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.TimestampData;

public abstract interface SessionInterface
{
  public static final int INFO_ID = 0;
  public static final int INFO_INTEGER = 1;
  public static final int INFO_BOOLEAN = 2;
  public static final int INFO_VARCHAR = 3;
  public static final int INFO_LIMIT = 4;
  public static final int INFO_ISOLATION = 0;
  public static final int INFO_AUTOCOMMIT = 1;
  public static final int INFO_CONNECTION_READONLY = 2;
  public static final int INFO_CATALOG = 3;
  public static final int TX_READ_UNCOMMITTED = 1;
  public static final int TX_READ_COMMITTED = 2;
  public static final int TX_REPEATABLE_READ = 4;
  public static final int TX_SERIALIZABLE = 8;
  public static final int lobStreamBlockSize = 524288;
  
  public abstract Result execute(Result paramResult);
  
  public abstract RowSetNavigatorClient getRows(long paramLong, int paramInt1, int paramInt2);
  
  public abstract void closeNavigator(long paramLong);
  
  public abstract void close();
  
  public abstract boolean isClosed();
  
  public abstract boolean isReadOnlyDefault();
  
  public abstract void setReadOnlyDefault(boolean paramBoolean);
  
  public abstract boolean isAutoCommit();
  
  public abstract void setAutoCommit(boolean paramBoolean);
  
  public abstract int getIsolation();
  
  public abstract void setIsolationDefault(int paramInt);
  
  public abstract void startPhasedTransaction();
  
  public abstract void prepareCommit();
  
  public abstract void commit(boolean paramBoolean);
  
  public abstract void rollback(boolean paramBoolean);
  
  public abstract void rollbackToSavepoint(String paramString);
  
  public abstract void savepoint(String paramString);
  
  public abstract void releaseSavepoint(String paramString);
  
  public abstract void addWarning(HsqlException paramHsqlException);
  
  public abstract Object getAttribute(int paramInt);
  
  public abstract void setAttribute(int paramInt, Object paramObject);
  
  public abstract long getId();
  
  public abstract void resetSession();
  
  public abstract String getInternalConnectionURL();
  
  public abstract BlobDataID createBlob(long paramLong);
  
  public abstract ClobDataID createClob(long paramLong);
  
  public abstract void allocateResultLob(ResultLob paramResultLob, InputStream paramInputStream);
  
  public abstract Scanner getScanner();
  
  public abstract Calendar getCalendar();
  
  public abstract TimestampData getCurrentDate();
  
  public abstract int getZoneSeconds();
  
  public abstract int getStreamBlockSize();
  
  public abstract HsqlProperties getClientProperties();
  
  public abstract JDBCConnection getJDBCConnection();
  
  public abstract void setJDBCConnection(JDBCConnection paramJDBCConnection);
  
  public abstract String getDatabaseUniqueName();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.SessionInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */