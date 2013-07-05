/*    */ import javax.vecmath.Vector4f;
/*    */ 
/*    */ public final class yo
/*    */   implements yn
/*    */ {
/* 10 */   private final Vector4f jdField_a_of_type_JavaxVecmathVector4f = new Vector4f();
/*    */   private float jdField_a_of_type_Float;
/*    */   private float jdField_b_of_type_Float;
/*    */   private String jdField_a_of_type_JavaLangString;
/*    */   private Vector4f jdField_b_of_type_JavaxVecmathVector4f;
/*    */   public static final zE a;
/*    */ 
/*    */   public yo(String paramString, Vector4f paramVector4f)
/*    */   {
/* 20 */     this.jdField_b_of_type_JavaxVecmathVector4f = paramVector4f;
/* 21 */     this.jdField_a_of_type_JavaLangString = paramString;
/* 22 */     this.jdField_a_of_type_Float = 15.0F;
/* 23 */     this.jdField_b_of_type_Float = (paramString = this).jdField_a_of_type_Float; paramString.jdField_a_of_type_JavaxVecmathVector4f.set(paramString.jdField_b_of_type_JavaxVecmathVector4f);
/*    */   }
/*    */ 
/*    */   public final Vector4f a()
/*    */   {
/* 28 */     return this.jdField_a_of_type_JavaxVecmathVector4f;
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq) {
/* 32 */     this.jdField_b_of_type_Float = Math.max(0.0F, this.jdField_b_of_type_Float - paramxq.a());
/*    */ 
/* 34 */     if (this.jdField_b_of_type_Float > this.jdField_a_of_type_Float - 0.25F) {
/* 35 */       this.jdField_a_of_type_JavaxVecmathVector4f.x = (1.0F - 0.5F * jdField_a_of_type_ZE.a());
/* 36 */       this.jdField_a_of_type_JavaxVecmathVector4f.y = (1.0F - 0.5F * jdField_a_of_type_ZE.a());
/*    */     } else {
/* 38 */       this.jdField_a_of_type_JavaxVecmathVector4f.set(this.jdField_b_of_type_JavaxVecmathVector4f);
/*    */     }
/* 40 */     if (this.jdField_b_of_type_Float < 3.0F) {
/* 41 */       paramxq = this.jdField_b_of_type_Float / 3.0F;
/* 42 */       this.jdField_a_of_type_JavaxVecmathVector4f.w = paramxq;
/*    */     }
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 61 */     return this.jdField_a_of_type_JavaLangString;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 15 */     jdField_a_of_type_ZE = new zE(25.0F);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yo
 * JD-Core Version:    0.6.2
 */