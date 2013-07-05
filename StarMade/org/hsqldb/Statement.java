package org.hsqldb;

import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;

public abstract class Statement
{
  static final int META_RESET_VIEWS = 1;
  static final int META_RESET_STATEMENTS = 2;
  static final Statement[] emptyArray = new Statement[0];
  final int type;
  int group;
  boolean isLogged = true;
  boolean isValid = true;
  int statementReturnType = 1;
  HsqlNameManager.HsqlName schemaName;
  Routine root;
  StatementCompound parent;
  boolean isError;
  boolean isTransactionStatement;
  boolean isExplain;
  String sql;
  long id;
  long compileTimestamp;
  HsqlNameManager.HsqlName[] readTableNames = HsqlNameManager.HsqlName.emptyArray;
  HsqlNameManager.HsqlName[] writeTableNames = HsqlNameManager.HsqlName.emptyArray;
  OrderedHashSet references;
  int cursorPropertiesRequest;

  public abstract Result execute(Session paramSession);

  public void setParameters(ExpressionColumn[] paramArrayOfExpressionColumn)
  {
  }

  Statement(int paramInt)
  {
    this.type = paramInt;
  }

  Statement(int paramInt1, int paramInt2)
  {
    this.type = paramInt1;
    this.group = paramInt2;
  }

  public final boolean isError()
  {
    return this.isError;
  }

  public boolean isTransactionStatement()
  {
    return this.isTransactionStatement;
  }

  public boolean isAutoCommitStatement()
  {
    return false;
  }

  public void setCompileTimestamp(long paramLong)
  {
    this.compileTimestamp = paramLong;
  }

  public long getCompileTimestamp()
  {
    return this.compileTimestamp;
  }

  public final void setSQL(String paramString)
  {
    this.sql = paramString;
  }

  public String getSQL()
  {
    return this.sql;
  }

  public OrderedHashSet getReferences()
  {
    return this.references;
  }

  public final void setDescribe()
  {
    this.isExplain = true;
  }

  public abstract String describe(Session paramSession);

  public HsqlNameManager.HsqlName getSchemaName()
  {
    return this.schemaName;
  }

  public final void setSchemaHsqlName(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.schemaName = paramHsqlName;
  }

  public final void setID(long paramLong)
  {
    this.id = paramLong;
  }

  public final long getID()
  {
    return this.id;
  }

  public final int getType()
  {
    return this.type;
  }

  public final int getGroup()
  {
    return this.group;
  }

  public final boolean isValid()
  {
    return this.isValid;
  }

  public final boolean isLogged()
  {
    return this.isLogged;
  }

  public void clearVariables()
  {
  }

  public void resolve(Session paramSession)
  {
  }

  public final HsqlNameManager.HsqlName[] getTableNamesForRead()
  {
    return this.readTableNames;
  }

  public final HsqlNameManager.HsqlName[] getTableNamesForWrite()
  {
    return this.writeTableNames;
  }

  public boolean isCatalogLock()
  {
    switch (this.group)
    {
    case 2002:
      if (this.type == 134)
        return false;
    case 2001:
    case 2012:
      return true;
    case 2014:
      return true;
    }
    return false;
  }

  public boolean isCatalogChange()
  {
    switch (this.group)
    {
    case 2001:
    case 2002:
    case 2012:
      return true;
    }
    return false;
  }

  public void setParent(StatementCompound paramStatementCompound)
  {
    this.parent = paramStatementCompound;
  }

  public void setRoot(Routine paramRoutine)
  {
    this.root = paramRoutine;
  }

  public boolean hasGeneratedColumns()
  {
    return false;
  }

  public ResultMetaData generatedResultMetaData()
  {
    return null;
  }

  public void setGeneratedColumnInfo(int paramInt, ResultMetaData paramResultMetaData)
  {
  }

  public ResultMetaData getResultMetaData()
  {
    return ResultMetaData.emptyResultMetaData;
  }

  public ResultMetaData getParametersMetaData()
  {
    return ResultMetaData.emptyParamMetaData;
  }

  public int getResultProperties()
  {
    return 0;
  }

  public int getStatementReturnType()
  {
    return this.statementReturnType;
  }

  public int getCursorPropertiesRequest()
  {
    return this.cursorPropertiesRequest;
  }

  public void setCursorPropertiesRequest(int paramInt)
  {
    this.cursorPropertiesRequest = paramInt;
  }

  public void clearStructures(Session paramSession)
  {
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.Statement
 * JD-Core Version:    0.6.2
 */