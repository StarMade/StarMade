package org.lwjgl.util;

public abstract interface WritableRectangle
  extends WritablePoint, WritableDimension
{
  public abstract void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract void setBounds(ReadablePoint paramReadablePoint, ReadableDimension paramReadableDimension);
  
  public abstract void setBounds(ReadableRectangle paramReadableRectangle);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.WritableRectangle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */