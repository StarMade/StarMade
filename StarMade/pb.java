/*     */ import org.schema.game.common.facedit.ElementEditorFrame;
/*     */ 
/*     */ final class pb
/*     */   implements Runnable
/*     */ {
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/* 671 */       new ElementEditorFrame()
/* 672 */         .setVisible(true);
/*     */       return;
/*     */     }
/*     */     catch (Exception localException2)
/*     */     {
/*     */       Exception localException1;
/* 673 */       (
/* 674 */         localException1 = 
/* 676 */         localException2).printStackTrace();
/* 675 */       d.a(localException1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pb
 * JD-Core Version:    0.6.2
 */