/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import org.schema.game.common.updater.MemorySettings;
/*     */ 
/*     */ public final class sk
/*     */   implements ActionListener
/*     */ {
/*     */   public sk(MemorySettings paramMemorySettings)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/*     */     try
/*     */     {
/* 144 */       if (MemorySettings.a()) {
/* 145 */         sr.a = Integer.parseInt(MemorySettings.a(this.a).getText());
/* 146 */         sr.b = Integer.parseInt(MemorySettings.b(this.a).getText());
/* 147 */         sr.c = Integer.parseInt(MemorySettings.c(this.a).getText());
/*     */       } else {
/* 149 */         sr.d = Integer.parseInt(MemorySettings.a(this.a).getText());
/* 150 */         sr.e = Integer.parseInt(MemorySettings.b(this.a).getText());
/* 151 */         sr.f = Integer.parseInt(MemorySettings.c(this.a).getText());
/*     */       }
/*     */ 
/* 154 */       sr.g = Integer.parseInt(MemorySettings.d(this.a).getText());
/* 155 */       sr.h = Integer.parseInt(MemorySettings.e(this.a).getText());
/* 156 */       sr.i = Integer.parseInt(MemorySettings.f(this.a).getText());
/*     */       try
/*     */       {
/* 159 */         MemorySettings.b();
/*     */       }
/*     */       catch (Exception localException1)
/*     */       {
/* 165 */         localException1.printStackTrace();
/*     */ 
/* 162 */         JOptionPane.showOptionDialog(new JPanel(), "Settings applied but failed to save for next session", "ERROR", 0, 0, null, null, null);
/*     */       }
/*     */ 
/* 167 */       this.a.dispose();
/*     */       return;
/*     */     }
/*     */     catch (Exception localException2)
/*     */     {
/* 173 */       localException2.printStackTrace();
/*     */ 
/* 170 */       JOptionPane.showOptionDialog(new JPanel(), "Please only use numbers", "ERROR", 0, 0, null, null, null);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sk
 * JD-Core Version:    0.6.2
 */