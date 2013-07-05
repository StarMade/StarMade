/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.HierarchyEvent;
/*     */ import java.awt.event.HierarchyListener;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ import org.lwjgl.Sys;
/*     */ 
/*     */ public class AWTGLCanvas extends Canvas
/*     */   implements DrawableLWJGL, ComponentListener, HierarchyListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  88 */   private static final AWTCanvasImplementation implementation = createImplementation();
/*     */   private boolean update_context;
/*  62 */   private Object SYNC_LOCK = new Object();
/*     */   private final PixelFormat pixel_format;
/*     */   private final Drawable drawable;
/*     */   private final ContextAttribs attribs;
/*     */   private PeerInfo peer_info;
/*     */   private ContextGL context;
/*     */   private int reentry_count;
/*     */   private boolean first_run;
/*     */ 
/*     */   static AWTCanvasImplementation createImplementation()
/*     */   {
/*  92 */     switch (LWJGLUtil.getPlatform()) {
/*     */     case 1:
/*  94 */       return new LinuxCanvasImplementation();
/*     */     case 3:
/*  96 */       return new WindowsCanvasImplementation();
/*     */     case 2:
/*  98 */       return new MacOSXCanvasImplementation();
/*     */     }
/* 100 */     throw new IllegalStateException("Unsupported platform");
/*     */   }
/*     */ 
/*     */   private void setUpdate()
/*     */   {
/* 105 */     synchronized (this.SYNC_LOCK) {
/* 106 */       this.update_context = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setPixelFormat(PixelFormatLWJGL pf) throws LWJGLException {
/* 111 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void setPixelFormat(PixelFormatLWJGL pf, ContextAttribs attribs) throws LWJGLException {
/* 115 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public PixelFormatLWJGL getPixelFormat() {
/* 119 */     return this.pixel_format;
/*     */   }
/*     */ 
/*     */   public ContextGL getContext()
/*     */   {
/* 124 */     return this.context;
/*     */   }
/*     */ 
/*     */   public ContextGL createSharedContext() throws LWJGLException
/*     */   {
/* 129 */     synchronized (this.SYNC_LOCK) {
/* 130 */       if (this.context == null) throw new IllegalStateException("Canvas not yet displayable");
/*     */ 
/* 132 */       return new ContextGL(this.peer_info, this.context.getContextAttribs(), this.context);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void checkGLError() {
/* 137 */     Util.checkGLError();
/*     */   }
/*     */ 
/*     */   public void initContext(float r, float g, float b)
/*     */   {
/* 142 */     GL11.glClearColor(r, g, b, 0.0F);
/*     */ 
/* 144 */     GL11.glClear(16384);
/*     */   }
/*     */ 
/*     */   public AWTGLCanvas() throws LWJGLException
/*     */   {
/* 149 */     this(new PixelFormat());
/*     */   }
/*     */ 
/*     */   public AWTGLCanvas(PixelFormat pixel_format)
/*     */     throws LWJGLException
/*     */   {
/* 158 */     this(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(), pixel_format);
/*     */   }
/*     */ 
/*     */   public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format)
/*     */     throws LWJGLException
/*     */   {
/* 168 */     this(device, pixel_format, null);
/*     */   }
/*     */ 
/*     */   public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format, Drawable drawable)
/*     */     throws LWJGLException
/*     */   {
/* 179 */     this(device, pixel_format, drawable, null);
/*     */   }
/*     */ 
/*     */   public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format, Drawable drawable, ContextAttribs attribs)
/*     */     throws LWJGLException
/*     */   {
/* 191 */     super(implementation.findConfiguration(device, pixel_format));
/* 192 */     if (pixel_format == null)
/* 193 */       throw new NullPointerException("Pixel format must be non-null");
/* 194 */     addHierarchyListener(this);
/* 195 */     addComponentListener(this);
/* 196 */     this.drawable = drawable;
/* 197 */     this.pixel_format = pixel_format;
/* 198 */     this.attribs = attribs;
/*     */   }
/*     */ 
/*     */   public void addNotify()
/*     */   {
/* 205 */     super.addNotify();
/*     */   }
/*     */ 
/*     */   public void removeNotify()
/*     */   {
/* 212 */     synchronized (this.SYNC_LOCK) {
/* 213 */       destroy();
/* 214 */       super.removeNotify();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setSwapInterval(int swap_interval)
/*     */   {
/* 220 */     synchronized (this.SYNC_LOCK) {
/* 221 */       if (this.context == null)
/* 222 */         throw new IllegalStateException("Canvas not yet displayable");
/* 223 */       ContextGL.setSwapInterval(swap_interval);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setVSyncEnabled(boolean enabled)
/*     */   {
/* 229 */     setSwapInterval(enabled ? 1 : 0);
/*     */   }
/*     */ 
/*     */   public void swapBuffers() throws LWJGLException
/*     */   {
/* 234 */     synchronized (this.SYNC_LOCK) {
/* 235 */       if (this.context == null)
/* 236 */         throw new IllegalStateException("Canvas not yet displayable");
/* 237 */       ContextGL.swapBuffers();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isCurrent() throws LWJGLException {
/* 242 */     synchronized (this.SYNC_LOCK) {
/* 243 */       if (this.context == null) throw new IllegalStateException("Canvas not yet displayable");
/*     */ 
/* 245 */       return this.context.isCurrent();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void makeCurrent()
/*     */     throws LWJGLException
/*     */   {
/* 254 */     synchronized (this.SYNC_LOCK) {
/* 255 */       if (this.context == null)
/* 256 */         throw new IllegalStateException("Canvas not yet displayable");
/* 257 */       this.context.makeCurrent();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void releaseContext() throws LWJGLException {
/* 262 */     synchronized (this.SYNC_LOCK) {
/* 263 */       if (this.context == null)
/* 264 */         throw new IllegalStateException("Canvas not yet displayable");
/* 265 */       if (this.context.isCurrent())
/* 266 */         this.context.releaseCurrent();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void destroy()
/*     */   {
/* 272 */     synchronized (this.SYNC_LOCK) {
/*     */       try {
/* 274 */         if (this.context != null) {
/* 275 */           this.context.forceDestroy();
/* 276 */           this.context = null;
/* 277 */           this.reentry_count = 0;
/* 278 */           this.peer_info.destroy();
/* 279 */           this.peer_info = null;
/*     */         }
/*     */       } catch (LWJGLException e) {
/* 282 */         throw new RuntimeException(e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void setCLSharingProperties(PointerBuffer properties) throws LWJGLException {
/* 288 */     synchronized (this.SYNC_LOCK) {
/* 289 */       if (this.context == null)
/* 290 */         throw new IllegalStateException("Canvas not yet displayable");
/* 291 */       this.context.setCLSharingProperties(properties);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void initGL()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void paintGL()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void paint(Graphics g)
/*     */   {
/* 312 */     LWJGLException exception = null;
/* 313 */     synchronized (this.SYNC_LOCK) {
/* 314 */       if (!isDisplayable())
/* 315 */         return;
/*     */       try {
/* 317 */         if (this.peer_info == null) {
/* 318 */           this.peer_info = implementation.createPeerInfo(this, this.pixel_format, this.attribs);
/*     */         }
/* 320 */         this.peer_info.lockAndGetHandle();
/*     */         try {
/* 322 */           if (this.context == null) {
/* 323 */             this.context = new ContextGL(this.peer_info, this.attribs, this.drawable != null ? (ContextGL)((HierarchyListener)this.drawable).getContext() : null);
/* 324 */             this.first_run = true;
/*     */           }
/*     */ 
/* 327 */           if (this.reentry_count == 0)
/* 328 */             this.context.makeCurrent();
/* 329 */           this.reentry_count += 1;
/*     */           try {
/* 331 */             if (this.update_context) {
/* 332 */               this.context.update();
/* 333 */               this.update_context = false;
/*     */             }
/* 335 */             if (this.first_run) {
/* 336 */               this.first_run = false;
/* 337 */               initGL();
/*     */             }
/* 339 */             paintGL();
/*     */           } finally {
/* 341 */             this.reentry_count -= 1;
/* 342 */             if (this.reentry_count == 0)
/* 343 */               this.context.releaseCurrent();
/*     */           }
/*     */         } finally {
/* 346 */           this.peer_info.unlock();
/*     */         }
/*     */       } catch (LWJGLException e) {
/* 349 */         exception = e;
/*     */       }
/*     */     }
/* 352 */     if (exception != null)
/* 353 */       exceptionOccurred(exception);
/*     */   }
/*     */ 
/*     */   protected void exceptionOccurred(LWJGLException exception)
/*     */   {
/* 363 */     LWJGLUtil.log("Unhandled exception occurred, skipping paint(): " + exception);
/*     */   }
/*     */ 
/*     */   public void update(Graphics g)
/*     */   {
/* 368 */     paint(g);
/*     */   }
/*     */ 
/*     */   public void componentShown(ComponentEvent e) {
/*     */   }
/*     */ 
/*     */   public void componentHidden(ComponentEvent e) {
/*     */   }
/*     */ 
/*     */   public void componentResized(ComponentEvent e) {
/* 378 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public void componentMoved(ComponentEvent e) {
/* 382 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public void setLocation(int x, int y) {
/* 386 */     super.setLocation(x, y);
/* 387 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public void setLocation(Point p) {
/* 391 */     super.setLocation(p);
/* 392 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public void setSize(Dimension d) {
/* 396 */     super.setSize(d);
/* 397 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public void setSize(int width, int height) {
/* 401 */     super.setSize(width, height);
/* 402 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public void setBounds(int x, int y, int width, int height) {
/* 406 */     super.setBounds(x, y, width, height);
/* 407 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public void hierarchyChanged(HierarchyEvent e) {
/* 411 */     setUpdate();
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  87 */     Sys.initialize();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AWTGLCanvas
 * JD-Core Version:    0.6.2
 */