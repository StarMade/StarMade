/*    */ import java.util.ArrayList;
/*    */ 
/*    */ final class fh
/*    */   implements ys
/*    */ {
/*    */   private fh(fg paramfg)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void a(yz paramyz, xp paramxp)
/*    */   {
/* 38 */     if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0))
/* 39 */       if (paramyz.b().equals("INVENTORY")) {
/* 40 */         if (!fg.a(this.jdField_a_of_type_Fg))
/* 41 */           fg.a(this.jdField_a_of_type_Fg);
/*    */       }
/* 43 */       else if (paramyz.b().equals("WEAPON")) {
/* 44 */         if (!fg.b(this.jdField_a_of_type_Fg))
/* 45 */           fg.b(this.jdField_a_of_type_Fg);
/*    */       }
/* 47 */       else if (paramyz.b().equals("FACTION")) {
/* 48 */         if (!fg.c(this.jdField_a_of_type_Fg))
/* 49 */           fg.c(this.jdField_a_of_type_Fg);
/*    */       }
/* 51 */       else if (paramyz.b().equals("CATALOG")) {
/* 52 */         if (!fg.d(this.jdField_a_of_type_Fg))
/* 53 */           fg.d(this.jdField_a_of_type_Fg);
/*    */       }
/* 55 */       else if (paramyz.b().equals("AI")) {
/* 56 */         if (!fg.e(this.jdField_a_of_type_Fg))
/* 57 */           fg.e(this.jdField_a_of_type_Fg);
/*    */       }
/* 59 */       else if (paramyz.b().equals("SHOP")) {
/* 60 */         if (!fg.f(this.jdField_a_of_type_Fg))
/* 61 */           fg.f(this.jdField_a_of_type_Fg);
/*    */       }
/* 63 */       else if (paramyz.b().equals("NAVIGATION")) {
/* 64 */         if (!fg.g(this.jdField_a_of_type_Fg))
/* 65 */           fg.g(this.jdField_a_of_type_Fg);
/*    */       } else {
/* 67 */         if (paramyz.b().equals("X")) {
/* 68 */           fg.h(this.jdField_a_of_type_Fg); return;
/*    */         }
/* 70 */         if (!jdField_a_of_type_Boolean) throw new AssertionError("not known command: " + paramyz.b());
/*    */       }
/*    */   }
/*    */ 
/*    */   public final boolean a()
/*    */   {
/* 82 */     return !this.jdField_a_of_type_Fg.a().b().isEmpty();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fh
 * JD-Core Version:    0.6.2
 */