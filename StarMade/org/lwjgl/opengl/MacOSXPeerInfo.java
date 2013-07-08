/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.LWJGLException;
/*  5:   */import org.lwjgl.LWJGLUtil;
/*  6:   */
/* 41:   */abstract class MacOSXPeerInfo
/* 42:   */  extends PeerInfo
/* 43:   */{
/* 44:   */  MacOSXPeerInfo(PixelFormat pixel_format, ContextAttribs attribs, boolean use_display_bpp, boolean support_window, boolean support_pbuffer, boolean double_buffered)
/* 45:   */    throws LWJGLException
/* 46:   */  {
/* 47:47 */    super(createHandle());
/* 48:   */    
/* 49:49 */    boolean gl32 = (attribs != null) && (attribs.getMajorVersion() == 3) && (attribs.getMinorVersion() == 2) && (attribs.isProfileCore());
/* 50:50 */    if ((gl32) && (!LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 7))) {
/* 51:51 */      throw new LWJGLException("OpenGL 3.2 requested, but it requires MacOS X 10.7 or newer");
/* 52:   */    }
/* 53:53 */    choosePixelFormat(pixel_format, gl32, use_display_bpp, support_window, support_pbuffer, double_buffered);
/* 54:   */  }
/* 55:   */  
/* 56:   */  private static native ByteBuffer createHandle();
/* 57:   */  
/* 58:58 */  private void choosePixelFormat(PixelFormat pixel_format, boolean gl32, boolean use_display_bpp, boolean support_window, boolean support_pbuffer, boolean double_buffered) throws LWJGLException { nChoosePixelFormat(getHandle(), pixel_format, gl32, use_display_bpp, support_window, support_pbuffer, double_buffered); }
/* 59:   */  
/* 60:   */  private static native void nChoosePixelFormat(ByteBuffer paramByteBuffer, PixelFormat paramPixelFormat, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5) throws LWJGLException;
/* 61:   */  
/* 62:   */  public void destroy() {
/* 63:63 */    nDestroy(getHandle());
/* 64:   */  }
/* 65:   */  
/* 66:   */  private static native void nDestroy(ByteBuffer paramByteBuffer);
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */