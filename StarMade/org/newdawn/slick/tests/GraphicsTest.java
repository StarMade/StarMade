/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.geom.Polygon;
/*  11:    */import org.newdawn.slick.util.FastTrig;
/*  12:    */
/*  24:    */public class GraphicsTest
/*  25:    */  extends BasicGame
/*  26:    */{
/*  27:    */  private boolean clip;
/*  28:    */  private float ang;
/*  29:    */  private Image image;
/*  30:    */  private Polygon poly;
/*  31:    */  private GameContainer container;
/*  32:    */  
/*  33:    */  public GraphicsTest()
/*  34:    */  {
/*  35: 35 */    super("Graphics Test");
/*  36:    */  }
/*  37:    */  
/*  39:    */  public void init(GameContainer container)
/*  40:    */    throws SlickException
/*  41:    */  {
/*  42: 42 */    this.container = container;
/*  43:    */    
/*  44: 44 */    this.image = new Image("testdata/logo.tga", true);
/*  45:    */    
/*  46: 46 */    Image temp = new Image("testdata/palette_tool.png");
/*  47: 47 */    container.setMouseCursor(temp, 0, 0);
/*  48:    */    
/*  49: 49 */    container.setIcons(new String[] { "testdata/icon.tga" });
/*  50: 50 */    container.setTargetFrameRate(100);
/*  51:    */    
/*  52: 52 */    this.poly = new Polygon();
/*  53: 53 */    float len = 100.0F;
/*  54:    */    
/*  55: 55 */    for (int x = 0; x < 360; x += 30) {
/*  56: 56 */      if (len == 100.0F) {
/*  57: 57 */        len = 50.0F;
/*  58:    */      } else {
/*  59: 59 */        len = 100.0F;
/*  60:    */      }
/*  61: 61 */      this.poly.addPoint((float)FastTrig.cos(Math.toRadians(x)) * len, (float)FastTrig.sin(Math.toRadians(x)) * len);
/*  62:    */    }
/*  63:    */  }
/*  64:    */  
/*  67:    */  public void render(GameContainer container, Graphics g)
/*  68:    */    throws SlickException
/*  69:    */  {
/*  70: 70 */    g.setColor(Color.white);
/*  71:    */    
/*  72: 72 */    g.setAntiAlias(true);
/*  73: 73 */    for (int x = 0; x < 360; x += 10) {
/*  74: 74 */      g.drawLine(700.0F, 100.0F, (int)(700.0D + Math.cos(Math.toRadians(x)) * 100.0D), (int)(100.0D + Math.sin(Math.toRadians(x)) * 100.0D));
/*  75:    */    }
/*  76:    */    
/*  77: 77 */    g.setAntiAlias(false);
/*  78:    */    
/*  79: 79 */    g.setColor(Color.yellow);
/*  80: 80 */    g.drawString("The Graphics Test!", 300.0F, 50.0F);
/*  81: 81 */    g.setColor(Color.white);
/*  82: 82 */    g.drawString("Space - Toggles clipping", 400.0F, 80.0F);
/*  83: 83 */    g.drawString("Frame rate capped to 100", 400.0F, 120.0F);
/*  84:    */    
/*  85: 85 */    if (this.clip) {
/*  86: 86 */      g.setColor(Color.gray);
/*  87: 87 */      g.drawRect(100.0F, 260.0F, 400.0F, 100.0F);
/*  88: 88 */      g.setClip(100, 260, 400, 100);
/*  89:    */    }
/*  90:    */    
/*  91: 91 */    g.setColor(Color.yellow);
/*  92: 92 */    g.translate(100.0F, 120.0F);
/*  93: 93 */    g.fill(this.poly);
/*  94: 94 */    g.setColor(Color.blue);
/*  95: 95 */    g.setLineWidth(3.0F);
/*  96: 96 */    g.draw(this.poly);
/*  97: 97 */    g.setLineWidth(1.0F);
/*  98: 98 */    g.translate(0.0F, 230.0F);
/*  99: 99 */    g.draw(this.poly);
/* 100:100 */    g.resetTransform();
/* 101:    */    
/* 102:102 */    g.setColor(Color.magenta);
/* 103:103 */    g.drawRoundRect(10.0F, 10.0F, 100.0F, 100.0F, 10);
/* 104:104 */    g.fillRoundRect(10.0F, 210.0F, 100.0F, 100.0F, 10);
/* 105:    */    
/* 106:106 */    g.rotate(400.0F, 300.0F, this.ang);
/* 107:107 */    g.setColor(Color.green);
/* 108:108 */    g.drawRect(200.0F, 200.0F, 200.0F, 200.0F);
/* 109:109 */    g.setColor(Color.blue);
/* 110:110 */    g.fillRect(250.0F, 250.0F, 100.0F, 100.0F);
/* 111:    */    
/* 112:112 */    g.drawImage(this.image, 300.0F, 270.0F);
/* 113:    */    
/* 114:114 */    g.setColor(Color.red);
/* 115:115 */    g.drawOval(100.0F, 100.0F, 200.0F, 200.0F);
/* 116:116 */    g.setColor(Color.red.darker());
/* 117:117 */    g.fillOval(300.0F, 300.0F, 150.0F, 100.0F);
/* 118:118 */    g.setAntiAlias(true);
/* 119:119 */    g.setColor(Color.white);
/* 120:120 */    g.setLineWidth(5.0F);
/* 121:121 */    g.drawOval(300.0F, 300.0F, 150.0F, 100.0F);
/* 122:122 */    g.setAntiAlias(true);
/* 123:123 */    g.resetTransform();
/* 124:    */    
/* 125:125 */    if (this.clip) {
/* 126:126 */      g.clearClip();
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 132:    */  public void update(GameContainer container, int delta)
/* 133:    */  {
/* 134:134 */    this.ang += delta * 0.1F;
/* 135:    */  }
/* 136:    */  
/* 139:    */  public void keyPressed(int key, char c)
/* 140:    */  {
/* 141:141 */    if (key == 1) {
/* 142:142 */      System.exit(0);
/* 143:    */    }
/* 144:144 */    if (key == 57) {
/* 145:145 */      this.clip = (!this.clip);
/* 146:    */    }
/* 147:    */  }
/* 148:    */  
/* 152:    */  public static void main(String[] argv)
/* 153:    */  {
/* 154:    */    try
/* 155:    */    {
/* 156:156 */      AppGameContainer container = new AppGameContainer(new GraphicsTest());
/* 157:157 */      container.setDisplayMode(800, 600, false);
/* 158:158 */      container.start();
/* 159:    */    } catch (SlickException e) {
/* 160:160 */      e.printStackTrace();
/* 161:    */    }
/* 162:    */  }
/* 163:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.GraphicsTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */