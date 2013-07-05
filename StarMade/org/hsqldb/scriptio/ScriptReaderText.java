package org.hsqldb.scriptio;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.SchemaManager;
import org.hsqldb.Session;
import org.hsqldb.Statement;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.LineReader;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStoreCollectionDatabase;
import org.hsqldb.result.Result;
import org.hsqldb.rowio.RowInputTextLog;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Type;

public class ScriptReaderText extends ScriptReaderBase
{
  LineReader dataStreamIn;
  RowInputTextLog rowIn;
  boolean isInsert;

  public ScriptReaderText(Database paramDatabase)
  {
    super(paramDatabase);
  }

  public ScriptReaderText(Database paramDatabase, String paramString, boolean paramBoolean)
    throws IOException
  {
    super(paramDatabase);
    Object localObject = this.database.logger.getFileAccess().openInputStreamElement(paramString);
    localObject = new BufferedInputStream((InputStream)localObject);
    if (paramBoolean)
      localObject = new GZIPInputStream((InputStream)localObject);
    this.dataStreamIn = new LineReader((InputStream)localObject, "ISO-8859-1");
    this.rowIn = new RowInputTextLog(paramDatabase.databaseProperties.isVersion18());
  }

  public ScriptReaderText(Database paramDatabase, InputStream paramInputStream)
  {
    super(paramDatabase);
    this.dataStreamIn = new LineReader(paramInputStream, "ISO-8859-1");
    this.rowIn = new RowInputTextLog(paramDatabase.databaseProperties.isVersion18());
  }

  protected void readDDL(Session paramSession)
  {
    while (readLoggedStatement(paramSession))
    {
      Statement localStatement = null;
      Result localResult = null;
      if (this.rowIn.getStatementType() == 3)
      {
        this.isInsert = true;
        break;
      }
      try
      {
        localStatement = paramSession.compileStatement(this.statement);
        localResult = paramSession.executeCompiledStatement(localStatement, ValuePool.emptyObjectArray);
      }
      catch (HsqlException localHsqlException)
      {
        localResult = Result.newErrorResult(localHsqlException);
      }
      if ((!localResult.isError()) || (localStatement == null) || ((localStatement.getType() != 48) && ((localStatement.getType() != 14) || (!localResult.getMainString().contains("org.hsqldb.Library")))))
        if (localResult.isError())
        {
          this.database.logger.logWarningEvent(localResult.getMainString(), localResult.getException());
          if ((localStatement == null) || (localStatement.getType() != 14))
            throw Error.error(localResult.getException(), 461, 25, new Object[] { new Integer(this.lineCount), localResult.getMainString() });
        }
    }
  }

  protected void readExistingData(Session paramSession)
  {
    try
    {
      Object localObject = null;
      this.database.setReferentialIntegrity(false);
      while ((this.isInsert) || (readLoggedStatement(paramSession)))
      {
        if (this.statementType == 6)
        {
          paramSession.setSchema(this.currentSchema);
        }
        else if (this.statementType == 3)
        {
          if (!this.rowIn.getTableName().equals(localObject))
          {
            localObject = this.rowIn.getTableName();
            String str = paramSession.getSchemaName(this.currentSchema);
            this.currentTable = this.database.schemaManager.getUserTable(paramSession, (String)localObject, str);
            this.currentStore = this.database.persistentStoreCollection.getStore(this.currentTable);
          }
          this.currentTable.insertFromScript(paramSession, this.currentStore, this.rowData);
        }
        else
        {
          throw Error.error(461, this.statement);
        }
        this.isInsert = false;
      }
      this.database.setReferentialIntegrity(true);
    }
    catch (Throwable localThrowable)
    {
      this.database.logger.logSevereEvent("readExistingData failed", localThrowable);
      throw Error.error(localThrowable, 461, 25, new Object[] { new Integer(this.lineCount), localThrowable.toString() });
    }
  }

  public boolean readLoggedStatement(Session paramSession)
  {
    if (!this.sessionChanged)
    {
      String str;
      try
      {
        str = this.dataStreamIn.readLine();
      }
      catch (EOFException localEOFException)
      {
        return false;
      }
      catch (IOException localIOException)
      {
        throw Error.error(localIOException, 452, null);
      }
      this.lineCount += 1;
      this.statement = StringConverter.unicodeStringToString(str);
      if (this.statement == null)
        return false;
    }
    processStatement(paramSession);
    return true;
  }

  void processStatement(Session paramSession)
  {
    if (this.statement.startsWith("/*C"))
    {
      int i = this.statement.indexOf('*', 4);
      this.sessionNumber = Integer.parseInt(this.statement.substring(3, i));
      this.statement = this.statement.substring(i + 2);
      this.sessionChanged = true;
      this.statementType = 5;
      return;
    }
    this.sessionChanged = false;
    this.rowIn.setSource(this.statement);
    this.statementType = this.rowIn.getStatementType();
    if (this.statementType == 1)
    {
      this.rowData = null;
      this.currentTable = null;
      return;
    }
    if (this.statementType == 4)
    {
      this.rowData = null;
      this.currentTable = null;
      return;
    }
    if (this.statementType == 6)
    {
      this.rowData = null;
      this.currentTable = null;
      this.currentSchema = this.rowIn.getSchemaName();
      return;
    }
    String str1 = this.rowIn.getTableName();
    String str2 = paramSession.getCurrentSchemaHsqlName().name;
    this.currentTable = this.database.schemaManager.getUserTable(paramSession, str1, str2);
    this.currentStore = this.database.persistentStoreCollection.getStore(this.currentTable);
    Type[] arrayOfType;
    if (this.statementType == 3)
      arrayOfType = this.currentTable.getColumnTypes();
    else if (this.currentTable.hasPrimaryKey())
      arrayOfType = this.currentTable.getPrimaryKeyTypes();
    else
      arrayOfType = this.currentTable.getColumnTypes();
    try
    {
      this.rowData = this.rowIn.readData(arrayOfType);
    }
    catch (IOException localIOException)
    {
      throw Error.error(localIOException, 452, null);
    }
  }

  public void close()
  {
    try
    {
      this.dataStreamIn.close();
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.scriptio.ScriptReaderText
 * JD-Core Version:    0.6.2
 */