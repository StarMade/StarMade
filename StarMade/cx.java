/*    */ public final class cx
/*    */ {
/*    */   public final int a;
/*    */   public final int b;
/*    */   public final mF a;
/*    */ 
/*    */   public cx(mF parammF, int paramInt1, int paramInt2)
/*    */   {
/* 11 */     this.jdField_a_of_type_Int = paramInt1;
/* 12 */     this.b = paramInt2;
/* 13 */     this.jdField_a_of_type_MF = parammF;
/*    */   }
/*    */ 
/*    */   public final int hashCode()
/*    */   {
/* 21 */     return this.jdField_a_of_type_MF.hashCode() + this.jdField_a_of_type_Int + this.b * 10000;
/*    */   }
/*    */ 
/*    */   public final boolean equals(Object paramObject)
/*    */   {
/* 29 */     return (((cx)paramObject).jdField_a_of_type_MF == this.jdField_a_of_type_MF) && (((cx)paramObject).jdField_a_of_type_Int == this.jdField_a_of_type_Int) && (((cx)paramObject).b == this.b);
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 36 */     return "SectorChange[" + this.jdField_a_of_type_Int + " -> " + this.b + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cx
 * JD-Core Version:    0.6.2
 */