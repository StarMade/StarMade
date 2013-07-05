/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ 
/*    */ final class bp
/*    */   implements ww
/*    */ {
/*    */   bp(bo parambo, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final boolean a(String paramString, wB paramwB)
/*    */   {
/*    */     try
/*    */     {
/* 26 */       if (paramString.length() > 0) {
/* 27 */         ElementInformation localElementInformation = ElementKeyMap.getInfo(this.jdField_a_of_type_Short);
/*    */ 
/* 30 */         if ((
/* 30 */           paramString = Integer.parseInt(paramString)) <= 0)
/*    */         {
/* 31 */           this.jdField_a_of_type_Bo.a().a().b("ERROR: Invalid quantity");
/* 32 */           return false;
/*    */         }
/*    */         kf localkf;
/* 35 */         if ((
/* 35 */           localkf = this.jdField_a_of_type_Bo.a().a()) == null)
/*    */         {
/* 36 */           this.jdField_a_of_type_Bo.a().a().b("ERROR: no shop available");
/* 37 */           return false;
/*    */         }
/*    */ 
/* 44 */         if (((
/* 44 */           i = localkf.a().a(this.jdField_a_of_type_Short)) < 0) || 
/* 44 */           (localkf.a().a(i) < paramString)) {
/* 45 */           this.jdField_a_of_type_Bo.a().a().b("ERROR: shop out of item");
/* 46 */           if (i >= 0) {
/* 47 */             this.jdField_a_of_type_Bo.a().a();
/* 48 */             this.jdField_a_of_type_Bo.a().a(String.valueOf(localkf.a().a(i)));
/* 49 */             this.jdField_a_of_type_Bo.a().e();
/* 50 */             this.jdField_a_of_type_Bo.a().g();
/*    */           }
/* 52 */           return false;
/*    */         }
/*    */ 
/* 56 */         int i = localkf.a(localElementInformation, paramString);
/*    */ 
/* 58 */         if (this.jdField_a_of_type_Bo.a().a().b() >= i)
/*    */         {
/* 60 */           xe.b("0022_action - purchase with credits");
/* 61 */           return true;
/*    */         }
/* 63 */         paramString = this.jdField_a_of_type_Bo.a().a().b() / localkf.a(localElementInformation, 1);
/* 64 */         this.jdField_a_of_type_Bo.a().a().b("ERROR\nYou can't affort that many!\nYou can only affort " + paramString);
/*    */ 
/* 68 */         this.jdField_a_of_type_Bo.a().a();
/* 69 */         this.jdField_a_of_type_Bo.a().a(String.valueOf(paramString));
/* 70 */         this.jdField_a_of_type_Bo.a().e();
/* 71 */         this.jdField_a_of_type_Bo.a().g();
/*    */ 
/* 73 */         return false;
/*    */       }
/*    */     }
/*    */     catch (NumberFormatException localNumberFormatException) {
/*    */     }
/* 78 */     paramwB.onFailedTextCheck("Please only enter numbers!");
/* 79 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bp
 * JD-Core Version:    0.6.2
 */