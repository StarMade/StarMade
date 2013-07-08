/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import org.lwjgl.BufferUtils;
/*   4:    */import org.lwjgl.LWJGLException;
/*   5:    */import org.lwjgl.LWJGLUtil;
/*   6:    */import org.lwjgl.PointerBuffer;
/*   7:    */import org.lwjgl.opengles.ContextAttribs;
/*   8:    */import org.lwjgl.opengles.EGL;
/*   9:    */import org.lwjgl.opengles.EGLConfig;
/*  10:    */import org.lwjgl.opengles.EGLContext;
/*  11:    */import org.lwjgl.opengles.EGLDisplay;
/*  12:    */import org.lwjgl.opengles.EGLSurface;
/*  13:    */import org.lwjgl.opengles.GLES20;
/*  14:    */import org.lwjgl.opengles.PixelFormat;
/*  15:    */import org.lwjgl.opengles.PowerManagementEventException;
/*  16:    */import org.lwjgl.opengles.Util;
/*  17:    */
/*  55:    */abstract class DrawableGLES
/*  56:    */  implements DrawableLWJGL
/*  57:    */{
/*  58:    */  protected PixelFormat pixel_format;
/*  59:    */  protected EGLDisplay eglDisplay;
/*  60:    */  protected EGLConfig eglConfig;
/*  61:    */  protected EGLSurface eglSurface;
/*  62:    */  protected ContextGLES context;
/*  63:    */  protected Drawable shared_drawable;
/*  64:    */  
/*  65:    */  public void setPixelFormat(PixelFormatLWJGL pf)
/*  66:    */    throws LWJGLException
/*  67:    */  {
/*  68: 68 */    synchronized (GlobalLock.lock) {
/*  69: 69 */      this.pixel_format = ((PixelFormat)pf);
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  73:    */  public PixelFormatLWJGL getPixelFormat() {
/*  74: 74 */    synchronized (GlobalLock.lock) {
/*  75: 75 */      return this.pixel_format;
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void initialize(long window, long display_id, int eglSurfaceType, PixelFormat pf) throws LWJGLException {
/*  80: 80 */    synchronized (GlobalLock.lock) {
/*  81: 81 */      if (this.eglSurface != null) {
/*  82: 82 */        this.eglSurface.destroy();
/*  83: 83 */        this.eglSurface = null;
/*  84:    */      }
/*  85:    */      
/*  86: 86 */      if (this.eglDisplay != null) {
/*  87: 87 */        this.eglDisplay.terminate();
/*  88: 88 */        this.eglDisplay = null;
/*  89:    */      }
/*  90:    */      
/*  91: 91 */      EGLDisplay eglDisplay = EGL.eglGetDisplay((int)display_id);
/*  92:    */      
/*  93: 93 */      int[] attribs = { 12329, 0, 12352, 4, 12333, 0 };
/*  94:    */      
/*  99: 99 */      EGLConfig[] configs = eglDisplay.chooseConfig(pf.getAttribBuffer(eglDisplay, eglSurfaceType, attribs), null, BufferUtils.createIntBuffer(1));
/* 100:100 */      if (configs.length == 0) {
/* 101:101 */        throw new LWJGLException("No EGLConfigs found for the specified PixelFormat.");
/* 102:    */      }
/* 103:103 */      EGLConfig eglConfig = pf.getBestMatch(configs);
/* 104:104 */      EGLSurface eglSurface = eglDisplay.createWindowSurface(eglConfig, window, null);
/* 105:105 */      pf.setSurfaceAttribs(eglSurface);
/* 106:    */      
/* 107:107 */      this.eglDisplay = eglDisplay;
/* 108:108 */      this.eglConfig = eglConfig;
/* 109:109 */      this.eglSurface = eglSurface;
/* 110:    */      
/* 112:112 */      if (this.context != null)
/* 113:113 */        this.context.getEGLContext().setDisplay(eglDisplay);
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 117:    */  public void createContext(ContextAttribs attribs, Drawable shared_drawable) throws LWJGLException {
/* 118:118 */    synchronized (GlobalLock.lock) {
/* 119:119 */      this.context = new ContextGLES(this, attribs, shared_drawable != null ? ((DrawableGLES)shared_drawable).getContext() : null);
/* 120:120 */      this.shared_drawable = shared_drawable;
/* 121:    */    }
/* 122:    */  }
/* 123:    */  
/* 124:    */  Drawable getSharedDrawable() {
/* 125:125 */    synchronized (GlobalLock.lock) {
/* 126:126 */      return this.shared_drawable;
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 130:    */  public EGLDisplay getEGLDisplay() {
/* 131:131 */    synchronized (GlobalLock.lock) {
/* 132:132 */      return this.eglDisplay;
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 136:    */  public EGLConfig getEGLConfig() {
/* 137:137 */    synchronized (GlobalLock.lock) {
/* 138:138 */      return this.eglConfig;
/* 139:    */    }
/* 140:    */  }
/* 141:    */  
/* 142:    */  public EGLSurface getEGLSurface() {
/* 143:143 */    synchronized (GlobalLock.lock) {
/* 144:144 */      return this.eglSurface;
/* 145:    */    }
/* 146:    */  }
/* 147:    */  
/* 148:    */  public ContextGLES getContext() {
/* 149:149 */    synchronized (GlobalLock.lock) {
/* 150:150 */      return this.context;
/* 151:    */    }
/* 152:    */  }
/* 153:    */  
/* 154:    */  public Context createSharedContext() throws LWJGLException {
/* 155:155 */    synchronized (GlobalLock.lock) {
/* 156:156 */      checkDestroyed();
/* 157:157 */      return new ContextGLES(this, this.context.getContextAttribs(), this.context);
/* 158:    */    }
/* 159:    */  }
/* 160:    */  
/* 162:    */  public void checkGLError() {}
/* 163:    */  
/* 164:    */  public void setSwapInterval(int swap_interval)
/* 165:    */  {
/* 166:166 */    ContextGLES.setSwapInterval(swap_interval);
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void swapBuffers()
/* 170:    */    throws LWJGLException
/* 171:    */  {}
/* 172:    */  
/* 173:    */  public void initContext(float r, float g, float b)
/* 174:    */  {
/* 175:175 */    GLES20.glClearColor(r, g, b, 0.0F);
/* 176:    */    
/* 177:177 */    GLES20.glClear(16384);
/* 178:    */  }
/* 179:    */  
/* 180:    */  public boolean isCurrent() throws LWJGLException {
/* 181:181 */    synchronized (GlobalLock.lock) {
/* 182:182 */      checkDestroyed();
/* 183:183 */      return this.context.isCurrent();
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 187:    */  public void makeCurrent() throws LWJGLException, PowerManagementEventException {
/* 188:188 */    synchronized (GlobalLock.lock) {
/* 189:189 */      checkDestroyed();
/* 190:190 */      this.context.makeCurrent();
/* 191:    */    }
/* 192:    */  }
/* 193:    */  
/* 194:    */  public void releaseContext() throws LWJGLException, PowerManagementEventException {
/* 195:195 */    synchronized (GlobalLock.lock) {
/* 196:196 */      checkDestroyed();
/* 197:197 */      if (this.context.isCurrent())
/* 198:198 */        this.context.releaseCurrent();
/* 199:    */    }
/* 200:    */  }
/* 201:    */  
/* 202:    */  public void destroy() {
/* 203:203 */    synchronized (GlobalLock.lock) {
/* 204:    */      try {
/* 205:205 */        if (this.context != null) {
/* 206:    */          try {
/* 207:207 */            releaseContext();
/* 208:    */          }
/* 209:    */          catch (PowerManagementEventException e) {}
/* 210:    */          
/* 212:212 */          this.context.forceDestroy();
/* 213:213 */          this.context = null;
/* 214:    */        }
/* 215:    */        
/* 216:216 */        if (this.eglSurface != null) {
/* 217:217 */          this.eglSurface.destroy();
/* 218:218 */          this.eglSurface = null;
/* 219:    */        }
/* 220:    */        
/* 221:221 */        if (this.eglDisplay != null) {
/* 222:222 */          this.eglDisplay.terminate();
/* 223:223 */          this.eglDisplay = null;
/* 224:    */        }
/* 225:    */        
/* 226:226 */        this.pixel_format = null;
/* 227:227 */        this.shared_drawable = null;
/* 228:    */      } catch (LWJGLException e) {
/* 229:229 */        LWJGLUtil.log("Exception occurred while destroying Drawable: " + e);
/* 230:    */      }
/* 231:    */    }
/* 232:    */  }
/* 233:    */  
/* 234:    */  protected void checkDestroyed() {
/* 235:235 */    if (this.context == null)
/* 236:236 */      throw new IllegalStateException("The Drawable has no context available.");
/* 237:    */  }
/* 238:    */  
/* 239:    */  public void setCLSharingProperties(PointerBuffer properties) throws LWJGLException {
/* 240:240 */    throw new UnsupportedOperationException();
/* 241:    */  }
/* 242:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.DrawableGLES
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */