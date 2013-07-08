/*   1:    */package org.schema.game.common.controller.elements;
/*   2:    */
/*   3:    */import ct;
/*   4:    */import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*   5:    */import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
/*   6:    */import it.unimi.dsi.fastutil.longs.LongArrayList;
/*   7:    */import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*   8:    */import jE;
/*   9:    */import ja;
/*  10:    */import java.io.PrintStream;
/*  11:    */import java.lang.reflect.InvocationTargetException;
/*  12:    */import java.util.ArrayList;
/*  13:    */import java.util.Iterator;
/*  14:    */import java.util.List;
/*  15:    */import ld;
/*  16:    */import le;
/*  17:    */import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*  18:    */import org.schema.game.common.controller.SegmentController;
/*  19:    */import org.schema.game.common.data.element.ElementCollection;
/*  20:    */import org.schema.game.common.data.element.ElementInformation;
/*  21:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  22:    */import org.schema.game.common.data.element.PointDistributionUnit;
/*  23:    */import org.schema.game.server.controller.GameServerController;
/*  24:    */import q;
/*  25:    */import vg;
/*  26:    */import x;
/*  27:    */import xq;
/*  28:    */
/*  42:    */public abstract class ElementCollectionManager
/*  43:    */{
/*  44:    */  private int debugID;
/*  45:    */  private static int debugIdGen;
/*  46: 46 */  private final List elementCollections = new ArrayList();
/*  47:    */  
/*  48: 48 */  public final LongOpenHashSet rawCollection = new LongOpenHashSet();
/*  49:    */  
/*  50:    */  private final SegmentController segmentController;
/*  51:    */  
/*  52:    */  private final short enhancerClazz;
/*  53: 53 */  private Object updateLock = new Object();
/*  54:    */  
/*  55:    */  private ElementCollectionCalculationThread finishedThread;
/*  56:    */  private LongArrayList scheduledListToUpdate;
/*  57: 57 */  private final LongArrayFIFOQueue modsA = new LongArrayFIFOQueue();
/*  58: 58 */  boolean modSwitch = false;
/*  59:    */  
/*  60:    */  private long lastDirtyTime;
/*  61:    */  
/*  62: 62 */  private long flagDirty = -1L;
/*  63: 63 */  private long updateDirty = -1L;
/*  64:    */  
/*  67: 67 */  private long updateStatus = -1L;
/*  68:    */  
/*  69: 69 */  private q absPosTmp = new q();
/*  70:    */  
/*  71: 71 */  private Long2ObjectOpenHashMap debug = new Long2ObjectOpenHashMap();
/*  72:    */  
/*  73:    */  public ElementCollectionManager(short paramShort, SegmentController paramSegmentController) {
/*  74: 74 */    this.debugID = (debugIdGen++);
/*  75: 75 */    this.enhancerClazz = paramShort;
/*  76: 76 */    this.segmentController = paramSegmentController;
/*  77:    */  }
/*  78:    */  
/*  79: 79 */  public void addModded(long paramLong) { synchronized (this.modsA) {
/*  80: 80 */      this.modsA.enqueue(paramLong);
/*  81: 81 */      return;
/*  82:    */    } }
/*  83:    */  
/*  84: 84 */  public void addModded(q paramq) { long l = ElementCollection.getIndex(paramq);
/*  85: 85 */    addModded(l);
/*  86:    */  }
/*  87:    */  
/*  88: 88 */  public void addModded(ja paramja) { synchronized (this.modsA)
/*  89:    */    {
/*  90: 90 */      this.modsA.enqueue(ElementCollection.getIndex(paramja.a, paramja.b, paramja.c));
/*  91: 91 */      return;
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  95: 95 */  public void addNew(ElementCollection paramElementCollection) { this.elementCollections.add(paramElementCollection); }
/*  96:    */  
/* 100:    */  public ElementCollection addNew(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController, long paramLong)
/* 101:    */  {
/* 102:102 */    (paramShort = newElementCollection(paramShort, paramElementCollectionManager, paramSegmentController)).addElement(paramLong);
/* 103:103 */    this.elementCollections.add(paramShort);
/* 104:104 */    return paramShort;
/* 105:    */  }
/* 106:    */  
/* 108:108 */  private q absPos = new q();
/* 109:    */  
/* 110:    */  protected long lastUpdate;
/* 111:    */  
/* 112:    */  protected long lastUpdateLocal;
/* 113:    */  private boolean stopped;
/* 114:    */  private long lastEnqueue;
/* 115:    */  protected static final long UPDATE_FREQUENCY_MS = 50L;
/* 116:    */  
/* 117:    */  private ElementCollection addToExistingCollection(long paramLong)
/* 118:    */  {
/* 119:119 */    ElementCollection.getPosFromIndex(paramLong, this.absPos);
/* 120:    */    
/* 121:121 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) { ElementCollection localElementCollection;
/* 122:122 */      if ((localElementCollection = (ElementCollection)localIterator.next()).isInsideBB(this.absPos, 1)) {
/* 123:123 */        for (int i = 0; i < 6; i++) {
/* 124:124 */          this.absPosTmp.b(this.absPos);
/* 125:125 */          this.absPosTmp.a(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
/* 126:126 */          if (localElementCollection.contains(this.absPosTmp)) {
/* 127:127 */            localElementCollection.addElement(paramLong);
/* 128:128 */            return localElementCollection;
/* 129:    */          }
/* 130:    */        }
/* 131:    */      }
/* 132:    */    }
/* 133:133 */    return null;
/* 134:    */  }
/* 135:    */  
/* 136:    */  private void checkEmpty() {
/* 137:137 */    ArrayList localArrayList = new ArrayList();
/* 138:138 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) { ElementCollection localElementCollection;
/* 139:139 */      if ((localElementCollection = (ElementCollection)localIterator.next()).size() <= 0) {
/* 140:140 */        localArrayList.add(localElementCollection);
/* 141:141 */        System.err.println("Element Collection is Empty -> removing");
/* 142:    */      }
/* 143:    */    }
/* 144:144 */    getCollection().removeAll(localArrayList);
/* 145:    */  }
/* 146:    */  
/* 147:147 */  private void checkOverlapping(ElementCollection paramElementCollection, long paramLong) { for (;;) { int i = 0;
/* 148:148 */      Object localObject = null;
/* 149:    */      
/* 150:150 */      ElementCollection.getPosFromIndex(paramLong, this.absPos);
/* 151:151 */      Iterator localIterator = getCollection().iterator(); do { if (!localIterator.hasNext()) break;
/* 152:152 */        ElementCollection localElementCollection; if (((localElementCollection = (ElementCollection)localIterator.next()) != paramElementCollection) && (localElementCollection.isInsideBB(this.absPos, 1))) {
/* 153:153 */          for (int j = 0; j < 6; j++) {
/* 154:154 */            this.absPosTmp.b(this.absPos);
/* 155:155 */            this.absPosTmp.a(org.schema.game.common.data.element.Element.DIRECTIONSi[j]);
/* 156:156 */            if (localElementCollection.contains(this.absPosTmp)) {
/* 157:157 */              localElementCollection.merge(paramElementCollection);
/* 158:158 */              i = 1;
/* 159:159 */              localObject = localElementCollection;
/* 160:    */              
/* 161:161 */              break;
/* 162:    */            }
/* 163:    */          }
/* 164:    */        }
/* 165:165 */      } while (i == 0);
/* 166:166 */      if (i == 0) {
/* 167:    */        break;
/* 168:    */      }
/* 169:    */      
/* 172:172 */      boolean bool = getCollection().remove(paramElementCollection);
/* 173:173 */      paramElementCollection.cleanUp();
/* 174:174 */      assert (bool);
/* 175:    */      
/* 177:177 */      paramElementCollection = localObject;this = this;
/* 178:    */    }
/* 179:    */  }
/* 180:    */  
/* 181:    */  public void clear()
/* 182:    */  {
/* 183:183 */    stopUpdate();
/* 184:184 */    collectionCleanUp();
/* 185:185 */    flagDirty();
/* 186:    */  }
/* 187:    */  
/* 190:    */  private void collectionCleanUp()
/* 191:    */  {
/* 192:192 */    long l1 = System.currentTimeMillis();
/* 193:    */    
/* 195:195 */    this.rawCollection.clear();
/* 196:196 */    this.modsA.clear();
/* 197:197 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 198:198 */      ((ElementCollection)localIterator.next()).cleanUp();
/* 199:    */    }
/* 200:200 */    getCollection().clear();
/* 201:    */    
/* 202:    */    long l2;
/* 203:203 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 204:204 */      System.err.println("[ELEMENTCOLLECTIONMANAGER][CLEAR] WARNING COLLECTION CLEANUP OF " + this.segmentController + " ON " + this.segmentController.getState() + " TOOK " + l2);
/* 205:    */    }
/* 206:    */  }
/* 207:    */  
/* 209:    */  public void doAdd(long paramLong)
/* 210:    */  {
/* 211:211 */    if (this.rawCollection.add(paramLong)) {
/* 212:212 */      flagDirty();
/* 213:    */    }
/* 214:    */  }
/* 215:    */  
/* 219:    */  public boolean doRemove(long paramLong)
/* 220:    */  {
/* 221:221 */    if ((paramLong = this.rawCollection.remove(paramLong))) {
/* 222:222 */      flagDirty();
/* 223:    */    }
/* 224:224 */    return paramLong;
/* 225:    */  }
/* 226:    */  
/* 228:    */  public void flagDirty()
/* 229:    */  {
/* 230:230 */    this.flagDirty += 1L;
/* 231:231 */    this.lastDirtyTime = System.currentTimeMillis();
/* 232:    */  }
/* 233:    */  
/* 234:    */  public List getCollection() {
/* 235:235 */    return this.elementCollections;
/* 236:    */  }
/* 237:    */  
/* 238:    */  public ManagerContainer getContainer()
/* 239:    */  {
/* 240:240 */    return ((ld)getSegmentController()).a();
/* 241:    */  }
/* 242:    */  
/* 244:    */  public abstract int getMargin();
/* 245:    */  
/* 246:    */  protected final SegmentController getSegmentController()
/* 247:    */  {
/* 248:248 */    return this.segmentController;
/* 249:    */  }
/* 250:    */  
/* 251:    */  protected abstract Class getType();
/* 252:    */  
/* 253:    */  public ElementCollection newElementCollection(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/* 254:    */  {
/* 255:    */    try
/* 256:    */    {
/* 257:257 */      (paramShort = ElementCollection.getInstanceOfT(getType(), this.enhancerClazz, paramElementCollectionManager, this.segmentController)).resetAABB();
/* 258:258 */      return paramShort;
/* 259:259 */    } catch (SecurityException localSecurityException) { localSecurityException;
/* 260:    */    } catch (IllegalArgumentException localIllegalArgumentException) {
/* 261:261 */      
/* 262:    */      
/* 271:271 */        localIllegalArgumentException;
/* 272:    */    } catch (InstantiationException localInstantiationException) {
/* 273:263 */      
/* 274:    */      
/* 281:271 */        localInstantiationException;
/* 282:    */    } catch (IllegalAccessException localIllegalAccessException) {
/* 283:265 */      
/* 284:    */      
/* 289:271 */        localIllegalAccessException;
/* 290:    */    } catch (NoSuchMethodException localNoSuchMethodException) {
/* 291:267 */      
/* 292:    */      
/* 295:271 */        localNoSuchMethodException;
/* 296:    */    } catch (InvocationTargetException localInvocationTargetException) {
/* 297:269 */      
/* 298:    */      
/* 299:271 */        localInvocationTargetException;
/* 300:    */    }
/* 301:    */    
/* 303:273 */    throw new RuntimeException("Cannot instantiate class: " + getType());
/* 304:    */  }
/* 305:    */  
/* 306:    */  public ElementCollection newElementCollection(short paramShort, SegmentController paramSegmentController)
/* 307:    */  {
/* 308:278 */    return newElementCollection(paramShort, this, paramSegmentController);
/* 309:    */  }
/* 310:    */  
/* 311:    */  protected abstract void onChangedCollection();
/* 312:    */  
/* 313:    */  private void onFinishedCollection()
/* 314:    */  {
/* 315:285 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 316:286 */      ((ElementCollection)localIterator.next()).onChangeFinished();
/* 317:    */    }
/* 318:288 */    pieceRefresh();
/* 319:    */  }
/* 320:    */  
/* 322:    */  protected void pieceRefresh() {}
/* 323:    */  
/* 324:    */  public boolean receiveDistribution(jE paramjE)
/* 325:    */  {
/* 326:296 */    for (ElementCollection localElementCollection : getCollection()) {
/* 327:    */      try
/* 328:    */      {
/* 329:299 */        if ((localElementCollection.getId() != null) && (localElementCollection.getId().a(new q()).equals(paramjE.b))) {
/* 330:300 */          ((PointDistributionUnit)localElementCollection).receiveDistChange(paramjE);
/* 331:301 */          return true;
/* 332:    */        }
/* 333:    */      }
/* 334:    */      catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {}
/* 335:    */    }
/* 336:    */    
/* 338:308 */    return false;
/* 339:    */  }
/* 340:    */  
/* 341:    */  public void remove(q paramq) {
/* 342:312 */    remove(ElementCollection.getIndex(paramq));
/* 343:    */  }
/* 344:    */  
/* 345:    */  public void remove(long paramLong) {
/* 346:316 */    synchronized (this.modsA) {
/* 347:317 */      this.modsA.enqueue(-paramLong);
/* 348:318 */      return;
/* 349:    */    }
/* 350:    */  }
/* 351:    */  
/* 352:    */  public String toString()
/* 353:    */  {
/* 354:324 */    return getSegmentController() + "->" + ElementKeyMap.getInfo(this.enhancerClazz).getName() + "(" + this.debugID + ")";
/* 355:    */  }
/* 356:    */  
/* 365:    */  protected void updateStructure(long arg1)
/* 366:    */  {
/* 367:337 */    if ((??? - this.lastUpdateLocal < 50L) && (this.scheduledListToUpdate == null)) {
/* 368:338 */      return;
/* 369:    */    }
/* 370:    */    
/* 371:341 */    this.lastUpdateLocal = ???;
/* 372:    */    
/* 373:343 */    if (!this.modsA.isEmpty()) {
/* 374:344 */      synchronized (this.modsA) {
/* 375:345 */        while (!this.modsA.isEmpty()) {
/* 376:    */          long l;
/* 377:347 */          if ((l = this.modsA.dequeueLong()) >= 0L) {
/* 378:348 */            doAdd(l);
/* 379:    */          } else {
/* 380:350 */            doRemove(-l);
/* 381:    */          }
/* 382:    */        }
/* 383:    */        
/* 384:354 */        this.modsA.clear();
/* 385:    */      }
/* 386:    */    }
/* 387:    */    
/* 391:361 */    updateCollections();
/* 392:    */  }
/* 393:    */  
/* 398:    */  public short getEnhancerClazz()
/* 399:    */  {
/* 400:370 */    return this.enhancerClazz;
/* 401:    */  }
/* 402:    */  
/* 403:    */  public abstract boolean needsUpdate();
/* 404:    */  
/* 405:    */  public void update(xq paramxq) {}
/* 406:    */  
/* 407:    */  public void updateCollections()
/* 408:    */  {
/* 409:379 */    synchronized (this.updateLock) {
/* 410:380 */      if (this.finishedThread != null) {
/* 411:381 */        this.finishedThread.apply();
/* 412:382 */        this.finishedThread = null;
/* 413:383 */        onChangedCollection();
/* 414:384 */        onFinishedCollection();
/* 415:    */      }
/* 416:    */      
/* 421:391 */      if (this.scheduledListToUpdate != null)
/* 422:    */      {
/* 426:396 */        this.scheduledListToUpdate.addAll(this.rawCollection);
/* 427:397 */        this.scheduledListToUpdate = null;
/* 428:398 */        this.updateLock.notify();
/* 429:    */      }
/* 430:    */      
/* 432:402 */      if ((this.flagDirty != this.updateStatus) && (System.currentTimeMillis() - this.lastUpdate > 1000L) && (System.currentTimeMillis() - this.lastEnqueue > 500L)) {
/* 433:403 */        this.lastEnqueue = System.currentTimeMillis();
/* 434:404 */        if (this.segmentController.isOnServer())
/* 435:    */        {
/* 436:406 */          ((vg)this.segmentController.getState()).a().a(this);
/* 437:    */        }
/* 438:    */        else {
/* 439:409 */          ((ct)this.segmentController.getState()).a().a(this);
/* 440:    */        }
/* 441:    */      }
/* 442:412 */      return;
/* 443:    */    }
/* 444:    */  }
/* 445:    */  
/* 446:416 */  public boolean flagPrepareUpdate(LongArrayList paramLongArrayList) { assert (paramLongArrayList != null);
/* 447:    */    
/* 448:418 */    synchronized (this.updateLock)
/* 449:    */    {
/* 450:420 */      this.flagDirty = this.updateStatus;
/* 451:421 */      this.lastUpdate = System.currentTimeMillis();
/* 452:422 */      this.scheduledListToUpdate = paramLongArrayList;
/* 453:    */      
/* 458:    */      try
/* 459:    */      {
/* 460:430 */        assert (this.scheduledListToUpdate != null);
/* 461:431 */        if (!this.stopped) {
/* 462:432 */          this.updateLock.wait(1000L);
/* 463:    */        } else {
/* 464:434 */          this.stopped = false;
/* 465:    */        }
/* 466:    */        
/* 467:437 */        if (this.scheduledListToUpdate != null) {
/* 468:438 */          getSegmentController().isOnServer();
/* 469:    */          
/* 479:449 */          this.scheduledListToUpdate = null;
/* 480:450 */          flagDirty();
/* 481:451 */          return false;
/* 482:    */        }
/* 483:453 */      } catch (InterruptedException localInterruptedException) { paramLongArrayList = null;
/* 484:    */        
/* 485:455 */        localInterruptedException.printStackTrace();
/* 486:    */      }
/* 487:    */    }
/* 488:    */    
/* 489:457 */    return true;
/* 490:    */  }
/* 491:    */  
/* 493:    */  public void stopUpdate()
/* 494:    */  {
/* 495:463 */    synchronized (this.updateLock) {
/* 496:464 */      this.stopped = true;
/* 497:465 */      this.updateLock.notify();
/* 498:466 */      return;
/* 499:    */    }
/* 500:    */  }
/* 501:    */  
/* 502:470 */  public void flagUpdateFinished(ElementCollectionCalculationThread paramElementCollectionCalculationThread) { synchronized (this.updateLock) {
/* 503:471 */      this.finishedThread = paramElementCollectionCalculationThread;
/* 504:472 */      return;
/* 505:    */    }
/* 506:    */  }
/* 507:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ElementCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */