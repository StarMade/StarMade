package org.hsqldb.dbinfo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.hsqldb.ColumnSchema;
import org.hsqldb.Constraint;
import org.hsqldb.Database;
import org.hsqldb.Expression;
import org.hsqldb.ExpressionColumn;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.NumberSequence;
import org.hsqldb.Routine;
import org.hsqldb.RoutineSchema;
import org.hsqldb.Schema;
import org.hsqldb.SchemaManager;
import org.hsqldb.SchemaObject;
import org.hsqldb.SchemaObjectSet;
import org.hsqldb.Session;
import org.hsqldb.SessionContext;
import org.hsqldb.SessionManager;
import org.hsqldb.SqlInvariants;
import org.hsqldb.Statement;
import org.hsqldb.Table;
import org.hsqldb.TextTable;
import org.hsqldb.TriggerDef;
import org.hsqldb.View;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.CountUpDownLatch;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LineGroupReader;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.WrapperIterator;
import org.hsqldb.persist.DataFileCache;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.TextCache;
import org.hsqldb.persist.TextFileSettings;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.Right;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;
import org.hsqldb.types.UserTypeModifier;

final class DatabaseInformationFull
  extends DatabaseInformationMain
{
  static final HashMappedList statementMap;
  
  DatabaseInformationFull(Database paramDatabase)
  {
    super(paramDatabase);
  }
  
  protected Table generateTable(Session paramSession, PersistentStore paramPersistentStore, int paramInt)
  {
    switch (paramInt)
    {
    case 15: 
      return SYSTEM_CACHEINFO(paramSession, paramPersistentStore);
    case 16: 
      return SYSTEM_COLUMN_SEQUENCE_USAGE(paramSession, paramPersistentStore);
    case 17: 
      return SYSTEM_COMMENTS(paramSession, paramPersistentStore);
    case 20: 
      return SYSTEM_SESSIONINFO(paramSession, paramPersistentStore);
    case 19: 
      return SYSTEM_PROPERTIES(paramSession, paramPersistentStore);
    case 21: 
      return SYSTEM_SESSIONS(paramSession, paramPersistentStore);
    case 22: 
      return SYSTEM_TEXTTABLES(paramSession, paramPersistentStore);
    case 23: 
      return ADMINISTRABLE_ROLE_AUTHORIZATIONS(paramSession, paramPersistentStore);
    case 24: 
      return APPLICABLE_ROLES(paramSession, paramPersistentStore);
    case 25: 
      return ASSERTIONS(paramSession, paramPersistentStore);
    case 26: 
      return AUTHORIZATIONS(paramSession, paramPersistentStore);
    case 27: 
      return CHARACTER_SETS(paramSession, paramPersistentStore);
    case 28: 
      return CHECK_CONSTRAINT_ROUTINE_USAGE(paramSession, paramPersistentStore);
    case 29: 
      return CHECK_CONSTRAINTS(paramSession, paramPersistentStore);
    case 30: 
      return COLLATIONS(paramSession, paramPersistentStore);
    case 31: 
      return COLUMN_COLUMN_USAGE(paramSession, paramPersistentStore);
    case 32: 
      return COLUMN_DOMAIN_USAGE(paramSession, paramPersistentStore);
    case 34: 
      return COLUMN_UDT_USAGE(paramSession, paramPersistentStore);
    case 36: 
      return CONSTRAINT_COLUMN_USAGE(paramSession, paramPersistentStore);
    case 37: 
      return CONSTRAINT_TABLE_USAGE(paramSession, paramPersistentStore);
    case 35: 
      return COLUMNS(paramSession, paramPersistentStore);
    case 38: 
      return DATA_TYPE_PRIVILEGES(paramSession, paramPersistentStore);
    case 39: 
      return DOMAIN_CONSTRAINTS(paramSession, paramPersistentStore);
    case 40: 
      return DOMAINS(paramSession, paramPersistentStore);
    case 41: 
      return ELEMENT_TYPES(paramSession, paramPersistentStore);
    case 42: 
      return ENABLED_ROLES(paramSession, paramPersistentStore);
    case 44: 
      return JAR_JAR_USAGE(paramSession, paramPersistentStore);
    case 45: 
      return JARS(paramSession, paramPersistentStore);
    case 46: 
      return KEY_COLUMN_USAGE(paramSession, paramPersistentStore);
    case 47: 
      return METHOD_SPECIFICATIONS(paramSession, paramPersistentStore);
    case 48: 
      return MODULE_COLUMN_USAGE(paramSession, paramPersistentStore);
    case 49: 
      return MODULE_PRIVILEGES(paramSession, paramPersistentStore);
    case 50: 
      return MODULE_TABLE_USAGE(paramSession, paramPersistentStore);
    case 51: 
      return MODULES(paramSession, paramPersistentStore);
    case 52: 
      return PARAMETERS(paramSession, paramPersistentStore);
    case 53: 
      return REFERENTIAL_CONSTRAINTS(paramSession, paramPersistentStore);
    case 54: 
      return ROLE_AUTHORIZATION_DESCRIPTORS(paramSession, paramPersistentStore);
    case 55: 
      return ROLE_COLUMN_GRANTS(paramSession, paramPersistentStore);
    case 57: 
      return ROLE_ROUTINE_GRANTS(paramSession, paramPersistentStore);
    case 58: 
      return ROLE_TABLE_GRANTS(paramSession, paramPersistentStore);
    case 60: 
      return ROLE_USAGE_GRANTS(paramSession, paramPersistentStore);
    case 59: 
      return ROLE_UDT_GRANTS(paramSession, paramPersistentStore);
    case 61: 
      return ROUTINE_COLUMN_USAGE(paramSession, paramPersistentStore);
    case 62: 
      return ROUTINE_JAR_USAGE(paramSession, paramPersistentStore);
    case 63: 
      return ROUTINE_PRIVILEGES(paramSession, paramPersistentStore);
    case 64: 
      return ROUTINE_ROUTINE_USAGE(paramSession, paramPersistentStore);
    case 65: 
      return ROUTINE_SEQUENCE_USAGE(paramSession, paramPersistentStore);
    case 66: 
      return ROUTINE_TABLE_USAGE(paramSession, paramPersistentStore);
    case 67: 
      return ROUTINES(paramSession, paramPersistentStore);
    case 68: 
      return SCHEMATA(paramSession, paramPersistentStore);
    case 69: 
      return SEQUENCES(paramSession, paramPersistentStore);
    case 70: 
      return SQL_FEATURES(paramSession, paramPersistentStore);
    case 71: 
      return SQL_IMPLEMENTATION_INFO(paramSession, paramPersistentStore);
    case 72: 
      return SQL_PACKAGES(paramSession, paramPersistentStore);
    case 73: 
      return SQL_PARTS(paramSession, paramPersistentStore);
    case 74: 
      return SQL_SIZING(paramSession, paramPersistentStore);
    case 75: 
      return SQL_SIZING_PROFILES(paramSession, paramPersistentStore);
    case 76: 
      return TABLE_CONSTRAINTS(paramSession, paramPersistentStore);
    case 78: 
      return TABLES(paramSession, paramPersistentStore);
    case 79: 
      return TRANSLATIONS(paramSession, paramPersistentStore);
    case 84: 
      return TRIGGERED_UPDATE_COLUMNS(paramSession, paramPersistentStore);
    case 80: 
      return TRIGGER_COLUMN_USAGE(paramSession, paramPersistentStore);
    case 81: 
      return TRIGGER_ROUTINE_USAGE(paramSession, paramPersistentStore);
    case 82: 
      return TRIGGER_SEQUENCE_USAGE(paramSession, paramPersistentStore);
    case 83: 
      return TRIGGER_TABLE_USAGE(paramSession, paramPersistentStore);
    case 85: 
      return TRIGGERS(paramSession, paramPersistentStore);
    case 87: 
      return UDT_PRIVILEGES(paramSession, paramPersistentStore);
    case 88: 
      return USAGE_PRIVILEGES(paramSession, paramPersistentStore);
    case 89: 
      return USER_DEFINED_TYPES(paramSession, paramPersistentStore);
    case 90: 
      return VIEW_COLUMN_USAGE(paramSession, paramPersistentStore);
    case 91: 
      return VIEW_ROUTINE_USAGE(paramSession, paramPersistentStore);
    case 92: 
      return VIEW_TABLE_USAGE(paramSession, paramPersistentStore);
    case 93: 
      return VIEWS(paramSession, paramPersistentStore);
    }
    return super.generateTable(paramSession, paramPersistentStore, paramInt);
  }
  
  Table SYSTEM_CACHEINFO(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[15];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[15]);
      addColumn(localTable1, "CACHE_FILE", CHARACTER_DATA);
      addColumn(localTable1, "MAX_CACHE_COUNT", CARDINAL_NUMBER);
      addColumn(localTable1, "MAX_CACHE_BYTES", CARDINAL_NUMBER);
      addColumn(localTable1, "CACHE_SIZE", CARDINAL_NUMBER);
      addColumn(localTable1, "CACHE_BYTES", CARDINAL_NUMBER);
      addColumn(localTable1, "FILE_FREE_BYTES", CARDINAL_NUMBER);
      addColumn(localTable1, "FILE_FREE_COUNT", CARDINAL_NUMBER);
      addColumn(localTable1, "FILE_FREE_POS", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[15].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName, new int[] { 0 }, true);
      return localTable1;
    }
    DataFileCache localDataFileCache = null;
    HashSet localHashSet = new HashSet();
    Iterator localIterator2 = this.database.schemaManager.databaseObjectIterator(3);
    while (localIterator2.hasNext())
    {
      Table localTable2 = (Table)localIterator2.next();
      PersistentStore localPersistentStore = localTable2.getRowStore(paramSession);
      if (paramSession.getGrantee().isFullyAccessibleByRole(localTable2.getName()))
      {
        if (localPersistentStore != null) {
          localDataFileCache = localPersistentStore.getCache();
        }
        if (localDataFileCache != null) {
          localHashSet.add(localDataFileCache);
        }
      }
    }
    Iterator localIterator1 = localHashSet.iterator();
    while (localIterator1.hasNext())
    {
      localDataFileCache = (DataFileCache)localIterator1.next();
      Object[] arrayOfObject = localTable1.getEmptyRowData();
      arrayOfObject[0] = FileUtil.getFileUtil().canonicalOrAbsolutePath(localDataFileCache.getFileName());
      arrayOfObject[1] = ValuePool.getLong(localDataFileCache.capacity());
      arrayOfObject[2] = ValuePool.getLong(localDataFileCache.bytesCapacity());
      arrayOfObject[3] = ValuePool.getLong(localDataFileCache.getCachedObjectCount());
      arrayOfObject[4] = ValuePool.getLong(localDataFileCache.getTotalCachedBlockSize());
      arrayOfObject[5] = ValuePool.getLong(localDataFileCache.getTotalFreeBlockSize());
      arrayOfObject[6] = ValuePool.getLong(localDataFileCache.getFreeBlockCount());
      arrayOfObject[7] = ValuePool.getLong(localDataFileCache.getFileFreePos());
      localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    }
    return localTable1;
  }
  
  Table SYSTEM_COLUMN_SEQUENCE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[16];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[16]);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "SEQUENCE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[16].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 3, 4 }, false);
      return localTable1;
    }
    Iterator localIterator = allTables();
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      if (localTable2.hasIdentityColumn())
      {
        OrderedHashSet localOrderedHashSet = paramSession.getGrantee().getColumnsForAllPrivileges(localTable2);
        if (!localOrderedHashSet.isEmpty())
        {
          int i = localTable2.getColumnCount();
          for (int j = 0; j < i; j++)
          {
            ColumnSchema localColumnSchema = localTable2.getColumn(j);
            if (localColumnSchema.isIdentity())
            {
              NumberSequence localNumberSequence = localColumnSchema.getIdentitySequence();
              if ((localNumberSequence.getName() != null) && (localOrderedHashSet.contains(localColumnSchema.getName())))
              {
                Object[] arrayOfObject = localTable1.getEmptyRowData();
                arrayOfObject[0] = this.database.getCatalogName().name;
                arrayOfObject[1] = localTable2.getSchemaName().name;
                arrayOfObject[2] = localTable2.getName().name;
                arrayOfObject[3] = localColumnSchema.getName().name;
                arrayOfObject[4] = this.database.getCatalogName().name;
                arrayOfObject[5] = localNumberSequence.getSchemaName().name;
                arrayOfObject[6] = localNumberSequence.getName().name;
                localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              }
            }
          }
        }
      }
    }
    return localTable1;
  }
  
  Table SYSTEM_COMMENTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[17];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[17]);
      addColumn(localTable, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_TYPE", SQL_IDENTIFIER);
      addColumn(localTable, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COMMENT", CHARACTER_DATA);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[17].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 3, 4 }, false);
      return localTable;
    }
    DITableInfo localDITableInfo = new DITableInfo();
    Iterator localIterator = allTables();
    Object localObject;
    Object[] arrayOfObject;
    while (localIterator.hasNext())
    {
      localObject = (Table)localIterator.next();
      if (paramSession.getGrantee().isAccessible((SchemaObject)localObject))
      {
        localDITableInfo.setTable((Table)localObject);
        int i = ((Table)localObject).getColumnCount();
        for (int j = 0; j < i; j++)
        {
          ColumnSchema localColumnSchema = ((Table)localObject).getColumn(j);
          if (localColumnSchema.getName().comment != null)
          {
            arrayOfObject = localTable.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = ((Table)localObject).getSchemaName().name;
            arrayOfObject[2] = ((Table)localObject).getName().name;
            arrayOfObject[3] = "COLUMN";
            arrayOfObject[4] = localColumnSchema.getName().name;
            arrayOfObject[5] = localColumnSchema.getName().comment;
            localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
        }
        if ((((Table)localObject).getTableType() == 1) || (((Table)localObject).getName().comment != null))
        {
          arrayOfObject = localTable.getEmptyRowData();
          arrayOfObject[0] = this.database.getCatalogName().name;
          arrayOfObject[1] = ((Table)localObject).getSchemaName().name;
          arrayOfObject[2] = ((Table)localObject).getName().name;
          arrayOfObject[3] = ((((Table)localObject).isView()) || (((Table)localObject).getTableType() == 1) ? "VIEW" : "TABLE");
          arrayOfObject[4] = null;
          arrayOfObject[5] = localDITableInfo.getRemark();
          localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        }
      }
    }
    localIterator = this.database.schemaManager.databaseObjectIterator(18);
    while (localIterator.hasNext())
    {
      localObject = (SchemaObject)localIterator.next();
      if ((paramSession.getGrantee().isAccessible((SchemaObject)localObject)) && (((SchemaObject)localObject).getName().comment != null))
      {
        arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = ((SchemaObject)localObject).getSchemaName().name;
        arrayOfObject[2] = ((SchemaObject)localObject).getName().name;
        arrayOfObject[3] = "ROUTINE";
        arrayOfObject[4] = null;
        arrayOfObject[5] = ((SchemaObject)localObject).getName().comment;
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table SYSTEM_PROPERTIES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[19];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[19]);
      addColumn(localTable, "PROPERTY_SCOPE", CHARACTER_DATA);
      addColumn(localTable, "PROPERTY_NAMESPACE", CHARACTER_DATA);
      addColumn(localTable, "PROPERTY_NAME", CHARACTER_DATA);
      addColumn(localTable, "PROPERTY_VALUE", CHARACTER_DATA);
      addColumn(localTable, "PROPERTY_CLASS", CHARACTER_DATA);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[19].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, true);
      return localTable;
    }
    String str1 = "SESSION";
    HsqlDatabaseProperties localHsqlDatabaseProperties = this.database.getProperties();
    String str2 = "database.properties";
    Iterator localIterator = localHsqlDatabaseProperties.getUserDefinedPropertyData().iterator();
    while (localIterator.hasNext())
    {
      Object[] arrayOfObject2 = (Object[])localIterator.next();
      Object[] arrayOfObject1 = localTable.getEmptyRowData();
      arrayOfObject1[0] = str1;
      arrayOfObject1[1] = str2;
      arrayOfObject1[2] = arrayOfObject2[0];
      arrayOfObject1[3] = this.database.logger.getValueStringForProperty((String)arrayOfObject1[2]);
      if (arrayOfObject1[3] == null) {
        arrayOfObject1[3] = localHsqlDatabaseProperties.getPropertyString((String)arrayOfObject1[2]);
      }
      arrayOfObject1[4] = arrayOfObject2[2];
      localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject1);
    }
    return localTable;
  }
  
  Table SYSTEM_SESSIONINFO(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[20];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[20]);
      addColumn(localTable, "KEY", CHARACTER_DATA);
      addColumn(localTable, "VALUE", CHARACTER_DATA);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[20].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, true);
      return localTable;
    }
    Object localObject = localTable.getEmptyRowData();
    localObject[0] = "SESSION ID";
    localObject[1] = String.valueOf(paramSession.getId());
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "AUTOCOMMIT";
    localObject[1] = (paramSession.isAutoCommit() ? "TRUE" : "FALSE");
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "USER";
    localObject[1] = paramSession.getUsername();
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "SESSION READONLY";
    localObject[1] = (paramSession.isReadOnlyDefault() ? "TRUE" : "FALSE");
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "DATABASE READONLY";
    localObject[1] = (this.database.isReadOnly() ? "TRUE" : "FALSE");
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "DATABASE";
    localObject[1] = this.database.getURI();
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "IDENTITY";
    localObject[1] = String.valueOf(paramSession.getLastIdentity());
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "CURRENT SCHEMA";
    localObject[1] = String.valueOf(paramSession.getSchemaName(null));
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "ISOLATION LEVEL";
    localObject[1] = String.valueOf(paramSession.getIsolation());
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "IGNORECASE";
    localObject[1] = (paramSession.isIgnorecase() ? "TRUE" : "FALSE");
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    localObject = localTable.getEmptyRowData();
    localObject[0] = "CURRENT STATEMENT";
    localObject[1] = "";
    Statement localStatement = paramSession.sessionContext.currentStatement;
    if (localStatement != null) {
      localObject[1] = localStatement.getSQL();
    }
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    return localTable;
  }
  
  Table SYSTEM_SESSIONS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[21];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[21]);
      addColumn(localTable, "SESSION_ID", CARDINAL_NUMBER);
      addColumn(localTable, "CONNECTED", TIME_STAMP);
      addColumn(localTable, "USER_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "IS_ADMIN", Type.SQL_BOOLEAN);
      addColumn(localTable, "AUTOCOMMIT", Type.SQL_BOOLEAN);
      addColumn(localTable, "READONLY", Type.SQL_BOOLEAN);
      addColumn(localTable, "LAST_IDENTITY", CARDINAL_NUMBER);
      addColumn(localTable, "SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TRANSACTION", Type.SQL_BOOLEAN);
      addColumn(localTable, "TRANSACTION_SIZE", CARDINAL_NUMBER);
      addColumn(localTable, "WAITING_FOR_THIS", CHARACTER_DATA);
      addColumn(localTable, "THIS_WAITING_FOR", CHARACTER_DATA);
      addColumn(localTable, "CURRENT_STATEMENT", CHARACTER_DATA);
      addColumn(localTable, "LATCH_COUNT", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[21].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0 }, true);
      return localTable;
    }
    Session[] arrayOfSession1 = this.database.sessionManager.getVisibleSessions(paramSession);
    for (int i = 0; i < arrayOfSession1.length; i++) {
      if (!arrayOfSession1[i].isClosed())
      {
        Session localSession = arrayOfSession1[i];
        Object[] arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = ValuePool.getLong(localSession.getId());
        arrayOfObject[1] = new TimestampData(localSession.getConnectTime() / 1000L);
        arrayOfObject[2] = localSession.getUsername();
        arrayOfObject[3] = ValuePool.getBoolean(localSession.isAdmin());
        arrayOfObject[4] = localSession.sessionContext.isAutoCommit;
        arrayOfObject[5] = Boolean.valueOf(localSession.isReadOnlyDefault);
        Number localNumber = localSession.getLastIdentity();
        if (localNumber != null) {
          arrayOfObject[6] = ValuePool.getLong(localNumber.longValue());
        }
        arrayOfObject[8] = Boolean.valueOf(localSession.isInMidTransaction());
        arrayOfObject[9] = ValuePool.getLong(localSession.getTransactionSize());
        HsqlNameManager.HsqlName localHsqlName2 = localSession.getCurrentSchemaHsqlName();
        if (localHsqlName2 != null) {
          arrayOfObject[7] = localHsqlName2.name;
        }
        arrayOfObject[10] = "";
        arrayOfObject[11] = "";
        Session[] arrayOfSession2;
        int j;
        if (localSession.waitingSessions.size() > 0)
        {
          localObject = new StringBuffer();
          arrayOfSession2 = new Session[localSession.waitingSessions.size()];
          localSession.waitingSessions.toArray(arrayOfSession2);
          for (j = 0; j < arrayOfSession2.length; j++)
          {
            if (j > 0) {
              ((StringBuffer)localObject).append(',');
            }
            ((StringBuffer)localObject).append(arrayOfSession2[j].getId());
          }
          arrayOfObject[10] = ((StringBuffer)localObject).toString();
        }
        if (localSession.waitedSessions.size() > 0)
        {
          localObject = new StringBuffer();
          arrayOfSession2 = new Session[localSession.waitedSessions.size()];
          localSession.waitedSessions.toArray(arrayOfSession2);
          for (j = 0; j < arrayOfSession2.length; j++)
          {
            if (j > 0) {
              ((StringBuffer)localObject).append(',');
            }
            ((StringBuffer)localObject).append(arrayOfSession2[j].getId());
          }
          arrayOfObject[11] = ((StringBuffer)localObject).toString();
        }
        Object localObject = localSession.sessionContext.currentStatement;
        arrayOfObject[12] = (localObject == null ? "" : ((Statement)localObject).getSQL());
        arrayOfObject[13] = new Long(localSession.latch.getCount());
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table SYSTEM_TEXTTABLES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[22];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[22]);
      addColumn(localTable1, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "DATA_SOURCE_DEFINTION", CHARACTER_DATA);
      addColumn(localTable1, "FILE_PATH", CHARACTER_DATA);
      addColumn(localTable1, "FILE_ENCODING", CHARACTER_DATA);
      addColumn(localTable1, "FIELD_SEPARATOR", CHARACTER_DATA);
      addColumn(localTable1, "VARCHAR_SEPARATOR", CHARACTER_DATA);
      addColumn(localTable1, "LONGVARCHAR_SEPARATOR", CHARACTER_DATA);
      addColumn(localTable1, "IS_IGNORE_FIRST", Type.SQL_BOOLEAN);
      addColumn(localTable1, "IS_ALL_QUOTED", Type.SQL_BOOLEAN);
      addColumn(localTable1, "IS_QUOTED", Type.SQL_BOOLEAN);
      addColumn(localTable1, "IS_DESC", Type.SQL_BOOLEAN);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[22].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable1;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(3);
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      PersistentStore localPersistentStore = localTable2.getRowStore(paramSession);
      if ((localTable2.isText()) && (isAccessibleTable(paramSession, localTable2)))
      {
        Object[] arrayOfObject = localTable1.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localTable2.getSchemaName().name;
        arrayOfObject[2] = localTable2.getName().name;
        arrayOfObject[3] = ((TextTable)localTable2).getDataSource();
        TextCache localTextCache = (TextCache)localPersistentStore.getCache();
        if (localTextCache != null)
        {
          TextFileSettings localTextFileSettings = localTextCache.getTextFileSettings();
          arrayOfObject[4] = FileUtil.getFileUtil().canonicalOrAbsolutePath(localTextCache.getFileName());
          arrayOfObject[5] = localTextFileSettings.stringEncoding;
          arrayOfObject[6] = localTextFileSettings.fs;
          arrayOfObject[7] = localTextFileSettings.vs;
          arrayOfObject[8] = localTextFileSettings.lvs;
          arrayOfObject[9] = ValuePool.getBoolean(localTextFileSettings.ignoreFirst);
          arrayOfObject[10] = ValuePool.getBoolean(localTextFileSettings.isQuoted);
          arrayOfObject[11] = ValuePool.getBoolean(localTextFileSettings.isAllQuoted);
          arrayOfObject[12] = (((TextTable)localTable2).isDescDataSource() ? Boolean.TRUE : Boolean.FALSE);
        }
        localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable1;
  }
  
  Table ADMINISTRABLE_ROLE_AUTHORIZATIONS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[23];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[23]);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "ROLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "IS_GRANTABLE", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[23].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable;
    }
    if (paramSession.isAdmin()) {
      insertRoles(paramSession, localTable, paramSession.getGrantee(), true);
    }
    return localTable;
  }
  
  Table APPLICABLE_ROLES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[24];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[24]);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "ROLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "IS_GRANTABLE", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[24].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable;
    }
    insertRoles(paramSession, localTable, paramSession.getGrantee(), paramSession.isAdmin());
    return localTable;
  }
  
  private void insertRoles(Session paramSession, Table paramTable, Grantee paramGrantee, boolean paramBoolean)
  {
    PersistentStore localPersistentStore = paramTable.getRowStore(paramSession);
    Object localObject;
    String str;
    Object[] arrayOfObject;
    if (paramBoolean)
    {
      localObject = this.database.getGranteeManager().getRoleNames();
      Iterator localIterator = ((Set)localObject).iterator();
      while (localIterator.hasNext())
      {
        str = (String)localIterator.next();
        arrayOfObject = paramTable.getEmptyRowData();
        arrayOfObject[0] = paramGrantee.getName().getNameString();
        arrayOfObject[1] = str;
        arrayOfObject[2] = "YES";
        paramTable.insertSys(paramSession, localPersistentStore, arrayOfObject);
      }
    }
    else
    {
      localObject = paramGrantee.getDirectRoles();
      for (int i = 0; i < ((OrderedHashSet)localObject).size(); i++)
      {
        str = (String)((OrderedHashSet)localObject).get(i);
        arrayOfObject = paramTable.getEmptyRowData();
        arrayOfObject[0] = paramGrantee.getName().getNameString();
        arrayOfObject[1] = str;
        arrayOfObject[2] = "NO";
        paramTable.insertSys(paramSession, localPersistentStore, arrayOfObject);
        paramGrantee = this.database.getGranteeManager().getRole(str);
        insertRoles(paramSession, paramTable, paramGrantee, paramBoolean);
      }
    }
  }
  
  Table ASSERTIONS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[25];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[25]);
      addColumn(localTable, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "IS_DEFERRABLE", YES_OR_NO);
      addColumn(localTable, "INITIALLY_DEFERRED", YES_OR_NO);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[25].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable;
    }
    return localTable;
  }
  
  Table AUTHORIZATIONS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[26];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[26]);
      addColumn(localTable, "AUTHORIZATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "AUTHORIZATION_TYPE", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[26].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, true);
      return localTable;
    }
    Object localObject = paramSession.getGrantee().visibleGrantees().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Grantee localGrantee = (Grantee)((Iterator)localObject).next();
      Object[] arrayOfObject = localTable.getEmptyRowData();
      arrayOfObject[0] = localGrantee.getName().getNameString();
      arrayOfObject[1] = (localGrantee.isRole() ? "ROLE" : "USER");
      localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    }
    return localTable;
  }
  
  Table CHARACTER_SETS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[27];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[27]);
      addColumn(localTable, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_REPERTOIRE", SQL_IDENTIFIER);
      addColumn(localTable, "FORM_OF_USE", SQL_IDENTIFIER);
      addColumn(localTable, "DEFAULT_COLLATE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "DEFAULT_COLLATE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "DEFAULT_COLLATE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[27].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(14);
    while (localIterator.hasNext())
    {
      Charset localCharset = (Charset)localIterator.next();
      if (paramSession.getGrantee().isAccessible(localCharset))
      {
        Object[] arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localCharset.getSchemaName().name;
        arrayOfObject[2] = localCharset.getName().name;
        arrayOfObject[3] = "UCS";
        arrayOfObject[4] = "UTF16";
        arrayOfObject[5] = arrayOfObject[0];
        if (localCharset.base == null)
        {
          arrayOfObject[6] = arrayOfObject[1];
          arrayOfObject[7] = arrayOfObject[2];
        }
        else
        {
          arrayOfObject[6] = localCharset.base.schema.name;
          arrayOfObject[7] = localCharset.base.name;
        }
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table CHECK_CONSTRAINT_ROUTINE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[28];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[28]);
      addColumn(localTable, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[28].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(5);
    while (localIterator.hasNext())
    {
      HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localIterator.next();
      if (localHsqlName2.parent != null)
      {
        Object localObject;
        Constraint localConstraint;
        switch (localHsqlName2.parent.type)
        {
        case 3: 
          try
          {
            localObject = (Table)this.database.schemaManager.getSchemaObject(localHsqlName2.parent.name, localHsqlName2.parent.schema.name, 3);
          }
          catch (Exception localException1) {}
          continue;
          localConstraint = ((Table)localObject).getConstraint(localHsqlName2.name);
          if (localConstraint.getConstraintType() == 3) {}
          break;
        case 13: 
          try
          {
            localObject = (Type)this.database.schemaManager.getSchemaObject(localHsqlName2.parent.name, localHsqlName2.parent.schema.name, 13);
          }
          catch (Exception localException2) {}
          continue;
          localConstraint = ((Type)localObject).userTypeModifier.getConstraint(localHsqlName2.name);
        default: 
          continue;
          OrderedHashSet localOrderedHashSet = localConstraint.getReferences();
          for (int i = 0; i < localOrderedHashSet.size(); i++)
          {
            HsqlNameManager.HsqlName localHsqlName3 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
            if ((localHsqlName3.type == 24) && (paramSession.getGrantee().isFullyAccessibleByRole(localHsqlName3)))
            {
              Object[] arrayOfObject = localTable.getEmptyRowData();
              arrayOfObject[0] = this.database.getCatalogName().name;
              arrayOfObject[1] = localConstraint.getSchemaName().name;
              arrayOfObject[2] = localConstraint.getName().name;
              arrayOfObject[3] = this.database.getCatalogName().name;
              arrayOfObject[4] = localHsqlName3.schema.name;
              arrayOfObject[5] = localHsqlName3.name;
              localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
          }
        }
      }
    }
    return localTable;
  }
  
  Table CHECK_CONSTRAINTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[29];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[29]);
      addColumn(localTable1, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "CHECK_CLAUSE", CHARACTER_DATA);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[29].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName, new int[] { 2, 1, 0 }, false);
      return localTable1;
    }
    Iterator localIterator1 = this.database.schemaManager.databaseObjectIterator(3);
    Constraint localConstraint;
    Object[] arrayOfObject;
    while (localIterator1.hasNext())
    {
      Table localTable2 = (Table)localIterator1.next();
      if ((!localTable2.isView()) && (paramSession.getGrantee().isFullyAccessibleByRole(localTable2.getName()))) {
        for (localConstraint : localTable2.getConstraints()) {
          if (localConstraint.getConstraintType() == 3)
          {
            arrayOfObject = localTable1.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localTable2.getSchemaName().name;
            arrayOfObject[2] = localConstraint.getName().name;
            try
            {
              arrayOfObject[3] = localConstraint.getCheckSQL();
            }
            catch (Exception localException1) {}
            localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
        }
      }
    }
    Iterator localIterator2 = this.database.schemaManager.databaseObjectIterator(13);
    while (localIterator2.hasNext())
    {
      Type localType = (Type)localIterator2.next();
      if ((localType.isDomainType()) && (paramSession.getGrantee().isFullyAccessibleByRole(localType.getName()))) {
        for (localConstraint : localType.userTypeModifier.getConstraints())
        {
          arrayOfObject = localTable1.getEmptyRowData();
          arrayOfObject[0] = this.database.getCatalogName().name;
          arrayOfObject[1] = localType.getSchemaName().name;
          arrayOfObject[2] = localConstraint.getName().name;
          try
          {
            arrayOfObject[3] = localConstraint.getCheckSQL();
          }
          catch (Exception localException2) {}
          localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        }
      }
    }
    return localTable1;
  }
  
  Table COLLATIONS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[30];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[30]);
      addColumn(localTable, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "PAD_ATTRIBUTE", CHARACTER_DATA);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[30].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable;
    }
    String str3 = "PAD SPACE";
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(15);
    Object[] arrayOfObject;
    String str2;
    String str1;
    while (localIterator.hasNext())
    {
      arrayOfObject = localTable.getEmptyRowData();
      Collation localCollation = (Collation)localIterator.next();
      str2 = localCollation.getSchemaName().name;
      str1 = localCollation.getName().name;
      arrayOfObject[0] = this.database.getCatalogName().name;
      arrayOfObject[1] = str2;
      arrayOfObject[2] = str1;
      arrayOfObject[3] = (localCollation.isPadSpace() ? "PAD SPACE" : "NO PAD");
      localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    }
    localIterator = Collation.nameToJavaName.keySet().iterator();
    while (localIterator.hasNext())
    {
      arrayOfObject = localTable.getEmptyRowData();
      str2 = "INFORMATION_SCHEMA";
      str1 = (String)localIterator.next();
      arrayOfObject[0] = this.database.getCatalogName().name;
      arrayOfObject[1] = str2;
      arrayOfObject[2] = str1;
      arrayOfObject[3] = str3;
      localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    }
    return localTable;
  }
  
  Table COLUMN_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[31];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[31]);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "DEPENDENT_COLUMN", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[31].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4 }, false);
      return localTable1;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(3);
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      if ((!localTable2.isView()) && (paramSession.getGrantee().isFullyAccessibleByRole(localTable2.getName())) && (localTable2.hasGeneratedColumn()))
      {
        HsqlNameManager.HsqlName localHsqlName2 = localTable2.getName();
        for (int i = 0; i < localTable2.getColumnCount(); i++)
        {
          ColumnSchema localColumnSchema = localTable2.getColumn(i);
          if (localColumnSchema.isGenerated())
          {
            OrderedHashSet localOrderedHashSet = localColumnSchema.getGeneratedColumnReferences();
            if (localOrderedHashSet != null) {
              for (int j = 0; j < localOrderedHashSet.size(); j++)
              {
                Object[] arrayOfObject = localTable1.getEmptyRowData();
                arrayOfObject[0] = this.database.getCatalogName().name;
                arrayOfObject[1] = localHsqlName2.schema.name;
                arrayOfObject[2] = localHsqlName2.name;
                arrayOfObject[3] = ((HsqlNameManager.HsqlName)localOrderedHashSet.get(j)).name;
                arrayOfObject[4] = localColumnSchema.getName().name;
                localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              }
            }
          }
        }
      }
    }
    return localTable1;
  }
  
  Table COLUMN_DOMAIN_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[32];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[32]);
      addColumn(localTable1, "DOMAIN_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "DOMAIN_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "DOMAIN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[32].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return localTable1;
    }
    Iterator localIterator = allTables();
    Grantee localGrantee = paramSession.getGrantee();
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      int i = localTable2.getColumnCount();
      HsqlNameManager.HsqlName localHsqlName2 = localTable2.getName();
      for (int j = 0; j < i; j++)
      {
        ColumnSchema localColumnSchema = localTable2.getColumn(j);
        Type localType = localColumnSchema.getDataType();
        if ((localType.isDomainType()) && (localGrantee.isFullyAccessibleByRole(localType.getName())))
        {
          Object[] arrayOfObject = localTable1.getEmptyRowData();
          arrayOfObject[0] = this.database.getCatalogName().name;
          arrayOfObject[1] = localType.getSchemaName().name;
          arrayOfObject[2] = localType.getName().name;
          arrayOfObject[3] = this.database.getCatalogName().name;
          arrayOfObject[4] = localHsqlName2.schema.name;
          arrayOfObject[5] = localHsqlName2.name;
          arrayOfObject[6] = localColumnSchema.getNameString();
          localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        }
      }
    }
    return localTable1;
  }
  
  Table COLUMN_UDT_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[34];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[34]);
      addColumn(localTable1, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[34].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return localTable1;
    }
    Iterator localIterator = allTables();
    Grantee localGrantee = paramSession.getGrantee();
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      int i = localTable2.getColumnCount();
      HsqlNameManager.HsqlName localHsqlName2 = localTable2.getName();
      for (int j = 0; j < i; j++)
      {
        ColumnSchema localColumnSchema = localTable2.getColumn(j);
        Type localType = localColumnSchema.getDataType();
        if ((localType.isDistinctType()) && (localGrantee.isFullyAccessibleByRole(localType.getName())))
        {
          Object[] arrayOfObject = localTable1.getEmptyRowData();
          arrayOfObject[0] = this.database.getCatalogName().name;
          arrayOfObject[1] = localType.getSchemaName().name;
          arrayOfObject[2] = localType.getName().name;
          arrayOfObject[3] = this.database.getCatalogName().name;
          arrayOfObject[4] = localHsqlName2.schema.name;
          arrayOfObject[5] = localHsqlName2.name;
          arrayOfObject[6] = localColumnSchema.getNameString();
          localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        }
      }
    }
    return localTable1;
  }
  
  Table COLUMNS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[35];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[35]);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "ORDINAL_POSITION", CARDINAL_NUMBER);
      addColumn(localTable1, "COLUMN_DEFAULT", CHARACTER_DATA);
      addColumn(localTable1, "IS_NULLABLE", YES_OR_NO);
      addColumn(localTable1, "DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable1, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable1, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable1, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(localTable1, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable1, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable1, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable1, "CHARACTER_SET_CATALOG", CHARACTER_DATA);
      addColumn(localTable1, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "DOMAIN_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "DOMAIN_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "DOMAIN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(localTable1, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(localTable1, "IS_SELF_REFERENCING", YES_OR_NO);
      addColumn(localTable1, "IS_IDENTITY", YES_OR_NO);
      addColumn(localTable1, "IDENTITY_GENERATION", CHARACTER_DATA);
      addColumn(localTable1, "IDENTITY_START", CHARACTER_DATA);
      addColumn(localTable1, "IDENTITY_INCREMENT", CHARACTER_DATA);
      addColumn(localTable1, "IDENTITY_MAXIMUM", CHARACTER_DATA);
      addColumn(localTable1, "IDENTITY_MINIMUM", CHARACTER_DATA);
      addColumn(localTable1, "IDENTITY_CYCLE", YES_OR_NO);
      addColumn(localTable1, "IS_GENERATED", CHARACTER_DATA);
      addColumn(localTable1, "GENERATION_EXPRESSION", CHARACTER_DATA);
      addColumn(localTable1, "IS_UPDATABLE", YES_OR_NO);
      addColumn(localTable1, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable1, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[35].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName, new int[] { 3, 2, 1, 4 }, false);
      return localTable1;
    }
    Iterator localIterator = allTables();
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      OrderedHashSet localOrderedHashSet = paramSession.getGrantee().getColumnsForAllPrivileges(localTable2);
      if (!localOrderedHashSet.isEmpty())
      {
        int i = localTable2.getColumnCount();
        for (int j = 0; j < i; j++)
        {
          ColumnSchema localColumnSchema = localTable2.getColumn(j);
          Type localType = localColumnSchema.getDataType();
          if (localOrderedHashSet.contains(localColumnSchema.getName()))
          {
            Object[] arrayOfObject = localTable1.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localTable2.getSchemaName().name;
            arrayOfObject[2] = localTable2.getName().name;
            arrayOfObject[3] = localColumnSchema.getName().name;
            arrayOfObject[4] = ValuePool.getLong(j + 1);
            arrayOfObject[5] = localColumnSchema.getDefaultSQL();
            arrayOfObject[6] = (localColumnSchema.isNullable() ? "YES" : "NO");
            arrayOfObject[7] = localType.getFullNameString();
            if (localType.isCharacterType())
            {
              arrayOfObject[8] = ValuePool.getLong(localType.precision);
              arrayOfObject[9] = ValuePool.getLong(localType.precision * 2L);
              arrayOfObject[16] = this.database.getCatalogName().name;
              arrayOfObject[17] = ((CharacterType)localType).getCharacterSet().getSchemaName().name;
              arrayOfObject[18] = ((CharacterType)localType).getCharacterSet().getName().name;
              arrayOfObject[19] = this.database.getCatalogName().name;
              arrayOfObject[20] = ((CharacterType)localType).getCollation().getSchemaName().name;
              arrayOfObject[21] = ((CharacterType)localType).getCollation().getName().name;
            }
            else if (localType.isNumberType())
            {
              arrayOfObject[10] = ValuePool.getLong(((NumberType)localType).getNumericPrecisionInRadix());
              arrayOfObject[45] = ValuePool.getLong(((NumberType)localType).getNumericPrecisionInRadix());
              if (localType.isExactNumberType())
              {
                Long tmp1003_1000 = ValuePool.getLong(localType.scale);
                arrayOfObject[46] = tmp1003_1000;
                arrayOfObject[12] = tmp1003_1000;
              }
              arrayOfObject[11] = ValuePool.getLong(localType.getPrecisionRadix());
            }
            else if (!localType.isBooleanType())
            {
              if (localType.isDateTimeType())
              {
                arrayOfObject[13] = ValuePool.getLong(localType.scale);
              }
              else if (localType.isIntervalType())
              {
                arrayOfObject[7] = "INTERVAL";
                ((IntervalType)localType);
                arrayOfObject[14] = IntervalType.getQualifier(localType.typeCode);
                arrayOfObject[15] = ValuePool.getLong(localType.precision);
                arrayOfObject[13] = ValuePool.getLong(localType.scale);
              }
              else if (localType.isBinaryType())
              {
                arrayOfObject[8] = ValuePool.getLong(localType.precision);
                arrayOfObject[9] = ValuePool.getLong(localType.precision);
              }
              else if (localType.isBitType())
              {
                arrayOfObject[8] = ValuePool.getLong(localType.precision);
                arrayOfObject[9] = ValuePool.getLong(localType.precision);
              }
              else if (localType.isArrayType())
              {
                arrayOfObject[31] = ValuePool.getLong(localType.arrayLimitCardinality());
                arrayOfObject[7] = "ARRAY";
              }
            }
            if (localType.isDomainType())
            {
              arrayOfObject[22] = this.database.getCatalogName().name;
              arrayOfObject[23] = localType.getSchemaName().name;
              arrayOfObject[24] = localType.getName().name;
            }
            if (localType.isDistinctType())
            {
              arrayOfObject[25] = this.database.getCatalogName().name;
              arrayOfObject[26] = localType.getSchemaName().name;
              arrayOfObject[27] = localType.getName().name;
            }
            arrayOfObject[28] = null;
            arrayOfObject[29] = null;
            arrayOfObject[30] = null;
            arrayOfObject[32] = localType.getDefinition();
            arrayOfObject[33] = null;
            arrayOfObject[34] = (localColumnSchema.isIdentity() ? "YES" : "NO");
            if (localColumnSchema.isIdentity())
            {
              NumberSequence localNumberSequence = localColumnSchema.getIdentitySequence();
              arrayOfObject[35] = (localNumberSequence.isAlways() ? "ALWAYS" : "BY DEFAULT");
              arrayOfObject[36] = Long.toString(localNumberSequence.getStartValue());
              arrayOfObject[37] = Long.toString(localNumberSequence.getIncrement());
              arrayOfObject[38] = Long.toString(localNumberSequence.getMaxValue());
              arrayOfObject[39] = Long.toString(localNumberSequence.getMinValue());
              arrayOfObject[40] = (localNumberSequence.isCycle() ? "YES" : "NO");
            }
            arrayOfObject[41] = "NEVER";
            if (localColumnSchema.isGenerated())
            {
              arrayOfObject[41] = "ALWAYS";
              arrayOfObject[42] = localColumnSchema.getGeneratingExpression().getSQL();
            }
            arrayOfObject[43] = (localTable2.isWritable() ? "YES" : "NO");
            arrayOfObject[44] = arrayOfObject[7];
            if (localType.isNumberType())
            {
              arrayOfObject[45] = arrayOfObject[10];
              arrayOfObject[46] = arrayOfObject[12];
            }
            localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
        }
      }
    }
    return localTable1;
  }
  
  Table CONSTRAINT_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[36];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[36]);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[36].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return localTable1;
    }
    Iterator localIterator1 = this.database.schemaManager.databaseObjectIterator(3);
    while (localIterator1.hasNext())
    {
      Table localTable2 = (Table)localIterator1.next();
      if ((!localTable2.isView()) && (paramSession.getGrantee().isFullyAccessibleByRole(localTable2.getName())))
      {
        Constraint[] arrayOfConstraint = localTable2.getConstraints();
        int i = arrayOfConstraint.length;
        String str1 = this.database.getCatalogName().name;
        String str2 = localTable2.getSchemaName().name;
        for (int j = 0; j < i; j++)
        {
          Constraint localConstraint = arrayOfConstraint[j];
          String str3 = localConstraint.getName().name;
          Object localObject1;
          Iterator localIterator2;
          switch (localConstraint.getConstraintType())
          {
          case 3: 
            localObject1 = localConstraint.getCheckColumnExpressions();
            if (localObject1 != null) {
              localIterator2 = ((OrderedHashSet)localObject1).iterator();
            }
            break;
          case 0: 
          case 2: 
          case 4: 
            while (localIterator2.hasNext())
            {
              Object localObject2 = (ExpressionColumn)localIterator2.next();
              HsqlNameManager.HsqlName localHsqlName2 = ((ExpressionColumn)localObject2).getColumn().getName();
              if (localHsqlName2.type == 9)
              {
                Object[] arrayOfObject = localTable1.getEmptyRowData();
                arrayOfObject[0] = this.database.getCatalogName().name;
                arrayOfObject[1] = localHsqlName2.schema.name;
                arrayOfObject[2] = localHsqlName2.parent.name;
                arrayOfObject[3] = localHsqlName2.name;
                arrayOfObject[4] = str1;
                arrayOfObject[5] = str2;
                arrayOfObject[6] = str3;
                try
                {
                  localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
                }
                catch (HsqlException localHsqlException1) {}
                continue;
                localObject1 = localTable2;
                localObject2 = localConstraint.getMainColumns();
                if (localConstraint.getConstraintType() == 0) {
                  localObject2 = localConstraint.getRefColumns();
                }
                for (int k = 0; k < localObject2.length; k++)
                {
                  arrayOfObject = localTable1.getEmptyRowData();
                  arrayOfObject[0] = this.database.getCatalogName().name;
                  arrayOfObject[1] = str2;
                  arrayOfObject[2] = ((Table)localObject1).getName().name;
                  arrayOfObject[3] = ((Table)localObject1).getColumn(localObject2[k]).getName().name;
                  arrayOfObject[4] = str1;
                  arrayOfObject[5] = str2;
                  arrayOfObject[6] = str3;
                  try
                  {
                    localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
                  }
                  catch (HsqlException localHsqlException2) {}
                }
              }
            }
          }
        }
      }
    }
    return localTable1;
  }
  
  Table CONSTRAINT_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[37];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[37]);
      addColumn(localTable, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[37].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result localResult = ((Session)localObject).executeDirectStatement("select DISTINCT TABLE_CATALOG, TABLE_SCHEMA, TABLE_NAME, CONSTRAINT_CATALOG, CONSTRAINT_SCHEMA, CONSTRAINT_NAME from INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE");
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    ((Session)localObject).close();
    return localTable;
  }
  
  Table DATA_TYPE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[38];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[38]);
      addColumn(localTable, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_TYPE", SQL_IDENTIFIER);
      addColumn(localTable, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[38].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 1, 2, 3, 4 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*data_type_privileges*/");
    Result localResult = ((Session)localObject).executeDirectStatement(str);
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    ((Session)localObject).close();
    return localTable;
  }
  
  Table DOMAIN_CONSTRAINTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[39];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[39]);
      addColumn(localTable, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "DOMAIN_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "DOMAIN_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "DOMAIN_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "IS_DEFERRABLE", YES_OR_NO);
      addColumn(localTable, "INITIALLY_DEFERRED", YES_OR_NO);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[39].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 4, 5, 6 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(13);
    while (localIterator.hasNext())
    {
      Type localType = (Type)localIterator.next();
      if ((localType.isDomainType()) && (paramSession.getGrantee().isFullyAccessibleByRole(localType.getName())))
      {
        Constraint[] arrayOfConstraint = localType.userTypeModifier.getConstraints();
        for (int i = 0; i < arrayOfConstraint.length; i++)
        {
          Object[] arrayOfObject = localTable.getEmptyRowData();
          String tmp274_271 = this.database.getCatalogName().name;
          arrayOfObject[3] = tmp274_271;
          arrayOfObject[0] = tmp274_271;
          String tmp291_288 = localType.getSchemaName().name;
          arrayOfObject[4] = tmp291_288;
          arrayOfObject[1] = tmp291_288;
          arrayOfObject[2] = arrayOfConstraint[i].getName().name;
          arrayOfObject[5] = localType.getName().name;
          arrayOfObject[6] = "NO";
          arrayOfObject[7] = "NO";
          localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        }
      }
    }
    return localTable;
  }
  
  Table DOMAINS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[40];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[40]);
      addColumn(localTable, "DOMAIN_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "DOMAIN_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "DOMAIN_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "DATA_TYPE", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(localTable, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "DOMAIN_DEFAULT", CHARACTER_DATA);
      addColumn(localTable, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(localTable, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(localTable, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[40].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 4, 5, 6 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(13);
    while (localIterator.hasNext())
    {
      Type localType = (Type)localIterator.next();
      if ((localType.isDomainType()) && (paramSession.getGrantee().isAccessible(localType)))
      {
        Object[] arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localType.getSchemaName().name;
        arrayOfObject[2] = localType.getName().name;
        arrayOfObject[3] = localType.getFullNameString();
        if (localType.isCharacterType())
        {
          arrayOfObject[4] = ValuePool.getLong(localType.precision);
          arrayOfObject[5] = ValuePool.getLong(localType.precision * 2L);
          arrayOfObject[6] = this.database.getCatalogName().name;
          arrayOfObject[7] = ((CharacterType)localType).getCharacterSet().getSchemaName().name;
          arrayOfObject[8] = ((CharacterType)localType).getCharacterSet().getName().name;
          arrayOfObject[9] = this.database.getCatalogName().name;
          arrayOfObject[10] = ((CharacterType)localType).getCollation().getSchemaName().name;
          arrayOfObject[11] = ((CharacterType)localType).getCollation().getName().name;
        }
        else if (localType.isNumberType())
        {
          arrayOfObject[12] = ValuePool.getLong(((NumberType)localType).getNumericPrecisionInRadix());
          arrayOfObject[22] = ValuePool.getLong(((NumberType)localType).getNumericPrecisionInRadix());
          if (localType.isExactNumberType())
          {
            Long tmp669_666 = ValuePool.getLong(localType.scale);
            arrayOfObject[23] = tmp669_666;
            arrayOfObject[14] = tmp669_666;
          }
          arrayOfObject[13] = ValuePool.getLong(localType.getPrecisionRadix());
        }
        else if (!localType.isBooleanType())
        {
          if (localType.isDateTimeType())
          {
            arrayOfObject[15] = ValuePool.getLong(localType.scale);
          }
          else if (localType.isIntervalType())
          {
            arrayOfObject[3] = "INTERVAL";
            ((IntervalType)localType);
            arrayOfObject[16] = IntervalType.getQualifier(localType.typeCode);
            arrayOfObject[17] = ValuePool.getLong(localType.precision);
            arrayOfObject[15] = ValuePool.getLong(localType.scale);
          }
          else if (localType.isBinaryType())
          {
            arrayOfObject[4] = ValuePool.getLong(localType.precision);
            arrayOfObject[5] = ValuePool.getLong(localType.precision);
          }
          else if (localType.isBitType())
          {
            arrayOfObject[4] = ValuePool.getLong(localType.precision);
            arrayOfObject[5] = ValuePool.getLong(localType.precision);
          }
          else if (localType.isArrayType())
          {
            arrayOfObject[19] = ValuePool.getLong(localType.arrayLimitCardinality());
            arrayOfObject[3] = "ARRAY";
          }
        }
        arrayOfObject[20] = localType.getDefinition();
        arrayOfObject[21] = arrayOfObject[3];
        Expression localExpression = localType.userTypeModifier.getDefaultClause();
        if (localExpression != null) {
          arrayOfObject[18] = localExpression.getSQL();
        }
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table ELEMENT_TYPES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[41];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[41]);
      addColumn(localTable1, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "OBJECT_TYPE", SQL_IDENTIFIER);
      addColumn(localTable1, "COLLECTION_TYPE_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(localTable1, "DATA_TYPE", SQL_IDENTIFIER);
      addColumn(localTable1, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable1, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable1, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable1, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(localTable1, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable1, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable1, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable1, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(localTable1, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(localTable1, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable1, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[41].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 4, 5, 27 }, true);
      return localTable1;
    }
    Iterator localIterator1 = allTables();
    Type localType1;
    Object[] arrayOfObject;
    while (localIterator1.hasNext())
    {
      Table localTable2 = (Table)localIterator1.next();
      OrderedHashSet localOrderedHashSet = paramSession.getGrantee().getColumnsForAllPrivileges(localTable2);
      if (!localOrderedHashSet.isEmpty())
      {
        int i = localTable2.getColumnCount();
        for (int j = 0; j < i; j++)
        {
          ColumnSchema localColumnSchema1 = localTable2.getColumn(j);
          if (localOrderedHashSet.contains(localColumnSchema1.getName()))
          {
            localType1 = localColumnSchema1.getDataType();
            if ((!localType1.isDistinctType()) && (!localType1.isDomainType()) && (localType1.isArrayType()))
            {
              arrayOfObject = localTable1.getEmptyRowData();
              arrayOfObject[0] = this.database.getCatalogName().name;
              arrayOfObject[1] = localTable2.getSchemaName().name;
              arrayOfObject[2] = localTable2.getName().name;
              arrayOfObject[3] = "TABLE";
              arrayOfObject[4] = localType1.getDefinition();
              addTypeInfo(arrayOfObject, ((ArrayType)localType1).collectionBaseType());
              try
              {
                localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              }
              catch (HsqlException localHsqlException2) {}
            }
          }
        }
      }
    }
    Iterator localIterator2 = this.database.schemaManager.databaseObjectIterator(13);
    while (localIterator2.hasNext())
    {
      localType1 = (Type)localIterator2.next();
      if ((localType1.isDomainType()) && (localType1.isArrayType()) && (paramSession.getGrantee().isAccessible(localType1)))
      {
        arrayOfObject = localTable1.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localType1.getSchemaName().name;
        arrayOfObject[2] = localType1.getName().name;
        arrayOfObject[3] = "DOMAIN";
        arrayOfObject[4] = localType1.getDefinition();
        addTypeInfo(arrayOfObject, ((ArrayType)localType1).collectionBaseType());
        localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    localIterator2 = this.database.schemaManager.databaseObjectIterator(12);
    while (localIterator2.hasNext())
    {
      localType1 = (Type)localIterator2.next();
      if ((localType1.isDistinctType()) && (localType1.isArrayType()) && (paramSession.getGrantee().isAccessible(localType1)))
      {
        arrayOfObject = localTable1.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localType1.getSchemaName().name;
        arrayOfObject[2] = localType1.getName().name;
        arrayOfObject[3] = "USER-DEFINED TYPE";
        arrayOfObject[4] = localType1.getDefinition();
        addTypeInfo(arrayOfObject, ((ArrayType)localType1).collectionBaseType());
        try
        {
          localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        }
        catch (HsqlException localHsqlException1) {}
      }
    }
    localIterator2 = this.database.schemaManager.databaseObjectIterator(24);
    while (localIterator2.hasNext())
    {
      Routine localRoutine = (Routine)localIterator2.next();
      if (paramSession.getGrantee().isAccessible(localRoutine))
      {
        localType1 = localRoutine.isProcedure() ? null : localRoutine.getReturnType();
        if ((localType1 != null) && (!localType1.isDistinctType()) && (!localType1.isDomainType()) && (localType1.isArrayType()))
        {
          arrayOfObject = localTable1.getEmptyRowData();
          arrayOfObject[0] = this.database.getCatalogName().name;
          arrayOfObject[1] = localRoutine.getSchemaName().name;
          arrayOfObject[2] = localRoutine.getName().name;
          arrayOfObject[3] = "ROUTINE";
          arrayOfObject[4] = localType1.getDefinition();
          addTypeInfo(arrayOfObject, ((ArrayType)localType1).collectionBaseType());
          try
          {
            localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
          catch (HsqlException localHsqlException3) {}
        }
        Type localType2 = localType1;
        int k = localRoutine.getParameterCount();
        for (int m = 0; m < k; m++)
        {
          ColumnSchema localColumnSchema2 = localRoutine.getParameter(m);
          localType1 = localColumnSchema2.getDataType();
          if ((!localType1.isDistinctType()) && (!localType1.isDomainType()) && (localType1.isArrayType()) && (!localType1.equals(localType2)))
          {
            arrayOfObject = localTable1.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localRoutine.getSchemaName().name;
            arrayOfObject[2] = localRoutine.getName().name;
            arrayOfObject[3] = "ROUTINE";
            arrayOfObject[4] = localType1.getDefinition();
            addTypeInfo(arrayOfObject, ((ArrayType)localType1).collectionBaseType());
            try
            {
              localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException4) {}
          }
        }
      }
    }
    return localTable1;
  }
  
  void addTypeInfo(Object[] paramArrayOfObject, Type paramType)
  {
    paramArrayOfObject[5] = paramType.getFullNameString();
    if (paramType.isCharacterType())
    {
      paramArrayOfObject[6] = ValuePool.getLong(paramType.precision);
      paramArrayOfObject[7] = ValuePool.getLong(paramType.precision * 2L);
      paramArrayOfObject[8] = this.database.getCatalogName().name;
      paramArrayOfObject[9] = ((CharacterType)paramType).getCharacterSet().getSchemaName().name;
      paramArrayOfObject[10] = ((CharacterType)paramType).getCharacterSet().getName().name;
      paramArrayOfObject[11] = this.database.getCatalogName().name;
      paramArrayOfObject[12] = ((CharacterType)paramType).getCollation().getSchemaName().name;
      paramArrayOfObject[13] = ((CharacterType)paramType).getCollation().getName().name;
    }
    else if (paramType.isNumberType())
    {
      paramArrayOfObject[14] = ValuePool.getLong(((NumberType)paramType).getNumericPrecisionInRadix());
      paramArrayOfObject[29] = ValuePool.getLong(((NumberType)paramType).getNumericPrecisionInRadix());
      if (paramType.isExactNumberType())
      {
        Long tmp197_194 = ValuePool.getLong(paramType.scale);
        paramArrayOfObject[30] = tmp197_194;
        paramArrayOfObject[16] = tmp197_194;
      }
      paramArrayOfObject[15] = ValuePool.getLong(paramType.getPrecisionRadix());
    }
    else if (!paramType.isBooleanType())
    {
      if (paramType.isDateTimeType())
      {
        paramArrayOfObject[17] = ValuePool.getLong(paramType.scale);
      }
      else if (paramType.isIntervalType())
      {
        paramArrayOfObject[5] = "INTERVAL";
        ((IntervalType)paramType);
        paramArrayOfObject[18] = IntervalType.getQualifier(paramType.typeCode);
        paramArrayOfObject[19] = ValuePool.getLong(paramType.precision);
        paramArrayOfObject[17] = ValuePool.getLong(paramType.scale);
      }
      else if (paramType.isBinaryType())
      {
        paramArrayOfObject[6] = ValuePool.getLong(paramType.precision);
        paramArrayOfObject[7] = ValuePool.getLong(paramType.precision);
      }
      else if (paramType.isBitType())
      {
        paramArrayOfObject[6] = ValuePool.getLong(paramType.precision);
        paramArrayOfObject[7] = ValuePool.getLong(paramType.precision);
      }
      else if (paramType.isArrayType())
      {
        paramArrayOfObject[26] = ValuePool.getLong(paramType.arrayLimitCardinality());
      }
    }
    paramArrayOfObject[27] = paramType.getDefinition();
    paramArrayOfObject[28] = paramArrayOfObject[5];
  }
  
  Table ENABLED_ROLES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[42];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[42]);
      addColumn(localTable, "ROLE_NAME", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[42].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, true);
      return localTable;
    }
    Object localObject = paramSession.getGrantee().getAllRoles().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Grantee localGrantee = (Grantee)((Iterator)localObject).next();
      Object[] arrayOfObject = localTable.getEmptyRowData();
      arrayOfObject[0] = localGrantee.getName().getNameString();
      localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    }
    return localTable;
  }
  
  Table JAR_JAR_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[44];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[44]);
      addColumn(localTable, "PATH_JAR_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "PATH_JAR_SCHAMA", SQL_IDENTIFIER);
      addColumn(localTable, "PATH_JAR_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "JAR_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "JAR_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "JAR_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[44].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable;
    }
    return localTable;
  }
  
  Table JARS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[45];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[45]);
      addColumn(localTable, "JAR_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "JAR_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "JAR_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "JAR_PATH", CHARACTER_DATA);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[45].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 3 }, false);
      return localTable;
    }
    return localTable;
  }
  
  Table KEY_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[46];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[46]);
      addColumn(localTable1, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "ORDINAL_POSITION", CARDINAL_NUMBER);
      addColumn(localTable1, "POSITION_IN_UNIQUE_CONSTRAINT", CARDINAL_NUMBER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[46].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 2, 1, 0, 6, 7 }, false);
      return localTable1;
    }
    Object localObject = this.database.schemaManager.databaseObjectIterator(3);
    while (((Iterator)localObject).hasNext())
    {
      Table localTable2 = (Table)((Iterator)localObject).next();
      String str1 = this.database.getCatalogName().name;
      HsqlNameManager.HsqlName localHsqlName = localTable2.getName();
      if ((!localTable2.isView()) && (paramSession.getGrantee().isAccessible(localHsqlName)))
      {
        Constraint[] arrayOfConstraint = localTable2.getConstraints();
        for (int i = 0; i < arrayOfConstraint.length; i++)
        {
          Constraint localConstraint1 = arrayOfConstraint[i];
          if ((localConstraint1.getConstraintType() == 4) || (localConstraint1.getConstraintType() == 2) || (localConstraint1.getConstraintType() == 0))
          {
            String str2 = localConstraint1.getName().name;
            int[] arrayOfInt1 = localConstraint1.getMainColumns();
            int[] arrayOfInt2 = null;
            if (localConstraint1.getConstraintType() == 0)
            {
              Table localTable3 = localConstraint1.getMain();
              Constraint localConstraint2 = localTable3.getConstraint(localConstraint1.getUniqueName().name);
              int[] arrayOfInt3 = localConstraint2.getMainColumns();
              arrayOfInt2 = new int[arrayOfInt1.length];
              for (int k = 0; k < arrayOfInt1.length; k++) {
                arrayOfInt2[k] = ArrayUtil.find(arrayOfInt3, arrayOfInt1[k]);
              }
              arrayOfInt1 = localConstraint1.getRefColumns();
            }
            if (paramSession.getGrantee().hasColumnRights(localTable2, arrayOfInt1)) {
              for (int j = 0; j < arrayOfInt1.length; j++)
              {
                Object[] arrayOfObject = localTable1.getEmptyRowData();
                arrayOfObject[0] = str1;
                arrayOfObject[1] = localHsqlName.schema.name;
                arrayOfObject[2] = str2;
                arrayOfObject[3] = str1;
                arrayOfObject[4] = localHsqlName.schema.name;
                arrayOfObject[5] = localHsqlName.name;
                arrayOfObject[6] = localTable2.getColumn(arrayOfInt1[j]).getName().name;
                arrayOfObject[7] = ValuePool.getLong(j + 1);
                if (localConstraint1.getConstraintType() == 0) {
                  arrayOfObject[8] = ValuePool.getInt(arrayOfInt2[j] + 1);
                }
                localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              }
            }
          }
        }
      }
    }
    return localTable1;
  }
  
  Table METHOD_SPECIFICATION_PARAMETERS(Session paramSession, PersistentStore paramPersistentStore)
  {
    return null;
  }
  
  Table METHOD_SPECIFICATIONS(Session paramSession, PersistentStore paramPersistentStore)
  {
    return null;
  }
  
  Table MODULE_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    return null;
  }
  
  Table MODULE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore)
  {
    return null;
  }
  
  Table MODULE_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    return null;
  }
  
  Table MODULES(Session paramSession, PersistentStore paramPersistentStore)
  {
    return null;
  }
  
  Table PARAMETERS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[52];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[52]);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "ORDINAL_POSITION", CARDINAL_NUMBER);
      addColumn(localTable, "PARAMETER_MODE", CHARACTER_DATA);
      addColumn(localTable, "IS_RESULT", YES_OR_NO);
      addColumn(localTable, "AS_LOCATOR", YES_OR_NO);
      addColumn(localTable, "PARAMETER_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "FROM_SQL_SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "FROM_SQL_SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "FROM_SQL_SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "TO_SQL_SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TO_SQL_SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TO_SQL_SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "CHARACTER_SET_CATALOG", CHARACTER_DATA);
      addColumn(localTable, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(localTable, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(localTable, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(localTable, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[52].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 3 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(18);
    while (localIterator.hasNext())
    {
      RoutineSchema localRoutineSchema = (RoutineSchema)localIterator.next();
      if (paramSession.getGrantee().isAccessible(localRoutineSchema))
      {
        Routine[] arrayOfRoutine = localRoutineSchema.getSpecificRoutines();
        for (int j = 0; j < arrayOfRoutine.length; j++)
        {
          Routine localRoutine = arrayOfRoutine[j];
          int i = localRoutine.getParameterCount();
          for (int k = 0; k < i; k++)
          {
            ColumnSchema localColumnSchema = localRoutine.getParameter(k);
            Type localType = localColumnSchema.getDataType();
            Object[] arrayOfObject = localTable.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localRoutine.getSchemaName().name;
            arrayOfObject[2] = localRoutine.getSpecificName().name;
            arrayOfObject[7] = localColumnSchema.getName().name;
            arrayOfObject[3] = ValuePool.getLong(k + 1);
            switch (localColumnSchema.getParameterMode())
            {
            case 1: 
              arrayOfObject[4] = "IN";
              break;
            case 4: 
              arrayOfObject[4] = "OUT";
              break;
            case 2: 
              arrayOfObject[4] = "INOUT";
            }
            arrayOfObject[5] = "NO";
            arrayOfObject[6] = "NO";
            arrayOfObject[14] = localType.getFullNameString();
            if (localType.isCharacterType())
            {
              arrayOfObject[15] = ValuePool.getLong(localType.precision);
              arrayOfObject[16] = ValuePool.getLong(localType.precision * 2L);
              arrayOfObject[17] = this.database.getCatalogName().name;
              arrayOfObject[18] = ((CharacterType)localType).getCharacterSet().getSchemaName().name;
              arrayOfObject[19] = ((CharacterType)localType).getCharacterSet().getName().name;
              arrayOfObject[20] = this.database.getCatalogName().name;
              arrayOfObject[21] = ((CharacterType)localType).getCollation().getSchemaName().name;
              arrayOfObject[22] = ((CharacterType)localType).getCollation().getName().name;
            }
            else if (localType.isNumberType())
            {
              arrayOfObject[23] = ValuePool.getLong(((NumberType)localType).getNumericPrecisionInRadix());
              arrayOfObject[24] = ValuePool.getLong(localType.getPrecisionRadix());
            }
            else if (!localType.isBooleanType())
            {
              if (localType.isDateTimeType())
              {
                arrayOfObject[26] = ValuePool.getLong(localType.scale);
              }
              else if (localType.isIntervalType())
              {
                arrayOfObject[14] = "INTERVAL";
                ((IntervalType)localType);
                arrayOfObject[27] = IntervalType.getQualifier(localType.typeCode);
                arrayOfObject[28] = ValuePool.getLong(localType.precision);
                arrayOfObject[26] = ValuePool.getLong(localType.scale);
              }
              else if (localType.isBinaryType())
              {
                arrayOfObject[15] = ValuePool.getLong(localType.precision);
                arrayOfObject[16] = ValuePool.getLong(localType.precision);
              }
              else if (localType.isBitType())
              {
                arrayOfObject[15] = ValuePool.getLong(localType.precision);
                arrayOfObject[16] = ValuePool.getLong(localType.precision);
              }
              else if (localType.isArrayType())
              {
                arrayOfObject[35] = ValuePool.getLong(localType.arrayLimitCardinality());
                arrayOfObject[14] = "ARRAY";
              }
            }
            if (localType.isDistinctType())
            {
              arrayOfObject[29] = this.database.getCatalogName().name;
              arrayOfObject[30] = localType.getSchemaName().name;
              arrayOfObject[31] = localType.getName().name;
            }
            arrayOfObject[36] = localType.getDefinition();
            localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
        }
      }
    }
    return localTable;
  }
  
  Table REFERENTIAL_CONSTRAINTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[53];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[53]);
      addColumn(localTable1, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "UNIQUE_CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "UNIQUE_CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "UNIQUE_CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "MATCH_OPTION", CHARACTER_DATA);
      addColumn(localTable1, "UPDATE_RULE", CHARACTER_DATA);
      addColumn(localTable1, "DELETE_RULE", CHARACTER_DATA);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[53].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2 }, false);
      return localTable1;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(3);
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      if ((!localTable2.isView()) && (paramSession.getGrantee().hasNonSelectTableRight(localTable2)))
      {
        Constraint[] arrayOfConstraint = localTable2.getConstraints();
        for (int i = 0; i < arrayOfConstraint.length; i++)
        {
          Constraint localConstraint = arrayOfConstraint[i];
          if (localConstraint.getConstraintType() == 0)
          {
            HsqlNameManager.HsqlName localHsqlName2 = localConstraint.getUniqueName();
            Object[] arrayOfObject = localTable1.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localConstraint.getSchemaName().name;
            arrayOfObject[2] = localConstraint.getName().name;
            if (isAccessibleTable(paramSession, localConstraint.getMain()))
            {
              arrayOfObject[3] = this.database.getCatalogName().name;
              arrayOfObject[4] = localHsqlName2.schema.name;
              arrayOfObject[5] = localHsqlName2.name;
            }
            arrayOfObject[6] = "NONE";
            arrayOfObject[7] = localConstraint.getUpdateActionString();
            arrayOfObject[8] = localConstraint.getDeleteActionString();
            localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
        }
      }
    }
    return localTable1;
  }
  
  Table ROLE_COLUMN_GRANTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[55];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[55]);
      addColumn(localTable, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable, "IS_GRANTABLE", YES_OR_NO);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[55].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 5, 6, 1, 0, 4, 3, 2 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result localResult = ((Session)localObject).executeDirectStatement("SELECT GRANTOR, GRANTEE, TABLE_CATALOG, TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, PRIVILEGE_TYPE, IS_GRANTABLE FROM INFORMATION_SCHEMA.COLUMN_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    ((Session)localObject).close();
    return localTable;
  }
  
  Table ROLE_ROUTINE_GRANTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[57];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[57]);
      addColumn(localTable, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable, "IS_GRANTABLE", YES_OR_NO);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[57].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result localResult = ((Session)localObject).executeDirectStatement("SELECT GRANTOR, GRANTEE, SPECIFIC_CATALOG, SPECIFIC_SCHEMA, SPECIFIC_NAME, ROUTINE_CATALOG, ROUTINE_SCHEMA, ROUTINE_NAME, PRIVILEGE_TYPE, IS_GRANTABLE FROM INFORMATION_SCHEMA.ROUTINE_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    ((Session)localObject).close();
    return localTable;
  }
  
  Table ROLE_TABLE_GRANTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[58];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[58]);
      addColumn(localTable, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable, "IS_GRANTABLE", YES_OR_NO);
      addColumn(localTable, "WITH_HIERARCHY", YES_OR_NO);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[58].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 3, 4, 5, 0, 1 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result localResult = ((Session)localObject).executeDirectStatement("SELECT GRANTOR, GRANTEE, TABLE_CATALOG, TABLE_SCHEMA, TABLE_NAME, PRIVILEGE_TYPE, IS_GRANTABLE, 'NO' FROM INFORMATION_SCHEMA.TABLE_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    ((Session)localObject).close();
    return localTable;
  }
  
  Table ROLE_UDT_GRANTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[59];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[59]);
      addColumn(localTable, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable, "IS_GRANTABLE", YES_OR_NO);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[58].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, null, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result localResult = ((Session)localObject).executeDirectStatement("SELECT GRANTOR, GRANTEE, UDT_CATALOG, UDT_SCHEMA, UDT_NAME, PRIVILEGE_TYPE, IS_GRANTABLE FROM INFORMATION_SCHEMA.UDT_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    ((Session)localObject).close();
    return localTable;
  }
  
  Table ROLE_USAGE_GRANTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[60];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[60]);
      addColumn(localTable, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_TYPE", CHARACTER_DATA);
      addColumn(localTable, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable, "IS_GRANTABLE", YES_OR_NO);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[60].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    Result localResult = ((Session)localObject).executeDirectStatement("SELECT GRANTOR, GRANTEE, OBJECT_CATALOG, OBJECT_SCHEMA, OBJECT_NAME, OBJECT_TYPE, PRIVILEGE_TYPE, IS_GRANTABLE FROM INFORMATION_SCHEMA.USAGE_PRIVILEGES JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON GRANTEE = ROLE_NAME;");
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    ((Session)localObject).close();
    return localTable;
  }
  
  Table ROUTINE_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[61];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[61]);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLUMN_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[61].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 3, 4, 5, 0, 1, 2, 6, 7, 8, 9 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(24);
    while (localIterator.hasNext())
    {
      Routine localRoutine = (Routine)localIterator.next();
      OrderedHashSet localOrderedHashSet = localRoutine.getReferences();
      for (int i = 0; i < localOrderedHashSet.size(); i++)
      {
        HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
        if ((localHsqlName2.type == 9) && (paramSession.getGrantee().isFullyAccessibleByRole(localHsqlName2)))
        {
          Object[] arrayOfObject = localTable.getEmptyRowData();
          arrayOfObject[0] = this.database.getCatalogName().name;
          arrayOfObject[1] = localRoutine.getSchemaName().name;
          arrayOfObject[2] = localRoutine.getSpecificName().name;
          arrayOfObject[3] = this.database.getCatalogName().name;
          arrayOfObject[4] = localRoutine.getSchemaName().name;
          arrayOfObject[5] = localRoutine.getName().name;
          arrayOfObject[6] = this.database.getCatalogName().name;
          arrayOfObject[7] = localHsqlName2.parent.schema.name;
          arrayOfObject[8] = localHsqlName2.parent.name;
          arrayOfObject[9] = localHsqlName2.name;
          try
          {
            localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
          catch (HsqlException localHsqlException) {}
        }
      }
    }
    return localTable;
  }
  
  Table ROUTINE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[63];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[63]);
      addColumn(localTable, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[63].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, false);
      return localTable;
    }
    OrderedHashSet localOrderedHashSet1 = paramSession.getGrantee().visibleGrantees();
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(24);
    while (localIterator.hasNext())
    {
      Routine localRoutine = (Routine)localIterator.next();
      for (int i = 0; i < localOrderedHashSet1.size(); i++)
      {
        Grantee localGrantee = (Grantee)localOrderedHashSet1.get(i);
        Object localObject = localGrantee.getAllDirectPrivileges(localRoutine);
        OrderedHashSet localOrderedHashSet2 = localGrantee.getAllGrantedPrivileges(localRoutine);
        if (!localOrderedHashSet2.isEmpty())
        {
          localOrderedHashSet2.addAll((Collection)localObject);
          localObject = localOrderedHashSet2;
        }
        for (int j = 0; j < ((OrderedHashSet)localObject).size(); j++)
        {
          Right localRight1 = (Right)((OrderedHashSet)localObject).get(j);
          Right localRight2 = localRight1.getGrantableRights();
          if (localRight1.canAccessFully(32))
          {
            String str = "EXECUTE";
            Object[] arrayOfObject = localTable.getEmptyRowData();
            arrayOfObject[0] = localRight1.getGrantor().getName().name;
            arrayOfObject[1] = localRight1.getGrantee().getName().name;
            arrayOfObject[2] = this.database.getCatalogName().name;
            arrayOfObject[3] = localRoutine.getSchemaName().name;
            arrayOfObject[4] = localRoutine.getSpecificName().name;
            arrayOfObject[5] = this.database.getCatalogName().name;
            arrayOfObject[6] = localRoutine.getSchemaName().name;
            arrayOfObject[7] = localRoutine.getName().name;
            arrayOfObject[8] = str;
            arrayOfObject[9] = ((localRight1.getGrantee() == localRoutine.getOwner()) || (localRight2.canAccessFully(32)) ? "YES" : "NO");
            try
            {
              localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException) {}
          }
        }
      }
    }
    return localTable;
  }
  
  Table ROUTINE_JAR_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[62];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[62]);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "JAR_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "JAR_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "JAR_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[62].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable;
    }
    if (!paramSession.isAdmin()) {
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(24);
    while (localIterator.hasNext())
    {
      Routine localRoutine = (Routine)localIterator.next();
      if (localRoutine.getLanguage() == 1)
      {
        Object[] arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localRoutine.getSchemaName().name;
        arrayOfObject[2] = localRoutine.getSpecificName().name;
        arrayOfObject[3] = this.database.getCatalogName().name;
        arrayOfObject[4] = this.database.schemaManager.getSQLJSchemaHsqlName().name;
        arrayOfObject[5] = "CLASSPATH";
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table ROUTINE_ROUTINE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[64];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[64]);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[64].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(24);
    while (localIterator.hasNext())
    {
      Routine localRoutine = (Routine)localIterator.next();
      OrderedHashSet localOrderedHashSet = localRoutine.getReferences();
      for (int i = 0; i < localOrderedHashSet.size(); i++)
      {
        HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
        if ((localHsqlName2.type == 24) && (paramSession.getGrantee().isFullyAccessibleByRole(localHsqlName2)))
        {
          Object[] arrayOfObject = localTable.getEmptyRowData();
          arrayOfObject[0] = this.database.getCatalogName().name;
          arrayOfObject[1] = localRoutine.getSchemaName().name;
          arrayOfObject[2] = localRoutine.getSpecificName().name;
          arrayOfObject[3] = this.database.getCatalogName().name;
          arrayOfObject[4] = localHsqlName2.schema.name;
          arrayOfObject[5] = localHsqlName2.name;
          try
          {
            localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
          catch (HsqlException localHsqlException) {}
        }
      }
    }
    return localTable;
  }
  
  Table ROUTINE_SEQUENCE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[65];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[65]);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[65].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(24);
    while (localIterator.hasNext())
    {
      Routine localRoutine = (Routine)localIterator.next();
      OrderedHashSet localOrderedHashSet = localRoutine.getReferences();
      for (int i = 0; i < localOrderedHashSet.size(); i++)
      {
        HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
        if ((localHsqlName2.type == 7) && (paramSession.getGrantee().isFullyAccessibleByRole(localHsqlName2)))
        {
          Object[] arrayOfObject = localTable.getEmptyRowData();
          arrayOfObject[0] = this.database.getCatalogName().name;
          arrayOfObject[1] = localRoutine.getSchemaName().name;
          arrayOfObject[2] = localRoutine.getSpecificName().name;
          arrayOfObject[3] = this.database.getCatalogName().name;
          arrayOfObject[4] = localHsqlName2.schema.name;
          arrayOfObject[5] = localHsqlName2.name;
          try
          {
            localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
          catch (HsqlException localHsqlException) {}
        }
      }
    }
    return localTable;
  }
  
  Table ROUTINE_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[66];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[66]);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[66].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 3, 4, 5, 0, 1, 2, 6, 7, 8 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(24);
    while (localIterator.hasNext())
    {
      Routine localRoutine = (Routine)localIterator.next();
      OrderedHashSet localOrderedHashSet = localRoutine.getReferences();
      for (int i = 0; i < localOrderedHashSet.size(); i++)
      {
        HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
        if (((localHsqlName2.type == 3) || (localHsqlName2.type == 4)) && (paramSession.getGrantee().isFullyAccessibleByRole(localHsqlName2)))
        {
          Object[] arrayOfObject = localTable.getEmptyRowData();
          arrayOfObject[0] = this.database.getCatalogName().name;
          arrayOfObject[1] = localRoutine.getSchemaName().name;
          arrayOfObject[2] = localRoutine.getSpecificName().name;
          arrayOfObject[3] = this.database.getCatalogName().name;
          arrayOfObject[4] = localRoutine.getSchemaName().name;
          arrayOfObject[5] = localRoutine.getName().name;
          arrayOfObject[6] = this.database.getCatalogName().name;
          arrayOfObject[7] = localHsqlName2.schema.name;
          arrayOfObject[8] = localHsqlName2.name;
          try
          {
            localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
          catch (HsqlException localHsqlException) {}
        }
      }
    }
    return localTable;
  }
  
  Table ROUTINES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[67];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[67]);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_TYPE", CHARACTER_DATA);
      addColumn(localTable, "MODULE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "MODULE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "MODULE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(localTable, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "TYPE_UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TYPE_UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TYPE_UDT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(localTable, "DTD_IDENTIFIER", SQL_IDENTIFIER);
      addColumn(localTable, "ROUTINE_BODY", CHARACTER_DATA);
      addColumn(localTable, "ROUTINE_DEFINITION", CHARACTER_DATA);
      addColumn(localTable, "EXTERNAL_NAME", CHARACTER_DATA);
      addColumn(localTable, "EXTERNAL_LANGUAGE", CHARACTER_DATA);
      addColumn(localTable, "PARAMETER_STYLE", CHARACTER_DATA);
      addColumn(localTable, "IS_DETERMINISTIC", YES_OR_NO);
      addColumn(localTable, "SQL_DATA_ACCESS", CHARACTER_DATA);
      addColumn(localTable, "IS_NULL_CALL", YES_OR_NO);
      addColumn(localTable, "SQL_PATH", CHARACTER_DATA);
      addColumn(localTable, "SCHEMA_LEVEL_ROUTINE", YES_OR_NO);
      addColumn(localTable, "MAX_DYNAMIC_RESULT_SETS", CARDINAL_NUMBER);
      addColumn(localTable, "IS_USER_DEFINED_CAST", YES_OR_NO);
      addColumn(localTable, "IS_IMPLICITLY_INVOCABLE", YES_OR_NO);
      addColumn(localTable, "SECURITY_TYPE", CHARACTER_DATA);
      addColumn(localTable, "TO_SQL_SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TO_SQL_SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TO_SQL_SPECIFIC_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "AS_LOCATOR", YES_OR_NO);
      addColumn(localTable, "CREATED", TIME_STAMP);
      addColumn(localTable, "LAST_ALTERED", TIME_STAMP);
      addColumn(localTable, "NEW_SAVEPOINT_LEVEL", YES_OR_NO);
      addColumn(localTable, "IS_UDT_DEPENDENT", YES_OR_NO);
      addColumn(localTable, "RESULT_CAST_FROM_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "RESULT_CAST_AS_LOCATOR", YES_OR_NO);
      addColumn(localTable, "RESULT_CAST_CHAR_MAX_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_CHAR_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_CHAR_SET_CATALOG", CHARACTER_DATA);
      addColumn(localTable, "RESULT_CAST_CHAR_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_NUMERIC_RADIX", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(localTable, "RESULT_CAST_INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_TYPE_UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_TYPE_UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_TYPE_UDT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_SCOPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "RESULT_CAST_MAX_CARDINALITY", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_DTD_IDENTIFIER", CHARACTER_DATA);
      addColumn(localTable, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_FROM_DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "RESULT_CAST_DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "RESULT_CAST_DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[67].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 3, 4, 5, 0, 1, 2 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(24);
    while (localIterator.hasNext())
    {
      Routine localRoutine = (Routine)localIterator.next();
      if (paramSession.getGrantee().isAccessible(localRoutine))
      {
        boolean bool = paramSession.getGrantee().isFullyAccessibleByRole(localRoutine.getName());
        Object[] arrayOfObject = localTable.getEmptyRowData();
        Type localType = localRoutine.isProcedure() ? null : localRoutine.getReturnType();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localRoutine.getSchemaName().name;
        arrayOfObject[2] = localRoutine.getSpecificName().name;
        arrayOfObject[3] = this.database.getCatalogName().name;
        arrayOfObject[4] = localRoutine.getSchemaName().name;
        arrayOfObject[5] = localRoutine.getName().name;
        arrayOfObject[6] = (localRoutine.isProcedure() ? "PROCEDURE" : "FUNCTION");
        arrayOfObject[7] = null;
        arrayOfObject[8] = null;
        arrayOfObject[9] = null;
        arrayOfObject[10] = null;
        arrayOfObject[11] = null;
        arrayOfObject[12] = null;
        arrayOfObject[13] = (localType == null ? null : localType.getNameString());
        if (localType != null)
        {
          if (localType.isCharacterType())
          {
            arrayOfObject[14] = ValuePool.getLong(localType.precision);
            arrayOfObject[15] = ValuePool.getLong(localType.precision * 2L);
            arrayOfObject[16] = this.database.getCatalogName().name;
            arrayOfObject[17] = ((CharacterType)localType).getCharacterSet().getSchemaName().name;
            arrayOfObject[18] = ((CharacterType)localType).getCharacterSet().getName().name;
            arrayOfObject[19] = this.database.getCatalogName().name;
            arrayOfObject[20] = ((CharacterType)localType).getCollation().getSchemaName().name;
            arrayOfObject[21] = ((CharacterType)localType).getCollation().getName().name;
          }
          else if (localType.isNumberType())
          {
            arrayOfObject[22] = ValuePool.getLong(((NumberType)localType).getNumericPrecisionInRadix());
            arrayOfObject[83] = ValuePool.getLong(((NumberType)localType).getNumericPrecisionInRadix());
            if (localType.isExactNumberType())
            {
              Long tmp1507_1504 = ValuePool.getLong(localType.scale);
              arrayOfObject[84] = tmp1507_1504;
              arrayOfObject[24] = tmp1507_1504;
            }
            arrayOfObject[23] = ValuePool.getLong(localType.getPrecisionRadix());
          }
          else if (!localType.isBooleanType())
          {
            if (localType.isDateTimeType())
            {
              arrayOfObject[25] = ValuePool.getLong(localType.scale);
            }
            else if (localType.isIntervalType())
            {
              arrayOfObject[13] = "INTERVAL";
              ((IntervalType)localType);
              arrayOfObject[26] = IntervalType.getQualifier(localType.typeCode);
              arrayOfObject[27] = ValuePool.getLong(localType.precision);
              arrayOfObject[25] = ValuePool.getLong(localType.scale);
            }
            else if (localType.isBinaryType())
            {
              arrayOfObject[14] = ValuePool.getLong(localType.precision);
              arrayOfObject[15] = ValuePool.getLong(localType.precision);
            }
            else if (localType.isBitType())
            {
              arrayOfObject[14] = ValuePool.getLong(localType.precision);
              arrayOfObject[15] = ValuePool.getLong(localType.precision);
            }
            else if (localType.isArrayType())
            {
              arrayOfObject[34] = ValuePool.getLong(localType.arrayLimitCardinality());
              arrayOfObject[13] = "ARRAY";
            }
          }
          arrayOfObject[35] = localType.getDefinition();
          arrayOfObject[82] = arrayOfObject[13];
        }
        arrayOfObject[28] = null;
        arrayOfObject[29] = null;
        arrayOfObject[30] = null;
        arrayOfObject[31] = null;
        arrayOfObject[32] = null;
        arrayOfObject[33] = null;
        arrayOfObject[36] = (localRoutine.getLanguage() == 1 ? "EXTERNAL" : "SQL");
        arrayOfObject[37] = (bool ? localRoutine.getSQL() : null);
        arrayOfObject[38] = localRoutine.getExternalName();
        arrayOfObject[39] = (localRoutine.getLanguage() == 1 ? "JAVA" : null);
        arrayOfObject[40] = (localRoutine.getLanguage() == 1 ? "JAVA" : null);
        arrayOfObject[41] = (localRoutine.isDeterministic() ? "YES" : "NO");
        arrayOfObject[42] = localRoutine.getDataImpactString();
        arrayOfObject[43] = (localRoutine.isNullInputOutput() ? "YES" : localType == null ? null : "NO");
        arrayOfObject[44] = null;
        arrayOfObject[45] = "YES";
        arrayOfObject[46] = ValuePool.getLong(0L);
        arrayOfObject[47] = (localType == null ? null : "NO");
        arrayOfObject[48] = null;
        arrayOfObject[49] = "DEFINER";
        arrayOfObject[50] = null;
        arrayOfObject[51] = null;
        arrayOfObject[52] = null;
        arrayOfObject[53] = (localType == null ? null : "NO");
        arrayOfObject[54] = null;
        arrayOfObject[55] = null;
        arrayOfObject[56] = "YES";
        arrayOfObject[57] = null;
        arrayOfObject[58] = null;
        arrayOfObject[59] = null;
        arrayOfObject[60] = null;
        arrayOfObject[61] = null;
        arrayOfObject[62] = null;
        arrayOfObject[63] = null;
        arrayOfObject[64] = null;
        arrayOfObject[65] = null;
        arrayOfObject[66] = null;
        arrayOfObject[67] = null;
        arrayOfObject[68] = null;
        arrayOfObject[69] = null;
        arrayOfObject[70] = null;
        arrayOfObject[71] = null;
        arrayOfObject[72] = null;
        arrayOfObject[73] = null;
        arrayOfObject[74] = null;
        arrayOfObject[75] = null;
        arrayOfObject[76] = null;
        arrayOfObject[77] = null;
        arrayOfObject[78] = null;
        arrayOfObject[79] = null;
        arrayOfObject[80] = null;
        arrayOfObject[81] = null;
        arrayOfObject[82] = arrayOfObject[13];
        arrayOfObject[83] = arrayOfObject[22];
        arrayOfObject[84] = arrayOfObject[24];
        arrayOfObject[85] = null;
        arrayOfObject[86] = null;
        arrayOfObject[87] = null;
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table SCHEMATA(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[68];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[68]);
      addColumn(localTable, "CATALOG_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SCHEMA_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SCHEMA_OWNER", SQL_IDENTIFIER);
      addColumn(localTable, "DEFAULT_CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "DEFAULT_CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "DEFAULT_CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SQL_PATH", CHARACTER_DATA);
      localObject1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[68].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject1, new int[] { 0, 1 }, false);
      return localTable;
    }
    String str1 = "INFORMATION_SCHEMA";
    String str2 = "SQL_TEXT";
    Object localObject3 = null;
    Grantee localGrantee = paramSession.getGrantee();
    Object localObject1 = this.database.schemaManager.getAllSchemas();
    for (int i = 0; i < localObject1.length; i++)
    {
      Object localObject2 = localObject1[i];
      if (localGrantee.hasSchemaUpdateOrGrantRights(localObject2.getName().getNameString()))
      {
        Object[] arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localObject2.getName().getNameString();
        arrayOfObject[2] = localObject2.getOwner().getName().getNameString();
        arrayOfObject[3] = this.database.getCatalogName().name;
        arrayOfObject[4] = str1;
        arrayOfObject[5] = str2;
        arrayOfObject[6] = localObject3;
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table SQL_FEATURES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[70];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[70]);
      addColumn(localTable, "FEATURE_ID", CHARACTER_DATA);
      addColumn(localTable, "FEATURE_NAME", CHARACTER_DATA);
      addColumn(localTable, "SUB_FEATURE_ID", CHARACTER_DATA);
      addColumn(localTable, "SUB_FEATURE_NAME", CHARACTER_DATA);
      addColumn(localTable, "IS_SUPPORTED", YES_OR_NO);
      addColumn(localTable, "IS_VERIFIED_BY", CHARACTER_DATA);
      addColumn(localTable, "COMMENTS", CHARACTER_DATA);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[70].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 2 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_features*/");
    Result localResult = ((Session)localObject).executeDirectStatement(str);
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    return localTable;
  }
  
  Table SQL_IMPLEMENTATION_INFO(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[71];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[71]);
      addColumn(localTable, "IMPLEMENTATION_INFO_ID", CARDINAL_NUMBER);
      addColumn(localTable, "IMPLEMENTATION_INFO_NAME", CHARACTER_DATA);
      addColumn(localTable, "INTEGER_VALUE", CARDINAL_NUMBER);
      addColumn(localTable, "CHARACTER_VALUE", CHARACTER_DATA);
      addColumn(localTable, "COMMENTS", CHARACTER_DATA);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[71].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_implementation_info*/");
    Result localResult = ((Session)localObject).executeDirectStatement(str);
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    return localTable;
  }
  
  Table SQL_PACKAGES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[72];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[72]);
      addColumn(localTable, "ID", CHARACTER_DATA);
      addColumn(localTable, "NAME", CHARACTER_DATA);
      addColumn(localTable, "IS_SUPPORTED", YES_OR_NO);
      addColumn(localTable, "IS_VERIFIED_BY", CHARACTER_DATA);
      addColumn(localTable, "COMMENTS", CHARACTER_DATA);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[72].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_packages*/");
    Result localResult = ((Session)localObject).executeDirectStatement(str);
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    return localTable;
  }
  
  Table SQL_PARTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[73];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[73]);
      addColumn(localTable, "PART", CHARACTER_DATA);
      addColumn(localTable, "NAME", CHARACTER_DATA);
      addColumn(localTable, "IS_SUPPORTED", YES_OR_NO);
      addColumn(localTable, "IS_VERIFIED_BY", CHARACTER_DATA);
      addColumn(localTable, "COMMENTS", CHARACTER_DATA);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[73].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_parts*/");
    Result localResult = ((Session)localObject).executeDirectStatement(str);
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    return localTable;
  }
  
  Table SQL_SIZING(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[74];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[74]);
      addColumn(localTable, "SIZING_ID", CARDINAL_NUMBER);
      addColumn(localTable, "SIZING_NAME", CHARACTER_DATA);
      addColumn(localTable, "SUPPORTED_VALUE", CARDINAL_NUMBER);
      addColumn(localTable, "COMMENTS", CHARACTER_DATA);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[74].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    String str = (String)statementMap.get("/*sql_sizing*/");
    Result localResult = ((Session)localObject).executeDirectStatement(str);
    localTable.insertSys(paramSession, paramPersistentStore, localResult);
    return localTable;
  }
  
  Table SQL_SIZING_PROFILES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[75];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[75]);
      addColumn(localTable, "SIZING_ID", CARDINAL_NUMBER);
      addColumn(localTable, "SIZING_NAME", CHARACTER_DATA);
      addColumn(localTable, "PROFILE_ID", CARDINAL_NUMBER);
      addColumn(localTable, "PROFILE_NAME", CHARACTER_DATA);
      addColumn(localTable, "REQUIRED_VALUE", CARDINAL_NUMBER);
      addColumn(localTable, "COMMENTS", CHARACTER_DATA);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[75].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, false);
      return localTable;
    }
    Object localObject = this.database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, paramSession.getUser());
    return localTable;
  }
  
  Table TABLE_CONSTRAINTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[76];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[76]);
      addColumn(localTable1, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "CONSTRAINT_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "IS_DEFERRABLE", YES_OR_NO);
      addColumn(localTable1, "INITIALLY_DEFERRED", YES_OR_NO);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[76].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 1, 2, 4, 5, 6 }, false);
      return localTable1;
    }
    Object localObject = this.database.schemaManager.databaseObjectIterator(3);
    Table localTable2 = null;
    while (((Iterator)localObject).hasNext())
    {
      localTable2 = (Table)((Iterator)localObject).next();
      if ((!localTable2.isView()) && (paramSession.getGrantee().hasNonSelectTableRight(localTable2))) {
        for (Constraint localConstraint : localTable2.getConstraints())
        {
          Object[] arrayOfObject = localTable1.getEmptyRowData();
          switch (localConstraint.getConstraintType())
          {
          case 3: 
            arrayOfObject[3] = "CHECK";
            break;
          case 2: 
            arrayOfObject[3] = "UNIQUE";
            break;
          case 0: 
            arrayOfObject[3] = "FOREIGN KEY";
            localTable2 = localConstraint.getRef();
            break;
          case 4: 
            arrayOfObject[3] = "PRIMARY KEY";
            break;
          }
          String str1 = this.database.getCatalogName().name;
          String str2 = localTable2.getSchemaName().name;
          arrayOfObject[0] = str1;
          arrayOfObject[1] = str2;
          arrayOfObject[2] = localConstraint.getName().name;
          arrayOfObject[4] = str1;
          arrayOfObject[5] = str2;
          arrayOfObject[6] = localTable2.getName().name;
          arrayOfObject[7] = "NO";
          arrayOfObject[8] = "NO";
          localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        }
      }
    }
    return localTable1;
  }
  
  Table TRANSLATIONS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[79];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[79]);
      addColumn(localTable, "TRANSLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TRANSLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TRANSLATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SOURCE_CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SOURCE_CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SOURCE_CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "TARGET_CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TARGET_CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TARGET_CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "TRANSLATION_SOURCE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TRANSLATION_SOURCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TRANSLATION_SOURCE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[79].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable;
    }
    return localTable;
  }
  
  Table TRIGGER_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[80];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[80]);
      addColumn(localTable, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLUMN_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[80].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(8);
    while (localIterator.hasNext())
    {
      TriggerDef localTriggerDef = (TriggerDef)localIterator.next();
      if (paramSession.getGrantee().isFullyAccessibleByRole(localTriggerDef.getName()))
      {
        OrderedHashSet localOrderedHashSet = localTriggerDef.getReferences();
        for (int i = 0; i < localOrderedHashSet.size(); i++)
        {
          HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
          if ((localHsqlName2.type == 9) && (paramSession.getGrantee().isAccessible(localHsqlName2)))
          {
            Object[] arrayOfObject = localTable.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localTriggerDef.getSchemaName().name;
            arrayOfObject[2] = localTriggerDef.getName().name;
            arrayOfObject[3] = this.database.getCatalogName().name;
            arrayOfObject[4] = localHsqlName2.parent.schema.name;
            arrayOfObject[5] = localHsqlName2.parent.name;
            arrayOfObject[6] = localHsqlName2.name;
            try
            {
              localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException) {}
          }
        }
      }
    }
    return localTable;
  }
  
  Table TRIGGER_ROUTINE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[81];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[81]);
      addColumn(localTable, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[81].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(8);
    while (localIterator.hasNext())
    {
      TriggerDef localTriggerDef = (TriggerDef)localIterator.next();
      if (paramSession.getGrantee().isFullyAccessibleByRole(localTriggerDef.getName()))
      {
        OrderedHashSet localOrderedHashSet = localTriggerDef.getReferences();
        for (int i = 0; i < localOrderedHashSet.size(); i++)
        {
          HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
          if (localHsqlName2.type == 24)
          {
            Object[] arrayOfObject = localTable.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localTriggerDef.getSchemaName().name;
            arrayOfObject[2] = localTriggerDef.getName().name;
            arrayOfObject[3] = this.database.getCatalogName().name;
            arrayOfObject[4] = localHsqlName2.schema.name;
            arrayOfObject[5] = localHsqlName2.name;
            try
            {
              localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException) {}
          }
        }
      }
    }
    return localTable;
  }
  
  Table TRIGGER_SEQUENCE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[82];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[82]);
      addColumn(localTable, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[82].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(8);
    while (localIterator.hasNext())
    {
      TriggerDef localTriggerDef = (TriggerDef)localIterator.next();
      if (paramSession.getGrantee().isFullyAccessibleByRole(localTriggerDef.getName()))
      {
        OrderedHashSet localOrderedHashSet = localTriggerDef.getReferences();
        for (int i = 0; i < localOrderedHashSet.size(); i++)
        {
          HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
          if (localHsqlName2.type == 7)
          {
            Object[] arrayOfObject = localTable.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localTriggerDef.getSchemaName().name;
            arrayOfObject[2] = localTriggerDef.getName().name;
            arrayOfObject[3] = this.database.getCatalogName().name;
            arrayOfObject[4] = localHsqlName2.schema.name;
            arrayOfObject[5] = localHsqlName2.name;
            try
            {
              localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException) {}
          }
        }
      }
    }
    return localTable;
  }
  
  Table TRIGGER_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[83];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[83]);
      addColumn(localTable, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[83].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(8);
    while (localIterator.hasNext())
    {
      TriggerDef localTriggerDef = (TriggerDef)localIterator.next();
      if (paramSession.getGrantee().isFullyAccessibleByRole(localTriggerDef.getName()))
      {
        OrderedHashSet localOrderedHashSet = localTriggerDef.getReferences();
        for (int i = 0; i < localOrderedHashSet.size(); i++)
        {
          HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
          if ((localHsqlName2.type == 3) || (localHsqlName2.type == 4))
          {
            Object[] arrayOfObject = localTable.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localTriggerDef.getSchemaName().name;
            arrayOfObject[2] = localTriggerDef.getName().name;
            arrayOfObject[3] = this.database.getCatalogName().name;
            arrayOfObject[4] = localHsqlName2.schema.name;
            arrayOfObject[5] = localHsqlName2.name;
            try
            {
              localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException) {}
          }
        }
      }
    }
    return localTable;
  }
  
  Table TRIGGERS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[85];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[85]);
      addColumn(localTable, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "EVENT_MANIPULATION", SQL_IDENTIFIER);
      addColumn(localTable, "EVENT_OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "EVENT_OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "EVENT_OBJECT_TABLE", SQL_IDENTIFIER);
      addColumn(localTable, "ACTION_ORDER", CARDINAL_NUMBER);
      addColumn(localTable, "ACTION_CONDITION", CHARACTER_DATA);
      addColumn(localTable, "ACTION_STATEMENT", CHARACTER_DATA);
      addColumn(localTable, "ACTION_ORIENTATION", CHARACTER_DATA);
      addColumn(localTable, "ACTION_TIMING", CHARACTER_DATA);
      addColumn(localTable, "ACTION_REFERENCE_OLD_TABLE", SQL_IDENTIFIER);
      addColumn(localTable, "ACTION_REFERENCE_NEW_TABLE", SQL_IDENTIFIER);
      addColumn(localTable, "ACTION_REFERENCE_OLD_ROW", SQL_IDENTIFIER);
      addColumn(localTable, "ACTION_REFERENCE_NEW_ROW", SQL_IDENTIFIER);
      addColumn(localTable, "CREATED", TIME_STAMP);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[85].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(8);
    while (localIterator.hasNext())
    {
      TriggerDef localTriggerDef = (TriggerDef)localIterator.next();
      boolean bool = paramSession.getGrantee().isFullyAccessibleByRole(localTriggerDef.getName());
      if (paramSession.getGrantee().hasNonSelectTableRight(localTriggerDef.getTable()))
      {
        Object[] arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localTriggerDef.getSchemaName().name;
        arrayOfObject[2] = localTriggerDef.getName().name;
        arrayOfObject[3] = localTriggerDef.getEventTypeString();
        arrayOfObject[4] = this.database.getCatalogName().name;
        arrayOfObject[5] = localTriggerDef.getTable().getSchemaName().name;
        arrayOfObject[6] = localTriggerDef.getTable().getName().name;
        int i = localTriggerDef.getTable().getTriggerIndex(localTriggerDef.getName().name);
        arrayOfObject[7] = ValuePool.getLong(i);
        arrayOfObject[8] = (bool ? localTriggerDef.getConditionSQL() : null);
        arrayOfObject[9] = (bool ? localTriggerDef.getProcedureSQL() : null);
        arrayOfObject[10] = localTriggerDef.getActionOrientationString();
        arrayOfObject[11] = localTriggerDef.getActionTimingString();
        arrayOfObject[12] = localTriggerDef.getOldTransitionTableName();
        arrayOfObject[13] = localTriggerDef.getNewTransitionTableName();
        arrayOfObject[14] = localTriggerDef.getOldTransitionRowName();
        arrayOfObject[15] = localTriggerDef.getNewTransitionRowName();
        arrayOfObject[16] = null;
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table TRIGGERED_UPDATE_COLUMNS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[84];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[84]);
      addColumn(localTable, "TRIGGER_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "TRIGGER_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "EVENT_OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "EVENT_OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "EVENT_OBJECT_TABLE", SQL_IDENTIFIER);
      addColumn(localTable, "EVENT_OBJECT_COLUMN", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[84].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(8);
    while (localIterator.hasNext())
    {
      TriggerDef localTriggerDef = (TriggerDef)localIterator.next();
      if (paramSession.getGrantee().isAccessible(localTriggerDef))
      {
        int[] arrayOfInt = localTriggerDef.getUpdateColumnIndexes();
        if (arrayOfInt != null) {
          for (int i = 0; i < arrayOfInt.length; i++)
          {
            ColumnSchema localColumnSchema = localTriggerDef.getTable().getColumn(arrayOfInt[i]);
            Object[] arrayOfObject = localTable.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localTriggerDef.getSchemaName().name;
            arrayOfObject[2] = localTriggerDef.getName().name;
            arrayOfObject[3] = this.database.getCatalogName().name;
            arrayOfObject[4] = localTriggerDef.getTable().getSchemaName().name;
            arrayOfObject[5] = localTriggerDef.getTable().getName().name;
            arrayOfObject[6] = localColumnSchema.getNameString();
            localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
        }
      }
    }
    return localTable;
  }
  
  Table UDT_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[87];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[87]);
      addColumn(localTable, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "UDT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable, "IS_GRANTABLE", YES_OR_NO);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[87].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 3, 4 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(12);
    OrderedHashSet localOrderedHashSet1 = paramSession.getGrantee().getGranteeAndAllRolesWithPublic();
    while (localIterator.hasNext())
    {
      SchemaObject localSchemaObject = (SchemaObject)localIterator.next();
      if (localSchemaObject.getType() == 12) {
        for (int i = 0; i < localOrderedHashSet1.size(); i++)
        {
          Grantee localGrantee = (Grantee)localOrderedHashSet1.get(i);
          Object localObject = localGrantee.getAllDirectPrivileges(localSchemaObject);
          OrderedHashSet localOrderedHashSet2 = localGrantee.getAllGrantedPrivileges(localSchemaObject);
          if (!localOrderedHashSet2.isEmpty())
          {
            localOrderedHashSet2.addAll((Collection)localObject);
            localObject = localOrderedHashSet2;
          }
          for (int j = 0; j < ((OrderedHashSet)localObject).size(); j++)
          {
            Right localRight1 = (Right)((OrderedHashSet)localObject).get(j);
            Right localRight2 = localRight1.getGrantableRights();
            Object[] arrayOfObject = localTable.getEmptyRowData();
            arrayOfObject[0] = localRight1.getGrantor().getName().name;
            arrayOfObject[1] = localRight1.getGrantee().getName().name;
            arrayOfObject[2] = this.database.getCatalogName().name;
            arrayOfObject[3] = localSchemaObject.getSchemaName().name;
            arrayOfObject[4] = localSchemaObject.getName().name;
            arrayOfObject[5] = "USAGE";
            arrayOfObject[6] = ((localRight1.getGrantee() == localSchemaObject.getOwner()) || (localRight2.isFull()) ? "YES" : "NO");
            try
            {
              localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException) {}
          }
        }
      }
    }
    return localTable;
  }
  
  Table USAGE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[88];
    Object localObject1;
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[88]);
      addColumn(localTable, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "OBJECT_TYPE", CHARACTER_DATA);
      addColumn(localTable, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable, "IS_GRANTABLE", YES_OR_NO);
      localObject1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[88].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject1, new int[] { 0, 1, 2, 3, 4, 5, 6, 7 }, false);
      return localTable;
    }
    WrapperIterator localWrapperIterator = new WrapperIterator(this.database.schemaManager.databaseObjectIterator(7), this.database.schemaManager.databaseObjectIterator(15));
    localWrapperIterator = new WrapperIterator(localWrapperIterator, this.database.schemaManager.databaseObjectIterator(14));
    localWrapperIterator = new WrapperIterator(localWrapperIterator, this.database.schemaManager.databaseObjectIterator(13));
    OrderedHashSet localOrderedHashSet1 = paramSession.getGrantee().getGranteeAndAllRolesWithPublic();
    while (localWrapperIterator.hasNext())
    {
      SchemaObject localSchemaObject = (SchemaObject)localWrapperIterator.next();
      for (int i = 0; i < localOrderedHashSet1.size(); i++)
      {
        Grantee localGrantee = (Grantee)localOrderedHashSet1.get(i);
        Object localObject2 = localGrantee.getAllDirectPrivileges(localSchemaObject);
        OrderedHashSet localOrderedHashSet2 = localGrantee.getAllGrantedPrivileges(localSchemaObject);
        if (!localOrderedHashSet2.isEmpty())
        {
          localOrderedHashSet2.addAll((Collection)localObject2);
          localObject2 = localOrderedHashSet2;
        }
        for (int j = 0; j < ((OrderedHashSet)localObject2).size(); j++)
        {
          Right localRight1 = (Right)((OrderedHashSet)localObject2).get(j);
          Right localRight2 = localRight1.getGrantableRights();
          localObject1 = localTable.getEmptyRowData();
          localObject1[0] = localRight1.getGrantor().getName().name;
          localObject1[1] = localRight1.getGrantee().getName().name;
          localObject1[2] = this.database.getCatalogName().name;
          localObject1[3] = localSchemaObject.getSchemaName().name;
          localObject1[4] = localSchemaObject.getName().name;
          localObject1[5] = SchemaObjectSet.getName(localSchemaObject.getName().type);
          localObject1[6] = "USAGE";
          localObject1[7] = ((localRight1.getGrantee() == localSchemaObject.getOwner()) || (localRight2.isFull()) ? "YES" : "NO");
          try
          {
            localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject1);
          }
          catch (HsqlException localHsqlException) {}
        }
      }
    }
    return localTable;
  }
  
  Table USER_DEFINED_TYPES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[89];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[89]);
      addColumn(localTable, "USER_DEFINED_TYPE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "USER_DEFINED_TYPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "USER_DEFINED_TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "USER_DEFINED_TYPE_CATEGORY", SQL_IDENTIFIER);
      addColumn(localTable, "IS_INSTANTIABLE", YES_OR_NO);
      addColumn(localTable, "IS_FINAL", YES_OR_NO);
      addColumn(localTable, "ORDERING_FORM", SQL_IDENTIFIER);
      addColumn(localTable, "ORDERING_CATEGORY", SQL_IDENTIFIER);
      addColumn(localTable, "ORDERING_ROUTINE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "ORDERING_ROUTINE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "ORDERING_ROUTINE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "REFERENCE_TYPE", SQL_IDENTIFIER);
      addColumn(localTable, "DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "CHARACTER_MAXIMUM_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "CHARACTER_OCTET_LENGTH", CARDINAL_NUMBER);
      addColumn(localTable, "CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "CHARACTER_SET_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "COLLATION_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "DATETIME_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "INTERVAL_TYPE", CHARACTER_DATA);
      addColumn(localTable, "INTERVAL_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "SOURCE_DTD_IDENTIFIER", CHARACTER_DATA);
      addColumn(localTable, "REF_DTD_IDENTIFIER", CHARACTER_DATA);
      addColumn(localTable, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "MAXIMUM_CARDINALITY", CARDINAL_NUMBER);
      addColumn(localTable, "EXTERNAL_NAME", CHARACTER_DATA);
      addColumn(localTable, "EXTERNAL_LANGUAGE", CHARACTER_DATA);
      addColumn(localTable, "JAVA_INTERFACE", CHARACTER_DATA);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[89].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 4, 5, 6 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(12);
    while (localIterator.hasNext())
    {
      Type localType = (Type)localIterator.next();
      if (localType.isDistinctType())
      {
        Object[] arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localType.getSchemaName().name;
        arrayOfObject[2] = localType.getName().name;
        arrayOfObject[12] = localType.getFullNameString();
        arrayOfObject[3] = "DISTINCT";
        arrayOfObject[4] = "YES";
        arrayOfObject[5] = "YES";
        arrayOfObject[6] = "FULL";
        if (localType.isCharacterType())
        {
          arrayOfObject[13] = ValuePool.getLong(localType.precision);
          arrayOfObject[14] = ValuePool.getLong(localType.precision * 2L);
          arrayOfObject[15] = this.database.getCatalogName().name;
          arrayOfObject[16] = ((CharacterType)localType).getCharacterSet().getSchemaName().name;
          arrayOfObject[17] = ((CharacterType)localType).getCharacterSet().getName().name;
          arrayOfObject[18] = this.database.getCatalogName().name;
          arrayOfObject[19] = ((CharacterType)localType).getCollation().getSchemaName().name;
          arrayOfObject[20] = ((CharacterType)localType).getCollation().getName().name;
        }
        else if (localType.isNumberType())
        {
          arrayOfObject[21] = ValuePool.getLong(((NumberType)localType).getNumericPrecisionInRadix());
          arrayOfObject[30] = ValuePool.getLong(((NumberType)localType).getNumericPrecisionInRadix());
          if (localType.isExactNumberType())
          {
            Long tmp818_815 = ValuePool.getLong(localType.scale);
            arrayOfObject[31] = tmp818_815;
            arrayOfObject[23] = tmp818_815;
          }
          arrayOfObject[22] = ValuePool.getLong(localType.getPrecisionRadix());
        }
        else if (!localType.isBooleanType())
        {
          if (localType.isDateTimeType())
          {
            arrayOfObject[24] = ValuePool.getLong(localType.scale);
          }
          else if (localType.isIntervalType())
          {
            arrayOfObject[12] = "INTERVAL";
            ((IntervalType)localType);
            arrayOfObject[25] = IntervalType.getQualifier(localType.typeCode);
            arrayOfObject[26] = ValuePool.getLong(localType.precision);
            arrayOfObject[24] = ValuePool.getLong(localType.scale);
          }
          else if (localType.isBinaryType())
          {
            arrayOfObject[13] = ValuePool.getLong(localType.precision);
            arrayOfObject[14] = ValuePool.getLong(localType.precision);
          }
          else if (localType.isBitType())
          {
            arrayOfObject[13] = ValuePool.getLong(localType.precision);
            arrayOfObject[14] = ValuePool.getLong(localType.precision);
          }
          else if (localType.isArrayType())
          {
            arrayOfObject[32] = ValuePool.getLong(localType.arrayLimitCardinality());
            arrayOfObject[12] = "ARRAY";
          }
        }
        arrayOfObject[27] = localType.getDefinition();
        arrayOfObject[29] = arrayOfObject[12];
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table VIEW_COLUMN_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[90];
    Object localObject;
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[90]);
      addColumn(localTable1, "VIEW_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "VIEW_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "VIEW_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[90].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return localTable1;
    }
    Iterator localIterator1 = this.database.schemaManager.databaseObjectIterator(3);
    while (localIterator1.hasNext())
    {
      Table localTable2 = (Table)localIterator1.next();
      if ((localTable2.isView()) && (paramSession.getGrantee().isFullyAccessibleByRole(localTable2.getName())))
      {
        localObject = this.database.getCatalogName().name;
        String str1 = localTable2.getSchemaName().name;
        String str2 = localTable2.getName().name;
        View localView = (View)localTable2;
        OrderedHashSet localOrderedHashSet = localView.getReferences();
        Iterator localIterator2 = localOrderedHashSet.iterator();
        while (localIterator2.hasNext())
        {
          HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localIterator2.next();
          if (localHsqlName.type == 9)
          {
            Object[] arrayOfObject = localTable1.getEmptyRowData();
            arrayOfObject[0] = localObject;
            arrayOfObject[1] = str1;
            arrayOfObject[2] = str2;
            arrayOfObject[3] = localObject;
            arrayOfObject[4] = localHsqlName.parent.schema.name;
            arrayOfObject[5] = localHsqlName.parent.name;
            arrayOfObject[6] = localHsqlName.name;
            try
            {
              localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException) {}
          }
        }
      }
    }
    return localTable1;
  }
  
  Table VIEW_ROUTINE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[91];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[91]);
      addColumn(localTable1, "VIEW_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "VIEW_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "VIEW_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "SPECIFIC_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "SPECIFIC_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "SPECIFIC_NAME", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[91].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable1;
    }
    Object localObject = this.database.schemaManager.databaseObjectIterator(3);
    while (((Iterator)localObject).hasNext())
    {
      Table localTable2 = (Table)((Iterator)localObject).next();
      if (localTable2.isView())
      {
        OrderedHashSet localOrderedHashSet = localTable2.getReferences();
        for (int i = 0; i < localOrderedHashSet.size(); i++)
        {
          HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
          if ((localHsqlName.type == 24) && (paramSession.getGrantee().isFullyAccessibleByRole(localHsqlName)))
          {
            Object[] arrayOfObject = localTable1.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localTable2.getSchemaName().name;
            arrayOfObject[2] = localTable2.getName().name;
            arrayOfObject[3] = this.database.getCatalogName().name;
            arrayOfObject[4] = localHsqlName.schema.name;
            arrayOfObject[5] = localHsqlName.name;
            try
            {
              localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException) {}
          }
        }
      }
    }
    return localTable1;
  }
  
  Table VIEW_TABLE_USAGE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[92];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[92]);
      addColumn(localTable1, "VIEW_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "VIEW_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "VIEW_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[92].name, false, 20);
      localTable1.createPrimaryKeyConstraint(localHsqlName1, new int[] { 0, 1, 2, 3, 4, 5 }, false);
      return localTable1;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(3);
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      if (localTable2.isView())
      {
        OrderedHashSet localOrderedHashSet = localTable2.getReferences();
        for (int i = 0; i < localOrderedHashSet.size(); i++)
        {
          HsqlNameManager.HsqlName localHsqlName2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
          if (((localHsqlName2.type == 3) || (localHsqlName2.type == 4)) && (paramSession.getGrantee().isFullyAccessibleByRole(localHsqlName2)))
          {
            Object[] arrayOfObject = localTable1.getEmptyRowData();
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localTable2.getSchemaName().name;
            arrayOfObject[2] = localTable2.getName().name;
            arrayOfObject[3] = this.database.getCatalogName().name;
            arrayOfObject[4] = localHsqlName2.schema.name;
            arrayOfObject[5] = localHsqlName2.name;
            try
            {
              localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
            catch (HsqlException localHsqlException) {}
          }
        }
      }
    }
    return localTable1;
  }
  
  Table VIEWS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[93];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[93]);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "VIEW_DEFINITION", CHARACTER_DATA);
      addColumn(localTable1, "CHECK_OPTION", CHARACTER_DATA);
      addColumn(localTable1, "IS_UPDATABLE", YES_OR_NO);
      addColumn(localTable1, "INSERTABLE_INTO", YES_OR_NO);
      addColumn(localTable1, "IS_TRIGGER_UPDATABLE", YES_OR_NO);
      addColumn(localTable1, "IS_TRIGGER_DELETABLE", YES_OR_NO);
      addColumn(localTable1, "IS_TRIGGER_INSERTABLE_INTO", YES_OR_NO);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[93].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 1, 2, 0 }, false);
      return localTable1;
    }
    Object localObject = allTables();
    while (((Iterator)localObject).hasNext())
    {
      Table localTable2 = (Table)((Iterator)localObject).next();
      if (((localTable2.isView()) || (localTable2.getSchemaName() == SqlInvariants.INFORMATION_SCHEMA_HSQLNAME)) && (isAccessibleTable(paramSession, localTable2)))
      {
        Object[] arrayOfObject = localTable1.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localTable2.getSchemaName().name;
        arrayOfObject[2] = localTable2.getName().name;
        String str = "NONE";
        if ((localTable2 instanceof View))
        {
          if (paramSession.getGrantee().isFullyAccessibleByRole(localTable2.getName())) {
            arrayOfObject[3] = ((View)localTable2).getStatement();
          }
          switch (((View)localTable2).getCheckOption())
          {
          case 0: 
            break;
          case 1: 
            str = "LOCAL";
            break;
          case 2: 
            str = "CASCADED";
          }
        }
        arrayOfObject[4] = str;
        arrayOfObject[5] = (localTable2.isUpdatable() ? "YES" : "NO");
        arrayOfObject[6] = (localTable2.isInsertable() ? "YES" : "NO");
        arrayOfObject[7] = (localTable2.isTriggerUpdatable() ? "YES" : "NO");
        arrayOfObject[8] = (localTable2.isTriggerDeletable() ? "YES" : "NO");
        arrayOfObject[9] = (localTable2.isTriggerInsertable() ? "YES" : "NO");
        localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable1;
  }
  
  Table ROLE_AUTHORIZATION_DESCRIPTORS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[54];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[54]);
      addColumn(localTable, "ROLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable, "IS_GRANTABLE", YES_OR_NO);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[54].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 1 }, true);
      return localTable;
    }
    Object localObject = "_SYSTEM";
    Iterator localIterator1 = paramSession.getGrantee().visibleGrantees().iterator();
    while (localIterator1.hasNext())
    {
      Grantee localGrantee1 = (Grantee)localIterator1.next();
      String str1 = localGrantee1.getName().getNameString();
      Iterator localIterator2 = localGrantee1.getDirectRoles().iterator();
      String str2 = localGrantee1.isAdmin() ? "YES" : "NO";
      while (localIterator2.hasNext())
      {
        Grantee localGrantee2 = (Grantee)localIterator2.next();
        Object[] arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = localGrantee2.getName().getNameString();
        arrayOfObject[1] = str1;
        arrayOfObject[2] = localObject;
        arrayOfObject[3] = str2;
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  static
  {
    synchronized (DatabaseInformationFull.class)
    {
      String[] arrayOfString = { "/*" };
      InputStream localInputStream = (InputStream)AccessController.doPrivileged(new PrivilegedAction()
      {
        public InputStream run()
        {
          return getClass().getResourceAsStream("/org/hsqldb/resources/information-schema.sql");
        }
      });
      InputStreamReader localInputStreamReader = null;
      try
      {
        localInputStreamReader = new InputStreamReader(localInputStream, "ISO-8859-1");
      }
      catch (Exception localException) {}
      LineNumberReader localLineNumberReader = new LineNumberReader(localInputStreamReader);
      LineGroupReader localLineGroupReader = new LineGroupReader(localLineNumberReader, arrayOfString);
      statementMap = localLineGroupReader.getAsMap();
      localLineGroupReader.close();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.dbinfo.DatabaseInformationFull
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */