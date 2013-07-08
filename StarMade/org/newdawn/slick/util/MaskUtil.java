package org.newdawn.slick.util;

import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class MaskUtil
{
  protected static SGL field_1919 = ;
  
  public static void defineMask()
  {
    field_1919.glDepthMask(true);
    field_1919.glClearDepth(1.0F);
    field_1919.glClear(256);
    field_1919.glDepthFunc(519);
    field_1919.glEnable(2929);
    field_1919.glDepthMask(true);
    field_1919.glColorMask(false, false, false, false);
  }
  
  public static void finishDefineMask()
  {
    field_1919.glDepthMask(false);
    field_1919.glColorMask(true, true, true, true);
  }
  
  public static void drawOnMask()
  {
    field_1919.glDepthFunc(514);
  }
  
  public static void drawOffMask()
  {
    field_1919.glDepthFunc(517);
  }
  
  public static void resetMask()
  {
    field_1919.glDepthMask(true);
    field_1919.glClearDepth(0.0F);
    field_1919.glClear(256);
    field_1919.glDepthMask(false);
    field_1919.glDisable(2929);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.MaskUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */