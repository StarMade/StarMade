/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.SegmentDrawer;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ 
/*     */ public class mr extends mw
/*     */ {
/*     */   private boolean jdField_d_of_type_Boolean;
/*  78 */   private boolean jdField_e_of_type_Boolean = false;
/*     */ 
/*  84 */   private boolean jdField_f_of_type_Boolean = true;
/*     */   public long a;
/*     */   private CubeMeshBufferContainer jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer;
/*     */   private dI jdField_a_of_type_DI;
/*     */   private dI jdField_b_of_type_DI;
/*     */   public Object a;
/*     */   public long b;
/*     */   public boolean a;
/*     */   private boolean jdField_g_of_type_Boolean;
/*     */   public ms a;
/*     */   public int a;
/*     */   public boolean b;
/*     */   private int jdField_b_of_type_Int;
/*     */   public long c;
/* 223 */   private static Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 224 */   private static Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 225 */   private static Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 226 */   private static Vector3f c = new Vector3f();
/* 227 */   private static Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/* 228 */   private static Vector3f jdField_e_of_type_JavaxVecmathVector3f = new Vector3f();
/* 229 */   private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 230 */   private Vector3f jdField_f_of_type_JavaxVecmathVector3f = new Vector3f();
/* 231 */   private Vector3f jdField_g_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */   public float a;
/*     */ 
/*     */   public mr(SegmentController paramSegmentController)
/*     */   {
/* 123 */     super(paramSegmentController);
/*     */ 
/*  86 */     this.jdField_a_of_type_JavaLangObject = new Object();
/*     */ 
/* 103 */     this.jdField_a_of_type_Ms = ms.jdField_a_of_type_Ms;
/*     */ 
/* 105 */     this.jdField_a_of_type_Int = 0;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 131 */     synchronized (this.jdField_a_of_type_JavaLangObject)
/*     */     {
/* 133 */       dI localdI = this.jdField_b_of_type_DI;
/*     */ 
/* 135 */       if ((!h) && (this.jdField_b_of_type_DI == this.jdField_a_of_type_DI)) throw new AssertionError();
/*     */ 
/* 137 */       this.jdField_b_of_type_DI = this.jdField_a_of_type_DI;
/*     */ 
/* 139 */       SegmentDrawer.a.a(localdI);
/*     */ 
/* 141 */       this.jdField_a_of_type_DI = null;
/*     */ 
/* 143 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 165 */     super.a(paramBoolean);
/*     */ 
/* 168 */     e(true);
/*     */ 
/* 171 */     ((ct)this.a.getState()).a().a().jdField_d_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 183 */     synchronized (this.jdField_a_of_type_JavaLangObject) {
/* 184 */       mr localmr1 = this; synchronized (this.jdField_a_of_type_JavaLangObject) { SegmentDrawer.a.a(localmr1.jdField_b_of_type_DI); localmr1.jdField_b_of_type_DI = null; }
/* 185 */       mr localmr2 = this; synchronized (this.jdField_a_of_type_JavaLangObject) { SegmentDrawer.a.a(localmr2.jdField_a_of_type_DI); localmr2.jdField_a_of_type_DI = null; }
/* 186 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/*     */     Transform localTransform1;
/* 204 */     if (!(
/* 204 */       localTransform1 = this.a.getWorldTransformClient())
/* 204 */       .equals(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform)) {
/* 205 */       paramVector3f1.set(this.jdField_a_of_type_Q.jdField_a_of_type_Int, this.jdField_a_of_type_Q.jdField_b_of_type_Int, this.jdField_a_of_type_Q.c);
/* 206 */       paramVector3f2.set(paramVector3f1);
/* 207 */       paramVector3f2.x += 8.0F;
/* 208 */       paramVector3f2.y += 8.0F;
/* 209 */       paramVector3f2.z += 8.0F;
/*     */ 
/* 211 */       paramVector3f1.x -= 8.0F;
/* 212 */       paramVector3f1.y -= 8.0F;
/* 213 */       paramVector3f1.z -= 8.0F;
/*     */ 
/* 217 */       Vector3f localVector3f4 = this.jdField_g_of_type_JavaxVecmathVector3f; Vector3f localVector3f3 = this.jdField_f_of_type_JavaxVecmathVector3f; Transform localTransform2 = localTransform1; Vector3f localVector3f2 = paramVector3f2; Vector3f localVector3f1 = paramVector3f1; if ((!h) && (localVector3f1.x > localVector3f2.x)) throw new AssertionError(); if ((!h) && (localVector3f1.y > localVector3f2.y)) throw new AssertionError(); if ((!h) && (localVector3f1.z > localVector3f2.z)) throw new AssertionError(); jdField_a_of_type_JavaxVecmathVector3f.sub(localVector3f2, localVector3f1); jdField_a_of_type_JavaxVecmathVector3f.scale(0.5F); jdField_a_of_type_JavaxVecmathVector3f.x += 0.0F; jdField_a_of_type_JavaxVecmathVector3f.y += 0.0F; jdField_a_of_type_JavaxVecmathVector3f.z += 0.0F; jdField_b_of_type_JavaxVecmathVector3f.add(localVector3f2, localVector3f1); jdField_b_of_type_JavaxVecmathVector3f.scale(0.5F); jdField_a_of_type_JavaxVecmathMatrix3f.set(localTransform2.basis); MatrixUtil.absolute(jdField_a_of_type_JavaxVecmathMatrix3f); c.set(jdField_b_of_type_JavaxVecmathVector3f); localTransform2.transform(c); jdField_a_of_type_JavaxVecmathMatrix3f.getRow(0, jdField_e_of_type_JavaxVecmathVector3f); jdField_d_of_type_JavaxVecmathVector3f.x = jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_a_of_type_JavaxVecmathVector3f); jdField_a_of_type_JavaxVecmathMatrix3f.getRow(1, jdField_e_of_type_JavaxVecmathVector3f); jdField_d_of_type_JavaxVecmathVector3f.y = jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_a_of_type_JavaxVecmathVector3f); jdField_a_of_type_JavaxVecmathMatrix3f.getRow(2, jdField_e_of_type_JavaxVecmathVector3f); jdField_d_of_type_JavaxVecmathVector3f.z = jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_a_of_type_JavaxVecmathVector3f); localVector3f3.sub(c, jdField_d_of_type_JavaxVecmathVector3f); localVector3f4.add(c, jdField_d_of_type_JavaxVecmathVector3f);
/* 218 */       this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(localTransform1);
/*     */     }
/* 220 */     paramVector3f1.set(this.jdField_f_of_type_JavaxVecmathVector3f);
/* 221 */     paramVector3f2.set(this.jdField_g_of_type_JavaxVecmathVector3f);
/*     */   }
/*     */ 
/*     */   public final CubeMeshBufferContainer a()
/*     */   {
/* 280 */     if ((!h) && (this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer != null)) throw new AssertionError();
/*     */ 
/* 282 */     CubeMeshBufferContainer localCubeMeshBufferContainer = dG.a();
/* 283 */     this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer = localCubeMeshBufferContainer;
/* 284 */     return localCubeMeshBufferContainer;
/*     */   }
/*     */ 
/*     */   public final CubeMeshBufferContainer b()
/*     */   {
/* 290 */     return this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer;
/*     */   }
/*     */ 
/*     */   public final dI a()
/*     */   {
/* 296 */     return this.jdField_b_of_type_DI;
/*     */   }
/*     */ 
/*     */   public final dI b()
/*     */   {
/* 303 */     return this.jdField_a_of_type_DI;
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/* 309 */     return this.jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 321 */     return this.jdField_f_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final boolean b()
/*     */   {
/* 328 */     return this.jdField_g_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final boolean c()
/*     */   {
/* 338 */     return this.jdField_e_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final boolean d()
/*     */   {
/* 349 */     return this.jdField_d_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 354 */     if (this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer != null) {
/* 355 */       dG.a(this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer);
/* 356 */       this.jdField_a_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 364 */     if (this.jdField_g_of_type_Boolean != paramBoolean) {
/* 365 */       this.a.getSegmentBuffer().a(paramBoolean ? 1 : -1, this);
/*     */     }
/* 367 */     this.jdField_g_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final void c(boolean paramBoolean)
/*     */   {
/* 381 */     this.jdField_f_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final void d(boolean paramBoolean)
/*     */   {
/* 395 */     this.jdField_e_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final void e(boolean paramBoolean)
/*     */   {
/* 400 */     if (paramBoolean)
/*     */     {
/* 402 */       this.jdField_f_of_type_Boolean = true;
/*     */     }
/* 404 */     this.jdField_d_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final void a(dI paramdI)
/*     */   {
/* 418 */     this.jdField_a_of_type_DI = paramdI;
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt) {
/* 422 */     this.jdField_b_of_type_Int = paramInt;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mr
 * JD-Core Version:    0.6.2
 */