/*     */ package org.schema.game.common.controller.elements;
/*     */ 
/*     */ import ct;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
/*     */ import it.unimi.dsi.fastutil.longs.LongArrayList;
/*     */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*     */ import jE;
/*     */ import ja;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import ld;
/*     */ import le;
/*     */ import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.element.PointDistributionUnit;
/*     */ import org.schema.game.server.controller.GameServerController;
/*     */ import q;
/*     */ import vg;
/*     */ import x;
/*     */ import xq;
/*     */ 
/*     */ public abstract class ElementCollectionManager
/*     */ {
/*     */   private int debugID;
/*     */   private static int debugIdGen;
/*  46 */   private final List elementCollections = new ArrayList();
/*     */ 
/*  48 */   public final LongOpenHashSet rawCollection = new LongOpenHashSet();
/*     */   private final SegmentController segmentController;
/*     */   private final short enhancerClazz;
/*  53 */   private Object updateLock = new Object();
/*     */   private ElementCollectionCalculationThread finishedThread;
/*     */   private LongArrayList scheduledListToUpdate;
/*  57 */   private final LongArrayFIFOQueue modsA = new LongArrayFIFOQueue();
/*  58 */   boolean modSwitch = false;
/*     */   private long lastDirtyTime;
/*  62 */   private long flagDirty = -1L;
/*  63 */   private long updateDirty = -1L;
/*     */ 
/*  67 */   private long updateStatus = -1L;
/*     */ 
/*  69 */   private q absPosTmp = new q();
/*     */ 
/*  71 */   private Long2ObjectOpenHashMap debug = new Long2ObjectOpenHashMap();
/*     */ 
/* 108 */   private q absPos = new q();
/*     */   protected long lastUpdate;
/*     */   protected long lastUpdateLocal;
/*     */   private boolean stopped;
/*     */   private long lastEnqueue;
/*     */   protected static final long UPDATE_FREQUENCY_MS = 50L;
/*     */ 
/*     */   public ElementCollectionManager(short paramShort, SegmentController paramSegmentController)
/*     */   {
/*  74 */     this.debugID = (debugIdGen++);
/*  75 */     this.enhancerClazz = paramShort;
/*  76 */     this.segmentController = paramSegmentController;
/*     */   }
/*     */   public void addModded(long paramLong) {
/*  79 */     synchronized (this.modsA) {
/*  80 */       this.modsA.enqueue(paramLong);
/*  81 */       return;
/*     */     }
/*     */   }
/*  84 */   public void addModded(q paramq) { long l = ElementCollection.getIndex(paramq);
/*  85 */     addModded(l); }
/*     */ 
/*     */   public void addModded(ja paramja) {
/*  88 */     synchronized (this.modsA)
/*     */     {
/*  90 */       this.modsA.enqueue(ElementCollection.getIndex(paramja.a, paramja.b, paramja.c));
/*  91 */       return;
/*     */     }
/*     */   }
/*     */ 
/*  95 */   public void addNew(ElementCollection paramElementCollection) { this.elementCollections.add(paramElementCollection); }
/*     */ 
/*     */ 
/*     */   public ElementCollection addNew(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController, long paramLong)
/*     */   {
/* 101 */     (
/* 102 */       paramShort = newElementCollection(paramShort, paramElementCollectionManager, paramSegmentController))
/* 102 */       .addElement(paramLong);
/* 103 */     this.elementCollections.add(paramShort);
/* 104 */     return paramShort;
/*     */   }
/*     */ 
/*     */   private ElementCollection addToExistingCollection(long paramLong)
/*     */   {
/* 119 */     ElementCollection.getPosFromIndex(paramLong, this.absPos);
/*     */ 
/* 121 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       ElementCollection localElementCollection;
/* 122 */       if ((
/* 122 */         localElementCollection = (ElementCollection)localIterator.next())
/* 122 */         .isInsideBB(this.absPos, 1)) {
/* 123 */         for (int i = 0; i < 6; i++) {
/* 124 */           this.absPosTmp.b(this.absPos);
/* 125 */           this.absPosTmp.a(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
/* 126 */           if (localElementCollection.contains(this.absPosTmp)) {
/* 127 */             localElementCollection.addElement(paramLong);
/* 128 */             return localElementCollection;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */   private void checkEmpty() {
/* 137 */     ArrayList localArrayList = new ArrayList();
/* 138 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       ElementCollection localElementCollection;
/* 139 */       if ((
/* 139 */         localElementCollection = (ElementCollection)localIterator.next())
/* 139 */         .size() <= 0) {
/* 140 */         localArrayList.add(localElementCollection);
/* 141 */         System.err.println("Element Collection is Empty -> removing");
/*     */       }
/*     */     }
/* 144 */     getCollection().removeAll(localArrayList);
/*     */   }
/*     */   private void checkOverlapping(ElementCollection paramElementCollection, long paramLong) {
/*     */     while (true) { int i = 0;
/* 148 */       Object localObject = null;
/*     */ 
/* 150 */       ElementCollection.getPosFromIndex(paramLong, this.absPos);
/* 151 */       Iterator localIterator = getCollection().iterator();
/*     */       do { if (!localIterator.hasNext())
/*     */           break;
/*     */         ElementCollection localElementCollection;
/* 152 */         if (((
/* 152 */           localElementCollection = (ElementCollection)localIterator.next()) != 
/* 152 */           paramElementCollection) && (localElementCollection.isInsideBB(this.absPos, 1)))
/* 153 */           for (int j = 0; j < 6; j++) {
/* 154 */             this.absPosTmp.b(this.absPos);
/* 155 */             this.absPosTmp.a(org.schema.game.common.data.element.Element.DIRECTIONSi[j]);
/* 156 */             if (localElementCollection.contains(this.absPosTmp)) {
/* 157 */               localElementCollection.merge(paramElementCollection);
/* 158 */               i = 1;
/* 159 */               localObject = localElementCollection;
/*     */ 
/* 161 */               break;
/*     */             }
/*     */           }
/*     */       }
/* 165 */       while (i == 0);
/* 166 */       if (i == 0)
/*     */       {
/*     */         break;
/*     */       }
/*     */ 
/* 172 */       boolean bool = getCollection().remove(paramElementCollection);
/* 173 */       paramElementCollection.cleanUp();
/* 174 */       assert (bool);
/*     */ 
/* 177 */       paramElementCollection = localObject; this = this;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 183 */     stopUpdate();
/* 184 */     collectionCleanUp();
/* 185 */     flagDirty();
/*     */   }
/*     */ 
/*     */   private void collectionCleanUp()
/*     */   {
/* 192 */     long l1 = System.currentTimeMillis();
/*     */ 
/* 195 */     this.rawCollection.clear();
/* 196 */     this.modsA.clear();
/* 197 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); ) ((ElementCollection)localIterator.next())
/* 198 */         .cleanUp();
/*     */ 
/* 200 */     getCollection().clear();
/*     */     long l2;
/* 203 */     if ((
/* 203 */       l2 = System.currentTimeMillis() - l1) > 
/* 203 */       10L)
/* 204 */       System.err.println("[ELEMENTCOLLECTIONMANAGER][CLEAR] WARNING COLLECTION CLEANUP OF " + this.segmentController + " ON " + this.segmentController.getState() + " TOOK " + l2);
/*     */   }
/*     */ 
/*     */   public void doAdd(long paramLong)
/*     */   {
/* 211 */     if (this.rawCollection.add(paramLong))
/*     */     {
/* 212 */       flagDirty();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean doRemove(long paramLong)
/*     */   {
/* 221 */     if ((
/* 221 */       paramLong = this.rawCollection.remove(paramLong)))
/*     */     {
/* 222 */       flagDirty();
/*     */     }
/* 224 */     return paramLong;
/*     */   }
/*     */ 
/*     */   public void flagDirty()
/*     */   {
/* 230 */     this.flagDirty += 1L;
/* 231 */     this.lastDirtyTime = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   public List getCollection() {
/* 235 */     return this.elementCollections;
/*     */   }
/*     */ 
/*     */   public ManagerContainer getContainer()
/*     */   {
/* 240 */     return ((ld)getSegmentController()).a();
/*     */   }
/*     */ 
/*     */   public abstract int getMargin();
/*     */ 
/*     */   protected final SegmentController getSegmentController()
/*     */   {
/* 248 */     return this.segmentController;
/*     */   }
/*     */ 
/*     */   protected abstract Class getType();
/*     */ 
/*     */   public ElementCollection newElementCollection(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/*     */   {
/*     */     try {
/* 256 */       (
/* 257 */         paramShort = ElementCollection.getInstanceOfT(getType(), this.enhancerClazz, paramElementCollectionManager, this.segmentController))
/* 257 */         .resetAABB();
/* 258 */       return paramShort; } catch (SecurityException localSecurityException) { localSecurityException
/* 259 */         .printStackTrace();
/*     */     }
/*     */     catch (IllegalArgumentException localIllegalArgumentException)
/*     */     {
/* 271 */       localIllegalArgumentException.printStackTrace();
/*     */     }
/*     */     catch (InstantiationException localInstantiationException)
/*     */     {
/* 271 */       localInstantiationException.printStackTrace();
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException)
/*     */     {
/* 271 */       localIllegalAccessException.printStackTrace();
/*     */     }
/*     */     catch (NoSuchMethodException localNoSuchMethodException)
/*     */     {
/* 271 */       localNoSuchMethodException.printStackTrace();
/*     */     }
/*     */     catch (InvocationTargetException localInvocationTargetException)
/*     */     {
/* 271 */       localInvocationTargetException.printStackTrace();
/*     */     }
/*     */ 
/* 273 */     throw new RuntimeException("Cannot instantiate class: " + getType());
/*     */   }
/*     */ 
/*     */   public ElementCollection newElementCollection(short paramShort, SegmentController paramSegmentController)
/*     */   {
/* 278 */     return newElementCollection(paramShort, this, paramSegmentController);
/*     */   }
/*     */ 
/*     */   protected abstract void onChangedCollection();
/*     */ 
/*     */   private void onFinishedCollection()
/*     */   {
/* 285 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); ) ((ElementCollection)localIterator.next())
/* 286 */         .onChangeFinished();
/*     */ 
/* 288 */     pieceRefresh();
/*     */   }
/*     */ 
/*     */   protected void pieceRefresh()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean receiveDistribution(jE paramjE) {
/* 296 */     for (ElementCollection localElementCollection : getCollection()) {
/*     */       try
/*     */       {
/* 299 */         if ((localElementCollection.getId() != null) && (localElementCollection.getId().a(new q()).equals(paramjE.b))) {
/* 300 */           ((PointDistributionUnit)localElementCollection).receiveDistChange(paramjE);
/* 301 */           return true;
/*     */         }
/*     */       }
/*     */       catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
/*     */       {
/*     */       }
/*     */     }
/* 308 */     return false;
/*     */   }
/*     */ 
/*     */   public void remove(q paramq) {
/* 312 */     remove(ElementCollection.getIndex(paramq));
/*     */   }
/*     */ 
/*     */   public void remove(long paramLong) {
/* 316 */     synchronized (this.modsA) {
/* 317 */       this.modsA.enqueue(-paramLong);
/* 318 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 324 */     return getSegmentController() + "->" + ElementKeyMap.getInfo(this.enhancerClazz).getName() + "(" + this.debugID + ")";
/*     */   }
/*     */ 
/*     */   protected void updateStructure(long arg1)
/*     */   {
/* 337 */     if ((??? - this.lastUpdateLocal < 50L) && (this.scheduledListToUpdate == null)) {
/* 338 */       return;
/*     */     }
/*     */ 
/* 341 */     this.lastUpdateLocal = ???;
/*     */ 
/* 343 */     if (!this.modsA.isEmpty()) {
/* 344 */       synchronized (this.modsA) {
/* 345 */         while (!this.modsA.isEmpty())
/*     */         {
/*     */           long l;
/* 347 */           if ((
/* 347 */             l = this.modsA.dequeueLong()) >= 
/* 347 */             0L)
/* 348 */             doAdd(l);
/*     */           else {
/* 350 */             doRemove(-l);
/*     */           }
/*     */         }
/*     */ 
/* 354 */         this.modsA.clear();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 361 */     updateCollections();
/*     */   }
/*     */ 
/*     */   public short getEnhancerClazz()
/*     */   {
/* 370 */     return this.enhancerClazz;
/*     */   }
/*     */ 
/*     */   public abstract boolean needsUpdate();
/*     */ 
/*     */   public void update(xq paramxq) {
/*     */   }
/*     */ 
/*     */   public void updateCollections() {
/* 379 */     synchronized (this.updateLock) {
/* 380 */       if (this.finishedThread != null) {
/* 381 */         this.finishedThread.apply();
/* 382 */         this.finishedThread = null;
/* 383 */         onChangedCollection();
/* 384 */         onFinishedCollection();
/*     */       }
/*     */ 
/* 391 */       if (this.scheduledListToUpdate != null)
/*     */       {
/* 396 */         this.scheduledListToUpdate.addAll(this.rawCollection);
/* 397 */         this.scheduledListToUpdate = null;
/* 398 */         this.updateLock.notify();
/*     */       }
/*     */ 
/* 402 */       if ((this.flagDirty != this.updateStatus) && (System.currentTimeMillis() - this.lastUpdate > 1000L) && (System.currentTimeMillis() - this.lastEnqueue > 500L)) {
/* 403 */         this.lastEnqueue = System.currentTimeMillis();
/* 404 */         if (this.segmentController.isOnServer())
/* 405 */           ((vg)this.segmentController.getState())
/* 406 */             .a().a(this);
/*     */         else {
/* 408 */           ((ct)this.segmentController.getState())
/* 409 */             .a().a(this);
/*     */         }
/*     */       }
/* 412 */       return;
/*     */     }
/*     */   }
/*     */ 
/* 416 */   public boolean flagPrepareUpdate(LongArrayList paramLongArrayList) { assert (paramLongArrayList != null);
/*     */ 
/* 418 */     synchronized (this.updateLock)
/*     */     {
/* 420 */       this.flagDirty = this.updateStatus;
/* 421 */       this.lastUpdate = System.currentTimeMillis();
/* 422 */       this.scheduledListToUpdate = paramLongArrayList;
/*     */       try
/*     */       {
/* 430 */         assert (this.scheduledListToUpdate != null);
/* 431 */         if (!this.stopped)
/* 432 */           this.updateLock.wait(1000L);
/*     */         else {
/* 434 */           this.stopped = false;
/*     */         }
/*     */ 
/* 437 */         if (this.scheduledListToUpdate != null) {
/* 438 */           getSegmentController().isOnServer();
/*     */ 
/* 449 */           this.scheduledListToUpdate = null;
/* 450 */           flagDirty();
/* 451 */           return false;
/*     */         }
/*     */       } catch (InterruptedException localInterruptedException) { paramLongArrayList = null;
/*     */ 
/* 455 */         localInterruptedException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 457 */     return true;
/*     */   }
/*     */ 
/*     */   public void stopUpdate()
/*     */   {
/* 463 */     synchronized (this.updateLock) {
/* 464 */       this.stopped = true;
/* 465 */       this.updateLock.notify();
/* 466 */       return;
/*     */     }
/*     */   }
/*     */ 
/* 470 */   public void flagUpdateFinished(ElementCollectionCalculationThread paramElementCollectionCalculationThread) { synchronized (this.updateLock) {
/* 471 */       this.finishedThread = paramElementCollectionCalculationThread;
/* 472 */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ElementCollectionManager
 * JD-Core Version:    0.6.2
 */