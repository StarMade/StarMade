package it.unimi.dsi.fastutil.booleans;

import java.util.List;

public abstract interface BooleanList
  extends List<Boolean>, Comparable<List<? extends Boolean>>, BooleanCollection
{
  public abstract BooleanListIterator iterator();
  
  @Deprecated
  public abstract BooleanListIterator booleanListIterator();
  
  @Deprecated
  public abstract BooleanListIterator booleanListIterator(int paramInt);
  
  public abstract BooleanListIterator listIterator();
  
  public abstract BooleanListIterator listIterator(int paramInt);
  
  @Deprecated
  public abstract BooleanList booleanSubList(int paramInt1, int paramInt2);
  
  public abstract BooleanList subList(int paramInt1, int paramInt2);
  
  public abstract void size(int paramInt);
  
  public abstract void getElements(int paramInt1, boolean[] paramArrayOfBoolean, int paramInt2, int paramInt3);
  
  public abstract void removeElements(int paramInt1, int paramInt2);
  
  public abstract void addElements(int paramInt, boolean[] paramArrayOfBoolean);
  
  public abstract void addElements(int paramInt1, boolean[] paramArrayOfBoolean, int paramInt2, int paramInt3);
  
  public abstract boolean add(boolean paramBoolean);
  
  public abstract void add(int paramInt, boolean paramBoolean);
  
  public abstract boolean addAll(int paramInt, BooleanCollection paramBooleanCollection);
  
  public abstract boolean addAll(int paramInt, BooleanList paramBooleanList);
  
  public abstract boolean addAll(BooleanList paramBooleanList);
  
  public abstract boolean getBoolean(int paramInt);
  
  public abstract int indexOf(boolean paramBoolean);
  
  public abstract int lastIndexOf(boolean paramBoolean);
  
  public abstract boolean removeBoolean(int paramInt);
  
  public abstract boolean set(int paramInt, boolean paramBoolean);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */