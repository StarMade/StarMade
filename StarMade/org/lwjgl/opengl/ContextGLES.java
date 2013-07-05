/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.Sys;
/*     */ import org.lwjgl.opengles.ContextAttribs;
/*     */ import org.lwjgl.opengles.ContextCapabilities;
/*     */ import org.lwjgl.opengles.EGL;
/*     */ import org.lwjgl.opengles.EGLContext;
/*     */ import org.lwjgl.opengles.EGLDisplay;
/*     */ import org.lwjgl.opengles.EGLSurface;
/*     */ import org.lwjgl.opengles.GLContext;
/*     */ import org.lwjgl.opengles.GLES20;
/*     */ import org.lwjgl.opengles.PowerManagementEventException;
/*     */ 
/*     */ final class ContextGLES
/*     */   implements Context
/*     */ {
/*  58 */   private static final ThreadLocal<ContextGLES> current_context_local = new ThreadLocal();
/*     */   private final DrawableGLES drawable;
/*     */   private final EGLContext eglContext;
/*     */   private final ContextAttribs contextAttribs;
/*     */   private boolean destroyed;
/*     */   private boolean destroy_requested;
/*     */   private Thread thread;
/*     */ 
/*     */   public EGLContext getEGLContext()
/*     */   {
/*  79 */     return this.eglContext;
/*     */   }
/*     */ 
/*     */   ContextAttribs getContextAttribs() {
/*  83 */     return this.contextAttribs;
/*     */   }
/*     */ 
/*     */   static ContextGLES getCurrentContext() {
/*  87 */     return (ContextGLES)current_context_local.get();
/*     */   }
/*     */ 
/*     */   ContextGLES(DrawableGLES drawable, ContextAttribs attribs, ContextGLES shared_context) throws LWJGLException
/*     */   {
/*  92 */     if (drawable == null) {
/*  93 */       throw new IllegalArgumentException();
/*     */     }
/*  95 */     ContextGLES context_lock = shared_context != null ? shared_context : this;
/*     */ 
/*  98 */     synchronized (context_lock) {
/*  99 */       if ((shared_context != null) && (shared_context.destroyed)) {
/* 100 */         throw new IllegalArgumentException("Shared context is destroyed");
/*     */       }
/* 102 */       this.drawable = drawable;
/* 103 */       this.contextAttribs = attribs;
/* 104 */       this.eglContext = drawable.getEGLDisplay().createContext(drawable.getEGLConfig(), shared_context == null ? null : shared_context.eglContext, attribs == null ? new ContextAttribs(2).getAttribList() : attribs.getAttribList());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void releaseCurrent()
/*     */     throws LWJGLException, PowerManagementEventException
/*     */   {
/* 112 */     EGL.eglReleaseCurrent(this.drawable.getEGLDisplay());
/* 113 */     GLContext.useContext(null);
/* 114 */     current_context_local.set(null);
/*     */ 
/* 116 */     synchronized (this) {
/* 117 */       this.thread = null;
/* 118 */       checkDestroy();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void swapBuffers() throws LWJGLException, PowerManagementEventException
/*     */   {
/* 124 */     ContextGLES current_context = getCurrentContext();
/* 125 */     if (current_context != null)
/* 126 */       current_context.drawable.getEGLSurface().swapBuffers();
/*     */   }
/*     */ 
/*     */   private boolean canAccess() {
/* 130 */     return (this.thread == null) || (Thread.currentThread() == this.thread);
/*     */   }
/*     */ 
/*     */   private void checkAccess() {
/* 134 */     if (!canAccess())
/* 135 */       throw new IllegalStateException("From thread " + Thread.currentThread() + ": " + this.thread + " already has the context current");
/*     */   }
/*     */ 
/*     */   public synchronized void makeCurrent() throws LWJGLException, PowerManagementEventException
/*     */   {
/* 140 */     checkAccess();
/* 141 */     if (this.destroyed)
/* 142 */       throw new IllegalStateException("Context is destroyed");
/* 143 */     this.thread = Thread.currentThread();
/* 144 */     current_context_local.set(this);
/* 145 */     this.eglContext.makeCurrent(this.drawable.getEGLSurface());
/* 146 */     GLContext.useContext(this);
/*     */   }
/*     */ 
/*     */   public synchronized boolean isCurrent() throws LWJGLException
/*     */   {
/* 151 */     if (this.destroyed)
/* 152 */       throw new IllegalStateException("Context is destroyed");
/* 153 */     return EGL.eglIsCurrentContext(this.eglContext);
/*     */   }
/*     */ 
/*     */   private void checkDestroy() {
/* 157 */     if ((!this.destroyed) && (this.destroy_requested))
/*     */       try {
/* 159 */         this.eglContext.destroy();
/* 160 */         this.destroyed = true;
/* 161 */         this.thread = null;
/*     */       } catch (LWJGLException e) {
/* 163 */         LWJGLUtil.log("Exception occurred while destroying context: " + e);
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void setSwapInterval(int value)
/*     */   {
/* 176 */     ContextGLES current_context = getCurrentContext();
/* 177 */     if (current_context != null)
/*     */       try {
/* 179 */         current_context.drawable.getEGLDisplay().setSwapInterval(value);
/*     */       } catch (LWJGLException e) {
/* 181 */         LWJGLUtil.log("Failed to set swap interval. Reason: " + e.getMessage());
/*     */       }
/*     */   }
/*     */ 
/*     */   public synchronized void forceDestroy()
/*     */     throws LWJGLException
/*     */   {
/* 192 */     checkAccess();
/* 193 */     destroy();
/*     */   }
/*     */ 
/*     */   public synchronized void destroy()
/*     */     throws LWJGLException
/*     */   {
/* 201 */     if (this.destroyed)
/* 202 */       return;
/* 203 */     this.destroy_requested = true;
/* 204 */     boolean was_current = isCurrent();
/* 205 */     int error = 0;
/* 206 */     if (was_current) {
/* 207 */       if ((GLContext.getCapabilities() != null) && (GLContext.getCapabilities().OpenGLES20))
/* 208 */         error = GLES20.glGetError();
/*     */       try
/*     */       {
/* 211 */         releaseCurrent();
/*     */       }
/*     */       catch (PowerManagementEventException e) {
/*     */       }
/*     */     }
/* 216 */     checkDestroy();
/* 217 */     if ((was_current) && (error != 0))
/* 218 */       throw new OpenGLException(error);
/*     */   }
/*     */ 
/*     */   public void releaseDrawable()
/*     */     throws LWJGLException
/*     */   {
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  75 */     Sys.initialize();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ContextGLES
 * JD-Core Version:    0.6.2
 */