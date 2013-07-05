/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL15;
/*     */ import org.lwjgl.util.vector.Matrix4f;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.forms.Mesh;
/*     */ 
/*     */ public final class fp
/*     */   implements xg
/*     */ {
/*     */   private ct jdField_a_of_type_Ct;
/*     */   private float jdField_a_of_type_Float;
/*     */   private Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f;
/*     */   private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private FloatBuffer jdField_a_of_type_JavaNioFloatBuffer;
/*     */   private float[] jdField_a_of_type_ArrayOfFloat;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private Mesh jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
/*     */ 
/*     */   public fp(ct paramct)
/*     */   {
/*  28 */     new Transform();
/*     */ 
/*  30 */     new Vector3f();
/*  31 */     this.jdField_a_of_type_Float = 0.0F;
/*     */ 
/*  33 */     this.jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/*  34 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  35 */     this.jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  36 */     this.jdField_a_of_type_ArrayOfFloat = new float[16];
/*     */ 
/*  40 */     this.jdField_a_of_type_Ct = paramct;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  49 */     if (!this.jdField_a_of_type_Boolean)
/*  50 */       c();
/*     */     try
/*     */     {
/*     */       kd localkd;
/*  54 */       if ((
/*  54 */         localkd = this.jdField_a_of_type_Ct.a()) != null)
/*     */       {
/*  63 */         Matrix4f localMatrix4f = xe.a;
/*  64 */         this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  65 */         localMatrix4f.store(this.jdField_a_of_type_JavaNioFloatBuffer);
/*  66 */         this.jdField_a_of_type_JavaNioFloatBuffer.rewind();
/*  67 */         this.jdField_a_of_type_JavaNioFloatBuffer.get(this.jdField_a_of_type_ArrayOfFloat);
/*  68 */         this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setFromOpenGLMatrix(this.jdField_a_of_type_ArrayOfFloat);
/*  69 */         this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin.set(0.0F, 0.0F, 0.0F);
/*     */ 
/*  72 */         yz.j();
/*  73 */         GlUtil.d();
/*     */ 
/*  76 */         GlUtil.c(xm.b() / 2, 105.0F, 0.0F);
/*     */ 
/*  79 */         GlUtil.b(10.0F, -10.0F, 10.0F);
/*     */ 
/*  81 */         this.jdField_a_of_type_JavaxVecmathMatrix3f.set(localkd.getWorldTransform().basis);
/*  82 */         this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.basis.mul(this.jdField_a_of_type_JavaxVecmathMatrix3f);
/*     */ 
/*  84 */         GlUtil.b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */ 
/*  88 */         GL11.glDisable(2896);
/*  89 */         GL11.glDisable(2929);
/*  90 */         GL11.glEnable(2977);
/*  91 */         GL11.glDisable(2884);
/*  92 */         GL11.glEnable(2929);
/*     */ 
/*  94 */         this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.a().a();
/*     */ 
/*  97 */         this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.f();
/*     */ 
/*  99 */         this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.a().c();
/* 100 */         GlUtil.c();
/* 101 */         yz.h();
/* 102 */         this.jdField_a_of_type_Float += 0.15F;
/* 103 */         GL11.glEnable(2896);
/* 104 */         GL11.glDisable(2977);
/* 105 */         GL11.glEnable(2929);
/* 106 */         GL15.glBindBuffer(34962, 0);
/*     */       }return;
/*     */     } catch (NullPointerException localNullPointerException) {
/* 109 */       System.err.println("EXCPETION HAS BEEN HANDLED");
/* 110 */       localNullPointerException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 127 */     this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)xe.a().a("Arrow").a().get(0));
/* 128 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fp
 * JD-Core Version:    0.6.2
 */