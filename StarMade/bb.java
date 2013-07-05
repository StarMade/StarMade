/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public final class bb extends H
/*    */ {
/*    */   private lP jdField_a_of_type_LP;
/*    */   private lP b;
/*    */   private gX jdField_a_of_type_GX;
/*    */ 
/*    */   public bb(ct paramct, lP paramlP1, lP paramlP2)
/*    */   {
/* 18 */     super(paramct);
/* 19 */     this.jdField_a_of_type_LP = paramlP1;
/* 20 */     this.b = paramlP2;
/*    */ 
/* 22 */     this.jdField_a_of_type_GX = new gX(paramct, paramlP1, paramlP2, this);
/* 23 */     this.jdField_a_of_type_GX.e();
/*    */   }
/*    */ 
/*    */   public final void a(yz paramyz, xp paramxp)
/*    */   {
/* 29 */     if (paramxp.a()) {
/* 30 */       if (paramyz.b().equals("OK")) {
/* 31 */         d(); return;
/* 32 */       }if ((paramyz.b().equals("CANCEL")) || (paramyz.b().equals("X"))) {
/* 33 */         System.err.println("CANCEL");
/* 34 */         d();
/*    */         return;
/*    */       }
/*    */       lP locallP;
/* 35 */       if (paramyz.b().equals("WAR")) {
/* 36 */         locallP = this.b; paramxp = this.jdField_a_of_type_LP; (paramyz = this.a.a().a.a.a).e(true); new aT(paramyz, paramyz.a(), "Declare war", "Enter a message for your declaration", paramxp, locallP).c();
/*    */ 
/* 38 */         d(); return;
/* 39 */       }if (paramyz.b().equals("PEACE")) {
/* 40 */         locallP = this.b; paramxp = this.jdField_a_of_type_LP; (paramyz = this.a.a().a.a.a).e(true); new aU(paramyz, paramyz.a(), "Peace Treaty Offer", "Enter a message for the offer", paramxp, locallP).c();
/*    */ 
/* 42 */         d(); return;
/* 43 */       }if (paramyz.b().equals("ALLY")) {
/* 44 */         locallP = this.b; paramxp = this.jdField_a_of_type_LP; (paramyz = this.a.a().a.a.a).e(true); new aW(paramyz, paramyz.a(), "Alliance Offer", "Enter a message for the offer", paramxp, locallP).c();
/*    */ 
/* 46 */         d();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public final boolean a()
/*    */   {
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   public final void handleKeyEvent()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final yz a()
/*    */   {
/* 71 */     return this.jdField_a_of_type_GX;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/* 76 */     this.a.a().a.a.a.a(500);
/*    */ 
/* 78 */     this.a.a().a.a.a.e(false);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bb
 * JD-Core Version:    0.6.2
 */