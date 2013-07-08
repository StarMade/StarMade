/*  1:   */package org.newdawn.slick.util;
/*  2:   */
/*  3:   */import org.newdawn.slick.opengl.renderer.Renderer;
/*  4:   */import org.newdawn.slick.opengl.renderer.SGL;
/*  5:   */
/* 11:   */public class MaskUtil
/* 12:   */{
/* 13:13 */  protected static SGL GL = ;
/* 14:   */  
/* 18:   */  public static void defineMask()
/* 19:   */  {
/* 20:20 */    GL.glDepthMask(true);
/* 21:21 */    GL.glClearDepth(1.0F);
/* 22:22 */    GL.glClear(256);
/* 23:23 */    GL.glDepthFunc(519);
/* 24:24 */    GL.glEnable(2929);
/* 25:25 */    GL.glDepthMask(true);
/* 26:26 */    GL.glColorMask(false, false, false, false);
/* 27:   */  }
/* 28:   */  
/* 31:   */  public static void finishDefineMask()
/* 32:   */  {
/* 33:33 */    GL.glDepthMask(false);
/* 34:34 */    GL.glColorMask(true, true, true, true);
/* 35:   */  }
/* 36:   */  
/* 39:   */  public static void drawOnMask()
/* 40:   */  {
/* 41:41 */    GL.glDepthFunc(514);
/* 42:   */  }
/* 43:   */  
/* 46:   */  public static void drawOffMask()
/* 47:   */  {
/* 48:48 */    GL.glDepthFunc(517);
/* 49:   */  }
/* 50:   */  
/* 53:   */  public static void resetMask()
/* 54:   */  {
/* 55:55 */    GL.glDepthMask(true);
/* 56:56 */    GL.glClearDepth(0.0F);
/* 57:57 */    GL.glClear(256);
/* 58:58 */    GL.glDepthMask(false);
/* 59:   */    
/* 60:60 */    GL.glDisable(2929);
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.MaskUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */