package org.hsqldb;

import org.hsqldb.dbinfo.DatabaseInformation;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FrameworkLogger;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlTimer;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.persist.LobManager;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStoreCollectionDatabase;
import org.hsqldb.result.Result;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.User;
import org.hsqldb.rights.UserManager;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Collation;

public class Database
{
  int databaseID;
  String databaseUniqueName;
  String databaseType;
  private final String canonicalPath;
  public HsqlProperties urlProperties;
  private final String path;
  public Collation collation;
  public DatabaseInformation dbInfo;
  private volatile int dbState;
  public Logger logger;
  boolean databaseReadOnly;
  private boolean filesReadOnly;
  private boolean filesInJar;
  public boolean sqlEnforceTypes = false;
  public boolean sqlEnforceRefs = false;
  public boolean sqlEnforceSize = true;
  public boolean sqlEnforceNames = false;
  public boolean sqlRegularNames = true;
  public boolean sqlEnforceTDCD = true;
  public boolean sqlEnforceTDCU = true;
  public boolean sqlTranslateTTI = true;
  public boolean sqlConcatNulls = true;
  public boolean sqlUniqueNulls = true;
  public boolean sqlNullsFirst = true;
  public boolean sqlConvertTruncate = true;
  public int sqlAvgScale = 0;
  public boolean sqlDoubleNaN = true;
  public boolean sqlLongvarIsLob = false;
  public boolean sqlSyntaxDb2 = false;
  public boolean sqlSyntaxMss = false;
  public boolean sqlSyntaxMys = false;
  public boolean sqlSyntaxOra = false;
  public boolean sqlSyntaxPgs = false;
  private boolean isReferentialIntegrity = true;
  public HsqlDatabaseProperties databaseProperties;
  private final boolean shutdownOnNoConnection;
  int resultMaxMemoryRows;
  public UserManager userManager;
  public GranteeManager granteeManager;
  public HsqlNameManager nameManager;
  public SessionManager sessionManager;
  public TransactionManager txManager;
  public int defaultIsolationLevel = 2;
  public boolean txConflictRollback = true;
  public SchemaManager schemaManager;
  public PersistentStoreCollectionDatabase persistentStoreCollection;
  public LobManager lobManager;
  public CheckpointRunner checkpointRunner;
  public static final int DATABASE_ONLINE = 1;
  public static final int DATABASE_OPENING = 2;
  public static final int DATABASE_CLOSING = 3;
  public static final int DATABASE_SHUTDOWN = 4;
  public static final int CLOSEMODE_IMMEDIATELY = 1;
  public static final int CLOSEMODE_NORMAL = 2;
  public static final int CLOSEMODE_COMPACT = 3;
  public static final int CLOSEMODE_SCRIPT = 4;
  
  Database(String paramString1, String paramString2, String paramString3, HsqlProperties paramHsqlProperties)
  {
    setState(4);
    this.databaseType = paramString1;
    this.path = paramString2;
    this.canonicalPath = paramString3;
    this.urlProperties = paramHsqlProperties;
    if (this.databaseType == "res:")
    {
      this.filesInJar = true;
      this.filesReadOnly = true;
    }
    this.logger = new Logger(this);
    this.shutdownOnNoConnection = this.urlProperties.isPropertyTrue("shutdown");
    this.lobManager = new LobManager(this);
  }
  
  synchronized void open()
  {
    if (!isShutdown()) {
      return;
    }
    reopen();
  }
  
  void reopen()
  {
    boolean bool = false;
    setState(2);
    try
    {
      this.nameManager = new HsqlNameManager(this);
      this.granteeManager = new GranteeManager(this);
      this.userManager = new UserManager(this);
      this.schemaManager = new SchemaManager(this);
      this.persistentStoreCollection = new PersistentStoreCollectionDatabase();
      this.isReferentialIntegrity = true;
      this.sessionManager = new SessionManager(this);
      this.collation = Collation.newDatabaseInstance();
      this.dbInfo = DatabaseInformation.newDatabaseInformation(this);
      this.txManager = new TransactionManager2PL(this);
      this.lobManager.createSchema();
      this.sessionManager.getSysLobSession().setSchema("SYSTEM_LOBS");
      this.schemaManager.setSchemaChangeTimestamp();
      this.schemaManager.createSystemTables();
      this.logger.openPersistence();
      bool = this.logger.isNewDatabase;
      if (bool)
      {
        String str1 = this.urlProperties.getProperty("user", "SA");
        String str2 = this.urlProperties.getProperty("password", "");
        this.userManager.createFirstUser(str1, str2);
        this.schemaManager.createPublicSchema();
        this.lobManager.initialiseLobSpace();
        this.logger.checkpoint(false);
      }
      this.lobManager.open();
      this.dbInfo.setWithContent(true);
      this.checkpointRunner = new CheckpointRunner();
    }
    catch (Throwable localThrowable)
    {
      this.logger.closePersistence(1);
      this.logger.releaseLock();
      setState(4);
      clearStructures();
      DatabaseManager.removeDatabase(this);
      HsqlException localHsqlException;
      if (!(localThrowable instanceof HsqlException)) {
        localHsqlException = Error.error(458, localThrowable);
      }
      this.logger.logSevereEvent("could not reopen database", localHsqlException);
      throw ((HsqlException)localHsqlException);
    }
    setState(1);
  }
  
  void clearStructures()
  {
    if (this.schemaManager != null) {
      this.schemaManager.release();
    }
    if (this.checkpointRunner != null) {
      this.checkpointRunner.stop();
    }
    this.granteeManager = null;
    this.userManager = null;
    this.nameManager = null;
    this.schemaManager = null;
    this.sessionManager = null;
    this.dbInfo = null;
    this.checkpointRunner = null;
  }
  
  public int getDatabaseID()
  {
    return this.databaseID;
  }
  
  public String getUniqueName()
  {
    return this.databaseUniqueName;
  }
  
  public void setUniqueName(String paramString)
  {
    this.databaseUniqueName = paramString;
  }
  
  public String getType()
  {
    return this.databaseType;
  }
  
  public String getPath()
  {
    return this.path;
  }
  
  public HsqlNameManager.HsqlName getCatalogName()
  {
    return this.nameManager.getCatalogName();
  }
  
  public HsqlDatabaseProperties getProperties()
  {
    return this.databaseProperties;
  }
  
  public SessionManager getSessionManager()
  {
    return this.sessionManager;
  }
  
  public boolean isReadOnly()
  {
    return this.databaseReadOnly;
  }
  
  boolean isShutdown()
  {
    return this.dbState == 4;
  }
  
  synchronized Session connect(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    if (paramString1.equalsIgnoreCase("SA")) {
      paramString1 = "SA";
    }
    User localUser = this.userManager.getUser(paramString1, paramString2);
    Session localSession = this.sessionManager.newSession(this, localUser, this.databaseReadOnly, true, paramString3, paramInt);
    return localSession;
  }
  
  public void setReadOnly()
  {
    this.databaseReadOnly = true;
    this.filesReadOnly = true;
  }
  
  public void setFilesReadOnly()
  {
    this.filesReadOnly = true;
  }
  
  public boolean isFilesReadOnly()
  {
    return this.filesReadOnly;
  }
  
  public boolean isFilesInJar()
  {
    return this.filesInJar;
  }
  
  public UserManager getUserManager()
  {
    return this.userManager;
  }
  
  public GranteeManager getGranteeManager()
  {
    return this.granteeManager;
  }
  
  public void setReferentialIntegrity(boolean paramBoolean)
  {
    this.isReferentialIntegrity = paramBoolean;
  }
  
  public boolean isReferentialIntegrity()
  {
    return this.isReferentialIntegrity;
  }
  
  public int getResultMaxMemoryRows()
  {
    return this.resultMaxMemoryRows;
  }
  
  public void setResultMaxMemoryRows(int paramInt)
  {
    this.resultMaxMemoryRows = paramInt;
  }
  
  public void setStrictNames(boolean paramBoolean)
  {
    this.sqlEnforceNames = paramBoolean;
  }
  
  public void setRegularNames(boolean paramBoolean)
  {
    this.sqlRegularNames = paramBoolean;
    this.nameManager.setSqlRegularNames(paramBoolean);
  }
  
  public void setStrictColumnSize(boolean paramBoolean)
  {
    this.sqlEnforceSize = paramBoolean;
  }
  
