/*      */ package org.schema.game.common.controller.database;
/*      */ 
/*      */ import Ad;
/*      */ import Af;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*      */ import jA;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.sql.Connection;
/*      */ import java.sql.DriverManager;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLSyntaxErrorException;
/*      */ import java.sql.Statement;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.table.DefaultTableModel;
/*      */ import javax.vecmath.Vector3f;
/*      */ import jd;
/*      */ import jy;
/*      */ import kB;
/*      */ import kF;
/*      */ import kd;
/*      */ import kf;
/*      */ import ki;
/*      */ import km;
/*      */ import kw;
/*      */ import kx;
/*      */ import ky;
/*      */ import kz;
/*      */ import mD;
/*      */ import mI;
/*      */ import mJ;
/*      */ import me;
/*      */ import mx;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.game.common.data.world.Universe;
/*      */ import org.schema.game.server.controller.EntityNotFountException;
/*      */ import q;
/*      */ import vg;
/*      */ 
/*      */ public class DatabaseIndex
/*      */ {
/*      */   private static java.lang.String jdField_a_of_type_JavaLangString;
/*      */   private Connection jdField_a_of_type_JavaSqlConnection;
/*      */   private static final HashMap jdField_a_of_type_JavaUtilHashMap;
/*   89 */   private static Pattern jdField_a_of_type_JavaUtilRegexPattern = Pattern.compile("(" + str + ')');
/*      */ 
/*      */   public static java.lang.String a(java.lang.String paramString)
/*      */   {
/*   95 */     paramString = jdField_a_of_type_JavaUtilRegexPattern.matcher(paramString);
/*   96 */     StringBuffer localStringBuffer = new StringBuffer();
/*   97 */     while (paramString.find())
/*      */     {
/*   99 */       paramString.appendReplacement(localStringBuffer, (java.lang.String)jdField_a_of_type_JavaUtilHashMap.get(paramString.group(1)));
/*      */     }
/*  101 */     paramString.appendTail(localStringBuffer);
/*  102 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   public DatabaseIndex() {
/*  106 */     System.err.println("[SQL] Fetching connection");
/*  107 */     this.jdField_a_of_type_JavaSqlConnection = DriverManager.getConnection("jdbc:hsqldb:file:" + jdField_a_of_type_JavaLangString, "SA", "");
/*  108 */     System.err.println("[SQL] connection successfull");
/*      */   }
/*      */ 
/*      */   public static boolean a()
/*      */   {
/*  124 */     return new File(jdField_a_of_type_JavaLangString).exists();
/*      */   }
/*      */ 
/*      */   public final int a(java.lang.String paramString, kB paramkB)
/*      */   {
/*  133 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  134 */     java.lang.String str = "";
/*  135 */     switch (kA.a[paramkB.ordinal()]) { case 1:
/*  136 */       str = "AND LAST_MOD = '' "; break;
/*      */     case 2:
/*  137 */       str = "AND NOT LAST_MOD = '' "; break;
/*      */     case 3:
/*  138 */       str = "";
/*      */     }
/*  140 */     return localStatement.executeUpdate("DELETE FROM ENTITIES WHERE UID LIKE '" + paramString + "' " + str + ";");
/*      */   }
/*      */ 
/*      */   public final int a(java.lang.String paramString, kB paramkB, q paramq, boolean paramBoolean)
/*      */   {
/*  150 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  151 */     Object localObject = "";
/*  152 */     switch (kA.a[paramkB.ordinal()]) { case 1:
/*  153 */       localObject = "AND LAST_MOD = '' "; break;
/*      */     case 2:
/*  154 */       localObject = "AND NOT LAST_MOD = '' "; break;
/*      */     case 3:
/*  155 */       localObject = "";
/*      */     }
/*  157 */     if (paramq != null) {
/*  158 */       localObject = (java.lang.String)localObject + "AND X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + " ";
/*      */     }
/*  160 */     if (paramBoolean) {
/*  161 */       localObject = (java.lang.String)localObject + " AND TYPE = 5 ";
/*      */     }
/*  163 */     paramString = localStatement.executeQuery("SELECT UID FROM ENTITIES WHERE UID LIKE '" + paramString + "' " + (java.lang.String)localObject + ";");
/*  164 */     paramkB = 0;
/*  165 */     while (paramString.next()) {
/*  166 */       paramq = paramString.getString(1).trim();
/*  167 */       localStatement.executeUpdate("DELETE FROM ENTITIES WHERE TYPE = 5 AND UID = '" + paramq + "'; ");
/*      */ 
/*  170 */       paramq = "ENTITY_SHIP_" + paramq;
/*      */ 
/*  172 */       paramBoolean = new File(vg.jdField_a_of_type_JavaLangString + paramq + ".ent")
/*  172 */         .delete();
/*      */       File[] arrayOfFile;
/*  183 */       int i = (arrayOfFile = localObject = new File(vg.f)
/*  176 */         .listFiles(new kx(paramq))).length;
/*      */ 
/*  183 */       for (int j = 0; j < i; j++) arrayOfFile[j]
/*  184 */           .delete();
/*      */ 
/*  186 */       System.err.println("[SQL] DESPAWNING: " + paramq + " DELETE SUC " + paramBoolean + "; DATA FOUND: " + localObject.length);
/*  187 */       paramkB++;
/*      */     }
/*      */ 
/*  190 */     localStatement.close();
/*  191 */     return paramkB;
/*      */   }
/*      */ 
/*      */   private void f()
/*      */   {
/*      */     Statement localStatement;
/*  195 */     (
/*  197 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/*  197 */       .execute("DROP TABLE SYSTEMS if exists;");
/*      */ 
/*  199 */     localStatement.execute("CREATE CACHED TABLE SYSTEMS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1), X INT not null, Y INT not null, Z INT not null, TYPE INT not null, STARTTIME BIGINT not null, NAME VARCHAR(64) not null, INFOS VARBINARY(8192) not null, primary key (ID));");
/*      */ 
/*  211 */     localStatement.execute("create unique index sysCoordIndex on SYSTEMS(X,Y,Z);");
/*  212 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   private void g()
/*      */   {
/*      */     Statement localStatement;
/*  217 */     (
/*  219 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/*  219 */       .execute("DROP TABLE SECTORS_ITEMS if exists;");
/*      */ 
/*  221 */     localStatement.execute("CREATE CACHED TABLE SECTORS_ITEMS(ID BIGINT, ITEMS VARBINARY(18432) not null, primary key (ID));");
/*      */ 
/*  227 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   private void h()
/*      */   {
/*      */     Statement localStatement;
/*  232 */     (
/*  234 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/*  234 */       .execute("DROP TABLE SECTORS if exists;");
/*      */ 
/*  236 */     localStatement.execute("CREATE CACHED TABLE SECTORS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1), X INT not null, Y INT not null, Z INT not null, TYPE INT not null, NAME VARCHAR(64) not null, ITEMS BIGINT not null, PROTECTION INT not null, STELLAR INT not null, primary key (ID));");
/*      */ 
/*  249 */     localStatement.execute("create index secTypeIndex on SECTORS(TYPE);");
/*  250 */     localStatement.execute("create index secStellarIndex on SECTORS(STELLAR);");
/*  251 */     localStatement.execute("create unique index secCoordIndex on SECTORS(X,Y,Z);");
/*  252 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   public final void a()
/*      */   {
/*  289 */     f();
/*  290 */     h();
/*  291 */     g();
/*      */     Statement localStatement;
/*  292 */     (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).execute("DROP TABLE ENTITIES if exists;"); localStatement.execute("CREATE CACHED TABLE ENTITIES(UID CHAR(64) not null, X INT not null, Y INT not null, Z INT not null, TYPE TINYINT not null, NAME char(64), FACTION INT default 0, CREATOR char(64), LAST_MOD char(64), SEED BIGINT, TOUCHED BOOLEAN, LOCAL_POS FLOAT ARRAY[3], DIM INT ARRAY[6], GEN_ID INT, ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1), primary key (ID));"); localStatement.execute("create index ENTITIES_PK on ENTITIES (UID);"); localStatement.execute("create unique index uidType on ENTITIES(UID,TYPE);"); localStatement.execute("create index coordIndex on ENTITIES(X,Y,Z);"); localStatement.execute("create index typeIndex on ENTITIES(TYPE);"); localStatement.close();
/*      */   }
/*      */ 
/*      */   public final List a(java.lang.String paramString)
/*      */   {
/*      */     Statement localStatement;
/*  302 */     (
/*  303 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/*  303 */       .setMaxRows(20);
/*      */ 
/*  306 */     java.lang.String str = "LIMIT 20";
/*      */ 
/*  308 */     paramString = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE UPPER(UID) LIKE UPPER('" + paramString + "') " + str + ";");
/*  309 */     localStatement.close();
/*  310 */     return a(paramString);
/*      */   }
/*      */ 
/*      */   public final List a(java.lang.String paramString, int paramInt)
/*      */   {
/*  315 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  316 */     java.lang.String str = "";
/*  317 */     if (paramInt > 0) {
/*  318 */       localStatement.setMaxRows(paramInt);
/*  319 */       str = "LIMIT " + paramInt;
/*      */     }
/*  321 */     paramString = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE UID = '" + paramString + "' " + str + ";");
/*  322 */     localStatement.close();
/*  323 */     return a(paramString);
/*      */   }
/*      */ 
/*      */   public final q a(java.lang.String paramString, q paramq)
/*      */   {
/*      */     Statement localStatement;
/*  328 */     ResultSet localResultSet = (
/*  328 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/*  328 */       .executeQuery("SELECT X, Y, Z FROM ENTITIES WHERE UID = '" + paramString + "';");
/*      */ 
/*  330 */     localStatement.close();
/*  331 */     if (localResultSet.next())
/*  332 */       paramq.b(localResultSet.getInt(1), localResultSet.getInt(2), localResultSet.getInt(3));
/*      */     else {
/*  334 */       throw new EntityNotFountException("Could not find Entity: " + paramString);
/*      */     }
/*  336 */     return paramq;
/*      */   }
/*      */ 
/*      */   public final void a(java.lang.String paramString, q paramq)
/*      */   {
/*      */     Statement localStatement;
/*  340 */     (
/*  341 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/*  341 */       .executeUpdate("UPDATE ENTITIES SET (X,Y,Z) = (" + paramq.a + ", " + paramq.b + ", " + paramq.c + ") WHERE UID = '" + paramString + "';");
/*  342 */     localStatement.close();
/*      */   }
/*      */   public final List a(q paramq, int paramInt) {
/*  345 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  346 */     if (paramInt >= 0) {
/*  347 */       localStatement.setMaxRows(Math.max(0, paramInt));
/*      */     }
/*  349 */     long l = System.currentTimeMillis();
/*  350 */     paramq = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";");
/*  351 */     System.err.println("[SQL] SECTOR QUERY TOOK " + (System.currentTimeMillis() - l) + "ms");
/*  352 */     localStatement.close();
/*  353 */     return a(paramq);
/*      */   }
/*      */   public final List a(q paramq1, q paramq2, int[] paramArrayOfInt) {
/*  356 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*      */ 
/*  359 */     long l = System.currentTimeMillis();
/*  360 */     StringBuilder localStringBuilder = new StringBuilder();
/*  361 */     java.lang.String str = "";
/*  362 */     if ((paramArrayOfInt != null) && (paramArrayOfInt.length > 0)) {
/*  363 */       localStringBuilder.append("(");
/*  364 */       for (int i = 0; i < paramArrayOfInt.length; i++) {
/*  365 */         localStringBuilder.append(paramArrayOfInt[i]);
/*  366 */         if (i < paramArrayOfInt.length - 1) {
/*  367 */           localStringBuilder.append(",");
/*      */         }
/*      */       }
/*      */ 
/*  371 */       localStringBuilder.append(")");
/*      */ 
/*  373 */       localObject = " TYPE IN " + localStringBuilder.toString() + " AND ";
/*      */     }
/*  375 */     Object localObject = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE " + (java.lang.String)localObject + "((X >= " + paramq1.a + " AND Y >= " + paramq1.b + " AND Z >= " + paramq1.c + ") AND (X < " + paramq2.a + " AND Y < " + paramq2.b + " AND Z < " + paramq2.c + "));");
/*  376 */     if (System.currentTimeMillis() - l > 20L) {
/*  377 */       System.err.println("[SQL] SECTOR QUERY TOOK " + (System.currentTimeMillis() - l) + "ms types: " + localStringBuilder + "; from " + paramq1 + " to " + paramq2);
/*      */     }
/*  379 */     localStatement.close();
/*  380 */     return a((ResultSet)localObject);
/*      */   }
/*      */ 
/*      */   private static List a(ResultSet paramResultSet) {
/*  384 */     long l = System.currentTimeMillis();
/*  385 */     ObjectArrayList localObjectArrayList = new ObjectArrayList();
/*  386 */     if (paramResultSet.next())
/*      */     {
/*      */       do
/*  389 */         localObjectArrayList.add(new kw(paramResultSet));
/*  390 */       while (paramResultSet.next());
/*      */     }
/*      */ 
/*  396 */     if (!paramResultSet.isClosed()) {
/*  397 */       paramResultSet.close();
/*      */     }
/*  399 */     if (System.currentTimeMillis() - l > 10L) {
/*  400 */       System.err.println("[SQL] SECTOR QUERY LIST CONVERSION TOOK " + (System.currentTimeMillis() - l) + "ms; list size: " + localObjectArrayList.size() + ";");
/*      */     }
/*  402 */     return localObjectArrayList;
/*      */   }
/*      */ 
/*      */   private static DefaultTableModel a(DefaultTableModel paramDefaultTableModel, ResultSet paramResultSet)
/*      */   {
/*  407 */     ResultSetMetaData localResultSetMetaData = paramResultSet.getMetaData();
/*  408 */     if (paramDefaultTableModel == null)
/*  409 */       paramDefaultTableModel = new DefaultTableModel();
/*  410 */     java.lang.String[] arrayOfString = new java.lang.String[localResultSetMetaData.getColumnCount()];
/*  411 */     for (int j = 0; j < arrayOfString.length; j++) {
/*  412 */       arrayOfString[j] = localResultSetMetaData.getColumnLabel(j + 1);
/*      */     }
/*      */ 
/*  415 */     paramDefaultTableModel.setColumnIdentifiers(arrayOfString);
/*      */ 
/*  417 */     while (paramResultSet.next()) {
/*  418 */       Object[] arrayOfObject = new Object[arrayOfString.length];
/*  419 */       for (int i = 0; i < arrayOfObject.length; i++) {
/*  420 */         arrayOfObject[i] = paramResultSet.getObject(i + 1);
/*      */       }
/*  422 */       paramDefaultTableModel.addRow(arrayOfObject);
/*      */     }
/*  424 */     return paramDefaultTableModel;
/*      */   }
/*      */ 
/*      */   public final void a(java.lang.String paramString)
/*      */   {
/*      */     Object localObject;
/*  430 */     paramString = (
/*  430 */       localObject = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/*  430 */       .executeQuery(paramString);
/*  431 */     ((Statement)localObject).close();
/*      */ 
/*  433 */     (
/*  434 */       localObject = new JFrame())
/*  434 */       .setDefaultCloseOperation(3);
/*  435 */     ((JFrame)localObject).setSize(1200, 700);
/*  436 */     ((JFrame)localObject).setContentPane(new JScrollPane(new JTable(a(new DefaultTableModel(), paramString))));
/*  437 */     ((JFrame)localObject).setVisible(true);
/*  438 */     paramString.close();
/*      */   }
/*      */ 
/*      */   private static long a(Connection paramConnection, long paramLong1, q paramq, int paramInt, byte[] paramArrayOfByte, long paramLong2, boolean paramBoolean)
/*      */   {
/*      */     Statement localStatement;
/*  443 */     (
/*  444 */       localStatement = paramConnection.createStatement())
/*  444 */       .setFetchSize(1);
/*      */ 
/*  448 */     if (localStatement.executeQuery("SELECT ID FROM SYSTEMS WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";")
/*  448 */       .next())
/*      */     {
/*  451 */       if (paramBoolean) {
/*  452 */         System.err.println("[DB] FORCE UPDATING SYSTEM " + paramq);
/*  453 */         (
/*  463 */           paramConnection = paramConnection.prepareStatement("UPDATE SYSTEMS SET (X,Y,Z,TYPE,NAME,STARTTIME,INFOS) = (CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS VARBINARY(8192))) WHERE ID = CAST(? AS BIGINT);"))
/*  463 */           .setInt(1, paramq.a);
/*  464 */         paramConnection.setInt(2, paramq.b);
/*  465 */         paramConnection.setInt(3, paramq.c);
/*  466 */         paramConnection.setInt(4, paramInt);
/*  467 */         paramConnection.setString(5, "default");
/*  468 */         paramConnection.setLong(6, paramLong2);
/*  469 */         paramConnection.setBytes(7, paramArrayOfByte);
/*  470 */         paramConnection.setLong(8, paramLong1);
/*      */ 
/*  472 */         paramConnection.execute();
/*      */ 
/*  474 */         paramConnection.close();
/*      */       }
/*      */     }
/*      */     else {
/*  478 */       System.err.println("[DATABASE][SYSTEMS] NO SYSTEM ENTRY FOUND FOR " + paramLong1 + " FOR " + paramq);
/*      */ 
/*  482 */       (
/*  492 */         paramConnection = paramConnection.prepareStatement("INSERT INTO SYSTEMS(X,Y,Z,TYPE,NAME,STARTTIME,INFOS) VALUES(CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS VARBINARY(8192)));", 1))
/*  492 */         .setInt(1, paramq.a);
/*  493 */       paramConnection.setInt(2, paramq.b);
/*  494 */       paramConnection.setInt(3, paramq.c);
/*  495 */       paramConnection.setInt(4, paramInt);
/*  496 */       paramConnection.setString(5, "default");
/*  497 */       paramConnection.setLong(6, paramLong2);
/*  498 */       paramConnection.setBytes(7, paramArrayOfByte);
/*  499 */       paramConnection.executeUpdate();
/*      */ 
/*  502 */       (
/*  503 */         paramLong1 = paramConnection.getGeneratedKeys())
/*  503 */         .next();
/*  504 */       paramLong1 = paramLong1.getLong(1);
/*      */ 
/*  508 */       paramConnection.close();
/*      */     }
/*  510 */     localStatement.close();
/*  511 */     return paramLong1;
/*      */   }
/*      */ 
/*      */   private static void a(Connection paramConnection, long paramLong1, q paramq, int paramInt1, Map paramMap, int paramInt2, long paramLong2)
/*      */   {
/*  526 */     Statement localStatement = paramConnection.createStatement();
/*      */     Object localObject;
/*  529 */     if (-1L < 0L) {
/*  530 */       localObject = mJ.a(paramq, new q());
/*      */ 
/*  532 */       (
/*  534 */         localObject = localStatement.executeQuery("SELECT ID FROM SYSTEMS WHERE X = " + ((q)localObject).a + " AND Y = " + ((q)localObject).b + " AND Z = " + ((q)localObject).c + ";"))
/*  534 */         .next();
/*  535 */       paramLong2 = ((ResultSet)localObject).getLong(1);
/*      */     }
/*      */ 
/*  539 */     if (localStatement.executeQuery("SELECT ID FROM SECTORS WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";")
/*  539 */       .next())
/*      */     {
/*  541 */       (
/*  549 */         localObject = paramConnection.prepareStatement("UPDATE SECTORS SET (TYPE,NAME,ITEMS,PROTECTION,STELLAR) = (CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS INT),CAST(? AS BIGINT)) WHERE ID = CAST(? AS BIGINT);"))
/*  549 */         .setInt(1, paramInt2);
/*  550 */       ((PreparedStatement)localObject).setString(2, "default");
/*  551 */       ((PreparedStatement)localObject).setLong(3, 0L);
/*  552 */       ((PreparedStatement)localObject).setInt(4, paramInt1);
/*  553 */       ((PreparedStatement)localObject).setLong(5, paramLong2);
/*  554 */       ((PreparedStatement)localObject).setLong(6, paramLong1);
/*      */ 
/*  556 */       ((PreparedStatement)localObject).execute();
/*      */ 
/*  558 */       ((PreparedStatement)localObject).close();
/*      */     } else {
/*  560 */       if (paramLong1 >= 0L) {
/*  561 */         System.err.println("[DATABASE] Sector ID not found: " + paramLong1 + " at pos" + paramq);
/*      */       }
/*  563 */       (
/*  574 */         localObject = paramConnection.prepareStatement("INSERT INTO SECTORS(X,Y,Z,TYPE,NAME,ITEMS,PROTECTION,STELLAR) VALUES(CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS TINYINT),CAST(? AS BIGINT));", 1))
/*  574 */         .setInt(1, paramq.a);
/*  575 */       ((PreparedStatement)localObject).setInt(2, paramq.b);
/*  576 */       ((PreparedStatement)localObject).setInt(3, paramq.c);
/*  577 */       ((PreparedStatement)localObject).setInt(4, paramInt2);
/*  578 */       ((PreparedStatement)localObject).setString(5, "default");
/*  579 */       ((PreparedStatement)localObject).setLong(6, 0L);
/*  580 */       ((PreparedStatement)localObject).setInt(7, paramInt1);
/*  581 */       ((PreparedStatement)localObject).setLong(8, paramLong2);
/*      */ 
/*  583 */       ((PreparedStatement)localObject).executeUpdate();
/*  584 */       (
/*  585 */         paramConnection = ((PreparedStatement)localObject).getGeneratedKeys())
/*  585 */         .next();
/*  586 */       paramLong1 = paramConnection.getLong(1);
/*  587 */       ((PreparedStatement)localObject).close();
/*      */     }
/*  589 */     paramLong1 = paramMap; long l = paramLong1; if ((paramConnection = localStatement).executeQuery("SELECT ID FROM SECTORS_ITEMS WHERE ID = " + l + ";").next()) { if (paramLong1.isEmpty()) { paramConnection.execute("DELETE FROM SECTORS_ITEMS WHERE ID = " + l + ";"); } else { (paramConnection = paramConnection.getConnection().prepareStatement("UPDATE SECTORS_ITEMS SET (ITEMS) = (CAST(? AS VARBINARY(18432))) WHERE ID = CAST(? AS BIGINT);")).setBytes(1, mx.a(paramLong1)); paramConnection.setLong(2, l); paramConnection.execute(); paramConnection.close(); } } else if (!paramLong1.isEmpty()) { (paramConnection = paramConnection.getConnection().prepareStatement("INSERT INTO SECTORS_ITEMS(ID, ITEMS) VALUES(CAST(? AS BIGINT),CAST(? AS VARBINARY(18432)));")).setLong(1, l); paramConnection.setBytes(2, mx.a(paramLong1)); paramConnection.execute(); paramConnection.close(); }
/*  590 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   public final void b()
/*      */   {
/*      */     Object localObject;
/*  631 */     if (!(
/*  627 */       localObject = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/*  627 */       .executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'SYSTEMS';")
/*  631 */       .next()) {
/*  632 */       f();
/*  633 */       h();
/*  634 */       g();
/*      */       File[] arrayOfFile;
/*  637 */       int j = (
/*  637 */         arrayOfFile = new File("./server-database/").listFiles()).length;
/*      */ 
/*  638 */       a(arrayOfFile, (Statement)localObject, null, j);
/*  639 */       b(arrayOfFile, (Statement)localObject, null, j);
/*      */ 
/*  641 */       localObject = new File("./server-database/").listFiles(new ky());
/*      */ 
/*  648 */       for (int i = 0; i < localObject.length; i++) {
/*  649 */         System.err.println("[MIGRATION] CLEANING UP DEPRECATED FILE: " + localObject[i].getName());
/*  650 */         localObject[i].delete();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void c() {
/*  656 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  657 */     this.jdField_a_of_type_JavaSqlConnection.getMetaData();
/*      */ 
/*  666 */     ResultSet localResultSet = null; if (!localStatement.executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'ENTITIES' AND COLUMN_NAME = 'GEN_ID'")
/*  666 */       .next()) {
/*  667 */       System.err.println("[SQL] Database migration needed: GEN_ID");
/*      */ 
/*  669 */       localStatement.executeUpdate("ALTER TABLE ENTITIES ADD COLUMN GEN_ID INT");
/*      */ 
/*  671 */       localResultSet = localStatement.executeQuery("SELECT UID FROM ENTITIES WHERE TYPE = 3 AND TOUCHED = FALSE;");
/*  672 */       Random localRandom = new Random();
/*      */ 
/*  674 */       while (localResultSet.next()) {
/*  675 */         localStatement.executeUpdate("UPDATE ENTITIES SET GEN_ID = " + localRandom.nextInt(kF.values().length) + " WHERE TYPE = 3 AND UID = '" + localResultSet.getString(1) + "';");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  686 */     if (!localStatement.executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'ENTITIES' AND COLUMN_NAME = 'ID'")
/*  686 */       .next()) {
/*  687 */       System.err.println("[SQL] Database migration needed: ID");
/*      */ 
/*  689 */       localStatement.executeUpdate("ALTER TABLE ENTITIES DROP PRIMARY KEY");
/*  690 */       localStatement.executeUpdate("ALTER TABLE ENTITIES ADD COLUMN ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1)");
/*      */ 
/*  695 */       localResultSet = localStatement.executeQuery("SELECT UID,TYPE FROM ENTITIES;");
/*  696 */       long l = 0L;
/*      */ 
/*  698 */       while (localResultSet.next()) {
/*  699 */         localStatement.executeUpdate("UPDATE ENTITIES SET ID = " + l + " WHERE TYPE = " + localResultSet.getByte(2) + " AND UID = '" + localResultSet.getString(1) + "';");
/*  700 */         l += 1L;
/*      */       }
/*  702 */       localStatement.executeUpdate("ALTER TABLE ENTITIES ALTER COLUMN ID RESTART WITH " + l + ";");
/*      */ 
/*  704 */       localStatement.executeUpdate("ALTER TABLE ENTITIES ADD PRIMARY KEY(ID);");
/*      */ 
/*  706 */       localStatement.execute("create unique index uidType on ENTITIES(UID,TYPE);");
/*      */     }
/*      */ 
/*  716 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   private static void a(Statement paramStatement, java.lang.String paramString1, q paramq1, int paramInt1, long paramLong, java.lang.String paramString2, java.lang.String paramString3, java.lang.String paramString4, boolean paramBoolean, int paramInt2, Vector3f paramVector3f, q paramq2, q paramq3, int paramInt3)
/*      */   {
/*      */     try {
/*  722 */       if ((Float.isNaN(paramVector3f.x)) || (Float.isNaN(paramVector3f.y)) || (Float.isNaN(paramVector3f.z))) {
/*  723 */         paramVector3f.set(400.0F, 400.0F, 400.0F);
/*  724 */         System.err.println("Exception: POS NaN: ");
/*  725 */         System.err.println("UID: " + paramString1);
/*  726 */         System.err.println("SECPOS: " + paramq1);
/*  727 */         System.err.println("TYPE: " + paramInt1);
/*  728 */         System.err.println("SEED: " + paramLong);
/*  729 */         System.err.println("lastModifier: " + paramString2);
/*  730 */         System.err.println("spawner: " + paramString3);
/*  731 */         System.err.println("realName: " + paramString4);
/*  732 */         System.err.println("touched: " + paramBoolean);
/*  733 */         System.err.println("faction: " + paramInt2);
/*  734 */         System.err.println("pos: " + paramVector3f);
/*  735 */         System.err.println("min: " + paramq2);
/*  736 */         System.err.println("max: " + paramq3);
/*  737 */         System.err.println("creatorId: " + paramInt3);
/*      */       }
/*  739 */       long l1 = System.currentTimeMillis();
/*  740 */       long l2 = System.currentTimeMillis();
/*  741 */       ResultSet localResultSet = paramStatement.executeQuery("SELECT ID FROM ENTITIES WHERE UID = '" + paramString1 + "' AND TYPE = " + paramInt1 + ";");
/*      */ 
/*  744 */       long l5 = System.currentTimeMillis() - l2;
/*      */       StringBuilder localStringBuilder;
/*      */       long l4;
/*      */       long l3;
/*  745 */       if (localResultSet.next())
/*      */       {
/*  763 */         l2 = System.currentTimeMillis();
/*      */ 
/*  810 */         (
/*  811 */           localStringBuilder = new StringBuilder())
/*  811 */           .append("UPDATE ENTITIES SET (UID,X,Y,Z,TYPE,NAME,FACTION,CREATOR,LAST_MOD,SEED,TOUCHED,LOCAL_POS,DIM,GEN_ID,ID) = (");
/*  812 */         localStringBuilder.append("'").append(paramString1).append("',");
/*  813 */         localStringBuilder.append(paramq1.a).append(",");
/*  814 */         localStringBuilder.append(paramq1.b).append(",");
/*  815 */         localStringBuilder.append(paramq1.c).append(",");
/*  816 */         localStringBuilder.append(paramInt1).append(",");
/*  817 */         localStringBuilder.append("'").append(paramString4).append("',");
/*  818 */         localStringBuilder.append(paramInt2).append(",");
/*  819 */         localStringBuilder.append("'").append(paramString3).append("',");
/*  820 */         localStringBuilder.append("'").append(paramString2).append("',");
/*  821 */         localStringBuilder.append(paramLong).append(",");
/*  822 */         localStringBuilder.append(paramBoolean).append(",");
/*  823 */         localStringBuilder.append("ARRAY[").append(paramVector3f.x).append(",").append(paramVector3f.y).append(",").append(paramVector3f.z).append("],");
/*  824 */         localStringBuilder.append("ARRAY[").append(paramq2.a).append(",").append(paramq2.b).append(",").append(paramq2.c).append(",").append(paramq3.a).append(",").append(paramq3.b).append(",").append(paramq3.c).append("],");
/*      */ 
/*  826 */         localStringBuilder.append(paramInt3).append(",");
/*  827 */         localStringBuilder.append("NULL");
/*  828 */         localStringBuilder.append(") WHERE ID = " + localResultSet.getLong(1) + ";");
/*  829 */         l4 = System.currentTimeMillis() - l2;
/*      */ 
/*  832 */         l2 = System.currentTimeMillis();
/*      */ 
/*  834 */         paramStatement.executeUpdate(localStringBuilder.toString());
/*  835 */         l3 = System.currentTimeMillis() - l2;
/*      */       }
/*      */       else
/*      */       {
/*  844 */         l2 = System.currentTimeMillis();
/*  845 */         (
/*  846 */           localStringBuilder = new StringBuilder())
/*  846 */           .append("INSERT INTO ENTITIES VALUES(");
/*  847 */         localStringBuilder.append("'").append(paramString1).append("',");
/*  848 */         localStringBuilder.append(paramq1.a).append(",");
/*  849 */         localStringBuilder.append(paramq1.b).append(",");
/*  850 */         localStringBuilder.append(paramq1.c).append(",");
/*  851 */         localStringBuilder.append(paramInt1).append(",");
/*  852 */         localStringBuilder.append("'").append(paramString4).append("',");
/*  853 */         localStringBuilder.append(paramInt2).append(",");
/*  854 */         localStringBuilder.append("'").append(paramString3).append("',");
/*  855 */         localStringBuilder.append("'").append(paramString2).append("',");
/*  856 */         localStringBuilder.append(paramLong).append(",");
/*  857 */         localStringBuilder.append(paramBoolean).append(",");
/*  858 */         localStringBuilder.append("ARRAY[").append(paramVector3f.x).append(",").append(paramVector3f.y).append(",").append(paramVector3f.z).append("],");
/*  859 */         localStringBuilder.append("ARRAY[").append(paramq2.a).append(",").append(paramq2.b).append(",").append(paramq2.c).append(",").append(paramq3.a).append(",").append(paramq3.b).append(",").append(paramq3.c).append("],");
/*      */ 
/*  861 */         localStringBuilder.append(paramInt3).append(",");
/*  862 */         localStringBuilder.append("NULL");
/*  863 */         localStringBuilder.append(");");
/*  864 */         l4 = System.currentTimeMillis() - l2;
/*      */ 
/*  866 */         l2 = System.currentTimeMillis();
/*  867 */         paramStatement.executeUpdate(localStringBuilder.toString());
/*  868 */         l3 = System.currentTimeMillis() - l2;
/*      */       }
/*      */       long l6;
/*  874 */       if ((
/*  874 */         l6 = System.currentTimeMillis() - l1) > 
/*  874 */         20L) {
/*  875 */         System.err.println("[SQL] WARNING: ROW UPDATE TOOK " + l6 + " on " + paramString1 + ": query: " + l5 + "; stringBuild: " + l4 + "; insertOrUpdate: " + l3);
/*      */       }
/*      */ 
/*  911 */       return;
/*      */     }
/*      */     catch (SQLSyntaxErrorException localSQLSyntaxErrorException)
/*      */     {
/*  895 */       System.err.println("Exception: FAILED TO HANDLE SQL: ");
/*  896 */       System.err.println("UID: " + paramString1);
/*  897 */       System.err.println("SECPOS: " + paramq1);
/*  898 */       System.err.println("TYPE: " + paramInt1);
/*  899 */       System.err.println("SEED: " + paramLong);
/*  900 */       System.err.println("lastModifier: " + paramString2);
/*  901 */       System.err.println("spawner: " + paramString3);
/*  902 */       System.err.println("realName: " + paramString4);
/*  903 */       System.err.println("touched: " + paramBoolean);
/*  904 */       System.err.println("faction: " + paramInt2);
/*  905 */       System.err.println("pos: " + paramVector3f);
/*  906 */       System.err.println("min: " + paramq2);
/*  907 */       System.err.println("max: " + paramq3);
/*  908 */       System.err.println("creatorId: " + paramInt3);
/*      */ 
/*  910 */       throw localSQLSyntaxErrorException;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void a(jd paramjd)
/*      */   {
/*      */     Statement localStatement;
/*  914 */     (
/*  915 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/*  915 */       .executeUpdate("UPDATE ENTITIES SET TOUCHED = FALSE WHERE TYPE = 3 AND LAST_MOD = '';");
/*      */     ResultSet localResultSet1;
/*  917 */     (
/*  918 */       localResultSet1 = localStatement.executeQuery("SELECT COUNT(*) FROM ENTITIES WHERE TYPE = 3 AND LAST_MOD = '';"))
/*  918 */       .next();
/*  919 */     float f1 = localResultSet1.getInt(1);
/*      */ 
/*  921 */     ResultSet localResultSet2 = localStatement.executeQuery("SELECT UID, SEED FROM ENTITIES WHERE TYPE = 3 AND LAST_MOD = '';");
/*      */ 
/*  923 */     Random localRandom = new Random();
/*  924 */     File[] arrayOfFile1 = new File(vg.jdField_a_of_type_JavaLangString).listFiles();
/*  925 */     File[] arrayOfFile2 = new File(vg.f).listFiles();
/*  926 */     float f2 = 0.0F;
/*  927 */     while (localResultSet2.next())
/*      */     {
/*  929 */       java.lang.String str1 = localResultSet2.getString(1).trim();
/*  930 */       long l = localResultSet2.getLong(2);
/*  931 */       java.lang.String str2 = "ENTITY_FLOATINGROCK_" + str1;
/*  932 */       if (paramjd != null) {
/*  933 */         paramjd.a((int)(f2 / f1 * 100.0F) + "% (" + str2 + ")");
/*      */       }
/*  935 */       for (int i = 0; i < arrayOfFile1.length; i++) {
/*  936 */         if (arrayOfFile1[i].getName().startsWith(str2)) {
/*  937 */           arrayOfFile1[i].delete();
/*  938 */           System.err.println("REMOVED FILE: " + str2);
/*  939 */           break;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  944 */       for (i = 0; i < arrayOfFile2.length; i++) {
/*  945 */         if (arrayOfFile2[i].getName().startsWith(str2)) {
/*  946 */           arrayOfFile2[i].delete();
/*  947 */           System.err.println("REMOVED DATA FILE: " + str2);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  952 */       if ((f2 > 0.0F) && ((int)f2 % 200 == 0)) {
/*  953 */         arrayOfFile1 = new File(vg.jdField_a_of_type_JavaLangString).listFiles();
/*  954 */         arrayOfFile2 = new File(vg.f).listFiles();
/*      */       }
/*  956 */       if (l == 0L) {
/*  957 */         localStatement.executeUpdate("UPDATE ENTITIES SET SEED = " + localRandom.nextLong() + " WHERE TYPE = 3 AND UID = '" + str1 + "';");
/*      */       }
/*  959 */       f2 += 1.0F;
/*      */     }
/*      */ 
/*  963 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   public final void a(java.lang.String paramString1, q paramq1, int paramInt1, long paramLong, java.lang.String paramString2, java.lang.String paramString3, java.lang.String paramString4, int paramInt2, Vector3f paramVector3f, q paramq2, q paramq3, int paramInt3)
/*      */   {
/*  968 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  969 */     if ((!jdField_a_of_type_Boolean) && (paramString1.startsWith("ENTITY_"))) throw new AssertionError(paramString1);
/*  970 */     a(localStatement, paramString1, paramq1, paramInt1, paramLong, paramString2, paramString3, paramString4, false, paramInt2, paramVector3f, paramq2, paramq3, paramInt3);
/*      */ 
/*  972 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   public final void a(SegmentController paramSegmentController)
/*      */   {
/*  999 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/* 1000 */     if ((paramSegmentController == null) || (localStatement == null)) {
/* 1001 */       throw new NullPointerException(paramSegmentController + ", " + localStatement);
/*      */     }
/* 1003 */     SegmentController localSegmentController = paramSegmentController; paramSegmentController = localStatement; Object localObject = ((vg)localSegmentController.getState()).a().getSector(localSegmentController.getSectorId());
/*      */     boolean bool;
/* 1003 */     if ((localSegmentController instanceof km)) bool = ((km)localSegmentController).a(); else bool = true; if (localObject == null) { System.err.println("[SQL][WANRING] Sector null for " + localSegmentController + "; using transient sector: " + localSegmentController.transientSector); localObject = localSegmentController.transientSector; } else { localObject = ((mx)localObject).jdField_a_of_type_Q; } a(paramSegmentController, localSegmentController.getUniqueIdentifier().split("_", 3)[2], (q)localObject, (paramSegmentController instanceof kd) ? 5 : (paramSegmentController instanceof jA) ? 4 : (paramSegmentController instanceof jy) ? 3 : (paramSegmentController instanceof ki) ? 2 : ((paramSegmentController = localSegmentController) instanceof kf) ? 1 : 0, localSegmentController.getSeed(), localSegmentController.getLastModifier(), localSegmentController.getSpawner(), localSegmentController.getRealName(), bool, localSegmentController.getFactionId(), localSegmentController.getWorldTransform().origin, localSegmentController.getMinPos(), localSegmentController.getMaxPos(), localSegmentController.getCreatorId());
/* 1004 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   public final void b(java.lang.String paramString)
/*      */   {
/*      */     Statement localStatement;
/* 1013 */     (
/* 1014 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/* 1014 */       .executeUpdate("DELETE FROM ENTITIES WHERE UID = '" + paramString.split("_", 3)[2] + "';");
/* 1015 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   public final void b(SegmentController paramSegmentController)
/*      */   {
/*      */     Statement localStatement;
/* 1018 */     (
/* 1019 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/* 1019 */       .executeUpdate("DELETE FROM ENTITIES WHERE UID = '" + paramSegmentController.getUniqueIdentifier().split("_", 3)[2] + "';");
/* 1020 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   public final void a(File paramFile)
/*      */   {
/* 1102 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/* 1103 */     a(paramFile, localStatement);
/* 1104 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   private static void a(File paramFile, Statement paramStatement) {
/* 1108 */     if ((paramFile.getName().startsWith("ENTITY_SHOP")) || (paramFile.getName().startsWith("ENTITY_SPACESTATION")) || (paramFile.getName().startsWith("ENTITY_FLOATINGROCK")) || (paramFile.getName().startsWith("ENTITY_PLANET")) || (paramFile.getName().startsWith("ENTITY_SHIP")))
/*      */     {
/* 1112 */       Ad localAd = Ad.a(localObject1 = new DataInputStream(new FileInputStream(paramFile)), 
/* 1112 */         true);
/* 1113 */       ((DataInputStream)localObject1).close();
/*      */ 
/* 1116 */       if (paramFile.getName().startsWith("ENTITY_SHOP"))
/* 1117 */         localObject1 = ((Ad[])localAd.a())[0];
/* 1118 */       else if (paramFile.getName().startsWith("ENTITY_SPACESTATION"))
/* 1119 */         localObject1 = ((Ad[])localAd.a())[1];
/* 1120 */       else if (paramFile.getName().startsWith("ENTITY_PLANET"))
/* 1121 */         localObject1 = ((Ad[])localAd.a())[1];
/*      */       else {
/* 1123 */         localObject1 = localAd;
/*      */       }
/* 1125 */       int i = 0;
/* 1126 */       if (paramFile.getName().startsWith("ENTITY_SHOP"))
/* 1127 */         i = 1;
/* 1128 */       else if (paramFile.getName().startsWith("ENTITY_SPACESTATION"))
/* 1129 */         i = 2;
/* 1130 */       else if (paramFile.getName().startsWith("ENTITY_FLOATINGROCK"))
/* 1131 */         i = 3;
/* 1132 */       else if (paramFile.getName().startsWith("ENTITY_PLANET"))
/*      */       {
/* 1134 */         i = 4;
/* 1135 */       } else if (paramFile.getName().startsWith("ENTITY_SHIP")) {
/* 1136 */         i = 5;
/*      */       }
/* 1138 */       System.err.println("PARSING: " + paramFile.getName() + " -> " + i);
/*      */ 
/* 1140 */       Object localObject1 = (Ad[])(
/* 1140 */         paramFile = (Ad[])((Ad)localObject1).a())[
/* 1140 */         6].a();
/*      */ 
/* 1144 */       java.lang.String str1 = (java.lang.String)paramFile[0].a();
/* 1145 */       q localq = (q)localObject1[3].a();
/*      */       long l;
/* 1147 */       if ((paramFile.length > 11) && (paramFile[11].a() == Af.e))
/* 1148 */         l = ((Long)paramFile[11].a()).longValue();
/*      */       else
/* 1150 */         l = 0L;
/*      */       java.lang.String str2;
/* 1153 */       if ((paramFile.length > 10) && (paramFile[10].a() == Af.i))
/* 1154 */         str2 = (java.lang.String)paramFile[10].a();
/*      */       else {
/* 1156 */         str2 = "";
/*      */       }
/* 1158 */       if (str2 == null) {
/* 1159 */         str2 = "";
/*      */       }
/* 1161 */       if (str2.startsWith("ENTITY_PLAYERSTATE_"))
/* 1162 */         str2 = str2.substring(19);
/*      */       java.lang.String str3;
/* 1166 */       if ((paramFile.length > 9) && (paramFile[9].a() == Af.i))
/* 1167 */         str3 = (java.lang.String)paramFile[9].a();
/*      */       else {
/* 1169 */         str3 = "";
/*      */       }
/* 1171 */       if (str3 == null) {
/* 1172 */         str3 = "";
/*      */       }
/* 1174 */       if (str3.startsWith("ENTITY_PLAYERSTATE_")) {
/* 1175 */         str3 = str3.substring(19);
/*      */       }
/* 1177 */       boolean bool = true;
/* 1178 */       if ((paramFile.length > 12) && (paramFile[12].a() != null) && (paramFile[12].a() == Af.b))
/* 1179 */         bool = ((Byte)paramFile[12].a()).byteValue() == 1;
/*      */       java.lang.String str4;
/* 1182 */       if (paramFile[5].a() != null)
/* 1183 */         str4 = (java.lang.String)paramFile[5].a();
/*      */       else {
/* 1185 */         str4 = "undef";
/*      */       }
/* 1187 */       int j = 0;
/*      */ 
/* 1189 */       if ((localObject1[4].a() != null) && (localObject1[4].a() == Af.d)) {
/* 1190 */         j = ((Integer)localObject1[4].a()).intValue();
/* 1191 */         System.err.println("PARSED FACTION " + j);
/*      */       } else {
/* 1193 */         System.err.println("COULD NOT PARSE FACTION " + localObject1[4].a().name());
/*      */       }
/*      */ 
/* 1197 */       Object localObject2 = new float[(
/* 1197 */         localObject1 = (Ad[])localObject1[1].a()).length];
/*      */ 
/* 1198 */       for (int k = 0; k < localObject1.length; k++)
/* 1199 */         localObject2[k] = ((Float)localObject1[k].a()).floatValue();
/*      */       Transform localTransform;
/* 1202 */       (
/* 1203 */         localTransform = new Transform())
/* 1203 */         .setFromOpenGLMatrix((float[])localObject2);
/*      */ 
/* 1205 */       localObject1 = (q)paramFile[1].a();
/* 1206 */       localObject2 = (q)paramFile[2].a();
/* 1207 */       paramFile = ((Integer)paramFile[8].a()).intValue();
/* 1208 */       a(paramStatement, str1.split("_", 3)[2], localq, i, l, str2, str3, str4, bool, j, localTransform.origin, (q)localObject1, (q)localObject2, paramFile);
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void a(File[] paramArrayOfFile, Statement paramStatement, jd paramjd, int paramInt)
/*      */   {
/* 1217 */     int i = 0;
/* 1218 */     paramArrayOfFile = paramArrayOfFile; int j = paramArrayOfFile.length; for (int k = 0; k < j; k++)
/*      */     {
/* 1219 */       File localFile;
/* 1219 */       Statement localStatement = paramStatement;
/*      */       Object localObject1;
/* 1219 */       if ((localObject1 = localFile = paramArrayOfFile[k])
/* 1219 */         .getName().startsWith("VOIDSYSTEM")) { Object localObject2 = Ad.a(localObject1 = new DataInputStream(new FileInputStream((File)localObject1)), true); ((DataInputStream)localObject1).close();
/*      */         Ad[] arrayOfAd;
/* 1219 */         localObject1 = (q)(arrayOfAd = (Ad[])((Ad)localObject2).a())[0].a(); localObject2 = (byte[])arrayOfAd[1].a();
/*      */         long l;
/* 1219 */         if (arrayOfAd[2].a() == Af.e) l = ((Long)arrayOfAd[2].a()).longValue(); else l = 0L; a(localStatement.getConnection(), 0L, (q)localObject1, 0, (byte[])localObject2, l, false); System.err.println("[SQL] INSERTED SYSTEM " + localObject1); }
/* 1220 */       if (paramjd != null) {
/* 1221 */         paramjd.a((int)(i / paramInt * 100.0F) + "%  (" + localFile.getName() + ")");
/*      */       }
/* 1223 */       i++;
/*      */     }
/*      */   }
/*      */ 
/* 1227 */   private static void b(File[] paramArrayOfFile, Statement paramStatement, jd paramjd, int paramInt) { int i = 0;
/* 1228 */     paramArrayOfFile = paramArrayOfFile; int j = paramArrayOfFile.length; for (int k = 0; k < j; k++)
/*      */     {
/* 1229 */       File localFile;
/* 1229 */       Statement localStatement = paramStatement;
/*      */       Object localObject;
/* 1229 */       if ((localObject = localFile = paramArrayOfFile[k])
/* 1229 */         .getName().startsWith("SECTOR")) { Ad localAd = Ad.a(localObject = new DataInputStream(new FileInputStream((File)localObject)), true); ((DataInputStream)localObject).close(); HashMap localHashMap = new HashMap(); localObject = (q)(arrayOfAd = (Ad[])localAd.a())[0].a(); if ((!jdField_a_of_type_Boolean) && (localObject == null)) throw new AssertionError(); int m = ((Integer)arrayOfAd[2].a()).intValue(); Ad[] arrayOfAd = (Ad[])arrayOfAd[3].a(); for (int n = 0; n < arrayOfAd.length - 1; n++)
/*      */         {
/* 1229 */           me localme;
/* 1229 */           (localme = new me()).fromTagStructure(arrayOfAd[n]); localme.b(vg.e++); if (localme.a() != 0) localHashMap.put(Integer.valueOf(localme.b()), localme);  } a(localStatement.getConnection(), -1L, (q)localObject, m, localHashMap, 0, -1L); System.err.println("[SQL] INSERTED SECTOR " + localObject); }
/* 1230 */       if (paramjd != null) {
/* 1231 */         paramjd.a((int)(i / paramInt * 100.0F) + "%  (" + localFile.getName() + ")");
/*      */       }
/* 1233 */       i++;
/*      */     } }
/*      */ 
/*      */   private static void c(File[] paramArrayOfFile, Statement paramStatement, jd paramjd, int paramInt) {
/* 1237 */     int i = 0;
/* 1238 */     paramArrayOfFile = paramArrayOfFile; int j = paramArrayOfFile.length; for (int k = 0; k < j; k++)
/*      */     {
/*      */       File localFile;
/* 1239 */       a(localFile = paramArrayOfFile[k], 
/* 1239 */         paramStatement);
/* 1240 */       if (paramjd != null) {
/* 1241 */         paramjd.a((int)(i / paramInt * 100.0F) + "%  (" + localFile.getName() + ")");
/*      */       }
/* 1243 */       i++;
/*      */     }
/*      */   }
/*      */ 
/* 1247 */   public final void b(jd paramjd) { File[] arrayOfFile = new File("./server-database/").listFiles();
/* 1248 */     Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/* 1249 */     int j = arrayOfFile.length;
/*      */ 
/* 1251 */     a(arrayOfFile, localStatement, paramjd, j);
/* 1252 */     b(arrayOfFile, localStatement, paramjd, j);
/* 1253 */     c(arrayOfFile, localStatement, paramjd, j);
/*      */ 
/* 1255 */     paramjd = new File("./server-database/").listFiles(new kz());
/*      */ 
/* 1262 */     for (int i = 0; i < paramjd.length; i++) {
/* 1263 */       System.err.println("[MIGRATION] CLEANING UP DEPRECATED FILE: " + paramjd[i].getName());
/* 1264 */       paramjd[i].delete();
/*      */     }
/* 1266 */     localStatement.close();
/*      */   }
/*      */ 
/*      */   public static void main(java.lang.String[] paramArrayOfString)
/*      */   {
/* 1272 */     (
/* 1273 */       paramArrayOfString = new DatabaseIndex())
/* 1273 */       .a();
/* 1274 */     paramArrayOfString.b(null);
/*      */ 
/* 1276 */     paramArrayOfString.a("SELECT * FROM SECTORS");
/* 1277 */     paramArrayOfString.a("SELECT * FROM SYSTEMS");
/*      */   }
/*      */ 
/*      */   public final void d()
/*      */   {
/*      */     Statement localStatement;
/* 1284 */     (
/* 1285 */       localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/* 1285 */       .execute("SHUTDOWN COMPACT;");
/* 1286 */     localStatement.close();
/* 1287 */     this.jdField_a_of_type_JavaSqlConnection.close();
/*      */   }
/*      */ 
/*      */   public static void e() { try { Class.forName("org.hsqldb.jdbc.JDBCDriver");
/*      */       return;
/*      */     } catch (Exception localException) {
/* 1294 */       System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
/* 1295 */       localException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public final long a(mI parammI, boolean paramBoolean)
/*      */   {
/* 1307 */     return a(this.jdField_a_of_type_JavaSqlConnection, parammI.b, parammI.jdField_a_of_type_Q, parammI.jdField_a_of_type_MD.ordinal(), parammI.jdField_a_of_type_ArrayOfByte, parammI.jdField_a_of_type_Long, paramBoolean);
/*      */   }
/*      */ 
/*      */   public final boolean a(q paramq, mJ parammJ)
/*      */   {
/*      */     try
/*      */     {
/*      */       Statement localStatement;
/*      */       ResultSet localResultSet1;
/* 1328 */       if ((
/* 1328 */         localResultSet1 = (
/* 1326 */         localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/* 1326 */         .executeQuery("SELECT ID FROM SYSTEMS WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";"))
/* 1328 */         .next()) {
/* 1329 */         int i = localResultSet1.getInt(1);
/*      */         ResultSet localResultSet2;
/* 1330 */         (
/* 1331 */           localResultSet2 = localStatement.executeQuery("SELECT TYPE, STARTTIME, NAME, INFOS FROM SYSTEMS WHERE ID = " + i + ";"))
/* 1331 */           .next();
/*      */ 
/* 1333 */         long l = i; localObject = null; parammJ.b = l;
/* 1334 */         parammJ.jdField_a_of_type_Q.b(paramq);
/* 1335 */         parammJ.jdField_a_of_type_Long = localResultSet2.getLong(2);
/* 1336 */         localResultSet2.getString(3);
/* 1337 */         parammJ.a(localResultSet2.getBytes(4));
/* 1338 */         localStatement.close();
/* 1339 */         return true;
/*      */       }
/* 1341 */       localStatement.close(); } catch (SQLException localSQLException) {
/* 1342 */       Object localObject = null;
/*      */ 
/* 1344 */       localSQLException.printStackTrace();
/*      */     }
/*      */ 
/* 1346 */     return false;
/*      */   }
/*      */   public final HashSet a(q paramq) {
/* 1349 */     Statement localStatement = null;
/*      */     try
/*      */     {
/* 1353 */       paramq = (
/* 1353 */         localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/* 1353 */         .executeQuery("SELECT UID, TYPE FROM ENTITIES WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";");
/* 1354 */       HashSet localHashSet = new HashSet();
/*      */       Object localObject;
/* 1356 */       while (paramq.next())
/*      */       {
/* 1359 */         localObject = kw.a(paramq.getString(1).trim(), 
/* 1359 */           paramq.getInt(2));
/*      */ 
/* 1361 */         localHashSet.add(localObject);
/*      */       }
/*      */ 
/* 1364 */       return localHashSet;
/*      */     } catch (SQLException localSQLException) {
/*      */     }
/*      */     finally {
/*      */       try {
/* 1369 */         localStatement.close();
/*      */       }
/*      */       catch (Exception localException3) {
/* 1372 */         localException3.printStackTrace();
/*      */       }
/*      */     }
/*      */ 
/* 1374 */     return new HashSet();
/*      */   }
/*      */ 
/*      */   public final void a(mx parammx)
/*      */   {
/* 1384 */     a(this.jdField_a_of_type_JavaSqlConnection, parammx.b(), parammx.jdField_a_of_type_Q, parammx.b(), parammx.a(), parammx.a().ordinal(), -1L);
/*      */   }
/*      */   public final void a(q paramq, Map paramMap, int paramInt) {
/* 1387 */     a(this.jdField_a_of_type_JavaSqlConnection, -1L, paramq, 0, paramMap, paramInt, -1L);
/*      */   }
/*      */   public final boolean a(q paramq, mx parammx) {
/* 1390 */     Statement localStatement = null;
/*      */     try
/*      */     {
/* 1396 */       if ((
/* 1396 */         localResultSet = (
/* 1395 */         localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/* 1395 */         .executeQuery("SELECT ID, TYPE, NAME, ITEMS, PROTECTION FROM SECTORS WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";"))
/* 1396 */         .next()) {
/* 1397 */         parammx.a(localResultSet.getInt(1));
/* 1398 */         parammx.jdField_a_of_type_Q = new q(paramq);
/* 1399 */         localResultSet.getString(3);
/*      */ 
/* 1401 */         mx localmx = parammx; paramq = null; if ((paramq = localStatement.executeQuery("SELECT ITEMS FROM SECTORS_ITEMS WHERE ID = " + localmx.b() + ";")).next()) localmx.a(paramq.getBytes(1));
/*      */ 
/* 1403 */         parammx.b(localResultSet.getInt(5));
/*      */ 
/* 1415 */         return true;
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*      */       ResultSet localResultSet;
/* 1416 */       localSQLException.printStackTrace();
/*      */     }
/*      */     finally {
/*      */       try {
/* 1412 */         localStatement.close();
/*      */       }
/*      */       catch (Exception localException4) {
/* 1415 */         localException4.printStackTrace();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1418 */     return false;
/*      */   }
/*      */   public final void a(q paramq) {
/* 1421 */     Statement localStatement = null;
/*      */     try {
/* 1423 */       (
/* 1426 */         localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement())
/* 1426 */         .executeUpdate("DELETE FROM SECTORS WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";");
/*      */       try {
/* 1434 */         localStatement.close();
/*      */         return; } catch (Exception localException1) {
/* 1435 */         paramq = null;
/*      */ 
/* 1437 */         localException1.printStackTrace();
/*      */         return;
/*      */       } } catch (SQLException localSQLException) { localSQLException.printStackTrace();
/*      */       try { localStatement.close();
/*      */         return; } catch (Exception localException2) { localException2.printStackTrace();
/*      */         return; }  } finally { try { localStatement.close();
/*      */       } catch (Exception localException3)
/*      */       {
/* 1437 */         localException3.printStackTrace();
/*      */       } }
/* 1437 */     throw paramq;
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*   59 */     jdField_a_of_type_JavaLangString = vg.jdField_a_of_type_JavaLangString + "index" + File.separator;
/*      */ 
/*   68 */     java.lang.String[][] arrayOfString; = { { "", "\\x00", "\\\\0" }, { "'", "'", "\\\\'" }, { "\"", "\"", "\\\\\"" }, { "\b", "\\x08", "\\\\b" }, { "\n", "\\n", "\\\\n" }, { "\r", "\\r", "\\\\r" }, { "\t", "\\t", "\\\\t" }, { "\032", "\\x1A", "\\\\Z" }, { "\\", "\\\\", "\\\\\\\\" } };
/*      */ 
/*   82 */     jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*   83 */     java.lang.String str = "";
/*   84 */     for ([Ljava.lang.String localString; : arrayOfString;)
/*      */     {
/*   86 */       jdField_a_of_type_JavaUtilHashMap.put(localString;[0], localString;[2]);
/*   87 */       str = str + (str.isEmpty() ? "" : "|") + localString;[1];
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.database.DatabaseIndex
 * JD-Core Version:    0.6.2
 */