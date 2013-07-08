/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Canvas;
/*   4:    */import java.awt.EventQueue;
/*   5:    */import java.awt.event.ComponentEvent;
/*   6:    */import java.awt.event.ComponentListener;
/*   7:    */import java.awt.event.HierarchyEvent;
/*   8:    */import java.awt.event.HierarchyListener;
/*   9:    */
/*  42:    */final class MacOSXCanvasListener
/*  43:    */  implements ComponentListener, HierarchyListener
/*  44:    */{
/*  45:    */  private final Canvas canvas;
/*  46:    */  private int width;
/*  47:    */  private int height;
/*  48:    */  private boolean context_update;
/*  49:    */  private boolean resized;
/*  50:    */  
/*  51:    */  MacOSXCanvasListener(Canvas canvas)
/*  52:    */  {
/*  53: 53 */    this.canvas = canvas;
/*  54: 54 */    canvas.addComponentListener(this);
/*  55: 55 */    canvas.addHierarchyListener(this);
/*  56: 56 */    setUpdate();
/*  57:    */  }
/*  58:    */  
/*  59:    */  public void disableListeners()
/*  60:    */  {
/*  61: 61 */    EventQueue.invokeLater(new Runnable() {
/*  62:    */      public void run() {
/*  63: 63 */        MacOSXCanvasListener.this.canvas.removeComponentListener(MacOSXCanvasListener.this);
/*  64: 64 */        MacOSXCanvasListener.this.canvas.removeHierarchyListener(MacOSXCanvasListener.this);
/*  65:    */      }
/*  66:    */    });
/*  67:    */  }
/*  68:    */  
/*  69:    */  public boolean syncShouldUpdateContext() {
/*  70:    */    boolean should_update;
/*  71: 71 */    synchronized (this) {
/*  72: 72 */      should_update = this.context_update;
/*  73: 73 */      this.context_update = false;
/*  74:    */    }
/*  75: 75 */    return should_update;
/*  76:    */  }
/*  77:    */  
/*  78:    */  private synchronized void setUpdate() {
/*  79: 79 */    synchronized (this) {
/*  80: 80 */      this.width = this.canvas.getWidth();
/*  81: 81 */      this.height = this.canvas.getHeight();
/*  82: 82 */      this.context_update = true;
/*  83:    */    }
/*  84:    */  }
/*  85:    */  
/*  86:    */  public int syncGetWidth() {
/*  87: 87 */    synchronized (this) {
/*  88: 88 */      return this.width;
/*  89:    */    }
/*  90:    */  }
/*  91:    */  
/*  92:    */  public int syncGetHeight() {
/*  93: 93 */    synchronized (this) {
/*  94: 94 */      return this.height;
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/*  99:    */  public void componentShown(ComponentEvent e) {}
/* 100:    */  
/* 101:    */  public void componentHidden(ComponentEvent e) {}
/* 102:    */  
/* 103:    */  public void componentResized(ComponentEvent e)
/* 104:    */  {
/* 105:105 */    setUpdate();
/* 106:106 */    this.resized = true;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public void componentMoved(ComponentEvent e) {
/* 110:110 */    setUpdate();
/* 111:    */  }
/* 112:    */  
/* 113:    */  public void hierarchyChanged(HierarchyEvent e) {
/* 114:114 */    setUpdate();
/* 115:    */  }
/* 116:    */  
/* 117:    */  public boolean wasResized() {
/* 118:118 */    if (this.resized) {
/* 119:119 */      this.resized = false;
/* 120:120 */      return true;
/* 121:    */    }
/* 122:    */    
/* 123:123 */    return false;
/* 124:    */  }
/* 125:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXCanvasListener
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */