/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public final class ey extends yW
/*     */ {
/*     */   Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform;
/*     */   Transform jdField_b_of_type_ComBulletphysicsLinearmathTransform;
/*  20 */   boolean jdField_a_of_type_Boolean = false;
/*     */ 
/*  25 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  26 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*  27 */   private Vector3f c = new Vector3f();
/*  28 */   private Vector3f jdField_d_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */   boolean jdField_b_of_type_Boolean;
/*     */   public int a;
/*  33 */   private int jdField_d_of_type_Int = 10;
/*     */ 
/*  35 */   public ey(Transform paramTransform) { super(512);
/*     */ 
/*  31 */     this.jdField_a_of_type_Int = -1;
/*     */ 
/*  36 */     this.c = true;
/*  37 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = paramTransform; }
/*     */ 
/*     */   private void a(Vector3f paramVector3f)
/*     */   {
/*  41 */     if (!this.jdField_b_of_type_Boolean)
/*  42 */       a(paramVector3f, this.jdField_a_of_type_JavaxVecmathVector3f);
/*     */   }
/*     */ 
/*     */   public final boolean a(int paramInt, xq paramxq)
/*     */   {
/*  89 */     float f = this.a.a(paramInt);
/*  90 */     this.a.a(paramInt, this.c);
/*  91 */     this.a.d(paramInt, this.jdField_d_of_type_JavaxVecmathVector3f);
/*  92 */     this.a.a(paramInt, f + paramxq.a() * 1000.0F);
/*     */ 
/*  94 */     this.a.a(paramInt, this.c.x + this.jdField_d_of_type_JavaxVecmathVector3f.x, this.c.y + this.jdField_d_of_type_JavaxVecmathVector3f.y, this.c.z + this.jdField_d_of_type_JavaxVecmathVector3f.z);
/*  95 */     return f < 3000.0F;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 103 */     if (this.jdField_b_of_type_ComBulletphysicsLinearmathTransform == null)
/*     */     {
/* 105 */       a(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 106 */       this.jdField_b_of_type_ComBulletphysicsLinearmathTransform = new Transform(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */     }
/*     */     else {
/* 109 */       this.jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 110 */       this.jdField_b_of_type_JavaxVecmathVector3f.sub(this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin);
/*     */       float f1;
/* 113 */       if ((
/* 113 */         f1 = this.jdField_b_of_type_JavaxVecmathVector3f.length()) > 
/* 113 */         zf.a) {
/* 114 */         this.jdField_b_of_type_JavaxVecmathVector3f.normalize();
/*     */ 
/* 116 */         this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.jdField_b_of_type_JavaxVecmathVector3f);
/*     */ 
/* 118 */         float f2 = zf.a;
/* 119 */         int i = (int)(f1 / f2);
/*     */ 
/* 121 */         float f3 = zf.a;
/* 122 */         if (i > this.jdField_d_of_type_Int)
/*     */         {
/* 125 */           f3 = f1 / this.jdField_d_of_type_Int;
/*     */         }
/*     */ 
/* 128 */         this.jdField_b_of_type_JavaxVecmathVector3f.scale(f3);
/* 129 */         while (f2 < f1) {
/* 130 */           this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.jdField_b_of_type_JavaxVecmathVector3f);
/* 131 */           a(this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.origin);
/* 132 */           f2 += f3;
/*     */         }
/* 134 */         this.jdField_b_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 141 */     super.a(paramxq);
/*     */ 
/* 143 */     if ((this.jdField_b_of_type_Boolean) && (b() <= 0))
/* 144 */       this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ey
 * JD-Core Version:    0.6.2
 */