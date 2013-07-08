/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */import org.lwjgl.LWJGLUtil;
/*   7:    */import org.lwjgl.PointerBuffer;
/*   8:    */import org.lwjgl.Sys;
/*   9:    */
/*  53:    */final class ContextGL
/*  54:    */  implements Context
/*  55:    */{
/*  56:    */  private Thread thread;
/*  57:    */  private boolean destroy_requested;
/*  58:    */  private boolean destroyed;
/*  59:    */  private final boolean forwardCompatible;
/*  60:    */  private final ContextAttribs contextAttribs;
/*  61:    */  private final PeerInfo peer_info;
/*  62:    */  private final ByteBuffer handle;
/*  63: 63 */  private static final ThreadLocal<ContextGL> current_context_local = new ThreadLocal();
/*  64:    */  
/*  81: 81 */  static { Sys.initialize(); }
/*  82: 82 */  private static final ContextImplementation implementation = createImplementation();
/*  83:    */  
/*  84:    */  private static ContextImplementation createImplementation()
/*  85:    */  {
/*  86: 86 */    switch () {
/*  87:    */    case 1: 
/*  88: 88 */      return new LinuxContextImplementation();
/*  89:    */    case 3: 
/*  90: 90 */      return new WindowsContextImplementation();
/*  91:    */    case 2: 
/*  92: 92 */      return new MacOSXContextImplementation();
/*  93:    */    }
/*  94: 94 */    throw new IllegalStateException("Unsupported platform");
/*  95:    */  }
/*  96:    */  
/*  97:    */  PeerInfo getPeerInfo()
/*  98:    */  {
/*  99: 99 */    return this.peer_info;
/* 100:    */  }
/* 101:    */  
/* 102:    */  ContextAttribs getContextAttribs() {
/* 103:103 */    return this.contextAttribs;
/* 104:    */  }
/* 105:    */  
/* 106:    */  static ContextGL getCurrentContext() {
/* 107:107 */    return (ContextGL)current_context_local.get();
/* 108:    */  }
/* 109:    */  
/* 110:    */  ContextGL(PeerInfo peer_info, ContextAttribs attribs, ContextGL shared_context) throws LWJGLException
/* 111:    */  {
/* 112:112 */    ContextGL context_lock = shared_context != null ? shared_context : this;
/* 113:    */    
/* 115:115 */    synchronized (context_lock) {
/* 116:116 */      if ((shared_context != null) && (shared_context.destroyed))
/* 117:117 */        throw new IllegalArgumentException("Shared context is destroyed");
/* 118:118 */      GLContext.loadOpenGLLibrary();
/* 119:    */      try {
/* 120:120 */        this.peer_info = peer_info;
/* 121:121 */        this.contextAttribs = attribs;
/* 122:    */        
/* 123:    */        IntBuffer attribList;
/* 124:124 */        if (attribs != null) {
/* 125:125 */          IntBuffer attribList = attribs.getAttribList();
/* 126:126 */          this.forwardCompatible = attribs.isForwardCompatible();
/* 127:    */        } else {
/* 128:128 */          attribList = null;
/* 129:129 */          this.forwardCompatible = false;
/* 130:    */        }
/* 131:    */        
/* 132:132 */        this.handle = implementation.create(peer_info, attribList, shared_context != null ? shared_context.handle : null);
/* 133:    */      } catch (LWJGLException e) {
/* 134:134 */        GLContext.unloadOpenGLLibrary();
/* 135:135 */        throw e;
/* 136:    */      }
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 140:    */  public void releaseCurrent() throws LWJGLException
/* 141:    */  {
/* 142:142 */    ContextGL current_context = getCurrentContext();
/* 143:143 */    if (current_context != null) {
/* 144:144 */      implementation.releaseCurrentContext();
/* 145:145 */      GLContext.useContext(null);
/* 146:146 */      current_context_local.set(null);
/* 147:147 */      synchronized (current_context) {
/* 148:148 */        current_context.thread = null;
/* 149:149 */        current_context.checkDestroy();
/* 150:    */      }
/* 151:    */    }
/* 152:    */  }
/* 153:    */  
/* 158:    */  public synchronized void releaseDrawable()
/* 159:    */    throws LWJGLException
/* 160:    */  {
/* 161:161 */    if (this.destroyed)
/* 162:162 */      throw new IllegalStateException("Context is destroyed");
/* 163:163 */    implementation.releaseDrawable(getHandle());
/* 164:    */  }
/* 165:    */  
/* 166:    */  public synchronized void update()
/* 167:    */  {
/* 168:168 */    if (this.destroyed)
/* 169:169 */      throw new IllegalStateException("Context is destroyed");
/* 170:170 */    implementation.update(getHandle());
/* 171:    */  }
/* 172:    */  
/* 173:    */  public static void swapBuffers() throws LWJGLException
/* 174:    */  {
/* 175:175 */    implementation.swapBuffers();
/* 176:    */  }
/* 177:    */  
/* 178:    */  private boolean canAccess() {
/* 179:179 */    return (this.thread == null) || (Thread.currentThread() == this.thread);
/* 180:    */  }
/* 181:    */  
/* 182:    */  private void checkAccess() {
/* 183:183 */    if (!canAccess()) {
/* 184:184 */      throw new IllegalStateException("From thread " + Thread.currentThread() + ": " + this.thread + " already has the context current");
/* 185:    */    }
/* 186:    */  }
/* 187:    */  
/* 188:    */  public synchronized void makeCurrent() throws LWJGLException {
/* 189:189 */    checkAccess();
/* 190:190 */    if (this.destroyed)
/* 191:191 */      throw new IllegalStateException("Context is destroyed");
/* 192:192 */    this.thread = Thread.currentThread();
/* 193:193 */    current_context_local.set(this);
/* 194:194 */    implementation.makeCurrent(this.peer_info, this.handle);
/* 195:195 */    GLContext.useContext(this, this.forwardCompatible);
/* 196:    */  }
/* 197:    */  
/* 198:    */  ByteBuffer getHandle() {
/* 199:199 */    return this.handle;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public synchronized boolean isCurrent() throws LWJGLException
/* 203:    */  {
/* 204:204 */    if (this.destroyed)
/* 205:205 */      throw new IllegalStateException("Context is destroyed");
/* 206:206 */    return implementation.isCurrent(this.handle);
/* 207:    */  }
/* 208:    */  
/* 209:    */  private void checkDestroy() {
/* 210:210 */    if ((!this.destroyed) && (this.destroy_requested)) {
/* 211:    */      try {
/* 212:212 */        releaseDrawable();
/* 213:213 */        implementation.destroy(this.peer_info, this.handle);
/* 214:214 */        CallbackUtil.unregisterCallbacks(this);
/* 215:215 */        this.destroyed = true;
/* 216:216 */        this.thread = null;
/* 217:217 */        GLContext.unloadOpenGLLibrary();
/* 218:    */      } catch (LWJGLException e) {
/* 219:219 */        LWJGLUtil.log("Exception occurred while destroying context: " + e);
/* 220:    */      }
/* 221:    */    }
/* 222:    */  }
/* 223:    */  
/* 230:    */  public static void setSwapInterval(int value)
/* 231:    */  {
/* 232:232 */    implementation.setSwapInterval(value);
/* 233:    */  }
/* 234:    */  
/* 238:    */  public synchronized void forceDestroy()
/* 239:    */    throws LWJGLException
/* 240:    */  {
/* 241:241 */    checkAccess();
/* 242:242 */    destroy();
/* 243:    */  }
/* 244:    */  
/* 247:    */  public synchronized void destroy()
/* 248:    */    throws LWJGLException
/* 249:    */  {
/* 250:250 */    if (this.destroyed)
/* 251:251 */      return;
/* 252:252 */    this.destroy_requested = true;
/* 253:253 */    boolean was_current = isCurrent();
/* 254:254 */    int error = 0;
/* 255:255 */    if (was_current) {
/* 256:256 */      if ((GLContext.getCapabilities() != null) && (GLContext.getCapabilities().OpenGL11))
/* 257:257 */        error = GL11.glGetError();
/* 258:258 */      releaseCurrent();
/* 259:    */    }
/* 260:260 */    checkDestroy();
/* 261:261 */    if ((was_current) && (error != 0))
/* 262:262 */      throw new OpenGLException(error);
/* 263:    */  }
/* 264:    */  
/* 265:    */  public synchronized void setCLSharingProperties(PointerBuffer properties) throws LWJGLException {
/* 266:266 */    ByteBuffer peer_handle = this.peer_info.lockAndGetHandle();
/* 267:    */    try {
/* 268:268 */      switch (LWJGLUtil.getPlatform()) {
/* 269:    */      case 3: 
/* 270:270 */        WindowsContextImplementation implWindows = (WindowsContextImplementation)implementation;
/* 271:271 */        properties.put(8200L).put(implWindows.getHGLRC(this.handle));
/* 272:272 */        properties.put(8203L).put(implWindows.getHDC(peer_handle));
/* 273:273 */        break;
/* 274:    */      case 1: 
/* 275:275 */        LinuxContextImplementation implLinux = (LinuxContextImplementation)implementation;
/* 276:276 */        properties.put(8200L).put(implLinux.getGLXContext(this.handle));
/* 277:277 */        properties.put(8202L).put(implLinux.getDisplay(peer_handle));
/* 278:278 */        break;
/* 279:    */      case 2: 
/* 280:280 */        if (LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 6))
/* 281:    */        {
/* 282:282 */          MacOSXContextImplementation implMacOSX = (MacOSXContextImplementation)implementation;
/* 283:283 */          long CGLShareGroup = implMacOSX.getCGLShareGroup(this.handle);
/* 284:284 */          properties.put(268435456L).put(CGLShareGroup); }
/* 285:285 */        break;
/* 286:    */      }
/* 287:    */      
/* 288:288 */      throw new UnsupportedOperationException("CL/GL context sharing is not supported on this platform.");
/* 289:    */    }
/* 290:    */    finally {
/* 291:291 */      this.peer_info.unlock();
/* 292:    */    }
/* 293:    */  }
/* 294:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ContextGL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */