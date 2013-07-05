package org.hsqldb.scriptio;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.hsqldb.Database;
import org.hsqldb.DatabaseManager;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.NumberSequence;
import org.hsqldb.Row;
import org.hsqldb.SchemaManager;
import org.hsqldb.Session;
import org.hsqldb.SessionManager;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileAccess.FileSync;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HsqlTimer;
import org.hsqldb.lib.Iterator;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.persist.Logger;
import org.hsqldb.result.Result;

public abstract class ScriptWriterBase
  implements Runnable
{
  Database database;
  String outFile;
  OutputStream fileStreamOut;
  FileAccess.FileSync outDescriptor;
  int tableRowCount;
  HsqlNameManager.HsqlName schemaToLog;
  boolean isClosed;
  boolean isCompressed;
  boolean isCrypt;
  boolean isDump;
  boolean includeCachedData;
  long byteCount;
  long lineCount;
  volatile boolean needsSync;
  private int syncCount;
  static final int INSERT = 0;
  static final int INSERT_WITH_SCHEMA = 1;
  Session currentSession;
  public static final String[] LIST_SCRIPT_FORMATS = { "TEXT", "BINARY", null, "COMPRESSED" };
  private Object timerTask;
  protected volatile int writeDelay = 60000;

  ScriptWriterBase(Database paramDatabase, OutputStream paramOutputStream, FileAccess.FileSync paramFileSync, boolean paramBoolean)
  {
    initBuffers();
    this.database = paramDatabase;
    this.includeCachedData = paramBoolean;
    this.currentSession = this.database.sessionManager.getSysSession();
    this.schemaToLog = (this.currentSession.loggedSchema = this.currentSession.currentSchema);
    this.fileStreamOut = new BufferedOutputStream(paramOutputStream, 16384);
    this.outDescriptor = paramFileSync;
  }

  ScriptWriterBase(Database paramDatabase, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    initBuffers();
    boolean bool = false;
    if (paramBoolean3)
      bool = FileUtil.getFileUtil().exists(paramString);
    else
      bool = paramDatabase.logger.getFileAccess().isStreamElement(paramString);
    if ((bool) && (paramBoolean2))
      throw Error.error(452, paramString);
    this.database = paramDatabase;
    this.isDump = paramBoolean3;
    this.includeCachedData = paramBoolean1;
    this.outFile = paramString;
    this.currentSession = this.database.sessionManager.getSysSession();
    this.schemaToLog = (this.currentSession.loggedSchema = this.currentSession.currentSchema);
    openFile();
  }

  protected abstract void initBuffers();

  public void sync()
  {
    if (this.isClosed)
      return;
    if (this.needsSync)
      forceSync();
  }

  public void forceSync()
  {
    if (this.isClosed)
      return;
    this.needsSync = false;
    synchronized (this.fileStreamOut)
    {
      try
      {
        this.fileStreamOut.flush();
        this.outDescriptor.sync();
        this.syncCount += 1;
      }
      catch (IOException localIOException)
      {
        this.database.logger.logWarningEvent("ScriptWriter synch error: ", localIOException);
      }
    }
  }

  public void close()
  {
    stop();
    if (this.isClosed)
      return;
    try
    {
      synchronized (this.fileStreamOut)
      {
        finishStream();
        forceSync();
        this.fileStreamOut.close();
        this.fileStreamOut = null;
        this.outDescriptor = null;
        this.isClosed = true;
      }
    }
    catch (IOException localIOException)
    {
      throw Error.error(452);
    }
    this.byteCount = 0L;
    this.lineCount = 0L;
  }

  public long size()
  {
    return this.byteCount;
  }

  public void writeAll()
  {
    try
    {
      writeDDL();
      writeExistingData();
    }
    catch (IOException localIOException)
    {
      throw Error.error(452);
    }
  }

  protected void openFile()
  {
    try
    {
      FileAccess localFileAccess = this.isDump ? FileUtil.getFileUtil() : this.database.logger.getFileAccess();
      OutputStream localOutputStream = localFileAccess.openOutputStreamElement(this.outFile);
      this.outDescriptor = localFileAccess.getFileSync(localOutputStream);
      this.fileStreamOut = localOutputStream;
      this.fileStreamOut = new BufferedOutputStream(localOutputStream, 16384);
    }
    catch (IOException localIOException)
    {
      throw Error.error(localIOException, 452, 26, new Object[] { localIOException.toString(), this.outFile });
    }
  }

  protected void finishStream()
    throws IOException
  {
  }

  protected void writeDDL()
    throws IOException
  {
    Result localResult = this.database.getScript(!this.includeCachedData);
    writeSingleColumnResult(localResult);
  }

  protected void writeExistingData()
    throws IOException
  {
    this.currentSession.loggedSchema = null;
    String[] arrayOfString = this.database.schemaManager.getSchemaNamesArray();
    for (int i = 0; i < arrayOfString.length; i++)
    {
      String str = arrayOfString[i];
      Iterator localIterator = this.database.schemaManager.databaseObjectIterator(str, 3);
      while (localIterator.hasNext())
      {
        Table localTable = (Table)localIterator.next();
        boolean bool = false;
        switch (localTable.getTableType())
        {
        case 4:
          bool = true;
          break;
        case 5:
          bool = this.includeCachedData;
          break;
        case 7:
          bool = (this.includeCachedData) && (!localTable.isReadOnly());
        case 6:
        }
        try
        {
          if (bool)
          {
            this.schemaToLog = localTable.getName().schema;
            writeTableInit(localTable);
            RowIterator localRowIterator = localTable.rowIteratorClustered(this.currentSession);
            while (localRowIterator.hasNext())
            {
              Row localRow = localRowIterator.getNextRow();
              writeRow(this.currentSession, localRow, localTable);
            }
            writeTableTerm(localTable);
          }
        }
        catch (Exception localException)
        {
          throw Error.error(452, localException.toString());
        }
      }
    }
    writeDataTerm();
  }

  protected void writeTableInit(Table paramTable)
    throws IOException
  {
  }

  protected void writeTableTerm(Table paramTable)
    throws IOException
  {
  }

  protected void writeSingleColumnResult(Result paramResult)
    throws IOException
  {
    RowSetNavigator localRowSetNavigator = paramResult.initialiseNavigator();
    while (localRowSetNavigator.hasNext())
    {
      Object[] arrayOfObject = (Object[])localRowSetNavigator.getNext();
      writeLogStatement(this.currentSession, (String)arrayOfObject[0]);
    }
  }

  abstract void writeRow(Session paramSession, Row paramRow, Table paramTable)
    throws IOException;

  protected abstract void writeDataTerm()
    throws IOException;

  protected abstract void writeSessionIdAndSchema(Session paramSession)
    throws IOException;

  public abstract void writeLogStatement(Session paramSession, String paramString)
    throws IOException;

  public abstract void writeOtherStatement(Session paramSession, String paramString)
    throws IOException;

  public abstract void writeInsertStatement(Session paramSession, Row paramRow, Table paramTable)
    throws IOException;

  public abstract void writeDeleteStatement(Session paramSession, Table paramTable, Object[] paramArrayOfObject)
    throws IOException;

  public abstract void writeSequenceStatement(Session paramSession, NumberSequence paramNumberSequence)
    throws IOException;

  public abstract void writeCommitStatement(Session paramSession)
    throws IOException;

  public void run()
  {
    try
    {
      if (this.writeDelay != 0)
        sync();
    }
    catch (Exception localException)
    {
    }
  }

  public void setWriteDelay(int paramInt)
  {
    this.writeDelay = paramInt;
  }

  public void start()
  {
    if (this.writeDelay > 0)
      this.timerTask = DatabaseManager.getTimer().schedulePeriodicallyAfter(0L, this.writeDelay, this, false);
  }

  public void stop()
  {
    if (this.timerTask != null)
    {
      HsqlTimer.cancel(this.timerTask);
      this.timerTask = null;
    }
  }

  public int getWriteDelay()
  {
    return this.writeDelay;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.scriptio.ScriptWriterBase
 * JD-Core Version:    0.6.2
 */