/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ 
/*     */ final class du extends Camera
/*     */ {
/*     */   private dA jdField_a_of_type_DA;
/*     */   private dB jdField_a_of_type_DB;
/*     */ 
/*     */   public du(dt paramdt, xa paramxa)
/*     */   {
/*  80 */     super(paramxa);
/*  81 */     this.jdField_a_of_type_DB = new dB(this, paramdt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*  82 */     this.a = this.jdField_a_of_type_DB;
/*  83 */     this.c = 0.2F;
/*  84 */     this.b = 0.2F;
/*     */ 
/*  86 */     this.jdField_a_of_type_DA = new dA(this, paramdt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*  95 */     super.a();
/*  96 */     getWorldTransform().basis.set(this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 104 */     if (this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a() != null)
/*     */     {
/* 107 */       if ((this.jdField_a_of_type_DA.jdField_a_of_type_Int != org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.b()]) || (this.jdField_a_of_type_DA.jdField_a_of_type_Yh != this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.a().a()))
/*     */       {
/* 111 */         System.err.println("NEW LOOKING ALGO " + this.jdField_a_of_type_DA.jdField_a_of_type_Int + " / " + org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.b()] + "; " + this.jdField_a_of_type_DA.jdField_a_of_type_Yh + " / " + this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.a().a());
/*     */ 
/* 113 */         this.jdField_a_of_type_DA = new dA(this, this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/*     */       }
/*     */ 
/* 116 */       this.jdField_a_of_type_DA.jdField_a_of_type_Boolean = this.jdField_a_of_type_Dt.b;
/* 117 */       this.jdField_a_of_type_DA.jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 118 */       this.jdField_a_of_type_DA.a(this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.a().a());
/* 119 */       this.jdField_a_of_type_DA.a(org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a().to.b()]);
/*     */ 
/* 121 */       this.a = this.jdField_a_of_type_DA;
/*     */     }
/*     */     else {
/* 124 */       this.a = this.jdField_a_of_type_DB;
/* 125 */       ((dB)this.a).jdField_a_of_type_Boolean = this.jdField_a_of_type_Dt.b;
/* 126 */       ((dB)this.a).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_Dt.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 127 */       ((dB)this.a).jdField_a_of_type_Int = this.jdField_a_of_type_Dt.jdField_a_of_type_Int;
/*     */     }
/*     */ 
/* 131 */     if (wV.a())
/*     */     {
/* 133 */       e();
/*     */ 
/* 135 */       if (cv.m.a()) {
/* 136 */         this.a.a(0.0F, 0.0F, -0.03F, 0.0F, b(), a());
/*     */       }
/*     */ 
/* 145 */       if (cv.n.a()) {
/* 146 */         this.a.a(0.0F, 0.0F, 0.03F, 0.0F, b(), a());
/*     */       }
/*     */ 
/* 156 */       if (Keyboard.isKeyDown(29))
/*     */       {
/* 158 */         this.a.a(0.0F, this.jdField_a_of_type_WV.c / (xm.a() / 2), this.jdField_a_of_type_WV.b / (xm.b() / 2), 0.0F, b(), a());
/*     */       }
/*     */       else
/*     */       {
/* 167 */         this.a.a(this.jdField_a_of_type_WV.b / (xm.b() / 2), ((xu.X.b()) || (xu.Y.b()) ? -this.jdField_a_of_type_WV.c : this.jdField_a_of_type_WV.c) / (xm.a() / 2), 0.0F, a(), b(), 0.0F);
/*     */       }
/*     */ 
/* 178 */       if (this.jdField_a_of_type_Dt.jdField_a_of_type_Boolean)
/*     */       {
/* 180 */         this.jdField_a_of_type_Dt.jdField_a_of_type_Du.getWorldTransform().basis.setIdentity();
/* 181 */         this.a.a();
/* 182 */         this.jdField_a_of_type_Dt.jdField_a_of_type_Boolean = false;
/*     */       }
/*     */ 
/* 186 */       b(paramxq);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     du
 * JD-Core Version:    0.6.2
 */