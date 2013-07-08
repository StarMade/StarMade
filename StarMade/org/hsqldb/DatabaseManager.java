package org.hsqldb;

import java.util.Vector;
import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlTimer;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.Set;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.hsqldb.store.ValuePool;

public class DatabaseManager
{
  private static int dbIDCounter;
  static final HashMap memDatabaseMap = new HashMap();
  static final HashMap fileDatabaseMap = new HashMap();
  static final HashMap resDatabaseMap = new HashMap();
  static final IntKeyHashMap databaseIDMap = new IntKeyHashMap();
  static HashMap serverMap = new HashMap();
  private static final HsqlTimer timer = new HsqlTimer();
  
  public static Vector getDatabaseURIs()
  {
    Vector localVector = new Vector();
    synchronized (databaseIDMap)
    {
      Iterator localIterator = databaseIDMap.values().iterator();
      while (localIterator.hasNext())
      {
        Database localDatabase = (Database)localIterator.next();
        localVector.addElement(localDatabase.getURI());
      }
    }
    return localVector;
  }
  
  public static void closeDatabases(int paramInt)
  {
    synchronized (databaseIDMap)
    {
      Iterator localIterator = databaseIDMap.values().iterator();
      while (localIterator.hasNext())
      {
        Database localDatabase = (Database)localIterator.next();
        try
        {
          localDatabase.close(paramInt);
        }
        catch (HsqlException localHsqlException) {}
      }
    }
  }
  
  public static Session newSession(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2)
  {
    Database localDatabase = null;
    synchronized (databaseIDMap)
    {
      localDatabase = (Database)databaseIDMap.get(paramInt1);
    }
    if (localDatabase == null) {
      return null;
    }
    ??? = localDatabase.connect(paramString1, paramString2, paramString3, paramInt2);
    ((Session)???).isNetwork = true;
    return ???;
  }
  
  public static Session newSession(String paramString1, String paramString2, String paramString3, String paramString4, HsqlProperties paramHsqlProperties, String paramString5, int paramInt)
  {
    Database localDatabase = getDatabase(paramString1, paramString2, paramHsqlProperties);
    if (localDatabase == null) {
      return null;
    }
    return localDatabase.connect(paramString3, paramString4, paramString5, paramInt);
  }
  
  public static Session getSession(int paramInt, long paramLong)
  {
    Database localDatabase = null;
    synchronized (databaseIDMap)
    {
      localDatabase = (Database)databaseIDMap.get(paramInt);
    }
    return localDatabase == null ? null : localDatabase.sessionManager.getSession(paramLong);
  }
  
  public static int getDatabase(String paramString1, String paramString2, Server paramServer, HsqlProperties paramHsqlProperties)
  {
    Database localDatabase = getDatabase(paramString1, paramString2, paramHsqlProperties);
    registerServer(paramServer, localDatabase);
    return localDatabase.databaseID;
  }
  
  public static Database getDatabase(int paramInt)
  {
    return (Database)databaseIDMap.get(paramInt);
  }
  
  public static void shutdownDatabases(Server paramServer, int paramInt)
  {
    Database[] arrayOfDatabase;
    synchronized (serverMap)
    {
      HashSet localHashSet = (HashSet)serverMap.get(paramServer);
      arrayOfDatabase = new Database[localHashSet.size()];
      localHashSet.toArray(arrayOfDatabase);
    }
    for (int i = 0; i < arrayOfDatabase.length; i++) {
      arrayOfDatabase[i].close(paramInt);
    }
  }
  
  public static Database getDatabase(String paramString1, String paramString2, HsqlProperties paramHsqlProperties)
  {
    Database localDatabase = getDatabaseObject(paramString1, paramString2, paramHsqlProperties);
    synchronized (localDatabase)
    {
      switch (localDatabase.getState())
      {
      case 1: 
        break;
      case 4: 
        if (lookupDatabaseObject(paramString1, paramString2) == null) {
          addDatabaseObject(paramString1, paramString2, localDatabase);
        }
        localDatabase.open();
        break;
      case 2: 
      case 3: 
        throw Error.error(451, 23);
      }
    }
    return localDatabase;
  }
  
  private static synchronized Database getDatabaseObject(String paramString1, String paramString2, HsqlProperties paramHsqlProperties)
  {
    Object localObject1 = paramString2;
    HashMap localHashMap;
    if (paramString1 == "file:")
    {
      localHashMap = fileDatabaseMap;
      localObject1 = filePathToKey(paramString2);
      localDatabase = (Database)localHashMap.get(localObject1);
      if ((localDatabase == null) && (localHashMap.size() > 0))
      {
        Iterator localIterator = localHashMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          if (((String)localObject1).equalsIgnoreCase(str))
          {
            localObject1 = str;
            break;
          }
        }
      }
    }
    else if (paramString1 == "res:")
    {
      localHashMap = resDatabaseMap;
    }
    else if (paramString1 == "mem:")
    {
      localHashMap = memDatabaseMap;
    }
    else
    {
      throw Error.runtimeError(201, "DatabaseManager");
    }
    Database localDatabase = (Database)localHashMap.get(localObject1);
    if (localDatabase == null)
    {
      localDatabase = new Database(paramString1, paramString2, (String)localObject1, paramHsqlProperties);
      localDatabase.databaseID = dbIDCounter;
      synchronized (databaseIDMap)
      {
        databaseIDMap.put(dbIDCounter, localDatabase);
        dbIDCounter += 1;
      }
      localHashMap.put(localObject1, localDatabase);
    }
    return localDatabase;
  }
  
  public static synchronized Database lookupDatabaseObject(String paramString1, String paramString2)
  {
    String str = paramString2;
    HashMap localHashMap;
    if (paramString1 == "file:")
    {
      localHashMap = fileDatabaseMap;
      str = filePathToKey(paramString2);
    }
    else if (paramString1 == "res:")
    {
      localHashMap = resDatabaseMap;
    }
    else if (paramString1 == "mem:")
    {
      localHashMap = memDatabaseMap;
    }
    else
    {
      throw Error.runtimeError(201, "DatabaseManager");
    }
    return (Database)localHashMap.get(str);
  }
  
  private static synchronized void addDatabaseObject(String paramString1, String paramString2, Database paramDatabase)
  {
    String str = paramString2;
    HashMap localHashMap;
    if (paramString1 == "file:")
    {
      localHashMap = fileDatabaseMap;
      str = filePathToKey(paramString2);
    }
    else if (paramString1 == "res:")
    {
      localHashMap = resDatabaseMap;
    }
    else if (paramString1 == "mem:")
    {
      localHashMap = memDatabaseMap;
    }
    else
    {
      throw Error.runtimeError(201, "DatabaseManager");
    }
    synchronized (databaseIDMap)
    {
      databaseIDMap.put(paramDatabase.databaseID, paramDatabase);
    }
    localHashMap.put(str, paramDatabase);
  }
  
  static void removeDatabase(Database paramDatabase)
  {
    int i = paramDatabase.databaseID;
    String str1 = paramDatabase.getType();
    String str2 = paramDatabase.getPath();
    String str3 = str2;
    notifyServers(paramDatabase);
    HashMap localHashMap;
    if (str1 == "file:")
    {
      localHashMap = fileDatabaseMap;
      str3 = filePathToKey(str2);
    }
    else if (str1 == "res:")
    {
      localHashMap = resDatabaseMap;
    }
    else if (str1 == "mem:")
    {
      localHashMap = memDatabaseMap;
    }
    else
    {
      throw Error.runtimeError(201, "DatabaseManager");
    }
    boolean bool = false;
    synchronized (databaseIDMap)
    {
      databaseIDMap.remove(i);
      bool = databaseIDMap.isEmpty();
    }
    synchronized (localHashMap)
    {
      localHashMap.remove(str3);
    }
    if (bool) {
      ValuePool.resetPool();
    }
  }
  
  public static void deRegisterServer(Server paramServer)
  {
    synchronized (serverMap)
    {
      serverMap.remove(paramServer);
    }
  }
  
  private static void registerServer(Server paramServer, Database paramDatabase)
  {
    synchronized (serverMap)
    {
      if (!serverMap.containsKey(paramServer)) {
        serverMap.put(paramServer, new HashSet());
      }
      HashSet localHashSet = (HashSet)serverMap.get(paramServer);
      localHashSet.add(paramDatabase);
    }
  }
  
  private static void notifyServers(Database paramDatabase)
  {
    Server[] arrayOfServer;
    synchronized (serverMap)
    {
      arrayOfServer = new Server[serverMap.size()];
      serverMap.keysToArray(arrayOfServer);
    }
    for (int i = 0; i < arrayOfServer.length; i++)
    {
      Server localServer = arrayOfServer[i];
      boolean bool = false;
      HashSet localHashSet;
      synchronized (serverMap)
      {
        localHashSet = (HashSet)serverMap.get(localServer);
      }
      if (localHashSet != null) {
        synchronized (localHashSet)
        {
          bool = localHashSet.remove(paramDatabase);
        }
      }
      if (bool) {
        localServer.notify(0, paramDatabase.databaseID);
      }
    }
  }
  
  static boolean isServerDB(Database paramDatabase)
  {
    Iterator localIterator = serverMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Server localServer = (Server)localIterator.next();
      HashSet localHashSet = (HashSet)serverMap.get(localServer);
      if (localHashSet.contains(paramDatabase)) {
        return true;
      }
    }
    return false;
  }
  
  public static HsqlTimer getTimer()
  {
    return timer;
  }
  
  private static String filePathToKey(String paramString)
  {
    try
    {
      return FileUtil.getFileUtil().canonicalPath(paramString);
    }
    catch (Exception localException) {}
    return paramString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.DatabaseManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */