package org.hsqldb.result;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import org.hsqldb.ColumnBase;
import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.SqlInvariants;
import org.hsqldb.Statement;
import org.hsqldb.StatementManager;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Type;

public class Result
{
  public static final ResultMetaData sessionAttributesMetaData = ResultMetaData.newResultMetaData(4);
  private static final ResultMetaData emptyMeta = ResultMetaData.newResultMetaData(0);
  public static final Result emptyGeneratedResult = newDataResult(emptyMeta);
  public static final Result updateZeroResult = newUpdateCountResult(0);
  public static final Result updateOneResult = newUpdateCountResult(1);
  public byte mode;
  int databaseID;
  long sessionID;
  private long id;
  private String databaseName;
  private String mainString;
  private String subString;
  private String zoneString;
  int errorCode;
  private HsqlException exception;
  long statementID;
  int statementReturnType;
  public int updateCount;
  private int fetchSize;
  private Result chainedResult;
  private int lobCount;
  ResultLob lobResults;
  public ResultMetaData metaData;
  public ResultMetaData parameterMetaData;
  public ResultMetaData generatedMetaData;
  public int rsProperties;
  public int queryTimeout;
  int generateKeys;
  public Object valueData;
  public Statement statement;
  public RowSetNavigator navigator;

  Result(int paramInt)
  {
    this.mode = ((byte)paramInt);
  }

  public Result(int paramInt1, int paramInt2)
  {
    this.mode = ((byte)paramInt1);
    this.updateCount = paramInt2;
  }

  public static Result newResult(RowSetNavigator paramRowSetNavigator)
  {
    Result localResult = new Result(3);
    localResult.navigator = paramRowSetNavigator;
    return localResult;
  }

  public static Result newResult(int paramInt)
  {
    RowSetNavigatorClient localRowSetNavigatorClient = null;
    Result localResult = null;
    switch (paramInt)
    {
    case 35:
    case 41:
    case 43:
      break;
    case 8:
    case 9:
      localRowSetNavigatorClient = new RowSetNavigatorClient(4);
      break;
    case 6:
    case 17:
      localRowSetNavigatorClient = new RowSetNavigatorClient(1);
      break;
    case 16:
      localRowSetNavigatorClient = new RowSetNavigatorClient(4);
      break;
    case 3:
    case 14:
    case 15:
    case 20:
      break;
    case 18:
      throw Error.runtimeError(201, "Result");
    case 4:
    case 5:
    case 7:
    case 10:
    case 11:
    case 12:
    case 13:
    case 19:
    case 21:
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
    case 32:
    case 33:
    case 34:
    case 36:
    case 37:
    case 38:
    case 39:
    case 40:
    case 42:
    }
    localResult = new Result(paramInt);
    localResult.navigator = localRowSetNavigatorClient;
    return localResult;
  }

  public static Result newResult(DataInput paramDataInput, RowInputBinary paramRowInputBinary)
    throws IOException, HsqlException
  {
    return newResult(null, paramDataInput.readByte(), paramDataInput, paramRowInputBinary);
  }

  public static Result newResult(Session paramSession, int paramInt, DataInput paramDataInput, RowInputBinary paramRowInputBinary)
    throws IOException, HsqlException
  {
    try
    {
      if (paramInt == 18)
        return ResultLob.newLob(paramDataInput, false);
      Result localResult = newResult(paramSession, paramDataInput, paramRowInputBinary, paramInt);
      return localResult;
    }
    catch (IOException localIOException)
    {
    }
    throw Error.error(1300);
  }

  public void readAdditionalResults(SessionInterface paramSessionInterface, DataInputStream paramDataInputStream, RowInputBinary paramRowInputBinary)
    throws IOException, HsqlException
  {
    Result localResult = this;
    setSession(paramSessionInterface);
    while (true)
    {
      int i = paramDataInputStream.readByte();
      if (i == 0)
        return;
      localResult = newResult(null, paramDataInputStream, paramRowInputBinary, i);
      addChainedResult(localResult);
    }
  }

  public void readLobResults(SessionInterface paramSessionInterface, DataInputStream paramDataInputStream, RowInputBinary paramRowInputBinary)
    throws IOException, HsqlException
  {
    Result localResult = this;
    int i = 0;
    setSession(paramSessionInterface);
    int j;
    while (true)
    {
      j = paramDataInputStream.readByte();
      if (j != 18)
        break;
      ResultLob localResultLob = ResultLob.newLob(paramDataInputStream, false);
      if ((paramSessionInterface instanceof Session))
        ((Session)paramSessionInterface).allocateResultLob(localResultLob, paramDataInputStream);
      else
        localResult.addLobResult(localResultLob);
      i = 1;
    }
    if (j != 0)
      throw Error.runtimeError(201, "Result");
    if (i != 0)
      ((Session)paramSessionInterface).registerResultLobs(localResult);
  }

  private static Result newResult(Session paramSession, DataInput paramDataInput, RowInputBinary paramRowInputBinary, int paramInt)
    throws IOException, HsqlException
  {
    Result localResult = newResult(paramInt);
    int i = paramDataInput.readInt();
    paramRowInputBinary.resetRow(0L, i);
    byte[] arrayOfByte = paramRowInputBinary.getBuffer();
    paramDataInput.readFully(arrayOfByte, 4, i - 4);
    int j;
    switch (paramInt)
    {
    case 7:
      localResult.statementReturnType = paramRowInputBinary.readByte();
      break;
    case 10:
    case 32:
    case 39:
      break;
    case 37:
      localResult.setStatementType(paramRowInputBinary.readByte());
      localResult.mainString = paramRowInputBinary.readString();
      localResult.rsProperties = paramRowInputBinary.readByte();
      localResult.generateKeys = paramRowInputBinary.readByte();
      if ((localResult.generateKeys == 11) || (localResult.generateKeys == 21))
        localResult.generatedMetaData = new ResultMetaData(paramRowInputBinary);
      break;
    case 40:
      localResult.id = paramRowInputBinary.readLong();
      break;
    case 36:
      localResult.statementID = paramRowInputBinary.readLong();
      break;
    case 34:
      localResult.updateCount = paramRowInputBinary.readInt();
      localResult.fetchSize = paramRowInputBinary.readInt();
      localResult.statementReturnType = paramRowInputBinary.readByte();
      localResult.mainString = paramRowInputBinary.readString();
      localResult.rsProperties = paramRowInputBinary.readByte();
      localResult.queryTimeout = paramRowInputBinary.readShort();
      localResult.generateKeys = paramRowInputBinary.readByte();
      if ((localResult.generateKeys == 11) || (localResult.generateKeys == 21))
        localResult.generatedMetaData = new ResultMetaData(paramRowInputBinary);
      break;
    case 31:
      localResult.databaseName = paramRowInputBinary.readString();
      localResult.mainString = paramRowInputBinary.readString();
      localResult.subString = paramRowInputBinary.readString();
      localResult.zoneString = paramRowInputBinary.readString();
      localResult.updateCount = paramRowInputBinary.readInt();
      break;
    case 2:
    case 19:
      localResult.mainString = paramRowInputBinary.readString();
      localResult.subString = paramRowInputBinary.readString();
      localResult.errorCode = paramRowInputBinary.readInt();
      break;
    case 11:
      localResult.databaseID = paramRowInputBinary.readInt();
      localResult.sessionID = paramRowInputBinary.readLong();
      localResult.databaseName = paramRowInputBinary.readString();
      localResult.mainString = paramRowInputBinary.readString();
      break;
    case 1:
      localResult.updateCount = paramRowInputBinary.readInt();
      break;
    case 33:
      j = paramRowInputBinary.readInt();
      localResult.setActionType(j);
      switch (j)
      {
      case 2:
      case 4:
        localResult.mainString = paramRowInputBinary.readString();
        break;
      case 0:
      case 1:
      case 6:
      case 7:
        break;
      case 3:
      case 5:
      default:
        throw Error.runtimeError(201, "Result");
      }
      break;
    case 38:
      j = paramRowInputBinary.readInt();
      localResult.setConnectionAttrType(j);
      switch (j)
      {
      case 10027:
        localResult.mainString = paramRowInputBinary.readString();
        break;
      default:
        throw Error.runtimeError(201, "Result");
      }
      break;
    case 4:
      localResult.statementReturnType = paramRowInputBinary.readByte();
      localResult.statementID = paramRowInputBinary.readLong();
      localResult.rsProperties = paramRowInputBinary.readByte();
      localResult.metaData = new ResultMetaData(paramRowInputBinary);
      localResult.parameterMetaData = new ResultMetaData(paramRowInputBinary);
      break;
    case 43:
      localResult.updateCount = paramRowInputBinary.readInt();
      localResult.fetchSize = paramRowInputBinary.readInt();
      localResult.statementID = paramRowInputBinary.readLong();
      localResult.statementReturnType = paramRowInputBinary.readByte();
      localResult.rsProperties = paramRowInputBinary.readByte();
      localResult.metaData = new ResultMetaData(paramRowInputBinary);
      localResult.valueData = readSimple(paramRowInputBinary, localResult.metaData);
      break;
    case 35:
      localResult.updateCount = paramRowInputBinary.readInt();
      localResult.fetchSize = paramRowInputBinary.readInt();
      localResult.statementID = paramRowInputBinary.readLong();
      localResult.rsProperties = paramRowInputBinary.readByte();
      localResult.queryTimeout = paramRowInputBinary.readShort();
      Statement localStatement = paramSession.statementManager.getStatement(paramSession, localResult.statementID);
      if (localStatement == null)
      {
        localResult.mode = 21;
        localResult.valueData = ValuePool.emptyObjectArray;
      }
      else
      {
        localResult.statement = localStatement;
        localResult.metaData = localResult.statement.getParametersMetaData();
        localResult.valueData = readSimple(paramRowInputBinary, localResult.metaData);
      }
      break;
    case 41:
      localResult.id = paramRowInputBinary.readLong();
      int k = paramRowInputBinary.readInt();
      localResult.setActionType(k);
      localResult.metaData = new ResultMetaData(paramRowInputBinary);
      localResult.valueData = readSimple(paramRowInputBinary, localResult.metaData);
      break;
    case 6:
    case 8:
    case 9:
    case 16:
      localResult.updateCount = paramRowInputBinary.readInt();
      localResult.fetchSize = paramRowInputBinary.readInt();
      localResult.statementID = paramRowInputBinary.readLong();
      localResult.queryTimeout = paramRowInputBinary.readShort();
      localResult.metaData = new ResultMetaData(paramRowInputBinary);
      localResult.navigator.readSimple(paramRowInputBinary, localResult.metaData);
      break;
    case 17:
      localResult.metaData = new ResultMetaData(paramRowInputBinary);
      localResult.navigator.read(paramRowInputBinary, localResult.metaData);
      break;
    case 13:
      localResult.id = paramRowInputBinary.readLong();
      localResult.updateCount = paramRowInputBinary.readInt();
      localResult.fetchSize = paramRowInputBinary.readInt();
      break;
    case 3:
    case 15:
    case 20:
      localResult.id = paramRowInputBinary.readLong();
      localResult.updateCount = paramRowInputBinary.readInt();
      localResult.fetchSize = paramRowInputBinary.readInt();
      localResult.rsProperties = paramRowInputBinary.readByte();
      localResult.metaData = new ResultMetaData(paramRowInputBinary);
      localResult.navigator = new RowSetNavigatorClient();
      localResult.navigator.read(paramRowInputBinary, localResult.metaData);
      break;
    case 14:
      localResult.metaData = new ResultMetaData(paramRowInputBinary);
      localResult.navigator = new RowSetNavigatorClient();
      localResult.navigator.read(paramRowInputBinary, localResult.metaData);
      break;
    case 5:
    case 12:
    case 18:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
    case 27:
    case 28:
    case 29:
    case 30:
    case 42:
    default:
      throw Error.runtimeError(201, "Result");
    }
    return localResult;
  }

  public static Result newPSMResult(int paramInt, String paramString, Object paramObject)
  {
    Result localResult = newResult(42);
    localResult.errorCode = paramInt;
    localResult.mainString = paramString;
    localResult.valueData = paramObject;
    return localResult;
  }

  public static Result newPSMResult(Object paramObject)
  {
    Result localResult = newResult(42);
    localResult.valueData = paramObject;
    return localResult;
  }

  public static Result newPrepareStatementRequest()
  {
    return newResult(37);
  }

  public static Result newPreparedExecuteRequest(Type[] paramArrayOfType, long paramLong)
  {
    Result localResult = newResult(35);
    localResult.metaData = ResultMetaData.newSimpleResultMetaData(paramArrayOfType);
    localResult.statementID = paramLong;
    localResult.valueData = ValuePool.emptyObjectArray;
    return localResult;
  }

  public static Result newCallResponse(Type[] paramArrayOfType, long paramLong, Object[] paramArrayOfObject)
  {
    Result localResult = newResult(43);
    localResult.metaData = ResultMetaData.newSimpleResultMetaData(paramArrayOfType);
    localResult.statementID = paramLong;
    localResult.valueData = paramArrayOfObject;
    return localResult;
  }

  public static Result newUpdateResultRequest(Type[] paramArrayOfType, long paramLong)
  {
    Result localResult = newResult(41);
    localResult.metaData = ResultMetaData.newUpdateResultMetaData(paramArrayOfType);
    localResult.id = paramLong;
    localResult.valueData = new Object[0];
    return localResult;
  }

  public void setPreparedResultUpdateProperties(Object[] paramArrayOfObject)
  {
    this.valueData = paramArrayOfObject;
  }

  public void setPreparedExecuteProperties(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mode = 35;
    this.valueData = paramArrayOfObject;
    this.updateCount = paramInt1;
    this.fetchSize = paramInt2;
    this.rsProperties = paramInt3;
  }

  public void setBatchedPreparedExecuteRequest()
  {
    this.mode = 9;
    if (this.navigator == null)
      this.navigator = new RowSetNavigatorClient(4);
    else
      this.navigator.clear();
    this.updateCount = 0;
    this.fetchSize = 0;
  }

  public void addBatchedPreparedExecuteRequest(Object[] paramArrayOfObject)
  {
    ((RowSetNavigatorClient)this.navigator).add(paramArrayOfObject);
  }

  public static Result newBatchedExecuteRequest()
  {
    Type[] arrayOfType = { Type.SQL_VARCHAR };
    Result localResult = newResult(8);
    localResult.metaData = ResultMetaData.newSimpleResultMetaData(arrayOfType);
    return localResult;
  }

  public static Result newBatchedExecuteResponse(int[] paramArrayOfInt, Result paramResult1, Result paramResult2)
  {
    Result localResult = newResult(16);
    localResult.addChainedResult(paramResult1);
    localResult.addChainedResult(paramResult2);
    Type[] arrayOfType = { Type.SQL_INTEGER };
    localResult.metaData = ResultMetaData.newSimpleResultMetaData(arrayOfType);
    Object[][] arrayOfObject; = new Object[paramArrayOfInt.length][];
    for (int i = 0; i < paramArrayOfInt.length; i++)
      arrayOfObject;[i] = { ValuePool.getInt(paramArrayOfInt[i]) };
    ((RowSetNavigatorClient)localResult.navigator).setData(arrayOfObject;);
    return localResult;
  }

  public static Result newResetSessionRequest()
  {
    Result localResult = newResult(10);
    return localResult;
  }

  public static Result newConnectionAttemptRequest(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
  {
    Result localResult = newResult(31);
    localResult.mainString = paramString1;
    localResult.subString = paramString2;
    localResult.zoneString = paramString4;
    localResult.databaseName = paramString3;
    localResult.updateCount = paramInt;
    return localResult;
  }

  public static Result newConnectionAcknowledgeResponse(Database paramDatabase, long paramLong, int paramInt)
  {
    Result localResult = newResult(11);
    localResult.sessionID = paramLong;
    localResult.databaseID = paramInt;
    localResult.databaseName = paramDatabase.getUniqueName();
    localResult.mainString = paramDatabase.getProperties().getClientPropertiesAsString();
    return localResult;
  }

  public static Result newUpdateZeroResult()
  {
    return new Result(1, 0);
  }

  public static Result newUpdateCountResult(int paramInt)
  {
    return new Result(1, paramInt);
  }

  public static Result newUpdateCountResult(ResultMetaData paramResultMetaData, int paramInt)
  {
    Result localResult1 = newResult(1);
    Result localResult2 = newGeneratedDataResult(paramResultMetaData);
    localResult1.updateCount = paramInt;
    localResult1.addChainedResult(localResult2);
    return localResult1;
  }

  public static Result newSingleColumnResult(ResultMetaData paramResultMetaData)
  {
    Result localResult = newResult(3);
    localResult.metaData = paramResultMetaData;
    localResult.navigator = new RowSetNavigatorClient();
    return localResult;
  }

  public static Result newSingleColumnResult(String paramString)
  {
    Result localResult = newResult(3);
    localResult.metaData = ResultMetaData.newSingleColumnMetaData(paramString);
    localResult.navigator = new RowSetNavigatorClient(8);
    return localResult;
  }

  public static Result newSingleColumnStringResult(String paramString1, String paramString2)
  {
    Result localResult = newSingleColumnResult(paramString1);
    LineNumberReader localLineNumberReader = new LineNumberReader(new StringReader(paramString2));
    while (true)
    {
      String str = null;
      try
      {
        str = localLineNumberReader.readLine();
      }
      catch (Exception localException)
      {
      }
      if (str == null)
        break;
      localResult.getNavigator().add(new Object[] { str });
    }
    return localResult;
  }

  public static Result newPrepareResponse(Statement paramStatement)
  {
    Result localResult = newResult(4);
    localResult.statement = paramStatement;
    localResult.statementID = paramStatement.getID();
    int i = paramStatement.getType();
    localResult.statementReturnType = paramStatement.getStatementReturnType();
    localResult.metaData = paramStatement.getResultMetaData();
    localResult.parameterMetaData = paramStatement.getParametersMetaData();
    return localResult;
  }

  public static Result newFreeStmtRequest(long paramLong)
  {
    Result localResult = newResult(36);
    localResult.statementID = paramLong;
    return localResult;
  }

  public static Result newExecuteDirectRequest()
  {
    return newResult(34);
  }

  public void setPrepareOrExecuteProperties(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt, String[] paramArrayOfString)
  {
    this.mainString = paramString;
    this.updateCount = paramInt1;
    this.fetchSize = paramInt2;
    this.statementReturnType = paramInt3;
    this.queryTimeout = paramInt4;
    this.rsProperties = paramInt5;
    this.generateKeys = paramInt6;
    this.generatedMetaData = ResultMetaData.newGeneratedColumnsMetaData(paramArrayOfInt, paramArrayOfString);
  }

  public static Result newSetSavepointRequest(String paramString)
  {
    Result localResult = newResult(38);
    localResult.setConnectionAttrType(10027);
    localResult.setMainString(paramString);
    return localResult;
  }

  public static Result newRequestDataResult(long paramLong, int paramInt1, int paramInt2)
  {
    Result localResult = newResult(13);
    localResult.id = paramLong;
    localResult.updateCount = paramInt1;
    localResult.fetchSize = paramInt2;
    return localResult;
  }

  public static Result newDataResult(ResultMetaData paramResultMetaData)
  {
    Result localResult = newResult(3);
    localResult.navigator = new RowSetNavigatorClient();
    localResult.metaData = paramResultMetaData;
    return localResult;
  }

  public static Result newGeneratedDataResult(ResultMetaData paramResultMetaData)
  {
    Result localResult = newResult(20);
    localResult.navigator = new RowSetNavigatorClient();
    localResult.metaData = paramResultMetaData;
    return localResult;
  }

  public int getExecuteProperties()
  {
    return this.rsProperties;
  }

  public static Result newDataHeadResult(SessionInterface paramSessionInterface, Result paramResult, int paramInt1, int paramInt2)
  {
    if (paramInt1 + paramInt2 > paramResult.navigator.getSize())
      paramInt2 = paramResult.navigator.getSize() - paramInt1;
    Result localResult = newResult(15);
    localResult.metaData = paramResult.metaData;
    localResult.navigator = new RowSetNavigatorClient(paramResult.navigator, paramInt1, paramInt2);
    localResult.navigator.setId(paramResult.navigator.getId());
    localResult.setSession(paramSessionInterface);
    localResult.rsProperties = paramResult.rsProperties;
    localResult.fetchSize = paramResult.fetchSize;
    return localResult;
  }

  public static Result newDataRowsResult(Result paramResult, int paramInt1, int paramInt2)
  {
    if (paramInt1 + paramInt2 > paramResult.navigator.getSize())
      paramInt2 = paramResult.navigator.getSize() - paramInt1;
    Result localResult = newResult(14);
    localResult.id = paramResult.id;
    localResult.metaData = paramResult.metaData;
    localResult.navigator = new RowSetNavigatorClient(paramResult.navigator, paramInt1, paramInt2);
    return localResult;
  }

  public static Result newDataRowsResult(RowSetNavigator paramRowSetNavigator)
  {
    Result localResult = newResult(14);
    localResult.navigator = paramRowSetNavigator;
    return localResult;
  }

  public static Result newSessionAttributesResult()
  {
    Result localResult = newResult(3);
    localResult.navigator = new RowSetNavigatorClient(1);
    localResult.metaData = sessionAttributesMetaData;
    localResult.navigator.add(new Object[4]);
    return localResult;
  }

  public static Result newWarningResult(HsqlException paramHsqlException)
  {
    Result localResult = newResult(19);
    localResult.mainString = paramHsqlException.getMessage();
    localResult.subString = paramHsqlException.getSQLState();
    localResult.errorCode = paramHsqlException.getErrorCode();
    return localResult;
  }

  public static Result newErrorResult(Throwable paramThrowable)
  {
    return newErrorResult(paramThrowable, null);
  }

  public static Result newErrorResult(Throwable paramThrowable, String paramString)
  {
    Result localResult = newResult(2);
    if ((paramThrowable instanceof HsqlException))
    {
      localResult.exception = ((HsqlException)paramThrowable);
      localResult.mainString = localResult.exception.getMessage();
      localResult.subString = localResult.exception.getSQLState();
      if (paramString != null)
      {
        Result tmp54_53 = localResult;
        tmp54_53.mainString = (tmp54_53.mainString + " in statement [" + paramString + "]");
      }
      localResult.errorCode = localResult.exception.getErrorCode();
    }
    else if ((paramThrowable instanceof OutOfMemoryError))
    {
      System.gc();
      localResult.exception = Error.error(460, paramThrowable);
      localResult.mainString = localResult.exception.getMessage();
      localResult.subString = localResult.exception.getSQLState();
      localResult.errorCode = localResult.exception.getErrorCode();
    }
    else
    {
      localResult.exception = Error.error(458, paramThrowable);
      localResult.mainString = (localResult.exception.getMessage() + " " + paramThrowable.toString());
      localResult.subString = localResult.exception.getSQLState();
      localResult.errorCode = localResult.exception.getErrorCode();
      if (paramString != null)
      {
        Result tmp233_232 = localResult;
        tmp233_232.mainString = (tmp233_232.mainString + " in statement [" + paramString + "]");
      }
    }
    return localResult;
  }

  public void write(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream, RowOutputInterface paramRowOutputInterface)
    throws IOException, HsqlException
  {
    paramRowOutputInterface.reset();
    paramRowOutputInterface.writeByte(this.mode);
    int i = paramRowOutputInterface.size();
    paramRowOutputInterface.writeSize(0);
    switch (this.mode)
    {
    case 7:
      paramRowOutputInterface.writeByte(this.statementReturnType);
      break;
    case 10:
    case 32:
    case 39:
      break;
    case 37:
      paramRowOutputInterface.writeByte(this.statementReturnType);
      paramRowOutputInterface.writeString(this.mainString);
      paramRowOutputInterface.writeByte(this.rsProperties);
      paramRowOutputInterface.writeByte(this.generateKeys);
      if ((this.generateKeys == 11) || (this.generateKeys == 21))
        this.generatedMetaData.write(paramRowOutputInterface);
      break;
    case 36:
      paramRowOutputInterface.writeLong(this.statementID);
      break;
    case 40:
      paramRowOutputInterface.writeLong(this.id);
      break;
    case 34:
      paramRowOutputInterface.writeInt(this.updateCount);
      paramRowOutputInterface.writeInt(this.fetchSize);
      paramRowOutputInterface.writeByte(this.statementReturnType);
      paramRowOutputInterface.writeString(this.mainString);
      paramRowOutputInterface.writeByte(this.rsProperties);
      paramRowOutputInterface.writeShort(this.queryTimeout);
      paramRowOutputInterface.writeByte(this.generateKeys);
      if ((this.generateKeys == 11) || (this.generateKeys == 21))
        this.generatedMetaData.write(paramRowOutputInterface);
      break;
    case 31:
      paramRowOutputInterface.writeString(this.databaseName);
      paramRowOutputInterface.writeString(this.mainString);
      paramRowOutputInterface.writeString(this.subString);
      paramRowOutputInterface.writeString(this.zoneString);
      paramRowOutputInterface.writeInt(this.updateCount);
      break;
    case 2:
    case 19:
      paramRowOutputInterface.writeString(this.mainString);
      paramRowOutputInterface.writeString(this.subString);
      paramRowOutputInterface.writeInt(this.errorCode);
      break;
    case 11:
      paramRowOutputInterface.writeInt(this.databaseID);
      paramRowOutputInterface.writeLong(this.sessionID);
      paramRowOutputInterface.writeString(this.databaseName);
      paramRowOutputInterface.writeString(this.mainString);
      break;
    case 1:
      paramRowOutputInterface.writeInt(this.updateCount);
      break;
    case 33:
      j = getActionType();
      paramRowOutputInterface.writeInt(j);
      switch (j)
      {
      case 2:
      case 4:
        paramRowOutputInterface.writeString(this.mainString);
        break;
      case 0:
      case 1:
      case 6:
      case 7:
        break;
      case 3:
      case 5:
      default:
        throw Error.runtimeError(201, "Result");
      }
      break;
    case 4:
      paramRowOutputInterface.writeByte(this.statementReturnType);
      paramRowOutputInterface.writeLong(this.statementID);
      paramRowOutputInterface.writeByte(this.rsProperties);
      this.metaData.write(paramRowOutputInterface);
      this.parameterMetaData.write(paramRowOutputInterface);
      break;
    case 43:
      paramRowOutputInterface.writeInt(this.updateCount);
      paramRowOutputInterface.writeInt(this.fetchSize);
      paramRowOutputInterface.writeLong(this.statementID);
      paramRowOutputInterface.writeByte(this.statementReturnType);
      paramRowOutputInterface.writeByte(this.rsProperties);
      this.metaData.write(paramRowOutputInterface);
      writeSimple(paramRowOutputInterface, this.metaData, (Object[])this.valueData);
      break;
    case 35:
      paramRowOutputInterface.writeInt(this.updateCount);
      paramRowOutputInterface.writeInt(this.fetchSize);
      paramRowOutputInterface.writeLong(this.statementID);
      paramRowOutputInterface.writeByte(this.rsProperties);
      paramRowOutputInterface.writeShort(this.queryTimeout);
      writeSimple(paramRowOutputInterface, this.metaData, (Object[])this.valueData);
      break;
    case 41:
      paramRowOutputInterface.writeLong(this.id);
      paramRowOutputInterface.writeInt(getActionType());
      this.metaData.write(paramRowOutputInterface);
      writeSimple(paramRowOutputInterface, this.metaData, (Object[])this.valueData);
      break;
    case 6:
    case 8:
    case 9:
    case 16:
      paramRowOutputInterface.writeInt(this.updateCount);
      paramRowOutputInterface.writeInt(this.fetchSize);
      paramRowOutputInterface.writeLong(this.statementID);
      paramRowOutputInterface.writeShort(this.queryTimeout);
      this.metaData.write(paramRowOutputInterface);
      this.navigator.writeSimple(paramRowOutputInterface, this.metaData);
      break;
    case 17:
      this.metaData.write(paramRowOutputInterface);
      this.navigator.write(paramRowOutputInterface, this.metaData);
      break;
    case 38:
      j = getConnectionAttrType();
      paramRowOutputInterface.writeInt(j);
      switch (j)
      {
      case 10027:
        paramRowOutputInterface.writeString(this.mainString);
        break;
      default:
        throw Error.runtimeError(201, "Result");
      }
      break;
    case 13:
      paramRowOutputInterface.writeLong(this.id);
      paramRowOutputInterface.writeInt(this.updateCount);
      paramRowOutputInterface.writeInt(this.fetchSize);
      break;
    case 14:
      this.metaData.write(paramRowOutputInterface);
      this.navigator.write(paramRowOutputInterface, this.metaData);
      break;
    case 3:
    case 15:
    case 20:
      paramRowOutputInterface.writeLong(this.id);
      paramRowOutputInterface.writeInt(this.updateCount);
      paramRowOutputInterface.writeInt(this.fetchSize);
      paramRowOutputInterface.writeByte(this.rsProperties);
      this.metaData.write(paramRowOutputInterface);
      this.navigator.write(paramRowOutputInterface, this.metaData);
      break;
    case 5:
    case 12:
    case 18:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
    case 27:
    case 28:
    case 29:
    case 30:
    case 42:
    default:
      throw Error.runtimeError(201, "Result");
    }
    paramRowOutputInterface.writeIntData(paramRowOutputInterface.size() - i, i);
    paramDataOutputStream.write(paramRowOutputInterface.getOutputStream().getBuffer(), 0, paramRowOutputInterface.size());
    int j = getLobCount();
    Object localObject = this;
    for (int k = 0; k < j; k++)
    {
      ResultLob localResultLob = ((Result)localObject).lobResults;
      localResultLob.writeBody(paramSessionInterface, paramDataOutputStream);
      localObject = ((Result)localObject).lobResults;
    }
    if (this.chainedResult == null)
      paramDataOutputStream.writeByte(0);
    else
      this.chainedResult.write(paramSessionInterface, paramDataOutputStream, paramRowOutputInterface);
    paramDataOutputStream.flush();
  }

  public int getType()
  {
    return this.mode;
  }

  public boolean isData()
  {
    return (this.mode == 3) || (this.mode == 15);
  }

  public boolean isError()
  {
    return this.mode == 2;
  }

  public boolean isWarning()
  {
    return this.mode == 19;
  }

  public boolean isUpdateCount()
  {
    return this.mode == 1;
  }

  public boolean isSimpleValue()
  {
    return this.mode == 42;
  }

  public boolean hasGeneratedKeys()
  {
    return (this.mode == 1) && (this.chainedResult != null);
  }

  public HsqlException getException()
  {
    return this.exception;
  }

  public long getStatementID()
  {
    return this.statementID;
  }

  public void setStatementID(long paramLong)
  {
    this.statementID = paramLong;
  }

  public String getMainString()
  {
    return this.mainString;
  }

  public void setMainString(String paramString)
  {
    this.mainString = paramString;
  }

  public String getSubString()
  {
    return this.subString;
  }

  public String getZoneString()
  {
    return this.zoneString;
  }

  public int getErrorCode()
  {
    return this.errorCode;
  }

  public Object getValueObject()
  {
    return this.valueData;
  }

  public void setValueObject(Object paramObject)
  {
    this.valueData = paramObject;
  }

  public Statement getStatement()
  {
    return this.statement;
  }

  public void setStatement(Statement paramStatement)
  {
    this.statement = paramStatement;
  }

  public String getDatabaseName()
  {
    return this.databaseName;
  }

  public void setMaxRows(int paramInt)
  {
    this.updateCount = paramInt;
  }

  public int getFetchSize()
  {
    return this.fetchSize;
  }

  public void setFetchSize(int paramInt)
  {
    this.fetchSize = paramInt;
  }

  public int getUpdateCount()
  {
    return this.updateCount;
  }

  public int getConnectionAttrType()
  {
    return this.updateCount;
  }

  public void setConnectionAttrType(int paramInt)
  {
    this.updateCount = paramInt;
  }

  public int getActionType()
  {
    return this.updateCount;
  }

  public void setActionType(int paramInt)
  {
    this.updateCount = paramInt;
  }

  public long getSessionId()
  {
    return this.sessionID;
  }

  public void setSessionId(long paramLong)
  {
    this.sessionID = paramLong;
  }

  public void setSession(SessionInterface paramSessionInterface)
  {
    if (this.navigator != null)
      this.navigator.setSession(paramSessionInterface);
  }

  public int getDatabaseId()
  {
    return this.databaseID;
  }

  public void setDatabaseId(int paramInt)
  {
    this.databaseID = paramInt;
  }

  public long getResultId()
  {
    return this.id;
  }

  public void setResultId(long paramLong)
  {
    this.id = paramLong;
    if (this.navigator != null)
      this.navigator.setId(paramLong);
  }

  public void setUpdateCount(int paramInt)
  {
    this.updateCount = paramInt;
  }

  public void setAsTransactionEndRequest(int paramInt, String paramString)
  {
    this.mode = 33;
    this.updateCount = paramInt;
    this.mainString = (paramString == null ? "" : paramString);
  }

  public Object[] getSingleRowData()
  {
    Object[] arrayOfObject = (Object[])initialiseNavigator().getNext();
    arrayOfObject = (Object[])ArrayUtil.resizeArrayIfDifferent(arrayOfObject, this.metaData.getColumnCount());
    return arrayOfObject;
  }

  public Object[] getParameterData()
  {
    return (Object[])this.valueData;
  }

  public Object[] getSessionAttributes()
  {
    return (Object[])initialiseNavigator().getNext();
  }

  public void setResultType(int paramInt)
  {
    this.mode = ((byte)paramInt);
  }

  public void setStatementType(int paramInt)
  {
    this.statementReturnType = paramInt;
  }

  public int getStatementType()
  {
    return this.statementReturnType;
  }

  public int getGeneratedResultType()
  {
    return this.generateKeys;
  }

  public ResultMetaData getGeneratedResultMetaData()
  {
    return this.generatedMetaData;
  }

  public Result getChainedResult()
  {
    return this.chainedResult;
  }

  public Result getUnlinkChainedResult()
  {
    Result localResult = this.chainedResult;
    this.chainedResult = null;
    return localResult;
  }

  public void addChainedResult(Result paramResult)
  {
    for (Result localResult = this; localResult.chainedResult != null; localResult = localResult.chainedResult);
    localResult.chainedResult = paramResult;
  }

  public void addWarnings(HsqlException[] paramArrayOfHsqlException)
  {
    for (int i = 0; i < paramArrayOfHsqlException.length; i++)
    {
      Result localResult = newWarningResult(paramArrayOfHsqlException[i]);
      addChainedResult(localResult);
    }
  }

  public int getLobCount()
  {
    return this.lobCount;
  }

  public ResultLob getLOBResult()
  {
    return this.lobResults;
  }

  public void addLobResult(ResultLob paramResultLob)
  {
    for (Object localObject = this; ((Result)localObject).lobResults != null; localObject = ((Result)localObject).lobResults);
    ((Result)localObject).lobResults = paramResultLob;
    this.lobCount += 1;
  }

  public void clearLobResults()
  {
    this.lobResults = null;
    this.lobCount = 0;
  }

  private static Object[] readSimple(RowInputBinary paramRowInputBinary, ResultMetaData paramResultMetaData)
    throws IOException
  {
    int i = paramRowInputBinary.readInt();
    return paramRowInputBinary.readData(paramResultMetaData.columnTypes);
  }

  private static void writeSimple(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData, Object[] paramArrayOfObject)
    throws IOException
  {
    paramRowOutputInterface.writeInt(1);
    paramRowOutputInterface.writeData(paramResultMetaData.getColumnCount(), paramResultMetaData.columnTypes, paramArrayOfObject, null, null);
  }

  public RowSetNavigator getNavigator()
  {
    return this.navigator;
  }

  public void setNavigator(RowSetNavigator paramRowSetNavigator)
  {
    this.navigator = paramRowSetNavigator;
  }

  public RowSetNavigator initialiseNavigator()
  {
    switch (this.mode)
    {
    case 6:
    case 8:
    case 9:
    case 16:
    case 17:
      this.navigator.beforeFirst();
      return this.navigator;
    case 3:
    case 15:
    case 20:
      this.navigator.reset();
      return this.navigator;
    case 4:
    case 5:
    case 7:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 18:
    case 19:
    }
    throw Error.runtimeError(201, "Result");
  }

  static
  {
    SqlInvariants.isSystemSchemaName("SYSTEM");
    for (int i = 0; i < 4; i++)
      sessionAttributesMetaData.columns[i] = new ColumnBase(null, null, null, null);
    sessionAttributesMetaData.columns[0].setType(Type.SQL_INTEGER);
    sessionAttributesMetaData.columns[1].setType(Type.SQL_INTEGER);
    sessionAttributesMetaData.columns[2].setType(Type.SQL_BOOLEAN);
    sessionAttributesMetaData.columns[3].setType(Type.SQL_VARCHAR);
    sessionAttributesMetaData.prepareData();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.result.Result
 * JD-Core Version:    0.6.2
 */