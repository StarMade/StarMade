/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ 
/*     */ public final class dx extends xb
/*     */   implements wx
/*     */ {
/*  21 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */   private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   private al jdField_a_of_type_Al;
/*  24 */   private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*     */   private float jdField_a_of_type_Float;
/*     */   private float jdField_b_of_type_Float;
/*     */ 
/*     */   public dx(al paramal)
/*     */   {
/*  39 */     super(paramal.a());
/*     */ 
/*  27 */     new Vector3f();
/*     */ 
/*  29 */     new Vector3f();
/*     */ 
/*  31 */     new Vector3f();
/*     */ 
/*  34 */     this.jdField_a_of_type_Float = 25.0F;
/*  35 */     this.jdField_b_of_type_Float = 5.0F;
/*  36 */     new q();
/*  37 */     new o();
/*     */ 
/*  40 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramal.a();
/*  41 */     this.jdField_a_of_type_Al = paramal;
/*     */   }
/*     */ 
/*     */   public final synchronized Vector3f a() {
/*  45 */     Vector3f localVector3f1 = super.a();
/*  46 */     Vector3f localVector3f2 = b();
/*     */ 
/*  49 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.a.getWorldTransform());
/*  50 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.basis.transform(localVector3f2);
/*  51 */     localVector3f1.add(localVector3f2);
/*  52 */     return localVector3f1;
/*     */   }
/*     */   public final Vector3f b() {
/*  55 */     return new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f.x + this.jdField_a_of_type_Al.a().a - 8.0F, this.jdField_a_of_type_JavaxVecmathVector3f.y + this.jdField_a_of_type_Al.a().b - 8.0F, this.jdField_a_of_type_JavaxVecmathVector3f.z + this.jdField_a_of_type_Al.a().c - 10.0F);
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(q paramq)
/*     */   {
/*  73 */     paramq.c(this.jdField_a_of_type_Al.a());
/*  74 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(paramq.a, paramq.b, paramq.c);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/*  82 */     if ((!((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a().a().a().a().a().a().g()) && (!((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a().a().a().a().g()))
/*     */     {
/*  88 */       return;
/*     */     }
/*  90 */     if (((ct)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a().a().a().c()) {
/*  91 */       return;
/*     */     }
/*     */ 
/*  94 */     Vector3f localVector3f1 = new Vector3f(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamForwLocal());
/*  95 */     Vector3f localVector3f2 = new Vector3f(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamUpLocal());
/*  96 */     Vector3f localVector3f3 = new Vector3f(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamLeftLocal());
/*     */ 
/* 100 */     float f = Keyboard.isKeyDown(42) ? this.jdField_a_of_type_Float : this.jdField_b_of_type_Float;
/* 101 */     localVector3f1.scale(f * paramxq.a());
/* 102 */     localVector3f2.scale(f * paramxq.a());
/* 103 */     localVector3f3.scale(f * paramxq.a());
/*     */ 
/* 107 */     if ((!cv.c.a()) || (!cv.d.a())) {
/* 108 */       if (cv.c.a()) {
/* 109 */         this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f1);
/*     */       }
/* 111 */       if (cv.d.a()) {
/* 112 */         localVector3f1.scale(-1.0F);
/* 113 */         this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f1);
/*     */       }
/*     */     }
/* 116 */     if ((!cv.a.a()) || (!cv.b.a())) {
/* 117 */       if (cv.a.a()) {
/* 118 */         localVector3f3.scale(-1.0F);
/* 119 */         this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f3);
/*     */       }
/* 121 */       if (cv.b.a()) {
/* 122 */         this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f3);
/*     */       }
/*     */     }
/* 125 */     if ((!cv.f.a()) || (!cv.e.a())) {
/* 126 */       if (cv.f.a()) {
/* 127 */         localVector3f2.scale(-1.0F);
/* 128 */         this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f2);
/*     */       }
/* 130 */       if (cv.e.a())
/* 131 */         this.jdField_a_of_type_JavaxVecmathVector3f.add(localVector3f2);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dx
 * JD-Core Version:    0.6.2
 */