package org.hsqldb.scriptio;

import org.hsqldb.Database;
import org.hsqldb.NumberSequence;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.persist.PersistentStore;

public abstract class ScriptReaderBase
{
  public static final int ANY_STATEMENT = 1;
  public static final int DELETE_STATEMENT = 2;
  public static final int INSERT_STATEMENT = 3;
  public static final int COMMIT_STATEMENT = 4;
  public static final int SESSION_ID = 5;
  public static final int SET_SCHEMA_STATEMENT = 6;
  Database database;
  int lineCount;
  int statementType;
  int sessionNumber;
  boolean sessionChanged;
  Object[] rowData;
  long sequenceValue;
  String statement;
  Table currentTable;
  PersistentStore currentStore;
  NumberSequence currentSequence;
  String currentSchema;

  ScriptReaderBase(Database paramDatabase)
  {
    this.database = paramDatabase;
  }

  public void readAll(Session paramSession)
  {
    readDDL(paramSession);
    readExistingData(paramSession);
  }

  protected abstract void readDDL(Session paramSession);

  protected abstract void readExistingData(Session paramSession);

  public abstract boolean readLoggedStatement(Session paramSession);

  public int getStatementType()
  {
    return this.statementType;
  }

  public int getSessionNumber()
  {
    return this.sessionNumber;
  }

  public Object[] getData()
  {
    return this.rowData;
  }

  public String getLoggedStatement()
  {
    return this.statement;
  }

  public NumberSequence getCurrentSequence()
  {
    return this.currentSequence;
  }

  public long getSequenceValue()
  {
    return this.sequenceValue;
  }

  public Table getCurrentTable()
  {
    return this.currentTable;
  }

  public String getCurrentSchema()
  {
    return this.currentSchema;
  }

  public int getLineNumber()
  {
    return this.lineCount;
  }

  public abstract void close();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.scriptio.ScriptReaderBase
 * JD-Core Version:    0.6.2
 */