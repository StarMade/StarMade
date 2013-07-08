/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import org.lwjgl.LWJGLException;
/*   4:    */import org.lwjgl.LWJGLUtil;
/*   5:    */import org.lwjgl.PointerBuffer;
/*   6:    */
/*  46:    */abstract class DrawableGL
/*  47:    */  implements DrawableLWJGL
/*  48:    */{
/*  49:    */  protected PixelFormat pixel_format;
/*  50:    */  protected PeerInfo peer_info;
/*  51:    */  protected ContextGL context;
/*  52:    */  
/*  53:    */  public void setPixelFormat(PixelFormatLWJGL pf)
/*  54:    */    throws LWJGLException
/*  55:    */  {
/*  56: 56 */    throw new UnsupportedOperationException();
/*  57:    */  }
/*  58:    */  
/*  59:    */  public void setPixelFormat(PixelFormatLWJGL pf, ContextAttribs attribs) throws LWJGLException {
/*  60: 60 */    this.pixel_format = ((PixelFormat)pf);
/*  61: 61 */    this.peer_info = Display.getImplementation().createPeerInfo(this.pixel_format, attribs);
/*  62:    */  }
/*  63:    */  
/*  64:    */  public PixelFormatLWJGL getPixelFormat() {
/*  65: 65 */    return this.pixel_format;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public ContextGL getContext() {
/*  69: 69 */    synchronized (GlobalLock.lock) {
/*  70: 70 */      return this.context;
/*  71:    */    }
/*  72:    */  }
/*  73:    */  
/*  74:    */  public ContextGL createSharedContext() throws LWJGLException {
/*  75: 75 */    synchronized (GlobalLock.lock) {
/*  76: 76 */      checkDestroyed();
/*  77: 77 */      return new ContextGL(this.peer_info, this.context.getContextAttribs(), this.context);
/*  78:    */    }
/*  79:    */  }
/*  80:    */  
/*  82:    */  public void checkGLError() {}
/*  83:    */  
/*  84:    */  public void setSwapInterval(int swap_interval)
/*  85:    */  {
/*  86: 86 */    ContextGL.setSwapInterval(swap_interval);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void swapBuffers()
/*  90:    */    throws LWJGLException
/*  91:    */  {}
/*  92:    */  
/*  93:    */  public void initContext(float r, float g, float b)
/*  94:    */  {
/*  95: 95 */    GL11.glClearColor(r, g, b, 0.0F);
/*  96:    */    
/*  97: 97 */    GL11.glClear(16384);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public boolean isCurrent() throws LWJGLException {
/* 101:101 */    synchronized (GlobalLock.lock) {
/* 102:102 */      checkDestroyed();
/* 103:103 */      return this.context.isCurrent();
/* 104:    */    }
/* 105:    */  }
/* 106:    */  
/* 107:    */  public void makeCurrent() throws LWJGLException {
/* 108:108 */    synchronized (GlobalLock.lock) {
/* 109:109 */      checkDestroyed();
/* 110:110 */      this.context.makeCurrent();
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 114:    */  public void releaseContext() throws LWJGLException {
/* 115:115 */    synchronized (GlobalLock.lock) {
/* 116:116 */      checkDestroyed();
/* 117:117 */      if (this.context.isCurrent())
/* 118:118 */        this.context.releaseCurrent();
/* 119:    */    }
/* 120:    */  }
/* 121:    */  
/* 122:    */  public void destroy() {
/* 123:123 */    synchronized (GlobalLock.lock) {
/* 124:124 */      if (this.context == null) {
/* 125:125 */        return;
/* 126:    */      }
/* 127:    */      try {
/* 128:128 */        releaseContext();
/* 129:    */        
/* 130:130 */        this.context.forceDestroy();
/* 131:131 */        this.context = null;
/* 132:    */        
/* 133:133 */        if (this.peer_info != null) {
/* 134:134 */          this.peer_info.destroy();
/* 135:135 */          this.peer_info = null;
/* 136:    */        }
/* 137:    */      } catch (LWJGLException e) {
/* 138:138 */        LWJGLUtil.log("Exception occurred while destroying Drawable: " + e);
/* 139:    */      }
/* 140:    */    }
/* 141:    */  }
/* 142:    */  
/* 143:    */  public void setCLSharingProperties(PointerBuffer properties) throws LWJGLException {
/* 144:144 */    synchronized (GlobalLock.lock) {
/* 145:145 */      checkDestroyed();
/* 146:146 */      this.context.setCLSharingProperties(properties);
/* 147:    */    }
/* 148:    */  }
/* 149:    */  
/* 150:    */  protected final void checkDestroyed() {
/* 151:151 */    if (this.context == null) {
/* 152:152 */      throw new IllegalStateException("The Drawable has no context available.");
/* 153:    */    }
/* 154:    */  }
/* 155:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.DrawableGL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */