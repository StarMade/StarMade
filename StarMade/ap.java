/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ 
/*    */ final class ap
/*    */   implements ww
/*    */ {
/*    */   ap(short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final boolean a(String paramString, wB paramwB)
/*    */   {
/*    */     try
/*    */     {
/* 32 */       if (paramString.length() > 0) {
/* 33 */         ElementKeyMap.getInfo(this.a);
/* 34 */         Integer.parseInt(paramString);
/* 35 */         return true;
/*    */       }
/*    */     }
/*    */     catch (NumberFormatException localNumberFormatException) {
/*    */     }
/* 40 */     paramwB.onFailedTextCheck("Amount must be a number!");
/* 41 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ap
 * JD-Core Version:    0.6.2
 */