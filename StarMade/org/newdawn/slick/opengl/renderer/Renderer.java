/*  1:   */package org.newdawn.slick.opengl.renderer;
/*  2:   */
/*  6:   */public class Renderer
/*  7:   */{
/*  8:   */  public static final int IMMEDIATE_RENDERER = 1;
/*  9:   */  
/* 12:   */  public static final int VERTEX_ARRAY_RENDERER = 2;
/* 13:   */  
/* 16:   */  public static final int DEFAULT_LINE_STRIP_RENDERER = 3;
/* 17:   */  
/* 20:   */  public static final int QUAD_BASED_LINE_STRIP_RENDERER = 4;
/* 21:   */  
/* 23:23 */  private static SGL renderer = new ImmediateModeOGLRenderer();
/* 24:   */  
/* 25:25 */  private static LineStripRenderer lineStripRenderer = new DefaultLineStripRenderer();
/* 26:   */  
/* 31:   */  public static void setRenderer(int type)
/* 32:   */  {
/* 33:33 */    switch (type) {
/* 34:   */    case 1: 
/* 35:35 */      setRenderer(new ImmediateModeOGLRenderer());
/* 36:36 */      return;
/* 37:   */    case 2: 
/* 38:38 */      setRenderer(new VAOGLRenderer());
/* 39:39 */      return;
/* 40:   */    }
/* 41:   */    
/* 42:42 */    throw new RuntimeException("Unknown renderer type: " + type);
/* 43:   */  }
/* 44:   */  
/* 49:   */  public static void setLineStripRenderer(int type)
/* 50:   */  {
/* 51:51 */    switch (type) {
/* 52:   */    case 3: 
/* 53:53 */      setLineStripRenderer(new DefaultLineStripRenderer());
/* 54:54 */      return;
/* 55:   */    case 4: 
/* 56:56 */      setLineStripRenderer(new QuadBasedLineStripRenderer());
/* 57:57 */      return;
/* 58:   */    }
/* 59:   */    
/* 60:60 */    throw new RuntimeException("Unknown line strip renderer type: " + type);
/* 61:   */  }
/* 62:   */  
/* 67:   */  public static void setLineStripRenderer(LineStripRenderer renderer)
/* 68:   */  {
/* 69:69 */    lineStripRenderer = renderer;
/* 70:   */  }
/* 71:   */  
/* 76:   */  public static void setRenderer(SGL r)
/* 77:   */  {
/* 78:78 */    renderer = r;
/* 79:   */  }
/* 80:   */  
/* 85:   */  public static SGL get()
/* 86:   */  {
/* 87:87 */    return renderer;
/* 88:   */  }
/* 89:   */  
/* 94:   */  public static LineStripRenderer getLineStripRenderer()
/* 95:   */  {
/* 96:96 */    return lineStripRenderer;
/* 97:   */  }
/* 98:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.renderer.Renderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */