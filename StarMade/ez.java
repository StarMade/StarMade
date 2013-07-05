/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ 
/*     */ public final class ez
/*     */   implements xg
/*     */ {
/*     */   private ey[] jdField_a_of_type_ArrayOfEy;
/*     */   private ze jdField_a_of_type_Ze;
/*     */   private eE jdField_a_of_type_EE;
/*     */   private zd jdField_a_of_type_Zd;
/*  33 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private ct jdField_a_of_type_Ct;
/*     */   public final ArrayList a;
/*  39 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*     */   public ez(ct paramct)
/*     */   {
/*  37 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*     */ 
/*  43 */     this.jdField_a_of_type_Ct = paramct;
/*  44 */     this.jdField_a_of_type_ArrayOfEy = new ey[32];
/*  45 */     for (paramct = 0; paramct < 32; paramct++) {
/*  46 */       this.jdField_a_of_type_ArrayOfEy[paramct] = new ey(xe.a().getWorldTransform());
/*     */     }
/*     */ 
/*  49 */     this.jdField_a_of_type_Ze = new ze();
/*     */ 
/*  52 */     this.jdField_a_of_type_EE = new eE(this.jdField_a_of_type_ArrayOfEy);
/*  53 */     this.jdField_a_of_type_Zd = new zd(this.jdField_a_of_type_EE, 16.0F);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  75 */     if (this.jdField_a_of_type_Boolean) {
/*  76 */       c();
/*     */     }
/*  78 */     this.jdField_a_of_type_Zd.b();
/*     */ 
/*  81 */     for (int i = 0; i < 32; i++)
/*  82 */       if (this.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_Boolean) {
/*  83 */         this.jdField_a_of_type_Ze.a(this.jdField_a_of_type_ArrayOfEy[i]);
/*  84 */         this.jdField_a_of_type_Ze.b();
/*     */       }
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 111 */     this.jdField_a_of_type_Ze.c();
/* 112 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */   private void a(Transform paramTransform) {
/* 115 */     for (int i = 0; i < 32; i++)
/* 116 */       if (this.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_ComBulletphysicsLinearmathTransform == paramTransform) {
/* 117 */         this.jdField_a_of_type_ArrayOfEy[i].jdField_b_of_type_Boolean = true;
/* 118 */         this.jdField_a_of_type_Ct.a().a().a(this.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, 50.0F);
/* 119 */         (paramTransform = this.jdField_a_of_type_EE).jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_Int = ((paramTransform.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_Int + 1) % 100000);
/* 120 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 152 */     synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 153 */       while (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty())
/*     */       {
/*     */         Object localObject1;
/* 155 */         if ((
/* 155 */           localObject1 = (eA)this.jdField_a_of_type_JavaUtilArrayList.remove(0)).jdField_a_of_type_Boolean)
/*     */         {
/* 156 */           Object localObject2 = ((eA)localObject1).jdField_a_of_type_ComBulletphysicsLinearmathTransform; localObject1 = this; for (Object localObject3 = 0; ; localObject3++) { if (localObject3 >= 32) break label204; if (!localObject1.jdField_a_of_type_ArrayOfEy[localObject3].jdField_a_of_type_Boolean) { System.err.println("[MISSILE] STARTING NEW TRAIL " + ((Transform)localObject2).origin); Object localObject4 = localObject2; (localObject2 = localObject1.jdField_a_of_type_ArrayOfEy[localObject3]).b(); ((ey)localObject2).jdField_a_of_type_ComBulletphysicsLinearmathTransform = localObject4; ((ey)localObject2).jdField_b_of_type_ComBulletphysicsLinearmathTransform = null; ((ey)localObject2).jdField_b_of_type_Boolean = false; ((ey)localObject2).jdField_a_of_type_Boolean = true; localObject4 = localObject3;
/*     */               eE tmp143_136 = ((ez)localObject1).jdField_a_of_type_EE; i = tmp143_136.a((localObject2 = tmp143_136).jdField_a_of_type_ArrayOfEy[localObject4].jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin, ((eE)localObject2).jdField_a_of_type_JavaxVecmathVector3f); localObject2.jdField_a_of_type_ArrayOfEy[localObject4].jdField_a_of_type_Int = i; ((yW)localObject2).a.c(i, localObject4, i, 0.0F); break; } }
/*     */         } else {
/* 158 */           label204: a(i.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 166 */     for (int i = 0; i < 32; )
/*     */     {
/* 168 */       if (this.jdField_a_of_type_ArrayOfEy[i].jdField_a_of_type_Boolean)
/* 169 */         this.jdField_a_of_type_ArrayOfEy[i].a(paramxq);
/* 170 */       i++;
/*     */     }
/*     */ 
/* 174 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(xe.a().a());
/*     */ 
/* 177 */     if (this.jdField_a_of_type_Ze != null) {
/* 178 */       this.jdField_a_of_type_Ze.a(paramxq);
/*     */     }
/*     */ 
/* 181 */     this.jdField_a_of_type_EE.a(paramxq);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ez
 * JD-Core Version:    0.6.2
 */