package org.hsqldb.server;

import java.net.InetAddress;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.persist.HsqlProperties;

public final class ServerConfiguration
  implements ServerConstants
{
  public static int getDefaultPort(int paramInt, boolean paramBoolean)
  {
    switch (paramInt)
    {
    case 1: 
      return paramBoolean ? 554 : 9001;
    case 0: 
      return paramBoolean ? 443 : 80;
    case 2: 
      return paramBoolean ? -1 : 9101;
    }
    return -1;
  }
  
  public static ServerProperties getPropertiesFromFile(int paramInt, String paramString1, String paramString2)
  {
    if (StringUtil.isEmpty(paramString1)) {
      return null;
    }
    ServerProperties localServerProperties = new ServerProperties(paramInt, paramString1, paramString2);
    boolean bool;
    try
    {
      bool = localServerProperties.load();
    }
    catch (Exception localException)
    {
      return null;
    }
    return bool ? localServerProperties : null;
  }
  
  public static String[] listLocalInetAddressNames()
  {
    HashSet localHashSet = new HashSet();
    InetAddress localInetAddress;
    InetAddress[] arrayOfInetAddress;
    try
    {
      localInetAddress = InetAddress.getLocalHost();
      arrayOfInetAddress = InetAddress.getAllByName(localInetAddress.getHostAddress());
      for (int i = 0; i < arrayOfInetAddress.length; i++)
      {
        localHashSet.add(arrayOfInetAddress[i].getHostAddress());
        localHashSet.add(arrayOfInetAddress[i].getHostName());
      }
      arrayOfInetAddress = InetAddress.getAllByName(localInetAddress.getHostName());
      for (i = 0; i < arrayOfInetAddress.length; i++)
      {
        localHashSet.add(arrayOfInetAddress[i].getHostAddress());
        localHashSet.add(arrayOfInetAddress[i].getHostName());
      }
    }
    catch (Exception localException1) {}
    try
    {
      localInetAddress = InetAddress.getByName(null);
      arrayOfInetAddress = InetAddress.getAllByName(localInetAddress.getHostAddress());
      for (int j = 0; j < arrayOfInetAddress.length; j++)
      {
        localHashSet.add(arrayOfInetAddress[j].getHostAddress());
        localHashSet.add(arrayOfInetAddress[j].getHostName());
      }
      arrayOfInetAddress = InetAddress.getAllByName(localInetAddress.getHostName());
      for (j = 0; j < arrayOfInetAddress.length; j++)
      {
        localHashSet.add(arrayOfInetAddress[j].getHostAddress());
        localHashSet.add(arrayOfInetAddress[j].getHostName());
      }
    }
    catch (Exception localException2) {}
    try
    {
      localHashSet.add(InetAddress.getByName("loopback").getHostAddress());
      localHashSet.add(InetAddress.getByName("loopback").getHostName());
    }
    catch (Exception localException3) {}
    String[] arrayOfString = new String[localHashSet.size()];
    localHashSet.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public static ServerProperties newDefaultProperties(int paramInt)
  {
    ServerProperties localServerProperties = new ServerProperties(paramInt);
    localServerProperties.setProperty("server.restart_on_shutdown", false);
    localServerProperties.setProperty("server.address", "0.0.0.0");
    localServerProperties.setProperty("server.no_system_exit", true);
    localServerProperties.setProperty("server.maxdatabases", 10);
    localServerProperties.setProperty("server.silent", true);
    localServerProperties.setProperty("server.tls", false);
    localServerProperties.setProperty("server.trace", false);
    localServerProperties.setProperty("server.default_page", "index.html");
    localServerProperties.setProperty("server.root", ".");
    return localServerProperties;
  }
  
  public static void translateAddressProperty(HsqlProperties paramHsqlProperties)
  {
    if (paramHsqlProperties == null) {
      return;
    }
    String str = paramHsqlProperties.getProperty("server.address");
    if (StringUtil.isEmpty(str)) {
      paramHsqlProperties.setProperty("server.address", "0.0.0.0");
    }
  }
  
  public static void translateDefaultDatabaseProperty(HsqlProperties paramHsqlProperties)
  {
    if (paramHsqlProperties == null) {
      return;
    }
    if (!paramHsqlProperties.isPropertyTrue("server.remote_open"))
    {
      if (paramHsqlProperties.getProperty("server.database.0") == null)
      {
        String str = paramHsqlProperties.getProperty("server.database");
        if (str == null) {
          str = "test";
        } else {
          paramHsqlProperties.removeProperty("server.database");
        }
        paramHsqlProperties.setProperty("server.database.0", str);
        paramHsqlProperties.setProperty("server.dbname.0", "");
      }
      if (paramHsqlProperties.getProperty("server.dbname.0") == null) {
        paramHsqlProperties.setProperty("server.dbname.0", "");
      }
    }
  }
  
  public static void translateDefaultNoSystemExitProperty(HsqlProperties paramHsqlProperties)
  {
    if (paramHsqlProperties == null) {
      return;
    }
    paramHsqlProperties.setPropertyIfNotExists("server.no_system_exit", "false");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.ServerConfiguration
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */