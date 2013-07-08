package org.hsqldb;

import java.lang.reflect.Method;
import java.sql.Connection;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArraySort;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rights.Grantee;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Type;

public class StatementProcedure
  extends StatementDMQL
{
  Expression expression;
  Routine procedure;
  Expression[] arguments = Expression.emptyArray;
  ResultMetaData resultMetaData;
  
  StatementProcedure(Session paramSession, Expression paramExpression, ParserDQL.CompileContext paramCompileContext)
  {
    super(7, 2003, paramSession.getCurrentSchemaHsqlName());
    this.statementReturnType = 2;
    if (paramExpression.opType == 27)
    {
      FunctionSQLInvoked localFunctionSQLInvoked = (FunctionSQLInvoked)paramExpression;
      if (localFunctionSQLInvoked.routine.returnsTable)
      {
        this.procedure = localFunctionSQLInvoked.routine;
        this.arguments = localFunctionSQLInvoked.nodes;
      }
      else
      {
        this.expression = paramExpression;
      }
    }
    else
    {
      this.expression = paramExpression;
    }
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
    if (this.procedure != null) {
      paramSession.getGrantee().checkAccess(this.procedure);
    }
  }
  
  StatementProcedure(Session paramSession, Routine paramRoutine, Expression[] paramArrayOfExpression, ParserDQL.CompileContext paramCompileContext)
  {
    super(7, 2003, paramSession.getCurrentSchemaHsqlName());
    if (paramRoutine.maxDynamicResults > 0) {
      this.statementReturnType = 0;
    }
    this.procedure = paramRoutine;
    this.arguments = paramArrayOfExpression;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
    paramSession.getGrantee().checkAccess(paramRoutine);
  }
  
  Result getResult(Session paramSession)
  {
    Result localResult = this.expression == null ? getProcedureResult(paramSession) : getExpressionResult(paramSession);
    localResult.setStatementType(this.statementReturnType);
    return localResult;
  }
  
  Result getProcedureResult(Session paramSession)
  {
    Object[] arrayOfObject = ValuePool.emptyObjectArray;
    int i;
    if (this.procedure.isPSM())
    {
      i = this.arguments.length;
      if (this.procedure.getMaxDynamicResults() > 0) {
        i++;
      }
    }
    else
    {
      i = this.procedure.javaMethod.getParameterTypes().length;
      if (this.procedure.javaMethodWithConnection) {
        i--;
      }
    }
    if (i > 0) {
      arrayOfObject = new Object[i];
    }
    Object localObject3;
    for (int j = 0; j < this.arguments.length; j++)
    {
      localObject1 = this.arguments[j];
      Object localObject2 = ((Expression)localObject1).getValue(paramSession);
      if (localObject1 != null)
      {
        localObject3 = this.procedure.getParameter(j).getDataType();
        arrayOfObject[j] = ((Type)localObject3).convertToType(paramSession, localObject2, ((Expression)localObject1).getDataType());
      }
    }
    paramSession.sessionContext.push();
    paramSession.sessionContext.routineArguments = arrayOfObject;
    paramSession.sessionContext.routineVariables = ValuePool.emptyObjectArray;
    Result localResult1 = Result.updateZeroResult;
    if (this.procedure.isPSM())
    {
      localResult1 = executePSMProcedure(paramSession);
    }
    else
    {
      localObject1 = paramSession.getInternalConnection();
      localResult1 = executeJavaProcedure(paramSession, (Connection)localObject1);
    }
    Object localObject1 = paramSession.sessionContext.routineArguments;
    paramSession.sessionContext.pop();
    if (!this.procedure.isPSM()) {
      paramSession.releaseInternalConnection();
    }
    if (localResult1.isError()) {
      return localResult1;
    }
    for (int k = 0; k < this.procedure.getParameterCount(); k++)
    {
      localObject3 = this.procedure.getParameter(k);
      int m = ((ColumnSchema)localObject3).getParameterMode();
      if (m != 1)
      {
        int n;
        if (this.arguments[k].isDynamicParam())
        {
          n = this.arguments[k].parameterIndex;
          paramSession.sessionContext.dynamicArguments[n] = localObject1[k];
        }
        else
        {
          n = this.arguments[k].getColumnIndex();
          paramSession.sessionContext.routineVariables[n] = localObject1[k];
        }
      }
    }
    Result localResult2 = localResult1;
    localResult1 = Result.newCallResponse(getParametersMetaData().getParameterTypes(), this.field_2142, paramSession.sessionContext.dynamicArguments);
    if (this.procedure.returnsTable())
    {
      localResult1.addChainedResult(localResult2);
    }
    else if (localObject1.length > this.arguments.length)
    {
      localResult2 = (Result)localObject1[this.arguments.length];
      localResult1.addChainedResult(localResult2);
    }
    return localResult1;
  }
  
  Result executePSMProcedure(Session paramSession)
  {
    int i = this.procedure.getVariableCount();
    paramSession.sessionContext.routineVariables = new Object[i];
    Result localResult = this.procedure.statement.execute(paramSession);
    if (localResult.isError()) {
      return localResult;
    }
    return localResult;
  }
  
  Result executeJavaProcedure(Session paramSession, Connection paramConnection)
  {
    Result localResult = Result.updateZeroResult;
    Object[] arrayOfObject1 = paramSession.sessionContext.routineArguments;
    Object[] arrayOfObject2 = this.procedure.convertArgsToJava(paramSession, arrayOfObject1);
    if (this.procedure.javaMethodWithConnection) {
      arrayOfObject2[0] = paramConnection;
    }
    localResult = this.procedure.invokeJavaMethod(paramSession, arrayOfObject2);
    this.procedure.convertArgsToSQL(paramSession, arrayOfObject1, arrayOfObject2);
    return localResult;
  }
  
  Result getExpressionResult(Session paramSession)
  {
    paramSession.sessionData.startRowProcessing();
    Object localObject = this.expression.getValue(paramSession);
    if (this.resultMetaData == null) {
      getResultMetaData();
    }
    Result localResult = Result.newSingleColumnResult(this.resultMetaData);
    Object[] arrayOfObject;
    if (this.expression.getDataType().isArrayType())
    {
      arrayOfObject = new Object[1];
      arrayOfObject[0] = localObject;
    }
    else if ((localObject instanceof Object[]))
    {
      arrayOfObject = (Object[])localObject;
    }
    else
    {
      arrayOfObject = new Object[1];
      arrayOfObject[0] = localObject;
    }
    localResult.getNavigator().add(arrayOfObject);
    return localResult;
  }
  
  TableDerived[] getSubqueries(Session paramSession)
  {
    OrderedHashSet localOrderedHashSet = null;
    if (this.expression != null) {
      localOrderedHashSet = this.expression.collectAllSubqueries(localOrderedHashSet);
    }
    for (int i = 0; i < this.arguments.length; i++) {
      localOrderedHashSet = this.arguments[i].collectAllSubqueries(localOrderedHashSet);
    }
    if ((localOrderedHashSet == null) || (localOrderedHashSet.size() == 0)) {
      return TableDerived.emptyArray;
    }
    TableDerived[] arrayOfTableDerived = new TableDerived[localOrderedHashSet.size()];
    localOrderedHashSet.toArray(arrayOfTableDerived);
    ArraySort.sort(arrayOfTableDerived, 0, arrayOfTableDerived.length, arrayOfTableDerived[0]);
    for (int j = 0; j < this.subqueries.length; j++) {
      arrayOfTableDerived[j].prepareTable();
    }
    return arrayOfTableDerived;
  }
  
  public ResultMetaData getResultMetaData()
  {
    if (this.resultMetaData != null) {
      return this.resultMetaData;
    }
    switch (this.type)
    {
    case 7: 
      if (this.expression == null) {
        return ResultMetaData.emptyResultMetaData;
      }
      ResultMetaData localResultMetaData = ResultMetaData.newResultMetaData(1);
      ColumnBase localColumnBase = new ColumnBase(null, null, null, "@p0");
      localColumnBase.setType(this.expression.getDataType());
      localResultMetaData.columns[0] = localColumnBase;
      localResultMetaData.prepareData();
      this.resultMetaData = localResultMetaData;
      return localResultMetaData;
    }
    throw Error.runtimeError(201, "StatementProcedure");
  }
  
  public ResultMetaData getParametersMetaData()
  {
    return super.getParametersMetaData();
  }
  
  void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet)
  {
    if (this.expression == null)
    {
      paramOrderedHashSet.addAll(this.procedure.getTableNamesForRead());
    }
    else
    {
      for (int i = 0; i < this.subqueries.length; i++) {
        if (this.subqueries[i].queryExpression != null) {
          this.subqueries[i].queryExpression.getBaseTableNames(paramOrderedHashSet);
        }
      }
      for (i = 0; i < this.routines.length; i++) {
        paramOrderedHashSet.addAll(this.routines[i].getTableNamesForRead());
      }
    }
  }
  
  void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet)
  {
    if (this.expression == null) {
      paramOrderedHashSet.addAll(this.procedure.getTableNamesForWrite());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.StatementProcedure
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */