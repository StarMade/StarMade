package org.hsqldb.jdbc;

import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
import javax.sql.DataSource;

public class JDBCDataSourceFactory
  implements ObjectFactory
{
  private static final String urlName = "url";
  private static final String databaseName = "database";
  private static final String userName = "user";
  private static final String userNameName = "username";
  private static final String passwordName = "password";
  private static final String loginTimeoutName = "loginTimeout";
  private static final String bdsClassName = "org.hsqldb.jdbc.JDBCDataSource";
  private static final String poolClassName = "org.hsqldb.jdbc.JDBCPool";
  private static final String pdsClassName = "org.hsqldb.jdbc.pool.JDBCPooledDataSource";
  private static final String xdsClassName = "org.hsqldb.jdbc.pool.JDBCXADataSource";

  public static DataSource createDataSource(Properties paramProperties)
    throws Exception
  {
    JDBCDataSource localJDBCDataSource = (JDBCDataSource)Class.forName("org.hsqldb.jdbc.JDBCDataSource").newInstance();
    String str = paramProperties.getProperty("database");
    if (str == null)
      str = paramProperties.getProperty("url");
    localJDBCDataSource.setDatabase(str);
    str = paramProperties.getProperty("user");
    if (str == null)
      str = paramProperties.getProperty("username");
    localJDBCDataSource.setUser(str);
    str = paramProperties.getProperty("password");
    localJDBCDataSource.setPassword(str);
    str = paramProperties.getProperty("loginTimeout");
    if (str != null)
    {
      str = str.trim();
      if (str.length() > 0)
        try
        {
          localJDBCDataSource.setLoginTimeout(Integer.parseInt(str));
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
    }
    return localJDBCDataSource;
  }

  public Object getObjectInstance(Object paramObject, Name paramName, Context paramContext, Hashtable paramHashtable)
    throws Exception
  {
    if (!(paramObject instanceof Reference))
      return null;
    Reference localReference = (Reference)paramObject;
    String str1 = localReference.getClassName();
    if (("org.hsqldb.jdbc.JDBCDataSource".equals(str1)) || ("org.hsqldb.jdbc.JDBCPool".equals(str1)) || ("org.hsqldb.jdbc.pool.JDBCPooledDataSource".equals(str1)) || ("org.hsqldb.jdbc.pool.JDBCXADataSource".equals(str1)))
    {
      JDBCCommonDataSource localJDBCCommonDataSource = (JDBCCommonDataSource)Class.forName(str1).newInstance();
      RefAddr localRefAddr = localReference.get("database");
      if (localRefAddr == null)
        throw new Exception(str1 + ": RefAddr not set: database");
      Object localObject = localRefAddr.getContent();
      if (!(localObject instanceof String))
        throw new Exception(str1 + ": invalid RefAddr: database");
      localJDBCCommonDataSource.setDatabase((String)localObject);
      localRefAddr = localReference.get("user");
      if (localRefAddr == null)
        throw new Exception(str1 + ": RefAddr not set: user");
      localObject = localReference.get("user").getContent();
      if (!(localObject instanceof String))
        throw new Exception(str1 + ": invalid RefAddr: user");
      localJDBCCommonDataSource.setUser((String)localObject);
      localRefAddr = localReference.get("password");
      if (localRefAddr == null)
      {
        localObject = "";
      }
      else
      {
        localObject = localReference.get("password").getContent();
        if (!(localObject instanceof String))
          throw new Exception(str1 + ": invalid RefAddr: password");
      }
      localJDBCCommonDataSource.setPassword((String)localObject);
      localRefAddr = localReference.get("loginTimeout");
      if (localRefAddr != null)
      {
        localObject = localRefAddr.getContent();
        if ((localObject instanceof String))
        {
          String str2 = ((String)localObject).trim();
          if (str2.length() > 0)
            try
            {
              localJDBCCommonDataSource.setLoginTimeout(Integer.parseInt(str2));
            }
            catch (NumberFormatException localNumberFormatException)
            {
            }
        }
      }
      return localJDBCCommonDataSource;
    }
    return null;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCDataSourceFactory
 * JD-Core Version:    0.6.2
 */