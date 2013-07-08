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
/* 20:   */public class FlashTest
/* 21:   */  extends BasicGame
/* 22:   */{
/* 23:   */  private Image image;
/* 24:   */  private boolean flash;
/* 25:   */  private GameContainer container;
/* 26:   */  
/* 27:   */  public FlashTest()
/* 28:   */  {
/* 29:29 */    super("Flash Test");
/* 30:   */  }
/* 31:   */  
/* 33:   */  public void init(GameContainer container)
/* 34:   */    throws SlickException
/* 35:   */  {
/* 36:36 */    this.container = container;
/* 37:   */    
/* 38:38 */    this.image = new Image("testdata/logo.tga");
/* 39:   */  }
/* 40:   */  
/* 43:   */  public void render(GameContainer container, Graphics g)
/* 44:   */  {
/* 45:45 */    g.drawString("Press space to toggle", 10.0F, 50.0F);
/* 46:46 */    if (this.flash) {
/* 47:47 */      this.image.draw(100.0F, 100.0F);
/* 48:   */    } else {
/* 49:49 */      this.image.drawFlash(100.0F, 100.0F, this.image.getWidth(), this.image.getHeight(), new Color(1.0F, 0.0F, 1.0F, 1.0F));
/* 50:   */    }
/* 51:   */  }
/* 52:   */  
/* 57:   */  public void update(GameContainer container, int delta) {}
/* 58:   */  
/* 62:   */  public static void main(String[] argv)
/* 63:   */  {
/* 64:   */    try
/* 65:   */    {
/* 66:66 */      AppGameContainer container = new AppGameContainer(new FlashTest());
/* 67:67 */      container.setDisplayMode(800, 600, false);
/* 68:68 */      container.start();
/* 69:   */    } catch (SlickException e) {
/* 70:70 */      e.printStackTrace();
/* 71:   */    }
/* 72:   */  }
/* 73:   */  
/* 76:   */  public void keyPressed(int key, char c)
/* 77:   */  {
/* 78:78 */    if (key == 57) {
/* 79:79 */      this.flash = (!this.flash);
/* 80:   */    }
/* 81:81 */    if (key == 1) {
/* 82:82 */      this.container.exit();
/* 83:   */    }
/* 84:   */  }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.FlashTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */