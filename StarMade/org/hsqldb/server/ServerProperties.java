package org.hsqldb.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.persist.HsqlProperties;

public class ServerProperties
  extends HsqlProperties
{
  static final int SERVER_PROPERTY = 0;
  static final int SERVER_MULTI_PROPERTY = 1;
  static final int SYSTEM_PROPERTY = 2;
  static final String sc_key_prefix = "server";
  static final String sc_key_address = "server.address";
  static final String sc_key_autorestart_server = "server.restart_on_shutdown";
  static final String sc_key_database = "server.database";
  static final String sc_key_dbname = "server.dbname";
  static final String sc_key_no_system_exit = "server.no_system_exit";
  static final String sc_key_port = "server.port";
  static final String sc_key_http_port = "server.port";
  static final String sc_key_silent = "server.silent";
  static final String sc_key_tls = "server.tls";
  static final String sc_key_trace = "server.trace";
  static final String sc_key_web_default_page = "server.default_page";
  static final String sc_key_web_root = "server.root";
  static final String sc_key_max_connections = "server.maxconnections";
  static final String sc_key_remote_open_db = "server.remote_open";
  static final String sc_key_max_databases = "server.maxdatabases";
  static final String sc_key_acl = "server.acl";
  static final String sc_key_daemon = "server.daemon";
  static final String sc_key_props = "server.props";
  static final String sc_key_system = "system";
  static final String sc_default_web_mime = "text/html";
  static final String sc_default_web_page = "index.html";
  static final String sc_default_web_root = ".";
  static final HashMap meta = new HashMap();
  static final OrderedHashSet prefixes = new OrderedHashSet();
  final int protocol;
  protected boolean initialised = false;
  IntKeyHashMap idToAliasMap = new IntKeyHashMap();
  IntKeyHashMap idToPathMap = new IntKeyHashMap();
  
  public ServerProperties(int paramInt, File paramFile)
    throws IOException
  {
    FileInputStream localFileInputStream = null;
    try
    {
      localFileInputStream = new FileInputStream(paramFile);
      this.stringProps.load(localFileInputStream);
    }
    finally
    {
      if (localFileInputStream != null) {
        localFileInputStream.close();
      }
    }
    this.protocol = paramInt;
  }
  
  ServerProperties(int paramInt)
  {
    this.protocol = paramInt;
  }
  
  ServerProperties(int paramInt, String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
    this.protocol = paramInt;
  }
  
  public void validate()
  {
    Enumeration localEnumeration = this.stringProps.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      localObject = (String)localEnumeration.nextElement();
      Object[] arrayOfObject = (Object[])meta.get(localObject);
      if (arrayOfObject == null) {
        arrayOfObject = getPrefixedMetadata((String)localObject);
      }
      String str1;
      if (arrayOfObject == null)
      {
        str1 = "unsupported property: " + (String)localObject;
        super.addError(0, str1);
      }
      else
      {
        str1 = null;
        if (((Integer)arrayOfObject[1]).intValue() == 2)
        {
          str1 = validateSystemProperty((String)localObject, arrayOfObject);
        }
        else if (((Integer)arrayOfObject[1]).intValue() == 1)
        {
          str1 = validateMultiProperty((String)localObject, arrayOfObject);
        }
        else
        {
          String str2 = getProperty((String)localObject);
          if (str2 == null)
          {
            if (arrayOfObject[4] == null) {
              str1 = "missing value for property: " + (String)localObject;
            } else {
              setProperty((String)localObject, arrayOfObject[4].toString());
            }
          }
          else {
            str1 = HsqlProperties.validateProperty((String)localObject, str2, arrayOfObject);
          }
        }
        if (str1 != null) {
          super.addError(0, str1);
        }
      }
    }
    Object localObject = this.idToAliasMap.keySet().iterator();
    int i;
    while (((Iterator)localObject).hasNext())
    {
      i = ((Iterator)localObject).nextInt();
      if (!this.idToPathMap.containsKey(i)) {
        addError(0, "no path for database id: " + i);
      }
    }
    localObject = this.idToPathMap.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      i = ((Iterator)localObject).nextInt();
      if (!this.idToAliasMap.containsKey(i)) {
        addError(0, "no alias for database id: " + i);
      }
    }
    this.initialised = true;
  }
  
  Object[] getPrefixedMetadata(String paramString)
  {
    for (int i = 0; i < prefixes.size(); i++)
    {
      String str = (String)prefixes.get(i);
      if (paramString.startsWith(str)) {
        return (Object[])meta.get(str);
      }
    }
    return null;
  }
  
  String validateMultiProperty(String paramString, Object[] paramArrayOfObject)
  {
    String str1 = (String)paramArrayOfObject[0];
    if ((paramArrayOfObject[0].equals("server.database")) && ("server.database".equals(paramString))) {
      paramString = paramString + ".0";
    }
    int i;
    try
    {
      i = Integer.parseInt(paramString.substring(str1.length() + 1));
    }
    catch (NumberFormatException localNumberFormatException)
    {
      return "maformed database enumerator: " + paramString;
    }
    String str2;
    Object localObject;
    if (paramArrayOfObject[0].equals("server.dbname"))
    {
      str2 = this.stringProps.getProperty(paramString).toLowerCase();
      localObject = this.idToAliasMap.put(i, str2);
      if (localObject != null) {
        return "duplicate database enumerator: " + paramString;
      }
    }
    else if (paramArrayOfObject[0].equals("server.database"))
    {
      str2 = this.stringProps.getProperty(paramString);
      localObject = this.idToPathMap.put(i, str2);
      if (localObject != null) {
        return "duplicate database enumerator: " + paramString;
      }
    }
    return null;
  }
  
  String validateSystemProperty(String paramString, Object[] paramArrayOfObject)
  {
    String str1 = (String)paramArrayOfObject[0];
    String str2 = paramString.substring(str1.length() + 1);
    String str3 = this.stringProps.getProperty(paramString);
    if (str3 == null) {
      return "value required for property: " + paramString;
    }
    System.setProperty(str2, str3);
    return null;
  }
  
  static
  {
    meta.put("server.database", getMeta("server.database", 1, null));
    meta.put("server.dbname", getMeta("server.dbname", 1, null));
    meta.put("system", getMeta("system", 2, null));
    meta.put("server.silent", getMeta("server.silent", 0, false));
    meta.put("server.trace", getMeta("server.trace", 0, false));
    meta.put("server.tls", getMeta("server.tls", 0, false));
    meta.put("server.acl", getMeta("server.acl", 0, null));
    meta.put("server.restart_on_shutdown", getMeta("server.restart_on_shutdown", 0, false));
    meta.put("server.remote_open", getMeta("server.remote_open", 0, false));
    meta.put("server.no_system_exit", getMeta("server.no_system_exit", 0, false));
    meta.put("server.daemon", getMeta("server.daemon", 0, false));
    meta.put("server.address", getMeta("server.address", 0, null));
    meta.put("server.port", getMeta("server.port", 0, 9001, 0, 65535));
    meta.put("server.port", getMeta("server.port", 0, 80, 0, 65535));
    meta.put("server.maxconnections", getMeta("server.maxconnections", 0, 100, 1, 10000));
    meta.put("server.maxdatabases", getMeta("server.maxdatabases", 0, 10, 1, 1000));
    prefixes.add("server.database");
    prefixes.add("server.dbname");
    prefixes.add("system");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.ServerProperties
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */