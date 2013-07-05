/*    */ public final class jb
/*    */ {
/*    */   public String a;
/*    */   public int a;
/*    */   public String b;
/*    */ 
/*    */   public jb(String paramString1, int paramInt, String paramString2)
/*    */   {
/*  9 */     this.jdField_a_of_type_JavaLangString = paramString1;
/* 10 */     this.jdField_a_of_type_Int = paramInt;
/* 11 */     this.b = paramString2;
/*    */   }
/*    */ 
/*    */   public final boolean equals(Object paramObject)
/*    */   {
/* 19 */     return (this.jdField_a_of_type_JavaLangString.equals(((jb)paramObject).jdField_a_of_type_JavaLangString)) && (this.jdField_a_of_type_Int == ((jb)paramObject).jdField_a_of_type_Int);
/*    */   }
/*    */ 
/*    */   public final int hashCode()
/*    */   {
/* 27 */     return this.jdField_a_of_type_JavaLangString.hashCode() + this.jdField_a_of_type_Int;
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 32 */     return this.jdField_a_of_type_JavaLangString + ":" + this.jdField_a_of_type_Int;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jb
 * JD-Core Version:    0.6.2
 */