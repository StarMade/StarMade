package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.BigList;

public abstract interface ByteBigList
  extends BigList<Byte>, ByteCollection, Comparable<BigList<? extends Byte>>
{
  public abstract ByteBigListIterator iterator();
  
  public abstract ByteBigListIterator listIterator();
  
  public abstract ByteBigListIterator listIterator(long paramLong);
  
  public abstract ByteBigList subList(long paramLong1, long paramLong2);
  
  public abstract void getElements(long paramLong1, byte[][] paramArrayOfByte, long paramLong2, long paramLong3);
  
  public abstract void removeElements(long paramLong1, long paramLong2);
  
  public abstract void addElements(long paramLong, byte[][] paramArrayOfByte);
  
  public abstract void addElements(long paramLong1, byte[][] paramArrayOfByte, long paramLong2, long paramLong3);
  
  public abstract void add(long paramLong, byte paramByte);
  
  public abstract boolean addAll(long paramLong, ByteCollection paramByteCollection);
  
  public abstract boolean addAll(long paramLong, ByteBigList paramByteBigList);
  
  public abstract boolean addAll(ByteBigList paramByteBigList);
  
  public abstract byte getByte(long paramLong);
  
  public abstract long indexOf(byte paramByte);
  
  public abstract long lastIndexOf(byte paramByte);
  
  public abstract byte removeByte(long paramLong);
  
  public abstract byte set(long paramLong, byte paramByte);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */