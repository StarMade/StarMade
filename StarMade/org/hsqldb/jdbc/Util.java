package org.hsqldb.jdbc;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTransactionRollbackException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLWarning;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.result.Result;

public class Util
{
  static final void throwError(HsqlException paramHsqlException)
    throws SQLException
  {
    throw sqlException(paramHsqlException.getMessage(), paramHsqlException.getSQLState(), paramHsqlException.getErrorCode(), paramHsqlException);
  }
  
  static final void throwError(Result paramResult)
    throws SQLException
  {
    throw sqlException(paramResult.getMainString(), paramResult.getSubString(), paramResult.getErrorCode(), paramResult.getException());
  }
  
  public static final SQLException sqlException(HsqlException paramHsqlException)
  {
    return sqlException(paramHsqlException.getMessage(), paramHsqlException.getSQLState(), paramHsqlException.getErrorCode(), paramHsqlException);
  }
  
  public static final SQLException sqlException(HsqlException paramHsqlException, Throwable paramThrowable)
  {
    return sqlException(paramHsqlException.getMessage(), paramHsqlException.getSQLState(), paramHsqlException.getErrorCode(), paramThrowable);
  }
  
  public static final SQLException sqlException(int paramInt)
  {
    return sqlException(Error.error(paramInt));
  }
  
  public static final SQLException sqlExceptionSQL(int paramInt)
  {
    return sqlException(Error.error(paramInt));
  }
  
  public static final SQLException sqlException(int paramInt, String paramString)
  {
    return sqlException(Error.error(paramInt, paramString));
  }
  
  public static final SQLException sqlException(int paramInt, String paramString, Throwable paramThrowable)
  {
    return sqlException(Error.error(paramInt, paramString), paramThrowable);
  }
  
  public static final SQLException sqlException(int paramInt1, int paramInt2)
  {
    return sqlException(Error.error(paramInt1, paramInt2));
  }
  
  static final SQLException sqlException(int paramInt1, int paramInt2, Object[] paramArrayOfObject)
  {
    return sqlException(Error.error(null, paramInt1, paramInt2, paramArrayOfObject));
  }
  
  static final SQLException notSupported()
  {
    HsqlException localHsqlException = Error.error(1500);
    return new SQLFeatureNotSupportedException(localHsqlException.getMessage(), localHsqlException.getSQLState(), -1500);
  }
  
  static SQLException notUpdatableColumn()
  {
    return sqlException(2500);
  }
  
  public static SQLException nullArgument()
  {
    return sqlException(423);
  }
  
  static SQLException nullArgument(String paramString)
  {
    return sqlException(423, paramString + ": null");
  }
  
  public static SQLException invalidArgument()
  {
    return sqlException(423);
  }
  
  public static SQLException invalidArgument(String paramString)
  {
    return sqlException(423, paramString);
  }
  
  public static SQLException outOfRangeArgument()
  {
    return sqlException(423);
  }
  
  public static SQLException outOfRangeArgument(String paramString)
  {
    return sqlException(423, paramString);
  }
  
  public static SQLException connectionClosedException()
  {
    return sqlException(1303);
  }
  
  public static SQLWarning sqlWarning(Result paramResult)
  {
    return new SQLWarning(paramResult.getMainString(), paramResult.getSubString(), paramResult.getErrorCode());
  }
  
  public static SQLException sqlException(Throwable paramThrowable)
  {
    return new SQLNonTransientConnectionException(paramThrowable);
  }
  
  public static SQLException sqlException(Result paramResult)
  {
    return sqlException(paramResult.getMainString(), paramResult.getSubString(), paramResult.getErrorCode(), paramResult.getException());
  }
  
  public static final SQLException sqlException(String paramString1, String paramString2, int paramInt, Throwable paramThrowable)
  {
    if (paramString2.startsWith("08"))
    {
      if (!paramString2.endsWith("3")) {
        return new SQLTransientConnectionException(paramString1, paramString2, paramInt, paramThrowable);
      }
      return new SQLNonTransientConnectionException(paramString1, paramString2, paramInt, paramThrowable);
    }
    if (paramString2.startsWith("22")) {
      return new SQLDataException(paramString1, paramString2, paramInt, paramThrowable);
    }
    if (paramString2.startsWith("23")) {
      return new SQLIntegrityConstraintViolationException(paramString1, paramString2, paramInt, paramThrowable);
    }
    if (paramString2.startsWith("28")) {
      return new SQLInvalidAuthorizationSpecException(paramString1, paramString2, paramInt, paramThrowable);
    }
    if ((paramString2.startsWith("42")) || (paramString2.startsWith("37")) || (paramString2.startsWith("2A"))) {
      return new SQLSyntaxErrorException(paramString1, paramString2, paramInt, paramThrowable);
    }
    if (paramString2.startsWith("40")) {
      return new SQLTransactionRollbackException(paramString1, paramString2, paramInt, paramThrowable);
    }
    if (paramString2.startsWith("0A")) {
      return new SQLFeatureNotSupportedException(paramString1, paramString2, paramInt, paramThrowable);
    }
    return new SQLException(paramString1, paramString2, paramInt, paramThrowable);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.jdbc.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */