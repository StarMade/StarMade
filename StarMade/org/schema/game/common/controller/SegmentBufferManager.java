/*     */ package org.schema.game.common.controller;
/*     */ 
/*     */ import ct;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectRBTreeMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import jK;
/*     */ import jL;
/*     */ import jM;
/*     */ import jN;
/*     */ import jY;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import javax.vecmath.Vector3f;
/*     */ import jz;
/*     */ import le;
/*     */ import org.schema.common.util.ByteUtil;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import q;
/*     */ import vg;
/*     */ import xO;
/*     */ 
/*     */ public class SegmentBufferManager
/*     */   implements jL
/*     */ {
/*     */   private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   private xO jdField_a_of_type_XO;
/*     */   private Long2ObjectRBTreeMap jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap;
/*     */   private int jdField_a_of_type_Int;
/*     */   private int jdField_b_of_type_Int;
/*     */   private int c;
/*     */   private long jdField_a_of_type_Long;
/*  46 */   private final q jdField_a_of_type_Q = new q();
/*     */ 
/* 356 */   private final q jdField_b_of_type_Q = new q();
/*     */ 
/*     */   private static long a(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  60 */     if ((!jdField_a_of_type_Boolean) && (paramInt1 >= 1048576)) throw new AssertionError();
/*  61 */     if ((!jdField_a_of_type_Boolean) && (paramInt2 >= 1048576)) throw new AssertionError();
/*  62 */     if ((!jdField_a_of_type_Boolean) && (paramInt3 >= 1048576)) throw new AssertionError();
/*     */ 
/*  64 */     long l1 = paramInt3 << 42 & 0x0;
/*  65 */     long l2 = paramInt2 << 21 & 0xFFE00000;
/*     */ 
/*  70 */     return paramInt1 + 
/*  68 */       l2 + l1;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  99 */     System.err.println("-8796093022208 MBytes needed if array 12");
/*     */ 
/* 102 */     paramArrayOfString = new HashSet();
/*     */ 
/* 104 */     for (int i = -1000; i < 100; 
/* 106 */       i++)
/* 107 */       for (int j = -100; j < 100; j++)
/* 108 */         for (int k = -100; k < 100; k++)
/*     */         {
/* 111 */           long l = a(k, j, i);
/*     */ 
/* 114 */           if ((!jdField_a_of_type_Boolean) && (paramArrayOfString.contains(Long.valueOf(l)))) throw new AssertionError();
/*     */ 
/* 116 */           paramArrayOfString.add(Long.valueOf(l));
/*     */         }
/*     */   }
/*     */ 
/*     */   public SegmentBufferManager(SegmentController paramSegmentController)
/*     */   {
/* 123 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/* 124 */     this.jdField_a_of_type_XO = new xO();
/* 125 */     this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap = new Long2ObjectRBTreeMap();
/*     */   }
/*     */ 
/*     */   public final void a(Segment paramSegment)
/*     */   {
/* 131 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap) {
/* 132 */       a(paramSegment.jdField_a_of_type_Q, true).a(paramSegment);
/* 133 */       if ((!jdField_a_of_type_Boolean) && (a(paramSegment.jdField_a_of_type_Q) == null)) throw new AssertionError();
/* 134 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/* 198 */     Long2ObjectRBTreeMap localLong2ObjectRBTreeMap1 = new Long2ObjectRBTreeMap();
/* 199 */     synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap) {
/* 200 */       localLong2ObjectRBTreeMap1.putAll(this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap);
/* 201 */       this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.clear();
/* 202 */       this.jdField_a_of_type_Int = 0;
/* 203 */       this.jdField_b_of_type_Int = 0;
/*     */     }
/* 205 */     if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getThreadPool().isTerminating()) && (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getThreadPool().isTerminated())) {
/* 206 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getThreadPool().execute(new jN(this, localLong2ObjectRBTreeMap2));
/*     */     }
/*     */ 
/* 229 */     return 0;
/*     */   }
/*     */ 
/*     */   public final boolean a(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*     */     jL localjL;
/* 237 */     if ((
/* 237 */       localjL = a(paramInt1, paramInt2, paramInt3, false)) != null)
/* 237 */       return localjL.a(paramInt1, paramInt2, paramInt3); return false;
/*     */   }
/*     */ 
/*     */   public final boolean a(q paramq)
/*     */   {
/*     */     jL localjL;
/* 244 */     if ((
/* 244 */       localjL = a(paramq, false)) != null)
/*     */     {
/* 244 */       return localjL.a(paramq); } return false;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 257 */     this.jdField_a_of_type_Int = Math.max(0, this.jdField_a_of_type_Int - 1);
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 262 */     this.jdField_b_of_type_Int = Math.max(0, this.jdField_b_of_type_Int - 1);
/*     */   }
/*     */ 
/*     */   public final boolean b(q paramq)
/*     */   {
/*     */     jL localjL;
/* 269 */     if ((
/* 269 */       localjL = a(paramq, false)) == null)
/*     */     {
/* 270 */       return false;
/*     */     }
/* 272 */     return localjL.b(paramq);
/*     */   }
/*     */ 
/*     */   public final Segment a(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*     */     jL localjL;
/* 281 */     if ((
/* 281 */       localjL = a(paramInt1, paramInt2, paramInt3, false)) != null)
/*     */     {
/* 282 */       return localjL.a(paramInt1, paramInt2, paramInt3);
/*     */     }
/* 284 */     return null;
/*     */   }
/*     */ 
/*     */   public final Segment a(q paramq)
/*     */   {
/* 289 */     return a(paramq.jdField_a_of_type_Int, paramq.jdField_b_of_type_Int, paramq.c);
/*     */   }
/*     */ 
/*     */   public final xO a() {
/* 293 */     return this.jdField_a_of_type_XO;
/*     */   }
/*     */   public final Long2ObjectRBTreeMap a() {
/* 296 */     return this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap;
/*     */   }
/*     */ 
/*     */   private jL a(int paramInt1, int paramInt2, int paramInt3, boolean arg4) {
/* 300 */     int i = ByteUtil.c(ByteUtil.b(paramInt1)); int j = ByteUtil.c(ByteUtil.b(paramInt2)); int k = ByteUtil.c(ByteUtil.b(paramInt3)); long l = a(i, j, k);
/*     */ 
/* 302 */     Object localObject = (jL)this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.get(l);
/*     */ 
/* 304 */     if ((??? != 0) && (localObject == null)) {
/* 305 */       synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap)
/*     */       {
/* 307 */         if (!this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.containsKey(l))
/*     */         {
/* 312 */           paramInt1 = ByteUtil.c(ByteUtil.b(paramInt1)) << 3;
/* 313 */           paramInt2 = ByteUtil.c(ByteUtil.b(paramInt2)) << 3;
/* 314 */           paramInt3 = ByteUtil.c(ByteUtil.b(paramInt3)) << 3;
/*     */ 
/* 318 */           localObject = new q(paramInt1, paramInt2, paramInt3);
/* 319 */           (
/* 320 */             paramInt1 = new q(paramInt1, paramInt2, paramInt3))
/* 320 */             .a(8, 8, 8);
/* 321 */           localObject = new jK(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, (q)localObject, paramInt1, this);
/* 322 */           this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.put(l, localObject);
/* 323 */           if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.get(l) == null)) throw new AssertionError();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 328 */     return localObject;
/*     */   }
/*     */ 
/*     */   private jL a(q paramq, boolean paramBoolean)
/*     */   {
/* 343 */     return a(paramq.jdField_a_of_type_Int, paramq.jdField_b_of_type_Int, paramq.c, paramBoolean);
/*     */   }
/*     */ 
/*     */   public final long a()
/*     */   {
/* 353 */     return this.jdField_a_of_type_Long;
/*     */   }
/*     */ 
/*     */   public final long a(q paramq1, q paramq2)
/*     */   {
/* 360 */     this.jdField_a_of_type_Q.jdField_a_of_type_Int = ByteUtil.c(ByteUtil.b(paramq1.jdField_a_of_type_Int));
/* 361 */     this.jdField_a_of_type_Q.jdField_b_of_type_Int = ByteUtil.c(ByteUtil.b(paramq1.jdField_b_of_type_Int));
/* 362 */     this.jdField_a_of_type_Q.c = ByteUtil.c(ByteUtil.b(paramq1.c));
/*     */ 
/* 364 */     this.jdField_b_of_type_Q.jdField_a_of_type_Int = ByteUtil.c(ByteUtil.b(paramq2.jdField_a_of_type_Int));
/* 365 */     this.jdField_b_of_type_Q.jdField_b_of_type_Int = ByteUtil.c(ByteUtil.b(paramq2.jdField_b_of_type_Int));
/* 366 */     this.jdField_b_of_type_Q.c = ByteUtil.c(ByteUtil.b(paramq2.c));
/*     */ 
/* 368 */     this.jdField_b_of_type_Q.jdField_a_of_type_Int += 1;
/* 369 */     this.jdField_b_of_type_Q.jdField_b_of_type_Int += 1;
/* 370 */     this.jdField_b_of_type_Q.c += 1;
/*     */ 
/* 372 */     if (this.jdField_a_of_type_Q.jdField_a_of_type_Int == this.jdField_b_of_type_Q.jdField_a_of_type_Int) {
/* 373 */       this.jdField_b_of_type_Q.jdField_a_of_type_Int += 1;
/*     */     }
/* 375 */     if (this.jdField_a_of_type_Q.jdField_b_of_type_Int == this.jdField_b_of_type_Q.jdField_b_of_type_Int) {
/* 376 */       this.jdField_b_of_type_Q.jdField_b_of_type_Int += 1;
/*     */     }
/* 378 */     if (this.jdField_a_of_type_Q.c == this.jdField_b_of_type_Q.c) {
/* 379 */       this.jdField_b_of_type_Q.c += 1;
/*     */     }
/*     */ 
/* 382 */     long l = 0L;
/* 383 */     for (paramq1 = this.jdField_a_of_type_Q.jdField_a_of_type_Int; paramq1 < this.jdField_b_of_type_Q.jdField_a_of_type_Int; 
/* 402 */       paramq1 += 8) {
/* 403 */       for (paramq2 = this.jdField_a_of_type_Q.jdField_b_of_type_Int; paramq2 < this.jdField_b_of_type_Q.jdField_b_of_type_Int; paramq2 += 8) {
/* 404 */         for (int i = this.jdField_a_of_type_Q.c; i < this.jdField_b_of_type_Q.c; i += 8)
/*     */         {
/*     */           jL localjL;
/* 408 */           if ((
/* 408 */             localjL = (jL)this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.get(a(paramq1, paramq2, i))) != null)
/*     */           {
/* 409 */             l = Math.max(l, localjL.a());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 415 */     return l;
/*     */   }
/*     */ 
/*     */   public final le a(q paramq, boolean paramBoolean)
/*     */   {
/*     */     jL localjL;
/* 427 */     if ((
/* 427 */       localjL = a(paramq, paramBoolean)) == null)
/*     */     {
/* 428 */       return null;
/*     */     }
/* 430 */     return localjL.a(paramq, paramBoolean);
/*     */   }
/*     */ 
/*     */   public final le a(q paramq, boolean paramBoolean, le paramle)
/*     */   {
/*     */     jL localjL;
/* 443 */     if ((
/* 443 */       localjL = a(paramq, paramBoolean)) == null)
/*     */     {
/* 444 */       return null;
/*     */     }
/* 446 */     return localjL.a(paramq, paramBoolean, paramle);
/*     */   }
/*     */ 
/*     */   public final SegmentController a()
/*     */   {
/* 453 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   }
/*     */ 
/*     */   public final int c()
/*     */   {
/* 458 */     return this.jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final boolean a(q paramq, jM paramjM)
/*     */   {
/*     */     jL localjL;
/* 472 */     if ((
/* 472 */       localjL = a(paramq, false)) != null)
/*     */     {
/* 473 */       return localjL.a(paramq, paramjM);
/*     */     }
/* 475 */     return true;
/*     */   }
/*     */ 
/*     */   public final boolean a(int paramInt1, int paramInt2, int paramInt3, jM paramjM)
/*     */   {
/*     */     jL localjL;
/* 489 */     if ((
/* 489 */       localjL = a(paramInt1, paramInt2, paramInt3, false)) != null)
/*     */     {
/* 490 */       return localjL.a(paramInt1, paramInt2, paramInt3, paramjM);
/*     */     }
/* 492 */     return true;
/*     */   }
/*     */ 
/*     */   public final void e() {
/* 496 */     this.jdField_a_of_type_Int += 1;
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, Segment paramSegment)
/*     */   {
/*     */     jL localjL;
/* 501 */     if ((
/* 501 */       localjL = a(paramSegment.jdField_a_of_type_Q, false)) != null)
/*     */     {
/* 502 */       localjL.a(paramInt, paramSegment);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final jz a(q paramq)
/*     */   {
/* 509 */     if (((
/* 509 */       paramq = a(paramq, false)) != null) && 
/* 509 */       (paramq.a() != 0))
/*     */     {
/* 511 */       jz localjz;
/* 511 */       SegmentBufferManager localSegmentBufferManager = this; q localq1 = paramq; short s = localq1.a(); q localq2 = null; (paramq = localjz = new jz()).jdField_a_of_type_Short = 
/* 511 */         s; if ((paramq.jdField_a_of_type_Short == 0) || (!ElementKeyMap.getInfo(paramq.jdField_a_of_type_Short).getControlledBy().isEmpty())) { paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(localq1); localq1 = new q(); for (localq2 = new q(); !paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.isEmpty(); )
/*     */         {
/* 511 */           le localle1;
/* 511 */           q localq3 = (localle1 = (le)paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.dequeue()).a(new q()); paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localq3); if (localle1.a() == paramq.jdField_a_of_type_Short) { paramq.jdField_a_of_type_JavaUtilArrayList.add(localq3); for (int i = 0; i < 6; i++) { localle1.a(localq1); localq1.a(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
/*     */               le localle2;
/* 511 */               if (((localle2 = localSegmentBufferManager.a(localq1, false, new le())) == null) || (localle2.a() == 0) || (localle2.a() != paramq.jdField_a_of_type_Short)) { paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localle2.a(new q())); } else if (!paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.contains(localle2.a(localq2))) { paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(localle2.a(new q())); paramq.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(localle2); }  }  }
/*     */         } }
/* 512 */       return localjz;
/*     */     }
/* 514 */     return null;
/*     */   }
/*     */ 
/*     */   public final void f() {
/* 518 */     this.jdField_b_of_type_Int += 1;
/*     */   }
/*     */ 
/*     */   public final boolean b() {
/* 522 */     return this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.isEmpty();
/*     */   }
/*     */ 
/*     */   public final boolean a(jM paramjM, q paramq1, q paramq2)
/*     */   {
/* 528 */     q localq = new q();
/*     */ 
/* 530 */     for (int i = paramq1.jdField_a_of_type_Int; i < paramq2.jdField_a_of_type_Int; 
/* 545 */       i += 16) {
/* 546 */       for (int j = paramq1.jdField_b_of_type_Int; j < paramq2.jdField_b_of_type_Int; j += 16) {
/* 547 */         for (int k = paramq1.c; k < paramq2.c; k += 16) {
/* 548 */           localq.b(i, j, k);
/*     */           boolean bool;
/* 550 */           if (!(
/* 550 */             bool = a(localq, paramjM)))
/*     */           {
/* 551 */             return bool;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 557 */     return true;
/*     */   }
/*     */ 
/*     */   public final boolean a(jM paramjM, boolean paramBoolean)
/*     */   {
/*     */     boolean bool;
/* 561 */     if (paramBoolean)
/*     */     {
/*     */       Iterator localIterator;
/* 562 */       synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap) {
/* 563 */         for (localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); localIterator.hasNext(); )
/*     */         {
/* 565 */           if (!(
/* 565 */             bool = ((jL)localIterator.next())
/* 564 */             .a(paramjM, paramBoolean)))
/*     */           {
/* 567 */             return bool;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 572 */     for (??? = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); ((Iterator)???).hasNext(); )
/*     */     {
/* 574 */       if (!(
/* 574 */         bool = ((jL)((Iterator)???).next())
/* 573 */         .a(paramjM, paramBoolean)))
/*     */       {
/* 576 */         return bool;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 581 */     return true;
/*     */   }
/*     */ 
/*     */   public final boolean b(jM paramjM, boolean paramBoolean)
/*     */   {
/*     */     boolean bool;
/* 588 */     if (paramBoolean)
/*     */     {
/*     */       Iterator localIterator;
/* 589 */       synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap) {
/* 590 */         for (localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); localIterator.hasNext(); )
/*     */         {
/* 592 */           if (!(
/* 592 */             bool = ((jL)localIterator.next())
/* 591 */             .b(paramjM, paramBoolean)))
/*     */           {
/* 594 */             return bool;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 599 */     for (??? = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); ((Iterator)???).hasNext(); )
/*     */     {
/* 601 */       if (!(
/* 601 */         bool = ((jL)((Iterator)???).next())
/* 600 */         .b(paramjM, paramBoolean)))
/*     */       {
/* 603 */         return bool;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 608 */     return true;
/*     */   }
/*     */ 
/*     */   public final boolean b(jM paramjM, q paramq1, q paramq2)
/*     */   {
/* 620 */     for (int i = paramq1.jdField_a_of_type_Int; i < paramq2.jdField_a_of_type_Int; 
/* 635 */       i += 16) {
/* 636 */       for (int j = paramq1.jdField_b_of_type_Int; j < paramq2.jdField_b_of_type_Int; j += 16) {
/* 637 */         for (int k = paramq1.c; k < paramq2.c; k += 16)
/*     */         {
/*     */           boolean bool;
/* 639 */           if (!(
/* 639 */             bool = a(i, j, k, paramjM)))
/*     */           {
/* 640 */             return bool;
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 648 */     return true;
/*     */   }
/*     */ 
/*     */   public final void a(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/*     */   {
/*     */     jL localjL;
/* 660 */     if ((
/* 660 */       localjL = a(paramSegment.jdField_a_of_type_Q, false)) != null)
/*     */     {
/* 661 */       localjL.a(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
/*     */   {
/*     */     jL localjL;
/* 673 */     if ((
/* 673 */       localjL = a(paramSegment.jdField_a_of_type_Q, false)) != null)
/*     */     {
/* 674 */       localjL.b(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 725 */     Iterator localIterator = null; synchronized (this.jdField_a_of_type_XO) {
/* 726 */       this.jdField_a_of_type_XO.a();
/* 727 */       for (localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.values().iterator(); localIterator.hasNext(); ) ((jL)localIterator.next())
/* 728 */           .a();
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(SegmentData paramSegmentData)
/*     */   {
/*     */     jL localjL;
/* 740 */     if (((
/* 740 */       localjL = a(paramSegmentData.getSegment().jdField_a_of_type_Q, false)) != null) && 
/* 740 */       (
/* 741 */       (localjL.a().a.x == this.jdField_a_of_type_XO.a.x) || (localjL.a().a.y == this.jdField_a_of_type_XO.a.y) || (localjL.a().a.z == this.jdField_a_of_type_XO.a.z) || (localjL.a().b.x == this.jdField_a_of_type_XO.b.x) || (localjL.a().b.y == this.jdField_a_of_type_XO.b.y) || (localjL.a().b.z == this.jdField_a_of_type_XO.b.z)))
/*     */     {
/* 750 */       localjL.a(paramSegmentData);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final int d()
/*     */   {
/* 773 */     return this.jdField_a_of_type_ItUnimiDsiFastutilLongsLong2ObjectRBTreeMap.size();
/*     */   }
/*     */ 
/*     */   public final void a(xO paramxO)
/*     */   {
/* 784 */     this.jdField_a_of_type_XO.a(paramxO.a, paramxO.b);
/* 785 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.aabbRecalcFlag();
/*     */   }
/*     */ 
/*     */   public final void b(Segment paramSegment)
/*     */   {
/*     */     jL localjL;
/* 794 */     if ((
/* 794 */       localjL = a(paramSegment.jdField_a_of_type_Q, false)) != null)
/*     */     {
/* 795 */       localjL.b(paramSegment);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void g()
/*     */   {
/* 803 */     this.jdField_a_of_type_Long = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   public final void b() {
/* 807 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a();
/*     */ 
/* 809 */     this.c = this.jdField_b_of_type_Int;
/*     */ 
/* 811 */     if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/* 812 */       vg.jdField_a_of_type_Int += this.c; return;
/*     */     }
/*     */ 
/* 815 */     ct.jdField_a_of_type_Int += this.c;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.SegmentBufferManager
 * JD-Core Version:    0.6.2
 */