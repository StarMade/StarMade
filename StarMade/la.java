/*     */ import java.io.IOException;
/*     */ import org.schema.game.common.crashreporter.CrashReporter;
/*     */ 
/*     */ public final class la
/*     */   implements Runnable
/*     */ {
/*     */   public la(CrashReporter paramCrashReporter)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/* 267 */       CrashReporter.a(this.a);
/*     */       return;
/*     */     }
/*     */     catch (IOException localIOException2)
/*     */     {
/*     */       IOException localIOException1;
/* 268 */       (
/* 269 */         localIOException1 = 
/* 271 */         localIOException2).printStackTrace();
/* 270 */       d.a(localIOException1, true);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     la
 * JD-Core Version:    0.6.2
 */