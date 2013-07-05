/*    */ public final class mj
/*    */ {
/*    */   Object a;
/*    */   public short a;
/*    */   public int a;
/*    */ 
/*    */   public final void a(int paramInt)
/*    */   {
/* 15 */     if ((this.jdField_a_of_type_JavaLangObject instanceof Integer))
/* 16 */       this.jdField_a_of_type_JavaLangObject = Integer.valueOf(((Integer)this.jdField_a_of_type_JavaLangObject).intValue() + paramInt);
/*    */   }
/*    */ 
/*    */   public final int a() {
/* 20 */     if ((this.jdField_a_of_type_JavaLangObject instanceof Integer)) {
/* 21 */       return ((Integer)this.jdField_a_of_type_JavaLangObject).intValue();
/*    */     }
/* 23 */     return 1;
/*    */   }
/*    */ 
/*    */   public mj()
/*    */   {
/*    */   }
/*    */ 
/*    */   public mj(mj parammj, int paramInt)
/*    */   {
/* 33 */     this.jdField_a_of_type_JavaLangObject = parammj.jdField_a_of_type_JavaLangObject;
/* 34 */     this.jdField_a_of_type_Short = parammj.jdField_a_of_type_Short;
/* 35 */     this.jdField_a_of_type_Int = paramInt;
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 40 */     return "[slot " + this.jdField_a_of_type_Int + "; t " + this.jdField_a_of_type_Short + "; c " + this.jdField_a_of_type_JavaLangObject + "]";
/*    */   }
/*    */ 
/*    */   public final void b(int paramInt)
/*    */   {
/* 46 */     this.jdField_a_of_type_JavaLangObject = Integer.valueOf(paramInt);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mj
 * JD-Core Version:    0.6.2
 */