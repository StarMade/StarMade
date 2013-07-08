package org.hsqldb.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLSocket;
import org.hsqldb.ClientConnection;
import org.hsqldb.ColumnBase;
import org.hsqldb.Database;
import org.hsqldb.DatabaseManager;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HashSet;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.resources.BundleHandler;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputBinary;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.Type;

class ServerConnection
  implements Runnable
{
  boolean keepAlive;
  private String user;
  int dbID;
  int dbIndex;
  private volatile Session session;
  private Socket socket;
  private Server server;
  private DataInputStream dataInput;
  private DataOutputStream dataOutput;
  private int mThread;
  static final int BUFFER_SIZE = 4096;
  final byte[] mainBuffer = new byte[4096];
  RowOutputInterface rowOut;
  RowInputBinary rowIn;
  Thread runnerThread;
  private static AtomicInteger mCurrentThread = new AtomicInteger(0);
  protected static String TEXTBANNER_PART1 = null;
  protected static String TEXTBANNER_PART2 = null;
  private CleanExit cleanExit = new CleanExit(null);
  private OdbcPacketOutputStream outPacket = null;
  public static long MAX_WAIT_FOR_CLIENT_DATA = 1000L;
  public static long CLIENT_DATA_POLLING_PERIOD = 100L;
  private Map sessionOdbcPsMap = new HashMap();
  private Map sessionOdbcPortalMap = new HashMap();
  private int streamProtocol = 0;
  static final int UNDEFINED_STREAM_PROTOCOL = 0;
  static final int HSQL_STREAM_PROTOCOL = 1;
  static final int ODBC_STREAM_PROTOCOL = 2;
  int odbcCommMode = 0;
  
  ServerConnection(Socket paramSocket, Server paramServer)
  {
    RowOutputBinary localRowOutputBinary = new RowOutputBinary(this.mainBuffer);
    this.rowIn = new RowInputBinary(localRowOutputBinary);
    this.rowOut = localRowOutputBinary;
    this.socket = paramSocket;
    this.server = paramServer;
    this.mThread = mCurrentThread.getAndIncrement();
    synchronized (paramServer.serverConnSet)
    {
      paramServer.serverConnSet.add(this);
    }
  }
  
  void signalClose()
  {
    this.keepAlive = false;
    if (!Thread.currentThread().equals(this.runnerThread)) {
      close();
    }
  }
  
  private void close()
  {
    if (this.session != null)
    {
      this.session.close();
      this.session = null;
    }
    synchronized (this)
    {
      try
      {
        if (this.socket != null)
        {
          this.socket.close();
          this.socket = null;
        }
      }
      catch (IOException localIOException) {}
      this.socket = null;
    }
    synchronized (this.server.serverConnSet)
    {
      this.server.serverConnSet.remove(this);
    }
    try
    {
      this.runnerThread.setContextClassLoader(null);
    }
    catch (Throwable localThrowable) {}
  }
  
  private void init()
  {
    this.runnerThread = Thread.currentThread();
    this.keepAlive = true;
    try
    {
      this.socket.setTcpNoDelay(true);
      this.dataInput = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
      this.dataOutput = new DataOutputStream(this.socket.getOutputStream());
      int i = handshake();
      switch (this.streamProtocol)
      {
      case 1: 
        if (i != -2010000)
        {
          if (i == -1900000) {
            i = -2000000;
          }
          localObject = ClientConnection.toNetCompVersionString(i);
          throw Error.error(null, 403, 0, new String[] { localObject, "version" });
        }
        localObject = Result.newResult(this.dataInput, this.rowIn);
        ((Result)localObject).readAdditionalResults(this.session, this.dataInput, this.rowIn);
        Result localResult = setDatabase((Result)localObject);
        localResult.write(this.session, this.dataOutput, this.rowOut);
        break;
      case 2: 
        odbcConnect(i);
        break;
      default: 
        this.keepAlive = false;
      }
    }
    catch (Exception localException)
    {
      Object localObject = new StringBuffer(this.mThread + ":Failed to connect client.");
      if (this.user != null) {
        ((StringBuffer)localObject).append("  User '" + this.user + "'.");
      }
      this.server.printWithThread(((StringBuffer)localObject).toString() + "  Stack trace follows.");
      this.server.printStackTrace(localException);
    }
  }
  
  private void receiveResult(int paramInt)
    throws ServerConnection.CleanExit, IOException
  {
    int i = 0;
    Result localResult1 = Result.newResult(this.session, paramInt, this.dataInput, this.rowIn);
    localResult1.readLobResults(this.session, this.dataInput, this.rowIn);
    this.server.printRequest(this.mThread, localResult1);
    Result localResult2 = null;
    switch (localResult1.getType())
    {
    case 31: 
      localResult2 = setDatabase(localResult1);
      break;
    case 32: 
      localResult2 = Result.updateZeroResult;
      i = 1;
      break;
    case 10: 
      this.session.resetSession();
      localResult2 = Result.updateZeroResult;
      break;
    case 21: 
      localResult2 = Result.newErrorResult(Error.error(1252));
      break;
    default: 
      localResult2 = this.session.execute(localResult1);
    }
    localResult2.write(this.session, this.dataOutput, this.rowOut);
    this.rowOut.reset(this.mainBuffer);
    this.rowIn.resetRow(this.mainBuffer.length);
    if (i != 0) {
      throw this.cleanExit;
    }
  }
  
  private void receiveOdbcPacket(char paramChar)
    throws IOException, ServerConnection.CleanExit
  {
    int i = 0;
    Object localObject1 = null;
    OdbcPacketInputStream localOdbcPacketInputStream = null;
    try
    {
      localOdbcPacketInputStream = OdbcPacketInputStream.newOdbcPacketInputStream(paramChar, this.dataInput);
      this.server.printWithThread("Got op (" + localOdbcPacketInputStream.packetType + ')');
      this.server.printWithThread("Got packet length of " + localOdbcPacketInputStream.available() + " + type byte + 4 size header");
      if (localOdbcPacketInputStream.available() >= 1000000000) {
        throw new IOException("Insane packet length: " + localOdbcPacketInputStream.available() + " + type byte + 4 size header");
      }
    }
    catch (SocketException localSocketException)
    {
      this.server.printWithThread("Ungraceful client exit: " + localSocketException);
      throw this.cleanExit;
    }
    catch (IOException localIOException)
    {
      this.server.printWithThread("Fatal ODBC protocol failure: " + localIOException);
      try
      {
        OdbcUtil.alertClient(1, localIOException.toString(), "08P01", this.dataOutput);
      }
      catch (Exception localException) {}
      throw this.cleanExit;
    }
    switch (this.odbcCommMode)
    {
    case 2: 
      if (localOdbcPacketInputStream.packetType != 'S')
      {
        if (this.server.isTrace()) {
          this.server.printWithThread("Ignoring a '" + localOdbcPacketInputStream.packetType + "'");
        }
        return;
      }
      this.odbcCommMode = 1;
      this.server.printWithThread("EXTENDED comm session being recovered");
      break;
    case 0: 
      switch (localOdbcPacketInputStream.packetType)
      {
      case 'B': 
      case 'C': 
      case 'D': 
      case 'E': 
      case 'H': 
      case 'P': 
      case 'S': 
        this.odbcCommMode = 1;
        this.server.printWithThread("Switching mode from SIMPLE to EXTENDED");
      }
      break;
    case 1: 
      switch (localOdbcPacketInputStream.packetType)
      {
      case 'Q': 
        this.odbcCommMode = 0;
        this.server.printWithThread("Switching mode from EXTENDED to SIMPLE");
      }
      break;
    default: 
      throw new RuntimeException("Unexpected ODBC comm mode value: " + this.odbcCommMode);
    }
    this.outPacket.reset();
    try
    {
      Result localResult2;
      Type[] arrayOfType;
      PgType[] arrayOfPgType;
      Object localObject5;
      String str4;
      String str3;
      OdbcPreparedStatement localOdbcPreparedStatement;
      StatementPortal localStatementPortal;
      String str1;
      int j;
      char c;
      ResultMetaData localResultMetaData1;
      int i5;
      String str2;
      int i9;
      switch (localOdbcPacketInputStream.packetType)
      {
      case 'Q': 
        String str6 = localOdbcPacketInputStream.readString();
        if ((str6.startsWith("BEGIN;")) || (str6.equals("BEGIN")))
        {
          str6 = str6.equals("BEGIN") ? null : str6.substring("BEGIN;".length());
          this.server.printWithThread("ODBC Trans started.  Session AutoCommit -> F");
          try
          {
            this.session.setAutoCommit(false);
          }
          catch (HsqlException localHsqlException1)
          {
            throw new RecoverableOdbcFailure("Failed to change transaction state: " + localHsqlException1.getMessage(), localHsqlException1.getSQLState());
          }
          this.outPacket.write("BEGIN");
          this.outPacket.xmit('C', this.dataOutput);
          if (str6 == null)
          {
            i = 1;
            break;
          }
        }
        if ((str6.startsWith("SAVEPOINT ")) && (str6.indexOf(';') > 0))
        {
          int m = str6.indexOf(';');
          this.server.printWithThread("Interposing BEFORE primary statement: " + str6.substring(0, m));
          odbcExecDirect(str6.substring(0, m));
          str6 = str6.substring(m + 1);
        }
        int k = str6.lastIndexOf(';');
        if (k > 0)
        {
          localObject2 = str6.substring(k + 1);
          if (((String)localObject2).startsWith("RELEASE "))
          {
            localObject1 = localObject2;
            str6 = str6.substring(0, k);
          }
        }
        localObject2 = str6.trim().toLowerCase();
        if (this.server.isTrace()) {
          this.server.printWithThread("Received query (" + str6 + ')');
        }
        if (((String)localObject2).startsWith("select current_schema()"))
        {
          this.server.printWithThread("Implement 'select current_schema() emulation!");
          throw new RecoverableOdbcFailure("current_schema() not supported yet", "0A000");
        }
        if (((String)localObject2).startsWith("select n.nspname,"))
        {
          this.server.printWithThread("Swallowing 'select n.nspname,...'");
          this.outPacket.writeShort(1);
          this.outPacket.write("oid");
          this.outPacket.writeInt(201);
          this.outPacket.writeShort(1);
          this.outPacket.writeInt(23);
          this.outPacket.writeShort(4);
          this.outPacket.writeInt(-1);
          this.outPacket.writeShort(0);
          this.outPacket.xmit('T', this.dataOutput);
          this.outPacket.write("SELECT");
          this.outPacket.xmit('C', this.dataOutput);
          i = 1;
        }
        else if (((String)localObject2).startsWith("select oid, typbasetype from"))
        {
          this.server.printWithThread("Simulating 'select oid, typbasetype...'");
          this.outPacket.writeShort(2);
          this.outPacket.write("oid");
          this.outPacket.writeInt(101);
          this.outPacket.writeShort(102);
          this.outPacket.writeInt(26);
          this.outPacket.writeShort(4);
          this.outPacket.writeInt(-1);
          this.outPacket.writeShort(0);
          this.outPacket.write("typbasetype");
          this.outPacket.writeInt(101);
          this.outPacket.writeShort(103);
          this.outPacket.writeInt(26);
          this.outPacket.writeShort(4);
          this.outPacket.writeInt(-1);
          this.outPacket.writeShort(0);
          this.outPacket.xmit('T', this.dataOutput);
          this.outPacket.write("SELECT");
          this.outPacket.xmit('C', this.dataOutput);
          i = 1;
        }
        else if (((String)localObject2).startsWith("select "))
        {
          this.server.printWithThread("Performing a real non-prepared SELECT...");
          Result localResult1 = Result.newExecuteDirectRequest();
          localResult1.setPrepareOrExecuteProperties(str6, 0, 0, 2, 0, 0, 2, null, null);
          localResult2 = this.session.execute(localResult1);
          switch (localResult2.getType())
          {
          case 3: 
            break;
          case 2: 
            throw new RecoverableOdbcFailure(localResult2);
          default: 
            throw new RecoverableOdbcFailure("Output Result from Query execution is of unexpected type: " + localResult2.getType());
          }
          RowSetNavigator localRowSetNavigator1 = localResult2.getNavigator();
          ResultMetaData localResultMetaData2 = localResult2.metaData;
          if (localResultMetaData2 == null) {
            throw new RecoverableOdbcFailure("Failed to get metadata for query results");
          }
          int i1 = localResultMetaData2.getColumnCount();
          String[] arrayOfString = localResultMetaData2.getGeneratedColumnNames();
          arrayOfType = localResultMetaData2.columnTypes;
          arrayOfPgType = new PgType[i1];
          for (int i3 = 0; i3 < arrayOfPgType.length; i3++) {
            arrayOfPgType[i3] = PgType.getPgType(arrayOfType[i3], localResultMetaData2.isTableColumn(i3));
          }
          localObject5 = localResultMetaData2.columns;
          this.outPacket.writeShort(i1);
          for (int i4 = 0; i4 < i1; i4++)
          {
            if (arrayOfString[i4] != null) {
              this.outPacket.write(arrayOfString[i4]);
            } else {
              this.outPacket.write(localObject5[i4].getNameString());
            }
            this.outPacket.writeInt(OdbcUtil.getTableOidForColumn(i4, localResultMetaData2));
            this.outPacket.writeShort(OdbcUtil.getIdForColumn(i4, localResultMetaData2));
            this.outPacket.writeInt(arrayOfPgType[i4].getOid());
            this.outPacket.writeShort(arrayOfPgType[i4].getTypeWidth());
            this.outPacket.writeInt(arrayOfPgType[i4].getLPConstraint());
            this.outPacket.writeShort(0);
          }
          this.outPacket.xmit('T', this.dataOutput);
          i4 = 0;
          while (localRowSetNavigator1.next())
          {
            i4++;
            Object[] arrayOfObject1 = localRowSetNavigator1.getCurrent();
            if (arrayOfObject1 == null) {
              throw new RecoverableOdbcFailure("Null row?");
            }
            if (arrayOfObject1.length < i1) {
              throw new RecoverableOdbcFailure("Data element mismatch. " + i1 + " metadata cols, yet " + arrayOfObject1.length + " data elements for row " + i4);
            }
            this.outPacket.writeShort(i1);
            for (int i6 = 0; i6 < i1; i6++) {
              if (arrayOfObject1[i6] == null)
              {
                this.outPacket.writeInt(-1);
              }
              else
              {
                str4 = arrayOfPgType[i6].valueString(arrayOfObject1[i6]);
                this.outPacket.writeSized(str4);
                if (this.server.isTrace()) {
                  this.server.printWithThread("R" + i4 + "C" + (i6 + 1) + " => (" + arrayOfObject1[i6].getClass().getName() + ") [" + str4 + ']');
                }
              }
            }
            this.outPacket.xmit('D', this.dataOutput);
          }
          this.outPacket.write("SELECT");
          this.outPacket.xmit('C', this.dataOutput);
          i = 1;
        }
        else if ((((String)localObject2).startsWith("deallocate \"")) && (((String)localObject2).charAt(((String)localObject2).length() - 1) == '"'))
        {
          String str5 = str6.trim().substring("deallocate \"".length()).trim();
          str3 = str5.substring(0, str5.length() - 1);
          localOdbcPreparedStatement = (OdbcPreparedStatement)this.sessionOdbcPsMap.get(str3);
          if (localOdbcPreparedStatement != null) {
            localOdbcPreparedStatement.close();
          }
          localStatementPortal = (StatementPortal)this.sessionOdbcPortalMap.get(str3);
          if (localStatementPortal != null) {
            localStatementPortal.close();
          }
          if ((localOdbcPreparedStatement == null) && (localStatementPortal == null)) {
            this.server.printWithThread("Ignoring bad 'DEALLOCATE' cmd");
          }
          if (this.server.isTrace()) {
            this.server.printWithThread("Deallocated PS/Portal '" + str3 + "'");
          }
          this.outPacket.write("DEALLOCATE");
          this.outPacket.xmit('C', this.dataOutput);
          i = 1;
        }
        else if (((String)localObject2).startsWith("set client_encoding to "))
        {
          this.server.printWithThread("Stubbing EXECDIR for: " + str6);
          this.outPacket.write("SET");
          this.outPacket.xmit('C', this.dataOutput);
          i = 1;
        }
        else
        {
          this.server.printWithThread("Performing a real EXECDIRECT...");
          odbcExecDirect(str6);
          i = 1;
        }
        break;
      case 'X': 
        if (this.sessionOdbcPsMap.size() > (this.sessionOdbcPsMap.containsKey("") ? 1 : 0)) {
          this.server.printWithThread("Client left " + this.sessionOdbcPsMap.size() + " PS objects open");
        }
        if (this.sessionOdbcPortalMap.size() > (this.sessionOdbcPortalMap.containsKey("") ? 1 : 0)) {
          this.server.printWithThread("Client left " + this.sessionOdbcPortalMap.size() + " Portal objects open");
        }
        OdbcUtil.validateInputPacketSize(localOdbcPacketInputStream);
        throw this.cleanExit;
      case 'H': 
        break;
      case 'S': 
        if (this.session.isAutoCommit()) {
          try
          {
            this.server.printWithThread("Silly implicit commit by Sync");
            this.session.commit(true);
          }
          catch (HsqlException localHsqlException2)
          {
            this.server.printWithThread("Implicit commit failed: " + localHsqlException2);
            OdbcUtil.alertClient(2, "Implicit commit failed", localHsqlException2.getSQLState(), this.dataOutput);
          }
        }
        i = 1;
        break;
      case 'P': 
        str1 = localOdbcPacketInputStream.readString();
        str7 = OdbcUtil.revertMungledPreparedQuery(localOdbcPacketInputStream.readString());
        j = localOdbcPacketInputStream.readUnsignedShort();
        for (int n = 0; n < j; n++) {
          if (localOdbcPacketInputStream.readInt() != 0) {
            throw new RecoverableOdbcFailure(null, "Parameter-type OID specifiers not supported yet", "0A000");
          }
        }
        if (this.server.isTrace()) {
          this.server.printWithThread("Received Prepare request for query (" + str7 + ") with handle '" + str1 + "'");
        }
        if ((str1.length() > 0) && (this.sessionOdbcPsMap.containsKey(str1))) {
          throw new RecoverableOdbcFailure(null, "PS handle '" + str1 + "' already in use.  " + "You must close it before recreating", "08P01");
        }
        new OdbcPreparedStatement(str1, str7, this.sessionOdbcPsMap, this.session);
        this.outPacket.xmit('1', this.dataOutput);
        break;
      case 'D': 
        c = localOdbcPacketInputStream.readByteChar();
        str3 = localOdbcPacketInputStream.readString();
        localOdbcPreparedStatement = null;
        localStatementPortal = null;
        if (c == 'S') {
          localOdbcPreparedStatement = (OdbcPreparedStatement)this.sessionOdbcPsMap.get(str3);
        } else if (c == 'P') {
          localStatementPortal = (StatementPortal)this.sessionOdbcPortalMap.get(str3);
        } else {
          throw new RecoverableOdbcFailure(null, "Description packet request type invalid: " + c, "08P01");
        }
        if (this.server.isTrace()) {
          this.server.printWithThread("Received Describe request for " + c + " of  handle '" + str3 + "'");
        }
        if ((localOdbcPreparedStatement == null) && (localStatementPortal == null)) {
          throw new RecoverableOdbcFailure(null, "No object present for " + c + " handle: " + str3, "08P01");
        }
        localObject3 = localOdbcPreparedStatement == null ? localStatementPortal.ackResult : localOdbcPreparedStatement.ackResult;
        localResultMetaData1 = ((Result)localObject3).parameterMetaData;
        j = localResultMetaData1.getColumnCount();
        localObject4 = localResultMetaData1.getParameterTypes();
        if (j != localObject4.length) {
          throw new RecoverableOdbcFailure("Parameter count mismatch.  Count of " + j + " reported, but there are " + localObject4.length + " param md objects");
        }
        if (c == 'S')
        {
          this.outPacket.writeShort(j);
          for (int i2 = 0; i2 < localObject4.length; i2++) {
            this.outPacket.writeInt(PgType.getPgType(localObject4[i2], true).getOid());
          }
          this.outPacket.xmit('t', this.dataOutput);
        }
        ResultMetaData localResultMetaData3 = ((Result)localObject3).metaData;
        if (localResultMetaData3.getColumnCount() < 1)
        {
          if (this.server.isTrace()) {
            this.server.printWithThread("Non-rowset query so returning NoData packet");
          }
          this.outPacket.xmit('n', this.dataOutput);
        }
        else
        {
          localObject5 = localResultMetaData3.getGeneratedColumnNames();
          if (localResultMetaData3.getColumnCount() != localObject5.length) {
            throw new RecoverableOdbcFailure("Couldn't get all column names: " + localResultMetaData3.getColumnCount() + " cols. but only got " + localObject5.length + " col. names");
          }
          arrayOfType = localResultMetaData3.columnTypes;
          arrayOfPgType = new PgType[localObject5.length];
          ColumnBase[] arrayOfColumnBase = localResultMetaData3.columns;
          for (i5 = 0; i5 < arrayOfPgType.length; i5++) {
            arrayOfPgType[i5] = PgType.getPgType(arrayOfType[i5], localResultMetaData3.isTableColumn(i5));
          }
          if (localObject5.length != arrayOfColumnBase.length) {
            throw new RecoverableOdbcFailure("Col data mismatch.  " + arrayOfColumnBase.length + " col instances but " + localObject5.length + " col names");
          }
          this.outPacket.writeShort(localObject5.length);
          for (i5 = 0; i5 < localObject5.length; i5++)
          {
            this.outPacket.write(localObject5[i5]);
            this.outPacket.writeInt(OdbcUtil.getTableOidForColumn(i5, localResultMetaData3));
            this.outPacket.writeShort(OdbcUtil.getIdForColumn(i5, localResultMetaData3));
            this.outPacket.writeInt(arrayOfPgType[i5].getOid());
            this.outPacket.writeShort(arrayOfPgType[i5].getTypeWidth());
            this.outPacket.writeInt(arrayOfPgType[i5].getLPConstraint());
            this.outPacket.writeShort(0);
          }
          this.outPacket.xmit('T', this.dataOutput);
        }
        break;
      case 'B': 
        str2 = localOdbcPacketInputStream.readString();
        str1 = localOdbcPacketInputStream.readString();
        i5 = localOdbcPacketInputStream.readUnsignedShort();
        boolean[] arrayOfBoolean = new boolean[i5];
        for (int i7 = 0; i7 < i5; i7++)
        {
          arrayOfBoolean[i7] = (localOdbcPacketInputStream.readUnsignedShort() != 0 ? 1 : false);
          if ((this.server.isTrace()) && (arrayOfBoolean[i7] != 0)) {
            this.server.printWithThread("Binary param #" + i7);
          }
        }
        j = localOdbcPacketInputStream.readUnsignedShort();
        Object[] arrayOfObject2 = new Object[j];
        for (int i8 = 0; i8 < arrayOfObject2.length; i8++) {
          if ((i8 < arrayOfBoolean.length) && (arrayOfBoolean[i8] != 0)) {
            arrayOfObject2[i8] = localOdbcPacketInputStream.readSizedBinaryData();
          } else {
            arrayOfObject2[i8] = localOdbcPacketInputStream.readSizedString();
          }
        }
        i8 = localOdbcPacketInputStream.readUnsignedShort();
        for (i9 = 0; i9 < i8; i9++) {
          if (localOdbcPacketInputStream.readUnsignedShort() != 0) {
            throw new RecoverableOdbcFailure(null, "Binary output values not supported", "0A000");
          }
        }
        if (this.server.isTrace()) {
          this.server.printWithThread("Received Bind request to make Portal from (" + str1 + ")' with handle '" + str2 + "'");
        }
        localOdbcPreparedStatement = (OdbcPreparedStatement)this.sessionOdbcPsMap.get(str1);
        if (localOdbcPreparedStatement == null) {
          throw new RecoverableOdbcFailure(null, "No object present for PS handle: " + str1, "08P01");
        }
        if ((str2.length() > 0) && (this.sessionOdbcPortalMap.containsKey(str2))) {
          throw new RecoverableOdbcFailure(null, "Portal handle '" + str2 + "' already in use.  " + "You must close it before recreating", "08P01");
        }
        localResultMetaData1 = localOdbcPreparedStatement.ackResult.parameterMetaData;
        if (j != localResultMetaData1.getColumnCount()) {
          throw new RecoverableOdbcFailure(null, "Client didn't specify all " + localResultMetaData1.getColumnCount() + " parameters (" + j + ')', "08P01");
        }
        new StatementPortal(str2, localOdbcPreparedStatement, arrayOfObject2, this.sessionOdbcPortalMap);
        this.outPacket.xmit('2', this.dataOutput);
        break;
      case 'E': 
        str2 = localOdbcPacketInputStream.readString();
        i9 = localOdbcPacketInputStream.readInt();
        if (this.server.isTrace()) {
          this.server.printWithThread("Received Exec request for " + i9 + " rows from portal handle '" + str2 + "'");
        }
        localStatementPortal = (StatementPortal)this.sessionOdbcPortalMap.get(str2);
        if (localStatementPortal == null) {
          throw new RecoverableOdbcFailure(null, "No object present for Portal handle: " + str2, "08P01");
        }
        localStatementPortal.bindResult.setPreparedExecuteProperties(localStatementPortal.parameters, i9, 0, 0);
        localResult2 = this.session.execute(localStatementPortal.bindResult);
        switch (localResult2.getType())
        {
        case 1: 
          this.outPacket.write(OdbcUtil.echoBackReplyString(localStatementPortal.lcQuery, localResult2.getUpdateCount()));
          this.outPacket.xmit('C', this.dataOutput);
          if ((!localStatementPortal.lcQuery.equals("commit")) && (!localStatementPortal.lcQuery.startsWith("commit ")) && (!localStatementPortal.lcQuery.equals("rollback")) && (!localStatementPortal.lcQuery.startsWith("rollback "))) {
            break label5064;
          }
          try
          {
            this.session.setAutoCommit(true);
          }
          catch (HsqlException localHsqlException3)
          {
            throw new RecoverableOdbcFailure("Failed to change transaction state: " + localHsqlException3.getMessage(), localHsqlException3.getSQLState());
          }
        case 3: 
          break;
        case 2: 
          throw new RecoverableOdbcFailure(localResult2);
        default: 
          throw new RecoverableOdbcFailure("Output Result from Portal execution is of unexpected type: " + localResult2.getType());
        }
        RowSetNavigator localRowSetNavigator2 = localResult2.getNavigator();
        int i10 = 0;
        int i11 = localStatementPortal.ackResult.metaData.getColumnCount();
        while (localRowSetNavigator2.next())
        {
          i10++;
          Object[] arrayOfObject3 = localRowSetNavigator2.getCurrent();
          if (arrayOfObject3 == null) {
            throw new RecoverableOdbcFailure("Null row?");
          }
          if (arrayOfObject3.length < i11) {
            throw new RecoverableOdbcFailure("Data element mismatch. " + i11 + " metadata cols, yet " + arrayOfObject3.length + " data elements for row " + i10);
          }
          this.outPacket.writeShort(i11);
          arrayOfType = localStatementPortal.ackResult.metaData.columnTypes;
          arrayOfPgType = new PgType[i11];
          for (int i12 = 0; i12 < arrayOfPgType.length; i12++) {
            arrayOfPgType[i12] = PgType.getPgType(arrayOfType[i12], localStatementPortal.ackResult.metaData.isTableColumn(i12));
          }
          for (i12 = 0; i12 < i11; i12++) {
            if (arrayOfObject3[i12] == null)
            {
              this.outPacket.writeInt(-1);
            }
            else
            {
              str4 = arrayOfPgType[i12].valueString(arrayOfObject3[i12]);
              this.outPacket.writeSized(str4);
              if (this.server.isTrace()) {
                this.server.printWithThread("R" + i10 + "C" + (i12 + 1) + " => (" + arrayOfObject3[i12].getClass().getName() + ") [" + str4 + ']');
              }
            }
          }
          this.outPacket.xmit('D', this.dataOutput);
        }
        if (localRowSetNavigator2.afterLast())
        {
          this.outPacket.write("SELECT");
          this.outPacket.xmit('C', this.dataOutput);
        }
        else
        {
          this.outPacket.xmit('s', this.dataOutput);
        }
        break;
      case 'C': 
        c = localOdbcPacketInputStream.readByteChar();
        str3 = localOdbcPacketInputStream.readString();
        localOdbcPreparedStatement = null;
        localStatementPortal = null;
        if (c == 'S')
        {
          localOdbcPreparedStatement = (OdbcPreparedStatement)this.sessionOdbcPsMap.get(str3);
          if (localOdbcPreparedStatement != null) {
            localOdbcPreparedStatement.close();
          }
        }
        else if (c == 'P')
        {
          localStatementPortal = (StatementPortal)this.sessionOdbcPortalMap.get(str3);
          if (localStatementPortal != null) {
            localStatementPortal.close();
          }
        }
        else
        {
          throw new RecoverableOdbcFailure(null, "Description packet request type invalid: " + c, "08P01");
        }
        if (this.server.isTrace()) {
          this.server.printWithThread("Closed " + c + " '" + str3 + "'? " + ((localOdbcPreparedStatement != null) || (localStatementPortal != null)));
        }
        this.outPacket.xmit('3', this.dataOutput);
        break;
      case 'F': 
      case 'G': 
      case 'I': 
      case 'J': 
      case 'K': 
      case 'L': 
      case 'M': 
      case 'N': 
      case 'O': 
      case 'R': 
      case 'T': 
      case 'U': 
      case 'V': 
      case 'W': 
      default: 
        throw new RecoverableOdbcFailure(null, "Unsupported operation type (" + localOdbcPacketInputStream.packetType + ')', "0A000");
      }
      label5064:
      OdbcUtil.validateInputPacketSize(localOdbcPacketInputStream);
      if (localObject1 != null)
      {
        this.server.printWithThread("Interposing AFTER primary statement: " + localObject1);
        odbcExecDirect(localObject1);
      }
      if (i != 0)
      {
        this.outPacket.reset();
        this.outPacket.writeByte(this.session.isAutoCommit() ? 73 : 84);
        this.outPacket.xmit('Z', this.dataOutput);
      }
    }
    catch (RecoverableOdbcFailure localRecoverableOdbcFailure)
    {
      String str7;
      Object localObject3;
      Object localObject4;
      Object localObject2 = localRecoverableOdbcFailure.getErrorResult();
      if (localObject2 == null)
      {
        str7 = localRecoverableOdbcFailure.getSqlStateCode();
        localObject3 = localRecoverableOdbcFailure.toString();
        localObject4 = localRecoverableOdbcFailure.getClientMessage();
        if (localObject3 != null) {
          this.server.printWithThread((String)localObject3);
        } else if (this.server.isTrace()) {
          this.server.printWithThread("Client error: " + (String)localObject4);
        }
        if (localObject4 != null) {
          OdbcUtil.alertClient(2, (String)localObject4, str7, this.dataOutput);
        }
      }
      else
      {
        if (this.server.isTrace()) {
          this.server.printWithThread("Result object error: " + ((Result)localObject2).getMainString());
        }
        OdbcUtil.alertClient(2, ((Result)localObject2).getMainString(), ((Result)localObject2).getSubString(), this.dataOutput);
      }
      switch (this.odbcCommMode)
      {
      case 0: 
        this.outPacket.reset();
        this.outPacket.writeByte(69);
        this.outPacket.xmit('Z', this.dataOutput);
      }
    }
    this.odbcCommMode = 2;
    this.server.printWithThread("Reverting to EXT_RECOVER mode");
  }
  
  public void run()
  {
    init();
    if (this.session != null) {
      try
      {
        while (this.keepAlive)
        {
          int i = this.dataInput.readByte();
          if (i < 48) {
            receiveResult(i);
          } else {
            receiveOdbcPacket((char)i);
          }
        }
      }
      catch (CleanExit localCleanExit)
      {
        this.keepAlive = false;
      }
      catch (IOException localIOException)
      {
        this.server.printWithThread(this.mThread + ":disconnected " + this.user);
      }
      catch (HsqlException localHsqlException)
      {
        if (this.keepAlive) {
          this.server.printStackTrace(localHsqlException);
        }
      }
      catch (Throwable localThrowable)
      {
        if (this.keepAlive) {
          this.server.printStackTrace(localThrowable);
        }
      }
    }
    close();
  }
  
  private Result setDatabase(Result paramResult)
  {
    try
    {
      String str = paramResult.getDatabaseName();
      this.dbIndex = this.server.getDBIndex(str);
      this.dbID = this.server.dbID[this.dbIndex];
      this.user = paramResult.getMainString();
      if (!this.server.isSilent()) {
        this.server.printWithThread(this.mThread + ":Trying to connect user '" + this.user + "' to DB (" + str + ')');
      }
      this.session = DatabaseManager.newSession(this.dbID, this.user, paramResult.getSubString(), paramResult.getZoneString(), paramResult.getUpdateCount());
      if (!this.server.isSilent()) {
        this.server.printWithThread(this.mThread + ":Connected user '" + this.user + "'");
      }
      return Result.newConnectionAcknowledgeResponse(this.session.getDatabase(), this.session.getId(), this.session.getDatabase().getDatabaseID());
    }
    catch (HsqlException localHsqlException)
    {
      this.session = null;
      return Result.newErrorResult(localHsqlException);
    }
    catch (RuntimeException localRuntimeException)
    {
      this.session = null;
      return Result.newErrorResult(localRuntimeException);
    }
  }
  
  String getConnectionThreadName()
  {
    return "HSQLDB Connection @" + Integer.toString(hashCode(), 16);
  }
  
  public int handshake()
    throws IOException
  {
    long l = new Date().getTime() + MAX_WAIT_FOR_CLIENT_DATA;
    if (!(this.socket instanceof SSLSocket))
    {
      do
      {
        try
        {
          Thread.sleep(CLIENT_DATA_POLLING_PERIOD);
        }
        catch (InterruptedException localInterruptedException) {}
      } while ((this.dataInput.available() < 5) && (new Date().getTime() < l));
      if (this.dataInput.available() < 1)
      {
        this.dataOutput.write((TEXTBANNER_PART1 + "2.1.0.0" + TEXTBANNER_PART2 + '\n').getBytes());
        this.dataOutput.flush();
        throw Error.error(404);
      }
    }
    int i = this.dataInput.readInt();
    switch (i >> 24)
    {
    case 80: 
      this.server.print("Rejected attempt from client using hsql HTTP protocol");
      return 0;
    case 0: 
      this.streamProtocol = 2;
      break;
    default: 
      this.streamProtocol = 1;
    }
    return i;
  }
  
  private void odbcConnect(int paramInt)
    throws IOException
  {
    int i = this.dataInput.readUnsignedShort();
    int j = this.dataInput.readUnsignedShort();
    if ((i == 1) && (j == 7))
    {
      this.server.print("A pre-9.0 client attempted to connect.  We rejected them.");
      return;
    }
    if ((i == 1234) && (j == 5679))
    {
      this.dataOutput.writeByte(78);
      odbcConnect(this.dataInput.readInt());
      return;
    }
    if ((i == 1234) && (j == 5678))
    {
      if (paramInt != 16) {
        this.server.print("ODBC cancellation request sent wrong packet length: " + paramInt);
      }
      this.server.print("Got an ODBC cancelation request for thread ID " + this.dataInput.readInt() + ", but we don't support " + "OOB cancellation yet.  " + "Ignoring this request and closing the connection.");
      return;
    }
    this.server.printWithThread("ODBC client connected.  ODBC Protocol Compatibility Version " + i + '.' + j);
    OdbcPacketInputStream localOdbcPacketInputStream = OdbcPacketInputStream.newOdbcPacketInputStream('\000', this.dataInput, paramInt - 8);
    Map localMap = localOdbcPacketInputStream.readStringPairs();
    if (this.server.isTrace()) {
      this.server.print("String Pairs from ODBC client: " + localMap);
    }
    try
    {
      try
      {
        OdbcUtil.validateInputPacketSize(localOdbcPacketInputStream);
      }
      catch (RecoverableOdbcFailure localRecoverableOdbcFailure)
      {
        throw new ClientFailure(localRecoverableOdbcFailure.toString(), localRecoverableOdbcFailure.getClientMessage());
      }
      localOdbcPacketInputStream.close();
      if (!localMap.containsKey("database")) {
        throw new ClientFailure("Client did not identify database", "Target database not identified");
      }
      if (!localMap.containsKey("user")) {
        throw new ClientFailure("Client did not identify user", "Target account not identified");
      }
      String str1 = (String)localMap.get("database");
      this.user = ((String)localMap.get("user"));
      if (str1.equals("/")) {
        str1 = "";
      }
      this.dataOutput.writeByte(82);
      this.dataOutput.writeInt(8);
      this.dataOutput.writeInt(3);
      this.dataOutput.flush();
      char c = '\000';
      try
      {
        c = (char)this.dataInput.readByte();
      }
      catch (EOFException localEOFException)
      {
        this.server.printWithThread("Looks like we got a goofy psql no-auth attempt.  Will probably retry properly very shortly");
        return;
      }
      if (c != 'p') {
        throw new ClientFailure("Expected password prefix 'p', but got '" + c + "'", "Password value not prefixed with 'p'");
      }
      int m = this.dataInput.readInt() - 5;
      if (m < 0) {
        throw new ClientFailure("Client submitted invalid password length " + m, "Invalid password length " + m);
      }
      String str2 = readNullTermdUTF(m, this.dataInput);
      this.dbIndex = this.server.getDBIndex(str1);
      this.dbID = this.server.dbID[this.dbIndex];
      if (!this.server.isSilent()) {
        this.server.printWithThread(this.mThread + ":Trying to connect user '" + this.user + "' to DB (" + str1 + ')');
      }
      try
      {
        this.session = DatabaseManager.newSession(this.dbID, this.user, str2, null, 0);
      }
      catch (Exception localException)
      {
        throw new ClientFailure("User name or password denied: " + localException, "Login attempt rejected");
      }
    }
    catch (ClientFailure localClientFailure)
    {
      this.server.print(localClientFailure.toString());
      OdbcUtil.alertClient(1, localClientFailure.getClientMessage(), "08006", this.dataOutput);
      return;
    }
    this.outPacket = OdbcPacketOutputStream.newOdbcPacketOutputStream();
    this.outPacket.writeInt(0);
    this.outPacket.xmit('R', this.dataOutput);
    for (int k = 0; k < OdbcUtil.hardcodedParams.length; k++) {
      OdbcUtil.writeParam(OdbcUtil.hardcodedParams[k][0], OdbcUtil.hardcodedParams[k][1], this.dataOutput);
    }
    this.outPacket.writeByte(73);
    this.outPacket.xmit('Z', this.dataOutput);
    OdbcUtil.alertClient(7, "MHello\nYou have connected to HyperSQL ODBC Server", this.dataOutput);
    this.dataOutput.flush();
  }
  
  private static String readNullTermdUTF(int paramInt, InputStream paramInputStream)
    throws IOException
  {
    int i = 0;
    byte[] arrayOfByte = new byte[paramInt + 3];
    arrayOfByte[0] = ((byte)(paramInt >>> 8));
    arrayOfByte[1] = ((byte)paramInt);
    while (i < paramInt + 1) {
      i += paramInputStream.read(arrayOfByte, 2 + i, paramInt + 1 - i);
    }
    if (arrayOfByte[(arrayOfByte.length - 1)] != 0) {
      throw new IOException("String not null-terminated");
    }
    for (int j = 2; j < arrayOfByte.length - 1; j++) {
      if (arrayOfByte[j] == 0) {
        throw new RuntimeException("Null internal to String at offset " + (j - 2));
      }
    }
    DataInputStream localDataInputStream = new DataInputStream(new ByteArrayInputStream(arrayOfByte));
    String str = localDataInputStream.readUTF();
    localDataInputStream.close();
    return str;
  }
  
  private void odbcExecDirect(String paramString)
    throws RecoverableOdbcFailure, IOException
  {
    String str1 = paramString;
    String str2 = str1.trim().toLowerCase();
    if ((str2.startsWith("release ")) && (!str2.startsWith("release savepoint")))
    {
      this.server.printWithThread("Transmogrifying 'RELEASE ...' to 'RELEASE SAVEPOINT...");
      str1 = str1.trim().substring(0, "release ".length()) + "SAVEPOINT " + str1.trim().substring("release ".length());
    }
    Result localResult1 = Result.newExecuteDirectRequest();
    localResult1.setPrepareOrExecuteProperties(str1, 0, 0, 1, 0, 0, 2, null, null);
    Result localResult2 = this.session.execute(localResult1);
    switch (localResult2.getType())
    {
    case 1: 
      break;
    case 2: 
      throw new RecoverableOdbcFailure(localResult2);
    default: 
      throw new RecoverableOdbcFailure("Output Result from execution is of unexpected type: " + localResult2.getType());
    }
    this.outPacket.reset();
    this.outPacket.write(OdbcUtil.echoBackReplyString(str2, localResult2.getUpdateCount()));
    this.outPacket.xmit('C', this.dataOutput);
    if ((str2.equals("commit")) || (str2.startsWith("commit ")) || (str2.equals("rollback")) || (str2.startsWith("rollback "))) {
      try
      {
        this.session.setAutoCommit(true);
      }
      catch (HsqlException localHsqlException)
      {
        throw new RecoverableOdbcFailure("Failed to change transaction state: " + localHsqlException.getMessage(), localHsqlException.getSQLState());
      }
    }
  }
  
  static
  {
    int i = BundleHandler.getBundleHandle("org_hsqldb_Server_messages", null);
    if (i < 0) {
      throw new RuntimeException("MISSING Resource Bundle.  See source code");
    }
    TEXTBANNER_PART1 = BundleHandler.getString(i, "textbanner.part1");
    TEXTBANNER_PART2 = BundleHandler.getString(i, "textbanner.part2");
    if ((TEXTBANNER_PART1 == null) || (TEXTBANNER_PART2 == null)) {
      throw new RuntimeException("MISSING Resource Bundle msg definition.  See source code");
    }
  }
  
  private static class ClientFailure
    extends Exception
  {
    private String clientMessage = null;
    
    public ClientFailure(String paramString1, String paramString2)
    {
      super();
      this.clientMessage = paramString2;
    }
    
    public String getClientMessage()
    {
      return this.clientMessage;
    }
  }
  
  private static class CleanExit
    extends Exception
  {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.server.ServerConnection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */