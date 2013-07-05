/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public class yf
/*     */   implements zn
/*     */ {
/*     */   private final ye jdField_a_of_type_Ye;
/*     */   private final yj jdField_a_of_type_Yj;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private Transform[] jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform;
/*     */   private int jdField_a_of_type_Int;
/*     */ 
/*     */   public yf(ye paramye, yj paramyj)
/*     */   {
/*  24 */     this.jdField_a_of_type_Ye = paramye;
/*  25 */     this.jdField_a_of_type_Yj = paramyj;
/*     */   }
/*     */ 
/*     */   public final ye a()
/*     */   {
/*  32 */     return this.jdField_a_of_type_Ye;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*  42 */     if (!this.jdField_a_of_type_Boolean) {
/*  43 */       yf localyf = this; this.jdField_a_of_type_Yj.a(); localyf.jdField_a_of_type_Boolean = true;
/*     */     }
/*  45 */     d.C.a = this;
/*  46 */     d.C.b();
/*  47 */     this.jdField_a_of_type_Yj.b();
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/*  56 */     GL13.glActiveTexture(33984);
/*  57 */     GL11.glBindTexture(3553, 0);
/*     */   }
/*     */ 
/*     */   public final void b() {
/*  61 */     yj.c();
/*  62 */     d.C.d();
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/*  70 */     this.jdField_a_of_type_Ye.b(paramxq);
/*  71 */     this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform = this.jdField_a_of_type_Ye.a();
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/*  82 */     if ((!b) && (this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform == null)) throw new AssertionError();
/*  83 */     GlUtil.a(paramzj, "m_BoneMatrices", this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform);
/*     */ 
/*  91 */     GL13.glActiveTexture(33984);
/*  92 */     GL11.glBindTexture(3553, this.jdField_a_of_type_Int);
/*  93 */     GlUtil.a(paramzj, "diffuseMap", 0);
/*     */ 
/*  99 */     GL13.glActiveTexture(33984);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt)
/*     */   {
/* 114 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yf
 * JD-Core Version:    0.6.2
 */