  public void setStrictReferences(boolean paramBoolean)
  {
    this.sqlEnforceRefs = paramBoolean;
  }
  
  public void setStrictTypes(boolean paramBoolean)
  {
    this.sqlEnforceTypes = paramBoolean;
  }
  
  public void setStrictTDCD(boolean paramBoolean)
  {
    this.sqlEnforceTDCD = paramBoolean;
  }
  
  public void setStrictTDCU(boolean paramBoolean)
  {
    this.sqlEnforceTDCU = paramBoolean;
  }
  
  public void setTranslateTTI(boolean paramBoolean)
  {
    this.sqlTranslateTTI = paramBoolean;
  }
  
  public void setNullsFirst(boolean paramBoolean)
  {
    this.sqlNullsFirst = paramBoolean;
  }
  
  public void setConcatNulls(boolean paramBoolean)
  {
    this.sqlConcatNulls = paramBoolean;
  }
  
  public void setUniqueNulls(boolean paramBoolean)
  {
    this.sqlUniqueNulls = paramBoolean;
  }
  
  public void setConvertTrunc(boolean paramBoolean)
  {
    this.sqlConvertTruncate = paramBoolean;
  }
  
  public void setDoubleNaN(boolean paramBoolean)
  {
    this.sqlDoubleNaN = paramBoolean;
  }
  
  public void setAvgScale(int paramInt)
  {
    this.sqlAvgScale = paramInt;
  }
  
  public void setLongVarIsLob(boolean paramBoolean)
  {
    this.sqlLongvarIsLob = paramBoolean;
  }
  
  public void setSyntaxDb2(boolean paramBoolean)
  {
    this.sqlSyntaxDb2 = paramBoolean;
  }
  
  public void setSyntaxMss(boolean paramBoolean)
  {
    this.sqlSyntaxMss = paramBoolean;
  }
  
  public void setSyntaxMys(boolean paramBoolean)
  {
    this.sqlSyntaxMys = paramBoolean;
  }
  
  public void setSyntaxOra(boolean paramBoolean)
  {
    this.sqlSyntaxOra = paramBoolean;
  }
  
  public void setSyntaxPgs(boolean paramBoolean)
  {
    this.sqlSyntaxPgs = paramBoolean;
  }
  
  protected void finalize()
  {
    if (getState() != 1) {
      return;
    }
    try
    {
      close(1);
    }
    catch (HsqlException localHsqlException) {}
  }
  
  void closeIfLast()
  {
    if ((this.sessionManager.isEmpty()) && (this.dbState == 1)) {
      if (this.shutdownOnNoConnection) {
        try
        {
          close(2);
        }
        catch (HsqlException localHsqlException) {}
      } else {
        this.logger.synchLog();
      }
    }
  }
  
  public void close(int paramInt)
  {
    HsqlException localHsqlException = null;
    synchronized (this)
    {
      if (getState() != 1) {
        return;
      }
      setState(3);
    }
    this.sessionManager.closeAllSessions();
    if (this.filesReadOnly) {
      paramInt = 1;
    }
    this.logger.closePersistence(paramInt);
    this.lobManager.close();
    this.sessionManager.close();
    try
    {
      if (paramInt == 3)
      {
        clearStructures();
        reopen();
        setState(3);
        this.logger.closePersistence(2);
        this.lobManager.close();
      }
    }
    catch (Throwable localThrowable)
    {
      if ((localThrowable instanceof HsqlException)) {
        localHsqlException = (HsqlException)localThrowable;
      } else {
        localHsqlException = Error.error(458, localThrowable);
      }
    }
    this.lobManager = null;
    this.logger.releaseLock();
    setState(4);
    clearStructures();
    DatabaseManager.removeDatabase(this);
    FrameworkLogger.clearLoggers("hsqldb.db." + getUniqueName());
    if (localHsqlException != null) {
      throw localHsqlException;
    }
  }
  
  private void setState(int paramInt)
  {
    this.dbState = paramInt;
  }
  
  int getState()
  {
    return this.dbState;
  }
  
  String getStateString()
  {
    int i = getState();
    switch (i)
    {
    case 3: 
      return "DATABASE_CLOSING";
    case 1: 
      return "DATABASE_ONLINE";
    case 2: 
      return "DATABASE_OPENING";
    case 4: 
      return "DATABASE_SHUTDOWN";
    }
    return "UNKNOWN";
  }
  
  public String[] getSettingsSQL()
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    StringBuffer localStringBuffer = new StringBuffer();
    if (!getCatalogName().name.equals("PUBLIC"))
    {
      localObject = getCatalogName().statementName;
      localStringBuffer.append("ALTER CATALOG PUBLIC RENAME TO ").append((String)localObject);
      localHsqlArrayList.add(localStringBuffer.toString());
      localStringBuffer.setLength(0);
    }
    if (!this.collation.isDefaultCollation()) {
      localHsqlArrayList.add(this.collation.getDatabaseCollationSQL());
    }
    Object localObject = this.schemaManager.getTables("SYSTEM_LOBS");
    for (int i = 0; i < ((HashMappedList)localObject).size(); i++)
    {
      Table localTable = (Table)((HashMappedList)localObject).get(i);
      if (localTable.isCached())
      {
        localStringBuffer.append("SET").append(' ').append("TABLE");
        localStringBuffer.append(' ');
        localStringBuffer.append(localTable.getName().getSchemaQualifiedStatementName());
        localStringBuffer.append(' ').append("TYPE").append(' ');
        localStringBuffer.append("CACHED");
        localHsqlArrayList.add(localStringBuffer.toString());
        localStringBuffer.setLength(0);
      }
    }
    String[] arrayOfString = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public Result getScript(boolean paramBoolean)
  {
    Result localResult = Result.newSingleColumnResult("COMMAND");
    String[] arrayOfString = this.logger.getPropertiesSQL(paramBoolean);
    addRows(localResult, arrayOfString);
    arrayOfString = getSettingsSQL();
    addRows(localResult, arrayOfString);
    arrayOfString = getGranteeManager().getSQL();
    addRows(localResult, arrayOfString);
    arrayOfString = this.schemaManager.getSQLArray();
    addRows(localResult, arrayOfString);
    arrayOfString = this.schemaManager.getCommentsArray();
    addRows(localResult, arrayOfString);
    if (paramBoolean)
    {
      arrayOfString = this.schemaManager.getIndexRootsSQL();
      addRows(localResult, arrayOfString);
    }
    arrayOfString = this.schemaManager.getTablePropsSQL(!paramBoolean);
    addRows(localResult, arrayOfString);
    arrayOfString = getUserManager().getAuthenticationSQL();
    addRows(localResult, arrayOfString);
    arrayOfString = getUserManager().getInitialSchemaSQL();
    addRows(localResult, arrayOfString);
    arrayOfString = getGranteeManager().getRightstSQL();
    addRows(localResult, arrayOfString);
    return localResult;
  }
  
  private static void addRows(Result paramResult, String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return;
    }
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramArrayOfString[i];
      paramResult.initialiseNavigator().add(arrayOfString);
    }
  }
  
  public String getURI()
  {
    return this.databaseType + this.canonicalPath;
  }
  
  public String getCanonicalPath()
  {
    return this.canonicalPath;
  }
  
  public HsqlProperties getURLProperties()
  {
    return this.urlProperties;
  }
  
  class CheckpointRunner
    implements Runnable
  {
    private volatile boolean waiting;
    private Object timerTask;
    
    CheckpointRunner() {}
    
    public void run()
    {
      try
      {
        Session localSession = Database.this.sessionManager.newSysSession();
        Statement localStatement = ParserCommand.getAutoCheckpointStatement(Database.this);
        localSession.executeCompiledStatement(localStatement, ValuePool.emptyObjectArray);
        localSession.commit(false);
        localSession.close();
        this.waiting = false;
      }
      catch (Exception localException) {}
    }
    
    public void start()
    {
      if (!Database.this.logger.isLogged()) {
        return;
      }
      synchronized (this)
      {
        if (this.waiting) {
          return;
        }
        this.waiting = true;
      }
      this.timerTask = DatabaseManager.getTimer().scheduleAfter(0L, this);
    }
    
    public void stop()
    {
      HsqlTimer.cancel(this.timerTask);
      this.timerTask = null;
      this.waiting = false;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.Database
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */