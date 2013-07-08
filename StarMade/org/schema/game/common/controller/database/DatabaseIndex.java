package org.schema.game.common.controller.database;

import class_1041;
import class_48;
import class_637;
import class_670;
import class_69;
import class_705;
import class_737;
import class_743;
import class_747;
import class_757;
import class_759;
import class_761;
import class_763;
import class_784;
import class_789;
import class_79;
import class_791;
import class_808;
import class_864;
import class_902;
import class_903;
import class_917;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.EntityNotFountException;

public class DatabaseIndex
{
  private static java.lang.String jdField_field_1731_of_type_JavaLangString;
  private Connection jdField_field_1731_of_type_JavaSqlConnection;
  private static final HashMap jdField_field_1731_of_type_JavaUtilHashMap;
  private static Pattern jdField_field_1731_of_type_JavaUtilRegexPattern = Pattern.compile("(" + str + ')');
  
  public static java.lang.String a(java.lang.String paramString)
  {
    paramString = jdField_field_1731_of_type_JavaUtilRegexPattern.matcher(paramString);
    StringBuffer localStringBuffer = new StringBuffer();
    while (paramString.find()) {
      paramString.appendReplacement(localStringBuffer, (java.lang.String)jdField_field_1731_of_type_JavaUtilHashMap.get(paramString.group(1)));
    }
    paramString.appendTail(localStringBuffer);
    return localStringBuffer.toString();
  }
  
  public DatabaseIndex()
  {
    System.err.println("[SQL] Fetching connection");
    this.jdField_field_1731_of_type_JavaSqlConnection = DriverManager.getConnection("jdbc:hsqldb:file:" + jdField_field_1731_of_type_JavaLangString, "SA", "");
    System.err.println("[SQL] connection successfull");
  }
  
  public static boolean a1()
  {
    return new File(jdField_field_1731_of_type_JavaLangString).exists();
  }
  
  public final int a2(java.lang.String paramString, class_917 paramclass_917)
  {
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    java.lang.String str = "";
    switch (class_918.field_1145[paramclass_917.ordinal()])
    {
    case 1: 
      str = "AND LAST_MOD = '' ";
      break;
    case 2: 
      str = "AND NOT LAST_MOD = '' ";
      break;
    case 3: 
      str = "";
    }
    return localStatement.executeUpdate("DELETE FROM ENTITIES WHERE UID LIKE '" + paramString + "' " + str + ";");
  }
  
  public final int a3(java.lang.String paramString, class_917 paramclass_917, class_48 paramclass_48, boolean paramBoolean)
  {
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    Object localObject = "";
    switch (class_918.field_1145[paramclass_917.ordinal()])
    {
    case 1: 
      localObject = "AND LAST_MOD = '' ";
      break;
    case 2: 
      localObject = "AND NOT LAST_MOD = '' ";
      break;
    case 3: 
      localObject = "";
    }
    if (paramclass_48 != null) {
      localObject = (java.lang.String)localObject + "AND X = " + paramclass_48.field_475 + " AND Y = " + paramclass_48.field_476 + " AND Z = " + paramclass_48.field_477 + " ";
    }
    if (paramBoolean) {
      localObject = (java.lang.String)localObject + " AND TYPE = 5 ";
    }
    paramString = localStatement.executeQuery("SELECT UID FROM ENTITIES WHERE UID LIKE '" + paramString + "' " + (java.lang.String)localObject + ";");
    for (paramclass_917 = 0; paramString.next(); paramclass_917++)
    {
      paramclass_48 = paramString.getString(1).trim();
      localStatement.executeUpdate("DELETE FROM ENTITIES WHERE TYPE = 5 AND UID = '" + paramclass_48 + "'; ");
      paramclass_48 = "ENTITY_SHIP_" + paramclass_48;
      paramBoolean = new File(class_1041.field_144 + paramclass_48 + ".ent").delete();
      File[] arrayOfFile;
      int i = (arrayOfFile = localObject = new File(class_1041.field_149).listFiles(new class_759(paramclass_48))).length;
      for (int j = 0; j < i; j++) {
        arrayOfFile[j].delete();
      }
      System.err.println("[SQL] DESPAWNING: " + paramclass_48 + " DELETE SUC " + paramBoolean + "; DATA FOUND: " + localObject.length);
    }
    localStatement.close();
    return paramclass_917;
  }
  
  private void f()
  {
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).execute("DROP TABLE SYSTEMS if exists;");
    localStatement.execute("CREATE CACHED TABLE SYSTEMS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1), X INT not null, Y INT not null, Z INT not null, TYPE INT not null, STARTTIME BIGINT not null, NAME VARCHAR(64) not null, INFOS VARBINARY(8192) not null, primary key (ID));");
    localStatement.execute("create unique index sysCoordIndex on SYSTEMS(X,Y,Z);");
    localStatement.close();
  }
  
  private void g()
  {
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).execute("DROP TABLE SECTORS_ITEMS if exists;");
    localStatement.execute("CREATE CACHED TABLE SECTORS_ITEMS(ID BIGINT, ITEMS VARBINARY(18432) not null, primary key (ID));");
    localStatement.close();
  }
  
  private void h()
  {
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).execute("DROP TABLE SECTORS if exists;");
    localStatement.execute("CREATE CACHED TABLE SECTORS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1), X INT not null, Y INT not null, Z INT not null, TYPE INT not null, NAME VARCHAR(64) not null, ITEMS BIGINT not null, PROTECTION INT not null, STELLAR INT not null, primary key (ID));");
    localStatement.execute("create index secTypeIndex on SECTORS(TYPE);");
    localStatement.execute("create index secStellarIndex on SECTORS(STELLAR);");
    localStatement.execute("create unique index secCoordIndex on SECTORS(X,Y,Z);");
    localStatement.close();
  }
  
  public final void a4()
  {
    f();
    h();
    g();
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).execute("DROP TABLE ENTITIES if exists;");
    localStatement.execute("CREATE CACHED TABLE ENTITIES(UID CHAR(64) not null, X INT not null, Y INT not null, Z INT not null, TYPE TINYINT not null, NAME char(64), FACTION INT default 0, CREATOR char(64), LAST_MOD char(64), SEED BIGINT, TOUCHED BOOLEAN, LOCAL_POS FLOAT ARRAY[3], DIM INT ARRAY[6], GEN_ID INT, ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1), primary key (ID));");
    localStatement.execute("create index ENTITIES_PK on ENTITIES (UID);");
    localStatement.execute("create unique index uidType on ENTITIES(UID,TYPE);");
    localStatement.execute("create index coordIndex on ENTITIES(X,Y,Z);");
    localStatement.execute("create index typeIndex on ENTITIES(TYPE);");
    localStatement.close();
  }
  
  public final List a5(java.lang.String paramString)
  {
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).setMaxRows(20);
    java.lang.String str = "LIMIT 20";
    paramString = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE UPPER(UID) LIKE UPPER('" + paramString + "') " + str + ";");
    localStatement.close();
    return a11(paramString);
  }
  
  public final List a6(java.lang.String paramString, int paramInt)
  {
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    java.lang.String str = "";
    if (paramInt > 0)
    {
      localStatement.setMaxRows(paramInt);
      str = "LIMIT " + paramInt;
    }
    paramString = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE UID = '" + paramString + "' " + str + ";");
    localStatement.close();
    return a11(paramString);
  }
  
  public final class_48 a7(java.lang.String paramString, class_48 paramclass_48)
  {
    Statement localStatement;
    ResultSet localResultSet = (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT X, Y, Z FROM ENTITIES WHERE UID = '" + paramString + "';");
    localStatement.close();
    if (localResultSet.next()) {
      paramclass_48.b(localResultSet.getInt(1), localResultSet.getInt(2), localResultSet.getInt(3));
    } else {
      throw new EntityNotFountException("Could not find Entity: " + paramString);
    }
    return paramclass_48;
  }
  
  public final void a8(java.lang.String paramString, class_48 paramclass_48)
  {
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeUpdate("UPDATE ENTITIES SET (X,Y,Z) = (" + paramclass_48.field_475 + ", " + paramclass_48.field_476 + ", " + paramclass_48.field_477 + ") WHERE UID = '" + paramString + "';");
    localStatement.close();
  }
  
  public final List a9(class_48 paramclass_48, int paramInt)
  {
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    if (paramInt >= 0) {
      localStatement.setMaxRows(Math.max(0, paramInt));
    }
    long l = System.currentTimeMillis();
    paramclass_48 = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE X = " + paramclass_48.field_475 + " AND Y = " + paramclass_48.field_476 + " AND Z = " + paramclass_48.field_477 + ";");
    System.err.println("[SQL] SECTOR QUERY TOOK " + (System.currentTimeMillis() - l) + "ms");
    localStatement.close();
    return a11(paramclass_48);
  }
  
  public final List a10(class_48 paramclass_481, class_48 paramclass_482, int[] paramArrayOfInt)
  {
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    long l = System.currentTimeMillis();
    StringBuilder localStringBuilder = new StringBuilder();
    java.lang.String str = "";
    if ((paramArrayOfInt != null) && (paramArrayOfInt.length > 0))
    {
      localStringBuilder.append("(");
      for (int i = 0; i < paramArrayOfInt.length; i++)
      {
        localStringBuilder.append(paramArrayOfInt[i]);
        if (i < paramArrayOfInt.length - 1) {
          localStringBuilder.append(",");
        }
      }
      localStringBuilder.append(")");
      localObject = " TYPE IN " + localStringBuilder.toString() + " AND ";
    }
    Object localObject = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE " + (java.lang.String)localObject + "((X >= " + paramclass_481.field_475 + " AND Y >= " + paramclass_481.field_476 + " AND Z >= " + paramclass_481.field_477 + ") AND (X < " + paramclass_482.field_475 + " AND Y < " + paramclass_482.field_476 + " AND Z < " + paramclass_482.field_477 + "));");
    if (System.currentTimeMillis() - l > 20L) {
      System.err.println("[SQL] SECTOR QUERY TOOK " + (System.currentTimeMillis() - l) + "ms types: " + localStringBuilder + "; from " + paramclass_481 + " to " + paramclass_482);
    }
    localStatement.close();
    return a11((ResultSet)localObject);
  }
  
  private static List a11(ResultSet paramResultSet)
  {
    long l = System.currentTimeMillis();
    ObjectArrayList localObjectArrayList = new ObjectArrayList();
    if (paramResultSet.next()) {
      do
      {
        localObjectArrayList.add(new class_757(paramResultSet));
      } while (paramResultSet.next());
    }
    if (!paramResultSet.isClosed()) {
      paramResultSet.close();
    }
    if (System.currentTimeMillis() - l > 10L) {
      System.err.println("[SQL] SECTOR QUERY LIST CONVERSION TOOK " + (System.currentTimeMillis() - l) + "ms; list size: " + localObjectArrayList.size() + ";");
    }
    return localObjectArrayList;
  }
  
  private static DefaultTableModel a12(DefaultTableModel paramDefaultTableModel, ResultSet paramResultSet)
  {
    ResultSetMetaData localResultSetMetaData = paramResultSet.getMetaData();
    if (paramDefaultTableModel == null) {
      paramDefaultTableModel = new DefaultTableModel();
    }
    java.lang.String[] arrayOfString = new java.lang.String[localResultSetMetaData.getColumnCount()];
    for (int j = 0; j < arrayOfString.length; j++) {
      arrayOfString[j] = localResultSetMetaData.getColumnLabel(j + 1);
    }
    paramDefaultTableModel.setColumnIdentifiers(arrayOfString);
    while (paramResultSet.next())
    {
      Object[] arrayOfObject = new Object[arrayOfString.length];
      for (int i = 0; i < arrayOfObject.length; i++) {
        arrayOfObject[i] = paramResultSet.getObject(i + 1);
      }
      paramDefaultTableModel.addRow(arrayOfObject);
    }
    return paramDefaultTableModel;
  }
  
  public final void a13(java.lang.String paramString)
  {
    Object localObject;
    paramString = (localObject = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeQuery(paramString);
    ((Statement)localObject).close();
    (localObject = new JFrame()).setDefaultCloseOperation(3);
    ((JFrame)localObject).setSize(1200, 700);
    ((JFrame)localObject).setContentPane(new JScrollPane(new JTable(a12(new DefaultTableModel(), paramString))));
    ((JFrame)localObject).setVisible(true);
    paramString.close();
  }
  
  private static long a14(Connection paramConnection, long paramLong1, class_48 paramclass_48, int paramInt, byte[] paramArrayOfByte, long paramLong2, boolean paramBoolean)
  {
    Statement localStatement;
    (localStatement = paramConnection.createStatement()).setFetchSize(1);
    if (localStatement.executeQuery("SELECT ID FROM SYSTEMS WHERE X = " + paramclass_48.field_475 + " AND Y = " + paramclass_48.field_476 + " AND Z = " + paramclass_48.field_477 + ";").next())
    {
      if (paramBoolean)
      {
        System.err.println("[DB] FORCE UPDATING SYSTEM " + paramclass_48);
        (paramConnection = paramConnection.prepareStatement("UPDATE SYSTEMS SET (X,Y,Z,TYPE,NAME,STARTTIME,INFOS) = (CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS VARBINARY(8192))) WHERE ID = CAST(? AS BIGINT);")).setInt(1, paramclass_48.field_475);
        paramConnection.setInt(2, paramclass_48.field_476);
        paramConnection.setInt(3, paramclass_48.field_477);
        paramConnection.setInt(4, paramInt);
        paramConnection.setString(5, "default");
        paramConnection.setLong(6, paramLong2);
        paramConnection.setBytes(7, paramArrayOfByte);
        paramConnection.setLong(8, paramLong1);
        paramConnection.execute();
        paramConnection.close();
      }
    }
    else
    {
      System.err.println("[DATABASE][SYSTEMS] NO SYSTEM ENTRY FOUND FOR " + paramLong1 + " FOR " + paramclass_48);
      (paramConnection = paramConnection.prepareStatement("INSERT INTO SYSTEMS(X,Y,Z,TYPE,NAME,STARTTIME,INFOS) VALUES(CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS VARBINARY(8192)));", 1)).setInt(1, paramclass_48.field_475);
      paramConnection.setInt(2, paramclass_48.field_476);
      paramConnection.setInt(3, paramclass_48.field_477);
      paramConnection.setInt(4, paramInt);
      paramConnection.setString(5, "default");
      paramConnection.setLong(6, paramLong2);
      paramConnection.setBytes(7, paramArrayOfByte);
      paramConnection.executeUpdate();
      (paramLong1 = paramConnection.getGeneratedKeys()).next();
      paramLong1 = paramLong1.getLong(1);
      paramConnection.close();
    }
    localStatement.close();
    return paramLong1;
  }
  
  private static void a15(Connection paramConnection, long paramLong1, class_48 paramclass_48, int paramInt1, Map paramMap, int paramInt2, long paramLong2)
  {
    Statement localStatement = paramConnection.createStatement();
    Object localObject;
    if (-1L < 0L)
    {
      localObject = class_789.a192(paramclass_48, new class_48());
      (localObject = localStatement.executeQuery("SELECT ID FROM SYSTEMS WHERE X = " + ((class_48)localObject).field_475 + " AND Y = " + ((class_48)localObject).field_476 + " AND Z = " + ((class_48)localObject).field_477 + ";")).next();
      paramLong2 = ((ResultSet)localObject).getLong(1);
    }
    if (localStatement.executeQuery("SELECT ID FROM SECTORS WHERE X = " + paramclass_48.field_475 + " AND Y = " + paramclass_48.field_476 + " AND Z = " + paramclass_48.field_477 + ";").next())
    {
      (localObject = paramConnection.prepareStatement("UPDATE SECTORS SET (TYPE,NAME,ITEMS,PROTECTION,STELLAR) = (CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS INT),CAST(? AS BIGINT)) WHERE ID = CAST(? AS BIGINT);")).setInt(1, paramInt2);
      ((PreparedStatement)localObject).setString(2, "default");
      ((PreparedStatement)localObject).setLong(3, 0L);
      ((PreparedStatement)localObject).setInt(4, paramInt1);
      ((PreparedStatement)localObject).setLong(5, paramLong2);
      ((PreparedStatement)localObject).setLong(6, paramLong1);
      ((PreparedStatement)localObject).execute();
      ((PreparedStatement)localObject).close();
    }
    else
    {
      if (paramLong1 >= 0L) {
        System.err.println("[DATABASE] Sector ID not found: " + paramLong1 + " at pos" + paramclass_48);
      }
      (localObject = paramConnection.prepareStatement("INSERT INTO SECTORS(X,Y,Z,TYPE,NAME,ITEMS,PROTECTION,STELLAR) VALUES(CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS TINYINT),CAST(? AS BIGINT));", 1)).setInt(1, paramclass_48.field_475);
      ((PreparedStatement)localObject).setInt(2, paramclass_48.field_476);
      ((PreparedStatement)localObject).setInt(3, paramclass_48.field_477);
      ((PreparedStatement)localObject).setInt(4, paramInt2);
      ((PreparedStatement)localObject).setString(5, "default");
      ((PreparedStatement)localObject).setLong(6, 0L);
      ((PreparedStatement)localObject).setInt(7, paramInt1);
      ((PreparedStatement)localObject).setLong(8, paramLong2);
      ((PreparedStatement)localObject).executeUpdate();
      (paramConnection = ((PreparedStatement)localObject).getGeneratedKeys()).next();
      paramLong1 = paramConnection.getLong(1);
      ((PreparedStatement)localObject).close();
    }
    paramLong1 = paramMap;
    long l = paramLong1;
    if ((paramConnection = localStatement).executeQuery("SELECT ID FROM SECTORS_ITEMS WHERE ID = " + l + ";").next())
    {
      if (paramLong1.isEmpty())
      {
        paramConnection.execute("DELETE FROM SECTORS_ITEMS WHERE ID = " + l + ";");
      }
      else
      {
        (paramConnection = paramConnection.getConnection().prepareStatement("UPDATE SECTORS_ITEMS SET (ITEMS) = (CAST(? AS VARBINARY(18432))) WHERE ID = CAST(? AS BIGINT);")).setBytes(1, class_670.a81(paramLong1));
        paramConnection.setLong(2, l);
        paramConnection.execute();
        paramConnection.close();
      }
    }
    else if (!paramLong1.isEmpty())
    {
      (paramConnection = paramConnection.getConnection().prepareStatement("INSERT INTO SECTORS_ITEMS(ID, ITEMS) VALUES(CAST(? AS BIGINT),CAST(? AS VARBINARY(18432)));")).setLong(1, l);
      paramConnection.setBytes(2, class_670.a81(paramLong1));
      paramConnection.execute();
      paramConnection.close();
    }
    localStatement.close();
  }
  
  public final void b()
  {
    Object localObject;
    if (!(localObject = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'SYSTEMS';").next())
    {
      f();
      h();
      g();
      File[] arrayOfFile;
      int j = (arrayOfFile = new File("./server-database/").listFiles()).length;
      a22(arrayOfFile, (Statement)localObject, null, j);
      b3(arrayOfFile, (Statement)localObject, null, j);
      localObject = new File("./server-database/").listFiles(new class_761());
      for (int i = 0; i < localObject.length; i++)
      {
        System.err.println("[MIGRATION] CLEANING UP DEPRECATED FILE: " + localObject[i].getName());
        localObject[i].delete();
      }
    }
  }
  
  public final void c()
  {
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    this.jdField_field_1731_of_type_JavaSqlConnection.getMetaData();
    ResultSet localResultSet = null;
    if (!localStatement.executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'ENTITIES' AND COLUMN_NAME = 'GEN_ID'").next())
    {
      System.err.println("[SQL] Database migration needed: GEN_ID");
      localStatement.executeUpdate("ALTER TABLE ENTITIES ADD COLUMN GEN_ID INT");
      localResultSet = localStatement.executeQuery("SELECT UID FROM ENTITIES WHERE TYPE = 3 AND TOUCHED = FALSE;");
      Random localRandom = new Random();
      while (localResultSet.next()) {
        localStatement.executeUpdate("UPDATE ENTITIES SET GEN_ID = " + localRandom.nextInt(class_902.values().length) + " WHERE TYPE = 3 AND UID = '" + localResultSet.getString(1) + "';");
      }
    }
    if (!localStatement.executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'ENTITIES' AND COLUMN_NAME = 'ID'").next())
    {
      System.err.println("[SQL] Database migration needed: ID");
      localStatement.executeUpdate("ALTER TABLE ENTITIES DROP PRIMARY KEY");
      localStatement.executeUpdate("ALTER TABLE ENTITIES ADD COLUMN ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1)");
      localResultSet = localStatement.executeQuery("SELECT UID,TYPE FROM ENTITIES;");
      for (long l = 0L; localResultSet.next(); l += 1L) {
        localStatement.executeUpdate("UPDATE ENTITIES SET ID = " + l + " WHERE TYPE = " + localResultSet.getByte(2) + " AND UID = '" + localResultSet.getString(1) + "';");
      }
      localStatement.executeUpdate("ALTER TABLE ENTITIES ALTER COLUMN ID RESTART WITH " + l + ";");
      localStatement.executeUpdate("ALTER TABLE ENTITIES ADD PRIMARY KEY(ID);");
      localStatement.execute("create unique index uidType on ENTITIES(UID,TYPE);");
    }
    localStatement.close();
  }
  
  private static void a16(Statement paramStatement, java.lang.String paramString1, class_48 paramclass_481, int paramInt1, long paramLong, java.lang.String paramString2, java.lang.String paramString3, java.lang.String paramString4, boolean paramBoolean, int paramInt2, Vector3f paramVector3f, class_48 paramclass_482, class_48 paramclass_483, int paramInt3)
  {
    try
    {
      if ((Float.isNaN(paramVector3f.field_615)) || (Float.isNaN(paramVector3f.field_616)) || (Float.isNaN(paramVector3f.field_617)))
      {
        paramVector3f.set(400.0F, 400.0F, 400.0F);
        System.err.println("Exception: POS NaN: ");
        System.err.println("UID: " + paramString1);
        System.err.println("SECPOS: " + paramclass_481);
        System.err.println("TYPE: " + paramInt1);
        System.err.println("SEED: " + paramLong);
        System.err.println("lastModifier: " + paramString2);
        System.err.println("spawner: " + paramString3);
        System.err.println("realName: " + paramString4);
        System.err.println("touched: " + paramBoolean);
        System.err.println("faction: " + paramInt2);
        System.err.println("pos: " + paramVector3f);
        System.err.println("min: " + paramclass_482);
        System.err.println("max: " + paramclass_483);
        System.err.println("creatorId: " + paramInt3);
      }
      long l1 = System.currentTimeMillis();
      long l2 = System.currentTimeMillis();
      ResultSet localResultSet = paramStatement.executeQuery("SELECT ID FROM ENTITIES WHERE UID = '" + paramString1 + "' AND TYPE = " + paramInt1 + ";");
      long l5 = System.currentTimeMillis() - l2;
      StringBuilder localStringBuilder;
      long l4;
      long l3;
      if (localResultSet.next())
      {
        l2 = System.currentTimeMillis();
        (localStringBuilder = new StringBuilder()).append("UPDATE ENTITIES SET (UID,X,Y,Z,TYPE,NAME,FACTION,CREATOR,LAST_MOD,SEED,TOUCHED,LOCAL_POS,DIM,GEN_ID,ID) = (");
        localStringBuilder.append("'").append(paramString1).append("',");
        localStringBuilder.append(paramclass_481.field_475).append(",");
        localStringBuilder.append(paramclass_481.field_476).append(",");
        localStringBuilder.append(paramclass_481.field_477).append(",");
        localStringBuilder.append(paramInt1).append(",");
        localStringBuilder.append("'").append(paramString4).append("',");
        localStringBuilder.append(paramInt2).append(",");
        localStringBuilder.append("'").append(paramString3).append("',");
        localStringBuilder.append("'").append(paramString2).append("',");
        localStringBuilder.append(paramLong).append(",");
        localStringBuilder.append(paramBoolean).append(",");
        localStringBuilder.append("ARRAY[").append(paramVector3f.field_615).append(",").append(paramVector3f.field_616).append(",").append(paramVector3f.field_617).append("],");
        localStringBuilder.append("ARRAY[").append(paramclass_482.field_475).append(",").append(paramclass_482.field_476).append(",").append(paramclass_482.field_477).append(",").append(paramclass_483.field_475).append(",").append(paramclass_483.field_476).append(",").append(paramclass_483.field_477).append("],");
        localStringBuilder.append(paramInt3).append(",");
        localStringBuilder.append("NULL");
        localStringBuilder.append(") WHERE ID = " + localResultSet.getLong(1) + ";");
        l4 = System.currentTimeMillis() - l2;
        l2 = System.currentTimeMillis();
        paramStatement.executeUpdate(localStringBuilder.toString());
        l3 = System.currentTimeMillis() - l2;
      }
      else
      {
        l2 = System.currentTimeMillis();
        (localStringBuilder = new StringBuilder()).append("INSERT INTO ENTITIES VALUES(");
        localStringBuilder.append("'").append(paramString1).append("',");
        localStringBuilder.append(paramclass_481.field_475).append(",");
        localStringBuilder.append(paramclass_481.field_476).append(",");
        localStringBuilder.append(paramclass_481.field_477).append(",");
        localStringBuilder.append(paramInt1).append(",");
        localStringBuilder.append("'").append(paramString4).append("',");
        localStringBuilder.append(paramInt2).append(",");
        localStringBuilder.append("'").append(paramString3).append("',");
        localStringBuilder.append("'").append(paramString2).append("',");
        localStringBuilder.append(paramLong).append(",");
        localStringBuilder.append(paramBoolean).append(",");
        localStringBuilder.append("ARRAY[").append(paramVector3f.field_615).append(",").append(paramVector3f.field_616).append(",").append(paramVector3f.field_617).append("],");
        localStringBuilder.append("ARRAY[").append(paramclass_482.field_475).append(",").append(paramclass_482.field_476).append(",").append(paramclass_482.field_477).append(",").append(paramclass_483.field_475).append(",").append(paramclass_483.field_476).append(",").append(paramclass_483.field_477).append("],");
        localStringBuilder.append(paramInt3).append(",");
        localStringBuilder.append("NULL");
        localStringBuilder.append(");");
        l4 = System.currentTimeMillis() - l2;
        l2 = System.currentTimeMillis();
        paramStatement.executeUpdate(localStringBuilder.toString());
        l3 = System.currentTimeMillis() - l2;
      }
      long l6;
      if ((l6 = System.currentTimeMillis() - l1) > 20L) {
        System.err.println("[SQL] WARNING: ROW UPDATE TOOK " + l6 + " on " + paramString1 + ": query: " + l5 + "; stringBuild: " + l4 + "; insertOrUpdate: " + l3);
      }
      return;
    }
    catch (SQLSyntaxErrorException localSQLSyntaxErrorException)
    {
      System.err.println("Exception: FAILED TO HANDLE SQL: ");
      System.err.println("UID: " + paramString1);
      System.err.println("SECPOS: " + paramclass_481);
      System.err.println("TYPE: " + paramInt1);
      System.err.println("SEED: " + paramLong);
      System.err.println("lastModifier: " + paramString2);
      System.err.println("spawner: " + paramString3);
      System.err.println("realName: " + paramString4);
      System.err.println("touched: " + paramBoolean);
      System.err.println("faction: " + paramInt2);
      System.err.println("pos: " + paramVector3f);
      System.err.println("min: " + paramclass_482);
      System.err.println("max: " + paramclass_483);
      System.err.println("creatorId: " + paramInt3);
      throw localSQLSyntaxErrorException;
    }
  }
  
  public final void a17(class_903 paramclass_903)
  {
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeUpdate("UPDATE ENTITIES SET TOUCHED = FALSE WHERE TYPE = 3 AND LAST_MOD = '';");
    ResultSet localResultSet1;
    (localResultSet1 = localStatement.executeQuery("SELECT COUNT(*) FROM ENTITIES WHERE TYPE = 3 AND LAST_MOD = '';")).next();
    float f1 = localResultSet1.getInt(1);
    ResultSet localResultSet2 = localStatement.executeQuery("SELECT UID, SEED FROM ENTITIES WHERE TYPE = 3 AND LAST_MOD = '';");
    Random localRandom = new Random();
    File[] arrayOfFile1 = new File(class_1041.field_144).listFiles();
    File[] arrayOfFile2 = new File(class_1041.field_149).listFiles();
    for (float f2 = 0.0F; localResultSet2.next(); f2 += 1.0F)
    {
      java.lang.String str1 = localResultSet2.getString(1).trim();
      long l = localResultSet2.getLong(2);
      java.lang.String str2 = "ENTITY_FLOATINGROCK_" + str1;
      if (paramclass_903 != null) {
        paramclass_903.a((int)(f2 / f1 * 100.0F) + "% (" + str2 + ")");
      }
      for (int i = 0; i < arrayOfFile1.length; i++) {
        if (arrayOfFile1[i].getName().startsWith(str2))
        {
          arrayOfFile1[i].delete();
          System.err.println("REMOVED FILE: " + str2);
          break;
        }
      }
      for (i = 0; i < arrayOfFile2.length; i++) {
        if (arrayOfFile2[i].getName().startsWith(str2))
        {
          arrayOfFile2[i].delete();
          System.err.println("REMOVED DATA FILE: " + str2);
        }
      }
      if ((f2 > 0.0F) && ((int)f2 % 200 == 0))
      {
        arrayOfFile1 = new File(class_1041.field_144).listFiles();
        arrayOfFile2 = new File(class_1041.field_149).listFiles();
      }
      if (l == 0L) {
        localStatement.executeUpdate("UPDATE ENTITIES SET SEED = " + localRandom.nextLong() + " WHERE TYPE = 3 AND UID = '" + str1 + "';");
      }
    }
    localStatement.close();
  }
  
  public final void a18(java.lang.String paramString1, class_48 paramclass_481, int paramInt1, long paramLong, java.lang.String paramString2, java.lang.String paramString3, java.lang.String paramString4, int paramInt2, Vector3f paramVector3f, class_48 paramclass_482, class_48 paramclass_483, int paramInt3)
  {
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    if ((!jdField_field_1731_of_type_Boolean) && (paramString1.startsWith("ENTITY_"))) {
      throw new AssertionError(paramString1);
    }
    a16(localStatement, paramString1, paramclass_481, paramInt1, paramLong, paramString2, paramString3, paramString4, false, paramInt2, paramVector3f, paramclass_482, paramclass_483, paramInt3);
    localStatement.close();
  }
  
  public final void a19(SegmentController paramSegmentController)
  {
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    if ((paramSegmentController == null) || (localStatement == null)) {
      throw new NullPointerException(paramSegmentController + ", " + localStatement);
    }
    SegmentController localSegmentController = paramSegmentController;
    paramSegmentController = localStatement;
    Object localObject = ((class_1041)localSegmentController.getState()).a62().getSector(localSegmentController.getSectorId());
    boolean bool;
    if ((localSegmentController instanceof class_784)) {
      bool = ((class_784)localSegmentController).a1();
    } else {
      bool = true;
    }
    if (localObject == null)
    {
      System.err.println("[SQL][WANRING] Sector null for " + localSegmentController + "; using transient sector: " + localSegmentController.transientSector);
      localObject = localSegmentController.transientSector;
    }
    else
    {
      localObject = ((class_670)localObject).jdField_field_136_of_type_Class_48;
    }
    a16(paramSegmentController, localSegmentController.getUniqueIdentifier().split("_", 3)[2], (class_48)localObject, (paramSegmentController instanceof class_747) ? 5 : (paramSegmentController instanceof class_864) ? 4 : (paramSegmentController instanceof class_705) ? 3 : (paramSegmentController instanceof class_737) ? 2 : ((paramSegmentController = localSegmentController) instanceof class_743) ? 1 : 0, localSegmentController.getSeed(), localSegmentController.getLastModifier(), localSegmentController.getSpawner(), localSegmentController.getRealName(), bool, localSegmentController.getFactionId(), localSegmentController.getWorldTransform().origin, localSegmentController.getMinPos(), localSegmentController.getMaxPos(), localSegmentController.getCreatorId());
    localStatement.close();
  }
  
  public final void b1(java.lang.String paramString)
  {
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeUpdate("DELETE FROM ENTITIES WHERE UID = '" + paramString.split("_", 3)[2] + "';");
    localStatement.close();
  }
  
  public final void b2(SegmentController paramSegmentController)
  {
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeUpdate("DELETE FROM ENTITIES WHERE UID = '" + paramSegmentController.getUniqueIdentifier().split("_", 3)[2] + "';");
    localStatement.close();
  }
  
  public final void a20(File paramFile)
  {
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    a21(paramFile, localStatement);
    localStatement.close();
  }
  
  private static void a21(File paramFile, Statement paramStatement)
  {
    if ((paramFile.getName().startsWith("ENTITY_SHOP")) || (paramFile.getName().startsWith("ENTITY_SPACESTATION")) || (paramFile.getName().startsWith("ENTITY_FLOATINGROCK")) || (paramFile.getName().startsWith("ENTITY_PLANET")) || (paramFile.getName().startsWith("ENTITY_SHIP")))
    {
      class_69 localclass_69 = class_69.a10(localObject1 = new DataInputStream(new FileInputStream(paramFile)), true);
      ((DataInputStream)localObject1).close();
      if (paramFile.getName().startsWith("ENTITY_SHOP")) {
        localObject1 = ((class_69[])localclass_69.a4())[0];
      } else if (paramFile.getName().startsWith("ENTITY_SPACESTATION")) {
        localObject1 = ((class_69[])localclass_69.a4())[1];
      } else if (paramFile.getName().startsWith("ENTITY_PLANET")) {
        localObject1 = ((class_69[])localclass_69.a4())[1];
      } else {
        localObject1 = localclass_69;
      }
      int i = 0;
      if (paramFile.getName().startsWith("ENTITY_SHOP")) {
        i = 1;
      } else if (paramFile.getName().startsWith("ENTITY_SPACESTATION")) {
        i = 2;
      } else if (paramFile.getName().startsWith("ENTITY_FLOATINGROCK")) {
        i = 3;
      } else if (paramFile.getName().startsWith("ENTITY_PLANET")) {
        i = 4;
      } else if (paramFile.getName().startsWith("ENTITY_SHIP")) {
        i = 5;
      }
      System.err.println("PARSING: " + paramFile.getName() + " -> " + i);
      Object localObject1 = (class_69[])(paramFile = (class_69[])((class_69)localObject1).a4())[6].a4();
      java.lang.String str1 = (java.lang.String)paramFile[0].a4();
      class_48 localclass_48 = (class_48)localObject1[3].a4();
      long l;
      if ((paramFile.length > 11) && (paramFile[11].a3() == class_79.field_552)) {
        l = ((Long)paramFile[11].a4()).longValue();
      } else {
        l = 0L;
      }
      java.lang.String str2;
      if ((paramFile.length > 10) && (paramFile[10].a3() == class_79.field_556)) {
        str2 = (java.lang.String)paramFile[10].a4();
      } else {
        str2 = "";
      }
      if (str2 == null) {
        str2 = "";
      }
      if (str2.startsWith("ENTITY_PLAYERSTATE_")) {
        str2 = str2.substring(19);
      }
      java.lang.String str3;
      if ((paramFile.length > 9) && (paramFile[9].a3() == class_79.field_556)) {
        str3 = (java.lang.String)paramFile[9].a4();
      } else {
        str3 = "";
      }
      if (str3 == null) {
        str3 = "";
      }
      if (str3.startsWith("ENTITY_PLAYERSTATE_")) {
        str3 = str3.substring(19);
      }
      boolean bool = true;
      if ((paramFile.length > 12) && (paramFile[12].a4() != null) && (paramFile[12].a3() == class_79.field_549)) {
        bool = ((Byte)paramFile[12].a4()).byteValue() == 1;
      }
      java.lang.String str4;
      if (paramFile[5].a4() != null) {
        str4 = (java.lang.String)paramFile[5].a4();
      } else {
        str4 = "undef";
      }
      int j = 0;
      if ((localObject1[4].a4() != null) && (localObject1[4].a3() == class_79.field_551))
      {
        j = ((Integer)localObject1[4].a4()).intValue();
        System.err.println("PARSED FACTION " + j);
      }
      else
      {
        System.err.println("COULD NOT PARSE FACTION " + localObject1[4].a3().name());
      }
      Object localObject2 = new float[(localObject1 = (class_69[])localObject1[1].a4()).length];
      for (int k = 0; k < localObject1.length; k++) {
        localObject2[k] = ((Float)localObject1[k].a4()).floatValue();
      }
      Transform localTransform;
      (localTransform = new Transform()).setFromOpenGLMatrix((float[])localObject2);
      localObject1 = (class_48)paramFile[1].a4();
      localObject2 = (class_48)paramFile[2].a4();
      paramFile = ((Integer)paramFile[8].a4()).intValue();
      a16(paramStatement, str1.split("_", 3)[2], localclass_48, i, l, str2, str3, str4, bool, j, localTransform.origin, (class_48)localObject1, (class_48)localObject2, paramFile);
    }
  }
  
  private static void a22(File[] paramArrayOfFile, Statement paramStatement, class_903 paramclass_903, int paramInt)
  {
    int i = 0;
    paramArrayOfFile = paramArrayOfFile;
    int j = paramArrayOfFile.length;
    for (int k = 0; k < j; k++)
    {
      File localFile;
      Statement localStatement = paramStatement;
      Object localObject1;
      if ((localObject1 = localFile = paramArrayOfFile[k]).getName().startsWith("VOIDSYSTEM"))
      {
        Object localObject2 = class_69.a10(localObject1 = new DataInputStream(new FileInputStream((File)localObject1)), true);
        ((DataInputStream)localObject1).close();
        class_69[] arrayOfclass_69;
        localObject1 = (class_48)(arrayOfclass_69 = (class_69[])((class_69)localObject2).a4())[0].a4();
        localObject2 = (byte[])arrayOfclass_69[1].a4();
        long l;
        if (arrayOfclass_69[2].a3() == class_79.field_552) {
          l = ((Long)arrayOfclass_69[2].a4()).longValue();
        } else {
          l = 0L;
        }
        a14(localStatement.getConnection(), 0L, (class_48)localObject1, 0, (byte[])localObject2, l, false);
        System.err.println("[SQL] INSERTED SYSTEM " + localObject1);
      }
      if (paramclass_903 != null) {
        paramclass_903.a((int)(i / paramInt * 100.0F) + "%  (" + localFile.getName() + ")");
      }
      i++;
    }
  }
  
  private static void b3(File[] paramArrayOfFile, Statement paramStatement, class_903 paramclass_903, int paramInt)
  {
    int i = 0;
    paramArrayOfFile = paramArrayOfFile;
    int j = paramArrayOfFile.length;
    for (int k = 0; k < j; k++)
    {
      File localFile;
      Statement localStatement = paramStatement;
      Object localObject;
      if ((localObject = localFile = paramArrayOfFile[k]).getName().startsWith("SECTOR"))
      {
        class_69 localclass_69 = class_69.a10(localObject = new DataInputStream(new FileInputStream((File)localObject)), true);
        ((DataInputStream)localObject).close();
        HashMap localHashMap = new HashMap();
        localObject = (class_48)(arrayOfclass_69 = (class_69[])localclass_69.a4())[0].a4();
        if ((!jdField_field_1731_of_type_Boolean) && (localObject == null)) {
          throw new AssertionError();
        }
        int m = ((Integer)arrayOfclass_69[2].a4()).intValue();
        class_69[] arrayOfclass_69 = (class_69[])arrayOfclass_69[3].a4();
        for (int n = 0; n < arrayOfclass_69.length - 1; n++)
        {
          class_637 localclass_637;
          (localclass_637 = new class_637()).fromTagStructure(arrayOfclass_69[n]);
          localclass_637.b6(class_1041.field_148++);
          if (localclass_637.a34() != 0) {
            localHashMap.put(Integer.valueOf(localclass_637.b5()), localclass_637);
          }
        }
        a15(localStatement.getConnection(), -1L, (class_48)localObject, m, localHashMap, 0, -1L);
        System.err.println("[SQL] INSERTED SECTOR " + localObject);
      }
      if (paramclass_903 != null) {
        paramclass_903.a((int)(i / paramInt * 100.0F) + "%  (" + localFile.getName() + ")");
      }
      i++;
    }
  }
  
  private static void c1(File[] paramArrayOfFile, Statement paramStatement, class_903 paramclass_903, int paramInt)
  {
    int i = 0;
    paramArrayOfFile = paramArrayOfFile;
    int j = paramArrayOfFile.length;
    for (int k = 0; k < j; k++)
    {
      File localFile;
      a21(localFile = paramArrayOfFile[k], paramStatement);
      if (paramclass_903 != null) {
        paramclass_903.a((int)(i / paramInt * 100.0F) + "%  (" + localFile.getName() + ")");
      }
      i++;
    }
  }
  
  public final void b4(class_903 paramclass_903)
  {
    File[] arrayOfFile = new File("./server-database/").listFiles();
    Statement localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement();
    int j = arrayOfFile.length;
    a22(arrayOfFile, localStatement, paramclass_903, j);
    b3(arrayOfFile, localStatement, paramclass_903, j);
    c1(arrayOfFile, localStatement, paramclass_903, j);
    paramclass_903 = new File("./server-database/").listFiles(new class_763());
    for (int i = 0; i < paramclass_903.length; i++)
    {
      System.err.println("[MIGRATION] CLEANING UP DEPRECATED FILE: " + paramclass_903[i].getName());
      paramclass_903[i].delete();
    }
    localStatement.close();
  }
  
  public static void main(java.lang.String[] paramArrayOfString)
  {
    (paramArrayOfString = new DatabaseIndex()).a4();
    paramArrayOfString.b4(null);
    paramArrayOfString.a13("SELECT * FROM SECTORS");
    paramArrayOfString.a13("SELECT * FROM SYSTEMS");
  }
  
  public final void d()
  {
    Statement localStatement;
    (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).execute("SHUTDOWN COMPACT;");
    localStatement.close();
    this.jdField_field_1731_of_type_JavaSqlConnection.close();
  }
  
  public static void e()
  {
    try
    {
      Class.forName("org.hsqldb.jdbc.JDBCDriver");
      return;
    }
    catch (Exception localException)
    {
      System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
      localException.printStackTrace();
    }
  }
  
  public final long a23(class_791 paramclass_791, boolean paramBoolean)
  {
    return a14(this.jdField_field_1731_of_type_JavaSqlConnection, paramclass_791.field_139, paramclass_791.jdField_field_136_of_type_Class_48, paramclass_791.jdField_field_136_of_type_Class_808.ordinal(), paramclass_791.jdField_field_136_of_type_ArrayOfByte, paramclass_791.jdField_field_136_of_type_Long, paramBoolean);
  }
  
  public final boolean a24(class_48 paramclass_48, class_789 paramclass_789)
  {
    try
    {
      Statement localStatement;
      ResultSet localResultSet1;
      if ((localResultSet1 = (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT ID FROM SYSTEMS WHERE X = " + paramclass_48.field_475 + " AND Y = " + paramclass_48.field_476 + " AND Z = " + paramclass_48.field_477 + ";")).next())
      {
        int i = localResultSet1.getInt(1);
        ResultSet localResultSet2;
        (localResultSet2 = localStatement.executeQuery("SELECT TYPE, STARTTIME, NAME, INFOS FROM SYSTEMS WHERE ID = " + i + ";")).next();
        long l = i;
        localObject = null;
        paramclass_789.field_139 = l;
        paramclass_789.jdField_field_136_of_type_Class_48.b1(paramclass_48);
        paramclass_789.jdField_field_136_of_type_Long = localResultSet2.getLong(2);
        localResultSet2.getString(3);
        paramclass_789.a82(localResultSet2.getBytes(4));
        localStatement.close();
        return true;
      }
      localStatement.close();
    }
    catch (SQLException localSQLException)
    {
      Object localObject = null;
      localSQLException.printStackTrace();
    }
    return false;
  }
  
  public final HashSet a25(class_48 paramclass_48)
  {
    Statement localStatement = null;
    try
    {
      paramclass_48 = (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT UID, TYPE FROM ENTITIES WHERE X = " + paramclass_48.field_475 + " AND Y = " + paramclass_48.field_476 + " AND Z = " + paramclass_48.field_477 + ";");
      HashSet localHashSet = new HashSet();
      while (paramclass_48.next())
      {
        localObject = class_757.a(paramclass_48.getString(1).trim(), paramclass_48.getInt(2));
        localHashSet.add(localObject);
      }
      Object localObject = localHashSet;
      return localObject;
    }
    catch (SQLException localSQLException) {}finally
    {
      try
      {
        localStatement.close();
      }
      catch (Exception localException3)
      {
        localException3;
      }
    }
    return new HashSet();
  }
  
  public final void a26(class_670 paramclass_670)
  {
    a15(this.jdField_field_1731_of_type_JavaSqlConnection, paramclass_670.b14(), paramclass_670.jdField_field_136_of_type_Class_48, paramclass_670.b5(), paramclass_670.a79(), paramclass_670.a68().ordinal(), -1L);
  }
  
  public final void a27(class_48 paramclass_48, Map paramMap, int paramInt)
  {
    a15(this.jdField_field_1731_of_type_JavaSqlConnection, -1L, paramclass_48, 0, paramMap, paramInt, -1L);
  }
  
  public final boolean a28(class_48 paramclass_48, class_670 paramclass_670)
  {
    Statement localStatement = null;
    try
    {
      ResultSet localResultSet;
      if ((localResultSet = (localStatement = this.jdField_field_1731_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT ID, TYPE, NAME, ITEMS, PROTECTION FROM SECTORS WHERE X = " + paramclass_48.field_475 + " AND Y = " + paramclass_48.field_476 + " AND Z = " + paramclass_48.field_477 + ";")).next())
      {
        paramclass_670.a38(localResultSet.getInt(1));
        paramclass_670.jdField_field_136_of_type_Class_48 = new class_48(paramclass_48);
        localResultSet.getString(3);
        class_670 localclass_670 = paramclass_670;
        paramclass_48 = null;
        if ((paramclass_48 = localStatement.executeQuery("SELECT ITEMS FROM SECTORS_ITEMS WHERE ID = " + localclass_670.b14() + ";")).next()) {
          localclass_670.a82(paramclass_48.getBytes(1));
        }
        paramclass_670.b6(localResultSet.getInt(5));
        return true;
      }
      return false;
    }
    catch (SQLException localSQLException)
    {
      localSQLException.printStackTrace();
    }
    finally
    {
      try
      {
        localStatement.close();
      }
      catch (Exception localException4)
      {
        localException4;
      }
    }
  }
  
  /* Error */
  public final void a29(class_48 paramclass_48)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 99	org/schema/game/common/controller/database/DatabaseIndex:jdField_field_1731_of_type_JavaSqlConnection	Ljava/sql/Connection;
    //   6: invokeinterface 116 1 0
    //   11: dup
    //   12: astore_2
    //   13: new 76	java/lang/StringBuilder
    //   16: dup
    //   17: ldc_w 1155
    //   20: invokespecial 80	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   23: aload_1
    //   24: getfield 157	class_48:field_475	I
    //   27: invokevirtual 160	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   30: ldc 162
    //   32: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: aload_1
    //   36: getfield 165	class_48:field_476	I
    //   39: invokevirtual 160	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   42: ldc 167
    //   44: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_1
    //   48: getfield 170	class_48:field_477	I
    //   51: invokevirtual 160	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   54: ldc 140
    //   56: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: invokeinterface 144 2 0
    //   67: pop
    //   68: aload_2
    //   69: invokeinterface 231 1 0
    //   74: return
    //   75: aconst_null
    //   76: astore_1
    //   77: invokevirtual 1072	java/lang/Exception:printStackTrace	()V
    //   80: return
    //   81: aconst_null
    //   82: astore_1
    //   83: invokevirtual 1109	java/sql/SQLException:printStackTrace	()V
    //   86: aload_2
    //   87: invokeinterface 231 1 0
    //   92: return
    //   93: invokevirtual 1072	java/lang/Exception:printStackTrace	()V
    //   96: return
    //   97: astore_1
    //   98: aload_2
    //   99: invokeinterface 231 1 0
    //   104: goto +6 -> 110
    //   107: invokevirtual 1072	java/lang/Exception:printStackTrace	()V
    //   110: aload_1
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	this	DatabaseIndex
    //   0	112	1	paramclass_48	class_48
    //   1	98	2	localStatement	Statement
    //   75	1	3	localException1	Exception
    //   81	1	4	localSQLException	SQLException
    //   93	1	5	localException2	Exception
    //   107	1	6	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   68	74	75	java/lang/Exception
    //   2	68	81	java/sql/SQLException
    //   86	92	93	java/lang/Exception
    //   2	68	97	finally
    //   81	86	97	finally
    //   98	104	107	java/lang/Exception
  }
  
  static
  {
    jdField_field_1731_of_type_JavaLangString = class_1041.field_144 + "index" + File.separator;
    java.lang.String[][] arrayOfString; = { { "", "\\x00", "\\\\0" }, { "'", "'", "\\\\'" }, { "\"", "\"", "\\\\\"" }, { "\b", "\\x08", "\\\\b" }, { "\n", "\\n", "\\\\n" }, { "\r", "\\r", "\\\\r" }, { "\t", "\\t", "\\\\t" }, { "\032", "\\x1A", "\\\\Z" }, { "\\", "\\\\", "\\\\\\\\" } };
    jdField_field_1731_of_type_JavaUtilHashMap = new HashMap();
    java.lang.String str = "";
    for ([Ljava.lang.String localString; : arrayOfString;)
    {
      jdField_field_1731_of_type_JavaUtilHashMap.put(localString;[0], localString;[2]);
      str = str + (str.isEmpty() ? "" : "|") + localString;[1];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.database.DatabaseIndex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */