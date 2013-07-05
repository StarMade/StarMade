package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.OrderedLongHashSet;
import org.hsqldb.navigator.RangeIterator;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Type;

public class RangeVariable
  implements Cloneable
{
  static final RangeVariable[] emptyArray = new RangeVariable[0];
  public static final int TABLE_RANGE = 1;
  public static final int TRANSITION_RANGE = 2;
  public static final int PARAMETER_RANGE = 3;
  public static final int VARIALBE_RANGE = 4;
  Table rangeTable;
  final HsqlNameManager.SimpleName tableAlias;
  private OrderedHashSet columnAliases;
  private HsqlNameManager.SimpleName[] columnAliasNames;
  private OrderedHashSet columnNames;
  OrderedHashSet namedJoinColumns;
  HashMap namedJoinColumnExpressions;
  private Object[] emptyData;
  boolean[] columnsInGroupBy;
  boolean hasKeyedColumnInGroupBy;
  boolean[] usedColumns;
  boolean[] updatedColumns;
  RangeVariableConditions[] joinConditions;
  RangeVariableConditions[] whereConditions;
  int subRangeCount;
  Expression joinCondition;
  boolean isLateral;
  boolean isLeftJoin;
  boolean isRightJoin;
  boolean isBoundary;
  boolean hasLateral;
  boolean hasLeftJoin;
  boolean hasRightJoin;
  int level;
  int indexDistinctCount;
  int rangePositionInJoin;
  int rangePosition;
  int parsePosition;
  HashMappedList variables;
  int rangeType;
  boolean isGenerated;

  public RangeVariable(HashMappedList paramHashMappedList, HsqlNameManager.SimpleName paramSimpleName, boolean paramBoolean, int paramInt)
  {
    this.variables = paramHashMappedList;
    this.rangeType = paramInt;
    this.rangeTable = null;
    this.tableAlias = paramSimpleName;
    this.emptyData = null;
    this.columnsInGroupBy = null;
    this.usedColumns = null;
    this.joinConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, true) };
    this.whereConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, false) };
    switch (paramInt)
    {
    case 2:
    case 3:
    case 4:
      break;
    default:
      throw Error.runtimeError(201, "RangeVariable");
    }
  }

  public RangeVariable(Table paramTable, HsqlNameManager.SimpleName paramSimpleName, OrderedHashSet paramOrderedHashSet, HsqlNameManager.SimpleName[] paramArrayOfSimpleName, ParserDQL.CompileContext paramCompileContext)
  {
    this.rangeType = 1;
    this.rangeTable = paramTable;
    this.tableAlias = paramSimpleName;
    this.columnAliases = paramOrderedHashSet;
    this.columnAliasNames = paramArrayOfSimpleName;
    this.joinConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, true) };
    this.whereConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, false) };
    paramCompileContext.registerRangeVariable(this);
    if (this.rangeTable.getColumnCount() != 0)
      setRangeTableVariables();
  }

  public RangeVariable(Table paramTable, int paramInt)
  {
    this.rangeType = 1;
    this.rangeTable = paramTable;
    this.tableAlias = null;
    this.emptyData = this.rangeTable.getEmptyRowData();
    this.columnsInGroupBy = this.rangeTable.getNewColumnCheckList();
    this.usedColumns = this.rangeTable.getNewColumnCheckList();
    this.rangePosition = paramInt;
    this.joinConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, true) };
    this.whereConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, false) };
  }

  public void setRangeTableVariables()
  {
    if ((this.columnAliasNames != null) && (this.rangeTable.getColumnCount() != this.columnAliasNames.length))
      throw Error.error(5593);
    this.emptyData = this.rangeTable.getEmptyRowData();
    this.columnsInGroupBy = this.rangeTable.getNewColumnCheckList();
    this.usedColumns = this.rangeTable.getNewColumnCheckList();
    this.joinConditions[0].rangeIndex = this.rangeTable.getPrimaryIndex();
    this.whereConditions[0].rangeIndex = this.rangeTable.getPrimaryIndex();
  }

  public RangeVariable duplicate()
  {
    RangeVariable localRangeVariable = null;
    try
    {
      localRangeVariable = (RangeVariable)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw Error.runtimeError(201, "RangeVariable");
    }
    localRangeVariable.resetConditions();
    return localRangeVariable;
  }

  public void setJoinType(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.isLeftJoin = paramBoolean1;
    this.isRightJoin = paramBoolean2;
    if (this.isRightJoin)
      this.whereConditions[0].rangeIndex = this.rangeTable.getPrimaryIndex();
  }

  public void addNamedJoinColumns(OrderedHashSet paramOrderedHashSet)
  {
    this.namedJoinColumns = paramOrderedHashSet;
  }

  public void addColumn(int paramInt)
  {
    if (this.usedColumns != null)
      this.usedColumns[paramInt] = true;
  }

  public void addAllColumns()
  {
    if (this.usedColumns != null)
      ArrayUtil.fillArray(this.usedColumns, true);
  }

  public void addNamedJoinColumnExpression(String paramString, Expression paramExpression)
  {
    if (this.namedJoinColumnExpressions == null)
      this.namedJoinColumnExpressions = new HashMap();
    this.namedJoinColumnExpressions.put(paramString, paramExpression);
  }

  public ExpressionColumn getColumnExpression(String paramString)
  {
    return this.namedJoinColumnExpressions == null ? null : (ExpressionColumn)this.namedJoinColumnExpressions.get(paramString);
  }

  public Table getTable()
  {
    return this.rangeTable;
  }

  public boolean hasAnyIndexCondition()
  {
    for (int i = 0; i < this.joinConditions.length; i++)
      if (this.joinConditions[0].indexedColumnCount > 0)
        return true;
    for (i = 0; i < this.whereConditions.length; i++)
      if (this.whereConditions[0].indexedColumnCount > 0)
        return true;
    return false;
  }

  public boolean hasSingleIndexCondition()
  {
    return (this.joinConditions.length == 1) && (this.joinConditions[0].indexedColumnCount > 0);
  }

  public boolean setDistinctColumnsOnIndex(int[] paramArrayOfInt)
  {
    if (this.joinConditions.length != 1)
      return false;
    int[] arrayOfInt = this.joinConditions[0].rangeIndex.getColumns();
    if (paramArrayOfInt.length != ArrayUtil.countTrueElements(this.usedColumns))
      return false;
    if ((paramArrayOfInt.length == 1) && (paramArrayOfInt[0] == arrayOfInt[0]))
    {
      this.indexDistinctCount = 1;
      return true;
    }
    return false;
  }

  public Index getSortIndex()
  {
    if (this.joinConditions.length == 1)
      return this.joinConditions[0].rangeIndex;
    return null;
  }

  public boolean setSortIndex(Index paramIndex, boolean paramBoolean)
  {
    if ((this.joinConditions.length == 1) && (this.joinConditions[0].indexedColumnCount == 0))
    {
      this.joinConditions[0].rangeIndex = paramIndex;
      this.joinConditions[0].reversed = paramBoolean;
      return true;
    }
    return false;
  }

  public boolean reverseOrder()
  {
    this.joinConditions[0].reverseIndexCondition();
    return true;
  }

  public OrderedHashSet getColumnNames()
  {
    if (this.columnNames == null)
    {
      this.columnNames = new OrderedHashSet();
      this.rangeTable.getColumnNames(this.usedColumns, this.columnNames);
    }
    return this.columnNames;
  }

  public OrderedHashSet getUniqueColumnNameSet()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    if (this.columnAliases != null)
    {
      localOrderedHashSet.addAll(this.columnAliases);
      return localOrderedHashSet;
    }
    for (int i = 0; i < this.rangeTable.columnList.size(); i++)
    {
      String str = this.rangeTable.getColumn(i).getName().name;
      boolean bool = localOrderedHashSet.add(str);
      if (!bool)
        throw Error.error(5578, str);
    }
    return localOrderedHashSet;
  }

  public int findColumn(String paramString1, String paramString2, String paramString3)
  {
    if (resolvesSchemaAndTableName(paramString1, paramString2))
      return findColumn(paramString3);
    return -1;
  }

  private int findColumn(String paramString)
  {
    if ((this.namedJoinColumnExpressions != null) && (this.namedJoinColumnExpressions.containsKey(paramString)))
      return -1;
    if (this.variables != null)
      return this.variables.getIndex(paramString);
    if (this.columnAliases != null)
      return this.columnAliases.getIndex(paramString);
    return this.rangeTable.findColumn(paramString);
  }

  public ColumnSchema getColumn(int paramInt)
  {
    if (this.variables == null)
      return this.rangeTable.getColumn(paramInt);
    return (ColumnSchema)this.variables.get(paramInt);
  }

  public HsqlNameManager.SimpleName getColumnAlias(int paramInt)
  {
    if (this.columnAliases == null)
      return this.rangeTable.getColumn(paramInt).getName();
    return this.columnAliasNames[paramInt];
  }

  public boolean hasColumnAlias()
  {
    return this.columnAliases != null;
  }

  public boolean hasTableAlias()
  {
    return this.tableAlias != null;
  }

  public HsqlNameManager.SimpleName getTableAlias()
  {
    return this.tableAlias == null ? this.rangeTable.getName() : this.tableAlias;
  }

  public RangeVariable getRangeForTableName(String paramString)
  {
    if (resolvesTableName(paramString))
      return this;
    return null;
  }

  private boolean resolvesSchemaAndTableName(String paramString1, String paramString2)
  {
    return (resolvesSchemaName(paramString1)) && (resolvesTableName(paramString2));
  }

  private boolean resolvesTableName(String paramString)
  {
    if (paramString == null)
      return true;
    if (this.variables != null)
    {
      if (this.tableAlias != null)
        return paramString.equals(this.tableAlias.name);
      return false;
    }
    if (this.tableAlias == null)
    {
      if (paramString.equals(this.rangeTable.getName().name))
        return true;
    }
    else if (paramString.equals(this.tableAlias.name))
      return true;
    return false;
  }

  private boolean resolvesSchemaName(String paramString)
  {
    if (paramString == null)
      return true;
    if (this.variables != null)
      return false;
    if (this.tableAlias != null)
      return false;
    return paramString.equals(this.rangeTable.getSchemaName().name);
  }

  public void addTableColumns(HsqlArrayList paramHsqlArrayList)
  {
    if (this.namedJoinColumns != null)
    {
      int i = paramHsqlArrayList.size();
      int j = 0;
      for (int k = 0; k < i; k++)
      {
        Object localObject = (Expression)paramHsqlArrayList.get(k);
        String str = ((Expression)localObject).getColumnName();
        if (this.namedJoinColumns.contains(str))
        {
          if (j != k)
          {
            paramHsqlArrayList.remove(k);
            paramHsqlArrayList.add(j, localObject);
          }
          localObject = getColumnExpression(str);
          paramHsqlArrayList.set(j, localObject);
          j++;
        }
      }
    }
    addTableColumns(paramHsqlArrayList, paramHsqlArrayList.size(), this.namedJoinColumns);
  }

  public int addTableColumns(HsqlArrayList paramHsqlArrayList, int paramInt, HashSet paramHashSet)
  {
    Table localTable = getTable();
    int i = localTable.getColumnCount();
    for (int j = 0; j < i; j++)
    {
      ColumnSchema localColumnSchema = localTable.getColumn(j);
      String str = this.columnAliases == null ? localColumnSchema.getName().name : (String)this.columnAliases.get(j);
      if ((paramHashSet == null) || (!paramHashSet.contains(str)))
      {
        ExpressionColumn localExpressionColumn = new ExpressionColumn(this, j);
        paramHsqlArrayList.add(paramInt++, localExpressionColumn);
      }
    }
    return paramInt;
  }

  public void addTableColumns(RangeVariable paramRangeVariable, Expression paramExpression, HashSet paramHashSet)
  {
    if (paramRangeVariable == this)
    {
      Table localTable = getTable();
      int i = localTable.getColumnCount();
      addTableColumns(paramExpression, 0, i, paramHashSet);
    }
  }

  protected int getFirstColumnIndex(RangeVariable paramRangeVariable)
  {
    if (paramRangeVariable == this)
      return 0;
    return -1;
  }

  protected void addTableColumns(Expression paramExpression, int paramInt1, int paramInt2, HashSet paramHashSet)
  {
    Table localTable = getTable();
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
    {
      ColumnSchema localColumnSchema = localTable.getColumn(i);
      String str = this.columnAliases == null ? localColumnSchema.getName().name : (String)this.columnAliases.get(i);
      if ((paramHashSet == null) || (!paramHashSet.contains(str)))
      {
        ExpressionColumn localExpressionColumn = new ExpressionColumn(this, i);
        localHsqlArrayList.add(localExpressionColumn);
      }
    }
    Expression[] arrayOfExpression = new Expression[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfExpression);
    paramExpression.nodes = arrayOfExpression;
  }

  public void setForCheckConstraint()
  {
    this.joinConditions[0].rangeIndex = null;
    this.rangePosition = 0;
  }

  public Expression getJoinCondition()
  {
    return this.joinCondition;
  }

  public void addJoinCondition(Expression paramExpression)
  {
    this.joinCondition = ExpressionLogical.andExpressions(this.joinCondition, paramExpression);
  }

  public void resetConditions()
  {
    Index localIndex = this.joinConditions[0].rangeIndex;
    this.joinConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, true) };
    this.joinConditions[0].rangeIndex = localIndex;
    this.whereConditions = new RangeVariableConditions[] { new RangeVariableConditions(this, false) };
  }

  public OrderedHashSet getSubqueries()
  {
    OrderedHashSet localOrderedHashSet = null;
    if (this.joinCondition != null)
      localOrderedHashSet = this.joinCondition.collectAllSubqueries(localOrderedHashSet);
    if ((this.rangeTable instanceof TableDerived))
    {
      QueryExpression localQueryExpression = ((TableDerived)this.rangeTable).getQueryExpression();
      Object localObject;
      if (localQueryExpression == null)
      {
        localObject = ((TableDerived)this.rangeTable).getDataExpression();
        if (localObject != null)
        {
          if (localOrderedHashSet == null)
            localOrderedHashSet = new OrderedHashSet();
          OrderedHashSet.addAll(localOrderedHashSet, ((Expression)localObject).getSubqueries());
        }
      }
      else
      {
        localObject = localQueryExpression.getSubqueries();
        localOrderedHashSet = OrderedHashSet.addAll(localOrderedHashSet, (OrderedHashSet)localObject);
        localOrderedHashSet = OrderedHashSet.add(localOrderedHashSet, this.rangeTable);
      }
    }
    return localOrderedHashSet;
  }

  public OrderedHashSet collectAllExpressions(OrderedHashSet paramOrderedHashSet, OrderedIntHashSet paramOrderedIntHashSet1, OrderedIntHashSet paramOrderedIntHashSet2)
  {
    if (this.joinCondition != null)
      paramOrderedHashSet = this.joinCondition.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2);
    QueryExpression localQueryExpression = this.rangeTable.getQueryExpression();
    Expression localExpression = this.rangeTable.getDataExpression();
    if (localQueryExpression != null)
      paramOrderedHashSet = localQueryExpression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2);
    if (localExpression != null)
      paramOrderedHashSet = localExpression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2);
    return paramOrderedHashSet;
  }

  public void replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression)
  {
    QueryExpression localQueryExpression = this.rangeTable.getQueryExpression();
    Expression localExpression = this.rangeTable.getDataExpression();
    if (localExpression != null)
      localExpression = localExpression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
    if (localQueryExpression != null)
      localQueryExpression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
    if (this.joinCondition != null)
      this.joinCondition = this.joinCondition.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
    for (int i = 0; i < this.joinConditions.length; i++)
      this.joinConditions[i].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
    for (i = 0; i < this.whereConditions.length; i++)
      this.whereConditions[i].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
  }

  public void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2)
  {
    if (this.joinCondition != null)
      this.joinCondition.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2);
  }

  public void resolveRangeTable(Session paramSession, RangeGroup paramRangeGroup, RangeGroup[] paramArrayOfRangeGroup)
  {
    Table localTable = this.rangeTable;
    QueryExpression localQueryExpression = this.rangeTable.getQueryExpression();
    Expression localExpression = this.rangeTable.getDataExpression();
    if ((localQueryExpression == null) && (localExpression == null))
      return;
    paramArrayOfRangeGroup = (RangeGroup[])ArrayUtil.toAdjustedArray(paramArrayOfRangeGroup, paramRangeGroup, paramArrayOfRangeGroup.length, 1);
    HsqlList localHsqlList;
    if (localExpression != null)
    {
      localHsqlList = localExpression.resolveColumnReferences(paramSession, RangeGroup.emptyGroup, paramArrayOfRangeGroup, null);
      localHsqlList = Expression.resolveColumnSet(paramSession, emptyArray, RangeGroup.emptyArray, localHsqlList);
      ExpressionColumn.checkColumnsResolved(localHsqlList);
      localExpression.resolveTypes(paramSession, null);
      setRangeTableVariables();
    }
    if (localQueryExpression != null)
    {
      localQueryExpression.resolveReferences(paramSession, paramArrayOfRangeGroup);
      localHsqlList = localQueryExpression.getUnresolvedExpressions();
      localHsqlList = Expression.resolveColumnSet(paramSession, emptyArray, RangeGroup.emptyArray, localHsqlList);
      ExpressionColumn.checkColumnsResolved(localHsqlList);
      localQueryExpression.resolveTypesPartOne(paramSession);
      localQueryExpression.resolveTypesPartTwo(paramSession);
      this.rangeTable.prepareTable();
      setRangeTableVariables();
    }
  }

  void resolveRangeTableTypes(Session paramSession, RangeVariable[] paramArrayOfRangeVariable)
  {
    QueryExpression localQueryExpression = this.rangeTable.getQueryExpression();
    if (localQueryExpression != null)
    {
      if ((localQueryExpression instanceof QuerySpecification))
      {
        QuerySpecification localQuerySpecification = (QuerySpecification)localQueryExpression;
        if ((!localQuerySpecification.isGrouped) && (!localQuerySpecification.isAggregated) && (!localQuerySpecification.isOrderSensitive))
          moveConditionsToInner(paramSession, paramArrayOfRangeVariable);
      }
      localQueryExpression.resolveTypesPartThree(paramSession);
    }
  }

  void moveConditionsToInner(Session paramSession, RangeVariable[] paramArrayOfRangeVariable)
  {
    int i = ArrayUtil.find(paramArrayOfRangeVariable, this);
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Expression localExpression1 = null;
    if (this.isRightJoin)
    {
      if (this.whereConditions.length > 1)
        return;
      addConditionsToList(localHsqlArrayList, this.whereConditions[0].indexCond);
      if ((this.whereConditions[0].indexCond != null) && (this.whereConditions[0].indexCond[0] != this.whereConditions[0].indexEndCond[0]))
        addConditionsToList(localHsqlArrayList, this.whereConditions[0].indexEndCond);
      RangeVariableResolver.decomposeAndConditions(paramSession, this.whereConditions[0].nonIndexCondition, localHsqlArrayList);
    }
    else
    {
      if ((this.joinConditions.length > 1) || (this.whereConditions.length > 1))
        return;
      addConditionsToList(localHsqlArrayList, this.joinConditions[0].indexCond);
      if ((this.joinConditions[0].indexCond != null) && (this.joinConditions[0].indexCond[0] != this.joinConditions[0].indexEndCond[0]))
        addConditionsToList(localHsqlArrayList, this.joinConditions[0].indexEndCond);
      addConditionsToList(localHsqlArrayList, this.whereConditions[0].indexCond);
      addConditionsToList(localHsqlArrayList, this.whereConditions[0].indexEndCond);
      RangeVariableResolver.decomposeAndConditions(paramSession, this.joinConditions[0].nonIndexCondition, localHsqlArrayList);
      RangeVariableResolver.decomposeAndConditions(paramSession, this.whereConditions[0].nonIndexCondition, localHsqlArrayList);
    }
    for (int j = localHsqlArrayList.size() - 1; j >= 0; j--)
    {
      Expression localExpression2 = (Expression)localHsqlArrayList.get(j);
      if ((localExpression2 == null) || (localExpression2 == ExpressionLogical.EXPR_TRUE) || (localExpression2.hasReference(paramArrayOfRangeVariable, i)))
        localHsqlArrayList.remove(j);
    }
    if (localHsqlArrayList.size() == 0)
    {
      if (this.rangeTable.isView())
        ((TableDerived)this.rangeTable).resetToView();
      return;
    }
    QueryExpression localQueryExpression = this.rangeTable.getQueryExpression();
    Expression[] arrayOfExpression = ((QuerySpecification)localQueryExpression).exprColumns;
    for (int k = 0; k < localHsqlArrayList.size(); k++)
    {
      Expression localExpression3 = (Expression)localHsqlArrayList.get(k);
      if (!localExpression3.hasReference(paramArrayOfRangeVariable, i))
      {
        localExpression3 = localExpression3.duplicate();
        localExpression3 = localExpression3.replaceColumnReferences(this, arrayOfExpression);
        if (localExpression3.collectAllSubqueries(null) != null)
          return;
        localExpression1 = ExpressionLogical.andExpressions(localExpression1, localExpression3);
      }
    }
    localQueryExpression.addExtraConditions(localExpression1);
  }

  private static void addConditionsToList(HsqlArrayList paramHsqlArrayList, Expression[] paramArrayOfExpression)
  {
    if (paramArrayOfExpression == null)
      return;
    for (int i = 0; i < paramArrayOfExpression.length; i++)
      if ((paramArrayOfExpression[i] != null) && ((paramArrayOfExpression[i].isSingleColumnCondition) || (paramArrayOfExpression[i].isSingleColumnNull) || (paramArrayOfExpression[i].isSingleColumnNotNull)))
        paramHsqlArrayList.add(paramArrayOfExpression[i]);
  }

  public String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer2 = new StringBuffer(paramInt);
    for (int i = 0; i < paramInt; i++)
      localStringBuffer2.append(' ');
    StringBuffer localStringBuffer1 = new StringBuffer();
    String str = "INNER";
    if (this.isLeftJoin)
    {
      str = "LEFT OUTER";
      if (this.isRightJoin)
        str = "FULL";
    }
    else if (this.isRightJoin)
    {
      str = "RIGHT OUTER";
    }
    localStringBuffer1.append(localStringBuffer2).append("join type=").append(str).append("\n");
    localStringBuffer1.append(localStringBuffer2).append("table=").append(this.rangeTable.getName().name).append("\n");
    if (this.tableAlias != null)
      localStringBuffer1.append(localStringBuffer2).append("alias=").append(this.tableAlias.name).append("\n");
    RangeVariableConditions[] arrayOfRangeVariableConditions = this.joinConditions;
    if (this.whereConditions[0].hasIndexCondition())
      arrayOfRangeVariableConditions = this.whereConditions;
    localStringBuffer1.append(localStringBuffer2).append("cardinality=");
    localStringBuffer1.append(arrayOfRangeVariableConditions[0].rangeIndex.size(paramSession, this.rangeTable.getRowStore(paramSession))).append("\n");
    int j = !arrayOfRangeVariableConditions[0].hasIndexCondition() ? 1 : 0;
    localStringBuffer1.append(localStringBuffer2);
    if ((arrayOfRangeVariableConditions == this.whereConditions) && (this.joinConditions[0].nonIndexCondition != null))
    {
      localStringBuffer1.append("join condition = [");
      localStringBuffer1.append(this.joinConditions[0].nonIndexCondition.describe(paramSession, paramInt));
      localStringBuffer1.append(localStringBuffer2).append("]\n");
      localStringBuffer1.append(localStringBuffer2);
    }
    localStringBuffer1.append("access=").append(j != 0 ? "FULL SCAN" : "INDEX PRED").append("\n");
    for (int k = 0; k < arrayOfRangeVariableConditions.length; k++)
    {
      if (k > 0)
      {
        localStringBuffer1.append(localStringBuffer2).append("OR condition = [");
      }
      else
      {
        localStringBuffer1.append(localStringBuffer2);
        if (arrayOfRangeVariableConditions == this.whereConditions)
          localStringBuffer1.append("where condition = [");
        else
          localStringBuffer1.append("join condition = [");
      }
      localStringBuffer1.append(arrayOfRangeVariableConditions[k].describe(paramSession, paramInt + 2));
      localStringBuffer1.append(localStringBuffer2).append("]\n");
    }
    if (arrayOfRangeVariableConditions == this.joinConditions)
    {
      localStringBuffer1.append(localStringBuffer2);
      if (this.whereConditions[0].nonIndexCondition != null)
      {
        localStringBuffer1.append("where condition = [");
        localStringBuffer1.append(this.whereConditions[0].nonIndexCondition.describe(paramSession, paramInt));
        localStringBuffer1.append(localStringBuffer2).append("]\n");
        localStringBuffer1.append(localStringBuffer2);
      }
    }
    return localStringBuffer1.toString();
  }

  public RangeIteratorMain getIterator(Session paramSession)
  {
    Object localObject;
    if (this.isRightJoin)
      localObject = new RangeIteratorRight(paramSession, this, null, null);
    else
      localObject = new RangeIteratorMain(paramSession, this, null);
    paramSession.sessionContext.setRangeIterator((RangeIterator)localObject);
    return localObject;
  }

  public static RangeIterator getIterator(Session paramSession, RangeVariable[] paramArrayOfRangeVariable)
  {
    if (paramArrayOfRangeVariable.length == 1)
      return paramArrayOfRangeVariable[0].getIterator(paramSession);
    RangeIteratorMain[] arrayOfRangeIteratorMain = new RangeIteratorMain[paramArrayOfRangeVariable.length];
    for (int i = 0; i < paramArrayOfRangeVariable.length; i++)
      arrayOfRangeIteratorMain[i] = paramArrayOfRangeVariable[i].getIterator(paramSession);
    return new RangeIteratorJoined(arrayOfRangeIteratorMain);
  }

  public static class RangeVariableConditions
  {
    final RangeVariable rangeVar;
    Expression[] indexCond;
    Expression[] indexEndCond;
    int[] opTypes;
    int[] opTypesEnd;
    Expression indexEndCondition;
    int indexedColumnCount;
    Index rangeIndex;
    final boolean isJoin;
    Expression excludeConditions;
    Expression nonIndexCondition;
    Expression terminalCondition;
    int opType;
    int opTypeEnd;
    boolean isFalse;
    boolean reversed;
    boolean hasIndex;

    RangeVariableConditions(RangeVariable paramRangeVariable, boolean paramBoolean)
    {
      this.rangeVar = paramRangeVariable;
      this.isJoin = paramBoolean;
    }

    RangeVariableConditions(RangeVariableConditions paramRangeVariableConditions)
    {
      this.rangeVar = paramRangeVariableConditions.rangeVar;
      this.isJoin = paramRangeVariableConditions.isJoin;
      this.nonIndexCondition = paramRangeVariableConditions.nonIndexCondition;
    }

    boolean hasIndexCondition()
    {
      return this.indexedColumnCount > 0;
    }

    boolean hasIndex()
    {
      return this.hasIndex;
    }

    void addCondition(Expression paramExpression)
    {
      if (paramExpression == null)
        return;
      if (((paramExpression instanceof ExpressionLogical)) && (((ExpressionLogical)paramExpression).isTerminal))
        this.terminalCondition = paramExpression;
      this.nonIndexCondition = ExpressionLogical.andExpressions(this.nonIndexCondition, paramExpression);
      if (Expression.EXPR_FALSE.equals(this.nonIndexCondition))
        this.isFalse = true;
      if ((this.rangeIndex == null) || (this.rangeIndex.getColumnCount() == 0))
        return;
      if (this.indexedColumnCount == 0)
        return;
      if (paramExpression.getIndexableExpression(this.rangeVar) == null)
        return;
      int i = paramExpression.getLeftNode().getColumnIndex();
      int[] arrayOfInt = this.rangeIndex.getColumns();
      switch (paramExpression.getType())
      {
      case 42:
      case 43:
        if (this.opType == 48)
        {
          if (arrayOfInt[(this.indexedColumnCount - 1)] == i)
          {
            this.nonIndexCondition = ExpressionLogical.andExpressions(this.nonIndexCondition, this.indexCond[(this.indexedColumnCount - 1)]);
            this.indexCond[(this.indexedColumnCount - 1)] = paramExpression;
            this.opType = paramExpression.opType;
            this.opTypes[(this.indexedColumnCount - 1)] = paramExpression.opType;
            if ((paramExpression.exprSubType == 53) && (this.indexedColumnCount == 1))
              this.indexEndCond[(this.indexedColumnCount - 1)] = ExpressionLogical.andExpressions(this.indexEndCond[(this.indexedColumnCount - 1)], paramExpression.nodes[2]);
          }
        }
        else
          addToIndexConditions(paramExpression);
        break;
      case 44:
      case 45:
        if ((this.opType == 43) || (this.opType == 42) || (this.opType == 48))
        {
          if ((this.opTypeEnd == 74) && (arrayOfInt[(this.indexedColumnCount - 1)] == i))
          {
            this.indexEndCond[(this.indexedColumnCount - 1)] = paramExpression;
            this.indexEndCondition = ExpressionLogical.andExpressions(this.indexEndCondition, paramExpression);
            this.opTypeEnd = paramExpression.opType;
            this.opTypesEnd[(this.indexedColumnCount - 1)] = paramExpression.opType;
          }
        }
        else
          addToIndexEndConditions(paramExpression);
        break;
      }
    }

    private boolean addToIndexConditions(Expression paramExpression)
    {
      if (((this.opType == 41) || (this.opType == 47)) && (this.indexedColumnCount < this.rangeIndex.getColumnCount()) && (this.rangeIndex.getColumns()[this.indexedColumnCount] == paramExpression.getLeftNode().getColumnIndex()))
      {
        this.indexCond[this.indexedColumnCount] = paramExpression;
        this.opType = paramExpression.opType;
        this.opTypes[this.indexedColumnCount] = paramExpression.opType;
        this.opTypeEnd = 74;
        this.opTypesEnd[this.indexedColumnCount] = 74;
        this.indexedColumnCount += 1;
        return true;
      }
      return false;
    }

    private boolean addToIndexEndConditions(Expression paramExpression)
    {
      if (((this.opType == 41) || (this.opType == 47)) && (this.indexedColumnCount < this.rangeIndex.getColumnCount()) && (this.rangeIndex.getColumns()[this.indexedColumnCount] == paramExpression.getLeftNode().getColumnIndex()))
      {
        ExpressionLogical localExpressionLogical = ExpressionLogical.newNotNullCondition(paramExpression.getLeftNode());
        this.indexCond[this.indexedColumnCount] = localExpressionLogical;
        this.indexEndCond[this.indexedColumnCount] = paramExpression;
        this.indexEndCondition = ExpressionLogical.andExpressions(this.indexEndCondition, paramExpression);
        this.opType = 48;
        this.opTypes[this.indexedColumnCount] = 48;
        this.opTypeEnd = paramExpression.opType;
        this.opTypesEnd[this.indexedColumnCount] = paramExpression.opType;
        this.indexedColumnCount += 1;
        return true;
      }
      return false;
    }

    public void addIndexCondition(Expression[] paramArrayOfExpression, Index paramIndex, int paramInt)
    {
      int i = paramIndex.getColumnCount();
      this.rangeIndex = paramIndex;
      this.indexCond = new Expression[i];
      this.indexEndCond = new Expression[i];
      this.opTypes = new int[i];
      this.opTypesEnd = new int[i];
      this.opType = paramArrayOfExpression[0].opType;
      this.opTypes[0] = paramArrayOfExpression[0].opType;
      switch (this.opType)
      {
      case 48:
        this.indexCond = paramArrayOfExpression;
        this.opTypeEnd = 74;
        this.opTypesEnd[0] = 74;
        break;
      case 42:
      case 43:
        this.indexCond = paramArrayOfExpression;
        if (paramArrayOfExpression[0].exprSubType == 53)
        {
          Expression tmp172_171 = paramArrayOfExpression[0].nodes[2];
          this.indexEndCondition = tmp172_171;
          this.indexEndCond[0] = tmp172_171;
        }
        this.opTypeEnd = 74;
        this.opTypesEnd[0] = 74;
        break;
      case 44:
      case 45:
        Object localObject = paramArrayOfExpression[0].getLeftNode();
        localObject = new ExpressionLogical(47, (Expression)localObject);
        localObject = new ExpressionLogical(48, (Expression)localObject);
        this.indexCond[0] = localObject;
        Expression tmp245_244 = paramArrayOfExpression[0];
        this.indexEndCondition = tmp245_244;
        this.indexEndCond[0] = tmp245_244;
        this.opTypeEnd = this.opType;
        this.opTypesEnd[0] = this.opType;
        this.opType = 48;
        this.opTypes[0] = 48;
        break;
      case 41:
      case 47:
        this.indexCond = paramArrayOfExpression;
        for (int j = 0; j < paramInt; j++)
        {
          Expression localExpression = paramArrayOfExpression[j];
          this.indexEndCond[j] = localExpression;
          this.indexEndCondition = ExpressionLogical.andExpressions(this.indexEndCondition, localExpression);
          this.opType = localExpression.opType;
          this.opTypes[0] = localExpression.opType;
        }
        this.opTypeEnd = this.opType;
        break;
      case 46:
      default:
        Error.runtimeError(201, "RangeVariable");
      }
      this.indexedColumnCount = paramInt;
      this.hasIndex = true;
    }

    public void reverseIndexCondition()
    {
      if ((this.opType == 41) || (this.opType == 47))
        return;
      this.indexEndCondition = null;
      for (int i = 0; i < this.indexedColumnCount; i++)
      {
        Expression localExpression = this.indexCond[i];
        this.indexCond[i] = this.indexEndCond[i];
        this.indexEndCond[i] = localExpression;
        this.indexEndCondition = ExpressionLogical.andExpressions(this.indexEndCondition, localExpression);
      }
      this.opType = this.opTypeEnd;
      this.reversed = true;
    }

    String describe(Session paramSession, int paramInt)
    {
      StringBuffer localStringBuffer1 = new StringBuffer();
      StringBuffer localStringBuffer2 = new StringBuffer(paramInt);
      for (int i = 0; i < paramInt; i++)
        localStringBuffer2.append(' ');
      localStringBuffer1.append("index=").append(this.rangeIndex.getName().name).append("\n");
      String str;
      if (hasIndexCondition())
      {
        if (this.indexedColumnCount > 0)
        {
          localStringBuffer1.append(localStringBuffer2).append("start conditions=[");
          for (i = 0; i < this.indexedColumnCount; i++)
            if ((this.indexCond != null) && (this.indexCond[i] != null))
              localStringBuffer1.append(this.indexCond[i].describe(paramSession, paramInt));
          localStringBuffer1.append("]\n");
        }
        if (this.indexEndCondition != null)
        {
          str = this.indexEndCondition.describe(paramSession, paramInt);
          localStringBuffer1.append(localStringBuffer2).append("end condition=[").append(str).append("]\n");
        }
      }
      if (this.nonIndexCondition != null)
      {
        str = this.nonIndexCondition.describe(paramSession, paramInt);
        localStringBuffer1.append(localStringBuffer2).append("other condition=[").append(str).append("]\n");
      }
      return localStringBuffer1.toString();
    }

    public void replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression)
    {
      int i;
      if (this.indexCond != null)
        for (i = 0; i < this.indexCond.length; i++)
          if (this.indexCond[i] != null)
            this.indexCond[i] = this.indexCond[i].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
      if (this.indexEndCond != null)
        for (i = 0; i < this.indexEndCond.length; i++)
          if (this.indexEndCond[i] != null)
            this.indexEndCond[i] = this.indexEndCond[i].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
      if (this.indexEndCondition != null)
        this.indexEndCondition = this.indexEndCondition.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
      if (this.excludeConditions != null)
        this.excludeConditions = this.excludeConditions.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
      if (this.nonIndexCondition != null)
        this.nonIndexCondition = this.nonIndexCondition.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
      if (this.terminalCondition != null)
        this.terminalCondition = this.terminalCondition.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
    }
  }

  public static class RangeIteratorJoined extends RangeVariable.RangeIteratorBase
  {
    RangeVariable.RangeIteratorMain[] rangeIterators;
    int currentIndex = 0;

    public RangeIteratorJoined(RangeVariable.RangeIteratorMain[] paramArrayOfRangeIteratorMain)
    {
      super();
      this.rangeIterators = paramArrayOfRangeIteratorMain;
      this.isBeforeFirst = true;
    }

    public boolean isBeforeFirst()
    {
      return this.isBeforeFirst;
    }

    public boolean next()
    {
      while (this.currentIndex >= 0)
      {
        RangeVariable.RangeIteratorMain localRangeIteratorMain = this.rangeIterators[this.currentIndex];
        if (localRangeIteratorMain.next())
        {
          if (this.currentIndex < this.rangeIterators.length - 1)
          {
            this.currentIndex += 1;
          }
          else
          {
            this.currentRow = this.rangeIterators[this.currentIndex].currentRow;
            this.currentData = this.currentRow.getData();
            return true;
          }
        }
        else
        {
          localRangeIteratorMain.reset();
          this.currentIndex -= 1;
        }
      }
      this.currentData = this.rangeIterators[(this.rangeIterators.length - 1)].rangeVar.emptyData;
      this.currentRow = null;
      for (int i = 0; i < this.rangeIterators.length; i++)
        this.rangeIterators[i].reset();
      return false;
    }

    public void remove()
    {
    }

    public void release()
    {
      if (this.it != null)
        this.it.release();
      for (int i = 0; i < this.rangeIterators.length; i++)
        this.rangeIterators[i].reset();
    }

    public void reset()
    {
      super.reset();
      for (int i = 0; i < this.rangeIterators.length; i++)
        this.rangeIterators[i].reset();
    }

    public int getRangePosition()
    {
      return 0;
    }
  }

  public static class RangeIteratorRight extends RangeVariable.RangeIteratorMain
  {
    boolean isOnRightOuterRows;

    private RangeIteratorRight(Session paramSession, RangeVariable paramRangeVariable, RangeVariable.RangeIteratorMain paramRangeIteratorMain)
    {
      super(paramRangeVariable, null);
      this.isFullIterator = true;
    }

    public void setOnOuterRows()
    {
      this.conditions = this.rangeVar.whereConditions;
      this.isOnRightOuterRows = true;
      this.hasLeftOuterRow = false;
      this.condIndex = 0;
      initialiseIterator();
    }

    public boolean next()
    {
      if (this.isOnRightOuterRows)
      {
        if (this.it == null)
          return false;
        return findNextRight();
      }
      return super.next();
    }

    private boolean findNextRight()
    {
      boolean bool = false;
      do
      {
        this.currentRow = this.it.getNextRow();
        if (this.currentRow == null)
          break;
        this.currentData = this.currentRow.getData();
        if ((this.conditions[this.condIndex].indexEndCondition != null) && (!this.conditions[this.condIndex].indexEndCondition.testCondition(this.session)))
          break;
      }
      while (((this.conditions[this.condIndex].nonIndexCondition != null) && (!this.conditions[this.condIndex].nonIndexCondition.testCondition(this.session))) || (!lookupAndTest()));
      bool = true;
      if (bool)
        return true;
      this.it.release();
      this.currentRow = null;
      this.currentData = this.rangeVar.emptyData;
      return bool;
    }

    private boolean lookupAndTest()
    {
      boolean bool = !this.lookup.contains(this.currentRow.getPos());
      if (bool)
      {
        this.currentData = this.currentRow.getData();
        if ((this.conditions[this.condIndex].nonIndexCondition != null) && (!this.conditions[this.condIndex].nonIndexCondition.testCondition(this.session)))
          bool = false;
      }
      return bool;
    }
  }

  public static class RangeIteratorMain extends RangeVariable.RangeIteratorBase
  {
    boolean hasLeftOuterRow;
    boolean isFullIterator;
    RangeVariable.RangeVariableConditions[] conditions;
    RangeVariable.RangeVariableConditions[] whereConditions;
    RangeVariable.RangeVariableConditions[] joinConditions;
    int condIndex = 0;
    OrderedLongHashSet lookup;
    Object[] currentJoinData = null;

    RangeIteratorMain()
    {
      super();
    }

    private RangeIteratorMain(Session paramSession, RangeVariable paramRangeVariable)
    {
      super();
      this.rangePosition = paramRangeVariable.rangePosition;
      this.store = paramRangeVariable.rangeTable.getRowStore(paramSession);
      this.session = paramSession;
      this.rangeVar = paramRangeVariable;
      this.currentData = paramRangeVariable.emptyData;
      this.isBeforeFirst = true;
      this.whereConditions = paramRangeVariable.whereConditions;
      this.joinConditions = paramRangeVariable.joinConditions;
      if (paramRangeVariable.isRightJoin)
        this.lookup = new OrderedLongHashSet();
      this.conditions = paramRangeVariable.joinConditions;
      if (paramRangeVariable.whereConditions[0].hasIndexCondition())
        this.conditions = paramRangeVariable.whereConditions;
    }

    public boolean isBeforeFirst()
    {
      return this.isBeforeFirst;
    }

    public boolean next()
    {
      while (this.condIndex < this.conditions.length)
      {
        if (this.isBeforeFirst)
        {
          this.isBeforeFirst = false;
          initialiseIterator();
        }
        boolean bool = findNext();
        if (bool)
          return true;
        reset();
        this.condIndex += 1;
      }
      this.condIndex = 0;
      return false;
    }

    public void remove()
    {
    }

    public void reset()
    {
      if (this.it != null)
        this.it.release();
      this.it = null;
      this.currentData = this.rangeVar.emptyData;
      this.currentRow = null;
      this.isBeforeFirst = true;
    }

    public int getRangePosition()
    {
      return this.rangeVar.rangePosition;
    }

    protected void initialiseIterator()
    {
      if (this.condIndex == 0)
        this.hasLeftOuterRow = this.rangeVar.isLeftJoin;
      if (this.conditions[this.condIndex].isFalse)
      {
        this.it = this.conditions[this.condIndex].rangeIndex.emptyIterator();
        return;
      }
      this.rangeVar.rangeTable.materialiseCorrelated(this.session);
      if (this.conditions[this.condIndex].indexCond == null)
      {
        if (this.conditions[this.condIndex].reversed)
          this.it = this.conditions[this.condIndex].rangeIndex.lastRow(this.session, this.store);
        else
          this.it = this.conditions[this.condIndex].rangeIndex.firstRow(this.session, this.store);
      }
      else
      {
        getFirstRow();
        if (!this.conditions[this.condIndex].isJoin)
          this.hasLeftOuterRow = false;
      }
    }

    private void getFirstRow()
    {
      if ((this.currentJoinData == null) || (this.currentJoinData.length < this.conditions[this.condIndex].indexedColumnCount))
        this.currentJoinData = new Object[this.conditions[this.condIndex].indexedColumnCount];
      for (int i = 0; i < this.conditions[this.condIndex].indexedColumnCount; i++)
      {
        int j = 0;
        int k = i == this.conditions[this.condIndex].indexedColumnCount - 1 ? this.conditions[this.condIndex].opType : this.conditions[this.condIndex].indexCond[i].getType();
        if ((k == 47) || (k == 48) || (k == 74))
        {
          this.currentJoinData[i] = null;
        }
        else
        {
          Type localType1 = this.conditions[this.condIndex].indexCond[i].getRightNode().getDataType();
          Object localObject = this.conditions[this.condIndex].indexCond[i].getRightNode().getValue(this.session);
          Type localType2 = this.conditions[this.condIndex].indexCond[i].getLeftNode().getDataType();
          if (localType2 != localType1)
          {
            j = localType2.compareToTypeRange(localObject);
            if ((j == 0) && (localType2.typeComparisonGroup != localType1.typeComparisonGroup))
              localObject = localType2.convertToType(this.session, localObject, localType1);
          }
          if (i == 0)
          {
            int m = this.conditions[this.condIndex].indexCond[0].getType();
            if (j < 0)
              switch (m)
              {
              case 42:
              case 43:
                localObject = null;
                break;
              default:
                this.it = this.conditions[this.condIndex].rangeIndex.emptyIterator();
                return;
              }
            else if (j > 0)
              switch (m)
              {
              case 48:
                localObject = null;
                break;
              default:
                this.it = this.conditions[this.condIndex].rangeIndex.emptyIterator();
                return;
              }
          }
          this.currentJoinData[i] = localObject;
        }
      }
      this.it = this.conditions[this.condIndex].rangeIndex.findFirstRow(this.session, this.store, this.currentJoinData, this.conditions[this.condIndex].indexedColumnCount, this.rangeVar.indexDistinctCount, this.conditions[this.condIndex].opType, this.conditions[this.condIndex].reversed, null);
    }

    private boolean findNext()
    {
      boolean bool = false;
      Expression localExpression;
      do
      {
        while (true)
        {
          this.currentRow = this.it.getNextRow();
          if (this.currentRow == null)
            break label267;
          this.currentData = this.currentRow.getData();
          if ((this.conditions[this.condIndex].terminalCondition != null) && (!this.conditions[this.condIndex].terminalCondition.testCondition(this.session)))
            break label267;
          if ((this.conditions[this.condIndex].indexEndCondition != null) && (!this.conditions[this.condIndex].indexEndCondition.testCondition(this.session)))
          {
            if (this.conditions[this.condIndex].isJoin)
              break label267;
            this.hasLeftOuterRow = false;
            break label267;
          }
          if ((this.joinConditions[this.condIndex].nonIndexCondition == null) || (this.joinConditions[this.condIndex].nonIndexCondition.testCondition(this.session)))
          {
            if ((this.whereConditions[this.condIndex].nonIndexCondition == null) || (this.whereConditions[this.condIndex].nonIndexCondition.testCondition(this.session)))
              break;
            this.hasLeftOuterRow = false;
            addFoundRow();
          }
        }
        localExpression = this.conditions[this.condIndex].excludeConditions;
      }
      while ((localExpression != null) && (localExpression.testCondition(this.session)));
      addFoundRow();
      this.hasLeftOuterRow = false;
      return true;
      label267: this.it.release();
      this.currentRow = null;
      this.currentData = this.rangeVar.emptyData;
      if ((this.hasLeftOuterRow) && (this.condIndex == this.conditions.length - 1))
      {
        bool = (this.whereConditions[this.condIndex].nonIndexCondition == null) || (this.whereConditions[this.condIndex].nonIndexCondition.testCondition(this.session));
        this.hasLeftOuterRow = false;
      }
      return bool;
    }

    private void addFoundRow()
    {
      if (this.rangeVar.isRightJoin)
        this.lookup.add(this.currentRow.getPos());
    }
  }

  public static class RangeIteratorBase
    implements RangeIterator
  {
    Session session;
    int rangePosition;
    RowIterator it;
    PersistentStore store;
    Object[] currentData;
    Row currentRow;
    boolean isBeforeFirst;
    RangeVariable rangeVar;

    public boolean isBeforeFirst()
    {
      return this.isBeforeFirst;
    }

    public boolean next()
    {
      if (this.isBeforeFirst)
        this.isBeforeFirst = false;
      else if (this.it == null)
        return false;
      this.currentRow = this.it.getNextRow();
      if (this.currentRow == null)
        return false;
      this.currentData = this.currentRow.getData();
      return true;
    }

    public Row getCurrentRow()
    {
      return this.currentRow;
    }

    public Object[] getCurrent()
    {
      return this.currentData;
    }

    public Object getCurrent(int paramInt)
    {
      return this.currentData == null ? null : this.currentData[paramInt];
    }

    public void setCurrent(Object[] paramArrayOfObject)
    {
      this.currentData = paramArrayOfObject;
    }

    public long getRowId()
    {
      return this.currentRow == null ? 0L : (this.rangeVar.rangeTable.getId() << 32) + this.currentRow.getPos();
    }

    public Object getRowidObject()
    {
      return this.currentRow == null ? null : ValuePool.getLong(getRowId());
    }

    public void remove()
    {
    }

    public void reset()
    {
      if (this.it != null)
        this.it.release();
      this.it = null;
      this.currentRow = null;
      this.isBeforeFirst = true;
    }

    public int getRangePosition()
    {
      return this.rangePosition;
    }

    public Row getNextRow()
    {
      throw Error.runtimeError(201, "RangeVariable");
    }

    public boolean hasNext()
    {
      throw Error.runtimeError(201, "RangeVariable");
    }

    public Object[] getNext()
    {
      throw Error.runtimeError(201, "RangeVariable");
    }

    public boolean setRowColumns(boolean[] paramArrayOfBoolean)
    {
      throw Error.runtimeError(201, "RangeVariable");
    }

    public void release()
    {
      if (this.it != null)
        this.it.release();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.RangeVariable
 * JD-Core Version:    0.6.2
 */