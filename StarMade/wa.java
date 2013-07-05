/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ 
/*     */ final class wa
/*     */   implements FilenameFilter
/*     */ {
/*     */   wa(vZ paramvZ)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final boolean accept(File paramFile, String paramString)
/*     */   {
/* 349 */     return paramString.startsWith(this.a.a);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wa
 * JD-Core Version:    0.6.2
 */