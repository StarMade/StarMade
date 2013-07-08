/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AngelCodeFont;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.Color;
/*   7:    */import org.newdawn.slick.GameContainer;
/*   8:    */import org.newdawn.slick.Graphics;
/*   9:    */import org.newdawn.slick.Image;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */import org.newdawn.slick.util.Log;
/*  12:    */
/*  21:    */public class FontTest
/*  22:    */  extends BasicGame
/*  23:    */{
/*  24:    */  private AngelCodeFont font;
/*  25:    */  private AngelCodeFont font2;
/*  26:    */  private Image image;
/*  27:    */  private static AppGameContainer container;
/*  28:    */  
/*  29:    */  public FontTest()
/*  30:    */  {
/*  31: 31 */    super("Font Test");
/*  32:    */  }
/*  33:    */  
/*  35:    */  public void init(GameContainer container)
/*  36:    */    throws SlickException
/*  37:    */  {
/*  38: 38 */    this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
/*  39: 39 */    this.font2 = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
/*  40: 40 */    this.image = new Image("testdata/demo2_00.tga", false);
/*  41:    */  }
/*  42:    */  
/*  45:    */  public void render(GameContainer container, Graphics g)
/*  46:    */  {
/*  47: 47 */    this.font.drawString(80.0F, 5.0F, "A Font Example", Color.red);
/*  48: 48 */    this.font.drawString(100.0F, 32.0F, "We - AV - Here is a more complete line that hopefully");
/*  49: 49 */    this.font.drawString(100.0F, 36 + this.font.getHeight("We Here is a more complete line that hopefully"), "will show some kerning.");
/*  50:    */    
/*  52: 52 */    this.font2.drawString(80.0F, 85.0F, "A Font Example", Color.red);
/*  53: 53 */    this.font2.drawString(100.0F, 132.0F, "We - AV - Here is a more complete line that hopefully");
/*  54: 54 */    this.font2.drawString(100.0F, 136 + this.font2.getHeight("We - Here is a more complete line that hopefully"), "will show some kerning.");
/*  55:    */    
/*  56: 56 */    this.image.draw(100.0F, 400.0F);
/*  57:    */    
/*  58: 58 */    String testStr = "Testing Font";
/*  59: 59 */    this.font2.drawString(100.0F, 300.0F, testStr);
/*  60: 60 */    g.setColor(Color.white);
/*  61: 61 */    g.drawRect(100.0F, 300 + this.font2.getYOffset(testStr), this.font2.getWidth(testStr), this.font2.getHeight(testStr) - this.font2.getYOffset(testStr));
/*  62: 62 */    this.font.drawString(500.0F, 300.0F, testStr);
/*  63: 63 */    g.setColor(Color.white);
/*  64: 64 */    g.drawRect(500.0F, 300 + this.font.getYOffset(testStr), this.font.getWidth(testStr), this.font.getHeight(testStr) - this.font.getYOffset(testStr));
/*  65:    */  }
/*  66:    */  
/*  69:    */  public void update(GameContainer container, int delta)
/*  70:    */    throws SlickException
/*  71:    */  {}
/*  72:    */  
/*  75:    */  public void keyPressed(int key, char c)
/*  76:    */  {
/*  77: 77 */    if (key == 1) {
/*  78: 78 */      System.exit(0);
/*  79:    */    }
/*  80: 80 */    if (key == 57) {
/*  81:    */      try {
/*  82: 82 */        container.setDisplayMode(640, 480, false);
/*  83:    */      } catch (SlickException e) {
/*  84: 84 */        Log.error(e);
/*  85:    */      }
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  95:    */  public static void main(String[] argv)
/*  96:    */  {
/*  97:    */    try
/*  98:    */    {
/*  99: 99 */      container = new AppGameContainer(new FontTest());
/* 100:100 */      container.setDisplayMode(800, 600, false);
/* 101:101 */      container.start();
/* 102:    */    } catch (SlickException e) {
/* 103:103 */      e.printStackTrace();
/* 104:    */    }
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.FontTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */