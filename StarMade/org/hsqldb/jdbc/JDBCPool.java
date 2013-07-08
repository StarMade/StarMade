package org.hsqldb.jdbc;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Wrapper;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import javax.sql.StatementEvent;
import javax.sql.StatementEventListener;
import org.hsqldb.jdbc.pool.JDBCPooledConnection;
import org.hsqldb.jdbc.pool.JDBCPooledDataSource;

public class JDBCPool
  implements DataSource, Serializable, Referenceable, ConnectionEventListener, StatementEventListener, Wrapper
{
  AtomicIntegerArray states;
  JDBCPooledConnection[] connections;
  JDBCPooledDataSource source = new JDBCPooledDataSource();
  volatile boolean closed;
  
  public Connection getConnection()
    throws SQLException
  {
    int i = 300;
    if (this.source.loginTimeout != 0) {
      i = this.source.loginTimeout * 10;
    }
    if (this.closed) {
      throw new SQLException("connection pool is closed");
    }
    for (int j = 0; j < i; j++)
    {
      for (int k = 0; k < this.states.length(); k++)
      {
        if (this.states.compareAndSet(k, 1, 2)) {
          return this.connections[k].getConnection();
        }
        if (this.states.compareAndSet(k, 0, 2)) {
          try
          {
            JDBCPooledConnection localJDBCPooledConnection = (JDBCPooledConnection)this.source.getPooledConnection();
            localJDBCPooledConnection.addConnectionEventListener(this);
            localJDBCPooledConnection.addStatementEventListener(this);
            this.connections[k] = localJDBCPooledConnection;
            return this.connections[k].getConnection();
          }
          catch (SQLException localSQLException)
          {
            this.states.set(k, 0);
          }
        }
      }
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException localInterruptedException) {}
    }
    throw Util.invalidArgument();
  }
  
  public Connection getConnection(String paramString1, String paramString2)
    throws SQLException
  {
    return this.source.getPooledConnection(paramString1, paramString2).getConnection();
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
    if (isWrapperFor(paramClass)) {
      return this;
    }
    throw Util.invalidArgument("iface: " + paramClass);
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
    return (paramClass != null) && (paramClass.isAssignableFrom(getClass()));
  }
  
  public Reference getReference()
    throws NamingException
  {
    String str = "org.hsqldb.jdbc.JDBCDataSourceFactory";
    Reference localReference = new Reference(getClass().getName(), str, null);
    localReference.add(new StringRefAddr("database", this.source.getDatabase()));
    localReference.add(new StringRefAddr("user", this.source.getUser()));
    localReference.add(new StringRefAddr("password", this.source.password));
    localReference.add(new StringRefAddr("loginTimeout", Integer.toString(this.source.loginTimeout)));
    localReference.add(new StringRefAddr("poolSize", Integer.toString(this.connections.length)));
    return localReference;
  }
  
  public void connectionClosed(ConnectionEvent paramConnectionEvent)
  {
    PooledConnection localPooledConnection = (PooledConnection)paramConnectionEvent.getSource();
    for (int i = 0; i < this.connections.length; i++) {
      if (this.connections[i] == localPooledConnection)
      {
        this.states.set(i, 1);
        break;
      }
    }
  }
  
  public void connectionErrorOccurred(ConnectionEvent paramConnectionEvent)
  {
    PooledConnection localPooledConnection = (PooledConnection)paramConnectionEvent.getSource();
    for (int i = 0; i < this.connections.length; i++) {
      if (this.connections[i] == localPooledConnection)
      {
        this.states.set(i, 2);
        this.connections[i] = null;
        this.states.set(i, 0);
        break;
      }
    }
  }
  
  public void statementClosed(StatementEvent paramStatementEvent) {}
  
  public void statementErrorOccurred(StatementEvent paramStatementEvent) {}
  
  public PrintWriter getLogWriter()
    throws SQLException
  {
    return this.source.getLogWriter();
  }
  
  public void setLogWriter(PrintWriter paramPrintWriter)
    throws SQLException
  {
    this.source.setLogWriter(paramPrintWriter);
  }
  
  public void setLoginTimeout(int paramInt)
    throws SQLException
  {
    this.source.setLoginTimeout(paramInt);
  }
  
  public int getLoginTimeout()
    throws SQLException
  {
    return this.source.getLoginTimeout();
  }
  
  public String getDescription()
  {
    return "org.hsqldb.jdbc.JDBCPool max size " + this.connections.length;
  }
  
  public String getDataSourceName()
  {
    return "org.hsqldb.jdbc.JDBCPool";
  }
  
  public String getDatabaseName()
  {
    return this.source.getUrl();
  }
  
  public String getDatabase()
  {
    return this.source.getDatabase();
  }
  
  public String getUrl()
  {
    return this.source.getUrl();
  }
  
  public String getUser()
  {
    return this.source.getUser();
  }
  
  public void setDatabaseName(String paramString)
  {
    this.source.setDatabaseName(paramString);
  }
  
  public void setDatabase(String paramString)
  {
    this.source.setDatabase(paramString);
  }
  
  public void setUrl(String paramString)
  {
    this.source.setUrl(paramString);
  }
  
  public void setPassword(String paramString)
  {
    this.source.setPassword(paramString);
  }
  
  public void setUser(String paramString)
  {
    this.source.setUser(paramString);
  }
  
  public void setProperties(Properties paramProperties)
  {
    this.source.setProperties(paramProperties);
  }
  
  public Logger getParentLogger()
    throws SQLFeatureNotSupportedException
  {
    throw ((SQLFeatureNotSupportedException)Util.notSupported());
  }
  
  public JDBCPool()
  {
    this(10);
  }
  
  public JDBCPool(int paramInt)
  {
    this.connections = new JDBCPooledConnection[paramInt];
    this.states = new AtomicIntegerArray(paramInt);
  }
  
  public void close(int paramInt)
    throws SQLException
  {
    if ((paramInt < 0) || (paramInt > 60)) {
      throw Util.outOfRangeArgument();
    }
    if (this.closed) {
      return;
    }
    this.closed = true;
    try
    {
      Thread.sleep(1000 * paramInt);
    }
    catch (Throwable localThrowable) {}
    for (int i = 0; i < this.connections.length; i++) {
      if (this.connections[i] != null) {
        this.connections[i].release();
      }
    }
    for (i = 0; i < this.connections.length; i++) {
      this.connections[i] = null;
    }
  }
  
  static abstract interface RefState
  {
    public static final int empty = 0;
    public static final int available = 1;
    public static final int allocated = 2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.jdbc.JDBCPool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */