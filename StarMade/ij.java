/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.Display;
/*     */ import org.lwjgl.opengl.DisplayMode;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Matrix4f;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public class ij extends yz
/*     */ {
/*     */   public static ArrayList a;
/*     */   public static HashSet a;
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yE jdField_b_of_type_YE;
/*     */   private yP jdField_a_of_type_YP;
/*  95 */   private Int2IntArrayMap jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap = new Int2IntArrayMap();
/*     */   private zC jdField_a_of_type_ZC;
/*  98 */   private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */   private Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*     */   private long jdField_a_of_type_Long;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private yh jdField_a_of_type_Yh;
/*     */   private zG jdField_a_of_type_ZG;
/*     */   private mF jdField_a_of_type_MF;
/*     */   private static float jdField_a_of_type_Float;
/*     */   private int jdField_a_of_type_Int;
/*     */   private final Transform[] jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform;
/*     */   private final String[] jdField_a_of_type_ArrayOfJavaLangString;
/*     */   private q[] jdField_a_of_type_ArrayOfQ;
/*     */   private q jdField_a_of_type_Q;
/*     */   private Vector3f d;
/*     */   private Vector3f e;
/*     */   private Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/*     */   private Transform jdField_c_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private final ArrayList jdField_b_of_type_JavaUtilArrayList;
/*     */ 
/*     */   public static void a(mF parammF, Vector4f paramVector4f, boolean paramBoolean, ct paramct)
/*     */   {
/*  65 */     if (paramBoolean) {
/*  66 */       paramVector4f.x = (0.3F + jdField_a_of_type_Float);
/*  67 */       paramVector4f.y = (0.3F + jdField_a_of_type_Float);
/*  68 */       paramVector4f.z = (0.3F + jdField_a_of_type_Float);
/*  69 */       return;
/*     */     }
/*  71 */     paramBoolean = paramBoolean ? jdField_a_of_type_Float : 0.0F;
/*     */ 
/*  77 */     if ((parammF instanceof cw)) {
/*  78 */       ((cw)parammF)
/*  79 */         .a();
/*     */     }
/*  81 */     paramct = paramct.a().a(parammF.getFactionId());
/*     */ 
/*  85 */     parammF.getRelationColor(paramct, paramVector4f, paramBoolean);
/*     */   }
/*     */ 
/*     */   public ij(ClientState paramClientState)
/*     */   {
/* 126 */     super(paramClientState);
/*     */ 
/*  99 */     new Vector3f();
/* 100 */     this.jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 1.0F, 0.0F, 1.0F);
/*     */ 
/* 107 */     new Vector3f();
/*     */ 
/* 174 */     this.jdField_a_of_type_Q = new q();
/* 175 */     this.d = new Vector3f();
/* 176 */     this.e = new Vector3f();
/* 177 */     this.jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*     */ 
/* 267 */     this.jdField_c_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*     */ 
/* 553 */     this.jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*     */ 
/* 128 */     yg localyg = xe.a().a("hud-target-c-4x4-gui-");
/* 129 */     this.jdField_a_of_type_YE = new yE(localyg, paramClientState);
/*     */ 
/* 131 */     this.jdField_b_of_type_YE = new yE(xe.a().a("hud-target-c-4x4-gui-"), paramClientState);
/*     */ 
/* 133 */     this.jdField_a_of_type_YP = new yP(32, 32, paramClientState);
/*     */ 
/* 135 */     this.jdField_a_of_type_ZG = new zF(8.0F);
/*     */ 
/* 137 */     this.jdField_a_of_type_ZC = ((ct)a()).a().a();
/*     */ 
/* 139 */     this.jdField_a_of_type_ArrayOfQ = new q[6];
/* 140 */     this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform = new Transform[6];
/* 141 */     this.jdField_a_of_type_ArrayOfJavaLangString = new String[6];
/*     */ 
/* 143 */     for (int i = 0; i < this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length; i++) {
/* 144 */       this.jdField_a_of_type_ArrayOfQ[i] = new q();
/* 145 */       this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i] = new Transform();
/* 146 */       this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i].setIdentity();
/*     */     }
/* 148 */     a(((ct)paramClientState).a());
/*     */   }
/*     */ 
/*     */   private void a(q paramq)
/*     */   {
/* 234 */     int i = Universe.getSectorSizeWithMargin();
/*     */ 
/* 236 */     q localq1 = new q();
/* 237 */     Vector3f localVector3f = new Vector3f();
/* 238 */     localq1.b(paramq);
/*     */ 
/* 240 */     for (int j = 0; j < 6; j++)
/*     */     {
/* 242 */       this.jdField_a_of_type_ArrayOfQ[j].b(paramq);
/* 243 */       localq1.b(paramq);
/* 244 */       q localq2 = org.schema.game.common.data.element.Element.DIRECTIONSi[j];
/* 245 */       localq1.a(localq2);
/* 246 */       this.jdField_a_of_type_ArrayOfQ[j].b(localq1);
/* 247 */       localVector3f.set(localq2.jdField_a_of_type_Int * i, localq2.b * i, localq2.c * i);
/* 248 */       this.jdField_a_of_type_ArrayOfJavaLangString[j] = ("Sector " + localq1);
/* 249 */       this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[j].origin.set(localVector3f);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected final void d()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 270 */     if (this.jdField_a_of_type_Long + 50L < System.currentTimeMillis()) {
/* 271 */       this.jdField_a_of_type_Boolean = true;
/* 272 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*     */     } else {
/* 274 */       this.jdField_a_of_type_Boolean = false;
/*     */     }
/* 276 */     jdField_a_of_type_Float = 0.5F + this.jdField_a_of_type_ZG.a() * 0.5F;
/*     */ 
/* 278 */     Object localObject6 = null; Object localObject1 = ((ct)a()).a().a.a.jdField_a_of_type_Ar;
/*     */ 
/* 284 */     this.jdField_a_of_type_MF = ((ar)localObject1).a();
/*     */ 
/* 287 */     this.jdField_a_of_type_Yh = null;
/* 288 */     if ((xe.a().a() instanceof xb)) {
/* 289 */       localObject1 = (xb)xe.a().a();
/* 290 */       this.jdField_a_of_type_Yh = ((xb)localObject1).a();
/*     */     }
/*     */     Object localObject5;
/*     */     Object localObject3;
/*     */     float f6;
/*     */     Object localObject4;
/*     */     Vector3f localVector3f4;
/* 293 */     for (int i = 0; i < this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform.length; i++) {
/* 294 */       if ((!jdField_b_of_type_Boolean) && (this.jdField_a_of_type_ArrayOfJavaLangString[i] == null)) throw new AssertionError();
/* 295 */       this.jdField_c_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ArrayOfComBulletphysicsLinearmathTransform[i]);
/* 296 */       localObject5 = this.jdField_c_of_type_ComBulletphysicsLinearmathTransform; int k = i; localObject3 = this;
/*     */       q localq1;
/* 296 */       q localq2 = mJ.a(localq1 = this.jdField_a_of_type_ArrayOfQ[k], ((ij)localObject3).jdField_a_of_type_Q); long l1 = ((ct)((ij)localObject3).a()).a().calculateStartTime(); (localObject6 = new q(localq1)).c(((ct)((ij)localObject3).a()).a().a()); ((Transform)localObject5).setIdentity();
/*     */       Vector3f localVector3f1;
/* 296 */       if (mI.a(((ct)((ij)localObject3).a()).a().a())) { f6 = (float)((System.currentTimeMillis() - l1) % 1200000L) / 1200000.0F; localq2.a(16); localq2.a(8, 8, 8); localq2.c(((ct)((ij)localObject3).a()).a().a()); ((ij)localObject3).d.set(((q)localObject6).jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), ((q)localObject6).b * Universe.getSectorSizeWithMargin(), ((q)localObject6).c * Universe.getSectorSizeWithMargin()); ((ij)localObject3).e.set(localq2.jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), localq2.b * Universe.getSectorSizeWithMargin(), localq2.c * Universe.getSectorSizeWithMargin()); ((ij)localObject3).jdField_b_of_type_ComBulletphysicsLinearmathTransform.setIdentity(); if (((ij)localObject3).e.lengthSquared() > 0.0F) { ((Transform)localObject5).origin.add(((ij)localObject3).e); ((Transform)localObject5).basis.rotX(6.283186F * f6); (localVector3f1 = new Vector3f()).sub(((ij)localObject3).d, ((ij)localObject3).e); ((Transform)localObject5).origin.add(localVector3f1); ((Transform)localObject5).basis.transform(((Transform)localObject5).origin); } else { ((Transform)localObject5).basis.rotX(6.283186F * f6); ((Transform)localObject5).origin.set(((ij)localObject3).d); ((Transform)localObject5).basis.transform(((Transform)localObject5).origin); }  } else { ((Transform)localObject5).origin.set(((q)localObject6).jdField_a_of_type_Int * Universe.getSectorSizeWithMargin(), ((q)localObject6).b * Universe.getSectorSizeWithMargin(), ((q)localObject6).c * Universe.getSectorSizeWithMargin()); }
/* 297 */       if (localq1.equals(((ct)((ij)localObject3).a()).a().a().b))
/*     */       {
/* 298 */         this.jdField_a_of_type_JavaxVecmathVector4f.set(0.1F + jdField_a_of_type_Float, 0.8F + jdField_a_of_type_Float, 0.6F + jdField_a_of_type_Float, 0.4F + jdField_a_of_type_Float);
/*     */       }
/* 300 */       else this.jdField_a_of_type_JavaxVecmathVector4f.set(0.4F, 0.4F, 0.4F, 0.4F);
/*     */ 
/* 302 */       int m = -100 - i; localObject5 = this.jdField_a_of_type_ArrayOfJavaLangString[i]; localObject4 = this.jdField_c_of_type_ComBulletphysicsLinearmathTransform; localObject3 = this; if (xe.a() != null) { ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.set(((Transform)localObject4).origin); ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.sub(xe.a().a()); float f5 = ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.length(); localVector3f4 = ((ij)localObject3).jdField_a_of_type_ZC.a(new Vector3f()); localObject6 = ((ij)localObject3).jdField_a_of_type_ZC.a(((Transform)localObject4).origin, new Vector3f(), true); (localVector3f1 = new Vector3f((Vector3f)localObject6)).sub(localVector3f4); ((ij)localObject3).jdField_a_of_type_YE.a().c(((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f); ((ij)localObject3).jdField_b_of_type_YE.a().c(((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f);
/*     */         float f3;
/* 302 */         if (localVector3f1.length() > ((ij)localObject3).jdField_a_of_type_Int) { localVector3f1.normalize(); localVector3f1.scale(((ij)localObject3).jdField_a_of_type_Int); localVector3f1.add(localVector3f4); ((ij)localObject3).jdField_a_of_type_YE.a_(0); ((ij)localObject3).jdField_a_of_type_YE.a().set(localVector3f1);
/*     */           Vector3f localVector3f5;
/* 302 */           (localVector3f5 = new Vector3f(((ij)localObject3).jdField_a_of_type_YE.a())).sub(localVector3f4); localVector3f5.scale(-1.0F); localVector3f5.normalize(); f3 = p.a(new Vector3f(0.0F, 1.0F, 0.0F), localVector3f5); ((ij)localObject3).jdField_a_of_type_YE.b(57.295776F * f3); ((ij)localObject3).jdField_a_of_type_YE.b(); ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(m, 1); } else { f3.add(localVector3f4); if (!((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.containsKey(m)) ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(m, 1); int i1 = ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.get(m); ((ij)localObject3).jdField_b_of_type_YE.a_(i1); if (((ij)localObject3).jdField_a_of_type_Boolean) ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(m, Math.min(15, i1 + 1)); ((ij)localObject3).jdField_b_of_type_YE.a().set(f3); ((ij)localObject3).jdField_b_of_type_YE.b(); ((ij)localObject3).jdField_a_of_type_YP.a().set(f3); ((ij)localObject3).jdField_a_of_type_YP.b.set(0, localObject5); ((ij)localObject3).jdField_a_of_type_YP.b.set(1, (int)f5 + "m"); ((ij)localObject3).jdField_a_of_type_YP.b(); }
/*     */       }
/*     */     }
/* 305 */     GlUtil.d();
/* 306 */     yg.b(this.jdField_a_of_type_YE.a());
/* 307 */     for (Object localObject2 = ((ct)a()).a().values().iterator(); ((Iterator)localObject2).hasNext(); )
/* 308 */       if ((
/* 308 */         localObject3 = (mF)((Iterator)localObject2).next()) != 
/* 308 */         this.jdField_a_of_type_Yh) {
/* 309 */         localObject4 = localObject3; localObject3 = this; localObject5 = (ct)a(); if ((xe.a() != null) && ((!(localObject4 instanceof kd)) || (!((kd)localObject4).c()))) { ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.set(((mF)localObject4).getWorldTransformClient().origin); if (((ct)localObject5).a() != null) ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.sub(((ct)localObject5).a().getWorldTransform().origin); else ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.sub(xe.a().a()); float f2 = ((ij)localObject3).jdField_c_of_type_JavaxVecmathVector3f.length(); if (((localObject4 != ((ij)localObject3).jdField_a_of_type_MF) && (f2 > 1200.0F)) || (f2 < 1.0F) || (!((mF)localObject4).isHidden())) { a((mF)localObject4, ((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f, localObject4 == ((ij)localObject3).jdField_a_of_type_MF, (ct)localObject5); if (localObject4 == ((ij)localObject3).jdField_a_of_type_MF) ((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f.w = 1.0F; else ((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f.w = Math.min(1.0F, 1200.0F / (f2 * 10.0F)); Vector3f localVector3f3 = ((ij)localObject3).jdField_a_of_type_ZC.a(new Vector3f()); localVector3f4 = ((ij)localObject3).jdField_a_of_type_ZC.a(((mF)localObject4).getWorldTransformClient().origin, new Vector3f(), true); (localObject6 = new Vector3f(localVector3f4)).sub(localVector3f3); if (((Vector3f)localObject6).length() > ((ij)localObject3).jdField_a_of_type_Int) { ((Vector3f)localObject6).normalize(); ((Vector3f)localObject6).scale(((ij)localObject3).jdField_a_of_type_Int); ((Vector3f)localObject6).add(localVector3f3); ((ij)localObject3).jdField_a_of_type_YE.a().set((Tuple3f)localObject6); ((ij)localObject3).jdField_a_of_type_YE.a().c(((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f); ((ij)localObject3).jdField_a_of_type_YE.a().b(0);
/*     */               Vector3f localVector3f2;
/* 309 */               (localVector3f2 = new Vector3f(((ij)localObject3).jdField_a_of_type_YE.a())).sub(localVector3f3); localVector3f2.scale(-1.0F); localVector3f2.normalize(); f6 = p.a(new Vector3f(0.0F, 1.0F, 0.0F), localVector3f2); jdField_b_of_type_JavaNioFloatBuffer.rewind(); xe.a.store(jdField_b_of_type_JavaNioFloatBuffer); float f4 = FastMath.i(f6); float f1 = FastMath.j(f6); jdField_b_of_type_JavaNioFloatBuffer.put(0, f1); jdField_b_of_type_JavaNioFloatBuffer.put(4, -f4); jdField_b_of_type_JavaNioFloatBuffer.put(8, 0.0F); jdField_b_of_type_JavaNioFloatBuffer.put(1, f4); jdField_b_of_type_JavaNioFloatBuffer.put(5, f1); jdField_b_of_type_JavaNioFloatBuffer.put(9, 0.0F); jdField_b_of_type_JavaNioFloatBuffer.put(2, 0.0F); jdField_b_of_type_JavaNioFloatBuffer.put(6, 0.0F); jdField_b_of_type_JavaNioFloatBuffer.put(10, 1.0F); jdField_b_of_type_JavaNioFloatBuffer.put(12, xe.a.m30 + ((Vector3f)localObject6).x); jdField_b_of_type_JavaNioFloatBuffer.put(13, xe.a.m31 + ((Vector3f)localObject6).y); jdField_b_of_type_JavaNioFloatBuffer.put(14, xe.a.m32 + ((Vector3f)localObject6).z); jdField_b_of_type_JavaNioFloatBuffer.rewind(); GL11.glLoadMatrix(jdField_b_of_type_JavaNioFloatBuffer); yg.a(((ij)localObject3).jdField_a_of_type_YE.a()); if (localObject4 == ((ij)localObject3).jdField_a_of_type_MF) { ((ij)localObject3).jdField_a_of_type_YP.a().set((Tuple3f)localObject6); ((ij)localObject3).jdField_a_of_type_YP.b.set(0, ((mF)localObject4).toNiceString()); ((ij)localObject3).jdField_a_of_type_YP.b.set(1, (int)f2 + "m"); } ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(((mF)localObject4).getId(), 1); } else { ((ij)localObject3).jdField_b_of_type_YE.a().c(((ij)localObject3).jdField_a_of_type_JavaxVecmathVector4f); ((Vector3f)localObject6).add(localVector3f3); if (!((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.containsKey(((mF)localObject4).getId())) ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(((mF)localObject4).getId(), 1); int n = ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.get(((mF)localObject4).getId()); ((ij)localObject3).jdField_b_of_type_YE.a_(n); ((ij)localObject3).jdField_b_of_type_YE.a().b(n); if (((ij)localObject3).jdField_a_of_type_Boolean) ((ij)localObject3).jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(((mF)localObject4).getId(), Math.min(15, n + 1)); jdField_b_of_type_JavaNioFloatBuffer.rewind(); xe.a.store(jdField_b_of_type_JavaNioFloatBuffer); jdField_b_of_type_JavaNioFloatBuffer.put(12, xe.a.m30 + ((Vector3f)localObject6).x); jdField_b_of_type_JavaNioFloatBuffer.put(13, xe.a.m31 + ((Vector3f)localObject6).y); jdField_b_of_type_JavaNioFloatBuffer.put(14, xe.a.m32 + ((Vector3f)localObject6).z); jdField_b_of_type_JavaNioFloatBuffer.rewind(); GL11.glLoadMatrix(jdField_b_of_type_JavaNioFloatBuffer); yg.a(((ij)localObject3).jdField_b_of_type_YE.a()); ((ij)localObject3).jdField_b_of_type_YE.a().set((Tuple3f)localObject6); if (localObject4 == ((ij)localObject3).jdField_a_of_type_MF) ((ij)localObject3).jdField_b_of_type_JavaUtilArrayList.add(new ik(((mF)localObject4).toNiceString(), (int)f2 + "m", (Vector3f)localObject6)); if (((localObject4 instanceof kd)) && (((kd)localObject4).b() > 0L)) { long l2 = ((kd)localObject4).a() - (System.currentTimeMillis() - ((kd)localObject4).b()); ((ij)localObject3).jdField_b_of_type_JavaUtilArrayList.add(new ik("CORE OVERHEATING", l2 / 1000L + "sec", (Vector3f)localObject6)); } } }
/*     */         }
/*     */       }
/* 312 */     yg.c(this.jdField_a_of_type_YE.a());
/* 313 */     GlUtil.c();
/*     */ 
/* 315 */     while (!this.jdField_b_of_type_JavaUtilArrayList.isEmpty()) {
/* 316 */       localObject2 = (ik)this.jdField_b_of_type_JavaUtilArrayList.remove(0);
/* 317 */       this.jdField_a_of_type_YP.a().set(((ik)localObject2).jdField_a_of_type_JavaxVecmathVector3f);
/* 318 */       this.jdField_a_of_type_YP.b.set(0, ((ik)localObject2).jdField_a_of_type_JavaLangString);
/* 319 */       this.jdField_a_of_type_YP.b.set(1, ((ik)localObject2).b);
/* 320 */       this.jdField_a_of_type_YP.b();
/*     */     }
/*     */     try {
/* 323 */       for (int j = 0; j < jdField_a_of_type_JavaUtilArrayList.size(); j++) {
/* 324 */         a((ev)jdField_a_of_type_JavaUtilArrayList.get(j), xe.a(), true, 100.0F, this.jdField_a_of_type_ZC);
/*     */       }
/*     */ 
/* 329 */       return;
/*     */     }
/*     */     catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/*     */     eN localeN;
/* 334 */     if (((ct)a()).a().a.a.jdField_a_of_type_Aa.c) {
/* 335 */       localeN = ((ct)a()).a().a();
/* 336 */       for (cA localcA : jdField_a_of_type_JavaUtilHashSet) {
/* 337 */         q localq = localeN.a().a(new q());
/* 338 */         for (int i = 0; i < localcA.jdField_a_of_type_ArrayOfCD.length; i++)
/*     */         {
/*     */           cD localcD;
/* 340 */           if (((
/* 340 */             localcD = localcA.jdField_a_of_type_ArrayOfCD[i])
/* 340 */             .a()) && 
/* 341 */             (localeN.a().b.equals(localcA.jdField_a_of_type_Q)) && 
/* 342 */             (localcD.a(eN.a(), localq)))
/* 343 */             a(localcD.a(localcA.jdField_a_of_type_Q), localeN.a(), false, 4000.0F, localeN.a());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(ev paramev, Camera paramCamera, boolean paramBoolean, float paramFloat, zC paramzC)
/*     */   {
/* 622 */     ct localct = (ct)a();
/* 623 */     this.jdField_c_of_type_JavaxVecmathVector3f.set(paramev.a().origin);
/*     */ 
/* 625 */     if ((paramBoolean) && (localct.a() != null) && 
/* 626 */       (!(localct.a() instanceof kd))) {
/* 627 */       this.jdField_c_of_type_JavaxVecmathVector3f.sub(localct.a().getWorldTransform().origin);
/*     */     }
/*     */     else
/*     */     {
/* 633 */       this.jdField_c_of_type_JavaxVecmathVector3f.sub(paramCamera.a());
/*     */     }
/*     */ 
/* 636 */     if (this.jdField_c_of_type_JavaxVecmathVector3f.length() > 
/* 636 */       paramFloat) {
/* 637 */       return;
/*     */     }
/* 639 */     paramBoolean = paramzC.a(new Vector3f());
/*     */ 
/* 641 */     paramCamera = paramzC.a(paramev.a().origin, new Vector3f(), true, paramCamera);
/*     */ 
/* 643 */     (
/* 644 */       paramCamera = new Vector3f(paramCamera))
/* 644 */       .sub(paramBoolean);
/*     */ 
/* 647 */     this.jdField_b_of_type_YE.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/* 648 */     paramCamera.add(paramBoolean);
/* 649 */     this.jdField_a_of_type_YP.a().set(paramCamera);
/* 650 */     this.jdField_a_of_type_YP.b.set(0, paramev.jdField_a_of_type_JavaLangString);
/* 651 */     this.jdField_a_of_type_YP.b.set(1, "");
/* 652 */     this.jdField_a_of_type_YP.b(1.0F, 1.0F, 1.0F);
/* 653 */     if (paramev.jdField_a_of_type_JavaxVecmathVector4f != null) {
/* 654 */       this.jdField_a_of_type_YP.a.r = paramev.jdField_a_of_type_JavaxVecmathVector4f.x;
/* 655 */       this.jdField_a_of_type_YP.a.g = paramev.jdField_a_of_type_JavaxVecmathVector4f.y;
/* 656 */       this.jdField_a_of_type_YP.a.b = paramev.jdField_a_of_type_JavaxVecmathVector4f.z;
/* 657 */       this.jdField_a_of_type_YP.a.jdField_a_of_type_Float = paramev.jdField_a_of_type_JavaxVecmathVector4f.w;
/*     */     }
/* 659 */     this.jdField_a_of_type_YP.b();
/* 660 */     this.jdField_a_of_type_YP.a.r = 1.0F;
/* 661 */     this.jdField_a_of_type_YP.a.g = 1.0F;
/* 662 */     this.jdField_a_of_type_YP.a.b = 1.0F;
/* 663 */     this.jdField_a_of_type_YP.a.jdField_a_of_type_Float = 1.0F;
/*     */   }
/*     */ 
/*     */   public final float a() {
/* 667 */     return Display.getDisplayMode().getHeight();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 672 */     return Display.getDisplayMode().getWidth();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 682 */     this.jdField_a_of_type_YE.c();
/*     */ 
/* 689 */     this.jdField_a_of_type_YP = new yP(32, 32, d.b(), a());
/* 690 */     this.jdField_a_of_type_YP.a(Color.white);
/* 691 */     this.jdField_a_of_type_YP.b = new ArrayList();
/* 692 */     this.jdField_a_of_type_YP.b.add("");
/* 693 */     this.jdField_a_of_type_YP.b.add("");
/* 694 */     this.jdField_a_of_type_YP.a(2.0F, 2.0F, 0.0F);
/*     */ 
/* 696 */     this.jdField_a_of_type_YP.c();
/*     */   }
/*     */ 
/*     */   public final void f()
/*     */   {
/* 701 */     this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.clear();
/* 702 */     ct localct = (ct)a();
/* 703 */     a(localct.a().a());
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq) {
/* 707 */     this.jdField_a_of_type_Int = ((int)Math.min(xm.a() / 2.4F, xm.b() / 2.4F));
/* 708 */     this.jdField_a_of_type_ZG.a(paramxq);
/*     */     try {
/* 710 */       for (int i = 0; i < jdField_a_of_type_JavaUtilArrayList.size(); i++) {
/* 711 */         ((ev)jdField_a_of_type_JavaUtilArrayList.get(i)).a(paramxq);
/* 712 */         if (!((ev)jdField_a_of_type_JavaUtilArrayList.get(i)).a()) {
/* 713 */           jdField_a_of_type_JavaUtilArrayList.remove(i);
/* 714 */           i--;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 719 */       return;
/*     */     }
/*     */     catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  57 */     jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  58 */     jdField_a_of_type_JavaUtilHashSet = new HashSet();
/*     */ 
/*  93 */     jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*     */ 
/* 266 */     BufferUtils.createFloatBuffer(16);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ij
 * JD-Core Version:    0.6.2
 */