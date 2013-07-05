/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectAVLTreeSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.ShipManagerContainer;
/*     */ import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
/*     */ import org.schema.game.common.controller.elements.thrust.ThrusterUnit;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.forms.Mesh;
/*     */ 
/*     */ public final class er
/*     */   implements xg, zn
/*     */ {
/*     */   private kd jdField_a_of_type_Kd;
/*     */   private static zj jdField_a_of_type_Zj;
/*  45 */   private static Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/*     */ 
/*  48 */   private ObjectAVLTreeSet jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet = new ObjectAVLTreeSet();
/*     */ 
/*  50 */   private float jdField_a_of_type_Float = 0.0F;
/*     */ 
/*  52 */   private Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*  54 */   private Vector4f jdField_b_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 1.0F, 1.0F);
/*  55 */   private Vector4f c = new Vector4f();
/*  56 */   private Vector4f d = new Vector4f();
/*     */   private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private q jdField_a_of_type_Q;
/*     */   private long jdField_a_of_type_Long;
/*     */   private ObjectPool jdField_a_of_type_ComBulletphysicsUtilObjectPool;
/*     */   private le jdField_a_of_type_Le;
/* 275 */   private static Vector4f e = new Vector4f();
/* 276 */   private static Vector4f f = new Vector4f();
/*     */ 
/*     */   public er(kd paramkd)
/*     */   {
/*  57 */     new Vector3f();
/*     */ 
/*  59 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/*     */ 
/*  62 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, -0.5F);
/*     */ 
/*  64 */     this.jdField_b_of_type_Boolean = true;
/*     */ 
/*  76 */     this.jdField_a_of_type_Q = new q();
/*     */ 
/*  78 */     this.jdField_a_of_type_Long = -1L;
/*     */ 
/*  81 */     this.jdField_a_of_type_ComBulletphysicsUtilObjectPool = ObjectPool.get(eF.class);
/*     */ 
/*  83 */     this.jdField_a_of_type_Le = new le();
/*     */ 
/*  86 */     this.jdField_a_of_type_Kd = paramkd;
/*     */ 
/*  88 */     jdField_a_of_type_JavaxVecmathMatrix3f.rotY(3.141593F);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 101 */     if (this.jdField_b_of_type_Boolean) {
/* 102 */       jdField_a_of_type_Zj = d.z;
/*     */     }
/* 104 */     if (this.jdField_a_of_type_Kd.a()) {
/* 105 */       return;
/*     */     }
/* 107 */     if (!this.jdField_a_of_type_Boolean)
/*     */     {
/* 109 */       if (this.jdField_a_of_type_Kd.getWorldTransform() != null)
/*     */       {
/* 111 */         this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 112 */         this.jdField_a_of_type_Boolean = true;
/*     */       }
/* 114 */       return;
/*     */     }
/*     */ 
/* 117 */     int i = 1;
/*     */ 
/* 122 */     ObjectBidirectionalIterator localObjectBidirectionalIterator = this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.iterator();
/* 123 */     while (localObjectBidirectionalIterator.hasPrevious()) {
/* 124 */       ((eF)localObjectBidirectionalIterator.previous())
/* 126 */         .a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform, this.jdField_a_of_type_JavaxVecmathVector3f);
/* 127 */       this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.basis.mul(jdField_a_of_type_JavaxVecmathMatrix3f);
/*     */ 
/* 129 */       if ((GlUtil.a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, xe.a.a())) && (GlUtil.a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform, eG.a))) {
/* 130 */         if (i != 0)
/*     */         {
/* 134 */           er localer = this; float f1 = Math.min(0.99F, localer.jdField_a_of_type_Kd.a().length() / localer.jdField_a_of_type_Kd.a()); localer.c.set(localer.jdField_a_of_type_JavaxVecmathVector4f); localer.d.set(localer.jdField_b_of_type_JavaxVecmathVector4f); localer.c.scale(f1); localer.d.scale(f1); localer.d.x = (0.5F - f1 / 2.0F); localer.d.z = f1; if (!e.equals(localer.c)) { GlUtil.a(jdField_a_of_type_Zj, "thrustColor0", localer.c); e.set(localer.c); } if (!f.equals(localer.d)) { GlUtil.a(jdField_a_of_type_Zj, "thrustColor1", localer.d); f.set(localer.d); } GlUtil.a(jdField_a_of_type_Zj, "ticks", localer.jdField_a_of_type_Float);
/* 135 */           int j = 0;
/*     */         }
/*     */ 
/* 138 */         GlUtil.d();
/* 139 */         GlUtil.b(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 140 */         ((Mesh)eG.a.a().get(0)).g();
/* 141 */         GlUtil.c();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final kd a()
/*     */   {
/* 155 */     return this.jdField_a_of_type_Kd;
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 171 */     jdField_a_of_type_Zj = d.z;
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/* 262 */     this.jdField_a_of_type_Long = System.currentTimeMillis();
/*     */   }
/*     */   public final void a(xq paramxq) {
/* 265 */     if ((this.jdField_a_of_type_Long > 0L) && (System.currentTimeMillis() - this.jdField_a_of_type_Long > 500L)) {
/* 266 */       er localer = this;
/*     */       Iterator localIterator;
/*     */       Object localObject1;
/* 266 */       synchronized (this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet) { for (localIterator = localer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.iterator(); localIterator.hasNext(); localer.jdField_a_of_type_ComBulletphysicsUtilObjectPool.release(localObject1)) (localObject1 = (eF)localIterator.next()).a(); localer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.clear(); for (localIterator = localer.jdField_a_of_type_Kd.a().getThrusterElementManager().getCollection().getCollection().iterator(); localIterator.hasNext(); )
/*     */         {
/* 266 */           Object localObject2;
/* 266 */           for (localObject1 = ((ThrusterUnit)localIterator.next()).getLastElements().entrySet().iterator(); ((Iterator)localObject1).hasNext(); localer.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.add(localObject2)) { Map.Entry localEntry = (Map.Entry)((Iterator)localObject1).next(); localer.jdField_a_of_type_Q.b((q)localEntry.getValue()); localer.jdField_a_of_type_Q.c -= 1; localObject2 = null;
/*     */             try { localObject2 = localer.jdField_a_of_type_Kd.getSegmentBuffer().a(localer.jdField_a_of_type_Q, false, localer.jdField_a_of_type_Le); } catch (IOException localIOException) { localIOException.printStackTrace(); } catch (InterruptedException localInterruptedException) { localInterruptedException.printStackTrace(); } if ((localObject2 == null) || (((le)localObject2).a() == 0)) (localObject2 = (eF)localer.jdField_a_of_type_ComBulletphysicsUtilObjectPool.get()).a(localer.jdField_a_of_type_Kd, (q)localEntry.getValue());  } 
/*     */         } }
/* 267 */       this.jdField_a_of_type_Long = -1L;
/*     */     }
/* 269 */     this.jdField_a_of_type_Float = ((float)(this.jdField_a_of_type_Float + paramxq.a() / 100.0F * ((Math.random() + 9.999999747378752E-005D) / 0.1000000014901161D)));
/* 270 */     if (this.jdField_a_of_type_Float > 1.0F)
/* 271 */       this.jdField_a_of_type_Float = 0.0F;
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     er
 * JD-Core Version:    0.6.2
 */