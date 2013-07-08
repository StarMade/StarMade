package org.hsqldb.navigator;

import java.io.IOException;
import org.hsqldb.HsqlException;
import org.hsqldb.QueryExpression;
import org.hsqldb.QuerySpecification;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.SessionData;
import org.hsqldb.SortAndSlice;
import org.hsqldb.TableBase;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowSetNavigatorDataTable
  extends RowSetNavigatorData
{
  final Session session;
  public TableBase table;
  public PersistentStore store;
  RowIterator iterator;
  Row currentRow;
  int maxMemoryRowCount;
  boolean isClosed;
  int visibleColumnCount;
  boolean isAggregate;
  boolean isSimpleAggregate;
  Object[] simpleAggregateData;
  Object[] tempRowData;
  boolean reindexTable;
  private Index mainIndex;
  private Index fullIndex;
  private Index orderIndex;
  private Index groupIndex;
  private Index idIndex;
  
  public RowSetNavigatorDataTable(Session paramSession, QuerySpecification paramQuerySpecification)
  {
    super(paramSession);
    this.session = paramSession;
    this.rangePosition = paramQuerySpecification.resultRangePosition;
    this.maxMemoryRowCount = paramSession.getResultMemoryRowCount();
    this.visibleColumnCount = paramQuerySpecification.indexLimitVisible;
    this.table = paramQuerySpecification.resultTable.duplicate();
    this.table.store = (this.store = paramSession.sessionData.getNewResultRowStore(this.table, !paramQuerySpecification.isAggregated));
    this.isAggregate = paramQuerySpecification.isAggregated;
    this.isSimpleAggregate = ((paramQuerySpecification.isAggregated) && (!paramQuerySpecification.isGrouped));
    this.reindexTable = paramQuerySpecification.isGrouped;
    this.mainIndex = paramQuerySpecification.mainIndex;
    this.fullIndex = paramQuerySpecification.fullIndex;
    this.orderIndex = paramQuerySpecification.orderIndex;
    this.groupIndex = paramQuerySpecification.groupIndex;
    this.idIndex = paramQuerySpecification.idIndex;
    this.tempRowData = new Object[1];
  }
  
  public RowSetNavigatorDataTable(Session paramSession, QuerySpecification paramQuerySpecification, RowSetNavigatorData paramRowSetNavigatorData)
  {
    this(paramSession, paramQuerySpecification);
    paramRowSetNavigatorData.reset();
    while (paramRowSetNavigatorData.hasNext()) {
      add(paramRowSetNavigatorData.getNext());
    }
  }
  
  public RowSetNavigatorDataTable(Session paramSession, QueryExpression paramQueryExpression)
  {
    super(paramSession);
    this.session = paramSession;
    this.maxMemoryRowCount = paramSession.getResultMemoryRowCount();
    this.table = paramQueryExpression.resultTable.duplicate();
    this.visibleColumnCount = this.table.getColumnCount();
    this.table.store = (this.store = paramSession.sessionData.getNewResultRowStore(this.table, true));
    this.mainIndex = paramQueryExpression.mainIndex;
    this.fullIndex = paramQueryExpression.fullIndex;
  }
  
  public RowSetNavigatorDataTable(Session paramSession, TableBase paramTableBase)
  {
    super(paramSession);
    this.session = paramSession;
    this.maxMemoryRowCount = paramSession.getResultMemoryRowCount();
    this.table = paramTableBase;
    this.visibleColumnCount = paramTableBase.getColumnCount();
    this.store = paramTableBase.getRowStore(paramSession);
    this.mainIndex = paramTableBase.getPrimaryIndex();
    this.fullIndex = paramTableBase.getFullIndex();
    this.size = ((int)this.mainIndex.size(paramSession, this.store));
    reset();
  }
  
  public void sortFull(Session paramSession)
  {
    if (this.reindexTable) {
      this.store.indexRows(paramSession);
    }
    this.mainIndex = this.fullIndex;
    reset();
  }
  
  public void sortOrder(Session paramSession)
  {
    if (this.orderIndex != null)
    {
      if (this.reindexTable) {
        this.store.indexRows(paramSession);
      }
      this.mainIndex = this.orderIndex;
      reset();
    }
  }
  
  public void sortOrderUnion(Session paramSession, SortAndSlice paramSortAndSlice)
  {
    if (paramSortAndSlice.index != null)
    {
      this.mainIndex = paramSortAndSlice.index;
      reset();
    }
  }
  
  public void add(Object[] paramArrayOfObject)
  {
    try
    {
      Row localRow = (Row)this.store.getNewCachedObject(this.session, paramArrayOfObject, false);
      this.store.indexRow(this.session, localRow);
      this.size += 1;
    }
    catch (HsqlException localHsqlException) {}
  }
  
  void addAdjusted(Object[] paramArrayOfObject, int[] paramArrayOfInt)
  {
    try
    {
      if (paramArrayOfInt == null)
      {
        paramArrayOfObject = (Object[])ArrayUtil.resizeArrayIfDifferent(paramArrayOfObject, this.visibleColumnCount);
      }
      else
      {
        Object[] arrayOfObject = new Object[this.visibleColumnCount];
        ArrayUtil.projectRow(paramArrayOfObject, paramArrayOfInt, arrayOfObject);
        paramArrayOfObject = arrayOfObject;
      }
      add(paramArrayOfObject);
    }
    catch (HsqlException localHsqlException) {}
  }
  
  public void update(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    if (this.isSimpleAggregate) {
      return;
    }
    RowIterator localRowIterator = this.groupIndex.findFirstRow(this.session, this.store, paramArrayOfObject1);
    if (localRowIterator.hasNext())
    {
      Row localRow = localRowIterator.getNextRow();
      localRowIterator.remove();
      localRowIterator.release();
      this.size -= 1;
      add(paramArrayOfObject2);
    }
  }
  
  public boolean absolute(int paramInt)
  {
    return super.absolute(paramInt);
  }
  
  public Object[] getCurrent()
  {
    return this.currentRow.getData();
  }
  
  public Row getCurrentRow()
  {
    return this.currentRow;
  }
  
  public boolean next()
  {
    boolean bool = super.next();
    this.currentRow = this.iterator.getNextRow();
    return bool;
  }
  
  public void remove()
  {
    if (this.currentRow != null)
    {
      this.iterator.remove();
      this.currentRow = null;
      this.currentPos -= 1;
      this.size -= 1;
    }
  }
  
  public void reset()
  {
    super.reset();
    this.iterator = this.mainIndex.firstRow(this.store);
  }
  
  public void release()
  {
    if (this.isClosed) {
      return;
    }
    this.iterator.release();
    this.store.release();
    this.isClosed = true;
  }
  
  public void clear()
  {
    this.table.clearAllData(this.store);
    this.size = 0;
    reset();
  }
  
  public boolean isMemory()
  {
    return this.store.isMemory();
  }
  
  public void read(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData)
    throws IOException
  {}
  
  public void write(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData)
    throws IOException
  {
    reset();
    paramRowOutputInterface.writeLong(this.id);
    paramRowOutputInterface.writeInt(this.size);
    paramRowOutputInterface.writeInt(0);
    paramRowOutputInterface.writeInt(this.size);
    while (hasNext())
    {
      Object[] arrayOfObject = getNext();
      paramRowOutputInterface.writeData(paramResultMetaData.getExtendedColumnCount(), paramResultMetaData.columnTypes, arrayOfObject, null, null);
    }
    reset();
  }
  
  public Object[] getData(Long paramLong)
  {
    this.tempRowData[0] = paramLong;
    RowIterator localRowIterator = this.idIndex.findFirstRow(this.session, this.store, this.tempRowData, this.idIndex.getDefaultColumnMap());
    return localRowIterator.getNext();
  }
  
  public void copy(RowSetNavigatorData paramRowSetNavigatorData, int[] paramArrayOfInt)
  {
    while (paramRowSetNavigatorData.hasNext())
    {
      Object[] arrayOfObject = paramRowSetNavigatorData.getNext();
      addAdjusted(arrayOfObject, paramArrayOfInt);
    }
    paramRowSetNavigatorData.release();
  }
  
  public void union(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData)
  {
    int i = this.table.getColumnTypes().length;
    removeDuplicates(paramSession);
    paramRowSetNavigatorData.reset();
    while (paramRowSetNavigatorData.hasNext())
    {
      Object[] arrayOfObject = paramRowSetNavigatorData.getNext();
      RowIterator localRowIterator = findFirstRow(arrayOfObject);
      if (!localRowIterator.hasNext())
      {
        arrayOfObject = (Object[])ArrayUtil.resizeArrayIfDifferent(arrayOfObject, i);
        add(arrayOfObject);
      }
    }
    paramRowSetNavigatorData.release();
  }
  
  public void intersect(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData)
  {
    removeDuplicates(paramSession);
    paramRowSetNavigatorData.sortFull(paramSession);
    while (hasNext())
    {
      Object[] arrayOfObject = getNext();
      boolean bool = paramRowSetNavigatorData.containsRow(arrayOfObject);
      if (!bool) {
        remove();
      }
    }
    paramRowSetNavigatorData.release();
  }
  
  public void intersectAll(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData)
  {
    Object localObject = null;
    Row localRow = null;
    Object[] arrayOfObject1 = null;
    sortFull(paramSession);
    reset();
    paramRowSetNavigatorData.sortFull(paramSession);
    RowIterator localRowIterator = this.fullIndex.emptyIterator();
    while (hasNext())
    {
      Object[] arrayOfObject2 = getNext();
      int i = (localObject == null) || (this.fullIndex.compareRowNonUnique(paramSession, arrayOfObject2, (Object[])localObject, this.fullIndex.getColumnCount()) != 0) ? 1 : 0;
      if (i != 0)
      {
        localObject = arrayOfObject2;
        localRowIterator = paramRowSetNavigatorData.findFirstRow(arrayOfObject2);
      }
      localRow = localRowIterator.getNextRow();
      arrayOfObject1 = localRow == null ? null : localRow.getData();
      if ((arrayOfObject1 == null) || (this.fullIndex.compareRowNonUnique(paramSession, arrayOfObject2, arrayOfObject1, this.fullIndex.getColumnCount()) != 0)) {
        remove();
      }
    }
    paramRowSetNavigatorData.release();
  }
  
  public void except(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData)
  {
    removeDuplicates(paramSession);
    paramRowSetNavigatorData.sortFull(paramSession);
    while (hasNext())
    {
      Object[] arrayOfObject = getNext();
      boolean bool = paramRowSetNavigatorData.containsRow(arrayOfObject);
      if (bool) {
        remove();
      }
    }
    paramRowSetNavigatorData.release();
  }
  
  public void exceptAll(Session paramSession, RowSetNavigatorData paramRowSetNavigatorData)
  {
    Object localObject = null;
    Row localRow = null;
    Object[] arrayOfObject1 = null;
    sortFull(paramSession);
    reset();
    paramRowSetNavigatorData.sortFull(paramSession);
    RowIterator localRowIterator = this.fullIndex.emptyIterator();
    while (hasNext())
    {
      Object[] arrayOfObject2 = getNext();
      int i = (localObject == null) || (this.fullIndex.compareRowNonUnique(paramSession, arrayOfObject2, (Object[])localObject, this.fullIndex.getColumnCount()) != 0) ? 1 : 0;
      if (i != 0)
      {
        localObject = arrayOfObject2;
        localRowIterator = paramRowSetNavigatorData.findFirstRow(arrayOfObject2);
      }
      localRow = localRowIterator.getNextRow();
      arrayOfObject1 = localRow == null ? null : localRow.getData();
      if ((arrayOfObject1 != null) && (this.fullIndex.compareRowNonUnique(paramSession, arrayOfObject2, arrayOfObject1, this.fullIndex.getColumnCount()) == 0)) {
        remove();
      }
    }
    paramRowSetNavigatorData.release();
  }
  
  public boolean hasUniqueNotNullRows(Session paramSession)
  {
    sortFull(paramSession);
    reset();
    Object localObject = null;
    while (hasNext())
    {
      Object[] arrayOfObject = getNext();
      if (!hasNull(arrayOfObject))
      {
        if ((localObject != null) && (this.fullIndex.compareRow(paramSession, (Object[])localObject, arrayOfObject) == 0)) {
          return false;
        }
        localObject = arrayOfObject;
      }
    }
    return true;
  }
  
  public void removeDuplicates(Session paramSession)
  {
    sortFull(paramSession);
    reset();
    Object localObject = null;
    while (next())
    {
      Object[] arrayOfObject = getCurrent();
      if ((localObject != null) && (this.fullIndex.compareRow(paramSession, (Object[])localObject, arrayOfObject) == 0)) {
        remove();
      } else {
        localObject = arrayOfObject;
      }
    }
    reset();
  }
  
  public void trim(int paramInt1, int paramInt2)
  {
    if (this.size == 0) {
      return;
    }
    if (paramInt1 >= this.size)
    {
      clear();
      return;
    }
    if (paramInt1 != 0)
    {
      reset();
      for (i = 0; i < paramInt1; i++)
      {
        next();
        remove();
      }
    }
    if ((paramInt2 == 0) || (paramInt2 >= this.size)) {
      return;
    }
    reset();
    for (int i = 0; i < paramInt2; i++) {
      next();
    }
    while (hasNext())
    {
      next();
      remove();
    }
  }
  
  boolean hasNull(Object[] paramArrayOfObject)
  {
    for (int i = 0; i < this.visibleColumnCount; i++) {
      if (paramArrayOfObject[i] == null) {
        return true;
      }
    }
    return false;
  }
  
  public Object[] getGroupData(Object[] paramArrayOfObject)
  {
    if (this.isSimpleAggregate)
    {
      if (this.simpleAggregateData == null)
      {
        this.simpleAggregateData = paramArrayOfObject;
        return null;
      }
      return this.simpleAggregateData;
    }
    RowIterator localRowIterator = this.groupIndex.findFirstRow(this.session, this.store, paramArrayOfObject);
    if (localRowIterator.hasNext())
    {
      Row localRow = localRowIterator.getNextRow();
      if (this.isAggregate) {
        localRow.setChanged(true);
      }
      return localRow.getData();
    }
    return null;
  }
  
  boolean containsRow(Object[] paramArrayOfObject)
  {
    RowIterator localRowIterator = this.mainIndex.findFirstRow(this.session, this.store, paramArrayOfObject);
    boolean bool = localRowIterator.hasNext();
    localRowIterator.release();
    return bool;
  }
  
  RowIterator findFirstRow(Object[] paramArrayOfObject)
  {
    return this.mainIndex.findFirstRow(this.session, this.store, paramArrayOfObject);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.navigator.RowSetNavigatorDataTable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */