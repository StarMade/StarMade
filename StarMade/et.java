/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public final class et
/*     */   implements xg
/*     */ {
/*     */   private zd jdField_a_of_type_Zd;
/*     */   eK jdField_a_of_type_EK;
/*  24 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   ld jdField_a_of_type_Ld;
/*  79 */   Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  99 */   q jdField_a_of_type_Q = new q();
/*     */ 
/*     */   public et(ld paramld)
/*     */   {
/*  29 */     this.jdField_a_of_type_Ld = paramld;
/*  30 */     this.jdField_a_of_type_EK = new eK();
/*     */ 
/*  32 */     this.jdField_a_of_type_Zd = new zd(this.jdField_a_of_type_EK, 8.0F);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  41 */     if (this.jdField_a_of_type_Boolean) {
/*  42 */       c();
/*     */     }
/*  44 */     GlUtil.d();
/*  45 */     GlUtil.b(this.jdField_a_of_type_Ld.a().getWorldTransformClient());
/*     */ 
/*  47 */     this.jdField_a_of_type_Zd.a.set(this.jdField_a_of_type_Ld.a().getWorldTransformClient());
/*  48 */     this.jdField_a_of_type_Zd.b();
/*  49 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  59 */     if (this.jdField_a_of_type_Zd.a == null) {
/*  60 */       this.jdField_a_of_type_Zd.a = new Transform();
/*     */     }
/*  62 */     this.jdField_a_of_type_Zd.c();
/*     */ 
/*  64 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 109 */     return this.jdField_a_of_type_EK.b() > 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     et
 * JD-Core Version:    0.6.2
 */