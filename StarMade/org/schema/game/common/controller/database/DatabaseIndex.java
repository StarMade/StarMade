/*    1:     */package org.schema.game.common.controller.database;
/*    2:     */
/*    3:     */import Ah;
/*    4:     */import Aj;
/*    5:     */import com.bulletphysics.linearmath.Transform;
/*    6:     */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    7:     */import jA;
/*    8:     */import java.io.DataInputStream;
/*    9:     */import java.io.FileInputStream;
/*   10:     */import java.io.PrintStream;
/*   11:     */import java.sql.Connection;
/*   12:     */import java.sql.DriverManager;
/*   13:     */import java.sql.PreparedStatement;
/*   14:     */import java.sql.ResultSetMetaData;
/*   15:     */import java.sql.SQLException;
/*   16:     */import java.sql.SQLSyntaxErrorException;
/*   17:     */import java.util.HashMap;
/*   18:     */import java.util.HashSet;
/*   19:     */import java.util.List;
/*   20:     */import java.util.Map;
/*   21:     */import java.util.Random;
/*   22:     */import java.util.regex.Matcher;
/*   23:     */import java.util.regex.Pattern;
/*   24:     */import javax.swing.JFrame;
/*   25:     */import javax.swing.JScrollPane;
/*   26:     */import javax.swing.JTable;
/*   27:     */import javax.swing.table.DefaultTableModel;
/*   28:     */import javax.vecmath.Vector3f;
/*   29:     */import jd;
/*   30:     */import jy;
/*   31:     */import kB;
/*   32:     */import kF;
/*   33:     */import kd;
/*   34:     */import kf;
/*   35:     */import ki;
/*   36:     */import km;
/*   37:     */import kw;
/*   38:     */import kx;
/*   39:     */import ky;
/*   40:     */import kz;
/*   41:     */import mD;
/*   42:     */import mI;
/*   43:     */import mJ;
/*   44:     */import me;
/*   45:     */import mx;
/*   46:     */import org.schema.game.common.controller.SegmentController;
/*   47:     */import org.schema.game.common.data.world.Universe;
/*   48:     */import org.schema.game.server.controller.EntityNotFountException;
/*   49:     */import vg;
/*   50:     */
/*   51:     */public class DatabaseIndex
/*   52:     */{
/*   53:     */  private static java.lang.String jdField_a_of_type_JavaLangString;
/*   54:     */  private Connection jdField_a_of_type_JavaSqlConnection;
/*   55:     */  private static final HashMap jdField_a_of_type_JavaUtilHashMap;
/*   56:     */  
/*   57:     */  static
/*   58:     */  {
/*   59:  59 */    jdField_a_of_type_JavaLangString = vg.jdField_a_of_type_JavaLangString + "index" + java.io.File.separator;
/*   60:     */    
/*   68:  68 */    java.lang.String[][] arrayOfString; = { { "", "\\x00", "\\\\0" }, { "'", "'", "\\\\'" }, { "\"", "\"", "\\\\\"" }, { "\b", "\\x08", "\\\\b" }, { "\n", "\\n", "\\\\n" }, { "\r", "\\r", "\\\\r" }, { "\t", "\\t", "\\\\t" }, { "\032", "\\x1A", "\\\\Z" }, { "\\", "\\\\", "\\\\\\\\" } };
/*   69:     */    
/*   82:  82 */    jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*   83:  83 */    java.lang.String str = "";
/*   84:  84 */    for ([Ljava.lang.String localString; : arrayOfString;)
/*   85:     */    {
/*   86:  86 */      jdField_a_of_type_JavaUtilHashMap.put(localString;[0], localString;[2]);
/*   87:  87 */      str = str + (str.isEmpty() ? "" : "|") + localString;[1]; } }
/*   88:     */  
/*   89:  89 */  private static Pattern jdField_a_of_type_JavaUtilRegexPattern = Pattern.compile("(" + str + ')');
/*   90:     */  
/*   93:     */  public static java.lang.String a(java.lang.String paramString)
/*   94:     */  {
/*   95:  95 */    paramString = jdField_a_of_type_JavaUtilRegexPattern.matcher(paramString);
/*   96:  96 */    StringBuffer localStringBuffer = new StringBuffer();
/*   97:  97 */    while (paramString.find())
/*   98:     */    {
/*   99:  99 */      paramString.appendReplacement(localStringBuffer, (java.lang.String)jdField_a_of_type_JavaUtilHashMap.get(paramString.group(1)));
/*  100:     */    }
/*  101: 101 */    paramString.appendTail(localStringBuffer);
/*  102: 102 */    return localStringBuffer.toString();
/*  103:     */  }
/*  104:     */  
/*  105:     */  public DatabaseIndex() {
/*  106: 106 */    System.err.println("[SQL] Fetching connection");
/*  107: 107 */    this.jdField_a_of_type_JavaSqlConnection = DriverManager.getConnection("jdbc:hsqldb:file:" + jdField_a_of_type_JavaLangString, "SA", "");
/*  108: 108 */    System.err.println("[SQL] connection successfull");
/*  109:     */  }
/*  110:     */  
/*  122:     */  public static boolean a()
/*  123:     */  {
/*  124: 124 */    return new java.io.File(jdField_a_of_type_JavaLangString).exists();
/*  125:     */  }
/*  126:     */  
/*  131:     */  public final int a(java.lang.String paramString, kB paramkB)
/*  132:     */  {
/*  133: 133 */    java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  134: 134 */    java.lang.String str = "";
/*  135: 135 */    switch (kA.a[paramkB.ordinal()]) {
/*  136: 136 */    case 1:  str = "AND LAST_MOD = '' ";break;
/*  137: 137 */    case 2:  str = "AND NOT LAST_MOD = '' ";break;
/*  138: 138 */    case 3:  str = "";
/*  139:     */    }
/*  140: 140 */    return localStatement.executeUpdate("DELETE FROM ENTITIES WHERE UID LIKE '" + paramString + "' " + str + ";");
/*  141:     */  }
/*  142:     */  
/*  148:     */  public final int a(java.lang.String paramString, kB paramkB, q paramq, boolean paramBoolean)
/*  149:     */  {
/*  150: 150 */    java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  151: 151 */    Object localObject = "";
/*  152: 152 */    switch (kA.a[paramkB.ordinal()]) {
/*  153: 153 */    case 1:  localObject = "AND LAST_MOD = '' ";break;
/*  154: 154 */    case 2:  localObject = "AND NOT LAST_MOD = '' ";break;
/*  155: 155 */    case 3:  localObject = "";
/*  156:     */    }
/*  157: 157 */    if (paramq != null) {
/*  158: 158 */      localObject = (java.lang.String)localObject + "AND X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + " ";
/*  159:     */    }
/*  160: 160 */    if (paramBoolean) {
/*  161: 161 */      localObject = (java.lang.String)localObject + " AND TYPE = 5 ";
/*  162:     */    }
/*  163: 163 */    paramString = localStatement.executeQuery("SELECT UID FROM ENTITIES WHERE UID LIKE '" + paramString + "' " + (java.lang.String)localObject + ";");
/*  164: 164 */    paramkB = 0;
/*  165: 165 */    while (paramString.next()) {
/*  166: 166 */      paramq = paramString.getString(1).trim();
/*  167: 167 */      localStatement.executeUpdate("DELETE FROM ENTITIES WHERE TYPE = 5 AND UID = '" + paramq + "'; ");
/*  168:     */      
/*  170: 170 */      paramq = "ENTITY_SHIP_" + paramq;
/*  171:     */      
/*  172: 172 */      paramBoolean = new java.io.File(vg.jdField_a_of_type_JavaLangString + paramq + ".ent").delete();
/*  173:     */      
/*  178:     */      java.io.File[] arrayOfFile;
/*  179:     */      
/*  183: 183 */      int i = (arrayOfFile = localObject = new java.io.File(vg.f).listFiles(new kx(paramq))).length; for (int j = 0; j < i; j++) {
/*  184: 184 */        arrayOfFile[j].delete();
/*  185:     */      }
/*  186: 186 */      System.err.println("[SQL] DESPAWNING: " + paramq + " DELETE SUC " + paramBoolean + "; DATA FOUND: " + localObject.length);
/*  187: 187 */      paramkB++;
/*  188:     */    }
/*  189:     */    
/*  190: 190 */    localStatement.close();
/*  191: 191 */    return paramkB;
/*  192:     */  }
/*  193:     */  
/*  194:     */  private void f()
/*  195:     */  {
/*  196:     */    java.sql.Statement localStatement;
/*  197: 197 */    (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).execute("DROP TABLE SYSTEMS if exists;");
/*  198:     */    
/*  199: 199 */    localStatement.execute("CREATE CACHED TABLE SYSTEMS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1), X INT not null, Y INT not null, Z INT not null, TYPE INT not null, STARTTIME BIGINT not null, NAME VARCHAR(64) not null, INFOS VARBINARY(8192) not null, primary key (ID));");
/*  200:     */    
/*  211: 211 */    localStatement.execute("create unique index sysCoordIndex on SYSTEMS(X,Y,Z);");
/*  212: 212 */    localStatement.close();
/*  213:     */  }
/*  214:     */  
/*  216:     */  private void g()
/*  217:     */  {
/*  218:     */    java.sql.Statement localStatement;
/*  219: 219 */    (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).execute("DROP TABLE SECTORS_ITEMS if exists;");
/*  220:     */    
/*  221: 221 */    localStatement.execute("CREATE CACHED TABLE SECTORS_ITEMS(ID BIGINT, ITEMS VARBINARY(18432) not null, primary key (ID));");
/*  222:     */    
/*  227: 227 */    localStatement.close();
/*  228:     */  }
/*  229:     */  
/*  231:     */  private void h()
/*  232:     */  {
/*  233:     */    java.sql.Statement localStatement;
/*  234: 234 */    (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).execute("DROP TABLE SECTORS if exists;");
/*  235:     */    
/*  236: 236 */    localStatement.execute("CREATE CACHED TABLE SECTORS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1), X INT not null, Y INT not null, Z INT not null, TYPE INT not null, NAME VARCHAR(64) not null, ITEMS BIGINT not null, PROTECTION INT not null, STELLAR INT not null, primary key (ID));");
/*  237:     */    
/*  249: 249 */    localStatement.execute("create index secTypeIndex on SECTORS(TYPE);");
/*  250: 250 */    localStatement.execute("create index secStellarIndex on SECTORS(STELLAR);");
/*  251: 251 */    localStatement.execute("create unique index secCoordIndex on SECTORS(X,Y,Z);");
/*  252: 252 */    localStatement.close();
/*  253:     */  }
/*  254:     */  
/*  287:     */  public final void a()
/*  288:     */  {
/*  289: 289 */    f();
/*  290: 290 */    h();
/*  291: 291 */    g();
/*  292: 292 */    java.sql.Statement localStatement; (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).execute("DROP TABLE ENTITIES if exists;");localStatement.execute("CREATE CACHED TABLE ENTITIES(UID CHAR(64) not null, X INT not null, Y INT not null, Z INT not null, TYPE TINYINT not null, NAME char(64), FACTION INT default 0, CREATOR char(64), LAST_MOD char(64), SEED BIGINT, TOUCHED BOOLEAN, LOCAL_POS FLOAT ARRAY[3], DIM INT ARRAY[6], GEN_ID INT, ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1), primary key (ID));");localStatement.execute("create index ENTITIES_PK on ENTITIES (UID);");localStatement.execute("create unique index uidType on ENTITIES(UID,TYPE);");localStatement.execute("create index coordIndex on ENTITIES(X,Y,Z);");localStatement.execute("create index typeIndex on ENTITIES(TYPE);");localStatement.close();
/*  293:     */  }
/*  294:     */  
/*  298:     */  public final List a(java.lang.String paramString)
/*  299:     */  {
/*  300:     */    java.sql.Statement localStatement;
/*  301:     */    
/*  303: 303 */    (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).setMaxRows(20);
/*  304:     */    
/*  306: 306 */    java.lang.String str = "LIMIT 20";
/*  307:     */    
/*  308: 308 */    paramString = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE UPPER(UID) LIKE UPPER('" + paramString + "') " + str + ";");
/*  309: 309 */    localStatement.close();
/*  310: 310 */    return a(paramString);
/*  311:     */  }
/*  312:     */  
/*  313:     */  public final List a(java.lang.String paramString, int paramInt)
/*  314:     */  {
/*  315: 315 */    java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  316: 316 */    java.lang.String str = "";
/*  317: 317 */    if (paramInt > 0) {
/*  318: 318 */      localStatement.setMaxRows(paramInt);
/*  319: 319 */      str = "LIMIT " + paramInt;
/*  320:     */    }
/*  321: 321 */    paramString = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE UID = '" + paramString + "' " + str + ";");
/*  322: 322 */    localStatement.close();
/*  323: 323 */    return a(paramString);
/*  324:     */  }
/*  325:     */  
/*  326:     */  public final q a(java.lang.String paramString, q paramq) {
/*  327:     */    java.sql.Statement localStatement;
/*  328: 328 */    java.sql.ResultSet localResultSet = (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT X, Y, Z FROM ENTITIES WHERE UID = '" + paramString + "';");
/*  329:     */    
/*  330: 330 */    localStatement.close();
/*  331: 331 */    if (localResultSet.next()) {
/*  332: 332 */      paramq.b(localResultSet.getInt(1), localResultSet.getInt(2), localResultSet.getInt(3));
/*  333:     */    } else {
/*  334: 334 */      throw new EntityNotFountException("Could not find Entity: " + paramString);
/*  335:     */    }
/*  336: 336 */    return paramq;
/*  337:     */  }
/*  338:     */  
/*  339:     */  public final void a(java.lang.String paramString, q paramq) {
/*  340:     */    java.sql.Statement localStatement;
/*  341: 341 */    (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeUpdate("UPDATE ENTITIES SET (X,Y,Z) = (" + paramq.a + ", " + paramq.b + ", " + paramq.c + ") WHERE UID = '" + paramString + "';");
/*  342: 342 */    localStatement.close();
/*  343:     */  }
/*  344:     */  
/*  345: 345 */  public final List a(q paramq, int paramInt) { java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  346: 346 */    if (paramInt >= 0) {
/*  347: 347 */      localStatement.setMaxRows(Math.max(0, paramInt));
/*  348:     */    }
/*  349: 349 */    long l = System.currentTimeMillis();
/*  350: 350 */    paramq = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";");
/*  351: 351 */    System.err.println("[SQL] SECTOR QUERY TOOK " + (System.currentTimeMillis() - l) + "ms");
/*  352: 352 */    localStatement.close();
/*  353: 353 */    return a(paramq);
/*  354:     */  }
/*  355:     */  
/*  356: 356 */  public final List a(q paramq1, q paramq2, int[] paramArrayOfInt) { java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  357:     */    
/*  359: 359 */    long l = System.currentTimeMillis();
/*  360: 360 */    StringBuilder localStringBuilder = new StringBuilder();
/*  361: 361 */    java.lang.String str = "";
/*  362: 362 */    if ((paramArrayOfInt != null) && (paramArrayOfInt.length > 0)) {
/*  363: 363 */      localStringBuilder.append("(");
/*  364: 364 */      for (int i = 0; i < paramArrayOfInt.length; i++) {
/*  365: 365 */        localStringBuilder.append(paramArrayOfInt[i]);
/*  366: 366 */        if (i < paramArrayOfInt.length - 1) {
/*  367: 367 */          localStringBuilder.append(",");
/*  368:     */        }
/*  369:     */      }
/*  370:     */      
/*  371: 371 */      localStringBuilder.append(")");
/*  372:     */      
/*  373: 373 */      localObject = " TYPE IN " + localStringBuilder.toString() + " AND ";
/*  374:     */    }
/*  375: 375 */    Object localObject = localStatement.executeQuery("SELECT * FROM ENTITIES WHERE " + (java.lang.String)localObject + "((X >= " + paramq1.a + " AND Y >= " + paramq1.b + " AND Z >= " + paramq1.c + ") AND (X < " + paramq2.a + " AND Y < " + paramq2.b + " AND Z < " + paramq2.c + "));");
/*  376: 376 */    if (System.currentTimeMillis() - l > 20L) {
/*  377: 377 */      System.err.println("[SQL] SECTOR QUERY TOOK " + (System.currentTimeMillis() - l) + "ms types: " + localStringBuilder + "; from " + paramq1 + " to " + paramq2);
/*  378:     */    }
/*  379: 379 */    localStatement.close();
/*  380: 380 */    return a((java.sql.ResultSet)localObject);
/*  381:     */  }
/*  382:     */  
/*  383:     */  private static List a(java.sql.ResultSet paramResultSet) {
/*  384: 384 */    long l = System.currentTimeMillis();
/*  385: 385 */    ObjectArrayList localObjectArrayList = new ObjectArrayList();
/*  386: 386 */    if (paramResultSet.next()) {
/*  387:     */      do
/*  388:     */      {
/*  389: 389 */        localObjectArrayList.add(new kw(paramResultSet));
/*  390: 390 */      } while (paramResultSet.next());
/*  391:     */    }
/*  392:     */    
/*  396: 396 */    if (!paramResultSet.isClosed()) {
/*  397: 397 */      paramResultSet.close();
/*  398:     */    }
/*  399: 399 */    if (System.currentTimeMillis() - l > 10L) {
/*  400: 400 */      System.err.println("[SQL] SECTOR QUERY LIST CONVERSION TOOK " + (System.currentTimeMillis() - l) + "ms; list size: " + localObjectArrayList.size() + ";");
/*  401:     */    }
/*  402: 402 */    return localObjectArrayList;
/*  403:     */  }
/*  404:     */  
/*  405:     */  private static DefaultTableModel a(DefaultTableModel paramDefaultTableModel, java.sql.ResultSet paramResultSet)
/*  406:     */  {
/*  407: 407 */    ResultSetMetaData localResultSetMetaData = paramResultSet.getMetaData();
/*  408: 408 */    if (paramDefaultTableModel == null)
/*  409: 409 */      paramDefaultTableModel = new DefaultTableModel();
/*  410: 410 */    java.lang.String[] arrayOfString = new java.lang.String[localResultSetMetaData.getColumnCount()];
/*  411: 411 */    for (int j = 0; j < arrayOfString.length; j++) {
/*  412: 412 */      arrayOfString[j] = localResultSetMetaData.getColumnLabel(j + 1);
/*  413:     */    }
/*  414:     */    
/*  415: 415 */    paramDefaultTableModel.setColumnIdentifiers(arrayOfString);
/*  416:     */    
/*  417: 417 */    while (paramResultSet.next()) {
/*  418: 418 */      Object[] arrayOfObject = new Object[arrayOfString.length];
/*  419: 419 */      for (int i = 0; i < arrayOfObject.length; i++) {
/*  420: 420 */        arrayOfObject[i] = paramResultSet.getObject(i + 1);
/*  421:     */      }
/*  422: 422 */      paramDefaultTableModel.addRow(arrayOfObject);
/*  423:     */    }
/*  424: 424 */    return paramDefaultTableModel;
/*  425:     */  }
/*  426:     */  
/*  427:     */  public final void a(java.lang.String paramString)
/*  428:     */  {
/*  429:     */    Object localObject;
/*  430: 430 */    paramString = (localObject = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeQuery(paramString);
/*  431: 431 */    ((java.sql.Statement)localObject).close();
/*  432:     */    
/*  433: 433 */    (
/*  434: 434 */      localObject = new JFrame()).setDefaultCloseOperation(3);
/*  435: 435 */    ((JFrame)localObject).setSize(1200, 700);
/*  436: 436 */    ((JFrame)localObject).setContentPane(new JScrollPane(new JTable(a(new DefaultTableModel(), paramString))));
/*  437: 437 */    ((JFrame)localObject).setVisible(true);
/*  438: 438 */    paramString.close();
/*  439:     */  }
/*  440:     */  
/*  441:     */  private static long a(Connection paramConnection, long paramLong1, q paramq, int paramInt, byte[] paramArrayOfByte, long paramLong2, boolean paramBoolean)
/*  442:     */  {
/*  443:     */    java.sql.Statement localStatement;
/*  444: 444 */    (localStatement = paramConnection.createStatement()).setFetchSize(1);
/*  445:     */    
/*  448: 448 */    if (localStatement.executeQuery("SELECT ID FROM SYSTEMS WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";").next())
/*  449:     */    {
/*  451: 451 */      if (paramBoolean) {
/*  452: 452 */        System.err.println("[DB] FORCE UPDATING SYSTEM " + paramq);
/*  453: 453 */        (
/*  454:     */        
/*  463: 463 */          paramConnection = paramConnection.prepareStatement("UPDATE SYSTEMS SET (X,Y,Z,TYPE,NAME,STARTTIME,INFOS) = (CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS VARBINARY(8192))) WHERE ID = CAST(? AS BIGINT);")).setInt(1, paramq.a);
/*  464: 464 */        paramConnection.setInt(2, paramq.b);
/*  465: 465 */        paramConnection.setInt(3, paramq.c);
/*  466: 466 */        paramConnection.setInt(4, paramInt);
/*  467: 467 */        paramConnection.setString(5, "default");
/*  468: 468 */        paramConnection.setLong(6, paramLong2);
/*  469: 469 */        paramConnection.setBytes(7, paramArrayOfByte);
/*  470: 470 */        paramConnection.setLong(8, paramLong1);
/*  471:     */        
/*  472: 472 */        paramConnection.execute();
/*  473:     */        
/*  474: 474 */        paramConnection.close();
/*  475:     */      }
/*  476:     */    }
/*  477:     */    else {
/*  478: 478 */      System.err.println("[DATABASE][SYSTEMS] NO SYSTEM ENTRY FOUND FOR " + paramLong1 + " FOR " + paramq);
/*  479:     */      
/*  482: 482 */      (
/*  483:     */      
/*  492: 492 */        paramConnection = paramConnection.prepareStatement("INSERT INTO SYSTEMS(X,Y,Z,TYPE,NAME,STARTTIME,INFOS) VALUES(CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS VARBINARY(8192)));", 1)).setInt(1, paramq.a);
/*  493: 493 */      paramConnection.setInt(2, paramq.b);
/*  494: 494 */      paramConnection.setInt(3, paramq.c);
/*  495: 495 */      paramConnection.setInt(4, paramInt);
/*  496: 496 */      paramConnection.setString(5, "default");
/*  497: 497 */      paramConnection.setLong(6, paramLong2);
/*  498: 498 */      paramConnection.setBytes(7, paramArrayOfByte);
/*  499: 499 */      paramConnection.executeUpdate();
/*  500:     */      
/*  502: 502 */      (
/*  503: 503 */        paramLong1 = paramConnection.getGeneratedKeys()).next();
/*  504: 504 */      paramLong1 = paramLong1.getLong(1);
/*  505:     */      
/*  508: 508 */      paramConnection.close();
/*  509:     */    }
/*  510: 510 */    localStatement.close();
/*  511: 511 */    return paramLong1;
/*  512:     */  }
/*  513:     */  
/*  524:     */  private static void a(Connection paramConnection, long paramLong1, q paramq, int paramInt1, Map paramMap, int paramInt2, long paramLong2)
/*  525:     */  {
/*  526: 526 */    java.sql.Statement localStatement = paramConnection.createStatement();
/*  527:     */    
/*  528:     */    Object localObject;
/*  529: 529 */    if (-1L < 0L) {
/*  530: 530 */      localObject = mJ.a(paramq, new q());
/*  531:     */      
/*  532: 532 */      (
/*  533:     */      
/*  534: 534 */        localObject = localStatement.executeQuery("SELECT ID FROM SYSTEMS WHERE X = " + ((q)localObject).a + " AND Y = " + ((q)localObject).b + " AND Z = " + ((q)localObject).c + ";")).next();
/*  535: 535 */      paramLong2 = ((java.sql.ResultSet)localObject).getLong(1);
/*  536:     */    }
/*  537:     */    
/*  539: 539 */    if (localStatement.executeQuery("SELECT ID FROM SECTORS WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";").next())
/*  540:     */    {
/*  549: 549 */      (localObject = paramConnection.prepareStatement("UPDATE SECTORS SET (TYPE,NAME,ITEMS,PROTECTION,STELLAR) = (CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS INT),CAST(? AS BIGINT)) WHERE ID = CAST(? AS BIGINT);")).setInt(1, paramInt2);
/*  550: 550 */      ((PreparedStatement)localObject).setString(2, "default");
/*  551: 551 */      ((PreparedStatement)localObject).setLong(3, 0L);
/*  552: 552 */      ((PreparedStatement)localObject).setInt(4, paramInt1);
/*  553: 553 */      ((PreparedStatement)localObject).setLong(5, paramLong2);
/*  554: 554 */      ((PreparedStatement)localObject).setLong(6, paramLong1);
/*  555:     */      
/*  556: 556 */      ((PreparedStatement)localObject).execute();
/*  557:     */      
/*  558: 558 */      ((PreparedStatement)localObject).close();
/*  559:     */    } else {
/*  560: 560 */      if (paramLong1 >= 0L) {
/*  561: 561 */        System.err.println("[DATABASE] Sector ID not found: " + paramLong1 + " at pos" + paramq);
/*  562:     */      }
/*  563:     */      
/*  574: 574 */      (localObject = paramConnection.prepareStatement("INSERT INTO SECTORS(X,Y,Z,TYPE,NAME,ITEMS,PROTECTION,STELLAR) VALUES(CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS VARCHAR(64)),CAST(? AS BIGINT),CAST(? AS TINYINT),CAST(? AS BIGINT));", 1)).setInt(1, paramq.a);
/*  575: 575 */      ((PreparedStatement)localObject).setInt(2, paramq.b);
/*  576: 576 */      ((PreparedStatement)localObject).setInt(3, paramq.c);
/*  577: 577 */      ((PreparedStatement)localObject).setInt(4, paramInt2);
/*  578: 578 */      ((PreparedStatement)localObject).setString(5, "default");
/*  579: 579 */      ((PreparedStatement)localObject).setLong(6, 0L);
/*  580: 580 */      ((PreparedStatement)localObject).setInt(7, paramInt1);
/*  581: 581 */      ((PreparedStatement)localObject).setLong(8, paramLong2);
/*  582:     */      
/*  583: 583 */      ((PreparedStatement)localObject).executeUpdate();
/*  584: 584 */      (
/*  585: 585 */        paramConnection = ((PreparedStatement)localObject).getGeneratedKeys()).next();
/*  586: 586 */      paramLong1 = paramConnection.getLong(1);
/*  587: 587 */      ((PreparedStatement)localObject).close();
/*  588:     */    }
/*  589: 589 */    paramLong1 = paramMap;long l = paramLong1; if ((paramConnection = localStatement).executeQuery("SELECT ID FROM SECTORS_ITEMS WHERE ID = " + l + ";").next()) { if (paramLong1.isEmpty()) { paramConnection.execute("DELETE FROM SECTORS_ITEMS WHERE ID = " + l + ";"); } else { (paramConnection = paramConnection.getConnection().prepareStatement("UPDATE SECTORS_ITEMS SET (ITEMS) = (CAST(? AS VARBINARY(18432))) WHERE ID = CAST(? AS BIGINT);")).setBytes(1, mx.a(paramLong1));paramConnection.setLong(2, l);paramConnection.execute();paramConnection.close(); } } else if (!paramLong1.isEmpty()) { (paramConnection = paramConnection.getConnection().prepareStatement("INSERT INTO SECTORS_ITEMS(ID, ITEMS) VALUES(CAST(? AS BIGINT),CAST(? AS VARBINARY(18432)));")).setLong(1, l);paramConnection.setBytes(2, mx.a(paramLong1));paramConnection.execute();paramConnection.close(); }
/*  590: 590 */    localStatement.close();
/*  591:     */  }
/*  592:     */  
/*  611:     */  public final void b()
/*  612:     */  {
/*  613:     */    Object localObject;
/*  614:     */    
/*  631: 631 */    if (!(localObject = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'SYSTEMS';").next()) {
/*  632: 632 */      f();
/*  633: 633 */      h();
/*  634: 634 */      g();
/*  635:     */      
/*  636:     */      java.io.File[] arrayOfFile;
/*  637: 637 */      int j = (arrayOfFile = new java.io.File("./server-database/").listFiles()).length;
/*  638: 638 */      a(arrayOfFile, (java.sql.Statement)localObject, null, j);
/*  639: 639 */      b(arrayOfFile, (java.sql.Statement)localObject, null, j);
/*  640:     */      
/*  641: 641 */      localObject = new java.io.File("./server-database/").listFiles(new ky());
/*  642:     */      
/*  648: 648 */      for (int i = 0; i < localObject.length; i++) {
/*  649: 649 */        System.err.println("[MIGRATION] CLEANING UP DEPRECATED FILE: " + localObject[i].getName());
/*  650: 650 */        localObject[i].delete();
/*  651:     */      }
/*  652:     */    }
/*  653:     */  }
/*  654:     */  
/*  655:     */  public final void c() {
/*  656: 656 */    java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  657: 657 */    this.jdField_a_of_type_JavaSqlConnection.getMetaData();
/*  658:     */    
/*  666: 666 */    java.sql.ResultSet localResultSet = null; if (!localStatement.executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'ENTITIES' AND COLUMN_NAME = 'GEN_ID'").next()) {
/*  667: 667 */      System.err.println("[SQL] Database migration needed: GEN_ID");
/*  668:     */      
/*  669: 669 */      localStatement.executeUpdate("ALTER TABLE ENTITIES ADD COLUMN GEN_ID INT");
/*  670:     */      
/*  671: 671 */      localResultSet = localStatement.executeQuery("SELECT UID FROM ENTITIES WHERE TYPE = 3 AND TOUCHED = FALSE;");
/*  672: 672 */      Random localRandom = new Random();
/*  673:     */      
/*  674: 674 */      while (localResultSet.next()) {
/*  675: 675 */        localStatement.executeUpdate("UPDATE ENTITIES SET GEN_ID = " + localRandom.nextInt(kF.values().length) + " WHERE TYPE = 3 AND UID = '" + localResultSet.getString(1) + "';");
/*  676:     */      }
/*  677:     */    }
/*  678:     */    
/*  686: 686 */    if (!localStatement.executeQuery("SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = 'ENTITIES' AND COLUMN_NAME = 'ID'").next()) {
/*  687: 687 */      System.err.println("[SQL] Database migration needed: ID");
/*  688:     */      
/*  689: 689 */      localStatement.executeUpdate("ALTER TABLE ENTITIES DROP PRIMARY KEY");
/*  690: 690 */      localStatement.executeUpdate("ALTER TABLE ENTITIES ADD COLUMN ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1)");
/*  691:     */      
/*  695: 695 */      localResultSet = localStatement.executeQuery("SELECT UID,TYPE FROM ENTITIES;");
/*  696: 696 */      long l = 0L;
/*  697:     */      
/*  698: 698 */      while (localResultSet.next()) {
/*  699: 699 */        localStatement.executeUpdate("UPDATE ENTITIES SET ID = " + l + " WHERE TYPE = " + localResultSet.getByte(2) + " AND UID = '" + localResultSet.getString(1) + "';");
/*  700: 700 */        l += 1L;
/*  701:     */      }
/*  702: 702 */      localStatement.executeUpdate("ALTER TABLE ENTITIES ALTER COLUMN ID RESTART WITH " + l + ";");
/*  703:     */      
/*  704: 704 */      localStatement.executeUpdate("ALTER TABLE ENTITIES ADD PRIMARY KEY(ID);");
/*  705:     */      
/*  706: 706 */      localStatement.execute("create unique index uidType on ENTITIES(UID,TYPE);");
/*  707:     */    }
/*  708:     */    
/*  716: 716 */    localStatement.close();
/*  717:     */  }
/*  718:     */  
/*  719:     */  private static void a(java.sql.Statement paramStatement, java.lang.String paramString1, q paramq1, int paramInt1, long paramLong, java.lang.String paramString2, java.lang.String paramString3, java.lang.String paramString4, boolean paramBoolean, int paramInt2, Vector3f paramVector3f, q paramq2, q paramq3, int paramInt3)
/*  720:     */  {
/*  721:     */    try {
/*  722: 722 */      if ((Float.isNaN(paramVector3f.x)) || (Float.isNaN(paramVector3f.y)) || (Float.isNaN(paramVector3f.z))) {
/*  723: 723 */        paramVector3f.set(400.0F, 400.0F, 400.0F);
/*  724: 724 */        System.err.println("Exception: POS NaN: ");
/*  725: 725 */        System.err.println("UID: " + paramString1);
/*  726: 726 */        System.err.println("SECPOS: " + paramq1);
/*  727: 727 */        System.err.println("TYPE: " + paramInt1);
/*  728: 728 */        System.err.println("SEED: " + paramLong);
/*  729: 729 */        System.err.println("lastModifier: " + paramString2);
/*  730: 730 */        System.err.println("spawner: " + paramString3);
/*  731: 731 */        System.err.println("realName: " + paramString4);
/*  732: 732 */        System.err.println("touched: " + paramBoolean);
/*  733: 733 */        System.err.println("faction: " + paramInt2);
/*  734: 734 */        System.err.println("pos: " + paramVector3f);
/*  735: 735 */        System.err.println("min: " + paramq2);
/*  736: 736 */        System.err.println("max: " + paramq3);
/*  737: 737 */        System.err.println("creatorId: " + paramInt3);
/*  738:     */      }
/*  739: 739 */      long l1 = System.currentTimeMillis();
/*  740: 740 */      long l2 = System.currentTimeMillis();
/*  741: 741 */      java.sql.ResultSet localResultSet = paramStatement.executeQuery("SELECT ID FROM ENTITIES WHERE UID = '" + paramString1 + "' AND TYPE = " + paramInt1 + ";");
/*  742:     */      
/*  744: 744 */      long l5 = System.currentTimeMillis() - l2;
/*  745: 745 */      StringBuilder localStringBuilder; long l4; long l3; if (localResultSet.next())
/*  746:     */      {
/*  763: 763 */        l2 = System.currentTimeMillis();
/*  764:     */        
/*  810: 810 */        (
/*  811: 811 */          localStringBuilder = new StringBuilder()).append("UPDATE ENTITIES SET (UID,X,Y,Z,TYPE,NAME,FACTION,CREATOR,LAST_MOD,SEED,TOUCHED,LOCAL_POS,DIM,GEN_ID,ID) = (");
/*  812: 812 */        localStringBuilder.append("'").append(paramString1).append("',");
/*  813: 813 */        localStringBuilder.append(paramq1.a).append(",");
/*  814: 814 */        localStringBuilder.append(paramq1.b).append(",");
/*  815: 815 */        localStringBuilder.append(paramq1.c).append(",");
/*  816: 816 */        localStringBuilder.append(paramInt1).append(",");
/*  817: 817 */        localStringBuilder.append("'").append(paramString4).append("',");
/*  818: 818 */        localStringBuilder.append(paramInt2).append(",");
/*  819: 819 */        localStringBuilder.append("'").append(paramString3).append("',");
/*  820: 820 */        localStringBuilder.append("'").append(paramString2).append("',");
/*  821: 821 */        localStringBuilder.append(paramLong).append(",");
/*  822: 822 */        localStringBuilder.append(paramBoolean).append(",");
/*  823: 823 */        localStringBuilder.append("ARRAY[").append(paramVector3f.x).append(",").append(paramVector3f.y).append(",").append(paramVector3f.z).append("],");
/*  824: 824 */        localStringBuilder.append("ARRAY[").append(paramq2.a).append(",").append(paramq2.b).append(",").append(paramq2.c).append(",").append(paramq3.a).append(",").append(paramq3.b).append(",").append(paramq3.c).append("],");
/*  825:     */        
/*  826: 826 */        localStringBuilder.append(paramInt3).append(",");
/*  827: 827 */        localStringBuilder.append("NULL");
/*  828: 828 */        localStringBuilder.append(") WHERE ID = " + localResultSet.getLong(1) + ";");
/*  829: 829 */        l4 = System.currentTimeMillis() - l2;
/*  830:     */        
/*  832: 832 */        l2 = System.currentTimeMillis();
/*  833:     */        
/*  834: 834 */        paramStatement.executeUpdate(localStringBuilder.toString());
/*  835: 835 */        l3 = System.currentTimeMillis() - l2;
/*  839:     */      }
/*  840:     */      else
/*  841:     */      {
/*  844: 844 */        l2 = System.currentTimeMillis();
/*  845: 845 */        (
/*  846: 846 */          localStringBuilder = new StringBuilder()).append("INSERT INTO ENTITIES VALUES(");
/*  847: 847 */        localStringBuilder.append("'").append(paramString1).append("',");
/*  848: 848 */        localStringBuilder.append(paramq1.a).append(",");
/*  849: 849 */        localStringBuilder.append(paramq1.b).append(",");
/*  850: 850 */        localStringBuilder.append(paramq1.c).append(",");
/*  851: 851 */        localStringBuilder.append(paramInt1).append(",");
/*  852: 852 */        localStringBuilder.append("'").append(paramString4).append("',");
/*  853: 853 */        localStringBuilder.append(paramInt2).append(",");
/*  854: 854 */        localStringBuilder.append("'").append(paramString3).append("',");
/*  855: 855 */        localStringBuilder.append("'").append(paramString2).append("',");
/*  856: 856 */        localStringBuilder.append(paramLong).append(",");
/*  857: 857 */        localStringBuilder.append(paramBoolean).append(",");
/*  858: 858 */        localStringBuilder.append("ARRAY[").append(paramVector3f.x).append(",").append(paramVector3f.y).append(",").append(paramVector3f.z).append("],");
/*  859: 859 */        localStringBuilder.append("ARRAY[").append(paramq2.a).append(",").append(paramq2.b).append(",").append(paramq2.c).append(",").append(paramq3.a).append(",").append(paramq3.b).append(",").append(paramq3.c).append("],");
/*  860:     */        
/*  861: 861 */        localStringBuilder.append(paramInt3).append(",");
/*  862: 862 */        localStringBuilder.append("NULL");
/*  863: 863 */        localStringBuilder.append(");");
/*  864: 864 */        l4 = System.currentTimeMillis() - l2;
/*  865:     */        
/*  866: 866 */        l2 = System.currentTimeMillis();
/*  867: 867 */        paramStatement.executeUpdate(localStringBuilder.toString());
/*  868: 868 */        l3 = System.currentTimeMillis() - l2;
/*  869:     */      }
/*  870:     */      
/*  872:     */      long l6;
/*  873:     */      
/*  874: 874 */      if ((l6 = System.currentTimeMillis() - l1) > 20L) {
/*  875: 875 */        System.err.println("[SQL] WARNING: ROW UPDATE TOOK " + l6 + " on " + paramString1 + ": query: " + l5 + "; stringBuild: " + l4 + "; insertOrUpdate: " + l3);
/*  876:     */      }
/*  877:     */      
/*  911: 911 */      return;
/*  912:     */    }
/*  913:     */    catch (SQLSyntaxErrorException localSQLSyntaxErrorException)
/*  914:     */    {
/*  915: 895 */      System.err.println("Exception: FAILED TO HANDLE SQL: ");
/*  916: 896 */      System.err.println("UID: " + paramString1);
/*  917: 897 */      System.err.println("SECPOS: " + paramq1);
/*  918: 898 */      System.err.println("TYPE: " + paramInt1);
/*  919: 899 */      System.err.println("SEED: " + paramLong);
/*  920: 900 */      System.err.println("lastModifier: " + paramString2);
/*  921: 901 */      System.err.println("spawner: " + paramString3);
/*  922: 902 */      System.err.println("realName: " + paramString4);
/*  923: 903 */      System.err.println("touched: " + paramBoolean);
/*  924: 904 */      System.err.println("faction: " + paramInt2);
/*  925: 905 */      System.err.println("pos: " + paramVector3f);
/*  926: 906 */      System.err.println("min: " + paramq2);
/*  927: 907 */      System.err.println("max: " + paramq3);
/*  928: 908 */      System.err.println("creatorId: " + paramInt3);
/*  929:     */      
/*  930: 910 */      throw localSQLSyntaxErrorException;
/*  931:     */    }
/*  932:     */  }
/*  933:     */  
/*  934:     */  public final void a(jd paramjd) { java.sql.Statement localStatement;
/*  935: 915 */    (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeUpdate("UPDATE ENTITIES SET TOUCHED = FALSE WHERE TYPE = 3 AND LAST_MOD = '';");
/*  936:     */    
/*  937:     */    java.sql.ResultSet localResultSet1;
/*  938: 918 */    (localResultSet1 = localStatement.executeQuery("SELECT COUNT(*) FROM ENTITIES WHERE TYPE = 3 AND LAST_MOD = '';")).next();
/*  939: 919 */    float f1 = localResultSet1.getInt(1);
/*  940:     */    
/*  941: 921 */    java.sql.ResultSet localResultSet2 = localStatement.executeQuery("SELECT UID, SEED FROM ENTITIES WHERE TYPE = 3 AND LAST_MOD = '';");
/*  942:     */    
/*  943: 923 */    Random localRandom = new Random();
/*  944: 924 */    java.io.File[] arrayOfFile1 = new java.io.File(vg.jdField_a_of_type_JavaLangString).listFiles();
/*  945: 925 */    java.io.File[] arrayOfFile2 = new java.io.File(vg.f).listFiles();
/*  946: 926 */    float f2 = 0.0F;
/*  947: 927 */    while (localResultSet2.next())
/*  948:     */    {
/*  949: 929 */      java.lang.String str1 = localResultSet2.getString(1).trim();
/*  950: 930 */      long l = localResultSet2.getLong(2);
/*  951: 931 */      java.lang.String str2 = "ENTITY_FLOATINGROCK_" + str1;
/*  952: 932 */      if (paramjd != null) {
/*  953: 933 */        paramjd.a((int)(f2 / f1 * 100.0F) + "% (" + str2 + ")");
/*  954:     */      }
/*  955: 935 */      for (int i = 0; i < arrayOfFile1.length; i++) {
/*  956: 936 */        if (arrayOfFile1[i].getName().startsWith(str2)) {
/*  957: 937 */          arrayOfFile1[i].delete();
/*  958: 938 */          System.err.println("REMOVED FILE: " + str2);
/*  959: 939 */          break;
/*  960:     */        }
/*  961:     */      }
/*  962:     */      
/*  964: 944 */      for (i = 0; i < arrayOfFile2.length; i++) {
/*  965: 945 */        if (arrayOfFile2[i].getName().startsWith(str2)) {
/*  966: 946 */          arrayOfFile2[i].delete();
/*  967: 947 */          System.err.println("REMOVED DATA FILE: " + str2);
/*  968:     */        }
/*  969:     */      }
/*  970:     */      
/*  972: 952 */      if ((f2 > 0.0F) && ((int)f2 % 200 == 0)) {
/*  973: 953 */        arrayOfFile1 = new java.io.File(vg.jdField_a_of_type_JavaLangString).listFiles();
/*  974: 954 */        arrayOfFile2 = new java.io.File(vg.f).listFiles();
/*  975:     */      }
/*  976: 956 */      if (l == 0L) {
/*  977: 957 */        localStatement.executeUpdate("UPDATE ENTITIES SET SEED = " + localRandom.nextLong() + " WHERE TYPE = 3 AND UID = '" + str1 + "';");
/*  978:     */      }
/*  979: 959 */      f2 += 1.0F;
/*  980:     */    }
/*  981:     */    
/*  983: 963 */    localStatement.close();
/*  984:     */  }
/*  985:     */  
/*  986:     */  public final void a(java.lang.String paramString1, q paramq1, int paramInt1, long paramLong, java.lang.String paramString2, java.lang.String paramString3, java.lang.String paramString4, int paramInt2, Vector3f paramVector3f, q paramq2, q paramq3, int paramInt3)
/*  987:     */  {
/*  988: 968 */    java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/*  989: 969 */    if ((!jdField_a_of_type_Boolean) && (paramString1.startsWith("ENTITY_"))) throw new AssertionError(paramString1);
/*  990: 970 */    a(localStatement, paramString1, paramq1, paramInt1, paramLong, paramString2, paramString3, paramString4, false, paramInt2, paramVector3f, paramq2, paramq3, paramInt3);
/*  991:     */    
/*  992: 972 */    localStatement.close();
/*  993:     */  }
/*  994:     */  
/* 1017:     */  public final void a(SegmentController paramSegmentController)
/* 1018:     */  {
/* 1019: 999 */    java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/* 1020:1000 */    if ((paramSegmentController == null) || (localStatement == null)) {
/* 1021:1001 */      throw new NullPointerException(paramSegmentController + ", " + localStatement);
/* 1022:     */    }
/* 1023:1003 */    SegmentController localSegmentController = paramSegmentController;paramSegmentController = localStatement;Object localObject = ((vg)localSegmentController.getState()).a().getSector(localSegmentController.getSectorId()); boolean bool; if ((localSegmentController instanceof km)) bool = ((km)localSegmentController).a(); else bool = true; if (localObject == null) { System.err.println("[SQL][WANRING] Sector null for " + localSegmentController + "; using transient sector: " + localSegmentController.transientSector);localObject = localSegmentController.transientSector; } else { localObject = ((mx)localObject).jdField_a_of_type_Q; } a(paramSegmentController, localSegmentController.getUniqueIdentifier().split("_", 3)[2], (q)localObject, (paramSegmentController instanceof kd) ? 5 : (paramSegmentController instanceof jA) ? 4 : (paramSegmentController instanceof jy) ? 3 : (paramSegmentController instanceof ki) ? 2 : ((paramSegmentController = localSegmentController) instanceof kf) ? 1 : 0, localSegmentController.getSeed(), localSegmentController.getLastModifier(), localSegmentController.getSpawner(), localSegmentController.getRealName(), bool, localSegmentController.getFactionId(), localSegmentController.getWorldTransform().origin, localSegmentController.getMinPos(), localSegmentController.getMaxPos(), localSegmentController.getCreatorId());
/* 1024:1004 */    localStatement.close();
/* 1025:     */  }
/* 1026:     */  
/* 1029:     */  public final void b(java.lang.String paramString)
/* 1030:     */  {
/* 1031:     */    java.sql.Statement localStatement;
/* 1032:     */    
/* 1034:1014 */    (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeUpdate("DELETE FROM ENTITIES WHERE UID = '" + paramString.split("_", 3)[2] + "';");
/* 1035:1015 */    localStatement.close();
/* 1036:     */  }
/* 1037:     */  
/* 1038:     */  public final void b(SegmentController paramSegmentController) { java.sql.Statement localStatement;
/* 1039:1019 */    (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeUpdate("DELETE FROM ENTITIES WHERE UID = '" + paramSegmentController.getUniqueIdentifier().split("_", 3)[2] + "';");
/* 1040:1020 */    localStatement.close();
/* 1041:     */  }
/* 1042:     */  
/* 1120:     */  public final void a(java.io.File paramFile)
/* 1121:     */  {
/* 1122:1102 */    java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/* 1123:1103 */    a(paramFile, localStatement);
/* 1124:1104 */    localStatement.close();
/* 1125:     */  }
/* 1126:     */  
/* 1127:     */  private static void a(java.io.File paramFile, java.sql.Statement paramStatement) {
/* 1128:1108 */    if ((paramFile.getName().startsWith("ENTITY_SHOP")) || (paramFile.getName().startsWith("ENTITY_SPACESTATION")) || (paramFile.getName().startsWith("ENTITY_FLOATINGROCK")) || (paramFile.getName().startsWith("ENTITY_PLANET")) || (paramFile.getName().startsWith("ENTITY_SHIP")))
/* 1129:     */    {
/* 1132:1112 */      Ah localAh = Ah.a(localObject1 = new DataInputStream(new FileInputStream(paramFile)), true);
/* 1133:1113 */      ((DataInputStream)localObject1).close();
/* 1134:     */      
/* 1136:1116 */      if (paramFile.getName().startsWith("ENTITY_SHOP")) {
/* 1137:1117 */        localObject1 = ((Ah[])localAh.a())[0];
/* 1138:1118 */      } else if (paramFile.getName().startsWith("ENTITY_SPACESTATION")) {
/* 1139:1119 */        localObject1 = ((Ah[])localAh.a())[1];
/* 1140:1120 */      } else if (paramFile.getName().startsWith("ENTITY_PLANET")) {
/* 1141:1121 */        localObject1 = ((Ah[])localAh.a())[1];
/* 1142:     */      } else {
/* 1143:1123 */        localObject1 = localAh;
/* 1144:     */      }
/* 1145:1125 */      int i = 0;
/* 1146:1126 */      if (paramFile.getName().startsWith("ENTITY_SHOP")) {
/* 1147:1127 */        i = 1;
/* 1148:1128 */      } else if (paramFile.getName().startsWith("ENTITY_SPACESTATION")) {
/* 1149:1129 */        i = 2;
/* 1150:1130 */      } else if (paramFile.getName().startsWith("ENTITY_FLOATINGROCK")) {
/* 1151:1131 */        i = 3;
/* 1152:1132 */      } else if (paramFile.getName().startsWith("ENTITY_PLANET"))
/* 1153:     */      {
/* 1154:1134 */        i = 4;
/* 1155:1135 */      } else if (paramFile.getName().startsWith("ENTITY_SHIP")) {
/* 1156:1136 */        i = 5;
/* 1157:     */      }
/* 1158:1138 */      System.err.println("PARSING: " + paramFile.getName() + " -> " + i);
/* 1159:     */      
/* 1160:1140 */      Object localObject1 = (Ah[])(paramFile = (Ah[])((Ah)localObject1).a())[6].a();
/* 1161:     */      
/* 1164:1144 */      java.lang.String str1 = (java.lang.String)paramFile[0].a();
/* 1165:1145 */      q localq = (q)localObject1[3].a();
/* 1166:     */      long l;
/* 1167:1147 */      if ((paramFile.length > 11) && (paramFile[11].a() == Aj.e)) {
/* 1168:1148 */        l = ((Long)paramFile[11].a()).longValue();
/* 1169:     */      } else {
/* 1170:1150 */        l = 0L;
/* 1171:     */      }
/* 1172:     */      java.lang.String str2;
/* 1173:1153 */      if ((paramFile.length > 10) && (paramFile[10].a() == Aj.i)) {
/* 1174:1154 */        str2 = (java.lang.String)paramFile[10].a();
/* 1175:     */      } else {
/* 1176:1156 */        str2 = "";
/* 1177:     */      }
/* 1178:1158 */      if (str2 == null) {
/* 1179:1159 */        str2 = "";
/* 1180:     */      }
/* 1181:1161 */      if (str2.startsWith("ENTITY_PLAYERSTATE_")) {
/* 1182:1162 */        str2 = str2.substring(19);
/* 1183:     */      }
/* 1184:     */      
/* 1185:     */      java.lang.String str3;
/* 1186:1166 */      if ((paramFile.length > 9) && (paramFile[9].a() == Aj.i)) {
/* 1187:1167 */        str3 = (java.lang.String)paramFile[9].a();
/* 1188:     */      } else {
/* 1189:1169 */        str3 = "";
/* 1190:     */      }
/* 1191:1171 */      if (str3 == null) {
/* 1192:1172 */        str3 = "";
/* 1193:     */      }
/* 1194:1174 */      if (str3.startsWith("ENTITY_PLAYERSTATE_")) {
/* 1195:1175 */        str3 = str3.substring(19);
/* 1196:     */      }
/* 1197:1177 */      boolean bool = true;
/* 1198:1178 */      if ((paramFile.length > 12) && (paramFile[12].a() != null) && (paramFile[12].a() == Aj.b)) {
/* 1199:1179 */        bool = ((Byte)paramFile[12].a()).byteValue() == 1;
/* 1200:     */      }
/* 1201:     */      java.lang.String str4;
/* 1202:1182 */      if (paramFile[5].a() != null) {
/* 1203:1183 */        str4 = (java.lang.String)paramFile[5].a();
/* 1204:     */      } else {
/* 1205:1185 */        str4 = "undef";
/* 1206:     */      }
/* 1207:1187 */      int j = 0;
/* 1208:     */      
/* 1209:1189 */      if ((localObject1[4].a() != null) && (localObject1[4].a() == Aj.d)) {
/* 1210:1190 */        j = ((Integer)localObject1[4].a()).intValue();
/* 1211:1191 */        System.err.println("PARSED FACTION " + j);
/* 1212:     */      } else {
/* 1213:1193 */        System.err.println("COULD NOT PARSE FACTION " + localObject1[4].a().name());
/* 1214:     */      }
/* 1215:     */      
/* 1217:1197 */      Object localObject2 = new float[(localObject1 = (Ah[])localObject1[1].a()).length];
/* 1218:1198 */      for (int k = 0; k < localObject1.length; k++) {
/* 1219:1199 */        localObject2[k] = ((Float)localObject1[k].a()).floatValue();
/* 1220:     */      }
/* 1221:     */      
/* 1222:     */      Transform localTransform;
/* 1223:1203 */      (localTransform = new Transform()).setFromOpenGLMatrix((float[])localObject2);
/* 1224:     */      
/* 1225:1205 */      localObject1 = (q)paramFile[1].a();
/* 1226:1206 */      localObject2 = (q)paramFile[2].a();
/* 1227:1207 */      paramFile = ((Integer)paramFile[8].a()).intValue();
/* 1228:1208 */      a(paramStatement, str1.split("_", 3)[2], localq, i, l, str2, str3, str4, bool, j, localTransform.origin, (q)localObject1, (q)localObject2, paramFile);
/* 1229:     */    }
/* 1230:     */  }
/* 1231:     */  
/* 1235:     */  private static void a(java.io.File[] paramArrayOfFile, java.sql.Statement paramStatement, jd paramjd, int paramInt)
/* 1236:     */  {
/* 1237:1217 */    int i = 0;
/* 1238:1218 */    paramArrayOfFile = paramArrayOfFile;int j = paramArrayOfFile.length; for (int k = 0; k < j; k++) { java.io.File localFile;
/* 1239:1219 */      java.sql.Statement localStatement = paramStatement; Object localObject1; if ((localObject1 = localFile = paramArrayOfFile[k]).getName().startsWith("VOIDSYSTEM")) { Object localObject2 = Ah.a(localObject1 = new DataInputStream(new FileInputStream((java.io.File)localObject1)), true);((DataInputStream)localObject1).close(); Ah[] arrayOfAh; localObject1 = (q)(arrayOfAh = (Ah[])((Ah)localObject2).a())[0].a();localObject2 = (byte[])arrayOfAh[1].a(); long l; if (arrayOfAh[2].a() == Aj.e) l = ((Long)arrayOfAh[2].a()).longValue(); else l = 0L; a(localStatement.getConnection(), 0L, (q)localObject1, 0, (byte[])localObject2, l, false);System.err.println("[SQL] INSERTED SYSTEM " + localObject1); }
/* 1240:1220 */      if (paramjd != null) {
/* 1241:1221 */        paramjd.a((int)(i / paramInt * 100.0F) + "%  (" + localFile.getName() + ")");
/* 1242:     */      }
/* 1243:1223 */      i++;
/* 1244:     */    }
/* 1245:     */  }
/* 1246:     */  
/* 1247:1227 */  private static void b(java.io.File[] paramArrayOfFile, java.sql.Statement paramStatement, jd paramjd, int paramInt) { int i = 0;
/* 1248:1228 */    paramArrayOfFile = paramArrayOfFile;int j = paramArrayOfFile.length; for (int k = 0; k < j; k++) { java.io.File localFile;
/* 1249:1229 */      java.sql.Statement localStatement = paramStatement; Object localObject; if ((localObject = localFile = paramArrayOfFile[k]).getName().startsWith("SECTOR")) { Ah localAh = Ah.a(localObject = new DataInputStream(new FileInputStream((java.io.File)localObject)), true);((DataInputStream)localObject).close();HashMap localHashMap = new HashMap();localObject = (q)(arrayOfAh = (Ah[])localAh.a())[0].a(); if ((!jdField_a_of_type_Boolean) && (localObject == null)) throw new AssertionError(); int m = ((Integer)arrayOfAh[2].a()).intValue();Ah[] arrayOfAh = (Ah[])arrayOfAh[3].a(); for (int n = 0; n < arrayOfAh.length - 1; n++) { me localme; (localme = new me()).fromTagStructure(arrayOfAh[n]);localme.b(vg.e++); if (localme.a() != 0) localHashMap.put(Integer.valueOf(localme.b()), localme); } a(localStatement.getConnection(), -1L, (q)localObject, m, localHashMap, 0, -1L);System.err.println("[SQL] INSERTED SECTOR " + localObject); }
/* 1250:1230 */      if (paramjd != null) {
/* 1251:1231 */        paramjd.a((int)(i / paramInt * 100.0F) + "%  (" + localFile.getName() + ")");
/* 1252:     */      }
/* 1253:1233 */      i++;
/* 1254:     */    }
/* 1255:     */  }
/* 1256:     */  
/* 1257:1237 */  private static void c(java.io.File[] paramArrayOfFile, java.sql.Statement paramStatement, jd paramjd, int paramInt) { int i = 0;
/* 1258:1238 */    paramArrayOfFile = paramArrayOfFile;int j = paramArrayOfFile.length; for (int k = 0; k < j; k++) { java.io.File localFile;
/* 1259:1239 */      a(localFile = paramArrayOfFile[k], paramStatement);
/* 1260:1240 */      if (paramjd != null) {
/* 1261:1241 */        paramjd.a((int)(i / paramInt * 100.0F) + "%  (" + localFile.getName() + ")");
/* 1262:     */      }
/* 1263:1243 */      i++;
/* 1264:     */    }
/* 1265:     */  }
/* 1266:     */  
/* 1267:1247 */  public final void b(jd paramjd) { java.io.File[] arrayOfFile = new java.io.File("./server-database/").listFiles();
/* 1268:1248 */    java.sql.Statement localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement();
/* 1269:1249 */    int j = arrayOfFile.length;
/* 1270:     */    
/* 1271:1251 */    a(arrayOfFile, localStatement, paramjd, j);
/* 1272:1252 */    b(arrayOfFile, localStatement, paramjd, j);
/* 1273:1253 */    c(arrayOfFile, localStatement, paramjd, j);
/* 1274:     */    
/* 1275:1255 */    paramjd = new java.io.File("./server-database/").listFiles(new kz());
/* 1276:     */    
/* 1282:1262 */    for (int i = 0; i < paramjd.length; i++) {
/* 1283:1263 */      System.err.println("[MIGRATION] CLEANING UP DEPRECATED FILE: " + paramjd[i].getName());
/* 1284:1264 */      paramjd[i].delete();
/* 1285:     */    }
/* 1286:1266 */    localStatement.close();
/* 1287:     */  }
/* 1288:     */  
/* 1291:     */  public static void main(java.lang.String[] paramArrayOfString)
/* 1292:     */  {
/* 1293:1273 */    (paramArrayOfString = new DatabaseIndex()).a();
/* 1294:1274 */    paramArrayOfString.b(null);
/* 1295:     */    
/* 1296:1276 */    paramArrayOfString.a("SELECT * FROM SECTORS");
/* 1297:1277 */    paramArrayOfString.a("SELECT * FROM SYSTEMS");
/* 1298:     */  }
/* 1299:     */  
/* 1301:     */  public final void d()
/* 1302:     */  {
/* 1303:     */    java.sql.Statement localStatement;
/* 1304:     */    
/* 1305:1285 */    (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).execute("SHUTDOWN COMPACT;");
/* 1306:1286 */    localStatement.close();
/* 1307:1287 */    this.jdField_a_of_type_JavaSqlConnection.close();
/* 1308:     */  }
/* 1309:     */  
/* 1310:     */  public static void e() {
/* 1311:     */    try {
/* 1312:1292 */      Class.forName("org.hsqldb.jdbc.JDBCDriver"); return;
/* 1313:     */    } catch (Exception localException) {
/* 1314:1294 */      System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
/* 1315:1295 */      localException.printStackTrace();
/* 1316:     */    }
/* 1317:     */  }
/* 1318:     */  
/* 1325:     */  public final long a(mI parammI, boolean paramBoolean)
/* 1326:     */  {
/* 1327:1307 */    return a(this.jdField_a_of_type_JavaSqlConnection, parammI.b, parammI.jdField_a_of_type_Q, parammI.jdField_a_of_type_MD.ordinal(), parammI.jdField_a_of_type_ArrayOfByte, parammI.jdField_a_of_type_Long, paramBoolean);
/* 1328:     */  }
/* 1329:     */  
/* 1334:     */  public final boolean a(q paramq, mJ parammJ)
/* 1335:     */  {
/* 1336:     */    try
/* 1337:     */    {
/* 1338:     */      java.sql.Statement localStatement;
/* 1339:     */      
/* 1343:     */      java.sql.ResultSet localResultSet1;
/* 1344:     */      
/* 1348:1328 */      if ((localResultSet1 = (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT ID FROM SYSTEMS WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";")).next()) {
/* 1349:1329 */        int i = localResultSet1.getInt(1);
/* 1350:     */        java.sql.ResultSet localResultSet2;
/* 1351:1331 */        (localResultSet2 = localStatement.executeQuery("SELECT TYPE, STARTTIME, NAME, INFOS FROM SYSTEMS WHERE ID = " + i + ";")).next();
/* 1352:     */        
/* 1353:1333 */        long l = i;localObject = null;parammJ.b = l;
/* 1354:1334 */        parammJ.jdField_a_of_type_Q.b(paramq);
/* 1355:1335 */        parammJ.jdField_a_of_type_Long = localResultSet2.getLong(2);
/* 1356:1336 */        localResultSet2.getString(3);
/* 1357:1337 */        parammJ.a(localResultSet2.getBytes(4));
/* 1358:1338 */        localStatement.close();
/* 1359:1339 */        return true;
/* 1360:     */      }
/* 1361:1341 */      localStatement.close();
/* 1362:1342 */    } catch (SQLException localSQLException) { Object localObject = null;
/* 1363:     */      
/* 1364:1344 */      localSQLException.printStackTrace();
/* 1365:     */    }
/* 1366:     */    
/* 1368:1346 */    return false;
/* 1369:     */  }
/* 1370:     */  
/* 1371:1349 */  public final HashSet a(q paramq) { java.sql.Statement localStatement = null;
/* 1372:     */    
/* 1373:     */    try
/* 1374:     */    {
/* 1375:1353 */      paramq = (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT UID, TYPE FROM ENTITIES WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";");
/* 1376:1354 */      HashSet localHashSet = new HashSet();
/* 1377:     */      Object localObject;
/* 1378:1356 */      while (paramq.next())
/* 1379:     */      {
/* 1381:1359 */        localObject = kw.a(paramq.getString(1).trim(), paramq.getInt(2));
/* 1382:     */        
/* 1383:1361 */        localHashSet.add(localObject);
/* 1384:     */      }
/* 1385:     */      
/* 1386:1364 */      return localHashSet;
/* 1387:     */    }
/* 1388:     */    catch (SQLException localSQLException) {}finally
/* 1389:     */    {
/* 1390:     */      try {
/* 1391:1369 */        localStatement.close();
/* 1392:1370 */      } catch (Exception localException3) { 
/* 1393:     */        
/* 1394:1372 */          localException3;
/* 1395:     */      }
/* 1396:     */    }
/* 1397:     */    
/* 1398:1374 */    return new HashSet();
/* 1399:     */  }
/* 1400:     */  
/* 1406:     */  public final void a(mx parammx)
/* 1407:     */  {
/* 1408:1384 */    a(this.jdField_a_of_type_JavaSqlConnection, parammx.b(), parammx.jdField_a_of_type_Q, parammx.b(), parammx.a(), parammx.a().ordinal(), -1L);
/* 1409:     */  }
/* 1410:     */  
/* 1411:1387 */  public final void a(q paramq, Map paramMap, int paramInt) { a(this.jdField_a_of_type_JavaSqlConnection, -1L, paramq, 0, paramMap, paramInt, -1L); }
/* 1412:     */  
/* 1413:     */  public final boolean a(q paramq, mx parammx) {
/* 1414:1390 */    java.sql.Statement localStatement = null;
/* 1415:     */    
/* 1416:     */    try
/* 1417:     */    {
/* 1418:     */      java.sql.ResultSet localResultSet;
/* 1419:     */      
/* 1420:1396 */      if ((localResultSet = (localStatement = this.jdField_a_of_type_JavaSqlConnection.createStatement()).executeQuery("SELECT ID, TYPE, NAME, ITEMS, PROTECTION FROM SECTORS WHERE X = " + paramq.a + " AND Y = " + paramq.b + " AND Z = " + paramq.c + ";")).next()) {
/* 1421:1397 */        parammx.a(localResultSet.getInt(1));
/* 1422:1398 */        parammx.jdField_a_of_type_Q = new q(paramq);
/* 1423:1399 */        localResultSet.getString(3);
/* 1424:     */        
/* 1425:1401 */        mx localmx = parammx;paramq = null; if ((paramq = localStatement.executeQuery("SELECT ITEMS FROM SECTORS_ITEMS WHERE ID = " + localmx.b() + ";")).next()) { localmx.a(paramq.getBytes(1));
/* 1426:     */        }
/* 1427:1403 */        parammx.b(localResultSet.getInt(5));
/* 1428:     */        
/* 1439:1415 */        return true;
/* 1440:     */      }
/* 1441:     */      
/* 1442:1418 */      return false;
/* 1443:     */    }
/* 1444:     */    catch (SQLException localSQLException)
/* 1445:     */    {
/* 1446:1408 */      
/* 1447:     */      
/* 1454:1416 */        localSQLException.printStackTrace();
/* 1455:     */    }
/* 1456:     */    finally {
/* 1457:     */      try {
/* 1458:1412 */        localStatement.close();
/* 1459:1413 */      } catch (Exception localException4) { 
/* 1460:     */        
/* 1461:1415 */          localException4;
/* 1462:     */      }
/* 1463:     */    }
/* 1464:     */  }
/* 1465:     */  
/* 1466:     */  /* Error */
/* 1467:     */  public final void a(q paramq)
/* 1468:     */  {
/* 1469:     */    // Byte code:
/* 1470:     */    //   0: aconst_null
/* 1471:     */    //   1: astore_2
/* 1472:     */    //   2: aload_0
/* 1473:     */    //   3: getfield 306	org/schema/game/common/controller/database/DatabaseIndex:jdField_a_of_type_JavaSqlConnection	Ljava/sql/Connection;
/* 1474:     */    //   6: invokeinterface 453 1 0
/* 1475:     */    //   11: dup
/* 1476:     */    //   12: astore_2
/* 1477:     */    //   13: new 234	java/lang/StringBuilder
/* 1478:     */    //   16: dup
/* 1479:     */    //   17: ldc 76
/* 1480:     */    //   19: invokespecial 357	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/* 1481:     */    //   22: aload_1
/* 1482:     */    //   23: getfield 310	q:a	I
/* 1483:     */    //   26: invokevirtual 360	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/* 1484:     */    //   29: ldc 16
/* 1485:     */    //   31: invokevirtual 363	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 1486:     */    //   34: aload_1
/* 1487:     */    //   35: getfield 311	q:b	I
/* 1488:     */    //   38: invokevirtual 360	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/* 1489:     */    //   41: ldc 19
/* 1490:     */    //   43: invokevirtual 363	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 1491:     */    //   46: aload_1
/* 1492:     */    //   47: getfield 312	q:c	I
/* 1493:     */    //   50: invokevirtual 360	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/* 1494:     */    //   53: ldc 52
/* 1495:     */    //   55: invokevirtual 363	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 1496:     */    //   58: invokevirtual 365	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* 1497:     */    //   61: invokeinterface 480 2 0
/* 1498:     */    //   66: pop
/* 1499:     */    //   67: aload_2
/* 1500:     */    //   68: invokeinterface 477 1 0
/* 1501:     */    //   73: return
/* 1502:     */    //   74: aconst_null
/* 1503:     */    //   75: astore_1
/* 1504:     */    //   76: invokevirtual 340	java/lang/Exception:printStackTrace	()V
/* 1505:     */    //   79: return
/* 1506:     */    //   80: aconst_null
/* 1507:     */    //   81: astore_1
/* 1508:     */    //   82: invokevirtual 368	java/sql/SQLException:printStackTrace	()V
/* 1509:     */    //   85: aload_2
/* 1510:     */    //   86: invokeinterface 477 1 0
/* 1511:     */    //   91: return
/* 1512:     */    //   92: invokevirtual 340	java/lang/Exception:printStackTrace	()V
/* 1513:     */    //   95: return
/* 1514:     */    //   96: astore_1
/* 1515:     */    //   97: aload_2
/* 1516:     */    //   98: invokeinterface 477 1 0
/* 1517:     */    //   103: goto +6 -> 109
/* 1518:     */    //   106: invokevirtual 340	java/lang/Exception:printStackTrace	()V
/* 1519:     */    //   109: aload_1
/* 1520:     */    //   110: athrow
/* 1521:     */    // Line number table:
/* 1522:     */    //   Java source line #1421	-> byte code offset #0
/* 1523:     */    //   Java source line #1423	-> byte code offset #2
/* 1524:     */    //   Java source line #1426	-> byte code offset #12
/* 1525:     */    //   Java source line #1434	-> byte code offset #67
/* 1526:     */    //   Java source line #1437	-> byte code offset #73
/* 1527:     */    //   Java source line #1435	-> byte code offset #74
/* 1528:     */    //   Java source line #1437	-> byte code offset #79
/* 1529:     */    //   Java source line #1430	-> byte code offset #80
/* 1530:     */    //   Java source line #1434	-> byte code offset #85
/* 1531:     */    //   Java source line #1437	-> byte code offset #91
/* 1532:     */    //   Java source line #1435	-> byte code offset #92
/* 1533:     */    //   Java source line #1438	-> byte code offset #95
/* 1534:     */    //   Java source line #1433	-> byte code offset #96
/* 1535:     */    //   Java source line #1434	-> byte code offset #97
/* 1536:     */    //   Java source line #1437	-> byte code offset #103
/* 1537:     */    //   Java source line #1435	-> byte code offset #106
/* 1538:     */    //   Java source line #1437	-> byte code offset #109
/* 1539:     */    // Local variable table:
/* 1540:     */    //   start	length	slot	name	signature
/* 1541:     */    //   0	111	0	this	DatabaseIndex
/* 1542:     */    //   0	111	1	paramq	q
/* 1543:     */    //   1	97	2	localStatement	java.sql.Statement
/* 1544:     */    //   74	1	3	localException1	Exception
/* 1545:     */    //   80	1	4	localSQLException	SQLException
/* 1546:     */    //   92	1	5	localException2	Exception
/* 1547:     */    //   106	1	6	localException3	Exception
/* 1548:     */    // Exception table:
/* 1549:     */    //   from	to	target	type
/* 1550:     */    //   67	73	74	java/lang/Exception
/* 1551:     */    //   2	67	80	java/sql/SQLException
/* 1552:     */    //   85	91	92	java/lang/Exception
/* 1553:     */    //   2	67	96	finally
/* 1554:     */    //   80	85	96	finally
/* 1555:     */    //   97	103	106	java/lang/Exception
/* 1556:     */  }
/* 1557:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.database.DatabaseIndex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */