package org.hsqldb.scriptio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;
import org.hsqldb.Database;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.NumberSequence;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.FileAccess.FileSync;
import org.hsqldb.rowio.RowOutputTextLog;

public class ScriptWriterText extends ScriptWriterBase
{
  RowOutputTextLog rowOut;
  public static final String ISO_8859_1 = "ISO-8859-1";
  public static byte[] BYTES_LINE_SEP;
  static byte[] BYTES_COMMIT;
  static byte[] BYTES_INSERT_INTO;
  static byte[] BYTES_VALUES;
  static byte[] BYTES_TERM;
  static byte[] BYTES_DELETE_FROM;
  static byte[] BYTES_WHERE;
  static byte[] BYTES_SEQUENCE;
  static byte[] BYTES_SEQUENCE_MID;
  static byte[] BYTES_C_ID_INIT;
  static byte[] BYTES_C_ID_TERM;
  static byte[] BYTES_SCHEMA;

  public ScriptWriterText(Database paramDatabase, OutputStream paramOutputStream, FileAccess.FileSync paramFileSync, boolean paramBoolean)
  {
    super(paramDatabase, paramOutputStream, paramFileSync, paramBoolean);
  }

  public ScriptWriterText(Database paramDatabase, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    super(paramDatabase, paramString, paramBoolean1, paramBoolean2, paramBoolean3);
  }

  public ScriptWriterText(Database paramDatabase, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramDatabase, paramString, paramBoolean1, true, false);
    if (paramBoolean2)
    {
      this.isCompressed = true;
      try
      {
        this.fileStreamOut = new GZIPOutputStream(this.fileStreamOut);
      }
      catch (IOException localIOException)
      {
        throw Error.error(localIOException, 452, 26, new Object[] { localIOException.toString(), this.outFile });
      }
    }
  }

  protected void initBuffers()
  {
    this.rowOut = new RowOutputTextLog();
  }

  protected void writeDataTerm()
    throws IOException
  {
  }

  protected void writeSessionIdAndSchema(Session paramSession)
    throws IOException
  {
    if (paramSession == null)
      return;
    if (paramSession != this.currentSession)
    {
      this.rowOut.reset();
      this.rowOut.write(BYTES_C_ID_INIT);
      this.rowOut.writeLong(paramSession.getId());
      this.rowOut.write(BYTES_C_ID_TERM);
      this.currentSession = paramSession;
      writeRowOutToFile();
    }
    if (this.schemaToLog != paramSession.loggedSchema)
    {
      this.rowOut.reset();
      writeSchemaStatement(this.schemaToLog);
      paramSession.loggedSchema = this.schemaToLog;
      writeRowOutToFile();
    }
  }

  private void writeSchemaStatement(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.rowOut.write(BYTES_SCHEMA);
    this.rowOut.writeString(paramHsqlName.statementName);
    this.rowOut.write(BYTES_LINE_SEP);
  }

  public void writeLogStatement(Session paramSession, String paramString)
    throws IOException
  {
    this.schemaToLog = paramSession.currentSchema;
    writeSessionIdAndSchema(paramSession);
    this.rowOut.reset();
    this.rowOut.writeString(paramString);
    this.rowOut.write(BYTES_LINE_SEP);
    writeRowOutToFile();
    this.needsSync = true;
  }

  protected void writeRow(Session paramSession, Row paramRow, Table paramTable)
    throws IOException
  {
    this.schemaToLog = paramTable.getName().schema;
    writeSessionIdAndSchema(paramSession);
    this.rowOut.reset();
    this.rowOut.setMode(0);
    this.rowOut.write(BYTES_INSERT_INTO);
    this.rowOut.writeString(paramTable.getName().statementName);
    this.rowOut.write(BYTES_VALUES);
    this.rowOut.writeData(paramRow, paramTable.getColumnTypes());
    this.rowOut.write(BYTES_TERM);
    this.rowOut.write(BYTES_LINE_SEP);
    writeRowOutToFile();
  }

  protected void writeTableInit(Table paramTable)
    throws IOException
  {
    if (paramTable.isEmpty(this.currentSession))
      return;
    if (this.schemaToLog == this.currentSession.loggedSchema)
      return;
    this.rowOut.reset();
    writeSchemaStatement(paramTable.getName().schema);
    writeRowOutToFile();
    this.currentSession.loggedSchema = this.schemaToLog;
  }

  public void writeOtherStatement(Session paramSession, String paramString)
    throws IOException
  {
    writeLogStatement(paramSession, paramString);
    if (this.writeDelay == 0)
      sync();
  }

  public void writeInsertStatement(Session paramSession, Row paramRow, Table paramTable)
    throws IOException
  {
    this.schemaToLog = paramTable.getName().schema;
    writeRow(paramSession, paramRow, paramTable);
  }

  public void writeDeleteStatement(Session paramSession, Table paramTable, Object[] paramArrayOfObject)
    throws IOException
  {
    this.schemaToLog = paramTable.getName().schema;
    writeSessionIdAndSchema(paramSession);
    this.rowOut.reset();
    this.rowOut.setMode(1);
    this.rowOut.write(BYTES_DELETE_FROM);
    this.rowOut.writeString(paramTable.getName().statementName);
    this.rowOut.write(BYTES_WHERE);
    this.rowOut.writeData(paramTable.getColumnCount(), paramTable.getColumnTypes(), paramArrayOfObject, paramTable.columnList, paramTable.getPrimaryKey());
    this.rowOut.write(BYTES_LINE_SEP);
    writeRowOutToFile();
  }

  public void writeSequenceStatement(Session paramSession, NumberSequence paramNumberSequence)
    throws IOException
  {
    this.schemaToLog = paramNumberSequence.getName().schema;
    writeSessionIdAndSchema(paramSession);
    this.rowOut.reset();
    this.rowOut.write(BYTES_SEQUENCE);
    this.rowOut.writeString(paramNumberSequence.getSchemaName().statementName);
    this.rowOut.write(46);
    this.rowOut.writeString(paramNumberSequence.getName().statementName);
    this.rowOut.write(BYTES_SEQUENCE_MID);
    this.rowOut.writeLong(paramNumberSequence.peek());
    this.rowOut.write(BYTES_LINE_SEP);
    writeRowOutToFile();
    this.needsSync = true;
  }

  public void writeCommitStatement(Session paramSession)
    throws IOException
  {
    writeSessionIdAndSchema(paramSession);
    this.rowOut.reset();
    this.rowOut.write(BYTES_COMMIT);
    this.rowOut.write(BYTES_LINE_SEP);
    writeRowOutToFile();
    this.needsSync = true;
    if (this.writeDelay == 0)
      sync();
  }

  protected void finishStream()
    throws IOException
  {
    if (this.isCompressed)
      ((GZIPOutputStream)this.fileStreamOut).finish();
  }

  void writeRowOutToFile()
    throws IOException
  {
    synchronized (this.fileStreamOut)
    {
      this.fileStreamOut.write(this.rowOut.getBuffer(), 0, this.rowOut.size());
      this.byteCount += this.rowOut.size();
      this.lineCount += 1L;
    }
  }

  static
  {
    String str = System.getProperty("line.separator", "\n");
    try
    {
      BYTES_LINE_SEP = str.getBytes();
      BYTES_COMMIT = "COMMIT".getBytes("ISO-8859-1");
      BYTES_INSERT_INTO = "INSERT INTO ".getBytes("ISO-8859-1");
      BYTES_VALUES = " VALUES(".getBytes("ISO-8859-1");
      BYTES_TERM = ")".getBytes("ISO-8859-1");
      BYTES_DELETE_FROM = "DELETE FROM ".getBytes("ISO-8859-1");
      BYTES_WHERE = " WHERE ".getBytes("ISO-8859-1");
      BYTES_SEQUENCE = "ALTER SEQUENCE ".getBytes("ISO-8859-1");
      BYTES_SEQUENCE_MID = " RESTART WITH ".getBytes("ISO-8859-1");
      BYTES_C_ID_INIT = "/*C".getBytes("ISO-8859-1");
      BYTES_C_ID_TERM = "*/".getBytes("ISO-8859-1");
      BYTES_SCHEMA = "SET SCHEMA ".getBytes("ISO-8859-1");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Error.runtimeError(201, "ScriptWriterText");
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.scriptio.ScriptWriterText
 * JD-Core Version:    0.6.2
 */