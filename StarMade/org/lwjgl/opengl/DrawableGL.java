/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ 
/*     */ abstract class DrawableGL
/*     */   implements DrawableLWJGL
/*     */ {
/*     */   protected PixelFormat pixel_format;
/*     */   protected PeerInfo peer_info;
/*     */   protected ContextGL context;
/*     */ 
/*     */   public void setPixelFormat(PixelFormatLWJGL pf)
/*     */     throws LWJGLException
/*     */   {
/*  56 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void setPixelFormat(PixelFormatLWJGL pf, ContextAttribs attribs) throws LWJGLException {
/*  60 */     this.pixel_format = ((PixelFormat)pf);
/*  61 */     this.peer_info = Display.getImplementation().createPeerInfo(this.pixel_format, attribs);
/*     */   }
/*     */ 
/*     */   public PixelFormatLWJGL getPixelFormat() {
/*  65 */     return this.pixel_format;
/*     */   }
/*     */ 
/*     */   public ContextGL getContext() {
/*  69 */     synchronized (GlobalLock.lock) {
/*  70 */       return this.context;
/*     */     }
/*     */   }
/*     */ 
/*     */   public ContextGL createSharedContext() throws LWJGLException {
/*  75 */     synchronized (GlobalLock.lock) {
/*  76 */       checkDestroyed();
/*  77 */       return new ContextGL(this.peer_info, this.context.getContextAttribs(), this.context);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void checkGLError() {
/*  82 */     Util.checkGLError();
/*     */   }
/*     */ 
/*     */   public void setSwapInterval(int swap_interval) {
/*  86 */     ContextGL.setSwapInterval(swap_interval);
/*     */   }
/*     */ 
/*     */   public void swapBuffers() throws LWJGLException {
/*  90 */     ContextGL.swapBuffers();
/*     */   }
/*     */ 
/*     */   public void initContext(float r, float g, float b)
/*     */   {
/*  95 */     GL11.glClearColor(r, g, b, 0.0F);
/*     */ 
/*  97 */     GL11.glClear(16384);
/*     */   }
/*     */ 
/*     */   public boolean isCurrent() throws LWJGLException {
/* 101 */     synchronized (GlobalLock.lock) {
/* 102 */       checkDestroyed();
/* 103 */       return this.context.isCurrent();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void makeCurrent() throws LWJGLException {
/* 108 */     synchronized (GlobalLock.lock) {
/* 109 */       checkDestroyed();
/* 110 */       this.context.makeCurrent();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void releaseContext() throws LWJGLException {
/* 115 */     synchronized (GlobalLock.lock) {
/* 116 */       checkDestroyed();
/* 117 */       if (this.context.isCurrent())
/* 118 */         this.context.releaseCurrent();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroy() {
/* 123 */     synchronized (GlobalLock.lock) {
/* 124 */       if (this.context == null)
/* 125 */         return;
/*     */       try
/*     */       {
/* 128 */         releaseContext();
/*     */ 
/* 130 */         this.context.forceDestroy();
/* 131 */         this.context = null;
/*     */ 
/* 133 */         if (this.peer_info != null) {
/* 134 */           this.peer_info.destroy();
/* 135 */           this.peer_info = null;
/*     */         }
/*     */       } catch (LWJGLException e) {
/* 138 */         LWJGLUtil.log("Exception occurred while destroying Drawable: " + e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setCLSharingProperties(PointerBuffer properties) throws LWJGLException {
/* 144 */     synchronized (GlobalLock.lock) {
/* 145 */       checkDestroyed();
/* 146 */       this.context.setCLSharingProperties(properties);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final void checkDestroyed() {
/* 151 */     if (this.context == null)
/* 152 */       throw new IllegalStateException("The Drawable has no context available.");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.DrawableGL
 * JD-Core Version:    0.6.2
 */