package org.hsqldb;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.jdbc.JDBCDriver;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.CountUpDownLatch;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlDeque;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.persist.LobManager;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.PersistentStoreCollectionSession;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.User;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;
import org.hsqldb.types.Type.TypedComparator;

public class Session
  implements SessionInterface
{
  private volatile boolean isClosed;
  public Database database;
  private final User sessionUser;
  private User user;
  private Grantee role;
  public boolean isReadOnlyDefault;
  int isolationLevelDefault = 2;
  int isolationLevel = 2;
  boolean isReadOnlyIsolation;
  int actionIndex;
  long actionTimestamp;
  long transactionTimestamp;
  long transactionEndTimestamp;
  boolean txConflictRollback;
  boolean isPreTransaction;
  boolean isTransaction;
  boolean isBatch;
  volatile boolean abortTransaction;
  volatile boolean redoAction;
  HsqlArrayList rowActionList;
  volatile boolean tempUnlocked;
  public OrderedHashSet waitedSessions;
  public OrderedHashSet waitingSessions;
  OrderedHashSet tempSet;
  public CountUpDownLatch latch = new CountUpDownLatch();
  Statement lockStatement;
  final String zoneString;
  final int sessionTimeZoneSeconds;
  int timeZoneSeconds;
  boolean isNetwork;
  private int sessionMaxRows;
  private final long sessionId;
  int sessionTxId = -1;
  private boolean script;
  boolean ignoreCase;
  private JDBCConnection intConnection;
  private JDBCConnection extConnection;
  public HsqlNameManager.HsqlName currentSchema;
  public HsqlNameManager.HsqlName loggedSchema;
  ParserCommand parser;
  boolean isProcessingScript;
  boolean isProcessingLog;
  public SessionContext sessionContext;
  int resultMaxMemoryRows;
  public SessionData sessionData;
  public StatementManager statementManager;
  private final long connectTime = System.currentTimeMillis();
  long currentDateSCN;
  long currentTimestampSCN;
  long currentMillis;
  private TimestampData currentDate;
  private TimestampData currentTimestamp;
  private TimestampData localTimestamp;
  private TimeData currentTime;
  private TimeData localTime;
  HsqlDeque sqlWarnings;
  Calendar calendar;
  Type.TypedComparator typedComparator;
  Scanner secondaryScanner;
  SimpleDateFormat simpleDateFormat;
  SimpleDateFormat simpleDateFormatGMT;
  Random randomGenerator = new Random();
  long seed = -1L;
  HsqlProperties clientProperties;

  Session(Database paramDatabase, User paramUser, boolean paramBoolean1, boolean paramBoolean2, long paramLong, String paramString, int paramInt)
  {
    this.sessionId = paramLong;
    this.database = paramDatabase;
    this.user = paramUser;
    this.sessionUser = paramUser;
    this.zoneString = paramString;
    this.sessionTimeZoneSeconds = paramInt;
    this.timeZoneSeconds = paramInt;
    this.rowActionList = new HsqlArrayList(32, true);
    this.waitedSessions = new OrderedHashSet();
    this.waitingSessions = new OrderedHashSet();
    this.tempSet = new OrderedHashSet();
    this.isolationLevelDefault = this.database.defaultIsolationLevel;
    this.isolationLevel = this.isolationLevelDefault;
    this.txConflictRollback = this.database.txConflictRollback;
    this.isReadOnlyDefault = paramBoolean2;
    this.isReadOnlyIsolation = (this.isolationLevel == 1);
    this.sessionContext = new SessionContext(this);
    this.sessionContext.isAutoCommit = (paramBoolean1 ? Boolean.TRUE : Boolean.FALSE);
    this.sessionContext.isReadOnly = (this.isReadOnlyDefault ? Boolean.TRUE : Boolean.FALSE);
    this.parser = new ParserCommand(this, new Scanner());
    setResultMemoryRowCount(this.database.getResultMaxMemoryRows());
    resetSchema();
    this.sessionData = new SessionData(this.database, this);
    this.statementManager = new StatementManager(this.database);
  }

  void resetSchema()
  {
    this.loggedSchema = null;
    this.currentSchema = this.user.getInitialOrDefaultSchema();
  }

  public long getId()
  {
    return this.sessionId;
  }

  public synchronized void close()
  {
    if (this.isClosed)
      return;
    rollback(false);
    try
    {
      this.database.logger.writeOtherStatement(this, "DISCONNECT");
    }
    catch (HsqlException localHsqlException)
    {
    }
    this.sessionData.closeAllNavigators();
    this.sessionData.persistentStoreCollection.release();
    this.statementManager.reset();
    this.database.sessionManager.removeSession(this);
    this.database.closeIfLast();
    this.rowActionList.clear();
    this.database = null;
    this.user = null;
    this.sessionContext.savepoints = null;
    this.sessionContext.lastIdentity = null;
    this.intConnection = null;
    this.isClosed = true;
  }

  public boolean isClosed()
  {
    return this.isClosed;
  }

  public synchronized void setIsolationDefault(int paramInt)
  {
    if (paramInt == 1)
      paramInt = 2;
    if (paramInt == this.isolationLevelDefault)
      return;
    this.isolationLevelDefault = paramInt;
    if (!isInMidTransaction())
    {
      this.isolationLevel = this.isolationLevelDefault;
      this.isReadOnlyIsolation = (paramInt == 1);
    }
  }

  public void setIsolation(int paramInt)
  {
    if (isInMidTransaction())
      throw Error.error(3701);
    if (paramInt == 1)
      paramInt = 2;
    if (this.isolationLevel != paramInt)
    {
      this.isolationLevel = paramInt;
      this.isReadOnlyIsolation = (paramInt == 1);
    }
  }

  public synchronized int getIsolation()
  {
    return this.isolationLevel;
  }

  void setLastIdentity(Number paramNumber)
  {
    this.sessionContext.lastIdentity = paramNumber;
  }

  public Number getLastIdentity()
  {
    return this.sessionContext.lastIdentity;
  }

  public Database getDatabase()
  {
    return this.database;
  }

  public String getUsername()
  {
    return this.user.getName().getNameString();
  }

  public User getUser()
  {
    return this.user;
  }

  public Grantee getGrantee()
  {
    return this.user;
  }

  public Grantee getRole()
  {
    return this.role;
  }

  public void setUser(User paramUser)
  {
    this.user = paramUser;
  }

  public void setRole(Grantee paramGrantee)
  {
    this.role = paramGrantee;
  }

  int getMaxRows()
  {
    return this.sessionContext.currentMaxRows;
  }

  void setSQLMaxRows(int paramInt)
  {
    this.sessionMaxRows = paramInt;
  }

  void checkAdmin()
  {
    this.user.checkAdmin();
  }

  void checkReadWrite()
  {
    if ((this.sessionContext.isReadOnly.booleanValue()) || (this.isReadOnlyIsolation))
      throw Error.error(3706);
  }

  void checkDDLWrite()
  {
    if ((this.isProcessingScript) || (this.isProcessingLog))
      return;
    checkReadWrite();
  }

  public long getActionTimestamp()
  {
    return this.actionTimestamp;
  }

  public void addDeleteAction(Table paramTable, Row paramRow, int[] paramArrayOfInt)
  {
    if (this.abortTransaction);
    this.database.txManager.addDeleteAction(this, paramTable, paramRow, paramArrayOfInt);
  }

  void addInsertAction(Table paramTable, PersistentStore paramPersistentStore, Row paramRow, int[] paramArrayOfInt)
  {
    this.database.txManager.addInsertAction(this, paramTable, paramPersistentStore, paramRow, paramArrayOfInt);
    if (this.abortTransaction);
  }

  public synchronized void setAutoCommit(boolean paramBoolean)
  {
    if (this.isClosed)
      return;
    if (this.sessionContext.isAutoCommit.booleanValue() != paramBoolean)
    {
      commit(false);
      this.sessionContext.isAutoCommit = ValuePool.getBoolean(paramBoolean);
    }
  }

  public void beginAction(Statement paramStatement)
  {
    this.actionIndex = this.rowActionList.size();
    this.database.txManager.beginAction(this, paramStatement);
    this.database.txManager.beginActionResume(this);
  }

  public void endAction(Result paramResult)
  {
    this.sessionData.persistentStoreCollection.clearStatementTables();
    if (paramResult.mode == 2)
    {
      this.sessionData.persistentStoreCollection.clearResultTables(this.actionTimestamp);
      this.database.txManager.rollbackAction(this);
    }
    else
    {
      this.sessionContext.diagnosticsVariables[2] = (paramResult.mode == 1 ? Integer.valueOf(paramResult.getUpdateCount()) : ValuePool.INTEGER_0);
      this.database.txManager.completeActions(this);
    }
  }

  public boolean hasLocks(Statement paramStatement)
  {
    if (this.lockStatement == paramStatement)
    {
      if ((this.isolationLevel == 4) || (this.isolationLevel == 8))
        return true;
      if (paramStatement.getTableNamesForRead().length == 0)
        return true;
    }
    return false;
  }

  public void startTransaction()
  {
    this.database.txManager.beginTransaction(this);
  }

  public synchronized void startPhasedTransaction()
  {
  }

  public synchronized void prepareCommit()
  {
    if (this.isClosed)
      throw Error.error(1303);
    if (!this.database.txManager.prepareCommitActions(this))
    {
      rollback(false);
      throw Error.error(4861);
    }
  }

  public synchronized void commit(boolean paramBoolean)
  {
    if (this.isClosed)
      return;
    if (this.sessionContext.depth > 0)
      return;
    if ((!this.isTransaction) && (this.rowActionList.size() == 0))
    {
      this.sessionContext.isReadOnly = (this.isReadOnlyDefault ? Boolean.TRUE : Boolean.FALSE);
      setIsolation(this.isolationLevelDefault);
      return;
    }
    if (!this.database.txManager.commitTransaction(this))
    {
      rollback(paramBoolean);
      throw Error.error(4861);
    }
    endTransaction(true, paramBoolean);
    if ((this.database != null) && (!this.sessionUser.isSystem()) && (this.database.logger.needsCheckpointReset()))
      this.database.checkpointRunner.start();
  }

  public synchronized void rollback(boolean paramBoolean)
  {
    if (this.isClosed)
      return;
    if (this.sessionContext.depth > 0)
      return;
    this.database.txManager.rollback(this);
    endTransaction(false, paramBoolean);
  }

  private void endTransaction(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.sessionContext.savepoints.clear();
    this.sessionContext.savepointTimestamps.clear();
    this.rowActionList.clear();
    this.sessionData.persistentStoreCollection.clearTransactionTables();
    this.sessionData.closeAllTransactionNavigators();
    this.sessionData.clearLobOps();
    this.lockStatement = null;
    logSequences();
    if (!paramBoolean2)
    {
      this.sessionContext.isReadOnly = (this.isReadOnlyDefault ? Boolean.TRUE : Boolean.FALSE);
      setIsolation(this.isolationLevelDefault);
    }
    StatementSession localStatementSession = paramBoolean1 ? StatementSession.commitNoChainStatement : StatementSession.rollbackNoChainStatement;
    if (this.database.logger.getSqlEventLogLevel() > 0)
      this.database.logger.logStatementEvent(this, localStatementSession, null, 1);
  }

  public synchronized void resetSession()
  {
    if (this.isClosed)
      return;
    rollback(false);
    this.sessionData.closeAllNavigators();
    this.sessionData.persistentStoreCollection.clearAllTables();
    this.sessionData.clearLobOps();
    this.statementManager.reset();
    this.sessionContext.lastIdentity = ValuePool.INTEGER_0;
    this.sessionContext.isAutoCommit = Boolean.TRUE;
    setResultMemoryRowCount(this.database.getResultMaxMemoryRows());
    this.user = this.sessionUser;
    resetSchema();
    setZoneSeconds(this.sessionTimeZoneSeconds);
    this.sessionMaxRows = 0;
    this.ignoreCase = false;
    setIsolation(this.isolationLevelDefault);
    this.txConflictRollback = this.database.txConflictRollback;
  }

  public synchronized void savepoint(String paramString)
  {
    int i = this.sessionContext.savepoints.getIndex(paramString);
    if (i != -1)
    {
      this.sessionContext.savepoints.remove(paramString);
      this.sessionContext.savepointTimestamps.remove(i);
    }
    this.sessionContext.savepoints.add(paramString, ValuePool.getInt(this.rowActionList.size()));
    this.sessionContext.savepointTimestamps.addLast(this.actionTimestamp);
  }

  public synchronized void rollbackToSavepoint(String paramString)
  {
    if (this.isClosed)
      return;
    int i = this.sessionContext.savepoints.getIndex(paramString);
    if (i < 0)
      throw Error.error(4821, paramString);
    this.database.txManager.rollbackSavepoint(this, i);
  }

  public synchronized void rollbackToSavepoint()
  {
    if (this.isClosed)
      return;
    String str = (String)this.sessionContext.savepoints.getKey(0);
    this.database.txManager.rollbackSavepoint(this, 0);
  }

  public synchronized void releaseSavepoint(String paramString)
  {
    int i = this.sessionContext.savepoints.getIndex(paramString);
    if (i < 0)
      throw Error.error(4821, paramString);
    while (this.sessionContext.savepoints.size() > i)
    {
      this.sessionContext.savepoints.remove(this.sessionContext.savepoints.size() - 1);
      this.sessionContext.savepointTimestamps.removeLast();
    }
  }

  public boolean isInMidTransaction()
  {
    return this.isTransaction;
  }

  public void setNoSQL()
  {
    this.sessionContext.noSQL = Boolean.TRUE;
  }

  public void setIgnoreCase(boolean paramBoolean)
  {
    this.ignoreCase = paramBoolean;
  }

  public boolean isIgnorecase()
  {
    return this.ignoreCase;
  }

  public void setReadOnly(boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.database.databaseReadOnly))
      throw Error.error(455);
    if (isInMidTransaction())
      throw Error.error(3701);
    this.sessionContext.isReadOnly = (paramBoolean ? Boolean.TRUE : Boolean.FALSE);
  }

  public synchronized void setReadOnlyDefault(boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.database.databaseReadOnly))
      throw Error.error(455);
    this.isReadOnlyDefault = paramBoolean;
    if (!isInMidTransaction())
      this.sessionContext.isReadOnly = (this.isReadOnlyDefault ? Boolean.TRUE : Boolean.FALSE);
  }

  public boolean isReadOnly()
  {
    return (this.sessionContext.isReadOnly.booleanValue()) || (this.isReadOnlyIsolation);
  }

  public synchronized boolean isReadOnlyDefault()
  {
    return this.isReadOnlyDefault;
  }

  public synchronized boolean isAutoCommit()
  {
    return this.sessionContext.isAutoCommit.booleanValue();
  }

  public synchronized int getStreamBlockSize()
  {
    return 524288;
  }

  void setScripting(boolean paramBoolean)
  {
    this.script = paramBoolean;
  }

  boolean isScripting()
  {
    return this.script;
  }

  JDBCConnection getInternalConnection()
  {
    if (this.intConnection == null)
      this.intConnection = new JDBCConnection(this);
    JDBCDriver.driverInstance.threadConnection.set(this.intConnection);
    return this.intConnection;
  }

  void releaseInternalConnection()
  {
    if (this.sessionContext.depth == 0)
      JDBCDriver.driverInstance.threadConnection.set(null);
  }

  public JDBCConnection getJDBCConnection()
  {
    return this.extConnection;
  }

  public void setJDBCConnection(JDBCConnection paramJDBCConnection)
  {
    this.extConnection = paramJDBCConnection;
  }

  public String getDatabaseUniqueName()
  {
    return this.database.getUniqueName();
  }

  public boolean isAdmin()
  {
    return this.user.isAdmin();
  }

  public long getConnectTime()
  {
    return this.connectTime;
  }

  public int getTransactionSize()
  {
    return this.rowActionList.size();
  }

  public long getTransactionTimestamp()
  {
    return this.transactionTimestamp;
  }

  public Statement compileStatement(String paramString, int paramInt)
  {
    this.parser.reset(paramString);
    Statement localStatement = this.parser.compileStatement(paramInt);
    return localStatement;
  }

  public Statement compileStatement(String paramString)
  {
    this.parser.reset(paramString);
    Statement localStatement = this.parser.compileStatement(0);
    localStatement.setCompileTimestamp(9223372036854775807L);
    return localStatement;
  }

  public synchronized Result execute(Result paramResult)
  {
    if (this.isClosed)
      return Result.newErrorResult(Error.error(1353));
    this.sessionContext.currentMaxRows = 0;
    this.isBatch = false;
    JavaSystem.gc();
    Object localObject2;
    Object localObject1;
    switch (paramResult.mode)
    {
    case 18:
      return performLOBOperation((ResultLob)paramResult);
    case 35:
      int i = paramResult.getUpdateCount();
      if (i == -1)
        this.sessionContext.currentMaxRows = 0;
      else
        this.sessionContext.currentMaxRows = i;
      Statement localStatement = paramResult.statement;
      if ((localStatement == null) || (localStatement.compileTimestamp < this.database.schemaManager.schemaChangeTimestamp))
      {
        long l = paramResult.getStatementID();
        localStatement = this.statementManager.getStatement(this, l);
        paramResult.setStatement(localStatement);
        if (localStatement == null)
          return Result.newErrorResult(Error.error(1252));
      }
      localObject2 = (Object[])paramResult.valueData;
      Result localResult2 = executeCompiledStatement(localStatement, (Object[])localObject2);
      localResult2 = performPostExecute(paramResult, localResult2);
      return localResult2;
    case 9:
      this.isBatch = true;
      localObject1 = executeCompiledBatchStatement(paramResult);
      localObject1 = performPostExecute(paramResult, (Result)localObject1);
      return localObject1;
    case 34:
      localObject1 = executeDirectStatement(paramResult);
      localObject1 = performPostExecute(paramResult, (Result)localObject1);
      return localObject1;
    case 8:
      this.isBatch = true;
      localObject1 = executeDirectBatchStatement(paramResult);
      localObject1 = performPostExecute(paramResult, (Result)localObject1);
      return localObject1;
    case 37:
      try
      {
        localObject1 = this.statementManager.compile(this, paramResult);
      }
      catch (Throwable localThrowable7)
      {
        localObject2 = paramResult.getMainString();
        if (this.database.getProperties().getErrorLevel() == 1)
          localObject2 = null;
        return Result.newErrorResult(localThrowable7, (String)localObject2);
      }
      Result localResult1 = Result.newPrepareResponse((Statement)localObject1);
      if ((((Statement)localObject1).getType() == 85) || (((Statement)localObject1).getType() == 7))
        this.sessionData.setResultSetProperties(paramResult, localResult1);
      localResult1 = performPostExecute(paramResult, localResult1);
      return localResult1;
    case 40:
      closeNavigator(paramResult.getResultId());
      return Result.updateZeroResult;
    case 41:
      localObject1 = executeResultUpdate(paramResult);
      localObject1 = performPostExecute(paramResult, (Result)localObject1);
      return localObject1;
    case 36:
      this.statementManager.freeStatement(paramResult.getStatementID());
      return Result.updateZeroResult;
    case 7:
      int j = paramResult.getStatementType();
      return getAttributesResult(j);
    case 6:
      return setAttributes(paramResult);
    case 33:
      switch (paramResult.getActionType())
      {
      case 0:
        try
        {
          commit(false);
        }
        catch (Throwable localThrowable1)
        {
          return Result.newErrorResult(localThrowable1);
        }
      case 6:
        try
        {
          commit(true);
        }
        catch (Throwable localThrowable2)
        {
          return Result.newErrorResult(localThrowable2);
        }
      case 1:
        rollback(false);
        break;
      case 7:
        rollback(true);
        break;
      case 4:
        try
        {
          String str = paramResult.getMainString();
          releaseSavepoint(str);
        }
        catch (Throwable localThrowable3)
        {
          return Result.newErrorResult(localThrowable3);
        }
      case 2:
        try
        {
          rollbackToSavepoint(paramResult.getMainString());
        }
        catch (Throwable localThrowable4)
        {
          return Result.newErrorResult(localThrowable4);
        }
      case 12:
        try
        {
          prepareCommit();
        }
        catch (Throwable localThrowable5)
        {
          return Result.newErrorResult(localThrowable5);
        }
      case 3:
      case 5:
      case 8:
      case 9:
      case 10:
      case 11:
      }
      return Result.updateZeroResult;
    case 38:
      switch (paramResult.getConnectionAttrType())
      {
      case 10027:
        try
        {
          savepoint(paramResult.getMainString());
        }
        catch (Throwable localThrowable6)
        {
          return Result.newErrorResult(localThrowable6);
        }
      }
      return Result.updateZeroResult;
    case 13:
      return this.sessionData.getDataResultSlice(paramResult.getResultId(), paramResult.getUpdateCount(), paramResult.getFetchSize());
    case 32:
      close();
      return Result.updateZeroResult;
    case 10:
    case 11:
    case 12:
    case 14:
    case 15:
    case 16:
    case 17:
    case 19:
    case 20:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:
    case 39:
    }
    return Result.newErrorResult(Error.runtimeError(201, "Session"));
  }

  private Result performPostExecute(Result paramResult1, Result paramResult2)
  {
    if (paramResult2.mode == 3)
      paramResult2 = this.sessionData.getDataResultHead(paramResult1, paramResult2, this.isNetwork);
    if ((this.sqlWarnings != null) && (this.sqlWarnings.size() > 0))
    {
      if (paramResult2.mode == 1)
        paramResult2 = new Result(1, paramResult2.getUpdateCount());
      HsqlException[] arrayOfHsqlException = getAndClearWarnings();
      paramResult2.addWarnings(arrayOfHsqlException);
    }
    return paramResult2;
  }

  public RowSetNavigatorClient getRows(long paramLong, int paramInt1, int paramInt2)
  {
    return this.sessionData.getRowSetSlice(paramLong, paramInt1, paramInt2);
  }

  public synchronized void closeNavigator(long paramLong)
  {
    this.sessionData.closeNavigator(paramLong);
  }

  public Result executeDirectStatement(Result paramResult)
  {
    String str = paramResult.getMainString();
    int i = paramResult.getUpdateCount();
    if (i == -1)
    {
      this.sessionContext.currentMaxRows = 0;
    }
    else if (this.sessionMaxRows == 0)
    {
      this.sessionContext.currentMaxRows = i;
    }
    else
    {
      this.sessionContext.currentMaxRows = this.sessionMaxRows;
      this.sessionMaxRows = 0;
    }
    HsqlArrayList localHsqlArrayList;
    try
    {
      localHsqlArrayList = this.parser.compileStatements(str, paramResult);
    }
    catch (Throwable localThrowable)
    {
      return Result.newErrorResult(localThrowable);
    }
    Result localResult = null;
    for (int j = 0; j < localHsqlArrayList.size(); j++)
    {
      Statement localStatement = (Statement)localHsqlArrayList.get(j);
      localStatement.setGeneratedColumnInfo(paramResult.getGeneratedResultType(), paramResult.getGeneratedResultMetaData());
      localResult = executeCompiledStatement(localStatement, ValuePool.emptyObjectArray);
      if (localResult.mode == 2)
        break;
    }
    return localResult;
  }

  public Result executeDirectStatement(String paramString)
  {
    try
    {
      Statement localStatement = compileStatement(paramString);
      Result localResult = executeCompiledStatement(localStatement, ValuePool.emptyObjectArray);
      return localResult;
    }
    catch (HsqlException localHsqlException)
    {
      return Result.newErrorResult(localHsqlException);
    }
  }

  public Result executeCompiledStatement(Statement paramStatement, Object[] paramArrayOfObject)
  {
    if (this.abortTransaction)
    {
      rollback(false);
      return Result.newErrorResult(Error.error(4861));
    }
    if ((this.sessionContext.depth > 0) && ((this.sessionContext.noSQL.booleanValue()) || (paramStatement.isAutoCommitStatement())))
      return Result.newErrorResult(Error.error(6000));
    if (paramStatement.isAutoCommitStatement())
    {
      if (isReadOnly())
        return Result.newErrorResult(Error.error(3706));
      try
      {
        commit(false);
      }
      catch (HsqlException localHsqlException)
      {
        this.database.logger.logInfoEvent("Exception at commit");
      }
    }
    this.sessionContext.currentStatement = paramStatement;
    boolean bool = paramStatement.isTransactionStatement();
    Result localResult;
    if (!bool)
    {
      if (this.database.logger.getSqlEventLogLevel() >= 2)
      {
        this.sessionContext.setDynamicArguments(paramArrayOfObject);
        this.database.logger.logStatementEvent(this, paramStatement, paramArrayOfObject, 2);
      }
      localResult = paramStatement.execute(this);
      this.sessionContext.currentStatement = null;
      return localResult;
    }
    while (true)
    {
      this.actionIndex = this.rowActionList.size();
      this.database.txManager.beginAction(this, paramStatement);
      paramStatement = this.sessionContext.currentStatement;
      if (paramStatement == null)
        return Result.newErrorResult(Error.error(1252));
      if (this.abortTransaction)
      {
        rollback(false);
        this.sessionContext.currentStatement = null;
        return Result.newErrorResult(Error.error(4861));
      }
      try
      {
        this.latch.await();
      }
      catch (InterruptedException localInterruptedException1)
      {
        this.abortTransaction = true;
      }
      if (this.abortTransaction)
      {
        rollback(false);
        this.sessionContext.currentStatement = null;
        return Result.newErrorResult(Error.error(4861));
      }
      this.database.txManager.beginActionResume(this);
      this.sessionContext.setDynamicArguments(paramArrayOfObject);
      if (this.database.logger.getSqlEventLogLevel() >= 2)
        this.database.logger.logStatementEvent(this, paramStatement, paramArrayOfObject, 2);
      localResult = paramStatement.execute(this);
      this.lockStatement = this.sessionContext.currentStatement;
      endAction(localResult);
      if (this.abortTransaction)
      {
        rollback(false);
        this.sessionContext.currentStatement = null;
        return Result.newErrorResult(Error.error(localResult.getException(), 4861, null));
      }
      if (!this.redoAction)
        break;
      this.redoAction = false;
      try
      {
        this.latch.await();
      }
      catch (InterruptedException localInterruptedException2)
      {
        this.abortTransaction = true;
      }
    }
    if ((this.sessionContext.depth == 0) && ((this.sessionContext.isAutoCommit.booleanValue()) || (paramStatement.isAutoCommitStatement())))
      try
      {
        if (localResult.mode == 2)
          rollback(false);
        else
          commit(false);
      }
      catch (Exception localException)
      {
        this.sessionContext.currentStatement = null;
        return Result.newErrorResult(Error.error(4861, localException));
      }
    this.sessionContext.currentStatement = null;
    return localResult;
  }

  private Result executeCompiledBatchStatement(Result paramResult)
  {
    Statement localStatement = paramResult.statement;
    if ((localStatement == null) || (localStatement.compileTimestamp < this.database.schemaManager.schemaChangeTimestamp))
    {
      long l = paramResult.getStatementID();
      localStatement = this.statementManager.getStatement(this, l);
      if (localStatement == null)
        return Result.newErrorResult(Error.error(1252));
    }
    int i = 0;
    RowSetNavigator localRowSetNavigator1 = paramResult.initialiseNavigator();
    int[] arrayOfInt = new int[localRowSetNavigator1.getSize()];
    Result localResult1 = null;
    if (localStatement.hasGeneratedColumns())
      localResult1 = Result.newGeneratedDataResult(localStatement.generatedResultMetaData());
    Object localObject = null;
    while (localRowSetNavigator1.hasNext())
    {
      Object[] arrayOfObject1 = (Object[])localRowSetNavigator1.getNext();
      Result localResult2 = executeCompiledStatement(localStatement, arrayOfObject1);
      if (localResult2.isUpdateCount())
      {
        if (localStatement.hasGeneratedColumns())
        {
          RowSetNavigator localRowSetNavigator2 = localResult2.getChainedResult().getNavigator();
          while (localRowSetNavigator2.hasNext())
          {
            Object[] arrayOfObject2 = localRowSetNavigator2.getNext();
            localResult1.getNavigator().add(arrayOfObject2);
          }
        }
        arrayOfInt[(i++)] = localResult2.getUpdateCount();
      }
      else if (localResult2.isData())
      {
        arrayOfInt[(i++)] = -2;
      }
      else if (localResult2.mode == 43)
      {
        arrayOfInt[(i++)] = -2;
      }
      else
      {
        if (localResult2.mode == 2)
        {
          arrayOfInt = ArrayUtil.arraySlice(arrayOfInt, 0, i);
          localObject = localResult2;
          break;
        }
        throw Error.runtimeError(201, "Session");
      }
    }
    return Result.newBatchedExecuteResponse(arrayOfInt, localResult1, localObject);
  }

  private Result executeDirectBatchStatement(Result paramResult)
  {
    int i = 0;
    RowSetNavigator localRowSetNavigator = paramResult.initialiseNavigator();
    int[] arrayOfInt = new int[localRowSetNavigator.getSize()];
    Object localObject = null;
    while (localRowSetNavigator.hasNext())
    {
      Object[] arrayOfObject = (Object[])localRowSetNavigator.getNext();
      String str = (String)arrayOfObject[0];
      Result localResult;
      try
      {
        localResult = executeDirectStatement(str);
      }
      catch (Throwable localThrowable)
      {
        localResult = Result.newErrorResult(localThrowable);
      }
      if (localResult.isUpdateCount())
      {
        arrayOfInt[(i++)] = localResult.getUpdateCount();
      }
      else if (localResult.isData())
      {
        arrayOfInt[(i++)] = -2;
      }
      else if (localResult.mode == 43)
      {
        arrayOfInt[(i++)] = -2;
      }
      else
      {
        if (localResult.mode == 2)
        {
          arrayOfInt = ArrayUtil.arraySlice(arrayOfInt, 0, i);
          localObject = localResult;
          break;
        }
        throw Error.runtimeError(201, "Session");
      }
    }
    return Result.newBatchedExecuteResponse(arrayOfInt, null, localObject);
  }

  private Result executeResultUpdate(Result paramResult)
  {
    long l = paramResult.getResultId();
    int i = paramResult.getActionType();
    Result localResult1 = this.sessionData.getDataResult(l);
    if (localResult1 == null)
      return Result.newErrorResult(Error.error(3601));
    Object[] arrayOfObject = (Object[])paramResult.valueData;
    Type[] arrayOfType = paramResult.metaData.columnTypes;
    StatementQuery localStatementQuery = (StatementQuery)localResult1.getStatement();
    QueryExpression localQueryExpression = localStatementQuery.queryExpression;
    Table localTable = localQueryExpression.getBaseTable();
    int[] arrayOfInt = localQueryExpression.getBaseTableColumnMap();
    this.sessionContext.rowUpdateStatement.setRowActionProperties(localResult1, i, localTable, arrayOfType, arrayOfInt);
    Result localResult2 = executeCompiledStatement(this.sessionContext.rowUpdateStatement, arrayOfObject);
    return localResult2;
  }

  public synchronized TimestampData getCurrentDate()
  {
    resetCurrentTimestamp();
    if (this.currentDate == null)
      this.currentDate = ((TimestampData)Type.SQL_DATE.getValue(this.currentMillis / 1000L, 0, getZoneSeconds()));
    return this.currentDate;
  }

  synchronized TimeData getCurrentTime(boolean paramBoolean)
  {
    resetCurrentTimestamp();
    int i;
    int j;
    if (paramBoolean)
    {
      if (this.currentTime == null)
      {
        i = (int)HsqlDateTime.getNormalisedTime(this.currentMillis) / 1000;
        j = (int)(this.currentMillis % 1000L) * 1000000;
        this.currentTime = new TimeData(i, j, getZoneSeconds());
      }
      return this.currentTime;
    }
    if (this.localTime == null)
    {
      i = (int)HsqlDateTime.getNormalisedTime(this.currentMillis + getZoneSeconds() * 1000) / 1000;
      j = (int)(this.currentMillis % 1000L) * 1000000;
      this.localTime = new TimeData(i, j, 0);
    }
    return this.localTime;
  }

  synchronized TimestampData getCurrentTimestamp(boolean paramBoolean)
  {
    resetCurrentTimestamp();
    int i;
    if (paramBoolean)
    {
      if (this.currentTimestamp == null)
      {
        i = (int)(this.currentMillis % 1000L) * 1000000;
        this.currentTimestamp = new TimestampData(this.currentMillis / 1000L, i, getZoneSeconds());
      }
      return this.currentTimestamp;
    }
    if (this.localTimestamp == null)
    {
      i = (int)(this.currentMillis % 1000L) * 1000000;
      this.localTimestamp = new TimestampData(this.currentMillis / 1000L + getZoneSeconds(), i, 0);
    }
    return this.localTimestamp;
  }

  private void resetCurrentTimestamp()
  {
    if (this.currentTimestampSCN != this.actionTimestamp)
    {
      this.currentTimestampSCN = this.actionTimestamp;
      this.currentMillis = System.currentTimeMillis();
      this.currentDate = null;
      this.currentTimestamp = null;
      this.localTimestamp = null;
      this.currentTime = null;
      this.localTime = null;
    }
  }

  public int getZoneSeconds()
  {
    return this.timeZoneSeconds;
  }

  public void setZoneSeconds(int paramInt)
  {
    if (paramInt == this.sessionTimeZoneSeconds)
    {
      this.calendar = null;
      this.timeZoneSeconds = this.sessionTimeZoneSeconds;
    }
    else
    {
      TimeZone localTimeZone = TimeZone.getDefault();
      localTimeZone.setRawOffset(paramInt * 1000);
      this.calendar = new GregorianCalendar(localTimeZone);
      this.timeZoneSeconds = paramInt;
    }
  }

  private Result getAttributesResult(int paramInt)
  {
    Result localResult = Result.newSessionAttributesResult();
    Object[] arrayOfObject = localResult.getSingleRowData();
    arrayOfObject[0] = ValuePool.getInt(paramInt);
    switch (paramInt)
    {
    case 0:
      arrayOfObject[1] = ValuePool.getInt(this.isolationLevel);
      break;
    case 1:
      arrayOfObject[2] = this.sessionContext.isAutoCommit;
      break;
    case 2:
      arrayOfObject[2] = this.sessionContext.isReadOnly;
      break;
    case 3:
      arrayOfObject[3] = this.database.getCatalogName().name;
    }
    return localResult;
  }

  private Result setAttributes(Result paramResult)
  {
    Object[] arrayOfObject = paramResult.getSessionAttributes();
    int i = ((Integer)arrayOfObject[0]).intValue();
    try
    {
      boolean bool;
      switch (i)
      {
      case 1:
        bool = ((Boolean)arrayOfObject[2]).booleanValue();
        setAutoCommit(bool);
        break;
      case 2:
        bool = ((Boolean)arrayOfObject[2]).booleanValue();
        setReadOnlyDefault(bool);
        break;
      case 0:
        int j = ((Integer)arrayOfObject[1]).intValue();
        setIsolationDefault(j);
        break;
      case 3:
        String str = (String)arrayOfObject[3];
        setCatalog(str);
      }
    }
    catch (HsqlException localHsqlException)
    {
      return Result.newErrorResult(localHsqlException);
    }
    return Result.updateZeroResult;
  }

  public synchronized Object getAttribute(int paramInt)
  {
    switch (paramInt)
    {
    case 0:
      return ValuePool.getInt(this.isolationLevel);
    case 1:
      return this.sessionContext.isAutoCommit;
    case 2:
      return this.isReadOnlyDefault ? Boolean.TRUE : Boolean.FALSE;
    case 3:
      return this.database.getCatalogName().name;
    }
    return null;
  }

  public synchronized void setAttribute(int paramInt, Object paramObject)
  {
    boolean bool;
    switch (paramInt)
    {
    case 1:
      bool = ((Boolean)paramObject).booleanValue();
      setAutoCommit(bool);
      break;
    case 2:
      bool = ((Boolean)paramObject).booleanValue();
      setReadOnlyDefault(bool);
      break;
    case 0:
      int i = ((Integer)paramObject).intValue();
      setIsolationDefault(i);
      break;
    case 3:
      String str = (String)paramObject;
      setCatalog(str);
    }
  }

  public BlobDataID createBlob(long paramLong)
  {
    long l = this.database.lobManager.createBlob(this, paramLong);
    if (l == 0L)
      throw Error.error(3474);
    this.sessionData.registerNewLob(l);
    return new BlobDataID(l);
  }

  public ClobDataID createClob(long paramLong)
  {
    long l = this.database.lobManager.createClob(this, paramLong);
    if (l == 0L)
      throw Error.error(3474);
    this.sessionData.registerNewLob(l);
    return new ClobDataID(l);
  }

  public void registerResultLobs(Result paramResult)
  {
    this.sessionData.registerLobForResult(paramResult);
  }

  public void allocateResultLob(ResultLob paramResultLob, InputStream paramInputStream)
  {
    this.sessionData.allocateLobForResult(paramResultLob, paramInputStream);
  }

  Result performLOBOperation(ResultLob paramResultLob)
  {
    long l = paramResultLob.getLobID();
    int i = paramResultLob.getSubType();
    switch (i)
    {
    case 11:
      return this.database.lobManager.getLob(l, paramResultLob.getOffset(), paramResultLob.getBlockLength());
    case 10:
      return this.database.lobManager.getLength(l);
    case 1:
      return this.database.lobManager.getBytes(l, paramResultLob.getOffset(), (int)paramResultLob.getBlockLength());
    case 2:
      return this.database.lobManager.setBytes(l, paramResultLob.getOffset(), paramResultLob.getByteArray(), (int)paramResultLob.getBlockLength());
    case 3:
      return this.database.lobManager.getChars(l, paramResultLob.getOffset(), (int)paramResultLob.getBlockLength());
    case 4:
      return this.database.lobManager.setChars(l, paramResultLob.getOffset(), paramResultLob.getCharArray());
    case 9:
      return this.database.lobManager.truncate(l, paramResultLob.getOffset());
    case 12:
      return this.database.lobManager.createDuplicateLob(l);
    case 5:
    case 6:
    case 7:
    case 8:
      throw Error.error(1551);
    }
    throw Error.runtimeError(201, "Session");
  }

  public String getInternalConnectionURL()
  {
    return "jdbc:hsqldb:" + this.database.getURI();
  }

  boolean isProcessingScript()
  {
    return this.isProcessingScript;
  }

  boolean isProcessingLog()
  {
    return this.isProcessingLog;
  }

  public void setSchema(String paramString)
  {
    this.currentSchema = this.database.schemaManager.getSchemaHsqlName(paramString);
  }

  public void setCatalog(String paramString)
  {
    if (this.database.getCatalogName().name.equals(paramString))
      return;
    throw Error.error(4840);
  }

  HsqlNameManager.HsqlName getSchemaHsqlName(String paramString)
  {
    return paramString == null ? this.currentSchema : this.database.schemaManager.getSchemaHsqlName(paramString);
  }

  public String getSchemaName(String paramString)
  {
    return paramString == null ? this.currentSchema.name : this.database.schemaManager.getSchemaName(paramString);
  }

  public void setCurrentSchemaHsqlName(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.currentSchema = paramHsqlName;
  }

  public HsqlNameManager.HsqlName getCurrentSchemaHsqlName()
  {
    return this.currentSchema;
  }

  public int getResultMemoryRowCount()
  {
    return this.resultMaxMemoryRows;
  }

  public void setResultMemoryRowCount(int paramInt)
  {
    if (this.database.logger.getTempDirectoryPath() != null)
    {
      if (paramInt < 0)
        paramInt = 0;
      this.resultMaxMemoryRows = paramInt;
    }
  }

  public void addWarning(HsqlException paramHsqlException)
  {
    if (this.sqlWarnings == null)
      this.sqlWarnings = new HsqlDeque();
    if (this.sqlWarnings.size() > 9)
      this.sqlWarnings.removeFirst();
    int i = this.sqlWarnings.indexOf(paramHsqlException);
    if (i >= 0)
      this.sqlWarnings.remove(i);
    this.sqlWarnings.add(paramHsqlException);
  }

  public HsqlException[] getAndClearWarnings()
  {
    if (this.sqlWarnings == null)
      return HsqlException.emptyArray;
    HsqlException[] arrayOfHsqlException = new HsqlException[this.sqlWarnings.size()];
    this.sqlWarnings.toArray(arrayOfHsqlException);
    this.sqlWarnings.clear();
    return arrayOfHsqlException;
  }

  public HsqlException getLastWarning()
  {
    if ((this.sqlWarnings == null) || (this.sqlWarnings.size() == 0))
      return null;
    return (HsqlException)this.sqlWarnings.getLast();
  }

  public void clearWarnings()
  {
    if (this.sqlWarnings != null)
      this.sqlWarnings.clear();
  }

  public Calendar getCalendar()
  {
    if (this.calendar == null)
      if (this.zoneString == null)
      {
        this.calendar = new GregorianCalendar();
      }
      else
      {
        TimeZone localTimeZone = TimeZone.getTimeZone(this.zoneString);
        this.calendar = new GregorianCalendar(localTimeZone);
      }
    return this.calendar;
  }

  public Type.TypedComparator getComparator()
  {
    if (this.typedComparator == null)
      this.typedComparator = Type.newComparator(this);
    return this.typedComparator;
  }

  public double random(long paramLong)
  {
    if (this.seed != paramLong)
    {
      this.randomGenerator.setSeed(paramLong);
      this.seed = paramLong;
    }
    return this.randomGenerator.nextDouble();
  }

  public double random()
  {
    return this.randomGenerator.nextDouble();
  }

  public Scanner getScanner()
  {
    if (this.secondaryScanner == null)
      this.secondaryScanner = new Scanner();
    return this.secondaryScanner;
  }

  public HsqlProperties getClientProperties()
  {
    if (this.clientProperties == null)
    {
      this.clientProperties = new HsqlProperties();
      this.clientProperties.setProperty("jdbc.translate_tti_types", this.database.sqlTranslateTTI);
    }
    return this.clientProperties;
  }

  public SimpleDateFormat getSimpleDateFormatGMT()
  {
    if (this.simpleDateFormatGMT == null)
    {
      this.simpleDateFormatGMT = new SimpleDateFormat("MMMM", Locale.ENGLISH);
      GregorianCalendar localGregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
      this.simpleDateFormatGMT.setCalendar(localGregorianCalendar);
    }
    return this.simpleDateFormatGMT;
  }

  void logSequences()
  {
    HashMap localHashMap = this.sessionData.sequenceUpdateMap;
    if ((localHashMap == null) || (localHashMap.isEmpty()))
      return;
    Iterator localIterator = localHashMap.keySet().iterator();
    int i = 0;
    int j = localHashMap.size();
    while (i < j)
    {
      NumberSequence localNumberSequence = (NumberSequence)localIterator.next();
      this.database.logger.writeSequenceStatement(this, localNumberSequence);
      i++;
    }
    this.sessionData.sequenceUpdateMap.clear();
  }

  String getStartTransactionSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("START").append(' ').append("TRANSACTION");
    if (this.isolationLevel != this.isolationLevelDefault)
    {
      localStringBuffer.append(' ');
      appendIsolationSQL(localStringBuffer, this.isolationLevel);
    }
    return localStringBuffer.toString();
  }

  String getTransactionIsolationSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("SET").append(' ').append("TRANSACTION");
    localStringBuffer.append(' ');
    appendIsolationSQL(localStringBuffer, this.isolationLevel);
    return localStringBuffer.toString();
  }

  String getSessionIsolationSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("SET").append(' ').append("SESSION");
    localStringBuffer.append(' ').append("CHARACTERISTICS").append(' ');
    localStringBuffer.append("AS").append(' ').append("TRANSACTION").append(' ');
    appendIsolationSQL(localStringBuffer, this.isolationLevelDefault);
    return localStringBuffer.toString();
  }

  static void appendIsolationSQL(StringBuffer paramStringBuffer, int paramInt)
  {
    paramStringBuffer.append("ISOLATION").append(' ');
    paramStringBuffer.append("LEVEL").append(' ');
    paramStringBuffer.append(getIsolationString(paramInt));
  }

  static String getIsolationString(int paramInt)
  {
    switch (paramInt)
    {
    case 1:
    case 2:
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("READ").append(' ');
      localStringBuffer.append("COMMITTED");
      return localStringBuffer.toString();
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    return "SERIALIZABLE";
  }

  String getSetSchemaStatement()
  {
    return "SET SCHEMA " + this.currentSchema.statementName;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.Session
 * JD-Core Version:    0.6.2
 */