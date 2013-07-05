/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL15;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public class zf
/*     */   implements xg
/*     */ {
/*     */   yW jdField_a_of_type_YW;
/*  27 */   private IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  28 */   private IntBuffer jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*     */   private static FloatBuffer jdField_a_of_type_JavaNioFloatBuffer;
/*     */   private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/*     */   public static float a;
/*  31 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  37 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  39 */   private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  42 */   private Vector3f d = new Vector3f();
/*  43 */   private Vector3f e = new Vector3f();
/*  44 */   private Vector3f f = new Vector3f();
/*     */ 
/*  46 */   private Vector3f g = new Vector3f();
/*  47 */   private Vector3f h = new Vector3f();
/*  48 */   private Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f();
/*  49 */   private Vector3f i = new Vector3f();
/*  50 */   private Vector3f[] jdField_a_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
/*  51 */   private Vector3f[] jdField_b_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
/*  52 */   private Vector3f[] jdField_c_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
/*  53 */   private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*  54 */   boolean jdField_a_of_type_Boolean = true;
/*  55 */   private static float[] jdField_a_of_type_ArrayOfFloat = { 0.0F, 0.5F, 0.75F, 0.25F };
/*     */   private int jdField_a_of_type_Int;
/*  59 */   private Vector3f j = new Vector3f();
/*     */ 
/*  61 */   private Vector3f k = new Vector3f();
/*  62 */   private Vector3f l = new Vector3f();
/*  63 */   private Vector3f m = new Vector3f();
/*     */   private Vector4f jdField_b_of_type_JavaxVecmathVector4f;
/*     */ 
/*     */   private zf()
/*     */   {
/*  65 */     new Vector3f();
/*  66 */     new Vector3f();
/*  67 */     new Vector3f();
/*     */ 
/*  69 */     new Vector3f();
/*     */ 
/*  71 */     this.jdField_b_of_type_JavaxVecmathVector4f = new Vector4f();
/*     */ 
/*  74 */     this.jdField_a_of_type_YW = null;
/*     */ 
/*  76 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/*     */ 
/*  78 */     for (int n = 0; n < this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f.length; n++) {
/*  79 */       this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[n] = new Vector3f();
/*  80 */       this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[n] = new Vector3f();
/*  81 */       this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[n] = new Vector3f();
/*     */     }
/*     */   }
/*     */ 
/*     */   public zf(byte paramByte)
/*     */   {
/*  87 */     this();
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void b()
/*     */   {
/*  97 */     if (this.jdField_a_of_type_YW.b() > 1) {
/*  98 */       zf localzf = this; yV localyV = this.jdField_a_of_type_YW.a; jdField_a_of_type_JavaNioFloatBuffer.rewind(); jdField_b_of_type_JavaNioFloatBuffer.rewind(); jdField_a_of_type_JavaNioFloatBuffer.limit(jdField_a_of_type_JavaNioFloatBuffer.capacity()); jdField_b_of_type_JavaNioFloatBuffer.limit(jdField_b_of_type_JavaNioFloatBuffer.capacity()); localzf.jdField_a_of_type_JavaxVecmathVector3f = GlUtil.c(localzf.jdField_a_of_type_JavaxVecmathVector3f); localzf.jdField_b_of_type_JavaxVecmathVector3f = GlUtil.b(localzf.jdField_b_of_type_JavaxVecmathVector3f); localzf.jdField_c_of_type_JavaxVecmathVector3f = GlUtil.a(localzf.jdField_c_of_type_JavaxVecmathVector3f); int n = Math.min(512, localzf.jdField_a_of_type_YW.b()); int i1 = localzf.jdField_a_of_type_YW.a() % localzf.jdField_a_of_type_YW.a.a(); float f1 = 0.0F; float f2 = 1.0F / n; localzf.jdField_a_of_type_Int = 0;
/*     */       int i2;
/*     */       int i3;
/*     */       int i4;
/*  98 */       if (localzf.jdField_a_of_type_Boolean) { i1 = i1 + n - 1; for (i2 = 0; i2 < n; i2++) { i3 = i1 % localzf.jdField_a_of_type_YW.a.a(); i4 = (i1 - 1) % localzf.jdField_a_of_type_YW.a.a(); localzf.a(i3, i4, localyV, f1, i2, n); f1 += f2; i1--; }  } else { for (i2 = 0; i2 < n; i2++) { i3 = i1 % localzf.jdField_a_of_type_YW.a.a(); i4 = (i1 + 1) % localzf.jdField_a_of_type_YW.a.a(); localzf.a(i3, i4, localyV, f1, i2, n); f1 += f2; i1++; }  } jdField_a_of_type_JavaNioFloatBuffer.flip(); jdField_b_of_type_JavaNioFloatBuffer.flip(); GL15.glBindBuffer(34962, localzf.jdField_a_of_type_JavaNioIntBuffer.get(0)); GL15.glBufferData(34962, jdField_a_of_type_JavaNioFloatBuffer, 35048); GL15.glBindBuffer(34962, localzf.jdField_b_of_type_JavaNioIntBuffer.get(0)); GL15.glBufferData(34962, jdField_b_of_type_JavaNioFloatBuffer, 35048);
/*     */ 
/* 100 */       if ((jdField_a_of_type_JavaNioFloatBuffer.position() == 0 ? 0 : 1) != 0)
/*     */       {
/* 101 */         GlUtil.d();
/*     */ 
/* 103 */         GL11.glDisable(2884);
/*     */ 
/* 105 */         GL11.glDisable(2896);
/* 106 */         GL11.glEnable(3042);
/* 107 */         GL11.glBlendFunc(770, 771);
/*     */ 
/* 109 */         localzf = this; GL11.glEnableClientState(32884); GL11.glEnableClientState(32888); GL15.glBindBuffer(34962, localzf.jdField_a_of_type_JavaNioIntBuffer.get(0)); GL11.glVertexPointer(4, 5126, 0, 0L); GL15.glBindBuffer(34962, localzf.jdField_b_of_type_JavaNioIntBuffer.get(0)); GL11.glTexCoordPointer(4, 5126, 0, 0L); GL11.glDrawArrays(8, 0, localzf.jdField_a_of_type_Int); GL15.glBindBuffer(34962, 0); GL11.glDisableClientState(32884); GL11.glDisableClientState(32888);
/*     */ 
/* 111 */         GL11.glDisable(3042);
/* 112 */         GlUtil.c();
/*     */ 
/* 114 */         GL11.glDisable(2903);
/* 115 */         GL11.glEnable(2884);
/* 116 */         GL11.glEnable(2896);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(int paramInt1, int paramInt2, yV paramyV, float paramFloat1, float paramFloat2, int paramInt3)
/*     */   {
/*     */     try
/*     */     {
/* 217 */       int n = paramFloat2 > 0.0F ? 1 : 0;
/*     */ 
/* 220 */       paramyV.a(paramInt1, this.d);
/* 221 */       if (paramFloat2 >= paramInt3 - 2) {
/* 222 */         this.f.set(this.g);
/*     */       } else {
/* 224 */         paramyV.a(paramInt2, this.e);
/* 225 */         this.f.sub(this.e, this.d);
/*     */       }
/*     */ 
/* 229 */       if (n != 0) {
/* 230 */         this.h.cross(this.f, this.g);
/*     */       }
/*     */ 
/* 233 */       this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/* 234 */       GlUtil.a(this.d, this.f, xe.a().a(), this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */ 
/* 236 */       paramyV.d(paramInt1, this.i);
/*     */ 
/* 238 */       this.jdField_a_of_type_JavaxVecmathVector4f.set(this.d.x, this.d.y, this.d.z, paramyV.a(paramInt1));
/*     */ 
/* 241 */       GlUtil.a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform, jdField_a_of_type_Float, this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f);
/*     */ 
/* 251 */       this.j.sub(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[3], this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[0]);
/* 252 */       this.k.sub(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[2], this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[1]);
/* 253 */       this.j.scale(0.5F);
/* 254 */       this.k.scale(0.5F);
/*     */ 
/* 258 */       this.l.add(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[0], this.j);
/* 259 */       this.m.add(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[1], this.k);
/*     */ 
/* 261 */       if (paramFloat2 > 0.0F) {
/* 262 */         this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[0].set(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[0]);
/* 263 */         this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[1].set(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[1]);
/* 264 */         this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[2].set(this.m);
/* 265 */         this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[3].set(this.l);
/* 266 */         GlUtil.a(jdField_a_of_type_JavaNioFloatBuffer, this.jdField_a_of_type_JavaxVecmathVector4f.w, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f, jdField_a_of_type_JavaNioFloatBuffer.position() - 8);
/*     */       }
/*     */ 
/* 271 */       if (paramFloat2 > 0.0F) {
/* 272 */         this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[0].set(this.l);
/* 273 */         this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[1].set(this.m);
/* 274 */         this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[2].set(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[2]);
/* 275 */         this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[3].set(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[3]);
/* 276 */         GlUtil.a(jdField_a_of_type_JavaNioFloatBuffer, this.jdField_a_of_type_JavaxVecmathVector4f.w, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f, -1);
/* 277 */         this.jdField_a_of_type_Int += 2;
/*     */       } else {
/* 279 */         GlUtil.a(jdField_a_of_type_JavaNioFloatBuffer, this.jdField_a_of_type_JavaxVecmathVector4f.w, this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f, -1);
/* 280 */         this.jdField_a_of_type_Int += 2;
/*     */       }
/*     */ 
/* 285 */       for (paramInt1 = 0; paramInt1 < 2; paramInt1++) {
/* 286 */         this.jdField_b_of_type_JavaxVecmathVector4f.set(0.0F, paramFloat2 * 0.05F, paramFloat1, jdField_a_of_type_ArrayOfFloat[paramInt1]);
/* 287 */         GlUtil.a(jdField_b_of_type_JavaNioFloatBuffer, this.jdField_b_of_type_JavaxVecmathVector4f);
/*     */       }
/*     */ 
/* 290 */       for (paramInt1 = 0; paramInt1 < 4; paramInt1++)
/* 291 */         if (paramFloat2 > 0.0F)
/* 292 */           this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[paramInt1].set(this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[paramInt1]);
/*     */         else
/* 294 */           this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[paramInt1].set(this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[paramInt1]);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 298 */       System.err.println("[ERROR][TRAILDRAWER] " + localException.getClass().getSimpleName() + " " + localException.getMessage());
/*     */     }
/*     */ 
/* 301 */     this.g.set(this.f);
/*     */   }
/*     */ 
/*     */   public void c()
/*     */   {
/* 353 */     zf localzf = this; if (jdField_a_of_type_JavaNioFloatBuffer == null) jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4104); else jdField_a_of_type_JavaNioFloatBuffer.rewind(); GL15.glGenBuffers(localzf.jdField_a_of_type_JavaNioIntBuffer); GL15.glBindBuffer(34962, localzf.jdField_a_of_type_JavaNioIntBuffer.get(0)); GL15.glBufferData(34962, jdField_a_of_type_JavaNioFloatBuffer, 35048); GL15.glBindBuffer(34962, 0); if (jdField_b_of_type_JavaNioFloatBuffer == null) jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4104); else jdField_b_of_type_JavaNioFloatBuffer.rewind(); GL15.glGenBuffers(localzf.jdField_b_of_type_JavaNioIntBuffer); GL15.glBindBuffer(34962, localzf.jdField_b_of_type_JavaNioIntBuffer.get(0)); GL15.glBufferData(34962, jdField_b_of_type_JavaNioFloatBuffer, 35048); GL15.glBindBuffer(34962, 0);
/*     */   }
/*     */ 
/*     */   public final void a(yW paramyW)
/*     */   {
/* 361 */     this.jdField_a_of_type_YW = paramyW;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  33 */     jdField_a_of_type_Float = 0.5F;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zf
 * JD-Core Version:    0.6.2
 */