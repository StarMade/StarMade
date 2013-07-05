/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Matrix4f;
/*     */ import org.lwjgl.util.vector.Vector4f;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ import org.schema.schine.network.client.GUICallbackController;
/*     */ 
/*     */ public abstract class yz extends xM
/*     */   implements xX, xg
/*     */ {
/*     */   public final Vector3f b;
/*     */   public boolean f;
/*     */   public ys a;
/*  97 */   private int[] jdField_a_of_type_ArrayOfInt = new int[4];
/*     */   public boolean g;
/*     */   private ClientState jdField_a_of_type_OrgSchemaSchineNetworkClientClientState;
/*     */   public Object a;
/*     */   private boolean jdField_a_of_type_Boolean;
/* 120 */   private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(3);
/*     */   public Vector4f a;
/* 124 */   private static DoubleBuffer jdField_a_of_type_JavaNioDoubleBuffer = BufferUtils.createDoubleBuffer(4);
/*     */ 
/* 126 */   private static DoubleBuffer jdField_b_of_type_JavaNioDoubleBuffer = BufferUtils.createDoubleBuffer(4);
/*     */ 
/* 128 */   private static DoubleBuffer c = BufferUtils.createDoubleBuffer(4);
/* 129 */   private static DoubleBuffer d = BufferUtils.createDoubleBuffer(4);
/*     */   public static boolean h;
/*     */ 
/*     */   public static void h()
/*     */   {
/*  29 */     GlUtil.a(5889);
/*  30 */     GlUtil.c();
/*  31 */     GlUtil.a(5888);
/*  32 */     GlUtil.c();
/*     */ 
/*  34 */     GL11.glEnable(2929);
/*  35 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   public static void i()
/*     */   {
/*  45 */     GL11.glDisable(2896);
/*  46 */     GL11.glDisable(2929);
/*  47 */     GlUtil.d();
/*  48 */     GlUtil.a(5889);
/*  49 */     GlUtil.d();
/*  50 */     GlUtil.b();
/*  51 */     GlUtil.a(xm.b(), xm.a(), 0.0F);
/*  52 */     GlUtil.a(5888);
/*  53 */     GlUtil.b();
/*     */   }
/*     */ 
/*     */   public static void j() {
/*  57 */     a(xm.b(), xm.a());
/*     */   }
/*     */ 
/*     */   public static void a(int paramInt1, int paramInt2) {
/*  61 */     GL11.glDisable(2896);
/*  62 */     GL11.glDisable(2929);
/*     */ 
/*  64 */     GlUtil.d();
/*     */ 
/*  66 */     GlUtil.a(5889);
/*     */ 
/*  68 */     GlUtil.d();
/*  69 */     GlUtil.b();
/*  70 */     GlUtil.a(paramInt1, paramInt2, 0.0F, -500.0F, 500.0F);
/*     */ 
/*  72 */     GlUtil.a(5888);
/*     */ 
/*  74 */     GlUtil.b();
/*     */   }
/*     */ 
/*     */   public yz(ClientState paramClientState)
/*     */   {
/*  90 */     this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  99 */     this.jdField_a_of_type_OrgLwjglUtilVectorVector4f = new Vector4f();
/*     */ 
/* 135 */     this.jdField_a_of_type_OrgSchemaSchineNetworkClientClientState = paramClientState;
/*     */   }
/*     */   public void a(yz paramyz) {
/* 138 */     paramyz.b(this);
/* 139 */     this.jdField_a_of_type_JavaUtilList.add(paramyz);
/*     */   }
/*     */ 
/*     */   public final void k()
/*     */   {
/* 145 */     if (h()) {
/* 146 */       return;
/*     */     }
/* 148 */     GlUtil.d();
/*     */ 
/* 151 */     this.f = false;
/*     */ 
/* 153 */     r();
/*     */ 
/* 155 */     if (this.g) {
/* 156 */       l();
/*     */     }
/*     */ 
/* 159 */     for (Iterator localIterator = this.jdField_a_of_type_JavaUtilList.iterator(); localIterator.hasNext(); ) ((xM)localIterator.next())
/* 160 */         .b();
/*     */ 
/* 162 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public void l()
/*     */   {
/* 169 */     if (h) {
/* 170 */       this.f = false;
/* 171 */       return;
/*     */     }
/*     */ 
/* 175 */     Matrix4f localMatrix4f = xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f;
/*     */ 
/* 177 */     float f2 = new Vector3f(localMatrix4f.m00, localMatrix4f.m01, localMatrix4f.m02).length();
/* 178 */     float f3 = new Vector3f(localMatrix4f.m10, localMatrix4f.m11, localMatrix4f.m12).length();
/*     */ 
/* 180 */     Mouse.getX();
/* 181 */     Mouse.getY();
/* 182 */     jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*     */ 
/* 231 */     float f4 = (Mouse.getX() - localMatrix4f.m30) * f2;
/* 232 */     float f1 = (xm.a() - Mouse.getY() - localMatrix4f.m31) * f3;
/* 233 */     this.jdField_b_of_type_JavaxVecmathVector3f.set(f4, f1, 0.0F);
/*     */ 
/* 236 */     if (System.currentTimeMillis() - a().getLastDeactivatedMenu() > 200L) {
/* 237 */       int j = (this.jdField_b_of_type_JavaxVecmathVector3f.x < b() * f2 * f2) && (this.jdField_b_of_type_JavaxVecmathVector3f.x > 0.0F) ? 1 : 0;
/*     */ 
/* 239 */       int i = (this.jdField_b_of_type_JavaxVecmathVector3f.y < a() * f3 * f3) && (this.jdField_b_of_type_JavaxVecmathVector3f.y > 0.0F) ? 1 : 0;
/*     */ 
/* 241 */       this.f = ((j != 0) && (i != 0));
/*     */     } else {
/* 243 */       this.f = false;
/*     */     }
/*     */ 
/* 246 */     if ((a_()) && 
/* 247 */       (this.jdField_a_of_type_Ys != null) && 
/* 248 */       (!this.jdField_a_of_type_Ys.a())) {
/* 249 */       this.jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.getGuiCallbackController().addCallback(this.jdField_a_of_type_Ys, this);
/*     */     }
/*     */ 
/* 253 */     this.jdField_a_of_type_Boolean = a_();
/*     */   }
/*     */ 
/*     */   public final void m()
/*     */   {
/* 259 */     GlUtil.d();
/* 260 */     r();
/* 261 */     l();
/* 262 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public void b(yz paramyz)
/*     */   {
/* 272 */     paramyz.b(null);
/* 273 */     this.jdField_a_of_type_JavaUtilList.remove(paramyz);
/*     */   }
/*     */ 
/*     */   protected void d()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void a(Vector4f paramVector4f)
/*     */   {
/* 308 */     jdField_a_of_type_JavaNioDoubleBuffer.put(new double[] { 1.0D, 0.0D, 0.0D, -paramVector4f.x }).rewind();
/* 309 */     GL11.glClipPlane(12288, jdField_a_of_type_JavaNioDoubleBuffer);
/* 310 */     GL11.glEnable(12288);
/*     */ 
/* 312 */     jdField_b_of_type_JavaNioDoubleBuffer.put(new double[] { -1.0D, 0.0D, 0.0D, paramVector4f.y }).rewind();
/* 313 */     GL11.glClipPlane(12289, jdField_b_of_type_JavaNioDoubleBuffer);
/* 314 */     GL11.glEnable(12289);
/*     */ 
/* 316 */     c.put(new double[] { 0.0D, 1.0D, 0.0D, -paramVector4f.z }).rewind();
/* 317 */     GL11.glClipPlane(12290, c);
/* 318 */     GL11.glEnable(12290);
/*     */ 
/* 320 */     d.put(new double[] { 0.0D, -1.0D, 0.0D, paramVector4f.w }).rewind();
/* 321 */     GL11.glClipPlane(12291, d);
/* 322 */     GL11.glEnable(12291);
/*     */ 
/* 324 */     b();
/*     */ 
/* 326 */     GL11.glDisable(12288);
/* 327 */     GL11.glDisable(12289);
/* 328 */     GL11.glDisable(12290);
/* 329 */     GL11.glDisable(12291);
/*     */   }
/*     */ 
/*     */   public abstract float a();
/*     */ 
/*     */   public final Vector3f f()
/*     */   {
/* 369 */     return this.jdField_b_of_type_JavaxVecmathVector3f;
/*     */   }
/*     */ 
/*     */   public ClientState a()
/*     */   {
/* 375 */     return this.jdField_a_of_type_OrgSchemaSchineNetworkClientClientState;
/*     */   }
/*     */ 
/*     */   public final Object b()
/*     */   {
/* 381 */     return this.jdField_a_of_type_JavaLangObject;
/*     */   }
/*     */ 
/*     */   public abstract float b();
/*     */ 
/*     */   public boolean a_()
/*     */   {
/* 398 */     return this.f;
/*     */   }
/*     */ 
/*     */   public final boolean j()
/*     */   {
/* 411 */     if (a().x > xm.b()) {
/* 412 */       return false;
/*     */     }
/* 414 */     if (a().y > xm.a()) {
/* 415 */       return false;
/*     */     }
/* 417 */     if (a().x + b() * this.jdField_a_of_type_JavaxVecmathVector3f.x < 0.0F) {
/* 418 */       return false;
/*     */     }
/* 420 */     if (a().y + a() * this.jdField_a_of_type_JavaxVecmathVector3f.y < 0.0F) {
/* 421 */       return false;
/*     */     }
/* 423 */     return true;
/*     */   }
/*     */ 
/*     */   public final boolean k()
/*     */   {
/* 433 */     for (int i = 0; i < 4; i++) {
/* 434 */       if (this.jdField_a_of_type_ArrayOfInt[i] != xe.jdField_a_of_type_JavaNioIntBuffer.get(i)) {
/* 435 */         for (i = 0; i < 4; i++) {
/* 436 */           this.jdField_a_of_type_ArrayOfInt[i] = xe.jdField_a_of_type_JavaNioIntBuffer.get(i);
/*     */         }
/*     */ 
/* 439 */         return true;
/*     */       }
/*     */     }
/* 442 */     return false;
/*     */   }
/*     */ 
/*     */   public final void h(int paramInt)
/*     */   {
/* 447 */     a(paramInt, xe.jdField_a_of_type_JavaNioIntBuffer.get(0), xe.jdField_a_of_type_JavaNioIntBuffer.get(1), xe.jdField_a_of_type_JavaNioIntBuffer.get(2), xe.jdField_a_of_type_JavaNioIntBuffer.get(3));
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*     */   {
/* 455 */     if ((paramInt1 & 0x10) == 16) {
/* 456 */       a().y = ((paramInt5 - a() * this.jdField_a_of_type_JavaxVecmathVector3f.y - paramInt3) / 2.0F);
/*     */     }
/* 458 */     if ((paramInt1 & 0x20) == 32) {
/* 459 */       a().x = ((paramInt4 - b() * this.jdField_a_of_type_JavaxVecmathVector3f.x - paramInt2) / 2.0F);
/*     */     }
/* 461 */     if ((paramInt1 & 0x1) == 1) {
/* 462 */       a().x = paramInt2;
/*     */     }
/* 464 */     if ((paramInt1 & 0x2) == 2) {
/* 465 */       a().x = (paramInt4 - b() * this.jdField_a_of_type_JavaxVecmathVector3f.x);
/*     */     }
/* 467 */     if ((paramInt1 & 0x4) == 4) {
/* 468 */       a().y = paramInt3;
/*     */     }
/* 470 */     if ((paramInt1 & 0x8) == 8)
/* 471 */       a().y = (paramInt5 - a() * this.jdField_a_of_type_JavaxVecmathVector3f.y);
/*     */   }
/*     */ 
/*     */   public void a(ys paramys)
/*     */   {
/* 494 */     this.jdField_a_of_type_Ys = paramys;
/*     */   }
/*     */ 
/*     */   public final void r()
/*     */   {
/* 537 */     GlUtil.b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 538 */     GlUtil.b(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/*     */   }
/*     */ 
/*     */   public final boolean l()
/*     */   {
/* 550 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 104 */     BufferUtils.createIntBuffer(16);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yz
 * JD-Core Version:    0.6.2
 */