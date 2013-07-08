/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.Color;
/*   7:    */import org.newdawn.slick.GameContainer;
/*   8:    */import org.newdawn.slick.Graphics;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.geom.Circle;
/*  11:    */import org.newdawn.slick.geom.Ellipse;
/*  12:    */import org.newdawn.slick.geom.Polygon;
/*  13:    */import org.newdawn.slick.geom.Rectangle;
/*  14:    */import org.newdawn.slick.geom.RoundedRectangle;
/*  15:    */import org.newdawn.slick.geom.Shape;
/*  16:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  17:    */
/*  32:    */public class ShapeTest
/*  33:    */  extends BasicGame
/*  34:    */{
/*  35:    */  private Rectangle rect;
/*  36:    */  private RoundedRectangle roundRect;
/*  37:    */  private Ellipse ellipse;
/*  38:    */  private Circle circle;
/*  39:    */  private Polygon polygon;
/*  40:    */  private ArrayList shapes;
/*  41:    */  private boolean[] keys;
/*  42:    */  private char[] lastChar;
/*  43: 43 */  private Polygon randomShape = new Polygon();
/*  44:    */  
/*  47:    */  public ShapeTest()
/*  48:    */  {
/*  49: 49 */    super("Geom Test");
/*  50:    */  }
/*  51:    */  
/*  52:    */  public void createPoly(float x, float y) {
/*  53: 53 */    int size = 20;
/*  54: 54 */    int change = 10;
/*  55:    */    
/*  56: 56 */    this.randomShape = new Polygon();
/*  57:    */    
/*  58: 58 */    this.randomShape.addPoint(0 + (int)(Math.random() * change), 0 + (int)(Math.random() * change));
/*  59: 59 */    this.randomShape.addPoint(size - (int)(Math.random() * change), 0 + (int)(Math.random() * change));
/*  60: 60 */    this.randomShape.addPoint(size - (int)(Math.random() * change), size - (int)(Math.random() * change));
/*  61: 61 */    this.randomShape.addPoint(0 + (int)(Math.random() * change), size - (int)(Math.random() * change));
/*  62:    */    
/*  64: 64 */    this.randomShape.setCenterX(x);
/*  65: 65 */    this.randomShape.setCenterY(y);
/*  66:    */  }
/*  67:    */  
/*  69:    */  public void init(GameContainer container)
/*  70:    */    throws SlickException
/*  71:    */  {
/*  72: 72 */    this.shapes = new ArrayList();
/*  73: 73 */    this.rect = new Rectangle(10.0F, 10.0F, 100.0F, 80.0F);
/*  74: 74 */    this.shapes.add(this.rect);
/*  75: 75 */    this.roundRect = new RoundedRectangle(150.0F, 10.0F, 60.0F, 80.0F, 20.0F);
/*  76: 76 */    this.shapes.add(this.roundRect);
/*  77: 77 */    this.ellipse = new Ellipse(350.0F, 40.0F, 50.0F, 30.0F);
/*  78: 78 */    this.shapes.add(this.ellipse);
/*  79: 79 */    this.circle = new Circle(470.0F, 60.0F, 50.0F);
/*  80: 80 */    this.shapes.add(this.circle);
/*  81: 81 */    this.polygon = new Polygon(new float[] { 550.0F, 10.0F, 600.0F, 40.0F, 620.0F, 100.0F, 570.0F, 130.0F });
/*  82: 82 */    this.shapes.add(this.polygon);
/*  83:    */    
/*  84: 84 */    this.keys = new boolean[256];
/*  85: 85 */    this.lastChar = new char[256];
/*  86: 86 */    createPoly(200.0F, 200.0F);
/*  87:    */  }
/*  88:    */  
/*  91:    */  public void render(GameContainer container, Graphics g)
/*  92:    */  {
/*  93: 93 */    g.setColor(Color.green);
/*  94:    */    
/*  95: 95 */    for (int i = 0; i < this.shapes.size(); i++) {
/*  96: 96 */      g.fill((Shape)this.shapes.get(i));
/*  97:    */    }
/*  98: 98 */    g.fill(this.randomShape);
/*  99: 99 */    g.setColor(Color.black);
/* 100:100 */    g.setAntiAlias(true);
/* 101:101 */    g.draw(this.randomShape);
/* 102:102 */    g.setAntiAlias(false);
/* 103:    */    
/* 104:104 */    g.setColor(Color.white);
/* 105:105 */    g.drawString("keys", 10.0F, 300.0F);
/* 106:106 */    g.drawString("wasd - move rectangle", 10.0F, 315.0F);
/* 107:107 */    g.drawString("WASD - resize rectangle", 10.0F, 330.0F);
/* 108:108 */    g.drawString("tgfh - move rounded rectangle", 10.0F, 345.0F);
/* 109:109 */    g.drawString("TGFH - resize rounded rectangle", 10.0F, 360.0F);
/* 110:110 */    g.drawString("ry - resize corner radius on rounded rectangle", 10.0F, 375.0F);
/* 111:111 */    g.drawString("ikjl - move ellipse", 10.0F, 390.0F);
/* 112:112 */    g.drawString("IKJL - resize ellipse", 10.0F, 405.0F);
/* 113:113 */    g.drawString("Arrows - move circle", 10.0F, 420.0F);
/* 114:114 */    g.drawString("Page Up/Page Down - resize circle", 10.0F, 435.0F);
/* 115:115 */    g.drawString("numpad 8546 - move polygon", 10.0F, 450.0F);
/* 116:    */  }
/* 117:    */  
/* 121:    */  public void update(GameContainer container, int delta)
/* 122:    */  {
/* 123:123 */    createPoly(200.0F, 200.0F);
/* 124:124 */    if (this.keys[1] != 0) {
/* 125:125 */      System.exit(0);
/* 126:    */    }
/* 127:127 */    if (this.keys[17] != 0) {
/* 128:128 */      if (this.lastChar[17] == 'w') {
/* 129:129 */        this.rect.setY(this.rect.getY() - 1.0F);
/* 130:    */      }
/* 131:    */      else {
/* 132:132 */        this.rect.setHeight(this.rect.getHeight() - 1.0F);
/* 133:    */      }
/* 134:    */    }
/* 135:135 */    if (this.keys[31] != 0) {
/* 136:136 */      if (this.lastChar[31] == 's') {
/* 137:137 */        this.rect.setY(this.rect.getY() + 1.0F);
/* 138:    */      }
/* 139:    */      else {
/* 140:140 */        this.rect.setHeight(this.rect.getHeight() + 1.0F);
/* 141:    */      }
/* 142:    */    }
/* 143:143 */    if (this.keys[30] != 0) {
/* 144:144 */      if (this.lastChar[30] == 'a') {
/* 145:145 */        this.rect.setX(this.rect.getX() - 1.0F);
/* 146:    */      }
/* 147:    */      else {
/* 148:148 */        this.rect.setWidth(this.rect.getWidth() - 1.0F);
/* 149:    */      }
/* 150:    */    }
/* 151:151 */    if (this.keys[32] != 0) {
/* 152:152 */      if (this.lastChar[32] == 'd') {
/* 153:153 */        this.rect.setX(this.rect.getX() + 1.0F);
/* 154:    */      }
/* 155:    */      else {
/* 156:156 */        this.rect.setWidth(this.rect.getWidth() + 1.0F);
/* 157:    */      }
/* 158:    */    }
/* 159:159 */    if (this.keys[20] != 0) {
/* 160:160 */      if (this.lastChar[20] == 't') {
/* 161:161 */        this.roundRect.setY(this.roundRect.getY() - 1.0F);
/* 162:    */      }
/* 163:    */      else {
/* 164:164 */        this.roundRect.setHeight(this.roundRect.getHeight() - 1.0F);
/* 165:    */      }
/* 166:    */    }
/* 167:167 */    if (this.keys[34] != 0) {
/* 168:168 */      if (this.lastChar[34] == 'g') {
/* 169:169 */        this.roundRect.setY(this.roundRect.getY() + 1.0F);
/* 170:    */      }
/* 171:    */      else {
/* 172:172 */        this.roundRect.setHeight(this.roundRect.getHeight() + 1.0F);
/* 173:    */      }
/* 174:    */    }
/* 175:175 */    if (this.keys[33] != 0) {
/* 176:176 */      if (this.lastChar[33] == 'f') {
/* 177:177 */        this.roundRect.setX(this.roundRect.getX() - 1.0F);
/* 178:    */      }
/* 179:    */      else {
/* 180:180 */        this.roundRect.setWidth(this.roundRect.getWidth() - 1.0F);
/* 181:    */      }
/* 182:    */    }
/* 183:183 */    if (this.keys[35] != 0) {
/* 184:184 */      if (this.lastChar[35] == 'h') {
/* 185:185 */        this.roundRect.setX(this.roundRect.getX() + 1.0F);
/* 186:    */      }
/* 187:    */      else {
/* 188:188 */        this.roundRect.setWidth(this.roundRect.getWidth() + 1.0F);
/* 189:    */      }
/* 190:    */    }
/* 191:191 */    if (this.keys[19] != 0) {
/* 192:192 */      this.roundRect.setCornerRadius(this.roundRect.getCornerRadius() - 1.0F);
/* 193:    */    }
/* 194:194 */    if (this.keys[21] != 0) {
/* 195:195 */      this.roundRect.setCornerRadius(this.roundRect.getCornerRadius() + 1.0F);
/* 196:    */    }
/* 197:197 */    if (this.keys[23] != 0) {
/* 198:198 */      if (this.lastChar[23] == 'i') {
/* 199:199 */        this.ellipse.setCenterY(this.ellipse.getCenterY() - 1.0F);
/* 200:    */      }
/* 201:    */      else {
/* 202:202 */        this.ellipse.setRadius2(this.ellipse.getRadius2() - 1.0F);
/* 203:    */      }
/* 204:    */    }
/* 205:205 */    if (this.keys[37] != 0) {
/* 206:206 */      if (this.lastChar[37] == 'k') {
/* 207:207 */        this.ellipse.setCenterY(this.ellipse.getCenterY() + 1.0F);
/* 208:    */      }
/* 209:    */      else {
/* 210:210 */        this.ellipse.setRadius2(this.ellipse.getRadius2() + 1.0F);
/* 211:    */      }
/* 212:    */    }
/* 213:213 */    if (this.keys[36] != 0) {
/* 214:214 */      if (this.lastChar[36] == 'j') {
/* 215:215 */        this.ellipse.setCenterX(this.ellipse.getCenterX() - 1.0F);
/* 216:    */      }
/* 217:    */      else {
/* 218:218 */        this.ellipse.setRadius1(this.ellipse.getRadius1() - 1.0F);
/* 219:    */      }
/* 220:    */    }
/* 221:221 */    if (this.keys[38] != 0) {
/* 222:222 */      if (this.lastChar[38] == 'l') {
/* 223:223 */        this.ellipse.setCenterX(this.ellipse.getCenterX() + 1.0F);
/* 224:    */      }
/* 225:    */      else {
/* 226:226 */        this.ellipse.setRadius1(this.ellipse.getRadius1() + 1.0F);
/* 227:    */      }
/* 228:    */    }
/* 229:229 */    if (this.keys['È'] != 0) {
/* 230:230 */      this.circle.setCenterY(this.circle.getCenterY() - 1.0F);
/* 231:    */    }
/* 232:232 */    if (this.keys['Ð'] != 0) {
/* 233:233 */      this.circle.setCenterY(this.circle.getCenterY() + 1.0F);
/* 234:    */    }
/* 235:235 */    if (this.keys['Ë'] != 0) {
/* 236:236 */      this.circle.setCenterX(this.circle.getCenterX() - 1.0F);
/* 237:    */    }
/* 238:238 */    if (this.keys['Í'] != 0) {
/* 239:239 */      this.circle.setCenterX(this.circle.getCenterX() + 1.0F);
/* 240:    */    }
/* 241:241 */    if (this.keys['É'] != 0) {
/* 242:242 */      this.circle.setRadius(this.circle.getRadius() - 1.0F);
/* 243:    */    }
/* 244:244 */    if (this.keys['Ñ'] != 0) {
/* 245:245 */      this.circle.setRadius(this.circle.getRadius() + 1.0F);
/* 246:    */    }
/* 247:247 */    if (this.keys[72] != 0) {
/* 248:248 */      this.polygon.setY(this.polygon.getY() - 1.0F);
/* 249:    */    }
/* 250:250 */    if (this.keys[76] != 0) {
/* 251:251 */      this.polygon.setY(this.polygon.getY() + 1.0F);
/* 252:    */    }
/* 253:253 */    if (this.keys[75] != 0) {
/* 254:254 */      this.polygon.setX(this.polygon.getX() - 1.0F);
/* 255:    */    }
/* 256:256 */    if (this.keys[77] != 0) {
/* 257:257 */      this.polygon.setX(this.polygon.getX() + 1.0F);
/* 258:    */    }
/* 259:    */  }
/* 260:    */  
/* 263:    */  public void keyPressed(int key, char c)
/* 264:    */  {
/* 265:265 */    this.keys[key] = true;
/* 266:266 */    this.lastChar[key] = c;
/* 267:    */  }
/* 268:    */  
/* 271:    */  public void keyReleased(int key, char c)
/* 272:    */  {
/* 273:273 */    this.keys[key] = false;
/* 274:    */  }
/* 275:    */  
/* 279:    */  public static void main(String[] argv)
/* 280:    */  {
/* 281:    */    try
/* 282:    */    {
/* 283:283 */      Renderer.setRenderer(2);
/* 284:284 */      AppGameContainer container = new AppGameContainer(new ShapeTest());
/* 285:285 */      container.setDisplayMode(800, 600, false);
/* 286:286 */      container.start();
/* 287:    */    } catch (SlickException e) {
/* 288:288 */      e.printStackTrace();
/* 289:    */    }
/* 290:    */  }
/* 291:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ShapeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */