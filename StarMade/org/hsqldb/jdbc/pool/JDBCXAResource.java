package org.hsqldb.jdbc.pool;

import java.sql.SQLException;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import org.hsqldb.HsqlException;
import org.hsqldb.SessionInterface;
import org.hsqldb.jdbc.JDBCConnection;

public class JDBCXAResource
  implements XAResource
{
  private JDBCConnection connection;
  private boolean originalAutoCommitMode;
  static int XA_STATE_INITIAL = 0;
  static int XA_STATE_STARTED = 1;
  static int XA_STATE_ENDED = 2;
  static int XA_STATE_PREPARED = 3;
  static int XA_STATE_DISPOSED = 4;
  int state = XA_STATE_INITIAL;
  private JDBCXADataSource xaDataSource;
  Xid xid = null;

  public boolean withinGlobalTransaction()
  {
    return this.state == XA_STATE_STARTED;
  }

  private void validateXid(Xid paramXid)
    throws XAException
  {
    if (paramXid == null)
      throw new XAException("Null Xid");
    if (this.xid == null)
      throw new XAException("There is no live transaction for this XAResource");
    if (!paramXid.equals(this.xid))
      throw new XAException("Given Xid is not that associated with this XAResource object");
  }

  public JDBCXAResource(JDBCXADataSource paramJDBCXADataSource, JDBCConnection paramJDBCConnection)
  {
    this.connection = paramJDBCConnection;
    this.xaDataSource = paramJDBCXADataSource;
  }

  JDBCXADataSource getXADataSource()
  {
    return this.xaDataSource;
  }

  public void commit(Xid paramXid, boolean paramBoolean)
    throws XAException
  {
    JDBCXAResource localJDBCXAResource = this.xaDataSource.getResource(paramXid);
    if (localJDBCXAResource == null)
      throw new XAException("The XADataSource has no such Xid:  " + paramXid);
    localJDBCXAResource.commitThis(paramBoolean);
  }

  public void commitThis(boolean paramBoolean)
    throws XAException
  {
    if ((paramBoolean) && (this.state == XA_STATE_PREPARED))
      throw new XAException("Transaction is in a 2-phase state when 1-phase is requested");
    if ((!paramBoolean) && (this.state != XA_STATE_PREPARED))
      throw new XAException("Attempt to do a 2-phase commit when transaction is not prepared");
    try
    {
      this.connection.commit();
    }
    catch (SQLException localSQLException)
    {
      throw new XAException(localSQLException.toString());
    }
    dispose();
  }

  private void dispose()
  {
    this.state = XA_STATE_DISPOSED;
    this.xaDataSource.removeResource(this.xid);
    this.xid = null;
  }

  public void end(Xid paramXid, int paramInt)
    throws XAException
  {
    validateXid(paramXid);
    if (this.state != XA_STATE_STARTED)
      throw new XAException("Invalid XAResource state");
    if (paramInt == 67108864);
    this.state = XA_STATE_ENDED;
    try
    {
      this.connection.setAutoCommit(this.originalAutoCommitMode);
    }
    catch (SQLException localSQLException)
    {
      throw new XAException(localSQLException.toString());
    }
  }

  public void forget(Xid paramXid)
    throws XAException
  {
    validateXid(paramXid);
    if (this.state != XA_STATE_PREPARED)
      throw new XAException("Attempted to forget a XAResource that is not in a heuristically completed state");
    dispose();
    this.state = XA_STATE_INITIAL;
  }

  public int getTransactionTimeout()
    throws XAException
  {
    throw new XAException("Transaction timeouts not implemented yet");
  }

  public boolean isSameRM(XAResource paramXAResource)
    throws XAException
  {
    if (!(paramXAResource instanceof JDBCXAResource))
      return false;
    return this.xaDataSource == ((JDBCXAResource)paramXAResource).getXADataSource();
  }

  public int prepare(Xid paramXid)
    throws XAException
  {
    JDBCXAResource localJDBCXAResource = this.xaDataSource.getResource(paramXid);
    if (localJDBCXAResource == null)
      throw new XAException("The XADataSource has no such Xid:  " + paramXid);
    return localJDBCXAResource.prepareThis();
  }

  public int prepareThis()
    throws XAException
  {
    if (this.state != XA_STATE_ENDED)
      throw new XAException("Invalid XAResource state");
    try
    {
      this.connection.getSession().prepareCommit();
    }
    catch (HsqlException localHsqlException)
    {
      this.state = XA_STATE_PREPARED;
      throw new XAException(localHsqlException.getMessage());
    }
    this.state = XA_STATE_PREPARED;
    return 0;
  }

  public Xid[] recover(int paramInt)
    throws XAException
  {
    return this.xaDataSource.getPreparedXids();
  }

  public void rollback(Xid paramXid)
    throws XAException
  {
    JDBCXAResource localJDBCXAResource = this.xaDataSource.getResource(paramXid);
    if (localJDBCXAResource == null)
      throw new XAException("The XADataSource has no such Xid in prepared state:  " + paramXid);
    localJDBCXAResource.rollbackThis();
  }

  public void rollbackThis()
    throws XAException
  {
    if (this.state != XA_STATE_PREPARED)
      throw new XAException("Invalid XAResource state");
    try
    {
      this.connection.rollback();
    }
    catch (SQLException localSQLException)
    {
      throw new XAException(localSQLException.toString());
    }
    dispose();
  }

  public boolean setTransactionTimeout(int paramInt)
    throws XAException
  {
    throw new XAException("Transaction timeouts not implemented yet");
  }

  public void start(Xid paramXid, int paramInt)
    throws XAException
  {
    if ((this.state != XA_STATE_INITIAL) && (this.state != XA_STATE_DISPOSED))
      throw new XAException("Invalid XAResource state");
    if (this.xaDataSource == null)
      throw new XAException("JDBCXAResource has not been associated with a XADataSource");
    if (paramXid == null)
      throw new XAException("Null Xid");
    try
    {
      this.originalAutoCommitMode = this.connection.getAutoCommit();
      this.connection.setAutoCommit(false);
    }
    catch (SQLException localSQLException)
    {
      throw new XAException(localSQLException.toString());
    }
    this.xid = paramXid;
    this.state = XA_STATE_STARTED;
    this.xaDataSource.addResource(this.xid, this);
  }

  JDBCConnection getConnection()
  {
    return this.connection;
  }

  void setConnection(JDBCConnection paramJDBCConnection)
  {
    this.connection = paramJDBCConnection;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.pool.JDBCXAResource
 * JD-Core Version:    0.6.2
 */