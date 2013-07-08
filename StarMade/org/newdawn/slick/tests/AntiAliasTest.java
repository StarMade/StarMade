/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.SlickException;
/*  9:   */
/* 16:   */public class AntiAliasTest
/* 17:   */  extends BasicGame
/* 18:   */{
/* 19:   */  public AntiAliasTest()
/* 20:   */  {
/* 21:21 */    super("AntiAlias Test");
/* 22:   */  }
/* 23:   */  
/* 25:   */  public void init(GameContainer container)
/* 26:   */    throws SlickException
/* 27:   */  {
/* 28:28 */    container.getGraphics().setBackground(Color.green);
/* 29:   */  }
/* 30:   */  
/* 33:   */  public void update(GameContainer container, int delta)
/* 34:   */    throws SlickException
/* 35:   */  {}
/* 36:   */  
/* 38:   */  public void render(GameContainer container, Graphics g)
/* 39:   */    throws SlickException
/* 40:   */  {
/* 41:41 */    g.setAntiAlias(true);
/* 42:42 */    g.setColor(Color.red);
/* 43:43 */    g.drawOval(100.0F, 100.0F, 100.0F, 100.0F);
/* 44:44 */    g.fillOval(300.0F, 100.0F, 100.0F, 100.0F);
/* 45:45 */    g.setAntiAlias(false);
/* 46:46 */    g.setColor(Color.red);
/* 47:47 */    g.drawOval(100.0F, 300.0F, 100.0F, 100.0F);
/* 48:48 */    g.fillOval(300.0F, 300.0F, 100.0F, 100.0F);
/* 49:   */  }
/* 50:   */  
/* 54:   */  public static void main(String[] argv)
/* 55:   */  {
/* 56:   */    try
/* 57:   */    {
/* 58:58 */      AppGameContainer container = new AppGameContainer(new AntiAliasTest());
/* 59:59 */      container.setDisplayMode(800, 600, false);
/* 60:60 */      container.start();
/* 61:   */    } catch (SlickException e) {
/* 62:62 */      e.printStackTrace();
/* 63:   */    }
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.AntiAliasTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */