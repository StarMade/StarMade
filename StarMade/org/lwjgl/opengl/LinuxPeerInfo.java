package org.lwjgl.opengl;

import java.nio.ByteBuffer;

abstract class LinuxPeerInfo
  extends PeerInfo
{
  LinuxPeerInfo()
  {
    super(createHandle());
  }
  
  private static native ByteBuffer createHandle();
  
  public final long getDisplay()
  {
    return nGetDisplay(getHandle());
  }
  
  private static native long nGetDisplay(ByteBuffer paramByteBuffer);
  
  public final long getDrawable()
  {
    return nGetDrawable(getHandle());
  }
  
  private static native long nGetDrawable(ByteBuffer paramByteBuffer);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.LinuxPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */