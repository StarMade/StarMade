package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.index.Index.IndexUse;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorDataChange;
import org.hsqldb.persist.CachedObject;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.PersistentStoreCollectionDatabase;
import org.hsqldb.persist.PersistentStoreCollectionSession;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;
import org.hsqldb.types.UserTypeModifier;

public class Table
  extends TableBase
  implements SchemaObject
{
  public static final Table[] emptyArray = new Table[0];
  protected HsqlNameManager.HsqlName tableName;
  protected long changeTimestamp;
  public HashMappedList columnList;
  int identityColumn;
  NumberSequence identitySequence;
  Constraint[] constraintList;
  Constraint[] fkConstraints;
  Constraint[] fkMainConstraints;
  Constraint[] checkConstraints;
  TriggerDef[] triggerList;
  TriggerDef[][] triggerLists;
  Expression[] colDefaults;
  private boolean hasDefaultValues;
  boolean[] colGenerated;
  private boolean hasGeneratedValues;
  boolean[] colRefFK;
  boolean[] colMainFK;
  boolean hasReferentialAction;
  boolean isDropped;
  private boolean hasDomainColumns;
  private boolean hasNotNullColumns;
  protected int[] defaultColumnMap;
  RangeVariable[] defaultRanges;
  
  public Table(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, int paramInt)
  {
    this.database = paramDatabase;
    this.tableName = paramHsqlName;
    this.persistenceId = paramDatabase.persistentStoreCollection.getNextId();
    switch (paramInt)
    {
    case 13: 
      this.persistenceScope = 21;
      this.isSessionBased = true;
      break;
    case 2: 
      this.persistenceScope = 21;
      this.isSessionBased = true;
      break;
    case 1: 
      this.isSessionBased = true;
    case 12: 
      this.persistenceScope = 24;
      this.isSchemaBased = true;
      break;
    case 5: 
      if (paramDatabase.logger.isFileDatabase())
      {
        this.persistenceScope = 24;
        this.isSchemaBased = true;
        this.isCached = true;
        this.isLogged = (!paramDatabase.isFilesReadOnly());
      }
      else
      {
        paramInt = 4;
      }
      break;
    case 4: 
      this.persistenceScope = 24;
      this.isSchemaBased = true;
      this.isLogged = (!paramDatabase.isFilesReadOnly());
      break;
    case 3: 
      this.persistenceScope = 22;
      this.isTemp = true;
      this.isSchemaBased = true;
      this.isSessionBased = true;
      break;
    case 6: 
      this.persistenceScope = 23;
      if (!paramDatabase.logger.isFileDatabase()) {
        throw Error.error(459);
      }
      this.isSchemaBased = true;
      this.isSessionBased = true;
      this.isTemp = true;
      this.isText = true;
      this.isReadOnly = true;
      break;
    case 7: 
      this.persistenceScope = 24;
      if (!paramDatabase.logger.isFileDatabase())
      {
        if (!paramDatabase.logger.isAllowedFullPath()) {
          throw Error.error(459);
        }
        this.isReadOnly = true;
      }
      this.isSchemaBased = true;
      this.isText = true;
      break;
    case 8: 
      this.persistenceScope = 21;
      this.isSchemaBased = true;
      this.isSessionBased = true;
      this.isView = true;
      break;
    case 9: 
      this.persistenceScope = 23;
      this.isSessionBased = true;
      break;
    case 11: 
      this.persistenceScope = 21;
      this.isSessionBased = true;
      break;
    case 10: 
    default: 
      throw Error.runtimeError(201, "Table");
    }
    this.tableType = paramInt;
    this.primaryKeyCols = null;
    this.primaryKeyTypes = null;
    this.identityColumn = -1;
    this.columnList = new HashMappedList();
    this.indexList = Index.emptyArray;
    this.constraintList = Constraint.emptyArray;
    this.fkConstraints = Constraint.emptyArray;
    this.fkMainConstraints = Constraint.emptyArray;
    this.checkConstraints = Constraint.emptyArray;
    this.triggerList = TriggerDef.emptyArray;
    this.triggerLists = new TriggerDef[9][];
    for (int i = 0; i < 9; i++) {
      this.triggerLists[i] = TriggerDef.emptyArray;
    }
    if ((paramDatabase.isFilesReadOnly()) && (isFileBased())) {
      this.isReadOnly = true;
    }
    if (!this.isSessionBased) {
      createDefaultStore();
    }
  }
  
  public Table(Table paramTable, HsqlNameManager.HsqlName paramHsqlName)
  {
    this.persistenceScope = 21;
    paramHsqlName.schema = SqlInvariants.SYSTEM_SCHEMA_HSQLNAME;
    this.tableName = paramHsqlName;
    this.database = paramTable.database;
    this.tableType = 9;
    this.columnList = paramTable.columnList;
    this.columnCount = paramTable.columnCount;
    this.indexList = Index.emptyArray;
    this.constraintList = Constraint.emptyArray;
    createPrimaryKey();
  }
  
  public void createDefaultStore()
  {
    this.store = this.database.logger.newStore(null, this.database.persistentStoreCollection, this);
  }
  
  public int getType()
  {
    return 3;
  }
  
  public final HsqlNameManager.HsqlName getName()
  {
    return this.tableName;
  }
  
  public HsqlNameManager.HsqlName getCatalogName()
  {
    return this.database.getCatalogName();
  }
  
  public HsqlNameManager.HsqlName getSchemaName()
  {
    return this.tableName.schema;
  }
  
  public Grantee getOwner()
  {
    return this.tableName.schema.owner;
  }
  
  public OrderedHashSet getReferences()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    if ((this.identitySequence != null) && (this.identitySequence.getName() != null)) {
      localOrderedHashSet.add(this.identitySequence.getName());
    }
    return localOrderedHashSet;
  }
  
  public RangeVariable[] getDefaultRanges()
  {
    if (this.defaultRanges == null) {
      this.defaultRanges = new RangeVariable[] { new RangeVariable(this, 0) };
    }
    return this.defaultRanges;
  }
  
  public OrderedHashSet getReferencesForDependents()
  {
    OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
    for (int i = 0; i < this.colTypes.length; i++)
    {
      ColumnSchema localColumnSchema = getColumn(i);
      OrderedHashSet localOrderedHashSet2 = localColumnSchema.getReferences();
      if ((localOrderedHashSet2 != null) && (!localOrderedHashSet2.isEmpty())) {
        localOrderedHashSet1.add(localColumnSchema.getName());
      }
    }
    for (i = 0; i < this.fkConstraints.length; i++) {
      if (this.fkConstraints[i].getMainTableName() != getName()) {
        localOrderedHashSet1.add(this.fkConstraints[i].getName());
      }
    }
    for (i = 0; i < this.triggerList.length; i++) {
      localOrderedHashSet1.add(this.triggerList[i].getName());
    }
    return localOrderedHashSet1;
  }
  
  public OrderedHashSet getComponents()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    localOrderedHashSet.addAll(this.constraintList);
    localOrderedHashSet.addAll(this.triggerList);
    for (int i = 0; i < this.indexList.length; i++) {
      if (!this.indexList[i].isConstraint()) {
        localOrderedHashSet.add(this.indexList[i]);
      }
    }
    return localOrderedHashSet;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject)
  {
    for (int i = 0; i < this.columnCount; i++)
    {
      ColumnSchema localColumnSchema = getColumn(i);
      localColumnSchema.compile(paramSession, this);
    }
  }
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CREATE").append(' ');
    if (isTemp())
    {
      localStringBuffer.append("GLOBAL").append(' ');
      localStringBuffer.append("TEMPORARY").append(' ');
    }
    else if (isText())
    {
      localStringBuffer.append("TEXT").append(' ');
    }
    else if (isCached())
    {
      localStringBuffer.append("CACHED").append(' ');
    }
    else
    {
      localStringBuffer.append("MEMORY").append(' ');
    }
    localStringBuffer.append("TABLE").append(' ');
    localStringBuffer.append(getName().getSchemaQualifiedStatementName());
    localStringBuffer.append('(');
    int[] arrayOfInt = getPrimaryKey();
    Constraint localConstraint1 = getPrimaryConstraint();
    Object localObject1;
    Object localObject2;
    for (int i = 0; i < this.columnCount; i++)
    {
      ColumnSchema localColumnSchema = getColumn(i);
      String str = localColumnSchema.getName().statementName;
      localObject1 = localColumnSchema.getDataType();
      if (i > 0) {
        localStringBuffer.append(',');
      }
      localStringBuffer.append(str);
      localStringBuffer.append(' ');
      localStringBuffer.append(((Type)localObject1).getTypeDefinition());
      if (((Type)localObject1).isCharacterType())
      {
        localObject2 = ((CharacterType)localObject1).getCollation();
        if (((Collation)localObject2).isObjectCollation())
        {
          localStringBuffer.append(' ').append("COLLATE").append(' ');
          localStringBuffer.append(((Collation)localObject2).getName().statementName);
        }
      }
      localObject2 = localColumnSchema.getDefaultSQL();
      if (localObject2 != null)
      {
        localStringBuffer.append(' ').append("DEFAULT").append(' ');
        localStringBuffer.append((String)localObject2);
      }
      if (localColumnSchema.isIdentity()) {
        localStringBuffer.append(' ').append(localColumnSchema.getIdentitySequence().getSQLColumnDefinition());
      }
      if (localColumnSchema.isGenerated())
      {
        localStringBuffer.append(' ').append("GENERATED").append(' ');
        localStringBuffer.append("ALWAYS").append(' ').append("AS").append("(");
        localStringBuffer.append(localColumnSchema.getGeneratingExpression().getSQL());
        localStringBuffer.append(")");
      }
      if (!localColumnSchema.isNullable())
      {
        Constraint localConstraint2 = getNotNullConstraintForColumn(i);
        if ((localConstraint2 != null) && (!localConstraint2.getName().isReservedName())) {
          localStringBuffer.append(' ').append("CONSTRAINT").append(' ').append(localConstraint2.getName().statementName);
        }
        localStringBuffer.append(' ').append("NOT").append(' ').append("NULL");
      }
      if ((arrayOfInt.length == 1) && (i == arrayOfInt[0]) && (localConstraint1.getName().isReservedName())) {
        localStringBuffer.append(' ').append("PRIMARY").append(' ').append("KEY");
      }
    }
    Constraint[] arrayOfConstraint = getConstraints();
    int j = 0;
    int k = arrayOfConstraint.length;
    while (j < k)
    {
      localObject1 = arrayOfConstraint[j];
      if (!((Constraint)localObject1).isForward)
      {
        localObject2 = ((Constraint)localObject1).getSQL();
        if (((String)localObject2).length() > 0)
        {
          localStringBuffer.append(',');
          localStringBuffer.append((String)localObject2);
        }
      }
      j++;
    }
    localStringBuffer.append(')');
    if (onCommitPreserve())
    {
      localStringBuffer.append(' ').append("ON").append(' ');
      localStringBuffer.append("COMMIT").append(' ').append("PRESERVE");
      localStringBuffer.append(' ').append("ROWS");
    }
    return localStringBuffer.toString();
  }
  
  public long getChangeTimestamp()
  {
    return this.changeTimestamp;
  }
  
  public final void setName(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.tableName = paramHsqlName;
  }
  
  String[] getSQL(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2)
  {
    for (int i = 0; i < this.constraintList.length; i++)
    {
      Constraint localConstraint = this.constraintList[i];
      if (localConstraint.isForward) {
        paramOrderedHashSet2.add(localConstraint);
      } else if ((localConstraint.getConstraintType() == 2) || (localConstraint.getConstraintType() == 4)) {
        paramOrderedHashSet1.add(localConstraint.getName());
      }
    }
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    localHsqlArrayList.add(getSQL());
    if ((!this.isTemp) && (!this.isText) && (this.identitySequence != null) && (this.identitySequence.getName() == null)) {
      localHsqlArrayList.add(NumberSequence.getRestartSQL(this));
    }
    for (int j = 0; j < this.indexList.length; j++) {
      if ((!this.indexList[j].isConstraint()) && (this.indexList[j].getColumnCount() > 0)) {
        localHsqlArrayList.add(this.indexList[j].getSQL());
      }
    }
    String[] arrayOfString = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public String getSQLForReadOnly()
  {
    if (this.isReadOnly)
    {
      StringBuffer localStringBuffer = new StringBuffer(64);
      localStringBuffer.append("SET").append(' ').append("TABLE").append(' ');
      localStringBuffer.append(getName().getSchemaQualifiedStatementName());
      localStringBuffer.append(' ').append("READ").append(' ');
      localStringBuffer.append("ONLY");
      return localStringBuffer.toString();
    }
    return null;
  }
  
  public String[] getSQLForTextSource(boolean paramBoolean)
  {
    if (isText())
    {
      HsqlArrayList localHsqlArrayList = new HsqlArrayList();
      if (this.isReadOnly) {
        localHsqlArrayList.add(getSQLForReadOnly());
      }
      String str1 = ((TextTable)this).getDataSourceDDL();
      if (str1 != null) {
        localHsqlArrayList.add(str1);
      }
      String str2 = ((TextTable)this).getDataSourceHeader();
      if ((paramBoolean) && (str2 != null) && (!this.isReadOnly)) {
        localHsqlArrayList.add(str2);
      }
      String[] arrayOfString = new String[localHsqlArrayList.size()];
      localHsqlArrayList.toArray(arrayOfString);
      return arrayOfString;
    }
    return null;
  }
  
  public String getSQLForClustered()
  {
    if ((!isCached()) && (!isText())) {
      return null;
    }
    Index localIndex = getClusteredIndex();
    if (localIndex == null) {
      return null;
    }
    String str = getColumnListSQL(localIndex.getColumns(), localIndex.getColumnCount());
    StringBuffer localStringBuffer = new StringBuffer(64);
    localStringBuffer.append("SET").append(' ').append("TABLE").append(' ');
    localStringBuffer.append(getName().getSchemaQualifiedStatementName());
    localStringBuffer.append(' ').append("CLUSTERED").append(' ');
    localStringBuffer.append("ON").append(' ').append(str);
    return localStringBuffer.toString();
  }
  
  public String[] getTriggerSQL()
  {
    String[] arrayOfString = new String[this.triggerList.length];
    for (int i = 0; i < this.triggerList.length; i++) {
      if (!this.triggerList[i].isSystem()) {
        arrayOfString[i] = this.triggerList[i].getSQL();
      }
    }
    return arrayOfString;
  }
  
  public String getIndexRootsSQL(long[] paramArrayOfLong)
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append("SET").append(' ').append("TABLE").append(' ');
    localStringBuffer.append(getName().getSchemaQualifiedStatementName());
    localStringBuffer.append(' ').append("INDEX").append(' ').append('\'');
    localStringBuffer.append(StringUtil.getList(paramArrayOfLong, " ", ""));
    localStringBuffer.append('\'');
    return localStringBuffer.toString();
  }
  
  public String getColumnListSQL(int[] paramArrayOfInt, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append('(');
    for (int i = 0; i < paramInt; i++)
    {
      localStringBuffer.append(getColumn(paramArrayOfInt[i]).getName().statementName);
      if (i < paramInt - 1) {
        localStringBuffer.append(',');
      }
    }
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }
  
  public String getColumnListWithTypeSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append('(');
    for (int i = 0; i < this.columnCount; i++)
    {
      ColumnSchema localColumnSchema = getColumn(i);
      String str = localColumnSchema.getName().statementName;
      Type localType = localColumnSchema.getDataType();
      if (i > 0) {
        localStringBuffer.append(',');
      }
      localStringBuffer.append(str);
      localStringBuffer.append(' ');
      localStringBuffer.append(localType.getTypeDefinition());
    }
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }
  
  public boolean isConnected()
  {
    return true;
  }
  
  public static int compareRows(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfInt, Type[] paramArrayOfType)
  {
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfType[paramArrayOfInt[j]].compare(paramSession, paramArrayOfObject1[paramArrayOfInt[j]], paramArrayOfObject2[paramArrayOfInt[j]]);
      if (k != 0) {
        return k;
      }
    }
    return 0;
  }
  
  public int getId()
  {
    return this.tableName.hashCode();
  }
  
  public final boolean isSchemaBaseTable()
  {
    switch (this.tableType)
    {
    case 4: 
    case 5: 
    case 7: 
      return true;
    }
    return false;
  }
  
  public final boolean isWithDataSource()
  {
    return this.isWithDataSource;
  }
  
  public final boolean isText()
  {
    return this.isText;
  }
  
  public final boolean isTemp()
  {
    return this.isTemp;
  }
  
  public final boolean isReadOnly()
  {
    return this.isReadOnly;
  }
  
  public final boolean isView()
  {
    return this.isView;
  }
  
  public boolean isQueryBased()
  {
    return false;
  }
  
  public boolean isCached()
  {
    return this.isCached;
  }
  
  public boolean isDataReadOnly()
  {
    return this.isReadOnly;
  }
  
  public boolean isDropped()
  {
    return this.isDropped;
  }
  
  final boolean isIndexingMutable()
  {
    return !isIndexCached();
  }
  
  boolean isIndexCached()
  {
    return this.isCached;
  }
  
  void checkDataReadOnly()
  {
    if (this.isReadOnly) {
      throw Error.error(456);
    }
  }
  
  public void setDataReadOnly(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      if ((this.database.isFilesReadOnly()) && (isFileBased())) {
        throw Error.error(456);
      }
      if ((this.database.getType() == "mem:") && (this.isText)) {
        throw Error.error(456);
      }
    }
    this.isReadOnly = paramBoolean;
  }
  
  public boolean isFileBased()
  {
    return (this.isCached) || (this.isText);
  }
  
  public void addConstraint(Constraint paramConstraint)
  {
    int i = paramConstraint.getConstraintType() == 4 ? 0 : this.constraintList.length;
    this.constraintList = ((Constraint[])ArrayUtil.toAdjustedArray(this.constraintList, paramConstraint, i, 1));
    updateConstraintLists();
  }
  
  void updateConstraintLists()
  {
    int i = 0;
    int j = 0;
    int k = 0;
    this.hasReferentialAction = false;
    for (int m = 0; m < this.constraintList.length; m++) {
      switch (this.constraintList[m].getConstraintType())
      {
      case 0: 
        i++;
        break;
      case 1: 
        j++;
        break;
      case 3: 
        if (!this.constraintList[m].isNotNull()) {
          k++;
        }
        break;
      }
    }
    this.fkConstraints = (i == 0 ? Constraint.emptyArray : new Constraint[i]);
    i = 0;
    this.fkMainConstraints = (j == 0 ? Constraint.emptyArray : new Constraint[j]);
    j = 0;
    this.checkConstraints = (k == 0 ? Constraint.emptyArray : new Constraint[k]);
    k = 0;
    this.colRefFK = new boolean[this.columnCount];
    this.colMainFK = new boolean[this.columnCount];
    for (m = 0; m < this.constraintList.length; m++) {
      switch (this.constraintList[m].getConstraintType())
      {
      case 0: 
        this.fkConstraints[i] = this.constraintList[m];
        ArrayUtil.intIndexesToBooleanArray(this.constraintList[m].getRefColumns(), this.colRefFK);
        i++;
        break;
      case 1: 
        this.fkMainConstraints[j] = this.constraintList[m];
        ArrayUtil.intIndexesToBooleanArray(this.constraintList[m].getMainColumns(), this.colMainFK);
        if (this.constraintList[m].hasTriggeredAction()) {
          this.hasReferentialAction = true;
        }
        j++;
        break;
      case 3: 
        if (!this.constraintList[m].isNotNull())
        {
          this.checkConstraints[k] = this.constraintList[m];
          k++;
        }
        break;
      }
    }
  }
  
  void verifyConstraintsIntegrity()
  {
    for (int i = 0; i < this.constraintList.length; i++)
    {
      Constraint localConstraint = this.constraintList[i];
      if ((localConstraint.getConstraintType() == 0) || (localConstraint.getConstraintType() == 1))
      {
        if (localConstraint.getMain() != this.database.schemaManager.findUserTable(null, localConstraint.getMain().getName().name, localConstraint.getMain().getName().schema.name)) {
          throw Error.runtimeError(201, "FK mismatch : " + localConstraint.getName().name);
        }
        if (localConstraint.getRef() != this.database.schemaManager.findUserTable(null, localConstraint.getRef().getName().name, localConstraint.getRef().getName().schema.name)) {
          throw Error.runtimeError(201, "FK mismatch : " + localConstraint.getName().name);
        }
      }
    }
  }
  
  public Constraint[] getConstraints()
  {
    return this.constraintList;
  }
  
  public Constraint[] getFKConstraints()
  {
    return this.fkConstraints;
  }
  
  public Constraint getPrimaryConstraint()
  {
    return this.primaryKeyCols.length == 0 ? null : this.constraintList[0];
  }
  
  void collectFKReadLocks(int[] paramArrayOfInt, OrderedHashSet paramOrderedHashSet)
  {
    for (int i = 0; i < this.fkMainConstraints.length; i++)
    {
      Constraint localConstraint = this.fkMainConstraints[i];
      Table localTable = localConstraint.getRef();
      int[] arrayOfInt1 = localConstraint.getMainColumns();
      if (localTable != this) {
        if (paramArrayOfInt == null)
        {
          if (localConstraint.core.hasDeleteAction)
          {
            int[] arrayOfInt2 = localConstraint.core.deleteAction == 0 ? null : localConstraint.getRefColumns();
            if (paramOrderedHashSet.add(localTable.getName())) {
              localTable.collectFKReadLocks(arrayOfInt2, paramOrderedHashSet);
            }
          }
        }
        else if ((ArrayUtil.haveCommonElement(paramArrayOfInt, arrayOfInt1)) && (paramOrderedHashSet.add(localTable.getName()))) {
          localTable.collectFKReadLocks(localConstraint.getRefColumns(), paramOrderedHashSet);
        }
      }
    }
  }
  
  void collectFKWriteLocks(int[] paramArrayOfInt, OrderedHashSet paramOrderedHashSet)
  {
    for (int i = 0; i < this.fkMainConstraints.length; i++)
    {
      Constraint localConstraint = this.fkMainConstraints[i];
      Table localTable = localConstraint.getRef();
      int[] arrayOfInt1 = localConstraint.getMainColumns();
      if (localTable != this) {
        if (paramArrayOfInt == null)
        {
          if (localConstraint.core.hasDeleteAction)
          {
            int[] arrayOfInt2 = localConstraint.core.deleteAction == 0 ? null : localConstraint.getRefColumns();
            if (paramOrderedHashSet.add(localTable.getName())) {
              localTable.collectFKWriteLocks(arrayOfInt2, paramOrderedHashSet);
            }
          }
        }
        else if ((ArrayUtil.haveCommonElement(paramArrayOfInt, arrayOfInt1)) && (localConstraint.core.hasUpdateAction) && (paramOrderedHashSet.add(localTable.getName()))) {
          localTable.collectFKWriteLocks(localConstraint.getRefColumns(), paramOrderedHashSet);
        }
      }
    }
  }
  
  Constraint getNotNullConstraintForColumn(int paramInt)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if ((localConstraint.isNotNull()) && (localConstraint.notNullColumnIndex == paramInt)) {
        return localConstraint;
      }
      i++;
    }
    return null;
  }
  
  Constraint getUniqueConstraintForColumns(int[] paramArrayOfInt)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if (localConstraint.isUniqueWithColumns(paramArrayOfInt)) {
        return localConstraint;
      }
      i++;
    }
    return null;
  }
  
  Constraint getFKConstraintForColumns(Table paramTable, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if (localConstraint.isEquivalent(paramTable, paramArrayOfInt1, this, paramArrayOfInt2)) {
        return localConstraint;
      }
      i++;
    }
    return null;
  }
  
  public Constraint getUniqueOrPKConstraintForIndex(Index paramIndex)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if ((localConstraint.getMainIndex() == paramIndex) && ((localConstraint.getConstraintType() == 2) || (localConstraint.getConstraintType() == 4))) {
        return localConstraint;
      }
      i++;
    }
    return null;
  }
  
  int getNextConstraintIndex(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if (localConstraint.getConstraintType() == paramInt2) {
        return i;
      }
      i++;
    }
    return -1;
  }
  
  public void addColumn(ColumnSchema paramColumnSchema)
  {
    String str = paramColumnSchema.getName().name;
    if (findColumn(str) >= 0) {
      throw Error.error(5504, str);
    }
    if (paramColumnSchema.isIdentity())
    {
      if (this.identityColumn != -1) {
        throw Error.error(5525, str);
      }
      this.identityColumn = this.columnCount;
      this.identitySequence = paramColumnSchema.getIdentitySequence();
    }
    addColumnNoCheck(paramColumnSchema);
  }
  
  public void addColumnNoCheck(ColumnSchema paramColumnSchema)
  {
    if (this.primaryKeyCols != null) {
      throw Error.runtimeError(201, "Table");
    }
    this.columnList.add(paramColumnSchema.getName().name, paramColumnSchema);
    this.columnCount += 1;
  }
  
  public boolean hasGeneratedColumn()
  {
    return this.hasGeneratedValues;
  }
  
  public boolean hasLobColumn()
  {
    return this.hasLobColumn;
  }
  
  public boolean hasIdentityColumn()
  {
    return this.identityColumn != -1;
  }
  
  public long getNextIdentity()
  {
    return this.identitySequence.peek();
  }
  
  void checkColumnsMatch(int[] paramArrayOfInt1, Table paramTable, int[] paramArrayOfInt2)
  {
    for (int i = 0; i < paramArrayOfInt1.length; i++)
    {
      Type localType1 = this.colTypes[paramArrayOfInt1[i]];
      Type localType2 = paramTable.colTypes[paramArrayOfInt2[i]];
      if (localType1.typeComparisonGroup != localType2.typeComparisonGroup) {
        throw Error.error(5562);
      }
    }
  }
  
  void checkColumnsMatch(ColumnSchema paramColumnSchema, int paramInt)
  {
    Type localType1 = this.colTypes[paramInt];
    Type localType2 = paramColumnSchema.getDataType();
    if (localType1.typeComparisonGroup != localType2.typeComparisonGroup) {
      throw Error.error(5562);
    }
  }
  
  Table moveDefinition(Session paramSession, int paramInt1, ColumnSchema paramColumnSchema, Constraint paramConstraint, Index paramIndex, int paramInt2, int paramInt3, OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2)
  {
    int i = 0;
    if ((paramConstraint != null) && (paramConstraint.constType == 4)) {
      i = 1;
    }
    Object localObject1;
    if (this.isText)
    {
      localObject1 = new TextTable(this.database, this.tableName, paramInt1);
      ((TextTable)localObject1).dataSource = ((TextTable)this).dataSource;
      ((TextTable)localObject1).isReversed = ((TextTable)this).isReversed;
      ((TextTable)localObject1).isConnected = ((TextTable)this).isConnected;
    }
    else
    {
      localObject1 = new Table(this.database, this.tableName, paramInt1);
    }
    if (this.tableType == 3) {
      ((Table)localObject1).persistenceScope = this.persistenceScope;
    }
    for (int j = 0; j < this.columnCount; j++)
    {
      ColumnSchema localColumnSchema = (ColumnSchema)this.columnList.get(j);
      if (j == paramInt2)
      {
        if (paramColumnSchema != null) {
          ((Table)localObject1).addColumn(paramColumnSchema);
        }
        if (paramInt3 <= 0) {}
      }
      else
      {
        ((Table)localObject1).addColumn(localColumnSchema);
      }
    }
    if (this.columnCount == paramInt2) {
      ((Table)localObject1).addColumn(paramColumnSchema);
    }
    int[] arrayOfInt = null;
    if ((hasPrimaryKey()) && (!paramOrderedHashSet1.contains(getPrimaryConstraint().getName())))
    {
      arrayOfInt = this.primaryKeyCols;
      arrayOfInt = ArrayUtil.toAdjustedColumnArray(arrayOfInt, paramInt2, paramInt3);
    }
    else if (i != 0)
    {
      arrayOfInt = paramConstraint.getMainColumns();
    }
    ((Table)localObject1).createPrimaryKey(getIndex(0).getName(), arrayOfInt, false);
    Object localObject2;
    for (int k = 1; k < this.indexList.length; k++)
    {
      Index localIndex1 = this.indexList[k];
      if (!paramOrderedHashSet2.contains(localIndex1.getName()))
      {
        localObject2 = ArrayUtil.toAdjustedColumnArray(localIndex1.getColumns(), paramInt2, paramInt3);
        Index localIndex2 = ((Table)localObject1).createIndexStructure(localIndex1.getName(), (int[])localObject2, localIndex1.getColumnDesc(), null, localIndex1.isUnique(), localIndex1.isConstraint(), localIndex1.isForward());
        localIndex2.setClustered(localIndex1.isClustered());
        ((Table)localObject1).addIndex(localIndex2);
      }
    }
    if (paramIndex != null) {
      ((Table)localObject1).addIndex(paramIndex);
    }
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    if (i != 0)
    {
      paramConstraint.core.mainIndex = localObject1.indexList[0];
      paramConstraint.core.mainTable = ((Table)localObject1);
      paramConstraint.core.mainTableName = ((Table)localObject1).tableName;
      localHsqlArrayList.add(paramConstraint);
    }
    for (int m = 0; m < this.constraintList.length; m++)
    {
      localObject2 = this.constraintList[m];
      if (!paramOrderedHashSet1.contains(((Constraint)localObject2).getName()))
      {
        localObject2 = ((Constraint)localObject2).duplicate();
        ((Constraint)localObject2).updateTable(paramSession, this, (Table)localObject1, paramInt2, paramInt3);
        localHsqlArrayList.add(localObject2);
      }
    }
    if ((i == 0) && (paramConstraint != null))
    {
      paramConstraint.updateTable(paramSession, this, (Table)localObject1, -1, 0);
      localHsqlArrayList.add(paramConstraint);
    }
    ((Table)localObject1).constraintList = new Constraint[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(((Table)localObject1).constraintList);
    ((Table)localObject1).updateConstraintLists();
    ((Table)localObject1).setBestRowIdentifiers();
    ((Table)localObject1).triggerList = this.triggerList;
    ((Table)localObject1).triggerLists = this.triggerLists;
    for (m = 0; m < ((Table)localObject1).constraintList.length; m++) {
      localObject1.constraintList[m].compile(paramSession, (SchemaObject)localObject1);
    }
    for (m = 0; m < ((Table)localObject1).columnCount; m++) {
      ((Table)localObject1).getColumn(m).compile(paramSession, (SchemaObject)localObject1);
    }
    return localObject1;
  }
  
  void checkColumnInCheckConstraint(int paramInt)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if ((localConstraint.constType == 3) && (!localConstraint.isNotNull()) && (localConstraint.hasColumn(paramInt)))
      {
        HsqlNameManager.HsqlName localHsqlName = localConstraint.getName();
        throw Error.error(5502, localHsqlName.getSchemaQualifiedStatementName());
      }
      i++;
    }
  }
  
  void checkColumnInFKConstraint(int paramInt)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if ((localConstraint.hasColumn(paramInt)) && ((localConstraint.getConstraintType() == 1) || (localConstraint.getConstraintType() == 0)))
      {
        HsqlNameManager.HsqlName localHsqlName = localConstraint.getName();
        throw Error.error(5533, localHsqlName.getSchemaQualifiedStatementName());
      }
      i++;
    }
  }
  
  OrderedHashSet getDependentConstraints(int paramInt)
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if (localConstraint.hasColumnOnly(paramInt)) {
        localOrderedHashSet.add(localConstraint);
      }
      i++;
    }
    return localOrderedHashSet;
  }
  
  OrderedHashSet getContainingConstraints(int paramInt)
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if (localConstraint.hasColumnPlus(paramInt)) {
        localOrderedHashSet.add(localConstraint);
      }
      i++;
    }
    return localOrderedHashSet;
  }
  
  OrderedHashSet getContainingIndexNames(int paramInt)
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    int i = 0;
    int j = this.indexList.length;
    while (i < j)
    {
      Index localIndex = this.indexList[i];
      if (ArrayUtil.find(localIndex.getColumns(), paramInt) != -1) {
        localOrderedHashSet.add(localIndex.getName());
      }
      i++;
    }
    return localOrderedHashSet;
  }
  
  OrderedHashSet getDependentConstraints(Constraint paramConstraint)
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    int i = 0;
    int j = this.fkMainConstraints.length;
    while (i < j)
    {
      Constraint localConstraint = this.fkMainConstraints[i];
      if (localConstraint.core.uniqueName == paramConstraint.getName()) {
        localOrderedHashSet.add(localConstraint);
      }
      i++;
    }
    return localOrderedHashSet;
  }
  
  public OrderedHashSet getDependentExternalConstraints()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if (((localConstraint.getConstraintType() == 1) || (localConstraint.getConstraintType() == 0)) && (localConstraint.core.mainTable != localConstraint.core.refTable)) {
        localOrderedHashSet.add(localConstraint);
      }
      i++;
    }
    return localOrderedHashSet;
  }
  
  public OrderedHashSet getUniquePKConstraintNames()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if ((localConstraint.constType == 2) || (localConstraint.constType == 4)) {
        localOrderedHashSet.add(localConstraint.getName());
      }
      i++;
    }
    return localOrderedHashSet;
  }
  
  void checkColumnInFKConstraint(int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if ((localConstraint.getConstraintType() == 0) && (localConstraint.hasColumn(paramInt1)) && ((paramInt2 == localConstraint.getUpdateAction()) || (paramInt2 == localConstraint.getDeleteAction())))
      {
        HsqlNameManager.HsqlName localHsqlName = localConstraint.getName();
        throw Error.error(5533, localHsqlName.getSchemaQualifiedStatementName());
      }
      i++;
    }
  }
  
  int getIdentityColumnIndex()
  {
    return this.identityColumn;
  }
  
  public int getColumnIndex(String paramString)
  {
    int i = findColumn(paramString);
    if (i == -1) {
      throw Error.error(5501, paramString);
    }
    return i;
  }
  
  public int findColumn(String paramString)
  {
    int i = this.columnList.getIndex(paramString);
    return i;
  }
  
  void resetDefaultsFlag()
  {
    this.hasDefaultValues = false;
    for (int i = 0; i < this.colDefaults.length; i++) {
      this.hasDefaultValues |= this.colDefaults[i] != null;
    }
    this.hasGeneratedValues = false;
    for (i = 0; i < this.colGenerated.length; i++) {
      this.hasGeneratedValues |= this.colGenerated[i];
    }
    this.hasNotNullColumns = false;
    for (i = 0; i < this.colNotNull.length; i++) {
      this.hasNotNullColumns |= this.colNotNull[i];
    }
  }
  
  public int[] getBestRowIdentifiers()
  {
    return this.bestRowIdentifierCols;
  }
  
  public boolean isBestRowIdentifiersStrict()
  {
    return this.bestRowIdentifierStrict;
  }
  
  public Index getClusteredIndex()
  {
    for (int i = 0; i < this.indexList.length; i++) {
      if (this.indexList[i].isClustered()) {
        return this.indexList[i];
      }
    }
    return null;
  }
  
  synchronized Index getIndexForColumn(Session paramSession, int paramInt)
  {
    int i = this.bestIndexForColumn[paramInt];
    if (i > -1) {
      return this.indexList[i];
    }
    switch (this.tableType)
    {
    case 1: 
    case 2: 
    case 3: 
    case 8: 
    case 11: 
      Index localIndex = createIndexForColumns(paramSession, new int[] { paramInt });
      return localIndex;
    }
    return null;
  }
  
  boolean isIndexed(int paramInt)
  {
    return this.bestIndexForColumn[paramInt] != -1;
  }
  
  int[] getUniqueNotNullColumnGroup(boolean[] paramArrayOfBoolean)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      int[] arrayOfInt;
      if (localConstraint.constType == 2)
      {
        arrayOfInt = localConstraint.getMainColumns();
        if ((ArrayUtil.areAllIntIndexesInBooleanArray(arrayOfInt, this.colNotNull)) && (ArrayUtil.areAllIntIndexesInBooleanArray(arrayOfInt, paramArrayOfBoolean))) {
          return arrayOfInt;
        }
      }
      else if (localConstraint.constType == 4)
      {
        arrayOfInt = localConstraint.getMainColumns();
        if (ArrayUtil.areAllIntIndexesInBooleanArray(arrayOfInt, paramArrayOfBoolean)) {
          return arrayOfInt;
        }
      }
      i++;
    }
    return null;
  }
  
  boolean areColumnsNotNull(int[] paramArrayOfInt)
  {
    return ArrayUtil.areAllIntIndexesInBooleanArray(paramArrayOfInt, this.colNotNull);
  }
  
  public void createPrimaryKey()
  {
    createPrimaryKey(null, ValuePool.emptyIntArray, false);
  }
  
  public void createPrimaryKey(HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfInt, boolean paramBoolean)
  {
    if (this.primaryKeyCols != null) {
      throw Error.runtimeError(201, "Table");
    }
    if (paramArrayOfInt == null) {
      paramArrayOfInt = ValuePool.emptyIntArray;
    }
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      getColumn(paramArrayOfInt[i]).setPrimaryKey(true);
    }
    this.primaryKeyCols = paramArrayOfInt;
    setColumnStructures();
    this.primaryKeyTypes = new Type[this.primaryKeyCols.length];
    ArrayUtil.projectRow(this.colTypes, this.primaryKeyCols, this.primaryKeyTypes);
    this.primaryKeyColsSequence = new int[this.primaryKeyCols.length];
    ArrayUtil.fillSequence(this.primaryKeyColsSequence);
    HsqlNameManager.HsqlName localHsqlName = paramHsqlName;
    if (localHsqlName == null) {
      localHsqlName = this.database.nameManager.newAutoName("IDX", getSchemaName(), getName(), 20);
    }
    createPrimaryIndex(this.primaryKeyCols, this.primaryKeyTypes, localHsqlName);
    setBestRowIdentifiers();
  }
  
  public void createPrimaryKeyConstraint(HsqlNameManager.HsqlName paramHsqlName, int[] paramArrayOfInt, boolean paramBoolean)
  {
    createPrimaryKey(paramHsqlName, paramArrayOfInt, paramBoolean);
    Constraint localConstraint = new Constraint(paramHsqlName, this, getPrimaryIndex(), 4);
    addConstraint(localConstraint);
  }
  
  void setColumnStructures()
  {
    if (this.colTypes == null) {
      this.colTypes = new Type[this.columnCount];
    }
    this.colDefaults = new Expression[this.columnCount];
    this.colNotNull = new boolean[this.columnCount];
    this.colGenerated = new boolean[this.columnCount];
    this.defaultColumnMap = new int[this.columnCount];
    this.hasDomainColumns = false;
    for (int i = 0; i < this.columnCount; i++) {
      setColumnTypeVars(i);
    }
    resetDefaultsFlag();
  }
  
  void setColumnTypeVars(int paramInt)
  {
    ColumnSchema localColumnSchema = getColumn(paramInt);
    Type localType = localColumnSchema.getDataType();
    if (localType.isDomainType()) {
      this.hasDomainColumns = true;
    }
    if (localType.isLobType()) {
      this.hasLobColumn = true;
    }
    this.colTypes[paramInt] = localType;
    this.colNotNull[paramInt] = ((localColumnSchema.isPrimaryKey()) || (!localColumnSchema.isNullable()) ? 1 : false);
    this.defaultColumnMap[paramInt] = paramInt;
    if (localColumnSchema.isIdentity())
    {
      this.identitySequence = localColumnSchema.getIdentitySequence();
      this.identityColumn = paramInt;
    }
    else if (this.identityColumn == paramInt)
    {
      this.identityColumn = -1;
    }
    this.colDefaults[paramInt] = localColumnSchema.getDefaultExpression();
    this.colGenerated[paramInt] = localColumnSchema.isGenerated();
    resetDefaultsFlag();
  }
  
  int[] getColumnMap()
  {
    return this.defaultColumnMap;
  }
  
  int[] getNewColumnMap()
  {
    return new int[this.columnCount];
  }
  
  boolean[] getColumnCheckList(int[] paramArrayOfInt)
  {
    boolean[] arrayOfBoolean = new boolean[this.columnCount];
    for (int i = 0; i < paramArrayOfInt.length; i++)
    {
      int j = paramArrayOfInt[i];
      if (j > -1) {
        arrayOfBoolean[j] = true;
      }
    }
    return arrayOfBoolean;
  }
  
  int[] getColumnIndexes(String[] paramArrayOfString)
  {
    int[] arrayOfInt = new int[paramArrayOfString.length];
    for (int i = 0; i < arrayOfInt.length; i++) {
      arrayOfInt[i] = getColumnIndex(paramArrayOfString[i]);
    }
    return arrayOfInt;
  }
  
  int[] getColumnIndexes(OrderedHashSet paramOrderedHashSet)
  {
    int[] arrayOfInt = new int[paramOrderedHashSet.size()];
    for (int i = 0; i < arrayOfInt.length; i++)
    {
      arrayOfInt[i] = getColumnIndex((String)paramOrderedHashSet.get(i));
      if (arrayOfInt[i] == -1) {
        throw Error.error(5501, (String)paramOrderedHashSet.get(i));
      }
    }
    return arrayOfInt;
  }
  
  int[] getColumnIndexes(HashMappedList paramHashMappedList)
  {
    int[] arrayOfInt = new int[paramHashMappedList.size()];
    for (int i = 0; i < arrayOfInt.length; i++) {
      arrayOfInt[i] = ((Integer)paramHashMappedList.get(i)).intValue();
    }
    return arrayOfInt;
  }
  
  public ColumnSchema getColumn(int paramInt)
  {
    return (ColumnSchema)this.columnList.get(paramInt);
  }
  
  public OrderedHashSet getColumnNameSet(int[] paramArrayOfInt)
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      localOrderedHashSet.add(((ColumnSchema)this.columnList.get(i)).getName());
    }
    return localOrderedHashSet;
  }
  
  public OrderedHashSet getColumnNameSet(boolean[] paramArrayOfBoolean)
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    for (int i = 0; i < paramArrayOfBoolean.length; i++) {
      if (paramArrayOfBoolean[i] != 0) {
        localOrderedHashSet.add(this.columnList.get(i));
      }
    }
    return localOrderedHashSet;
  }
  
  public void getColumnNames(boolean[] paramArrayOfBoolean, Set paramSet)
  {
    for (int i = 0; i < paramArrayOfBoolean.length; i++) {
      if (paramArrayOfBoolean[i] != 0) {
        paramSet.add(((ColumnSchema)this.columnList.get(i)).getName());
      }
    }
  }
  
  public OrderedHashSet getColumnNameSet()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    for (int i = 0; i < this.columnCount; i++) {
      localOrderedHashSet.add(((ColumnSchema)this.columnList.get(i)).getName());
    }
    return localOrderedHashSet;
  }
  
  Object[] getNewRowData(Session paramSession)
  {
    Object[] arrayOfObject = new Object[this.columnCount];
    if (this.hasDefaultValues) {
      for (int i = 0; i < this.columnCount; i++)
      {
        Expression localExpression = this.colDefaults[i];
        if (localExpression != null) {
          arrayOfObject[i] = localExpression.getValue(paramSession, this.colTypes[i]);
        }
      }
    }
    return arrayOfObject;
  }
  
  boolean hasTrigger(int paramInt)
  {
    return this.triggerLists[paramInt].length != 0;
  }
  
  void addTrigger(TriggerDef paramTriggerDef, HsqlNameManager.HsqlName paramHsqlName)
  {
    int i = this.triggerList.length;
    if (paramHsqlName != null)
    {
      int j = getTriggerIndex(paramHsqlName.name);
      if (j != -1) {
        i = j + 1;
      }
    }
    this.triggerList = ((TriggerDef[])ArrayUtil.toAdjustedArray(this.triggerList, paramTriggerDef, i, 1));
    TriggerDef[] arrayOfTriggerDef = this.triggerLists[paramTriggerDef.triggerType];
    i = arrayOfTriggerDef.length;
    if (paramHsqlName != null) {
      for (int k = 0; k < arrayOfTriggerDef.length; k++)
      {
        TriggerDef localTriggerDef = arrayOfTriggerDef[k];
        if (localTriggerDef.getName().name.equals(paramHsqlName.name))
        {
          i = k + 1;
          break;
        }
      }
    }
    arrayOfTriggerDef = (TriggerDef[])ArrayUtil.toAdjustedArray(arrayOfTriggerDef, paramTriggerDef, i, 1);
    this.triggerLists[paramTriggerDef.triggerType] = arrayOfTriggerDef;
  }
  
  TriggerDef getTrigger(String paramString)
  {
    for (int i = this.triggerList.length - 1; i >= 0; i--) {
      if (this.triggerList[i].getName().name.equals(paramString)) {
        return this.triggerList[i];
      }
    }
    return null;
  }
  
  public int getTriggerIndex(String paramString)
  {
    for (int i = 0; i < this.triggerList.length; i++) {
      if (this.triggerList[i].getName().name.equals(paramString)) {
        return i;
      }
    }
    return -1;
  }
  
  void removeTrigger(TriggerDef paramTriggerDef)
  {
    TriggerDef localTriggerDef = null;
    for (int i = 0; i < this.triggerList.length; i++)
    {
      localTriggerDef = this.triggerList[i];
      if (localTriggerDef.getName().name.equals(paramTriggerDef.getName().name))
      {
        localTriggerDef.terminate();
        this.triggerList = ((TriggerDef[])ArrayUtil.toAdjustedArray(this.triggerList, null, i, -1));
        break;
      }
    }
    if (localTriggerDef == null) {
      return;
    }
    i = localTriggerDef.triggerType;
    for (int j = 0; j < this.triggerLists[i].length; j++)
    {
      localTriggerDef = this.triggerLists[i][j];
      if (localTriggerDef.getName().name.equals(paramTriggerDef.getName().name))
      {
        this.triggerLists[i] = ((TriggerDef[])(TriggerDef[])ArrayUtil.toAdjustedArray(this.triggerLists[i], null, j, -1));
        break;
      }
    }
  }
  
  void releaseTriggers()
  {
    for (int i = 0; i < 9; i++)
    {
      for (int j = 0; j < this.triggerLists[i].length; j++) {
        this.triggerLists[i][j].terminate();
      }
      this.triggerLists[i] = TriggerDef.emptyArray;
    }
    this.triggerList = TriggerDef.emptyArray;
  }
  
  int getIndexIndex(String paramString)
  {
    Index[] arrayOfIndex = this.indexList;
    for (int i = 0; i < arrayOfIndex.length; i++) {
      if (paramString.equals(arrayOfIndex[i].getName().name)) {
        return i;
      }
    }
    return -1;
  }
  
  Index getIndex(String paramString)
  {
    Index[] arrayOfIndex = this.indexList;
    int i = getIndexIndex(paramString);
    return i == -1 ? null : arrayOfIndex[i];
  }
  
  int getConstraintIndex(String paramString)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      if (this.constraintList[i].getName().name.equals(paramString)) {
        return i;
      }
      i++;
    }
    return -1;
  }
  
  public Constraint getConstraint(String paramString)
  {
    int i = getConstraintIndex(paramString);
    return i < 0 ? null : this.constraintList[i];
  }
  
  public Constraint getUniqueConstraintForIndex(Index paramIndex)
  {
    int i = 0;
    int j = this.constraintList.length;
    while (i < j)
    {
      Constraint localConstraint = this.constraintList[i];
      if ((localConstraint.getMainIndex() == paramIndex) && ((localConstraint.getConstraintType() == 4) || (localConstraint.getConstraintType() == 2))) {
        return localConstraint;
      }
      i++;
    }
    return null;
  }
  
  void removeConstraint(String paramString)
  {
    int i = getConstraintIndex(paramString);
    if (i != -1) {
      removeConstraint(i);
    }
  }
  
  void removeConstraint(int paramInt)
  {
    this.constraintList = ((Constraint[])ArrayUtil.toAdjustedArray(this.constraintList, null, paramInt, -1));
    updateConstraintLists();
  }
  
  void renameColumn(ColumnSchema paramColumnSchema, String paramString, boolean paramBoolean)
  {
    String str = paramColumnSchema.getName().name;
    int i = getColumnIndex(str);
    this.columnList.setKey(i, paramString);
    paramColumnSchema.getName().rename(paramString, paramBoolean);
  }
  
  void renameColumn(ColumnSchema paramColumnSchema, HsqlNameManager.HsqlName paramHsqlName)
  {
    String str = paramColumnSchema.getName().name;
    int i = getColumnIndex(str);
    if (findColumn(paramHsqlName.name) != -1) {
      throw Error.error(5504);
    }
    this.columnList.setKey(i, paramHsqlName.name);
    paramColumnSchema.getName().rename(paramHsqlName);
  }
  
  public TriggerDef[] getTriggers()
  {
    return this.triggerList;
  }
  
  public boolean isWritable()
  {
    return (!this.isReadOnly) && (!this.database.databaseReadOnly) && ((!this.database.isFilesReadOnly()) || ((!this.isCached) && (!this.isText)));
  }
  
  public boolean isInsertable()
  {
    return isWritable();
  }
  
  public boolean isUpdatable()
  {
    return isWritable();
  }
  
  public boolean isTriggerInsertable()
  {
    return false;
  }
  
  public boolean isTriggerUpdatable()
  {
    return false;
  }
  
  public boolean isTriggerDeletable()
  {
    return false;
  }
  
  public int[] getUpdatableColumns()
  {
    return this.defaultColumnMap;
  }
  
  public Table getBaseTable()
  {
    return this;
  }
  
  public int[] getBaseTableColumnMap()
  {
    return this.defaultColumnMap;
  }
  
  Index createIndexForColumns(Session paramSession, int[] paramArrayOfInt)
  {
    Index localIndex = null;
    HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newAutoName("IDX_T", getSchemaName(), getName(), 20);
    try
    {
      localIndex = createAndAddIndexStructure(localHsqlName, paramArrayOfInt, null, null, false, false, false);
    }
    catch (Throwable localThrowable)
    {
      return null;
    }
    switch (this.tableType)
    {
    case 1: 
    case 3: 
      paramSession.sessionData.persistentStoreCollection.registerIndex(this);
      break;
    }
    return localIndex;
  }
  
  void fireTriggers(Session paramSession, int paramInt, RowSetNavigatorDataChange paramRowSetNavigatorDataChange)
  {
    if (!this.database.isReferentialIntegrity()) {
      return;
    }
    TriggerDef[] arrayOfTriggerDef = this.triggerLists[paramInt];
    int i = 0;
    int j = arrayOfTriggerDef.length;
    while (i < j)
    {
      TriggerDef localTriggerDef = arrayOfTriggerDef[i];
      boolean bool = localTriggerDef instanceof TriggerDefSQL;
      if (localTriggerDef.hasOldTable()) {}
      localTriggerDef.pushPair(paramSession, null, null);
      i++;
    }
  }
  
  void fireTriggers(Session paramSession, int paramInt, RowSetNavigator paramRowSetNavigator)
  {
    if (!this.database.isReferentialIntegrity()) {
      return;
    }
    TriggerDef[] arrayOfTriggerDef = this.triggerLists[paramInt];
    int i = 0;
    int j = arrayOfTriggerDef.length;
    while (i < j)
    {
      TriggerDef localTriggerDef = arrayOfTriggerDef[i];
      boolean bool = localTriggerDef instanceof TriggerDefSQL;
      if (localTriggerDef.hasOldTable()) {}
      localTriggerDef.pushPair(paramSession, null, null);
      i++;
    }
  }
  
  void fireTriggers(Session paramSession, int paramInt, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfInt)
  {
    if (!this.database.isReferentialIntegrity()) {
      return;
    }
    TriggerDef[] arrayOfTriggerDef = this.triggerLists[paramInt];
    int i = 0;
    int j = arrayOfTriggerDef.length;
    while (i < j)
    {
      TriggerDef localTriggerDef = arrayOfTriggerDef[i];
      boolean bool = localTriggerDef instanceof TriggerDefSQL;
      if ((paramArrayOfInt == null) || (localTriggerDef.getUpdateColumnIndexes() == null) || (ArrayUtil.haveCommonElement(localTriggerDef.getUpdateColumnIndexes(), paramArrayOfInt))) {
        if (localTriggerDef.isForEachRow())
        {
          switch (localTriggerDef.triggerType)
          {
          case 6: 
            break;
          case 3: 
            if (!bool) {
              paramArrayOfObject2 = (Object[])ArrayUtil.duplicateArray(paramArrayOfObject2);
            }
            break;
          case 5: 
            if (!bool)
            {
              paramArrayOfObject1 = (Object[])ArrayUtil.duplicateArray(paramArrayOfObject1);
              paramArrayOfObject2 = (Object[])ArrayUtil.duplicateArray(paramArrayOfObject2);
            }
            break;
          case 4: 
          case 7: 
          case 8: 
            if (!bool) {
              paramArrayOfObject1 = (Object[])ArrayUtil.duplicateArray(paramArrayOfObject1);
            }
            break;
          }
          localTriggerDef.pushPair(paramSession, paramArrayOfObject1, paramArrayOfObject2);
        }
        else
        {
          localTriggerDef.pushPair(paramSession, null, null);
        }
      }
      i++;
    }
  }
  
  public void enforceRowConstraints(Session paramSession, Object[] paramArrayOfObject)
  {
    for (int i = 0; i < this.columnCount; i++)
    {
      Type localType = this.colTypes[i];
      Object localObject;
      ColumnSchema localColumnSchema;
      if ((this.hasDomainColumns) && (localType.isDomainType()))
      {
        localObject = localType.userTypeModifier.getConstraints();
        localColumnSchema = getColumn(i);
        for (int j = 0; j < localObject.length; j++) {
          localObject[j].checkCheckConstraint(paramSession, this, localColumnSchema, paramArrayOfObject[i]);
        }
      }
      if ((this.colNotNull[i] != 0) && (paramArrayOfObject[i] == null))
      {
        Constraint localConstraint = getNotNullConstraintForColumn(i);
        if ((localConstraint == null) && (ArrayUtil.find(this.primaryKeyCols, i) > -1)) {
          localConstraint = getPrimaryConstraint();
        }
        localObject = localConstraint == null ? "" : localConstraint.getName().name;
        localColumnSchema = getColumn(i);
        String[] arrayOfString = { localObject, this.tableName.statementName, localColumnSchema.getName().statementName };
        throw Error.error(null, 10, 3, arrayOfString);
      }
    }
  }
  
  public void enforceTypeLimits(Session paramSession, Object[] paramArrayOfObject)
  {
    for (int i = 0; i < this.columnCount; i++) {
      paramArrayOfObject[i] = this.colTypes[i].convertToTypeLimits(paramSession, paramArrayOfObject[i]);
    }
  }
  
  int indexTypeForColumn(Session paramSession, int paramInt)
  {
    int i = this.bestIndexForColumn[paramInt];
    if (i > -1) {
      return (this.indexList[i].isUnique()) && (this.indexList[i].getColumnCount() == 1) ? 2 : 1;
    }
    switch (this.tableType)
    {
    case 1: 
    case 2: 
    case 3: 
    case 8: 
    case 11: 
      return 1;
    }
    return 0;
  }
  
  synchronized Index getIndexForColumns(Session paramSession, int[] paramArrayOfInt)
  {
    int i = this.bestIndexForColumn[paramArrayOfInt[0]];
    if (i > -1) {
      return this.indexList[i];
    }
    switch (this.tableType)
    {
    case 1: 
    case 2: 
    case 3: 
    case 8: 
    case 11: 
      Index localIndex = createIndexForColumns(paramSession, paramArrayOfInt);
      return localIndex;
    }
    return null;
  }
  
  Index getFullIndexForColumns(int[] paramArrayOfInt)
  {
    for (int i = 0; i < this.indexList.length; i++) {
      if (ArrayUtil.haveEqualArrays(this.indexList[i].getColumns(), paramArrayOfInt, paramArrayOfInt.length)) {
        return this.indexList[i];
      }
    }
    return null;
  }
  
  synchronized Index.IndexUse[] getIndexForColumns(Session paramSession, OrderedIntHashSet paramOrderedIntHashSet, int paramInt, boolean paramBoolean)
  {
    Object localObject = Index.emptyUseArray;
    if (paramOrderedIntHashSet.isEmpty()) {
      return Index.emptyUseArray;
    }
    int i = 0;
    int j = this.indexList.length;
    while (i < j)
    {
      Index localIndex2 = getIndex(i);
      int[] arrayOfInt = localIndex2.getColumns();
      int k = paramBoolean ? paramOrderedIntHashSet.getOrderedStartMatchCount(arrayOfInt) : paramOrderedIntHashSet.getStartMatchCount(arrayOfInt);
      if (k != 0)
      {
        if (k == paramOrderedIntHashSet.size()) {
          return localIndex2.asArray();
        }
        if ((k == localIndex2.getColumnCount()) && (localIndex2.isUnique())) {
          return localIndex2.asArray();
        }
        if ((localObject.length == 0) && (k == localIndex2.getColumnCount()))
        {
          localObject = localIndex2.asArray();
        }
        else
        {
          Index.IndexUse[] arrayOfIndexUse = new Index.IndexUse[localObject.length + 1];
          ArrayUtil.copyArray(localObject, arrayOfIndexUse, localObject.length);
          arrayOfIndexUse[(arrayOfIndexUse.length - 1)] = new Index.IndexUse(localIndex2, k);
          localObject = arrayOfIndexUse;
        }
      }
      i++;
    }
    switch (this.tableType)
    {
    case 1: 
    case 2: 
    case 3: 
    case 8: 
    case 11: 
      Index localIndex1 = createIndexForColumns(paramSession, paramOrderedIntHashSet.toArray());
      if (localIndex1 != null) {
        localObject = localIndex1.asArray();
      }
      break;
    }
    return localObject;
  }
  
  public final long[] getIndexRootsArray()
  {
    PersistentStore localPersistentStore = this.database.persistentStoreCollection.getStore(this);
    long[] arrayOfLong = new long[this.indexList.length * 2 + 1];
    int i = 0;
    for (int j = 0; j < this.indexList.length; j++)
    {
      CachedObject localCachedObject = localPersistentStore.getAccessor(this.indexList[j]);
      arrayOfLong[(i++)] = (localCachedObject == null ? -1L : localCachedObject.getPos());
    }
    for (j = 0; j < this.indexList.length; j++) {
      arrayOfLong[(i++)] = this.indexList[j].sizeUnique(localPersistentStore);
    }
    arrayOfLong[i] = this.indexList[0].size(null, localPersistentStore);
    return arrayOfLong;
  }
  
  public void setIndexRoots(long[] paramArrayOfLong)
  {
    if (!this.isCached) {
      throw Error.error(5501, this.tableName.name);
    }
    PersistentStore localPersistentStore = this.database.persistentStoreCollection.getStore(this);
    int i = 0;
    for (int j = 0; j < this.indexList.length; j++) {
      localPersistentStore.setAccessor(this.indexList[j], paramArrayOfLong[(i++)]);
    }
    long l = paramArrayOfLong[(this.indexList.length * 2)];
    for (int k = 0; k < this.indexList.length; k++) {
      localPersistentStore.setElementCount(this.indexList[k], l, paramArrayOfLong[(i++)]);
    }
  }
  
  void setIndexRoots(Session paramSession, String paramString)
  {
    if (!this.isCached) {
      throw Error.error(5501, this.tableName.name);
    }
    ParserDQL localParserDQL = new ParserDQL(paramSession, new Scanner(paramString));
    long[] arrayOfLong = new long[getIndexCount() * 2 + 1];
    localParserDQL.read();
    int i = 0;
    long l;
    for (int j = 0; j < getIndexCount(); j++)
    {
      l = localParserDQL.readBigint();
      arrayOfLong[(i++)] = l;
    }
    try
    {
      for (j = 0; j < getIndexCount() + 1; j++)
      {
        l = localParserDQL.readBigint();
        arrayOfLong[(i++)] = l;
      }
    }
    catch (Exception localException)
    {
      i = getIndexCount();
    }
    while (i < arrayOfLong.length)
    {
      arrayOfLong[i] = -1L;
      i++;
    }
    setIndexRoots(arrayOfLong);
  }
  
  Row insertSingleRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfInt)
  {
    if (this.identityColumn != -1) {
      setIdentityColumn(paramSession, paramArrayOfObject);
    }
    if (this.hasGeneratedValues) {
      setGeneratedColumns(paramSession, paramArrayOfObject);
    }
    if ((this.hasDomainColumns) || (this.hasNotNullColumns)) {
      enforceRowConstraints(paramSession, paramArrayOfObject);
    }
    if (this.isView) {
      return null;
    }
    Row localRow = (Row)paramPersistentStore.getNewCachedObject(paramSession, paramArrayOfObject, true);
    paramSession.addInsertAction(this, paramPersistentStore, localRow, paramArrayOfInt);
    return localRow;
  }
  
  void insertIntoTable(Session paramSession, Result paramResult)
  {
    PersistentStore localPersistentStore = getRowStore(paramSession);
    RowSetNavigator localRowSetNavigator = paramResult.initialiseNavigator();
    while (localRowSetNavigator.hasNext())
    {
      Object[] arrayOfObject1 = (Object[])localRowSetNavigator.getNext();
      Object[] arrayOfObject2 = (Object[])ArrayUtil.resizeArrayIfDifferent(arrayOfObject1, this.columnCount);
      insertData(paramSession, localPersistentStore, arrayOfObject2);
    }
  }
  
  public void insertNoCheckFromLog(Session paramSession, Object[] paramArrayOfObject)
  {
    systemUpdateIdentityValue(paramArrayOfObject);
    PersistentStore localPersistentStore = getRowStore(paramSession);
    Row localRow = (Row)localPersistentStore.getNewCachedObject(paramSession, paramArrayOfObject, true);
    paramSession.addInsertAction(this, localPersistentStore, localRow, null);
  }
  
  public int insertSys(Session paramSession, PersistentStore paramPersistentStore, Result paramResult)
  {
    RowSetNavigator localRowSetNavigator = paramResult.getNavigator();
    for (int i = 0; localRowSetNavigator.hasNext(); i++) {
      insertSys(paramSession, paramPersistentStore, (Object[])localRowSetNavigator.getNext());
    }
    return i;
  }
  
  void insertResult(Session paramSession, PersistentStore paramPersistentStore, Result paramResult)
  {
    RowSetNavigator localRowSetNavigator = paramResult.initialiseNavigator();
    while (localRowSetNavigator.hasNext())
    {
      Object[] arrayOfObject1 = (Object[])localRowSetNavigator.getNext();
      Object[] arrayOfObject2 = (Object[])ArrayUtil.resizeArrayIfDifferent(arrayOfObject1, this.columnCount);
      insertData(paramSession, paramPersistentStore, arrayOfObject2);
    }
  }
  
  public void insertFromScript(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject)
  {
    systemUpdateIdentityValue(paramArrayOfObject);
    if (paramSession.database.getProperties().isVersion18()) {
      for (int i = 0; i < this.columnCount; i++) {
        if ((paramArrayOfObject[i] != null) && ((this.colTypes[i].isCharacterType()) || (this.colTypes[i].isBinaryType())))
        {
          int j;
          if ((paramArrayOfObject[i] instanceof String)) {
            j = ((String)paramArrayOfObject[i]).length();
          } else if ((paramArrayOfObject[i] instanceof BinaryData)) {
            j = (int)((BinaryData)paramArrayOfObject[i]).length(paramSession);
          } else {
            throw Error.runtimeError(1200, "Table");
          }
          if (j > this.colTypes[i].precision)
          {
            j = (j / 10 + 1) * 10;
            this.colTypes[i] = Type.getType(this.colTypes[i].typeCode, this.colTypes[i].getCharacterSet(), this.colTypes[i].getCollation(), j, 0);
            ColumnSchema localColumnSchema = getColumn(i);
            localColumnSchema.setType(this.colTypes[i]);
          }
        }
      }
    }
    insertData(paramSession, paramPersistentStore, paramArrayOfObject);
  }
  
  public void insertData(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject)
  {
    Row localRow = (Row)paramPersistentStore.getNewCachedObject(paramSession, paramArrayOfObject, false);
    paramPersistentStore.indexRow(paramSession, localRow);
  }
  
  public void insertSys(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject)
  {
    Row localRow = (Row)paramPersistentStore.getNewCachedObject(paramSession, paramArrayOfObject, false);
    paramPersistentStore.indexRow(paramSession, localRow);
  }
  
  protected void setIdentityColumn(Session paramSession, Object[] paramArrayOfObject)
  {
    if (this.identityColumn != -1)
    {
      Number localNumber = (Number)paramArrayOfObject[this.identityColumn];
      if (this.identitySequence.getName() == null)
      {
        if (localNumber == null)
        {
          localNumber = (Number)this.identitySequence.getValueObject();
          paramArrayOfObject[this.identityColumn] = localNumber;
        }
        else
        {
          this.identitySequence.userUpdate(localNumber.longValue());
        }
      }
      else if (localNumber == null)
      {
        localNumber = (Number)paramSession.sessionData.getSequenceValue(this.identitySequence);
        paramArrayOfObject[this.identityColumn] = localNumber;
      }
      if (paramSession != null) {
        paramSession.setLastIdentity(localNumber);
      }
    }
  }
  
  public void setGeneratedColumns(Session paramSession, Object[] paramArrayOfObject)
  {
    if (this.hasGeneratedValues) {
      for (int i = 0; i < this.colGenerated.length; i++) {
        if (this.colGenerated[i] != 0)
        {
          Expression localExpression = getColumn(i).getGeneratingExpression();
          RangeVariable.RangeIteratorBase localRangeIteratorBase = paramSession.sessionContext.getCheckIterator(getDefaultRanges()[0]);
          localRangeIteratorBase.currentData = paramArrayOfObject;
          paramArrayOfObject[i] = localExpression.getValue(paramSession, this.colTypes[i]);
        }
      }
    }
  }
  
  public void systemSetIdentityColumn(Session paramSession, Object[] paramArrayOfObject)
  {
    if (this.identityColumn != -1)
    {
      Number localNumber = (Number)paramArrayOfObject[this.identityColumn];
      if (localNumber == null)
      {
        localNumber = (Number)this.identitySequence.getValueObject();
        paramArrayOfObject[this.identityColumn] = localNumber;
      }
      else
      {
        this.identitySequence.userUpdate(localNumber.longValue());
      }
    }
  }
  
  protected void systemUpdateIdentityValue(Object[] paramArrayOfObject)
  {
    if (this.identityColumn != -1)
    {
      Number localNumber = (Number)paramArrayOfObject[this.identityColumn];
      if (localNumber != null) {
        this.identitySequence.systemUpdate(localNumber.longValue());
      }
    }
  }
  
  public Row getDeleteRowFromLog(Session paramSession, Object[] paramArrayOfObject)
  {
    Row localRow = null;
    PersistentStore localPersistentStore = getRowStore(paramSession);
    RowIterator localRowIterator;
    if (hasPrimaryKey())
    {
      localRowIterator = getPrimaryIndex().findFirstRow(paramSession, localPersistentStore, paramArrayOfObject, this.primaryKeyColsSequence);
      localRow = localRowIterator.getNextRow();
      localRowIterator.release();
    }
    else if (this.bestIndex == null)
    {
      localRowIterator = rowIterator(paramSession);
      for (;;)
      {
        localRow = localRowIterator.getNextRow();
        if (localRow != null) {
          if (compareRows(paramSession, localRow.getData(), paramArrayOfObject, this.defaultColumnMap, this.colTypes) == 0) {
            break;
          }
        }
      }
      localRowIterator.release();
    }
    else
    {
      localRowIterator = this.bestIndex.findFirstRow(paramSession, localPersistentStore, paramArrayOfObject);
      for (;;)
      {
        localRow = localRowIterator.getNextRow();
        if (localRow == null) {
          break;
        }
        Object[] arrayOfObject = localRow.getData();
        if (this.bestIndex.compareRowNonUnique(paramSession, arrayOfObject, paramArrayOfObject, this.bestIndex.getColumns()) != 0) {
          localRow = null;
        } else {
          if (compareRows(paramSession, arrayOfObject, paramArrayOfObject, this.defaultColumnMap, this.colTypes) == 0) {
            break;
          }
        }
      }
      localRowIterator.release();
    }
    return localRow;
  }
  
  public RowIterator rowIteratorClustered(Session paramSession)
  {
    PersistentStore localPersistentStore = getRowStore(paramSession);
    Index localIndex = getClusteredIndex();
    if (localIndex == null) {
      localIndex = getPrimaryIndex();
    }
    return localIndex.firstRow(paramSession, localPersistentStore);
  }
  
  public RowIterator rowIteratorClustered(PersistentStore paramPersistentStore)
  {
    Index localIndex = getClusteredIndex();
    if (localIndex == null) {
      localIndex = getPrimaryIndex();
    }
    return localIndex.firstRow(paramPersistentStore);
  }
  
  public void clearAllData(Session paramSession)
  {
    super.clearAllData(paramSession);
    if (this.identitySequence != null) {
      this.identitySequence.reset();
    }
  }
  
  public void clearAllData(PersistentStore paramPersistentStore)
  {
    super.clearAllData(paramPersistentStore);
    if (this.identitySequence != null) {
      this.identitySequence.reset();
    }
  }
  
  public PersistentStore getRowStore(Session paramSession)
  {
    if (this.store != null) {
      return this.store;
    }
    if (this.isSessionBased) {
      return paramSession.sessionData.persistentStoreCollection.getStore(this);
    }
    return this.database.persistentStoreCollection.getStore(this);
  }
  
  public void setDataTimestamp(long paramLong)
  {
    this.dataTimestamp = paramLong;
  }
  
  public QueryExpression getQueryExpression()
  {
    return null;
  }
  
  public Expression getDataExpression()
  {
    return null;
  }
  
  public void prepareTable() {}
  
  public void materialise(Session paramSession) {}
  
  public void materialiseCorrelated(Session paramSession) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.Table
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */