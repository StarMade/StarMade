/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.GameContainer;
/*  6:   */import org.newdawn.slick.Graphics;
/*  7:   */import org.newdawn.slick.Image;
/*  8:   */import org.newdawn.slick.SlickException;
/*  9:   */
/* 19:   */public class ImageCornerTest
/* 20:   */  extends BasicGame
/* 21:   */{
/* 22:   */  private Image image;
/* 23:   */  private Image[] images;
/* 24:   */  private int width;
/* 25:   */  private int height;
/* 26:   */  
/* 27:   */  public ImageCornerTest()
/* 28:   */  {
/* 29:29 */    super("Image Corner Test");
/* 30:   */  }
/* 31:   */  
/* 33:   */  public void init(GameContainer container)
/* 34:   */    throws SlickException
/* 35:   */  {
/* 36:36 */    this.image = new Image("testdata/logo.png");
/* 37:   */    
/* 38:38 */    this.width = (this.image.getWidth() / 3);
/* 39:39 */    this.height = (this.image.getHeight() / 3);
/* 40:   */    
/* 41:41 */    this.images = new Image[] { this.image.getSubImage(0, 0, this.width, this.height), this.image.getSubImage(this.width, 0, this.width, this.height), this.image.getSubImage(this.width * 2, 0, this.width, this.height), this.image.getSubImage(0, this.height, this.width, this.height), this.image.getSubImage(this.width, this.height, this.width, this.height), this.image.getSubImage(this.width * 2, this.height, this.width, this.height), this.image.getSubImage(0, this.height * 2, this.width, this.height), this.image.getSubImage(this.width, this.height * 2, this.width, this.height), this.image.getSubImage(this.width * 2, this.height * 2, this.width, this.height) };
/* 42:   */    
/* 47:47 */    this.images[0].setColor(2, 0.0F, 1.0F, 1.0F, 1.0F);
/* 48:48 */    this.images[1].setColor(3, 0.0F, 1.0F, 1.0F, 1.0F);
/* 49:49 */    this.images[1].setColor(2, 0.0F, 1.0F, 1.0F, 1.0F);
/* 50:50 */    this.images[2].setColor(3, 0.0F, 1.0F, 1.0F, 1.0F);
/* 51:51 */    this.images[3].setColor(1, 0.0F, 1.0F, 1.0F, 1.0F);
/* 52:52 */    this.images[3].setColor(2, 0.0F, 1.0F, 1.0F, 1.0F);
/* 53:   */    
/* 54:54 */    this.images[4].setColor(1, 0.0F, 1.0F, 1.0F, 1.0F);
/* 55:55 */    this.images[4].setColor(0, 0.0F, 1.0F, 1.0F, 1.0F);
/* 56:56 */    this.images[4].setColor(3, 0.0F, 1.0F, 1.0F, 1.0F);
/* 57:57 */    this.images[4].setColor(2, 0.0F, 1.0F, 1.0F, 1.0F);
/* 58:58 */    this.images[5].setColor(0, 0.0F, 1.0F, 1.0F, 1.0F);
/* 59:59 */    this.images[5].setColor(3, 0.0F, 1.0F, 1.0F, 1.0F);
/* 60:   */    
/* 61:61 */    this.images[6].setColor(1, 0.0F, 1.0F, 1.0F, 1.0F);
/* 62:62 */    this.images[7].setColor(1, 0.0F, 1.0F, 1.0F, 1.0F);
/* 63:63 */    this.images[7].setColor(0, 0.0F, 1.0F, 1.0F, 1.0F);
/* 64:64 */    this.images[8].setColor(0, 0.0F, 1.0F, 1.0F, 1.0F);
/* 65:   */  }
/* 66:   */  
/* 69:   */  public void render(GameContainer container, Graphics g)
/* 70:   */  {
/* 71:71 */    for (int x = 0; x < 3; x++) {
/* 72:72 */      for (int y = 0; y < 3; y++) {
/* 73:73 */        this.images[(x + y * 3)].draw(100 + x * this.width, 100 + y * this.height);
/* 74:   */      }
/* 75:   */    }
/* 76:   */  }
/* 77:   */  
/* 82:   */  public static void main(String[] argv)
/* 83:   */  {
/* 84:84 */    boolean sharedContextTest = false;
/* 85:   */    try
/* 86:   */    {
/* 87:87 */      AppGameContainer container = new AppGameContainer(new ImageCornerTest());
/* 88:88 */      container.setDisplayMode(800, 600, false);
/* 89:89 */      container.start();
/* 90:   */    } catch (SlickException e) {
/* 91:91 */      e.printStackTrace();
/* 92:   */    }
/* 93:   */  }
/* 94:   */  
/* 95:   */  public void update(GameContainer container, int delta)
/* 96:   */    throws SlickException
/* 97:   */  {}
/* 98:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ImageCornerTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */