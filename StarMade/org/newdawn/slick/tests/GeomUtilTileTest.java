/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.HashSet;
/*   6:    */import org.newdawn.slick.AppGameContainer;
/*   7:    */import org.newdawn.slick.BasicGame;
/*   8:    */import org.newdawn.slick.Color;
/*   9:    */import org.newdawn.slick.GameContainer;
/*  10:    */import org.newdawn.slick.Graphics;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */import org.newdawn.slick.geom.Circle;
/*  13:    */import org.newdawn.slick.geom.GeomUtil;
/*  14:    */import org.newdawn.slick.geom.GeomUtilListener;
/*  15:    */import org.newdawn.slick.geom.Polygon;
/*  16:    */import org.newdawn.slick.geom.Shape;
/*  17:    */import org.newdawn.slick.geom.Vector2f;
/*  18:    */
/*  26:    */public class GeomUtilTileTest
/*  27:    */  extends BasicGame
/*  28:    */  implements GeomUtilListener
/*  29:    */{
/*  30:    */  private Shape source;
/*  31:    */  private Shape cut;
/*  32:    */  private Shape[] result;
/*  33: 33 */  private GeomUtil util = new GeomUtil();
/*  34:    */  
/*  36: 36 */  private ArrayList original = new ArrayList();
/*  37:    */  
/*  38: 38 */  private ArrayList combined = new ArrayList();
/*  39:    */  
/*  41: 41 */  private ArrayList intersections = new ArrayList();
/*  42:    */  
/*  43: 43 */  private ArrayList used = new ArrayList();
/*  44:    */  
/*  46:    */  private ArrayList[][] quadSpace;
/*  47:    */  
/*  49:    */  private Shape[][] quadSpaceShapes;
/*  50:    */  
/*  52:    */  public GeomUtilTileTest()
/*  53:    */  {
/*  54: 54 */    super("GeomUtilTileTest");
/*  55:    */  }
/*  56:    */  
/*  68:    */  private void generateSpace(ArrayList shapes, float minx, float miny, float maxx, float maxy, int segments)
/*  69:    */  {
/*  70: 70 */    this.quadSpace = new ArrayList[segments][segments];
/*  71: 71 */    this.quadSpaceShapes = new Shape[segments][segments];
/*  72:    */    
/*  73: 73 */    float dx = (maxx - minx) / segments;
/*  74: 74 */    float dy = (maxy - miny) / segments;
/*  75:    */    
/*  76: 76 */    for (int x = 0; x < segments; x++) {
/*  77: 77 */      for (int y = 0; y < segments; y++) {
/*  78: 78 */        this.quadSpace[x][y] = new ArrayList();
/*  79:    */        
/*  81: 81 */        Polygon segmentPolygon = new Polygon();
/*  82: 82 */        segmentPolygon.addPoint(minx + dx * x, miny + dy * y);
/*  83: 83 */        segmentPolygon.addPoint(minx + dx * x + dx, miny + dy * y);
/*  84: 84 */        segmentPolygon.addPoint(minx + dx * x + dx, miny + dy * y + dy);
/*  85: 85 */        segmentPolygon.addPoint(minx + dx * x, miny + dy * y + dy);
/*  86:    */        
/*  87: 87 */        for (int i = 0; i < shapes.size(); i++) {
/*  88: 88 */          Shape shape = (Shape)shapes.get(i);
/*  89:    */          
/*  90: 90 */          if (collides(shape, segmentPolygon)) {
/*  91: 91 */            this.quadSpace[x][y].add(shape);
/*  92:    */          }
/*  93:    */        }
/*  94:    */        
/*  95: 95 */        this.quadSpaceShapes[x][y] = segmentPolygon;
/*  96:    */      }
/*  97:    */    }
/*  98:    */  }
/*  99:    */  
/* 104:    */  private void removeFromQuadSpace(Shape shape)
/* 105:    */  {
/* 106:106 */    int segments = this.quadSpace.length;
/* 107:    */    
/* 108:108 */    for (int x = 0; x < segments; x++) {
/* 109:109 */      for (int y = 0; y < segments; y++) {
/* 110:110 */        this.quadSpace[x][y].remove(shape);
/* 111:    */      }
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 119:    */  private void addToQuadSpace(Shape shape)
/* 120:    */  {
/* 121:121 */    int segments = this.quadSpace.length;
/* 122:    */    
/* 123:123 */    for (int x = 0; x < segments; x++) {
/* 124:124 */      for (int y = 0; y < segments; y++) {
/* 125:125 */        if (collides(shape, this.quadSpaceShapes[x][y])) {
/* 126:126 */          this.quadSpace[x][y].add(shape);
/* 127:    */        }
/* 128:    */      }
/* 129:    */    }
/* 130:    */  }
/* 131:    */  
/* 134:    */  public void init()
/* 135:    */  {
/* 136:136 */    int size = 10;
/* 137:137 */    int[][] map = { { 0, 0, 0, 0, 0, 0, 0, 3, 0, 0 }, { 0, 1, 1, 1, 0, 0, 1, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 5, 1, 6, 0 }, { 0, 1, 2, 0, 0, 0, 4, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 3, 0, 1, 1, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
/* 138:    */    
/* 160:160 */    for (int x = 0; x < map[0].length; x++) {
/* 161:161 */      for (int y = 0; y < map.length; y++) {
/* 162:162 */        if (map[y][x] != 0) {
/* 163:163 */          switch (map[y][x]) {
/* 164:    */          case 1: 
/* 165:165 */            Polygon p2 = new Polygon();
/* 166:166 */            p2.addPoint(x * 32, y * 32);
/* 167:167 */            p2.addPoint(x * 32 + 32, y * 32);
/* 168:168 */            p2.addPoint(x * 32 + 32, y * 32 + 32);
/* 169:169 */            p2.addPoint(x * 32, y * 32 + 32);
/* 170:170 */            this.original.add(p2);
/* 171:171 */            break;
/* 172:    */          case 2: 
/* 173:173 */            Polygon poly = new Polygon();
/* 174:174 */            poly.addPoint(x * 32, y * 32);
/* 175:175 */            poly.addPoint(x * 32 + 32, y * 32);
/* 176:176 */            poly.addPoint(x * 32, y * 32 + 32);
/* 177:177 */            this.original.add(poly);
/* 178:178 */            break;
/* 179:    */          case 3: 
/* 180:180 */            Circle ellipse = new Circle(x * 32 + 16, y * 32 + 32, 16.0F, 16);
/* 181:181 */            this.original.add(ellipse);
/* 182:182 */            break;
/* 183:    */          case 4: 
/* 184:184 */            Polygon p = new Polygon();
/* 185:185 */            p.addPoint(x * 32 + 32, y * 32);
/* 186:186 */            p.addPoint(x * 32 + 32, y * 32 + 32);
/* 187:187 */            p.addPoint(x * 32, y * 32 + 32);
/* 188:188 */            this.original.add(p);
/* 189:189 */            break;
/* 190:    */          case 5: 
/* 191:191 */            Polygon p3 = new Polygon();
/* 192:192 */            p3.addPoint(x * 32, y * 32);
/* 193:193 */            p3.addPoint(x * 32 + 32, y * 32);
/* 194:194 */            p3.addPoint(x * 32 + 32, y * 32 + 32);
/* 195:195 */            this.original.add(p3);
/* 196:196 */            break;
/* 197:    */          case 6: 
/* 198:198 */            Polygon p4 = new Polygon();
/* 199:199 */            p4.addPoint(x * 32, y * 32);
/* 200:200 */            p4.addPoint(x * 32 + 32, y * 32);
/* 201:201 */            p4.addPoint(x * 32, y * 32 + 32);
/* 202:202 */            this.original.add(p4);
/* 203:    */          }
/* 204:    */          
/* 205:    */        }
/* 206:    */      }
/* 207:    */    }
/* 208:    */    
/* 209:209 */    long before = System.currentTimeMillis();
/* 210:    */    
/* 212:212 */    generateSpace(this.original, 0.0F, 0.0F, (size + 1) * 32, (size + 1) * 32, 8);
/* 213:213 */    this.combined = combineQuadSpace();
/* 214:    */    
/* 218:218 */    long after = System.currentTimeMillis();
/* 219:219 */    System.out.println("Combine took: " + (after - before));
/* 220:220 */    System.out.println("Combine result: " + this.combined.size());
/* 221:    */  }
/* 222:    */  
/* 227:    */  private ArrayList combineQuadSpace()
/* 228:    */  {
/* 229:229 */    boolean updated = true;
/* 230:230 */    while (updated) {
/* 231:231 */      updated = false;
/* 232:    */      
/* 233:233 */      for (int x = 0; x < this.quadSpace.length; x++) {
/* 234:234 */        for (int y = 0; y < this.quadSpace.length; y++) {
/* 235:235 */          ArrayList shapes = this.quadSpace[x][y];
/* 236:236 */          int before = shapes.size();
/* 237:237 */          combine(shapes);
/* 238:238 */          int after = shapes.size();
/* 239:    */          
/* 240:240 */          updated |= before != after;
/* 241:    */        }
/* 242:    */      }
/* 243:    */    }
/* 244:    */    
/* 247:247 */    HashSet result = new HashSet();
/* 248:    */    
/* 249:249 */    for (int x = 0; x < this.quadSpace.length; x++) {
/* 250:250 */      for (int y = 0; y < this.quadSpace.length; y++) {
/* 251:251 */        result.addAll(this.quadSpace[x][y]);
/* 252:    */      }
/* 253:    */    }
/* 254:    */    
/* 255:255 */    return new ArrayList(result);
/* 256:    */  }
/* 257:    */  
/* 264:    */  private ArrayList combine(ArrayList shapes)
/* 265:    */  {
/* 266:266 */    ArrayList last = shapes;
/* 267:267 */    ArrayList current = shapes;
/* 268:268 */    boolean first = true;
/* 269:    */    
/* 270:270 */    while ((current.size() != last.size()) || (first)) {
/* 271:271 */      first = false;
/* 272:272 */      last = current;
/* 273:273 */      current = combineImpl(current);
/* 274:    */    }
/* 275:    */    
/* 276:276 */    ArrayList pruned = new ArrayList();
/* 277:277 */    for (int i = 0; i < current.size(); i++) {
/* 278:278 */      pruned.add(((Shape)current.get(i)).prune());
/* 279:    */    }
/* 280:280 */    return pruned;
/* 281:    */  }
/* 282:    */  
/* 290:    */  private ArrayList combineImpl(ArrayList shapes)
/* 291:    */  {
/* 292:292 */    ArrayList result = new ArrayList(shapes);
/* 293:293 */    if (this.quadSpace != null) {
/* 294:294 */      result = shapes;
/* 295:    */    }
/* 296:    */    
/* 297:297 */    for (int i = 0; i < shapes.size(); i++) {
/* 298:298 */      Shape first = (Shape)shapes.get(i);
/* 299:299 */      for (int j = i + 1; j < shapes.size(); j++) {
/* 300:300 */        Shape second = (Shape)shapes.get(j);
/* 301:    */        
/* 302:302 */        if (first.intersects(second))
/* 303:    */        {
/* 306:306 */          Shape[] joined = this.util.union(first, second);
/* 307:307 */          if (joined.length == 1) {
/* 308:308 */            if (this.quadSpace != null) {
/* 309:309 */              removeFromQuadSpace(first);
/* 310:310 */              removeFromQuadSpace(second);
/* 311:311 */              addToQuadSpace(joined[0]);
/* 312:    */            } else {
/* 313:313 */              result.remove(first);
/* 314:314 */              result.remove(second);
/* 315:315 */              result.add(joined[0]);
/* 316:    */            }
/* 317:317 */            return result;
/* 318:    */          }
/* 319:    */        }
/* 320:    */      }
/* 321:    */    }
/* 322:322 */    return result;
/* 323:    */  }
/* 324:    */  
/* 331:    */  public boolean collides(Shape shape1, Shape shape2)
/* 332:    */  {
/* 333:333 */    if (shape1.intersects(shape2)) {
/* 334:334 */      return true;
/* 335:    */    }
/* 336:336 */    for (int i = 0; i < shape1.getPointCount(); i++) {
/* 337:337 */      float[] pt = shape1.getPoint(i);
/* 338:338 */      if (shape2.contains(pt[0], pt[1])) {
/* 339:339 */        return true;
/* 340:    */      }
/* 341:    */    }
/* 342:342 */    for (int i = 0; i < shape2.getPointCount(); i++) {
/* 343:343 */      float[] pt = shape2.getPoint(i);
/* 344:344 */      if (shape1.contains(pt[0], pt[1])) {
/* 345:345 */        return true;
/* 346:    */      }
/* 347:    */    }
/* 348:    */    
/* 349:349 */    return false;
/* 350:    */  }
/* 351:    */  
/* 353:    */  public void init(GameContainer container)
/* 354:    */    throws SlickException
/* 355:    */  {
/* 356:356 */    this.util.setListener(this);
/* 357:357 */    init();
/* 358:    */  }
/* 359:    */  
/* 363:    */  public void update(GameContainer container, int delta)
/* 364:    */    throws SlickException
/* 365:    */  {}
/* 366:    */  
/* 370:    */  public void render(GameContainer container, Graphics g)
/* 371:    */    throws SlickException
/* 372:    */  {
/* 373:373 */    g.setColor(Color.green);
/* 374:374 */    for (int i = 0; i < this.original.size(); i++) {
/* 375:375 */      Shape shape = (Shape)this.original.get(i);
/* 376:376 */      g.draw(shape);
/* 377:    */    }
/* 378:    */    
/* 379:379 */    g.setColor(Color.white);
/* 380:380 */    if (this.quadSpaceShapes != null) {
/* 381:381 */      g.draw(this.quadSpaceShapes[0][0]);
/* 382:    */    }
/* 383:    */    
/* 384:384 */    g.translate(0.0F, 320.0F);
/* 385:    */    
/* 386:386 */    for (int i = 0; i < this.combined.size(); i++) {
/* 387:387 */      g.setColor(Color.white);
/* 388:388 */      Shape shape = (Shape)this.combined.get(i);
/* 389:389 */      g.draw(shape);
/* 390:390 */      for (int j = 0; j < shape.getPointCount(); j++) {
/* 391:391 */        g.setColor(Color.yellow);
/* 392:392 */        float[] pt = shape.getPoint(j);
/* 393:393 */        g.fillOval(pt[0] - 1.0F, pt[1] - 1.0F, 3.0F, 3.0F);
/* 394:    */      }
/* 395:    */    }
/* 396:    */  }
/* 397:    */  
/* 403:    */  public static void main(String[] argv)
/* 404:    */  {
/* 405:    */    try
/* 406:    */    {
/* 407:407 */      AppGameContainer container = new AppGameContainer(new GeomUtilTileTest());
/* 408:    */      
/* 409:409 */      container.setDisplayMode(800, 600, false);
/* 410:410 */      container.start();
/* 411:    */    } catch (SlickException e) {
/* 412:412 */      e.printStackTrace();
/* 413:    */    }
/* 414:    */  }
/* 415:    */  
/* 416:    */  public void pointExcluded(float x, float y) {}
/* 417:    */  
/* 418:    */  public void pointIntersected(float x, float y)
/* 419:    */  {
/* 420:420 */    this.intersections.add(new Vector2f(x, y));
/* 421:    */  }
/* 422:    */  
/* 423:    */  public void pointUsed(float x, float y) {
/* 424:424 */    this.used.add(new Vector2f(x, y));
/* 425:    */  }
/* 426:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.GeomUtilTileTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */