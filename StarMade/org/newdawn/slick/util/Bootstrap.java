/*  1:   */package org.newdawn.slick.util;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.Game;
/*  5:   */
/* 17:   */public class Bootstrap
/* 18:   */{
/* 19:   */  public static void runAsApplication(Game game, int width, int height, boolean fullscreen)
/* 20:   */  {
/* 21:   */    try
/* 22:   */    {
/* 23:23 */      AppGameContainer container = new AppGameContainer(game, width, height, fullscreen);
/* 24:24 */      container.start();
/* 25:   */    } catch (Exception e) {
/* 26:26 */      e.printStackTrace();
/* 27:   */    }
/* 28:   */  }
/* 29:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.Bootstrap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */