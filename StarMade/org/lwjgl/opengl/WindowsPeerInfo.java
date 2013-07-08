/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import org.lwjgl.LWJGLException;
/*  6:   */
/* 42:   */abstract class WindowsPeerInfo
/* 43:   */  extends PeerInfo
/* 44:   */{
/* 45:   */  protected WindowsPeerInfo()
/* 46:   */  {
/* 47:47 */    super(createHandle());
/* 48:   */  }
/* 49:   */  
/* 50:   */  private static native ByteBuffer createHandle();
/* 51:   */  
/* 52:52 */  protected static int choosePixelFormat(long hdc, int origin_x, int origin_y, PixelFormat pixel_format, IntBuffer pixel_format_caps, boolean use_hdc_bpp, boolean support_window, boolean support_pbuffer, boolean double_buffered) throws LWJGLException { return nChoosePixelFormat(hdc, origin_x, origin_y, pixel_format, pixel_format_caps, use_hdc_bpp, support_window, support_pbuffer, double_buffered); }
/* 53:   */  
/* 54:   */  private static native int nChoosePixelFormat(long paramLong, int paramInt1, int paramInt2, PixelFormat paramPixelFormat, IntBuffer paramIntBuffer, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) throws LWJGLException;
/* 55:   */  
/* 56:   */  protected static native void setPixelFormat(long paramLong, int paramInt) throws LWJGLException;
/* 57:   */  
/* 58:58 */  public final long getHdc() { return nGetHdc(getHandle()); }
/* 59:   */  
/* 60:   */  private static native long nGetHdc(ByteBuffer paramByteBuffer);
/* 61:   */  
/* 62:   */  public final long getHwnd() {
/* 63:63 */    return nGetHwnd(getHandle());
/* 64:   */  }
/* 65:   */  
/* 66:   */  private static native long nGetHwnd(ByteBuffer paramByteBuffer);
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */