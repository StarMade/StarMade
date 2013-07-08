package it.unimi.dsi.fastutil.bytes;

import java.util.List;

public abstract interface ByteList
  extends List<Byte>, Comparable<List<? extends Byte>>, ByteCollection
{
  public abstract ByteListIterator iterator();
  
  @Deprecated
  public abstract ByteListIterator byteListIterator();
  
  @Deprecated
  public abstract ByteListIterator byteListIterator(int paramInt);
  
  public abstract ByteListIterator listIterator();
  
  public abstract ByteListIterator listIterator(int paramInt);
  
  @Deprecated
  public abstract ByteList byteSubList(int paramInt1, int paramInt2);
  
  public abstract ByteList subList(int paramInt1, int paramInt2);
  
  public abstract void size(int paramInt);
  
  public abstract void getElements(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3);
  
  public abstract void removeElements(int paramInt1, int paramInt2);
  
  public abstract void addElements(int paramInt, byte[] paramArrayOfByte);
  
  public abstract void addElements(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3);
  
  public abstract boolean add(byte paramByte);
  
  public abstract void add(int paramInt, byte paramByte);
  
  public abstract boolean addAll(int paramInt, ByteCollection paramByteCollection);
  
  public abstract boolean addAll(int paramInt, ByteList paramByteList);
  
  public abstract boolean addAll(ByteList paramByteList);
  
  public abstract byte getByte(int paramInt);
  
  public abstract int indexOf(byte paramByte);
  
  public abstract int lastIndexOf(byte paramByte);
  
  public abstract byte removeByte(int paramInt);
  
  public abstract byte set(int paramInt, byte paramByte);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */