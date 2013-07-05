package org.hsqldb.index;

import org.hsqldb.Row;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.TableBase;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.types.Type;

public abstract interface Index extends SchemaObject
{
  public static final int INDEX_NONE = 0;
  public static final int INDEX_NON_UNIQUE = 1;
  public static final int INDEX_UNIQUE = 2;
  public static final double minimumSelectivity = 16.0D;
  public static final double cachedFactor = 8.0D;
  public static final int probeDepth = 4;
  public static final Index[] emptyArray = new Index[0];
  public static final IndexUse[] emptyUseArray = new IndexUse[0];

  public abstract IndexUse[] asArray();

  public abstract RowIterator emptyIterator();

  public abstract int getPosition();

  public abstract void setPosition(int paramInt);

  public abstract long getPersistenceId();

  public abstract int getColumnCount();

  public abstract boolean isUnique();

  public abstract boolean isConstraint();

  public abstract int[] getColumns();

  public abstract Type[] getColumnTypes();

  public abstract boolean[] getColumnDesc();

  public abstract int[] getDefaultColumnMap();

  public abstract int getIndexOrderValue();

  public abstract boolean isForward();

  public abstract void setTable(TableBase paramTableBase);

  public abstract void setClustered(boolean paramBoolean);

  public abstract boolean isClustered();

  public abstract long size(Session paramSession, PersistentStore paramPersistentStore);

  public abstract long sizeUnique(PersistentStore paramPersistentStore);

  public abstract double[] searchCost(Session paramSession, PersistentStore paramPersistentStore);

  public abstract boolean isEmpty(PersistentStore paramPersistentStore);

  public abstract void checkIndex(PersistentStore paramPersistentStore);

  public abstract void insert(Session paramSession, PersistentStore paramPersistentStore, Row paramRow);

  public abstract void delete(Session paramSession, PersistentStore paramPersistentStore, Row paramRow);

  public abstract boolean existsParent(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfInt);

  public abstract RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, boolean[] paramArrayOfBoolean);

  public abstract RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject);

  public abstract RowIterator findFirstRow(Session paramSession, PersistentStore paramPersistentStore, Object[] paramArrayOfObject, int[] paramArrayOfInt);

  public abstract RowIterator findFirstRowNotNull(Session paramSession, PersistentStore paramPersistentStore);

  public abstract RowIterator firstRow(PersistentStore paramPersistentStore);

  public abstract RowIterator firstRow(Session paramSession, PersistentStore paramPersistentStore);

  public abstract RowIterator lastRow(Session paramSession, PersistentStore paramPersistentStore);

  public abstract int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfInt);

  public abstract int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfInt, int paramInt);

  public abstract int compareRowNonUnique(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt);

  public abstract int compareRow(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2);

  public static class IndexUse
  {
    public Index index;
    public int columnCount;

    public IndexUse(Index paramIndex, int paramInt)
    {
      this.index = paramIndex;
      this.columnCount = paramInt;
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.index.Index
 * JD-Core Version:    0.6.2
 */