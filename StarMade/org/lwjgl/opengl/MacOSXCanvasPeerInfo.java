/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.nio.ByteBuffer;
/*     */ import org.lwjgl.LWJGLException;
/*     */ 
/*     */ abstract class MacOSXCanvasPeerInfo extends MacOSXPeerInfo
/*     */ {
/*  53 */   private final AWTSurfaceLock awt_surface = new AWTSurfaceLock();
/*     */   public ByteBuffer window_handle;
/*     */ 
/*     */   protected MacOSXCanvasPeerInfo(PixelFormat pixel_format, ContextAttribs attribs, boolean support_pbuffer)
/*     */     throws LWJGLException
/*     */   {
/*  57 */     super(pixel_format, attribs, true, true, support_pbuffer, true);
/*     */   }
/*     */ 
/*     */   protected void initHandle(Canvas component) throws LWJGLException {
/*  61 */     boolean forceCALayer = true;
/*  62 */     String javaVersion = System.getProperty("java.version");
/*     */ 
/*  64 */     if ((javaVersion.startsWith("1.5")) || (javaVersion.startsWith("1.6")))
/*     */     {
/*  68 */       forceCALayer = false;
/*     */     }
/*     */ 
/*  71 */     Insets insets = getInsets(component);
/*     */ 
/*  73 */     int top = insets != null ? insets.top : 0;
/*  74 */     int left = insets != null ? insets.left : 0;
/*     */ 
/*  76 */     this.window_handle = nInitHandle(this.awt_surface.lockAndGetHandle(component), getHandle(), this.window_handle, forceCALayer, component.getX() - left, component.getY() - top);
/*     */ 
/*  78 */     if (javaVersion.startsWith("1.7"))
/*     */     {
/*  81 */       addComponentListener(component);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addComponentListener(final Canvas component)
/*     */   {
/*  87 */     ComponentListener[] components = component.getComponentListeners();
/*     */ 
/*  90 */     for (int i = 0; i < components.length; i++) {
/*  91 */       ComponentListener c = components[i];
/*  92 */       if (c.toString() == "CanvasPeerInfoListener") {
/*  93 */         return;
/*     */       }
/*     */     }
/*     */ 
/*  97 */     ComponentListener comp = new ComponentListener()
/*     */     {
/*     */       public void componentHidden(ComponentEvent e) {
/*     */       }
/*     */ 
/*     */       public void componentMoved(ComponentEvent e) {
/* 103 */         Insets insets = MacOSXCanvasPeerInfo.this.getInsets(component);
/*     */ 
/* 105 */         int top = insets != null ? insets.top : 0;
/* 106 */         int left = insets != null ? insets.left : 0;
/*     */ 
/* 108 */         MacOSXCanvasPeerInfo.nSetLayerPosition(MacOSXCanvasPeerInfo.this.getHandle(), component.getX() - left, component.getY() - top);
/*     */       }
/*     */ 
/*     */       public void componentResized(ComponentEvent e) {
/* 112 */         Insets insets = MacOSXCanvasPeerInfo.this.getInsets(component);
/*     */ 
/* 114 */         int top = insets != null ? insets.top : 0;
/* 115 */         int left = insets != null ? insets.left : 0;
/*     */ 
/* 117 */         MacOSXCanvasPeerInfo.nSetLayerPosition(MacOSXCanvasPeerInfo.this.getHandle(), component.getX() - left, component.getY() - top);
/*     */       }
/*     */ 
/*     */       public void componentShown(ComponentEvent e)
/*     */       {
/*     */       }
/*     */ 
/*     */       public String toString() {
/* 125 */         return "CanvasPeerInfoListener";
/*     */       }
/*     */     };
/* 129 */     component.addComponentListener(comp);
/*     */   }
/*     */ 
/*     */   private static native ByteBuffer nInitHandle(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, ByteBuffer paramByteBuffer3, boolean paramBoolean, int paramInt1, int paramInt2) throws LWJGLException;
/*     */ 
/*     */   private static native void nSetLayerPosition(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
/*     */ 
/*     */   protected void doUnlock() throws LWJGLException {
/* 137 */     this.awt_surface.unlock();
/*     */   }
/*     */ 
/*     */   private Insets getInsets(Canvas component) {
/* 141 */     Component parent = component.getParent();
/*     */ 
/* 143 */     while (parent != null) {
/* 144 */       if ((parent instanceof Container)) {
/* 145 */         return ((Container)parent).getInsets();
/*     */       }
/* 147 */       parent = parent.getParent();
/*     */     }
/*     */ 
/* 150 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXCanvasPeerInfo
 * JD-Core Version:    0.6.2
 */