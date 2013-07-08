/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.Image;
/*  9:   */import org.newdawn.slick.SlickException;
/* 10:   */
/* 18:   */public class TransparentColorTest
/* 19:   */  extends BasicGame
/* 20:   */{
/* 21:   */  private Image image;
/* 22:   */  private Image timage;
/* 23:   */  
/* 24:   */  public TransparentColorTest()
/* 25:   */  {
/* 26:26 */    super("Transparent Color Test");
/* 27:   */  }
/* 28:   */  
/* 30:   */  public void init(GameContainer container)
/* 31:   */    throws SlickException
/* 32:   */  {
/* 33:33 */    this.image = new Image("testdata/transtest.png");
/* 34:34 */    this.timage = new Image("testdata/transtest.png", new Color(94, 66, 41, 255));
/* 35:   */  }
/* 36:   */  
/* 39:   */  public void render(GameContainer container, Graphics g)
/* 40:   */  {
/* 41:41 */    g.setBackground(Color.red);
/* 42:42 */    this.image.draw(10.0F, 10.0F);
/* 43:43 */    this.timage.draw(10.0F, 310.0F);
/* 44:   */  }
/* 45:   */  
/* 50:   */  public void update(GameContainer container, int delta) {}
/* 51:   */  
/* 55:   */  public static void main(String[] argv)
/* 56:   */  {
/* 57:   */    try
/* 58:   */    {
/* 59:59 */      AppGameContainer container = new AppGameContainer(new TransparentColorTest());
/* 60:60 */      container.setDisplayMode(800, 600, false);
/* 61:61 */      container.start();
/* 62:   */    } catch (SlickException e) {
/* 63:63 */      e.printStackTrace();
/* 64:   */    }
/* 65:   */  }
/* 66:   */  
/* 67:   */  public void keyPressed(int key, char c) {}
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.TransparentColorTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */