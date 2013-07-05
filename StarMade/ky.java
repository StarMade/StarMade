/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ 
/*     */ public final class ky
/*     */   implements FilenameFilter
/*     */ {
/*     */   public final boolean accept(File paramFile, String paramString)
/*     */   {
/* 645 */     return (paramString.startsWith("SECTOR")) || (paramString.startsWith("VOIDSYSTEM"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ky
 * JD-Core Version:    0.6.2
 */