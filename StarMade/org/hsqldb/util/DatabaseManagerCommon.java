package org.hsqldb.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

class DatabaseManagerCommon
{
  private static Random rRandom = new Random(100L);
  static String[] selectHelp = { "SELECT * FROM ", "SELECT [LIMIT n m] [DISTINCT] \n{ selectExpression | table.* | * } [, ... ] \n[INTO [CACHED|TEMP|TEXT] newTable] \nFROM tableList \n[WHERE Expression] \n[ORDER BY selectExpression [{ASC | DESC}] [, ...] ] \n[GROUP BY Expression [, ...] ] \n[UNION [ALL] selectStatement]" };
  static String[] insertHelp = { "INSERT INTO ", "INSERT INTO table [ (column [,...] ) ] \n{ VALUES(Expression [,...]) [,...] | SelectStatement }" };
  static String[] updateHelp = { "UPDATE ", "UPDATE table SET column = Expression [, ...] \n[WHERE Expression]" };
  static String[] deleteHelp = { "DELETE FROM ", "DELETE FROM table [WHERE Expression]" };
  static String[] createTableHelp = { "CREATE TABLE ", "CREATE [TEMP] [CACHED|MEMORY|TEXT] TABLE name \n( columnDefinition [, ...] ) \n\ncolumnDefinition: \ncolumn DataType [ [NOT] NULL] [PRIMARY KEY] \nDataType: \n{ INTEGER | DOUBLE | VARCHAR | DATE | TIME |... }" };
  static String[] dropTableHelp = { "DROP TABLE ", "DROP TABLE table" };
  static String[] createIndexHelp = { "CREATE INDEX ", "CREATE [UNIQUE] INDEX index ON \ntable (column [, ...])" };
  static String[] dropIndexHelp = { "DROP INDEX ", "DROP INDEX table.index" };
  static String[] checkpointHelp = { "CHECKPOINT", "(HSQLDB SQL only)" };
  static String[] scriptHelp = { "SCRIPT", "SCRIPT ['file']\n\n(HSQLDB SQL only)" };
  static String[] shutdownHelp = { "SHUTDOWN", "SHUTDOWN [COMPACT|IMMEDIATELY|SCRIPT]\n\n(HSQLDB SQL only)" };
  static String[] setHelp = { "SET ", "SET AUTOCOMMIT { TRUE | FALSE }\nSET DATABASE COLLATION \"<collationname>\"\nSET FILES CHECKPOINT DEFRAG <size>\nSET DATABASE INITIAL SCHEMA <schemaname>\nSET FILES LOG SIZE <size>\nSET MAXROWS maxrows\nSET PASSWORD <password>\nSET FILES READ { ONLY | WRITE }\nSET SCHEMA <schemaname>\nSET TABLE <tablename> READ { ONLY | WRITE }\nSET TABLE <tablename> SOURCE { ON | OFF }\nSET TABLE <tablename> SOURCE \"<file>\" [DESC]\n\n\n(HSQLDB SQL only)" };
  static String[] testHelp = { "-->>>TEST<<<-- ;\n--#1000;\nDROP TABLE Test IF EXISTS;\nCREATE TABLE Test(\n  Id INTEGER PRIMARY KEY,\n  FirstName VARCHAR(20),\n  Name VARCHAR(50),\n  ZIP INTEGER) ;\nINSERT INTO Test \n  VALUES(#,'Julia','Peterson-Clancy',#) ;\nUPDATE Test SET Name='Hans' WHERE Id=# ;\nSELECT * FROM Test WHERE Id=# ;\nDELETE FROM Test WHERE Id=# ;\nDROP TABLE Test IF EXISTS;", "This test script is parsed by the DatabaseManager\nIt may be changed manually. Rules:\n- it must start with -->>>TEST<<<--.\n- each line must end with ';' (no spaces after)\n- lines starting with -- are comments\n- lines starting with --#<count> means set new count\n" };
  static String[] testDataSql = { "SELECT * FROM Product", "SELECT * FROM Invoice", "SELECT * FROM Item", "SELECT * FROM Customer a INNER JOIN Invoice i ON a.ID=i.CustomerID", "SELECT * FROM Customer a LEFT OUTER JOIN Invoice i ON a.ID=i.CustomerID", "SELECT * FROM Invoice d INNER JOIN Item i ON d.ID=i.InvoiceID", "SELECT * FROM Customer WHERE Street LIKE '1%' ORDER BY Lastname", "SELECT a.id, a.firstname, a.lastname, count(i.Total) \"COUNT\", COALESCE(sum(i.Total), 0) \"TOTAL\", COALESCE(AVG(i.Total),0) \"AVG\" FROM Customer a LEFT OUTER JOIN Invoice i ON a.ID=i.CustomerID GROUP BY a.id, a.firstname, a.lastname" };
  
  static String random(String[] paramArrayOfString)
  {
    return paramArrayOfString[random(paramArrayOfString.length)];
  }
  
  static int random(int paramInt)
  {
    paramInt = rRandom.nextInt() % paramInt;
    return paramInt < 0 ? -paramInt : paramInt;
  }
  
  static void createTestTables(Statement paramStatement)
  {
    String[] arrayOfString = { "DROP TABLE Item IF EXISTS;", "DROP TABLE Invoice IF EXISTS;", "DROP TABLE Product IF EXISTS;", "DROP TABLE Customer IF EXISTS;", "CREATE TABLE Customer(ID INTEGER PRIMARY KEY,FirstName VARCHAR(20),LastName VARCHAR(20),Street VARCHAR(20),City VARCHAR(20));", "CREATE TABLE Product(ID INTEGER PRIMARY KEY,Name VARCHAR(20),Price DECIMAL(10,2));", "CREATE TABLE Invoice(ID INTEGER PRIMARY KEY,CustomerID INTEGER,Total DECIMAL(10,2), FOREIGN KEY (CustomerId) REFERENCES Customer(ID) ON DELETE CASCADE);", "CREATE TABLE Item(InvoiceID INTEGER,Item INTEGER,ProductID INTEGER,Quantity INTEGER,Cost DECIMAL(10,2),PRIMARY KEY(InvoiceID,Item), FOREIGN KEY (InvoiceId) REFERENCES Invoice (ID) ON DELETE CASCADE, FOREIGN KEY (ProductId) REFERENCES Product(ID) ON DELETE CASCADE);" };
    for (int i = 0; i < arrayOfString.length; i++) {
      try
      {
        paramStatement.execute(arrayOfString[i]);
      }
      catch (SQLException localSQLException) {}
    }
  }
  
  static String createTestData(Statement paramStatement)
    throws SQLException
  {
    String[] arrayOfString1 = { "White", "Karsen", "Smith", "Ringer", "May", "King", "Fuller", "Miller", "Ott", "Sommer", "Schneider", "Steel", "Peterson", "Heiniger", "Clancy" };
    String[] arrayOfString2 = { "Mary", "James", "Anne", "George", "Sylvia", "Robert", "Janet", "Michael", "Andrew", "Bill", "Susanne", "Laura", "Bob", "Julia", "John" };
    String[] arrayOfString3 = { "Upland Pl.", "College Av.", "- 20th Ave.", "Seventh Av." };
    String[] arrayOfString4 = { "New York", "Dallas", "Boston", "Chicago", "Seattle", "San Francisco", "Berne", "Oslo", "Paris", "Lyon", "Palo Alto", "Olten" };
    String[] arrayOfString5 = { "Iron", "Ice Tea", "Clock", "Chair", "Telephone", "Shoe" };
    int i = 50;
    for (int j = 0; j < i; j++)
    {
      paramStatement.execute("INSERT INTO Customer VALUES(" + j + ",'" + random(arrayOfString2) + "','" + random(arrayOfString1) + "','" + random(554) + " " + random(arrayOfString3) + "','" + random(arrayOfString4) + "')");
      paramStatement.execute("INSERT INTO Product VALUES(" + j + ",'" + random(arrayOfString5) + " " + random(arrayOfString5) + "'," + (20 + 2 * random(120)) + ")");
    }
    for (j = 0; j < i; j++)
    {
      paramStatement.execute("INSERT INTO Invoice VALUES(" + j + "," + random(i) + ",0.0)");
      for (int k = random(20) + 2; k >= 0; k--) {
        paramStatement.execute("INSERT INTO Item VALUES(" + j + "," + k + "," + random(i) + "," + (1 + random(24)) + ",1.5)");
      }
    }
    paramStatement.execute("UPDATE Product SET Price=ROUND(Price*.1,2)");
    paramStatement.execute("UPDATE Item SET Cost=Cost*(SELECT Price FROM Product prod WHERE ProductID=prod.ID)");
    paramStatement.execute("UPDATE Invoice SET Total=(SELECT SUM(Cost*Quantity) FROM Item WHERE InvoiceID=Invoice.ID)");
    return "SELECT * FROM Customer";
  }
  
  static String readFile(String paramString)
  {
    try
    {
      FileReader localFileReader = new FileReader(paramString);
      BufferedReader localBufferedReader = new BufferedReader(localFileReader);
      StringBuffer localStringBuffer = new StringBuffer();
      String str = null;
      int i = 0;
      while ((str = localBufferedReader.readLine()) != null)
      {
        i++;
        localStringBuffer.append(str);
        localStringBuffer.append('\n');
      }
      localBufferedReader.close();
      localFileReader.close();
      return localStringBuffer.toString();
    }
    catch (IOException localIOException)
    {
      return localIOException.toString();
    }
  }
  
  static void writeFile(String paramString1, String paramString2)
  {
    try
    {
      FileWriter localFileWriter = new FileWriter(paramString1);
      localFileWriter.write(paramString2.toCharArray());
      localFileWriter.close();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  static long testStatement(Statement paramStatement, String paramString, int paramInt)
    throws SQLException
  {
    long l = System.currentTimeMillis();
    if (paramString.indexOf('#') == -1) {
      paramInt = 1;
    }
    for (int i = 0; i < paramInt; i++)
    {
      int j;
      for (String str = paramString;; str = str.substring(0, j) + (int)(Math.random() * i) + str.substring(j + 3))
      {
        j = str.indexOf("#r#");
        if (j == -1) {
          break;
        }
      }
      for (;;)
      {
        j = str.indexOf('#');
        if (j == -1) {
          break;
        }
        str = str.substring(0, j) + i + str.substring(j + 1);
      }
      paramStatement.execute(str);
    }
    return System.currentTimeMillis() - l;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.util.DatabaseManagerCommon
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */