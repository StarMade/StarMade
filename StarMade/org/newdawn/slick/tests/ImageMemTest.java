/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.GameContainer;
/*  6:   */import org.newdawn.slick.Graphics;
/*  7:   */import org.newdawn.slick.Image;
/*  8:   */import org.newdawn.slick.SlickException;
/*  9:   */
/* 16:   */public class ImageMemTest
/* 17:   */  extends BasicGame
/* 18:   */{
/* 19:   */  public ImageMemTest()
/* 20:   */  {
/* 21:21 */    super("Image Memory Test");
/* 22:   */  }
/* 23:   */  
/* 24:   */  public void init(GameContainer container)
/* 25:   */    throws SlickException
/* 26:   */  {
/* 27:   */    try
/* 28:   */    {
/* 29:29 */      Image img = new Image(2400, 2400);
/* 30:30 */      img.getGraphics();
/* 31:31 */      img.destroy();
/* 32:32 */      img = new Image(2400, 2400);
/* 33:33 */      img.getGraphics();
/* 34:   */    } catch (Exception ex) {
/* 35:35 */      ex.printStackTrace();
/* 36:   */    }
/* 37:   */  }
/* 38:   */  
/* 43:   */  public void render(GameContainer container, Graphics g) {}
/* 44:   */  
/* 49:   */  public void update(GameContainer container, int delta) {}
/* 50:   */  
/* 54:   */  public static void main(String[] argv)
/* 55:   */  {
/* 56:   */    try
/* 57:   */    {
/* 58:58 */      AppGameContainer container = new AppGameContainer(new ImageMemTest());
/* 59:59 */      container.setDisplayMode(800, 600, false);
/* 60:60 */      container.start();
/* 61:   */    } catch (SlickException e) {
/* 62:62 */      e.printStackTrace();
/* 63:   */    }
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ImageMemTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */