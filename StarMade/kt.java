/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.server.controller.GameServerController;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ 
/*     */ public final class kt extends kp
/*     */ {
/*     */   public kt(StateInterface paramStateInterface, kd paramkd)
/*     */   {
/*  18 */     super(paramStateInterface, paramkd);
/*     */   }
/*     */ 
/*     */   public final String getUniqueIdentifier()
/*     */   {
/*  25 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getUniqueIdentifier() + "_AI";
/*     */   }
/*     */ 
/*     */   public final boolean isVolatile() {
/*  29 */     return false;
/*     */   }
/*     */ 
/*     */   public final void a(SegmentController paramSegmentController)
/*     */   {
/*  37 */     super.a(paramSegmentController);
/*  38 */     Iterator localIterator = null; if (this.jdField_a_of_type_Wo.a()) {
/*  39 */       if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().b()) && (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().from.a().a() == paramSegmentController)) {
/*  40 */         return;
/*     */       }
/*  42 */       if (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().isEmpty()) {
/*  43 */         for (localIterator = this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().iterator(); localIterator.hasNext(); ) {
/*  44 */           if (((ElementDocking)localIterator.next()).from
/*  44 */             .a().a() == paramSegmentController) {
/*  45 */             return;
/*     */           }
/*     */         }
/*     */       }
/*  49 */       ((sJ)this.jdField_a_of_type_Wo).a(paramSegmentController);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final void b(ko paramko)
/*     */   {
/*  56 */     if (paramko.a() == kq.b) {
/*  57 */       if (paramko.a().equals("Turret"))
/*  58 */         this.jdField_a_of_type_Wo = new tm((kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, a(kq.c).a());
/*     */       else {
/*  60 */         this.jdField_a_of_type_Wo = new sR((kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, a(kq.c).a());
/*     */       }
/*     */     }
/*     */ 
/*  64 */     super.b(paramko);
/*     */   }
/*     */ 
/*     */   protected final void a(ko paramko)
/*     */   {
/*  74 */     if (paramko.a() == kq.c) {
/*  75 */       paramko.a();
/*     */ 
/*  80 */       this.jdField_a_of_type_Wo = new sJ("shipAiEntity", (kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(lb paramlb)
/*     */   {
/*  91 */     if (this.jdField_a_of_type_Wo.a()) {
/*  92 */       if ((paramlb != null) && (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getFactionId() == -1)) {
/*  93 */         ((vg)paramlb.getState()).a().a(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*     */       }
/*  95 */       if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != null) && (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getFactionId() != 0)) {
/*  96 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.setFactionId(0);
/*     */       }
/*     */     }
/*     */ 
/* 100 */     super.a(paramlb);
/*     */   }
/*     */ 
/*     */   protected final void b()
/*     */   {
/*     */     ko localko;
/* 108 */     if ((
/* 108 */       localko = a(kq.b))
/* 108 */       .a().equals("Turret")) {
/* 109 */       this.jdField_a_of_type_Wo = new tm((kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, a(kq.c).a()); return;
/* 110 */     }if (localko.a().equals("Ship"))
/* 111 */       this.jdField_a_of_type_Wo = new sR((kd)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController, a(kq.c).a());
/*     */   }
/*     */ 
/*     */   public final boolean b()
/*     */   {
/* 128 */     return a(kq.c).a();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kt
 * JD-Core Version:    0.6.2
 */