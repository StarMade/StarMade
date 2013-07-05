package org.hsqldb.persist;

import java.io.File;
import java.io.IOException;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.NumberSequence;
import org.hsqldb.Row;
import org.hsqldb.SchemaManager;
import org.hsqldb.Session;
import org.hsqldb.SessionManager;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileArchiver;
import org.hsqldb.scriptio.ScriptReaderBase;
import org.hsqldb.scriptio.ScriptReaderDecode;
import org.hsqldb.scriptio.ScriptReaderText;
import org.hsqldb.scriptio.ScriptWriterBase;
import org.hsqldb.scriptio.ScriptWriterEncode;
import org.hsqldb.scriptio.ScriptWriterText;

public class Log
{
  private HsqlDatabaseProperties properties;
  private String fileName;
  private Database database;
  private FileAccess fa;
  ScriptWriterBase dbLogWriter;
  private String scriptFileName;
  private String logFileName;
  private boolean filesReadOnly;
  private long maxLogSize;
  private int writeDelay;
  private DataFileCache cache;

  Log(Database paramDatabase)
  {
    this.database = paramDatabase;
    this.fa = paramDatabase.logger.getFileAccess();
    this.fileName = paramDatabase.getPath();
    this.properties = paramDatabase.getProperties();
  }

  void initParams()
  {
    this.maxLogSize = (this.database.logger.propLogSize * 1024L * 1024L);
    this.writeDelay = this.database.logger.propWriteDelay;
    this.filesReadOnly = this.database.isFilesReadOnly();
    this.scriptFileName = (this.fileName + ".script");
    this.logFileName = (this.fileName + ".log");
  }

  void open()
  {
    initParams();
    int i = this.properties.getDBModified();
    switch (i)
    {
    case 3:
      break;
    case 1:
      deleteNewAndOldFiles();
      deleteOldTempFiles();
      if (this.properties.isVersion18())
      {
        if (this.fa.isStreamElement(this.scriptFileName))
          processScript();
        else
          this.database.schemaManager.createPublicSchema();
        HsqlNameManager.HsqlName localHsqlName = this.database.schemaManager.findSchemaHsqlName("PUBLIC");
        if (localHsqlName != null)
          this.database.schemaManager.setDefaultSchemaHsqlName(localHsqlName);
      }
      else
      {
        processScript();
      }
      processLog();
      checkpoint();
      break;
    case 2:
      renameNewDataFile();
      renameNewBackup();
      renameNewScript();
      deleteLog();
      this.properties.setDBModified(0);
    case 0:
      processScript();
      if ((!this.filesReadOnly) && (isAnyCacheModified()))
      {
        this.properties.setDBModified(1);
        checkpoint();
      }
      break;
    }
    if (!this.filesReadOnly)
    {
      openLog();
      this.properties.setDBModified(1);
    }
  }

  void close(boolean paramBoolean)
  {
    closeLog();
    deleteOldDataFiles();
    deleteOldTempFiles();
    deleteTempFileDirectory();
    writeScript(paramBoolean);
    this.database.logger.closeAllTextCaches(paramBoolean);
    if (this.cache != null)
      this.cache.close(true);
    this.properties.setProperty("hsqldb.script_format", this.database.logger.propScriptFormat);
    this.properties.setDBModified(2);
    deleteLog();
    if (this.cache != null)
      if (paramBoolean)
      {
        this.cache.deleteFile();
        this.cache.deleteBackup();
      }
      else
      {
        this.cache.backupFile(false);
        this.cache.renameBackupFile();
      }
    renameNewScript();
    this.properties.setDBModified(0);
  }

  void shutdown()
  {
    if (this.cache != null)
      this.cache.close(false);
    this.database.logger.closeAllTextCaches(false);
    closeLog();
  }

  void deleteNewAndOldFiles()
  {
    deleteOldDataFiles();
    this.fa.removeElement(this.fileName + ".data" + ".new");
    this.fa.removeElement(this.fileName + ".backup" + ".new");
    this.fa.removeElement(this.scriptFileName + ".new");
  }

  void deleteBackup()
  {
    this.fa.removeElement(this.fileName + ".backup");
  }

  void deleteData()
  {
    this.fa.removeElement(this.fileName + ".data");
  }

  void backupData()
    throws IOException
  {
    if (this.database.logger.propIncrementBackup)
    {
      this.fa.removeElement(this.fileName + ".backup");
      return;
    }
    if (this.fa.isStreamElement(this.fileName + ".data"))
      FileArchiver.archive(this.fileName + ".data", this.fileName + ".backup" + ".new", this.database.logger.getFileAccess(), 1);
  }

  void renameNewDataFile()
  {
    if (this.fa.isStreamElement(this.fileName + ".data" + ".new"))
      this.fa.renameElement(this.fileName + ".data" + ".new", this.fileName + ".data");
  }

  void renameNewBackup()
  {
    this.fa.removeElement(this.fileName + ".backup");
    if (this.fa.isStreamElement(this.fileName + ".backup" + ".new"))
      this.fa.renameElement(this.fileName + ".backup" + ".new", this.fileName + ".backup");
  }

  void renameNewScript()
  {
    if (this.fa.isStreamElement(this.scriptFileName + ".new"))
      this.fa.renameElement(this.scriptFileName + ".new", this.scriptFileName);
  }

  void deleteNewScript()
  {
    this.fa.removeElement(this.scriptFileName + ".new");
  }

  void deleteNewBackup()
  {
    this.fa.removeElement(this.fileName + ".backup" + ".new");
  }

  void deleteLog()
  {
    this.fa.removeElement(this.logFileName);
  }

  boolean isAnyCacheModified()
  {
    if ((this.cache != null) && (this.cache.isModified()))
      return true;
    return this.database.logger.isAnyTextCacheModified();
  }

  void checkpoint()
  {
    if (this.filesReadOnly)
      return;
    boolean bool = checkpointClose();
    if (bool)
      checkpointReopen();
    else
      this.database.logger.logSevereEvent("checkpoint failed - see previous error", null);
  }

  void checkpoint(boolean paramBoolean)
  {
    if (this.filesReadOnly)
      return;
    if (this.cache == null)
      paramBoolean = false;
    else if (forceDefrag())
      paramBoolean = true;
    if (paramBoolean)
      try
      {
        defrag();
        this.database.sessionManager.resetLoggedSchemas();
        return;
      }
      catch (Throwable localThrowable)
      {
        this.database.logger.logSevereEvent("defrag failed", localThrowable);
      }
    checkpoint();
  }

  boolean checkpointClose()
  {
    if (this.filesReadOnly)
      return true;
    this.database.logger.logInfoEvent("checkpointClose start");
    synchLog();
    this.database.lobManager.synch();
    this.database.lobManager.deleteUnusedLobs();
    deleteOldDataFiles();
    try
    {
      writeScript(false);
    }
    catch (Throwable localThrowable1)
    {
      deleteNewScript();
      this.database.logger.logSevereEvent("checkpoint failed - recovered", localThrowable1);
      return false;
    }
    try
    {
      if (this.cache != null)
      {
        this.cache.commitChanges();
        this.cache.backupFile(false);
      }
    }
    catch (Throwable localThrowable2)
    {
      deleteNewScript();
      deleteNewBackup();
      try
      {
        if (!this.cache.isFileOpen())
          this.cache.open(false);
      }
      catch (Throwable localThrowable4)
      {
      }
      this.database.logger.logSevereEvent("checkpoint failed - recovered", localThrowable2);
      return false;
    }
    closeLog();
    this.properties.setProperty("hsqldb.script_format", this.database.logger.propScriptFormat);
    this.properties.setDBModified(2);
    deleteLog();
    renameNewScript();
    renameNewBackup();
    try
    {
      this.properties.setDBModified(0);
    }
    catch (Throwable localThrowable3)
    {
    }
    this.database.logger.logInfoEvent("checkpointClose end");
    return true;
  }

  boolean checkpointReopen()
  {
    if (this.filesReadOnly)
      return true;
    this.database.sessionManager.resetLoggedSchemas();
    try
    {
      if (this.cache != null)
        this.cache.openShadowFile();
      if (this.dbLogWriter != null)
        openLog();
      this.properties.setDBModified(1);
    }
    catch (Throwable localThrowable)
    {
      return false;
    }
    return true;
  }

  public void defrag()
  {
    if (this.cache.fileFreePosition == this.cache.initialFreePos)
      return;
    this.database.logger.logInfoEvent("defrag start");
    try
    {
      synchLog();
      this.database.lobManager.synch();
      deleteOldDataFiles();
      DataFileDefrag localDataFileDefrag = this.cache.defrag();
    }
    catch (HsqlException localHsqlException)
    {
      throw localHsqlException;
    }
    catch (Throwable localThrowable)
    {
      this.database.logger.logSevereEvent("defrag failure", localThrowable);
      throw Error.error(466, localThrowable);
    }
    this.database.logger.logInfoEvent("defrag end");
  }

  boolean forceDefrag()
  {
    long l1 = this.database.logger.propCacheDefragLimit * this.cache.getFileFreePos() / 100L;
    long l2 = this.cache.freeBlocks.getLostBlocksSize();
    return (l1 > 0L) && (l2 > l1);
  }

  boolean hasCache()
  {
    return this.cache != null;
  }

  DataFileCache getCache()
  {
    if (this.cache == null)
    {
      this.cache = new DataFileCache(this.database, this.fileName);
      this.cache.open(this.filesReadOnly);
    }
    return this.cache;
  }

  void setLogSize(int paramInt)
  {
    this.maxLogSize = (paramInt * 1024L * 1024L);
  }

  int getWriteDelay()
  {
    return this.writeDelay;
  }

  void setWriteDelay(int paramInt)
  {
    this.writeDelay = paramInt;
    if ((this.dbLogWriter != null) && (this.dbLogWriter.getWriteDelay() != paramInt))
    {
      this.dbLogWriter.forceSync();
      this.dbLogWriter.stop();
      this.dbLogWriter.setWriteDelay(paramInt);
      this.dbLogWriter.start();
    }
  }

  public void setIncrementBackup(boolean paramBoolean)
  {
    if (this.cache != null)
      this.cache.setIncrementBackup(paramBoolean);
  }

  void writeOtherStatement(Session paramSession, String paramString)
  {
    try
    {
      this.dbLogWriter.writeOtherStatement(paramSession, paramString);
    }
    catch (IOException localIOException)
    {
      throw Error.error(452, this.logFileName);
    }
    if ((this.maxLogSize > 0L) && (this.dbLogWriter.size() > this.maxLogSize))
      this.database.logger.setCheckpointRequired();
  }

  void writeInsertStatement(Session paramSession, Row paramRow, Table paramTable)
  {
    try
    {
      this.dbLogWriter.writeInsertStatement(paramSession, paramRow, paramTable);
    }
    catch (IOException localIOException)
    {
      throw Error.error(452, this.logFileName);
    }
    if ((this.maxLogSize > 0L) && (this.dbLogWriter.size() > this.maxLogSize))
      this.database.logger.setCheckpointRequired();
  }

  void writeDeleteStatement(Session paramSession, Table paramTable, Object[] paramArrayOfObject)
  {
    try
    {
      this.dbLogWriter.writeDeleteStatement(paramSession, paramTable, paramArrayOfObject);
    }
    catch (IOException localIOException)
    {
      throw Error.error(452, this.logFileName);
    }
    if ((this.maxLogSize > 0L) && (this.dbLogWriter.size() > this.maxLogSize))
      this.database.logger.setCheckpointRequired();
  }

  void writeSequenceStatement(Session paramSession, NumberSequence paramNumberSequence)
  {
    try
    {
      this.dbLogWriter.writeSequenceStatement(paramSession, paramNumberSequence);
    }
    catch (IOException localIOException)
    {
      throw Error.error(452, this.logFileName);
    }
    if ((this.maxLogSize > 0L) && (this.dbLogWriter.size() > this.maxLogSize))
      this.database.logger.setCheckpointRequired();
  }

  void writeCommitStatement(Session paramSession)
  {
    try
    {
      this.dbLogWriter.writeCommitStatement(paramSession);
    }
    catch (IOException localIOException)
    {
      throw Error.error(452, this.logFileName);
    }
    if ((this.maxLogSize > 0L) && (this.dbLogWriter.size() > this.maxLogSize))
      this.database.logger.setCheckpointRequired();
  }

  void synchLog()
  {
    if (this.dbLogWriter != null)
      this.dbLogWriter.forceSync();
  }

  void openLog()
  {
    if (this.filesReadOnly)
      return;
    Crypto localCrypto = this.database.logger.getCrypto();
    try
    {
      if (localCrypto == null)
        this.dbLogWriter = new ScriptWriterText(this.database, this.logFileName, false, false, false);
      else
        this.dbLogWriter = new ScriptWriterEncode(this.database, this.logFileName, localCrypto);
      this.dbLogWriter.setWriteDelay(this.writeDelay);
      this.dbLogWriter.start();
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(452, this.logFileName);
    }
  }

  synchronized void closeLog()
  {
    if (this.dbLogWriter != null)
    {
      this.database.logger.logDetailEvent("log close size: " + this.dbLogWriter.size());
      this.dbLogWriter.close();
    }
  }

  void writeScript(boolean paramBoolean)
  {
    deleteNewScript();
    Crypto localCrypto = this.database.logger.getCrypto();
    if (localCrypto == null)
    {
      boolean bool = this.database.logger.propScriptFormat == 3;
      localObject = new ScriptWriterText(this.database, this.scriptFileName + ".new", paramBoolean, bool);
    }
    else
    {
      localObject = new ScriptWriterEncode(this.database, this.scriptFileName + ".new", paramBoolean, localCrypto);
    }
    ((ScriptWriterBase)localObject).writeAll();
    ((ScriptWriterBase)localObject).close();
    Object localObject = null;
  }

  private void processScript()
  {
    Object localObject = null;
    try
    {
      Crypto localCrypto = this.database.logger.getCrypto();
      if (localCrypto == null)
      {
        boolean bool = this.database.logger.propScriptFormat == 3;
        localObject = new ScriptReaderText(this.database, this.scriptFileName, bool);
      }
      else
      {
        localObject = new ScriptReaderDecode(this.database, this.scriptFileName, localCrypto, false);
      }
      Session localSession = this.database.sessionManager.getSysSessionForScript(this.database);
      ((ScriptReaderBase)localObject).readAll(localSession);
      ((ScriptReaderBase)localObject).close();
    }
    catch (Throwable localThrowable)
    {
      if (localObject != null)
      {
        ((ScriptReaderBase)localObject).close();
        if (this.cache != null)
          this.cache.close(false);
        this.database.logger.closeAllTextCaches(false);
      }
      this.database.logger.logWarningEvent("Script processing failure", localThrowable);
      if ((localThrowable instanceof HsqlException))
        throw ((HsqlException)localThrowable);
      if ((localThrowable instanceof IOException))
        throw Error.error(452, localThrowable);
      if ((localThrowable instanceof OutOfMemoryError))
        throw Error.error(460);
      throw Error.error(458, localThrowable);
    }
  }

  private void processLog()
  {
    if (this.fa.isStreamElement(this.logFileName))
      ScriptRunner.runScript(this.database, this.logFileName);
  }

  void deleteOldDataFiles()
  {
    if (this.database.logger.isStoredFileAccess())
      return;
    try
    {
      File localFile = new File(this.database.getCanonicalPath());
      File[] arrayOfFile = localFile.getParentFile().listFiles();
      if (arrayOfFile == null)
        return;
      for (int i = 0; i < arrayOfFile.length; i++)
        if ((arrayOfFile[i].getName().startsWith(localFile.getName())) && (arrayOfFile[i].getName().endsWith(".old")))
          arrayOfFile[i].delete();
    }
    catch (Throwable localThrowable)
    {
    }
  }

  void deleteOldTempFiles()
  {
    try
    {
      if (this.database.logger.tempDirectoryPath == null)
        return;
      File localFile = new File(this.database.logger.tempDirectoryPath);
      File[] arrayOfFile = localFile.listFiles();
      if (arrayOfFile == null)
        return;
      for (int i = 0; i < arrayOfFile.length; i++)
        arrayOfFile[i].delete();
    }
    catch (Throwable localThrowable)
    {
    }
  }

  void deleteTempFileDirectory()
  {
    try
    {
      if (this.database.logger.tempDirectoryPath == null)
        return;
      File localFile = new File(this.database.logger.tempDirectoryPath);
      localFile.delete();
    }
    catch (Throwable localThrowable)
    {
    }
  }

  String getLogFileName()
  {
    return this.logFileName;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.Log
 * JD-Core Version:    0.6.2
 */