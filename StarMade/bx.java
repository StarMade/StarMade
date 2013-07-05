/*    */ final class bx
/*    */   implements ww
/*    */ {
/*    */   bx(bw parambw, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final boolean a(String paramString, wB paramwB)
/*    */   {
/*    */     try
/*    */     {
/* 27 */       if (paramString.length() > 0) {
/* 28 */         paramString = Integer.parseInt(paramString);
/* 29 */         String str = this.jdField_a_of_type_Bw.a().a().getInventory(null).b(this.jdField_a_of_type_Short);
/*    */         kf localkf;
/* 34 */         if ((
/* 34 */           localkf = this.jdField_a_of_type_Bw.a().a()) == null)
/*    */         {
/* 35 */           this.jdField_a_of_type_Bw.a().a().b("ERROR: no shop available");
/* 36 */           return false;
/*    */         }
/* 38 */         if (paramString <= 0) {
/* 39 */           this.jdField_a_of_type_Bw.a().a().b("ERROR: Invalid quantity");
/* 40 */           return false;
/*    */         }
/*    */         int i;
/* 44 */         if ((
/* 44 */           i = localkf.a().a(this.jdField_a_of_type_Short)) >= 0)
/*    */         {
/* 44 */           localkf.a(); if (localkf.a().a(i) + paramString > mn.e()) {
/* 45 */             this.jdField_a_of_type_Bw.a().a().b("ERROR: Shop cannot stock any\nmore of that item!");
/* 46 */             localkf.a(); this.jdField_a_of_type_Bw.a().a(String.valueOf(mn.e() - localkf.a().a(i)));
/* 47 */             this.jdField_a_of_type_Bw.a().e();
/* 48 */             return false;
/*    */           }
/*    */         }
/* 50 */         if (paramString <= str) {
/* 51 */           xe.b("0022_action - receive credits");
/* 52 */           return true;
/*    */         }
/* 54 */         this.jdField_a_of_type_Bw.a().a().b("ERROR\nYou can't sell that many!\nYou can only sell " + str + "!");
/*    */ 
/* 59 */         this.jdField_a_of_type_Bw.a().a(String.valueOf(str));
/* 60 */         this.jdField_a_of_type_Bw.a().e();
/* 61 */         this.jdField_a_of_type_Bw.a().g();
/*    */ 
/* 63 */         return false;
/*    */       }
/*    */     }
/*    */     catch (NumberFormatException localNumberFormatException) {
/*    */     }
/* 68 */     paramwB.onFailedTextCheck("Please only enter numbers!");
/* 69 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bx
 * JD-Core Version:    0.6.2
 */