package org.hsqldb.jdbc.pool;

import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.CommonDataSource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.Xid;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCCommonDataSource;
import org.hsqldb.jdbc.JDBCConnection;
import org.hsqldb.jdbc.JDBCDriver;
import org.hsqldb.jdbc.Util;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.Set;

public class JDBCXADataSource
  extends JDBCCommonDataSource
  implements XADataSource, Serializable, Referenceable, CommonDataSource
{
  private HashMap resources = new HashMap();
  
  public XAConnection getXAConnection()
    throws SQLException
  {
    JDBCConnection localJDBCConnection = (JDBCConnection)JDBCDriver.getConnection(this.url, this.connectionProps);
    JDBCXAConnection localJDBCXAConnection = new JDBCXAConnection(this, localJDBCConnection);
    return localJDBCXAConnection;
  }
  
  public XAConnection getXAConnection(String paramString1, String paramString2)
    throws SQLException
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw Util.nullArgument();
    }
    if ((paramString1.equals(this.user)) && (paramString2.equals(this.password))) {
      return getXAConnection();
    }
    throw Util.sqlException(Error.error(4000));
  }
  
  public Reference getReference()
    throws NamingException
  {
    String str = "org.hsqldb.jdbc.JDBCDataSourceFactory";
    Reference localReference = new Reference(getClass().getName(), str, null);
    localReference.add(new StringRefAddr("database", getDatabase()));
    localReference.add(new StringRefAddr("user", getUser()));
    localReference.add(new StringRefAddr("password", this.password));
    localReference.add(new StringRefAddr("loginTimeout", Integer.toString(this.loginTimeout)));
    return localReference;
  }
  
  public void addResource(Xid paramXid, JDBCXAResource paramJDBCXAResource)
  {
    this.resources.put(paramXid, paramJDBCXAResource);
  }
  
  public JDBCXADataSource()
    throws SQLException
  {}
  
  public JDBCXAResource removeResource(Xid paramXid)
  {
    return (JDBCXAResource)this.resources.remove(paramXid);
  }
  
  Xid[] getPreparedXids()
  {
    Iterator localIterator = this.resources.keySet().iterator();
    HashSet localHashSet = new HashSet();
    while (localIterator.hasNext())
    {
      Xid localXid = (Xid)localIterator.next();
      if (((JDBCXAResource)this.resources.get(localXid)).state == JDBCXAResource.XA_STATE_PREPARED) {
        localHashSet.add(localXid);
      }
    }
    Xid[] arrayOfXid = new Xid[localHashSet.size()];
    localHashSet.toArray(arrayOfXid);
    return arrayOfXid;
  }
  
  JDBCXAResource getResource(Xid paramXid)
  {
    return (JDBCXAResource)this.resources.get(paramXid);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.pool.JDBCXADataSource
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */