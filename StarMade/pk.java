/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ final class pk
/*     */   implements ActionListener
/*     */ {
/*     */   pk(oU paramoU)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/*     */     try
/*     */     {
/* 555 */       if (JOptionPane.showOptionDialog(this.a, "Database Migration from v0.078 to v0.079+.\nThis updates only the current universe.\nOld catalog data will automatically update, when you import!\nBe sure to have a Backup!\n(All you data got backed up on Version update)", "Migration", 0, 1, null, new String[] { "Ok", "Cancel" }, "Ok") == 
/* 555 */         1) {
/* 556 */         return;
/*     */       }
/*     */ 
/* 558 */       JOptionPane.showMessageDialog(this.a, "Database Migration in Progress.\nThis may take a few minutes.\nPlease do not interrupt this progress!");
/*     */ 
/* 563 */       tH.a.a();
/* 564 */       JOptionPane.showOptionDialog(this.a, "Database Migration was successfull.\nYou can now play the game. Have fun!", "Migration completed", 0, 1, null, new String[] { "Ok" }, "Ok");
/*     */       return;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 570 */       (
/* 571 */         paramActionEvent = 
/* 573 */         localIOException).printStackTrace();
/* 572 */       d.a(paramActionEvent);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pk
 * JD-Core Version:    0.6.2
 */