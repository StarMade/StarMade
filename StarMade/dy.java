/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ 
/*     */ public class dy extends xb
/*     */ {
/*     */   protected q a;
/*  17 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */   protected SegmentController a;
/*     */   protected al a;
/*  20 */   private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  21 */   private float jdField_a_of_type_Float = 13.0F;
/*     */ 
/*  23 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*     */   public dy(al paramal)
/*     */   {
/*  29 */     super(paramal.a());
/*     */ 
/*  16 */     this.jdField_a_of_type_Q = new q();
/*     */ 
/*  24 */     new Vector3f();
/*  25 */     new Vector3f();
/*  26 */     new q();
/*  27 */     new o();
/*     */ 
/*  30 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramal.a();
/*  31 */     this.jdField_a_of_type_Al = paramal;
/*     */   }
/*     */ 
/*     */   public final synchronized Vector3f a()
/*     */   {
/*  46 */     Vector3f localVector3f = super.a();
/*     */ 
/*  48 */     Object localObject = this; localObject = new Vector3f(((dy)localObject).jdField_a_of_type_JavaxVecmathVector3f.x + ((dy)localObject).jdField_a_of_type_Al.a().a - 8.0F, ((dy)localObject).jdField_a_of_type_JavaxVecmathVector3f.y + ((dy)localObject).jdField_a_of_type_Al.a().b - 8.0F, ((dy)localObject).jdField_a_of_type_JavaxVecmathVector3f.z + ((dy)localObject).jdField_a_of_type_Al.a().c - 8.0F);
/*     */ 
/*  52 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.a.getWorldTransform());
/*     */ 
/*  54 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.basis.transform((Tuple3f)localObject);
/*  55 */     localVector3f.add((Tuple3f)localObject);
/*  56 */     return localVector3f;
/*     */   }
/*     */   public final q a() {
/*  59 */     return this.jdField_a_of_type_Q;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*  79 */     this.jdField_a_of_type_Float = 50.0F;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq) {
/*  83 */     this.jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_Q.a, this.jdField_a_of_type_Q.b, this.jdField_a_of_type_Q.c);
/*  84 */     this.jdField_b_of_type_JavaxVecmathVector3f.sub(this.jdField_a_of_type_JavaxVecmathVector3f);
/*     */     float f1;
/*  87 */     if ((
/*  87 */       f1 = this.jdField_b_of_type_JavaxVecmathVector3f.length()) <= 
/*  87 */       0.0F) {
/*  88 */       return;
/*     */     }
/*  90 */     float f2 = this.jdField_b_of_type_JavaxVecmathVector3f.length();
/*  91 */     this.jdField_b_of_type_JavaxVecmathVector3f.normalize();
/*     */ 
/*  93 */     this.jdField_b_of_type_JavaxVecmathVector3f.scale(paramxq.a() * Math.max(f2 * 3.0F, this.jdField_a_of_type_Float));
/*     */ 
/*  97 */     if (this.jdField_b_of_type_JavaxVecmathVector3f.length() < f1)
/*     */     {
/*  99 */       this.jdField_a_of_type_JavaxVecmathVector3f.add(this.jdField_b_of_type_JavaxVecmathVector3f); return;
/*     */     }
/*     */ 
/* 104 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_Q.a, this.jdField_a_of_type_Q.b, this.jdField_a_of_type_Q.c);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dy
 * JD-Core Version:    0.6.2
 */