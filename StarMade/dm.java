/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Tuple4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.BeamHandler;
/*     */ import org.schema.game.common.data.element.BeamHandler.BeamState;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.forms.Mesh;
/*     */ 
/*     */ public class dm
/*     */   implements xg
/*     */ {
/*     */   private List jdField_a_of_type_JavaUtilList;
/*     */   private static final Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*     */   private Vector4f jdField_b_of_type_JavaxVecmathVector4f;
/*     */   private static final Vector4f jdField_c_of_type_JavaxVecmathVector4f;
/*     */   private static final Vector4f jdField_d_of_type_JavaxVecmathVector4f;
/*     */   private static final Vector4f jdField_e_of_type_JavaxVecmathVector4f;
/*     */   private static final Vector4f jdField_f_of_type_JavaxVecmathVector4f;
/*     */   private static final Vector4f jdField_g_of_type_JavaxVecmathVector4f;
/*     */   private static final Vector4f jdField_h_of_type_JavaxVecmathVector4f;
/*     */   private static final Vector4f jdField_i_of_type_JavaxVecmathVector4f;
/*     */   private static final Vector4f jdField_j_of_type_JavaxVecmathVector4f;
/*     */   private static Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*     */   private static Vector3f jdField_b_of_type_JavaxVecmathVector3f;
/*     */   private static Vector3f jdField_c_of_type_JavaxVecmathVector3f;
/*  54 */   private ObjectOpenHashSet jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet = new ObjectOpenHashSet();
/*     */ 
/*  57 */   private final zE jdField_a_of_type_ZE = new zE();
/*     */   private static final Vector3f jdField_d_of_type_JavaxVecmathVector3f;
/*     */   private static final Vector3f jdField_e_of_type_JavaxVecmathVector3f;
/*  63 */   private final Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*     */ 
/*  65 */   private final q jdField_a_of_type_Q = new q();
/*     */   private static final Vector3f jdField_f_of_type_JavaxVecmathVector3f;
/*     */   private static final Vector3f jdField_g_of_type_JavaxVecmathVector3f;
/*     */   private static final Vector3f jdField_h_of_type_JavaxVecmathVector3f;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private final do jdField_a_of_type_Do;
/*     */   private static Vector3f jdField_i_of_type_JavaxVecmathVector3f;
/*     */   private static float jdField_a_of_type_Float;
/*     */   private static Vector3f jdField_j_of_type_JavaxVecmathVector3f;
/*     */ 
/*     */   public dm(do paramdo, List paramList, dl paramdl)
/*     */   {
/*  82 */     this.jdField_a_of_type_Do = paramdo;
/*  83 */     a(paramList, paramdl);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/*  92 */     for (Iterator localIterator = this.jdField_a_of_type_JavaUtilList.iterator(); localIterator.hasNext(); ) ((jq)localIterator.next())
/*  94 */         .getHandler().setDrawer(null);
/*     */ 
/*  96 */     this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.clear();
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 103 */     if (this.jdField_a_of_type_JavaUtilList == null) {
/*     */       try {
/* 105 */         throw new NullPointerException("[CLIENT][ERROR] ########## beam handlers null of beam drawer " + this); } catch (NullPointerException localNullPointerException) { localNullPointerException
/* 106 */           .printStackTrace();
/*     */ 
/* 109 */         return;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 117 */     if (this.jdField_a_of_type_Boolean)
/* 118 */       for (BeamHandler localBeamHandler : this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet)
/* 119 */         a(localBeamHandler);
/*     */   }
/*     */ 
/*     */   private void a(BeamHandler paramBeamHandler)
/*     */   {
/* 129 */     for (BeamHandler.BeamState localBeamState : paramBeamHandler.getBeamStates().values())
/*     */     {
/* 131 */       if ((!jdField_b_of_type_Boolean) && (localBeamState == null)) throw new AssertionError();
/* 132 */       localBeamState.color.set(this.jdField_b_of_type_JavaxVecmathVector4f);
/* 133 */       a(localBeamState, this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 134 */       do.jdField_a_of_type_Int += 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(ObjectHeapPriorityQueue paramObjectHeapPriorityQueue)
/*     */   {
/*     */     Iterator localIterator1;
/* 191 */     if (this.jdField_a_of_type_Boolean)
/* 192 */       for (localIterator1 = this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator(); localIterator1.hasNext(); )
/* 193 */         for (localIterator2 = ((BeamHandler)localIterator1.next())
/* 193 */           .getBeamStates().values().iterator(); localIterator2.hasNext(); )
/*     */         {
/*     */           BeamHandler.BeamState localBeamState;
/* 193 */           (
/* 194 */             localBeamState = (BeamHandler.BeamState)localIterator2.next()).color
/* 194 */             .set(this.jdField_b_of_type_JavaxVecmathVector4f);
/*     */ 
/* 203 */           jdField_j_of_type_JavaxVecmathVector3f.sub(xe.a().a(), localBeamState.from);
/* 204 */           localBeamState.camDistStart = jdField_j_of_type_JavaxVecmathVector3f.lengthSquared();
/*     */ 
/* 207 */           paramObjectHeapPriorityQueue.enqueue(localBeamState);
/*     */         }
/*     */     Iterator localIterator2;
/*     */   }
/*     */ 
/*     */   public static void a(BeamHandler.BeamState paramBeamState, Transform paramTransform)
/*     */   {
/* 239 */     jdField_a_of_type_JavaxVecmathVector3f.set(paramBeamState.from);
/*     */ 
/* 241 */     if (paramBeamState.hitPoint != null)
/* 242 */       jdField_b_of_type_JavaxVecmathVector3f.set(paramBeamState.hitPoint);
/*     */     else {
/* 244 */       jdField_b_of_type_JavaxVecmathVector3f.set(paramBeamState.to);
/*     */     }
/*     */ 
/* 247 */     if (!GlUtil.a(jdField_a_of_type_JavaxVecmathVector3f, jdField_b_of_type_JavaxVecmathVector3f, xe.a.a())) {
/* 248 */       return;
/*     */     }
/*     */ 
/* 251 */     jdField_c_of_type_JavaxVecmathVector3f.sub(jdField_a_of_type_JavaxVecmathVector3f, jdField_b_of_type_JavaxVecmathVector3f);
/* 252 */     float f1 = jdField_c_of_type_JavaxVecmathVector3f.length();
/* 253 */     jdField_d_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 1.0F);
/* 254 */     paramTransform.origin.set(jdField_a_of_type_JavaxVecmathVector3f);
/*     */ 
/* 257 */     jdField_i_of_type_JavaxVecmathVector3f.cross(jdField_c_of_type_JavaxVecmathVector3f, jdField_d_of_type_JavaxVecmathVector3f);
/* 258 */     jdField_i_of_type_JavaxVecmathVector3f.normalize();
/* 259 */     GlUtil.a(jdField_c_of_type_JavaxVecmathVector3f, paramTransform);
/* 260 */     GlUtil.d(jdField_i_of_type_JavaxVecmathVector3f, paramTransform);
/* 261 */     jdField_d_of_type_JavaxVecmathVector3f.cross(jdField_c_of_type_JavaxVecmathVector3f, jdField_i_of_type_JavaxVecmathVector3f);
/* 262 */     jdField_d_of_type_JavaxVecmathVector3f.normalize();
/* 263 */     GlUtil.c(jdField_d_of_type_JavaxVecmathVector3f, paramTransform);
/*     */ 
/* 267 */     Matrix3f localMatrix3f = paramTransform.basis; Vector3f localVector3f2 = xe.a().a(); Object localObject = jdField_b_of_type_JavaxVecmathVector3f; Vector3f localVector3f1 = jdField_a_of_type_JavaxVecmathVector3f; jdField_f_of_type_JavaxVecmathVector3f.sub(localVector3f2, localVector3f1); jdField_g_of_type_JavaxVecmathVector3f.sub((Tuple3f)localObject, localVector3f1); jdField_h_of_type_JavaxVecmathVector3f.cross(jdField_f_of_type_JavaxVecmathVector3f, jdField_g_of_type_JavaxVecmathVector3f); jdField_h_of_type_JavaxVecmathVector3f.normalize(); jdField_e_of_type_JavaxVecmathVector3f.set(0.0F, 1.0F, 0.0F); localMatrix3f.transform(jdField_e_of_type_JavaxVecmathVector3f); float f3 = (float)FastMath.a(jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_h_of_type_JavaxVecmathVector3f)); jdField_e_of_type_JavaxVecmathVector3f.set(1.0F, 0.0F, 0.0F); localMatrix3f.transform(jdField_e_of_type_JavaxVecmathVector3f); float f2 = (float)FastMath.a(jdField_e_of_type_JavaxVecmathVector3f.dot(jdField_h_of_type_JavaxVecmathVector3f)); if (1.570796F > f2) f3 = 6.283186F - f3; f2 = f3;
/*     */ 
/* 269 */     localObject = paramBeamState.color; if (!jdField_i_of_type_JavaxVecmathVector4f.equals(jdField_a_of_type_JavaxVecmathVector4f)) { jdField_i_of_type_JavaxVecmathVector4f.set(jdField_a_of_type_JavaxVecmathVector4f); GlUtil.a(do.jdField_a_of_type_Zj, "thrustColor0", jdField_i_of_type_JavaxVecmathVector4f); } if (!jdField_j_of_type_JavaxVecmathVector4f.equals((Tuple4f)localObject)) { jdField_j_of_type_JavaxVecmathVector4f.set((Tuple4f)localObject); GlUtil.a(do.jdField_a_of_type_Zj, "thrustColor1", jdField_j_of_type_JavaxVecmathVector4f);
/*     */     }
/* 269 */     float f4;
/* 269 */     if (Float.compare(f4 = f1 / 20.0F, jdField_a_of_type_Float) != 0) { GlUtil.a(do.jdField_a_of_type_Zj, "texCoordMult", f4); jdField_a_of_type_Float = f4;
/*     */     }
/* 271 */     GlUtil.d();
/*     */ 
/* 275 */     GlUtil.b(paramTransform);
/*     */ 
/* 278 */     GlUtil.a(57.295776F * (f2 + 1.570796F));
/*     */ 
/* 283 */     do.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.j();
/*     */ 
/* 285 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/* 290 */     for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator(); localIterator.hasNext(); ) { localObject1 = (BeamHandler)localIterator.next();
/* 291 */       localObject2 = localObject1; localObject1 = this; if (((BeamHandler)localObject2).getSegmentController().isClientOwnObject()) { GlUtil.a(d.B, "selectionColor", 0.7F, 0.77F, 0.1F, 1.0F); for (localObject2 = ((BeamHandler)localObject2).getBeamStates().values().iterator(); ((Iterator)localObject2).hasNext(); ) { Object localObject3 = (BeamHandler.BeamState)((Iterator)localObject2).next(); if ((!jdField_b_of_type_Boolean) && (localObject3 == null)) throw new AssertionError(); if (((BeamHandler.BeamState)localObject3).segmentHit != null) { ((BeamHandler.BeamState)localObject3).segmentHit.a(); if (((BeamHandler.BeamState)localObject3).segmentHit.a() != 0) { zE localzE = ((dm)localObject1).jdField_a_of_type_ZE; SegmentController localSegmentController = ((BeamHandler.BeamState)localObject3).segmentHit.a().a(); Object localObject4 = ((BeamHandler.BeamState)localObject3).segmentHit.a(((dm)localObject1).jdField_a_of_type_Q); (localObject3 = localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform.set(localSegmentController.getWorldTransformClient()); localObject4 = new Vector3f(((q)localObject4).jdField_a_of_type_Int - 8, ((q)localObject4).b - 8, ((q)localObject4).c - 8); ((dm)localObject3).jdField_a_of_type_ComBulletphysicsLinearmathTransform.basis.transform((Tuple3f)localObject4); ((dm)localObject3).jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject4); GlUtil.d(); GlUtil.b(((dm)localObject3).jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */               float tmp267_266 = (1.05F + localzE.a() * 0.05F);
/*     */               float tmp268_267 = tmp267_266; GL11.glScalef(tmp267_266, tmp268_267, tmp268_267); do.b.j(); GlUtil.c();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     Object localObject1;
/*     */     Object localObject2;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void f()
/*     */   {
/* 366 */     if (this.jdField_a_of_type_JavaUtilList != null) {
/* 367 */       d();
/*     */     }
/* 369 */     this.jdField_a_of_type_JavaUtilList = null;
/*     */   }
/*     */ 
/*     */   public final void a(List paramList, dl paramdl) {
/* 373 */     Object localObject = paramdl; paramdl = this; switch (dn.a[localObject.ordinal()]) { case 1:
/* 373 */       paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_c_of_type_JavaxVecmathVector4f; break;
/*     */     case 2:
/* 373 */       paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_d_of_type_JavaxVecmathVector4f; break;
/*     */     case 3:
/* 373 */       paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_e_of_type_JavaxVecmathVector4f; break;
/*     */     case 4:
/* 373 */       paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_h_of_type_JavaxVecmathVector4f; break;
/*     */     case 5:
/* 373 */       paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_f_of_type_JavaxVecmathVector4f; break;
/*     */     case 6:
/* 373 */       paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_g_of_type_JavaxVecmathVector4f; break;
/*     */     default:
/* 373 */       paramdl.jdField_b_of_type_JavaxVecmathVector4f = jdField_d_of_type_JavaxVecmathVector4f; }
/* 374 */     this.jdField_a_of_type_JavaUtilList = paramList;
/* 375 */     if (this.jdField_a_of_type_JavaUtilList != null) {
/* 376 */       paramdl = this; d(); for (localObject = paramdl.jdField_a_of_type_JavaUtilList.iterator(); ((Iterator)localObject).hasNext(); ((jq)((Iterator)localObject).next()).getHandler().setDrawer(paramdl));
/* 376 */       return;
/*     */     }
/* 378 */     d();
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 400 */     this.jdField_a_of_type_ZE.a(paramxq);
/*     */   }
/*     */ 
/*     */   public final void a(BeamHandler paramBeamHandler, boolean paramBoolean)
/*     */   {
/* 435 */     if (paramBoolean)
/* 436 */       this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(paramBeamHandler);
/*     */     else {
/* 438 */       this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.remove(paramBeamHandler);
/*     */     }
/*     */ 
/* 441 */     if (this.jdField_a_of_type_Boolean != (!this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty())) {
/* 442 */       this.jdField_a_of_type_Do.a(this, !this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty());
/*     */     }
/*     */ 
/* 445 */     this.jdField_a_of_type_Boolean = (!this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty());
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  39 */     jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*  41 */     jdField_c_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 1.0F, 1.0F);
/*  42 */     jdField_d_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 1.0F, 0.0F, 1.0F);
/*  43 */     jdField_e_of_type_JavaxVecmathVector4f = new Vector4f(0.7F, 0.0F, 0.0F, 1.0F);
/*  44 */     jdField_f_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 0.0F, 1.0F);
/*  45 */     jdField_g_of_type_JavaxVecmathVector4f = new Vector4f(0.7F, 0.7F, 0.7F, 1.0F);
/*  46 */     jdField_h_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 0.3F, 1.0F, 1.0F);
/*  47 */     jdField_i_of_type_JavaxVecmathVector4f = new Vector4f();
/*  48 */     jdField_j_of_type_JavaxVecmathVector4f = new Vector4f();
/*     */ 
/*  50 */     jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  51 */     jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  52 */     jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  59 */     jdField_d_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 1.0F);
/*     */ 
/*  61 */     jdField_e_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 1.0F);
/*     */ 
/*  69 */     jdField_f_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  71 */     jdField_g_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*  73 */     jdField_h_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/* 181 */     jdField_i_of_type_JavaxVecmathVector3f = new Vector3f();
/* 182 */     new Vector3f(-8.0F, -8.0F, -8.0F);
/*     */ 
/* 187 */     jdField_j_of_type_JavaxVecmathVector3f = new Vector3f();
/* 188 */     new Vector3f();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dm
 * JD-Core Version:    0.6.2
 */