package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.navigator.RowSetNavigatorDataTable;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Type;

public class QueryExpression
  implements RangeGroup
{
  public static final int NOUNION = 0;
  public static final int UNION = 1;
  public static final int UNION_ALL = 2;
  public static final int INTERSECT = 3;
  public static final int INTERSECT_ALL = 4;
  public static final int EXCEPT_ALL = 5;
  public static final int EXCEPT = 6;
  public static final int UNION_TERM = 7;
  int columnCount;
  private QueryExpression leftQueryExpression;
  private QueryExpression rightQueryExpression;
  SortAndSlice sortAndSlice;
  private int unionType;
  private boolean unionCorresponding;
  private OrderedHashSet unionCorrespondingColumns;
  int[] unionColumnMap;
  Type[] unionColumnTypes;
  boolean isFullOrder;
  HsqlList unresolvedExpressions;
  boolean isReferencesResolved;
  boolean isPartOneResolved;
  boolean isPartTwoResolved;
  boolean isResolved;
  int persistenceScope = 21;
  ResultMetaData resultMetaData;
  boolean[] accessibleColumns;
  View view;
  boolean isBaseMergeable;
  boolean isMergeable;
  boolean isUpdatable;
  boolean isInsertable;
  boolean isCheckable;
  boolean isTopLevel;
  boolean isRecursive;
  boolean isSingleRow;
  boolean acceptsSequences;
  boolean isCorrelated;
  boolean isTable;
  boolean isValueList;
  TableDerived recursiveTable;
  public TableBase resultTable;
  public Index mainIndex;
  public Index fullIndex;
  public Index orderIndex;
  public Index idIndex;
  ParserDQL.CompileContext compileContext;
  
  QueryExpression(ParserDQL.CompileContext paramCompileContext)
  {
    this.compileContext = paramCompileContext;
    this.sortAndSlice = SortAndSlice.noSort;
  }
  
  public QueryExpression(ParserDQL.CompileContext paramCompileContext, QueryExpression paramQueryExpression)
  {
    this(paramCompileContext);
    this.sortAndSlice = SortAndSlice.noSort;
    this.leftQueryExpression = paramQueryExpression;
  }
  
  public RangeVariable[] getRangeVariables()
  {
    return RangeVariable.emptyArray;
  }
  
  public void setCorrelated()
  {
    this.isCorrelated = true;
  }
  
  public void setSingleRow()
  {
    this.isSingleRow = true;
  }
  
  public boolean isRecursive()
  {
    return this.isRecursive;
  }
  
  void addUnion(QueryExpression paramQueryExpression, int paramInt)
  {
    this.sortAndSlice = SortAndSlice.noSort;
    this.rightQueryExpression = paramQueryExpression;
    this.unionType = paramInt;
    setFullOrder();
  }
  
  void addSortAndSlice(SortAndSlice paramSortAndSlice)
  {
    this.sortAndSlice = paramSortAndSlice;
    paramSortAndSlice.sortUnion = true;
  }
  
  public void setUnionCorresoponding()
  {
    this.unionCorresponding = true;
  }
  
  public void setUnionCorrespondingColumns(OrderedHashSet paramOrderedHashSet)
  {
    this.unionCorrespondingColumns = paramOrderedHashSet;
  }
  
  public void setFullOrder()
  {
    this.isFullOrder = true;
    if (this.leftQueryExpression != null) {
      this.leftQueryExpression.setFullOrder();
    }
    if (this.rightQueryExpression != null) {
      this.rightQueryExpression.setFullOrder();
    }
  }
  
  public void resolve(Session paramSession)
  {
    resolveReferences(paramSession, RangeGroup.emptyArray);
    ExpressionColumn.checkColumnsResolved(this.unresolvedExpressions);
    resolveTypes(paramSession);
  }
  
  public void resolve(Session paramSession, RangeGroup[] paramArrayOfRangeGroup, Type[] paramArrayOfType)
  {
    resolveReferences(paramSession, paramArrayOfRangeGroup);
    int i;
    if (this.unresolvedExpressions != null) {
      for (i = 0; i < this.unresolvedExpressions.size(); i++)
      {
        Expression localExpression = (Expression)this.unresolvedExpressions.get(i);
        HsqlList localHsqlList = localExpression.resolveColumnReferences(paramSession, RangeGroup.emptyGroup, paramArrayOfRangeGroup, null);
        ExpressionColumn.checkColumnsResolved(localHsqlList);
      }
    }
    resolveTypesPartOne(paramSession);
    if (paramArrayOfType != null) {
      for (i = 0; (i < this.unionColumnTypes.length) && (i < paramArrayOfType.length); i++) {
        if (this.unionColumnTypes[i] == null) {
          this.unionColumnTypes[i] = paramArrayOfType[i];
        }
      }
    }
    resolveTypesPartTwo(paramSession);
    resolveTypesPartThree(paramSession);
  }
  
  public void resolveReferences(Session paramSession, RangeGroup[] paramArrayOfRangeGroup)
  {
    if (this.isReferencesResolved) {
      return;
    }
    this.leftQueryExpression.resolveReferences(paramSession, paramArrayOfRangeGroup);
    this.rightQueryExpression.resolveReferences(paramSession, paramArrayOfRangeGroup);
    addUnresolvedExpressions(this.leftQueryExpression.unresolvedExpressions);
    addUnresolvedExpressions(this.rightQueryExpression.unresolvedExpressions);
    if ((this.leftQueryExpression.isCorrelated) || (this.rightQueryExpression.isCorrelated)) {
      setCorrelated();
    }
    if (!this.unionCorresponding)
    {
      this.columnCount = this.leftQueryExpression.getColumnCount();
      int i = this.rightQueryExpression.getColumnCount();
      if (this.columnCount != i) {
        throw Error.error(5594);
      }
      this.unionColumnTypes = new Type[this.columnCount];
      this.leftQueryExpression.unionColumnMap = (this.rightQueryExpression.unionColumnMap = new int[this.columnCount]);
      ArrayUtil.fillSequence(this.leftQueryExpression.unionColumnMap);
      resolveColumnRefernecesInUnionOrderBy();
      this.accessibleColumns = this.leftQueryExpression.accessibleColumns;
      this.isReferencesResolved = true;
      return;
    }
    String[] arrayOfString1 = this.leftQueryExpression.getColumnNames();
    String[] arrayOfString2 = this.rightQueryExpression.getColumnNames();
    Object localObject;
    int k;
    if (this.unionCorrespondingColumns == null)
    {
      this.unionCorrespondingColumns = new OrderedHashSet();
      OrderedIntHashSet localOrderedIntHashSet = new OrderedIntHashSet();
      localObject = new OrderedIntHashSet();
      for (k = 0; k < arrayOfString1.length; k++)
      {
        String str = arrayOfString1[k];
        int m = ArrayUtil.find(arrayOfString2, str);
        if ((str.length() > 0) && (m != -1))
        {
          if (this.leftQueryExpression.accessibleColumns[k] == 0) {
            throw Error.error(5578);
          }
          if (this.rightQueryExpression.accessibleColumns[m] == 0) {
            throw Error.error(5578);
          }
          localOrderedIntHashSet.add(k);
          ((OrderedIntHashSet)localObject).add(m);
          this.unionCorrespondingColumns.add(str);
        }
      }
      if (this.unionCorrespondingColumns.isEmpty()) {
        throw Error.error(5578);
      }
      this.leftQueryExpression.unionColumnMap = localOrderedIntHashSet.toArray();
      this.rightQueryExpression.unionColumnMap = ((OrderedIntHashSet)localObject).toArray();
    }
    else
    {
      this.leftQueryExpression.unionColumnMap = new int[this.unionCorrespondingColumns.size()];
      this.rightQueryExpression.unionColumnMap = new int[this.unionCorrespondingColumns.size()];
      for (int j = 0; j < this.unionCorrespondingColumns.size(); j++)
      {
        localObject = (String)this.unionCorrespondingColumns.get(j);
        k = ArrayUtil.find(arrayOfString1, localObject);
        if (k == -1) {
          throw Error.error(5501);
        }
        if (this.leftQueryExpression.accessibleColumns[k] == 0) {
          throw Error.error(5578);
        }
        this.leftQueryExpression.unionColumnMap[j] = k;
        k = ArrayUtil.find(arrayOfString2, localObject);
        if (k == -1) {
          throw Error.error(5501);
        }
        if (this.rightQueryExpression.accessibleColumns[k] == 0) {
          throw Error.error(5578);
        }
        this.rightQueryExpression.unionColumnMap[j] = k;
      }
    }
    this.columnCount = this.unionCorrespondingColumns.size();
    this.unionColumnTypes = new Type[this.columnCount];
    resolveColumnRefernecesInUnionOrderBy();
    this.accessibleColumns = new boolean[this.columnCount];
    ArrayUtil.fillArray(this.accessibleColumns, true);
    this.isReferencesResolved = true;
  }
  
  void resolveColumnRefernecesInUnionOrderBy()
  {
    int i = this.sortAndSlice.getOrderLength();
    if (i == 0) {
      return;
    }
    String[] arrayOfString = getColumnNames();
    for (int j = 0; j < i; j++)
    {
      Expression localExpression1 = (Expression)this.sortAndSlice.exprList.get(j);
      Expression localExpression2 = localExpression1.getLeftNode();
      int k;
      if (localExpression2.getType() == 1)
      {
        if (localExpression2.getDataType().typeCode == 4)
        {
          k = ((Integer)localExpression2.getValue(null)).intValue();
          if ((0 < k) && (k <= arrayOfString.length))
          {
            localExpression1.getLeftNode().queryTableColumnIndex = (k - 1);
            continue;
          }
        }
      }
      else if (localExpression2.getType() == 2)
      {
        k = ArrayUtil.find(arrayOfString, localExpression2.getColumnName());
        if (k >= 0)
        {
          localExpression1.getLeftNode().queryTableColumnIndex = k;
          continue;
        }
      }
      throw Error.error(5576);
    }
    this.sortAndSlice.prepare(null);
  }
  
  private void addUnresolvedExpressions(HsqlList paramHsqlList)
  {
    if (paramHsqlList == null) {
      return;
    }
    if (this.unresolvedExpressions == null) {
      this.unresolvedExpressions = new ArrayListIdentity();
    }
    this.unresolvedExpressions.addAll(paramHsqlList);
  }
  
  public void resolveTypes(Session paramSession)
  {
    if (this.isResolved) {
      return;
    }
    resolveTypesPartOne(paramSession);
    resolveTypesPartTwo(paramSession);
    resolveTypesPartThree(paramSession);
  }
  
  void resolveTypesPartOne(Session paramSession)
  {
    if (this.isPartOneResolved) {
      return;
    }
    ArrayUtil.projectRowReverse(this.leftQueryExpression.unionColumnTypes, this.leftQueryExpression.unionColumnMap, this.unionColumnTypes);
    this.leftQueryExpression.resolveTypesPartOne(paramSession);
    ArrayUtil.projectRow(this.leftQueryExpression.unionColumnTypes, this.leftQueryExpression.unionColumnMap, this.unionColumnTypes);
    ArrayUtil.projectRowReverse(this.rightQueryExpression.unionColumnTypes, this.rightQueryExpression.unionColumnMap, this.unionColumnTypes);
    this.rightQueryExpression.resolveTypesPartOne(paramSession);
    ArrayUtil.projectRow(this.rightQueryExpression.unionColumnTypes, this.rightQueryExpression.unionColumnMap, this.unionColumnTypes);
    this.isPartOneResolved = true;
  }
  
  void resolveTypesPartTwo(Session paramSession)
  {
    if (this.isPartTwoResolved) {
      return;
    }
    ArrayUtil.projectRowReverse(this.leftQueryExpression.unionColumnTypes, this.leftQueryExpression.unionColumnMap, this.unionColumnTypes);
    this.leftQueryExpression.resolveTypesPartTwo(paramSession);
    this.leftQueryExpression.resolveTypesPartThree(paramSession);
    ArrayUtil.projectRowReverse(this.rightQueryExpression.unionColumnTypes, this.rightQueryExpression.unionColumnMap, this.unionColumnTypes);
    this.rightQueryExpression.resolveTypesPartTwo(paramSession);
    this.rightQueryExpression.resolveTypesPartThree(paramSession);
    ResultMetaData localResultMetaData1 = this.leftQueryExpression.getMetaData();
    ResultMetaData localResultMetaData2 = this.rightQueryExpression.getMetaData();
    for (int i = 0; i < this.leftQueryExpression.unionColumnMap.length; i++)
    {
      int j = this.leftQueryExpression.unionColumnMap[i];
      int k = this.rightQueryExpression.unionColumnMap[i];
      ColumnBase localColumnBase = localResultMetaData1.columns[j];
      byte b1 = localResultMetaData1.columns[j].getNullability();
      byte b2 = localResultMetaData2.columns[k].getNullability();
      if (((localColumnBase instanceof ColumnSchema)) && ((localResultMetaData2.columns[k] instanceof ColumnBase)))
      {
        localColumnBase = new ColumnBase();
        localColumnBase.setType(this.leftQueryExpression.unionColumnTypes[i]);
        localColumnBase.setNullability(b1);
        localResultMetaData1.columns[j] = localColumnBase;
      }
      if ((b2 == 1) || ((b2 == 2) && (b1 == 0)))
      {
        if ((localColumnBase instanceof ColumnSchema))
        {
          localColumnBase = new ColumnBase();
          localColumnBase.setType(this.leftQueryExpression.unionColumnTypes[i]);
          localResultMetaData1.columns[j] = localColumnBase;
        }
        localColumnBase.setNullability(b2);
      }
    }
    if ((this.unionCorresponding) || (this.isRecursive))
    {
      this.resultMetaData = this.leftQueryExpression.getMetaData().getNewMetaData(this.leftQueryExpression.unionColumnMap);
      createTable(paramSession);
    }
    if (this.sortAndSlice.hasOrder()) {
      for (QueryExpression localQueryExpression = this;; localQueryExpression = localQueryExpression.leftQueryExpression) {
        if ((localQueryExpression.leftQueryExpression == null) || (localQueryExpression.unionCorresponding))
        {
          this.sortAndSlice.setIndex(paramSession, localQueryExpression.resultTable);
          break;
        }
      }
    }
    this.isPartTwoResolved = true;
  }
  
  void resolveTypesPartThree(Session paramSession)
  {
    this.compileContext = null;
    this.isResolved = true;
  }
  
  public Object[] getValues(Session paramSession)
  {
    Result localResult = getResult(paramSession, 2);
    int i = localResult.getNavigator().getSize();
    if (i == 0) {
      return new Object[localResult.metaData.getColumnCount()];
    }
    if (i == 1) {
      return localResult.getSingleRowData();
    }
    throw Error.error(3201);
  }
  
  public void addExtraConditions(Expression paramExpression) {}
  
  public Object[] getSingleRowValues(Session paramSession)
  {
    Result localResult = getResult(paramSession, 2);
    int i = localResult.getNavigator().getSize();
    if (i == 0) {
      return null;
    }
    if (i == 1) {
      return localResult.getSingleRowData();
    }
    throw Error.error(3201);
  }
  
  public Object getValue(Session paramSession)
  {
    Object[] arrayOfObject = getValues(paramSession);
    return arrayOfObject[0];
  }
  
  Result getResult(Session paramSession, int paramInt)
  {
    if (this.isRecursive) {
      return getResultRecursive(paramSession);
    }
    int i = this.unionType == 2 ? paramInt : 0;
    Result localResult1 = this.leftQueryExpression.getResult(paramSession, i);
    Object localObject1 = (RowSetNavigatorData)localResult1.getNavigator();
    Result localResult2 = this.rightQueryExpression.getResult(paramSession, i);
    Object localObject2 = (RowSetNavigatorData)localResult2.getNavigator();
    Object localObject3;
    if (this.unionCorresponding)
    {
      int j = (paramSession.resultMaxMemoryRows == 0) || ((((RowSetNavigatorData)localObject1).getSize() < paramSession.resultMaxMemoryRows) && (((RowSetNavigatorData)localObject2).getSize() < paramSession.resultMaxMemoryRows)) ? 1 : 0;
      if (j != 0) {
        localObject3 = new RowSetNavigatorData(paramSession, this);
      } else {
        localObject3 = new RowSetNavigatorDataTable(paramSession, this);
      }
      ((RowSetNavigatorData)localObject3).copy((RowIterator)localObject1, this.leftQueryExpression.unionColumnMap);
      ((RowSetNavigatorData)localObject1).release();
      localObject1 = localObject3;
      localResult1.setNavigator((RowSetNavigator)localObject1);
      localResult1.metaData = getMetaData();
      if (j != 0) {
        localObject3 = new RowSetNavigatorData(paramSession, this);
      } else {
        localObject3 = new RowSetNavigatorDataTable(paramSession, this);
      }
      ((RowSetNavigatorData)localObject3).copy((RowIterator)localObject2, this.rightQueryExpression.unionColumnMap);
      ((RowSetNavigatorData)localObject2).release();
      localObject2 = localObject3;
    }
    switch (this.unionType)
    {
    case 1: 
      ((RowSetNavigatorData)localObject1).union(paramSession, (RowSetNavigatorData)localObject2);
      break;
    case 2: 
      ((RowSetNavigatorData)localObject1).unionAll(paramSession, (RowSetNavigatorData)localObject2);
      break;
    case 3: 
      ((RowSetNavigatorData)localObject1).intersect(paramSession, (RowSetNavigatorData)localObject2);
      break;
    case 4: 
      ((RowSetNavigatorData)localObject1).intersectAll(paramSession, (RowSetNavigatorData)localObject2);
      break;
    case 6: 
      ((RowSetNavigatorData)localObject1).except(paramSession, (RowSetNavigatorData)localObject2);
      break;
    case 5: 
      ((RowSetNavigatorData)localObject1).exceptAll(paramSession, (RowSetNavigatorData)localObject2);
      break;
    default: 
      throw Error.runtimeError(201, "QueryExpression");
    }
    if (this.sortAndSlice.hasOrder()) {
      ((RowSetNavigatorData)localObject1).sortOrderUnion(paramSession, this.sortAndSlice);
    }
    if (this.sortAndSlice.hasLimit())
    {
      localObject3 = this.sortAndSlice.getLimits(paramSession, this, paramInt);
      ((RowSetNavigatorData)localObject1).trim(localObject3[0], localObject3[1]);
    }
    ((RowSetNavigatorData)localObject1).reset();
    return localResult1;
  }
  
  Result getResultRecursive(Session paramSession)
  {
    RowSetNavigatorData localRowSetNavigatorData1 = new RowSetNavigatorData(paramSession, this);
    Result localResult2 = Result.newResult(localRowSetNavigatorData1);
    this.recursiveTable.materialise(paramSession);
    RowIterator localRowIterator = this.recursiveTable.rowIterator(paramSession);
    localRowSetNavigatorData1.copy(localRowIterator, this.unionColumnMap);
    localResult2.metaData = this.resultMetaData;
    for (int i = 0;; i++)
    {
      Result localResult1 = this.rightQueryExpression.getResult(paramSession, 0);
      RowSetNavigatorData localRowSetNavigatorData2 = (RowSetNavigatorData)localResult1.getNavigator();
      if (localRowSetNavigatorData2.isEmpty()) {
        break;
      }
      switch (this.unionType)
      {
      case 1: 
        localRowSetNavigatorData1.union(paramSession, localRowSetNavigatorData2);
        break;
      case 2: 
        localRowSetNavigatorData1.unionAll(paramSession, localRowSetNavigatorData2);
        break;
      default: 
        throw Error.runtimeError(201, "QueryExpression");
      }
      this.recursiveTable.clearAllData(paramSession);
      localRowSetNavigatorData2.reset();
      this.recursiveTable.insertIntoTable(paramSession, localResult1);
      if (i > 256) {
        throw Error.error(458);
      }
    }
    this.recursiveTable.clearAllData(paramSession);
    localRowSetNavigatorData1.reset();
    return localResult2;
  }
  
  public OrderedHashSet getSubqueries()
  {
    OrderedHashSet localOrderedHashSet = this.leftQueryExpression.getSubqueries();
    localOrderedHashSet = OrderedHashSet.addAll(localOrderedHashSet, this.rightQueryExpression.getSubqueries());
    return localOrderedHashSet;
  }
  
  public boolean isSingleColumn()
  {
    return this.leftQueryExpression.isSingleColumn();
  }
  
  public ResultMetaData getMetaData()
  {
    if (this.resultMetaData != null) {
      return this.resultMetaData;
    }
    return this.leftQueryExpression.getMetaData();
  }
  
  public QuerySpecification getMainSelect()
  {
    if (this.leftQueryExpression == null) {
      return (QuerySpecification)this;
    }
    return this.leftQueryExpression.getMainSelect();
  }
  
  public String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer2 = new StringBuffer(paramInt);
    for (int i = 0; i < paramInt; i++) {
      localStringBuffer2.append(' ');
    }
    StringBuffer localStringBuffer1 = new StringBuffer();
    String str;
    switch (this.unionType)
    {
    case 1: 
      str = "UNION";
      break;
    case 2: 
      str = "UNION ALL";
      break;
    case 3: 
      str = "INTERSECT";
      break;
    case 4: 
      str = "INTERSECT ALL";
      break;
    case 6: 
      str = "EXCEPT";
      break;
    case 5: 
      str = "EXCEPT ALL";
      break;
    default: 
      throw Error.runtimeError(201, "QueryExpression");
    }
    localStringBuffer1.append(localStringBuffer2).append(str).append("\n");
    localStringBuffer1.append(localStringBuffer2).append("Left Query=[\n");
    localStringBuffer1.append(localStringBuffer2).append(this.leftQueryExpression.describe(paramSession, paramInt + 2));
    localStringBuffer1.append(localStringBuffer2).append("]\n");
    localStringBuffer1.append(localStringBuffer2).append("Right Query=[\n");
    localStringBuffer1.append(localStringBuffer2).append(this.rightQueryExpression.describe(paramSession, paramInt + 2));
    localStringBuffer1.append(localStringBuffer2).append("]\n");
    return localStringBuffer1.toString();
  }
  
  public HsqlList getUnresolvedExpressions()
  {
    return this.unresolvedExpressions;
  }
  
  public boolean areColumnsResolved()
  {
    if ((this.unresolvedExpressions == null) || (this.unresolvedExpressions.isEmpty())) {
      return true;
    }
    for (int i = 0; i < this.unresolvedExpressions.size(); i++)
    {
      Expression localExpression = (Expression)this.unresolvedExpressions.get(i);
      if (localExpression.getRangeVariable() == null) {
        return false;
      }
      if (localExpression.getRangeVariable().rangeType == 1) {
        return false;
      }
    }
    return true;
  }
  
  String[] getColumnNames()
  {
    if (this.unionCorrespondingColumns == null) {
      return this.leftQueryExpression.getColumnNames();
    }
    String[] arrayOfString = new String[this.unionCorrespondingColumns.size()];
    this.unionCorrespondingColumns.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public Type[] getColumnTypes()
  {
    return this.unionColumnTypes;
  }
  
  public int getColumnCount()
  {
    if (this.unionCorrespondingColumns == null)
    {
      int i = this.leftQueryExpression.getColumnCount();
      int j = this.rightQueryExpression.getColumnCount();
      if (i != j) {
        throw Error.error(5594);
      }
      return i;
    }
    return this.unionCorrespondingColumns.size();
  }
  
  public OrderedHashSet collectAllExpressions(OrderedHashSet paramOrderedHashSet, OrderedIntHashSet paramOrderedIntHashSet1, OrderedIntHashSet paramOrderedIntHashSet2)
  {
    paramOrderedHashSet = this.leftQueryExpression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2);
    if (this.rightQueryExpression != null) {
      paramOrderedHashSet = this.rightQueryExpression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2);
    }
    return paramOrderedHashSet;
  }
  
  OrderedHashSet collectRangeVariables(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet)
  {
    paramOrderedHashSet = this.leftQueryExpression.collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet);
    if (this.rightQueryExpression != null) {
      paramOrderedHashSet = this.rightQueryExpression.collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet);
    }
    return paramOrderedHashSet;
  }
  
  public void collectObjectNames(Set paramSet)
  {
    this.leftQueryExpression.collectObjectNames(paramSet);
    if (this.rightQueryExpression != null) {
      this.rightQueryExpression.collectObjectNames(paramSet);
    }
  }
  
  public HashMappedList getColumns()
  {
    getResultTable();
    return ((TableDerived)getResultTable()).columnList;
  }
  
  public void setView(View paramView)
  {
    this.view = paramView;
    this.isUpdatable = true;
    this.acceptsSequences = true;
    this.isTopLevel = true;
  }
  
  public void setTableColumnNames(HashMappedList paramHashMappedList)
  {
    if (this.resultTable != null)
    {
      ((TableDerived)this.resultTable).columnList = paramHashMappedList;
      return;
    }
    this.leftQueryExpression.setTableColumnNames(paramHashMappedList);
  }
  
  void createTable(Session paramSession)
  {
    createResultTable(paramSession);
    this.mainIndex = this.resultTable.getPrimaryIndex();
    if (this.sortAndSlice.hasOrder()) {
      this.orderIndex = this.sortAndSlice.getNewIndex(paramSession, this.resultTable);
    }
    int[] arrayOfInt = new int[this.columnCount];
    ArrayUtil.fillSequence(arrayOfInt);
    this.fullIndex = this.resultTable.createAndAddIndexStructure(null, arrayOfInt, null, null, false, false, false);
    this.resultTable.fullIndex = this.fullIndex;
  }
  
  void createResultTable(Session paramSession)
  {
    HsqlNameManager.HsqlName localHsqlName = paramSession.database.nameManager.getSubqueryTableName();
    int i = this.persistenceScope == 21 ? 2 : 9;
    HashMappedList localHashMappedList = this.leftQueryExpression.getUnionColumns();
    try
    {
      this.resultTable = new TableDerived(paramSession.database, localHsqlName, i, this.unionColumnTypes, localHashMappedList, ValuePool.emptyIntArray);
    }
    catch (Exception localException) {}
  }
  
  public void setColumnsDefined()
  {
    if (this.leftQueryExpression != null) {
      this.leftQueryExpression.setColumnsDefined();
    }
  }
  
  public void setReturningResult()
  {
    if (this.compileContext.getSequences().length > 0) {
      throw Error.error(5598);
    }
    this.isTopLevel = true;
    setReturningResultSet();
  }
  
  void setReturningResultSet()
  {
    if (this.unionCorresponding)
    {
      this.persistenceScope = 23;
      return;
    }
    this.leftQueryExpression.setReturningResultSet();
  }
  
  private HashMappedList getUnionColumns()
  {
    if ((this.unionCorresponding) || (this.leftQueryExpression == null))
    {
      HashMappedList localHashMappedList1 = ((TableDerived)this.resultTable).columnList;
      HashMappedList localHashMappedList2 = new HashMappedList();
      for (int i = 0; i < this.unionColumnMap.length; i++)
      {
        ColumnSchema localColumnSchema = (ColumnSchema)localHashMappedList1.get(this.unionColumnMap[i]);
        localHashMappedList2.add(localColumnSchema.getName().name, localColumnSchema);
      }
      return localHashMappedList2;
    }
    return this.leftQueryExpression.getUnionColumns();
  }
  
  public HsqlNameManager.HsqlName[] getResultColumnNames()
  {
    if (this.resultTable == null) {
      return this.leftQueryExpression.getResultColumnNames();
    }
    HashMappedList localHashMappedList = ((TableDerived)this.resultTable).columnList;
    HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[localHashMappedList.size()];
    for (int i = 0; i < arrayOfHsqlName.length; i++) {
      arrayOfHsqlName[i] = ((ColumnSchema)localHashMappedList.get(i)).getName();
    }
    return arrayOfHsqlName;
  }
  
  public TableBase getResultTable()
  {
    if (this.resultTable != null) {
      return this.resultTable;
    }
    if (this.leftQueryExpression != null) {
      return this.leftQueryExpression.getResultTable();
    }
    return null;
  }
  
  public Table getBaseTable()
  {
    return null;
  }
  
  public boolean isUpdatable()
  {
    return this.isUpdatable;
  }
  
  public boolean isInsertable()
  {
    return this.isInsertable;
  }
  
  public int[] getBaseTableColumnMap()
  {
    return null;
  }
  
  public Expression getCheckCondition()
  {
    return null;
  }
  
  public boolean hasReference(RangeVariable paramRangeVariable)
  {
    if (this.leftQueryExpression.hasReference(paramRangeVariable)) {
      return true;
    }
    return this.rightQueryExpression.hasReference(paramRangeVariable);
  }
  
  void getBaseTableNames(OrderedHashSet paramOrderedHashSet)
  {
    this.leftQueryExpression.getBaseTableNames(paramOrderedHashSet);
    this.rightQueryExpression.getBaseTableNames(paramOrderedHashSet);
  }
  
  boolean isEquivalent(QueryExpression paramQueryExpression)
  {
    return (this.leftQueryExpression.isEquivalent(paramQueryExpression.leftQueryExpression)) && (this.unionType == paramQueryExpression.unionType) && (this.rightQueryExpression == null ? paramQueryExpression.rightQueryExpression == null : this.rightQueryExpression.isEquivalent(paramQueryExpression.rightQueryExpression));
  }
  
  public void replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression)
  {
    this.leftQueryExpression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
    this.rightQueryExpression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
  }
  
  public void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2)
  {
    this.leftQueryExpression.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2);
    this.rightQueryExpression.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.QueryExpression
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */