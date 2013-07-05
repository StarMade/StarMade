/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JOptionPane;
/*     */ import org.schema.game.common.updater.Launcher;
/*     */ 
/*     */ public final class sh
/*     */   implements ActionListener
/*     */ {
/*     */   public sh(Launcher paramLauncher)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/*  92 */     { "ham", "spam" }[2] = "yam";
/*     */ 
/* 103 */     if (((
/* 103 */       paramActionEvent = (String)JOptionPane.showInputDialog(this.a, "Mirror URL", "Mirror", -1, null, null, "")) != null) && 
/* 103 */       (paramActionEvent.length() > 0)) {
/* 104 */       sy.a = paramActionEvent;
/* 105 */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sh
 * JD-Core Version:    0.6.2
 */