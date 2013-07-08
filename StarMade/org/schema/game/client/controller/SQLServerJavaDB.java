package org.schema.game.client.controller;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SQLServerJavaDB
{
  private String field_486 = "embedded";
  private String field_487 = "org.apache.derby.jdbc.EmbeddedDriver";
  private String field_488 = "jdbc:derby:";
  
  public static void main(String[] paramArrayOfString)
  {
    Object localObject1 = paramArrayOfString;
    Object localObject2 = localObject1;
    localObject1 = paramArrayOfString = new SQLServerJavaDB();
    if ((localObject2.length > 0) && (localObject2[0].equalsIgnoreCase("derbyclient")))
    {
      ((SQLServerJavaDB)localObject1).field_486 = "derbyclient";
      ((SQLServerJavaDB)localObject1).field_487 = "org.apache.derby.jdbc.ClientDriver";
      ((SQLServerJavaDB)localObject1).field_488 = "jdbc:derby://localhost:1527/";
    }
    System.out.println("SimpleApp starting in " + paramArrayOfString.field_486 + " mode");
    localObject1 = paramArrayOfString;
    try
    {
      Class.forName(((SQLServerJavaDB)localObject1).field_487).newInstance();
      System.out.println("Loaded the appropriate driver");
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      System.err.println("\nUnable to load the JDBC driver " + ((SQLServerJavaDB)localObject1).field_487);
      System.err.println("Please check your CLASSPATH.");
      localClassNotFoundException.printStackTrace(System.err);
    }
    catch (InstantiationException localInstantiationException)
    {
      System.err.println("\nUnable to instantiate the JDBC driver " + ((SQLServerJavaDB)localObject1).field_487);
      localInstantiationException.printStackTrace(System.err);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      System.err.println("\nNot allowed to access the JDBC driver " + ((SQLServerJavaDB)localObject1).field_487);
      localIllegalAccessException.printStackTrace(System.err);
    }
    localObject1 = null;
    ArrayList localArrayList = new ArrayList();
    Object localObject4 = null;
    ResultSet localResultSet = null;
    try
    {
      (localObject3 = new Properties()).put("user", "schema");
      ((Properties)localObject3).put("password", "schema");
      localObject4 = "schemadb";
      localObject1 = DriverManager.getConnection(paramArrayOfString.field_488 + (String)localObject4 + ";create=true", (Properties)localObject3);
      System.out.println("Connected to and created database " + (String)localObject4);
      ((Connection)localObject1).setAutoCommit(false);
      localObject4 = ((Connection)localObject1).createStatement();
      localArrayList.add(localObject4);
      ((Statement)localObject4).execute("create table location(num int, addr varchar(40))");
      System.out.println("Created table location");
      Object localObject3 = ((Connection)localObject1).prepareStatement("insert into location values (?, ?)");
      localArrayList.add(localObject3);
      ((PreparedStatement)localObject3).setInt(1, 1956);
      ((PreparedStatement)localObject3).setString(2, "Webster St.");
      ((PreparedStatement)localObject3).executeUpdate();
      System.out.println("Inserted 1956 Webster");
      ((PreparedStatement)localObject3).setInt(1, 1910);
      ((PreparedStatement)localObject3).setString(2, "Union St.");
      ((PreparedStatement)localObject3).executeUpdate();
      System.out.println("Inserted 1910 Union");
      localObject3 = ((Connection)localObject1).prepareStatement("update location set num=?, addr=? where num=?");
      localArrayList.add(localObject3);
      ((PreparedStatement)localObject3).setInt(1, 180);
      ((PreparedStatement)localObject3).setString(2, "Grand Ave.");
      ((PreparedStatement)localObject3).setInt(3, 1956);
      ((PreparedStatement)localObject3).executeUpdate();
      System.out.println("Updated 1956 Webster to 180 Grand");
      ((PreparedStatement)localObject3).setInt(1, 300);
      ((PreparedStatement)localObject3).setString(2, "Lakeshore Ave.");
      ((PreparedStatement)localObject3).setInt(3, 180);
      ((PreparedStatement)localObject3).executeUpdate();
      System.out.println("Updated 180 Grand to 300 Lakeshore");
      localResultSet = ((Statement)localObject4).executeQuery("SELECT num, addr FROM location ORDER BY num");
      int j = 0;
      if (!localResultSet.next())
      {
        j = 1;
        a1("No rows in ResultSet");
      }
      int i;
      if ((i = localResultSet.getInt(1)) != 300)
      {
        j = 1;
        a1("Wrong row returned, expected num=300, got " + i);
      }
      if (!localResultSet.next())
      {
        j = 1;
        a1("Too few rows");
      }
      if ((i = localResultSet.getInt(1)) != 1910)
      {
        j = 1;
        a1("Wrong row returned, expected num=1910, got " + i);
      }
      if (localResultSet.next())
      {
        j = 1;
        a1("Too many rows");
      }
      if (j == 0) {
        System.out.println("Verified the rows");
      }
      ((Statement)localObject4).execute("drop table location");
      System.out.println("Dropped table location");
      ((Connection)localObject1).commit();
      System.out.println("Committed the transaction");
      if (paramArrayOfString.field_486.equals("embedded")) {
        try
        {
          DriverManager.getConnection("jdbc:derby:;shutdown=true");
        }
        catch (SQLException localSQLException1)
        {
          if (((paramArrayOfString = localSQLException1).getErrorCode() == 50000) && ("XJ015".equals(paramArrayOfString.getSQLState())))
          {
            System.out.println("Derby shut down normally");
          }
          else
          {
            System.err.println("Derby did not shut down normally");
            a(paramArrayOfString);
          }
        }
      }
      try
      {
        if (localResultSet != null) {
          localResultSet.close();
        }
      }
      catch (SQLException localSQLException2)
      {
        a(localSQLException2;
      }
      while (!localArrayList.isEmpty())
      {
        localObject4 = (Statement)localArrayList.remove(0);
        try
        {
          if (localObject4 != null) {
            ((Statement)localObject4).close();
          }
        }
        catch (SQLException localSQLException3)
        {
          a(localSQLException3;
        }
      }
      try
      {
        if (localObject1 != null) {
          ((Connection)localObject1).close();
        }
      }
      catch (SQLException localSQLException4)
      {
        a(localSQLException4;
      }
      Statement localStatement;
      System.out.println("SimpleApp finished");
    }
    catch (SQLException localSQLException5)
    {
      try
      {
        if (localResultSet != null) {
          localResultSet.close();
        }
      }
      catch (SQLException localSQLException6)
      {
        a(localSQLException6;
      }
      while (!localArrayList.isEmpty())
      {
        localObject4 = (Statement)localArrayList.remove(0);
        try
        {
          if (localObject4 != null) {
            ((Statement)localObject4).close();
          }
        }
        catch (SQLException localSQLException7)
        {
          a(localSQLException7;
        }
      }
      try
      {
        if (localObject1 != null) {
          ((Connection)localObject1).close();
        }
      }
      catch (SQLException localSQLException8)
      {
        a(localSQLException8;
      }
    }
    finally
    {
      try
      {
        if (localResultSet != null) {
          localResultSet.close();
        }
      }
      catch (SQLException localSQLException9)
      {
        a(localSQLException9;
      }
      while (!localArrayList.isEmpty())
      {
        localStatement = (Statement)localArrayList.remove(0);
        try
        {
          if (localStatement != null) {
            localStatement.close();
          }
        }
        catch (SQLException localSQLException10)
        {
          a(localSQLException10;
        }
      }
      try
      {
        if (localObject1 != null) {
          ((Connection)localObject1).close();
        }
      }
      catch (SQLException localSQLException11)
      {
        a(localSQLException11;
      }
    }
  }
  
  private static void a(SQLException paramSQLException)
  {
    while (paramSQLException != null)
    {
      System.err.println("\n----- SQLException -----");
      System.err.println("  SQL State:  " + paramSQLException.getSQLState());
      System.err.println("  Error Code: " + paramSQLException.getErrorCode());
      System.err.println("  Message:    " + paramSQLException.getMessage());
      paramSQLException = paramSQLException.getNextException();
    }
  }
  
  private static void a1(String paramString)
  {
    System.err.println("\nData verification failed:");
    System.err.println("\t" + paramString);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.client.controller.SQLServerJavaDB
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */