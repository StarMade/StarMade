package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.RowType;
import org.hsqldb.types.Type;

public class ExpressionTable
  extends Expression
{
  boolean isTable;
  boolean ordinality = false;
  
  ExpressionTable(Expression[] paramArrayOfExpression, boolean paramBoolean)
  {
    super(30);
    this.nodes = paramArrayOfExpression;
    this.ordinality = paramBoolean;
  }
  
  public String getSQL()
  {
    if (this.isTable) {
      return "TABLE";
    }
    return "UNNEST";
  }
  
  protected String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    localStringBuffer.append('\n');
    for (int i = 0; i < paramInt; i++) {
      localStringBuffer.append(' ');
    }
    if (this.isTable) {
      localStringBuffer.append("TABLE").append(' ');
    } else {
      localStringBuffer.append("UNNEST").append(' ');
    }
    localStringBuffer.append(this.nodes[0].describe(paramSession, paramInt));
    return localStringBuffer.toString();
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i].resolveTypes(paramSession, this);
      }
    }
    if ((this.nodes.length == 1) && (this.nodes[0].dataType.isRowType()))
    {
      if (this.ordinality) {
        throw Error.error(5581, "ORDINALITY");
      }
      this.nodeDataTypes = ((RowType)this.nodes[0].dataType).getTypesArray();
      this.table.prepareTable();
      this.table.columnList = ((FunctionSQLInvoked)this.nodes[0]).routine.getTable().columnList;
      this.isTable = true;
      return;
    }
    for (i = 0; i < this.nodes.length; i++) {
      if (!this.nodes[i].dataType.isArrayType()) {
        throw Error.error(5563, "UNNEST");
      }
    }
    i = this.ordinality ? this.nodes.length + 1 : this.nodes.length;
    this.nodeDataTypes = new Type[i];
    for (int j = 0; j < this.nodes.length; j++)
    {
      this.nodeDataTypes[j] = this.nodes[j].dataType.collectionBaseType();
      if ((this.nodeDataTypes[j] == null) || (this.nodeDataTypes[j] == Type.SQL_ALL_TYPES)) {
        throw Error.error(5567, "UNNEST");
      }
    }
    if (this.ordinality) {
      this.nodeDataTypes[this.nodes.length] = Type.SQL_INTEGER;
    }
    this.table.prepareTable();
  }
  
  public Result getResult(Session paramSession)
  {
    switch (this.opType)
    {
    case 30: 
      RowSetNavigatorData localRowSetNavigatorData = this.table.getNavigator(paramSession);
      Result localResult = Result.newResult(localRowSetNavigatorData);
      localResult.metaData = this.table.queryExpression.getMetaData();
      return localResult;
    }
    throw Error.runtimeError(201, "ExpressionTable");
  }
  
  public Object[] getRowValue(Session paramSession)
  {
    switch (this.opType)
    {
    case 30: 
      return this.table.queryExpression.getValues(paramSession);
    }
    throw Error.runtimeError(201, "Expression");
  }
  
  Object getValue(Session paramSession, Type paramType)
  {
    switch (this.opType)
    {
    case 30: 
      materialise(paramSession);
      Object[] arrayOfObject = this.table.getValues(paramSession);
      if (arrayOfObject.length == 1) {
        return ((Object[])arrayOfObject)[0];
      }
      return arrayOfObject;
    }
    throw Error.runtimeError(201, "Expression");
  }
  
  public Object getValue(Session paramSession)
  {
    return this.valueData;
  }
  
  void insertValuesIntoSubqueryTable(Session paramSession, PersistentStore paramPersistentStore)
  {
    if (this.isTable) {
      insertTableValues(paramSession, paramPersistentStore);
    } else {
      insertArrayValues(paramSession, paramPersistentStore);
    }
  }
  
  private void insertTableValues(Session paramSession, PersistentStore paramPersistentStore)
  {
    Result localResult = this.nodes[0].getResult(paramSession);
    RowSetNavigator localRowSetNavigator = localResult.navigator;
    int i = localRowSetNavigator.getSize();
    while (localRowSetNavigator.hasNext())
    {
      Object[] arrayOfObject = localRowSetNavigator.getNext();
      Row localRow = (Row)paramPersistentStore.getNewCachedObject(paramSession, arrayOfObject, false);
      try
      {
        paramPersistentStore.indexRow(paramSession, localRow);
      }
      catch (HsqlException localHsqlException) {}
    }
  }
  
  private void insertArrayValues(Session paramSession, PersistentStore paramPersistentStore)
  {
    Object[][] arrayOfObject; = new Object[this.nodes.length][];
    for (int i = 0; i < arrayOfObject;.length; i++)
    {
      Object[] arrayOfObject1 = (Object[])this.nodes[i].getValue(paramSession);
      if (arrayOfObject1 == null) {
        arrayOfObject1 = ValuePool.emptyObjectArray;
      }
      arrayOfObject;[i] = arrayOfObject1;
    }
    for (i = 0;; i++)
    {
      int j = 0;
      Object[] arrayOfObject2 = new Object[this.nodeDataTypes.length];
      for (int k = 0; k < arrayOfObject;.length; k++) {
        if (i < arrayOfObject;[k].length)
        {
          arrayOfObject2[k] = arrayOfObject;[k][i];
          j = 1;
        }
      }
      if (j == 0) {
        break;
      }
      if (this.ordinality) {
        arrayOfObject2[this.nodes.length] = ValuePool.getInt(i + 1);
      }
      Row localRow = (Row)paramPersistentStore.getNewCachedObject(paramSession, arrayOfObject2, false);
      paramPersistentStore.indexRow(paramSession, localRow);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.ExpressionTable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */