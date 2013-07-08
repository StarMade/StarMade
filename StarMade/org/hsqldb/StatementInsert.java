package org.hsqldb;

import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

public class StatementInsert
  extends StatementDML
{
  int overrideUserValue = -1;
  
  StatementInsert(Session paramSession, Table paramTable, int[] paramArrayOfInt, Expression paramExpression, boolean[] paramArrayOfBoolean, ParserDQL.CompileContext paramCompileContext)
  {
    super(50, 2004, paramSession.getCurrentSchemaHsqlName());
    this.targetTable = paramTable;
    this.baseTable = (paramTable.isTriggerInsertable() ? paramTable : paramTable.getBaseTable());
    this.insertColumnMap = paramArrayOfInt;
    this.insertCheckColumns = paramArrayOfBoolean;
    this.insertExpression = paramExpression;
    setupChecks();
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
    this.isSimpleInsert = ((paramExpression != null) && (paramExpression.nodes.length == 1) && (this.updatableTableCheck == null));
  }
  
  StatementInsert(Session paramSession, Table paramTable, int[] paramArrayOfInt, boolean[] paramArrayOfBoolean, QueryExpression paramQueryExpression, ParserDQL.CompileContext paramCompileContext, int paramInt)
  {
    super(50, 2004, paramSession.getCurrentSchemaHsqlName());
    this.targetTable = paramTable;
    this.baseTable = (paramTable.isTriggerInsertable() ? paramTable : paramTable.getBaseTable());
    this.insertColumnMap = paramArrayOfInt;
    this.insertCheckColumns = paramArrayOfBoolean;
    this.queryExpression = paramQueryExpression;
    this.overrideUserValue = paramInt;
    setupChecks();
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  Result getResult(Session paramSession)
  {
    Result localResult = null;
    RowSetNavigator localRowSetNavigator = null;
    PersistentStore localPersistentStore = this.baseTable.getRowStore(paramSession);
    if (this.generatedIndexes != null)
    {
      localResult = Result.newUpdateCountResult(this.generatedResultMetaData, 0);
      localRowSetNavigator = localResult.getChainedResult().getNavigator();
    }
    if (this.isSimpleInsert)
    {
      localObject = this.baseTable.getColumnTypes();
      Object[] arrayOfObject = getInsertData(paramSession, (Type[])localObject, this.insertExpression.nodes[0].nodes);
      return insertSingleRow(paramSession, localPersistentStore, arrayOfObject);
    }
    Object localObject = this.queryExpression == null ? getInsertValuesNavigator(paramSession) : getInsertSelectNavigator(paramSession);
    int i = ((RowSetNavigator)localObject).getSize();
    if (i > 0) {
      insertRowSet(paramSession, localRowSetNavigator, (RowSetNavigator)localObject);
    }
    if (this.baseTable.triggerLists[0].length > 0) {
      this.baseTable.fireTriggers(paramSession, 0, (RowSetNavigator)localObject);
    }
    if (localResult == null) {
      localResult = new Result(1, i);
    } else {
      localResult.setUpdateCount(i);
    }
    if (i == 0) {
      paramSession.addWarning(HsqlException.noDataCondition);
    }
    return localResult;
  }
  
  RowSetNavigator getInsertSelectNavigator(Session paramSession)
  {
    Type[] arrayOfType1 = this.baseTable.getColumnTypes();
    int[] arrayOfInt = this.insertColumnMap;
    Result localResult = this.queryExpression.getResult(paramSession, 0);
    RowSetNavigator localRowSetNavigator = localResult.initialiseNavigator();
    Type[] arrayOfType2 = localResult.metaData.columnTypes;
    RowSetNavigatorClient localRowSetNavigatorClient = new RowSetNavigatorClient(2);
    while (localRowSetNavigator.hasNext())
    {
      Object[] arrayOfObject1 = this.baseTable.getNewRowData(paramSession);
      Object[] arrayOfObject2 = (Object[])localRowSetNavigator.getNext();
      for (int i = 0; i < arrayOfInt.length; i++)
      {
        int j = arrayOfInt[i];
        if (j != this.overrideUserValue)
        {
          Type localType = arrayOfType2[i];
          arrayOfObject1[j] = arrayOfType1[j].convertToType(paramSession, arrayOfObject2[i], localType);
        }
      }
      localRowSetNavigatorClient.add(arrayOfObject1);
    }
    return localRowSetNavigatorClient;
  }
  
  RowSetNavigator getInsertValuesNavigator(Session paramSession)
  {
    Type[] arrayOfType = this.baseTable.getColumnTypes();
    Expression[] arrayOfExpression1 = this.insertExpression.nodes;
    RowSetNavigatorClient localRowSetNavigatorClient = new RowSetNavigatorClient(arrayOfExpression1.length);
    for (int i = 0; i < arrayOfExpression1.length; i++)
    {
      Expression[] arrayOfExpression2 = arrayOfExpression1[i].nodes;
      Object[] arrayOfObject = getInsertData(paramSession, arrayOfType, arrayOfExpression2);
      localRowSetNavigatorClient.add(arrayOfObject);
    }
    return localRowSetNavigatorClient;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.StatementInsert
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */