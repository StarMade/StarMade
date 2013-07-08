/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Canvas;
/*   4:    */import java.awt.Component;
/*   5:    */import java.awt.Container;
/*   6:    */import java.awt.Insets;
/*   7:    */import java.awt.event.ComponentEvent;
/*   8:    */import java.awt.event.ComponentListener;
/*   9:    */import java.nio.ByteBuffer;
/*  10:    */import org.lwjgl.LWJGLException;
/*  11:    */
/*  50:    */abstract class MacOSXCanvasPeerInfo
/*  51:    */  extends MacOSXPeerInfo
/*  52:    */{
/*  53: 53 */  private final AWTSurfaceLock awt_surface = new AWTSurfaceLock();
/*  54:    */  public ByteBuffer window_handle;
/*  55:    */  
/*  56:    */  protected MacOSXCanvasPeerInfo(PixelFormat pixel_format, ContextAttribs attribs, boolean support_pbuffer) throws LWJGLException {
/*  57: 57 */    super(pixel_format, attribs, true, true, support_pbuffer, true);
/*  58:    */  }
/*  59:    */  
/*  60:    */  protected void initHandle(Canvas component) throws LWJGLException {
/*  61: 61 */    boolean forceCALayer = true;
/*  62: 62 */    String javaVersion = System.getProperty("java.version");
/*  63:    */    
/*  64: 64 */    if ((javaVersion.startsWith("1.5")) || (javaVersion.startsWith("1.6")))
/*  65:    */    {
/*  68: 68 */      forceCALayer = false;
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    Insets insets = getInsets(component);
/*  72:    */    
/*  73: 73 */    int top = insets != null ? insets.top : 0;
/*  74: 74 */    int left = insets != null ? insets.left : 0;
/*  75:    */    
/*  76: 76 */    this.window_handle = nInitHandle(this.awt_surface.lockAndGetHandle(component), getHandle(), this.window_handle, forceCALayer, component.getX() - left, component.getY() - top);
/*  77:    */    
/*  78: 78 */    if (javaVersion.startsWith("1.7"))
/*  79:    */    {
/*  81: 81 */      addComponentListener(component);
/*  82:    */    }
/*  83:    */  }
/*  84:    */  
/*  85:    */  private void addComponentListener(final Canvas component)
/*  86:    */  {
/*  87: 87 */    ComponentListener[] components = component.getComponentListeners();
/*  88:    */    
/*  90: 90 */    for (int i = 0; i < components.length; i++) {
/*  91: 91 */      ComponentListener c = components[i];
/*  92: 92 */      if (c.toString() == "CanvasPeerInfoListener") {
/*  93: 93 */        return;
/*  94:    */      }
/*  95:    */    }
/*  96:    */    
/*  97: 97 */    ComponentListener comp = new ComponentListener()
/*  98:    */    {
/*  99:    */      public void componentHidden(ComponentEvent e) {}
/* 100:    */      
/* 101:    */      public void componentMoved(ComponentEvent e)
/* 102:    */      {
/* 103:103 */        Insets insets = MacOSXCanvasPeerInfo.this.getInsets(component);
/* 104:    */        
/* 105:105 */        int top = insets != null ? insets.top : 0;
/* 106:106 */        int left = insets != null ? insets.left : 0;
/* 107:    */        
/* 108:108 */        MacOSXCanvasPeerInfo.nSetLayerPosition(MacOSXCanvasPeerInfo.this.getHandle(), component.getX() - left, component.getY() - top);
/* 109:    */      }
/* 110:    */      
/* 111:    */      public void componentResized(ComponentEvent e) {
/* 112:112 */        Insets insets = MacOSXCanvasPeerInfo.this.getInsets(component);
/* 113:    */        
/* 114:114 */        int top = insets != null ? insets.top : 0;
/* 115:115 */        int left = insets != null ? insets.left : 0;
/* 116:    */        
/* 117:117 */        MacOSXCanvasPeerInfo.nSetLayerPosition(MacOSXCanvasPeerInfo.this.getHandle(), component.getX() - left, component.getY() - top);
/* 118:    */      }
/* 119:    */      
/* 121:    */      public void componentShown(ComponentEvent e) {}
/* 122:    */      
/* 123:    */      public String toString()
/* 124:    */      {
/* 125:125 */        return "CanvasPeerInfoListener";
/* 126:    */      }
/* 127:    */      
/* 128:128 */    };
/* 129:129 */    component.addComponentListener(comp);
/* 130:    */  }
/* 131:    */  
/* 132:    */  private static native ByteBuffer nInitHandle(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, ByteBuffer paramByteBuffer3, boolean paramBoolean, int paramInt1, int paramInt2) throws LWJGLException;
/* 133:    */  
/* 134:    */  private static native void nSetLayerPosition(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
/* 135:    */  
/* 136:    */  protected void doUnlock() throws LWJGLException {
/* 137:137 */    this.awt_surface.unlock();
/* 138:    */  }
/* 139:    */  
/* 140:    */  private Insets getInsets(Canvas component) {
/* 141:141 */    Component parent = component.getParent();
/* 142:    */    
/* 143:143 */    while (parent != null) {
/* 144:144 */      if ((parent instanceof Container)) {
/* 145:145 */        return ((Container)parent).getInsets();
/* 146:    */      }
/* 147:147 */      parent = parent.getParent();
/* 148:    */    }
/* 149:    */    
/* 150:150 */    return null;
/* 151:    */  }
/* 152:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXCanvasPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */