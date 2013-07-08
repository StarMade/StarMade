package org.hsqldb.jdbc;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.sql.CommonDataSource;

public abstract class JDBCCommonDataSource
  implements CommonDataSource, Serializable
{
  protected Properties connectionProps = new Properties();
  protected String description = null;
  protected String dataSourceName = null;
  protected String serverName = null;
  protected String networkProtocol = null;
  protected int loginTimeout = 0;
  protected transient PrintWriter logWriter;
  protected String user = null;
  protected String password = null;
  protected String url = null;
  
  public PrintWriter getLogWriter()
    throws SQLException
  {
    return this.logWriter;
  }
  
  public void setLogWriter(PrintWriter paramPrintWriter)
    throws SQLException
  {
    this.logWriter = paramPrintWriter;
  }
  
  public void setLoginTimeout(int paramInt)
    throws SQLException
  {
    this.loginTimeout = paramInt;
    this.connectionProps.setProperty("loginTimeout", Integer.toString(this.loginTimeout));
  }
  
  public int getLoginTimeout()
    throws SQLException
  {
    return this.loginTimeout;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getDataSourceName()
  {
    return this.dataSourceName;
  }
  
  public String getNetworkProtocol()
  {
    return this.networkProtocol;
  }
  
  public String getServerName()
  {
    return this.serverName;
  }
  
  public String getDatabaseName()
  {
    return this.url;
  }
  
  public String getDatabase()
  {
    return this.url;
  }
  
  public String getUrl()
  {
    return this.url;
  }
  
  public String getUser()
  {
    return this.user;
  }
  
  public void setDatabaseName(String paramString)
  {
    this.url = paramString;
  }
  
  public void setDatabase(String paramString)
  {
    this.url = paramString;
  }
  
  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.password = paramString;
    this.connectionProps.setProperty("password", paramString);
  }
  
  public void setUser(String paramString)
  {
    this.user = paramString;
    this.connectionProps.setProperty("user", paramString);
  }
  
  public void setProperties(Properties paramProperties)
  {
    this.connectionProps = (paramProperties == null ? new Properties() : (Properties)paramProperties.clone());
    if (this.user != null) {
      paramProperties.setProperty("user", this.user);
    }
    if (this.password != null) {
      paramProperties.setProperty("password", this.password);
    }
    if (this.loginTimeout != 0) {
      paramProperties.setProperty("loginTimeout", Integer.toString(this.loginTimeout));
    }
  }
  
  public Logger getParentLogger()
    throws SQLFeatureNotSupportedException
  {
    throw ((SQLFeatureNotSupportedException)Util.notSupported());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCCommonDataSource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */