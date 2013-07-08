/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.Color;
/*   7:    */import org.newdawn.slick.GameContainer;
/*   8:    */import org.newdawn.slick.Graphics;
/*   9:    */import org.newdawn.slick.Input;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */import org.newdawn.slick.geom.Circle;
/*  12:    */import org.newdawn.slick.geom.GeomUtil;
/*  13:    */import org.newdawn.slick.geom.GeomUtilListener;
/*  14:    */import org.newdawn.slick.geom.Polygon;
/*  15:    */import org.newdawn.slick.geom.Rectangle;
/*  16:    */import org.newdawn.slick.geom.Shape;
/*  17:    */import org.newdawn.slick.geom.Transform;
/*  18:    */import org.newdawn.slick.geom.Vector2f;
/*  19:    */
/*  28:    */public class GeomUtilTest
/*  29:    */  extends BasicGame
/*  30:    */  implements GeomUtilListener
/*  31:    */{
/*  32:    */  private Shape source;
/*  33:    */  private Shape cut;
/*  34:    */  private Shape[] result;
/*  35: 35 */  private ArrayList points = new ArrayList();
/*  36:    */  
/*  37: 37 */  private ArrayList marks = new ArrayList();
/*  38:    */  
/*  39: 39 */  private ArrayList exclude = new ArrayList();
/*  40:    */  
/*  42:    */  private boolean dynamic;
/*  43:    */  
/*  44: 44 */  private GeomUtil util = new GeomUtil();
/*  45:    */  
/*  47:    */  private int xp;
/*  48:    */  
/*  50:    */  private int yp;
/*  51:    */  
/*  52:    */  private Circle circle;
/*  53:    */  
/*  54:    */  private Shape rect;
/*  55:    */  
/*  56:    */  private Polygon star;
/*  57:    */  
/*  58:    */  private boolean union;
/*  59:    */  
/*  61:    */  public GeomUtilTest()
/*  62:    */  {
/*  63: 63 */    super("GeomUtilTest");
/*  64:    */  }
/*  65:    */  
/*  68:    */  public void init()
/*  69:    */  {
/*  70: 70 */    Polygon source = new Polygon();
/*  71: 71 */    source.addPoint(100.0F, 100.0F);
/*  72: 72 */    source.addPoint(150.0F, 80.0F);
/*  73: 73 */    source.addPoint(210.0F, 120.0F);
/*  74: 74 */    source.addPoint(340.0F, 150.0F);
/*  75: 75 */    source.addPoint(150.0F, 200.0F);
/*  76: 76 */    source.addPoint(120.0F, 250.0F);
/*  77: 77 */    this.source = source;
/*  78:    */    
/*  79: 79 */    this.circle = new Circle(0.0F, 0.0F, 50.0F);
/*  80: 80 */    this.rect = new Rectangle(-100.0F, -40.0F, 200.0F, 80.0F);
/*  81: 81 */    this.star = new Polygon();
/*  82:    */    
/*  83: 83 */    float dis = 40.0F;
/*  84: 84 */    for (int i = 0; i < 360; i += 30) {
/*  85: 85 */      dis = dis == 40.0F ? 60.0F : 40.0F;
/*  86: 86 */      double x = Math.cos(Math.toRadians(i)) * dis;
/*  87: 87 */      double y = Math.sin(Math.toRadians(i)) * dis;
/*  88: 88 */      this.star.addPoint((float)x, (float)y);
/*  89:    */    }
/*  90:    */    
/*  91: 91 */    this.cut = this.circle;
/*  92: 92 */    this.cut.setLocation(203.0F, 78.0F);
/*  93: 93 */    this.xp = ((int)this.cut.getCenterX());
/*  94: 94 */    this.yp = ((int)this.cut.getCenterY());
/*  95: 95 */    makeBoolean();
/*  96:    */  }
/*  97:    */  
/*  99:    */  public void init(GameContainer container)
/* 100:    */    throws SlickException
/* 101:    */  {
/* 102:102 */    this.util.setListener(this);
/* 103:103 */    init();
/* 104:104 */    container.setVSync(true);
/* 105:    */  }
/* 106:    */  
/* 109:    */  public void update(GameContainer container, int delta)
/* 110:    */    throws SlickException
/* 111:    */  {
/* 112:112 */    if (container.getInput().isKeyPressed(57)) {
/* 113:113 */      this.dynamic = (!this.dynamic);
/* 114:    */    }
/* 115:115 */    if (container.getInput().isKeyPressed(28)) {
/* 116:116 */      this.union = (!this.union);
/* 117:117 */      makeBoolean();
/* 118:    */    }
/* 119:119 */    if (container.getInput().isKeyPressed(2)) {
/* 120:120 */      this.cut = this.circle;
/* 121:121 */      this.circle.setCenterX(this.xp);
/* 122:122 */      this.circle.setCenterY(this.yp);
/* 123:123 */      makeBoolean();
/* 124:    */    }
/* 125:125 */    if (container.getInput().isKeyPressed(3)) {
/* 126:126 */      this.cut = this.rect;
/* 127:127 */      this.rect.setCenterX(this.xp);
/* 128:128 */      this.rect.setCenterY(this.yp);
/* 129:129 */      makeBoolean();
/* 130:    */    }
/* 131:131 */    if (container.getInput().isKeyPressed(4)) {
/* 132:132 */      this.cut = this.star;
/* 133:133 */      this.star.setCenterX(this.xp);
/* 134:134 */      this.star.setCenterY(this.yp);
/* 135:135 */      makeBoolean();
/* 136:    */    }
/* 137:    */    
/* 138:138 */    if (this.dynamic) {
/* 139:139 */      this.xp = container.getInput().getMouseX();
/* 140:140 */      this.yp = container.getInput().getMouseY();
/* 141:141 */      makeBoolean();
/* 142:    */    }
/* 143:    */  }
/* 144:    */  
/* 147:    */  private void makeBoolean()
/* 148:    */  {
/* 149:149 */    this.marks.clear();
/* 150:150 */    this.points.clear();
/* 151:151 */    this.exclude.clear();
/* 152:152 */    this.cut.setCenterX(this.xp);
/* 153:153 */    this.cut.setCenterY(this.yp);
/* 154:154 */    if (this.union) {
/* 155:155 */      this.result = this.util.union(this.source, this.cut);
/* 156:    */    } else {
/* 157:157 */      this.result = this.util.subtract(this.source, this.cut);
/* 158:    */    }
/* 159:    */  }
/* 160:    */  
/* 163:    */  public void render(GameContainer container, Graphics g)
/* 164:    */    throws SlickException
/* 165:    */  {
/* 166:166 */    g.drawString("Space - toggle movement of cutting shape", 530.0F, 10.0F);
/* 167:167 */    g.drawString("1,2,3 - select cutting shape", 530.0F, 30.0F);
/* 168:168 */    g.drawString("Mouse wheel - rotate shape", 530.0F, 50.0F);
/* 169:169 */    g.drawString("Enter - toggle union/subtract", 530.0F, 70.0F);
/* 170:170 */    g.drawString(new StringBuilder().append("MODE: ").append(this.union ? "Union" : "Cut").toString(), 530.0F, 200.0F);
/* 171:    */    
/* 172:172 */    g.setColor(Color.green);
/* 173:173 */    g.draw(this.source);
/* 174:174 */    g.setColor(Color.red);
/* 175:175 */    g.draw(this.cut);
/* 176:    */    
/* 177:177 */    g.setColor(Color.white);
/* 178:178 */    for (int i = 0; i < this.exclude.size(); i++) {
/* 179:179 */      Vector2f pt = (Vector2f)this.exclude.get(i);
/* 180:180 */      g.drawOval(pt.x - 3.0F, pt.y - 3.0F, 7.0F, 7.0F);
/* 181:    */    }
/* 182:182 */    g.setColor(Color.yellow);
/* 183:183 */    for (int i = 0; i < this.points.size(); i++) {
/* 184:184 */      Vector2f pt = (Vector2f)this.points.get(i);
/* 185:185 */      g.fillOval(pt.x - 1.0F, pt.y - 1.0F, 3.0F, 3.0F);
/* 186:    */    }
/* 187:187 */    g.setColor(Color.white);
/* 188:188 */    for (int i = 0; i < this.marks.size(); i++) {
/* 189:189 */      Vector2f pt = (Vector2f)this.marks.get(i);
/* 190:190 */      g.fillOval(pt.x - 1.0F, pt.y - 1.0F, 3.0F, 3.0F);
/* 191:    */    }
/* 192:    */    
/* 193:193 */    g.translate(0.0F, 300.0F);
/* 194:194 */    g.setColor(Color.white);
/* 195:195 */    if (this.result != null) {
/* 196:196 */      for (int i = 0; i < this.result.length; i++) {
/* 197:197 */        g.draw(this.result[i]);
/* 198:    */      }
/* 199:    */      
/* 200:200 */      g.drawString(new StringBuilder().append("Polys:").append(this.result.length).toString(), 10.0F, 100.0F);
/* 201:201 */      g.drawString(new StringBuilder().append("X:").append(this.xp).toString(), 10.0F, 120.0F);
/* 202:202 */      g.drawString(new StringBuilder().append("Y:").append(this.yp).toString(), 10.0F, 130.0F);
/* 203:    */    }
/* 204:    */  }
/* 205:    */  
/* 210:    */  public static void main(String[] argv)
/* 211:    */  {
/* 212:    */    try
/* 213:    */    {
/* 214:214 */      AppGameContainer container = new AppGameContainer(new GeomUtilTest());
/* 215:215 */      container.setDisplayMode(800, 600, false);
/* 216:216 */      container.start();
/* 217:    */    } catch (SlickException e) {
/* 218:218 */      e.printStackTrace();
/* 219:    */    }
/* 220:    */  }
/* 221:    */  
/* 222:    */  public void pointExcluded(float x, float y) {
/* 223:223 */    this.exclude.add(new Vector2f(x, y));
/* 224:    */  }
/* 225:    */  
/* 226:    */  public void pointIntersected(float x, float y) {
/* 227:227 */    this.marks.add(new Vector2f(x, y));
/* 228:    */  }
/* 229:    */  
/* 230:    */  public void pointUsed(float x, float y) {
/* 231:231 */    this.points.add(new Vector2f(x, y));
/* 232:    */  }
/* 233:    */  
/* 234:    */  public void mouseWheelMoved(int change) {
/* 235:235 */    if (this.dynamic) {
/* 236:236 */      if (change < 0) {
/* 237:237 */        this.cut = this.cut.transform(Transform.createRotateTransform((float)Math.toRadians(10.0D), this.cut.getCenterX(), this.cut.getCenterY()));
/* 238:    */      } else {
/* 239:239 */        this.cut = this.cut.transform(Transform.createRotateTransform((float)Math.toRadians(-10.0D), this.cut.getCenterX(), this.cut.getCenterY()));
/* 240:    */      }
/* 241:    */    }
/* 242:    */  }
/* 243:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.GeomUtilTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */