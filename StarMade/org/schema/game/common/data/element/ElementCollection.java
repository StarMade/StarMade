/*   1:    */package org.schema.game.common.data.element;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   4:    */import it.unimi.dsi.fastutil.longs.LongArrayList;
/*   5:    */import jL;
/*   6:    */import java.io.DataOutputStream;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.PrintStream;
/*   9:    */import java.util.Collection;
/*  10:    */import java.util.Iterator;
/*  11:    */import java.util.Observable;
/*  12:    */import javax.vecmath.Vector3f;
/*  13:    */import kd;
/*  14:    */import le;
/*  15:    */import org.schema.game.common.controller.SegmentController;
/*  16:    */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  17:    */import org.schema.game.common.data.world.Segment;
/*  18:    */import q;
/*  19:    */
/*  23:    */public abstract class ElementCollection
/*  24:    */  extends Observable
/*  25:    */{
/*  26:    */  protected static final long SHORT_MAX2 = 65534L;
/*  27:    */  protected static final long SHORT_MAX2x2 = 4294705156L;
/*  28:    */  
/*  29:    */  public static ElementCollection getInstanceOfT(Class paramClass, short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/*  30:    */  {
/*  31: 31 */    (paramClass = (ElementCollection)paramClass.newInstance()).initialize(paramShort, paramElementCollectionManager, paramSegmentController);
/*  32:    */    
/*  33: 33 */    return paramClass;
/*  34:    */  }
/*  35:    */  
/*  36:    */  public static final long getIndex(q paramq)
/*  37:    */  {
/*  38: 38 */    return getIndex(paramq.a, paramq.b, paramq.c);
/*  39:    */  }
/*  40:    */  
/*  42:    */  public static final long getIndex(int paramInt1, int paramInt2, int paramInt3)
/*  43:    */  {
/*  44: 44 */    long l1 = paramInt1 + 32767;
/*  45: 45 */    long l2 = paramInt2 + 32767;
/*  46:    */    
/*  49:    */    long l3;
/*  50:    */    
/*  52: 52 */    if ((l3 = (paramInt3 + 32767) * 4294705156L + l2 * 65534L + l1) < 0L) {
/*  53: 53 */      throw new IllegalArgumentException("ElementCollection Index out of bounds: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + " -> " + l3);
/*  54:    */    }
/*  55:    */    
/*  56: 56 */    return l3;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public static q getPosFromIndex(long paramLong, q paramq) {
/*  60: 60 */    long l1 = paramLong / 4294705156L;
/*  61:    */    
/*  63: 63 */    long l2 = (paramLong = paramLong - l1 * 4294705156L) / 65534L;
/*  64: 64 */    paramLong -= l2 * 65534L;
/*  65:    */    
/*  66: 66 */    paramq.b((int)(paramLong - 32767L), (int)(l2 - 32767L), (int)(l1 - 32767L));
/*  67:    */    
/*  69: 69 */    return paramq;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public static void writeIndexAsShortPos(long paramLong, DataOutputStream paramDataOutputStream) {
/*  73: 73 */    long l1 = paramLong / 4294705156L;
/*  74:    */    
/*  76: 76 */    long l2 = (paramLong = paramLong - l1 * 4294705156L) / 65534L;
/*  77: 77 */    paramLong -= l2 * 65534L;
/*  78:    */    
/*  79: 79 */    paramDataOutputStream.writeShort((short)(int)(paramLong - 32767L));
/*  80:    */    
/*  82: 82 */    paramDataOutputStream.writeShort((short)(int)(l2 - 32767L));
/*  83: 83 */    paramDataOutputStream.writeShort((short)(int)(l1 - 32767L));
/*  84:    */  }
/*  85:    */  
/*  86: 86 */  public static void main(String[] paramArrayOfString) { paramArrayOfString = new q();
/*  87: 87 */    q localq = new q();
/*  88: 88 */    for (int i = -32767; i < 32767; i++) {
/*  89: 89 */      System.err.println("Z " + i);
/*  90: 90 */      for (int j = -32767; j < 32767; j++)
/*  91:    */      {
/*  92: 92 */        for (int k = -32767; k < 32767; k++) {
/*  93: 93 */          paramArrayOfString.b(k, j, i);
/*  94:    */          
/*  95:    */          long l;
/*  96: 96 */          getPosFromIndex(l = getIndex(paramArrayOfString), localq);
/*  97: 97 */          if (!paramArrayOfString.equals(localq)) {
/*  98: 98 */            throw new IllegalArgumentException(paramArrayOfString + "; " + localq + "; index " + l);
/*  99:    */          }
/* 100:    */        }
/* 101:    */      }
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 113:    */  public static boolean isOverlapping(ElementCollection paramElementCollection1, ElementCollection paramElementCollection2, int paramInt)
/* 114:    */  {
/* 115:115 */    Vector3f localVector3f1 = new Vector3f(paramElementCollection2.max.a + paramInt, paramElementCollection2.max.b + paramInt, paramElementCollection2.max.c + paramInt);
/* 116:116 */    paramElementCollection2 = new Vector3f(paramElementCollection2.min.a - paramInt, paramElementCollection2.min.b - paramInt, paramElementCollection2.min.c - paramInt);
/* 117:117 */    Vector3f localVector3f2 = new Vector3f(paramElementCollection1.max.a + paramInt, paramElementCollection1.max.b + paramInt, paramElementCollection1.max.c + paramInt);
/* 118:    */    
/* 124:124 */    return AabbUtil2.testAabbAgainstAabb2(new Vector3f(paramElementCollection1.min.a - paramInt, paramElementCollection1.min.b - paramInt, paramElementCollection1.min.c - paramInt), localVector3f2, paramElementCollection2, localVector3f1);
/* 125:    */  }
/* 126:    */  
/* 147:147 */  private final LongArrayList neighboringCollection = new LongArrayList();
/* 148:    */  
/* 150:150 */  private final q min = new q(2147483647, 2147483647, 2147483647);
/* 151:151 */  private final q max = new q(-2147483648, -2147483648, -2147483648);
/* 152:    */  
/* 153:    */  protected ElementCollectionManager col;
/* 154:    */  
/* 155:    */  private SegmentController controller;
/* 156:    */  private short clazzId;
/* 157:157 */  private final Vector3f halfSize = new Vector3f();
/* 158:158 */  private final q minScaled = new q();
/* 159:    */  
/* 160:160 */  private final q maxScaled = new q();
/* 161:161 */  private final q idPos = new q(2147483647, 2147483647, 2147483647);
/* 162:162 */  private le id = null;
/* 163:    */  
/* 164:164 */  private final q posTmp = new q();
/* 165:165 */  private boolean radiusChanged = true;
/* 166:    */  
/* 167:    */  private float radius;
/* 168:    */  
/* 169:    */  private boolean idChanged;
/* 170:    */  
/* 171:171 */  private q absPos = new q();
/* 172:    */  
/* 173:    */  public boolean addElement(long paramLong) { boolean bool;
/* 174:174 */    if ((bool = this.neighboringCollection.add(paramLong)))
/* 175:    */    {
/* 176:176 */      long l1 = paramLong / 4294705156L;
/* 177:    */      
/* 179:179 */      long l2 = (paramLong = paramLong - l1 * 4294705156L) / 65534L;
/* 180:    */      
/* 182:182 */      paramLong = (int)(paramLong - l2 * 65534L - 32767L);
/* 183:    */      
/* 185:185 */      int i = (int)(l2 - 32767L);
/* 186:186 */      int j = (int)(l1 - 32767L);
/* 187:    */      
/* 188:188 */      updateBB(paramLong, i, j);
/* 189:    */    }
/* 190:190 */    return bool;
/* 191:    */  }
/* 192:    */  
/* 193:    */  public void cleanUp() {
/* 194:194 */    deleteObservers();
/* 195:195 */    clear();
/* 196:    */  }
/* 197:    */  
/* 198:    */  public void clear() {
/* 199:199 */    this.neighboringCollection.clear();
/* 200:    */  }
/* 201:    */  
/* 207:    */  public boolean contains(q paramq)
/* 208:    */  {
/* 209:209 */    return this.neighboringCollection.contains(getIndex(paramq));
/* 210:    */  }
/* 211:    */  
/* 214:    */  public Vector3f getCenter(Vector3f paramVector3f)
/* 215:    */  {
/* 216:216 */    paramVector3f.set(this.max.a - Math.abs(this.max.a - this.min.a) / 2.0F, this.max.b - Math.abs(this.max.b - this.min.b) / 2.0F, this.max.c - Math.abs(this.max.c - this.min.c) / 2.0F);
/* 217:217 */    return paramVector3f;
/* 218:    */  }
/* 219:    */  
/* 220:    */  public le getId()
/* 221:    */  {
/* 222:222 */    if (this.idChanged) {
/* 223:    */      try {
/* 224:224 */        this.id = this.controller.getSegmentBuffer().a(this.idPos, true);
/* 225:225 */      } catch (IOException localIOException) { 
/* 226:    */        
/* 229:229 */          localIOException;
/* 230:    */      } catch (InterruptedException localInterruptedException) {
/* 231:227 */        
/* 232:    */        
/* 233:229 */          localInterruptedException;
/* 234:    */      }
/* 235:    */    }
/* 236:    */    
/* 237:231 */    return this.id;
/* 238:    */  }
/* 239:    */  
/* 241:    */  public q getMax()
/* 242:    */  {
/* 243:237 */    return this.max;
/* 244:    */  }
/* 245:    */  
/* 247:    */  public q getMin()
/* 248:    */  {
/* 249:243 */    return this.min;
/* 250:    */  }
/* 251:    */  
/* 253:247 */  public Collection getNeighboringCollection() { return this.neighboringCollection; }
/* 254:    */  
/* 255:    */  public Vector3f getOpenGLCenter(Vector3f paramVector3f) {
/* 256:250 */    paramVector3f.set(this.max.a - Math.abs(this.max.a - this.min.a) / 2.0F - 8.0F, this.max.b - Math.abs(this.max.b - this.min.b) / 2.0F - 8.0F, this.max.c - Math.abs(this.max.c - this.min.c) / 2.0F - 8.0F);
/* 257:    */    
/* 260:254 */    return paramVector3f;
/* 261:    */  }
/* 262:    */  
/* 263:    */  public float getRadius()
/* 264:    */  {
/* 265:259 */    if (this.radiusChanged) {
/* 266:260 */      this.minScaled.b(this.min);
/* 267:261 */      this.minScaled.c(this.col.getMargin(), this.col.getMargin(), this.col.getMargin());
/* 268:262 */      this.minScaled.c(kd.a);
/* 269:263 */      this.minScaled.a(1);
/* 270:    */      
/* 272:266 */      this.maxScaled.b(this.max);
/* 273:267 */      this.maxScaled.a(this.col.getMargin(), this.col.getMargin(), this.col.getMargin());
/* 274:268 */      this.maxScaled.c(kd.a);
/* 275:269 */      this.maxScaled.a(1);
/* 276:    */      
/* 278:272 */      this.halfSize.set((this.maxScaled.a - this.minScaled.a) / 2.0F, (this.maxScaled.b - this.minScaled.b) / 2.0F, (this.maxScaled.c - this.minScaled.c) / 2.0F);
/* 279:273 */      this.radiusChanged = false;
/* 280:274 */      this.radius = this.halfSize.length();
/* 281:    */    }
/* 282:276 */    return this.radius;
/* 283:    */  }
/* 284:    */  
/* 286:    */  public abstract q getSignificator();
/* 287:    */  
/* 289:    */  public void initialize(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/* 290:    */  {
/* 291:285 */    this.col = paramElementCollectionManager;
/* 292:286 */    this.controller = paramSegmentController;
/* 293:287 */    this.clazzId = paramShort;
/* 294:    */  }
/* 295:    */  
/* 304:    */  public boolean isInsideBB(q paramq)
/* 305:    */  {
/* 306:300 */    return isInsideBB(paramq, 0);
/* 307:    */  }
/* 308:    */  
/* 316:    */  public boolean isInsideBB(q paramq, int paramInt)
/* 317:    */  {
/* 318:312 */    if ((paramq.a >= this.min.a - paramInt) && (paramq.a <= this.max.a + paramInt) && (paramq.b >= this.min.b - paramInt) && (paramq.b <= this.max.b + paramInt) && (paramq.c >= this.min.c - paramInt) && (paramq.c <= this.max.c + paramInt)) { return true;
/* 319:    */    }
/* 320:    */    
/* 324:318 */    return false;
/* 325:    */  }
/* 326:    */  
/* 328:322 */  public void merge(ElementCollection paramElementCollection) { merge(paramElementCollection.neighboringCollection); }
/* 329:    */  
/* 330:324 */  q mPos = new q();
/* 331:    */  
/* 332:326 */  private void merge(Collection paramCollection) { if (paramCollection.size() > 0) {
/* 333:327 */      for (Iterator localIterator = paramCollection.iterator(); localIterator.hasNext();) { long l1;
/* 334:328 */        getPosFromIndex(l1 = ((Long)localIterator.next()).longValue(), this.mPos);
/* 335:329 */        long l2 = l1 / 4294705156L;
/* 336:    */        
/* 338:332 */        long l3 = (l1 = l1 - l2 * 4294705156L) / 65534L;
/* 339:    */        
/* 341:335 */        int i = (int)(l1 - l3 * 65534L - 32767L);
/* 342:    */        
/* 344:338 */        int j = (int)(l3 - 32767L);
/* 345:339 */        int k = (int)(l2 - 32767L);
/* 346:340 */        updateBB(i, j, k);
/* 347:    */      }
/* 348:342 */      this.neighboringCollection.addAll(paramCollection);
/* 349:    */    }
/* 350:    */  }
/* 351:    */  
/* 352:    */  public boolean narrowTest(ElementCollection paramElementCollection)
/* 353:    */  {
/* 354:348 */    for (paramElementCollection = paramElementCollection.neighboringCollection.iterator(); paramElementCollection.hasNext();) {
/* 355:349 */      getPosFromIndex(((Long)paramElementCollection.next()).longValue(), this.absPos);
/* 356:350 */      for (int i = 0; i < 6; i++)
/* 357:    */      {
/* 358:352 */        this.posTmp.b(this.absPos);
/* 359:353 */        this.posTmp.a(Element.DIRECTIONSi[i]);
/* 360:354 */        if (this.neighboringCollection.contains(getIndex(this.posTmp)))
/* 361:    */        {
/* 365:359 */          return true;
/* 366:    */        }
/* 367:    */      }
/* 368:    */    }
/* 369:    */    
/* 370:364 */    return false;
/* 371:    */  }
/* 372:    */  
/* 374:    */  public void onChangeFinished() {}
/* 375:    */  
/* 376:    */  public void onRemove(q paramq) {}
/* 377:    */  
/* 378:    */  public boolean overlaps(ElementCollection paramElementCollection, int paramInt)
/* 379:    */  {
/* 380:374 */    return isOverlapping(this, paramElementCollection, paramInt);
/* 381:    */  }
/* 382:    */  
/* 396:    */  public boolean remove(q paramq)
/* 397:    */  {
/* 398:    */    boolean bool;
/* 399:    */    
/* 411:405 */    if ((bool = this.neighboringCollection.remove(paramq))) {
/* 412:406 */      updateBBFull();
/* 413:407 */      onRemove(paramq);
/* 414:    */    }
/* 415:409 */    return bool; }
/* 416:    */  
/* 417:    */  protected abstract void significatorUpdate(int paramInt1, int paramInt2, int paramInt3);
/* 418:    */  
/* 419:413 */  public int size() { return this.neighboringCollection.size(); }
/* 420:    */  
/* 421:    */  public String toString()
/* 422:    */  {
/* 423:417 */    return getClass().getSimpleName() + hashCode() + "[" + getMin() + "/" + getMax() + "](" + this.neighboringCollection.size() + ")";
/* 424:    */  }
/* 425:    */  
/* 426:    */  private void updateBBFull() {
/* 427:421 */    if (this.neighboringCollection.isEmpty()) {
/* 428:422 */      resetAABB();
/* 429:    */    }
/* 430:424 */    getSignificator().b(2147483647, 2147483647, 2147483647);
/* 431:425 */    this.min.b(2147483647, 2147483647, 2147483647);
/* 432:426 */    this.max.b(-2147483648, -2147483648, -2147483648);
/* 433:    */    
/* 434:428 */    this.idPos.b(2147483647, 2147483647, 2147483647);
/* 435:429 */    for (Iterator localIterator = this.neighboringCollection.iterator(); localIterator.hasNext();) {
/* 436:430 */      getPosFromIndex(((Long)localIterator.next()).longValue(), this.absPos);
/* 437:431 */      if ((this.absPos.a < this.idPos.a) || ((this.absPos.a == this.idPos.a) && (this.absPos.b < this.idPos.b)) || ((this.absPos.a == this.idPos.a) && (this.absPos.b == this.idPos.b) && (this.absPos.c < this.idPos.c))) {
/* 438:432 */        this.idPos.b(this.absPos);
/* 439:    */      }
/* 440:    */      
/* 441:435 */      this.min.a = Math.min(this.absPos.a, this.min.a);
/* 442:436 */      this.min.b = Math.min(this.absPos.b, this.min.b);
/* 443:437 */      this.min.c = Math.min(this.absPos.c, this.min.c);
/* 444:    */      
/* 445:439 */      this.max.a = Math.max(this.absPos.a, this.max.a);
/* 446:440 */      this.max.b = Math.max(this.absPos.b, this.max.b);
/* 447:441 */      this.max.c = Math.max(this.absPos.c, this.max.c);
/* 448:    */      
/* 449:443 */      significatorUpdate(this.absPos.a, this.absPos.b, this.absPos.c);
/* 450:    */    }
/* 451:    */    
/* 452:446 */    this.id = this.controller.getSegmentBuffer().a(this.idPos, true);
/* 453:    */    
/* 454:448 */    this.radiusChanged = true;
/* 455:    */  }
/* 456:    */  
/* 457:    */  public void resetAABB() {
/* 458:452 */    getSignificator().b(0, 0, 0);
/* 459:453 */    this.min.b(2147483647, 2147483647, 2147483647);
/* 460:454 */    this.max.b(-2147483648, -2147483648, -2147483648);
/* 461:455 */    this.id = null;
/* 462:456 */    this.minScaled.b(0, 0, 0);
/* 463:457 */    this.maxScaled.b(0, 0, 0);
/* 464:458 */    this.halfSize.set(0.0F, 0.0F, 0.0F);
/* 465:459 */    this.idPos.b(2147483647, 2147483647, 2147483647);
/* 466:460 */    this.radiusChanged = true;
/* 467:461 */    this.idChanged = true;
/* 468:    */  }
/* 469:    */  
/* 473:    */  protected void updateBB(int paramInt1, int paramInt2, int paramInt3)
/* 474:    */  {
/* 475:469 */    if ((paramInt1 < this.idPos.a) || (paramInt2 < this.idPos.b) || (paramInt3 < this.idPos.c)) {
/* 476:470 */      this.idPos.b(paramInt1, paramInt2, paramInt3);
/* 477:471 */      this.idChanged = true;
/* 478:    */    }
/* 479:473 */    this.min.a = Math.min(paramInt1, this.min.a);
/* 480:474 */    this.min.b = Math.min(paramInt2, this.min.b);
/* 481:475 */    this.min.c = Math.min(paramInt3, this.min.c);
/* 482:    */    
/* 483:477 */    this.max.a = Math.max(paramInt1, this.max.a);
/* 484:478 */    this.max.b = Math.max(paramInt2, this.max.b);
/* 485:479 */    this.max.c = Math.max(paramInt3, this.max.c);
/* 486:    */    
/* 487:481 */    significatorUpdate(paramInt1, paramInt2, paramInt3);
/* 488:482 */    this.radiusChanged = true;
/* 489:    */  }
/* 490:    */  
/* 493:    */  public short getClazzId()
/* 494:    */  {
/* 495:489 */    return this.clazzId;
/* 496:    */  }
/* 497:    */  
/* 499:    */  public SegmentController getController()
/* 500:    */  {
/* 501:495 */    return this.controller;
/* 502:    */  }
/* 503:    */  
/* 504:498 */  public static long getIndex(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment) { return getIndex(paramSegment.a.a + paramByte1, paramSegment.a.b + paramByte2, paramSegment.a.c + paramByte3); }
/* 505:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */