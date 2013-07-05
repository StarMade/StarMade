/*    */ import java.util.Random;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.game.common.data.world.Universe;
/*    */ 
/*    */ public final class uq extends uu
/*    */ {
/* 12 */   private q jdField_c_of_type_Q = new q();
/* 13 */   private q jdField_d_of_type_Q = new q();
/* 14 */   private q e = new q();
/* 15 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 16 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */   private q[] jdField_a_of_type_ArrayOfQ;
/* 18 */   private int jdField_b_of_type_Int = 20;
/* 19 */   private int jdField_c_of_type_Int = 5;
/*    */ 
/* 21 */   private int jdField_d_of_type_Int = 25;
/*    */ 
/*    */   public uq(uu[] paramArrayOfuu, q paramq1, q paramq2) {
/* 24 */     super(paramArrayOfuu, paramq1, paramq2, 4, 0);
/*    */   }
/*    */ 
/*    */   protected final short a(q paramq)
/*    */   {
/* 29 */     a(paramq, this.jdField_c_of_type_Q);
/* 30 */     a(this.jdField_b_of_type_Q, this.jdField_d_of_type_Q);
/* 31 */     a(this.jdField_a_of_type_Q, this.e);
/*    */ 
/* 33 */     a(this.e, this.jdField_d_of_type_Q);
/*    */ 
/* 36 */     if (this.jdField_a_of_type_ArrayOfQ == null) {
/* 37 */       this.jdField_a_of_type_ArrayOfQ = new q[this.jdField_b_of_type_Int];
/*    */ 
/* 39 */       for (paramq = 0; paramq < this.jdField_a_of_type_ArrayOfQ.length; paramq++) {
/* 40 */         this.jdField_a_of_type_ArrayOfQ[paramq] = new q(Universe.getRandom().nextInt(this.jdField_d_of_type_Q.a), this.jdField_c_of_type_Int + Universe.getRandom().nextInt(this.jdField_d_of_type_Int - this.jdField_c_of_type_Int), Universe.getRandom().nextInt(this.jdField_d_of_type_Q.jdField_c_of_type_Int));
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 46 */     for (paramq = 0; paramq < this.jdField_a_of_type_ArrayOfQ.length; paramq++) {
/* 47 */       this.jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_c_of_type_Q.a + 0.5F, 0.0F, this.jdField_c_of_type_Q.jdField_c_of_type_Int + 0.5F);
/* 48 */       this.jdField_b_of_type_JavaxVecmathVector3f.set(this.jdField_d_of_type_Q.a / 2.0F, 0.0F, this.jdField_d_of_type_Q.jdField_c_of_type_Int / 2.0F);
/*    */ 
/* 50 */       this.jdField_a_of_type_JavaxVecmathVector3f.sub(this.jdField_b_of_type_JavaxVecmathVector3f);
/*    */       float f;
/* 54 */       if (((
/* 54 */         f = this.jdField_a_of_type_JavaxVecmathVector3f.length()) < 
/* 54 */         this.jdField_d_of_type_Q.a / 2.0F + 0.5F) && (f > 5.5F) && (this.jdField_c_of_type_Q.a == this.jdField_a_of_type_ArrayOfQ[paramq].a) && (this.jdField_c_of_type_Q.jdField_c_of_type_Int == this.jdField_a_of_type_ArrayOfQ[paramq].jdField_c_of_type_Int) && (this.jdField_c_of_type_Q.jdField_b_of_type_Int > this.jdField_d_of_type_Q.jdField_b_of_type_Int - this.jdField_a_of_type_ArrayOfQ[paramq].jdField_b_of_type_Int)) {
/* 55 */         if ((Math.random() < 0.2D) && (this.jdField_c_of_type_Q.jdField_b_of_type_Int == this.jdField_d_of_type_Q.jdField_b_of_type_Int - this.jdField_a_of_type_ArrayOfQ[paramq].jdField_b_of_type_Int + 1)) {
/* 56 */           return 62;
/*    */         }
/* 58 */         return 5;
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 63 */     return 32767;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uq
 * JD-Core Version:    0.6.2
 */