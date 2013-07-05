/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectRBTreeMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.SegmentDrawer;
/*     */ import org.schema.game.common.controller.SegmentBufferManager;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ 
/*     */ public final class df extends Thread
/*     */ {
/* 469 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */   protected ArrayList a;
/*     */   private ArrayList jdField_b_of_type_JavaUtilArrayList;
/*     */   private db jdField_a_of_type_Db;
/*     */   private cZ jdField_a_of_type_CZ;
/*     */   private dh jdField_a_of_type_Dh;
/*     */   private dg jdField_a_of_type_Dg;
/*     */   public Object a;
/* 482 */   private HashSet jdField_a_of_type_JavaUtilHashSet = new HashSet();
/*     */ 
/* 518 */   private final Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 519 */   private Vector3f c = new Vector3f();
/*     */ 
/*     */   public df(SegmentDrawer paramSegmentDrawer)
/*     */   {
/* 485 */     super("SegentSorter");
/*     */ 
/* 481 */     this.jdField_a_of_type_JavaLangObject = new Object();
/*     */ 
/* 486 */     setPriority(1);
/* 487 */     this.jdField_a_of_type_Db = new db();
/* 488 */     this.jdField_a_of_type_CZ = new cZ();
/* 489 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 490 */     this.jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/* 491 */     this.jdField_a_of_type_Dh = new dh(this, (byte)0);
/* 492 */     this.jdField_a_of_type_Dg = new dg(this, (byte)0);
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/* 522 */     while (!xm.a())
/*     */       try {
/* 524 */         this.jdField_b_of_type_JavaxVecmathVector3f.set(xe.a().a());
/* 525 */         db.a(this.jdField_a_of_type_Db).set(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 526 */         cZ.a(this.jdField_a_of_type_CZ).set(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 527 */         this.jdField_a_of_type_Dh.jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_b_of_type_JavaxVecmathVector3f);
/*     */ 
/* 529 */         System.currentTimeMillis();
/*     */         Object localObject10;
/* 531 */         synchronized (SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 532 */           ArrayList localArrayList = SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer); localObject7 = this; for (int k = 0; k < localArrayList.size(); localObject6++) synchronized (((SegmentBufferManager)(localObject8 = (SegmentController)localArrayList.get(k)).getSegmentBuffer()).a())
/*     */             {
/* 532 */               Object localObject8;
/* 532 */               for (localObject10 = ((SegmentBufferManager)((SegmentController)localObject8).getSegmentBuffer()).a().values().iterator(); ((Iterator)localObject10).hasNext(); ((df)localObject7).jdField_b_of_type_JavaUtilArrayList.add((jK)localObject8)) localObject8 = (jL)((Iterator)localObject10).next(); 
/*     */             } 
/*     */         }
/*     */         try
/*     */         {
/* 535 */           Collections.sort(this.jdField_b_of_type_JavaUtilArrayList, this.jdField_a_of_type_CZ);
/*     */         }
/*     */         catch (Exception localException1)
/*     */         {
/* 539 */           localException1.printStackTrace();
/*     */         }
/* 538 */         continue;
/*     */ 
/* 541 */         this.jdField_a_of_type_JavaUtilArrayList.clear();
/*     */ 
/* 543 */         Object localObject5 = this.jdField_b_of_type_JavaUtilArrayList; Object localObject7 = this; this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_Dc.i = 0L; ((df)localObject7).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_c_of_type_Int = 0; ((df)localObject7).jdField_a_of_type_JavaUtilHashSet.clear(); int m = SegmentDrawer.jdField_a_of_type_DH.jdField_a_of_type_Int << 3; int n = 0; for (int i1 = 0; i1 < ((List)localObject5).size(); i1++) { localObject10 = (jK)((List)localObject5).get(i1); if (n + ((jK)localObject10).c() < m) { if ((!(((jK)localObject10).a() instanceof kd)) || (!((kd)((jK)localObject10).a()).a()) || (((kd)((jK)localObject10).a()).a().contains(SegmentDrawer.a(((df)localObject7).jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).a()))) { n += ((jK)localObject10).c(); ((jK)localObject10).b(((df)localObject7).jdField_a_of_type_Dh, true); } } else if (((jK)localObject10).a()) { System.err.println("DEACTIVATING REGION: " + ((jK)localObject10).a() + " of " + ((jK)localObject10).a() + "; fill: " + n); ((jK)localObject10).b(((df)localObject7).jdField_a_of_type_Dg, true); }  } System.currentTimeMillis();
/*     */ 
/* 545 */         this.jdField_b_of_type_JavaUtilArrayList.clear();
/*     */ 
/* 548 */         System.currentTimeMillis();
/*     */ 
/* 553 */         for (int i = 0; i < this.jdField_a_of_type_JavaUtilArrayList.size(); i++)
/*     */         {
/* 555 */           (
/* 556 */             localObject5 = (mr)this.jdField_a_of_type_JavaUtilArrayList.get(i))
/* 556 */             .a().getAbsoluteSegmentWorldPositionClient((Segment)localObject5, this.c);
/* 557 */           this.c.sub(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 558 */           ((mr)localObject5).jdField_a_of_type_Float = this.c.lengthSquared();
/*     */         }
/*     */         try {
/* 561 */           Collections.sort(this.jdField_a_of_type_JavaUtilArrayList, this.jdField_a_of_type_Db);
/*     */         }
/*     */         catch (Exception localException2)
/*     */         {
/* 566 */           localException2.printStackTrace();
/*     */ 
/* 564 */           System.err.println("[Exception] Catched: Resorting triggered by exception");
/* 565 */         }continue;
/*     */ 
/* 567 */         i = 0;
/*     */ 
/* 569 */         int j = 0;
/*     */ 
/* 580 */         if ((this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr.length != SegmentDrawer.jdField_a_of_type_DH.jdField_a_of_type_Int) && 
/* 581 */           (!jdField_a_of_type_Boolean)) throw new AssertionError();
/*     */ 
/* 585 */         for (Iterator localIterator = this.jdField_a_of_type_JavaUtilArrayList.iterator(); localIterator.hasNext(); )
/*     */         {
/* 587 */           localObject9 = (mr)localIterator.next();
/*     */ 
/* 589 */           if (j < SegmentDrawer.jdField_a_of_type_DH.jdField_a_of_type_Int - 100) {
/* 590 */             localObject7 = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr[i];
/* 591 */             this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.d[i] = localObject7;
/* 592 */             this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr[i] = localObject9;
/* 593 */             this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr[i].a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_Int);
/* 594 */             localObject7 = localObject9; if ((((mr)localObject7).jdField_a_of_type_Ms == ms.d) || (((mr)localObject7).jdField_a_of_type_Ms == ms.e)) { ((mr)localObject7).jdField_a_of_type_Ms = ms.b; ((mr)localObject7).jdField_a_of_type_Int = 0; ((Q)((mr)localObject7).a().getSegmentProvider()).a((mr)localObject7); }
/* 595 */             i++;
/*     */           }
/* 598 */           else if (((mr)localObject9).b())
/*     */           {
/* 602 */             this.jdField_a_of_type_JavaUtilHashSet.add(localObject9);
/* 603 */             ((mr)localObject9).e(true);
/*     */           }
/*     */ 
/* 606 */           j++;
/*     */         }
/*     */ 
/* 609 */         synchronized (this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ArrayOfMr)
/*     */         {
/* 613 */           localObject7 = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ArrayOfMr;
/* 614 */           Object localObject9 = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_c_of_type_ArrayOfMr;
/*     */ 
/* 616 */           SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer, i);
/*     */ 
/* 618 */           this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ArrayOfMr = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr;
/* 619 */           this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_c_of_type_ArrayOfMr = this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.d;
/* 620 */           this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr = ((mr[])localObject7);
/* 621 */           this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.d = ((mr[])localObject9);
/*     */ 
/* 623 */           if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_ArrayOfMr == this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_a_of_type_ArrayOfMr)) throw new AssertionError("Pointers equal...");
/*     */ 
/*     */         }
/*     */ 
/* 627 */         synchronized (SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 628 */           SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).addAll(this.jdField_a_of_type_JavaUtilHashSet);
/*     */         }
/*     */ 
/* 631 */         synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 632 */           SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer);
/* 633 */           this.jdField_a_of_type_JavaLangObject.wait();
/*     */         }
/* 635 */         Thread.sleep(500L);
/* 636 */         this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.jdField_b_of_type_Int += 1;
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 641 */         localInterruptedException.printStackTrace();
/*     */       }
/*     */       catch (Exception localException3)
/*     */       {
/* 641 */         localException3.printStackTrace();
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     df
 * JD-Core Version:    0.6.2
 */