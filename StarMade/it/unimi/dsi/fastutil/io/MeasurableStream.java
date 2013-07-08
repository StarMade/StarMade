package it.unimi.dsi.fastutil.io;

import java.io.IOException;

public abstract interface MeasurableStream
{
  public abstract long length()
    throws IOException;
  
  public abstract long position()
    throws IOException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.io.MeasurableStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */