package org.hsqldb.navigator;

import org.hsqldb.Row;

public abstract interface RowIterator
{
  public abstract Row getNextRow();
  
  public abstract Object[] getNext();
  
  public abstract boolean hasNext();
  
  public abstract void remove();
  
  public abstract boolean setRowColumns(boolean[] paramArrayOfBoolean);
  
  public abstract void release();
  
  public abstract long getRowId();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.navigator.RowIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */