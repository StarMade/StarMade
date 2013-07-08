package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArraySort;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.result.Result;

public class StatementExpression
  extends StatementDMQL
{
  Expression expression;
  
  StatementExpression(Session paramSession, ParserDQL.CompileContext paramCompileContext, int paramInt, Expression paramExpression)
  {
    super(paramInt, 2007, null);
    switch (paramInt)
    {
    case 58: 
    case 1201: 
      break;
    default: 
      throw Error.runtimeError(201, "");
    }
    this.isTransactionStatement = false;
    this.expression = paramExpression;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    switch (this.type)
    {
    case 58: 
      return this.sql;
    case 1201: 
      localStringBuffer.append(this.expression.getSQL());
    }
    return localStringBuffer.toString();
  }
  
  TableDerived[] getSubqueries(Session paramSession)
  {
    OrderedHashSet localOrderedHashSet = null;
    if (this.expression != null) {
      localOrderedHashSet = this.expression.collectAllSubqueries(localOrderedHashSet);
    }
    if ((localOrderedHashSet == null) || (localOrderedHashSet.size() == 0)) {
      return TableDerived.emptyArray;
    }
    TableDerived[] arrayOfTableDerived = new TableDerived[localOrderedHashSet.size()];
    localOrderedHashSet.toArray(arrayOfTableDerived);
    ArraySort.sort(arrayOfTableDerived, 0, arrayOfTableDerived.length, arrayOfTableDerived[0]);
    for (int i = 0; i < this.subqueries.length; i++) {
      arrayOfTableDerived[i].prepareTable();
    }
    return arrayOfTableDerived;
  }
  
  protected String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append('\n');
    for (int i = 0; i < paramInt; i++) {
      localStringBuffer.append(' ');
    }
    localStringBuffer.append("STATEMENT");
    return localStringBuffer.toString();
  }
  
  public Result execute(Session paramSession)
  {
    Result localResult;
    try
    {
      if (this.subqueries.length > 0) {
        materializeSubQueries(paramSession);
      }
      localResult = getResult(paramSession);
    }
    catch (Throwable localThrowable)
    {
      localResult = Result.newErrorResult(localThrowable, null);
    }
    if (localResult.isError()) {
      localResult.getException().setStatementType(this.group, this.type);
    }
    return localResult;
  }
  
  Result getResult(Session paramSession)
  {
    switch (this.type)
    {
    case 58: 
    case 1201: 
      Result localResult = this.expression.getResult(paramSession);
      if (localResult.isData())
      {
        RowSetNavigatorData localRowSetNavigatorData = new RowSetNavigatorData(paramSession, localResult.getNavigator());
        localResult.setNavigator(localRowSetNavigatorData);
      }
      return localResult;
    }
    throw Error.runtimeError(201, "");
  }
  
  public void resolve(Session paramSession) {}
  
  String describeImpl(Session paramSession)
    throws Exception
  {
    return getSQL();
  }
  
  void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet)
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
  
  void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.StatementExpression
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */