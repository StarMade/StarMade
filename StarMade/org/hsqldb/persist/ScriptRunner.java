package org.hsqldb.persist;

import java.io.EOFException;
import java.io.InputStream;
import org.hsqldb.ColumnSchema;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Row;
import org.hsqldb.SchemaManager;
import org.hsqldb.Session;
import org.hsqldb.SessionManager;
import org.hsqldb.Statement;
import org.hsqldb.StatementDML;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.StopWatch;
import org.hsqldb.result.Result;
import org.hsqldb.scriptio.ScriptReaderBase;
import org.hsqldb.scriptio.ScriptReaderDecode;
import org.hsqldb.scriptio.ScriptReaderText;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Type;

public class ScriptRunner
{
  public static void runScript(Database paramDatabase, InputStream paramInputStream)
  {
    Crypto localCrypto = paramDatabase.logger.getCrypto();
    Object localObject;
    if (localCrypto == null)
      localObject = new ScriptReaderText(paramDatabase, paramInputStream);
    else
      try
      {
        localObject = new ScriptReaderDecode(paramDatabase, paramInputStream, localCrypto, true);
      }
      catch (Throwable localThrowable)
      {
        paramDatabase.logger.logSevereEvent("opening log file", localThrowable);
        return;
      }
    runScript(paramDatabase, (ScriptReaderBase)localObject);
  }

  public static void runScript(Database paramDatabase, String paramString)
  {
    Crypto localCrypto = paramDatabase.logger.getCrypto();
    Object localObject;
    try
    {
      if (localCrypto == null)
        localObject = new ScriptReaderText(paramDatabase, paramString, false);
      else
        localObject = new ScriptReaderDecode(paramDatabase, paramString, localCrypto, true);
    }
    catch (Throwable localThrowable)
    {
      if (!(localThrowable instanceof EOFException))
        paramDatabase.logger.logSevereEvent("opening log file", localThrowable);
      return;
    }
    runScript(paramDatabase, (ScriptReaderBase)localObject);
  }

  private static void runScript(Database paramDatabase, ScriptReaderBase paramScriptReaderBase)
  {
    IntKeyHashMap localIntKeyHashMap = new IntKeyHashMap();
    Session localSession = null;
    int i = 0;
    StatementDML localStatementDML = new StatementDML(81, 2004, null);
    String str2 = paramDatabase.getPath();
    boolean bool = paramDatabase.getURLProperties().isPropertyTrue("hsqldb.full_log_replay");
    localStatementDML.setCompileTimestamp(9223372036854775807L);
    paramDatabase.setReferentialIntegrity(false);
    try
    {
      StopWatch localStopWatch = new StopWatch();
      while (paramScriptReaderBase.readLoggedStatement(localSession))
      {
        int k = paramScriptReaderBase.getSessionNumber();
        if ((localSession == null) || (i != k))
        {
          i = k;
          localSession = (Session)localIntKeyHashMap.get(i);
          if (localSession == null)
          {
            localSession = paramDatabase.getSessionManager().newSessionForLog(paramDatabase);
            localIntKeyHashMap.put(i, localSession);
          }
        }
        if (localSession.isClosed())
        {
          localIntKeyHashMap.remove(i);
        }
        else
        {
          Result localResult = null;
          int j = paramScriptReaderBase.getStatementType();
          Object localObject2;
          Object localObject1;
          switch (j)
          {
          case 1:
            String str1 = paramScriptReaderBase.getLoggedStatement();
            try
            {
              Statement localStatement = localSession.compileStatement(str1);
              if ((paramDatabase.getProperties().isVersion18()) && (localStatement.getType() == 77))
              {
                Table localTable = (Table)((org.hsqldb.StatementSchema)localStatement).getArguments()[0];
                for (int m = 0; m < localTable.getColumnCount(); m++)
                {
                  localObject2 = localTable.getColumn(m);
                  if (((ColumnSchema)localObject2).getDataType().isBitType())
                    ((ColumnSchema)localObject2).setType(Type.SQL_BOOLEAN);
                }
              }
              localResult = localSession.executeCompiledStatement(localStatement, ValuePool.emptyObjectArray);
            }
            catch (Throwable localThrowable2)
            {
              localResult = Result.newErrorResult(localThrowable2);
            }
            if ((localResult != null) && (localResult.isError()))
            {
              if (localResult.getException() != null)
                throw localResult.getException();
              throw Error.error(localResult);
            }
            break;
          case 4:
            localSession.commit(false);
            break;
          case 3:
            localSession.sessionContext.currentStatement = localStatementDML;
            localSession.beginAction(localStatementDML);
            localObject1 = paramScriptReaderBase.getData();
            paramScriptReaderBase.getCurrentTable().insertNoCheckFromLog(localSession, (Object[])localObject1);
            localSession.endAction(Result.updateOneResult);
            break;
          case 2:
            localSession.sessionContext.currentStatement = localStatementDML;
            localSession.beginAction(localStatementDML);
            localObject1 = paramScriptReaderBase.getCurrentTable();
            Object[] arrayOfObject = paramScriptReaderBase.getData();
            localObject2 = ((Table)localObject1).getDeleteRowFromLog(localSession, arrayOfObject);
            if (localObject2 != null)
              localSession.addDeleteAction((Table)localObject1, (Row)localObject2, null);
            localSession.endAction(Result.updateOneResult);
            break;
          case 6:
            localObject1 = paramDatabase.schemaManager.findSchemaHsqlName(paramScriptReaderBase.getCurrentSchema());
            localSession.setCurrentSchemaHsqlName((HsqlNameManager.HsqlName)localObject1);
            break;
          case 5:
          }
          if (localSession.isClosed())
            localIntKeyHashMap.remove(i);
        }
      }
    }
    catch (HsqlException localHsqlException)
    {
      str3 = "statement error processing log " + str2 + "line: " + paramScriptReaderBase.getLineNumber();
      paramDatabase.logger.logSevereEvent(str3, localHsqlException);
      if (bool)
        throw Error.error(localHsqlException, 461, str3);
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      str3 = "out of memory processing log" + str2 + " line: " + paramScriptReaderBase.getLineNumber();
      paramDatabase.logger.logSevereEvent(str3, localOutOfMemoryError);
      throw Error.error(460);
    }
    catch (Throwable localThrowable1)
    {
      String str3 = "statement error processing log " + str2 + "line: " + paramScriptReaderBase.getLineNumber();
      paramDatabase.logger.logSevereEvent(str3, localThrowable1);
      if (bool)
        throw Error.error(localThrowable1, 461, str3);
    }
    finally
    {
      if (paramScriptReaderBase != null)
        paramScriptReaderBase.close();
      paramDatabase.getSessionManager().closeAllSessions();
      paramDatabase.setReferentialIntegrity(true);
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.ScriptRunner
 * JD-Core Version:    0.6.2
 */