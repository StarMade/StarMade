package org.hsqldb.util;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import org.hsqldb.lib.RCData;
import org.hsqldb.lib.java.JavaSystem;

public class DatabaseManager
  extends Applet
  implements ActionListener, WindowListener, KeyListener
{
  static final String field_1661 = System.getProperty("line.separator");
  static final int iMaxRecent = 24;
  private static boolean TT_AVAILABLE = false;
  private static final String HELP_TEXT = "See the forums, mailing lists, and HSQLDB User Guide\nat http://hsqldb.org.\n\nPlease paste the following version identifier with any\nproblem reports or help requests:  $Revision: 4818 $" + (TT_AVAILABLE ? "" : "\n\nTransferTool classes are not in CLASSPATH.\nTo enable the Tools menu, add 'transfer.jar' to your class path.");
  private static final String ABOUT_TEXT = "$Revision: 4818 $ of DatabaseManager\n\nCopyright (c) 1995-2000, The Hypersonic SQL Group.\nCopyright (c) 2001-2011, The HSQL Development Group.\nhttp://hsqldb.org  (User Guide available at this site).\n\n\nYou may use and redistribute according to the HSQLDB\nlicense documented in the source code and at the web\nsite above." + (TT_AVAILABLE ? "\n\nTransferTool options are available." : "");
  Connection cConn;
  DatabaseMetaData dMeta;
  Statement sStatement;
  Menu mRecent;
  String[] sRecent;
  int iRecent;
  TextArea txtCommand;
  Button butExecute;
  Button butClear;
  Tree tTree;
  Panel pResult;
  long lTime;
  int iResult;
  Grid gResult;
  TextArea txtResult;
  boolean bHelp;
  Frame fMain;
  Image imgEmpty;
  static boolean bMustExit;
  String ifHuge = "";
  static String defDriver = "org.hsqldb.jdbcDriver";
  static String defURL = "jdbc:hsqldb:mem:.";
  static String defUser = "SA";
  static String defPassword = "";
  static String defScript;
  static String defDirectory;
  
  public void connect(Connection paramConnection)
  {
    if (paramConnection == null) {
      return;
    }
    if (this.cConn != null) {
      try
      {
        this.cConn.close();
      }
      catch (SQLException localSQLException1) {}
    }
    this.cConn = paramConnection;
    try
    {
      this.dMeta = this.cConn.getMetaData();
      this.sStatement = this.cConn.createStatement();
      refreshTree();
    }
    catch (SQLException localSQLException2)
    {
      localSQLException2.printStackTrace();
    }
  }
  
  public void init()
  {
    DatabaseManager localDatabaseManager = new DatabaseManager();
    localDatabaseManager.main();
    try
    {
      localDatabaseManager.connect(ConnectionDialog.createConnection(defDriver, defURL, defUser, defPassword));
      localDatabaseManager.insertTestData();
      localDatabaseManager.refreshTree();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void threadedDBM()
  {
    System.getProperties().put("sun.java2d.noddraw", "true");
    Object localObject1 = null;
    Object localObject2 = null;
    int i = 0;
    int j = 0;
    bMustExit = false;
    DatabaseManager localDatabaseManager = new DatabaseManager();
    localDatabaseManager.main();
    Connection localConnection = null;
    try
    {
      localConnection = ConnectionDialog.createConnection(localDatabaseManager.fMain, "Connect");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    if (localConnection == null) {
      return;
    }
    localDatabaseManager.connect(localConnection);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    System.getProperties().put("sun.java2d.noddraw", "true");
    String str3 = null;
    String str4 = null;
    int i = 0;
    int j = 0;
    bMustExit = true;
    for (int k = 0; k < paramArrayOfString.length; k++)
    {
      String str1 = paramArrayOfString[k];
      String str2 = paramArrayOfString[k].toLowerCase();
      if (str2.startsWith("--")) {
        str2 = str2.substring(1);
      }
      if ((!str2.equals("-noexit")) && (!str2.equals("-help")) && (k == paramArrayOfString.length - 1)) {
        throw new IllegalArgumentException("No value for argument " + str1);
      }
      k++;
      if (str2.equals("-driver"))
      {
        defDriver = paramArrayOfString[k];
        i = 1;
      }
      else if (str2.equals("-url"))
      {
        defURL = paramArrayOfString[k];
        i = 1;
      }
      else if (str2.equals("-user"))
      {
        defUser = paramArrayOfString[k];
        i = 1;
      }
      else if (str2.equals("-password"))
      {
        defPassword = paramArrayOfString[k];
        i = 1;
      }
      else if (str2.equals("-urlid"))
      {
        str3 = paramArrayOfString[k];
        j = 1;
      }
      else if (str2.equals("-rcfile"))
      {
        str4 = paramArrayOfString[k];
        j = 1;
      }
      else if (str2.equals("-dir"))
      {
        defDirectory = paramArrayOfString[k];
      }
      else if (str2.equals("-script"))
      {
        defScript = paramArrayOfString[k];
      }
      else if (str2.equals("-noexit"))
      {
        bMustExit = false;
        k--;
      }
      else
      {
        if (str2.equals("-help"))
        {
          showUsage();
          return;
        }
        throw new IllegalArgumentException("invalid argrument " + str1 + " try:  java... " + DatabaseManagerSwing.class.getName() + " --help");
      }
    }
    DatabaseManager localDatabaseManager = new DatabaseManager();
    localDatabaseManager.main();
    Connection localConnection = null;
    try
    {
      if ((i != 0) && (j != 0)) {
        throw new IllegalArgumentException("You may not specify both (urlid) AND (url/user/password).");
      }
      if (i != 0)
      {
        localConnection = ConnectionDialog.createConnection(defDriver, defURL, defUser, defPassword);
      }
      else if (j != 0)
      {
        if (str3 == null) {
          throw new IllegalArgumentException("You must specify an 'urlid' to use an RC file");
        }
        i = 1;
        if (str4 == null) {
          str4 = System.getProperty("user.home") + "/dbmanager.rc";
        }
        localConnection = new RCData(new File(str4), str3).getConnection(null, System.getProperty("javax.net.ssl.trustStore"));
      }
      else
      {
        localConnection = ConnectionDialog.createConnection(localDatabaseManager.fMain, "Connect");
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    if (localConnection == null) {
      return;
    }
    localDatabaseManager.connect(localConnection);
  }
  
  private static void showUsage()
  {
    System.out.println("Usage: java DatabaseManager [--options]\nwhere options include:\n    --help                show this message\n    --driver <classname>  jdbc driver class\n    --url <name>          jdbc url\n    --user <name>         username used for connection\n    --password <password> password for this user\n    --urlid <urlid>       use url/user/password/driver in rc file\n    --rcfile <file>       (defaults to 'dbmanager.rc' in home dir)\n    --dir <path>          default directory\n    --script <file>       reads from script file\n    --noexit              do not call system.exit()");
  }
  
  void insertTestData()
  {
    try
    {
      DatabaseManagerCommon.createTestTables(this.sStatement);
      refreshTree();
      this.txtCommand.setText(DatabaseManagerCommon.createTestData(this.sStatement));
      refreshTree();
      for (int i = 0; i < DatabaseManagerCommon.testDataSql.length; i++) {
        addToRecent(DatabaseManagerCommon.testDataSql[i]);
      }
      execute();
    }
    catch (SQLException localSQLException)
    {
      localSQLException.printStackTrace();
    }
  }
  
  public void main()
  {
    this.fMain = new Frame("HSQL Database Manager");
    this.imgEmpty = createImage(new MemoryImageSource(2, 2, new int[16], 2, 2));
    this.fMain.setIconImage(this.imgEmpty);
    this.fMain.addWindowListener(this);
    MenuBar localMenuBar = new MenuBar();
    String[] arrayOfString1 = { "-Connect...", "--", "-Open Script...", "-Save Script...", "-Save Result...", "-Save Result csv...", "--", "-Exit" };
    addMenu(localMenuBar, "File", arrayOfString1);
    String[] arrayOfString2 = { "RRefresh Tree", "--", "GResults in Grid", "TResults in Text", "--", "1Shrink Tree", "2Enlarge Tree", "3Shrink Command", "4Enlarge Command" };
    addMenu(localMenuBar, "View", arrayOfString2);
    String[] arrayOfString3 = { "SSELECT", "IINSERT", "UUPDATE", "DDELETE", "--", "-CREATE TABLE", "-DROP TABLE", "-CREATE INDEX", "-DROP INDEX", "--", "-CHECKPOINT", "-SCRIPT", "-SET", "-SHUTDOWN", "--", "-Test Script" };
    addMenu(localMenuBar, "Command", arrayOfString3);
    Menu localMenu1 = new Menu("Recent");
    this.mRecent = new Menu("Recent");
    localMenuBar.add(this.mRecent);
    String[] arrayOfString4 = { "-AutoCommit on", "-AutoCommit off", "OCommit", "LRollback", "--", "-Disable MaxRows", "-Set MaxRows to 100", "--", "-Logging on", "-Logging off", "--", "-Insert test data" };
    addMenu(localMenuBar, "Options", arrayOfString4);
    String[] arrayOfString5 = { "-Dump", "-Restore", "-Transfer" };
    addMenu(localMenuBar, "Tools", arrayOfString5);
    Menu localMenu2 = new Menu("Help");
    MenuItem localMenuItem1 = new MenuItem("About");
    localMenuItem1.setShortcut(new MenuShortcut(65));
    localMenuItem1.addActionListener(this);
    localMenu2.add(localMenuItem1);
    MenuItem localMenuItem2 = new MenuItem("Help");
    localMenuItem2.setShortcut(new MenuShortcut(72));
    localMenuItem2.addActionListener(this);
    localMenu2.add(localMenuItem2);
    this.fMain.setMenuBar(localMenuBar);
    this.fMain.setSize(640, 480);
    this.fMain.add("Center", this);
    initGUI();
    this.sRecent = new String[24];
    Dimension localDimension1 = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension localDimension2 = this.fMain.getSize();
    if (localDimension1.width >= 640)
    {
      this.fMain.setLocation((localDimension1.width - localDimension2.width) / 2, (localDimension1.height - localDimension2.height) / 2);
    }
    else
    {
      this.fMain.setLocation(0, 0);
      this.fMain.setSize(localDimension1);
    }
    this.fMain.show();
    if (defScript != null)
    {
      if (defDirectory != null) {
        defScript = defDirectory + File.separator + defScript;
      }
      this.txtCommand.setText(DatabaseManagerCommon.readFile(defScript));
    }
    this.txtCommand.requestFocus();
  }
  
  void addMenu(MenuBar paramMenuBar, String paramString, String[] paramArrayOfString)
  {
    Menu localMenu = new Menu(paramString);
    if ((paramString.equals("Tools")) && (!TT_AVAILABLE)) {
      localMenu.setEnabled(false);
    }
    addMenuItems(localMenu, paramArrayOfString);
    paramMenuBar.add(localMenu);
  }
  
  void addMenuItems(Menu paramMenu, String[] paramArrayOfString)
  {
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      MenuItem localMenuItem = new MenuItem(paramArrayOfString[i].substring(1));
      int j = paramArrayOfString[i].charAt(0);
      if (j != 45) {
        localMenuItem.setShortcut(new MenuShortcut(j));
      }
      localMenuItem.addActionListener(this);
      paramMenu.add(localMenuItem);
    }
  }
  
  public void keyPressed(KeyEvent paramKeyEvent) {}
  
  public void keyReleased(KeyEvent paramKeyEvent) {}
  
  public void keyTyped(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getKeyChar() == '\n') && (paramKeyEvent.isControlDown()))
    {
      paramKeyEvent.consume();
      execute();
    }
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    String str1 = paramActionEvent.getActionCommand();
    if ((str1 == null) && ((paramActionEvent.getSource() instanceof MenuItem))) {
      str1 = ((MenuItem)paramActionEvent.getSource()).getLabel();
    }
    if (str1 != null) {
      if (str1.equals("Execute"))
      {
        execute();
      }
      else if (str1.equals("Clear"))
      {
        clear();
      }
      else if (str1.equals("Exit"))
      {
        windowClosing(null);
      }
      else if (str1.equals("Transfer"))
      {
        Transfer.work(null);
      }
      else if (str1.equals("Dump"))
      {
        Transfer.work(new String[] { "-d" });
      }
      else if (str1.equals("Restore"))
      {
        Transfer.work(new String[] { "-r" });
        refreshTree();
      }
      else if (str1.equals("Logging on"))
      {
        JavaSystem.setLogToSystem(true);
      }
      else if (str1.equals("Logging off"))
      {
        JavaSystem.setLogToSystem(false);
      }
      else if (str1.equals("Help"))
      {
        showHelp(new String[] { "", HELP_TEXT });
      }
      else if (str1.equals("About"))
      {
        showHelp(new String[] { "", ABOUT_TEXT });
      }
      else if (str1.equals("Refresh Tree"))
      {
        refreshTree();
      }
      else if (str1.startsWith("#"))
      {
        int i = Integer.parseInt(str1.substring(1));
        this.txtCommand.setText(this.sRecent[i]);
      }
      else if (str1.equals("Connect..."))
      {
        connect(ConnectionDialog.createConnection(this.fMain, "Connect"));
        refreshTree();
      }
      else if (str1.equals("Results in Grid"))
      {
        this.iResult = 0;
        this.pResult.removeAll();
        this.pResult.add("Center", this.gResult);
        this.pResult.doLayout();
      }
      else
      {
        FileDialog localFileDialog;
        String str2;
        Object localObject;
        if (str1.equals("Open Script..."))
        {
          localFileDialog = new FileDialog(this.fMain, "Open Script", 0);
          if (defDirectory != null) {
            localFileDialog.setDirectory(defDirectory);
          }
          localFileDialog.show();
          str2 = localFileDialog.getFile();
          if (str2 != null)
          {
            localObject = new StringBuffer();
            this.ifHuge = DatabaseManagerCommon.readFile(localFileDialog.getDirectory() + str2);
            if (4096 <= this.ifHuge.length())
            {
              ((StringBuffer)localObject).append("This huge file cannot be edited.\n Please execute or clear\n");
              this.txtCommand.setText(((StringBuffer)localObject).toString());
            }
            else
            {
              this.txtCommand.setText(this.ifHuge);
            }
          }
        }
        else if (str1.equals("Save Script..."))
        {
          localFileDialog = new FileDialog(this.fMain, "Save Script", 1);
          if (defDirectory != null) {
            localFileDialog.setDirectory(defDirectory);
          }
          localFileDialog.show();
          str2 = localFileDialog.getFile();
          if (str2 != null) {
            DatabaseManagerCommon.writeFile(localFileDialog.getDirectory() + str2, this.txtCommand.getText());
          }
        }
        else if (str1.equals("Save Result csv..."))
        {
          localFileDialog = new FileDialog(this.fMain, "Save Result CSV", 1);
          if (defDirectory != null) {
            localFileDialog.setDirectory(defDirectory);
          }
          localFileDialog.show();
          str2 = localFileDialog.getDirectory();
          localObject = localFileDialog.getFile();
          if (str2 != null) {
            localObject = str2 + "/" + (String)localObject;
          }
          if (localObject != null)
          {
            showResultInText();
            saveAsCsv((String)localObject);
          }
        }
        else if (str1.equals("Save Result..."))
        {
          localFileDialog = new FileDialog(this.fMain, "Save Result", 1);
          if (defDirectory != null) {
            localFileDialog.setDirectory(defDirectory);
          }
          localFileDialog.show();
          str2 = localFileDialog.getFile();
          if (str2 != null)
          {
            showResultInText();
            DatabaseManagerCommon.writeFile(localFileDialog.getDirectory() + str2, this.txtResult.getText());
          }
        }
        else if (str1.equals("Results in Text"))
        {
          this.iResult = 1;
          this.pResult.removeAll();
          this.pResult.add("Center", this.txtResult);
          this.pResult.doLayout();
          showResultInText();
        }
        else if (str1.equals("AutoCommit on"))
        {
          try
          {
            this.cConn.setAutoCommit(true);
          }
          catch (SQLException localSQLException1) {}
        }
        else if (str1.equals("AutoCommit off"))
        {
          try
          {
            this.cConn.setAutoCommit(false);
          }
          catch (SQLException localSQLException2) {}
        }
        else
        {
          Dimension localDimension;
          if (str1.equals("Enlarge Tree"))
          {
            localDimension = this.tTree.getMinimumSize();
            localDimension.width += 20;
            this.tTree.setMinimumSize(localDimension);
            this.fMain.pack();
          }
          else if (str1.equals("Shrink Tree"))
          {
            localDimension = this.tTree.getMinimumSize();
            localDimension.width -= 20;
            if (localDimension.width >= 0) {
              this.tTree.setMinimumSize(localDimension);
            }
            this.fMain.pack();
          }
          else if (str1.equals("Enlarge Command"))
          {
            this.txtCommand.setRows(this.txtCommand.getRows() + 1);
            this.fMain.pack();
          }
          else if (str1.equals("Shrink Command"))
          {
            int j = this.txtCommand.getRows() - 1;
            this.txtCommand.setRows(j < 1 ? 1 : j);
            this.fMain.pack();
          }
          else if (str1.equals("Commit"))
          {
            try
            {
              this.cConn.commit();
            }
            catch (SQLException localSQLException3) {}
          }
          else if (str1.equals("Insert test data"))
          {
            insertTestData();
          }
          else if (str1.equals("Rollback"))
          {
            try
            {
              this.cConn.rollback();
            }
            catch (SQLException localSQLException4) {}
          }
          else if (str1.equals("Disable MaxRows"))
          {
            try
            {
              this.sStatement.setMaxRows(0);
            }
            catch (SQLException localSQLException5) {}
          }
          else if (str1.equals("Set MaxRows to 100"))
          {
            try
            {
              this.sStatement.setMaxRows(100);
            }
            catch (SQLException localSQLException6) {}
          }
          else if (str1.equals("SELECT"))
          {
            showHelp(DatabaseManagerCommon.selectHelp);
          }
          else if (str1.equals("INSERT"))
          {
            showHelp(DatabaseManagerCommon.insertHelp);
          }
          else if (str1.equals("UPDATE"))
          {
            showHelp(DatabaseManagerCommon.updateHelp);
          }
          else if (str1.equals("DELETE"))
          {
            showHelp(DatabaseManagerCommon.deleteHelp);
          }
          else if (str1.equals("CREATE TABLE"))
          {
            showHelp(DatabaseManagerCommon.createTableHelp);
          }
          else if (str1.equals("DROP TABLE"))
          {
            showHelp(DatabaseManagerCommon.dropTableHelp);
          }
          else if (str1.equals("CREATE INDEX"))
          {
            showHelp(DatabaseManagerCommon.createIndexHelp);
          }
          else if (str1.equals("DROP INDEX"))
          {
            showHelp(DatabaseManagerCommon.dropIndexHelp);
          }
          else if (str1.equals("CHECKPOINT"))
          {
            showHelp(DatabaseManagerCommon.checkpointHelp);
          }
          else if (str1.equals("SCRIPT"))
          {
            showHelp(DatabaseManagerCommon.scriptHelp);
          }
          else if (str1.equals("SHUTDOWN"))
          {
            showHelp(DatabaseManagerCommon.shutdownHelp);
          }
          else if (str1.equals("SET"))
          {
            showHelp(DatabaseManagerCommon.setHelp);
          }
          else if (str1.equals("Test Script"))
          {
            showHelp(DatabaseManagerCommon.testHelp);
          }
        }
      }
    }
  }
  
  void showHelp(String[] paramArrayOfString)
  {
    this.txtCommand.setText(paramArrayOfString[0]);
    this.txtResult.setText(paramArrayOfString[1]);
    this.bHelp = true;
    this.pResult.removeAll();
    this.pResult.add("Center", this.txtResult);
    this.pResult.doLayout();
    this.txtCommand.requestFocus();
    this.txtCommand.setCaretPosition(paramArrayOfString[0].length());
  }
  
  public void windowActivated(WindowEvent paramWindowEvent) {}
  
  public void windowDeactivated(WindowEvent paramWindowEvent) {}
  
  public void windowClosed(WindowEvent paramWindowEvent) {}
  
  public void windowClosing(WindowEvent paramWindowEvent)
  {
    try
    {
      if (this.cConn != null) {
        this.cConn.close();
      }
    }
    catch (Exception localException) {}
    this.fMain.dispose();
    if (bMustExit) {
      System.exit(0);
    }
  }
  
  public void windowDeiconified(WindowEvent paramWindowEvent) {}
  
  public void windowIconified(WindowEvent paramWindowEvent) {}
  
  public void windowOpened(WindowEvent paramWindowEvent) {}
  
  void clear()
  {
    this.ifHuge = "";
    this.txtCommand.setText(this.ifHuge);
  }
  
  void execute()
  {
    String str1 = null;
    if (4096 <= this.ifHuge.length()) {
      str1 = this.ifHuge;
    } else {
      str1 = this.txtCommand.getText();
    }
    if (str1.startsWith("-->>>TEST<<<--"))
    {
      testPerformance();
      return;
    }
    String[] arrayOfString = new String[1];
    this.lTime = System.currentTimeMillis();
    try
    {
      if (this.sStatement == null) {
        return;
      }
      this.sStatement.execute(str1);
      this.lTime = (System.currentTimeMillis() - this.lTime);
      int i = this.sStatement.getUpdateCount();
      if (i == -1)
      {
        localObject = this.sStatement.getResultSet();
        try
        {
          formatResultSet((ResultSet)localObject);
        }
        catch (Throwable localThrowable)
        {
          arrayOfString[0] = "Error displaying the ResultSet";
          this.gResult.setHead(arrayOfString);
          String str2 = localThrowable.getMessage();
          arrayOfString[0] = str2;
          this.gResult.addRow(arrayOfString);
        }
      }
      else
      {
        arrayOfString[0] = "update count";
        this.gResult.setHead(arrayOfString);
        arrayOfString[0] = String.valueOf(i);
        this.gResult.addRow(arrayOfString);
      }
      addToRecent(this.txtCommand.getText());
    }
    catch (SQLException localSQLException)
    {
      this.lTime = (System.currentTimeMillis() - this.lTime);
      arrayOfString[0] = "SQL Error";
      this.gResult.setHead(arrayOfString);
      Object localObject = localSQLException.getMessage();
      localObject = (String)localObject + " / Error Code: " + localSQLException.getErrorCode();
      localObject = (String)localObject + " / State: " + localSQLException.getSQLState();
      arrayOfString[0] = localObject;
      this.gResult.addRow(arrayOfString);
    }
    updateResult();
    System.gc();
  }
  
  void updateResult()
  {
    if (this.iResult == 0)
    {
      if (this.bHelp)
      {
        this.pResult.removeAll();
        this.pResult.add("Center", this.gResult);
        this.pResult.doLayout();
        this.bHelp = false;
      }
      this.gResult.update();
      this.gResult.repaint();
    }
    else
    {
      showResultInText();
    }
    this.txtCommand.selectAll();
    this.txtCommand.requestFocus();
  }
  
  void formatResultSet(ResultSet paramResultSet)
  {
    Object localObject;
    if (paramResultSet == null)
    {
      localObject = new String[1];
      localObject[0] = "Result";
      this.gResult.setHead((String[])localObject);
      localObject[0] = "(empty)";
      this.gResult.addRow((String[])localObject);
      return;
    }
    try
    {
      localObject = paramResultSet.getMetaData();
      int i = ((ResultSetMetaData)localObject).getColumnCount();
      String[] arrayOfString = new String[i];
      for (int j = 1; j <= i; j++) {
        arrayOfString[(j - 1)] = ((ResultSetMetaData)localObject).getColumnLabel(j);
      }
      this.gResult.setHead(arrayOfString);
      while (paramResultSet.next())
      {
        for (j = 1; j <= i; j++) {
          try
          {
            arrayOfString[(j - 1)] = paramResultSet.getString(j);
            if (paramResultSet.wasNull()) {
              arrayOfString[(j - 1)] = "(null)";
            }
          }
          catch (SQLException localSQLException2)
          {
            arrayOfString[(j - 1)] = "(binary data)";
          }
        }
        this.gResult.addRow(arrayOfString);
      }
      paramResultSet.close();
    }
    catch (SQLException localSQLException1) {}
  }
  
  void testPerformance()
  {
    String str1 = this.txtCommand.getText();
    StringBuffer localStringBuffer = new StringBuffer();
    long l1 = 0L;
    for (int i = 0; i < str1.length(); i++)
    {
      c = str1.charAt(i);
      if (c != '\n') {
        localStringBuffer.append(c);
      }
    }
    str1 = localStringBuffer.toString();
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "ms";
    arrayOfString[1] = "count";
    arrayOfString[2] = "sql";
    arrayOfString[3] = "error";
    this.gResult.setHead(arrayOfString);
    char c = '\001';
    this.lTime = (System.currentTimeMillis() - this.lTime);
    while (!str1.equals(""))
    {
      int k = str1.indexOf(';');
      String str2;
      if (k != -1)
      {
        str2 = str1.substring(0, k);
        str1 = str1.substring(k + 1);
      }
      else
      {
        str2 = str1;
        str1 = "";
      }
      int j;
      if (str2.startsWith("--#"))
      {
        j = Integer.parseInt(str2.substring(3));
      }
      else if (!str2.startsWith("--"))
      {
        arrayOfString[2] = str2;
        long l2 = 0L;
        try
        {
          l2 = DatabaseManagerCommon.testStatement(this.sStatement, str2, j);
          l1 += l2;
          arrayOfString[0] = String.valueOf(l2);
          arrayOfString[1] = String.valueOf(j);
          arrayOfString[3] = "";
        }
        catch (SQLException localSQLException)
        {
          String tmp274_271 = "n/a";
          arrayOfString[1] = tmp274_271;
          arrayOfString[0] = tmp274_271;
          arrayOfString[3] = localSQLException.toString();
        }
        this.gResult.addRow(arrayOfString);
        System.out.println(l2 + " ms : " + str2);
      }
    }
    arrayOfString[0] = ("" + l1);
    arrayOfString[1] = "total";
    arrayOfString[2] = "";
    this.gResult.addRow(arrayOfString);
    this.lTime = (System.currentTimeMillis() - this.lTime);
    updateResult();
  }
  
  void saveAsCsv(String paramString)
  {
    try
    {
      File localFile = new File(paramString);
      CSVWriter localCSVWriter = new CSVWriter(localFile, null);
      String[] arrayOfString1 = this.gResult.getHead();
      int i = arrayOfString1.length;
      Vector localVector = this.gResult.getData();
      int j = localVector.size();
      localCSVWriter.writeHeader(arrayOfString1);
      for (int k = 0; k < j; k++)
      {
        String[] arrayOfString2 = (String[])localVector.elementAt(k);
        String[] arrayOfString3 = new String[arrayOfString2.length];
        for (int m = 0; m < arrayOfString2.length; m++)
        {
          String str = arrayOfString2[m];
          if (str.equals("(null)")) {
            str = "";
          }
          arrayOfString3[m] = str;
        }
        localCSVWriter.writeData(arrayOfString3);
      }
      localCSVWriter.close();
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("IOError: " + localIOException.getMessage());
    }
  }
  
  void showResultInText()
  {
    String[] arrayOfString1 = this.gResult.getHead();
    int i = arrayOfString1.length;
    int[] arrayOfInt = new int[i];
    Vector localVector = this.gResult.getData();
    int j = localVector.size();
    for (int k = 0; k < i; k++) {
      arrayOfInt[k] = arrayOfString1[k].length();
    }
    String[] arrayOfString2;
    int n;
    for (k = 0; k < j; k++)
    {
      arrayOfString2 = (String[])localVector.elementAt(k);
      for (m = 0; m < i; m++)
      {
        n = arrayOfString2[m].length();
        if (n > arrayOfInt[m]) {
          arrayOfInt[m] = n;
        }
      }
    }
    StringBuffer localStringBuffer = new StringBuffer();
    for (int m = 0; m < i; m++)
    {
      localStringBuffer.append(arrayOfString1[m]);
      for (n = arrayOfString1[m].length(); n <= arrayOfInt[m]; n++) {
        localStringBuffer.append(' ');
      }
    }
    localStringBuffer.append(field_1661);
    for (m = 0; m < i; m++)
    {
      for (n = 0; n < arrayOfInt[m]; n++) {
        localStringBuffer.append('-');
      }
      localStringBuffer.append(' ');
    }
    localStringBuffer.append(field_1661);
    for (m = 0; m < j; m++)
    {
      arrayOfString2 = (String[])localVector.elementAt(m);
      for (n = 0; n < i; n++)
      {
        localStringBuffer.append(arrayOfString2[n]);
        for (int i1 = arrayOfString2[n].length(); i1 <= arrayOfInt[n]; i1++) {
          localStringBuffer.append(' ');
        }
      }
      localStringBuffer.append(field_1661);
    }
    localStringBuffer.append(field_1661 + j + " row(s) in " + this.lTime + " ms");
    this.txtResult.setText(localStringBuffer.toString());
  }
  
  private void addToRecent(String paramString)
  {
    for (int i = 0; i < 24; i++) {
      if (paramString.equals(this.sRecent[i])) {
        return;
      }
    }
    if (this.sRecent[this.iRecent] != null) {
      this.mRecent.remove(this.iRecent);
    }
    this.sRecent[this.iRecent] = paramString;
    if (paramString.length() > 43) {
      paramString = paramString.substring(0, 40) + "...";
    }
    MenuItem localMenuItem = new MenuItem(paramString);
    localMenuItem.setActionCommand("#" + this.iRecent);
    localMenuItem.addActionListener(this);
    this.mRecent.insert(localMenuItem, this.iRecent);
    this.iRecent = ((this.iRecent + 1) % 24);
  }
  
  private void initGUI()
  {
    Panel localPanel1 = new Panel();
    Panel localPanel2 = new Panel();
    this.pResult = new Panel();
    localPanel1.setLayout(new BorderLayout());
    localPanel2.setLayout(new BorderLayout());
    this.pResult.setLayout(new BorderLayout());
    Font localFont = new Font("Dialog", 0, 12);
    this.txtCommand = new TextArea(5, 40);
    this.txtCommand.addKeyListener(this);
    this.txtResult = new TextArea(20, 40);
    this.txtCommand.setFont(localFont);
    this.txtResult.setFont(new Font("Courier", 0, 12));
    this.butExecute = new Button("Execute");
    this.butClear = new Button("Clear");
    this.butExecute.addActionListener(this);
    this.butClear.addActionListener(this);
    localPanel2.add("East", this.butExecute);
    localPanel2.add("West", this.butClear);
    localPanel2.add("Center", this.txtCommand);
    this.gResult = new Grid();
    setLayout(new BorderLayout());
    this.pResult.add("Center", this.gResult);
    localPanel1.add("North", localPanel2);
    localPanel1.add("Center", this.pResult);
    this.fMain.add("Center", localPanel1);
    this.tTree = new Tree();
    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
    if (localDimension.width >= 640) {
      this.tTree.setMinimumSize(new Dimension(200, 100));
    } else {
      this.tTree.setMinimumSize(new Dimension(80, 100));
    }
    this.gResult.setMinimumSize(new Dimension(200, 300));
    this.fMain.add("West", this.tTree);
    doLayout();
    this.fMain.pack();
  }
  
  protected void refreshTree()
  {
    boolean bool1 = false;
    this.tTree.removeAll();
    try
    {
      bool1 = this.cConn.getAutoCommit();
      this.cConn.setAutoCommit(false);
      int i = Color.yellow.getRGB();
      int j = Color.orange.getRGB();
      int k = Color.red.getRGB();
      this.tTree.addRow("", this.dMeta.getURL(), "-", 0);
      String[] arrayOfString = { "TABLE", "GLOBAL TEMPORARY", "VIEW" };
      Vector localVector1 = new Vector();
      Vector localVector2 = new Vector();
      Vector localVector3 = new Vector();
      ResultSet localResultSet1 = this.dMeta.getTables(null, null, null, arrayOfString);
      try
      {
        while (localResultSet1.next())
        {
          localVector1.addElement(localResultSet1.getString(2));
          localVector2.addElement(localResultSet1.getString(3));
          localVector3.addElement(localResultSet1.getString(5));
        }
      }
      finally
      {
        localResultSet1.close();
      }
      for (int m = 0; m < localVector2.size(); m++)
      {
        String str1 = (String)localVector2.elementAt(m);
        String str2 = (String)localVector1.elementAt(m);
        String str3 = "tab-" + str1 + "-";
        this.tTree.addRow(str3, str1, "+", i);
        String str4 = (String)localVector3.elementAt(m);
        if ((str2 != null) && (!str2.trim().equals(""))) {
          this.tTree.addRow(str3 + "s", "schema: " + str2);
        }
        if ((str4 != null) && (!str4.trim().equals(""))) {
          this.tTree.addRow(str3 + "r", " " + str4);
        }
        ResultSet localResultSet2 = this.dMeta.getColumns(null, str2, str1, null);
        try
        {
          while (localResultSet2.next())
          {
            localObject2 = localResultSet2.getString(4);
            localObject3 = str3 + "col-" + (String)localObject2 + "-";
            this.tTree.addRow((String)localObject3, (String)localObject2, "+", j);
            String str5 = localResultSet2.getString(6);
            this.tTree.addRow((String)localObject3 + "t", "Type: " + str5);
            boolean bool3 = localResultSet2.getInt(11) != 0;
            this.tTree.addRow((String)localObject3 + "n", "Nullable: " + bool3);
          }
        }
        finally
        {
          localResultSet2.close();
        }
        this.tTree.addRow(str3 + "ind", "Indices", "+", 0);
        Object localObject2 = this.dMeta.getIndexInfo(null, str2, str1, false, false);
        Object localObject3 = null;
        try
        {
          while (((ResultSet)localObject2).next())
          {
            boolean bool2 = ((ResultSet)localObject2).getBoolean(4);
            String str6 = ((ResultSet)localObject2).getString(6);
            String str7 = str3 + "ind-" + str6 + "-";
            if ((localObject3 == null) || (!((String)localObject3).equals(str6)))
            {
              this.tTree.addRow(str7, str6, "+", k);
              this.tTree.addRow(str7 + "u", "Unique: " + (!bool2));
              localObject3 = str6;
            }
            String str8 = ((ResultSet)localObject2).getString(9);
            this.tTree.addRow(str7 + "c-" + str8 + "-", str8);
          }
        }
        finally
        {
          ((ResultSet)localObject2).close();
        }
      }
      this.tTree.addRow("p", "Properties", "+", 0);
      this.tTree.addRow("pu", "User: " + this.dMeta.getUserName());
      this.tTree.addRow("pr", "ReadOnly: " + this.cConn.isReadOnly());
      this.tTree.addRow("pa", "AutoCommit: " + this.cConn.getAutoCommit());
      this.tTree.addRow("pd", "Driver: " + this.dMeta.getDriverName());
      this.tTree.addRow("pp", "Product: " + this.dMeta.getDatabaseProductName());
      this.tTree.addRow("pv", "Version: " + this.dMeta.getDatabaseProductVersion());
      try
      {
        this.cConn.setAutoCommit(bool1);
      }
      catch (SQLException localSQLException1) {}
      this.tTree.update();
    }
    catch (SQLException localSQLException2)
    {
      this.tTree.addRow("", "Error getting metadata:", "-", 0);
      this.tTree.addRow("-", localSQLException2.getMessage());
      this.tTree.addRow("-", localSQLException2.getSQLState());
    }
    finally
    {
      try
      {
        this.cConn.setAutoCommit(bool1);
      }
      catch (SQLException localSQLException4) {}
    }
  }
  
  static
  {
    try
    {
      Class.forName(DatabaseManager.class.getPackage().getName() + ".Transfer");
      TT_AVAILABLE = true;
    }
    catch (Throwable localThrowable) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.util.DatabaseManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */