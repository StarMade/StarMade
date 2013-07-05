/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JTextField;
/*    */ import org.schema.game.common.Starter;
/*    */ 
/*    */ final class pt
/*    */   implements ActionListener
/*    */ {
/*    */   pt(pr parampr)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void actionPerformed(ActionEvent paramActionEvent)
/*    */   {
/* 88 */     if (pr.a(this.a).getText().length() > 0)
/*    */     {
/* 91 */       Starter.b(Starter.a = pr.a(this.a).getText());
/*    */     }
/*    */     else
/*    */     {
/* 93 */       Starter.a = "";
/* 94 */       Starter.b("");
/*    */     }
/* 96 */     this.a.dispose();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pt
 * JD-Core Version:    0.6.2
 */