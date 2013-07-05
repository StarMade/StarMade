/*    */ public final class qe
/*    */ {
/*    */   public String a;
/*    */   public String b;
/*    */   public int a;
/*    */ 
/*    */   public qe(String paramString1, int paramInt, String paramString2)
/*    */   {
/* 10 */     this.b = paramString1;
/* 11 */     this.jdField_a_of_type_Int = paramInt;
/* 12 */     this.jdField_a_of_type_JavaLangString = paramString2;
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 17 */     return this.jdField_a_of_type_JavaLangString + "@" + this.b + ":" + this.jdField_a_of_type_Int;
/*    */   }
/*    */ 
/*    */   public final boolean equals(Object paramObject)
/*    */   {
/* 26 */     return (this.jdField_a_of_type_JavaLangString.equals(((qe)paramObject).jdField_a_of_type_JavaLangString)) && (this.b.equals(((qe)paramObject).b)) && (this.jdField_a_of_type_Int == ((qe)paramObject).jdField_a_of_type_Int);
/*    */   }
/*    */ 
/*    */   public final int hashCode()
/*    */   {
/* 35 */     return this.jdField_a_of_type_JavaLangString.hashCode() + this.b.hashCode() + this.jdField_a_of_type_Int;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qe
 * JD-Core Version:    0.6.2
 */