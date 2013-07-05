package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.result.Result;

public class StatementSimple extends Statement
{
  String sqlState;
  String message;
  HsqlNameManager.HsqlName label;
  ColumnSchema[] variables;
  int[] variableIndexes;

  StatementSimple(int paramInt, HsqlNameManager.HsqlName paramHsqlName)
  {
    super(paramInt, 2007);
    this.references = new OrderedHashSet();
    this.isTransactionStatement = false;
    this.label = paramHsqlName;
  }

  StatementSimple(int paramInt, String paramString1, String paramString2)
  {
    super(paramInt, 2007);
    this.references = new OrderedHashSet();
    this.isTransactionStatement = false;
    this.sqlState = paramString1;
    this.message = paramString2;
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    switch (this.type)
    {
    case 92:
      localStringBuffer.append("SIGNAL").append(' ');
      localStringBuffer.append("SQLSTATE");
      localStringBuffer.append(' ').append('\'').append(this.sqlState).append('\'');
      break;
    case 91:
      localStringBuffer.append("RESIGNAL").append(' ');
      localStringBuffer.append("SQLSTATE");
      localStringBuffer.append(' ').append('\'').append(this.sqlState).append('\'');
      break;
    case 102:
      localStringBuffer.append("ITERATE").append(' ').append(this.label);
      break;
    case 89:
      localStringBuffer.append("LEAVE").append(' ').append(this.label);
    }
    return localStringBuffer.toString();
  }

  protected String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append('\n');
    for (int i = 0; i < paramInt; i++)
      localStringBuffer.append(' ');
    localStringBuffer.append("STATEMENT");
    return localStringBuffer.toString();
  }

  public Result execute(Session paramSession)
  {
    Result localResult;
    try
    {
      localResult = getResult(paramSession);
    }
    catch (Throwable localThrowable)
    {
      localResult = Result.newErrorResult(localThrowable, null);
    }
    if (localResult.isError())
      localResult.getException().setStatementType(this.group, this.type);
    return localResult;
  }

  Result getResult(Session paramSession)
  {
    switch (this.type)
    {
    case 91:
    case 92:
      HsqlException localHsqlException = Error.error(this.message, this.sqlState);
      return Result.newErrorResult(localHsqlException);
    case 89:
    case 102:
      return Result.newPSMResult(this.type, this.label.name, null);
    }
    throw Error.runtimeError(201, "");
  }

  public void resolve(Session paramSession)
  {
    int i = 0;
    StatementCompound localStatementCompound;
    switch (this.type)
    {
    case 91:
    case 92:
      i = 1;
      break;
    case 102:
      localStatementCompound = this.parent;
    case 89:
    default:
      while (localStatementCompound != null)
      {
        if (localStatementCompound.isLoop)
        {
          if (this.label == null)
          {
            i = 1;
            break;
          }
          if ((localStatementCompound.label != null) && (this.label.name.equals(localStatementCompound.label.name)))
          {
            i = 1;
            break;
          }
        }
        localStatementCompound = localStatementCompound.parent;
        continue;
        i = 1;
        break;
        throw Error.runtimeError(201, "");
      }
    }
    if (i == 0)
      throw Error.error(5602);
  }

  public String describe(Session paramSession)
  {
    return "";
  }

  public boolean isCatalogLock()
  {
    return false;
  }

  public boolean isCatalogChange()
  {
    return false;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.StatementSimple
 * JD-Core Version:    0.6.2
 */