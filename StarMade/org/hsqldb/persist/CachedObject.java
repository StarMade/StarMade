package org.hsqldb.persist;

import org.hsqldb.lib.LongLookup;
import org.hsqldb.rowio.RowOutputInterface;

public abstract interface CachedObject
{
  public static final CachedObject[] emptyArray = new CachedObject[0];

  public abstract boolean isMemory();

  public abstract void updateAccessCount(int paramInt);

  public abstract int getAccessCount();

  public abstract void setStorageSize(int paramInt);

  public abstract int getStorageSize();

  public abstract long getPos();

  public abstract void setPos(long paramLong);

  public abstract boolean isNew();

  public abstract boolean hasChanged();

  public abstract boolean isKeepInMemory();

  public abstract boolean keepInMemory(boolean paramBoolean);

  public abstract boolean isInMemory();

  public abstract void setInMemory(boolean paramBoolean);

  public abstract void restore();

  public abstract void destroy();

  public abstract int getRealSize(RowOutputInterface paramRowOutputInterface);

  public abstract void write(RowOutputInterface paramRowOutputInterface);

  public abstract void write(RowOutputInterface paramRowOutputInterface, LongLookup paramLongLookup);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.CachedObject
 * JD-Core Version:    0.6.2
 */