package it.unimi.dsi.fastutil.booleans;

import java.util.Collection;

public abstract interface BooleanCollection
  extends Collection<Boolean>, BooleanIterable
{
  public abstract BooleanIterator iterator();
  
  @Deprecated
  public abstract BooleanIterator booleanIterator();
  
  public abstract <T> T[] toArray(T[] paramArrayOfT);
  
  public abstract boolean contains(boolean paramBoolean);
  
  public abstract boolean[] toBooleanArray();
  
  public abstract boolean[] toBooleanArray(boolean[] paramArrayOfBoolean);
  
  public abstract boolean[] toArray(boolean[] paramArrayOfBoolean);
  
  public abstract boolean add(boolean paramBoolean);
  
  public abstract boolean rem(boolean paramBoolean);
  
  public abstract boolean addAll(BooleanCollection paramBooleanCollection);
  
  public abstract boolean containsAll(BooleanCollection paramBooleanCollection);
  
  public abstract boolean removeAll(BooleanCollection paramBooleanCollection);
  
  public abstract boolean retainAll(BooleanCollection paramBooleanCollection);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */