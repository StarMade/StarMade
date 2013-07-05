/*     */ final class ia
/*     */   implements ww
/*     */ {
/*     */   public final boolean a(String paramString, wB paramwB)
/*     */   {
/*     */     try
/*     */     {
/* 440 */       if (paramString.length() == 0) {
/* 441 */         return true;
/*     */       }
/* 443 */       q.a(paramString);
/* 444 */       return true; } catch (NumberFormatException localNumberFormatException) { localNumberFormatException
/* 445 */         .printStackTrace();
/*     */ 
/* 448 */       paramwB.onFailedTextCheck("Wrong Format. Must be x, y, z"); }
/* 449 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ia
 * JD-Core Version:    0.6.2
 */