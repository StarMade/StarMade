/*  1:   */package org.newdawn.slick.opengl;
/*  2:   */
/*  3:   */import org.newdawn.slick.opengl.renderer.Renderer;
/*  4:   */import org.newdawn.slick.opengl.renderer.SGL;
/*  5:   */
/* 11:   */public final class GLUtils
/* 12:   */{
/* 13:   */  public static void checkGLContext()
/* 14:   */  {
/* 15:   */    try
/* 16:   */    {
/* 17:17 */      Renderer.get().glGetError();
/* 18:   */    } catch (NullPointerException e) {
/* 19:19 */      throw new RuntimeException("OpenGL based resources (images, fonts, sprites etc) must be loaded as part of init() or the game loop. They cannot be loaded before initialisation.");
/* 20:   */    }
/* 21:   */  }
/* 22:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.GLUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */