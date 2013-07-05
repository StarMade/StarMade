package org.hsqldb.auth;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hsqldb.jdbc.JDBCArrayBasic;
import org.hsqldb.lib.FrameworkLogger;
import org.hsqldb.types.Type;

public class AuthBeanMultiplexer
{
  private static FrameworkLogger logger = FrameworkLogger.getLog(AuthBeanMultiplexer.class);
  private static AuthBeanMultiplexer singleton = new AuthBeanMultiplexer();
  private static Map<String, List<AuthFunctionBean>> beans = new HashMap();

  public static AuthBeanMultiplexer getSingleton()
  {
    return singleton;
  }

  public void clear()
  {
    beans.clear();
  }

  public void setAuthFunctionBeans(Map<String, List<AuthFunctionBean>> paramMap)
  {
    if (beans.size() > 0)
      throw new IllegalStateException("Use setAuthFunctionBeans(Map) only when the set is empty");
    beans.putAll(paramMap);
  }

  protected static String getUniqueNameFor(Connection paramConnection)
    throws SQLException
  {
    ResultSet localResultSet = paramConnection.createStatement().executeQuery("CALL database_name()");
    try
    {
      if (!localResultSet.next())
        throw new SQLException("Engine did not reveal unique database name");
      String str = localResultSet.getString(1);
      return str;
    }
    finally
    {
      if (localResultSet != null)
        try
        {
          localResultSet.close();
        }
        catch (SQLException localSQLException2)
        {
          logger.error("Failed to close ResultSet for retrieving db name");
        }
      localResultSet = null;
    }
  }

  public void setAuthFunctionBeans(Connection paramConnection, List<AuthFunctionBean> paramList)
    throws SQLException
  {
    setAuthFunctionBeans(getUniqueNameFor(paramConnection), paramList);
  }

  public void setAuthFunctionBeans(String paramString, List<AuthFunctionBean> paramList)
  {
    if ((paramString == null) || (paramString.length() != 16))
      throw new IllegalArgumentException(new StringBuilder().append("Database name not exactly 16 characters long: ").append(paramString).toString());
    Object localObject = (List)beans.get(paramString);
    if (localObject == null)
    {
      localObject = new ArrayList();
      beans.put(paramString, localObject);
    }
    else if (((List)localObject).size() > 0)
    {
      throw new IllegalStateException("Use setAuthFunctionBeans(String, List) only when the db's AuthFunctionBean list is empty");
    }
    ((List)localObject).addAll(paramList);
  }

  public void setAuthFunctionBean(Connection paramConnection, AuthFunctionBean paramAuthFunctionBean)
    throws SQLException
  {
    setAuthFunctionBeans(getUniqueNameFor(paramConnection), Collections.singletonList(paramAuthFunctionBean));
  }

  public void setAuthFunctionBean(String paramString, AuthFunctionBean paramAuthFunctionBean)
  {
    setAuthFunctionBeans(paramString, Collections.singletonList(paramAuthFunctionBean));
  }

  public static Array authenticate(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    if ((paramString1 == null) || (paramString1.length() != 16))
      throw new IllegalStateException(new StringBuilder().append("Internal problem.  Database name not exactly 16 characters long: ").append(paramString1).toString());
    List localList = (List)beans.get(paramString1);
    if (localList == null)
    {
      logger.error(new StringBuilder().append("Database '").append(paramString1).append("' has not been set up with ").append(AuthBeanMultiplexer.class.getName()).toString());
      throw new IllegalArgumentException(new StringBuilder().append("Database '").append(paramString1).append("' has not been set up with ").append(AuthBeanMultiplexer.class.getName()).toString());
    }
    Object localObject = null;
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      AuthFunctionBean localAuthFunctionBean = (AuthFunctionBean)localIterator.next();
      try
      {
        String[] arrayOfString = localAuthFunctionBean.authenticate(paramString2, paramString3);
        return arrayOfString == null ? null : new JDBCArrayBasic(arrayOfString, Type.SQL_VARCHAR);
      }
      catch (RuntimeException localRuntimeException)
      {
        if (localObject == null)
          localObject = localRuntimeException;
        logger.error(new StringBuilder().append("System failure of an AuthFunctionBean: ").append(localRuntimeException.getMessage() == null ? localRuntimeException.toString() : localRuntimeException.getMessage()).toString());
      }
      catch (Exception localException)
      {
        throw localException;
      }
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.auth.AuthBeanMultiplexer
 * JD-Core Version:    0.6.2
 */