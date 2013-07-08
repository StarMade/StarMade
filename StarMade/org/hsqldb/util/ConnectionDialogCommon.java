package org.hsqldb.util;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import org.hsqldb.lib.java.JavaSystem;

class ConnectionDialogCommon
{
  private static String[][] connTypes;
  private static final String[][] sJDBCTypes = { { "HSQL Database Engine In-Memory", "org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:." }, { "HSQL Database Engine Standalone", "org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:«database/path?»" }, { "HSQL Database Engine Server", "org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/" }, { "HSQL Database Engine WebServer", "org.hsqldb.jdbcDriver", "jdbc:hsqldb:http://«hostname/?»" }, { "JDBC-ODBC Bridge from Sun", "sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:«database?»" }, { "Cloudscape RMI", "RmiJdbc.RJDriver", "jdbc:rmi://«host?»:1099/jdbc:cloudscape:«database?»;create=true" }, { "IBM DB2", "COM.ibm.db2.jdbc.app.DB2Driver", "jdbc:db2:«database?»" }, { "IBM DB2 (thin)", "COM.ibm.db2.jdbc.net.DB2Driver", "jdbc:db2://«host?»:6789/«database?»" }, { "Informix", "com.informix.jdbc.IfxDriver", "jdbc:informix-sqli://«host?»:1533/«database?»:INFORMIXSERVER=«server?»" }, { "InstantDb", "jdbc.idbDriver", "jdbc:idb:«database?».prp" }, { "MySQL Connector/J", "com.mysql.jdbc.Driver", "jdbc:mysql://«host?»/«database?»" }, { "MM.MySQL", "org.gjt.mm.mysql.Driver", "jdbc:mysql://«host?»/«database?»" }, { "Oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:oci8:@«database?»" }, { "Oracle (thin)", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@«host?»:1521:«database?»" }, { "PointBase", "com.pointbase.jdbc.jdbcUniversalDriver", "jdbc:pointbase://«host?»/«database?»" }, { "PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://«host?»/«database?»" }, { "PostgreSQL v6.5", "postgresql.Driver", "jdbc:postgresql://«host?»/«database?»" } };
  private static final String fileName = "hsqlprefs.dat";
  private static File recentSettings = null;
  static String emptySettingName = "Recent settings...";
  private static String homedir = null;
  
  static String[][] getTypes()
  {
    return sJDBCTypes;
  }
  
  static synchronized Hashtable loadRecentConnectionSettings()
    throws IOException
  {
    Hashtable localHashtable = new Hashtable();
    try
    {
      if (recentSettings == null)
      {
        setHomeDir();
        if (homedir == null) {
          return localHashtable;
        }
        recentSettings = new File(homedir, "hsqlprefs.dat");
        if (!recentSettings.exists())
        {
          JavaSystem.createNewFile(recentSettings);
          return localHashtable;
        }
      }
    }
    catch (Throwable localThrowable1)
    {
      return localHashtable;
    }
    FileInputStream localFileInputStream = null;
    ObjectInputStream localObjectInputStream = null;
    try
    {
      localFileInputStream = new FileInputStream(recentSettings);
      localObjectInputStream = new ObjectInputStream(localFileInputStream);
      localHashtable.clear();
      for (;;)
      {
        ConnectionSetting localConnectionSetting = (ConnectionSetting)localObjectInputStream.readObject();
        if (!emptySettingName.equals(localConnectionSetting.getName())) {
          localHashtable.put(localConnectionSetting.getName(), localConnectionSetting);
        }
      }
    }
    catch (EOFException localEOFException) {}catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new IOException("Unrecognized class type " + localClassNotFoundException.getMessage());
    }
    catch (ClassCastException localClassCastException)
    {
      throw new IOException("Unrecognized class type " + localClassCastException.getMessage());
    }
    catch (Throwable localThrowable2) {}finally
    {
      if (localObjectInputStream != null) {
        localObjectInputStream.close();
      }
      if (localFileInputStream != null) {
        localFileInputStream.close();
      }
    }
    return localHashtable;
  }
  
  static void addToRecentConnectionSettings(Hashtable paramHashtable, ConnectionSetting paramConnectionSetting)
    throws IOException
  {
    paramHashtable.put(paramConnectionSetting.getName(), paramConnectionSetting);
    storeRecentConnectionSettings(paramHashtable);
  }
  
  private static void storeRecentConnectionSettings(Hashtable paramHashtable)
  {
    try
    {
      if (recentSettings == null)
      {
        setHomeDir();
        if (homedir == null) {
          return;
        }
        recentSettings = new File(homedir, "hsqlprefs.dat");
        if (recentSettings.exists()) {}
      }
      if ((paramHashtable == null) || (paramHashtable.size() == 0)) {
        return;
      }
      FileOutputStream localFileOutputStream = new FileOutputStream(recentSettings);
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
      Enumeration localEnumeration = paramHashtable.elements();
      while (localEnumeration.hasMoreElements()) {
        localObjectOutputStream.writeObject(localEnumeration.nextElement());
      }
      localObjectOutputStream.flush();
      localObjectOutputStream.close();
      localFileOutputStream.close();
    }
    catch (Throwable localThrowable) {}
  }
  
  static void deleteRecentConnectionSettings()
  {
    try
    {
      if (recentSettings == null)
      {
        setHomeDir();
        if (homedir == null) {
          return;
        }
        recentSettings = new File(homedir, "hsqlprefs.dat");
      }
      if (!recentSettings.exists())
      {
        recentSettings = null;
        return;
      }
      recentSettings.delete();
      recentSettings = null;
    }
    catch (Throwable localThrowable) {}
  }
  
  public static void setHomeDir()
  {
    if (homedir == null) {
      try
      {
        Class localClass = Class.forName("sun.security.action.GetPropertyAction");
        Constructor localConstructor = localClass.getConstructor(new Class[] { String.class });
        PrivilegedAction localPrivilegedAction = (PrivilegedAction)localConstructor.newInstance(new Object[] { "user.home" });
        homedir = (String)AccessController.doPrivileged(localPrivilegedAction);
      }
      catch (Exception localException)
      {
        System.err.println("No access to home directory.  Continuing without...");
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.util.ConnectionDialogCommon
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */