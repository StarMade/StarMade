/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.newdawn.slick.AngelCodeFont;
/*   5:    */import org.newdawn.slick.AppGameContainer;
/*   6:    */import org.newdawn.slick.BasicGame;
/*   7:    */import org.newdawn.slick.Color;
/*   8:    */import org.newdawn.slick.Font;
/*   9:    */import org.newdawn.slick.GameContainer;
/*  10:    */import org.newdawn.slick.Graphics;
/*  11:    */import org.newdawn.slick.Image;
/*  12:    */import org.newdawn.slick.SlickException;
/*  13:    */import org.newdawn.slick.opengl.pbuffer.GraphicsFactory;
/*  14:    */
/*  26:    */public class ImageGraphicsTest
/*  27:    */  extends BasicGame
/*  28:    */{
/*  29:    */  private Image preloaded;
/*  30:    */  private Image target;
/*  31:    */  private Image cut;
/*  32:    */  private Graphics gTarget;
/*  33:    */  private Graphics offscreenPreload;
/*  34:    */  private Image testImage;
/*  35:    */  private Font testFont;
/*  36:    */  private float ang;
/*  37: 37 */  private String using = "none";
/*  38:    */  
/*  41:    */  public ImageGraphicsTest()
/*  42:    */  {
/*  43: 43 */    super("Image Graphics Test");
/*  44:    */  }
/*  45:    */  
/*  47:    */  public void init(GameContainer container)
/*  48:    */    throws SlickException
/*  49:    */  {
/*  50: 50 */    this.testImage = new Image("testdata/logo.png");
/*  51: 51 */    this.preloaded = new Image("testdata/logo.png");
/*  52: 52 */    this.testFont = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
/*  53: 53 */    this.target = new Image(400, 300);
/*  54: 54 */    this.cut = new Image(100, 100);
/*  55: 55 */    this.gTarget = this.target.getGraphics();
/*  56: 56 */    this.offscreenPreload = this.preloaded.getGraphics();
/*  57:    */    
/*  58: 58 */    this.offscreenPreload.drawString("Drawing over a loaded image", 5.0F, 15.0F);
/*  59: 59 */    this.offscreenPreload.setLineWidth(5.0F);
/*  60: 60 */    this.offscreenPreload.setAntiAlias(true);
/*  61: 61 */    this.offscreenPreload.setColor(Color.blue.brighter());
/*  62: 62 */    this.offscreenPreload.drawOval(200.0F, 30.0F, 50.0F, 50.0F);
/*  63: 63 */    this.offscreenPreload.setColor(Color.white);
/*  64: 64 */    this.offscreenPreload.drawRect(190.0F, 20.0F, 70.0F, 70.0F);
/*  65: 65 */    this.offscreenPreload.flush();
/*  66:    */    
/*  67: 67 */    if (GraphicsFactory.usingFBO()) {
/*  68: 68 */      this.using = "FBO (Frame Buffer Objects)";
/*  69: 69 */    } else if (GraphicsFactory.usingPBuffer()) {
/*  70: 70 */      this.using = "Pbuffer (Pixel Buffers)";
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    System.out.println(this.preloaded.getColor(50, 50));
/*  74:    */  }
/*  75:    */  
/*  80:    */  public void render(GameContainer container, Graphics g)
/*  81:    */    throws SlickException
/*  82:    */  {
/*  83: 83 */    this.gTarget.setBackground(new Color(0, 0, 0, 0));
/*  84: 84 */    this.gTarget.clear();
/*  85: 85 */    this.gTarget.rotate(200.0F, 160.0F, this.ang);
/*  86: 86 */    this.gTarget.setFont(this.testFont);
/*  87: 87 */    this.gTarget.fillRect(10.0F, 10.0F, 50.0F, 50.0F);
/*  88: 88 */    this.gTarget.drawString("HELLO WORLD", 10.0F, 10.0F);
/*  89:    */    
/*  90: 90 */    this.gTarget.drawImage(this.testImage, 100.0F, 150.0F);
/*  91: 91 */    this.gTarget.drawImage(this.testImage, 100.0F, 50.0F);
/*  92: 92 */    this.gTarget.drawImage(this.testImage, 50.0F, 75.0F);
/*  93:    */    
/*  96: 96 */    this.gTarget.flush();
/*  97:    */    
/*  98: 98 */    g.setColor(Color.red);
/*  99: 99 */    g.fillRect(250.0F, 50.0F, 200.0F, 200.0F);
/* 100:    */    
/* 102:102 */    this.target.draw(300.0F, 100.0F);
/* 103:103 */    this.target.draw(300.0F, 410.0F, 200.0F, 150.0F);
/* 104:104 */    this.target.draw(505.0F, 410.0F, 100.0F, 75.0F);
/* 105:    */    
/* 108:108 */    g.setColor(Color.white);
/* 109:109 */    g.drawString("Testing On Offscreen Buffer", 300.0F, 80.0F);
/* 110:110 */    g.setColor(Color.green);
/* 111:111 */    g.drawRect(300.0F, 100.0F, this.target.getWidth(), this.target.getHeight());
/* 112:112 */    g.drawRect(300.0F, 410.0F, this.target.getWidth() / 2, this.target.getHeight() / 2);
/* 113:113 */    g.drawRect(505.0F, 410.0F, this.target.getWidth() / 4, this.target.getHeight() / 4);
/* 114:    */    
/* 118:118 */    g.setColor(Color.white);
/* 119:119 */    g.drawString("Testing Font On Back Buffer", 10.0F, 100.0F);
/* 120:120 */    g.drawString("Using: " + this.using, 10.0F, 580.0F);
/* 121:121 */    g.setColor(Color.red);
/* 122:122 */    g.fillRect(10.0F, 120.0F, 200.0F, 5.0F);
/* 123:    */    
/* 125:125 */    int xp = (int)(60.0D + Math.sin(this.ang / 60.0F) * 50.0D);
/* 126:126 */    g.copyArea(this.cut, xp, 50);
/* 127:    */    
/* 130:130 */    this.cut.draw(30.0F, 250.0F);
/* 131:131 */    g.setColor(Color.white);
/* 132:132 */    g.drawRect(30.0F, 250.0F, this.cut.getWidth(), this.cut.getHeight());
/* 133:133 */    g.setColor(Color.gray);
/* 134:134 */    g.drawRect(xp, 50.0F, this.cut.getWidth(), this.cut.getHeight());
/* 135:    */    
/* 139:139 */    this.preloaded.draw(2.0F, 400.0F);
/* 140:140 */    g.setColor(Color.blue);
/* 141:141 */    g.drawRect(2.0F, 400.0F, this.preloaded.getWidth(), this.preloaded.getHeight());
/* 142:    */  }
/* 143:    */  
/* 146:    */  public void update(GameContainer container, int delta)
/* 147:    */  {
/* 148:148 */    this.ang += delta * 0.1F;
/* 149:    */  }
/* 150:    */  
/* 154:    */  public static void main(String[] argv)
/* 155:    */  {
/* 156:    */    try
/* 157:    */    {
/* 158:158 */      GraphicsFactory.setUseFBO(false);
/* 159:    */      
/* 160:160 */      AppGameContainer container = new AppGameContainer(new ImageGraphicsTest());
/* 161:161 */      container.setDisplayMode(800, 600, false);
/* 162:162 */      container.start();
/* 163:    */    } catch (SlickException e) {
/* 164:164 */      e.printStackTrace();
/* 165:    */    }
/* 166:    */  }
/* 167:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ImageGraphicsTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */