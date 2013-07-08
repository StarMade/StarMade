/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.GameContainer;
/*  6:   */import org.newdawn.slick.Graphics;
/*  7:   */import org.newdawn.slick.SlickException;
/*  8:   */
/* 15:   */public class DoubleClickTest
/* 16:   */  extends BasicGame
/* 17:   */{
/* 18:   */  public DoubleClickTest()
/* 19:   */  {
/* 20:20 */    super("Double Click Test");
/* 21:   */  }
/* 22:   */  
/* 24:24 */  private String message = "Click or Double Click";
/* 25:   */  
/* 28:   */  public void init(GameContainer container)
/* 29:   */    throws SlickException
/* 30:   */  {}
/* 31:   */  
/* 34:   */  public void update(GameContainer container, int delta)
/* 35:   */    throws SlickException
/* 36:   */  {}
/* 37:   */  
/* 39:   */  public void render(GameContainer container, Graphics g)
/* 40:   */    throws SlickException
/* 41:   */  {
/* 42:42 */    g.drawString(this.message, 100.0F, 100.0F);
/* 43:   */  }
/* 44:   */  
/* 48:   */  public static void main(String[] argv)
/* 49:   */  {
/* 50:   */    try
/* 51:   */    {
/* 52:52 */      AppGameContainer container = new AppGameContainer(new DoubleClickTest());
/* 53:53 */      container.setDisplayMode(800, 600, false);
/* 54:54 */      container.start();
/* 55:   */    } catch (SlickException e) {
/* 56:56 */      e.printStackTrace();
/* 57:   */    }
/* 58:   */  }
/* 59:   */  
/* 62:   */  public void mouseClicked(int button, int x, int y, int clickCount)
/* 63:   */  {
/* 64:64 */    if (clickCount == 1) {
/* 65:65 */      this.message = ("Single Click: " + button + " " + x + "," + y);
/* 66:   */    }
/* 67:67 */    if (clickCount == 2) {
/* 68:68 */      this.message = ("Double Click: " + button + " " + x + "," + y);
/* 69:   */    }
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.DoubleClickTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */