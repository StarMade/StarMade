/*    */ public class w extends H
/*    */ {
/*    */   private fa a;
/*    */ 
/*    */   public w(ct paramct, String paramString1, String paramString2)
/*    */   {
/* 15 */     super(paramct);
/* 16 */     this.a = new fa(paramct, this, paramString1, paramString2);
/*    */   }
/*    */ 
/*    */   public final void a(yz paramyz, xp paramxp)
/*    */   {
/* 22 */     if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0) && (!b())) {
/* 23 */       H.a = System.currentTimeMillis();
/* 24 */       if (paramyz.b().equals("OK")) {
/* 25 */         xe.b("0022_menu_ui - enter");
/* 26 */         d(); return;
/*    */       }
/* 28 */       if (paramyz.b().equals("CANCEL")) {
/* 29 */         xe.b("0022_menu_ui - enter");
/* 30 */         d(); return;
/*    */       }
/*    */ 
/* 33 */       if (!b) throw new AssertionError("not known command: " + paramyz.b());
/*    */     }
/*    */   }
/*    */ 
/*    */   public final yz a()
/*    */   {
/* 47 */     return this.a;
/*    */   }
/*    */ 
/*    */   public void handleKeyEvent()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final boolean a()
/*    */   {
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     w
 * JD-Core Version:    0.6.2
 */