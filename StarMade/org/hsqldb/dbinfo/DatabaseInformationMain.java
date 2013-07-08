package org.hsqldb.dbinfo;

import org.hsqldb.ColumnSchema;
import org.hsqldb.Constraint;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.NumberSequence;
import org.hsqldb.Routine;
import org.hsqldb.RoutineSchema;
import org.hsqldb.SchemaManager;
import org.hsqldb.Session;
import org.hsqldb.SessionManager;
import org.hsqldb.SqlInvariants;
import org.hsqldb.Table;
import org.hsqldb.TypeInvariants;
import org.hsqldb.index.Index;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.WrapperIterator;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.Right;
import org.hsqldb.rights.User;
import org.hsqldb.rights.UserManager;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

class DatabaseInformationMain
  extends DatabaseInformation
{
  static Type CARDINAL_NUMBER = TypeInvariants.CARDINAL_NUMBER;
  static Type YES_OR_NO = TypeInvariants.YES_OR_NO;
  static Type CHARACTER_DATA = TypeInvariants.CHARACTER_DATA;
  static Type SQL_IDENTIFIER = TypeInvariants.SQL_IDENTIFIER;
  static Type TIME_STAMP = TypeInvariants.TIME_STAMP;
  protected static final HsqlNameManager.HsqlName[] sysTableHsqlNames;
  protected static final boolean[] sysTableSessionDependent = new boolean[sysTableNames.length];
  protected static final HashSet nonCachedTablesSet;
  protected static final String[] tableTypes = { "GLOBAL TEMPORARY", "SYSTEM TABLE", "TABLE", "VIEW" };
  protected final Table[] sysTables = new Table[sysTableNames.length];
  
  DatabaseInformationMain(Database paramDatabase)
  {
    super(paramDatabase);
    Session localSession = paramDatabase.sessionManager.getSysSession();
    init(localSession);
  }
  
  protected final void addColumn(Table paramTable, String paramString, Type paramType)
  {
    HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaColumnName(paramString, paramTable.getName());
    ColumnSchema localColumnSchema = new ColumnSchema(localHsqlName, paramType, true, false, null);
    paramTable.addColumn(localColumnSchema);
  }
  
  protected final Iterator allTables()
  {
    return new WrapperIterator(this.database.schemaManager.databaseObjectIterator(3), new WrapperIterator(this.sysTables, true));
  }
  
  protected final void cacheClear(Session paramSession)
  {
    int i = this.sysTables.length;
    while (i-- > 0)
    {
      Table localTable = this.sysTables[i];
      if (localTable != null) {
        localTable.clearAllData(paramSession);
      }
    }
  }
  
  protected Table generateTable(Session paramSession, PersistentStore paramPersistentStore, int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return SYSTEM_BESTROWIDENTIFIER(paramSession, paramPersistentStore);
    case 1: 
      return SYSTEM_COLUMNS(paramSession, paramPersistentStore);
    case 18: 
      return SYSTEM_CONNECTION_PROPERTIES(paramSession, paramPersistentStore);
    case 2: 
      return SYSTEM_CROSSREFERENCE(paramSession, paramPersistentStore);
    case 3: 
      return SYSTEM_INDEXINFO(paramSession, paramPersistentStore);
    case 4: 
      return SYSTEM_PRIMARYKEYS(paramSession, paramPersistentStore);
    case 5: 
      return SYSTEM_PROCEDURECOLUMNS(paramSession, paramPersistentStore);
    case 6: 
      return SYSTEM_PROCEDURES(paramSession, paramPersistentStore);
    case 7: 
      return SYSTEM_SCHEMAS(paramSession, paramPersistentStore);
    case 14: 
      return SYSTEM_SEQUENCES(paramSession, paramPersistentStore);
    case 8: 
      return SYSTEM_TABLES(paramSession, paramPersistentStore);
    case 9: 
      return SYSTEM_TABLETYPES(paramSession, paramPersistentStore);
    case 10: 
      return SYSTEM_TYPEINFO(paramSession, paramPersistentStore);
    case 12: 
      return SYSTEM_USERS(paramSession, paramPersistentStore);
    case 11: 
      return SYSTEM_UDTS(paramSession, paramPersistentStore);
    case 13: 
      return SYSTEM_VERSIONCOLUMNS(paramSession, paramPersistentStore);
    case 33: 
      return COLUMN_PRIVILEGES(paramSession, paramPersistentStore);
    case 69: 
      return SEQUENCES(paramSession, paramPersistentStore);
    case 77: 
      return TABLE_PRIVILEGES(paramSession, paramPersistentStore);
    case 43: 
      return INFORMATION_SCHEMA_CATALOG_NAME(paramSession, paramPersistentStore);
    }
    return null;
  }
  
  protected final void init(Session paramSession)
  {
    for (int i = 0; i < this.sysTables.length; i++)
    {
      Table localTable = this.sysTables[i] =  = generateTable(paramSession, null, i);
      if (localTable != null) {
        localTable.setDataReadOnly(true);
      }
    }
    GranteeManager localGranteeManager = this.database.getGranteeManager();
    Right localRight = new Right();
    localRight.set(1, null);
    for (int j = 0; j < sysTableHsqlNames.length; j++) {
      if (this.sysTables[j] != null) {
        localGranteeManager.grantSystemToPublic(this.sysTables[j], localRight);
      }
    }
    localRight = Right.fullRights;
    localGranteeManager.grantSystemToPublic(TypeInvariants.YES_OR_NO, localRight);
    localGranteeManager.grantSystemToPublic(TypeInvariants.TIME_STAMP, localRight);
    localGranteeManager.grantSystemToPublic(TypeInvariants.CARDINAL_NUMBER, localRight);
    localGranteeManager.grantSystemToPublic(TypeInvariants.CHARACTER_DATA, localRight);
    localGranteeManager.grantSystemToPublic(TypeInvariants.SQL_CHARACTER, localRight);
    localGranteeManager.grantSystemToPublic(TypeInvariants.SQL_IDENTIFIER_CHARSET, localRight);
    localGranteeManager.grantSystemToPublic(TypeInvariants.SQL_IDENTIFIER, localRight);
    localGranteeManager.grantSystemToPublic(TypeInvariants.SQL_TEXT, localRight);
  }
  
  protected final boolean isAccessibleTable(Session paramSession, Table paramTable)
  {
    return paramSession.getGrantee().isAccessible(paramTable);
  }
  
  protected final Table createBlankTable(HsqlNameManager.HsqlName paramHsqlName)
  {
    Table localTable = new Table(this.database, paramHsqlName, 1);
    return localTable;
  }
  
  public final Table getSystemTable(Session paramSession, String paramString)
  {
    if (!isSystemTable(paramString)) {
      return null;
    }
    int i = getSysTableID(paramString);
    Table localTable = this.sysTables[i];
    if (localTable == null) {
      return localTable;
    }
    if (!this.withContent) {
      return localTable;
    }
    return localTable;
  }
  
  public boolean isNonCachedTable(String paramString)
  {
    return nonCachedTablesSet.contains(paramString);
  }
  
  public final void setStore(Session paramSession, Table paramTable, PersistentStore paramPersistentStore)
  {
    long l = this.database.schemaManager.getSchemaChangeTimestamp();
    if ((paramPersistentStore.getTimestamp() == l) && (!isNonCachedTable(paramTable.getName().name))) {
      return;
    }
    paramPersistentStore.removeAll();
    paramPersistentStore.setTimestamp(l);
    int i = getSysTableID(paramTable.getName().name);
    generateTable(paramSession, paramPersistentStore, i);
  }
  
  final Table SYSTEM_BESTROWIDENTIFIER(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[0];
    Object localObject1;
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[0]);
      addColumn(localTable1, "SCOPE", Type.SQL_SMALLINT);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "DATA_TYPE", Type.SQL_SMALLINT);
      addColumn(localTable1, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_SIZE", Type.SQL_INTEGER);
      addColumn(localTable1, "BUFFER_LENGTH", Type.SQL_INTEGER);
      addColumn(localTable1, "DECIMAL_DIGITS", Type.SQL_SMALLINT);
      addColumn(localTable1, "PSEUDO_COLUMN", Type.SQL_SMALLINT);
      addColumn(localTable1, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "NULLABLE", Type.SQL_SMALLINT);
      addColumn(localTable1, "IN_KEY", Type.SQL_BOOLEAN);
      localObject1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[0].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject1, new int[] { 0, 8, 9, 10, 1 }, false);
      return localTable1;
    }
    DITableInfo localDITableInfo = new DITableInfo();
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(3);
    boolean bool = this.database.sqlTranslateTTI;
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      if ((!localTable2.isView()) && (isAccessibleTable(paramSession, localTable2)))
      {
        int[] arrayOfInt = localTable2.getBestRowIdentifiers();
        if (arrayOfInt != null)
        {
          localDITableInfo.setTable(localTable2);
          Boolean localBoolean = ValuePool.getBoolean(localTable2.isBestRowIdentifiersStrict());
          String str1 = localTable2.getCatalogName().name;
          String str2 = localTable2.getSchemaName().name;
          String str3 = localTable2.getName().name;
          Type[] arrayOfType = localTable2.getColumnTypes();
          localObject1 = localDITableInfo.getBRIScope();
          Integer localInteger = localDITableInfo.getBRIPseudo();
          for (int i = 0; i < arrayOfInt.length; i++)
          {
            ColumnSchema localColumnSchema = localTable2.getColumn(i);
            Object localObject2 = arrayOfType[i];
            if (bool) {
              if (((Type)localObject2).isIntervalType()) {
                localObject2 = ((IntervalType)localObject2).getCharacterType();
              } else if (((Type)localObject2).isDateTimeTypeWithZone()) {
                localObject2 = ((DateTimeType)localObject2).getDateTimeTypeWithoutZone();
              }
            }
            Object[] arrayOfObject = localTable1.getEmptyRowData();
            arrayOfObject[0] = localObject1;
            arrayOfObject[1] = localColumnSchema.getName().name;
            arrayOfObject[2] = ValuePool.getInt(((Type)localObject2).getJDBCTypeCode());
            arrayOfObject[3] = ((Type)localObject2).getNameString();
            arrayOfObject[4] = ValuePool.getInt(((Type)localObject2).getJDBCPrecision());
            arrayOfObject[5] = null;
            arrayOfObject[6] = (((Type)localObject2).acceptsScale() ? ValuePool.getInt(((Type)localObject2).getJDBCScale()) : null);
            arrayOfObject[7] = localInteger;
            arrayOfObject[8] = str1;
            arrayOfObject[9] = str2;
            arrayOfObject[10] = str3;
            arrayOfObject[11] = ValuePool.getInt(localColumnSchema.getNullability());
            arrayOfObject[12] = localBoolean;
            localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
        }
      }
    }
    return localTable1;
  }
  
  final Table SYSTEM_COLUMNS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[1];
    Object localObject1;
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[1]);
      addColumn(localTable1, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "DATA_TYPE", Type.SQL_SMALLINT);
      addColumn(localTable1, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_SIZE", Type.SQL_INTEGER);
      addColumn(localTable1, "BUFFER_LENGTH", Type.SQL_INTEGER);
      addColumn(localTable1, "DECIMAL_DIGITS", Type.SQL_INTEGER);
      addColumn(localTable1, "NUM_PREC_RADIX", Type.SQL_INTEGER);
      addColumn(localTable1, "NULLABLE", Type.SQL_INTEGER);
      addColumn(localTable1, "REMARKS", CHARACTER_DATA);
      addColumn(localTable1, "COLUMN_DEF", CHARACTER_DATA);
      addColumn(localTable1, "SQL_DATA_TYPE", Type.SQL_INTEGER);
      addColumn(localTable1, "SQL_DATETIME_SUB", Type.SQL_INTEGER);
      addColumn(localTable1, "CHAR_OCTET_LENGTH", Type.SQL_INTEGER);
      addColumn(localTable1, "ORDINAL_POSITION", Type.SQL_INTEGER);
      addColumn(localTable1, "IS_NULLABLE", YES_OR_NO);
      addColumn(localTable1, "SCOPE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "SCOPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "SCOPE_TABLE", SQL_IDENTIFIER);
      addColumn(localTable1, "SOURCE_DATA_TYPE", SQL_IDENTIFIER);
      addColumn(localTable1, "IS_AUTOINCREMENT", YES_OR_NO);
      addColumn(localTable1, "IS_GENERATEDCOLUMN", YES_OR_NO);
      localObject1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[1].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject1, new int[] { 0, 1, 2, 16 }, false);
      return localTable1;
    }
    Iterator localIterator = allTables();
    DITableInfo localDITableInfo = new DITableInfo();
    boolean bool = this.database.sqlTranslateTTI;
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      if (isAccessibleTable(paramSession, localTable2))
      {
        localDITableInfo.setTable(localTable2);
        localObject1 = localTable2.getCatalogName().name;
        String str1 = localTable2.getSchemaName().name;
        String str2 = localTable2.getName().name;
        int i = localTable2.getColumnCount();
        for (int j = 0; j < i; j++)
        {
          ColumnSchema localColumnSchema = localTable2.getColumn(j);
          Object localObject2 = localColumnSchema.getDataType();
          if (bool) {
            if (((Type)localObject2).isIntervalType()) {
              localObject2 = ((IntervalType)localObject2).getCharacterType();
            } else if (((Type)localObject2).isDateTimeTypeWithZone()) {
              localObject2 = ((DateTimeType)localObject2).getDateTimeTypeWithoutZone();
            }
          }
          Object[] arrayOfObject = localTable1.getEmptyRowData();
          arrayOfObject[0] = localObject1;
          arrayOfObject[1] = str1;
          arrayOfObject[2] = str2;
          arrayOfObject[3] = localColumnSchema.getName().name;
          arrayOfObject[4] = ValuePool.getInt(((Type)localObject2).getJDBCTypeCode());
          arrayOfObject[5] = ((Type)localObject2).getNameString();
          arrayOfObject[6] = ValuePool.INTEGER_0;
          arrayOfObject[15] = ValuePool.INTEGER_0;
          if (((Type)localObject2).isArrayType()) {
            arrayOfObject[5] = ((Type)localObject2).getDefinition();
          }
          if (((Type)localObject2).isCharacterType())
          {
            arrayOfObject[6] = ValuePool.getInt(((Type)localObject2).getJDBCPrecision());
            arrayOfObject[15] = ValuePool.getInt(((Type)localObject2).getJDBCPrecision());
          }
          if (((Type)localObject2).isBinaryType())
          {
            arrayOfObject[6] = ValuePool.getInt(((Type)localObject2).getJDBCPrecision());
            arrayOfObject[15] = ValuePool.getInt(((Type)localObject2).getJDBCPrecision());
          }
          if (((Type)localObject2).isNumberType())
          {
            arrayOfObject[6] = ValuePool.getInt(((NumberType)localObject2).getNumericPrecisionInRadix());
            arrayOfObject[9] = ValuePool.getInt(((Type)localObject2).getPrecisionRadix());
            if (((Type)localObject2).isExactNumberType()) {
              arrayOfObject[8] = ValuePool.getLong(((Type)localObject2).scale);
            }
          }
          if (((Type)localObject2).isDateTimeType())
          {
            int k = localColumnSchema.getDataType().displaySize();
            arrayOfObject[6] = ValuePool.getInt(k);
            arrayOfObject[14] = ValuePool.getInt(((DateTimeType)localObject2).getSqlDateTimeSub());
          }
          arrayOfObject[10] = ValuePool.getInt(localColumnSchema.getNullability());
          arrayOfObject[11] = localDITableInfo.getColRemarks(j);
          arrayOfObject[12] = localColumnSchema.getDefaultSQL();
          arrayOfObject[13] = ValuePool.getInt(((Type)localObject2).typeCode);
          arrayOfObject[16] = ValuePool.getInt(j + 1);
          arrayOfObject[17] = (localColumnSchema.isNullable() ? "YES" : "NO");
          if (((Type)localObject2).isDistinctType()) {
            arrayOfObject[21] = ((Type)localObject2).getName().getSchemaQualifiedStatementName();
          }
          arrayOfObject[22] = (localColumnSchema.isIdentity() ? "YES" : "NO");
          arrayOfObject[23] = (localColumnSchema.isGenerated() ? "YES" : "NO");
          localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        }
      }
    }
    return localTable1;
  }
  
  final Table SYSTEM_CROSSREFERENCE(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[2];
    Object localObject;
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[2]);
      addColumn(localTable1, "PKTABLE_CAT", SQL_IDENTIFIER);
      addColumn(localTable1, "PKTABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable1, "PKTABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "PKCOLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "FKTABLE_CAT", SQL_IDENTIFIER);
      addColumn(localTable1, "FKTABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable1, "FKTABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "FKCOLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "KEY_SEQ", Type.SQL_SMALLINT);
      addColumn(localTable1, "UPDATE_RULE", Type.SQL_SMALLINT);
      addColumn(localTable1, "DELETE_RULE", Type.SQL_SMALLINT);
      addColumn(localTable1, "FK_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "PK_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "DEFERRABILITY", Type.SQL_SMALLINT);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[2].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 4, 5, 6, 8, 11 }, false);
      return localTable1;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(3);
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Constraint localConstraint;
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      if ((!localTable2.isView()) && (isAccessibleTable(paramSession, localTable2))) {
        for (localConstraint : localTable2.getConstraints()) {
          if ((localConstraint.getConstraintType() == 0) && (isAccessibleTable(paramSession, localConstraint.getRef()))) {
            localHsqlArrayList.add(localConstraint);
          }
        }
      }
    }
    for (??? = 0; ??? < localHsqlArrayList.size(); ???++)
    {
      localConstraint = (Constraint)localHsqlArrayList.get(???);
      Table localTable4 = localConstraint.getMain();
      String str2 = localTable4.getName().name;
      Table localTable3 = localConstraint.getRef();
      String str6 = localTable3.getName().name;
      localObject = localTable4.getCatalogName().name;
      String str1 = localTable4.getSchemaName().name;
      String str4 = localTable3.getCatalogName().name;
      String str5 = localTable3.getSchemaName().name;
      int[] arrayOfInt1 = localConstraint.getMainColumns();
      int[] arrayOfInt2 = localConstraint.getRefColumns();
      int i = arrayOfInt2.length;
      String str8 = localConstraint.getRefName().name;
      String str9 = localConstraint.getUniqueName().name;
      Integer localInteger4 = ValuePool.getInt(localConstraint.getDeferability());
      Integer localInteger3 = ValuePool.getInt(localConstraint.getDeleteAction());
      Integer localInteger2 = ValuePool.getInt(localConstraint.getUpdateAction());
      for (int m = 0; m < i; m++)
      {
        Integer localInteger1 = ValuePool.getInt(m + 1);
        String str3 = localTable4.getColumn(arrayOfInt1[m]).getNameString();
        String str7 = localTable3.getColumn(arrayOfInt2[m]).getNameString();
        Object[] arrayOfObject = localTable1.getEmptyRowData();
        arrayOfObject[0] = localObject;
        arrayOfObject[1] = str1;
        arrayOfObject[2] = str2;
        arrayOfObject[3] = str3;
        arrayOfObject[4] = str4;
        arrayOfObject[5] = str5;
        arrayOfObject[6] = str6;
        arrayOfObject[7] = str7;
        arrayOfObject[8] = localInteger1;
        arrayOfObject[9] = localInteger2;
        arrayOfObject[10] = localInteger3;
        arrayOfObject[11] = str8;
        arrayOfObject[12] = str9;
        arrayOfObject[13] = localInteger4;
        localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable1;
  }
  
  final Table SYSTEM_INDEXINFO(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[3];
    Object localObject1;
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[3]);
      addColumn(localTable1, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "NON_UNIQUE", Type.SQL_BOOLEAN);
      addColumn(localTable1, "INDEX_QUALIFIER", SQL_IDENTIFIER);
      addColumn(localTable1, "INDEX_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "TYPE", Type.SQL_SMALLINT);
      addColumn(localTable1, "ORDINAL_POSITION", Type.SQL_SMALLINT);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "ASC_OR_DESC", CHARACTER_DATA);
      addColumn(localTable1, "CARDINALITY", Type.SQL_INTEGER);
      addColumn(localTable1, "PAGES", Type.SQL_INTEGER);
      addColumn(localTable1, "FILTER_CONDITION", CHARACTER_DATA);
      addColumn(localTable1, "ROW_CARDINALITY", Type.SQL_INTEGER);
      localObject1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[3].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject1, new int[] { 0, 1, 2, 3, 4, 5, 7 }, false);
      return localTable1;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(3);
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      if ((!localTable2.isView()) && (isAccessibleTable(paramSession, localTable2)))
      {
        localObject1 = localTable2.getCatalogName().name;
        String str1 = localTable2.getSchemaName().name;
        String str2 = localTable2.getName().name;
        Object localObject4 = null;
        Object localObject2 = localObject1;
        int i = localTable2.getIndexCount();
        for (int m = 0; m < i; m++)
        {
          Index localIndex = localTable2.getIndex(m);
          int k = localTable2.getIndex(m).getColumnCount();
          if (k >= 1)
          {
            String str3 = localIndex.getName().name;
            Boolean localBoolean = localIndex.isUnique() ? Boolean.FALSE : Boolean.TRUE;
            Object localObject3 = null;
            Integer localInteger2 = ValuePool.INTEGER_0;
            Object localObject5 = null;
            int[] arrayOfInt = localIndex.getColumns();
            Integer localInteger1 = ValuePool.getInt(3);
            for (int n = 0; n < k; n++)
            {
              int j = arrayOfInt[n];
              Object[] arrayOfObject = localTable1.getEmptyRowData();
              arrayOfObject[0] = localObject1;
              arrayOfObject[1] = str1;
              arrayOfObject[2] = str2;
              arrayOfObject[3] = localBoolean;
              arrayOfObject[4] = localObject2;
              arrayOfObject[5] = str3;
              arrayOfObject[6] = localInteger1;
              arrayOfObject[7] = ValuePool.getInt(n + 1);
              arrayOfObject[8] = localTable2.getColumn(arrayOfInt[n]).getName().name;
              arrayOfObject[9] = "A";
              arrayOfObject[10] = localObject3;
              arrayOfObject[11] = localInteger2;
              arrayOfObject[13] = localObject5;
              arrayOfObject[12] = localObject4;
              localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
            }
          }
        }
      }
    }
    return localTable1;
  }
  
  final Table SYSTEM_PRIMARYKEYS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[4];
    Object localObject;
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[4]);
      addColumn(localTable1, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "KEY_SEQ", Type.SQL_SMALLINT);
      addColumn(localTable1, "PK_NAME", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[4].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 3, 2, 1, 0 }, false);
      return localTable1;
    }
    HsqlDatabaseProperties localHsqlDatabaseProperties = this.database.getProperties();
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(3);
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      if ((!localTable2.isView()) && (isAccessibleTable(paramSession, localTable2)) && (localTable2.hasPrimaryKey()))
      {
        Constraint localConstraint = localTable2.getPrimaryConstraint();
        localObject = localTable2.getCatalogName().name;
        String str1 = localTable2.getSchemaName().name;
        String str2 = localTable2.getName().name;
        String str3 = localConstraint.getName().name;
        int[] arrayOfInt = localConstraint.getMainColumns();
        int i = arrayOfInt.length;
        for (int j = 0; j < i; j++)
        {
          Object[] arrayOfObject = localTable1.getEmptyRowData();
          arrayOfObject[0] = localObject;
          arrayOfObject[1] = str1;
          arrayOfObject[2] = str2;
          arrayOfObject[3] = localTable2.getColumn(arrayOfInt[j]).getName().name;
          arrayOfObject[4] = ValuePool.getInt(j + 1);
          arrayOfObject[5] = str3;
          localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
        }
      }
    }
    return localTable1;
  }
  
  Table SYSTEM_PROCEDURECOLUMNS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[5];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[5]);
      addColumn(localTable, "PROCEDURE_CAT", SQL_IDENTIFIER);
      addColumn(localTable, "PROCEDURE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable, "PROCEDURE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLUMN_TYPE", Type.SQL_SMALLINT);
      addColumn(localTable, "DATA_TYPE", Type.SQL_SMALLINT);
      addColumn(localTable, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "PRECISION", Type.SQL_INTEGER);
      addColumn(localTable, "LENGTH", Type.SQL_INTEGER);
      addColumn(localTable, "SCALE", Type.SQL_SMALLINT);
      addColumn(localTable, "RADIX", Type.SQL_SMALLINT);
      addColumn(localTable, "NULLABLE", Type.SQL_SMALLINT);
      addColumn(localTable, "REMARKS", CHARACTER_DATA);
      addColumn(localTable, "COLUMN_DEF", CHARACTER_DATA);
      addColumn(localTable, "SQL_DATA_TYPE", Type.SQL_INTEGER);
      addColumn(localTable, "SQL_DATETIME_SUB", Type.SQL_INTEGER);
      addColumn(localTable, "CHAR_OCTET_LENGTH", Type.SQL_INTEGER);
      addColumn(localTable, "ORDINAL_POSITION", Type.SQL_INTEGER);
      addColumn(localTable, "IS_NULLABLE", CHARACTER_DATA);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[5].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 19, 17 }, false);
      return localTable;
    }
    boolean bool = this.database.sqlTranslateTTI;
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
            Object[] arrayOfObject = localTable.getEmptyRowData();
            Object localObject = localColumnSchema.getDataType();
            if (bool) {
              if (((Type)localObject).isIntervalType()) {
                localObject = ((IntervalType)localObject).getCharacterType();
              } else if (((Type)localObject).isDateTimeTypeWithZone()) {
                localObject = ((DateTimeType)localObject).getDateTimeTypeWithoutZone();
              }
            }
            arrayOfObject[0] = this.database.getCatalogName().name;
            arrayOfObject[1] = localRoutine.getSchemaName().name;
            arrayOfObject[19] = localRoutine.getSpecificName().name;
            arrayOfObject[2] = localRoutine.getName().name;
            arrayOfObject[3] = localColumnSchema.getName().name;
            arrayOfObject[17] = ValuePool.getInt(k + 1);
            arrayOfObject[4] = ValuePool.getInt(localColumnSchema.getParameterMode());
            arrayOfObject[6] = ((Type)localObject).getFullNameString();
            arrayOfObject[5] = ValuePool.getInt(((Type)localObject).getJDBCTypeCode());
            arrayOfObject[7] = ValuePool.INTEGER_0;
            arrayOfObject[16] = ValuePool.INTEGER_0;
            if (((Type)localObject).isCharacterType())
            {
              arrayOfObject[7] = ValuePool.getInt(((Type)localObject).getJDBCPrecision());
              arrayOfObject[16] = ValuePool.getInt(((Type)localObject).getJDBCPrecision());
            }
            if (((Type)localObject).isBinaryType())
            {
              arrayOfObject[7] = ValuePool.getInt(((Type)localObject).getJDBCPrecision());
              arrayOfObject[16] = ValuePool.getInt(((Type)localObject).getJDBCPrecision());
            }
            if (((Type)localObject).isNumberType())
            {
              arrayOfObject[7] = ValuePool.getInt(((NumberType)localObject).getNumericPrecisionInRadix());
              arrayOfObject[10] = ValuePool.getLong(((Type)localObject).getPrecisionRadix());
              if (((Type)localObject).isExactNumberType()) {
                arrayOfObject[9] = ValuePool.getLong(((Type)localObject).scale);
              }
            }
            if (((Type)localObject).isDateTimeType())
            {
              int m = localColumnSchema.getDataType().displaySize();
              arrayOfObject[7] = ValuePool.getInt(m);
            }
            arrayOfObject[14] = ValuePool.getInt(localColumnSchema.getDataType().typeCode);
            arrayOfObject[11] = ValuePool.getInt(localColumnSchema.getNullability());
            arrayOfObject[18] = (localColumnSchema.isNullable() ? "YES" : "NO");
            localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
          }
        }
      }
    }
    return localTable;
  }
  
  Table SYSTEM_PROCEDURES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[6];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[6]);
      addColumn(localTable, "PROCEDURE_CAT", SQL_IDENTIFIER);
      addColumn(localTable, "PROCEDURE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable, "PROCEDURE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COL_4", Type.SQL_INTEGER);
      addColumn(localTable, "COL_5", Type.SQL_INTEGER);
      addColumn(localTable, "COL_6", Type.SQL_INTEGER);
      addColumn(localTable, "REMARKS", CHARACTER_DATA);
      addColumn(localTable, "PROCEDURE_TYPE", Type.SQL_SMALLINT);
      addColumn(localTable, "SPECIFIC_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[6].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2, 8 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(24);
    while (localIterator.hasNext())
    {
      Routine localRoutine = (Routine)localIterator.next();
      Object[] arrayOfObject = localTable.getEmptyRowData();
      String tmp217_214 = this.database.getCatalogName().name;
      arrayOfObject[0] = tmp217_214;
      arrayOfObject[0] = tmp217_214;
      arrayOfObject[1] = localRoutine.getSchemaName().name;
      arrayOfObject[2] = localRoutine.getName().name;
      arrayOfObject[6] = localRoutine.getName().comment;
      arrayOfObject[7] = (localRoutine.isProcedure() ? ValuePool.INTEGER_1 : ValuePool.INTEGER_2);
      arrayOfObject[8] = localRoutine.getSpecificName().name;
      localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    }
    return localTable;
  }
  
  final Table SYSTEM_CONNECTION_PROPERTIES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[18];
    Object localObject1;
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[18]);
      addColumn(localTable, "NAME", SQL_IDENTIFIER);
      addColumn(localTable, "MAX_LEN", Type.SQL_INTEGER);
      addColumn(localTable, "DEFAULT_VALUE", SQL_IDENTIFIER);
      addColumn(localTable, "DESCRIPTION", SQL_IDENTIFIER);
      localObject1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[4].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject1, new int[] { 0 }, true);
      return localTable;
    }
    Iterator localIterator = HsqlDatabaseProperties.getPropertiesMetaIterator();
    while (localIterator.hasNext())
    {
      Object[] arrayOfObject = (Object[])localIterator.next();
      int i = ((Integer)arrayOfObject[1]).intValue();
      if (i == 1 ? ("readonly".equals(arrayOfObject[0])) || ("files_readonly".equals(arrayOfObject[0])) : i == 2)
      {
        localObject1 = localTable.getEmptyRowData();
        Object localObject2 = arrayOfObject[4];
        localObject1[0] = arrayOfObject[0];
        localObject1[1] = ValuePool.getInt(8);
        localObject1[2] = (localObject2 == null ? null : localObject2.toString());
        localObject1[3] = "see HyperSQL guide";
        localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject1);
      }
    }
    return localTable;
  }
  
  protected void addProcRows(Session paramSession, Table paramTable, HsqlArrayList paramHsqlArrayList, String paramString1, String paramString2, String paramString3, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, String paramString4, Integer paramInteger4, String paramString5, String paramString6)
  {
    PersistentStore localPersistentStore = paramTable.getRowStore(paramSession);
    Object[] arrayOfObject = paramTable.getEmptyRowData();
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = paramString3;
    arrayOfObject[3] = paramInteger1;
    arrayOfObject[4] = paramInteger2;
    arrayOfObject[5] = paramInteger3;
    arrayOfObject[6] = paramString4;
    arrayOfObject[7] = paramInteger4;
    arrayOfObject[9] = paramString6;
    arrayOfObject[8] = paramString5;
    paramTable.insertSys(paramSession, localPersistentStore, arrayOfObject);
    if (paramHsqlArrayList != null)
    {
      int i = paramHsqlArrayList.size();
      for (int j = 0; j < i; j++)
      {
        arrayOfObject = paramTable.getEmptyRowData();
        paramString3 = (String)paramHsqlArrayList.get(j);
        arrayOfObject[0] = paramString1;
        arrayOfObject[1] = paramString2;
        arrayOfObject[2] = paramString3;
        arrayOfObject[3] = paramInteger1;
        arrayOfObject[4] = paramInteger2;
        arrayOfObject[5] = paramInteger3;
        arrayOfObject[6] = paramString4;
        arrayOfObject[7] = paramInteger4;
        arrayOfObject[9] = "ALIAS";
        arrayOfObject[8] = paramString5;
        paramTable.insertSys(paramSession, localPersistentStore, arrayOfObject);
      }
    }
  }
  
  protected void addPColRows(Session paramSession, Table paramTable, HsqlArrayList paramHsqlArrayList, String paramString1, String paramString2, String paramString3, String paramString4, Integer paramInteger1, Integer paramInteger2, String paramString5, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, Integer paramInteger6, Integer paramInteger7, String paramString6, String paramString7, Integer paramInteger8, Integer paramInteger9, Integer paramInteger10, Integer paramInteger11, String paramString8, String paramString9)
  {
    PersistentStore localPersistentStore = paramTable.getRowStore(paramSession);
    Object[] arrayOfObject = paramTable.getEmptyRowData();
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = paramString3;
    arrayOfObject[3] = paramString4;
    arrayOfObject[4] = paramInteger1;
    arrayOfObject[5] = paramInteger2;
    arrayOfObject[6] = paramString5;
    arrayOfObject[7] = paramInteger3;
    arrayOfObject[8] = paramInteger4;
    arrayOfObject[9] = paramInteger5;
    arrayOfObject[10] = paramInteger6;
    arrayOfObject[11] = paramInteger7;
    arrayOfObject[12] = paramString6;
    arrayOfObject[13] = paramString7;
    arrayOfObject[14] = paramInteger8;
    arrayOfObject[15] = paramInteger9;
    arrayOfObject[16] = paramInteger10;
    arrayOfObject[17] = paramInteger11;
    arrayOfObject[18] = paramString8;
    arrayOfObject[19] = paramString9;
    paramTable.insertSys(paramSession, localPersistentStore, arrayOfObject);
    if (paramHsqlArrayList != null)
    {
      int i = paramHsqlArrayList.size();
      for (int j = 0; j < i; j++)
      {
        arrayOfObject = paramTable.getEmptyRowData();
        paramString3 = (String)paramHsqlArrayList.get(j);
        arrayOfObject[0] = paramString1;
        arrayOfObject[1] = paramString2;
        arrayOfObject[2] = paramString3;
        arrayOfObject[3] = paramString4;
        arrayOfObject[4] = paramInteger1;
        arrayOfObject[5] = paramInteger2;
        arrayOfObject[6] = paramString5;
        arrayOfObject[7] = paramInteger3;
        arrayOfObject[8] = paramInteger4;
        arrayOfObject[9] = paramInteger5;
        arrayOfObject[10] = paramInteger6;
        arrayOfObject[11] = paramInteger7;
        arrayOfObject[12] = paramString6;
        arrayOfObject[13] = paramString7;
        arrayOfObject[14] = paramInteger8;
        arrayOfObject[15] = paramInteger9;
        arrayOfObject[16] = paramInteger10;
        arrayOfObject[17] = paramInteger11;
        arrayOfObject[18] = paramString8;
        arrayOfObject[19] = paramString9;
        paramTable.insertSys(paramSession, localPersistentStore, arrayOfObject);
      }
    }
  }
  
  final Table SYSTEM_SCHEMAS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[7];
    Object localObject;
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[7]);
      addColumn(localTable, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "IS_DEFAULT", Type.SQL_BOOLEAN);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[7].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, true);
      return localTable;
    }
    String[] arrayOfString = this.database.schemaManager.getSchemaNamesArray();
    String str1 = this.database.schemaManager.getDefaultSchemaHsqlName().name;
    for (int i = 0; i < arrayOfString.length; i++)
    {
      localObject = localTable.getEmptyRowData();
      String str2 = arrayOfString[i];
      localObject[0] = str2;
      localObject[1] = this.database.getCatalogName().name;
      localObject[2] = (str2.equals(str1) ? Boolean.TRUE : Boolean.FALSE);
      localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    }
    return localTable;
  }
  
  final Table SYSTEM_TABLES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[8];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[8]);
      addColumn(localTable1, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "REMARKS", CHARACTER_DATA);
      addColumn(localTable1, "TYPE_CAT", SQL_IDENTIFIER);
      addColumn(localTable1, "TYPE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable1, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "SELF_REFERENCING_COL_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "REF_GENERATION", CHARACTER_DATA);
      addColumn(localTable1, "HSQLDB_TYPE", SQL_IDENTIFIER);
      addColumn(localTable1, "READ_ONLY", Type.SQL_BOOLEAN);
      addColumn(localTable1, "COMMIT_ACTION", CHARACTER_DATA);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[8].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 3, 1, 2, 0 }, false);
      return localTable1;
    }
    Object localObject = allTables();
    DITableInfo localDITableInfo = new DITableInfo();
    while (((Iterator)localObject).hasNext())
    {
      Table localTable2 = (Table)((Iterator)localObject).next();
      if (isAccessibleTable(paramSession, localTable2))
      {
        localDITableInfo.setTable(localTable2);
        Object[] arrayOfObject = localTable1.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localTable2.getSchemaName().name;
        arrayOfObject[2] = localTable2.getName().name;
        arrayOfObject[3] = localDITableInfo.getJDBCStandardType();
        arrayOfObject[4] = localDITableInfo.getRemark();
        arrayOfObject[10] = localDITableInfo.getHsqlType();
        arrayOfObject[11] = (localTable2.isDataReadOnly() ? Boolean.TRUE : Boolean.FALSE);
        arrayOfObject[12] = (localTable2.isTemp() ? "DELETE" : localTable2.onCommitPreserve() ? "PRESERVE" : null);
        localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable1;
  }
  
  Table SYSTEM_TABLETYPES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[9];
    Object localObject;
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[9]);
      addColumn(localTable, "TABLE_TYPE", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[9].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, true);
      return localTable;
    }
    for (int i = 0; i < tableTypes.length; i++)
    {
      localObject = localTable.getEmptyRowData();
      localObject[0] = tableTypes[i];
      localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    }
    return localTable;
  }
  
  final Table SYSTEM_TYPEINFO(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[10];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[10]);
      addColumn(localTable, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "DATA_TYPE", Type.SQL_SMALLINT);
      addColumn(localTable, "PRECISION", Type.SQL_INTEGER);
      addColumn(localTable, "LITERAL_PREFIX", CHARACTER_DATA);
      addColumn(localTable, "LITERAL_SUFFIX", CHARACTER_DATA);
      addColumn(localTable, "CREATE_PARAMS", CHARACTER_DATA);
      addColumn(localTable, "NULLABLE", Type.SQL_SMALLINT);
      addColumn(localTable, "CASE_SENSITIVE", Type.SQL_BOOLEAN);
      addColumn(localTable, "SEARCHABLE", Type.SQL_INTEGER);
      addColumn(localTable, "UNSIGNED_ATTRIBUTE", Type.SQL_BOOLEAN);
      addColumn(localTable, "FIXED_PREC_SCALE", Type.SQL_BOOLEAN);
      addColumn(localTable, "AUTO_INCREMENT", Type.SQL_BOOLEAN);
      addColumn(localTable, "LOCAL_TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "MINIMUM_SCALE", Type.SQL_SMALLINT);
      addColumn(localTable, "MAXIMUM_SCALE", Type.SQL_SMALLINT);
      addColumn(localTable, "SQL_DATA_TYPE", Type.SQL_INTEGER);
      addColumn(localTable, "SQL_DATETIME_SUB", Type.SQL_INTEGER);
      addColumn(localTable, "NUM_PREC_RADIX", Type.SQL_INTEGER);
      addColumn(localTable, "INTERVAL_PRECISION", Type.SQL_INTEGER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[10].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 1, 0 }, true);
      return localTable;
    }
    Iterator localIterator = Type.typeNames.keySet().iterator();
    boolean bool = this.database.sqlTranslateTTI;
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      int i = Type.typeNames.get(str);
      Object localObject = Type.getDefaultType(i);
      if (localObject != null)
      {
        if (bool) {
          if (((Type)localObject).isIntervalType()) {
            localObject = ((IntervalType)localObject).getCharacterType();
          } else if (((Type)localObject).isDateTimeTypeWithZone()) {
            localObject = ((DateTimeType)localObject).getDateTimeTypeWithoutZone();
          }
        }
        arrayOfObject = localTable.getEmptyRowData();
        arrayOfObject[0] = str;
        arrayOfObject[1] = ValuePool.getInt(((Type)localObject).getJDBCTypeCode());
        long l = ((Type)localObject).getMaxPrecision();
        arrayOfObject[2] = (l > 2147483647L ? ValuePool.INTEGER_MAX : ValuePool.getInt((int)l));
        if ((((Type)localObject).isBinaryType()) || (((Type)localObject).isCharacterType()) || (((Type)localObject).isDateTimeType()) || (((Type)localObject).isIntervalType()))
        {
          arrayOfObject[3] = "'";
          arrayOfObject[4] = "'";
        }
        if ((((Type)localObject).acceptsPrecision()) && (((Type)localObject).acceptsScale())) {
          arrayOfObject[5] = "PRECISION,SCALE";
        } else if (((Type)localObject).acceptsPrecision()) {
          arrayOfObject[5] = (((Type)localObject).isNumberType() ? "PRECISION" : "LENGTH");
        } else if (((Type)localObject).acceptsScale()) {
          arrayOfObject[5] = "SCALE";
        }
        arrayOfObject[6] = ValuePool.INTEGER_1;
        arrayOfObject[7] = ((((Type)localObject).isCharacterType()) && (((Type)localObject).typeCode != 100) ? Boolean.TRUE : Boolean.FALSE);
        if (((Type)localObject).isLobType()) {
          arrayOfObject[8] = ValuePool.INTEGER_0;
        } else if ((((Type)localObject).isCharacterType()) || ((((Type)localObject).isBinaryType()) && (!((Type)localObject).isBitType()))) {
          arrayOfObject[8] = ValuePool.getInt(3);
        } else {
          arrayOfObject[8] = ValuePool.getInt(2);
        }
        arrayOfObject[9] = Boolean.FALSE;
        arrayOfObject[10] = ((((Type)localObject).typeCode == 2) || (((Type)localObject).typeCode == 3) ? Boolean.TRUE : Boolean.FALSE);
        arrayOfObject[11] = (((Type)localObject).isIntegralType() ? Boolean.TRUE : Boolean.FALSE);
        arrayOfObject[12] = null;
        arrayOfObject[13] = ValuePool.INTEGER_0;
        arrayOfObject[14] = ValuePool.getInt(((Type)localObject).getMaxScale());
        arrayOfObject[15] = null;
        arrayOfObject[16] = null;
        arrayOfObject[17] = ValuePool.getInt(((Type)localObject).getPrecisionRadix());
        if (((Type)localObject).isIntervalType()) {
          arrayOfObject[18] = null;
        }
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    Object[] arrayOfObject = localTable.getEmptyRowData();
    arrayOfObject[0] = "DISTINCT";
    arrayOfObject[1] = ValuePool.getInt(2001);
    return localTable;
  }
  
  Table SYSTEM_UDTS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[11];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[11]);
      addColumn(localTable, "TYPE_CAT", SQL_IDENTIFIER);
      addColumn(localTable, "TYPE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "CLASS_NAME", CHARACTER_DATA);
      addColumn(localTable, "DATA_TYPE", Type.SQL_INTEGER);
      addColumn(localTable, "REMARKS", CHARACTER_DATA);
      addColumn(localTable, "BASE_TYPE", Type.SQL_SMALLINT);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[11].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, null, false);
      return localTable;
    }
    boolean bool = this.database.sqlTranslateTTI;
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(12);
    while (localIterator.hasNext())
    {
      Type localType = (Type)localIterator.next();
      if (localType.isDistinctType())
      {
        Object[] arrayOfObject = localTable.getEmptyRowData();
        Object localObject = localType;
        if (bool) {
          if (((Type)localObject).isIntervalType()) {
            localObject = ((IntervalType)localObject).getCharacterType();
          } else if (((Type)localObject).isDateTimeTypeWithZone()) {
            localObject = ((DateTimeType)localObject).getDateTimeTypeWithoutZone();
          }
        }
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localType.getSchemaName().name;
        arrayOfObject[2] = localType.getName().name;
        arrayOfObject[3] = ((Type)localObject).getJDBCClassName();
        arrayOfObject[4] = ValuePool.getInt(2001);
        arrayOfObject[5] = null;
        arrayOfObject[6] = ValuePool.getInt(((Type)localObject).getJDBCTypeCode());
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  Table SYSTEM_VERSIONCOLUMNS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[13];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[13]);
      addColumn(localTable, "SCOPE", Type.SQL_INTEGER);
      addColumn(localTable, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "DATA_TYPE", Type.SQL_SMALLINT);
      addColumn(localTable, "TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "COLUMN_SIZE", Type.SQL_SMALLINT);
      addColumn(localTable, "BUFFER_LENGTH", Type.SQL_INTEGER);
      addColumn(localTable, "DECIMAL_DIGITS", Type.SQL_SMALLINT);
      addColumn(localTable, "PSEUDO_COLUMN", Type.SQL_SMALLINT);
      addColumn(localTable, "TABLE_CAT", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_SCHEM", SQL_IDENTIFIER);
      addColumn(localTable, "TABLE_NAME", SQL_IDENTIFIER);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[13].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, null, false);
      return localTable;
    }
    return localTable;
  }
  
  Table SYSTEM_USERS(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[12];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[12]);
      addColumn(localTable, "USER_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "ADMIN", Type.SQL_BOOLEAN);
      addColumn(localTable, "INITIAL_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "AUTHENTICATION", SQL_IDENTIFIER);
      addColumn(localTable, "PASSWORD_DIGEST", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[12].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, true);
      return localTable;
    }
    Object localObject = this.database.getUserManager().listVisibleUsers(paramSession);
    for (int i = 0; i < ((HsqlArrayList)localObject).size(); i++)
    {
      Object[] arrayOfObject = localTable.getEmptyRowData();
      User localUser = (User)((HsqlArrayList)localObject).get(i);
      HsqlNameManager.HsqlName localHsqlName = localUser.getInitialSchema();
      arrayOfObject[0] = localUser.getName().getNameString();
      arrayOfObject[1] = ValuePool.getBoolean(localUser.isAdmin());
      arrayOfObject[2] = (localHsqlName == null ? null : localHsqlName.name);
      arrayOfObject[3] = (localUser.isExternalOnly ? "EXTERNAL" : localUser.isLocalOnly ? "LOCAL" : "ANY");
      arrayOfObject[4] = localUser.getPasswordDigest();
      localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
    }
    return localTable;
  }
  
  final Table COLUMN_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[33];
    Object localObject1;
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[33]);
      addColumn(localTable1, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable1, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "IS_GRANTABLE", YES_OR_NO);
      localObject1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[33].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject1, new int[] { 2, 3, 4, 5, 6, 1, 0 }, false);
      return localTable1;
    }
    OrderedHashSet localOrderedHashSet1 = paramSession.getGrantee().getGranteeAndAllRolesWithPublic();
    Iterator localIterator = allTables();
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      String str2 = localTable2.getName().name;
      localObject1 = this.database.getCatalogName().name;
      String str1 = localTable2.getSchemaName().name;
      for (int i = 0; i < localOrderedHashSet1.size(); i++)
      {
        Grantee localGrantee = (Grantee)localOrderedHashSet1.get(i);
        Object localObject2 = localGrantee.getAllDirectPrivileges(localTable2);
        OrderedHashSet localOrderedHashSet2 = localGrantee.getAllGrantedPrivileges(localTable2);
        if (!localOrderedHashSet2.isEmpty())
        {
          localOrderedHashSet2.addAll((Collection)localObject2);
          localObject2 = localOrderedHashSet2;
        }
        for (int j = 0; j < ((OrderedHashSet)localObject2).size(); j++)
        {
          Right localRight1 = (Right)((OrderedHashSet)localObject2).get(j);
          Right localRight2 = localRight1.getGrantableRights();
          for (int k = 0; k < Right.privilegeTypes.length; k++)
          {
            OrderedHashSet localOrderedHashSet3 = localRight1.getColumnsForPrivilege(localTable2, Right.privilegeTypes[k]);
            OrderedHashSet localOrderedHashSet4 = localRight2.getColumnsForPrivilege(localTable2, Right.privilegeTypes[k]);
            for (int m = 0; m < localOrderedHashSet3.size(); m++)
            {
              HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet3.get(m);
              Object[] arrayOfObject = localTable1.getEmptyRowData();
              arrayOfObject[0] = localRight1.getGrantor().getName().name;
              arrayOfObject[1] = localRight1.getGrantee().getName().name;
              arrayOfObject[2] = localObject1;
              arrayOfObject[3] = str1;
              arrayOfObject[4] = str2;
              arrayOfObject[5] = localHsqlName.name;
              arrayOfObject[6] = Right.privilegeNames[k];
              arrayOfObject[7] = ((localRight1.getGrantee() == localTable2.getOwner()) || (localOrderedHashSet4.contains(localHsqlName)) ? "YES" : "NO");
              try
              {
                localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              }
              catch (HsqlException localHsqlException) {}
            }
          }
        }
      }
    }
    return localTable1;
  }
  
  final Table SEQUENCES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[69];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[69]);
      addColumn(localTable, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "MAXIMUM_VALUE", CHARACTER_DATA);
      addColumn(localTable, "MINIMUM_VALUE", CHARACTER_DATA);
      addColumn(localTable, "INCREMENT", CHARACTER_DATA);
      addColumn(localTable, "CYCLE_OPTION", YES_OR_NO);
      addColumn(localTable, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "START_WITH", CHARACTER_DATA);
      addColumn(localTable, "NEXT_VALUE", CHARACTER_DATA);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[69].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(7);
    while (localIterator.hasNext())
    {
      NumberSequence localNumberSequence = (NumberSequence)localIterator.next();
      if (paramSession.getGrantee().isAccessible(localNumberSequence))
      {
        Object[] arrayOfObject = localTable.getEmptyRowData();
        NumberType localNumberType = (NumberType)localNumberSequence.getDataType();
        int i = (localNumberType.typeCode == 2) || (localNumberType.typeCode == 3) ? 10 : 2;
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localNumberSequence.getSchemaName().name;
        arrayOfObject[2] = localNumberSequence.getName().name;
        arrayOfObject[3] = localNumberSequence.getDataType().getFullNameString();
        arrayOfObject[4] = ValuePool.getInt(localNumberType.getPrecision());
        arrayOfObject[5] = ValuePool.getInt(i);
        arrayOfObject[6] = ValuePool.INTEGER_0;
        arrayOfObject[7] = String.valueOf(localNumberSequence.getMaxValue());
        arrayOfObject[8] = String.valueOf(localNumberSequence.getMinValue());
        arrayOfObject[9] = String.valueOf(localNumberSequence.getIncrement());
        arrayOfObject[10] = (localNumberSequence.isCycle() ? "YES" : "NO");
        arrayOfObject[11] = arrayOfObject[3];
        arrayOfObject[12] = arrayOfObject[4];
        arrayOfObject[13] = arrayOfObject[13];
        arrayOfObject[14] = String.valueOf(localNumberSequence.getStartValue());
        arrayOfObject[15] = String.valueOf(localNumberSequence.peek());
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  final Table SYSTEM_SEQUENCES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[14];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[14]);
      addColumn(localTable, "SEQUENCE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable, "SEQUENCE_NAME", SQL_IDENTIFIER);
      addColumn(localTable, "DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_PRECISION_RADIX", CARDINAL_NUMBER);
      addColumn(localTable, "NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "MAXIMUM_VALUE", CHARACTER_DATA);
      addColumn(localTable, "MINIMUM_VALUE", CHARACTER_DATA);
      addColumn(localTable, "INCREMENT", CHARACTER_DATA);
      addColumn(localTable, "CYCLE_OPTION", YES_OR_NO);
      addColumn(localTable, "DECLARED_DATA_TYPE", CHARACTER_DATA);
      addColumn(localTable, "DECLARED_NUMERIC_PRECISION", CARDINAL_NUMBER);
      addColumn(localTable, "DECLARED_NUMERIC_SCALE", CARDINAL_NUMBER);
      addColumn(localTable, "START_WITH", CHARACTER_DATA);
      addColumn(localTable, "NEXT_VALUE", CHARACTER_DATA);
      HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[14].name, false, 20);
      localTable.createPrimaryKeyConstraint(localHsqlName, new int[] { 0, 1, 2 }, false);
      return localTable;
    }
    Iterator localIterator = this.database.schemaManager.databaseObjectIterator(7);
    while (localIterator.hasNext())
    {
      NumberSequence localNumberSequence = (NumberSequence)localIterator.next();
      if (paramSession.getGrantee().isAccessible(localNumberSequence))
      {
        Object[] arrayOfObject = localTable.getEmptyRowData();
        NumberType localNumberType = (NumberType)localNumberSequence.getDataType();
        int i = (localNumberType.typeCode == 2) || (localNumberType.typeCode == 3) ? 10 : 2;
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localNumberSequence.getSchemaName().name;
        arrayOfObject[2] = localNumberSequence.getName().name;
        arrayOfObject[3] = localNumberSequence.getDataType().getFullNameString();
        arrayOfObject[4] = ValuePool.getInt(localNumberType.getPrecision());
        arrayOfObject[5] = ValuePool.getInt(i);
        arrayOfObject[6] = ValuePool.INTEGER_0;
        arrayOfObject[7] = String.valueOf(localNumberSequence.getMaxValue());
        arrayOfObject[8] = String.valueOf(localNumberSequence.getMinValue());
        arrayOfObject[9] = String.valueOf(localNumberSequence.getIncrement());
        arrayOfObject[10] = (localNumberSequence.isCycle() ? "YES" : "NO");
        arrayOfObject[11] = arrayOfObject[3];
        arrayOfObject[12] = arrayOfObject[4];
        arrayOfObject[13] = arrayOfObject[13];
        arrayOfObject[14] = String.valueOf(localNumberSequence.getStartValue());
        arrayOfObject[15] = String.valueOf(localNumberSequence.peek());
        localTable.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable;
  }
  
  final Table TABLE_PRIVILEGES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[77];
    Object localObject1;
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[77]);
      addColumn(localTable1, "GRANTOR", SQL_IDENTIFIER);
      addColumn(localTable1, "GRANTEE", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "PRIVILEGE_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "IS_GRANTABLE", YES_OR_NO);
      addColumn(localTable1, "WITH_HIERARCHY", YES_OR_NO);
      localObject1 = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[69].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject1, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
      return localTable1;
    }
    OrderedHashSet localOrderedHashSet1 = paramSession.getGrantee().getGranteeAndAllRolesWithPublic();
    Iterator localIterator = allTables();
    while (localIterator.hasNext())
    {
      Table localTable2 = (Table)localIterator.next();
      String str2 = localTable2.getName().name;
      localObject1 = localTable2.getCatalogName().name;
      String str1 = localTable2.getSchemaName().name;
      for (int i = 0; i < localOrderedHashSet1.size(); i++)
      {
        Grantee localGrantee = (Grantee)localOrderedHashSet1.get(i);
        Object localObject2 = localGrantee.getAllDirectPrivileges(localTable2);
        OrderedHashSet localOrderedHashSet2 = localGrantee.getAllGrantedPrivileges(localTable2);
        if (!localOrderedHashSet2.isEmpty())
        {
          localOrderedHashSet2.addAll((Collection)localObject2);
          localObject2 = localOrderedHashSet2;
        }
        for (int j = 0; j < ((OrderedHashSet)localObject2).size(); j++)
        {
          Right localRight1 = (Right)((OrderedHashSet)localObject2).get(j);
          Right localRight2 = localRight1.getGrantableRights();
          for (int k = 0; k < Right.privilegeTypes.length; k++) {
            if (localRight1.canAccessFully(Right.privilegeTypes[k]))
            {
              String str3 = Right.privilegeNames[k];
              Object[] arrayOfObject = localTable1.getEmptyRowData();
              arrayOfObject[0] = localRight1.getGrantor().getName().name;
              arrayOfObject[1] = localRight1.getGrantee().getName().name;
              arrayOfObject[2] = localObject1;
              arrayOfObject[3] = str1;
              arrayOfObject[4] = str2;
              arrayOfObject[5] = str3;
              arrayOfObject[6] = ((localRight1.getGrantee() == localTable2.getOwner()) || (localRight2.canAccessFully(Right.privilegeTypes[k])) ? "YES" : "NO");
              arrayOfObject[7] = "NO";
              try
              {
                localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
              }
              catch (HsqlException localHsqlException) {}
            }
          }
        }
      }
    }
    return localTable1;
  }
  
  Table TABLES(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable1 = this.sysTables[78];
    if (localTable1 == null)
    {
      localTable1 = createBlankTable(sysTableHsqlNames[78]);
      addColumn(localTable1, "TABLE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "TABLE_TYPE", CHARACTER_DATA);
      addColumn(localTable1, "SELF_REFERENCING_COLUMN_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "REFERENCE_GENERATION", CHARACTER_DATA);
      addColumn(localTable1, "USER_DEFINED_TYPE_CATALOG", SQL_IDENTIFIER);
      addColumn(localTable1, "USER_DEFINED_TYPE_SCHEMA", SQL_IDENTIFIER);
      addColumn(localTable1, "USER_DEFINED_TYPE_NAME", SQL_IDENTIFIER);
      addColumn(localTable1, "IS_INSERTABLE_INTO", YES_OR_NO);
      addColumn(localTable1, "IS_TYPED", YES_OR_NO);
      addColumn(localTable1, "COMMIT_ACTION", CHARACTER_DATA);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[78].name, false, 20);
      localTable1.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0, 1, 2 }, false);
      return localTable1;
    }
    Object localObject = allTables();
    while (((Iterator)localObject).hasNext())
    {
      Table localTable2 = (Table)((Iterator)localObject).next();
      if (isAccessibleTable(paramSession, localTable2))
      {
        Object[] arrayOfObject = localTable1.getEmptyRowData();
        arrayOfObject[0] = this.database.getCatalogName().name;
        arrayOfObject[1] = localTable2.getSchemaName().name;
        arrayOfObject[2] = localTable2.getName().name;
        switch (localTable2.getTableType())
        {
        case 1: 
        case 8: 
          arrayOfObject[3] = "VIEW";
          arrayOfObject[9] = (localTable2.isInsertable() ? "YES" : "NO");
          break;
        case 3: 
        case 6: 
          arrayOfObject[3] = "GLOBAL TEMPORARY";
          arrayOfObject[9] = "YES";
          break;
        case 2: 
        case 4: 
        case 5: 
        case 7: 
        default: 
          arrayOfObject[3] = "BASE TABLE";
          arrayOfObject[9] = (localTable2.isInsertable() ? "YES" : "NO");
        }
        arrayOfObject[4] = null;
        arrayOfObject[5] = null;
        arrayOfObject[6] = null;
        arrayOfObject[7] = null;
        arrayOfObject[8] = null;
        arrayOfObject[10] = "NO";
        arrayOfObject[11] = (localTable2.isTemp() ? "DELETE" : localTable2.onCommitPreserve() ? "PRESERVE" : null);
        localTable1.insertSys(paramSession, paramPersistentStore, arrayOfObject);
      }
    }
    return localTable1;
  }
  
  final Table INFORMATION_SCHEMA_CATALOG_NAME(Session paramSession, PersistentStore paramPersistentStore)
  {
    Table localTable = this.sysTables[43];
    if (localTable == null)
    {
      localTable = createBlankTable(sysTableHsqlNames[43]);
      addColumn(localTable, "CATALOG_NAME", SQL_IDENTIFIER);
      localObject = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[43].name, false, 20);
      localTable.createPrimaryKeyConstraint((HsqlNameManager.HsqlName)localObject, new int[] { 0 }, true);
      return localTable;
    }
    Object localObject = localTable.getEmptyRowData();
    localObject[0] = this.database.getCatalogName().name;
    localTable.insertSys(paramSession, paramPersistentStore, (Object[])localObject);
    return localTable;
  }
  
  static
  {
    synchronized (DatabaseInformationMain.class)
    {
      nonCachedTablesSet = new HashSet();
      sysTableHsqlNames = new HsqlNameManager.HsqlName[sysTableNames.length];
      for (int i = 0; i < sysTableNames.length; i++)
      {
        sysTableHsqlNames[i] = HsqlNameManager.newInfoSchemaTableName(sysTableNames[i]);
        sysTableHsqlNames[i].schema = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
        sysTableSessionDependent[i] = true;
      }
      nonCachedTablesSet.add("SYSTEM_CACHEINFO");
      nonCachedTablesSet.add("SYSTEM_SESSIONINFO");
      nonCachedTablesSet.add("SYSTEM_SESSIONS");
      nonCachedTablesSet.add("SYSTEM_PROPERTIES");
      nonCachedTablesSet.add("SYSTEM_SEQUENCES");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.dbinfo.DatabaseInformationMain
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */