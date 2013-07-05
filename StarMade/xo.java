/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ 
/*     */ final class xo
/*     */   implements Runnable
/*     */ {
/*     */   xo(xm paramxm)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/* 698 */       Thread.sleep(5000L);
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/* 701 */       localInterruptedException.printStackTrace();
/*     */     }
/*     */ 
/* 702 */     if (!xm.a(this.a))
/* 703 */       xm.a(new ErrorDialogException("Sound initialization failed. Please report the bug, restart the game, and turn sound off in options for a temporal workaround."));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xo
 * JD-Core Version:    0.6.2
 */