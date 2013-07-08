package org.newdawn.slick.util;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;

public class Bootstrap
{
  public static void runAsApplication(Game game, int width, int height, boolean fullscreen)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(game, width, height, fullscreen);
      container.start();
    }
    catch (Exception container)
    {
      container.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.Bootstrap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */