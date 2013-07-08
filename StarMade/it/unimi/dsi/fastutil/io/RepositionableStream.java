package it.unimi.dsi.fastutil.io;

import java.io.IOException;

public abstract interface RepositionableStream
{
  public abstract void position(long paramLong)
    throws IOException;
  
  public abstract long position()
    throws IOException;
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.RepositionableStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */