/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ 
/*     */ final class py
/*     */   implements ActionListener
/*     */ {
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/*     */     try
/*     */     {
/* 146 */       new File(sE.a("StarMade"), "cred").delete();
/*     */       return;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 147 */       (
/* 148 */         paramActionEvent = 
/* 150 */         localException).printStackTrace();
/* 149 */       d.a(paramActionEvent);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     py
 * JD-Core Version:    0.6.2
 */