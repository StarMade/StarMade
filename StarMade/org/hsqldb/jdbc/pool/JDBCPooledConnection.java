package org.hsqldb.jdbc.pool;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.jdbc.JDBCConnectionEventListener;
import org.hsqldb.lib.OrderedHashSet;

public class JDBCPooledConnection
  implements PooledConnection, JDBCConnectionEventListener
{
  protected OrderedHashSet listeners = new OrderedHashSet();
  protected JDBCConnection connection;
  protected JDBCConnection userConnection;
  protected boolean isInUse;

  public synchronized Connection getConnection()
    throws SQLException
  {
    if (this.isInUse)
      throw new SQLException("Connection in use");
    this.isInUse = true;
    this.userConnection = new JDBCConnection(this.connection, this);
    return this.userConnection;
  }

  public void close()
    throws SQLException
  {
    if (this.connection != null)
    {
      this.connection.closeFully();
      this.connection = null;
    }
  }

  public void addConnectionEventListener(ConnectionEventListener paramConnectionEventListener)
  {
    this.listeners.add(paramConnectionEventListener);
  }

  public void removeConnectionEventListener(ConnectionEventListener paramConnectionEventListener)
  {
    this.listeners.remove(paramConnectionEventListener);
  }

  public void addStatementEventListener(StatementEventListener paramStatementEventListener)
  {
  }

  public void removeStatementEventListener(StatementEventListener paramStatementEventListener)
  {
  }

  public synchronized void connectionClosed()
  {
    ConnectionEvent localConnectionEvent = new ConnectionEvent(this);
    this.userConnection = null;
    reset();
    for (int i = 0; i < this.listeners.size(); i++)
    {
      ConnectionEventListener localConnectionEventListener = (ConnectionEventListener)this.listeners.get(i);
      localConnectionEventListener.connectionClosed(localConnectionEvent);
    }
  }

  public synchronized void connectionErrorOccured(SQLException paramSQLException)
  {
    ConnectionEvent localConnectionEvent = new ConnectionEvent(this, paramSQLException);
    reset();
    for (int i = 0; i < this.listeners.size(); i++)
    {
      ConnectionEventListener localConnectionEventListener = (ConnectionEventListener)this.listeners.get(i);
      localConnectionEventListener.connectionErrorOccurred(localConnectionEvent);
    }
  }

  public synchronized boolean isInUse()
  {
    return this.isInUse;
  }

  public synchronized void reset()
  {
    if (this.userConnection != null)
      try
      {
        this.userConnection.close();
      }
      catch (SQLException localSQLException1)
      {
      }
    try
    {
      this.connection.reset();
    }
    catch (SQLException localSQLException2)
    {
    }
    this.isInUse = false;
  }

  public synchronized void release()
  {
    if (this.userConnection != null)
      try
      {
        this.userConnection.close();
      }
      catch (SQLException localSQLException1)
      {
      }
    try
    {
      this.connection.close();
    }
    catch (SQLException localSQLException2)
    {
    }
    this.isInUse = false;
  }

  public JDBCPooledConnection(JDBCConnection paramJDBCConnection)
  {
    this.connection = paramJDBCConnection;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.pool.JDBCPooledConnection
 * JD-Core Version:    0.6.2
 */