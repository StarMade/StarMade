package org.hsqldb.util;

import java.applet.AppletContext;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.hsqldb.lib.RCData;
import org.hsqldb.lib.java.JavaSystem;

public class DatabaseManagerSwing
  extends JApplet
  implements ActionListener, WindowListener, KeyListener, MouseListener
{
  private static String homedir = null;
  private boolean isOracle = false;
  ArrayList localActionList = new ArrayList();
  private JFrame jframe = null;
  private static final String DEFAULT_RCFILE;
  private static boolean TT_AVAILABLE;
  private static final String HELP_TEXT;
  private static final String ABOUT_TEXT;
  static final String field_1676;
  static final String NULL_STR = "[null]";
  static int iMaxRecent;
  Connection cConn;
  Connection rowConn;
  DatabaseMetaData dMeta;
  Statement sStatement;
  JMenu mRecent;
  String[] sRecent;
  int iRecent;
  JTextArea txtCommand;
  JScrollPane txtCommandScroll;
  JButton butExecute;
  JTree tTree;
  JScrollPane tScrollPane;
  DefaultTreeModel treeModel;
  TableModel tableModel;
  DefaultMutableTreeNode rootNode;
  JPanel pResult;
  long lTime;
  GridSwing gResult;
  JTable gResultTable;
  JScrollPane gScrollPane;
  JTextArea txtResult;
  JScrollPane txtResultScroll;
  JSplitPane nsSplitPane;
  JSplitPane ewSplitPane;
  boolean bHelp;
  RootPaneContainer fMain;
  static boolean bMustExit;
  String sqlScriptBuffer = null;
  JToolBar jtoolbar;
  private boolean showSchemas = true;
  private boolean showTooltips = true;
  private boolean autoRefresh = true;
  private boolean gridFormat = true;
  static DatabaseManagerSwing refForFontDialogSwing;
  boolean displayRowCounts = false;
  boolean showSys = false;
  boolean showIndexDetails = true;
  String currentLAF = null;
  JPanel pStatus;
  static JButton iReadyStatus;
  JRadioButtonMenuItem rbAllSchemas = new JRadioButtonMenuItem("*");
  JMenuItem mitemAbout = new JMenuItem("About", 65);
  JMenuItem mitemHelp = new JMenuItem("Help", 72);
  JMenuItem mitemUpdateSchemas = new JMenuItem("Update Schemas");
  JCheckBoxMenuItem boxAutoCommit = new JCheckBoxMenuItem("Autocommit mode");
  JCheckBoxMenuItem boxLogging = new JCheckBoxMenuItem("Logging mode");
  JCheckBoxMenuItem boxShowSchemas = new JCheckBoxMenuItem("Show schemas");
  JCheckBoxMenuItem boxAutoRefresh = new JCheckBoxMenuItem("Auto-refresh tree");
  JCheckBoxMenuItem boxTooltips = new JCheckBoxMenuItem("Show Tooltips");
  JCheckBoxMenuItem boxRowCounts = new JCheckBoxMenuItem("Show row counts");
  JCheckBoxMenuItem boxShowGrid = new JCheckBoxMenuItem("Show results in Grid (a.o.t. Text)");
  JCheckBoxMenuItem boxShowSys = new JCheckBoxMenuItem("Show system tables");
  JRadioButtonMenuItem rbNativeLF = new JRadioButtonMenuItem("Native Look & Feel");
  JRadioButtonMenuItem rbJavaLF = new JRadioButtonMenuItem("Java Look & Feel");
  JRadioButtonMenuItem rbMotifLF = new JRadioButtonMenuItem("Motif Look & Feel");
  JLabel jStatusLine;
  static String READY_STATUS;
  private static final String AUTOCOMMIT_BOX_TEXT = "Autocommit mode";
  private static final String LOGGING_BOX_TEXT = "Logging mode";
  private static final String SHOWSCHEMAS_BOX_TEXT = "Show schemas";
  private static final String AUTOREFRESH_BOX_TEXT = "Auto-refresh tree";
  private static final String SHOWTIPS_BOX_TEXT = "Show Tooltips";
  private static final String ROWCOUNTS_BOX_TEXT = "Show row counts";
  private static final String SHOWSYS_BOX_TEXT = "Show system tables";
  private static final String GRID_BOX_TEXT = "Show results in Grid (a.o.t. Text)";
  Cursor fMainCursor;
  Cursor txtCommandCursor;
  Cursor txtResultCursor;
  HashMap tipMap = new HashMap();
  private JMenu mnuSchemas = new JMenu("Schemas");
  private final Cursor waitCursor = new Cursor(3);
  static String defDriver;
  static String defURL;
  static String defUser;
  static String defPassword;
  static String defScript;
  static String defDirectory;
  private String schemaFilter = null;
  private DBMPrefs prefs = null;
  Thread dummyThread = new Thread("dummy");
  private String busyText = null;
  private Runnable enableButtonRunnable = new Runnable()
  {
    public void run()
    {
      DatabaseManagerSwing.this.jbuttonClear.setEnabled(true);
      DatabaseManagerSwing.this.jbuttonExecute.setEnabled(true);
    }
  };
  private Runnable disableButtonRunnable = new Runnable()
  {
    public void run()
    {
      DatabaseManagerSwing.this.jbuttonClear.setEnabled(false);
      DatabaseManagerSwing.this.jbuttonExecute.setEnabled(false);
    }
  };
  private Thread buttonUpdaterThread = null;
  private static final int BUTTON_CHECK_PERIOD = 500;
  private Runnable buttonUpdater = new Runnable()
  {
    public void run()
    {
      for (;;)
      {
        try
        {
          Thread.sleep(500L);
        }
        catch (InterruptedException localInterruptedException) {}
        if (DatabaseManagerSwing.this.buttonUpdaterThread == null) {
          return;
        }
        int i = DatabaseManagerSwing.this.txtCommand.getText().length() > 0 ? 1 : 0;
        if (DatabaseManagerSwing.this.jbuttonClear.isEnabled() != i) {
          SwingUtilities.invokeLater(i != 0 ? DatabaseManagerSwing.this.enableButtonRunnable : DatabaseManagerSwing.this.disableButtonRunnable);
        }
      }
    }
  };
  private JButton jbuttonClear;
  private JButton jbuttonExecute;
  private Runnable treeRefreshRunnable = new Runnable()
  {
    public void run()
    {
      try
      {
        DatabaseManagerSwing.this.directRefreshTree();
      }
      catch (RuntimeException localRuntimeException)
      {
        CommonSwing.errorMessage(localRuntimeException);
        throw localRuntimeException;
      }
      finally
      {
        DatabaseManagerSwing.this.setWaiting(null);
      }
    }
  };
  private MouseEvent alreadyHandled = null;
  private static final String[] usertables;
  private static final String[] nonSystables;
  private static final HashSet oracleSysUsers;
  private static final String[] oracleSysSchemas;
  ActionListener schemaListListener = new ActionListener()
  {
    public void actionPerformed(ActionEvent paramAnonymousActionEvent)
    {
      DatabaseManagerSwing.this.schemaFilter = paramAnonymousActionEvent.getActionCommand();
      if (DatabaseManagerSwing.this.schemaFilter.equals("*")) {
        DatabaseManagerSwing.this.schemaFilter = null;
      }
      DatabaseManagerSwing.this.refreshTree();
    }
  };
  private static final String tString = Boolean.TRUE.toString();
  private static final String fString = Boolean.FALSE.toString();
  
  public DatabaseManagerSwing()
  {
    this.jframe = new JFrame("HSQLDB DatabaseManager");
    this.fMain = this.jframe;
  }
  
  public DatabaseManagerSwing(JFrame paramJFrame)
  {
    this.jframe = paramJFrame;
    this.fMain = this.jframe;
  }
  
  public void init()
  {
    this.fMain = this;
    main();
    for (int i = 0; i < this.localActionList.size(); i++)
    {
      AbstractButton localAbstractButton = (AbstractButton)this.localActionList.get(i);
      localAbstractButton.setEnabled(false);
    }
    Connection localConnection = null;
    int j = 0;
    if (getParameter("jdbcDriver") != null)
    {
      j = 1;
      defDriver = getParameter("jdbcDriver");
    }
    if (getParameter("jdbcUrl") != null)
    {
      j = 1;
      defURL = getParameter("jdbcUrl");
    }
    if (getParameter("jdbcUser") != null)
    {
      j = 1;
      defUser = getParameter("jdbcUser");
    }
    if (getParameter("jdbcPassword") != null)
    {
      j = 1;
      defPassword = getParameter("jdbcPassword");
    }
    try
    {
      setWaiting("Initializing");
      localConnection = j != 0 ? ConnectionDialogSwing.createConnection(defDriver, defURL, defUser, defPassword) : ConnectionDialogSwing.createConnection(this.jframe, "Connect");
    }
    catch (Exception localException)
    {
      CommonSwing.errorMessage(localException);
    }
    finally
    {
      setWaiting(null);
    }
    if (localConnection != null) {
      connect(localConnection);
    }
    if ((getParameter("loadSampleData") != null) && (getParameter("loadSampleData").equals("true")))
    {
      insertTestData();
      try
      {
        Thread.sleep(1000L);
      }
      catch (InterruptedException localInterruptedException) {}
      refreshTree();
    }
    if (getParameter("schemaFilter") != null) {
      this.schemaFilter = getParameter("schemaFilter");
    }
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
    DatabaseManagerSwing localDatabaseManagerSwing = new DatabaseManagerSwing(new JFrame("HSQL Database Manager"));
    refForFontDialogSwing = localDatabaseManagerSwing;
    localDatabaseManagerSwing.main();
    Connection localConnection = null;
    localDatabaseManagerSwing.setWaiting("Initializing");
    try
    {
      if ((i != 0) && (j != 0)) {
        throw new IllegalArgumentException("You may not specify both (urlid) AND (url/user/password).");
      }
      if (i != 0)
      {
        localConnection = ConnectionDialogSwing.createConnection(defDriver, defURL, defUser, defPassword);
      }
      else if (j != 0)
      {
        if (str3 == null) {
          throw new IllegalArgumentException("You must specify an 'urlid' to use an RC file");
        }
        i = 1;
        String str5 = str4 == null ? DEFAULT_RCFILE : str4;
        RCData localRCData = new RCData(new File(str5), str3);
        localConnection = localRCData.getConnection(null, System.getProperty("javax.net.ssl.trustStore"));
      }
      else
      {
        localConnection = ConnectionDialogSwing.createConnection(localDatabaseManagerSwing.jframe, "Connect");
      }
    }
    catch (Exception localException)
    {
      CommonSwing.errorMessage(localException);
    }
    finally
    {
      localDatabaseManagerSwing.setWaiting(null);
    }
    if (localConnection != null) {
      localDatabaseManagerSwing.connect(localConnection);
    }
    FontDialogSwing.creatFontDialog(refForFontDialogSwing);
    localDatabaseManagerSwing.start();
  }
  
  public void connect(Connection paramConnection)
  {
    this.schemaFilter = null;
    if (paramConnection == null) {
      return;
    }
    if (this.cConn != null) {
      try
      {
        this.cConn.close();
      }
      catch (SQLException localSQLException1)
      {
        CommonSwing.errorMessage(localSQLException1);
      }
    }
    this.cConn = paramConnection;
    this.rowConn = paramConnection;
    try
    {
      this.dMeta = this.cConn.getMetaData();
      this.isOracle = (this.dMeta.getDatabaseProductName().indexOf("Oracle") >= 0);
      this.sStatement = this.cConn.createStatement();
      updateAutoCommitBox();
      this.showIndexDetails = (!this.isOracle);
      Driver localDriver = DriverManager.getDriver(this.dMeta.getURL());
      ConnectionSetting localConnectionSetting = new ConnectionSetting(this.dMeta.getDatabaseProductName(), localDriver.getClass().getName(), this.dMeta.getURL(), this.dMeta.getUserName().replaceAll("@localhost", ""), "");
      Hashtable localHashtable = ConnectionDialogCommon.loadRecentConnectionSettings();
      ConnectionDialogCommon.addToRecentConnectionSettings(localHashtable, localConnectionSetting);
      ConnectionDialogSwing.setConnectionSetting(localConnectionSetting);
      refreshTree();
      clearResultPanel();
      if ((this.fMain instanceof JApplet)) {
        getAppletContext().showStatus("JDBC Connection established to a " + this.dMeta.getDatabaseProductName() + " v. " + this.dMeta.getDatabaseProductVersion() + " database as '" + this.dMeta.getUserName() + "'.");
      }
    }
    catch (SQLException localSQLException2)
    {
      CommonSwing.errorMessage(localSQLException2);
    }
    catch (IOException localIOException)
    {
      CommonSwing.errorMessage(localIOException);
    }
    catch (Exception localException)
    {
      CommonSwing.errorMessage(localException);
    }
  }
  
  private static void showUsage()
  {
    System.out.println("Usage: java DatabaseManagerSwing [--options]\nwhere options include:\n    --help                show this message\n    --driver <classname>  jdbc driver class\n    --url <name>          jdbc url\n    --user <name>         username used for connection\n    --password <password> password for this user\n    --urlid <urlid>       use url/user/password/driver in rc file\n    --rcfile <file>       (defaults to 'dbmanager.rc' in home dir)\n    --dir <path>          default directory\n    --script <file>       reads from script file\n    --noexit              do not call system.exit()");
  }
  
  private void insertTestData()
  {
    try
    {
      DatabaseManagerCommon.createTestTables(this.sStatement);
      this.txtCommand.setText(DatabaseManagerCommon.createTestData(this.sStatement));
      for (int i = 0; i < DatabaseManagerCommon.testDataSql.length; i++) {
        addToRecent(DatabaseManagerCommon.testDataSql[i]);
      }
      executeCurrentSQL();
    }
    catch (SQLException localSQLException)
    {
      CommonSwing.errorMessage(localSQLException);
    }
  }
  
  public void setMustExit(boolean paramBoolean)
  {
    bMustExit = paramBoolean;
  }
  
  public void main()
  {
    try
    {
      this.prefs = new DBMPrefs(this.fMain instanceof JApplet);
    }
    catch (Exception localException) {}
    if (this.prefs == null)
    {
      setLF(CommonSwing.Native);
    }
    else
    {
      this.autoRefresh = this.prefs.autoRefresh;
      this.displayRowCounts = this.prefs.showRowCounts;
      this.showSys = this.prefs.showSysTables;
      this.showSchemas = this.prefs.showSchemas;
      this.gridFormat = this.prefs.resultGrid;
      this.showTooltips = this.prefs.showTooltips;
      setLF(this.prefs.laf);
    }
    this.fMain.getContentPane().add(createToolBar(), "North");
    if ((this.fMain instanceof Frame)) {
      ((Frame)this.fMain).setIconImage(CommonSwing.getIcon("Frame"));
    }
    if ((this.fMain instanceof Window)) {
      ((Window)this.fMain).addWindowListener(this);
    }
    JMenuBar localJMenuBar = new JMenuBar();
    String[] arrayOfString1 = { "-Connect...", "--", "OOpen Script...", "-Save Script...", "-Save Result...", "--", "-Exit" };
    JMenu localJMenu1 = addMenu(localJMenuBar, "File", arrayOfString1);
    JMenuItem localJMenuItem;
    for (int i = 2; i < localJMenu1.getItemCount(); i++)
    {
      localJMenuItem = localJMenu1.getItem(i);
      if (localJMenuItem != null) {
        this.localActionList.add(localJMenuItem);
      }
    }
    Object[] arrayOfObject1 = { "RRefresh Tree", this.boxAutoRefresh, "--", this.boxRowCounts, this.boxShowSys, this.boxShowSchemas, this.boxShowGrid };
    addMenu(localJMenuBar, "View", arrayOfObject1);
    String[] arrayOfString2 = { "SSELECT", "IINSERT", "UUPDATE", "DDELETE", "EEXECUTE", "---", "-CREATE TABLE", "-DROP TABLE", "-CREATE INDEX", "-DROP INDEX", "--", "CCOMMIT*", "LROLLBACK*", "-CHECKPOINT*", "-SCRIPT", "-SET", "-SHUTDOWN", "--", "-Test Script" };
    addMenu(localJMenuBar, "Command", arrayOfString2);
    this.mRecent = new JMenu("Recent");
    this.mRecent.setMnemonic(82);
    localJMenuBar.add(this.mRecent);
    ButtonGroup localButtonGroup = new ButtonGroup();
    localButtonGroup.add(this.rbNativeLF);
    localButtonGroup.add(this.rbJavaLF);
    localButtonGroup.add(this.rbMotifLF);
    this.boxShowSchemas.setSelected(this.showSchemas);
    this.boxShowGrid.setSelected(this.gridFormat);
    this.boxTooltips.setSelected(this.showTooltips);
    this.boxShowGrid.setAccelerator(KeyStroke.getKeyStroke(71, 2));
    this.boxAutoRefresh.setSelected(this.autoRefresh);
    this.boxRowCounts.setSelected(this.displayRowCounts);
    this.boxShowSys.setSelected(this.showSys);
    this.rbNativeLF.setActionCommand("LFMODE:" + CommonSwing.Native);
    this.rbJavaLF.setActionCommand("LFMODE:" + CommonSwing.Java);
    this.rbMotifLF.setActionCommand("LFMODE:" + CommonSwing.Motif);
    this.tipMap.put(this.mitemUpdateSchemas, "Refresh the schema list in this menu");
    this.tipMap.put(this.rbAllSchemas, "Display items in all schemas");
    this.tipMap.put(this.mitemAbout, "Display product information");
    this.tipMap.put(this.mitemHelp, "Display advice for obtaining help");
    this.tipMap.put(this.boxAutoRefresh, "Refresh tree (and schema list) automaticallywhen YOU modify database objects");
    this.tipMap.put(this.boxShowSchemas, "Display object names in tree-like schemaname.basename");
    this.tipMap.put(this.rbNativeLF, "Set Look and Feel to Native for your platform");
    this.tipMap.put(this.rbJavaLF, "Set Look and Feel to Java");
    this.tipMap.put(this.rbMotifLF, "Set Look and Feel to Motif");
    this.boxTooltips.setToolTipText("Display tooltips (hover text), like this");
    this.tipMap.put(this.boxAutoCommit, "Shows current Auto-commit mode.  Click to change");
    this.tipMap.put(this.boxLogging, "Shows current JDBC DriverManager logging mode.  Click to change");
    this.tipMap.put(this.boxShowSys, "Show system tables in table tree to the left");
    this.tipMap.put(this.boxShowGrid, "Show query results in grid (in text if off)");
    this.tipMap.put(this.boxRowCounts, "Show row counts with table names in tree");
    this.boxAutoRefresh.setMnemonic(67);
    this.boxShowSchemas.setMnemonic(89);
    this.boxAutoCommit.setMnemonic(65);
    this.boxShowSys.setMnemonic(89);
    this.boxShowGrid.setMnemonic(71);
    this.boxRowCounts.setMnemonic(67);
    this.boxLogging.setMnemonic(76);
    this.rbAllSchemas.setMnemonic(151);
    this.rbNativeLF.setMnemonic(78);
    this.rbJavaLF.setMnemonic(74);
    this.rbMotifLF.setMnemonic(77);
    this.mitemUpdateSchemas.setMnemonic(85);
    Object[] arrayOfObject2 = { this.rbNativeLF, this.rbJavaLF, this.rbMotifLF, "--", "-Set Fonts", "--", this.boxAutoCommit, "--", "-Disable MaxRows", "-Set MaxRows to 100", "--", this.boxLogging, "--", "-Insert test data" };
    addMenu(localJMenuBar, "Options", arrayOfObject2);
    String[] arrayOfString3 = { "-Dump", "-Restore", "-Transfer" };
    localJMenu1 = addMenu(localJMenuBar, "Tools", arrayOfString3);
    localJMenu1.setEnabled(TT_AVAILABLE);
    this.localActionList.add(localJMenu1);
    for (int j = 0; j < localJMenu1.getItemCount(); j++)
    {
      localJMenuItem = localJMenu1.getItem(j);
      if (localJMenuItem != null) {
        this.localActionList.add(localJMenuItem);
      }
    }
    this.mnuSchemas.setMnemonic(83);
    localJMenuBar.add(this.mnuSchemas);
    JMenu localJMenu2 = new JMenu("Help");
    localJMenu2.setMnemonic(72);
    localJMenu2.add(this.mitemAbout);
    localJMenu2.add(this.mitemHelp);
    localJMenu2.add(this.boxTooltips);
    this.rbAllSchemas.addActionListener(this.schemaListListener);
    this.mitemUpdateSchemas.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        DatabaseManagerSwing.this.updateSchemaList();
      }
    });
    this.mitemHelp.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JOptionPane.showMessageDialog(DatabaseManagerSwing.this.fMain.getContentPane(), DatabaseManagerSwing.HELP_TEXT, "HELP", 1);
      }
    });
    this.mitemAbout.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JOptionPane.showMessageDialog(DatabaseManagerSwing.this.fMain.getContentPane(), DatabaseManagerSwing.ABOUT_TEXT, "About", 1);
      }
    });
    this.boxTooltips.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        DatabaseManagerSwing.this.showTooltips = DatabaseManagerSwing.this.boxTooltips.isSelected();
        DatabaseManagerSwing.this.resetTooltips();
      }
    });
    localJMenuBar.add(localJMenu2);
    if ((this.fMain instanceof JApplet)) {
      ((JApplet)this.fMain).setJMenuBar(localJMenuBar);
    } else if ((this.fMain instanceof JFrame)) {
      ((JFrame)this.fMain).setJMenuBar(localJMenuBar);
    }
    initGUI();
    this.sRecent = new String[iMaxRecent];
    if (!(this.fMain instanceof JApplet)) {
      CommonSwing.setFramePositon((JFrame)this.fMain);
    }
    ((Component)this.fMain).setVisible(true);
    if (defScript != null)
    {
      if (defDirectory != null) {
        defScript = defDirectory + File.separator + defScript;
      }
      this.sqlScriptBuffer = DatabaseManagerCommon.readFile(defScript);
      if (4096 <= this.sqlScriptBuffer.length())
      {
        int k = this.sqlScriptBuffer.indexOf('\n');
        if (k > 0) {
          k = this.sqlScriptBuffer.indexOf('\n', k + 1);
        }
        if (k > 0) {
          k = this.sqlScriptBuffer.indexOf('\n', k + 1);
        }
        if (k < 1) {
          k = 100;
        }
        this.txtCommand.setText("............... Script File loaded: " + defScript + " ..................... \n" + "............... Click Execute or Clear " + "...................\n" + this.sqlScriptBuffer.substring(0, k + 1) + "..........................................." + "..............................\n" + "............................................." + "............................\n");
        this.txtCommand.setEnabled(false);
      }
      else
      {
        this.txtCommand.setText(this.sqlScriptBuffer);
        this.sqlScriptBuffer = null;
        this.txtCommand.setEnabled(true);
      }
    }
    resetTooltips();
    this.txtCommand.requestFocus();
  }
  
  private JMenu addMenu(JMenuBar paramJMenuBar, String paramString, Object[] paramArrayOfObject)
  {
    JMenu localJMenu = new JMenu(paramString);
    localJMenu.setMnemonic(paramString.charAt(0));
    addMenuItems(localJMenu, paramArrayOfObject);
    paramJMenuBar.add(localJMenu);
    return localJMenu;
  }
  
  private void addMenuItems(JMenu paramJMenu, Object[] paramArrayOfObject)
  {
    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      if (paramArrayOfObject[i].equals("--"))
      {
        paramJMenu.addSeparator();
      }
      else if (paramArrayOfObject[i].equals("---"))
      {
        if (localDimension.width >= 640) {
          paramJMenu.addSeparator();
        }
      }
      else
      {
        JMenuItem localJMenuItem;
        if ((paramArrayOfObject[i] instanceof JMenuItem))
        {
          localJMenuItem = (JMenuItem)paramArrayOfObject[i];
        }
        else if ((paramArrayOfObject[i] instanceof String))
        {
          localJMenuItem = new JMenuItem(((String)paramArrayOfObject[i]).substring(1));
          int j = ((String)paramArrayOfObject[i]).charAt(0);
          if (j != 45)
          {
            KeyStroke localKeyStroke = KeyStroke.getKeyStroke(j, 2);
            localJMenuItem.setAccelerator(localKeyStroke);
          }
        }
        else
        {
          throw new RuntimeException("Unexpected element for menu item creation: " + paramArrayOfObject[i].getClass().getName());
        }
        localJMenuItem.addActionListener(this);
        paramJMenu.add(localJMenuItem);
      }
    }
  }
  
  public void keyPressed(KeyEvent paramKeyEvent) {}
  
  public void keyReleased(KeyEvent paramKeyEvent) {}
  
  public void keyTyped(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getKeyChar() == '\n') && (paramKeyEvent.isControlDown()))
    {
      paramKeyEvent.consume();
      executeCurrentSQL();
    }
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    String str = paramActionEvent.getActionCommand();
    if ((str == null) && ((paramActionEvent.getSource() instanceof JMenuItem))) {
      str = ((JMenuItem)paramActionEvent.getSource()).getText();
    }
    if (str != null) {
      if (str.equals("Exit"))
      {
        windowClosing(null);
      }
      else if (str.equals("Transfer"))
      {
        Transfer.work(null);
      }
      else if (str.equals("Dump"))
      {
        Transfer.work(new String[] { "-d" });
      }
      else if (str.equals("Restore"))
      {
        JOptionPane.showMessageDialog(this.fMain.getContentPane(), "Use Ctrl-R or the View menu to\nupdate nav. tree after Restoration", "Suggestion", 1);
        Transfer.work(new String[] { "-r" });
      }
      else if (str.equals("Logging mode"))
      {
        JavaSystem.setLogToSystem(this.boxLogging.isSelected());
      }
      else if (str.equals("Auto-refresh tree"))
      {
        this.autoRefresh = this.boxAutoRefresh.isSelected();
        refreshTree();
      }
      else if (str.equals("Refresh Tree"))
      {
        refreshTree();
      }
      else if (str.startsWith("#"))
      {
        int i = Integer.parseInt(str.substring(1));
        this.txtCommand.setText(this.sRecent[i]);
      }
      else
      {
        Object localObject1;
        if (str.equals("Connect..."))
        {
          localObject1 = null;
          try
          {
            setWaiting("Connecting");
            localObject1 = ConnectionDialogSwing.createConnection(this.jframe, "Connect");
          }
          finally
          {
            setWaiting(null);
          }
          connect((Connection)localObject1);
        }
        else if (str.equals("Show results in Grid (a.o.t. Text)"))
        {
          this.gridFormat = this.boxShowGrid.isSelected();
          displayResults();
        }
        else
        {
          int j;
          File localFile;
          if (str.equals("Open Script..."))
          {
            localObject1 = new JFileChooser(".");
            ((JFileChooser)localObject1).setDialogTitle("Open Script...");
            if (defDirectory != null) {
              ((JFileChooser)localObject1).setCurrentDirectory(new File(defDirectory));
            }
            j = ((JFileChooser)localObject1).showOpenDialog((Component)this.fMain);
            if (j == 0)
            {
              localFile = ((JFileChooser)localObject1).getSelectedFile();
              if (localFile != null)
              {
                this.sqlScriptBuffer = DatabaseManagerCommon.readFile(localFile.getAbsolutePath());
                if (4096 <= this.sqlScriptBuffer.length())
                {
                  int k = this.sqlScriptBuffer.indexOf('\n');
                  if (k > 0) {
                    k = this.sqlScriptBuffer.indexOf('\n', k + 1);
                  }
                  if (k > 0) {
                    k = this.sqlScriptBuffer.indexOf('\n', k + 1);
                  }
                  if (k < 1) {
                    k = 100;
                  }
                  this.txtCommand.setText("............... Script File loaded: " + localFile + " ..................... \n" + "............... Click Execute or Clear " + "...................\n" + this.sqlScriptBuffer.substring(0, k + 1) + "........................................." + "................................\n" + "..........................................." + "..............................\n");
                  this.txtCommand.setEnabled(false);
                }
                else
                {
                  this.txtCommand.setText(this.sqlScriptBuffer);
                  this.sqlScriptBuffer = null;
                  this.txtCommand.setEnabled(true);
                }
              }
            }
          }
          else if (str.equals("Save Script..."))
          {
            localObject1 = new JFileChooser(".");
            ((JFileChooser)localObject1).setDialogTitle("Save Script");
            if (defDirectory != null) {
              ((JFileChooser)localObject1).setCurrentDirectory(new File(defDirectory));
            }
            j = ((JFileChooser)localObject1).showSaveDialog((Component)this.fMain);
            if (j == 0)
            {
              localFile = ((JFileChooser)localObject1).getSelectedFile();
              if (localFile != null) {
                DatabaseManagerCommon.writeFile(localFile.getAbsolutePath(), this.txtCommand.getText());
              }
            }
          }
          else if (str.equals("Save Result..."))
          {
            localObject1 = new JFileChooser(".");
            ((JFileChooser)localObject1).setDialogTitle("Save Result...");
            if (defDirectory != null) {
              ((JFileChooser)localObject1).setCurrentDirectory(new File(defDirectory));
            }
            j = ((JFileChooser)localObject1).showSaveDialog((Component)this.fMain);
            if (j == 0)
            {
              localFile = ((JFileChooser)localObject1).getSelectedFile();
              if (localFile != null)
              {
                showResultInText();
                DatabaseManagerCommon.writeFile(localFile.getAbsolutePath(), this.txtResult.getText());
              }
            }
          }
          else if (str.equals("Show system tables"))
          {
            this.showSys = this.boxShowSys.isSelected();
            refreshTree();
          }
          else if (str.equals("Show row counts"))
          {
            this.displayRowCounts = this.boxRowCounts.isSelected();
            refreshTree();
          }
          else if (str.startsWith("LFMODE:"))
          {
            setLF(str.substring("LFMODE:".length()));
          }
          else if (str.equals("Set Fonts"))
          {
            FontDialogSwing.creatFontDialog(refForFontDialogSwing);
          }
          else if (str.equals("Autocommit mode"))
          {
            try
            {
              this.cConn.setAutoCommit(this.boxAutoCommit.isSelected());
            }
            catch (SQLException localSQLException1)
            {
              this.boxAutoCommit.setSelected(!this.boxAutoCommit.isSelected());
              CommonSwing.errorMessage(localSQLException1);
            }
          }
          else if (str.equals("COMMIT*"))
          {
            try
            {
              this.cConn.commit();
              showHelp(new String[] { "", "COMMIT executed" });
            }
            catch (SQLException localSQLException2)
            {
              CommonSwing.errorMessage(localSQLException2);
            }
          }
          else if (str.equals("Insert test data"))
          {
            insertTestData();
            refreshTree();
          }
          else if (str.equals("ROLLBACK*"))
          {
            try
            {
              this.cConn.rollback();
              showHelp(new String[] { "", "ROLLBACK executed" });
            }
            catch (SQLException localSQLException3)
            {
              CommonSwing.errorMessage(localSQLException3);
            }
          }
          else if (str.equals("Disable MaxRows"))
          {
            try
            {
              this.sStatement.setMaxRows(0);
            }
            catch (SQLException localSQLException4)
            {
              CommonSwing.errorMessage(localSQLException4);
            }
          }
          else if (str.equals("Set MaxRows to 100"))
          {
            try
            {
              this.sStatement.setMaxRows(100);
            }
            catch (SQLException localSQLException5)
            {
              CommonSwing.errorMessage(localSQLException5);
            }
          }
          else if (str.equals("SELECT"))
          {
            showHelp(DatabaseManagerCommon.selectHelp);
          }
          else if (str.equals("INSERT"))
          {
            showHelp(DatabaseManagerCommon.insertHelp);
          }
          else if (str.equals("UPDATE"))
          {
            showHelp(DatabaseManagerCommon.updateHelp);
          }
          else if (str.equals("DELETE"))
          {
            showHelp(DatabaseManagerCommon.deleteHelp);
          }
          else if (str.equals("EXECUTE"))
          {
            executeCurrentSQL();
          }
          else if (str.equals("CREATE TABLE"))
          {
            showHelp(DatabaseManagerCommon.createTableHelp);
          }
          else if (str.equals("DROP TABLE"))
          {
            showHelp(DatabaseManagerCommon.dropTableHelp);
          }
          else if (str.equals("CREATE INDEX"))
          {
            showHelp(DatabaseManagerCommon.createIndexHelp);
          }
          else if (str.equals("DROP INDEX"))
          {
            showHelp(DatabaseManagerCommon.dropIndexHelp);
          }
          else if (str.equals("CHECKPOINT*"))
          {
            try
            {
              this.cConn.createStatement().executeUpdate("CHECKPOINT");
              showHelp(new String[] { "", "CHECKPOINT executed" });
            }
            catch (SQLException localSQLException6)
            {
              CommonSwing.errorMessage(localSQLException6);
            }
          }
          else if (str.equals("SCRIPT"))
          {
            showHelp(DatabaseManagerCommon.scriptHelp);
          }
          else if (str.equals("SHUTDOWN"))
          {
            showHelp(DatabaseManagerCommon.shutdownHelp);
          }
          else if (str.equals("SET"))
          {
            showHelp(DatabaseManagerCommon.setHelp);
          }
          else if (str.equals("Test Script"))
          {
            showHelp(DatabaseManagerCommon.testHelp);
          }
          else if (str.equals("Show schemas"))
          {
            this.showSchemas = this.boxShowSchemas.isSelected();
            refreshTree();
          }
          else
          {
            throw new RuntimeException("Unexpected action triggered: " + str);
          }
        }
      }
    }
  }
  
  private void displayResults()
  {
    if (this.gridFormat) {
      setResultsInGrid();
    } else {
      setResultsInText();
    }
  }
  
  private void setResultsInGrid()
  {
    this.pResult.removeAll();
    this.pResult.add(this.gScrollPane, "Center");
    this.pResult.doLayout();
    this.gResult.fireTableChanged(null);
    this.pResult.repaint();
  }
  
  private void setResultsInText()
  {
    this.pResult.removeAll();
    this.pResult.add(this.txtResultScroll, "Center");
    this.pResult.doLayout();
    showResultInText();
    this.pResult.repaint();
  }
  
  private void showHelp(String[] paramArrayOfString)
  {
    this.txtCommand.setText(paramArrayOfString[0]);
    this.bHelp = true;
    this.pResult.removeAll();
    this.pResult.add(this.txtResultScroll, "Center");
    this.pResult.doLayout();
    this.txtResult.setText(paramArrayOfString[1]);
    this.pResult.repaint();
    this.txtCommand.requestFocus();
    this.txtCommand.setCaretPosition(paramArrayOfString[0].length());
  }
  
  public void windowActivated(WindowEvent paramWindowEvent) {}
  
  public void windowDeactivated(WindowEvent paramWindowEvent) {}
  
  public void windowClosed(WindowEvent paramWindowEvent) {}
  
  public void windowDeiconified(WindowEvent paramWindowEvent) {}
  
  public void windowIconified(WindowEvent paramWindowEvent) {}
  
  public void windowOpened(WindowEvent paramWindowEvent) {}
  
  public void windowClosing(WindowEvent paramWindowEvent)
  {
    stop();
    try
    {
      if (this.cConn != null) {
        this.cConn.close();
      }
      if (this.prefs != null)
      {
        this.prefs.autoRefresh = this.autoRefresh;
        this.prefs.showRowCounts = this.displayRowCounts;
        this.prefs.showSysTables = this.showSys;
        this.prefs.showSchemas = this.showSchemas;
        this.prefs.resultGrid = this.gridFormat;
        this.prefs.showTooltips = this.showTooltips;
        this.prefs.laf = this.currentLAF;
        this.prefs.store();
      }
    }
    catch (Exception localException)
    {
      CommonSwing.errorMessage(localException);
    }
    if ((this.fMain instanceof Window)) {
      ((Window)this.fMain).dispose();
    }
    if (bMustExit) {
      System.exit(0);
    }
  }
  
  private void clear()
  {
    this.sqlScriptBuffer = null;
    this.txtCommand.setText("");
    this.txtCommand.setEnabled(true);
  }
  
  private void backgroundIt(Runnable paramRunnable, String paramString)
  {
    if (this.busyText != null)
    {
      Toolkit.getDefaultToolkit().beep();
      return;
    }
    setWaiting(paramString);
    SwingUtilities.invokeLater(paramRunnable);
  }
  
  private void clearResultPanel()
  {
    this.gResult.setHead(new Object[0]);
    this.gResult.clear();
    if (this.gridFormat) {
      this.gResult.fireTableChanged(null);
    } else {
      showResultInText();
    }
  }
  
  public void setWaiting(String paramString)
  {
    this.busyText = paramString;
    if (this.busyText == null)
    {
      if ((this.fMain instanceof Frame)) {
        ((Frame)this.fMain).setCursor(this.fMainCursor);
      } else {
        ((Component)this.fMain).setCursor(this.fMainCursor);
      }
      this.txtCommand.setCursor(this.txtCommandCursor);
      this.txtResult.setCursor(this.txtResultCursor);
    }
    else
    {
      if (this.fMainCursor == null)
      {
        this.fMainCursor = ((this.fMain instanceof Frame) ? ((Frame)this.fMain).getCursor() : ((Component)this.fMain).getCursor());
        this.txtCommandCursor = this.txtCommand.getCursor();
        this.txtResultCursor = this.txtResult.getCursor();
      }
      if ((this.fMain instanceof Frame)) {
        ((Frame)this.fMain).setCursor(this.waitCursor);
      } else {
        ((Component)this.fMain).setCursor(this.waitCursor);
      }
      this.txtCommand.setCursor(this.waitCursor);
      this.txtResult.setCursor(this.waitCursor);
    }
    setStatusLine(this.busyText, this.busyText == null ? this.gResult.getRowCount() : 0);
  }
  
  public void start()
  {
    if (this.buttonUpdaterThread == null) {
      this.buttonUpdaterThread = new Thread(this.buttonUpdater);
    }
    this.buttonUpdaterThread.start();
  }
  
  public void stop()
  {
    System.err.println("Stopping");
    Thread localThread = this.buttonUpdaterThread;
    if (localThread != null) {
      localThread.setContextClassLoader(null);
    }
    this.buttonUpdaterThread = null;
  }
  
  protected void executeCurrentSQL()
  {
    if (this.txtCommand.getText().length() < 1)
    {
      CommonSwing.errorMessage("No SQL to execute");
      return;
    }
    backgroundIt(new StatementExecRunnable(), "Executing SQL");
  }
  
  private void executeSQL()
  {
    String[] arrayOfString = new String[1];
    String str1 = null;
    try
    {
      this.lTime = System.currentTimeMillis();
      str1 = this.sqlScriptBuffer == null ? this.txtCommand.getText() : this.sqlScriptBuffer;
      this.sStatement.execute(str1);
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
          String str3 = localThrowable.getMessage();
          arrayOfString[0] = str3;
          this.gResult.addRow(arrayOfString);
        }
      }
      else
      {
        arrayOfString[0] = "update count";
        this.gResult.setHead(arrayOfString);
        arrayOfString[0] = ("" + i);
        this.gResult.addRow(arrayOfString);
      }
      this.lTime = (System.currentTimeMillis() - this.lTime);
      if (this.sqlScriptBuffer == null)
      {
        addToRecent(str1);
        this.txtCommand.setEnabled(true);
      }
      else
      {
        clear();
      }
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
      CommonSwing.errorMessage(localSQLException);
      return;
    }
    if (this.autoRefresh)
    {
      setStatusLine("Refreshing object tree", 0);
      String str2 = str1.toUpperCase(Locale.ENGLISH);
      if ((str2.indexOf("ALTER") > -1) || (str2.indexOf("DROP") > -1) || (str2.indexOf("CREATE") > -1)) {
        directRefreshTree();
      }
    }
  }
  
  private void updateResult()
  {
    if (this.gridFormat)
    {
      if (this.bHelp)
      {
        this.pResult.removeAll();
        this.pResult.add(this.gScrollPane, "Center");
        this.pResult.doLayout();
        this.gResult.fireTableChanged(null);
        this.pResult.repaint();
        this.bHelp = false;
      }
    }
    else {
      showResultInText();
    }
    this.txtCommand.selectAll();
    this.txtCommand.requestFocus();
  }
  
  private void formatResultSet(ResultSet paramResultSet)
  {
    Object localObject;
    if (paramResultSet == null)
    {
      localObject = new String[1];
      localObject[0] = "Result";
      this.gResult.setHead((Object[])localObject);
      localObject[0] = "(empty)";
      this.gResult.addRow((Object[])localObject);
      return;
    }
    try
    {
      localObject = paramResultSet.getMetaData();
      int i = ((ResultSetMetaData)localObject).getColumnCount();
      Object[] arrayOfObject = new Object[i];
      boolean[] arrayOfBoolean = new boolean[i];
      for (int j = 1; j <= i; j++)
      {
        arrayOfObject[(j - 1)] = ((ResultSetMetaData)localObject).getColumnLabel(j);
        arrayOfBoolean[(j - 1)] = (((ResultSetMetaData)localObject).getColumnType(j) == 12 ? 1 : false);
      }
      this.gResult.setHead(arrayOfObject);
      while (paramResultSet.next())
      {
        for (j = 1; j <= i; j++) {
          try
          {
            arrayOfObject[(j - 1)] = paramResultSet.getObject(j);
            if (paramResultSet.wasNull()) {
              arrayOfObject[(j - 1)] = (arrayOfBoolean[(j - 1)] != 0 ? "[null]" : null);
            }
          }
          catch (SQLException localSQLException2) {}
        }
        this.gResult.addRow(arrayOfObject);
      }
      paramResultSet.close();
    }
    catch (SQLException localSQLException1)
    {
      CommonSwing.errorMessage(localSQLException1);
    }
  }
  
  private void testPerformance()
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
          arrayOfString[0] = ("" + l2);
          arrayOfString[1] = ("" + j);
          arrayOfString[3] = "";
        }
        catch (SQLException localSQLException)
        {
          String tmp310_307 = "n/a";
          arrayOfString[1] = tmp310_307;
          arrayOfString[0] = tmp310_307;
          arrayOfString[3] = localSQLException.toString();
          CommonSwing.errorMessage(localSQLException);
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
  }
  
  private void showResultInText()
  {
    Object[] arrayOfObject1 = this.gResult.getHead();
    int i = arrayOfObject1.length;
    int[] arrayOfInt = new int[i];
    Vector localVector = this.gResult.getData();
    int j = localVector.size();
    for (int k = 0; k < i; k++) {
      arrayOfInt[k] = arrayOfObject1[k].toString().length();
    }
    Object[] arrayOfObject2;
    for (k = 0; k < j; k++)
    {
      arrayOfObject2 = (Object[])localVector.elementAt(k);
      for (m = 0; m < i; m++)
      {
        String str1 = arrayOfObject2[m] == null ? "" : arrayOfObject2[m].toString();
        int i1 = str1.length();
        if (i1 > arrayOfInt[m]) {
          arrayOfInt[m] = i1;
        }
      }
    }
    StringBuffer localStringBuffer = new StringBuffer();
    int n;
    for (int m = 0; m < i; m++)
    {
      localStringBuffer.append(arrayOfObject1[m]);
      for (n = arrayOfObject1[m].toString().length(); n <= arrayOfInt[m]; n++) {
        localStringBuffer.append(' ');
      }
    }
    localStringBuffer.append(field_1676);
    for (m = 0; m < i; m++)
    {
      for (n = 0; n < arrayOfInt[m]; n++) {
        localStringBuffer.append('-');
      }
      localStringBuffer.append(' ');
    }
    localStringBuffer.append(field_1676);
    for (m = 0; m < j; m++)
    {
      arrayOfObject2 = (Object[])localVector.elementAt(m);
      for (n = 0; n < i; n++)
      {
        String str2 = arrayOfObject2[n] == null ? "" : arrayOfObject2[n].toString();
        localStringBuffer.append(str2);
        for (int i2 = str2.length(); i2 <= arrayOfInt[n]; i2++) {
          localStringBuffer.append(' ');
        }
      }
      localStringBuffer.append(field_1676);
    }
    this.txtResult.setText(localStringBuffer.toString());
  }
  
  private void addToRecent(String paramString)
  {
    for (int i = 0; i < iMaxRecent; i++) {
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
    JMenuItem localJMenuItem = new JMenuItem(paramString);
    localJMenuItem.setActionCommand("#" + this.iRecent);
    localJMenuItem.addActionListener(this);
    this.mRecent.insert(localJMenuItem, this.iRecent);
    this.iRecent = ((this.iRecent + 1) % iMaxRecent);
  }
  
  public final void mouseClicked(MouseEvent paramMouseEvent) {}
  
  public final void mouseEntered(MouseEvent paramMouseEvent) {}
  
  public final void mouseExited(MouseEvent paramMouseEvent) {}
  
  public final void mousePressed(MouseEvent paramMouseEvent)
  {
    if (this.alreadyHandled == paramMouseEvent) {
      return;
    }
    handlePopup(paramMouseEvent);
    this.alreadyHandled = paramMouseEvent;
  }
  
  public final void mouseReleased(MouseEvent paramMouseEvent)
  {
    if (this.alreadyHandled == paramMouseEvent) {
      return;
    }
    handlePopup(paramMouseEvent);
    this.alreadyHandled = paramMouseEvent;
  }
  
  public final void handlePopup(MouseEvent paramMouseEvent)
  {
    if (!paramMouseEvent.isPopupTrigger()) {
      return;
    }
    Object localObject = paramMouseEvent.getSource();
    if (!(localObject instanceof JTree)) {
      return;
    }
    JTree localJTree = (JTree)localObject;
    TreePath localTreePath = localJTree.getPathForLocation(paramMouseEvent.getX(), paramMouseEvent.getY());
    if (localTreePath == null) {
      return;
    }
    JPopupMenu localJPopupMenu = new JPopupMenu();
    String[] arrayOfString = { "Select", "Delete", "Update", "Insert" };
    for (int i = 0; i < arrayOfString.length; i++)
    {
      PopupListener localPopupListener = new PopupListener(arrayOfString[i], localTreePath);
      String str = localPopupListener.toString();
      if (str == null) {
        return;
      }
      if (str.length() > 40) {
        str = str.substring(0, 40) + "...";
      }
      JMenuItem localJMenuItem = new JMenuItem(str);
      localJMenuItem.addActionListener(localPopupListener);
      localJPopupMenu.add(localJMenuItem);
    }
    localJPopupMenu.show(paramMouseEvent.getComponent(), paramMouseEvent.getX(), paramMouseEvent.getY());
  }
  
  private String quoteTableName(String paramString)
  {
    int i = paramString.indexOf(".");
    if (i < 0)
    {
      int j = paramString.indexOf(" (");
      if (j >= 0) {
        paramString = paramString.substring(0, j);
      }
      return quoteObjectName(paramString);
    }
    String str1 = paramString.substring(0, i);
    String str2 = paramString.substring(i + 1);
    int k = str2.indexOf("  (");
    if (k >= 0) {
      str2 = str2.substring(0, k);
    }
    return quoteObjectName(str1) + '.' + quoteObjectName(str2);
  }
  
  private String quoteObjectName(String paramString)
  {
    return "\"" + paramString + "\"";
  }
  
  private void initGUI()
  {
    JPanel localJPanel = new JPanel();
    this.pResult = new JPanel();
    this.nsSplitPane = new JSplitPane(0, localJPanel, this.pResult);
    this.nsSplitPane.setOneTouchExpandable(true);
    localJPanel.setLayout(new BorderLayout());
    this.pResult.setLayout(new BorderLayout());
    Font localFont = new Font("Dialog", 0, 12);
    this.txtCommand = new JTextArea(7, 40);
    this.txtCommand.setMargin(new Insets(5, 5, 5, 5));
    this.txtCommand.addKeyListener(this);
    this.txtCommandScroll = new JScrollPane(this.txtCommand);
    this.txtResult = new JTextArea(25, 40);
    this.txtResult.setMargin(new Insets(5, 5, 5, 5));
    this.txtResultScroll = new JScrollPane(this.txtResult);
    this.txtCommand.setFont(localFont);
    this.txtResult.setFont(new Font("Courier", 0, 12));
    localJPanel.add(this.txtCommandScroll, "Center");
    this.gResult = new GridSwing();
    TableSorter localTableSorter = new TableSorter(this.gResult);
    this.tableModel = localTableSorter;
    this.gResultTable = new JTable(localTableSorter);
    localTableSorter.setTableHeader(this.gResultTable.getTableHeader());
    this.gScrollPane = new JScrollPane(this.gResultTable);
    this.gResultTable.setAutoResizeMode(0);
    this.gResult.setJTable(this.gResultTable);
    this.pResult.add(this.gScrollPane, "Center");
    this.rootNode = new DefaultMutableTreeNode("Connection");
    this.treeModel = new DefaultTreeModel(this.rootNode);
    this.tTree = new JTree(this.treeModel);
    this.tScrollPane = new JScrollPane(this.tTree);
    this.tTree.addMouseListener(this);
    this.tScrollPane.setPreferredSize(new Dimension(200, 400));
    this.tScrollPane.setMinimumSize(new Dimension(70, 100));
    this.txtCommandScroll.setPreferredSize(new Dimension(560, 100));
    this.txtCommandScroll.setMinimumSize(new Dimension(180, 100));
    this.gScrollPane.setPreferredSize(new Dimension(460, 300));
    this.ewSplitPane = new JSplitPane(1, this.tScrollPane, this.nsSplitPane);
    this.ewSplitPane.setOneTouchExpandable(true);
    this.fMain.getContentPane().add(this.ewSplitPane, "Center");
    this.jStatusLine = new JLabel();
    iReadyStatus = new JButton(new ImageIcon(CommonSwing.getIcon("StatusReady")));
    iReadyStatus.setSelectedIcon(new ImageIcon(CommonSwing.getIcon("StatusRunning")));
    this.pStatus = new JPanel();
    this.pStatus.setLayout(new BorderLayout());
    this.pStatus.add(iReadyStatus, "West");
    this.pStatus.add(this.jStatusLine, "Center");
    this.fMain.getContentPane().add(this.pStatus, "South");
    doLayout();
    if ((this.fMain instanceof Window)) {
      ((Window)this.fMain).pack();
    } else {
      ((Container)this.fMain).validate();
    }
  }
  
  private DefaultMutableTreeNode makeNode(Object paramObject, MutableTreeNode paramMutableTreeNode)
  {
    DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode(paramObject);
    if (paramMutableTreeNode != null) {
      this.treeModel.insertNodeInto(localDefaultMutableTreeNode, paramMutableTreeNode, paramMutableTreeNode.getChildCount());
    }
    return localDefaultMutableTreeNode;
  }
  
  protected void refreshTree()
  {
    backgroundIt(this.treeRefreshRunnable, "Refreshing object tree");
  }
  
  protected void directRefreshTree()
  {
    DecimalFormat localDecimalFormat = new DecimalFormat(" ( ####,###,####,##0 )");
    while (this.treeModel.getChildCount(this.rootNode) > 0)
    {
      localObject1 = (DefaultMutableTreeNode)this.treeModel.getChild(this.rootNode, 0);
      this.treeModel.removeNodeFromParent((MutableTreeNode)localObject1);
      ((DefaultMutableTreeNode)localObject1).removeAllChildren();
      ((DefaultMutableTreeNode)localObject1).removeFromParent();
    }
    this.treeModel.nodeStructureChanged(this.rootNode);
    this.treeModel.reload();
    this.tScrollPane.repaint();
    Object localObject1 = null;
    try
    {
      this.rootNode.setUserObject(this.dMeta.getURL());
      localObject1 = this.dMeta.getTables(null, null, null, this.showSys ? usertables : nonSystables);
      Vector localVector1 = new Vector();
      Vector localVector2 = new Vector();
      Vector localVector3 = new Vector();
      String str1;
      while (((ResultSet)localObject1).next())
      {
        str1 = ((ResultSet)localObject1).getString(2);
        if (((this.showSys) || (!this.isOracle) || (!oracleSysUsers.contains(str1))) && ((this.schemaFilter == null) || (str1.equals(this.schemaFilter))))
        {
          localVector2.addElement(str1);
          localVector1.addElement(((ResultSet)localObject1).getString(3));
          localVector3.addElement(((ResultSet)localObject1).getString(5));
        }
      }
      ((ResultSet)localObject1).close();
      localObject1 = null;
      int[] arrayOfInt = new int[localVector1.size()];
      try
      {
        arrayOfInt = getRowCounts(localVector1, localVector2);
      }
      catch (Exception localException)
      {
        CommonSwing.errorMessage(localException);
      }
      for (int i = 0; i < localVector1.size(); i++)
      {
        ResultSet localResultSet = null;
        String str2;
        Object localObject2;
        Object localObject3;
        Object localObject4;
        Object localObject5;
        try
        {
          str2 = (String)localVector1.elementAt(i);
          if ((this.isOracle) && (str2.startsWith("BIN$")))
          {
            if (localResultSet == null) {
              continue;
            }
            try
            {
              localResultSet.close();
            }
            catch (SQLException localSQLException4) {}
          }
          str1 = (String)localVector2.elementAt(i);
          String str3 = "";
          if ((str1 != null) && (this.showSchemas)) {
            str3 = str1 + '.';
          }
          localObject2 = this.displayRowCounts ? " " + localDecimalFormat.format(arrayOfInt[i]) : "";
          localObject3 = str3 + str2 + (String)localObject2;
          DefaultMutableTreeNode localDefaultMutableTreeNode2 = makeNode(localObject3, this.rootNode);
          localResultSet = this.dMeta.getColumns(null, str1, str2, null);
          if ((str1 != null) && (!str1.trim().equals(""))) {
            makeNode(str1, localDefaultMutableTreeNode2);
          }
          localObject4 = (String)localVector3.elementAt(i);
          if ((localObject4 != null) && (!((String)localObject4).trim().equals(""))) {
            makeNode(localObject4, localDefaultMutableTreeNode2);
          }
          while (localResultSet.next())
          {
            String str4 = localResultSet.getString(4);
            localObject5 = makeNode(str4, localDefaultMutableTreeNode2);
            String str5 = localResultSet.getString(6);
            makeNode("Type: " + str5, (MutableTreeNode)localObject5);
            boolean bool2 = localResultSet.getInt(11) != 0;
            makeNode("Nullable: " + bool2, (MutableTreeNode)localObject5);
          }
          if (localResultSet != null) {
            try
            {
              localResultSet.close();
            }
            catch (SQLException localSQLException5) {}
          }
          localDefaultMutableTreeNode3 = makeNode("Indices", localDefaultMutableTreeNode2);
        }
        finally
        {
          if (localResultSet != null) {
            try
            {
              localResultSet.close();
            }
            catch (SQLException localSQLException7) {}
          }
        }
        DefaultMutableTreeNode localDefaultMutableTreeNode3;
        if (this.showIndexDetails)
        {
          localObject2 = null;
          try
          {
            localObject2 = this.dMeta.getIndexInfo(null, str1, str2, false, false);
            localObject3 = null;
            localObject4 = null;
            while (((ResultSet)localObject2).next())
            {
              boolean bool1 = ((ResultSet)localObject2).getBoolean(4);
              localObject5 = ((ResultSet)localObject2).getString(6);
              if ((localObject3 == null) || (!((String)localObject3).equals(localObject5)))
              {
                localObject4 = makeNode(localObject5, localDefaultMutableTreeNode3);
                makeNode("Unique: " + (!bool1), (MutableTreeNode)localObject4);
                localObject3 = localObject5;
              }
              makeNode(((ResultSet)localObject2).getString(9), (MutableTreeNode)localObject4);
            }
          }
          catch (SQLException localSQLException6)
          {
            if ((localSQLException6.getMessage() == null) || ((!localSQLException6.getMessage().startsWith("ORA-25191:")) && (!localSQLException6.getMessage().startsWith("ORA-01702:")) && (!localSQLException6.getMessage().startsWith("ORA-01031:")))) {
              throw localSQLException6;
            }
          }
          finally
          {
            if (localObject2 != null)
            {
              ((ResultSet)localObject2).close();
              localObject2 = null;
            }
          }
        }
      }
      DefaultMutableTreeNode localDefaultMutableTreeNode1 = makeNode("Properties", this.rootNode);
      makeNode("User: " + this.dMeta.getUserName(), localDefaultMutableTreeNode1);
      makeNode("ReadOnly: " + this.cConn.isReadOnly(), localDefaultMutableTreeNode1);
      makeNode("AutoCommit: " + this.cConn.getAutoCommit(), localDefaultMutableTreeNode1);
      makeNode("Driver: " + this.dMeta.getDriverName(), localDefaultMutableTreeNode1);
      makeNode("Product: " + this.dMeta.getDatabaseProductName(), localDefaultMutableTreeNode1);
      makeNode("Version: " + this.dMeta.getDatabaseProductVersion(), localDefaultMutableTreeNode1);
      if (localObject1 != null) {
        try
        {
          ((ResultSet)localObject1).close();
        }
        catch (SQLException localSQLException1) {}
      }
      this.treeModel.nodeStructureChanged(this.rootNode);
    }
    catch (SQLException localSQLException2)
    {
      localDefaultMutableTreeNode1 = makeNode("Error getting metadata:", this.rootNode);
      makeNode(localSQLException2.getMessage(), localDefaultMutableTreeNode1);
      makeNode(localSQLException2.getSQLState(), localDefaultMutableTreeNode1);
      CommonSwing.errorMessage(localSQLException2);
    }
    finally
    {
      if (localObject1 != null) {
        try
        {
          ((ResultSet)localObject1).close();
        }
        catch (SQLException localSQLException8) {}
      }
    }
    this.treeModel.reload();
    this.tScrollPane.repaint();
    updateSchemaList();
  }
  
  void setStatusLine(String paramString, int paramInt)
  {
    iReadyStatus.setSelected(paramString != null);
    if (paramString == null)
    {
      String str = "";
      if (this.schemaFilter != null) {
        str = " /  Tree showing objects in schema '" + this.schemaFilter + "'";
      }
      if (paramInt > 1) {
        str = str + " / " + paramInt + " rows retrieved";
      }
      this.jStatusLine.setText("  " + READY_STATUS + str);
    }
    else
    {
      this.jStatusLine.setText("  " + paramString + "...");
    }
  }
  
  protected int[] getRowCounts(Vector paramVector1, Vector paramVector2)
    throws Exception
  {
    if (!this.displayRowCounts) {
      return null;
    }
    String str1 = "SELECT COUNT(*) FROM ";
    int[] arrayOfInt = new int[paramVector1.size()];
    try
    {
      Statement localStatement = this.rowConn.createStatement();
      for (int i = 0; i < paramVector1.size(); i++) {
        try
        {
          String str3 = (String)paramVector2.elementAt(i);
          str3 = "\"" + str3 + "\".\"";
          String str2 = str3 + (String)paramVector1.elementAt(i) + "\"";
          ResultSet localResultSet = localStatement.executeQuery(str1 + str2);
          while (localResultSet.next()) {
            arrayOfInt[i] = localResultSet.getInt(1);
          }
        }
        catch (Exception localException2)
        {
          System.err.println("Unable to get row count for table " + paramVector2.elementAt(i) + '.' + paramVector1.elementAt(i) + ".  Using value '0': " + localException2);
        }
      }
    }
    catch (Exception localException1)
    {
      CommonSwing.errorMessage(localException1);
    }
    return arrayOfInt;
  }
  
  protected JToolBar createToolBar()
  {
    JToolBar localJToolBar = new JToolBar();
    localJToolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
    this.jbuttonClear = new JButton("Clear SQL", new ImageIcon(CommonSwing.getIcon("Clear")));
    this.jbuttonClear.putClientProperty("is3DEnabled", Boolean.TRUE);
    this.tipMap.put(this.jbuttonClear, "Clear SQL");
    this.jbuttonClear.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        if ((DatabaseManagerSwing.this.sqlScriptBuffer == null) && (DatabaseManagerSwing.this.txtCommand.getText().length() < 1))
        {
          CommonSwing.errorMessage("No SQL to clear");
          return;
        }
        DatabaseManagerSwing.this.clear();
      }
    });
    this.jbuttonExecute = new JButton("Execute SQL", new ImageIcon(CommonSwing.getIcon("Execute")));
    this.tipMap.put(this.jbuttonExecute, "Execute SQL");
    this.jbuttonExecute.putClientProperty("is3DEnabled", Boolean.TRUE);
    this.jbuttonExecute.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        DatabaseManagerSwing.this.executeCurrentSQL();
      }
    });
    localJToolBar.addSeparator();
    localJToolBar.add(this.jbuttonClear);
    localJToolBar.addSeparator();
    localJToolBar.add(this.jbuttonExecute);
    localJToolBar.addSeparator();
    this.jbuttonClear.setAlignmentY(0.5F);
    this.jbuttonClear.setAlignmentX(0.5F);
    this.jbuttonExecute.setAlignmentY(0.5F);
    this.jbuttonExecute.setAlignmentX(0.5F);
    return localJToolBar;
  }
  
  void updateAutoCommitBox()
  {
    try
    {
      this.boxAutoCommit.setSelected(this.cConn.getAutoCommit());
    }
    catch (SQLException localSQLException)
    {
      CommonSwing.errorMessage(localSQLException);
    }
  }
  
  private void setLF(String paramString)
  {
    if ((this.currentLAF != null) && (this.currentLAF == paramString)) {
      return;
    }
    if ((this.pResult != null) && (this.gridFormat)) {
      this.pResult.removeAll();
    }
    CommonSwing.setSwingLAF((Component)this.fMain, paramString);
    if ((this.pResult != null) && (this.gridFormat)) {
      setResultsInGrid();
    }
    this.currentLAF = paramString;
    if (this.currentLAF.equals(CommonSwing.Native)) {
      this.rbNativeLF.setSelected(true);
    } else if (this.currentLAF.equals(CommonSwing.Java)) {
      this.rbJavaLF.setSelected(true);
    } else if (this.currentLAF.equals(CommonSwing.Motif)) {
      this.rbMotifLF.setSelected(true);
    }
  }
  
  void resetTooltips()
  {
    Iterator localIterator = this.tipMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      JComponent localJComponent = (JComponent)localIterator.next();
      localJComponent.setToolTipText(this.showTooltips ? (String)this.tipMap.get(localJComponent) : (String)null);
    }
  }
  
  private void updateSchemaList()
  {
    ButtonGroup localButtonGroup = new ButtonGroup();
    ArrayList localArrayList = new ArrayList();
    ResultSet localResultSet = null;
    try
    {
      localResultSet = this.dMeta.getSchemas();
      if (localResultSet == null) {
        throw new SQLException("Failed to get metadata from database");
      }
      while (localResultSet.next()) {
        localArrayList.add(localResultSet.getString(1));
      }
      if (localResultSet != null) {
        try
        {
          localResultSet.close();
        }
        catch (SQLException localSQLException1) {}
      }
      this.mnuSchemas.removeAll();
    }
    catch (SQLException localSQLException2)
    {
      CommonSwing.errorMessage(localSQLException2);
    }
    finally
    {
      if (localResultSet != null) {
        try
        {
          localResultSet.close();
        }
        catch (SQLException localSQLException4) {}
      }
    }
    this.rbAllSchemas.setSelected(this.schemaFilter == null);
    localButtonGroup.add(this.rbAllSchemas);
    this.mnuSchemas.add(this.rbAllSchemas);
    for (int i = 0; i < localArrayList.size(); i++)
    {
      String str = (String)localArrayList.get(i);
      JRadioButtonMenuItem localJRadioButtonMenuItem = new JRadioButtonMenuItem(str);
      localButtonGroup.add(localJRadioButtonMenuItem);
      this.mnuSchemas.add(localJRadioButtonMenuItem);
      localJRadioButtonMenuItem.setSelected((this.schemaFilter != null) && (this.schemaFilter.equals(str)));
      localJRadioButtonMenuItem.addActionListener(this.schemaListListener);
      localJRadioButtonMenuItem.setEnabled(localArrayList.size() > 1);
    }
    this.mnuSchemas.addSeparator();
    this.mnuSchemas.add(this.mitemUpdateSchemas);
  }
  
  static
  {
    try
    {
      Class localClass = Class.forName("sun.security.action.GetPropertyAction");
      Constructor localConstructor = localClass.getConstructor(new Class[] { String.class });
      PrivilegedAction localPrivilegedAction = (PrivilegedAction)localConstructor.newInstance(new Object[] { "user.home" });
      homedir = (String)AccessController.doPrivileged(localPrivilegedAction);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + localIllegalAccessException.getMessage() + ')');
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + localNoSuchMethodException.getMessage() + ')');
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + localClassNotFoundException.getMessage() + ')');
    }
    catch (InstantiationException localInstantiationException)
    {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + localInstantiationException.getMessage() + ')');
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + localInvocationTargetException.getMessage() + ')');
    }
    catch (AccessControlException localAccessControlException)
    {
      System.err.println("Failed to get home directory.\nTherefore not retrieving/storing user preferences.\n(" + localAccessControlException.getMessage() + ')');
    }
    DEFAULT_RCFILE = homedir + "/dbmanager.rc";
    TT_AVAILABLE = false;
    try
    {
      Class.forName(DatabaseManagerSwing.class.getPackage().getName() + ".Transfer");
      TT_AVAILABLE = true;
    }
    catch (Throwable localThrowable) {}
    HELP_TEXT = "See the HSQLDB Utilities Guide, forums and mailing lists \nat http://hsqldb.org.\n\nPlease paste the following version identifier with any\nproblem reports or help requests:  $Revision: 4201 $" + (TT_AVAILABLE ? "" : "\n\nTransferTool classes are not in CLASSPATH.\nTo enable the Tools menu, add 'transfer.jar' to your class path.");
    ABOUT_TEXT = "$Revision: 4201 $ of DatabaseManagerSwing\n\nCopyright (c) 2001-2010, The HSQL Development Group.\nhttp://hsqldb.org  (Utilities Guide available at this site).\n\n\nYou may use and redistribute according to the HSQLDB\nlicense documented in the source code and at the web\nsite above." + (TT_AVAILABLE ? "\n\nTransferTool options are available." : "");
    field_1676 = System.getProperty("line.separator");
    iMaxRecent = 24;
    READY_STATUS = "Ready";
    defDriver = "org.hsqldb.jdbcDriver";
    defURL = "jdbc:hsqldb:mem:.";
    defUser = "SA";
    defPassword = "";
    usertables = new String[] { "TABLE", "GLOBAL TEMPORARY", "VIEW", "SYSTEM TABLE" };
    nonSystables = new String[] { "TABLE", "GLOBAL TEMPORARY", "VIEW" };
    oracleSysUsers = new HashSet();
    oracleSysSchemas = new String[] { "SYS", "SYSTEM", "OUTLN", "DBSNMP", "OUTLN", "MDSYS", "ORDSYS", "ORDPLUGINS", "CTXSYS", "DSSYS", "PERFSTAT", "WKPROXY", "WKSYS", "WMSYS", "XDB", "ANONYMOUS", "ODM", "ODM_MTR", "OLAPSYS", "TRACESVR", "REPADMIN" };
    for (int i = 0; i < oracleSysSchemas.length; i++) {
      oracleSysUsers.add(oracleSysSchemas[i]);
    }
  }
  
  public class DBMPrefs
  {
    public File prefsFile = null;
    boolean autoRefresh = true;
    boolean showRowCounts = false;
    boolean showSysTables = false;
    boolean showSchemas = true;
    boolean resultGrid = true;
    String laf = CommonSwing.Native;
    boolean showTooltips = true;
    
    public DBMPrefs(boolean paramBoolean)
      throws IOException
    {
      if (!paramBoolean)
      {
        if (DatabaseManagerSwing.homedir == null) {
          throw new IOException("Skipping preferences since do not know home dir");
        }
        this.prefsFile = new File(DatabaseManagerSwing.homedir, "dbmprefs.properties");
      }
      load();
    }
    
    public void load()
      throws IOException
    {
      String str;
      if (this.prefsFile == null)
      {
        str = DatabaseManagerSwing.this.getParameter("autoRefresh");
        if (str != null) {
          this.autoRefresh = Boolean.valueOf(str).booleanValue();
        }
        str = DatabaseManagerSwing.this.getParameter("showRowCounts");
        if (str != null) {
          this.showRowCounts = Boolean.valueOf(str).booleanValue();
        }
        str = DatabaseManagerSwing.this.getParameter("showSysTables");
        if (str != null) {
          this.showSysTables = Boolean.valueOf(str).booleanValue();
        }
        str = DatabaseManagerSwing.this.getParameter("showSchemas");
        if (str != null) {
          this.showSchemas = Boolean.valueOf(str).booleanValue();
        }
        str = DatabaseManagerSwing.this.getParameter("resultGrid");
        if (str != null) {
          this.resultGrid = Boolean.valueOf(str).booleanValue();
        }
        str = DatabaseManagerSwing.this.getParameter("laf");
        this.laf = (str == null ? CommonSwing.Native : str);
        str = DatabaseManagerSwing.this.getParameter("showTooltips");
        if (str != null) {
          this.showTooltips = Boolean.valueOf(str).booleanValue();
        }
      }
      else
      {
        if (!this.prefsFile.exists()) {
          throw new IOException("No such file: " + this.prefsFile);
        }
        Properties localProperties = new Properties();
        try
        {
          FileInputStream localFileInputStream = new FileInputStream(this.prefsFile);
          localProperties.load(localFileInputStream);
          localFileInputStream.close();
        }
        catch (IOException localIOException)
        {
          throw new IOException("Failed to read preferences file '" + this.prefsFile + "':  " + localIOException.getMessage());
        }
        str = localProperties.getProperty("autoRefresh");
        if (str != null) {
          this.autoRefresh = Boolean.valueOf(str).booleanValue();
        }
        str = localProperties.getProperty("showRowCounts");
        if (str != null) {
          this.showRowCounts = Boolean.valueOf(str).booleanValue();
        }
        str = localProperties.getProperty("showSysTables");
        if (str != null) {
          this.showSysTables = Boolean.valueOf(str).booleanValue();
        }
        str = localProperties.getProperty("showSchemas");
        if (str != null) {
          this.showSchemas = Boolean.valueOf(str).booleanValue();
        }
        str = localProperties.getProperty("resultGrid");
        if (str != null) {
          this.resultGrid = Boolean.valueOf(str).booleanValue();
        }
        str = localProperties.getProperty("laf");
        this.laf = (str == null ? CommonSwing.Native : str);
        str = localProperties.getProperty("showTooltips");
        if (str != null) {
          this.showTooltips = Boolean.valueOf(str).booleanValue();
        }
      }
    }
    
    public void store()
    {
      if (this.prefsFile == null) {
        return;
      }
      Properties localProperties = new Properties();
      localProperties.setProperty("autoRefresh", this.autoRefresh ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      localProperties.setProperty("showRowCounts", this.showRowCounts ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      localProperties.setProperty("showSysTables", this.showSysTables ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      localProperties.setProperty("showSchemas", this.showSchemas ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      localProperties.setProperty("resultGrid", this.resultGrid ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      localProperties.setProperty("laf", this.laf);
      localProperties.setProperty("showTooltips", this.showTooltips ? DatabaseManagerSwing.tString : DatabaseManagerSwing.fString);
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(this.prefsFile);
        localProperties.store(localFileOutputStream, "DatabaseManagerSwing user preferences");
        localFileOutputStream.flush();
        localFileOutputStream.close();
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Failed to prepare preferences file '" + this.prefsFile + "':  " + localIOException.getMessage());
      }
    }
  }
  
  private class PopupListener
    implements ActionListener
  {
    public static final int DEPTH_URL = 1;
    public static final int DEPTH_TABLE = 2;
    public static final int DEPTH_COLUMN = 3;
    String command;
    TreePath treePath;
    TreePath tablePath;
    TreePath columnPath;
    String table = null;
    String column = null;
    
    PopupListener(String paramString, TreePath paramTreePath)
    {
      this.command = paramString;
      this.treePath = paramTreePath;
    }
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      DatabaseManagerSwing.this.txtCommand.setText(getCommandString());
    }
    
    public String toString()
    {
      return getCommandString();
    }
    
    public String getCommandString()
    {
      int i = this.treePath.getPathCount();
      if (i == 1) {
        return "";
      }
      if (i == 2)
      {
        this.tablePath = this.treePath;
        this.table = this.treePath.getPathComponent(1).toString();
      }
      if (i == 3)
      {
        this.tablePath = this.treePath.getParentPath();
        this.table = this.treePath.getPathComponent(1).toString();
        this.columnPath = this.treePath;
        this.column = this.treePath.getPathComponent(2).toString();
      }
      Object localObject1;
      Object localObject2;
      String str1;
      int j;
      if (this.command.toUpperCase().equals("SELECT"))
      {
        localObject1 = "SELECT * FROM " + DatabaseManagerSwing.this.quoteTableName(this.table);
        if (this.column != null)
        {
          localObject2 = (DefaultMutableTreeNode)this.treePath.getLastPathComponent();
          str1 = null;
          if (((DefaultMutableTreeNode)localObject2).getChildCount() > 0)
          {
            str1 = ((DefaultMutableTreeNode)localObject2).getFirstChild().toString();
            j = str1.indexOf("CHAR") >= 0 ? 1 : 0;
            localObject1 = (String)localObject1 + " WHERE " + DatabaseManagerSwing.this.quoteObjectName(this.column);
            if (j != 0) {
              localObject1 = (String)localObject1 + " LIKE '%%'";
            } else {
              localObject1 = (String)localObject1 + " = ";
            }
          }
        }
        return localObject1;
      }
      if (this.command.toUpperCase().equals("UPDATE"))
      {
        localObject1 = "UPDATE " + DatabaseManagerSwing.this.quoteTableName(this.table) + " SET ";
        if (this.column != null) {
          localObject1 = (String)localObject1 + DatabaseManagerSwing.this.quoteObjectName(this.column) + " = ";
        }
        return localObject1;
      }
      if (this.command.toUpperCase().equals("DELETE"))
      {
        localObject1 = "DELETE FROM " + DatabaseManagerSwing.this.quoteTableName(this.table);
        if (this.column != null)
        {
          localObject2 = (DefaultMutableTreeNode)this.treePath.getLastPathComponent();
          str1 = null;
          if (((DefaultMutableTreeNode)localObject2).getChildCount() > 0)
          {
            str1 = ((DefaultMutableTreeNode)localObject2).getFirstChild().toString();
            j = str1.indexOf("CHAR") >= 0 ? 1 : 0;
            localObject1 = (String)localObject1 + " WHERE " + DatabaseManagerSwing.this.quoteObjectName(this.column);
            if (j != 0) {
              localObject1 = (String)localObject1 + " LIKE '%%'";
            } else {
              localObject1 = (String)localObject1 + " = ";
            }
          }
        }
        return localObject1;
      }
      if (this.command.toUpperCase().equals("INSERT"))
      {
        str1 = "";
        String str2 = " ";
        String str3 = "";
        String str4 = "";
        if (this.tablePath == null) {
          return null;
        }
        localObject1 = (TreeNode)this.tablePath.getLastPathComponent();
        localObject2 = ((TreeNode)localObject1).children();
        while (((Enumeration)localObject2).hasMoreElements())
        {
          Object localObject3 = ((Enumeration)localObject2).nextElement();
          if (!localObject3.toString().equals("Indices"))
          {
            DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)localObject3;
            String str5 = null;
            if (localDefaultMutableTreeNode.getChildCount() != 0)
            {
              str5 = localDefaultMutableTreeNode.getFirstChild().toString();
              if (str5.indexOf("CHAR") >= 0) {
                str4 = "''";
              } else {
                str4 = "";
              }
              str1 = str1 + str3 + DatabaseManagerSwing.this.quoteObjectName(localObject3.toString());
              str2 = str2 + str3 + str4;
              str3 = ", ";
            }
          }
        }
        return "INSERT INTO " + DatabaseManagerSwing.this.quoteTableName(this.table) + "\n( " + str1 + " )\nVALUES (" + str2 + ")";
      }
      return "Got here in error " + this.command + ".  Should never happen";
    }
  }
  
  protected class StatementExecRunnable
    implements Runnable
  {
    protected StatementExecRunnable() {}
    
    public void run()
    {
      DatabaseManagerSwing.this.gResult.clear();
      try
      {
        if (DatabaseManagerSwing.this.txtCommand.getText().startsWith("-->>>TEST<<<--")) {
          DatabaseManagerSwing.this.testPerformance();
        } else {
          DatabaseManagerSwing.this.executeSQL();
        }
        DatabaseManagerSwing.this.updateResult();
        DatabaseManagerSwing.this.displayResults();
        DatabaseManagerSwing.this.updateAutoCommitBox();
      }
      catch (RuntimeException localRuntimeException)
      {
        CommonSwing.errorMessage(localRuntimeException);
        throw localRuntimeException;
      }
      finally
      {
        DatabaseManagerSwing.this.setWaiting(null);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.util.DatabaseManagerSwing
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */