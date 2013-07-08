package it.unimi.dsi.fastutil.shorts;

import java.util.List;

public abstract interface ShortList
  extends List<Short>, Comparable<List<? extends Short>>, ShortCollection
{
  public abstract ShortListIterator iterator();
  
  @Deprecated
  public abstract ShortListIterator shortListIterator();
  
  @Deprecated
  public abstract ShortListIterator shortListIterator(int paramInt);
  
  public abstract ShortListIterator listIterator();
  
  public abstract ShortListIterator listIterator(int paramInt);
  
  @Deprecated
  public abstract ShortList shortSubList(int paramInt1, int paramInt2);
  
  public abstract ShortList subList(int paramInt1, int paramInt2);
  
  public abstract void size(int paramInt);
  
  public abstract void getElements(int paramInt1, short[] paramArrayOfShort, int paramInt2, int paramInt3);
  
  public abstract void removeElements(int paramInt1, int paramInt2);
  
  public abstract void addElements(int paramInt, short[] paramArrayOfShort);
  
  public abstract void addElements(int paramInt1, short[] paramArrayOfShort, int paramInt2, int paramInt3);
  
  public abstract boolean add(short paramShort);
  
  public abstract void add(int paramInt, short paramShort);
  
  public abstract boolean addAll(int paramInt, ShortCollection paramShortCollection);
  
  public abstract boolean addAll(int paramInt, ShortList paramShortList);
  
  public abstract boolean addAll(ShortList paramShortList);
  
  public abstract short getShort(int paramInt);
  
  public abstract int indexOf(short paramShort);
  
  public abstract int lastIndexOf(short paramShort);
  
  public abstract short removeShort(int paramInt);
  
  public abstract short set(int paramInt, short paramShort);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */