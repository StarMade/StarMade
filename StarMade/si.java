/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import javax.swing.JOptionPane;
/*     */ import org.schema.game.common.updater.Launcher;
/*     */ import org.schema.game.common.updater.MemorySettings;
/*     */ 
/*     */ public final class si
/*     */   implements ActionListener
/*     */ {
/*     */   public si(Launcher paramLauncher)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 125 */     if (((
/* 125 */       paramActionEvent = (String)JOptionPane.showInputDialog(this.a, "Please enter a port to start the server on", "Port", -1, null, null, String.valueOf(sr.j))) != null) && 
/* 125 */       (paramActionEvent.length() > 0)) try { paramActionEvent = 0; sr.j = Integer.parseInt(paramActionEvent);
/*     */ 
/* 130 */         JOptionPane.showMessageDialog(this.a, "Saving Setting: Port set to " + sr.j + ".", "Port", 1);
/*     */ 
/* 134 */         MemorySettings.b();
/*     */         return;
/*     */       } catch (NumberFormatException localNumberFormatException) { JOptionPane.showMessageDialog(this.a, "Port must be a number", "Port set error", 0);
/*     */         return;
/*     */       }
/*     */       catch (IOException localIOException) {
/* 140 */         paramActionEvent = null;
/*     */ 
/* 143 */         localIOException.printStackTrace();
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     si
 * JD-Core Version:    0.6.2
 */