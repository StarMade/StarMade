package org.hsqldb;

import java.util.Locale;
import org.hsqldb.persist.HsqlProperties;

public class DatabaseURL
{
  static final String S_DOT = ".";
  public static final String S_MEM = "mem:";
  public static final String S_FILE = "file:";
  public static final String S_RES = "res:";
  public static final String S_ALIAS = "alias:";
  public static final String S_HSQL = "hsql://";
  public static final String S_HSQLS = "hsqls://";
  public static final String S_HTTP = "http://";
  public static final String S_HTTPS = "https://";
  public static final String S_URL_PREFIX = "jdbc:hsqldb:";
  public static final String S_URL_INTERNAL = "jdbc:default:connection";
  public static final String url_connection_type = "connection_type";
  public static final String url_database = "database";
  
  public static boolean isFileBasedDatabaseType(String paramString)
  {
    return (paramString == "file:") || (paramString == "res:");
  }
  
  public static boolean isInProcessDatabaseType(String paramString)
  {
    return (paramString == "file:") || (paramString == "res:") || (paramString == "mem:");
  }
  
  public static HsqlProperties parseURL(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    String str1 = paramString.toLowerCase(Locale.ENGLISH);
    HsqlProperties localHsqlProperties1 = new HsqlProperties();
    HsqlProperties localHsqlProperties2 = null;
    String str2 = null;
    int i = 0;
    String str3 = null;
    int j = 0;
    int k = 0;
    if (paramBoolean1) {
      if (str1.startsWith("jdbc:hsqldb:")) {
        i = "jdbc:hsqldb:".length();
      } else {
        return localHsqlProperties1;
      }
    }
    String str6;
    String str7;
    for (;;)
    {
      m = paramString.indexOf("${");
      if (m == -1) {
        break;
      }
      n = paramString.indexOf("}", m);
      if (n == -1) {
        break;
      }
      str6 = paramString.substring(m + 2, n);
      str7 = null;
      try
      {
        str7 = System.getProperty(str6);
      }
      catch (SecurityException localSecurityException2) {}
      if (str7 == null) {
        break;
      }
      paramString = paramString.substring(0, m) + str7 + paramString.substring(n + 1);
      str1 = paramString.toLowerCase(Locale.ENGLISH);
    }
    localHsqlProperties1.setProperty("url", paramString);
    int m = paramString.length();
    int n = paramString.indexOf(';', i);
    if (n > -1)
    {
      str2 = paramString.substring(n + 1, str1.length());
      m = n;
      localHsqlProperties2 = HsqlProperties.delimitedArgPairsToProps(str2, "=", ";", null);
      localHsqlProperties1.addProperties(localHsqlProperties2);
    }
    if ((m == i + 1) && (str1.startsWith(".", i)))
    {
      str3 = ".";
    }
    else if (str1.startsWith("mem:", i))
    {
      str3 = "mem:";
    }
    else if (str1.startsWith("file:", i))
    {
      str3 = "file:";
    }
    else if (str1.startsWith("res:", i))
    {
      str3 = "res:";
    }
    else if (str1.startsWith("alias:", i))
    {
      str3 = "alias:";
    }
    else if (str1.startsWith("hsql://", i))
    {
      str3 = "hsql://";
      j = 9001;
      k = 1;
    }
    else if (str1.startsWith("hsqls://", i))
    {
      str3 = "hsqls://";
      j = 554;
      k = 1;
    }
    else if (str1.startsWith("http://", i))
    {
      str3 = "http://";
      j = 80;
      k = 1;
    }
    else if (str1.startsWith("https://", i))
    {
      str3 = "https://";
      j = 443;
      k = 1;
    }
    if (str3 == null) {
      str3 = "file:";
    } else if (str3 == ".") {
      str3 = "mem:";
    } else {
      i += str3.length();
    }
    localHsqlProperties1.setProperty("connection_type", str3);
    String str4;
    if (k != 0)
    {
      str6 = null;
      str7 = null;
      String str8 = null;
      int i1 = paramString.indexOf('/', i);
      if ((i1 > 0) && (i1 < m)) {
        str6 = paramString.substring(i1, m);
      } else {
        i1 = m;
      }
      int i2;
      if (paramString.charAt(i) == '[')
      {
        i2 = paramString.indexOf(']', i + 2);
        if ((i2 < 0) || (i2 >= i1)) {
          return null;
        }
        str7 = str1.substring(i + 1, i2);
        if (i1 > i2 + 1) {
          str8 = paramString.substring(i2 + 1, i1);
        }
      }
      else
      {
        i2 = paramString.indexOf(':', i + 1);
        if ((i2 > -1) && (i2 < i1)) {
          str8 = paramString.substring(i2, i1);
        } else {
          i2 = -1;
        }
        str7 = str1.substring(i, i2 > 0 ? i2 : i1);
      }
      if (str8 != null)
      {
        if ((str8.length() < 2) || (str8.charAt(0) != ':')) {
          return null;
        }
        try
        {
          j = Integer.parseInt(str8.substring(1));
        }
        catch (NumberFormatException localNumberFormatException)
        {
          return null;
        }
      }
      String str5;
      if (paramBoolean2)
      {
        str5 = "";
        str4 = str6;
      }
      else if (str6 == null)
      {
        str5 = "/";
        str4 = "";
      }
      else
      {
        int i3 = str6.lastIndexOf('/');
        if (i3 < 1)
        {
          str5 = "/";
          str4 = str6.substring(1).toLowerCase(Locale.ENGLISH);
        }
        else
        {
          str5 = str6.substring(0, i3);
          str4 = str6.substring(i3 + 1);
        }
      }
      localHsqlProperties1.setProperty("port", j);
      localHsqlProperties1.setProperty("host", str7);
      localHsqlProperties1.setProperty("path", str5);
      if ((!paramBoolean2) && (localHsqlProperties2 != null))
      {
        String str9 = localHsqlProperties2.getProperty("filepath");
        if ((str9 != null) && (str4.length() != 0)) {
          str4 = str4 + ";" + str9;
        } else if ((paramString.indexOf("mem:") == m + 1) || (paramString.indexOf("file:") == m + 1)) {
          str4 = str4 + paramString.substring(m);
        }
      }
    }
    else
    {
      if (str3 == "mem:")
      {
        str4 = str1.substring(i, m);
      }
      else if (str3 == "res:")
      {
        str4 = paramString.substring(i, m);
        if (str4.indexOf('/') != 0) {
          str4 = '/' + str4;
        }
      }
      else
      {
        str4 = paramString.substring(i, m);
        if (str4.startsWith("~"))
        {
          str6 = "~";
          try
          {
            str6 = System.getProperty("user.home");
          }
          catch (SecurityException localSecurityException1) {}
          str4 = str6 + str4.substring(1);
        }
      }
      if (str4.length() == 0) {
        return null;
      }
    }
    i = str4.indexOf("&password=");
    if (i != -1)
    {
      str6 = str4.substring(i + "&password=".length());
      localHsqlProperties1.setProperty("password", str6);
      str4 = str4.substring(0, i);
    }
    i = str4.indexOf("?user=");
    if (i != -1)
    {
      str6 = str4.substring(i + "?user=".length());
      localHsqlProperties1.setProperty("user", str6);
      str4 = str4.substring(0, i);
    }
    localHsqlProperties1.setProperty("database", str4);
    return localHsqlProperties1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.DatabaseURL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */