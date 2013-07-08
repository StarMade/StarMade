/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.Font;
/*  7:   */import org.newdawn.slick.GameContainer;
/*  8:   */import org.newdawn.slick.Graphics;
/*  9:   */import org.newdawn.slick.SlickException;
/* 10:   */import org.newdawn.slick.SpriteSheet;
/* 11:   */import org.newdawn.slick.SpriteSheetFont;
/* 12:   */import org.newdawn.slick.util.Log;
/* 13:   */
/* 14:   */public class SpriteSheetFontTest extends BasicGame
/* 15:   */{
/* 16:   */  private Font font;
/* 17:   */  private static AppGameContainer container;
/* 18:   */  
/* 19:   */  public SpriteSheetFontTest()
/* 20:   */  {
/* 21:21 */    super("SpriteSheetFont Test");
/* 22:   */  }
/* 23:   */  
/* 25:   */  public void init(GameContainer container)
/* 26:   */    throws SlickException
/* 27:   */  {
/* 28:28 */    SpriteSheet sheet = new SpriteSheet("testdata/spriteSheetFont.png", 32, 32);
/* 29:29 */    this.font = new SpriteSheetFont(sheet, ' ');
/* 30:   */  }
/* 31:   */  
/* 34:   */  public void render(GameContainer container, Graphics g)
/* 35:   */  {
/* 36:36 */    g.setBackground(Color.gray);
/* 37:37 */    this.font.drawString(80.0F, 5.0F, "A FONT EXAMPLE", Color.red);
/* 38:38 */    this.font.drawString(100.0F, 50.0F, "A MORE COMPLETE LINE");
/* 39:   */  }
/* 40:   */  
/* 43:   */  public void update(GameContainer container, int delta)
/* 44:   */    throws SlickException
/* 45:   */  {}
/* 46:   */  
/* 49:   */  public void keyPressed(int key, char c)
/* 50:   */  {
/* 51:51 */    if (key == 1) {
/* 52:52 */      System.exit(0);
/* 53:   */    }
/* 54:54 */    if (key == 57) {
/* 55:   */      try {
/* 56:56 */        container.setDisplayMode(640, 480, false);
/* 57:   */      } catch (SlickException e) {
/* 58:58 */        Log.error(e);
/* 59:   */      }
/* 60:   */    }
/* 61:   */  }
/* 62:   */  
/* 71:   */  public static void main(String[] argv)
/* 72:   */  {
/* 73:   */    try
/* 74:   */    {
/* 75:75 */      container = new AppGameContainer(new SpriteSheetFontTest());
/* 76:76 */      container.setDisplayMode(800, 600, false);
/* 77:77 */      container.start();
/* 78:   */    } catch (SlickException e) {
/* 79:79 */      e.printStackTrace();
/* 80:   */    }
/* 81:   */  }
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.SpriteSheetFontTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */