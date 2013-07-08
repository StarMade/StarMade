/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AngelCodeFont;
/*  4:   */import org.newdawn.slick.AppGameContainer;
/*  5:   */import org.newdawn.slick.BasicGame;
/*  6:   */import org.newdawn.slick.Font;
/*  7:   */import org.newdawn.slick.GameContainer;
/*  8:   */import org.newdawn.slick.Graphics;
/*  9:   */import org.newdawn.slick.Image;
/* 10:   */import org.newdawn.slick.SlickException;
/* 11:   */
/* 19:   */public class PureFontTest
/* 20:   */  extends BasicGame
/* 21:   */{
/* 22:   */  private Font font;
/* 23:   */  private Image image;
/* 24:   */  private static AppGameContainer container;
/* 25:   */  
/* 26:   */  public PureFontTest()
/* 27:   */  {
/* 28:28 */    super("Hiero Font Test");
/* 29:   */  }
/* 30:   */  
/* 32:   */  public void init(GameContainer container)
/* 33:   */    throws SlickException
/* 34:   */  {
/* 35:35 */    this.image = new Image("testdata/sky.jpg");
/* 36:36 */    this.font = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
/* 37:   */  }
/* 38:   */  
/* 41:   */  public void render(GameContainer container, Graphics g)
/* 42:   */  {
/* 43:43 */    this.image.draw(0.0F, 0.0F, 800.0F, 600.0F);
/* 44:44 */    this.font.drawString(100.0F, 32.0F, "On top of old smokey, all");
/* 45:45 */    this.font.drawString(100.0F, 80.0F, "covered with sand..");
/* 46:   */  }
/* 47:   */  
/* 50:   */  public void update(GameContainer container, int delta)
/* 51:   */    throws SlickException
/* 52:   */  {}
/* 53:   */  
/* 56:   */  public void keyPressed(int key, char c)
/* 57:   */  {
/* 58:58 */    if (key == 1) {
/* 59:59 */      System.exit(0);
/* 60:   */    }
/* 61:   */  }
/* 62:   */  
/* 69:   */  public static void main(String[] argv)
/* 70:   */  {
/* 71:   */    try
/* 72:   */    {
/* 73:73 */      container = new AppGameContainer(new PureFontTest());
/* 74:74 */      container.setDisplayMode(800, 600, false);
/* 75:75 */      container.start();
/* 76:   */    } catch (SlickException e) {
/* 77:77 */      e.printStackTrace();
/* 78:   */    }
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.PureFontTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */