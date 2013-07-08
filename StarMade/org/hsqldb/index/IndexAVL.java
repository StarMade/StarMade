package org.hsqldb.index;

import java.io.PrintStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.hsqldb.Constraint;
import org.hsqldb.Database;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Row;
import org.hsqldb.RowAVL;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.TableBase;
import org.hsqldb.TransactionManager;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.ReadWriteLockDummy;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rights.Grantee;
import org.hsqldb.types.Type;

public class IndexAVL
  implements Index
{
  private static final IndexRowIterator emptyIterator = new IndexRowIterator(null, (PersistentStore)null, null, null, 0, false, false);
  private final long persistenceId;
  protected final HsqlNameManager.HsqlName name;
  private final boolean[] colCheck;
  final int[] colIndex;
  private final int[] defaultColMap;
  final Type[] colTypes;
  private final boolean[] colDesc;
  private final boolean[] nullsLast;
  final boolean isSimpleOrder;
  final boolean isSimple;
  protected final boolean isPK;
  protected final boolean isUnique;
  protected final boolean isConstraint;
  private final boolean isForward;
  private boolean isClustered;
  protected TableBase table;
  int position;
  private Index.IndexUse[] asArray;
  Object[] nullData;
  ReadWriteLock lock;
  Lock readLock;
  Lock writeLock;
  
  public IndexAVL(HsqlNameManager.HsqlName paramHsqlName, long paramLong, TableBase paramTableBase, int[] paramArrayOfInt, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2, Type[] paramArrayOfType, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this.persistenceId = paramLong;
    this.name = paramHsqlName;
    this.colIndex = paramArrayOfInt;
    this.colTypes = paramArrayOfType;
    this.colDesc = (paramArrayOfBoolean1 == null ? new boolean[paramArrayOfInt.length] : paramArrayOfBoolean1);
    this.nullsLast = (paramArrayOfBoolean2 == null ? new boolean[paramArrayOfInt.length] : paramArrayOfBoolean2);
    this.isPK = paramBoolean1;
    this.isUnique = paramBoolean2;
    this.isConstraint = paramBoolean3;
    this.isForward = paramBoolean4;
    this.table = paramTableBase;
    this.colCheck = paramTableBase.getNewColumnCheckList();
    this.asArray = new Index.IndexUse[] { new Index.IndexUse(this, this.colIndex.length) };
    ArrayUtil.intIndexesToBooleanArray(this.colIndex, this.colCheck);
    this.defaultColMap = new int[paramArrayOfInt.length];
    ArrayUtil.fillSequence(this.defaultColMap);
    boolean bool = this.colIndex.length > 0;
    for (int i = 0; i < this.colDesc.length; i++) {
      if ((this.colDesc[i] != 0) || (this.nullsLast[i] != 0)) {
        bool = false;
      }
    }
    this.isSimpleOrder = bool;
    this.isSimple = ((this.isSimpleOrder) && (this.colIndex.length == 1));
    this.nullData = new Object[this.colIndex.length];
    switch (paramTableBase.getTableType())
    {
    case 4: 
    case 5: 
    case 7: 
      this.lock = new ReentrantReadWriteLock();
      break;
    case 6: 
    default: 
      this.lock = new ReadWriteLockDummy();
    }
    this.readLock = this.lock.readLock();
    this.writeLock = this.lock.writeLock();
  }
  
  public int getType()
  {
    return 20;
  }
  
  public HsqlNameManager.HsqlName getName()
  {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getCatalogName()
  {
    return this.name.schema.schema;
  }
  
  public HsqlNameManager.HsqlName getSchemaName()
  {
    return this.name.schema;
  }
  
  public Grantee getOwner()
  {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences()
  {
    return new OrderedHashSet();
  }
  
  public OrderedHashSet getComponents()
  {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer = new StringBuffer(64);
    localStringBuffer.append("CREATE").append(' ');
    if (isUnique()) {
      localStringBuffer.append("UNIQUE").append(' ');
    }
    localStringBuffer.append("INDEX").append(' ');
    localStringBuffer.append(getName().statementName);
    localStringBuffer.append(' ').append("ON").append(' ');
    localStringBuffer.append(((Table)this.table).getName().getSchemaQualifiedStatementName());
    localStringBuffer.append(((Table)this.table).getColumnListSQL(this.colIndex, this.colIndex.length));
    return localStringBuffer.toString();
  }
  
  public long getChangeTimestamp()
  {
    return 0L;
  }
  
  public Index.IndexUse[] asArray()
  {
    return this.asArray;
  }
  
  public RowIterator emptyIterator()
  {
    return emptyIterator;
  }
  
  public int getPosition()
  {
    return this.position;
  }
  
  public void setPosition(int paramInt)
  {
    this.position = paramInt;
  }
  
  public long getPersistenceId()
  {
    return this.persistenceId;
  }
  
  public int getColumnCount()
  {
    return this.colIndex.length;
  }
  
  public boolean isUnique()
  {
    return this.isUnique;
  }
  
  public boolean isConstraint()
  {
    return this.isConstraint;
  }
  
  public int[] getColumns()
  {
    return this.colIndex;
  }
  
  public Type[] getColumnTypes()
  {
    return this.colTypes;
  }
  
  public boolean[] getColumnDesc()
  {
    return this.colDesc;
  }
  
  public int[] getDefaultColumnMap()
  {
    return this.defaultColMap;
  }
  
  public int getIndexOrderValue()
  {
    if (this.isPK) {
      return 0;
    }
    if (this.isConstraint) {
      return this.isUnique ? 0 : this.isForward ? 4 : 1;
    }
    return 2;
  }
  
  public boolean isForward()
  {
    return this.isForward;
  }
  
  public void setTable(TableBase paramTableBase)
  {
    this.table = paramTableBase;
  }
  
  public void setClustered(boolean paramBoolean)
  {
    this.isClustered = paramBoolean;
  }
  
  public boolean isClustered()
  {
    return this.isClustered;
  }
  
  public long size(Session paramSession, PersistentStore paramPersistentStore)
  {
    this.readLock.lock();
    try
    {
      long l = paramPersistentStore.elementCount(paramSession);
      return l;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  public long sizeUnique(PersistentStore paramPersistentStore)
  {
    this.readLock.lock();
    try
    {
      long l = paramPersistentStore.elementCountUnique(this);
      return l;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  public double[] searchCost(Session paramSession, PersistentStore paramPersistentStore)
  {
    int i = 0;
    int j = 1;
    double[] arrayOfDouble1 = new double[this.colIndex.length];
    int k = 0;
    int[] arrayOfInt = new int[1];
    this.readLock.lock();
    try
    {
      Object localObject1 = getAccessor(paramPersistentStore);
      Object localObject2 = localObject1;
      double[] arrayOfDouble2;
      if (localObject1 == null)
      {
        arrayOfDouble2 = new double[this.colIndex.length];
        return arrayOfDouble2;
      }
      for (;;)
      {
        localObject1 = localObject2;
        localObject2 = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
        if (localObject2 == null) {
          break;
        }
        if (k == 4)
        {
          i = 1;
          break;
        }
        k++;
      }
      for (;;)
      {
        localObject2 = next(paramPersistentStore, (NodeAVL)localObject1, k, 4, arrayOfInt);
        k = arrayOfInt[0];
        if (localObject2 == null) {
          break;
        }
        compareRowForChange(paramSession, ((NodeAVL)localObject1).getData(paramPersistentStore), ((NodeAVL)localObject2).getData(paramPersistentStore), arrayOfDouble1);
        localObject1 = localObject2;
        j++;
      }
      if (i != 0)
      {
        arrayOfDouble2 = new double[this.colIndex.length];
        int m = probeFactor(paramSession, paramPersistentStore, arrayOfDouble2, true) + probeFactor(paramSession, paramPersistentStore, arrayOfDouble2, false);
        for (n = 0; n < this.colIndex.length; n++)
        {
          arrayOfDouble2[n] /= 2.0D;
          for (int i1 = 0; i1 < arrayOfDouble2[n]; i1++) {
            arrayOfDouble1[n] *= 2.0D;
          }
        }
      }
      long l = paramPersistentStore.elementCount();
      for (int n = 0; n < this.colIndex.length; n++)
      {
        if (arrayOfDouble1[n] == 0.0D) {
          arrayOfDouble1[n] = 1.0D;
        }
        arrayOfDouble1[n] = (l / arrayOfDouble1[n]);
        if (arrayOfDouble1[n] < 2.0D) {
          arrayOfDouble1[n] = 2.0D;
        }
      }
      double[] arrayOfDouble3 = arrayOfDouble1;
      return arrayOfDouble3;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  int probeFactor(Session paramSession, PersistentStore paramPersistentStore, double[] paramArrayOfDouble, boolean paramBoolean)
  {
    int i = 0;
    Object localObject1 = getAccessor(paramPersistentStore);
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      return 0;
    }
    while (localObject2 != null)
    {
      localObject1 = localObject2;
      localObject2 = paramBoolean ? ((NodeAVL)localObject1).getLeft(paramPersistentStore) : ((NodeAVL)localObject1).getRight(paramPersistentStore);
      i++;
      if ((i > 4) && (localObject2 != null)) {
        compareRowForChange(paramSession, ((NodeAVL)localObject1).getData(paramPersistentStore), ((NodeAVL)localObject2).getData(paramPersistentStore), paramArrayOfDouble);
      }
    }
    return i - 4;
  }
  
  public int getNodeCount(Session paramSession, PersistentStore paramPersistentStore)
  {
    int i = 0;
    this.readLock.lock();
    try
    {
      RowIterator localRowIterator = firstRow(paramSession, paramPersistentStore);
      while (localRowIterator.hasNext())
      {
        localRowIterator.getNextRow();
        i++;
      }
      int j = i;
      return j;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  public boolean isEmpty(PersistentStore paramPersistentStore)
  {
    this.readLock.lock();
    try
    {
      boolean bool = getAccessor(paramPersistentStore) == null;
      return bool;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  public void unlinkNodes(NodeAVL paramNodeAVL)
  {
    this.writeLock.lock();
    try
    {
      Object localObject1 = paramNodeAVL;
      for (Object localObject2 = localObject1; localObject2 != null; localObject2 = ((NodeAVL)localObject1).getLeft(null)) {
        localObject1 = localObject2;
      }
      while (localObject1 != null)
      {
        NodeAVL localNodeAVL = nextUnlink((NodeAVL)localObject1);
        localObject1 = localNodeAVL;
      }
    }
    finally
    {
      this.writeLock.unlock();
    }
  }
  
  private NodeAVL nextUnlink(NodeAVL paramNodeAVL)
  {
    NodeAVL localNodeAVL = paramNodeAVL.getRight(null);
    if (localNodeAVL != null)
    {
      paramNodeAVL = localNodeAVL;
      for (localNodeAVL = paramNodeAVL.getLeft(null); localNodeAVL != null; localNodeAVL = paramNodeAVL.getLeft(null)) {
        paramNodeAVL = localNodeAVL;
      }
      return paramNodeAVL;
    }
    localNodeAVL = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.getParent(null); (paramNodeAVL != null) && (paramNodeAVL.isRight(localNodeAVL)); paramNodeAVL = paramNodeAVL.getParent(null))
    {
      paramNodeAVL.nRight = null;
      localNodeAVL.getRow(null).destroy();
      localNodeAVL.delete();
      localNodeAVL = paramNodeAVL;
    }
    if (paramNodeAVL != null) {
      paramNodeAVL.nLeft = null;
    }
    localNodeAVL.getRow(null).destroy();
    localNodeAVL.delete();
    return paramNodeAVL;
  }
  
  public void checkIndex(PersistentStore paramPersistentStore)
  {
    this.readLock.lock();
    try
    {
      Object localObject1 = getAccessor(paramPersistentStore);
      Object localObject2 = null;
      while (localObject1 != null)
      {
        localObject2 = localObject1;
        checkNodes(paramPersistentStore, (NodeAVL)localObject1);
        localObject1 = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
      }
      localObject1 = localObject2;
      while (localObject2 != null)
      {
        checkNodes(paramPersistentStore, (NodeAVL)localObject2);
        localObject2 = next(paramPersistentStore, (NodeAVL)localObject2);
      }
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  void checkNodes(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVL localNodeAVL1 = paramNodeAVL.getLeft(paramPersistentStore);
    NodeAVL localNodeAVL2 = paramNodeAVL.getRight(paramPersistentStore);
    if ((localNodeAVL1 != null) && (localNodeAVL1.getBalance(paramPersistentStore) == -2)) {
      System.out.print("broken index - deleted");
    }
    if ((localNodeAVL2 != null) && (localNodeAVL2.getBalance(paramPersistentStore) == -2)) {
      System.out.print("broken index -deleted");
    }
    if ((localNodeAVL1 != null) && (!paramNodeAVL.equals(localNodeAVL1.getParent(paramPersistentStore)))) {
      System.out.print("broken index - no parent");
    }
    if ((localNodeAVL2 != null) && (!paramNodeAVL.equals(localNodeAVL2.getParent(paramPersistentStore)))) {
      System.out.print("broken index - no parent");
    }
  }
  
  public int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++)
    {
      int k = this.colTypes[j].compare(paramSession, paramArrayOfObject1[this.colIndex[j]], paramArrayOfObject2[paramArrayOfInt[j]]);
      if (k != 0) {
        return k;
      }
    }
    return 0;
  }
  
  public int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfInt, int paramInt)
  {
    for (int i = 0; i < paramInt; i++)
    {
      int j = this.colTypes[i].compare(paramSession, paramArrayOfObject1[this.colIndex[i]], paramArrayOfObject2[paramArrayOfInt[i]]);
      if (j != 0) {
        return j;
      }
    }
    return 0;
  }
  
  public int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt)
  {
    for (int i = 0; i < paramInt; i++)
    {
      int j = this.colTypes[i].compare(paramSession, paramArrayOfObject1[this.colIndex[i]], paramArrayOfObject2[this.colIndex[i]]);
      if (j != 0) {
        return j;
      }
    }
    return 0;
  }
  
  public void compareRowForChange(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, double[] paramArrayOfDouble)
  {
    for (int i = 0; i < this.colIndex.length; i++)
    {
      int j = 0;
      int k = this.colTypes[i].compare(paramSession, paramArrayOfObject1[this.colIndex[i]], paramArrayOfObject2[this.colIndex[i]]);
      if ((j != 0) || (k != 0))
      {
        paramArrayOfDouble[i] += 1.0D;
        j = 1;
      }
    }
  }
  
  public int compareRow(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    for (int i = 0; i < this.colIndex.length; i++)
    {
      int j = this.colTypes[i].compare(paramSession, paramArrayOfObject1[this.colIndex[i]], paramArrayOfObject2[this.colIndex[i]]);
      if (j != 0)
      {
        if (this.isSimpleOrder) {
          return j;
        }
        int k = (paramArrayOfObject1[this.colIndex[i]] == null) || (paramArrayOfObject2[this.colIndex[i]] == null) ? 1 : 0;
        if ((this.colDesc[i] != 0) && (k == 0)) {
          j = -j;
        }
        if ((this.nullsLast[i] != 0) && (k != 0)) {
          j = -j;
        }
        return j;
      }
    }
    return 0;
  }
  
  int compareRowForInsertOrDelete(Session paramSession, Row paramRow1, Row paramRow2, boolean paramBoolean, int paramInt)
  {
    Object[] arrayOfObject1 = paramRow1.getData();
    Object[] arrayOfObject2 = paramRow2.getData();
    for (int i = paramInt; i < this.colIndex.length; i++)
    {
      int j = this.colTypes[i].compare(paramSession, arrayOfObject1[this.colIndex[i]], arrayOfObject2[this.colIndex[i]]);
      if (j != 0)
      {
        if (this.isSimpleOrder) {
          return j;
        }
        int k = (arrayOfObject1[this.colIndex[i]] == null) || (arrayOfObject2[this.colIndex[i]] == null) ? 1 : 0;
        if ((this.colDesc[i] != 0) && (k == 0)) {
          j = -j;
        }
        if ((this.nullsLast[i] != 0) && (k != 0)) {
          j = -j;
        }
        return j;
      }
    }
    if (paramBoolean)
    {
      long l = paramRow1.getPos() - paramRow2.getPos();
      return l > 0L ? 1 : l == 0L ? 0 : -1;
    }
    return 0;
  }
  
  int compareObject(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfInt, int paramInt)
  {
    return this.colTypes[paramInt].compare(paramSession, paramArrayOfObject1[this.colIndex[paramInt]], paramArrayOfObject2[paramArrayOfInt[paramInt]]);
  }
  
  boolean hasNulls(Session paramSession, Object[] paramArrayOfObject)
  {
    if (this.colIndex.length == 1) {
      return paramArrayOfObject[this.colIndex[0]] == null;
    }
    boolean bool = paramSession == null ? true : paramSession.database.sqlUniqueNulls;
    for (int i = 0; i < this.colIndex.length; i++) {
      if (paramArrayOfObject[this.colIndex[i]] == null)
      {
        if (bool) {
          return true;
        }
      }
      else if (!bool) {
        return false;
      }
    }
    return !bool;
  }
  
  public void insert(Session paramSession, PersistentStore paramPersistentStore, Row paramRow)
  {
    boolean bool1 = true;
    int i = -1;
    boolean bool2 = (!this.isUnique) || (hasNulls(paramSession, paramRow.getData()));
    this.writeLock.lock();
    paramPersistentStore.writeLock();
    try
    {
      NodeAVL localNodeAVL1 = getAccessor(paramPersistentStore);
      NodeAVL localNodeAVL2 = localNodeAVL1;
      if (localNodeAVL1 == null)
      {
        paramPersistentStore.setAccessor(this, ((RowAVL)paramRow).getNode(this.position));
        return;
      }
      for (;;)
      {
        Row localRow = localNodeAVL1.getRow(paramPersistentStore);
        i = compareRowForInsertOrDelete(paramSession, paramRow, localRow, bool2, 0);
        if ((i == 0) && (paramSession != null) && (!bool2) && (paramSession.database.txManager.isMVRows()) && (!isEqualReadable(paramSession, paramPersistentStore, localNodeAVL1)))
        {
          bool2 = true;
          i = compareRowForInsertOrDelete(paramSession, paramRow, localRow, bool2, this.colIndex.length);
        }
        if (i == 0)
        {
          Constraint localConstraint = null;
          if (this.isConstraint) {
            localConstraint = ((Table)this.table).getUniqueConstraintForIndex(this);
          }
          if (localConstraint == null) {
            throw Error.error(104, this.name.statementName);
          }
          throw localConstraint.getException(paramRow.getData());
        }
        bool1 = i < 0;
        localNodeAVL2 = localNodeAVL1;
        localNodeAVL1 = localNodeAVL2.child(paramPersistentStore, bool1);
        if (localNodeAVL1 == null) {
          break;
        }
      }
      localNodeAVL2 = localNodeAVL2.set(paramPersistentStore, bool1, ((RowAVL)paramRow).getNode(this.position));
      balance(paramPersistentStore, localNodeAVL2, bool1);
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    finally
    {
      paramPersistentStore.writeUnlock();
      this.writeLock.unlock();
    }
  }
  
  public void delete(Session paramSession, PersistentStore paramPersistentStore, Row paramRow)
  {
    if (!paramRow.isInMemory()) {
      paramRow = (Row)paramPersistentStore.get(paramRow, false);
    }
    NodeAVL localNodeAVL = ((RowAVL)paramRow).getNode(this.position);
    if (localNodeAVL != null) {
      delete(paramPersistentStore, localNodeAVL);
    }
  }
  
  void delete(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    if (paramNodeAVL == null) {
      return;
    }
    this.writeLock.lock();
    paramPersistentStore.writeLock();
    try
    {
      int i;
      NodeAVL localNodeAVL4;
      NodeAVL localNodeAVL6;
      if (paramNodeAVL.getLeft(paramPersistentStore) == null)
      {
        localNodeAVL1 = paramNodeAVL.getRight(paramPersistentStore);
      }
      else if (paramNodeAVL.getRight(paramPersistentStore) == null)
      {
        localNodeAVL1 = paramNodeAVL.getLeft(paramPersistentStore);
      }
      else
      {
        NodeAVL localNodeAVL2 = paramNodeAVL;
        NodeAVL localNodeAVL3;
        for (paramNodeAVL = paramNodeAVL.getLeft(paramPersistentStore);; paramNodeAVL = localNodeAVL3)
        {
          localNodeAVL3 = paramNodeAVL.getRight(paramPersistentStore);
          if (localNodeAVL3 == null) {
            break;
          }
        }
        localNodeAVL1 = paramNodeAVL.getLeft(paramPersistentStore);
        i = paramNodeAVL.getBalance(paramPersistentStore);
        paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, localNodeAVL2.getBalance(paramPersistentStore));
        localNodeAVL2 = localNodeAVL2.setBalance(paramPersistentStore, i);
        localNodeAVL4 = paramNodeAVL.getParent(paramPersistentStore);
        NodeAVL localNodeAVL5 = localNodeAVL2.getParent(paramPersistentStore);
        if (localNodeAVL2.isRoot(paramPersistentStore)) {
          paramPersistentStore.setAccessor(this, paramNodeAVL);
        }
        paramNodeAVL = paramNodeAVL.setParent(paramPersistentStore, localNodeAVL5);
        if (localNodeAVL5 != null) {
          if (localNodeAVL5.isRight(localNodeAVL2)) {
            localNodeAVL5 = localNodeAVL5.setRight(paramPersistentStore, paramNodeAVL);
          } else {
            localNodeAVL5 = localNodeAVL5.setLeft(paramPersistentStore, paramNodeAVL);
          }
        }
        if (localNodeAVL2.equals(localNodeAVL4))
        {
          localNodeAVL2 = localNodeAVL2.setParent(paramPersistentStore, paramNodeAVL);
          if (localNodeAVL2.isLeft(paramNodeAVL))
          {
            paramNodeAVL = paramNodeAVL.setLeft(paramPersistentStore, localNodeAVL2);
            localNodeAVL6 = localNodeAVL2.getRight(paramPersistentStore);
            paramNodeAVL = paramNodeAVL.setRight(paramPersistentStore, localNodeAVL6);
          }
          else
          {
            paramNodeAVL = paramNodeAVL.setRight(paramPersistentStore, localNodeAVL2);
            localNodeAVL6 = localNodeAVL2.getLeft(paramPersistentStore);
            paramNodeAVL = paramNodeAVL.setLeft(paramPersistentStore, localNodeAVL6);
          }
        }
        else
        {
          localNodeAVL2 = localNodeAVL2.setParent(paramPersistentStore, localNodeAVL4);
          localNodeAVL4 = localNodeAVL4.setRight(paramPersistentStore, localNodeAVL2);
          localNodeAVL6 = localNodeAVL2.getLeft(paramPersistentStore);
          NodeAVL localNodeAVL7 = localNodeAVL2.getRight(paramPersistentStore);
          paramNodeAVL = paramNodeAVL.setLeft(paramPersistentStore, localNodeAVL6);
          paramNodeAVL = paramNodeAVL.setRight(paramPersistentStore, localNodeAVL7);
        }
        paramNodeAVL.getRight(paramPersistentStore).setParent(paramPersistentStore, paramNodeAVL);
        paramNodeAVL.getLeft(paramPersistentStore).setParent(paramPersistentStore, paramNodeAVL);
        localNodeAVL2 = localNodeAVL2.setLeft(paramPersistentStore, localNodeAVL1);
        if (localNodeAVL1 != null) {
          localNodeAVL1 = localNodeAVL1.setParent(paramPersistentStore, localNodeAVL2);
        }
        localNodeAVL2 = localNodeAVL2.setRight(paramPersistentStore, null);
        paramNodeAVL = localNodeAVL2;
      }
      boolean bool = paramNodeAVL.isFromLeft(paramPersistentStore);
      paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL1);
      NodeAVL localNodeAVL1 = paramNodeAVL.getParent(paramPersistentStore);
      paramNodeAVL.delete();
      while (localNodeAVL1 != null)
      {
        paramNodeAVL = localNodeAVL1;
        i = bool ? 1 : -1;
        switch (paramNodeAVL.getBalance(paramPersistentStore) * i)
        {
        case -1: 
          paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, 0);
          break;
        case 0: 
          paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, i);
          return;
        case 1: 
          localNodeAVL4 = paramNodeAVL.child(paramPersistentStore, !bool);
          int j = localNodeAVL4.getBalance(paramPersistentStore);
          if (j * i >= 0)
          {
            paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL4);
            localNodeAVL6 = localNodeAVL4.child(paramPersistentStore, bool);
            paramNodeAVL = paramNodeAVL.set(paramPersistentStore, !bool, localNodeAVL6);
            localNodeAVL4 = localNodeAVL4.set(paramPersistentStore, bool, paramNodeAVL);
            if (j == 0)
            {
              paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, i);
              localNodeAVL4 = localNodeAVL4.setBalance(paramPersistentStore, -i);
              return;
            }
            paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, 0);
            localNodeAVL4 = localNodeAVL4.setBalance(paramPersistentStore, 0);
            paramNodeAVL = localNodeAVL4;
          }
          else
          {
            localNodeAVL6 = localNodeAVL4.child(paramPersistentStore, bool);
            paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL6);
            j = localNodeAVL6.getBalance(paramPersistentStore);
            localNodeAVL4 = localNodeAVL4.set(paramPersistentStore, bool, localNodeAVL6.child(paramPersistentStore, !bool));
            localNodeAVL6 = localNodeAVL6.set(paramPersistentStore, !bool, localNodeAVL4);
            paramNodeAVL = paramNodeAVL.set(paramPersistentStore, !bool, localNodeAVL6.child(paramPersistentStore, bool));
            localNodeAVL6 = localNodeAVL6.set(paramPersistentStore, bool, paramNodeAVL);
            paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, j == i ? -i : 0);
            localNodeAVL4 = localNodeAVL4.setBalance(paramPersistentStore, j == -i ? i : 0);
            localNodeAVL6 = localNodeAVL6.setBalance(paramPersistentStore, 0);
            paramNodeAVL = localNodeAVL6;
          }
          break;
        }
        bool = paramNodeAVL.isFromLeft(paramPersistentStore);
        localNodeAVL1 = paramNodeAVL.getParent(paramPersistentStore);
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    finally
    {
      paramPersistentStore.writeUnlock();
      this.writeLock.unlock();
    }
  }
  
  public boolean existsParent(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfInt)
  {
    NodeAVL localNodeAVL = findNode(paramSession, paramPersistentStore, paramArrayOfObject, paramArrayOfInt, paramArrayOfInt.length, 41, 2, false);
    return localNodeAVL != null;
  }
  
  public RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, boolean[] paramArrayOfBoolean)
  {
    if (paramInt3 == 74) {
      return lastRow(paramSession, paramPersistentStore);
    }
    NodeAVL localNodeAVL = findNode(paramSession, paramPersistentStore, paramArrayOfObject, this.defaultColMap, paramInt1, paramInt3, 0, paramBoolean);
    if (localNodeAVL == null) {
      return emptyIterator;
    }
    return new IndexRowIterator(paramSession, paramPersistentStore, this, localNodeAVL, paramInt2, false, paramBoolean);
  }
  
  public RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject)
  {
    NodeAVL localNodeAVL = findNode(paramSession, paramPersistentStore, paramArrayOfObject, this.colIndex, this.colIndex.length, 41, 0, false);
    if (localNodeAVL == null) {
      return emptyIterator;
    }
    return new IndexRowIterator(paramSession, paramPersistentStore, this, localNodeAVL, 0, false, false);
  }
  
  public RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfInt)
  {
    NodeAVL localNodeAVL = findNode(paramSession, paramPersistentStore, paramArrayOfObject, paramArrayOfInt, paramArrayOfInt.length, 41, 0, false);
    if (localNodeAVL == null) {
      return emptyIterator;
    }
    return new IndexRowIterator(paramSession, paramPersistentStore, this, localNodeAVL, 0, false, false);
  }
  
  public RowIterator findFirstRowNotNull(Session paramSession, PersistentStore paramPersistentStore)
  {
    NodeAVL localNodeAVL = findNode(paramSession, paramPersistentStore, this.nullData, this.defaultColMap, 1, 48, 0, false);
    if (localNodeAVL == null) {
      return emptyIterator;
    }
    return new IndexRowIterator(paramSession, paramPersistentStore, this, localNodeAVL, 0, false, false);
  }
  
  public RowIterator firstRow(Session paramSession, PersistentStore paramPersistentStore)
  {
    this.readLock.lock();
    try
    {
      Object localObject1 = getAccessor(paramPersistentStore);
      for (Object localObject2 = localObject1; localObject2 != null; localObject2 = ((NodeAVL)localObject1).getLeft(paramPersistentStore)) {
        localObject1 = localObject2;
      }
      while ((paramSession != null) && (localObject1 != null))
      {
        localObject3 = ((NodeAVL)localObject1).getRow(paramPersistentStore);
        if (paramSession.database.txManager.canRead(paramSession, (Row)localObject3, 0, null)) {
          break;
        }
        localObject1 = next(paramPersistentStore, (NodeAVL)localObject1);
      }
      if (localObject1 == null)
      {
        localObject3 = emptyIterator;
        return localObject3;
      }
      Object localObject3 = new IndexRowIterator(paramSession, paramPersistentStore, this, (NodeAVL)localObject1, 0, false, false);
      return localObject3;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  public RowIterator firstRow(PersistentStore paramPersistentStore)
  {
    this.readLock.lock();
    try
    {
      Object localObject1 = getAccessor(paramPersistentStore);
      for (Object localObject2 = localObject1; localObject2 != null; localObject2 = ((NodeAVL)localObject1).getLeft(paramPersistentStore)) {
        localObject1 = localObject2;
      }
      if (localObject1 == null)
      {
        localIndexRowIterator = emptyIterator;
        return localIndexRowIterator;
      }
      IndexRowIterator localIndexRowIterator = new IndexRowIterator(null, paramPersistentStore, this, (NodeAVL)localObject1, 0, false, false);
      return localIndexRowIterator;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  public RowIterator lastRow(Session paramSession, PersistentStore paramPersistentStore)
  {
    this.readLock.lock();
    try
    {
      Object localObject1 = getAccessor(paramPersistentStore);
      for (Object localObject2 = localObject1; localObject2 != null; localObject2 = ((NodeAVL)localObject1).getRight(paramPersistentStore)) {
        localObject1 = localObject2;
      }
      while ((paramSession != null) && (localObject1 != null))
      {
        localObject3 = ((NodeAVL)localObject1).getRow(paramPersistentStore);
        if (paramSession.database.txManager.canRead(paramSession, (Row)localObject3, 0, null)) {
          break;
        }
        localObject1 = last(paramPersistentStore, (NodeAVL)localObject1);
      }
      if (localObject1 == null)
      {
        localObject3 = emptyIterator;
        return localObject3;
      }
      Object localObject3 = new IndexRowIterator(paramSession, paramPersistentStore, this, (NodeAVL)localObject1, 0, false, true);
      return localObject3;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  NodeAVL next(Session paramSession, PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, int paramInt)
  {
    if (paramNodeAVL == null) {
      return null;
    }
    for (;;)
    {
      if (paramInt == 0)
      {
        paramNodeAVL = next(paramPersistentStore, paramNodeAVL);
      }
      else
      {
        localObject = paramNodeAVL.getData(paramPersistentStore);
        return findNode(paramSession, paramPersistentStore, (Object[])localObject, this.colIndex, paramInt, 43, 0, false);
      }
      if (paramNodeAVL == null) {
        return paramNodeAVL;
      }
      if (paramSession == null) {
        return paramNodeAVL;
      }
      Object localObject = paramNodeAVL.getRow(paramPersistentStore);
      if (paramSession.database.txManager.canRead(paramSession, (Row)localObject, 0, null)) {
        return paramNodeAVL;
      }
    }
  }
  
  NodeAVL last(Session paramSession, PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, int paramInt)
  {
    if (paramNodeAVL == null) {
      return null;
    }
    for (;;)
    {
      if (paramInt == 0)
      {
        paramNodeAVL = last(paramPersistentStore, paramNodeAVL);
      }
      else
      {
        localObject = paramNodeAVL.getData(paramPersistentStore);
        return findNode(paramSession, paramPersistentStore, (Object[])localObject, this.colIndex, paramInt, 44, 0, false);
      }
      if (paramNodeAVL == null) {
        return paramNodeAVL;
      }
      if (paramSession == null) {
        return paramNodeAVL;
      }
      Object localObject = paramNodeAVL.getRow(paramPersistentStore);
      if (paramSession.database.txManager.canRead(paramSession, (Row)localObject, 0, null)) {
        return paramNodeAVL;
      }
    }
  }
  
  NodeAVL next(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVL localNodeAVL = paramNodeAVL.getRight(paramPersistentStore);
    if (localNodeAVL != null)
    {
      paramNodeAVL = localNodeAVL;
      for (localNodeAVL = paramNodeAVL.getLeft(paramPersistentStore); localNodeAVL != null; localNodeAVL = paramNodeAVL.getLeft(paramPersistentStore)) {
        paramNodeAVL = localNodeAVL;
      }
      return paramNodeAVL;
    }
    localNodeAVL = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore); (paramNodeAVL != null) && (paramNodeAVL.isRight(localNodeAVL)); paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore)) {
      localNodeAVL = paramNodeAVL;
    }
    return paramNodeAVL;
  }
  
  NodeAVL next(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    NodeAVL localNodeAVL = paramInt1 == paramInt2 ? null : paramNodeAVL.getRight(paramPersistentStore);
    if (localNodeAVL != null)
    {
      paramInt1++;
      paramNodeAVL = localNodeAVL;
      localNodeAVL = paramInt1 == paramInt2 ? null : paramNodeAVL.getLeft(paramPersistentStore);
      while (localNodeAVL != null)
      {
        paramInt1++;
        paramNodeAVL = localNodeAVL;
        if (paramInt1 == paramInt2) {
          localNodeAVL = null;
        } else {
          localNodeAVL = paramNodeAVL.getLeft(paramPersistentStore);
        }
      }
      paramArrayOfInt[0] = paramInt1;
      return paramNodeAVL;
    }
    localNodeAVL = paramNodeAVL;
    paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore);
    paramInt1--;
    while ((paramNodeAVL != null) && (paramNodeAVL.isRight(localNodeAVL)))
    {
      localNodeAVL = paramNodeAVL;
      paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore);
      paramInt1--;
    }
    paramArrayOfInt[0] = paramInt1;
    return paramNodeAVL;
  }
  
  NodeAVL last(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    if (paramNodeAVL == null) {
      return null;
    }
    NodeAVL localNodeAVL = paramNodeAVL.getLeft(paramPersistentStore);
    if (localNodeAVL != null)
    {
      paramNodeAVL = localNodeAVL;
      for (localNodeAVL = paramNodeAVL.getRight(paramPersistentStore); localNodeAVL != null; localNodeAVL = paramNodeAVL.getRight(paramPersistentStore)) {
        paramNodeAVL = localNodeAVL;
      }
      return paramNodeAVL;
    }
    localNodeAVL = paramNodeAVL;
    for (paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore); (paramNodeAVL != null) && (paramNodeAVL.isLeft(localNodeAVL)); paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore)) {
      localNodeAVL = paramNodeAVL;
    }
    return paramNodeAVL;
  }
  
  boolean isEqualReadable(Session paramSession, PersistentStore paramPersistentStore, NodeAVL paramNodeAVL)
  {
    NodeAVL localNodeAVL = paramNodeAVL;
    Row localRow = paramNodeAVL.getRow(paramPersistentStore);
    paramSession.database.txManager.setTransactionInfo(localRow);
    if (paramSession.database.txManager.canRead(paramSession, localRow, 1, null)) {
      return true;
    }
    Object[] arrayOfObject1 = paramNodeAVL.getData(paramPersistentStore);
    Object[] arrayOfObject2;
    do
    {
      localNodeAVL = last(paramPersistentStore, localNodeAVL);
      if (localNodeAVL == null) {
        break;
      }
      arrayOfObject2 = localNodeAVL.getData(paramPersistentStore);
      if (compareRow(paramSession, arrayOfObject1, arrayOfObject2) != 0) {
        break;
      }
      localRow = localNodeAVL.getRow(paramPersistentStore);
      paramSession.database.txManager.setTransactionInfo(localRow);
    } while (!paramSession.database.txManager.canRead(paramSession, localRow, 1, null));
    return true;
    do
    {
      localNodeAVL = next(paramSession, paramPersistentStore, paramNodeAVL, 0);
      if (localNodeAVL == null) {
        break;
      }
      arrayOfObject2 = localNodeAVL.getData(paramPersistentStore);
      if (compareRow(paramSession, arrayOfObject1, arrayOfObject2) != 0) {
        break;
      }
      localRow = localNodeAVL.getRow(paramPersistentStore);
      paramSession.database.txManager.setTransactionInfo(localRow);
    } while (!paramSession.database.txManager.canRead(paramSession, localRow, 1, null));
    return true;
    return false;
  }
  
  NodeAVL findNode(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    this.readLock.lock();
    try
    {
      Object localObject1 = getAccessor(paramPersistentStore);
      NodeAVL localNodeAVL = null;
      Object localObject2 = null;
      Row localRow = null;
      if ((paramInt2 != 41) && (paramInt2 != 47)) {
        paramInt1--;
      }
      while (localObject1 != null)
      {
        localRow = ((NodeAVL)localObject1).getRow(paramPersistentStore);
        int i = 0;
        if (paramInt1 > 0) {
          i = compareRowNonUnique(paramSession, localRow.getData(), paramArrayOfObject, paramArrayOfInt, paramInt1);
        }
        if (i == 0) {
          switch (paramInt2)
          {
          case 41: 
          case 47: 
            localObject2 = localObject1;
            localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
            break;
          case 43: 
          case 48: 
            i = compareObject(paramSession, localRow.getData(), paramArrayOfObject, paramArrayOfInt, paramInt1);
            if (i <= 0)
            {
              localNodeAVL = ((NodeAVL)localObject1).getRight(paramPersistentStore);
            }
            else
            {
              localObject2 = localObject1;
              localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
            }
            break;
          case 42: 
            i = compareObject(paramSession, localRow.getData(), paramArrayOfObject, paramArrayOfInt, paramInt1);
            if (i < 0)
            {
              localNodeAVL = ((NodeAVL)localObject1).getRight(paramPersistentStore);
            }
            else
            {
              localObject2 = localObject1;
              localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
            }
            break;
          case 44: 
            i = compareObject(paramSession, localRow.getData(), paramArrayOfObject, paramArrayOfInt, paramInt1);
            if (i < 0)
            {
              localObject2 = localObject1;
              localNodeAVL = ((NodeAVL)localObject1).getRight(paramPersistentStore);
            }
            else
            {
              localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
            }
            break;
          case 45: 
            i = compareObject(paramSession, localRow.getData(), paramArrayOfObject, paramArrayOfInt, paramInt1);
            if (i <= 0)
            {
              localObject2 = localObject1;
              localNodeAVL = ((NodeAVL)localObject1).getRight(paramPersistentStore);
            }
            else
            {
              localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
            }
            break;
          case 46: 
          default: 
            Error.runtimeError(201, "Index");
            break;
          }
        } else if (i < 0) {
          localNodeAVL = ((NodeAVL)localObject1).getRight(paramPersistentStore);
        } else if (i > 0) {
          localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
        }
        if (localNodeAVL == null) {
          break;
        }
        localObject1 = localNodeAVL;
      }
      if (paramSession == null)
      {
        localObject3 = localObject2;
        return localObject3;
      }
      while (localObject2 != null)
      {
        localRow = ((NodeAVL)localObject2).getRow(paramPersistentStore);
        if (!paramSession.database.txManager.canRead(paramSession, localRow, paramInt3, this.colIndex))
        {
          localObject2 = paramBoolean ? last(paramPersistentStore, (NodeAVL)localObject2) : next(paramPersistentStore, (NodeAVL)localObject2);
          if (localObject2 != null)
          {
            localRow = ((NodeAVL)localObject2).getRow(paramPersistentStore);
            if ((paramInt1 > 0) && (compareRowNonUnique(paramSession, localRow.getData(), paramArrayOfObject, paramArrayOfInt, paramInt1) != 0)) {
              localObject2 = null;
            }
          }
        }
      }
      Object localObject3 = localObject2;
      return localObject3;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  NodeAVL findNode(Session paramSession, PersistentStore paramPersistentStore, Object paramObject, int paramInt1, int paramInt2)
  {
    this.readLock.lock();
    try
    {
      Object localObject1 = getAccessor(paramPersistentStore);
      NodeAVL localNodeAVL = null;
      Object localObject2 = null;
      Row localRow = null;
      while (localObject1 != null)
      {
        localRow = ((NodeAVL)localObject1).getRow(paramPersistentStore);
        int i = this.colTypes[0].compare(paramSession, paramObject, localRow.getData()[this.colIndex[0]]);
        switch (paramInt1)
        {
        case 41: 
        case 47: 
          if (i == 0)
          {
            localObject2 = localObject1;
            localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
          }
          else if (i > 0)
          {
            localNodeAVL = ((NodeAVL)localObject1).getRight(paramPersistentStore);
          }
          else if (i < 0)
          {
            localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
          }
          break;
        case 43: 
        case 48: 
          if (i >= 0)
          {
            localNodeAVL = ((NodeAVL)localObject1).getRight(paramPersistentStore);
          }
          else
          {
            localObject2 = localObject1;
            localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
          }
          break;
        case 42: 
          if (i > 0)
          {
            localNodeAVL = ((NodeAVL)localObject1).getRight(paramPersistentStore);
          }
          else
          {
            localObject2 = localObject1;
            localNodeAVL = ((NodeAVL)localObject1).getLeft(paramPersistentStore);
          }
          break;
        case 44: 
        case 45: 
        case 46: 
        default: 
          Error.runtimeError(201, "Index");
        }
        if (localNodeAVL == null) {
          break;
        }
        localObject1 = localNodeAVL;
      }
      if (paramSession == null)
      {
        localObject3 = localObject2;
        return localObject3;
      }
      while (localObject2 != null)
      {
        localRow = ((NodeAVL)localObject2).getRow(paramPersistentStore);
        if (!paramSession.database.txManager.canRead(paramSession, localRow, paramInt2, this.colIndex))
        {
          localObject2 = next(paramPersistentStore, (NodeAVL)localObject2);
          if ((paramInt1 == 41) && (this.colTypes[0].compare(paramSession, paramObject, localRow.getData()[this.colIndex[0]]) != 0)) {
            localObject2 = null;
          }
        }
      }
      Object localObject3 = localObject2;
      return localObject3;
    }
    finally
    {
      this.readLock.unlock();
    }
  }
  
  void balance(PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, boolean paramBoolean)
  {
    for (;;)
    {
      int i = paramBoolean ? 1 : -1;
      switch (paramNodeAVL.getBalance(paramPersistentStore) * i)
      {
      case 1: 
        paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, 0);
        return;
      case 0: 
        paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, -i);
        break;
      case -1: 
        NodeAVL localNodeAVL1 = paramNodeAVL.child(paramPersistentStore, paramBoolean);
        if (localNodeAVL1.getBalance(paramPersistentStore) == -i)
        {
          paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL1);
          paramNodeAVL = paramNodeAVL.set(paramPersistentStore, paramBoolean, localNodeAVL1.child(paramPersistentStore, !paramBoolean));
          localNodeAVL1 = localNodeAVL1.set(paramPersistentStore, !paramBoolean, paramNodeAVL);
          paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, 0);
          localNodeAVL1 = localNodeAVL1.setBalance(paramPersistentStore, 0);
        }
        else
        {
          NodeAVL localNodeAVL2 = localNodeAVL1.child(paramPersistentStore, !paramBoolean);
          paramNodeAVL.replace(paramPersistentStore, this, localNodeAVL2);
          localNodeAVL1 = localNodeAVL1.set(paramPersistentStore, !paramBoolean, localNodeAVL2.child(paramPersistentStore, paramBoolean));
          localNodeAVL2 = localNodeAVL2.set(paramPersistentStore, paramBoolean, localNodeAVL1);
          paramNodeAVL = paramNodeAVL.set(paramPersistentStore, paramBoolean, localNodeAVL2.child(paramPersistentStore, !paramBoolean));
          localNodeAVL2 = localNodeAVL2.set(paramPersistentStore, !paramBoolean, paramNodeAVL);
          int j = localNodeAVL2.getBalance(paramPersistentStore);
          paramNodeAVL = paramNodeAVL.setBalance(paramPersistentStore, j == -i ? i : 0);
          localNodeAVL1 = localNodeAVL1.setBalance(paramPersistentStore, j == i ? -i : 0);
          localNodeAVL2 = localNodeAVL2.setBalance(paramPersistentStore, 0);
        }
        return;
      }
      if (paramNodeAVL.isRoot(paramPersistentStore)) {
        return;
      }
      paramBoolean = paramNodeAVL.isFromLeft(paramPersistentStore);
      paramNodeAVL = paramNodeAVL.getParent(paramPersistentStore);
    }
  }
  
  NodeAVL getAccessor(PersistentStore paramPersistentStore)
  {
    NodeAVL localNodeAVL = (NodeAVL)paramPersistentStore.getAccessor(this);
    return localNodeAVL;
  }
  
  IndexRowIterator getIterator(Session paramSession, PersistentStore paramPersistentStore, NodeAVL paramNodeAVL, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramNodeAVL == null) {
      return emptyIterator;
    }
    IndexRowIterator localIndexRowIterator = new IndexRowIterator(paramSession, paramPersistentStore, this, paramNodeAVL, 0, paramBoolean1, paramBoolean2);
    return localIndexRowIterator;
  }
  
  public static final class IndexRowIterator
    implements RowIterator
  {
    final Session session;
    final PersistentStore store;
    final IndexAVL index;
    NodeAVL nextnode;
    Row lastrow;
    int distinctCount;
    boolean single;
    boolean reversed;
    
    public IndexRowIterator(Session paramSession, PersistentStore paramPersistentStore, IndexAVL paramIndexAVL, NodeAVL paramNodeAVL, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.session = paramSession;
      this.store = paramPersistentStore;
      this.index = paramIndexAVL;
      this.distinctCount = paramInt;
      this.single = paramBoolean1;
      this.reversed = paramBoolean2;
      if (paramIndexAVL == null) {
        return;
      }
      this.nextnode = paramNodeAVL;
    }
    
    public boolean hasNext()
    {
      return this.nextnode != null;
    }
    
    public Row getNextRow()
    {
      if (this.nextnode == null)
      {
        release();
        return null;
      }
      NodeAVL localNodeAVL = this.nextnode;
      if (this.single)
      {
        this.nextnode = null;
      }
      else
      {
        this.index.readLock.lock();
        this.store.writeLock();
        try
        {
          if (this.reversed) {
            this.nextnode = this.index.last(this.session, this.store, this.nextnode, this.distinctCount);
          } else {
            this.nextnode = this.index.next(this.session, this.store, this.nextnode, this.distinctCount);
          }
        }
        finally
        {
          this.store.writeUnlock();
          this.index.readLock.unlock();
        }
      }
      this.lastrow = localNodeAVL.getRow(this.store);
      return this.lastrow;
    }
    
    public Object[] getNext()
    {
      Row localRow = getNextRow();
      return localRow == null ? null : localRow.getData();
    }
    
    public void remove()
    {
      this.store.delete(this.session, this.lastrow);
      this.store.remove(this.lastrow.getPos());
    }
    
    public void release() {}
    
    public boolean setRowColumns(boolean[] paramArrayOfBoolean)
    {
      return false;
    }
    
    public long getRowId()
    {
      return this.nextnode.getPos();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.index.IndexAVL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */