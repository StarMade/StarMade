package org.lwjgl.opengl;

import java.awt.Canvas;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.MemoryUtil;

final class LinuxDisplay
  implements DisplayImplementation
{
  public static final int CurrentTime = 0;
  public static final int GrabSuccess = 0;
  public static final int AutoRepeatModeOff = 0;
  public static final int AutoRepeatModeOn = 1;
  public static final int AutoRepeatModeDefault = 2;
  public static final int None = 0;
  private static final int KeyPressMask = 1;
  private static final int KeyReleaseMask = 2;
  private static final int ButtonPressMask = 4;
  private static final int ButtonReleaseMask = 8;
  private static final int NotifyAncestor = 0;
  private static final int NotifyNonlinear = 3;
  private static final int NotifyPointer = 5;
  private static final int NotifyPointerRoot = 6;
  private static final int NotifyDetailNone = 7;
  private static final int SetModeInsert = 0;
  private static final int SaveSetRoot = 1;
  private static final int SaveSetUnmap = 1;
  private static final int X_SetInputFocus = 42;
  private static final int FULLSCREEN_LEGACY = 1;
  private static final int FULLSCREEN_NETWM = 2;
  private static final int WINDOWED = 3;
  private static int current_window_mode = 3;
  private static final int XRANDR = 10;
  private static final int XF86VIDMODE = 11;
  private static final int NONE = 12;
  private static long display;
  private static long current_window;
  private static long saved_error_handler;
  private static int display_connection_usage_count;
  private final LinuxEvent event_buffer = new LinuxEvent();
  private final LinuxEvent tmp_event_buffer = new LinuxEvent();
  private int current_displaymode_extension = 12;
  private long delete_atom;
  private PeerInfo peer_info;
  private ByteBuffer saved_gamma;
  private ByteBuffer current_gamma;
  private DisplayMode saved_mode;
  private DisplayMode current_mode;
  private XRandR.Screen[] savedXrandrConfig;
  private boolean keyboard_grabbed;
  private boolean pointer_grabbed;
  private boolean input_released;
  private boolean grab;
  private boolean focused;
  private boolean minimized;
  private boolean dirty;
  private boolean close_requested;
  private long current_cursor;
  private long blank_cursor;
  private boolean mouseInside = true;
  private boolean resizable;
  private boolean resized;
  private int window_x;
  private int window_y;
  private int window_width;
  private int window_height;
  private Canvas parent;
  private long parent_window;
  private static boolean xembedded;
  private long parent_proxy_focus_window;
  private boolean parent_focused;
  private boolean parent_focus_changed;
  private long last_window_focus = 0L;
  private LinuxKeyboard keyboard;
  private LinuxMouse mouse;
  private final FocusListener focus_listener = new FocusListener()
  {
    public void focusGained(FocusEvent local_e)
    {
      synchronized (GlobalLock.lock)
      {
        LinuxDisplay.this.parent_focused = true;
        LinuxDisplay.this.parent_focus_changed = true;
      }
    }
    
    public void focusLost(FocusEvent local_e)
    {
      synchronized (GlobalLock.lock)
      {
        LinuxDisplay.this.parent_focused = false;
        LinuxDisplay.this.parent_focus_changed = true;
      }
    }
  };
  
  /* Error */
  private static ByteBuffer getCurrentGammaRamp()
    throws LWJGLException
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: invokestatic 153	org/lwjgl/opengl/LinuxDisplay:isXF86VidModeSupported	()Z
    //   9: ifeq +21 -> 30
    //   12: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   15: invokestatic 161	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
    //   18: invokestatic 165	org/lwjgl/opengl/LinuxDisplay:nGetCurrentGammaRamp	(JI)Ljava/nio/ByteBuffer;
    //   21: astore_0
    //   22: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   25: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   28: aload_0
    //   29: areturn
    //   30: aconst_null
    //   31: astore_0
    //   32: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   35: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   38: aload_0
    //   39: areturn
    //   40: astore_1
    //   41: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   44: aload_1
    //   45: athrow
    //   46: astore_2
    //   47: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   50: aload_2
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   21	18	0	localByteBuffer	ByteBuffer
    //   40	5	1	localObject1	Object
    //   46	5	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	22	40	finally
    //   30	32	40	finally
    //   40	41	40	finally
    //   3	25	46	finally
    //   30	35	46	finally
    //   40	47	46	finally
  }
  
  private static native ByteBuffer nGetCurrentGammaRamp(long paramLong, int paramInt)
    throws LWJGLException;
  
  private static int getBestDisplayModeExtension()
  {
    int result;
    int result;
    if (isXrandrSupported())
    {
      LWJGLUtil.log("Using Xrandr for display mode switching");
      result = 10;
    }
    else
    {
      int result;
      if (isXF86VidModeSupported())
      {
        LWJGLUtil.log("Using XF86VidMode for display mode switching");
        result = 11;
      }
      else
      {
        LWJGLUtil.log("No display mode extensions available");
        result = 12;
      }
    }
    return result;
  }
  
  /* Error */
  private static boolean isXrandrSupported()
  {
    // Byte code:
    //   0: ldc 190
    //   2: invokestatic 196	org/lwjgl/opengl/Display:getPrivilegedBoolean	(Ljava/lang/String;)Z
    //   5: ifeq +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   13: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   16: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   19: invokestatic 200	org/lwjgl/opengl/LinuxDisplay:nIsXrandrSupported	(J)Z
    //   22: istore_0
    //   23: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   26: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   29: iload_0
    //   30: ireturn
    //   31: astore_1
    //   32: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   35: aload_1
    //   36: athrow
    //   37: astore_0
    //   38: new 202	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   45: ldc 205
    //   47: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload_0
    //   51: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokestatic 183	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
    //   60: iconst_0
    //   61: istore_1
    //   62: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   65: iload_1
    //   66: ireturn
    //   67: astore_2
    //   68: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   71: aload_2
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   22	8	0	bool1	boolean
    //   37	14	0	local_e	LWJGLException
    //   31	5	1	localObject1	Object
    //   61	5	1	bool2	boolean
    //   67	5	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   16	23	31	finally
    //   31	32	31	finally
    //   13	26	37	org/lwjgl/LWJGLException
    //   31	37	37	org/lwjgl/LWJGLException
    //   13	26	67	finally
    //   31	62	67	finally
    //   67	68	67	finally
  }
  
  private static native boolean nIsXrandrSupported(long paramLong)
    throws LWJGLException;
  
  /* Error */
  private static boolean isXF86VidModeSupported()
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   9: invokestatic 221	org/lwjgl/opengl/LinuxDisplay:nIsXF86VidModeSupported	(J)Z
    //   12: istore_0
    //   13: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   16: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   19: iload_0
    //   20: ireturn
    //   21: astore_1
    //   22: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   25: aload_1
    //   26: athrow
    //   27: astore_0
    //   28: new 202	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   35: ldc 223
    //   37: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_0
    //   41: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   44: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   47: invokestatic 183	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
    //   50: iconst_0
    //   51: istore_1
    //   52: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   55: iload_1
    //   56: ireturn
    //   57: astore_2
    //   58: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   61: aload_2
    //   62: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   12	8	0	bool1	boolean
    //   27	14	0	local_e	LWJGLException
    //   21	5	1	localObject1	Object
    //   51	5	1	bool2	boolean
    //   57	5	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	13	21	finally
    //   21	22	21	finally
    //   3	16	27	org/lwjgl/LWJGLException
    //   21	27	27	org/lwjgl/LWJGLException
    //   3	16	57	finally
    //   21	52	57	finally
    //   57	58	57	finally
  }
  
  private static native boolean nIsXF86VidModeSupported(long paramLong)
    throws LWJGLException;
  
  /* Error */
  private static boolean isNetWMFullscreenSupported()
    throws LWJGLException
  {
    // Byte code:
    //   0: ldc 226
    //   2: invokestatic 196	org/lwjgl/opengl/Display:getPrivilegedBoolean	(Ljava/lang/String;)Z
    //   5: ifeq +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   13: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   16: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   19: invokestatic 161	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
    //   22: invokestatic 230	org/lwjgl/opengl/LinuxDisplay:nIsNetWMFullscreenSupported	(JI)Z
    //   25: istore_0
    //   26: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   29: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   32: iload_0
    //   33: ireturn
    //   34: astore_1
    //   35: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   38: aload_1
    //   39: athrow
    //   40: astore_0
    //   41: new 202	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   48: ldc 232
    //   50: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_0
    //   54: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokestatic 183	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
    //   63: iconst_0
    //   64: istore_1
    //   65: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   68: iload_1
    //   69: ireturn
    //   70: astore_2
    //   71: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   74: aload_2
    //   75: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   25	8	0	bool1	boolean
    //   40	14	0	local_e	LWJGLException
    //   34	5	1	localObject1	Object
    //   64	5	1	bool2	boolean
    //   70	5	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   16	26	34	finally
    //   34	35	34	finally
    //   13	29	40	org/lwjgl/LWJGLException
    //   34	40	40	org/lwjgl/LWJGLException
    //   13	29	70	finally
    //   34	65	70	finally
    //   70	71	70	finally
  }
  
  private static native boolean nIsNetWMFullscreenSupported(long paramLong, int paramInt)
    throws LWJGLException;
  
  static void lockAWT()
  {
    try
    {
      
    }
    catch (LWJGLException local_e)
    {
      LWJGLUtil.log("Caught exception while locking AWT: " + local_e);
    }
  }
  
  private static native void nLockAWT()
    throws LWJGLException;
  
  static void unlockAWT()
  {
    try
    {
      
    }
    catch (LWJGLException local_e)
    {
      LWJGLUtil.log("Caught exception while unlocking AWT: " + local_e);
    }
  }
  
  private static native void nUnlockAWT()
    throws LWJGLException;
  
  static void incDisplay()
    throws LWJGLException
  {
    if (display_connection_usage_count == 0)
    {
      try
      {
        GLContext.loadOpenGLLibrary();
        org.lwjgl.opengles.GLContext.loadOpenGLLibrary();
      }
      catch (Throwable local_t) {}
      saved_error_handler = setErrorHandler();
      display = openDisplay();
    }
    display_connection_usage_count += 1;
  }
  
  private static native int callErrorHandler(long paramLong1, long paramLong2, long paramLong3);
  
  private static native long setErrorHandler();
  
  private static native long resetErrorHandler(long paramLong);
  
  private static native void synchronize(long paramLong, boolean paramBoolean);
  
  private static int globalErrorHandler(long display, long event_ptr, long error_display, long serial, long error_code, long request_code, long minor_code)
    throws LWJGLException
  {
    if ((xembedded) && (request_code == 42L)) {
      return 0;
    }
    if (display == getDisplay())
    {
      String error_msg = getErrorText(display, error_code);
      throw new LWJGLException("X Error - disp: 0x" + Long.toHexString(error_display) + " serial: " + serial + " error: " + error_msg + " request_code: " + request_code + " minor_code: " + minor_code);
    }
    if (saved_error_handler != 0L) {
      return callErrorHandler(saved_error_handler, display, event_ptr);
    }
    return 0;
  }
  
  private static native String getErrorText(long paramLong1, long paramLong2);
  
  static void decDisplay() {}
  
  static native long openDisplay()
    throws LWJGLException;
  
  static native void closeDisplay(long paramLong);
  
  private int getWindowMode(boolean fullscreen)
    throws LWJGLException
  {
    if (fullscreen)
    {
      if ((this.current_displaymode_extension == 10) && (isNetWMFullscreenSupported()))
      {
        LWJGLUtil.log("Using NetWM for fullscreen window");
        return 2;
      }
      LWJGLUtil.log("Using legacy mode for fullscreen window");
      return 1;
    }
    return 3;
  }
  
  static long getDisplay()
  {
    if (display_connection_usage_count <= 0) {
      throw new InternalError("display_connection_usage_count = " + display_connection_usage_count);
    }
    return display;
  }
  
  static int getDefaultScreen()
  {
    return nGetDefaultScreen(getDisplay());
  }
  
  static native int nGetDefaultScreen(long paramLong);
  
  static long getWindow()
  {
    return current_window;
  }
  
  private void ungrabKeyboard()
  {
    if (this.keyboard_grabbed)
    {
      nUngrabKeyboard(getDisplay());
      this.keyboard_grabbed = false;
    }
  }
  
  static native int nUngrabKeyboard(long paramLong);
  
  private void grabKeyboard()
  {
    if (!this.keyboard_grabbed)
    {
      int res = nGrabKeyboard(getDisplay(), getWindow());
      if (res == 0) {
        this.keyboard_grabbed = true;
      }
    }
  }
  
  static native int nGrabKeyboard(long paramLong1, long paramLong2);
  
  private void grabPointer()
  {
    if (!this.pointer_grabbed)
    {
      int result = nGrabPointer(getDisplay(), getWindow(), 0L);
      if (result == 0)
      {
        this.pointer_grabbed = true;
        if (isLegacyFullscreen()) {
          nSetViewPort(getDisplay(), getWindow(), getDefaultScreen());
        }
      }
    }
  }
  
  static native int nGrabPointer(long paramLong1, long paramLong2, long paramLong3);
  
  private static native void nSetViewPort(long paramLong1, long paramLong2, int paramInt);
  
  private void ungrabPointer()
  {
    if (this.pointer_grabbed)
    {
      this.pointer_grabbed = false;
      nUngrabPointer(getDisplay());
    }
  }
  
  static native int nUngrabPointer(long paramLong);
  
  private static boolean isFullscreen()
  {
    return (current_window_mode == 1) || (current_window_mode == 2);
  }
  
  private boolean shouldGrab()
  {
    return (!this.input_released) && (this.grab) && (this.mouse != null);
  }
  
  private void updatePointerGrab()
  {
    if ((isFullscreen()) || (shouldGrab())) {
      grabPointer();
    } else {
      ungrabPointer();
    }
    updateCursor();
  }
  
  private void updateCursor()
  {
    long cursor;
    long cursor;
    if (shouldGrab()) {
      cursor = this.blank_cursor;
    } else {
      cursor = this.current_cursor;
    }
    nDefineCursor(getDisplay(), getWindow(), cursor);
  }
  
  private static native void nDefineCursor(long paramLong1, long paramLong2, long paramLong3);
  
  private static boolean isLegacyFullscreen()
  {
    return current_window_mode == 1;
  }
  
  private void updateKeyboardGrab()
  {
    if (isLegacyFullscreen()) {
      grabKeyboard();
    } else {
      ungrabKeyboard();
    }
  }
  
  public void createWindow(DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int local_x, int local_y)
    throws LWJGLException
  {
    
    try
    {
      incDisplay();
      try
      {
        if ((drawable instanceof DrawableGLES)) {
          this.peer_info = new LinuxDisplayPeerInfo();
        }
        ByteBuffer handle = this.peer_info.lockAndGetHandle();
        try
        {
          current_window_mode = getWindowMode(Display.isFullscreen());
          if (current_window_mode != 3) {
            Compiz.setLegacyFullscreenSupport(true);
          }
          boolean undecorated = (Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated")) || ((current_window_mode != 3) && (Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated_fs")));
          this.parent = parent;
          this.parent_window = (parent != null ? getHandle(parent) : getRootWindow(getDisplay(), getDefaultScreen()));
          this.resizable = Display.isResizable();
          this.resized = false;
          this.window_x = local_x;
          this.window_y = local_y;
          this.window_width = mode.getWidth();
          this.window_height = mode.getHeight();
          current_window = nCreateWindow(getDisplay(), getDefaultScreen(), handle, mode, current_window_mode, local_x, local_y, undecorated, this.parent_window, this.resizable);
          mapRaised(getDisplay(), current_window);
          xembedded = (parent != null) && (isAncestorXEmbedded(this.parent_window));
          this.blank_cursor = createBlankCursor();
          this.current_cursor = 0L;
          this.focused = false;
          this.input_released = false;
          this.pointer_grabbed = false;
          this.keyboard_grabbed = false;
          this.close_requested = false;
          this.grab = false;
          this.minimized = false;
          this.dirty = true;
          if ((drawable instanceof DrawableGLES)) {
            ((DrawableGLES)drawable).initialize(current_window, getDisplay(), 4, (org.lwjgl.opengles.PixelFormat)drawable.getPixelFormat());
          }
          if (parent != null)
          {
            parent.addFocusListener(this.focus_listener);
            this.parent_focused = parent.isFocusOwner();
            this.parent_focus_changed = true;
          }
        }
        finally
        {
          this.peer_info.unlock();
        }
      }
      catch (LWJGLException handle)
      {
        throw handle;
      }
    }
    finally
    {
      unlockAWT();
    }
  }
  
  private static native long nCreateWindow(long paramLong1, int paramInt1, ByteBuffer paramByteBuffer, DisplayMode paramDisplayMode, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, long paramLong2, boolean paramBoolean2)
    throws LWJGLException;
  
  private static native long getRootWindow(long paramLong, int paramInt);
  
  private static native boolean hasProperty(long paramLong1, long paramLong2, long paramLong3);
  
  private static native long getParentWindow(long paramLong1, long paramLong2)
    throws LWJGLException;
  
  private static native int getChildCount(long paramLong1, long paramLong2)
    throws LWJGLException;
  
  private static native void mapRaised(long paramLong1, long paramLong2);
  
  private static native void reparentWindow(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2);
  
  private static native long nGetInputFocus(long paramLong)
    throws LWJGLException;
  
  private static native void nSetInputFocus(long paramLong1, long paramLong2, long paramLong3);
  
  private static native void nSetWindowSize(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean);
  
  private static native int nGetX(long paramLong1, long paramLong2);
  
  private static native int nGetY(long paramLong1, long paramLong2);
  
  private static native int nGetWidth(long paramLong1, long paramLong2);
  
  private static native int nGetHeight(long paramLong1, long paramLong2);
  
  private static boolean isAncestorXEmbedded(long window)
    throws LWJGLException
  {
    long xembed_atom = internAtom("_XEMBED_INFO", true);
    if (xembed_atom != 0L) {
      for (long local_w = window; local_w != 0L; local_w = getParentWindow(getDisplay(), local_w)) {
        if (hasProperty(getDisplay(), local_w, xembed_atom)) {
          return true;
        }
      }
    }
    return false;
  }
  
  private static long getHandle(Canvas parent)
    throws LWJGLException
  {
    AWTCanvasImplementation awt_impl = AWTGLCanvas.createImplementation();
    LinuxPeerInfo parent_peer_info = (LinuxPeerInfo)awt_impl.createPeerInfo(parent, null, null);
    ByteBuffer parent_peer_info_handle = parent_peer_info.lockAndGetHandle();
    try
    {
      long l = parent_peer_info.getDrawable();
      return l;
    }
    finally
    {
      parent_peer_info.unlock();
    }
  }
  
  private void updateInputGrab()
  {
    updatePointerGrab();
    updateKeyboardGrab();
  }
  
  public void destroyWindow()
  {
    
    try
    {
      if (this.parent != null) {
        this.parent.removeFocusListener(this.focus_listener);
      }
      try
      {
        setNativeCursor(null);
      }
      catch (LWJGLException local_e)
      {
        LWJGLUtil.log("Failed to reset cursor: " + local_e.getMessage());
      }
      nDestroyCursor(getDisplay(), this.blank_cursor);
      this.blank_cursor = 0L;
      ungrabKeyboard();
      nDestroyWindow(getDisplay(), getWindow());
      decDisplay();
      if (current_window_mode != 3) {
        Compiz.setLegacyFullscreenSupport(false);
      }
    }
    finally
    {
      unlockAWT();
    }
  }
  
  static native void nDestroyWindow(long paramLong1, long paramLong2);
  
  public void switchDisplayMode(DisplayMode mode)
    throws LWJGLException
  {
    
    try
    {
      switchDisplayModeOnTmpDisplay(mode);
      this.current_mode = mode;
    }
    finally
    {
      unlockAWT();
    }
  }
  
  private void switchDisplayModeOnTmpDisplay(DisplayMode mode)
    throws LWJGLException
  {
    
    try
    {
      nSwitchDisplayMode(getDisplay(), getDefaultScreen(), this.current_displaymode_extension, mode);
    }
    finally
    {
      decDisplay();
    }
  }
  
  private static native void nSwitchDisplayMode(long paramLong, int paramInt1, int paramInt2, DisplayMode paramDisplayMode)
    throws LWJGLException;
  
  private static long internAtom(String atom_name, boolean only_if_exists)
    throws LWJGLException
  {
    
    try
    {
      long l = nInternAtom(getDisplay(), atom_name, only_if_exists);
      return l;
    }
    finally
    {
      decDisplay();
    }
  }
  
  static native long nInternAtom(long paramLong, String paramString, boolean paramBoolean);
  
  public void resetDisplayMode()
  {
    
    try
    {
      if ((this.current_displaymode_extension == 10) && (this.savedXrandrConfig.length > 0)) {
        AccessController.doPrivileged(new PrivilegedAction()
        {
          public Object run()
          {
            XRandR.setConfiguration(LinuxDisplay.this.savedXrandrConfig);
            return null;
          }
        });
      } else {
        switchDisplayMode(this.saved_mode);
      }
      if (isXF86VidModeSupported()) {
        doSetGamma(this.saved_gamma);
      }
      Compiz.setLegacyFullscreenSupport(false);
    }
    catch (LWJGLException local_e)
    {
      LWJGLUtil.log("Caught exception while resetting mode: " + local_e);
    }
    finally
    {
      unlockAWT();
    }
  }
  
  /* Error */
  public int getGammaRampLength()
  {
    // Byte code:
    //   0: invokestatic 153	org/lwjgl/opengl/LinuxDisplay:isXF86VidModeSupported	()Z
    //   3: ifne +5 -> 8
    //   6: iconst_0
    //   7: ireturn
    //   8: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   11: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   14: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   17: invokestatic 161	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
    //   20: invokestatic 644	org/lwjgl/opengl/LinuxDisplay:nGetGammaRampLength	(JI)I
    //   23: istore_1
    //   24: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   27: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   30: iload_1
    //   31: ireturn
    //   32: astore_1
    //   33: new 202	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   40: ldc_w 646
    //   43: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: aload_1
    //   47: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokestatic 183	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
    //   56: iconst_0
    //   57: istore_2
    //   58: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   61: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   64: iload_2
    //   65: ireturn
    //   66: astore_3
    //   67: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   70: aload_3
    //   71: athrow
    //   72: astore_1
    //   73: new 202	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   80: ldc_w 648
    //   83: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: aload_1
    //   87: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: invokestatic 183	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
    //   96: iconst_0
    //   97: istore_2
    //   98: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   101: iload_2
    //   102: ireturn
    //   103: astore 4
    //   105: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   108: aload 4
    //   110: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	111	0	this	LinuxDisplay
    //   23	8	1	i	int
    //   32	15	1	local_e	LWJGLException
    //   72	15	1	local_e	LWJGLException
    //   57	45	2	j	int
    //   66	5	3	localObject1	Object
    //   103	6	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   14	24	32	org/lwjgl/LWJGLException
    //   14	24	66	finally
    //   32	58	66	finally
    //   66	67	66	finally
    //   11	27	72	org/lwjgl/LWJGLException
    //   32	61	72	org/lwjgl/LWJGLException
    //   66	72	72	org/lwjgl/LWJGLException
    //   11	27	103	finally
    //   32	61	103	finally
    //   66	98	103	finally
    //   103	105	103	finally
  }
  
  private static native int nGetGammaRampLength(long paramLong, int paramInt)
    throws LWJGLException;
  
  public void setGammaRamp(FloatBuffer gammaRamp)
    throws LWJGLException
  {
    if (!isXF86VidModeSupported()) {
      throw new LWJGLException("No gamma ramp support (Missing XF86VM extension)");
    }
    doSetGamma(convertToNativeRamp(gammaRamp));
  }
  
  private void doSetGamma(ByteBuffer native_gamma)
    throws LWJGLException
  {
    
    try
    {
      setGammaRampOnTmpDisplay(native_gamma);
      this.current_gamma = native_gamma;
    }
    finally
    {
      unlockAWT();
    }
  }
  
  private static void setGammaRampOnTmpDisplay(ByteBuffer native_gamma)
    throws LWJGLException
  {
    
    try
    {
      nSetGammaRamp(getDisplay(), getDefaultScreen(), native_gamma);
    }
    finally
    {
      decDisplay();
    }
  }
  
  private static native void nSetGammaRamp(long paramLong, int paramInt, ByteBuffer paramByteBuffer)
    throws LWJGLException;
  
  private static ByteBuffer convertToNativeRamp(FloatBuffer ramp)
    throws LWJGLException
  {
    return nConvertToNativeRamp(ramp, ramp.position(), ramp.remaining());
  }
  
  private static native ByteBuffer nConvertToNativeRamp(FloatBuffer paramFloatBuffer, int paramInt1, int paramInt2)
    throws LWJGLException;
  
  public String getAdapter()
  {
    return null;
  }
  
  public String getVersion()
  {
    return null;
  }
  
  public DisplayMode init()
    throws LWJGLException
  {
    
    try
    {
      Compiz.init();
      this.delete_atom = internAtom("WM_DELETE_WINDOW", false);
      this.current_displaymode_extension = getBestDisplayModeExtension();
      if (this.current_displaymode_extension == 12) {
        throw new LWJGLException("No display mode extension is available");
      }
      DisplayMode[] modes = getAvailableDisplayModes();
      if ((modes == null) || (modes.length == 0)) {
        throw new LWJGLException("No modes available");
      }
      switch (this.current_displaymode_extension)
      {
      case 10: 
        this.savedXrandrConfig = ((XRandR.Screen[])AccessController.doPrivileged(new PrivilegedAction())
        {
          public XRandR.Screen[] run()
          {
            return XRandR.getConfiguration();
          }
        }());
        this.saved_mode = getCurrentXRandrMode();
        break;
      case 11: 
        this.saved_mode = modes[0];
        break;
      default: 
        throw new LWJGLException("Unknown display mode extension: " + this.current_displaymode_extension);
      }
      this.current_mode = this.saved_mode;
      this.saved_gamma = getCurrentGammaRamp();
      this.current_gamma = this.saved_gamma;
      DisplayMode localDisplayMode = this.saved_mode;
      return localDisplayMode;
    }
    finally
    {
      unlockAWT();
    }
  }
  
  /* Error */
  private static DisplayMode getCurrentXRandrMode()
    throws LWJGLException
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   9: invokestatic 161	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
    //   12: invokestatic 716	org/lwjgl/opengl/LinuxDisplay:nGetCurrentXRandrMode	(JI)Lorg/lwjgl/opengl/DisplayMode;
    //   15: astore_0
    //   16: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   19: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   22: aload_0
    //   23: areturn
    //   24: astore_1
    //   25: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   28: aload_1
    //   29: athrow
    //   30: astore_2
    //   31: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   34: aload_2
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   15	8	0	localDisplayMode	DisplayMode
    //   24	5	1	localObject1	Object
    //   30	5	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	16	24	finally
    //   24	25	24	finally
    //   3	19	30	finally
    //   24	31	30	finally
  }
  
  private static native DisplayMode nGetCurrentXRandrMode(long paramLong, int paramInt)
    throws LWJGLException;
  
  public void setTitle(String title)
  {
    
    try
    {
      ByteBuffer titleText = MemoryUtil.encodeUTF8(title);
      nSetTitle(getDisplay(), getWindow(), MemoryUtil.getAddress(titleText), titleText.remaining() - 1);
    }
    finally
    {
      unlockAWT();
    }
  }
  
  private static native void nSetTitle(long paramLong1, long paramLong2, long paramLong3, int paramInt);
  
  public boolean isCloseRequested()
  {
    boolean result = this.close_requested;
    this.close_requested = false;
    return result;
  }
  
  public boolean isVisible()
  {
    return !this.minimized;
  }
  
  public boolean isActive()
  {
    return (this.focused) || (isLegacyFullscreen());
  }
  
  public boolean isDirty()
  {
    boolean result = this.dirty;
    this.dirty = false;
    return result;
  }
  
  public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs)
    throws LWJGLException
  {
    this.peer_info = new LinuxDisplayPeerInfo(pixel_format);
    return this.peer_info;
  }
  
  private void relayEventToParent(LinuxEvent event_buffer, int event_mask)
  {
    this.tmp_event_buffer.copyFrom(event_buffer);
    this.tmp_event_buffer.setWindow(this.parent_window);
    this.tmp_event_buffer.sendEvent(getDisplay(), this.parent_window, true, event_mask);
  }
  
  private void relayEventToParent(LinuxEvent event_buffer)
  {
    if (this.parent == null) {
      return;
    }
    switch (event_buffer.getType())
    {
    case 2: 
      relayEventToParent(event_buffer, 1);
      break;
    case 3: 
      relayEventToParent(event_buffer, 1);
      break;
    case 4: 
      if ((xembedded) || (!this.focused)) {
        relayEventToParent(event_buffer, 1);
      }
      break;
    case 5: 
      if ((xembedded) || (!this.focused)) {
        relayEventToParent(event_buffer, 1);
      }
      break;
    }
  }
  
  private void processEvents()
  {
    while (LinuxEvent.getPending(getDisplay()) > 0)
    {
      this.event_buffer.nextEvent(getDisplay());
      long event_window = this.event_buffer.getWindow();
      relayEventToParent(this.event_buffer);
      if ((event_window == getWindow()) && (!this.event_buffer.filterEvent(event_window)) && ((this.mouse == null) || (!this.mouse.filterEvent(this.grab, shouldWarpPointer(), this.event_buffer))) && ((this.keyboard == null) || (!this.keyboard.filterEvent(this.event_buffer)))) {
        switch (this.event_buffer.getType())
        {
        case 9: 
          setFocused(true, this.event_buffer.getFocusDetail());
          break;
        case 10: 
          setFocused(false, this.event_buffer.getFocusDetail());
          break;
        case 33: 
          if ((this.event_buffer.getClientFormat() == 32) && (this.event_buffer.getClientData(0) == this.delete_atom)) {
            this.close_requested = true;
          }
          break;
        case 19: 
          this.dirty = true;
          this.minimized = false;
          break;
        case 18: 
          this.dirty = true;
          this.minimized = true;
          break;
        case 12: 
          this.dirty = true;
          break;
        case 22: 
          int local_x = nGetX(getDisplay(), getWindow());
          int local_y = nGetY(getDisplay(), getWindow());
          int width = nGetWidth(getDisplay(), getWindow());
          int height = nGetHeight(getDisplay(), getWindow());
          this.window_x = local_x;
          this.window_y = local_y;
          if ((this.window_width != width) || (this.window_height != height))
          {
            this.resized = true;
            this.window_width = width;
            this.window_height = height;
          }
          break;
        case 7: 
          this.mouseInside = true;
          break;
        case 8: 
          this.mouseInside = false;
        }
      }
    }
  }
  
  public void update()
  {
    
    try
    {
      processEvents();
      checkInput();
    }
    finally
    {
      unlockAWT();
    }
  }
  
  public void reshape(int local_x, int local_y, int width, int height)
  {
    
    try
    {
      nReshape(getDisplay(), getWindow(), local_x, local_y, width, height);
    }
    finally
    {
      unlockAWT();
    }
  }
  
  private static native void nReshape(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  /* Error */
  public DisplayMode[] getAvailableDisplayModes()
    throws LWJGLException
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   9: invokestatic 161	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
    //   12: aload_0
    //   13: getfield 128	org/lwjgl/opengl/LinuxDisplay:current_displaymode_extension	I
    //   16: invokestatic 836	org/lwjgl/opengl/LinuxDisplay:nGetAvailableDisplayModes	(JII)[Lorg/lwjgl/opengl/DisplayMode;
    //   19: astore_1
    //   20: aload_1
    //   21: astore_2
    //   22: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   25: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   28: aload_2
    //   29: areturn
    //   30: astore_3
    //   31: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   34: aload_3
    //   35: athrow
    //   36: astore 4
    //   38: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   41: aload 4
    //   43: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	44	0	this	LinuxDisplay
    //   19	2	1	modes	DisplayMode[]
    //   21	8	2	arrayOfDisplayMode1	DisplayMode[]
    //   30	5	3	localObject1	Object
    //   36	6	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	22	30	finally
    //   30	31	30	finally
    //   3	25	36	finally
    //   30	38	36	finally
  }
  
  private static native DisplayMode[] nGetAvailableDisplayModes(long paramLong, int paramInt1, int paramInt2)
    throws LWJGLException;
  
  public boolean hasWheel()
  {
    return true;
  }
  
  public int getButtonCount()
  {
    return this.mouse.getButtonCount();
  }
  
  public void createMouse()
    throws LWJGLException
  {
    
    try
    {
      this.mouse = new LinuxMouse(getDisplay(), getWindow(), getWindow());
    }
    finally
    {
      unlockAWT();
    }
  }
  
  public void destroyMouse()
  {
    this.mouse = null;
    updateInputGrab();
  }
  
  public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons)
  {
    
    try
    {
      this.mouse.poll(this.grab, coord_buffer, buttons);
    }
    finally
    {
      unlockAWT();
    }
  }
  
  public void readMouse(ByteBuffer buffer)
  {
    
    try
    {
      this.mouse.read(buffer);
    }
    finally
    {
      unlockAWT();
    }
  }
  
  public void setCursorPosition(int local_x, int local_y)
  {
    
    try
    {
      this.mouse.setCursorPosition(local_x, local_y);
    }
    finally
    {
      unlockAWT();
    }
  }
  
  private void checkInput()
  {
    if (this.parent == null) {
      return;
    }
    if (xembedded)
    {
      long current_focus_window = 0L;
      if ((this.last_window_focus != current_focus_window) || (this.parent_focused != this.focused)) {
        if (isParentWindowActive(current_focus_window))
        {
          if (this.parent_focused)
          {
            nSetInputFocus(getDisplay(), current_window, 0L);
            this.last_window_focus = current_window;
            this.focused = true;
          }
          else
          {
            nSetInputFocus(getDisplay(), this.parent_proxy_focus_window, 0L);
            this.last_window_focus = this.parent_proxy_focus_window;
            this.focused = false;
          }
        }
        else
        {
          this.last_window_focus = current_focus_window;
          this.focused = false;
        }
      }
    }
    else if ((this.parent_focus_changed) && (this.parent_focused))
    {
      setInputFocusUnsafe(getWindow());
      this.parent_focus_changed = false;
    }
  }
  
  private void setInputFocusUnsafe(long window)
  {
    try
    {
      nSetInputFocus(getDisplay(), window, 0L);
      nSync(getDisplay(), false);
    }
    catch (LWJGLException local_e)
    {
      LWJGLUtil.log("Got exception while trying to focus: " + local_e);
    }
  }
  
  private static native void nSync(long paramLong, boolean paramBoolean)
    throws LWJGLException;
  
  private boolean isParentWindowActive(long window)
  {
    try
    {
      if (window == current_window) {
        return true;
      }
      if (getChildCount(getDisplay(), window) != 0) {
        return false;
      }
      long parent_window = getParentWindow(getDisplay(), window);
      if (parent_window == 0L) {
        return false;
      }
      long local_w = current_window;
      while (local_w != 0L)
      {
        local_w = getParentWindow(getDisplay(), local_w);
        if (local_w == parent_window)
        {
          this.parent_proxy_focus_window = window;
          return true;
        }
      }
    }
    catch (LWJGLException parent_window)
    {
      LWJGLUtil.log("Failed to detect if parent window is active: " + parent_window.getMessage());
      return true;
    }
    return false;
  }
  
  private void setFocused(boolean got_focus, int focus_detail)
  {
    if ((this.focused == got_focus) || (focus_detail == 7) || (focus_detail == 5) || (focus_detail == 6) || (xembedded)) {
      return;
    }
    this.focused = got_focus;
    if (this.focused) {
      acquireInput();
    } else {
      releaseInput();
    }
  }
  
  private void releaseInput()
  {
    if ((isLegacyFullscreen()) || (this.input_released)) {
      return;
    }
    this.input_released = true;
    updateInputGrab();
    if (current_window_mode == 2)
    {
      nIconifyWindow(getDisplay(), getWindow(), getDefaultScreen());
      try
      {
        if ((this.current_displaymode_extension == 10) && (this.savedXrandrConfig.length > 0)) {
          AccessController.doPrivileged(new PrivilegedAction()
          {
            public Object run()
            {
              XRandR.setConfiguration(LinuxDisplay.this.savedXrandrConfig);
              return null;
            }
          });
        } else {
          switchDisplayModeOnTmpDisplay(this.saved_mode);
        }
        setGammaRampOnTmpDisplay(this.saved_gamma);
      }
      catch (LWJGLException local_e)
      {
        LWJGLUtil.log("Failed to restore saved mode: " + local_e.getMessage());
      }
    }
  }
  
  private static native void nIconifyWindow(long paramLong1, long paramLong2, int paramInt);
  
  private void acquireInput()
  {
    if ((isLegacyFullscreen()) || (!this.input_released)) {
      return;
    }
    this.input_released = false;
    updateInputGrab();
    if (current_window_mode == 2) {
      try
      {
        switchDisplayModeOnTmpDisplay(this.current_mode);
        setGammaRampOnTmpDisplay(this.current_gamma);
      }
      catch (LWJGLException local_e)
      {
        LWJGLUtil.log("Failed to restore mode: " + local_e.getMessage());
      }
    }
  }
  
  public void grabMouse(boolean new_grab)
  {
    
    try
    {
      if (new_grab != this.grab)
      {
        this.grab = new_grab;
        updateInputGrab();
        this.mouse.changeGrabbed(this.grab, shouldWarpPointer());
      }
    }
    finally
    {
      unlockAWT();
    }
  }
  
  private boolean shouldWarpPointer()
  {
    return (this.pointer_grabbed) && (shouldGrab());
  }
  
  /* Error */
  public int getNativeCursorCapabilities()
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   9: invokestatic 910	org/lwjgl/opengl/LinuxDisplay:nGetNativeCursorCapabilities	(J)I
    //   12: istore_1
    //   13: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   16: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   19: iload_1
    //   20: ireturn
    //   21: astore_2
    //   22: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   25: aload_2
    //   26: athrow
    //   27: astore_1
    //   28: new 912	java/lang/RuntimeException
    //   31: dup
    //   32: aload_1
    //   33: invokespecial 915	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   36: athrow
    //   37: astore_3
    //   38: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   41: aload_3
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	LinuxDisplay
    //   12	8	1	i	int
    //   27	6	1	local_e	LWJGLException
    //   21	5	2	localObject1	Object
    //   37	5	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	13	21	finally
    //   21	22	21	finally
    //   3	16	27	org/lwjgl/LWJGLException
    //   21	27	27	org/lwjgl/LWJGLException
    //   3	16	37	finally
    //   21	38	37	finally
  }
  
  private static native int nGetNativeCursorCapabilities(long paramLong)
    throws LWJGLException;
  
  public void setNativeCursor(Object handle)
    throws LWJGLException
  {
    this.current_cursor = getCursorHandle(handle);
    lockAWT();
    try
    {
      updateCursor();
    }
    finally
    {
      unlockAWT();
    }
  }
  
  /* Error */
  public int getMinCursorSize()
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   9: invokestatic 349	org/lwjgl/opengl/LinuxDisplay:getWindow	()J
    //   12: invokestatic 924	org/lwjgl/opengl/LinuxDisplay:nGetMinCursorSize	(JJ)I
    //   15: istore_1
    //   16: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   19: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   22: iload_1
    //   23: ireturn
    //   24: astore_2
    //   25: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   28: aload_2
    //   29: athrow
    //   30: astore_1
    //   31: new 202	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   38: ldc_w 926
    //   41: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: aload_1
    //   45: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: invokestatic 183	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
    //   54: iconst_0
    //   55: istore_2
    //   56: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   59: iload_2
    //   60: ireturn
    //   61: astore_3
    //   62: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   65: aload_3
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	LinuxDisplay
    //   15	8	1	i	int
    //   30	15	1	local_e	LWJGLException
    //   24	5	2	localObject1	Object
    //   55	5	2	j	int
    //   61	5	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	16	24	finally
    //   24	25	24	finally
    //   3	19	30	org/lwjgl/LWJGLException
    //   24	30	30	org/lwjgl/LWJGLException
    //   3	19	61	finally
    //   24	56	61	finally
    //   61	62	61	finally
  }
  
  private static native int nGetMinCursorSize(long paramLong1, long paramLong2);
  
  /* Error */
  public int getMaxCursorSize()
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   9: invokestatic 349	org/lwjgl/opengl/LinuxDisplay:getWindow	()J
    //   12: invokestatic 930	org/lwjgl/opengl/LinuxDisplay:nGetMaxCursorSize	(JJ)I
    //   15: istore_1
    //   16: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   19: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   22: iload_1
    //   23: ireturn
    //   24: astore_2
    //   25: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   28: aload_2
    //   29: athrow
    //   30: astore_1
    //   31: new 202	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   38: ldc_w 932
    //   41: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: aload_1
    //   45: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: invokestatic 183	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
    //   54: iconst_0
    //   55: istore_2
    //   56: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   59: iload_2
    //   60: ireturn
    //   61: astore_3
    //   62: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   65: aload_3
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	LinuxDisplay
    //   15	8	1	i	int
    //   30	15	1	local_e	LWJGLException
    //   24	5	2	localObject1	Object
    //   55	5	2	j	int
    //   61	5	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	16	24	finally
    //   24	25	24	finally
    //   3	19	30	org/lwjgl/LWJGLException
    //   24	30	30	org/lwjgl/LWJGLException
    //   3	19	61	finally
    //   24	56	61	finally
    //   61	62	61	finally
  }
  
  private static native int nGetMaxCursorSize(long paramLong1, long paramLong2);
  
  public void createKeyboard()
    throws LWJGLException
  {
    
    try
    {
      this.keyboard = new LinuxKeyboard(getDisplay(), getWindow());
    }
    finally
    {
      unlockAWT();
    }
  }
  
  public void destroyKeyboard()
  {
    
    try
    {
      this.keyboard.destroy(getDisplay());
      this.keyboard = null;
    }
    finally
    {
      unlockAWT();
    }
  }
  
  public void pollKeyboard(ByteBuffer keyDownBuffer)
  {
    
    try
    {
      this.keyboard.poll(keyDownBuffer);
    }
    finally
    {
      unlockAWT();
    }
  }
  
  public void readKeyboard(ByteBuffer buffer)
  {
    
    try
    {
      this.keyboard.read(buffer);
    }
    finally
    {
      unlockAWT();
    }
  }
  
  private static native long nCreateCursor(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, IntBuffer paramIntBuffer1, int paramInt6, IntBuffer paramIntBuffer2, int paramInt7)
    throws LWJGLException;
  
  private static long createBlankCursor()
  {
    return nCreateBlankCursor(getDisplay(), getWindow());
  }
  
  static native long nCreateBlankCursor(long paramLong1, long paramLong2);
  
  /* Error */
  public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
    throws LWJGLException
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   9: iload_1
    //   10: iload_2
    //   11: iload_3
    //   12: iload 4
    //   14: iload 5
    //   16: aload 6
    //   18: aload 6
    //   20: invokevirtual 955	java/nio/IntBuffer:position	()I
    //   23: aload 7
    //   25: aload 7
    //   27: ifnull +11 -> 38
    //   30: aload 7
    //   32: invokevirtual 955	java/nio/IntBuffer:position	()I
    //   35: goto +4 -> 39
    //   38: iconst_m1
    //   39: invokestatic 957	org/lwjgl/opengl/LinuxDisplay:nCreateCursor	(JIIIIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)J
    //   42: lstore 8
    //   44: lload 8
    //   46: invokestatic 961	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   49: astore 10
    //   51: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   54: aload 10
    //   56: areturn
    //   57: astore 8
    //   59: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   62: aload 8
    //   64: athrow
    //   65: astore 11
    //   67: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   70: aload 11
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	LinuxDisplay
    //   0	73	1	width	int
    //   0	73	2	height	int
    //   0	73	3	xHotspot	int
    //   0	73	4	yHotspot	int
    //   0	73	5	numImages	int
    //   0	73	6	images	IntBuffer
    //   0	73	7	delays	IntBuffer
    //   42	3	8	cursor	long
    //   57	6	8	cursor	LWJGLException
    //   49	6	10	localLong	Long
    //   65	6	11	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   6	51	57	org/lwjgl/LWJGLException
    //   3	51	65	finally
    //   57	67	65	finally
  }
  
  private static long getCursorHandle(Object cursor_handle)
  {
    return cursor_handle != null ? ((Long)cursor_handle).longValue() : 0L;
  }
  
  public void destroyCursor(Object cursorHandle)
  {
    
    try
    {
      nDestroyCursor(getDisplay(), getCursorHandle(cursorHandle));
      decDisplay();
    }
    finally
    {
      unlockAWT();
    }
  }
  
  static native void nDestroyCursor(long paramLong1, long paramLong2);
  
  /* Error */
  public int getPbufferCapabilities()
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   9: invokestatic 161	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
    //   12: invokestatic 976	org/lwjgl/opengl/LinuxDisplay:nGetPbufferCapabilities	(JI)I
    //   15: istore_1
    //   16: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   19: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   22: iload_1
    //   23: ireturn
    //   24: astore_2
    //   25: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   28: aload_2
    //   29: athrow
    //   30: astore_1
    //   31: new 202	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   38: ldc_w 978
    //   41: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: aload_1
    //   45: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: invokestatic 183	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
    //   54: iconst_0
    //   55: istore_2
    //   56: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   59: iload_2
    //   60: ireturn
    //   61: astore_3
    //   62: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   65: aload_3
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	LinuxDisplay
    //   15	8	1	i	int
    //   30	15	1	local_e	LWJGLException
    //   24	5	2	localObject1	Object
    //   55	5	2	j	int
    //   61	5	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	16	24	finally
    //   24	25	24	finally
    //   3	19	30	org/lwjgl/LWJGLException
    //   24	30	30	org/lwjgl/LWJGLException
    //   3	19	61	finally
    //   24	56	61	finally
    //   61	62	61	finally
  }
  
  private static native int nGetPbufferCapabilities(long paramLong, int paramInt);
  
  public boolean isBufferLost(PeerInfo handle)
  {
    return false;
  }
  
  public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs)
    throws LWJGLException
  {
    return new LinuxPbufferPeerInfo(width, height, pixel_format);
  }
  
  public void setPbufferAttrib(PeerInfo handle, int attrib, int value)
  {
    throw new UnsupportedOperationException();
  }
  
  public void bindTexImageToPbuffer(PeerInfo handle, int buffer)
  {
    throw new UnsupportedOperationException();
  }
  
  public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer)
  {
    throw new UnsupportedOperationException();
  }
  
  private static ByteBuffer convertIcon(ByteBuffer icon, int width, int height)
  {
    ByteBuffer icon_rgb = BufferUtils.createByteBuffer(icon.capacity());
    int depth = 4;
    for (int local_y = 0; local_y < height; local_y++) {
      for (int local_x = 0; local_x < width; local_x++)
      {
        byte local_r = icon.get(local_x * 4 + local_y * width * 4);
        byte local_g = icon.get(local_x * 4 + local_y * width * 4 + 1);
        byte local_b = icon.get(local_x * 4 + local_y * width * 4 + 2);
        icon_rgb.put(local_x * depth + local_y * width * depth, local_b);
        icon_rgb.put(local_x * depth + local_y * width * depth + 1, local_g);
        icon_rgb.put(local_x * depth + local_y * width * depth + 2, local_r);
      }
    }
    return icon_rgb;
  }
  
  private static ByteBuffer convertIconMask(ByteBuffer icon, int width, int height)
  {
    ByteBuffer icon_mask = BufferUtils.createByteBuffer(icon.capacity() / 4 / 8);
    int depth = 4;
    for (int local_y = 0; local_y < height; local_y++) {
      for (int local_x = 0; local_x < width; local_x++)
      {
        byte local_a = icon.get(local_x * 4 + local_y * width * 4 + 3);
        int mask_index = local_x + local_y * width;
        int mask_byte_index = mask_index / 8;
        int mask_bit_index = mask_index % 8;
        byte bit = (local_a & 0xFF) >= 127 ? 1 : 0;
        byte new_byte = (byte)((icon_mask.get(mask_byte_index) | bit << mask_bit_index) & 0xFF);
        icon_mask.put(mask_byte_index, new_byte);
      }
    }
    return icon_mask;
  }
  
  /* Error */
  public int setIcon(ByteBuffer[] icons)
  {
    // Byte code:
    //   0: invokestatic 146	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
    //   3: invokestatic 149	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
    //   6: aload_1
    //   7: astore_2
    //   8: aload_2
    //   9: arraylength
    //   10: istore_3
    //   11: iconst_0
    //   12: istore 4
    //   14: iload 4
    //   16: iload_3
    //   17: if_icmpge +99 -> 116
    //   20: aload_2
    //   21: iload 4
    //   23: aaload
    //   24: astore 5
    //   26: aload 5
    //   28: invokevirtual 1038	java/nio/ByteBuffer:limit	()I
    //   31: iconst_4
    //   32: idiv
    //   33: istore 6
    //   35: iload 6
    //   37: i2d
    //   38: invokestatic 1044	java/lang/Math:sqrt	(D)D
    //   41: d2i
    //   42: istore 7
    //   44: iload 7
    //   46: ifle +64 -> 110
    //   49: aload 5
    //   51: iload 7
    //   53: iload 7
    //   55: invokestatic 1046	org/lwjgl/opengl/LinuxDisplay:convertIcon	(Ljava/nio/ByteBuffer;II)Ljava/nio/ByteBuffer;
    //   58: astore 8
    //   60: aload 5
    //   62: iload 7
    //   64: iload 7
    //   66: invokestatic 1048	org/lwjgl/opengl/LinuxDisplay:convertIconMask	(Ljava/nio/ByteBuffer;II)Ljava/nio/ByteBuffer;
    //   69: astore 9
    //   71: invokestatic 157	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
    //   74: invokestatic 349	org/lwjgl/opengl/LinuxDisplay:getWindow	()J
    //   77: aload 8
    //   79: aload 8
    //   81: invokevirtual 1004	java/nio/ByteBuffer:capacity	()I
    //   84: aload 9
    //   86: aload 9
    //   88: invokevirtual 1004	java/nio/ByteBuffer:capacity	()I
    //   91: iload 7
    //   93: iload 7
    //   95: invokestatic 1052	org/lwjgl/opengl/LinuxDisplay:nSetWindowIcon	(JJLjava/nio/ByteBuffer;ILjava/nio/ByteBuffer;III)V
    //   98: iconst_1
    //   99: istore 10
    //   101: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   104: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   107: iload 10
    //   109: ireturn
    //   110: iinc 4 1
    //   113: goto -99 -> 14
    //   116: iconst_0
    //   117: istore_2
    //   118: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   121: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   124: iload_2
    //   125: ireturn
    //   126: astore 11
    //   128: invokestatic 168	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
    //   131: aload 11
    //   133: athrow
    //   134: astore_2
    //   135: new 202	java/lang/StringBuilder
    //   138: dup
    //   139: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   142: ldc_w 1054
    //   145: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload_2
    //   149: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   152: invokevirtual 216	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   155: invokestatic 183	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
    //   158: iconst_0
    //   159: istore_3
    //   160: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   163: iload_3
    //   164: ireturn
    //   165: astore 12
    //   167: invokestatic 171	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
    //   170: aload 12
    //   172: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	173	0	this	LinuxDisplay
    //   0	173	1	icons	ByteBuffer[]
    //   7	118	2	arr$	ByteBuffer[]
    //   134	15	2	arr$	LWJGLException
    //   10	154	3	len$	int
    //   12	99	4	local_i$	int
    //   24	37	5	icon	ByteBuffer
    //   33	3	6	size	int
    //   42	52	7	dimension	int
    //   58	22	8	icon_rgb	ByteBuffer
    //   69	18	9	icon_mask	ByteBuffer
    //   99	9	10	i	int
    //   126	6	11	localObject1	Object
    //   165	6	12	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	101	126	finally
    //   110	118	126	finally
    //   126	128	126	finally
    //   3	104	134	org/lwjgl/LWJGLException
    //   110	121	134	org/lwjgl/LWJGLException
    //   126	134	134	org/lwjgl/LWJGLException
    //   3	104	165	finally
    //   110	121	165	finally
    //   126	160	165	finally
    //   165	167	165	finally
  }
  
  private static native void nSetWindowIcon(long paramLong1, long paramLong2, ByteBuffer paramByteBuffer1, int paramInt1, ByteBuffer paramByteBuffer2, int paramInt2, int paramInt3, int paramInt4);
  
  public int getX()
  {
    return this.window_x;
  }
  
  public int getY()
  {
    return this.window_y;
  }
  
  public int getWidth()
  {
    return this.window_width;
  }
  
  public int getHeight()
  {
    return this.window_height;
  }
  
  public boolean isInsideWindow()
  {
    return this.mouseInside;
  }
  
  public void setResizable(boolean resizable)
  {
    if (this.resizable == resizable) {
      return;
    }
    this.resizable = resizable;
    nSetWindowSize(getDisplay(), getWindow(), this.window_width, this.window_height, resizable);
  }
  
  public boolean wasResized()
  {
    if (this.resized)
    {
      this.resized = false;
      return true;
    }
    return false;
  }
  
  private static final class Compiz
  {
    private static boolean applyFix;
    private static Provider provider;
    
    static void init()
    {
      if (Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.nocompiz_lfs")) {
        return;
      }
      AccessController.doPrivileged(new PrivilegedAction()
      {
        public Object run()
        {
          try
          {
            if (!LinuxDisplay.Compiz.isProcessActive("compiz"))
            {
              Object localObject1 = null;
              return null;
            }
            LinuxDisplay.Compiz.access$402(null);
            String providerName = null;
            if (LinuxDisplay.Compiz.isProcessActive("dbus-daemon"))
            {
              providerName = "Dbus";
              LinuxDisplay.Compiz.access$402(new LinuxDisplay.Compiz.Provider()
              {
                private static final String KEY = "/org/freedesktop/compiz/workarounds/allscreens/legacy_fullscreen";
                
                public boolean hasLegacyFullscreenSupport()
                  throws LWJGLException
                {
                  List output = LinuxDisplay.Compiz.run(new String[] { "dbus-send", "--print-reply", "--type=method_call", "--dest=org.freedesktop.compiz", "/org/freedesktop/compiz/workarounds/allscreens/legacy_fullscreen", "org.freedesktop.compiz.get" });
                  if ((output == null) || (output.size() < 2)) {
                    throw new LWJGLException("Invalid Dbus reply.");
                  }
                  String line = (String)output.get(0);
                  if (!line.startsWith("method return")) {
                    throw new LWJGLException("Invalid Dbus reply.");
                  }
                  line = ((String)output.get(1)).trim();
                  if ((!line.startsWith("boolean")) || (line.length() < 12)) {
                    throw new LWJGLException("Invalid Dbus reply.");
                  }
                  return "true".equalsIgnoreCase(line.substring("boolean".length() + 1));
                }
                
                public void setLegacyFullscreenSupport(boolean state)
                  throws LWJGLException
                {
                  if (LinuxDisplay.Compiz.run(new String[] { "dbus-send", "--type=method_call", "--dest=org.freedesktop.compiz", "/org/freedesktop/compiz/workarounds/allscreens/legacy_fullscreen", "org.freedesktop.compiz.set", "boolean:" + Boolean.toString(state) }) == null) {
                    throw new LWJGLException("Failed to apply Compiz LFS workaround.");
                  }
                }
              });
            }
            else
            {
              try
              {
                Runtime.getRuntime().exec("gconftool");
                providerName = "gconftool";
                LinuxDisplay.Compiz.access$402(new LinuxDisplay.Compiz.Provider()
                {
                  private static final String KEY = "/apps/compiz/plugins/workarounds/allscreens/options/legacy_fullscreen";
                  
                  public boolean hasLegacyFullscreenSupport()
                    throws LWJGLException
                  {
                    List output = LinuxDisplay.Compiz.run(new String[] { "gconftool", "-g", "/apps/compiz/plugins/workarounds/allscreens/options/legacy_fullscreen" });
                    if ((output == null) || (output.size() == 0)) {
                      throw new LWJGLException("Invalid gconftool reply.");
                    }
                    return Boolean.parseBoolean(((String)output.get(0)).trim());
                  }
                  
                  public void setLegacyFullscreenSupport(boolean state)
                    throws LWJGLException
                  {
                    if (LinuxDisplay.Compiz.run(new String[] { "gconftool", "-s", "/apps/compiz/plugins/workarounds/allscreens/options/legacy_fullscreen", "-s", Boolean.toString(state), "-t", "bool" }) == null) {
                      throw new LWJGLException("Failed to apply Compiz LFS workaround.");
                    }
                    if (state) {
                      try
                      {
                        Thread.sleep(200L);
                      }
                      catch (InterruptedException local_e)
                      {
                        local_e.printStackTrace();
                      }
                    }
                  }
                });
              }
              catch (IOException local_e) {}
            }
            if ((LinuxDisplay.Compiz.provider != null) && (!LinuxDisplay.Compiz.provider.hasLegacyFullscreenSupport()))
            {
              LinuxDisplay.Compiz.access$602(true);
              LWJGLUtil.log("Using " + providerName + " to apply Compiz LFS workaround.");
            }
            return null;
          }
          catch (LWJGLException providerName)
          {
            providerName = providerName;
            return null;
          }
          finally {}
          return null;
        }
      });
    }
    
    static void setLegacyFullscreenSupport(boolean enabled)
    {
      if (!applyFix) {
        return;
      }
      AccessController.doPrivileged(new PrivilegedAction()
      {
        public Object run()
        {
          try
          {
            LinuxDisplay.Compiz.provider.setLegacyFullscreenSupport(this.val$enabled);
          }
          catch (LWJGLException local_e)
          {
            LWJGLUtil.log("Failed to change Compiz Legacy Fullscreen Support. Reason: " + local_e.getMessage());
          }
          return null;
        }
      });
    }
    
    private static List<String> run(String... command)
      throws LWJGLException
    {
      List<String> output = new ArrayList();
      try
      {
        Process local_p = Runtime.getRuntime().exec(command);
        try
        {
          int exitValue = local_p.waitFor();
          if (exitValue != 0) {
            return null;
          }
        }
        catch (InterruptedException exitValue)
        {
          throw new LWJGLException("Process interrupted.", exitValue);
        }
        BufferedReader exitValue = new BufferedReader(new InputStreamReader(local_p.getInputStream()));
        String line;
        while ((line = exitValue.readLine()) != null) {
          output.add(line);
        }
        exitValue.close();
      }
      catch (IOException local_p)
      {
        throw new LWJGLException("Process failed.", local_p);
      }
      return output;
    }
    
    private static boolean isProcessActive(String processName)
      throws LWJGLException
    {
      List<String> output = run(new String[] { "ps", "-C", processName });
      if (output == null) {
        return false;
      }
      Iterator local_i$ = output.iterator();
      while (local_i$.hasNext())
      {
        String line = (String)local_i$.next();
        if (line.contains(processName)) {
          return true;
        }
      }
      return false;
    }
    
    private static abstract interface Provider
    {
      public abstract boolean hasLegacyFullscreenSupport()
        throws LWJGLException;
      
      public abstract void setLegacyFullscreenSupport(boolean paramBoolean)
        throws LWJGLException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.LinuxDisplay
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */