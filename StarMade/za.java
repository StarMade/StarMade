/*    */ import java.util.Random;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.schine.graphicsengine.camera.Camera;
/*    */ 
/*    */ public final class za extends yW
/*    */ {
/* 19 */   private float jdField_b_of_type_Float = 30.0F;
/* 20 */   private float jdField_c_of_type_Float = 30.0F;
/* 21 */   private float d = this.jdField_c_of_type_Float * this.jdField_c_of_type_Float;
/* 22 */   private float e = 3.3F;
/* 23 */   private Random jdField_a_of_type_JavaUtilRandom = new Random();
/* 24 */   float jdField_a_of_type_Float = 0.0F;
/*    */   private float f;
/*    */   private float g;
/* 32 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 33 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/* 34 */   private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */ 
/*    */   public final boolean a(int paramInt, xq paramxq)
/*    */   {
/* 53 */     float f1 = this.f / this.g / 3.0F;
/* 54 */     float f2 = this.a.a(paramInt);
/* 55 */     this.a.a(paramInt, this.jdField_b_of_type_JavaxVecmathVector3f);
/* 56 */     this.jdField_c_of_type_JavaxVecmathVector3f.sub(this.jdField_b_of_type_JavaxVecmathVector3f, xe.a().a());
/* 57 */     if (this.jdField_c_of_type_JavaxVecmathVector3f.lengthSquared() > this.d * 2.0F) {
/* 58 */       return false;
/*    */     }
/*    */ 
/* 65 */     if ((this.jdField_b_of_type_JavaxVecmathVector3f.x - xe.a().a().x) * this.jdField_a_of_type_JavaxVecmathVector3f.x + (this.jdField_b_of_type_JavaxVecmathVector3f.y - xe.a().a().y) * this.jdField_a_of_type_JavaxVecmathVector3f.y + (this.jdField_b_of_type_JavaxVecmathVector3f.z - xe.a().a().z) * this.jdField_a_of_type_JavaxVecmathVector3f.z <= 
/* 65 */       0.0D) {
/* 66 */       return false;
/*    */     }
/* 68 */     this.a.a(paramInt, (float)(f2 + f1 * paramxq.a() * 1000.0D));
/*    */ 
/* 71 */     return f2 < 1000.0F;
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq)
/*    */   {
/* 78 */     xe.a().a(this.jdField_a_of_type_JavaxVecmathVector3f);
/* 79 */     super.a(paramxq);
/* 80 */     this.jdField_a_of_type_Float += paramxq.a();
/*    */   }
/*    */ 
/*    */   public final void a(float paramFloat1, float paramFloat2)
/*    */   {
/*    */     Camera localCamera;
/* 86 */     if ((
/* 86 */       localCamera = xe.a()) == null)
/*    */     {
/* 87 */       return;
/*    */     }
/* 89 */     this.f = paramFloat1;
/* 90 */     this.g = paramFloat2;
/*    */ 
/* 92 */     Vector3f localVector3f1 = new Vector3f(0.0F, 0.0F, 0.0F);
/* 93 */     paramFloat1 = (int)(this.e * (paramFloat1 / paramFloat2));
/*    */ 
/* 95 */     for (paramFloat2 = 0; paramFloat2 < paramFloat1; paramFloat2++) {
/* 96 */       Vector3f localVector3f2 = new Vector3f(localCamera.a());
/* 97 */       Vector3f localVector3f3 = new Vector3f(localCamera.c());
/* 98 */       Vector3f localVector3f4 = new Vector3f(localCamera.f());
/* 99 */       Vector3f localVector3f5 = new Vector3f(localCamera.d());
/* 100 */       localVector3f3.scale(this.jdField_c_of_type_Float);
/* 101 */       localVector3f4.scale(this.jdField_a_of_type_JavaUtilRandom.nextFloat() * this.jdField_b_of_type_Float - this.jdField_b_of_type_Float / 2.0F);
/* 102 */       localVector3f5.scale(this.jdField_a_of_type_JavaUtilRandom.nextFloat() * this.jdField_b_of_type_Float - this.jdField_b_of_type_Float / 2.0F);
/*    */ 
/* 104 */       localVector3f2.add(localVector3f3);
/* 105 */       localVector3f2.add(localVector3f4);
/* 106 */       localVector3f2.add(localVector3f5);
/*    */ 
/* 108 */       a(localVector3f2, localVector3f1);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     za
 * JD-Core Version:    0.6.2
 */