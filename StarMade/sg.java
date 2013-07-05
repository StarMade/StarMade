/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import org.schema.game.common.updater.MemorySettings;
/*    */ 
/*    */ public final class sg
/*    */   implements ActionListener
/*    */ {
/*    */   public final void actionPerformed(ActionEvent paramActionEvent)
/*    */   {
/* 81 */     new MemorySettings()
/* 82 */       .setVisible(true);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sg
 * JD-Core Version:    0.6.2
 */