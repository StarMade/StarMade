package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Function;

public abstract interface Char2ByteFunction
  extends Function<Character, Byte>
{
  public abstract byte put(char paramChar, byte paramByte);
  
  public abstract byte get(char paramChar);
  
  public abstract byte remove(char paramChar);
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract void defaultReturnValue(byte paramByte);
  
  public abstract byte defaultReturnValue();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ByteFunction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */