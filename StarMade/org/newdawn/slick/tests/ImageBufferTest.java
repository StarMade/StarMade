/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.GameContainer;
/*  6:   */import org.newdawn.slick.Graphics;
/*  7:   */import org.newdawn.slick.Image;
/*  8:   */import org.newdawn.slick.ImageBuffer;
/*  9:   */import org.newdawn.slick.SlickException;
/* 10:   */
/* 18:   */public class ImageBufferTest
/* 19:   */  extends BasicGame
/* 20:   */{
/* 21:   */  private Image image;
/* 22:   */  
/* 23:   */  public ImageBufferTest()
/* 24:   */  {
/* 25:25 */    super("Image Buffer Test");
/* 26:   */  }
/* 27:   */  
/* 29:   */  public void init(GameContainer container)
/* 30:   */    throws SlickException
/* 31:   */  {
/* 32:32 */    ImageBuffer buffer = new ImageBuffer(320, 200);
/* 33:33 */    for (int x = 0; x < 320; x++) {
/* 34:34 */      for (int y = 0; y < 200; y++) {
/* 35:35 */        if (y == 20) {
/* 36:36 */          buffer.setRGBA(x, y, 255, 255, 255, 255);
/* 37:   */        } else {
/* 38:38 */          buffer.setRGBA(x, y, x, y, 0, 255);
/* 39:   */        }
/* 40:   */      }
/* 41:   */    }
/* 42:42 */    this.image = buffer.getImage();
/* 43:   */  }
/* 44:   */  
/* 47:   */  public void render(GameContainer container, Graphics g)
/* 48:   */  {
/* 49:49 */    this.image.draw(50.0F, 50.0F);
/* 50:   */  }
/* 51:   */  
/* 55:   */  public void update(GameContainer container, int delta) {}
/* 56:   */  
/* 60:   */  public void keyPressed(int key, char c)
/* 61:   */  {
/* 62:62 */    if (key == 1) {
/* 63:63 */      System.exit(0);
/* 64:   */    }
/* 65:   */  }
/* 66:   */  
/* 70:   */  public static void main(String[] argv)
/* 71:   */  {
/* 72:   */    try
/* 73:   */    {
/* 74:74 */      AppGameContainer container = new AppGameContainer(new ImageBufferTest());
/* 75:75 */      container.setDisplayMode(800, 600, false);
/* 76:76 */      container.start();
/* 77:   */    } catch (SlickException e) {
/* 78:78 */      e.printStackTrace();
/* 79:   */    }
/* 80:   */  }
/* 81:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ImageBufferTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */