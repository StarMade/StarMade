package org.hsqldb;

import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.lib.LongKeyIntValueHashMap;
import org.hsqldb.lib.LongValueHashMap;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;

public final class StatementManager
{
  private Database database;
  private IntKeyHashMap schemaMap;
  private LongKeyHashMap csidMap;
  private LongKeyIntValueHashMap useMap;
  private long next_cs_id;
  
  StatementManager(Database paramDatabase)
  {
    this.database = paramDatabase;
    this.schemaMap = new IntKeyHashMap();
    this.csidMap = new LongKeyHashMap();
    this.useMap = new LongKeyIntValueHashMap();
    this.next_cs_id = 0L;
  }
  
  synchronized void reset()
  {
    this.schemaMap.clear();
    this.csidMap.clear();
    this.useMap.clear();
    this.next_cs_id = 0L;
  }
  
  private long nextID()
  {
    this.next_cs_id += 1L;
    return this.next_cs_id;
  }
  
  private long getStatementID(HsqlNameManager.HsqlName paramHsqlName, String paramString)
  {
    LongValueHashMap localLongValueHashMap = (LongValueHashMap)this.schemaMap.get(paramHsqlName.hashCode());
    if (localLongValueHashMap == null) {
      return -1L;
    }
    return localLongValueHashMap.get(paramString, -1);
  }
  
  public synchronized Statement getStatement(Session paramSession, long paramLong)
  {
    Statement localStatement = (Statement)this.csidMap.get(paramLong);
    if (localStatement == null) {
      return null;
    }
    if (localStatement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp())
    {
      localStatement = recompileStatement(paramSession, localStatement);
      if (localStatement == null)
      {
        freeStatement(paramLong);
        return null;
      }
      this.csidMap.put(paramLong, localStatement);
    }
    return localStatement;
  }
  
  public synchronized Statement getStatement(Session paramSession, Statement paramStatement)
  {
    long l = paramStatement.getID();
    Statement localStatement = (Statement)this.csidMap.get(l);
    if (localStatement != null) {
      return getStatement(paramSession, l);
    }
    if (paramStatement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp())
    {
      localStatement = recompileStatement(paramSession, paramStatement);
      if (localStatement == null)
      {
        freeStatement(l);
        return null;
      }
    }
    return localStatement;
  }
  
  private Statement recompileStatement(Session paramSession, Statement paramStatement)
  {
    HsqlNameManager.HsqlName localHsqlName1 = paramSession.getCurrentSchemaHsqlName();
    Statement localStatement1;
    try
    {
      HsqlNameManager.HsqlName localHsqlName2 = paramStatement.getSchemaName();
      int i = paramStatement.getCursorPropertiesRequest();
      if (localHsqlName2 != null) {
        paramSession.setSchema(localHsqlName2.name);
      }
      int j = paramStatement.generatedResultMetaData() != null ? 1 : 0;
      localStatement1 = paramSession.compileStatement(paramStatement.getSQL(), i);
      localStatement1.setCursorPropertiesRequest(i);
      Object localObject1;
      if (!paramStatement.getResultMetaData().areTypesCompatible(localStatement1.getResultMetaData()))
      {
        localObject1 = null;
        return localObject1;
      }
      if (!paramStatement.getParametersMetaData().areTypesCompatible(localStatement1.getParametersMetaData()))
      {
        localObject1 = null;
        return localObject1;
      }
      localStatement1.setCompileTimestamp(this.database.txManager.getGlobalChangeTimestamp());
      if (j != 0)
      {
        localObject1 = (StatementDML)paramStatement;
        localStatement1.setGeneratedColumnInfo(((StatementDML)localObject1).generatedType, ((StatementDML)localObject1).generatedInputMetaData);
      }
    }
    catch (Throwable localThrowable)
    {
      Statement localStatement2 = null;
      return localStatement2;
    }
    finally
    {
      paramSession.setCurrentSchemaHsqlName(localHsqlName1);
    }
    return localStatement1;
  }
  
  private long registerStatement(long paramLong, Statement paramStatement)
  {
    if (paramLong < 0L)
    {
      paramLong = nextID();
      int i = paramStatement.getSchemaName().hashCode();
      LongValueHashMap localLongValueHashMap = (LongValueHashMap)this.schemaMap.get(i);
      if (localLongValueHashMap == null)
      {
        localLongValueHashMap = new LongValueHashMap();
        this.schemaMap.put(i, localLongValueHashMap);
      }
      localLongValueHashMap.put(paramStatement.getSQL(), paramLong);
    }
    paramStatement.setID(paramLong);
    paramStatement.setCompileTimestamp(this.database.txManager.getGlobalChangeTimestamp());
    this.csidMap.put(paramLong, paramStatement);
    return paramLong;
  }
  
  synchronized void freeStatement(long paramLong)
  {
    if (paramLong == -1L) {
      return;
    }
    int i = this.useMap.get(paramLong, 1);
    if (i > 1)
    {
      this.useMap.put(paramLong, i - 1);
      return;
    }
    Statement localStatement = (Statement)this.csidMap.remove(paramLong);
    if (localStatement != null)
    {
      int j = localStatement.getSchemaName().hashCode();
      LongValueHashMap localLongValueHashMap = (LongValueHashMap)this.schemaMap.get(j);
      String str = localStatement.getSQL();
      localLongValueHashMap.remove(str);
    }
    this.useMap.remove(paramLong);
  }
  
  synchronized Statement compile(Session paramSession, Result paramResult)
    throws Throwable
  {
    int i = paramResult.getExecuteProperties();
    Statement localStatement = null;
    String str = paramResult.getMainString();
    long l = getStatementID(paramSession.currentSchema, str);
    if (l >= 0L)
    {
      localStatement = (Statement)this.csidMap.get(l);
      if ((localStatement != null) && (localStatement.getCursorPropertiesRequest() != i))
      {
        localStatement = null;
        l = -1L;
      }
    }
    if ((localStatement == null) || (!localStatement.isValid()) || (localStatement.getCompileTimestamp() < this.database.schemaManager.getSchemaChangeTimestamp()))
    {
      localStatement = paramSession.compileStatement(str, i);
      localStatement.setCursorPropertiesRequest(i);
      l = registerStatement(l, localStatement);
    }
    int j = this.useMap.get(l, 0) + 1;
    this.useMap.put(l, j);
    localStatement.setGeneratedColumnInfo(paramResult.getGeneratedResultType(), paramResult.getGeneratedResultMetaData());
    return localStatement;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.StatementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */