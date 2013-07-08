package org.hsqldb.server;

import java.sql.SQLException;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.Util;

public class HsqlServerFactory
{
  public static HsqlSocketRequestHandler createHsqlServer(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    throws SQLException
  {
    ServerProperties localServerProperties = new ServerProperties(1);
    localServerProperties.setProperty("server.dbname.0", "");
    localServerProperties.setProperty("server.database.0", paramString);
    localServerProperties.setProperty("server.trace", paramBoolean1);
    localServerProperties.setProperty("server.silent", paramBoolean2);
    Server localServer = new Server();
    try
    {
      localServer.setProperties(localServerProperties);
    }
    catch (Exception localException)
    {
      throw new SQLException("Failed to set server properties: " + localException);
    }
    if (!localServer.openDatabases())
    {
      Throwable localThrowable = localServer.getServerError();
      if ((localThrowable instanceof HsqlException)) {
        throw Util.sqlException((HsqlException)localThrowable);
      }
      throw Util.sqlException(Error.error(458));
    }
    localServer.setState(1);
    return localServer;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.HsqlServerFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */