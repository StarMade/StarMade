package org.newdawn.slick.openal;

import java.io.IOException;

abstract interface AudioInputStream
{
  public abstract int getChannels();
  
  public abstract int getRate();
  
  public abstract int read()
    throws IOException;
  
  public abstract int read(byte[] paramArrayOfByte)
    throws IOException;
  
  public abstract int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract boolean atEnd();
  
  public abstract void close()
    throws IOException;
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.AudioInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */