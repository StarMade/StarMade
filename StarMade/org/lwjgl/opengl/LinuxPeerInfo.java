/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */
/* 39:   */abstract class LinuxPeerInfo
/* 40:   */  extends PeerInfo
/* 41:   */{
/* 42:   */  LinuxPeerInfo()
/* 43:   */  {
/* 44:44 */    super(createHandle());
/* 45:   */  }
/* 46:   */  
/* 47:   */  private static native ByteBuffer createHandle();
/* 48:   */  
/* 49:49 */  public final long getDisplay() { return nGetDisplay(getHandle()); }
/* 50:   */  
/* 51:   */  private static native long nGetDisplay(ByteBuffer paramByteBuffer);
/* 52:   */  
/* 53:   */  public final long getDrawable() {
/* 54:54 */    return nGetDrawable(getHandle());
/* 55:   */  }
/* 56:   */  
/* 57:   */  private static native long nGetDrawable(ByteBuffer paramByteBuffer);
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */