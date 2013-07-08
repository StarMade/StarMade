/*   1:    */package org.newdawn.slick.util.pathfinding;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.LinkedList;
/*   5:    */import java.util.List;
/*   6:    */import org.newdawn.slick.util.pathfinding.heuristics.ClosestHeuristic;
/*   7:    */
/*  14:    */public class AStarPathFinder
/*  15:    */  implements PathFinder, PathFindingContext
/*  16:    */{
/*  17: 17 */  private ArrayList closed = new ArrayList();
/*  18:    */  
/*  19: 19 */  private PriorityList open = new PriorityList(null);
/*  20:    */  
/*  22:    */  private TileBasedMap map;
/*  23:    */  
/*  25:    */  private int maxSearchDistance;
/*  26:    */  
/*  28:    */  private Node[][] nodes;
/*  29:    */  
/*  31:    */  private boolean allowDiagMovement;
/*  32:    */  
/*  34:    */  private AStarHeuristic heuristic;
/*  35:    */  
/*  37:    */  private Node current;
/*  38:    */  
/*  40:    */  private Mover mover;
/*  41:    */  
/*  43:    */  private int sourceX;
/*  44:    */  
/*  45:    */  private int sourceY;
/*  46:    */  
/*  47:    */  private int distance;
/*  48:    */  
/*  50:    */  public AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagMovement)
/*  51:    */  {
/*  52: 52 */    this(map, maxSearchDistance, allowDiagMovement, new ClosestHeuristic());
/*  53:    */  }
/*  54:    */  
/*  63:    */  public AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagMovement, AStarHeuristic heuristic)
/*  64:    */  {
/*  65: 65 */    this.heuristic = heuristic;
/*  66: 66 */    this.map = map;
/*  67: 67 */    this.maxSearchDistance = maxSearchDistance;
/*  68: 68 */    this.allowDiagMovement = allowDiagMovement;
/*  69:    */    
/*  70: 70 */    this.nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
/*  71: 71 */    for (int x = 0; x < map.getWidthInTiles(); x++) {
/*  72: 72 */      for (int y = 0; y < map.getHeightInTiles(); y++) {
/*  73: 73 */        this.nodes[x][y] = new Node(x, y);
/*  74:    */      }
/*  75:    */    }
/*  76:    */  }
/*  77:    */  
/*  80:    */  public Path findPath(Mover mover, int sx, int sy, int tx, int ty)
/*  81:    */  {
/*  82: 82 */    this.current = null;
/*  83:    */    
/*  85: 85 */    this.mover = mover;
/*  86: 86 */    this.sourceX = tx;
/*  87: 87 */    this.sourceY = ty;
/*  88: 88 */    this.distance = 0;
/*  89:    */    
/*  90: 90 */    if (this.map.blocked(this, tx, ty)) {
/*  91: 91 */      return null;
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    for (int x = 0; x < this.map.getWidthInTiles(); x++) {
/*  95: 95 */      for (int y = 0; y < this.map.getHeightInTiles(); y++) {
/*  96: 96 */        this.nodes[x][y].reset();
/*  97:    */      }
/*  98:    */    }
/*  99:    */    
/* 102:102 */    this.nodes[sx][sy].cost = 0.0F;
/* 103:103 */    this.nodes[sx][sy].depth = 0;
/* 104:104 */    this.closed.clear();
/* 105:105 */    this.open.clear();
/* 106:106 */    addToOpen(this.nodes[sx][sy]);
/* 107:    */    
/* 108:108 */    this.nodes[tx][ty].parent = null;
/* 109:    */    
/* 111:111 */    int maxDepth = 0;
/* 112:112 */    while ((maxDepth < this.maxSearchDistance) && (this.open.size() != 0))
/* 113:    */    {
/* 115:115 */      int lx = sx;
/* 116:116 */      int ly = sy;
/* 117:117 */      if (this.current != null) {
/* 118:118 */        lx = this.current.x;
/* 119:119 */        ly = this.current.y;
/* 120:    */      }
/* 121:    */      
/* 122:122 */      this.current = getFirstInOpen();
/* 123:123 */      this.distance = this.current.depth;
/* 124:    */      
/* 125:125 */      if ((this.current == this.nodes[tx][ty]) && 
/* 126:126 */        (isValidLocation(mover, lx, ly, tx, ty))) {
/* 127:    */        break;
/* 128:    */      }
/* 129:    */      
/* 131:131 */      removeFromOpen(this.current);
/* 132:132 */      addToClosed(this.current);
/* 133:    */      
/* 136:136 */      for (int x = -1; x < 2; x++) {
/* 137:137 */        for (int y = -1; y < 2; y++)
/* 138:    */        {
/* 139:139 */          if ((x != 0) || (y != 0))
/* 140:    */          {
/* 145:145 */            if ((this.allowDiagMovement) || 
/* 146:146 */              (x == 0) || (y == 0))
/* 147:    */            {
/* 152:152 */              int xp = x + this.current.x;
/* 153:153 */              int yp = y + this.current.y;
/* 154:    */              
/* 155:155 */              if (isValidLocation(mover, this.current.x, this.current.y, xp, yp))
/* 156:    */              {
/* 159:159 */                float nextStepCost = this.current.cost + getMovementCost(mover, this.current.x, this.current.y, xp, yp);
/* 160:160 */                Node neighbour = this.nodes[xp][yp];
/* 161:161 */                this.map.pathFinderVisited(xp, yp);
/* 162:    */                
/* 167:167 */                if (nextStepCost < neighbour.cost) {
/* 168:168 */                  if (inOpenList(neighbour)) {
/* 169:169 */                    removeFromOpen(neighbour);
/* 170:    */                  }
/* 171:171 */                  if (inClosedList(neighbour)) {
/* 172:172 */                    removeFromClosed(neighbour);
/* 173:    */                  }
/* 174:    */                }
/* 175:    */                
/* 179:179 */                if ((!inOpenList(neighbour)) && (!inClosedList(neighbour))) {
/* 180:180 */                  neighbour.cost = nextStepCost;
/* 181:181 */                  neighbour.heuristic = getHeuristicCost(mover, xp, yp, tx, ty);
/* 182:182 */                  maxDepth = Math.max(maxDepth, neighbour.setParent(this.current));
/* 183:183 */                  addToOpen(neighbour);
/* 184:    */                }
/* 185:    */              }
/* 186:    */            }
/* 187:    */          }
/* 188:    */        }
/* 189:    */      }
/* 190:    */    }
/* 191:    */    
/* 192:192 */    if (this.nodes[tx][ty].parent == null) {
/* 193:193 */      return null;
/* 194:    */    }
/* 195:    */    
/* 199:199 */    Path path = new Path();
/* 200:200 */    Node target = this.nodes[tx][ty];
/* 201:201 */    while (target != this.nodes[sx][sy]) {
/* 202:202 */      path.prependStep(target.x, target.y);
/* 203:203 */      target = target.parent;
/* 204:    */    }
/* 205:205 */    path.prependStep(sx, sy);
/* 206:    */    
/* 208:208 */    return path;
/* 209:    */  }
/* 210:    */  
/* 215:    */  public int getCurrentX()
/* 216:    */  {
/* 217:217 */    if (this.current == null) {
/* 218:218 */      return -1;
/* 219:    */    }
/* 220:    */    
/* 221:221 */    return this.current.x;
/* 222:    */  }
/* 223:    */  
/* 228:    */  public int getCurrentY()
/* 229:    */  {
/* 230:230 */    if (this.current == null) {
/* 231:231 */      return -1;
/* 232:    */    }
/* 233:    */    
/* 234:234 */    return this.current.y;
/* 235:    */  }
/* 236:    */  
/* 242:    */  protected Node getFirstInOpen()
/* 243:    */  {
/* 244:244 */    return (Node)this.open.first();
/* 245:    */  }
/* 246:    */  
/* 251:    */  protected void addToOpen(Node node)
/* 252:    */  {
/* 253:253 */    node.setOpen(true);
/* 254:254 */    this.open.add(node);
/* 255:    */  }
/* 256:    */  
/* 262:    */  protected boolean inOpenList(Node node)
/* 263:    */  {
/* 264:264 */    return node.isOpen();
/* 265:    */  }
/* 266:    */  
/* 271:    */  protected void removeFromOpen(Node node)
/* 272:    */  {
/* 273:273 */    node.setOpen(false);
/* 274:274 */    this.open.remove(node);
/* 275:    */  }
/* 276:    */  
/* 281:    */  protected void addToClosed(Node node)
/* 282:    */  {
/* 283:283 */    node.setClosed(true);
/* 284:284 */    this.closed.add(node);
/* 285:    */  }
/* 286:    */  
/* 292:    */  protected boolean inClosedList(Node node)
/* 293:    */  {
/* 294:294 */    return node.isClosed();
/* 295:    */  }
/* 296:    */  
/* 301:    */  protected void removeFromClosed(Node node)
/* 302:    */  {
/* 303:303 */    node.setClosed(false);
/* 304:304 */    this.closed.remove(node);
/* 305:    */  }
/* 306:    */  
/* 316:    */  protected boolean isValidLocation(Mover mover, int sx, int sy, int x, int y)
/* 317:    */  {
/* 318:318 */    boolean invalid = (x < 0) || (y < 0) || (x >= this.map.getWidthInTiles()) || (y >= this.map.getHeightInTiles());
/* 319:    */    
/* 320:320 */    if ((!invalid) && ((sx != x) || (sy != y))) {
/* 321:321 */      this.mover = mover;
/* 322:322 */      this.sourceX = sx;
/* 323:323 */      this.sourceY = sy;
/* 324:324 */      invalid = this.map.blocked(this, x, y);
/* 325:    */    }
/* 326:    */    
/* 327:327 */    return !invalid;
/* 328:    */  }
/* 329:    */  
/* 339:    */  public float getMovementCost(Mover mover, int sx, int sy, int tx, int ty)
/* 340:    */  {
/* 341:341 */    this.mover = mover;
/* 342:342 */    this.sourceX = sx;
/* 343:343 */    this.sourceY = sy;
/* 344:    */    
/* 345:345 */    return this.map.getCost(this, tx, ty);
/* 346:    */  }
/* 347:    */  
/* 358:    */  public float getHeuristicCost(Mover mover, int x, int y, int tx, int ty)
/* 359:    */  {
/* 360:360 */    return this.heuristic.getCost(this.map, mover, x, y, tx, ty);
/* 361:    */  }
/* 362:    */  
/* 368:    */  private class PriorityList
/* 369:    */  {
/* 370:370 */    private List list = new LinkedList();
/* 371:    */    
/* 373:    */    private PriorityList() {}
/* 374:    */    
/* 376:    */    public Object first()
/* 377:    */    {
/* 378:378 */      return this.list.get(0);
/* 379:    */    }
/* 380:    */    
/* 383:    */    public void clear()
/* 384:    */    {
/* 385:385 */      this.list.clear();
/* 386:    */    }
/* 387:    */    
/* 393:    */    public void add(Object o)
/* 394:    */    {
/* 395:395 */      for (int i = 0; i < this.list.size(); i++) {
/* 396:396 */        if (((Comparable)this.list.get(i)).compareTo(o) > 0) {
/* 397:397 */          this.list.add(i, o);
/* 398:398 */          break;
/* 399:    */        }
/* 400:    */      }
/* 401:401 */      if (!this.list.contains(o)) {
/* 402:402 */        this.list.add(o);
/* 403:    */      }
/* 404:    */    }
/* 405:    */    
/* 411:    */    public void remove(Object o)
/* 412:    */    {
/* 413:413 */      this.list.remove(o);
/* 414:    */    }
/* 415:    */    
/* 420:    */    public int size()
/* 421:    */    {
/* 422:422 */      return this.list.size();
/* 423:    */    }
/* 424:    */    
/* 430:    */    public boolean contains(Object o)
/* 431:    */    {
/* 432:432 */      return this.list.contains(o);
/* 433:    */    }
/* 434:    */    
/* 435:    */    public String toString() {
/* 436:436 */      String temp = "{";
/* 437:437 */      for (int i = 0; i < size(); i++) {
/* 438:438 */        temp = temp + this.list.get(i).toString() + ",";
/* 439:    */      }
/* 440:440 */      temp = temp + "}";
/* 441:    */      
/* 442:442 */      return temp;
/* 443:    */    }
/* 444:    */  }
/* 445:    */  
/* 448:    */  private class Node
/* 449:    */    implements Comparable
/* 450:    */  {
/* 451:    */    private int x;
/* 452:    */    
/* 454:    */    private int y;
/* 455:    */    
/* 457:    */    private float cost;
/* 458:    */    
/* 460:    */    private Node parent;
/* 461:    */    
/* 463:    */    private float heuristic;
/* 464:    */    
/* 465:    */    private int depth;
/* 466:    */    
/* 467:    */    private boolean open;
/* 468:    */    
/* 469:    */    private boolean closed;
/* 470:    */    
/* 472:    */    public Node(int x, int y)
/* 473:    */    {
/* 474:474 */      this.x = x;
/* 475:475 */      this.y = y;
/* 476:    */    }
/* 477:    */    
/* 483:    */    public int setParent(Node parent)
/* 484:    */    {
/* 485:485 */      parent.depth += 1;
/* 486:486 */      this.parent = parent;
/* 487:    */      
/* 488:488 */      return this.depth;
/* 489:    */    }
/* 490:    */    
/* 493:    */    public int compareTo(Object other)
/* 494:    */    {
/* 495:495 */      Node o = (Node)other;
/* 496:    */      
/* 497:497 */      float f = this.heuristic + this.cost;
/* 498:498 */      float of = o.heuristic + o.cost;
/* 499:    */      
/* 500:500 */      if (f < of)
/* 501:501 */        return -1;
/* 502:502 */      if (f > of) {
/* 503:503 */        return 1;
/* 504:    */      }
/* 505:505 */      return 0;
/* 506:    */    }
/* 507:    */    
/* 513:    */    public void setOpen(boolean open)
/* 514:    */    {
/* 515:515 */      this.open = open;
/* 516:    */    }
/* 517:    */    
/* 522:    */    public boolean isOpen()
/* 523:    */    {
/* 524:524 */      return this.open;
/* 525:    */    }
/* 526:    */    
/* 531:    */    public void setClosed(boolean closed)
/* 532:    */    {
/* 533:533 */      this.closed = closed;
/* 534:    */    }
/* 535:    */    
/* 540:    */    public boolean isClosed()
/* 541:    */    {
/* 542:542 */      return this.closed;
/* 543:    */    }
/* 544:    */    
/* 547:    */    public void reset()
/* 548:    */    {
/* 549:549 */      this.closed = false;
/* 550:550 */      this.open = false;
/* 551:551 */      this.cost = 0.0F;
/* 552:552 */      this.depth = 0;
/* 553:    */    }
/* 554:    */    
/* 557:    */    public String toString()
/* 558:    */    {
/* 559:559 */      return "[Node " + this.x + "," + this.y + "]";
/* 560:    */    }
/* 561:    */  }
/* 562:    */  
/* 565:    */  public Mover getMover()
/* 566:    */  {
/* 567:567 */    return this.mover;
/* 568:    */  }
/* 569:    */  
/* 572:    */  public int getSearchDistance()
/* 573:    */  {
/* 574:574 */    return this.distance;
/* 575:    */  }
/* 576:    */  
/* 579:    */  public int getSourceX()
/* 580:    */  {
/* 581:581 */    return this.sourceX;
/* 582:    */  }
/* 583:    */  
/* 586:    */  public int getSourceY()
/* 587:    */  {
/* 588:588 */    return this.sourceY;
/* 589:    */  }
/* 590:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.AStarPathFinder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */