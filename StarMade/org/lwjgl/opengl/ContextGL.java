package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.PointerBuffer;
import org.lwjgl.Sys;

final class ContextGL
  implements Context
{
  private static final ContextImplementation implementation = createImplementation();
  private static final ThreadLocal<ContextGL> current_context_local = new ThreadLocal();
  private final ByteBuffer handle;
  private final PeerInfo peer_info;
  private final ContextAttribs contextAttribs;
  private final boolean forwardCompatible;
  private boolean destroyed;
  private boolean destroy_requested;
  private Thread thread;
  
  private static ContextImplementation createImplementation()
  {
    switch ()
    {
    case 1: 
      return new LinuxContextImplementation();
    case 3: 
      return new WindowsContextImplementation();
    case 2: 
      return new MacOSXContextImplementation();
    }
    throw new IllegalStateException("Unsupported platform");
  }
  
  PeerInfo getPeerInfo()
  {
    return this.peer_info;
  }
  
  ContextAttribs getContextAttribs()
  {
    return this.contextAttribs;
  }
  
  static ContextGL getCurrentContext()
  {
    return (ContextGL)current_context_local.get();
  }
  
  ContextGL(PeerInfo peer_info, ContextAttribs attribs, ContextGL shared_context)
    throws LWJGLException
  {
    ContextGL context_lock = shared_context != null ? shared_context : this;
    synchronized (context_lock)
    {
      if ((shared_context != null) && (shared_context.destroyed)) {
        throw new IllegalArgumentException("Shared context is destroyed");
      }
      GLContext.loadOpenGLLibrary();
      try
      {
        this.peer_info = peer_info;
        this.contextAttribs = attribs;
        IntBuffer attribList;
        if (attribs != null)
        {
          IntBuffer attribList = attribs.getAttribList();
          this.forwardCompatible = attribs.isForwardCompatible();
        }
        else
        {
          attribList = null;
          this.forwardCompatible = false;
        }
        this.handle = implementation.create(peer_info, attribList, shared_context != null ? shared_context.handle : null);
      }
      catch (LWJGLException attribList)
      {
        GLContext.unloadOpenGLLibrary();
        throw attribList;
      }
    }
  }
  
  public void releaseCurrent()
    throws LWJGLException
  {
    ContextGL current_context = getCurrentContext();
    if (current_context != null)
    {
      implementation.releaseCurrentContext();
      GLContext.useContext(null);
      current_context_local.set(null);
      synchronized (current_context)
      {
        current_context.thread = null;
        current_context.checkDestroy();
      }
    }
  }
  
  public synchronized void releaseDrawable()
    throws LWJGLException
  {
    if (this.destroyed) {
      throw new IllegalStateException("Context is destroyed");
    }
    implementation.releaseDrawable(getHandle());
  }
  
  public synchronized void update()
  {
    if (this.destroyed) {
      throw new IllegalStateException("Context is destroyed");
    }
    implementation.update(getHandle());
  }
  
  public static void swapBuffers()
    throws LWJGLException
  {
    implementation.swapBuffers();
  }
  
  private boolean canAccess()
  {
    return (this.thread == null) || (Thread.currentThread() == this.thread);
  }
  
  private void checkAccess()
  {
    if (!canAccess()) {
      throw new IllegalStateException("From thread " + Thread.currentThread() + ": " + this.thread + " already has the context current");
    }
  }
  
  public synchronized void makeCurrent()
    throws LWJGLException
  {
    checkAccess();
    if (this.destroyed) {
      throw new IllegalStateException("Context is destroyed");
    }
    this.thread = Thread.currentThread();
    current_context_local.set(this);
    implementation.makeCurrent(this.peer_info, this.handle);
    GLContext.useContext(this, this.forwardCompatible);
  }
  
  ByteBuffer getHandle()
  {
    return this.handle;
  }
  
  public synchronized boolean isCurrent()
    throws LWJGLException
  {
    if (this.destroyed) {
      throw new IllegalStateException("Context is destroyed");
    }
    return implementation.isCurrent(this.handle);
  }
  
  private void checkDestroy()
  {
    if ((!this.destroyed) && (this.destroy_requested)) {
      try
      {
        releaseDrawable();
        implementation.destroy(this.peer_info, this.handle);
        CallbackUtil.unregisterCallbacks(this);
        this.destroyed = true;
        this.thread = null;
        GLContext.unloadOpenGLLibrary();
      }
      catch (LWJGLException local_e)
      {
        LWJGLUtil.log("Exception occurred while destroying context: " + local_e);
      }
    }
  }
  
  public static void setSwapInterval(int value)
  {
    implementation.setSwapInterval(value);
  }
  
  public synchronized void forceDestroy()
    throws LWJGLException
  {
    checkAccess();
    destroy();
  }
  
  public synchronized void destroy()
    throws LWJGLException
  {
    if (this.destroyed) {
      return;
    }
    this.destroy_requested = true;
    boolean was_current = isCurrent();
    int error = 0;
    if (was_current)
    {
      if ((GLContext.getCapabilities() != null) && (GLContext.getCapabilities().OpenGL11)) {
        error = GL11.glGetError();
      }
      releaseCurrent();
    }
    checkDestroy();
    if ((was_current) && (error != 0)) {
      throw new OpenGLException(error);
    }
  }
  
  public synchronized void setCLSharingProperties(PointerBuffer properties)
    throws LWJGLException
  {
    ByteBuffer peer_handle = this.peer_info.lockAndGetHandle();
    try
    {
      switch (LWJGLUtil.getPlatform())
      {
      case 3: 
        WindowsContextImplementation implWindows = (WindowsContextImplementation)implementation;
        properties.put(8200L).put(implWindows.getHGLRC(this.handle));
        properties.put(8203L).put(implWindows.getHDC(peer_handle));
        break;
      case 1: 
        LinuxContextImplementation implLinux = (LinuxContextImplementation)implementation;
        properties.put(8200L).put(implLinux.getGLXContext(this.handle));
        properties.put(8202L).put(implLinux.getDisplay(peer_handle));
        break;
      case 2: 
        if (LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 6))
        {
          MacOSXContextImplementation implMacOSX = (MacOSXContextImplementation)implementation;
          long CGLShareGroup = implMacOSX.getCGLShareGroup(this.handle);
          properties.put(268435456L).put(CGLShareGroup);
        }
        break;
      }
      throw new UnsupportedOperationException("CL/GL context sharing is not supported on this platform.");
    }
    finally
    {
      this.peer_info.unlock();
    }
  }
  
  static
  {
    Sys.initialize();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ContextGL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */