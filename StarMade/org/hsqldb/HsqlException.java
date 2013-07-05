package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.result.Result;

public class HsqlException extends RuntimeException
{
  public static final HsqlException[] emptyArray = new HsqlException[0];
  public static final HsqlException noDataCondition = Error.error(1100);
  private String message;
  private String state;
  private int code;
  private int level;
  private int statementGroup;
  private int statementCode;

  public HsqlException(Throwable paramThrowable, String paramString1, String paramString2, int paramInt)
  {
    super(paramThrowable);
    this.message = paramString1;
    this.state = paramString2;
    this.code = paramInt;
  }

  public HsqlException(Result paramResult)
  {
    this.message = paramResult.getMainString();
    this.state = paramResult.getSubString();
    this.code = paramResult.getErrorCode();
  }

  public HsqlException(Throwable paramThrowable, String paramString, int paramInt)
  {
    super(paramThrowable);
    this.message = paramThrowable.toString();
    this.state = paramString;
    this.code = paramInt;
  }

  public String getMessage()
  {
    return this.message;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public String getSQLState()
  {
    return this.state;
  }

  public int getErrorCode()
  {
    return this.code;
  }

  public void setLevel(int paramInt)
  {
    this.level = paramInt;
  }

  public int getLevel()
  {
    return this.level;
  }

  public int getStatementCode()
  {
    return this.statementCode;
  }

  public void setStatementType(int paramInt1, int paramInt2)
  {
    this.statementGroup = paramInt1;
    this.statementCode = paramInt2;
  }

  public int hashCode()
  {
    return this.code;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof HsqlException))
    {
      HsqlException localHsqlException = (HsqlException)paramObject;
      return (this.code == localHsqlException.code) && (equals(this.state, localHsqlException.state)) && (equals(this.message, localHsqlException.message));
    }
    return false;
  }

  private static boolean equals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2)
      return true;
    if ((paramObject1 == null) || (paramObject2 == null))
      return false;
    return paramObject1.equals(paramObject2);
  }

  public static class HsqlRuntimeMemoryError extends OutOfMemoryError
  {
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.HsqlException
 * JD-Core Version:    0.6.2
 */