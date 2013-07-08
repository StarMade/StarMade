/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.Image;
/*  9:   */import org.newdawn.slick.Input;
/* 10:   */import org.newdawn.slick.SlickException;
/* 11:   */
/* 16:   */public class ImageReadTest
/* 17:   */  extends BasicGame
/* 18:   */{
/* 19:   */  private Image image;
/* 20:20 */  private Color[] read = new Color[6];
/* 21:   */  
/* 23:   */  private Graphics g;
/* 24:   */  
/* 26:   */  public ImageReadTest()
/* 27:   */  {
/* 28:28 */    super("Image Read Test");
/* 29:   */  }
/* 30:   */  
/* 32:   */  public void init(GameContainer container)
/* 33:   */    throws SlickException
/* 34:   */  {
/* 35:35 */    this.image = new Image("testdata/testcard.png");
/* 36:36 */    this.read[0] = this.image.getColor(0, 0);
/* 37:37 */    this.read[1] = this.image.getColor(30, 40);
/* 38:38 */    this.read[2] = this.image.getColor(55, 70);
/* 39:39 */    this.read[3] = this.image.getColor(80, 90);
/* 40:   */  }
/* 41:   */  
/* 44:   */  public void render(GameContainer container, Graphics g)
/* 45:   */  {
/* 46:46 */    this.g = g;
/* 47:   */    
/* 48:48 */    this.image.draw(100.0F, 100.0F);
/* 49:49 */    g.setColor(Color.white);
/* 50:50 */    g.drawString("Move mouse over test image", 200.0F, 20.0F);
/* 51:51 */    g.setColor(this.read[0]);
/* 52:52 */    g.drawString(this.read[0].toString(), 100.0F, 300.0F);
/* 53:53 */    g.setColor(this.read[1]);
/* 54:54 */    g.drawString(this.read[1].toString(), 150.0F, 320.0F);
/* 55:55 */    g.setColor(this.read[2]);
/* 56:56 */    g.drawString(this.read[2].toString(), 200.0F, 340.0F);
/* 57:57 */    g.setColor(this.read[3]);
/* 58:58 */    g.drawString(this.read[3].toString(), 250.0F, 360.0F);
/* 59:59 */    if (this.read[4] != null) {
/* 60:60 */      g.setColor(this.read[4]);
/* 61:61 */      g.drawString("On image: " + this.read[4].toString(), 100.0F, 250.0F);
/* 62:   */    }
/* 63:63 */    if (this.read[5] != null) {
/* 64:64 */      g.setColor(Color.white);
/* 65:65 */      g.drawString("On screen: " + this.read[5].toString(), 100.0F, 270.0F);
/* 66:   */    }
/* 67:   */  }
/* 68:   */  
/* 71:   */  public void update(GameContainer container, int delta)
/* 72:   */  {
/* 73:73 */    int mx = container.getInput().getMouseX();
/* 74:74 */    int my = container.getInput().getMouseY();
/* 75:   */    
/* 76:76 */    if ((mx >= 100) && (my >= 100) && (mx < 200) && (my < 200)) {
/* 77:77 */      this.read[4] = this.image.getColor(mx - 100, my - 100);
/* 78:   */    } else {
/* 79:79 */      this.read[4] = Color.black;
/* 80:   */    }
/* 81:   */    
/* 82:82 */    this.read[5] = this.g.getPixel(mx, my);
/* 83:   */  }
/* 84:   */  
/* 88:   */  public static void main(String[] argv)
/* 89:   */  {
/* 90:   */    try
/* 91:   */    {
/* 92:92 */      AppGameContainer container = new AppGameContainer(new ImageReadTest());
/* 93:93 */      container.setDisplayMode(800, 600, false);
/* 94:94 */      container.start();
/* 95:   */    } catch (SlickException e) {
/* 96:96 */      e.printStackTrace();
/* 97:   */    }
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ImageReadTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */