package org.hsqldb.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;

public class RCData
{
  public static final String DEFAULT_JDBC_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
  private String defaultJdbcDriverName = "org.hsqldb.jdbc.JDBCDriver";
  public String id;
  public String url;
  public String username;
  public String password;
  public String ti;
  public String driver;
  public String charset;
  public String truststore;
  public String libpath;
  
  public void setDefaultJdbcDriver(String paramString)
  {
    this.defaultJdbcDriverName = paramString;
  }
  
  public String getDefaultJdbcDriverName()
  {
    return this.defaultJdbcDriverName;
  }
  
  public RCData(File paramFile, String paramString)
    throws Exception
  {
    if (paramFile == null) {
      throw new IllegalArgumentException("RC file name not specified");
    }
    if (!paramFile.canRead()) {
      throw new IOException("Please set up authentication file '" + paramFile + "'");
    }
    StringTokenizer localStringTokenizer = null;
    int i = 0;
    int j = 0;
    BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramFile));
    try
    {
      String str1;
      while ((str1 = localBufferedReader.readLine()) != null)
      {
        j++;
        str1 = str1.trim();
        if ((str1.length() != 0) && (str1.charAt(0) != '#'))
        {
          localStringTokenizer = new StringTokenizer(str1);
          String str2;
          String str3;
          if (localStringTokenizer.countTokens() == 1)
          {
            str2 = localStringTokenizer.nextToken();
            str3 = "";
          }
          else if (localStringTokenizer.countTokens() > 1)
          {
            str2 = localStringTokenizer.nextToken();
            str3 = localStringTokenizer.nextToken("").trim();
          }
          else
          {
            try
            {
              localBufferedReader.close();
            }
            catch (IOException localIOException1) {}
            throw new Exception("Corrupt line " + j + " in '" + paramFile + "':  " + str1);
          }
          if (paramString == null)
          {
            if (str2.equals("urlid")) {
              System.out.println(str3);
            }
          }
          else if (str2.equals("urlid"))
          {
            if (str3.equals(paramString))
            {
              if (this.id == null)
              {
                this.id = paramString;
                i = 1;
              }
              else
              {
                try
                {
                  localBufferedReader.close();
                }
                catch (IOException localIOException2) {}
                throw new Exception("Key '" + paramString + " redefined at" + " line " + j + " in '" + paramFile);
              }
            }
            else {
              i = 0;
            }
          }
          else if (i != 0) {
            if (str2.equals("url"))
            {
              this.url = str3;
            }
            else if (str2.equals("username"))
            {
              this.username = str3;
            }
            else if (str2.equals("driver"))
            {
              this.driver = str3;
            }
            else if (str2.equals("charset"))
            {
              this.charset = str3;
            }
            else if (str2.equals("truststore"))
            {
              this.truststore = str3;
            }
            else if (str2.equals("password"))
            {
              this.password = str3;
            }
            else if (str2.equals("transiso"))
            {
              this.ti = str3;
            }
            else if (str2.equals("libpath"))
            {
              this.libpath = str3;
            }
            else
            {
              try
              {
                localBufferedReader.close();
              }
              catch (IOException localIOException3) {}
              throw new Exception("Bad line " + j + " in '" + paramFile + "':  " + str1);
            }
          }
        }
      }
    }
    finally
    {
      try
      {
        localBufferedReader.close();
      }
      catch (IOException localIOException5) {}
      localBufferedReader = null;
    }
    if (paramString == null) {
      return;
    }
    if (this.url == null) {
      throw new Exception("url not set for '" + paramString + "' in file '" + paramFile + "'");
    }
    if (this.libpath != null) {
      throw new IllegalArgumentException("Sorry, 'libpath' not supported yet");
    }
  }
  
  public RCData(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
    throws Exception
  {
    this(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, null);
  }
  
  public RCData(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
    throws Exception
  {
    this(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, null);
  }
  
  public RCData(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
    throws Exception
  {
    this.id = paramString1;
    this.url = paramString2;
    this.username = paramString3;
    this.password = paramString4;
    this.ti = paramString9;
    this.driver = paramString5;
    this.charset = paramString6;
    this.truststore = paramString7;
    this.libpath = paramString8;
    if (paramString8 != null) {
      throw new IllegalArgumentException("Sorry, 'libpath' not supported yet");
    }
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new Exception("id or url was not set");
    }
  }
  
  public Connection getConnection()
    throws ClassNotFoundException, SQLException, MalformedURLException
  {
    return getConnection(null, null);
  }
  
  public Connection getConnection(String paramString1, String paramString2)
    throws ClassNotFoundException, MalformedURLException, SQLException
  {
    String str1 = null;
    String str2 = null;
    Properties localProperties = System.getProperties();
    if (paramString1 == null) {
      str1 = this.driver == null ? "org.hsqldb.jdbc.JDBCDriver" : this.driver;
    } else {
      str1 = expandSysPropVars(paramString1);
    }
    if (paramString2 == null)
    {
      if (this.truststore != null) {
        str2 = expandSysPropVars(this.truststore);
      }
    }
    else {
      str2 = expandSysPropVars(paramString2);
    }
    if (str2 == null) {
      localProperties.remove("javax.net.ssl.trustStore");
    } else {
      localProperties.put("javax.net.ssl.trustStore", str2);
    }
    String str3 = null;
    try
    {
      str3 = expandSysPropVars(this.url);
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      throw new MalformedURLException(localIllegalArgumentException1.toString() + " for URL '" + this.url + "'");
    }
    String str4 = null;
    if (this.username != null) {
      try
      {
        str4 = expandSysPropVars(this.username);
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        throw new MalformedURLException(localIllegalArgumentException2.toString() + " for user name '" + this.username + "'");
      }
    }
    String str5 = null;
    if (this.password != null) {
      try
      {
        str5 = expandSysPropVars(this.password);
      }
      catch (IllegalArgumentException localIllegalArgumentException3)
      {
        throw new MalformedURLException(localIllegalArgumentException3.toString() + " for password");
      }
    }
    Class.forName(str1);
    Connection localConnection = str4 == null ? DriverManager.getConnection(str3) : DriverManager.getConnection(str3, str4, str5);
    if (this.ti != null) {
      setTI(localConnection, this.ti);
    }
    return localConnection;
  }
  
  public static String expandSysPropVars(String paramString)
  {
    int i;
    int j;
    String str2;
    for (String str1 = new String(paramString);; str1 = str1.substring(0, i) + str2 + str1.substring(j + 1))
    {
      i = str1.indexOf("${");
      if (i < 0) {
        break;
      }
      j = str1.indexOf(125, i + 2);
      if (j < 0) {
        break;
      }
      String str3 = str1.substring(i + 2, j);
      if (str3.length() < 1) {
        throw new IllegalArgumentException("Bad variable setting");
      }
      str2 = System.getProperty(str3);
      if (str2 == null) {
        throw new IllegalArgumentException("No Java system property with name '" + str3 + "'");
      }
    }
    return str1;
  }
  
  public static void setTI(Connection paramConnection, String paramString)
    throws SQLException
  {
    int i = -1;
    if (paramString.equals("TRANSACTION_READ_UNCOMMITTED")) {
      i = 1;
    }
    if (paramString.equals("TRANSACTION_READ_COMMITTED")) {
      i = 2;
    }
    if (paramString.equals("TRANSACTION_REPEATABLE_READ")) {
      i = 4;
    }
    if (paramString.equals("TRANSACTION_SERIALIZABLE")) {
      i = 8;
    }
    if (paramString.equals("TRANSACTION_NONE")) {
      i = 0;
    }
    if (i < 0) {
      throw new SQLException("Trans. isol. value not supported by " + RCData.class.getName() + ": " + paramString);
    }
    paramConnection.setTransactionIsolation(i);
  }
  
  public static String tiToString(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return "TRANSACTION_READ_UNCOMMITTED";
    case 2: 
      return "TRANSACTION_READ_COMMITTED";
    case 4: 
      return "TRANSACTION_REPEATABLE_READ";
    case 8: 
      return "TRANSACTION_SERIALIZABLE";
    case 0: 
      return "TRANSACTION_NONE";
    }
    return "Custom Transaction Isolation numerical value: " + paramInt;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.RCData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */