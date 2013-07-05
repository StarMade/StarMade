package org.hsqldb.server;

import org.hsqldb.result.Result;

class RecoverableOdbcFailure extends Exception
{
  private String clientMessage = null;
  private String sqlStateCode = null;
  private Result errorResult = null;

  public String getSqlStateCode()
  {
    return this.sqlStateCode;
  }

  public Result getErrorResult()
  {
    return this.errorResult;
  }

  public RecoverableOdbcFailure(Result paramResult)
  {
    this.errorResult = paramResult;
  }

  public RecoverableOdbcFailure(String paramString)
  {
    super(paramString);
    this.clientMessage = paramString;
  }

  public RecoverableOdbcFailure(String paramString1, String paramString2)
  {
    this(paramString1);
    this.sqlStateCode = paramString2;
  }

  public RecoverableOdbcFailure(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1);
    this.clientMessage = paramString2;
    this.sqlStateCode = paramString3;
  }

  public String getClientMessage()
  {
    return this.clientMessage;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.RecoverableOdbcFailure
 * JD-Core Version:    0.6.2
 */