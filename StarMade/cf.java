/*    */ public final class cf extends cb
/*    */ {
/*    */   private static final long serialVersionUID = 9159389806588076558L;
/*    */   private boolean c;
/*    */ 
/*    */   public cf(wk paramwk, ct paramct, String paramString, int paramInt)
/*    */   {
/* 15 */     super(paramwk, paramct, paramString, paramInt);
/*    */   }
/*    */ 
/*    */   protected final boolean a()
/*    */   {
/* 21 */     this.a.a().a(); bJ.a();
/* 22 */     if ((this.c != this.jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Boolean)) {
/* 23 */       this.jdField_a_of_type_Long = System.currentTimeMillis();
/*    */     }
/* 25 */     this.c = this.jdField_a_of_type_Boolean;
/*    */ 
/* 27 */     if (!this.jdField_a_of_type_Boolean) { this.a.a().a(); bJ.a(); } else {
/* 28 */       return (!this.a.a().a().b()) && (System.currentTimeMillis() - this.jdField_a_of_type_Long > this.a);
/*    */     }
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   public final String a()
/*    */   {
/* 41 */     if (this.jdField_a_of_type_Boolean) { this.a.a().a(); bJ.a();
/* 42 */       return super.a() + "(Tutorial will resume in " + (this.a - (System.currentTimeMillis() - this.jdField_a_of_type_Long)) / 1000L + " sec\nor press 'k')";
/*    */     }
/* 44 */     return super.a();
/*    */   }
/*    */ 
/*    */   public final boolean c()
/*    */   {
/* 54 */     this.c = false;
/* 55 */     return super.c();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cf
 * JD-Core Version:    0.6.2
 */