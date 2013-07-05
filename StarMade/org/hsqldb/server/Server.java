package org.hsqldb.server;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import org.hsqldb.DatabaseManager;
import org.hsqldb.DatabaseURL;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.Set;
import org.hsqldb.lib.StopWatch;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.resources.BundleHandler;
import org.hsqldb.result.Result;

public class Server
  implements HsqlSocketRequestHandler
{
  protected static final int serverBundleHandle = BundleHandler.getBundleHandle("org_hsqldb_Server_messages", null);
  ServerProperties serverProperties;
  HashSet serverConnSet;
  protected String[] dbAlias;
  protected String[] dbType;
  protected String[] dbPath;
  protected HsqlProperties[] dbProps;
  protected int[] dbID;
  protected long[] dbActionSequence;
  HashSet aliasSet = new HashSet();
  protected int maxConnections;
  volatile long actionSequence;
  protected String serverId;
  protected int serverProtocol;
  protected ThreadGroup serverConnectionThreadGroup;
  protected HsqlSocketFactory socketFactory;
  protected ServerSocket socket;
  private Thread serverThread;
  private Throwable serverError;
  private volatile int serverState;
  private volatile boolean isSilent;
  protected volatile boolean isRemoteOpen;
  protected boolean isDaemon;
  private PrintWriter logWriter;
  private PrintWriter errWriter;
  private ServerAcl acl = null;
  private volatile boolean isShuttingDown;

  public Thread getServerThread()
  {
    return this.serverThread;
  }

  public Server()
  {
    this(1);
  }

  protected Server(int paramInt)
  {
    init(paramInt);
  }

  public void checkRunning(boolean paramBoolean)
  {
    printWithThread(new StringBuilder().append("checkRunning(").append(paramBoolean).append(") entered").toString());
    int i = getState();
    int j = ((paramBoolean) && (i != 1)) || ((!paramBoolean) && (i != 16)) ? 1 : 0;
    if (j != 0)
    {
      String str = new StringBuilder().append("server is ").append(paramBoolean ? "not " : "").append("running").toString();
      throw Error.error(458, str);
    }
    printWithThread(new StringBuilder().append("checkRunning(").append(paramBoolean).append(") exited").toString());
  }

  public synchronized void signalCloseAllServerConnections()
  {
    printWithThread("signalCloseAllServerConnections() entered");
    ServerConnection[] arrayOfServerConnection;
    synchronized (this.serverConnSet)
    {
      arrayOfServerConnection = new ServerConnection[this.serverConnSet.size()];
      this.serverConnSet.toArray(arrayOfServerConnection);
    }
    for (int i = 0; i < arrayOfServerConnection.length; i++)
    {
      ServerConnection localServerConnection = arrayOfServerConnection[i];
      printWithThread(new StringBuilder().append("Closing ").append(localServerConnection).toString());
      localServerConnection.signalClose();
    }
    printWithThread("signalCloseAllServerConnections() exited");
  }

  protected void finalize()
    throws Throwable
  {
    if (this.serverThread != null)
      releaseServerSocket();
  }

  public String getAddress()
  {
    return this.socket == null ? this.serverProperties.getProperty("server.address") : this.socket.getInetAddress().getHostAddress();
  }

  public String getDatabaseName(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
      return this.serverProperties.getProperty(new StringBuilder().append("server.dbname.").append(paramInt).toString());
    if (getState() == 1)
      return (this.dbAlias == null) || (paramInt < 0) || (paramInt >= this.dbAlias.length) ? null : this.dbAlias[paramInt];
    return null;
  }

  public String getDatabasePath(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
      return this.serverProperties.getProperty(new StringBuilder().append("server.database.").append(paramInt).toString());
    if (getState() == 1)
      return (this.dbPath == null) || (paramInt < 0) || (paramInt >= this.dbPath.length) ? null : this.dbPath[paramInt];
    return null;
  }

  public String getDatabaseType(int paramInt)
  {
    return (this.dbType == null) || (paramInt < 0) || (paramInt >= this.dbType.length) ? null : this.dbType[paramInt];
  }

  public String getDefaultWebPage()
  {
    return "[IGNORED]";
  }

  public String getHelpString()
  {
    return BundleHandler.getString(serverBundleHandle, "server.help");
  }

  public PrintWriter getErrWriter()
  {
    return this.errWriter;
  }

  public PrintWriter getLogWriter()
  {
    return this.logWriter;
  }

  public int getPort()
  {
    return this.serverProperties.getIntegerProperty("server.port", ServerConfiguration.getDefaultPort(this.serverProtocol, isTls()));
  }

  public String getProductName()
  {
    return "HSQLDB server";
  }

  public String getProductVersion()
  {
    return "2.2.9";
  }

  public String getProtocol()
  {
    return isTls() ? "HSQLS" : "HSQL";
  }

  public Throwable getServerError()
  {
    return this.serverError;
  }

  public String getServerId()
  {
    return this.serverId;
  }

  public int getState()
  {
    return this.serverState;
  }

  public String getStateDescriptor()
  {
    Throwable localThrowable = getServerError();
    String str;
    switch (this.serverState)
    {
    case 16:
      str = "SHUTDOWN";
      break;
    case 4:
      str = "OPENING";
      break;
    case 8:
      str = "CLOSING";
      break;
    case 1:
      str = "ONLINE";
      break;
    default:
      str = "UNKNOWN";
    }
    return str;
  }

  public String getWebRoot()
  {
    return "[IGNORED]";
  }

  public void handleConnection(Socket paramSocket)
  {
    printWithThread(new StringBuilder().append("handleConnection(").append(paramSocket).append(") entered").toString());
    if (!allowConnection(paramSocket))
    {
      try
      {
        paramSocket.close();
      }
      catch (Exception localException)
      {
      }
      printWithThread("allowConnection(): connection refused");
      printWithThread("handleConnection() exited");
      return;
    }
    if (this.socketFactory != null)
      this.socketFactory.configureSocket(paramSocket);
    Object localObject;
    String str;
    if (this.serverProtocol == 1)
    {
      localObject = new ServerConnection(paramSocket, this);
      str = ((ServerConnection)localObject).getConnectionThreadName();
    }
    else
    {
      localObject = new WebServerConnection(paramSocket, (WebServer)this);
      str = ((WebServerConnection)localObject).getConnectionThreadName();
    }
    Thread localThread = new Thread(this.serverConnectionThreadGroup, (Runnable)localObject, str);
    localThread.start();
    printWithThread("handleConnection() exited");
  }

  public boolean isNoSystemExit()
  {
    return this.serverProperties.isPropertyTrue("server.no_system_exit");
  }

  public boolean isRestartOnShutdown()
  {
    return this.serverProperties.isPropertyTrue("server.restart_on_shutdown");
  }

  public boolean isSilent()
  {
    return this.isSilent;
  }

  public boolean isTls()
  {
    return this.serverProperties.isPropertyTrue("server.tls");
  }

  public boolean isTrace()
  {
    return this.serverProperties.isPropertyTrue("server.trace");
  }

  public boolean putPropertiesFromFile(String paramString)
  {
    return putPropertiesFromFile(paramString, ".properties");
  }

  public boolean putPropertiesFromFile(String paramString1, String paramString2)
  {
    if (getState() != 16)
      throw Error.error(458, "server properties");
    paramString1 = FileUtil.getFileUtil().canonicalOrAbsolutePath(paramString1);
    ServerProperties localServerProperties = ServerConfiguration.getPropertiesFromFile(1, paramString1, paramString2);
    if ((localServerProperties == null) || (localServerProperties.isEmpty()))
      return false;
    printWithThread(new StringBuilder().append("putPropertiesFromFile(): [").append(paramString1).append(".properties]").toString());
    try
    {
      setProperties(localServerProperties);
    }
    catch (Exception localException)
    {
      throw Error.error(localException, 458, 26, new String[] { "Failed to set properties" });
    }
    return true;
  }

  public void putPropertiesFromString(String paramString)
  {
    if (getState() != 16)
      throw Error.error(458);
    if (StringUtil.isEmpty(paramString))
      return;
    printWithThread(new StringBuilder().append("putPropertiesFromString(): [").append(paramString).append("]").toString());
    HsqlProperties localHsqlProperties = HsqlProperties.delimitedArgPairsToProps(paramString, "=", ";", "server");
    try
    {
      setProperties(localHsqlProperties);
    }
    catch (Exception localException)
    {
      throw Error.error(localException, 458, 26, new String[] { "Failed to set properties" });
    }
  }

  public void setAddress(String paramString)
  {
    checkRunning(false);
    if (StringUtil.isEmpty(paramString))
      paramString = "0.0.0.0";
    printWithThread(new StringBuilder().append("setAddress(").append(paramString).append(")").toString());
    this.serverProperties.setProperty("server.address", paramString);
  }

  public void setDatabaseName(int paramInt, String paramString)
  {
    checkRunning(false);
    printWithThread(new StringBuilder().append("setDatabaseName(").append(paramInt).append(",").append(paramString).append(")").toString());
    this.serverProperties.setProperty(new StringBuilder().append("server.dbname.").append(paramInt).toString(), paramString);
  }

  public void setDatabasePath(int paramInt, String paramString)
  {
    checkRunning(false);
    printWithThread(new StringBuilder().append("setDatabasePath(").append(paramInt).append(",").append(paramString).append(")").toString());
    this.serverProperties.setProperty(new StringBuilder().append("server.database.").append(paramInt).toString(), paramString);
  }

  public void setDefaultWebPage(String paramString)
  {
    checkRunning(false);
    printWithThread(new StringBuilder().append("setDefaultWebPage(").append(paramString).append(")").toString());
    if (this.serverProtocol != 0)
      return;
    this.serverProperties.setProperty("server.default_page", paramString);
  }

  public void setPort(int paramInt)
  {
    checkRunning(false);
    printWithThread(new StringBuilder().append("setPort(").append(paramInt).append(")").toString());
    this.serverProperties.setProperty("server.port", paramInt);
  }

  public void setErrWriter(PrintWriter paramPrintWriter)
  {
    this.errWriter = paramPrintWriter;
  }

  public void setLogWriter(PrintWriter paramPrintWriter)
  {
    this.logWriter = paramPrintWriter;
  }

  public void setNoSystemExit(boolean paramBoolean)
  {
    printWithThread(new StringBuilder().append("setNoSystemExit(").append(paramBoolean).append(")").toString());
    this.serverProperties.setProperty("server.no_system_exit", paramBoolean);
  }

  public void setRestartOnShutdown(boolean paramBoolean)
  {
    printWithThread(new StringBuilder().append("setRestartOnShutdown(").append(paramBoolean).append(")").toString());
    this.serverProperties.setProperty("server.restart_on_shutdown", paramBoolean);
  }

  public void setSilent(boolean paramBoolean)
  {
    printWithThread(new StringBuilder().append("setSilent(").append(paramBoolean).append(")").toString());
    this.serverProperties.setProperty("server.silent", paramBoolean);
    this.isSilent = paramBoolean;
  }

  public void setTls(boolean paramBoolean)
  {
    checkRunning(false);
    printWithThread(new StringBuilder().append("setTls(").append(paramBoolean).append(")").toString());
    this.serverProperties.setProperty("server.tls", paramBoolean);
  }

  public void setTrace(boolean paramBoolean)
  {
    printWithThread(new StringBuilder().append("setTrace(").append(paramBoolean).append(")").toString());
    this.serverProperties.setProperty("server.trace", paramBoolean);
    JavaSystem.setLogToSystem(paramBoolean);
  }

  public void setDaemon(boolean paramBoolean)
  {
    checkRunning(false);
    printWithThread(new StringBuilder().append("setDaemon(").append(paramBoolean).append(")").toString());
    this.serverProperties.setProperty("server.daemon", paramBoolean);
  }

  public void setWebRoot(String paramString)
  {
    checkRunning(false);
    paramString = new File(paramString).getAbsolutePath();
    printWithThread(new StringBuilder().append("setWebRoot(").append(paramString).append(")").toString());
    if (this.serverProtocol != 0)
      return;
    this.serverProperties.setProperty("server.root", paramString);
  }

  public void setProperties(HsqlProperties paramHsqlProperties)
    throws IOException, ServerAcl.AclFormatException
  {
    checkRunning(false);
    if (paramHsqlProperties != null)
    {
      paramHsqlProperties.validate();
      localObject = paramHsqlProperties.getErrorKeys();
      if (localObject.length > 0)
        throw Error.error(407, localObject[0]);
      this.serverProperties.addProperties(paramHsqlProperties);
    }
    this.maxConnections = this.serverProperties.getIntegerProperty("server.maxconnections", 16);
    JavaSystem.setLogToSystem(isTrace());
    this.isSilent = this.serverProperties.isPropertyTrue("server.silent");
    this.isRemoteOpen = this.serverProperties.isPropertyTrue("server.remote_open");
    this.isDaemon = this.serverProperties.isPropertyTrue("server.daemon");
    Object localObject = this.serverProperties.getProperty("server.acl");
    if (localObject != null)
    {
      this.acl = new ServerAcl(new File((String)localObject));
      if ((this.logWriter != null) && (!this.isSilent))
        this.acl.setPrintWriter(this.logWriter);
    }
  }

  public int start()
  {
    printWithThread("start() entered");
    int i = getState();
    if (this.serverThread != null)
    {
      printWithThread("start(): serverThread != null; no action taken");
      return i;
    }
    setState(4);
    this.serverThread = new ServerThread("HSQLDB Server ");
    if (this.isDaemon)
      this.serverThread.setDaemon(true);
    this.serverThread.start();
    while (getState() == 4)
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    printWithThread("start() exiting");
    return i;
  }

  public int stop()
  {
    printWithThread("stop() entered");
    int i = getState();
    if (this.serverThread == null)
    {
      printWithThread("stop() serverThread is null; no action taken");
      return i;
    }
    releaseServerSocket();
    printWithThread("stop() exiting");
    return i;
  }

  protected boolean allowConnection(Socket paramSocket)
  {
    if (this.isShuttingDown)
      return false;
    return this.acl == null ? true : this.acl.permitAccess(paramSocket.getInetAddress().getAddress());
  }

  protected void init(int paramInt)
  {
    this.serverState = 16;
    this.serverConnSet = new HashSet();
    this.serverId = toString();
    this.serverId = this.serverId.substring(this.serverId.lastIndexOf(46) + 1);
    this.serverProtocol = paramInt;
    this.serverProperties = ServerConfiguration.newDefaultProperties(paramInt);
    this.logWriter = new PrintWriter(System.out);
    this.errWriter = new PrintWriter(System.err);
    JavaSystem.setLogToSystem(isTrace());
  }

  protected synchronized void setState(int paramInt)
  {
    this.serverState = paramInt;
  }

  public final void notify(int paramInt1, int paramInt2)
  {
    printWithThread(new StringBuilder().append("notifiy(").append(paramInt1).append(",").append(paramInt2).append(") entered").toString());
    if (paramInt1 != 0)
      return;
    releaseDatabase(paramInt2);
    int i = 1;
    for (int j = 0; j < this.dbID.length; j++)
      if (this.dbAlias[j] != null)
        i = 0;
    if ((!this.isRemoteOpen) && (i != 0))
      stop();
  }

  final synchronized void releaseDatabase(int paramInt)
  {
    int i = 0;
    printWithThread(new StringBuilder().append("releaseDatabase(").append(paramInt).append(") entered").toString());
    for (int j = 0; j < this.dbID.length; j++)
      if ((this.dbID[j] == paramInt) && (this.dbAlias[j] != null))
      {
        this.dbID[j] = 0;
        this.dbActionSequence[j] = 0L;
        this.dbAlias[j] = null;
        this.dbPath[j] = null;
        this.dbType[j] = null;
        this.dbProps[j] = null;
      }
    ServerConnection[] arrayOfServerConnection;
    synchronized (this.serverConnSet)
    {
      arrayOfServerConnection = new ServerConnection[this.serverConnSet.size()];
      this.serverConnSet.toArray(arrayOfServerConnection);
    }
    for (int k = 0; k < arrayOfServerConnection.length; k++)
    {
      ServerConnection localServerConnection = arrayOfServerConnection[k];
      if (localServerConnection.dbID == paramInt)
        localServerConnection.signalClose();
    }
    printWithThread(new StringBuilder().append("releaseDatabase(").append(paramInt).append(") exiting").toString());
  }

  protected void print(String paramString)
  {
    PrintWriter localPrintWriter = this.logWriter;
    if (localPrintWriter != null)
    {
      localPrintWriter.println(new StringBuilder().append("[").append(this.serverId).append("]: ").append(paramString).toString());
      localPrintWriter.flush();
    }
  }

  final void printResource(String paramString)
  {
    if (serverBundleHandle < 0)
      return;
    String str = BundleHandler.getString(serverBundleHandle, paramString);
    if (str == null)
      return;
    StringTokenizer localStringTokenizer = new StringTokenizer(str, "\n\r");
    while (localStringTokenizer.hasMoreTokens())
      print(localStringTokenizer.nextToken());
  }

  protected void printStackTrace(Throwable paramThrowable)
  {
    if (this.errWriter != null)
    {
      paramThrowable.printStackTrace(this.errWriter);
      this.errWriter.flush();
    }
  }

  final void printWithTimestamp(String paramString)
  {
    print(new StringBuilder().append(HsqlDateTime.getSystemTimeString()).append(" ").append(paramString).toString());
  }

  protected void printWithThread(String paramString)
  {
    if (!isSilent())
      print(new StringBuilder().append("[").append(Thread.currentThread()).append("]: ").append(paramString).toString());
  }

  protected void printError(String paramString)
  {
    PrintWriter localPrintWriter = this.errWriter;
    if (localPrintWriter != null)
    {
      localPrintWriter.print(new StringBuilder().append("[").append(this.serverId).append("]: ").toString());
      localPrintWriter.print(new StringBuilder().append("[").append(Thread.currentThread()).append("]: ").toString());
      localPrintWriter.println(paramString);
      localPrintWriter.flush();
    }
  }

  final void printRequest(int paramInt, Result paramResult)
  {
    if (isSilent())
      return;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramInt);
    localStringBuffer.append(':');
    switch (paramResult.getType())
    {
    case 37:
      localStringBuffer.append("SQLCLI:SQLPREPARE ");
      localStringBuffer.append(paramResult.getMainString());
      break;
    case 34:
      localStringBuffer.append(paramResult.getMainString());
      break;
    case 21:
    case 35:
      localStringBuffer.append("SQLCLI:SQLEXECUTE:");
      localStringBuffer.append(paramResult.getStatementID());
      break;
    case 9:
      localStringBuffer.append("SQLCLI:SQLEXECUTE:");
      localStringBuffer.append("BATCHMODE:");
      localStringBuffer.append(paramResult.getStatementID());
      break;
    case 41:
      localStringBuffer.append("SQLCLI:RESULTUPDATE:");
      localStringBuffer.append(paramResult.getStatementID());
      break;
    case 36:
      localStringBuffer.append("SQLCLI:SQLFREESTMT:");
      localStringBuffer.append(paramResult.getStatementID());
      break;
    case 7:
      localStringBuffer.append("HSQLCLI:GETSESSIONATTR");
      break;
    case 6:
      localStringBuffer.append("HSQLCLI:SETSESSIONATTR:");
      break;
    case 33:
      localStringBuffer.append("SQLCLI:SQLENDTRAN:");
      switch (paramResult.getActionType())
      {
      case 0:
        localStringBuffer.append("COMMIT");
        break;
      case 1:
        localStringBuffer.append("ROLLBACK");
        break;
      case 4:
        localStringBuffer.append("SAVEPOINT_NAME_RELEASE ");
        localStringBuffer.append(paramResult.getMainString());
        break;
      case 2:
        localStringBuffer.append("SAVEPOINT_NAME_ROLLBACK ");
        localStringBuffer.append(paramResult.getMainString());
        break;
      case 3:
      default:
        localStringBuffer.append(paramResult.getActionType());
      }
      break;
    case 39:
      localStringBuffer.append("SQLCLI:SQLSTARTTRAN");
      break;
    case 32:
      localStringBuffer.append("SQLCLI:SQLDISCONNECT");
      break;
    case 38:
      localStringBuffer.append("SQLCLI:SQLSETCONNECTATTR:");
      switch (paramResult.getConnectionAttrType())
      {
      case 10027:
        localStringBuffer.append("SQL_ATTR_SAVEPOINT_NAME ");
        localStringBuffer.append(paramResult.getMainString());
        break;
      default:
        localStringBuffer.append(paramResult.getConnectionAttrType());
      }
      break;
    case 40:
      localStringBuffer.append("HQLCLI:CLOSE_RESULT:RESULT_ID ");
      localStringBuffer.append(paramResult.getResultId());
      break;
    case 13:
      localStringBuffer.append("HQLCLI:REQUESTDATA:RESULT_ID ");
      localStringBuffer.append(paramResult.getResultId());
      localStringBuffer.append(" ROWOFFSET ");
      localStringBuffer.append(paramResult.getUpdateCount());
      localStringBuffer.append(" ROWCOUNT ");
      localStringBuffer.append(paramResult.getFetchSize());
      break;
    case 8:
    case 10:
    case 11:
    case 12:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 20:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:
    default:
      localStringBuffer.append("SQLCLI:MODE:");
      localStringBuffer.append(paramResult.getType());
    }
    print(localStringBuffer.toString());
  }

  final synchronized int getDBIndex(String paramString)
  {
    int i = paramString.indexOf(59);
    String str1 = paramString;
    String str2 = null;
    if (i != -1)
    {
      str1 = paramString.substring(0, i);
      str2 = paramString.substring(i + 1);
    }
    int j = ArrayUtil.find(this.dbAlias, str1);
    if (j == -1)
    {
      if (str2 == null)
      {
        HsqlException localHsqlException = Error.error(458, "database alias does not exist");
        printError(new StringBuilder().append("database alias=").append(str1).append(" does not exist").toString());
        setServerError(localHsqlException);
        throw localHsqlException;
      }
      return openDatabase(str1, str2);
    }
    return j;
  }

  final int openDatabase(String paramString1, String paramString2)
  {
    if (!this.isRemoteOpen)
    {
      HsqlException localHsqlException1 = Error.error(458, "remote open not allowed");
      printError("Remote database open not allowed");
      setServerError(localHsqlException1);
      throw localHsqlException1;
    }
    int i = getFirstEmptyDatabaseIndex();
    if (i < -1)
    {
      i = closeOldestDatabase();
      if (i < -1)
      {
        localObject1 = Error.error(458, "limit of open databases reached");
        printError("limit of open databases reached");
        setServerError((Throwable)localObject1);
        throw ((Throwable)localObject1);
      }
    }
    Object localObject1 = DatabaseURL.parseURL(paramString2, false, false);
    if (localObject1 == null)
    {
      localObject2 = Error.error(458, "invalid database path");
      printError("invalid database path");
      setServerError((Throwable)localObject2);
      throw ((Throwable)localObject2);
    }
    Object localObject2 = ((HsqlProperties)localObject1).getProperty("database");
    String str = ((HsqlProperties)localObject1).getProperty("connection_type");
    try
    {
      int j = DatabaseManager.getDatabase(str, (String)localObject2, this, (HsqlProperties)localObject1);
      this.dbID[i] = j;
      this.dbActionSequence[i] = this.actionSequence;
      this.dbAlias[i] = paramString1;
      this.dbPath[i] = localObject2;
      this.dbType[i] = str;
      this.dbProps[i] = localObject1;
      return i;
    }
    catch (HsqlException localHsqlException2)
    {
      printError(new StringBuilder().append("Database [index=").append(i).append(", db=").append(this.dbType[i]).append(this.dbPath[i]).append(", alias=").append(this.dbAlias[i]).append("] did not open: ").append(localHsqlException2.toString()).toString());
      setServerError(localHsqlException2);
      throw localHsqlException2;
    }
  }

  final int getFirstEmptyDatabaseIndex()
  {
    for (int i = 0; i < this.dbAlias.length; i++)
      if (this.dbAlias[i] == null)
        return i;
    return -1;
  }

  final boolean openDatabases()
  {
    printWithThread("openDatabases() entered");
    boolean bool = false;
    setDBInfoArrays();
    for (int i = 0; i < this.dbAlias.length; i++)
      if (this.dbAlias[i] != null)
      {
        printWithThread(new StringBuilder().append("Opening database: [").append(this.dbType[i]).append(this.dbPath[i]).append("]").toString());
        StopWatch localStopWatch = new StopWatch();
        int j;
        try
        {
          j = DatabaseManager.getDatabase(this.dbType[i], this.dbPath[i], this, this.dbProps[i]);
          this.dbID[i] = j;
          bool = true;
        }
        catch (HsqlException localHsqlException)
        {
          printError(new StringBuilder().append("Database [index=").append(i).append(", db=").append(this.dbType[i]).append(this.dbPath[i]).append(", alias=").append(this.dbAlias[i]).append("] did not open: ").append(localHsqlException.toString()).toString());
          setServerError(localHsqlException);
          this.dbAlias[i] = null;
          this.dbPath[i] = null;
          this.dbType[i] = null;
          this.dbProps[i] = null;
          continue;
        }
        localStopWatch.stop();
        String str = new StringBuilder().append("Database [index=").append(i).append(", id=").append(j).append(", db=").append(this.dbType[i]).append(this.dbPath[i]).append(", alias=").append(this.dbAlias[i]).append("] opened sucessfully").toString();
        print(localStopWatch.elapsedTimeToMessage(str));
      }
    printWithThread("openDatabases() exiting");
    if (this.isRemoteOpen)
      bool = true;
    if ((!bool) && (getServerError() == null))
      setServerError(Error.error(407));
    return bool;
  }

  private void setDBInfoArrays()
  {
    IntKeyHashMap localIntKeyHashMap = getDBNameArray();
    int i = localIntKeyHashMap.size();
    if (this.serverProperties.isPropertyTrue("server.remote_open"))
    {
      int j = this.serverProperties.getIntegerProperty("server.maxdatabases", 10);
      if (i < j)
        i = j;
    }
    this.dbAlias = new String[i];
    this.dbPath = new String[this.dbAlias.length];
    this.dbType = new String[this.dbAlias.length];
    this.dbID = new int[this.dbAlias.length];
    this.dbActionSequence = new long[this.dbAlias.length];
    this.dbProps = new HsqlProperties[this.dbAlias.length];
    Iterator localIterator = localIntKeyHashMap.keySet().iterator();
    int k = 0;
    while (localIterator.hasNext())
    {
      int m = localIterator.nextInt();
      String str = getDatabasePath(m, true);
      if (str == null)
      {
        printWithThread(new StringBuilder().append("missing database path: ").append(localIntKeyHashMap.get(m)).toString());
      }
      else
      {
        HsqlProperties localHsqlProperties = DatabaseURL.parseURL(str, false, false);
        if (localHsqlProperties == null)
        {
          printWithThread(new StringBuilder().append("malformed database path: ").append(str).toString());
        }
        else
        {
          this.dbAlias[k] = ((String)localIntKeyHashMap.get(m));
          this.dbPath[k] = localHsqlProperties.getProperty("database");
          this.dbType[k] = localHsqlProperties.getProperty("connection_type");
          this.dbProps[k] = localHsqlProperties;
          k++;
        }
      }
    }
  }

  private IntKeyHashMap getDBNameArray()
  {
    int i = "server.dbname.".length();
    IntKeyHashMap localIntKeyHashMap = new IntKeyHashMap();
    Enumeration localEnumeration = this.serverProperties.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      String str1 = (String)localEnumeration.nextElement();
      if (str1.startsWith("server.dbname."))
      {
        int j;
        try
        {
          j = Integer.parseInt(str1.substring(i));
        }
        catch (NumberFormatException localNumberFormatException)
        {
          printWithThread(new StringBuilder().append("maformed database enumerator: ").append(str1).toString());
        }
        continue;
        String str2 = this.serverProperties.getProperty(str1).toLowerCase();
        if (!this.aliasSet.add(str2))
          printWithThread(new StringBuilder().append("duplicate alias: ").append(str2).toString());
        Object localObject = localIntKeyHashMap.put(j, str2);
        if (localObject != null)
          printWithThread(new StringBuilder().append("duplicate database enumerator: ").append(str1).toString());
      }
    }
    return localIntKeyHashMap;
  }

  private void openServerSocket()
    throws Exception
  {
    printWithThread("openServerSocket() entered");
    if (isTls())
      printWithThread("Requesting TLS/SSL-encrypted JDBC");
    StopWatch localStopWatch = new StopWatch();
    this.socketFactory = HsqlSocketFactory.getInstance(isTls());
    String str = getAddress();
    int i = getPort();
    if ((StringUtil.isEmpty(str)) || ("0.0.0.0".equalsIgnoreCase(str.trim())))
      this.socket = this.socketFactory.createServerSocket(i);
    else
      try
      {
        this.socket = this.socketFactory.createServerSocket(i, str);
      }
      catch (UnknownHostException localUnknownHostException)
      {
        String[] arrayOfString = ServerConfiguration.listLocalInetAddressNames();
        int j;
        Object[] arrayOfObject;
        if (arrayOfString.length > 0)
        {
          j = 61;
          StringBuffer localStringBuffer = new StringBuffer();
          for (int k = 0; k < arrayOfString.length; k++)
          {
            if (localStringBuffer.length() > 0)
              localStringBuffer.append(", ");
            localStringBuffer.append(arrayOfString[k]);
          }
          arrayOfObject = new Object[] { str, localStringBuffer.toString() };
        }
        else
        {
          j = 62;
          arrayOfObject = new Object[] { str };
        }
        throw new UnknownHostException(Error.getMessage(j, 0, arrayOfObject));
      }
    this.socket.setSoTimeout(1000);
    printWithThread(new StringBuilder().append("Got server socket: ").append(this.socket).toString());
    print(localStopWatch.elapsedTimeToMessage("Server socket opened successfully"));
    if (this.socketFactory.isSecure())
      print("Using TLS/SSL-encrypted JDBC");
    printWithThread("openServerSocket() exiting");
  }

  private void printServerOnlineMessage()
  {
    String str = new StringBuilder().append(getProductName()).append(" ").append(getProductVersion()).append(" is online on port ").append(getPort()).toString();
    printWithTimestamp(str);
    printResource("online.help");
  }

  protected void printProperties()
  {
    if (isSilent())
      return;
    Enumeration localEnumeration = this.serverProperties.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      String str1 = (String)localEnumeration.nextElement();
      String str2 = this.serverProperties.getProperty(str1);
      printWithThread(new StringBuilder().append(str1).append("=").append(str2).toString());
    }
  }

  private synchronized void releaseServerSocket()
  {
    printWithThread("releaseServerSocket() entered");
    if (this.socket != null)
    {
      printWithThread(new StringBuilder().append("Releasing server socket: [").append(this.socket).append("]").toString());
      setState(8);
      try
      {
        this.socket.close();
      }
      catch (IOException localIOException)
      {
        printError("Exception closing server socket");
        printError(new StringBuilder().append("releaseServerSocket(): ").append(localIOException).toString());
      }
      this.socket = null;
    }
    printWithThread("releaseServerSocket() exited");
  }

  private void run()
  {
    printWithThread("run() entered");
    print("Initiating startup sequence...");
    printProperties();
    StopWatch localStopWatch = new StopWatch();
    setServerError(null);
    try
    {
      openServerSocket();
    }
    catch (Exception localException)
    {
      setServerError(localException);
      printError("run()/openServerSocket(): ");
      printStackTrace(localException);
      shutdown(true);
      return;
    }
    String str = new StringBuilder().append("HSQLDB Connections @").append(Integer.toString(hashCode(), 16)).toString();
    ThreadGroup localThreadGroup = new ThreadGroup(str);
    localThreadGroup.setDaemon(false);
    this.serverConnectionThreadGroup = localThreadGroup;
    if (!openDatabases())
    {
      setServerError(null);
      printError("Shutting down because there are no open databases");
      shutdown(true);
      return;
    }
    setState(1);
    print(localStopWatch.elapsedTimeToMessage("Startup sequence completed"));
    printServerOnlineMessage();
    this.isShuttingDown = false;
    try
    {
      while (this.socket != null)
        try
        {
          handleConnection(this.socket.accept());
        }
        catch (InterruptedIOException localInterruptedIOException)
        {
        }
    }
    catch (IOException localIOException)
    {
      if (getState() == 1)
      {
        setServerError(localIOException);
        printError(new StringBuilder().append(this).append(".run()/handleConnection(): ").toString());
        printStackTrace(localIOException);
      }
    }
    catch (Throwable localThrowable)
    {
      printWithThread(localThrowable.toString());
    }
    finally
    {
      shutdown(false);
    }
  }

  protected void setServerError(Throwable paramThrowable)
  {
    this.serverError = paramThrowable;
  }

  public void shutdownCatalogs(int paramInt)
  {
    DatabaseManager.shutdownDatabases(this, paramInt);
  }

  public void shutdownWithCatalogs(int paramInt)
  {
    this.isShuttingDown = true;
    DatabaseManager.shutdownDatabases(this, paramInt);
    shutdown(false);
    this.isShuttingDown = false;
  }

  public void shutdown()
  {
    shutdown(false);
  }

  protected synchronized void shutdown(boolean paramBoolean)
  {
    if (this.serverState == 16)
      return;
    printWithThread("shutdown() entered");
    StopWatch localStopWatch = new StopWatch();
    print("Initiating shutdown sequence...");
    releaseServerSocket();
    DatabaseManager.deRegisterServer(this);
    int i;
    if (this.dbPath != null)
      for (i = 0; i < this.dbPath.length; i++)
        releaseDatabase(this.dbID[i]);
    if (this.serverConnectionThreadGroup != null)
    {
      if (!this.serverConnectionThreadGroup.isDestroyed())
      {
        i = this.serverConnectionThreadGroup.activeCount();
        for (int j = 0; (this.serverConnectionThreadGroup.activeCount() > 0) && (j < i); j++)
          try
          {
            Thread.sleep(100L);
          }
          catch (Exception localException)
          {
          }
        try
        {
          this.serverConnectionThreadGroup.destroy();
          printWithThread(new StringBuilder().append(this.serverConnectionThreadGroup.getName()).append(" destroyed").toString());
        }
        catch (Throwable localThrowable2)
        {
          printWithThread(new StringBuilder().append(this.serverConnectionThreadGroup.getName()).append(" not destroyed").toString());
          printWithThread(localThrowable2.toString());
        }
      }
      this.serverConnectionThreadGroup = null;
    }
    this.serverThread = null;
    setState(16);
    print(localStopWatch.elapsedTimeToMessage("Shutdown sequence completed"));
    if (isNoSystemExit())
    {
      printWithTimestamp("SHUTDOWN : System.exit() was not called");
      printWithThread("shutdown() exited");
    }
    else
    {
      printWithTimestamp("SHUTDOWN : System.exit() is called next");
      printWithThread("shutdown() exiting...");
      try
      {
        System.exit(0);
      }
      catch (Throwable localThrowable1)
      {
        printWithThread(localThrowable1.toString());
      }
    }
  }

  synchronized void setActionSequence(int paramInt)
  {
    this.dbActionSequence[paramInt] = (this.actionSequence++);
  }

  protected int closeOldestDatabase()
  {
    return -1;
  }

  protected static void printHelp(String paramString)
  {
    System.out.println(BundleHandler.getString(serverBundleHandle, paramString));
  }

  public static void main(String[] paramArrayOfString)
  {
    HsqlProperties localHsqlProperties = null;
    localHsqlProperties = HsqlProperties.argArrayToProps(paramArrayOfString, "server");
    String[] arrayOfString = localHsqlProperties.getErrorKeys();
    if (arrayOfString.length != 0)
    {
      System.out.println(new StringBuilder().append("no value for argument:").append(arrayOfString[0]).toString());
      printHelp("server.help");
      return;
    }
    String str1 = localHsqlProperties.getProperty("server.props");
    String str2 = "";
    if (str1 == null)
    {
      str1 = "server";
      str2 = ".properties";
    }
    else
    {
      localHsqlProperties.removeProperty("server.props");
    }
    str1 = FileUtil.getFileUtil().canonicalOrAbsolutePath(str1);
    ServerProperties localServerProperties1 = ServerConfiguration.getPropertiesFromFile(1, str1, str2);
    ServerProperties localServerProperties2 = localServerProperties1 == null ? new ServerProperties(1) : localServerProperties1;
    localServerProperties2.addProperties(localHsqlProperties);
    ServerConfiguration.translateDefaultDatabaseProperty(localServerProperties2);
    ServerConfiguration.translateDefaultNoSystemExitProperty(localServerProperties2);
    ServerConfiguration.translateAddressProperty(localServerProperties2);
    Server localServer = new Server();
    try
    {
      localServer.setProperties(localServerProperties2);
    }
    catch (Exception localException)
    {
      localServer.printError("Failed to set properties");
      localServer.printStackTrace(localException);
      return;
    }
    localServer.print("Startup sequence initiated from main() method");
    if (localServerProperties1 != null)
    {
      localServer.print(new StringBuilder().append("Loaded properties from [").append(str1).append(str2).append("]").toString());
    }
    else
    {
      localServer.print("Could not load properties from file");
      localServer.print("Using cli/default properties only");
    }
    localServer.start();
  }

  private class ServerThread extends Thread
  {
    ServerThread(String arg2)
    {
      super();
      setName(str + '@' + Integer.toString(Server.this.hashCode(), 16));
    }

    public void run()
    {
      Server.this.run();
      Server.this.printWithThread("ServerThread.run() exited");
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.Server
 * JD-Core Version:    0.6.2
 */