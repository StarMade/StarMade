package org.hsqldb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.hsqldb.error.Error;
import org.hsqldb.lib.CharArrayWriter;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.CountdownInputStream;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongKeyHashMap;
import org.hsqldb.lib.LongKeyLongValueHashMap;
import org.hsqldb.lib.ReaderInputStream;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorClient;
import org.hsqldb.persist.LobManager;
import org.hsqldb.persist.Logger;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.persist.PersistentStoreCollectionSession;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultLob;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.BlobDataID;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.ClobDataID;
import org.hsqldb.types.LobData;
import org.hsqldb.types.Type;

public class SessionData
{
  private final Database database;
  private final Session session;
  public PersistentStoreCollectionSession persistentStoreCollection;
  LongKeyHashMap resultMap;
  Object currentValue;
  HashMap sequenceMap;
  HashMap sequenceUpdateMap;
  boolean hasLobOps;
  long firstNewLobID;
  LongKeyLongValueHashMap resultLobs = new LongKeyLongValueHashMap();
  
  public SessionData(Database paramDatabase, Session paramSession)
  {
    this.database = paramDatabase;
    this.session = paramSession;
    this.persistentStoreCollection = new PersistentStoreCollectionSession(paramSession);
  }
  
  public PersistentStore getSubqueryRowStore(TableBase paramTableBase)
  {
    PersistentStore localPersistentStore = this.persistentStoreCollection.getStore(paramTableBase);
    localPersistentStore.removeAll();
    return localPersistentStore;
  }
  
  public PersistentStore getNewResultRowStore(TableBase paramTableBase, boolean paramBoolean)
  {
    try
    {
      PersistentStore localPersistentStore = this.session.database.logger.newStore(this.session, this.persistentStoreCollection, paramTableBase);
      if (!paramBoolean) {
        localPersistentStore.setMemory(true);
      }
      return localPersistentStore;
    }
    catch (HsqlException localHsqlException)
    {
      throw Error.runtimeError(201, "SessionData");
    }
  }
  
  void setResultSetProperties(Result paramResult1, Result paramResult2)
  {
    int i = paramResult1.rsProperties;
    int j = paramResult2.rsProperties;
    if (i != j)
    {
      if (ResultProperties.isReadOnly(i))
      {
        j = ResultProperties.addHoldable(j, ResultProperties.isHoldable(i));
      }
      else if (ResultProperties.isUpdatable(j))
      {
        if (ResultProperties.isHoldable(i)) {
          this.session.addWarning(Error.error(4713));
        }
      }
      else
      {
        j = ResultProperties.addHoldable(j, ResultProperties.isHoldable(i));
        this.session.addWarning(Error.error(4712));
      }
      if (ResultProperties.isSensitive(i)) {
        this.session.addWarning(Error.error(4711));
      }
      j = ResultProperties.addScrollable(j, ResultProperties.isScrollable(i));
      paramResult2.rsProperties = j;
    }
  }
  
  Result getDataResultHead(Result paramResult1, Result paramResult2, boolean paramBoolean)
  {
    int i = paramResult1.getFetchSize();
    paramResult2.setResultId(this.session.actionTimestamp);
    int j = paramResult1.rsProperties;
    int k = paramResult2.rsProperties;
    if (j != k)
    {
      if (ResultProperties.isReadOnly(j)) {
        k = ResultProperties.addHoldable(k, ResultProperties.isHoldable(j));
      } else if (ResultProperties.isReadOnly(k)) {
        k = ResultProperties.addHoldable(k, ResultProperties.isHoldable(j));
      } else if (this.session.isAutoCommit()) {
        k = ResultProperties.addHoldable(k, ResultProperties.isHoldable(j));
      } else {
        k = ResultProperties.addHoldable(k, false);
      }
      k = ResultProperties.addScrollable(k, ResultProperties.isScrollable(j));
      paramResult2.rsProperties = k;
    }
    int m = 0;
    int n = 0;
    if (ResultProperties.isUpdatable(paramResult2.rsProperties)) {
      m = 1;
    }
    if (paramBoolean)
    {
      if ((i != 0) && (paramResult2.getNavigator().getSize() > i))
      {
        n = 1;
        m = 1;
      }
    }
    else if (!paramResult2.getNavigator().isMemory()) {
      m = 1;
    }
    if (m != 0)
    {
      if (this.resultMap == null) {
        this.resultMap = new LongKeyHashMap();
      }
      this.resultMap.put(paramResult2.getResultId(), paramResult2);
      paramResult2.rsProperties = ResultProperties.addIsHeld(paramResult2.rsProperties, true);
    }
    if (n != 0) {
      paramResult2 = Result.newDataHeadResult(this.session, paramResult2, 0, i);
    }
    return paramResult2;
  }
  
  Result getDataResultSlice(long paramLong, int paramInt1, int paramInt2)
  {
    Result localResult = (Result)this.resultMap.get(paramLong);
    RowSetNavigator localRowSetNavigator = localResult.getNavigator();
    if (paramInt1 + paramInt2 > localRowSetNavigator.getSize()) {
      paramInt2 = localRowSetNavigator.getSize() - paramInt1;
    }
    return Result.newDataRowsResult(localResult, paramInt1, paramInt2);
  }
  
  Result getDataResult(long paramLong)
  {
    Result localResult = (Result)this.resultMap.get(paramLong);
    return localResult;
  }
  
  RowSetNavigatorClient getRowSetSlice(long paramLong, int paramInt1, int paramInt2)
  {
    Result localResult = (Result)this.resultMap.get(paramLong);
    RowSetNavigator localRowSetNavigator = localResult.getNavigator();
    if (paramInt1 + paramInt2 > localRowSetNavigator.getSize()) {
      paramInt2 = localRowSetNavigator.getSize() - paramInt1;
    }
    return new RowSetNavigatorClient(localRowSetNavigator, paramInt1, paramInt2);
  }
  
  public void closeNavigator(long paramLong)
  {
    Result localResult = (Result)this.resultMap.remove(paramLong);
    if (localResult != null) {
      localResult.getNavigator().release();
    }
  }
  
  public void closeAllNavigators()
  {
    if (this.resultMap == null) {
      return;
    }
    Iterator localIterator = this.resultMap.values().iterator();
    while (localIterator.hasNext())
    {
      Result localResult = (Result)localIterator.next();
      localResult.getNavigator().release();
    }
    this.resultMap.clear();
  }
  
  public void closeAllTransactionNavigators()
  {
    if (this.resultMap == null) {
      return;
    }
    Iterator localIterator = this.resultMap.values().iterator();
    while (localIterator.hasNext())
    {
      Result localResult = (Result)localIterator.next();
      if (!ResultProperties.isHoldable(localResult.rsProperties))
      {
        localResult.getNavigator().release();
        localIterator.remove();
      }
    }
  }
  
  public void registerNewLob(long paramLong)
  {
    this.firstNewLobID = paramLong;
    this.hasLobOps = true;
  }
  
  public void clearLobOps()
  {
    this.firstNewLobID = 0L;
    this.hasLobOps = false;
  }
  
  public long getFirstLobID()
  {
    return this.firstNewLobID;
  }
  
  public void adjustLobUsageCount(Object paramObject, int paramInt)
  {
    if ((this.session.isProcessingLog) || (this.session.isProcessingScript)) {
      return;
    }
    if (paramObject == null) {
      return;
    }
    this.database.lobManager.adjustUsageCount(this.session, ((LobData)paramObject).getId(), paramInt);
    this.hasLobOps = true;
  }
  
  public void adjustLobUsageCount(TableBase paramTableBase, Object[] paramArrayOfObject, int paramInt)
  {
    if (!paramTableBase.hasLobColumn) {
      return;
    }
    if (paramTableBase.isTemp) {
      return;
    }
    if ((this.session.isProcessingLog) || (this.session.isProcessingScript)) {
      return;
    }
    for (int i = 0; i < paramTableBase.columnCount; i++) {
      if (paramTableBase.colTypes[i].isLobType())
      {
        Object localObject = paramArrayOfObject[i];
        if (localObject != null)
        {
          this.database.lobManager.adjustUsageCount(this.session, ((LobData)localObject).getId(), paramInt);
          this.hasLobOps = true;
        }
      }
    }
  }
  
  public void allocateLobForResult(ResultLob paramResultLob, InputStream paramInputStream)
  {
    try
    {
      long l2;
      long l1;
      Object localObject;
      CountdownInputStream localCountdownInputStream;
      Result localResult;
      switch (paramResultLob.getSubType())
      {
      case 7: 
        l2 = paramResultLob.getBlockLength();
        if (l2 < 0L)
        {
          allocateBlobSegments(paramResultLob, paramResultLob.getInputStream());
        }
        else
        {
          if (paramInputStream == null)
          {
            l1 = paramResultLob.getLobID();
            paramInputStream = paramResultLob.getInputStream();
          }
          else
          {
            localObject = this.session.createBlob(l2);
            l1 = ((BlobData)localObject).getId();
            this.resultLobs.put(paramResultLob.getLobID(), l1);
          }
          localCountdownInputStream = new CountdownInputStream(paramInputStream);
          localCountdownInputStream.setCount(l2);
          this.database.lobManager.setBytesForNewBlob(l1, localCountdownInputStream, paramResultLob.getBlockLength());
        }
        break;
      case 8: 
        l2 = paramResultLob.getBlockLength();
        if (l2 < 0L)
        {
          allocateClobSegments(paramResultLob, paramResultLob.getReader());
        }
        else
        {
          if (paramInputStream == null)
          {
            l1 = paramResultLob.getLobID();
            if (paramResultLob.getReader() != null) {
              paramInputStream = new ReaderInputStream(paramResultLob.getReader());
            } else {
              paramInputStream = paramResultLob.getInputStream();
            }
          }
          else
          {
            localObject = this.session.createClob(l2);
            l1 = ((ClobData)localObject).getId();
            this.resultLobs.put(paramResultLob.getLobID(), l1);
          }
          localCountdownInputStream = new CountdownInputStream(paramInputStream);
          localCountdownInputStream.setCount(l2 * 2L);
          this.database.lobManager.setCharsForNewClob(l1, localCountdownInputStream, paramResultLob.getBlockLength(), false);
        }
        break;
      case 2: 
        l1 = this.resultLobs.get(paramResultLob.getLobID());
        l2 = paramResultLob.getBlockLength();
        localObject = paramResultLob.getByteArray();
        localResult = this.database.lobManager.setBytes(l1, paramResultLob.getOffset(), (byte[])localObject, (int)l2);
        break;
      case 4: 
        l1 = this.resultLobs.get(paramResultLob.getLobID());
        l2 = paramResultLob.getBlockLength();
        localObject = paramResultLob.getCharArray();
        localResult = this.database.lobManager.setChars(l1, paramResultLob.getOffset(), (char[])localObject);
      }
    }
    catch (Throwable localThrowable)
    {
      this.resultLobs.clear();
      throw Error.error(458, localThrowable);
    }
  }
  
  private void allocateBlobSegments(ResultLob paramResultLob, InputStream paramInputStream)
    throws IOException
  {
    long l = paramResultLob.getOffset();
    int i = this.session.getStreamBlockSize();
    HsqlByteArrayOutputStream localHsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(i);
    for (;;)
    {
      localHsqlByteArrayOutputStream.reset();
      localHsqlByteArrayOutputStream.write(paramInputStream, i);
      byte[] arrayOfByte = localHsqlByteArrayOutputStream.getBuffer();
      Result localResult = this.database.lobManager.setBytes(paramResultLob.getLobID(), l, arrayOfByte, localHsqlByteArrayOutputStream.size());
      l += localHsqlByteArrayOutputStream.size();
      if (localHsqlByteArrayOutputStream.size() < i) {
        return;
      }
    }
  }
  
  private void allocateClobSegments(ResultLob paramResultLob, Reader paramReader)
    throws IOException
  {
    long l = paramResultLob.getOffset();
    int i = this.session.getStreamBlockSize();
    CharArrayWriter localCharArrayWriter = new CharArrayWriter(i);
    for (;;)
    {
      localCharArrayWriter.reset();
      localCharArrayWriter.write(paramReader, i);
      char[] arrayOfChar = localCharArrayWriter.getBuffer();
      if (localCharArrayWriter.size() < i) {
        arrayOfChar = localCharArrayWriter.toCharArray();
      }
      Result localResult = this.database.lobManager.setChars(paramResultLob.getLobID(), l, arrayOfChar);
      l += localCharArrayWriter.size();
      if (localCharArrayWriter.size() < i) {
        return;
      }
    }
  }
  
  public void registerLobForResult(Result paramResult)
  {
    RowSetNavigator localRowSetNavigator = paramResult.getNavigator();
    if (localRowSetNavigator == null)
    {
      registerLobsForRow((Object[])paramResult.valueData);
    }
    else
    {
      while (localRowSetNavigator.next())
      {
        Object[] arrayOfObject = localRowSetNavigator.getCurrent();
        registerLobsForRow(arrayOfObject);
      }
      localRowSetNavigator.reset();
    }
    this.resultLobs.clear();
  }
  
  private void registerLobsForRow(Object[] paramArrayOfObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      Object localObject;
      long l;
      if ((paramArrayOfObject[i] instanceof BlobDataID))
      {
        localObject = (BlobDataID)paramArrayOfObject[i];
        l = ((BlobData)localObject).getId();
        if (l < 0L) {
          l = this.resultLobs.get(l);
        }
        paramArrayOfObject[i] = this.database.lobManager.getBlob(l);
      }
      else if ((paramArrayOfObject[i] instanceof ClobDataID))
      {
        localObject = (ClobDataID)paramArrayOfObject[i];
        l = ((ClobData)localObject).getId();
        if (l < 0L) {
          l = this.resultLobs.get(l);
        }
        paramArrayOfObject[i] = this.database.lobManager.getClob(l);
      }
    }
  }
  
  ClobData createClobFromFile(String paramString1, String paramString2)
  {
    this.session.checkAdmin();
    paramString1 = this.database.logger.getSecurePath(paramString1);
    if (paramString1 == null) {
      throw Error.error(457, paramString1);
    }
    File localFile = new File(paramString1);
    boolean bool = localFile.exists();
    if (!bool) {
      throw Error.error(452);
    }
    long l = localFile.length();
    Object localObject1 = null;
    try
    {
      ClobDataID localClobDataID1 = this.session.createClob(l);
      localObject1 = new FileInputStream(localFile);
      InputStreamReader localInputStreamReader = new InputStreamReader((InputStream)localObject1, paramString2);
      localObject1 = new ReaderInputStream(localInputStreamReader);
      this.database.lobManager.setCharsForNewClob(localClobDataID1.getId(), (InputStream)localObject1, l, true);
      ClobDataID localClobDataID2 = localClobDataID1;
      return localClobDataID2;
    }
    catch (IOException localIOException)
    {
      throw Error.error(452, localIOException.toString());
    }
    finally
    {
      try
      {
        ((InputStream)localObject1).close();
      }
      catch (Exception localException2) {}
    }
  }
  
  BlobData createBlobFromFile(String paramString)
  {
    this.session.checkAdmin();
    paramString = this.database.logger.getSecurePath(paramString);
    if (paramString == null) {
      throw Error.error(457, paramString);
    }
    File localFile = new File(paramString);
    boolean bool = localFile.exists();
    if (!bool) {
      throw Error.error(452);
    }
    long l = localFile.length();
    FileInputStream localFileInputStream = null;
    try
    {
      BlobDataID localBlobDataID1 = this.session.createBlob(l);
      localFileInputStream = new FileInputStream(localFile);
      this.database.lobManager.setBytesForNewBlob(localBlobDataID1.getId(), localFileInputStream, l);
      BlobDataID localBlobDataID2 = localBlobDataID1;
      return localBlobDataID2;
    }
    catch (IOException localIOException)
    {
      throw Error.error(452);
    }
    finally
    {
      try
      {
        localFileInputStream.close();
      }
      catch (Exception localException2) {}
    }
  }
  
  public void startRowProcessing()
  {
    if (this.sequenceMap != null) {
      this.sequenceMap.clear();
    }
  }
  
  public Object getSequenceValue(NumberSequence paramNumberSequence)
  {
    if (this.sequenceMap == null)
    {
      this.sequenceMap = new HashMap();
      this.sequenceUpdateMap = new HashMap();
    }
    HsqlNameManager.HsqlName localHsqlName = paramNumberSequence.getName();
    Object localObject = this.sequenceMap.get(localHsqlName);
    if (localObject == null)
    {
      localObject = paramNumberSequence.getValueObject();
      this.sequenceMap.put(localHsqlName, localObject);
      this.sequenceUpdateMap.put(paramNumberSequence, localObject);
    }
    return localObject;
  }
  
  public Object getSequenceCurrent(NumberSequence paramNumberSequence)
  {
    return this.sequenceUpdateMap == null ? null : this.sequenceUpdateMap.get(paramNumberSequence);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.SessionData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */