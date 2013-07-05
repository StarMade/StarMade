/*    */ import javax.vecmath.Vector2f;
/*    */ 
/*    */ public class uv extends uu
/*    */ {
/*    */   private Vector2f jdField_a_of_type_JavaxVecmathVector2f;
/*    */   private float jdField_a_of_type_Float;
/*    */   private float b;
/*    */ 
/*    */   public uv(uu[] paramArrayOfuu, q paramq1, q paramq2)
/*    */   {
/* 20 */     super(paramArrayOfuu, paramq1, paramq2, 5, 0);
/*    */ 
/* 11 */     new q();
/* 12 */     new q();
/* 13 */     new q();
/* 14 */     this.jdField_a_of_type_JavaxVecmathVector2f = new Vector2f();
/* 15 */     new Vector2f();
/*    */ 
/* 24 */     this.jdField_b_of_type_Float = 5.0F;
/*    */ 
/* 21 */     this.jdField_a_of_type_Float = 57.0F;
/*    */   }
/*    */ 
/*    */   public final boolean a(q paramq)
/*    */   {
/* 26 */     if ((paramq.b >= this.jdField_a_of_type_Q.b) && (paramq.b <= this.jdField_b_of_type_Q.b)) {
/* 27 */       this.jdField_a_of_type_JavaxVecmathVector2f.x = paramq.a;
/* 28 */       this.jdField_a_of_type_JavaxVecmathVector2f.y = paramq.c;
/* 29 */       if ((this.jdField_a_of_type_JavaxVecmathVector2f.length() < this.jdField_a_of_type_Float + this.jdField_b_of_type_Float) && (this.jdField_a_of_type_JavaxVecmathVector2f.length() > this.jdField_a_of_type_Float - this.jdField_b_of_type_Float)) {
/* 30 */         return true;
/*    */       }
/*    */     }
/*    */ 
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */   protected final short a(q paramq)
/*    */   {
/* 39 */     if ((!jdField_a_of_type_Boolean) && (!a(paramq))) throw new AssertionError();
/* 40 */     if ((paramq.b > this.jdField_a_of_type_Q.b) && (paramq.b < this.jdField_b_of_type_Q.b))
/*    */     {
/* 42 */       this.jdField_a_of_type_JavaxVecmathVector2f.x = paramq.a;
/* 43 */       this.jdField_a_of_type_JavaxVecmathVector2f.y = paramq.c;
/* 44 */       if ((this.jdField_a_of_type_JavaxVecmathVector2f.length() < this.jdField_a_of_type_Float + this.jdField_b_of_type_Float) && (this.jdField_a_of_type_JavaxVecmathVector2f.length() > this.jdField_a_of_type_Float - this.jdField_b_of_type_Float))
/*    */       {
/* 46 */         if ((this.jdField_a_of_type_JavaxVecmathVector2f.length() < this.jdField_a_of_type_Float + (this.jdField_b_of_type_Float - 2.0F)) && (this.jdField_a_of_type_JavaxVecmathVector2f.length() > this.jdField_a_of_type_Float - (this.jdField_b_of_type_Float - 2.0F))) {
/* 47 */           return 0;
/*    */         }
/* 49 */         return 5;
/*    */       }
/*    */ 
/*    */     }
/* 54 */     else if ((paramq.b == this.jdField_a_of_type_Q.b) || (paramq.b == this.jdField_b_of_type_Q.b))
/*    */     {
/* 56 */       this.jdField_a_of_type_JavaxVecmathVector2f.x = paramq.a;
/* 57 */       this.jdField_a_of_type_JavaxVecmathVector2f.y = paramq.c;
/* 58 */       if ((this.jdField_a_of_type_JavaxVecmathVector2f.length() < this.jdField_a_of_type_Float + 1.0F) && (this.jdField_a_of_type_JavaxVecmathVector2f.length() > this.jdField_a_of_type_Float - 1.0F)) {
/* 59 */         return 55;
/*    */       }
/* 61 */       return 5;
/*    */     }
/*    */ 
/* 65 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uv
 * JD-Core Version:    0.6.2
 */