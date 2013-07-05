/*    */ final class bs
/*    */   implements ww
/*    */ {
/*    */   bs(br parambr)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final boolean a(String paramString, wB paramwB)
/*    */   {
/*    */     try
/*    */     {
/* 26 */       if (paramString.length() > 0)
/*    */       {
/* 29 */         if (Integer.parseInt(paramString) <= 0)
/*    */         {
/* 30 */           this.a.a().a().b("ERROR: Invalid quantity");
/* 31 */           return false;
/*    */         }
/*    */ 
/* 35 */         xe.b("0022_action - purchase with credits");
/* 36 */         return true;
/*    */       }
/*    */     } catch (NumberFormatException localNumberFormatException) {
/*    */     }
/* 40 */     paramwB.onFailedTextCheck("Please only enter numbers!");
/* 41 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bs
 * JD-Core Version:    0.6.2
 */