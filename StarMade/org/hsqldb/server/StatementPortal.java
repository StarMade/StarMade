package org.hsqldb.server;

import java.sql.SQLException;
import java.util.Map;
import org.hsqldb.Session;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.types.Type;

class StatementPortal
{
  public Object[] parameters;
  public Result bindResult;
  public Result ackResult;
  public String lcQuery;
  public String handle;
  private Map containingMap;
  private Session session;

  public StatementPortal(String paramString, OdbcPreparedStatement paramOdbcPreparedStatement, Map paramMap)
    throws RecoverableOdbcFailure
  {
    this(paramString, paramOdbcPreparedStatement, new Object[0], paramMap);
  }

  public StatementPortal(String paramString, OdbcPreparedStatement paramOdbcPreparedStatement, Object[] paramArrayOfObject, Map paramMap)
    throws RecoverableOdbcFailure
  {
    this.handle = paramString;
    this.lcQuery = paramOdbcPreparedStatement.query.toLowerCase();
    this.ackResult = paramOdbcPreparedStatement.ackResult;
    this.session = paramOdbcPreparedStatement.session;
    this.containingMap = paramMap;
    this.bindResult = Result.newPreparedExecuteRequest(paramOdbcPreparedStatement.ackResult.parameterMetaData.getParameterTypes(), paramOdbcPreparedStatement.ackResult.getStatementID());
    switch (this.bindResult.getType())
    {
    case 35:
      break;
    case 2:
      throw new RecoverableOdbcFailure(this.bindResult);
    default:
      throw new RecoverableOdbcFailure("Output Result from seconary Statement prep is of unexpected type: " + this.bindResult.getType());
    }
    if (paramArrayOfObject.length < 1)
    {
      this.parameters = new Object[0];
    }
    else
    {
      ResultMetaData localResultMetaData = paramOdbcPreparedStatement.ackResult.parameterMetaData;
      if (localResultMetaData == null)
        throw new RecoverableOdbcFailure("No metadata for Result ack");
      Type[] arrayOfType = localResultMetaData.getParameterTypes();
      if (arrayOfType.length != paramArrayOfObject.length)
        throw new RecoverableOdbcFailure(null, "Client didn't specify all " + arrayOfType.length + " parameters (" + paramArrayOfObject.length + ')', "08P01");
      this.parameters = new Object[paramArrayOfObject.length];
      try
      {
        for (int i = 0; i < this.parameters.length; i++)
          this.parameters[i] = ((paramArrayOfObject[i] instanceof String) ? PgType.getPgType(arrayOfType[i], true).getParameter((String)paramArrayOfObject[i], this.session) : paramArrayOfObject[i]);
      }
      catch (SQLException localSQLException)
      {
        throw new RecoverableOdbcFailure("Typing failure: " + localSQLException);
      }
    }
    paramMap.put(paramString, this);
  }

  public void close()
  {
    this.containingMap.remove(this.handle);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.StatementPortal
 * JD-Core Version:    0.6.2
 */