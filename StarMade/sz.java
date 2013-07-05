/*     */ import java.io.IOException;
/*     */ 
/*     */ final class sz
/*     */   implements Runnable
/*     */ {
/*     */   sz(sy paramsy)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/* 489 */       sy.a(this.a);
/*     */       return;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 492 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sz
 * JD-Core Version:    0.6.2
 */