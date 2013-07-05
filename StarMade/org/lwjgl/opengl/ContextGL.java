/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ import org.lwjgl.Sys;
/*     */ 
/*     */ final class ContextGL
/*     */   implements Context
/*     */ {
/*  82 */   private static final ContextImplementation implementation = createImplementation();
/*     */ 
/*  63 */   private static final ThreadLocal<ContextGL> current_context_local = new ThreadLocal();
/*     */   private final ByteBuffer handle;
/*     */   private final PeerInfo peer_info;
/*     */   private final ContextAttribs contextAttribs;
/*     */   private final boolean forwardCompatible;
/*     */   private boolean destroyed;
/*     */   private boolean destroy_requested;
/*     */   private Thread thread;
/*     */ 
/*     */   private static ContextImplementation createImplementation()
/*     */   {
/*  86 */     switch (LWJGLUtil.getPlatform()) {
/*     */     case 1:
/*  88 */       return new LinuxContextImplementation();
/*     */     case 3:
/*  90 */       return new WindowsContextImplementation();
/*     */     case 2:
/*  92 */       return new MacOSXContextImplementation();
/*     */     }
/*  94 */     throw new IllegalStateException("Unsupported platform");
/*     */   }
/*     */ 
/*     */   PeerInfo getPeerInfo()
/*     */   {
/*  99 */     return this.peer_info;
/*     */   }
/*     */ 
/*     */   ContextAttribs getContextAttribs() {
/* 103 */     return this.contextAttribs;
/*     */   }
/*     */ 
/*     */   static ContextGL getCurrentContext() {
/* 107 */     return (ContextGL)current_context_local.get();
/*     */   }
/*     */ 
/*     */   ContextGL(PeerInfo peer_info, ContextAttribs attribs, ContextGL shared_context) throws LWJGLException
/*     */   {
/* 112 */     ContextGL context_lock = shared_context != null ? shared_context : this;
/*     */ 
/* 115 */     synchronized (context_lock) {
/* 116 */       if ((shared_context != null) && (shared_context.destroyed))
/* 117 */         throw new IllegalArgumentException("Shared context is destroyed");
/* 118 */       GLContext.loadOpenGLLibrary();
/*     */       try {
/* 120 */         this.peer_info = peer_info;
/* 121 */         this.contextAttribs = attribs;
/*     */         IntBuffer attribList;
/* 124 */         if (attribs != null) {
/* 125 */           IntBuffer attribList = attribs.getAttribList();
/* 126 */           this.forwardCompatible = attribs.isForwardCompatible();
/*     */         } else {
/* 128 */           attribList = null;
/* 129 */           this.forwardCompatible = false;
/*     */         }
/*     */ 
/* 132 */         this.handle = implementation.create(peer_info, attribList, shared_context != null ? shared_context.handle : null);
/*     */       } catch (LWJGLException e) {
/* 134 */         GLContext.unloadOpenGLLibrary();
/* 135 */         throw e;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void releaseCurrent() throws LWJGLException
/*     */   {
/* 142 */     ContextGL current_context = getCurrentContext();
/* 143 */     if (current_context != null) {
/* 144 */       implementation.releaseCurrentContext();
/* 145 */       GLContext.useContext(null);
/* 146 */       current_context_local.set(null);
/* 147 */       synchronized (current_context) {
/* 148 */         current_context.thread = null;
/* 149 */         current_context.checkDestroy();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void releaseDrawable()
/*     */     throws LWJGLException
/*     */   {
/* 161 */     if (this.destroyed)
/* 162 */       throw new IllegalStateException("Context is destroyed");
/* 163 */     implementation.releaseDrawable(getHandle());
/*     */   }
/*     */ 
/*     */   public synchronized void update()
/*     */   {
/* 168 */     if (this.destroyed)
/* 169 */       throw new IllegalStateException("Context is destroyed");
/* 170 */     implementation.update(getHandle());
/*     */   }
/*     */ 
/*     */   public static void swapBuffers() throws LWJGLException
/*     */   {
/* 175 */     implementation.swapBuffers();
/*     */   }
/*     */ 
/*     */   private boolean canAccess() {
/* 179 */     return (this.thread == null) || (Thread.currentThread() == this.thread);
/*     */   }
/*     */ 
/*     */   private void checkAccess() {
/* 183 */     if (!canAccess())
/* 184 */       throw new IllegalStateException("From thread " + Thread.currentThread() + ": " + this.thread + " already has the context current");
/*     */   }
/*     */ 
/*     */   public synchronized void makeCurrent() throws LWJGLException
/*     */   {
/* 189 */     checkAccess();
/* 190 */     if (this.destroyed)
/* 191 */       throw new IllegalStateException("Context is destroyed");
/* 192 */     this.thread = Thread.currentThread();
/* 193 */     current_context_local.set(this);
/* 194 */     implementation.makeCurrent(this.peer_info, this.handle);
/* 195 */     GLContext.useContext(this, this.forwardCompatible);
/*     */   }
/*     */ 
/*     */   ByteBuffer getHandle() {
/* 199 */     return this.handle;
/*     */   }
/*     */ 
/*     */   public synchronized boolean isCurrent() throws LWJGLException
/*     */   {
/* 204 */     if (this.destroyed)
/* 205 */       throw new IllegalStateException("Context is destroyed");
/* 206 */     return implementation.isCurrent(this.handle);
/*     */   }
/*     */ 
/*     */   private void checkDestroy() {
/* 210 */     if ((!this.destroyed) && (this.destroy_requested))
/*     */       try {
/* 212 */         releaseDrawable();
/* 213 */         implementation.destroy(this.peer_info, this.handle);
/* 214 */         CallbackUtil.unregisterCallbacks(this);
/* 215 */         this.destroyed = true;
/* 216 */         this.thread = null;
/* 217 */         GLContext.unloadOpenGLLibrary();
/*     */       } catch (LWJGLException e) {
/* 219 */         LWJGLUtil.log("Exception occurred while destroying context: " + e);
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void setSwapInterval(int value)
/*     */   {
/* 232 */     implementation.setSwapInterval(value);
/*     */   }
/*     */ 
/*     */   public synchronized void forceDestroy()
/*     */     throws LWJGLException
/*     */   {
/* 241 */     checkAccess();
/* 242 */     destroy();
/*     */   }
/*     */ 
/*     */   public synchronized void destroy()
/*     */     throws LWJGLException
/*     */   {
/* 250 */     if (this.destroyed)
/* 251 */       return;
/* 252 */     this.destroy_requested = true;
/* 253 */     boolean was_current = isCurrent();
/* 254 */     int error = 0;
/* 255 */     if (was_current) {
/* 256 */       if ((GLContext.getCapabilities() != null) && (GLContext.getCapabilities().OpenGL11))
/* 257 */         error = GL11.glGetError();
/* 258 */       releaseCurrent();
/*     */     }
/* 260 */     checkDestroy();
/* 261 */     if ((was_current) && (error != 0))
/* 262 */       throw new OpenGLException(error);
/*     */   }
/*     */ 
/*     */   public synchronized void setCLSharingProperties(PointerBuffer properties) throws LWJGLException {
/* 266 */     ByteBuffer peer_handle = this.peer_info.lockAndGetHandle();
/*     */     try {
/* 268 */       switch (LWJGLUtil.getPlatform()) {
/*     */       case 3:
/* 270 */         WindowsContextImplementation implWindows = (WindowsContextImplementation)implementation;
/* 271 */         properties.put(8200L).put(implWindows.getHGLRC(this.handle));
/* 272 */         properties.put(8203L).put(implWindows.getHDC(peer_handle));
/* 273 */         break;
/*     */       case 1:
/* 275 */         LinuxContextImplementation implLinux = (LinuxContextImplementation)implementation;
/* 276 */         properties.put(8200L).put(implLinux.getGLXContext(this.handle));
/* 277 */         properties.put(8202L).put(implLinux.getDisplay(peer_handle));
/* 278 */         break;
/*     */       case 2:
/* 280 */         if (LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 6))
/*     */         {
/* 282 */           MacOSXContextImplementation implMacOSX = (MacOSXContextImplementation)implementation;
/* 283 */           long CGLShareGroup = implMacOSX.getCGLShareGroup(this.handle);
/* 284 */           properties.put(268435456L).put(CGLShareGroup);
/* 285 */         }break;
/*     */       }
/*     */ 
/* 288 */       throw new UnsupportedOperationException("CL/GL context sharing is not supported on this platform.");
/*     */     }
/*     */     finally {
/* 291 */       this.peer_info.unlock();
/*     */     }
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  81 */     Sys.initialize();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ContextGL
 * JD-Core Version:    0.6.2
 */