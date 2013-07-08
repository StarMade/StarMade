/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.SlickException;
/*   9:    */
/*  15:    */public class TransformTest2
/*  16:    */  extends BasicGame
/*  17:    */{
/*  18: 18 */  private float scale = 1.0F;
/*  19:    */  
/*  21:    */  private boolean scaleUp;
/*  22:    */  
/*  23:    */  private boolean scaleDown;
/*  24:    */  
/*  25: 25 */  private float camX = 320.0F;
/*  26:    */  
/*  27: 27 */  private float camY = 240.0F;
/*  28:    */  
/*  30:    */  private boolean moveLeft;
/*  31:    */  
/*  33:    */  private boolean moveUp;
/*  34:    */  
/*  35:    */  private boolean moveRight;
/*  36:    */  
/*  37:    */  private boolean moveDown;
/*  38:    */  
/*  40:    */  public TransformTest2()
/*  41:    */  {
/*  42: 42 */    super("Transform Test");
/*  43:    */  }
/*  44:    */  
/*  46:    */  public void init(GameContainer container)
/*  47:    */    throws SlickException
/*  48:    */  {
/*  49: 49 */    container.setTargetFrameRate(100);
/*  50:    */  }
/*  51:    */  
/*  54:    */  public void render(GameContainer contiainer, Graphics g)
/*  55:    */  {
/*  56: 56 */    g.translate(320.0F, 240.0F);
/*  57:    */    
/*  58: 58 */    g.translate(-this.camX * this.scale, -this.camY * this.scale);
/*  59:    */    
/*  61: 61 */    g.scale(this.scale, this.scale);
/*  62:    */    
/*  63: 63 */    g.setColor(Color.red);
/*  64: 64 */    for (int x = 0; x < 10; x++) {
/*  65: 65 */      for (int y = 0; y < 10; y++) {
/*  66: 66 */        g.fillRect(-500 + x * 100, -500 + y * 100, 80.0F, 80.0F);
/*  67:    */      }
/*  68:    */    }
/*  69:    */    
/*  70: 70 */    g.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
/*  71: 71 */    g.fillRect(-320.0F, -240.0F, 640.0F, 480.0F);
/*  72: 72 */    g.setColor(Color.white);
/*  73: 73 */    g.drawRect(-320.0F, -240.0F, 640.0F, 480.0F);
/*  74:    */  }
/*  75:    */  
/*  78:    */  public void update(GameContainer container, int delta)
/*  79:    */  {
/*  80: 80 */    if (this.scaleUp) {
/*  81: 81 */      this.scale += delta * 0.001F;
/*  82:    */    }
/*  83: 83 */    if (this.scaleDown) {
/*  84: 84 */      this.scale -= delta * 0.001F;
/*  85:    */    }
/*  86:    */    
/*  87: 87 */    float moveSpeed = delta * 0.4F * (1.0F / this.scale);
/*  88:    */    
/*  89: 89 */    if (this.moveLeft) {
/*  90: 90 */      this.camX -= moveSpeed;
/*  91:    */    }
/*  92: 92 */    if (this.moveUp) {
/*  93: 93 */      this.camY -= moveSpeed;
/*  94:    */    }
/*  95: 95 */    if (this.moveRight) {
/*  96: 96 */      this.camX += moveSpeed;
/*  97:    */    }
/*  98: 98 */    if (this.moveDown) {
/*  99: 99 */      this.camY += moveSpeed;
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 105:    */  public void keyPressed(int key, char c)
/* 106:    */  {
/* 107:107 */    if (key == 1) {
/* 108:108 */      System.exit(0);
/* 109:    */    }
/* 110:110 */    if (key == 16) {
/* 111:111 */      this.scaleUp = true;
/* 112:    */    }
/* 113:113 */    if (key == 30) {
/* 114:114 */      this.scaleDown = true;
/* 115:    */    }
/* 116:    */    
/* 117:117 */    if (key == 203) {
/* 118:118 */      this.moveLeft = true;
/* 119:    */    }
/* 120:120 */    if (key == 200) {
/* 121:121 */      this.moveUp = true;
/* 122:    */    }
/* 123:123 */    if (key == 205) {
/* 124:124 */      this.moveRight = true;
/* 125:    */    }
/* 126:126 */    if (key == 208) {
/* 127:127 */      this.moveDown = true;
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 133:    */  public void keyReleased(int key, char c)
/* 134:    */  {
/* 135:135 */    if (key == 16) {
/* 136:136 */      this.scaleUp = false;
/* 137:    */    }
/* 138:138 */    if (key == 30) {
/* 139:139 */      this.scaleDown = false;
/* 140:    */    }
/* 141:    */    
/* 142:142 */    if (key == 203) {
/* 143:143 */      this.moveLeft = false;
/* 144:    */    }
/* 145:145 */    if (key == 200) {
/* 146:146 */      this.moveUp = false;
/* 147:    */    }
/* 148:148 */    if (key == 205) {
/* 149:149 */      this.moveRight = false;
/* 150:    */    }
/* 151:151 */    if (key == 208) {
/* 152:152 */      this.moveDown = false;
/* 153:    */    }
/* 154:    */  }
/* 155:    */  
/* 159:    */  public static void main(String[] argv)
/* 160:    */  {
/* 161:    */    try
/* 162:    */    {
/* 163:163 */      AppGameContainer container = new AppGameContainer(new TransformTest2());
/* 164:164 */      container.setDisplayMode(640, 480, false);
/* 165:165 */      container.start();
/* 166:    */    } catch (SlickException e) {
/* 167:167 */      e.printStackTrace();
/* 168:    */    }
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.TransformTest2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */