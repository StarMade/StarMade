package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArraySort;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.User;
import org.hsqldb.store.ValuePool;

public abstract class StatementDMQL
  extends Statement
{
  public static final String PCOL_PREFIX = "@p";
  static final String RETURN_COLUMN_NAME = "@p0";
  Table targetTable;
  Table baseTable;
  int[] baseColumnMap;
  RangeVariable[] targetRangeVariables = RangeVariable.emptyArray;
  Table sourceTable;
  Expression condition;
  boolean restartIdentity;
  int[] insertColumnMap = ValuePool.emptyIntArray;
  int[] updateColumnMap = ValuePool.emptyIntArray;
  int[] baseUpdateColumnMap = ValuePool.emptyIntArray;
  Expression[] updateExpressions = Expression.emptyArray;
  Expression[][] multiColumnValues;
  Expression insertExpression;
  boolean[] insertCheckColumns;
  boolean[] updateCheckColumns;
  Expression updatableTableCheck;
  RangeVariable checkRangeVariable;
  QueryExpression queryExpression;
  HsqlNameManager.SimpleName cursorName;
  ExpressionColumn[] parameters;
  ResultMetaData parameterMetaData;
  TableDerived[] subqueries = TableDerived.emptyArray;
  int rangeIteratorCount;
  NumberSequence[] sequences;
  Routine[] routines;
  RangeVariable[] rangeVariables;
  
  StatementDMQL(int paramInt1, int paramInt2, HsqlNameManager.HsqlName paramHsqlName)
  {
    super(paramInt1, paramInt2);
    this.schemaName = paramHsqlName;
    this.isTransactionStatement = true;
  }
  
  void setBaseIndexColumnMap()
  {
    if (this.targetTable != this.baseTable) {
      this.baseColumnMap = this.targetTable.getBaseTableColumnMap();
    }
  }
  
  public void setCursorName(HsqlNameManager.SimpleName paramSimpleName)
  {
    this.cursorName = paramSimpleName;
  }
  
  public HsqlNameManager.SimpleName getCursorName()
  {
    return this.cursorName;
  }
  
  public Result execute(Session paramSession)
  {
    if ((this.targetTable != null) && (paramSession.isReadOnly()) && (!this.targetTable.isTemp()))
    {
      HsqlException localHsqlException = Error.error(3706);
      return Result.newErrorResult(localHsqlException);
    }
    if (this.isExplain) {
      return getExplainResult(paramSession);
    }
    Result localResult;
    try
    {
      if (this.subqueries.length > 0) {
        materializeSubQueries(paramSession);
      }
      localResult = getResult(paramSession);
      clearStructures(paramSession);
    }
    catch (Throwable localThrowable)
    {
      clearStructures(paramSession);
      localResult = Result.newErrorResult(localThrowable, null);
      localResult.getException().setStatementType(this.group, this.type);
    }
    return localResult;
  }
  
  private Result getExplainResult(Session paramSession)
  {
    Result localResult = Result.newSingleColumnStringResult("OPERATION", describe(paramSession));
    OrderedHashSet localOrderedHashSet = getReferences();
    localResult.navigator.add(new Object[] { "Object References" });
    HsqlNameManager.HsqlName localHsqlName;
    for (int i = 0; i < localOrderedHashSet.size(); i++)
    {
      localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
      localResult.navigator.add(new Object[] { localHsqlName.getSchemaQualifiedStatementName() });
    }
    localResult.navigator.add(new Object[] { "Read Locks" });
    for (i = 0; i < this.readTableNames.length; i++)
    {
      localHsqlName = this.readTableNames[i];
      localResult.navigator.add(new Object[] { localHsqlName.getSchemaQualifiedStatementName() });
    }
    localResult.navigator.add(new Object[] { "WriteLocks" });
    for (i = 0; i < this.writeTableNames.length; i++)
    {
      localHsqlName = this.writeTableNames[i];
      localResult.navigator.add(new Object[] { localHsqlName.getSchemaQualifiedStatementName() });
    }
    return localResult;
  }
  
  abstract Result getResult(Session paramSession);
  
  abstract void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet);
  
  abstract void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet);
  
  boolean[] getInsertOrUpdateColumnCheckList()
  {
    switch (this.type)
    {
    case 50: 
      return this.insertCheckColumns;
    case 82: 
      return this.updateCheckColumns;
    case 128: 
      boolean[] arrayOfBoolean = (boolean[])ArrayUtil.duplicateArray(this.insertCheckColumns);
      ArrayUtil.orBooleanArray(this.updateCheckColumns, arrayOfBoolean);
      return arrayOfBoolean;
    }
    return null;
  }
  
  void materializeSubQueries(Session paramSession)
  {
    HashSet localHashSet = new HashSet();
    for (int i = 0; i < this.subqueries.length; i++)
    {
      TableDerived localTableDerived = this.subqueries[i];
      if ((localHashSet.add(localTableDerived)) && (!localTableDerived.isCorrelated())) {
        localTableDerived.materialise(paramSession);
      }
    }
  }
  
  TableDerived[] getSubqueries(Session paramSession)
  {
    OrderedHashSet localOrderedHashSet1 = null;
    for (int i = 0; i < this.targetRangeVariables.length; i++) {
      if (this.targetRangeVariables[i] != null)
      {
        OrderedHashSet localOrderedHashSet2 = this.targetRangeVariables[i].getSubqueries();
        localOrderedHashSet1 = OrderedHashSet.addAll(localOrderedHashSet1, localOrderedHashSet2);
      }
    }
    for (i = 0; i < this.updateExpressions.length; i++) {
      localOrderedHashSet1 = this.updateExpressions[i].collectAllSubqueries(localOrderedHashSet1);
    }
    if (this.insertExpression != null) {
      localOrderedHashSet1 = this.insertExpression.collectAllSubqueries(localOrderedHashSet1);
    }
    if (this.condition != null) {
      localOrderedHashSet1 = this.condition.collectAllSubqueries(localOrderedHashSet1);
    }
    if (this.queryExpression != null)
    {
      localObject = this.queryExpression.getSubqueries();
      localOrderedHashSet1 = OrderedHashSet.addAll(localOrderedHashSet1, (OrderedHashSet)localObject);
    }
    if (this.updatableTableCheck != null)
    {
      localObject = this.updatableTableCheck.getSubqueries();
      localOrderedHashSet1 = OrderedHashSet.addAll(localOrderedHashSet1, (OrderedHashSet)localObject);
    }
    if ((localOrderedHashSet1 == null) || (localOrderedHashSet1.size() == 0)) {
      return TableDerived.emptyArray;
    }
    Object localObject = new TableDerived[localOrderedHashSet1.size()];
    localOrderedHashSet1.toArray((Object[])localObject);
    ArraySort.sort((Object[])localObject, 0, localObject.length, localObject[0]);
    return localObject;
  }
  
  void setDatabseObjects(Session paramSession, ParserDQL.CompileContext paramCompileContext)
  {
    this.parameters = paramCompileContext.getParameters();
    setParameterMetaData();
    this.subqueries = getSubqueries(paramSession);
    this.rangeIteratorCount = paramCompileContext.getRangeVarCount();
    this.rangeVariables = paramCompileContext.getAllRangeVariables();
    this.sequences = paramCompileContext.getSequences();
    this.routines = paramCompileContext.getRoutines();
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    collectTableNamesForWrite(localOrderedHashSet);
    if (localOrderedHashSet.size() > 0)
    {
      this.writeTableNames = new HsqlNameManager.HsqlName[localOrderedHashSet.size()];
      localOrderedHashSet.toArray(this.writeTableNames);
      localOrderedHashSet.clear();
    }
    collectTableNamesForRead(localOrderedHashSet);
    localOrderedHashSet.removeAll(this.writeTableNames);
    if (localOrderedHashSet.size() > 0)
    {
      this.readTableNames = new HsqlNameManager.HsqlName[localOrderedHashSet.size()];
      localOrderedHashSet.toArray(this.readTableNames);
    }
    this.references = paramCompileContext.getSchemaObjectNames();
    if (this.targetTable != null) {
      this.references.add(this.targetTable.getName());
    }
  }
  
  void checkAccessRights(Session paramSession)
  {
    if ((this.targetTable != null) && (!this.targetTable.isTemp()))
    {
      if (!paramSession.isProcessingScript) {
        this.targetTable.checkDataReadOnly();
      }
      Grantee localGrantee = this.targetTable.getOwner();
      if ((localGrantee != null) && (localGrantee.isSystem()) && (!paramSession.getUser().isSystem())) {
        throw Error.error(5501, this.targetTable.getName().name);
      }
      paramSession.checkReadWrite();
    }
    if (paramSession.isAdmin()) {
      return;
    }
    for (int i = 0; i < this.sequences.length; i++) {
      paramSession.getGrantee().checkAccess(this.sequences[i]);
    }
    for (i = 0; i < this.routines.length; i++) {
      if (!this.routines[i].isLibraryRoutine()) {
        paramSession.getGrantee().checkAccess(this.routines[i]);
      }
    }
    for (i = 0; i < this.rangeVariables.length; i++)
    {
      RangeVariable localRangeVariable = this.rangeVariables[i];
      if (localRangeVariable.rangeTable.getSchemaName() != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME) {
        paramSession.getGrantee().checkSelect(localRangeVariable.rangeTable, localRangeVariable.usedColumns);
      }
    }
    switch (this.type)
    {
    case 7: 
      break;
    case 50: 
      paramSession.getGrantee().checkInsert(this.targetTable, this.insertCheckColumns);
      break;
    case 85: 
      break;
    case 19: 
      paramSession.getGrantee().checkDelete(this.targetTable);
      break;
    case 82: 
      paramSession.getGrantee().checkUpdate(this.targetTable, this.updateCheckColumns);
      break;
    case 128: 
      paramSession.getGrantee().checkInsert(this.targetTable, this.insertCheckColumns);
      paramSession.getGrantee().checkUpdate(this.targetTable, this.updateCheckColumns);
    }
  }
  
  Result getWriteAccessResult(Session paramSession)
  {
    try
    {
      if ((this.targetTable != null) && (!this.targetTable.isTemp())) {
        paramSession.checkReadWrite();
      }
    }
    catch (HsqlException localHsqlException)
    {
      return Result.newErrorResult(localHsqlException);
    }
    return null;
  }
  
  public ResultMetaData getResultMetaData()
  {
    switch (this.type)
    {
    case 19: 
    case 50: 
    case 82: 
    case 128: 
      return ResultMetaData.emptyResultMetaData;
    }
    throw Error.runtimeError(201, "StatementDMQL");
  }
  
  public ResultMetaData getParametersMetaData()
  {
    return this.parameterMetaData;
  }
  
  void setParameterMetaData()
  {
    int i = 0;
    if (this.parameters.length == 0)
    {
      this.parameterMetaData = ResultMetaData.emptyParamMetaData;
      return;
    }
    this.parameterMetaData = ResultMetaData.newParameterMetaData(this.parameters.length);
    for (int k = 0; k < this.parameters.length; k++)
    {
      int j = k + i;
      this.parameterMetaData.columnLabels[j] = ("@p" + (k + 1));
      this.parameterMetaData.columnTypes[j] = this.parameters[k].dataType;
      if (this.parameters[k].dataType == null) {
        throw Error.error(5567);
      }
      int m = 1;
      if ((this.parameters[k].column != null) && (this.parameters[k].column.getParameterMode() != 0)) {
        m = this.parameters[k].column.getParameterMode();
      }
      this.parameterMetaData.paramModes[j] = m;
      this.parameterMetaData.paramNullable[j] = (this.parameters[k].column == null ? 1 : this.parameters[k].column.getNullability());
    }
  }
  
  public String describe(Session paramSession)
  {
    try
    {
      return describeImpl(paramSession);
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      return localThrowable.toString();
    }
  }
  
  String describeImpl(Session paramSession)
    throws Exception
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    int j;
    switch (this.type)
    {
    case 85: 
      localStringBuffer.append(this.queryExpression.describe(paramSession, 0));
      appendParms(localStringBuffer).append('\n');
      appendSubqueries(paramSession, localStringBuffer, 2);
      return localStringBuffer.toString();
    case 50: 
      if (this.queryExpression == null)
      {
        localStringBuffer.append("INSERT VALUES");
        localStringBuffer.append('[').append('\n');
        appendMultiColumns(localStringBuffer, this.insertColumnMap).append('\n');
        appendTable(localStringBuffer).append('\n');
        appendParms(localStringBuffer).append('\n');
        appendSubqueries(paramSession, localStringBuffer, 2).append(']');
        return localStringBuffer.toString();
      }
      localStringBuffer.append("INSERT SELECT");
      localStringBuffer.append('[').append('\n');
      appendColumns(localStringBuffer, this.insertColumnMap).append('\n');
      appendTable(localStringBuffer).append('\n');
      localStringBuffer.append(this.queryExpression.describe(paramSession, i)).append('\n');
      appendParms(localStringBuffer).append('\n');
      appendSubqueries(paramSession, localStringBuffer, 2).append(']');
      return localStringBuffer.toString();
    case 82: 
      localStringBuffer.append("UPDATE");
      localStringBuffer.append('[').append('\n');
      appendColumns(localStringBuffer, this.updateColumnMap).append('\n');
      appendTable(localStringBuffer).append('\n');
      appendCondition(paramSession, localStringBuffer);
      for (j = 0; j < this.targetRangeVariables.length; j++) {
        localStringBuffer.append(this.targetRangeVariables[j].describe(paramSession, i)).append('\n');
      }
      appendParms(localStringBuffer).append('\n');
      appendSubqueries(paramSession, localStringBuffer, 2).append(']');
      return localStringBuffer.toString();
    case 19: 
      localStringBuffer.append("DELETE");
      localStringBuffer.append('[').append('\n');
      appendTable(localStringBuffer).append('\n');
      appendCondition(paramSession, localStringBuffer);
      for (j = 0; j < this.targetRangeVariables.length; j++) {
        localStringBuffer.append(this.targetRangeVariables[j].describe(paramSession, i)).append('\n');
      }
      appendParms(localStringBuffer).append('\n');
      appendSubqueries(paramSession, localStringBuffer, 2).append(']');
      return localStringBuffer.toString();
    case 7: 
      localStringBuffer.append("CALL");
      localStringBuffer.append('[').append(']');
      return localStringBuffer.toString();
    case 128: 
      localStringBuffer.append("MERGE");
      localStringBuffer.append('[').append('\n');
      appendMultiColumns(localStringBuffer, this.insertColumnMap).append('\n');
      appendColumns(localStringBuffer, this.updateColumnMap).append('\n');
      appendTable(localStringBuffer).append('\n');
      appendCondition(paramSession, localStringBuffer);
      for (j = 0; j < this.targetRangeVariables.length; j++) {
        localStringBuffer.append(this.targetRangeVariables[j].describe(paramSession, i)).append('\n');
      }
      appendParms(localStringBuffer).append('\n');
      appendSubqueries(paramSession, localStringBuffer, 2).append(']');
      return localStringBuffer.toString();
    }
    return "UNKNOWN";
  }
  
  private StringBuffer appendSubqueries(Session paramSession, StringBuffer paramStringBuffer, int paramInt)
  {
    paramStringBuffer.append("SUBQUERIES[");
    for (int i = 0; i < this.subqueries.length; i++)
    {
      paramStringBuffer.append("\n[level=").append(this.subqueries[i].depth).append('\n');
      if (this.subqueries[i].queryExpression == null)
      {
        for (int j = 0; j < paramInt; j++) {
          paramStringBuffer.append(' ');
        }
        paramStringBuffer.append("value expression");
      }
      else
      {
        paramStringBuffer.append(this.subqueries[i].queryExpression.describe(paramSession, paramInt));
      }
      paramStringBuffer.append("]");
    }
    paramStringBuffer.append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendTable(StringBuffer paramStringBuffer)
  {
    paramStringBuffer.append("TABLE[").append(this.targetTable.getName().name).append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendSourceTable(StringBuffer paramStringBuffer)
  {
    paramStringBuffer.append("SOURCE TABLE[").append(this.sourceTable.getName().name).append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendColumns(StringBuffer paramStringBuffer, int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (this.updateExpressions.length == 0)) {
      return paramStringBuffer;
    }
    paramStringBuffer.append("COLUMNS=[");
    for (int i = 0; i < paramArrayOfInt.length; i++) {
      paramStringBuffer.append('\n').append(paramArrayOfInt[i]).append(':').append(' ').append(this.targetTable.getColumn(paramArrayOfInt[i]).getNameString());
    }
    for (i = 0; i < this.updateExpressions.length; i++) {
      paramStringBuffer.append('[').append(this.updateExpressions[i]).append(']');
    }
    paramStringBuffer.append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendMultiColumns(StringBuffer paramStringBuffer, int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (this.multiColumnValues == null)) {
      return paramStringBuffer;
    }
    paramStringBuffer.append("COLUMNS=[");
    for (int i = 0; i < this.multiColumnValues.length; i++) {
      for (int j = 0; j < paramArrayOfInt.length; j++) {
        paramStringBuffer.append('\n').append(paramArrayOfInt[j]).append(':').append(' ').append(this.targetTable.getColumn(paramArrayOfInt[j]).getName().name).append('[').append(this.multiColumnValues[i][j]).append(']');
      }
    }
    paramStringBuffer.append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendParms(StringBuffer paramStringBuffer)
  {
    paramStringBuffer.append("PARAMETERS=[");
    for (int i = 0; i < this.parameters.length; i++) {
      paramStringBuffer.append('\n').append('@').append(i).append('[').append(this.parameters[i].describe(null, 0)).append(']');
    }
    paramStringBuffer.append(']');
    return paramStringBuffer;
  }
  
  private StringBuffer appendCondition(Session paramSession, StringBuffer paramStringBuffer)
  {
    return this.condition == null ? paramStringBuffer.append("CONDITION[]\n") : paramStringBuffer.append("CONDITION[").append(this.condition.describe(paramSession, 0)).append("]\n");
  }
  
  public void resolve(Session paramSession) {}
  
  public final boolean isCatalogLock()
  {
    return false;
  }
  
  public boolean isCatalogChange()
  {
    return false;
  }
  
  public void clearStructures(Session paramSession)
  {
    paramSession.sessionContext.clearStructures(this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.StatementDMQL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */