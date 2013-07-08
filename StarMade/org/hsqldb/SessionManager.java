package org.hsqldb;

import org.hsqldb.lib.Collection;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.rights.User;
import org.hsqldb.rights.UserManager;

public class SessionManager
{
  long sessionIdCount = 0L;
  private LongKeyHashMap sessionMap = new LongKeyHashMap();
  private Session sysSession;
  private Session sysLobSession;
  
  public SessionManager(Database paramDatabase)
  {
    User localUser = paramDatabase.getUserManager().getSysUser();
    this.sysSession = new Session(paramDatabase, localUser, false, false, this.sessionIdCount++, null, 0);
    this.sysLobSession = new Session(paramDatabase, localUser, true, false, this.sessionIdCount++, null, 0);
  }
  
  public synchronized Session newSession(Database paramDatabase, User paramUser, boolean paramBoolean1, boolean paramBoolean2, String paramString, int paramInt)
  {
    Session localSession = new Session(paramDatabase, paramUser, paramBoolean2, paramBoolean1, this.sessionIdCount, paramString, paramInt);
    this.sessionMap.put(this.sessionIdCount, localSession);
    this.sessionIdCount += 1L;
    return localSession;
  }
  
  public synchronized Session newSessionForLog(Database paramDatabase)
  {
    boolean bool = paramDatabase.databaseProperties.isVersion18();
    Session localSession = new Session(paramDatabase, paramDatabase.getUserManager().getSysUser(), bool, false, this.sessionIdCount, null, 0);
    localSession.isProcessingLog = true;
    this.sessionMap.put(this.sessionIdCount, localSession);
    this.sessionIdCount += 1L;
    return localSession;
  }
  
  public Session getSysSessionForScript(Database paramDatabase)
  {
    Session localSession = new Session(paramDatabase, paramDatabase.getUserManager().getSysUser(), false, false, 0L, null, 0);
    localSession.isProcessingScript = true;
    return localSession;
  }
  
  public Session getSysLobSession()
  {
    return this.sysLobSession;
  }
  
  public Session getSysSession()
  {
    this.sysSession.currentSchema = this.sysSession.database.schemaManager.getDefaultSchemaHsqlName();
    this.sysSession.isProcessingScript = false;
    this.sysSession.isProcessingLog = false;
    this.sysSession.setUser(this.sysSession.database.getUserManager().getSysUser());
    return this.sysSession;
  }
  
  public Session newSysSession()
  {
    Session localSession = new Session(this.sysSession.database, this.sysSession.getUser(), false, false, this.sessionIdCount, null, 0);
    localSession.currentSchema = this.sysSession.database.schemaManager.getDefaultSchemaHsqlName();
    this.sessionMap.put(this.sessionIdCount, localSession);
    this.sessionIdCount += 1L;
    return localSession;
  }
  
  public Session newSysSession(HsqlNameManager.HsqlName paramHsqlName, User paramUser)
  {
    Session localSession = new Session(this.sysSession.database, paramUser, false, false, 0L, null, 0);
    localSession.currentSchema = paramHsqlName;
    return localSession;
  }
  
  public void closeAllSessions()
  {
    Session[] arrayOfSession = getAllSessions();
    for (int i = 0; i < arrayOfSession.length; i++) {
      arrayOfSession[i].close();
    }
  }
  
  synchronized void removeSession(Session paramSession)
  {
    this.sessionMap.remove(paramSession.getId());
  }
  
  synchronized void close()
  {
    closeAllSessions();
    this.sysSession.close();
    this.sysLobSession.close();
  }
  
  synchronized boolean isEmpty()
  {
    return this.sessionMap.isEmpty();
  }
  
  public synchronized Session[] getVisibleSessions(Session paramSession)
  {
    return new Session[] { paramSession.isAdmin() ? getAllSessions() : paramSession };
  }
  
  synchronized Session getSession(long paramLong)
  {
    return (Session)this.sessionMap.get(paramLong);
  }
  
  public synchronized Session[] getAllSessions()
  {
    Session[] arrayOfSession = new Session[this.sessionMap.size()];
    Iterator localIterator = this.sessionMap.values().iterator();
    for (int i = 0; localIterator.hasNext(); i++) {
      arrayOfSession[i] = ((Session)localIterator.next());
    }
    return arrayOfSession;
  }
  
  public synchronized boolean isUserActive(String paramString)
  {
    Iterator localIterator = this.sessionMap.values().iterator();
    for (int i = 0; localIterator.hasNext(); i++)
    {
      Session localSession = (Session)localIterator.next();
      if (paramString.equals(localSession.getUser().getName().getNameString())) {
        return true;
      }
    }
    return false;
  }
  
  public synchronized void removeSchemaReference(Schema paramSchema)
  {
    Iterator localIterator = this.sessionMap.values().iterator();
    for (int i = 0; localIterator.hasNext(); i++)
    {
      Session localSession = (Session)localIterator.next();
      if (localSession.getCurrentSchemaHsqlName() == paramSchema.getName()) {
        localSession.resetSchema();
      }
    }
  }
  
  public synchronized void resetLoggedSchemas()
  {
    Iterator localIterator = this.sessionMap.values().iterator();
    for (int i = 0; localIterator.hasNext(); i++)
    {
      Session localSession = (Session)localIterator.next();
      localSession.loggedSchema = null;
    }
    this.sysLobSession.loggedSchema = null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.SessionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */