/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */import org.newdawn.slick.AngelCodeFont;
/*   5:    */import org.newdawn.slick.AppGameContainer;
/*   6:    */import org.newdawn.slick.BasicGame;
/*   7:    */import org.newdawn.slick.Color;
/*   8:    */import org.newdawn.slick.GameContainer;
/*   9:    */import org.newdawn.slick.Graphics;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */
/*  19:    */public class DistanceFieldTest
/*  20:    */  extends BasicGame
/*  21:    */{
/*  22:    */  private AngelCodeFont font;
/*  23:    */  
/*  24:    */  public DistanceFieldTest()
/*  25:    */  {
/*  26: 26 */    super("DistanceMapTest Test");
/*  27:    */  }
/*  28:    */  
/*  30:    */  public void init(GameContainer container)
/*  31:    */    throws SlickException
/*  32:    */  {
/*  33: 33 */    this.font = new AngelCodeFont("testdata/distance.fnt", "testdata/distance-dis.png");
/*  34: 34 */    container.getGraphics().setBackground(Color.black);
/*  35:    */  }
/*  36:    */  
/*  40:    */  public void update(GameContainer container, int delta)
/*  41:    */    throws SlickException
/*  42:    */  {}
/*  43:    */  
/*  46:    */  public void render(GameContainer container, Graphics g)
/*  47:    */    throws SlickException
/*  48:    */  {
/*  49: 49 */    String text = "abc";
/*  50: 50 */    this.font.drawString(610.0F, 100.0F, text);
/*  51:    */    
/*  52: 52 */    GL11.glDisable(3042);
/*  53: 53 */    GL11.glEnable(3008);
/*  54: 54 */    GL11.glAlphaFunc(518, 0.5F);
/*  55: 55 */    this.font.drawString(610.0F, 150.0F, text);
/*  56: 56 */    GL11.glDisable(3008);
/*  57: 57 */    GL11.glEnable(3042);
/*  58:    */    
/*  59: 59 */    g.translate(-50.0F, -130.0F);
/*  60: 60 */    g.scale(10.0F, 10.0F);
/*  61: 61 */    this.font.drawString(0.0F, 0.0F, text);
/*  62:    */    
/*  63: 63 */    GL11.glDisable(3042);
/*  64: 64 */    GL11.glEnable(3008);
/*  65: 65 */    GL11.glAlphaFunc(518, 0.5F);
/*  66: 66 */    this.font.drawString(0.0F, 26.0F, text);
/*  67: 67 */    GL11.glDisable(3008);
/*  68: 68 */    GL11.glEnable(3042);
/*  69:    */    
/*  70: 70 */    g.resetTransform();
/*  71: 71 */    g.setColor(Color.lightGray);
/*  72: 72 */    g.drawString("Original Size on Sheet", 620.0F, 210.0F);
/*  73: 73 */    g.drawString("10x Scale Up", 40.0F, 575.0F);
/*  74:    */    
/*  75: 75 */    g.setColor(Color.darkGray);
/*  76: 76 */    g.drawRect(40.0F, 40.0F, 560.0F, 530.0F);
/*  77: 77 */    g.drawRect(610.0F, 105.0F, 150.0F, 100.0F);
/*  78:    */    
/*  79: 79 */    g.setColor(Color.white);
/*  80: 80 */    g.drawString("512x512 Font Sheet", 620.0F, 300.0F);
/*  81: 81 */    g.drawString("NEHE Charset", 620.0F, 320.0F);
/*  82: 82 */    g.drawString("4096x4096 (8x) Source Image", 620.0F, 340.0F);
/*  83: 83 */    g.drawString("ScanSize = 20", 620.0F, 360.0F);
/*  84:    */  }
/*  85:    */  
/*  90:    */  public void keyPressed(int key, char c) {}
/*  91:    */  
/*  95:    */  public static void main(String[] argv)
/*  96:    */  {
/*  97:    */    try
/*  98:    */    {
/*  99: 99 */      AppGameContainer container = new AppGameContainer(new DistanceFieldTest());
/* 100:100 */      container.setDisplayMode(800, 600, false);
/* 101:101 */      container.start();
/* 102:    */    } catch (SlickException e) {
/* 103:103 */      e.printStackTrace();
/* 104:    */    }
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.DistanceFieldTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */