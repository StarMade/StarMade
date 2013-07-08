/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import org.lwjgl.LWJGLException;
/*   4:    */import org.lwjgl.LWJGLUtil;
/*   5:    */import org.lwjgl.Sys;
/*   6:    */import org.lwjgl.opengles.ContextAttribs;
/*   7:    */import org.lwjgl.opengles.ContextCapabilities;
/*   8:    */import org.lwjgl.opengles.EGL;
/*   9:    */import org.lwjgl.opengles.EGLContext;
/*  10:    */import org.lwjgl.opengles.EGLDisplay;
/*  11:    */import org.lwjgl.opengles.EGLSurface;
/*  12:    */import org.lwjgl.opengles.GLContext;
/*  13:    */import org.lwjgl.opengles.GLES20;
/*  14:    */import org.lwjgl.opengles.PowerManagementEventException;
/*  15:    */
/*  55:    */final class ContextGLES
/*  56:    */  implements Context
/*  57:    */{
/*  58: 58 */  private static final ThreadLocal<ContextGLES> current_context_local = new ThreadLocal();
/*  59:    */  
/*  60:    */  private final DrawableGLES drawable;
/*  61:    */  
/*  62:    */  private final EGLContext eglContext;
/*  63:    */  
/*  64:    */  private final ContextAttribs contextAttribs;
/*  65:    */  
/*  66:    */  private boolean destroyed;
/*  67:    */  
/*  68:    */  private boolean destroy_requested;
/*  69:    */  
/*  70:    */  private Thread thread;
/*  71:    */  
/*  73:    */  static
/*  74:    */  {
/*  75: 75 */    Sys.initialize();
/*  76:    */  }
/*  77:    */  
/*  78:    */  public EGLContext getEGLContext() {
/*  79: 79 */    return this.eglContext;
/*  80:    */  }
/*  81:    */  
/*  82:    */  ContextAttribs getContextAttribs() {
/*  83: 83 */    return this.contextAttribs;
/*  84:    */  }
/*  85:    */  
/*  86:    */  static ContextGLES getCurrentContext() {
/*  87: 87 */    return (ContextGLES)current_context_local.get();
/*  88:    */  }
/*  89:    */  
/*  90:    */  ContextGLES(DrawableGLES drawable, ContextAttribs attribs, ContextGLES shared_context) throws LWJGLException
/*  91:    */  {
/*  92: 92 */    if (drawable == null) {
/*  93: 93 */      throw new IllegalArgumentException();
/*  94:    */    }
/*  95: 95 */    ContextGLES context_lock = shared_context != null ? shared_context : this;
/*  96:    */    
/*  98: 98 */    synchronized (context_lock) {
/*  99: 99 */      if ((shared_context != null) && (shared_context.destroyed)) {
/* 100:100 */        throw new IllegalArgumentException("Shared context is destroyed");
/* 101:    */      }
/* 102:102 */      this.drawable = drawable;
/* 103:103 */      this.contextAttribs = attribs;
/* 104:104 */      this.eglContext = drawable.getEGLDisplay().createContext(drawable.getEGLConfig(), shared_context == null ? null : shared_context.eglContext, attribs == null ? new ContextAttribs(2).getAttribList() : attribs.getAttribList());
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 109:    */  public void releaseCurrent()
/* 110:    */    throws LWJGLException, PowerManagementEventException
/* 111:    */  {
/* 112:112 */    EGL.eglReleaseCurrent(this.drawable.getEGLDisplay());
/* 113:113 */    GLContext.useContext(null);
/* 114:114 */    current_context_local.set(null);
/* 115:    */    
/* 116:116 */    synchronized (this) {
/* 117:117 */      this.thread = null;
/* 118:118 */      checkDestroy();
/* 119:    */    }
/* 120:    */  }
/* 121:    */  
/* 122:    */  public static void swapBuffers() throws LWJGLException, PowerManagementEventException
/* 123:    */  {
/* 124:124 */    ContextGLES current_context = getCurrentContext();
/* 125:125 */    if (current_context != null)
/* 126:126 */      current_context.drawable.getEGLSurface().swapBuffers();
/* 127:    */  }
/* 128:    */  
/* 129:    */  private boolean canAccess() {
/* 130:130 */    return (this.thread == null) || (Thread.currentThread() == this.thread);
/* 131:    */  }
/* 132:    */  
/* 133:    */  private void checkAccess() {
/* 134:134 */    if (!canAccess()) {
/* 135:135 */      throw new IllegalStateException("From thread " + Thread.currentThread() + ": " + this.thread + " already has the context current");
/* 136:    */    }
/* 137:    */  }
/* 138:    */  
/* 139:    */  public synchronized void makeCurrent() throws LWJGLException, PowerManagementEventException {
/* 140:140 */    checkAccess();
/* 141:141 */    if (this.destroyed)
/* 142:142 */      throw new IllegalStateException("Context is destroyed");
/* 143:143 */    this.thread = Thread.currentThread();
/* 144:144 */    current_context_local.set(this);
/* 145:145 */    this.eglContext.makeCurrent(this.drawable.getEGLSurface());
/* 146:146 */    GLContext.useContext(this);
/* 147:    */  }
/* 148:    */  
/* 149:    */  public synchronized boolean isCurrent() throws LWJGLException
/* 150:    */  {
/* 151:151 */    if (this.destroyed)
/* 152:152 */      throw new IllegalStateException("Context is destroyed");
/* 153:153 */    return EGL.eglIsCurrentContext(this.eglContext);
/* 154:    */  }
/* 155:    */  
/* 156:    */  private void checkDestroy() {
/* 157:157 */    if ((!this.destroyed) && (this.destroy_requested)) {
/* 158:    */      try {
/* 159:159 */        this.eglContext.destroy();
/* 160:160 */        this.destroyed = true;
/* 161:161 */        this.thread = null;
/* 162:    */      } catch (LWJGLException e) {
/* 163:163 */        LWJGLUtil.log("Exception occurred while destroying context: " + e);
/* 164:    */      }
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 174:    */  public static void setSwapInterval(int value)
/* 175:    */  {
/* 176:176 */    ContextGLES current_context = getCurrentContext();
/* 177:177 */    if (current_context != null) {
/* 178:    */      try {
/* 179:179 */        current_context.drawable.getEGLDisplay().setSwapInterval(value);
/* 180:    */      } catch (LWJGLException e) {
/* 181:181 */        LWJGLUtil.log("Failed to set swap interval. Reason: " + e.getMessage());
/* 182:    */      }
/* 183:    */    }
/* 184:    */  }
/* 185:    */  
/* 189:    */  public synchronized void forceDestroy()
/* 190:    */    throws LWJGLException
/* 191:    */  {
/* 192:192 */    checkAccess();
/* 193:193 */    destroy();
/* 194:    */  }
/* 195:    */  
/* 198:    */  public synchronized void destroy()
/* 199:    */    throws LWJGLException
/* 200:    */  {
/* 201:201 */    if (this.destroyed)
/* 202:202 */      return;
/* 203:203 */    this.destroy_requested = true;
/* 204:204 */    boolean was_current = isCurrent();
/* 205:205 */    int error = 0;
/* 206:206 */    if (was_current) {
/* 207:207 */      if ((GLContext.getCapabilities() != null) && (GLContext.getCapabilities().OpenGLES20)) {
/* 208:208 */        error = GLES20.glGetError();
/* 209:    */      }
/* 210:    */      try {
/* 211:211 */        releaseCurrent();
/* 212:    */      }
/* 213:    */      catch (PowerManagementEventException e) {}
/* 214:    */    }
/* 215:    */    
/* 216:216 */    checkDestroy();
/* 217:217 */    if ((was_current) && (error != 0)) {
/* 218:218 */      throw new OpenGLException(error);
/* 219:    */    }
/* 220:    */  }
/* 221:    */  
/* 222:    */  public void releaseDrawable()
/* 223:    */    throws LWJGLException
/* 224:    */  {}
/* 225:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ContextGLES
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */