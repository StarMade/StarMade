package org.hsqldb.auth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import org.hsqldb.lib.FrameworkLogger;

public class AuthUtils
{
  private static FrameworkLogger logger = FrameworkLogger.getLog(AuthUtils.class);

  static String getInitialSchema(Connection paramConnection)
    throws SQLException
  {
    ResultSet localResultSet = paramConnection.createStatement().executeQuery("SELECT initial_schema FROM information_schema.system_users\nWHERE user_name = current_user");
    try
    {
      if (!localResultSet.next())
        throw new IllegalStateException("Failed to retrieve initial_schema for current user");
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
          logger.error("Failed to close ResultSet for retrieving initial schema");
        }
      localResultSet = null;
    }
  }

  static Set getEnabledRoles(Connection paramConnection)
    throws SQLException
  {
    HashSet localHashSet = new HashSet();
    ResultSet localResultSet = paramConnection.createStatement().executeQuery("SELECT * FROM information_schema.enabled_roles");
    try
    {
      while (localResultSet.next())
        localHashSet.add(localResultSet.getString(1));
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
    return localHashSet;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.auth.AuthUtils
 * JD-Core Version:    0.6.2
 */