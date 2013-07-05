/*     */ import java.io.File;
/*     */ import javax.swing.filechooser.FileFilter;
/*     */ 
/*     */ final class pv extends FileFilter
/*     */ {
/*     */   public final boolean accept(File paramFile)
/*     */   {
/* 125 */     if (paramFile.isDirectory()) {
/* 126 */       return true;
/*     */     }
/* 128 */     if (paramFile.getName().endsWith(".png")) {
/* 129 */       return true;
/*     */     }
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   public final String getDescription()
/*     */   {
/* 136 */     return "PNG";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pv
 * JD-Core Version:    0.6.2
 */