/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ 
/*     */ public final class dr extends Camera
/*     */   implements dw, wx
/*     */ {
/*     */   private SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   private wW jdField_a_of_type_WW;
/*     */   private Camera jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/*     */ 
/*     */   public dr(Camera paramCamera, al paramal)
/*     */   {
/*  77 */     super(new dx(paramal));
/*  78 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramal.a();
/*     */ 
/*  81 */     this.a = new wX(this);
/*     */ 
/*  84 */     this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera;
/*     */   }
/*     */ 
/*     */   public final Vector3f b()
/*     */   {
/*  93 */     return ((dx)a()).b();
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/* 102 */     ((dx)a()).handleKeyEvent();
/*     */   }
/*     */ 
/*     */   public final void a(q paramq)
/*     */   {
/* 128 */     ((dx)a()).a(new q(paramq));
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 141 */     if (!cv.U.a()) {
/* 142 */       ((wX)this.a).a.set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/*     */ 
/* 144 */       super.a(paramxq);
/*     */     }
/*     */ 
/* 148 */     if ((this.jdField_a_of_type_WW != null) && (this.jdField_a_of_type_WW.a()))
/*     */     {
/* 150 */       this.jdField_a_of_type_WW.a(paramxq);
/* 151 */       getWorldTransform().set(this.jdField_a_of_type_WW.getWorldTransform());
/*     */     }
/*     */ 
/* 154 */     if (this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera != null) {
/* 155 */       a(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
/* 156 */       this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(Camera paramCamera)
/*     */   {
/* 171 */     this.jdField_a_of_type_WW = new wW(paramCamera, this);
/*     */   }
/*     */ 
/*     */   public final SegmentController a() {
/* 175 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dr
 * JD-Core Version:    0.6.2
 */