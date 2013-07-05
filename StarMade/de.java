/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.SegmentDrawer;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ 
/*     */ public final class de extends Thread
/*     */ {
/*     */   private ArrayList jdField_a_of_type_JavaUtilArrayList;
/*     */   public Object a;
/*     */   public int a;
/*     */   public long a;
/*     */ 
/*     */   public de(SegmentDrawer paramSegmentDrawer)
/*     */   {
/* 295 */     super("SegmentLightingUpdateThreadManager");
/* 296 */     SegmentDrawer.a(paramSegmentDrawer, new HashSet(100));
/* 297 */     SegmentDrawer.b(paramSegmentDrawer, new HashSet(100));
/* 298 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList(2);
/* 299 */     for (int i = 0; i < 2; i++)
/*     */     {
/*     */       dd localdd;
/* 300 */       (
/* 301 */         localdd = new dd(paramSegmentDrawer, this))
/* 301 */         .start();
/* 302 */       this.jdField_a_of_type_JavaUtilArrayList.add(localdd);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(dd paramdd, mr parammr, boolean arg3)
/*     */   {
/* 310 */     if (??? != 0) {
/* 311 */       synchronized (SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 312 */         SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).remove(parammr);
/*     */       }
/*     */     }
/* 315 */     synchronized (this.jdField_a_of_type_JavaUtilArrayList) { this.jdField_a_of_type_JavaUtilArrayList.add(paramdd);
/* 318 */       this.jdField_a_of_type_JavaUtilArrayList.notify();
/*     */       return; }
/*     */   }
/* 322 */   public final void a(mr parammr) { if (parammr.g()) {
/* 323 */       parammr.e(false); return;
/*     */     }
/* 325 */     if (!parammr.b)
/*     */     {
/* 327 */       parammr.b = true;
/*     */ 
/* 329 */       SegmentDrawer.c(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).add(parammr);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 336 */     if (!SegmentDrawer.c(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).isEmpty()) {
/* 337 */       synchronized (SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 338 */         SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).addAll(SegmentDrawer.c(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer));
/* 339 */         SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).notify();
/*     */       }
/*     */ 
/* 342 */       SegmentDrawer.c(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try {
/* 349 */       while (xe.a() == null);
/* 350 */       while (!xm.a()) {
/* 351 */         dd localdd = null;
/* 352 */         synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 353 */           while (this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 354 */             this.jdField_a_of_type_JavaUtilArrayList.wait();
/*     */           }
/* 356 */           localdd = (dd)this.jdField_a_of_type_JavaUtilArrayList.remove(0);
/*     */         }
/*     */ 
/* 360 */         synchronized (SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 361 */           while (SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).isEmpty())
/* 362 */             SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).wait();
/* 368 */           da localda;
/* 365 */           da.a(localda = new da())
/* 365 */             .set(xe.a().a());
/*     */ 
/* 367 */           Object localObject7 = null;
/* 368 */           for (Object localObject8 = SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).iterator(); ((Iterator)localObject8).hasNext(); ) { mr localmr = (mr)((Iterator)localObject8).next();
/* 369 */             if ((localObject7 == null) || (localda.a((mr)localObject7, localmr) > 0)) {
/* 370 */               localObject7 = localmr;
/*     */             }
/*     */           }
/*     */ 
/* 374 */           localObject8 = localObject7;
/*     */ 
/* 376 */           SegmentDrawer.d(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).remove(localObject8);
/* 377 */           ((mr)localObject8).b = false;
/* 378 */           synchronized (SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 379 */             synchronized (SegmentDrawer.a(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer)) {
/* 380 */               if ((((mr)localObject8).c()) || (SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).contains(localObject8)))
/*     */               {
/* 382 */                 a(localdd, (mr)localObject8, false);
/* 383 */                 continue;
/*     */               }
/* 385 */               SegmentDrawer.b(this.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer).add(localObject8);
/*     */             }
/*     */           }
/* 388 */           synchronized (((mr)localObject8).a) {
/* 389 */             ((mr)localObject8).d(true);
/* 390 */             ((mr)localObject8).c = System.currentTimeMillis();
/* 391 */             ((mr)localObject8).e(false);
/*     */           }
/* 393 */           localObject3.a((mr)localObject8);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 399 */       return; } catch (InterruptedException localInterruptedException) {
/* 400 */       Object localObject5 = null;
/*     */ 
/* 399 */       localInterruptedException
/* 400 */         .printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de
 * JD-Core Version:    0.6.2
 */