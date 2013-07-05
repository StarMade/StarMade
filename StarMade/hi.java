/*     */ import java.io.PrintStream;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ final class hi
/*     */   implements ww
/*     */ {
/*     */   public final boolean a(String paramString, wB paramwB)
/*     */   {
/* 355 */     if ((paramString.length() >= 3) && (paramString.length() <= 16))
/*     */     {
/* 357 */       if (Pattern.matches("[a-zA-Z0-9 _-]+", paramString))
/*     */       {
/* 360 */         return true;
/*     */       }
/* 362 */       System.err.println("MATCH FOUND ^ALPHANUMERIC");
/*     */     }
/*     */ 
/* 367 */     paramwB.onFailedTextCheck("Please only alphanumeric (and space, _, -) values \nand between 3 and 16 long!");
/*     */ 
/* 372 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hi
 * JD-Core Version:    0.6.2
 */