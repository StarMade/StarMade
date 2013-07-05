/*     */ package org.schema.schine.graphicsengine.camera;
/*     */ 
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.util.vector.Matrix4f;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientStateInterface;
/*     */ import wU;
/*     */ import wV;
/*     */ import wY;
/*     */ import wZ;
/*     */ import xX;
/*     */ import xa;
/*     */ import xe;
/*     */ import xm;
/*     */ import xq;
/*     */ import xu;
/*     */ import yh;
/*     */ 
/*     */ public class Camera
/*     */   implements xX, yh
/*     */ {
/*  55 */   private float jdField_d_of_type_Float = 0.0007F;
/*     */   private float[][] jdField_a_of_type_Array2dOfFloat;
/*     */   protected wV a;
/*  70 */   private float[] jdField_a_of_type_ArrayOfFloat = new float[16];
/*     */   private float[] jdField_b_of_type_ArrayOfFloat;
/*     */   private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*  73 */   private static FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*     */ 
/*  75 */   private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*     */ 
/*  78 */   private int jdField_a_of_type_Int = 0;
/*     */   private xa jdField_a_of_type_Xa;
/*     */   public wZ a;
/*     */   private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private Transform jdField_c_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*     */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/*     */   private Vector3f jdField_c_of_type_JavaxVecmathVector3f;
/*     */   private Vector3f jdField_d_of_type_JavaxVecmathVector3f;
/*     */   private Vector3f jdField_e_of_type_JavaxVecmathVector3f;
/*     */   private Vector3f f;
/*     */   private Vector3f g;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   public float a;
/*     */   private float jdField_e_of_type_Float;
/*     */   private ThreadLocal jdField_a_of_type_JavaLangThreadLocal;
/*     */   private final Vector3f h;
/*     */   public float b;
/*     */   public float c;
/*     */   private final Vector3f i;
/*     */   private final Vector3f j;
/*     */   private final Vector3f k;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  31 */     paramArrayOfString = new float[] { -0.3499902F, -5.811467E-007F, 0.9367533F, 0.0F, 0.5169892F, 0.8339127F, 0.1931583F, 0.0F, -0.7811701F, 0.5518945F, -0.2918607F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F };
/*     */     Transform localTransform;
/*  35 */     (
/*  36 */       localTransform = new Transform())
/*  36 */       .setFromOpenGLMatrix(paramArrayOfString);
/*     */ 
/*  38 */     (
/*  39 */       paramArrayOfString = new Vector3f(188.99323F, 77.021645F, 294.43661F))
/*  39 */       .negate();
/*  40 */     Vector3f localVector3f1 = new Vector3f(paramArrayOfString);
/*  41 */     Vector3f localVector3f2 = new Vector3f();
/*  42 */     localTransform.transform(localVector3f1);
/*     */     do
/*     */     {
/*  45 */       localVector3f2.set(paramArrayOfString);
/*  46 */       localTransform.basis.transform(localVector3f2);
/*  47 */     }while (localVector3f2.equals(localVector3f1));
/*  48 */     throw new NullPointerException(localVector3f2 + "; " + localVector3f1);
/*     */   }
/*     */ 
/*     */   public Camera(xa paramxa)
/*     */   {
/*  84 */     new Vector3f();
/*     */ 
/*  86 */     new Vector3f();
/*     */ 
/*  88 */     new Vector3f();
/*  89 */     new Vector3f();
/*  90 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  91 */     this.jdField_c_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*     */ 
/*  93 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  94 */     this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  95 */     this.jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*  96 */     this.jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  98 */     this.jdField_e_of_type_JavaxVecmathVector3f = new Vector3f();
/*  99 */     this.f = new Vector3f();
/* 100 */     this.g = new Vector3f();
/* 101 */     this.jdField_a_of_type_Boolean = true;
/*     */ 
/* 105 */     this.jdField_a_of_type_JavaLangThreadLocal = new wU();
/*     */ 
/* 111 */     this.h = new Vector3f();
/* 112 */     this.jdField_b_of_type_Float = 1.0F;
/* 113 */     this.jdField_c_of_type_Float = 1.0F;
/*     */ 
/* 118 */     this.i = new Vector3f();
/* 119 */     this.j = new Vector3f();
/* 120 */     this.k = new Vector3f();
/*     */ 
/* 132 */     xa localxa1 = paramxa; paramxa = this; this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform(); paramxa.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity(); paramxa.jdField_b_of_type_ArrayOfFloat = new float[16]; paramxa.a(); paramxa.jdField_a_of_type_WZ = new wY(paramxa); paramxa.jdField_a_of_type_Array2dOfFloat = new float[6][4]; xa localxa2 = localxa1; (localxa1 = paramxa).jdField_a_of_type_Xa = localxa2; localxa2.a = localxa1; localxa1.a(); paramxa.jdField_a_of_type_WV = new wV();
/*     */   }
/*     */ 
/*     */   public final Vector3f a(Vector3f paramVector3f)
/*     */   {
/* 154 */     return GlUtil.c(paramVector3f, getWorldTransform());
/*     */   }
/*     */ 
/*     */   public final Vector3f c() {
/* 158 */     return GlUtil.c(new Vector3f(), getWorldTransform());
/*     */   }
/*     */ 
/*     */   public final Vector3f d() {
/* 162 */     return GlUtil.d(new Vector3f(), getWorldTransform());
/*     */   }
/*     */   public final Vector3f b(Vector3f paramVector3f) {
/* 165 */     return GlUtil.d(paramVector3f, getWorldTransform());
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 191 */     return this.jdField_c_of_type_Float * ((Float)xu.T.a()).floatValue();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 197 */     return this.jdField_b_of_type_Float * ((Float)xu.T.a()).floatValue();
/*     */   }
/*     */ 
/*     */   public final Vector3f c(Vector3f paramVector3f)
/*     */   {
/* 207 */     Vector3f localVector3f1 = (Vector3f)a().get();
/* 208 */     Vector3f localVector3f2 = (Vector3f)a().get();
/*     */     try
/*     */     {
/* 212 */       localVector3f1.set(getWorldTransform().origin);
/* 213 */       localVector3f2.set(c());
/* 214 */       localVector3f2.negate();
/* 215 */       localVector3f2.scale(this.jdField_a_of_type_Float);
/* 216 */       localVector3f1.add(localVector3f2);
/* 217 */       paramVector3f.set(localVector3f1);
/* 218 */       return localVector3f1;
/*     */     } finally {
/* 220 */       a().release(localVector3f1);
/* 221 */       a().release(localVector3f2); } throw paramVector3f;
/*     */   }
/*     */ 
/*     */   private ObjectPool a()
/*     */   {
/* 230 */     return (ObjectPool)this.jdField_a_of_type_JavaLangThreadLocal.get();
/*     */   }
/*     */ 
/*     */   public final Vector3f a()
/*     */   {
/* 235 */     return this.h;
/*     */   }
/*     */ 
/*     */   public final Vector3f e()
/*     */   {
/* 244 */     return GlUtil.e(new Vector3f(), getWorldTransform());
/*     */   }
/*     */ 
/*     */   public final Vector3f f()
/*     */   {
/* 251 */     return GlUtil.f(new Vector3f(), getWorldTransform());
/*     */   }
/*     */   public final Vector3f d(Vector3f paramVector3f) {
/* 254 */     return GlUtil.f(paramVector3f, getWorldTransform());
/*     */   }
/*     */ 
/*     */   public final xa a()
/*     */   {
/* 264 */     if ((!jdField_b_of_type_Boolean) && (this.jdField_a_of_type_Xa == null)) throw new AssertionError();
/* 265 */     return this.jdField_a_of_type_Xa;
/*     */   }
/*     */ 
/*     */   public Transform getWorldTransform()
/*     */   {
/* 272 */     return this.jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*     */   }
/*     */ 
/*     */   public final boolean a(Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/* 333 */     float f5 = paramVector3f2.z; float f4 = paramVector3f2.y; float f3 = paramVector3f2.x; float f2 = paramVector3f1.z; float f1 = paramVector3f1.y; paramVector3f2 = paramVector3f1.x; paramVector3f1 = this; for (int m = 0; m < 6; m++) if ((paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * paramVector3f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f1 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * f3 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f1 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * paramVector3f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f4 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * f3 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f4 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * paramVector3f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f1 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f5 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * f3 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f1 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f5 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * paramVector3f2 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f4 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f5 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) && (paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][0] * f3 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][1] * f4 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][2] * f5 + paramVector3f1.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F)) return false; 
/* 333 */     return true;
/*     */   }
/*     */ 
/*     */   public final boolean a(float paramFloat1, float paramFloat2, float paramFloat3)
/*     */   {
/* 362 */     for (int m = 0; m < 6; m++)
/*     */     {
/* 364 */       if (this.jdField_a_of_type_Array2dOfFloat[m][0] * paramFloat1 + this.jdField_a_of_type_Array2dOfFloat[m][1] * paramFloat2 + this.jdField_a_of_type_Array2dOfFloat[m][2] * paramFloat3 + this.jdField_a_of_type_Array2dOfFloat[m][3] <= 0.0F) {
/* 365 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 369 */     return true;
/*     */   }
/*     */   public final boolean a(Vector3f paramVector3f) {
/* 372 */     return a(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/*     */   }
/*     */ 
/*     */   protected int a(int paramInt)
/*     */   {
/* 377 */     return Math.max(0, paramInt);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 388 */     c(this.h);
/*     */ 
/* 391 */     c().normalize();
/* 392 */     f().normalize();
/*     */ 
/* 394 */     this.jdField_d_of_type_JavaxVecmathVector3f.cross(f(), c());
/* 395 */     this.jdField_d_of_type_JavaxVecmathVector3f.normalize();
/* 396 */     this.jdField_d_of_type_JavaxVecmathVector3f.negate();
/*     */ 
/* 399 */     this.g.set(c());
/* 400 */     this.g.negate();
/*     */ 
/* 404 */     this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.basis.setRow(0, this.jdField_d_of_type_JavaxVecmathVector3f);
/* 405 */     this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.basis.setRow(1, f());
/* 406 */     this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.basis.setRow(2, this.g);
/*     */ 
/* 408 */     this.f.set(this.h);
/*     */ 
/* 410 */     this.f.negate();
/*     */ 
/* 412 */     this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.origin.set(this.f);
/*     */ 
/* 414 */     this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.basis.transform(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.origin);
/*     */ 
/* 416 */     GlUtil.a(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform);
/*     */ 
/* 421 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(this.f);
/* 422 */     this.jdField_b_of_type_JavaxVecmathVector3f.set(this.g);
/* 423 */     this.jdField_c_of_type_JavaxVecmathVector3f.set(f());
/* 424 */     this.jdField_e_of_type_JavaxVecmathVector3f.set(this.jdField_d_of_type_JavaxVecmathVector3f);
/* 425 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_c_of_type_ComBulletphysicsLinearmathTransform);
/* 426 */     d();
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/* 438 */     getWorldTransform().setIdentity();
/* 439 */     a(new Vector3f(0.0F, 0.0F, -1.0F));
/*     */ 
/* 441 */     b(new Vector3f(1.0F, 0.0F, 0.0F));
/* 442 */     c(new Vector3f(0.0F, 1.0F, 0.0F));
/*     */ 
/* 444 */     getWorldTransform().getOpenGLMatrix(this.jdField_b_of_type_ArrayOfFloat);
/*     */ 
/* 450 */     this.jdField_a_of_type_Float = this.jdField_e_of_type_Float;
/*     */   }
/*     */ 
/*     */   public final void a(float paramFloat)
/*     */   {
/* 475 */     this.jdField_e_of_type_Float = paramFloat;
/* 476 */     this.jdField_a_of_type_Boolean = true;
/* 477 */     a();
/*     */   }
/*     */ 
/*     */   public final void a(Vector3f paramVector3f)
/*     */   {
/* 488 */     GlUtil.a(paramVector3f, getWorldTransform());
/*     */   }
/*     */ 
/*     */   public final void b(Vector3f paramVector3f)
/*     */   {
/* 528 */     GlUtil.c(paramVector3f, getWorldTransform());
/*     */   }
/*     */ 
/*     */   public final void c(Vector3f paramVector3f) {
/* 532 */     GlUtil.d(paramVector3f, getWorldTransform());
/*     */   }
/*     */ 
/*     */   public void a(xq paramxq)
/*     */   {
/*     */     boolean bool;
/* 537 */     if (((
/* 537 */       bool = wV.a())) || 
/* 537 */       (this.jdField_a_of_type_Boolean))
/*     */     {
/* 539 */       e();
/*     */ 
/* 541 */       if ((!bool) && (this.jdField_a_of_type_Boolean))
/*     */       {
/* 544 */         this.jdField_a_of_type_WZ.a(0.0F, 0.0F, 0.0F, a(), b(), 0.0F);
/*     */       }
/*     */       else
/*     */       {
/* 552 */         this.jdField_a_of_type_WZ.a(this.jdField_a_of_type_WV.b / (xm.b() / 2), this.jdField_a_of_type_WV.c / (xm.a() / 2), 0.0F, a(), b(), 0.0F);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 561 */     b(paramxq);
/*     */ 
/* 563 */     GlUtil.f(this.i, getWorldTransform());
/* 564 */     GlUtil.e(this.j, getWorldTransform());
/* 565 */     GlUtil.c(this.k, getWorldTransform());
/*     */   }
/*     */   public final void d() {
/* 568 */     jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 569 */     jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 570 */     xe.b.store(jdField_a_of_type_JavaNioFloatBuffer);
/* 571 */     xe.a.store(jdField_b_of_type_JavaNioFloatBuffer);
/*     */ 
/* 576 */     this.jdField_a_of_type_ArrayOfFloat[0] = (jdField_b_of_type_JavaNioFloatBuffer.get(0) * jdField_a_of_type_JavaNioFloatBuffer.get(0) + jdField_b_of_type_JavaNioFloatBuffer.get(1) * jdField_a_of_type_JavaNioFloatBuffer.get(4) + jdField_b_of_type_JavaNioFloatBuffer.get(2) * jdField_a_of_type_JavaNioFloatBuffer.get(8) + jdField_b_of_type_JavaNioFloatBuffer.get(3) * jdField_a_of_type_JavaNioFloatBuffer.get(12));
/* 577 */     this.jdField_a_of_type_ArrayOfFloat[1] = (jdField_b_of_type_JavaNioFloatBuffer.get(0) * jdField_a_of_type_JavaNioFloatBuffer.get(1) + jdField_b_of_type_JavaNioFloatBuffer.get(1) * jdField_a_of_type_JavaNioFloatBuffer.get(5) + jdField_b_of_type_JavaNioFloatBuffer.get(2) * jdField_a_of_type_JavaNioFloatBuffer.get(9) + jdField_b_of_type_JavaNioFloatBuffer.get(3) * jdField_a_of_type_JavaNioFloatBuffer.get(13));
/* 578 */     this.jdField_a_of_type_ArrayOfFloat[2] = (jdField_b_of_type_JavaNioFloatBuffer.get(0) * jdField_a_of_type_JavaNioFloatBuffer.get(2) + jdField_b_of_type_JavaNioFloatBuffer.get(1) * jdField_a_of_type_JavaNioFloatBuffer.get(6) + jdField_b_of_type_JavaNioFloatBuffer.get(2) * jdField_a_of_type_JavaNioFloatBuffer.get(10) + jdField_b_of_type_JavaNioFloatBuffer.get(3) * jdField_a_of_type_JavaNioFloatBuffer.get(14));
/* 579 */     this.jdField_a_of_type_ArrayOfFloat[3] = (jdField_b_of_type_JavaNioFloatBuffer.get(0) * jdField_a_of_type_JavaNioFloatBuffer.get(3) + jdField_b_of_type_JavaNioFloatBuffer.get(1) * jdField_a_of_type_JavaNioFloatBuffer.get(7) + jdField_b_of_type_JavaNioFloatBuffer.get(2) * jdField_a_of_type_JavaNioFloatBuffer.get(11) + jdField_b_of_type_JavaNioFloatBuffer.get(3) * jdField_a_of_type_JavaNioFloatBuffer.get(15));
/*     */ 
/* 581 */     this.jdField_a_of_type_ArrayOfFloat[4] = (jdField_b_of_type_JavaNioFloatBuffer.get(4) * jdField_a_of_type_JavaNioFloatBuffer.get(0) + jdField_b_of_type_JavaNioFloatBuffer.get(5) * jdField_a_of_type_JavaNioFloatBuffer.get(4) + jdField_b_of_type_JavaNioFloatBuffer.get(6) * jdField_a_of_type_JavaNioFloatBuffer.get(8) + jdField_b_of_type_JavaNioFloatBuffer.get(7) * jdField_a_of_type_JavaNioFloatBuffer.get(12));
/* 582 */     this.jdField_a_of_type_ArrayOfFloat[5] = (jdField_b_of_type_JavaNioFloatBuffer.get(4) * jdField_a_of_type_JavaNioFloatBuffer.get(1) + jdField_b_of_type_JavaNioFloatBuffer.get(5) * jdField_a_of_type_JavaNioFloatBuffer.get(5) + jdField_b_of_type_JavaNioFloatBuffer.get(6) * jdField_a_of_type_JavaNioFloatBuffer.get(9) + jdField_b_of_type_JavaNioFloatBuffer.get(7) * jdField_a_of_type_JavaNioFloatBuffer.get(13));
/* 583 */     this.jdField_a_of_type_ArrayOfFloat[6] = (jdField_b_of_type_JavaNioFloatBuffer.get(4) * jdField_a_of_type_JavaNioFloatBuffer.get(2) + jdField_b_of_type_JavaNioFloatBuffer.get(5) * jdField_a_of_type_JavaNioFloatBuffer.get(6) + jdField_b_of_type_JavaNioFloatBuffer.get(6) * jdField_a_of_type_JavaNioFloatBuffer.get(10) + jdField_b_of_type_JavaNioFloatBuffer.get(7) * jdField_a_of_type_JavaNioFloatBuffer.get(14));
/* 584 */     this.jdField_a_of_type_ArrayOfFloat[7] = (jdField_b_of_type_JavaNioFloatBuffer.get(4) * jdField_a_of_type_JavaNioFloatBuffer.get(3) + jdField_b_of_type_JavaNioFloatBuffer.get(5) * jdField_a_of_type_JavaNioFloatBuffer.get(7) + jdField_b_of_type_JavaNioFloatBuffer.get(6) * jdField_a_of_type_JavaNioFloatBuffer.get(11) + jdField_b_of_type_JavaNioFloatBuffer.get(7) * jdField_a_of_type_JavaNioFloatBuffer.get(15));
/*     */ 
/* 586 */     this.jdField_a_of_type_ArrayOfFloat[8] = (jdField_b_of_type_JavaNioFloatBuffer.get(8) * jdField_a_of_type_JavaNioFloatBuffer.get(0) + jdField_b_of_type_JavaNioFloatBuffer.get(9) * jdField_a_of_type_JavaNioFloatBuffer.get(4) + jdField_b_of_type_JavaNioFloatBuffer.get(10) * jdField_a_of_type_JavaNioFloatBuffer.get(8) + jdField_b_of_type_JavaNioFloatBuffer.get(11) * jdField_a_of_type_JavaNioFloatBuffer.get(12));
/* 587 */     this.jdField_a_of_type_ArrayOfFloat[9] = (jdField_b_of_type_JavaNioFloatBuffer.get(8) * jdField_a_of_type_JavaNioFloatBuffer.get(1) + jdField_b_of_type_JavaNioFloatBuffer.get(9) * jdField_a_of_type_JavaNioFloatBuffer.get(5) + jdField_b_of_type_JavaNioFloatBuffer.get(10) * jdField_a_of_type_JavaNioFloatBuffer.get(9) + jdField_b_of_type_JavaNioFloatBuffer.get(11) * jdField_a_of_type_JavaNioFloatBuffer.get(13));
/* 588 */     this.jdField_a_of_type_ArrayOfFloat[10] = (jdField_b_of_type_JavaNioFloatBuffer.get(8) * jdField_a_of_type_JavaNioFloatBuffer.get(2) + jdField_b_of_type_JavaNioFloatBuffer.get(9) * jdField_a_of_type_JavaNioFloatBuffer.get(6) + jdField_b_of_type_JavaNioFloatBuffer.get(10) * jdField_a_of_type_JavaNioFloatBuffer.get(10) + jdField_b_of_type_JavaNioFloatBuffer.get(11) * jdField_a_of_type_JavaNioFloatBuffer.get(14));
/* 589 */     this.jdField_a_of_type_ArrayOfFloat[11] = (jdField_b_of_type_JavaNioFloatBuffer.get(8) * jdField_a_of_type_JavaNioFloatBuffer.get(3) + jdField_b_of_type_JavaNioFloatBuffer.get(9) * jdField_a_of_type_JavaNioFloatBuffer.get(7) + jdField_b_of_type_JavaNioFloatBuffer.get(10) * jdField_a_of_type_JavaNioFloatBuffer.get(11) + jdField_b_of_type_JavaNioFloatBuffer.get(11) * jdField_a_of_type_JavaNioFloatBuffer.get(15));
/*     */ 
/* 591 */     this.jdField_a_of_type_ArrayOfFloat[12] = (jdField_b_of_type_JavaNioFloatBuffer.get(12) * jdField_a_of_type_JavaNioFloatBuffer.get(0) + jdField_b_of_type_JavaNioFloatBuffer.get(13) * jdField_a_of_type_JavaNioFloatBuffer.get(4) + jdField_b_of_type_JavaNioFloatBuffer.get(14) * jdField_a_of_type_JavaNioFloatBuffer.get(8) + jdField_b_of_type_JavaNioFloatBuffer.get(15) * jdField_a_of_type_JavaNioFloatBuffer.get(12));
/* 592 */     this.jdField_a_of_type_ArrayOfFloat[13] = (jdField_b_of_type_JavaNioFloatBuffer.get(12) * jdField_a_of_type_JavaNioFloatBuffer.get(1) + jdField_b_of_type_JavaNioFloatBuffer.get(13) * jdField_a_of_type_JavaNioFloatBuffer.get(5) + jdField_b_of_type_JavaNioFloatBuffer.get(14) * jdField_a_of_type_JavaNioFloatBuffer.get(9) + jdField_b_of_type_JavaNioFloatBuffer.get(15) * jdField_a_of_type_JavaNioFloatBuffer.get(13));
/* 593 */     this.jdField_a_of_type_ArrayOfFloat[14] = (jdField_b_of_type_JavaNioFloatBuffer.get(12) * jdField_a_of_type_JavaNioFloatBuffer.get(2) + jdField_b_of_type_JavaNioFloatBuffer.get(13) * jdField_a_of_type_JavaNioFloatBuffer.get(6) + jdField_b_of_type_JavaNioFloatBuffer.get(14) * jdField_a_of_type_JavaNioFloatBuffer.get(10) + jdField_b_of_type_JavaNioFloatBuffer.get(15) * jdField_a_of_type_JavaNioFloatBuffer.get(14));
/* 594 */     this.jdField_a_of_type_ArrayOfFloat[15] = (jdField_b_of_type_JavaNioFloatBuffer.get(12) * jdField_a_of_type_JavaNioFloatBuffer.get(3) + jdField_b_of_type_JavaNioFloatBuffer.get(13) * jdField_a_of_type_JavaNioFloatBuffer.get(7) + jdField_b_of_type_JavaNioFloatBuffer.get(14) * jdField_a_of_type_JavaNioFloatBuffer.get(11) + jdField_b_of_type_JavaNioFloatBuffer.get(15) * jdField_a_of_type_JavaNioFloatBuffer.get(15));
/*     */ 
/* 597 */     this.jdField_a_of_type_Array2dOfFloat[0][0] = (this.jdField_a_of_type_ArrayOfFloat[3] - this.jdField_a_of_type_ArrayOfFloat[0]);
/* 598 */     this.jdField_a_of_type_Array2dOfFloat[0][1] = (this.jdField_a_of_type_ArrayOfFloat[7] - this.jdField_a_of_type_ArrayOfFloat[4]);
/* 599 */     this.jdField_a_of_type_Array2dOfFloat[0][2] = (this.jdField_a_of_type_ArrayOfFloat[11] - this.jdField_a_of_type_ArrayOfFloat[8]);
/* 600 */     this.jdField_a_of_type_Array2dOfFloat[0][3] = (this.jdField_a_of_type_ArrayOfFloat[15] - this.jdField_a_of_type_ArrayOfFloat[12]);
/*     */ 
/* 603 */     float f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[0][0] * this.jdField_a_of_type_Array2dOfFloat[0][0] + this.jdField_a_of_type_Array2dOfFloat[0][1] * this.jdField_a_of_type_Array2dOfFloat[0][1] + this.jdField_a_of_type_Array2dOfFloat[0][2] * this.jdField_a_of_type_Array2dOfFloat[0][2]);
/* 604 */     this.jdField_a_of_type_Array2dOfFloat[0][0] /= f1;
/* 605 */     this.jdField_a_of_type_Array2dOfFloat[0][1] /= f1;
/* 606 */     this.jdField_a_of_type_Array2dOfFloat[0][2] /= f1;
/* 607 */     this.jdField_a_of_type_Array2dOfFloat[0][3] /= f1;
/*     */ 
/* 610 */     this.jdField_a_of_type_Array2dOfFloat[1][0] = (this.jdField_a_of_type_ArrayOfFloat[3] + this.jdField_a_of_type_ArrayOfFloat[0]);
/* 611 */     this.jdField_a_of_type_Array2dOfFloat[1][1] = (this.jdField_a_of_type_ArrayOfFloat[7] + this.jdField_a_of_type_ArrayOfFloat[4]);
/* 612 */     this.jdField_a_of_type_Array2dOfFloat[1][2] = (this.jdField_a_of_type_ArrayOfFloat[11] + this.jdField_a_of_type_ArrayOfFloat[8]);
/* 613 */     this.jdField_a_of_type_Array2dOfFloat[1][3] = (this.jdField_a_of_type_ArrayOfFloat[15] + this.jdField_a_of_type_ArrayOfFloat[12]);
/*     */ 
/* 616 */     f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[1][0] * this.jdField_a_of_type_Array2dOfFloat[1][0] + this.jdField_a_of_type_Array2dOfFloat[1][1] * this.jdField_a_of_type_Array2dOfFloat[1][1] + this.jdField_a_of_type_Array2dOfFloat[1][2] * this.jdField_a_of_type_Array2dOfFloat[1][2]);
/* 617 */     this.jdField_a_of_type_Array2dOfFloat[1][0] /= f1;
/* 618 */     this.jdField_a_of_type_Array2dOfFloat[1][1] /= f1;
/* 619 */     this.jdField_a_of_type_Array2dOfFloat[1][2] /= f1;
/* 620 */     this.jdField_a_of_type_Array2dOfFloat[1][3] /= f1;
/*     */ 
/* 623 */     this.jdField_a_of_type_Array2dOfFloat[2][0] = (this.jdField_a_of_type_ArrayOfFloat[3] + this.jdField_a_of_type_ArrayOfFloat[1]);
/* 624 */     this.jdField_a_of_type_Array2dOfFloat[2][1] = (this.jdField_a_of_type_ArrayOfFloat[7] + this.jdField_a_of_type_ArrayOfFloat[5]);
/* 625 */     this.jdField_a_of_type_Array2dOfFloat[2][2] = (this.jdField_a_of_type_ArrayOfFloat[11] + this.jdField_a_of_type_ArrayOfFloat[9]);
/* 626 */     this.jdField_a_of_type_Array2dOfFloat[2][3] = (this.jdField_a_of_type_ArrayOfFloat[15] + this.jdField_a_of_type_ArrayOfFloat[13]);
/*     */ 
/* 629 */     f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[2][0] * this.jdField_a_of_type_Array2dOfFloat[2][0] + this.jdField_a_of_type_Array2dOfFloat[2][1] * this.jdField_a_of_type_Array2dOfFloat[2][1] + this.jdField_a_of_type_Array2dOfFloat[2][2] * this.jdField_a_of_type_Array2dOfFloat[2][2]);
/* 630 */     this.jdField_a_of_type_Array2dOfFloat[2][0] /= f1;
/* 631 */     this.jdField_a_of_type_Array2dOfFloat[2][1] /= f1;
/* 632 */     this.jdField_a_of_type_Array2dOfFloat[2][2] /= f1;
/* 633 */     this.jdField_a_of_type_Array2dOfFloat[2][3] /= f1;
/*     */ 
/* 636 */     this.jdField_a_of_type_Array2dOfFloat[3][0] = (this.jdField_a_of_type_ArrayOfFloat[3] - this.jdField_a_of_type_ArrayOfFloat[1]);
/* 637 */     this.jdField_a_of_type_Array2dOfFloat[3][1] = (this.jdField_a_of_type_ArrayOfFloat[7] - this.jdField_a_of_type_ArrayOfFloat[5]);
/* 638 */     this.jdField_a_of_type_Array2dOfFloat[3][2] = (this.jdField_a_of_type_ArrayOfFloat[11] - this.jdField_a_of_type_ArrayOfFloat[9]);
/* 639 */     this.jdField_a_of_type_Array2dOfFloat[3][3] = (this.jdField_a_of_type_ArrayOfFloat[15] - this.jdField_a_of_type_ArrayOfFloat[13]);
/*     */ 
/* 642 */     f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[3][0] * this.jdField_a_of_type_Array2dOfFloat[3][0] + this.jdField_a_of_type_Array2dOfFloat[3][1] * this.jdField_a_of_type_Array2dOfFloat[3][1] + this.jdField_a_of_type_Array2dOfFloat[3][2] * this.jdField_a_of_type_Array2dOfFloat[3][2]);
/* 643 */     this.jdField_a_of_type_Array2dOfFloat[3][0] /= f1;
/* 644 */     this.jdField_a_of_type_Array2dOfFloat[3][1] /= f1;
/* 645 */     this.jdField_a_of_type_Array2dOfFloat[3][2] /= f1;
/* 646 */     this.jdField_a_of_type_Array2dOfFloat[3][3] /= f1;
/*     */ 
/* 649 */     this.jdField_a_of_type_Array2dOfFloat[4][0] = (this.jdField_a_of_type_ArrayOfFloat[3] - this.jdField_a_of_type_ArrayOfFloat[2]);
/* 650 */     this.jdField_a_of_type_Array2dOfFloat[4][1] = (this.jdField_a_of_type_ArrayOfFloat[7] - this.jdField_a_of_type_ArrayOfFloat[6]);
/* 651 */     this.jdField_a_of_type_Array2dOfFloat[4][2] = (this.jdField_a_of_type_ArrayOfFloat[11] - this.jdField_a_of_type_ArrayOfFloat[10]);
/* 652 */     this.jdField_a_of_type_Array2dOfFloat[4][3] = (this.jdField_a_of_type_ArrayOfFloat[15] - this.jdField_a_of_type_ArrayOfFloat[14]);
/*     */ 
/* 655 */     f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[4][0] * this.jdField_a_of_type_Array2dOfFloat[4][0] + this.jdField_a_of_type_Array2dOfFloat[4][1] * this.jdField_a_of_type_Array2dOfFloat[4][1] + this.jdField_a_of_type_Array2dOfFloat[4][2] * this.jdField_a_of_type_Array2dOfFloat[4][2]);
/* 656 */     this.jdField_a_of_type_Array2dOfFloat[4][0] /= f1;
/* 657 */     this.jdField_a_of_type_Array2dOfFloat[4][1] /= f1;
/* 658 */     this.jdField_a_of_type_Array2dOfFloat[4][2] /= f1;
/* 659 */     this.jdField_a_of_type_Array2dOfFloat[4][3] /= f1;
/*     */ 
/* 662 */     this.jdField_a_of_type_Array2dOfFloat[5][0] = (this.jdField_a_of_type_ArrayOfFloat[3] + this.jdField_a_of_type_ArrayOfFloat[2]);
/* 663 */     this.jdField_a_of_type_Array2dOfFloat[5][1] = (this.jdField_a_of_type_ArrayOfFloat[7] + this.jdField_a_of_type_ArrayOfFloat[6]);
/* 664 */     this.jdField_a_of_type_Array2dOfFloat[5][2] = (this.jdField_a_of_type_ArrayOfFloat[11] + this.jdField_a_of_type_ArrayOfFloat[10]);
/* 665 */     this.jdField_a_of_type_Array2dOfFloat[5][3] = (this.jdField_a_of_type_ArrayOfFloat[15] + this.jdField_a_of_type_ArrayOfFloat[14]);
/*     */ 
/* 668 */     f1 = FastMath.l(this.jdField_a_of_type_Array2dOfFloat[5][0] * this.jdField_a_of_type_Array2dOfFloat[5][0] + this.jdField_a_of_type_Array2dOfFloat[5][1] * this.jdField_a_of_type_Array2dOfFloat[5][1] + this.jdField_a_of_type_Array2dOfFloat[5][2] * this.jdField_a_of_type_Array2dOfFloat[5][2]);
/* 669 */     this.jdField_a_of_type_Array2dOfFloat[5][0] /= f1;
/* 670 */     this.jdField_a_of_type_Array2dOfFloat[5][1] /= f1;
/* 671 */     this.jdField_a_of_type_Array2dOfFloat[5][2] /= f1;
/* 672 */     this.jdField_a_of_type_Array2dOfFloat[5][3] /= f1;
/*     */   }
/*     */ 
/*     */   public void a(ClientStateInterface paramClientStateInterface, xq paramxq) {
/* 676 */     this.jdField_a_of_type_WV.a(paramClientStateInterface);
/*     */   }
/*     */   protected final void e() {
/* 679 */     if ((xu.W.a().equals("ZOOM")) || (Keyboard.isKeyDown(42)))
/*     */     {
/* 682 */       int m = this.jdField_a_of_type_Int;
/* 683 */       this.jdField_a_of_type_Int = a(this.jdField_a_of_type_Int - this.jdField_a_of_type_WV.jdField_a_of_type_Int);
/* 684 */       if (this.jdField_a_of_type_Boolean)
/*     */       {
/* 686 */         this.jdField_a_of_type_Int = ((int)this.jdField_e_of_type_Float);
/* 687 */         this.jdField_a_of_type_Boolean = false;
/*     */       }
/*     */ 
/* 691 */       if (m != this.jdField_a_of_type_Int) {
/* 692 */         float f1 = this.jdField_a_of_type_Int * this.jdField_d_of_type_Float;
/* 693 */         this.jdField_a_of_type_Float = (this.jdField_a_of_type_Int > 0 ? FastMath.f(FastMath.b(f1, 1.3F)) : 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b(xq paramxq) {
/* 699 */     this.jdField_a_of_type_Xa.a(paramxq);
/* 700 */     getWorldTransform().origin.set(a().a());
/*     */   }
/*     */ 
/*     */   public final Vector3f g()
/*     */   {
/* 707 */     return this.i;
/*     */   }
/*     */ 
/*     */   public final Vector3f h()
/*     */   {
/* 713 */     return this.j;
/*     */   }
/*     */ 
/*     */   public final Vector3f i()
/*     */   {
/* 719 */     return this.k;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.camera.Camera
 * JD-Core Version:    0.6.2
 */