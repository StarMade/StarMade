package org.newdawn.slick.opengl;

import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public final class GLUtils
{
  public static void checkGLContext()
  {
    try
    {
      Renderer.get().glGetError();
    }
    catch (NullPointerException local_e)
    {
      throw new RuntimeException("OpenGL based resources (images, fonts, sprites etc) must be loaded as part of init() or the game loop. They cannot be loaded before initialisation.");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.GLUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */