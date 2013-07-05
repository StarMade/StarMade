/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.HierarchyEvent;
/*     */ import java.awt.event.HierarchyListener;
/*     */ 
/*     */ final class MacOSXCanvasListener
/*     */   implements ComponentListener, HierarchyListener
/*     */ {
/*     */   private final Canvas canvas;
/*     */   private int width;
/*     */   private int height;
/*     */   private boolean context_update;
/*     */   private boolean resized;
/*     */ 
/*     */   MacOSXCanvasListener(Canvas canvas)
/*     */   {
/*  53 */     this.canvas = canvas;
/*  54 */     canvas.addComponentListener(this);
/*  55 */     canvas.addHierarchyListener(this);
/*  56 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public void disableListeners()
/*     */   {
/*  61 */     EventQueue.invokeLater(new Runnable() {
/*     */       public void run() {
/*  63 */         MacOSXCanvasListener.this.canvas.removeComponentListener(MacOSXCanvasListener.this);
/*  64 */         MacOSXCanvasListener.this.canvas.removeHierarchyListener(MacOSXCanvasListener.this);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public boolean syncShouldUpdateContext()
/*     */   {
/*     */     boolean should_update;
/*  71 */     synchronized (this) {
/*  72 */       should_update = this.context_update;
/*  73 */       this.context_update = false;
/*     */     }
/*  75 */     return should_update;
/*     */   }
/*     */ 
/*     */   private synchronized void setUpdate() {
/*  79 */     synchronized (this) {
/*  80 */       this.width = this.canvas.getWidth();
/*  81 */       this.height = this.canvas.getHeight();
/*  82 */       this.context_update = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int syncGetWidth() {
/*  87 */     synchronized (this) {
/*  88 */       return this.width;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int syncGetHeight() {
/*  93 */     synchronized (this) {
/*  94 */       return this.height;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void componentShown(ComponentEvent e) {
/*     */   }
/*     */ 
/*     */   public void componentHidden(ComponentEvent e) {
/*     */   }
/*     */ 
/*     */   public void componentResized(ComponentEvent e) {
/* 105 */     setUpdate();
/* 106 */     this.resized = true;
/*     */   }
/*     */ 
/*     */   public void componentMoved(ComponentEvent e) {
/* 110 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public void hierarchyChanged(HierarchyEvent e) {
/* 114 */     setUpdate();
/*     */   }
/*     */ 
/*     */   public boolean wasResized() {
/* 118 */     if (this.resized) {
/* 119 */       this.resized = false;
/* 120 */       return true;
/*     */     }
/*     */ 
/* 123 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXCanvasListener
 * JD-Core Version:    0.6.2
 */