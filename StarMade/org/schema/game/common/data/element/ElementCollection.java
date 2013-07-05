/*     */ package org.schema.game.common.data.element;
/*     */ 
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import it.unimi.dsi.fastutil.longs.LongArrayList;
/*     */ import jL;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Observable;
/*     */ import javax.vecmath.Vector3f;
/*     */ import kd;
/*     */ import le;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import q;
/*     */ 
/*     */ public abstract class ElementCollection extends Observable
/*     */ {
/*     */   protected static final long SHORT_MAX2 = 65534L;
/*     */   protected static final long SHORT_MAX2x2 = 4294705156L;
/* 147 */   private final LongArrayList neighboringCollection = new LongArrayList();
/*     */ 
/* 150 */   private final q min = new q(2147483647, 2147483647, 2147483647);
/* 151 */   private final q max = new q(-2147483648, -2147483648, -2147483648);
/*     */   protected ElementCollectionManager col;
/*     */   private SegmentController controller;
/*     */   private short clazzId;
/* 157 */   private final Vector3f halfSize = new Vector3f();
/* 158 */   private final q minScaled = new q();
/*     */ 
/* 160 */   private final q maxScaled = new q();
/* 161 */   private final q idPos = new q(2147483647, 2147483647, 2147483647);
/* 162 */   private le id = null;
/*     */ 
/* 164 */   private final q posTmp = new q();
/* 165 */   private boolean radiusChanged = true;
/*     */   private float radius;
/*     */   private boolean idChanged;
/* 171 */   private q absPos = new q();
/*     */ 
/* 324 */   q mPos = new q();
/*     */ 
/*     */   public static ElementCollection getInstanceOfT(Class paramClass, short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/*     */   {
/*  30 */     (
/*  31 */       paramClass = (ElementCollection)paramClass.newInstance())
/*  31 */       .initialize(paramShort, paramElementCollectionManager, paramSegmentController);
/*     */ 
/*  33 */     return paramClass;
/*     */   }
/*     */ 
/*     */   public static final long getIndex(q paramq)
/*     */   {
/*  38 */     return getIndex(paramq.a, paramq.b, paramq.c);
/*     */   }
/*     */ 
/*     */   public static final long getIndex(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  44 */     long l1 = paramInt1 + 32767;
/*  45 */     long l2 = paramInt2 + 32767;
/*     */     long l3;
/*  52 */     if ((
/*  52 */       l3 = (paramInt3 + 32767) * 
/*  50 */       4294705156L + l2 * 65534L + l1) < 
/*  52 */       0L) {
/*  53 */       throw new IllegalArgumentException("ElementCollection Index out of bounds: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + " -> " + l3);
/*     */     }
/*     */ 
/*  56 */     return l3;
/*     */   }
/*     */ 
/*     */   public static q getPosFromIndex(long paramLong, q paramq) {
/*  60 */     long l1 = paramLong / 4294705156L;
/*     */ 
/*  63 */     long l2 = (
/*  63 */       paramLong = paramLong - l1 * 4294705156L) / 
/*  63 */       65534L;
/*  64 */     paramLong -= l2 * 65534L;
/*     */ 
/*  66 */     paramq.b((int)(paramLong - 32767L), (int)(l2 - 32767L), (int)(l1 - 32767L));
/*     */ 
/*  69 */     return paramq;
/*     */   }
/*     */ 
/*     */   public static void writeIndexAsShortPos(long paramLong, DataOutputStream paramDataOutputStream) {
/*  73 */     long l1 = paramLong / 4294705156L;
/*     */ 
/*  76 */     long l2 = (
/*  76 */       paramLong = paramLong - l1 * 4294705156L) / 
/*  76 */       65534L;
/*  77 */     paramLong -= l2 * 65534L;
/*     */ 
/*  79 */     paramDataOutputStream.writeShort((short)(int)(paramLong - 32767L));
/*     */ 
/*  82 */     paramDataOutputStream.writeShort((short)(int)(l2 - 32767L));
/*  83 */     paramDataOutputStream.writeShort((short)(int)(l1 - 32767L));
/*     */   }
/*     */   public static void main(String[] paramArrayOfString) {
/*  86 */     paramArrayOfString = new q();
/*  87 */     q localq = new q();
/*  88 */     for (int i = -32767; i < 32767; i++) {
/*  89 */       System.err.println("Z " + i);
/*  90 */       for (int j = -32767; j < 32767; j++)
/*     */       {
/*  92 */         for (int k = -32767; k < 32767; k++) {
/*  93 */           paramArrayOfString.b(k, j, i);
/*     */           long l;
/*  96 */           getPosFromIndex(l = getIndex(paramArrayOfString), 
/*  96 */             localq);
/*  97 */           if (!paramArrayOfString.equals(localq))
/*  98 */             throw new IllegalArgumentException(paramArrayOfString + "; " + localq + "; index " + l);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isOverlapping(ElementCollection paramElementCollection1, ElementCollection paramElementCollection2, int paramInt)
/*     */   {
/* 115 */     Vector3f localVector3f1 = new Vector3f(paramElementCollection2.max.a + paramInt, paramElementCollection2.max.b + paramInt, paramElementCollection2.max.c + paramInt);
/* 116 */     paramElementCollection2 = new Vector3f(paramElementCollection2.min.a - paramInt, paramElementCollection2.min.b - paramInt, paramElementCollection2.min.c - paramInt);
/* 117 */     Vector3f localVector3f2 = new Vector3f(paramElementCollection1.max.a + paramInt, paramElementCollection1.max.b + paramInt, paramElementCollection1.max.c + paramInt);
/*     */ 
/* 124 */     return AabbUtil2.testAabbAgainstAabb2(new Vector3f(paramElementCollection1.min.a - paramInt, paramElementCollection1.min.b - paramInt, paramElementCollection1.min.c - paramInt), 
/* 124 */       localVector3f2, paramElementCollection2, localVector3f1);
/*     */   }
/*     */ 
/*     */   public boolean addElement(long paramLong)
/*     */   {
/*     */     boolean bool;
/* 174 */     if ((
/* 174 */       bool = this.neighboringCollection.add(paramLong)))
/*     */     {
/* 176 */       long l1 = paramLong / 4294705156L;
/*     */ 
/* 179 */       long l2 = (
/* 179 */         paramLong = paramLong - l1 * 4294705156L) / 
/* 179 */         65534L;
/*     */ 
/* 182 */       paramLong = (int)(paramLong - l2 * 65534L - 
/* 182 */         32767L);
/*     */ 
/* 185 */       int i = (int)(l2 - 32767L);
/* 186 */       int j = (int)(l1 - 32767L);
/*     */ 
/* 188 */       updateBB(paramLong, i, j);
/*     */     }
/* 190 */     return bool;
/*     */   }
/*     */ 
/*     */   public void cleanUp() {
/* 194 */     deleteObservers();
/* 195 */     clear();
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 199 */     this.neighboringCollection.clear();
/*     */   }
/*     */ 
/*     */   public boolean contains(q paramq)
/*     */   {
/* 209 */     return this.neighboringCollection.contains(getIndex(paramq));
/*     */   }
/*     */ 
/*     */   public Vector3f getCenter(Vector3f paramVector3f)
/*     */   {
/* 216 */     paramVector3f.set(this.max.a - Math.abs(this.max.a - this.min.a) / 2.0F, this.max.b - Math.abs(this.max.b - this.min.b) / 2.0F, this.max.c - Math.abs(this.max.c - this.min.c) / 2.0F);
/* 217 */     return paramVector3f;
/*     */   }
/*     */ 
/*     */   public le getId()
/*     */   {
/* 222 */     if (this.idChanged) {
/*     */       try {
/* 224 */         this.id = this.controller.getSegmentBuffer().a(this.idPos, true);
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/* 229 */         localIOException.printStackTrace();
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 229 */         localInterruptedException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 231 */     return this.id;
/*     */   }
/*     */ 
/*     */   public q getMax()
/*     */   {
/* 237 */     return this.max;
/*     */   }
/*     */ 
/*     */   public q getMin()
/*     */   {
/* 243 */     return this.min;
/*     */   }
/*     */ 
/*     */   public Collection getNeighboringCollection() {
/* 247 */     return this.neighboringCollection;
/*     */   }
/*     */   public Vector3f getOpenGLCenter(Vector3f paramVector3f) {
/* 250 */     paramVector3f.set(this.max.a - Math.abs(this.max.a - this.min.a) / 2.0F - 8.0F, this.max.b - Math.abs(this.max.b - this.min.b) / 2.0F - 8.0F, this.max.c - Math.abs(this.max.c - this.min.c) / 2.0F - 8.0F);
/*     */ 
/* 254 */     return paramVector3f;
/*     */   }
/*     */ 
/*     */   public float getRadius()
/*     */   {
/* 259 */     if (this.radiusChanged) {
/* 260 */       this.minScaled.b(this.min);
/* 261 */       this.minScaled.c(this.col.getMargin(), this.col.getMargin(), this.col.getMargin());
/* 262 */       this.minScaled.c(kd.a);
/* 263 */       this.minScaled.a(1);
/*     */ 
/* 266 */       this.maxScaled.b(this.max);
/* 267 */       this.maxScaled.a(this.col.getMargin(), this.col.getMargin(), this.col.getMargin());
/* 268 */       this.maxScaled.c(kd.a);
/* 269 */       this.maxScaled.a(1);
/*     */ 
/* 272 */       this.halfSize.set((this.maxScaled.a - this.minScaled.a) / 2.0F, (this.maxScaled.b - this.minScaled.b) / 2.0F, (this.maxScaled.c - this.minScaled.c) / 2.0F);
/* 273 */       this.radiusChanged = false;
/* 274 */       this.radius = this.halfSize.length();
/*     */     }
/* 276 */     return this.radius;
/*     */   }
/*     */ 
/*     */   public abstract q getSignificator();
/*     */ 
/*     */   public void initialize(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
/*     */   {
/* 285 */     this.col = paramElementCollectionManager;
/* 286 */     this.controller = paramSegmentController;
/* 287 */     this.clazzId = paramShort;
/*     */   }
/*     */ 
/*     */   public boolean isInsideBB(q paramq)
/*     */   {
/* 300 */     return isInsideBB(paramq, 0);
/*     */   }
/*     */ 
/*     */   public boolean isInsideBB(q paramq, int paramInt)
/*     */   {
/* 312 */     if ((paramq.a >= this.min.a - paramInt) && (paramq.a <= this.max.a + paramInt) && (paramq.b >= this.min.b - paramInt) && (paramq.b <= this.max.b + paramInt) && (paramq.c >= this.min.c - paramInt) && (paramq.c <= this.max.c + paramInt)) return true;
/*     */ 
/* 318 */     return false;
/*     */   }
/*     */ 
/*     */   public void merge(ElementCollection paramElementCollection)
/*     */   {
/* 322 */     merge(paramElementCollection.neighboringCollection);
/*     */   }
/*     */ 
/*     */   private void merge(Collection paramCollection) {
/* 326 */     if (paramCollection.size() > 0) {
/* 327 */       for (Iterator localIterator = paramCollection.iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         long l1;
/* 328 */         getPosFromIndex(l1 = ((Long)localIterator.next()).longValue(), 
/* 328 */           this.mPos);
/* 329 */         long l2 = l1 / 4294705156L;
/*     */ 
/* 332 */         long l3 = (
/* 332 */           l1 = l1 - l2 * 4294705156L) / 
/* 332 */           65534L;
/*     */ 
/* 335 */         int i = (int)(l1 - l3 * 65534L - 
/* 335 */           32767L);
/*     */ 
/* 338 */         int j = (int)(l3 - 32767L);
/* 339 */         int k = (int)(l2 - 32767L);
/* 340 */         updateBB(i, j, k);
/*     */       }
/* 342 */       this.neighboringCollection.addAll(paramCollection);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean narrowTest(ElementCollection paramElementCollection)
/*     */   {
/* 348 */     for (paramElementCollection = paramElementCollection.neighboringCollection.iterator(); paramElementCollection.hasNext(); ) {
/* 349 */       getPosFromIndex(((Long)paramElementCollection.next()).longValue(), 
/* 349 */         this.absPos);
/* 350 */       for (int i = 0; i < 6; i++)
/*     */       {
/* 352 */         this.posTmp.b(this.absPos);
/* 353 */         this.posTmp.a(Element.DIRECTIONSi[i]);
/* 354 */         if (this.neighboringCollection.contains(getIndex(this.posTmp)))
/*     */         {
/* 359 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 364 */     return false;
/*     */   }
/*     */ 
/*     */   public void onChangeFinished() {
/*     */   }
/*     */ 
/*     */   public void onRemove(q paramq) {
/*     */   }
/*     */ 
/*     */   public boolean overlaps(ElementCollection paramElementCollection, int paramInt) {
/* 374 */     return isOverlapping(this, paramElementCollection, paramInt);
/*     */   }
/*     */ 
/*     */   public boolean remove(q paramq)
/*     */   {
/*     */     boolean bool;
/* 405 */     if ((
/* 405 */       bool = this.neighboringCollection.remove(paramq)))
/*     */     {
/* 406 */       updateBBFull();
/* 407 */       onRemove(paramq);
/*     */     }
/* 409 */     return bool;
/*     */   }
/*     */   protected abstract void significatorUpdate(int paramInt1, int paramInt2, int paramInt3);
/*     */ 
/* 413 */   public int size() { return this.neighboringCollection.size(); }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 417 */     return getClass().getSimpleName() + hashCode() + "[" + getMin() + "/" + getMax() + "](" + this.neighboringCollection.size() + ")";
/*     */   }
/*     */ 
/*     */   private void updateBBFull() {
/* 421 */     if (this.neighboringCollection.isEmpty()) {
/* 422 */       resetAABB();
/*     */     }
/* 424 */     getSignificator().b(2147483647, 2147483647, 2147483647);
/* 425 */     this.min.b(2147483647, 2147483647, 2147483647);
/* 426 */     this.max.b(-2147483648, -2147483648, -2147483648);
/*     */ 
/* 428 */     this.idPos.b(2147483647, 2147483647, 2147483647);
/* 429 */     for (Iterator localIterator = this.neighboringCollection.iterator(); localIterator.hasNext(); ) {
/* 430 */       getPosFromIndex(((Long)localIterator.next()).longValue(), 
/* 430 */         this.absPos);
/* 431 */       if ((this.absPos.a < this.idPos.a) || ((this.absPos.a == this.idPos.a) && (this.absPos.b < this.idPos.b)) || ((this.absPos.a == this.idPos.a) && (this.absPos.b == this.idPos.b) && (this.absPos.c < this.idPos.c))) {
/* 432 */         this.idPos.b(this.absPos);
/*     */       }
/*     */ 
/* 435 */       this.min.a = Math.min(this.absPos.a, this.min.a);
/* 436 */       this.min.b = Math.min(this.absPos.b, this.min.b);
/* 437 */       this.min.c = Math.min(this.absPos.c, this.min.c);
/*     */ 
/* 439 */       this.max.a = Math.max(this.absPos.a, this.max.a);
/* 440 */       this.max.b = Math.max(this.absPos.b, this.max.b);
/* 441 */       this.max.c = Math.max(this.absPos.c, this.max.c);
/*     */ 
/* 443 */       significatorUpdate(this.absPos.a, this.absPos.b, this.absPos.c);
/*     */     }
/*     */ 
/* 446 */     this.id = this.controller.getSegmentBuffer().a(this.idPos, true);
/*     */ 
/* 448 */     this.radiusChanged = true;
/*     */   }
/*     */ 
/*     */   public void resetAABB() {
/* 452 */     getSignificator().b(0, 0, 0);
/* 453 */     this.min.b(2147483647, 2147483647, 2147483647);
/* 454 */     this.max.b(-2147483648, -2147483648, -2147483648);
/* 455 */     this.id = null;
/* 456 */     this.minScaled.b(0, 0, 0);
/* 457 */     this.maxScaled.b(0, 0, 0);
/* 458 */     this.halfSize.set(0.0F, 0.0F, 0.0F);
/* 459 */     this.idPos.b(2147483647, 2147483647, 2147483647);
/* 460 */     this.radiusChanged = true;
/* 461 */     this.idChanged = true;
/*     */   }
/*     */ 
/*     */   protected void updateBB(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 469 */     if ((paramInt1 < this.idPos.a) || (paramInt2 < this.idPos.b) || (paramInt3 < this.idPos.c)) {
/* 470 */       this.idPos.b(paramInt1, paramInt2, paramInt3);
/* 471 */       this.idChanged = true;
/*     */     }
/* 473 */     this.min.a = Math.min(paramInt1, this.min.a);
/* 474 */     this.min.b = Math.min(paramInt2, this.min.b);
/* 475 */     this.min.c = Math.min(paramInt3, this.min.c);
/*     */ 
/* 477 */     this.max.a = Math.max(paramInt1, this.max.a);
/* 478 */     this.max.b = Math.max(paramInt2, this.max.b);
/* 479 */     this.max.c = Math.max(paramInt3, this.max.c);
/*     */ 
/* 481 */     significatorUpdate(paramInt1, paramInt2, paramInt3);
/* 482 */     this.radiusChanged = true;
/*     */   }
/*     */ 
/*     */   public short getClazzId()
/*     */   {
/* 489 */     return this.clazzId;
/*     */   }
/*     */ 
/*     */   public SegmentController getController()
/*     */   {
/* 495 */     return this.controller;
/*     */   }
/*     */   public static long getIndex(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment) {
/* 498 */     return getIndex(paramSegment.a.a + paramByte1, paramSegment.a.b + paramByte2, paramSegment.a.c + paramByte3);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementCollection
 * JD-Core Version:    0.6.2
 */