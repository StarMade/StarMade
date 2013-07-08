/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */
/*  23:    */public class ImageTest
/*  24:    */  extends BasicGame
/*  25:    */{
/*  26:    */  private Image tga;
/*  27:    */  private Image scaleMe;
/*  28:    */  private Image scaled;
/*  29:    */  private Image gif;
/*  30:    */  private Image image;
/*  31:    */  private Image subImage;
/*  32:    */  private Image rotImage;
/*  33:    */  private float rot;
/*  34: 34 */  public static boolean exitMe = true;
/*  35:    */  
/*  38:    */  public ImageTest()
/*  39:    */  {
/*  40: 40 */    super("Image Test");
/*  41:    */  }
/*  42:    */  
/*  44:    */  public void init(GameContainer container)
/*  45:    */    throws SlickException
/*  46:    */  {
/*  47: 47 */    this.image = (this.tga = new Image("testdata/logo.png"));
/*  48: 48 */    this.rotImage = new Image("testdata/logo.png");
/*  49: 49 */    this.rotImage = this.rotImage.getScaledCopy(this.rotImage.getWidth() / 2, this.rotImage.getHeight() / 2);
/*  50:    */    
/*  52: 52 */    this.scaleMe = new Image("testdata/logo.tga", true, 2);
/*  53: 53 */    this.gif = new Image("testdata/logo.gif");
/*  54: 54 */    this.gif.destroy();
/*  55: 55 */    this.gif = new Image("testdata/logo.gif");
/*  56: 56 */    this.scaled = this.gif.getScaledCopy(120, 120);
/*  57: 57 */    this.subImage = this.image.getSubImage(200, 0, 70, 260);
/*  58: 58 */    this.rot = 0.0F;
/*  59:    */    
/*  60: 60 */    if (exitMe) {
/*  61: 61 */      container.exit();
/*  62:    */    }
/*  63:    */    
/*  64: 64 */    Image test = this.tga.getSubImage(50, 50, 50, 50);
/*  65: 65 */    System.out.println(test.getColor(50, 50));
/*  66:    */  }
/*  67:    */  
/*  70:    */  public void render(GameContainer container, Graphics g)
/*  71:    */  {
/*  72: 72 */    g.drawRect(0.0F, 0.0F, this.image.getWidth(), this.image.getHeight());
/*  73: 73 */    this.image.draw(0.0F, 0.0F);
/*  74: 74 */    this.image.draw(500.0F, 0.0F, 200.0F, 100.0F);
/*  75: 75 */    this.scaleMe.draw(500.0F, 100.0F, 200.0F, 100.0F);
/*  76: 76 */    this.scaled.draw(400.0F, 500.0F);
/*  77: 77 */    Image flipped = this.scaled.getFlippedCopy(true, false);
/*  78: 78 */    flipped.draw(520.0F, 500.0F);
/*  79: 79 */    Image flipped2 = flipped.getFlippedCopy(false, true);
/*  80: 80 */    flipped2.draw(520.0F, 380.0F);
/*  81: 81 */    Image flipped3 = flipped2.getFlippedCopy(true, false);
/*  82: 82 */    flipped3.draw(400.0F, 380.0F);
/*  83:    */    
/*  84: 84 */    for (int i = 0; i < 3; i++) {
/*  85: 85 */      this.subImage.draw(200 + i * 30, 300.0F);
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    g.translate(500.0F, 200.0F);
/*  89: 89 */    g.rotate(50.0F, 50.0F, this.rot);
/*  90: 90 */    g.scale(0.3F, 0.3F);
/*  91: 91 */    this.image.draw();
/*  92: 92 */    g.resetTransform();
/*  93:    */    
/*  94: 94 */    this.rotImage.setRotation(this.rot);
/*  95: 95 */    this.rotImage.draw(100.0F, 200.0F);
/*  96:    */  }
/*  97:    */  
/* 100:    */  public void update(GameContainer container, int delta)
/* 101:    */  {
/* 102:102 */    this.rot += delta * 0.1F;
/* 103:103 */    if (this.rot > 360.0F) {
/* 104:104 */      this.rot -= 360.0F;
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 112:    */  public static void main(String[] argv)
/* 113:    */  {
/* 114:114 */    boolean sharedContextTest = false;
/* 115:    */    try
/* 116:    */    {
/* 117:117 */      exitMe = false;
/* 118:118 */      if (sharedContextTest) {
/* 119:119 */        GameContainer.enableSharedContext();
/* 120:120 */        exitMe = true;
/* 121:    */      }
/* 122:    */      
/* 123:123 */      AppGameContainer container = new AppGameContainer(new ImageTest());
/* 124:124 */      container.setForceExit(!sharedContextTest);
/* 125:125 */      container.setDisplayMode(800, 600, false);
/* 126:126 */      container.start();
/* 127:    */      
/* 128:128 */      if (sharedContextTest) {
/* 129:129 */        System.out.println("Exit first instance");
/* 130:130 */        exitMe = false;
/* 131:131 */        container = new AppGameContainer(new ImageTest());
/* 132:132 */        container.setDisplayMode(800, 600, false);
/* 133:133 */        container.start();
/* 134:    */      }
/* 135:    */    } catch (SlickException e) {
/* 136:136 */      e.printStackTrace();
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 143:    */  public void keyPressed(int key, char c)
/* 144:    */  {
/* 145:145 */    if (key == 57) {
/* 146:146 */      if (this.image == this.gif) {
/* 147:147 */        this.image = this.tga;
/* 148:    */      } else {
/* 149:149 */        this.image = this.gif;
/* 150:    */      }
/* 151:    */    }
/* 152:    */  }
/* 153:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ImageTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */