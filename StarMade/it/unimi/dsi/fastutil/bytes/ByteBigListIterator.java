package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.BigListIterator;

public abstract interface ByteBigListIterator
  extends ByteBidirectionalIterator, BigListIterator<Byte>
{
  public abstract void set(byte paramByte);
  
  public abstract void add(byte paramByte);
  
  public abstract void set(Byte paramByte);
  
  public abstract void add(Byte paramByte);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */