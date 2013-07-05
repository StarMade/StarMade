/*    */ public final class ja
/*    */ {
/*    */   public int a;
/*    */   public int b;
/*    */   public int c;
/*    */   public short a;
/*    */ 
/*    */   public ja(q paramq, short paramShort)
/*    */   {
/* 12 */     a(paramq);
/* 13 */     this.jdField_a_of_type_Short = paramShort;
/*    */   }
/*    */   public ja() {
/*    */   }
/*    */   public ja(ja paramja) {
/* 18 */     this.jdField_a_of_type_Int = paramja.jdField_a_of_type_Int;
/* 19 */     this.b = paramja.b;
/* 20 */     this.c = paramja.c;
/* 21 */     this.jdField_a_of_type_Short = paramja.jdField_a_of_type_Short;
/*    */   }
/*    */ 
/*    */   public final boolean equals(Object paramObject) {
/* 25 */     if (paramObject != null) {
/* 26 */       if ((paramObject instanceof ja)) {
/* 27 */         paramObject = (ja)paramObject;
/* 28 */         return (this.jdField_a_of_type_Short == paramObject.jdField_a_of_type_Short) && (this.jdField_a_of_type_Int == paramObject.jdField_a_of_type_Int) && (this.b == paramObject.b) && (this.c == paramObject.c);
/*    */       }
/*    */ 
/* 33 */       paramObject = (q)paramObject;
/* 34 */       return (this.jdField_a_of_type_Int == paramObject.jdField_a_of_type_Int) && (this.b == paramObject.b) && (this.c == paramObject.c);
/*    */     }
/*    */ 
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public final int hashCode()
/*    */   {
/* 54 */     return ((this.jdField_a_of_type_Int ^ this.jdField_a_of_type_Int >>> 16) * 
/* 52 */       15 + (this.b ^ this.b >>> 16)) * 
/* 53 */       15 + (this.c ^ this.c >>> 16);
/*    */   }
/*    */ 
/*    */   public final void a(q paramq, short paramShort) {
/* 57 */     a(paramq);
/* 58 */     this.jdField_a_of_type_Short = paramShort;
/*    */   }
/*    */ 
/*    */   private void a(q paramq) {
/* 62 */     this.jdField_a_of_type_Int = paramq.jdField_a_of_type_Int;
/* 63 */     this.b = paramq.b;
/* 64 */     this.c = paramq.c;
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 71 */     return "ElementPosition [x=" + this.jdField_a_of_type_Int + ", y=" + this.b + ", z=" + this.c + ", type=" + this.jdField_a_of_type_Short + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ja
 * JD-Core Version:    0.6.2
 */