/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ 
/*     */ public final class jf
/*     */   implements FilenameFilter
/*     */ {
/*     */   public final boolean accept(File paramFile, String paramString)
/*     */   {
/* 353 */     return paramString.startsWith("ENTITY_SHIP_MOB_SIM");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jf
 * JD-Core Version:    0.6.2
 */