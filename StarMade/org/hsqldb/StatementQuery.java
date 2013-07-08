package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;

public class StatementQuery
  extends StatementDMQL
{
  StatementQuery(Session paramSession, QueryExpression paramQueryExpression, ParserDQL.CompileContext paramCompileContext)
  {
    super(85, 2003, paramSession.getCurrentSchemaHsqlName());
    this.statementReturnType = 2;
    this.queryExpression = paramQueryExpression;
    setDatabseObjects(paramSession, paramCompileContext);
    checkAccessRights(paramSession);
  }
  
  Result getResult(Session paramSession)
  {
    Result localResult = this.queryExpression.getResult(paramSession, paramSession.getMaxRows());
    localResult.setStatement(this);
    return localResult;
  }
  
  public ResultMetaData getResultMetaData()
  {
    switch (this.type)
    {
    case 85: 
      return this.queryExpression.getMetaData();
    case 65: 
      return this.queryExpression.getMetaData();
    }
    throw Error.runtimeError(201, "StatementQuery.getResultMetaData()");
  }
  
  void collectTableNamesForRead(OrderedHashSet paramOrderedHashSet)
  {
    this.queryExpression.getBaseTableNames(paramOrderedHashSet);
    for (int i = 0; i < this.subqueries.length; i++) {
      if (this.subqueries[i].queryExpression != null) {
        this.subqueries[i].queryExpression.getBaseTableNames(paramOrderedHashSet);
      }
    }
    for (i = 0; i < this.routines.length; i++) {
      paramOrderedHashSet.addAll(this.routines[i].getTableNamesForRead());
    }
  }
  
  void collectTableNamesForWrite(OrderedHashSet paramOrderedHashSet)
  {
    if (this.queryExpression.isUpdatable) {
      this.queryExpression.getBaseTableNames(paramOrderedHashSet);
    }
  }
  
  public int getResultProperties()
  {
    return this.queryExpression.isUpdatable ? 8 : 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.StatementQuery
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */