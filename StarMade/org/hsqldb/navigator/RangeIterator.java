package org.hsqldb.navigator;

import org.hsqldb.Row;

public abstract interface RangeIterator extends RowIterator
{
  public abstract boolean isBeforeFirst();

  public abstract boolean next();

  public abstract Row getCurrentRow();

  public abstract Object[] getCurrent();

  public abstract Object getCurrent(int paramInt);

  public abstract void setCurrent(Object[] paramArrayOfObject);

  public abstract Object getRowidObject();

  public abstract void remove();

  public abstract void reset();

  public abstract int getRangePosition();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.navigator.RangeIterator
 * JD-Core Version:    0.6.2
 */