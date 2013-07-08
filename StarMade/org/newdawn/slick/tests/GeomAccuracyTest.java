/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.Input;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */import org.newdawn.slick.geom.Ellipse;
/*  12:    */import org.newdawn.slick.geom.Rectangle;
/*  13:    */import org.newdawn.slick.geom.RoundedRectangle;
/*  14:    */
/*  35:    */public class GeomAccuracyTest
/*  36:    */  extends BasicGame
/*  37:    */{
/*  38:    */  private GameContainer container;
/*  39:    */  private Color geomColor;
/*  40:    */  private Color overlayColor;
/*  41:    */  private boolean hideOverlay;
/*  42:    */  private int colorIndex;
/*  43:    */  private int curTest;
/*  44:    */  private static final int NUMTESTS = 3;
/*  45:    */  private Image magImage;
/*  46:    */  
/*  47:    */  public GeomAccuracyTest()
/*  48:    */  {
/*  49: 49 */    super("Geometry Accuracy Tests");
/*  50:    */  }
/*  51:    */  
/*  53:    */  public void init(GameContainer container)
/*  54:    */    throws SlickException
/*  55:    */  {
/*  56: 56 */    this.container = container;
/*  57:    */    
/*  58: 58 */    this.geomColor = Color.magenta;
/*  59: 59 */    this.overlayColor = Color.white;
/*  60:    */    
/*  61: 61 */    this.magImage = new Image(21, 21);
/*  62:    */  }
/*  63:    */  
/*  67:    */  public void render(GameContainer container, Graphics g)
/*  68:    */  {
/*  69: 69 */    String text = new String();
/*  70:    */    
/*  71: 71 */    switch (this.curTest)
/*  72:    */    {
/*  73:    */    case 0: 
/*  74: 74 */      text = "Rectangles";
/*  75: 75 */      rectTest(g);
/*  76: 76 */      break;
/*  77:    */    
/*  78:    */    case 1: 
/*  79: 79 */      text = "Ovals";
/*  80: 80 */      ovalTest(g);
/*  81: 81 */      break;
/*  82:    */    
/*  83:    */    case 2: 
/*  84: 84 */      text = "Arcs";
/*  85: 85 */      arcTest(g);
/*  86:    */    }
/*  87:    */    
/*  88:    */    
/*  89: 89 */    g.setColor(Color.white);
/*  90: 90 */    g.drawString("Press T to toggle overlay", 200.0F, 55.0F);
/*  91: 91 */    g.drawString("Press N to switch tests", 200.0F, 35.0F);
/*  92: 92 */    g.drawString("Press C to cycle drawing colors", 200.0F, 15.0F);
/*  93: 93 */    g.drawString("Current Test:", 400.0F, 35.0F);
/*  94: 94 */    g.setColor(Color.blue);
/*  95: 95 */    g.drawString(text, 485.0F, 35.0F);
/*  96:    */    
/*  97: 97 */    g.setColor(Color.white);
/*  98: 98 */    g.drawString("Normal:", 10.0F, 150.0F);
/*  99: 99 */    g.drawString("Filled:", 10.0F, 300.0F);
/* 100:    */    
/* 101:101 */    g.drawString("Drawn with Graphics context", 125.0F, 400.0F);
/* 102:102 */    g.drawString("Drawn using Shapes", 450.0F, 400.0F);
/* 103:    */    
/* 105:105 */    g.copyArea(this.magImage, container.getInput().getMouseX() - 10, container.getInput().getMouseY() - 10);
/* 106:106 */    this.magImage.draw(351.0F, 451.0F, 5.0F);
/* 107:107 */    g.drawString("Mag Area -", 250.0F, 475.0F);
/* 108:108 */    g.setColor(Color.darkGray);
/* 109:109 */    g.drawRect(350.0F, 450.0F, 106.0F, 106.0F);
/* 110:    */    
/* 111:111 */    g.setColor(Color.white);
/* 112:112 */    g.drawString("NOTE:", 500.0F, 450.0F);
/* 113:113 */    g.drawString("lines should be flush with edges", 525.0F, 470.0F);
/* 114:114 */    g.drawString("corners should be symetric", 525.0F, 490.0F);
/* 115:    */  }
/* 116:    */  
/* 122:    */  void arcTest(Graphics g)
/* 123:    */  {
/* 124:124 */    if (!this.hideOverlay) {
/* 125:125 */      g.setColor(this.overlayColor);
/* 126:126 */      g.drawLine(198.0F, 100.0F, 198.0F, 198.0F);
/* 127:127 */      g.drawLine(100.0F, 198.0F, 198.0F, 198.0F);
/* 128:    */    }
/* 129:    */    
/* 130:130 */    g.setColor(this.geomColor);
/* 131:131 */    g.drawArc(100.0F, 100.0F, 99.0F, 99.0F, 0.0F, 90.0F);
/* 132:    */  }
/* 133:    */  
/* 140:    */  void ovalTest(Graphics g)
/* 141:    */  {
/* 142:142 */    g.setColor(this.geomColor);
/* 143:143 */    g.drawOval(100.0F, 100.0F, 99.0F, 99.0F);
/* 144:144 */    g.fillOval(100.0F, 250.0F, 99.0F, 99.0F);
/* 145:    */    
/* 147:147 */    Ellipse elip = new Ellipse(449.0F, 149.0F, 49.0F, 49.0F);
/* 148:148 */    g.draw(elip);
/* 149:149 */    elip = new Ellipse(449.0F, 299.0F, 49.0F, 49.0F);
/* 150:150 */    g.fill(elip);
/* 151:    */    
/* 152:152 */    if (!this.hideOverlay) {
/* 153:153 */      g.setColor(this.overlayColor);
/* 154:154 */      g.drawLine(100.0F, 149.0F, 198.0F, 149.0F);
/* 155:155 */      g.drawLine(149.0F, 100.0F, 149.0F, 198.0F);
/* 156:    */      
/* 157:157 */      g.drawLine(100.0F, 299.0F, 198.0F, 299.0F);
/* 158:158 */      g.drawLine(149.0F, 250.0F, 149.0F, 348.0F);
/* 159:    */      
/* 160:160 */      g.drawLine(400.0F, 149.0F, 498.0F, 149.0F);
/* 161:161 */      g.drawLine(449.0F, 100.0F, 449.0F, 198.0F);
/* 162:    */      
/* 163:163 */      g.drawLine(400.0F, 299.0F, 498.0F, 299.0F);
/* 164:164 */      g.drawLine(449.0F, 250.0F, 449.0F, 348.0F);
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 174:    */  void rectTest(Graphics g)
/* 175:    */  {
/* 176:176 */    g.setColor(this.geomColor);
/* 177:    */    
/* 179:179 */    g.drawRect(100.0F, 100.0F, 99.0F, 99.0F);
/* 180:180 */    g.fillRect(100.0F, 250.0F, 99.0F, 99.0F);
/* 181:    */    
/* 182:182 */    g.drawRoundRect(250.0F, 100.0F, 99.0F, 99.0F, 10);
/* 183:183 */    g.fillRoundRect(250.0F, 250.0F, 99.0F, 99.0F, 10);
/* 184:    */    
/* 186:186 */    Rectangle rect = new Rectangle(400.0F, 100.0F, 99.0F, 99.0F);
/* 187:187 */    g.draw(rect);
/* 188:188 */    rect = new Rectangle(400.0F, 250.0F, 99.0F, 99.0F);
/* 189:189 */    g.fill(rect);
/* 190:    */    
/* 191:191 */    RoundedRectangle rrect = new RoundedRectangle(550.0F, 100.0F, 99.0F, 99.0F, 10.0F);
/* 192:192 */    g.draw(rrect);
/* 193:193 */    rrect = new RoundedRectangle(550.0F, 250.0F, 99.0F, 99.0F, 10.0F);
/* 194:194 */    g.fill(rrect);
/* 195:    */    
/* 197:197 */    if (!this.hideOverlay) {
/* 198:198 */      g.setColor(this.overlayColor);
/* 199:    */      
/* 201:201 */      g.drawLine(100.0F, 149.0F, 198.0F, 149.0F);
/* 202:202 */      g.drawLine(149.0F, 100.0F, 149.0F, 198.0F);
/* 203:    */      
/* 204:204 */      g.drawLine(250.0F, 149.0F, 348.0F, 149.0F);
/* 205:205 */      g.drawLine(299.0F, 100.0F, 299.0F, 198.0F);
/* 206:    */      
/* 207:207 */      g.drawLine(400.0F, 149.0F, 498.0F, 149.0F);
/* 208:208 */      g.drawLine(449.0F, 100.0F, 449.0F, 198.0F);
/* 209:    */      
/* 210:210 */      g.drawLine(550.0F, 149.0F, 648.0F, 149.0F);
/* 211:211 */      g.drawLine(599.0F, 100.0F, 599.0F, 198.0F);
/* 212:    */      
/* 214:214 */      g.drawLine(100.0F, 299.0F, 198.0F, 299.0F);
/* 215:215 */      g.drawLine(149.0F, 250.0F, 149.0F, 348.0F);
/* 216:    */      
/* 217:217 */      g.drawLine(250.0F, 299.0F, 348.0F, 299.0F);
/* 218:218 */      g.drawLine(299.0F, 250.0F, 299.0F, 348.0F);
/* 219:    */      
/* 220:220 */      g.drawLine(400.0F, 299.0F, 498.0F, 299.0F);
/* 221:221 */      g.drawLine(449.0F, 250.0F, 449.0F, 348.0F);
/* 222:    */      
/* 223:223 */      g.drawLine(550.0F, 299.0F, 648.0F, 299.0F);
/* 224:224 */      g.drawLine(599.0F, 250.0F, 599.0F, 348.0F);
/* 225:    */    }
/* 226:    */  }
/* 227:    */  
/* 232:    */  public void update(GameContainer container, int delta) {}
/* 233:    */  
/* 237:    */  public void keyPressed(int key, char c)
/* 238:    */  {
/* 239:239 */    if (key == 1) {
/* 240:240 */      System.exit(0);
/* 241:    */    }
/* 242:    */    
/* 243:243 */    if (key == 49) {
/* 244:244 */      this.curTest += 1;
/* 245:245 */      this.curTest %= 3;
/* 246:    */    }
/* 247:    */    
/* 248:248 */    if (key == 46) {
/* 249:249 */      this.colorIndex += 1;
/* 250:    */      
/* 251:251 */      this.colorIndex %= 4;
/* 252:252 */      setColors();
/* 253:    */    }
/* 254:    */    
/* 255:255 */    if (key == 20) {
/* 256:256 */      this.hideOverlay = (!this.hideOverlay);
/* 257:    */    }
/* 258:    */  }
/* 259:    */  
/* 264:    */  private void setColors()
/* 265:    */  {
/* 266:266 */    switch (this.colorIndex)
/* 267:    */    {
/* 268:    */    case 0: 
/* 269:269 */      this.overlayColor = Color.white;
/* 270:270 */      this.geomColor = Color.magenta;
/* 271:271 */      break;
/* 272:    */    
/* 273:    */    case 1: 
/* 274:274 */      this.overlayColor = Color.magenta;
/* 275:275 */      this.geomColor = Color.white;
/* 276:276 */      break;
/* 277:    */    
/* 278:    */    case 2: 
/* 279:279 */      this.overlayColor = Color.red;
/* 280:280 */      this.geomColor = Color.green;
/* 281:281 */      break;
/* 282:    */    
/* 283:    */    case 3: 
/* 284:284 */      this.overlayColor = Color.red;
/* 285:285 */      this.geomColor = Color.white;
/* 286:    */    }
/* 287:    */    
/* 288:    */  }
/* 289:    */  
/* 294:    */  public static void main(String[] argv)
/* 295:    */  {
/* 296:    */    try
/* 297:    */    {
/* 298:298 */      AppGameContainer container = new AppGameContainer(new GeomAccuracyTest());
/* 299:299 */      container.setDisplayMode(800, 600, false);
/* 300:300 */      container.start();
/* 301:    */    } catch (SlickException e) {
/* 302:302 */      e.printStackTrace();
/* 303:    */    }
/* 304:    */  }
/* 305:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.GeomAccuracyTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */