/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ 
/*     */ public final class sj
/*     */   implements Runnable
/*     */ {
/*     */   public sj(JScrollPane paramJScrollPane)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/* 176 */     this.a.getVerticalScrollBar().setValue(this.a.getVerticalScrollBar().getMinimum());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sj
 * JD-Core Version:    0.6.2
 */