/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ import org.lwjgl.opengles.ContextAttribs;
/*     */ import org.lwjgl.opengles.EGL;
/*     */ import org.lwjgl.opengles.EGLConfig;
/*     */ import org.lwjgl.opengles.EGLContext;
/*     */ import org.lwjgl.opengles.EGLDisplay;
/*     */ import org.lwjgl.opengles.EGLSurface;
/*     */ import org.lwjgl.opengles.GLES20;
/*     */ import org.lwjgl.opengles.PixelFormat;
/*     */ import org.lwjgl.opengles.PowerManagementEventException;
/*     */ import org.lwjgl.opengles.Util;
/*     */ 
/*     */ abstract class DrawableGLES
/*     */   implements DrawableLWJGL
/*     */ {
/*     */   protected PixelFormat pixel_format;
/*     */   protected EGLDisplay eglDisplay;
/*     */   protected EGLConfig eglConfig;
/*     */   protected EGLSurface eglSurface;
/*     */   protected ContextGLES context;
/*     */   protected Drawable shared_drawable;
/*     */ 
/*     */   public void setPixelFormat(PixelFormatLWJGL pf)
/*     */     throws LWJGLException
/*     */   {
/*  68 */     synchronized (GlobalLock.lock) {
/*  69 */       this.pixel_format = ((PixelFormat)pf);
/*     */     }
/*     */   }
/*     */ 
/*     */   public PixelFormatLWJGL getPixelFormat() {
/*  74 */     synchronized (GlobalLock.lock) {
/*  75 */       return this.pixel_format;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void initialize(long window, long display_id, int eglSurfaceType, PixelFormat pf) throws LWJGLException {
/*  80 */     synchronized (GlobalLock.lock) {
/*  81 */       if (this.eglSurface != null) {
/*  82 */         this.eglSurface.destroy();
/*  83 */         this.eglSurface = null;
/*     */       }
/*     */ 
/*  86 */       if (this.eglDisplay != null) {
/*  87 */         this.eglDisplay.terminate();
/*  88 */         this.eglDisplay = null;
/*     */       }
/*     */ 
/*  91 */       EGLDisplay eglDisplay = EGL.eglGetDisplay((int)display_id);
/*     */ 
/*  93 */       int[] attribs = { 12329, 0, 12352, 4, 12333, 0 };
/*     */ 
/*  99 */       EGLConfig[] configs = eglDisplay.chooseConfig(pf.getAttribBuffer(eglDisplay, eglSurfaceType, attribs), null, BufferUtils.createIntBuffer(1));
/* 100 */       if (configs.length == 0) {
/* 101 */         throw new LWJGLException("No EGLConfigs found for the specified PixelFormat.");
/*     */       }
/* 103 */       EGLConfig eglConfig = pf.getBestMatch(configs);
/* 104 */       EGLSurface eglSurface = eglDisplay.createWindowSurface(eglConfig, window, null);
/* 105 */       pf.setSurfaceAttribs(eglSurface);
/*     */ 
/* 107 */       this.eglDisplay = eglDisplay;
/* 108 */       this.eglConfig = eglConfig;
/* 109 */       this.eglSurface = eglSurface;
/*     */ 
/* 112 */       if (this.context != null)
/* 113 */         this.context.getEGLContext().setDisplay(eglDisplay);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void createContext(ContextAttribs attribs, Drawable shared_drawable) throws LWJGLException {
/* 118 */     synchronized (GlobalLock.lock) {
/* 119 */       this.context = new ContextGLES(this, attribs, shared_drawable != null ? ((DrawableGLES)shared_drawable).getContext() : null);
/* 120 */       this.shared_drawable = shared_drawable;
/*     */     }
/*     */   }
/*     */ 
/*     */   Drawable getSharedDrawable() {
/* 125 */     synchronized (GlobalLock.lock) {
/* 126 */       return this.shared_drawable;
/*     */     }
/*     */   }
/*     */ 
/*     */   public EGLDisplay getEGLDisplay() {
/* 131 */     synchronized (GlobalLock.lock) {
/* 132 */       return this.eglDisplay;
/*     */     }
/*     */   }
/*     */ 
/*     */   public EGLConfig getEGLConfig() {
/* 137 */     synchronized (GlobalLock.lock) {
/* 138 */       return this.eglConfig;
/*     */     }
/*     */   }
/*     */ 
/*     */   public EGLSurface getEGLSurface() {
/* 143 */     synchronized (GlobalLock.lock) {
/* 144 */       return this.eglSurface;
/*     */     }
/*     */   }
/*     */ 
/*     */   public ContextGLES getContext() {
/* 149 */     synchronized (GlobalLock.lock) {
/* 150 */       return this.context;
/*     */     }
/*     */   }
/*     */ 
/*     */   public Context createSharedContext() throws LWJGLException {
/* 155 */     synchronized (GlobalLock.lock) {
/* 156 */       checkDestroyed();
/* 157 */       return new ContextGLES(this, this.context.getContextAttribs(), this.context);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void checkGLError() {
/* 162 */     Util.checkGLError();
/*     */   }
/*     */ 
/*     */   public void setSwapInterval(int swap_interval) {
/* 166 */     ContextGLES.setSwapInterval(swap_interval);
/*     */   }
/*     */ 
/*     */   public void swapBuffers() throws LWJGLException {
/* 170 */     ContextGLES.swapBuffers();
/*     */   }
/*     */ 
/*     */   public void initContext(float r, float g, float b)
/*     */   {
/* 175 */     GLES20.glClearColor(r, g, b, 0.0F);
/*     */ 
/* 177 */     GLES20.glClear(16384);
/*     */   }
/*     */ 
/*     */   public boolean isCurrent() throws LWJGLException {
/* 181 */     synchronized (GlobalLock.lock) {
/* 182 */       checkDestroyed();
/* 183 */       return this.context.isCurrent();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void makeCurrent() throws LWJGLException, PowerManagementEventException {
/* 188 */     synchronized (GlobalLock.lock) {
/* 189 */       checkDestroyed();
/* 190 */       this.context.makeCurrent();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void releaseContext() throws LWJGLException, PowerManagementEventException {
/* 195 */     synchronized (GlobalLock.lock) {
/* 196 */       checkDestroyed();
/* 197 */       if (this.context.isCurrent())
/* 198 */         this.context.releaseCurrent();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroy() {
/* 203 */     synchronized (GlobalLock.lock) {
/*     */       try {
/* 205 */         if (this.context != null) {
/*     */           try {
/* 207 */             releaseContext();
/*     */           }
/*     */           catch (PowerManagementEventException e)
/*     */           {
/*     */           }
/* 212 */           this.context.forceDestroy();
/* 213 */           this.context = null;
/*     */         }
/*     */ 
/* 216 */         if (this.eglSurface != null) {
/* 217 */           this.eglSurface.destroy();
/* 218 */           this.eglSurface = null;
/*     */         }
/*     */ 
/* 221 */         if (this.eglDisplay != null) {
/* 222 */           this.eglDisplay.terminate();
/* 223 */           this.eglDisplay = null;
/*     */         }
/*     */ 
/* 226 */         this.pixel_format = null;
/* 227 */         this.shared_drawable = null;
/*     */       } catch (LWJGLException e) {
/* 229 */         LWJGLUtil.log("Exception occurred while destroying Drawable: " + e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void checkDestroyed() {
/* 235 */     if (this.context == null)
/* 236 */       throw new IllegalStateException("The Drawable has no context available.");
/*     */   }
/*     */ 
/*     */   public void setCLSharingProperties(PointerBuffer properties) throws LWJGLException {
/* 240 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.DrawableGLES
 * JD-Core Version:    0.6.2
 */