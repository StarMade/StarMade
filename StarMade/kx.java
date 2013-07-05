/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ 
/*     */ public final class kx
/*     */   implements FilenameFilter
/*     */ {
/*     */   public kx(String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final boolean accept(File paramFile, String paramString)
/*     */   {
/* 179 */     return paramString.startsWith(this.a);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kx
 * JD-Core Version:    0.6.2
 */