package org.hsqldb;

import java.util.Comparator;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.navigator.RowSetNavigatorDataTable;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.types.Type;

public class TableDerived
  extends Table
  implements Comparator
{
  public static final TableDerived[] emptyArray = new TableDerived[0];
  QueryExpression queryExpression;
  Expression dataExpression;
  boolean uniqueRows;
  boolean uniquePredicate;
  String sql;
  View view;
  int depth;
  boolean canRecompile = false;
  
  public TableDerived(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, int paramInt)
  {
    super(paramDatabase, paramHsqlName, paramInt);
    switch (paramInt)
    {
    case 2: 
    case 8: 
    case 9: 
    case 11: 
    case 12: 
    case 13: 
      break;
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 10: 
    default: 
      throw Error.runtimeError(201, "Table");
    }
  }
  
  public TableDerived(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, int paramInt, Type[] paramArrayOfType, HashMappedList paramHashMappedList, int[] paramArrayOfInt)
  {
    this(paramDatabase, paramHsqlName, paramInt);
    this.colTypes = paramArrayOfType;
    this.columnList = paramHashMappedList;
    this.columnCount = paramHashMappedList.size();
    createPrimaryKey(null, paramArrayOfInt, true);
  }
  
  public TableDerived(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName, int paramInt1, QueryExpression paramQueryExpression, Expression paramExpression, int paramInt2, int paramInt3)
  {
    super(paramDatabase, paramHsqlName, paramInt1);
    switch (paramInt1)
    {
    case 2: 
    case 8: 
      break;
    default: 
      throw Error.runtimeError(201, "Table");
    }
    this.queryExpression = paramQueryExpression;
    this.dataExpression = paramExpression;
    this.depth = paramInt3;
    switch (paramInt2)
    {
    case 55: 
      paramQueryExpression.setSingleRow();
      break;
    case 54: 
      if (paramQueryExpression != null) {
        paramQueryExpression.setFullOrder();
      }
      this.uniqueRows = true;
      break;
    case 57: 
      paramQueryExpression.setFullOrder();
      this.uniquePredicate = true;
    }
    if (paramExpression != null) {
      paramExpression.table = this;
    }
  }
  
  public int getId()
  {
    return 0;
  }
  
  public boolean isQueryBased()
  {
    return true;
  }
  
  public boolean isWritable()
  {
    return true;
  }
  
  public boolean isInsertable()
  {
    if ((this.view != null) && (this.view.isTriggerInsertable)) {
      return false;
    }
    return this.queryExpression == null ? false : this.queryExpression.isInsertable();
  }
  
  public boolean isUpdatable()
  {
    if ((this.view != null) && (this.view.isTriggerUpdatable)) {
      return false;
    }
    return this.queryExpression == null ? false : this.queryExpression.isUpdatable();
  }
  
  public int[] getUpdatableColumns()
  {
    if (this.queryExpression != null) {
      return this.queryExpression.getBaseTableColumnMap();
    }
    return this.defaultColumnMap;
  }
  
  public boolean isTriggerInsertable()
  {
    if (this.view != null) {
      return this.view.isTriggerInsertable;
    }
    return false;
  }
  
  public boolean isTriggerUpdatable()
  {
    if (this.view != null) {
      return this.view.isTriggerUpdatable;
    }
    return false;
  }
  
  public boolean isTriggerDeletable()
  {
    if (this.view != null) {
      return this.view.isTriggerDeletable;
    }
    return false;
  }
  
  public Table getBaseTable()
  {
    return this.queryExpression == null ? this : this.queryExpression.getBaseTable();
  }
  
  public int[] getBaseTableColumnMap()
  {
    return this.queryExpression == null ? null : this.queryExpression.getBaseTableColumnMap();
  }
  
  public QueryExpression getQueryExpression()
  {
    return this.queryExpression;
  }
  
  public Expression getDataExpression()
  {
    return this.dataExpression;
  }
  
  public void prepareTable()
  {
    if (this.columnCount > 0) {
      return;
    }
    if ((this.dataExpression != null) && (this.columnCount == 0))
    {
      TableUtil.addAutoColumns(this, this.dataExpression.nodeDataTypes);
      setTableIndexesForSubquery();
    }
    if (this.queryExpression != null)
    {
      this.columnList = this.queryExpression.getColumns();
      this.columnCount = this.queryExpression.getColumnCount();
      setTableIndexesForSubquery();
    }
  }
  
  public void prepareTable(HsqlNameManager.HsqlName[] paramArrayOfHsqlName)
  {
    if ((this.dataExpression == null) || (this.queryExpression != null))
    {
      this.columnCount = this.queryExpression.getColumnCount();
      this.columnList = this.queryExpression.getColumns();
    }
    if (paramArrayOfHsqlName != null)
    {
      if (paramArrayOfHsqlName.length != this.columnList.size()) {
        throw Error.error(5593);
      }
      for (int i = 0; i < this.columnCount; i++)
      {
        this.columnList.setKey(i, paramArrayOfHsqlName[i].name);
        ColumnSchema localColumnSchema = (ColumnSchema)this.columnList.get(i);
        localColumnSchema.getName().rename(paramArrayOfHsqlName[i]);
      }
    }
    setTableIndexesForSubquery();
  }
  
  private void setTableIndexesForSubquery()
  {
    int[] arrayOfInt1 = null;
    if ((this.uniqueRows) || (this.uniquePredicate))
    {
      arrayOfInt1 = new int[getColumnCount()];
      ArrayUtil.fillSequence(arrayOfInt1);
    }
    int[] arrayOfInt2 = this.uniqueRows ? arrayOfInt1 : null;
    if (this.primaryKeyCols == null) {
      createPrimaryKey(null, arrayOfInt2, false);
    }
    if (this.uniqueRows) {
      this.fullIndex = getPrimaryIndex();
    } else if (this.uniquePredicate) {
      this.fullIndex = createIndexForColumns(null, arrayOfInt1);
    }
  }
  
  void setCorrelated()
  {
    if (this.dataExpression != null) {
      this.dataExpression.isCorrelated = true;
    }
    if (this.queryExpression != null) {
      this.queryExpression.isCorrelated = true;
    }
  }
  
  boolean isCorrelated()
  {
    if (this.dataExpression != null) {
      return this.dataExpression.isCorrelated;
    }
    if (this.queryExpression != null) {
      return this.queryExpression.isCorrelated;
    }
    return false;
  }
  
  boolean hasUniqueNotNullRows(Session paramSession)
  {
    return getNavigator(paramSession).hasUniqueNotNullRows(paramSession);
  }
  
  void resetToView()
  {
    this.queryExpression = this.view.getQueryExpression();
  }
  
  public void materialise(Session paramSession)
  {
    paramSession.sessionContext.pushStatementState();
    try
    {
      if (this.dataExpression != null)
      {
        localPersistentStore = paramSession.sessionData.getSubqueryRowStore(this);
        this.dataExpression.insertValuesIntoSubqueryTable(paramSession, localPersistentStore);
        return;
      }
      if (this.queryExpression == null) {
        return;
      }
      Result localResult = this.queryExpression.getResult(paramSession, 0);
      if (this.uniqueRows)
      {
        RowSetNavigatorData localRowSetNavigatorData = (RowSetNavigatorData)localResult.getNavigator();
        localRowSetNavigatorData.removeDuplicates(paramSession);
      }
      PersistentStore localPersistentStore = paramSession.sessionData.getSubqueryRowStore(this);
      insertResult(paramSession, localPersistentStore, localResult);
      localResult.getNavigator().release();
    }
    finally
    {
      paramSession.sessionContext.popStatementState();
    }
  }
  
  public void materialiseCorrelated(Session paramSession)
  {
    if (isCorrelated()) {
      materialise(paramSession);
    }
  }
  
  public boolean isRecompiled()
  {
    if ((this.canRecompile) && ((this.queryExpression instanceof QuerySpecification)))
    {
      QuerySpecification localQuerySpecification = (QuerySpecification)this.queryExpression;
      return (!localQuerySpecification.isAggregated) && (!localQuerySpecification.isGrouped) && (!localQuerySpecification.isOrderSensitive);
    }
    return false;
  }
  
  public TableDerived newDerivedTable(Session paramSession)
  {
    TableDerived localTableDerived = this;
    if (isRecompiled())
    {
      ParserDQL localParserDQL = new ParserDQL(paramSession, new Scanner());
      localParserDQL.reset(this.sql, paramSession.parser.compileContext.getRangeVarCount());
      localParserDQL.read();
      localTableDerived = localParserDQL.XreadSubqueryTableBody(this.tableName, 23);
      paramSession.parser.compileContext.setNextRangeVarIndex(localParserDQL.compileContext.getRangeVarCount());
      localTableDerived.queryExpression.resolve(paramSession);
      localTableDerived.columnList = this.columnList;
      localTableDerived.columnCount = this.columnList.size();
      localTableDerived.triggerList = this.triggerList;
      localTableDerived.triggerLists = this.triggerLists;
      localTableDerived.view = this.view;
      localTableDerived.createPrimaryKey();
    }
    return localTableDerived;
  }
  
  public Object[] getValues(Session paramSession)
  {
    RowIterator localRowIterator = rowIterator(paramSession);
    if (localRowIterator.hasNext())
    {
      Row localRow = localRowIterator.getNextRow();
      if (localRowIterator.hasNext()) {
        throw Error.error(3201);
      }
      return localRow.getData();
    }
    return new Object[getColumnCount()];
  }
  
  public Object getValue(Session paramSession)
  {
    Object[] arrayOfObject = getValues(paramSession);
    return arrayOfObject[0];
  }
  
  public RowSetNavigatorData getNavigator(Session paramSession)
  {
    RowSetNavigatorDataTable localRowSetNavigatorDataTable = new RowSetNavigatorDataTable(paramSession, this);
    return localRowSetNavigatorDataTable;
  }
  
  public void setSQL(String paramString)
  {
    this.sql = paramString;
  }
  
  public int compare(Object paramObject1, Object paramObject2)
  {
    TableDerived localTableDerived1 = (TableDerived)paramObject1;
    TableDerived localTableDerived2 = (TableDerived)paramObject2;
    if ((localTableDerived1.view != null) && (localTableDerived2.view != null))
    {
      int i = this.database.schemaManager.getTableIndex(localTableDerived1.view);
      int j = this.database.schemaManager.getTableIndex(localTableDerived2.view);
      if (i == -1) {
        i = this.database.schemaManager.getTables(localTableDerived1.view.getSchemaName().name).size();
      }
      if (j == -1) {
        j = this.database.schemaManager.getTables(localTableDerived2.view.getSchemaName().name).size();
      }
      int k = i - j;
      return k == 0 ? localTableDerived2.depth - localTableDerived1.depth : k;
    }
    return localTableDerived2.depth - localTableDerived1.depth;
  }
  
  public void setDataTimestamp(long paramLong) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.TableDerived
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */