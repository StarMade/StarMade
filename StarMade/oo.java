/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JList;
/*    */ import org.schema.game.common.data.element.FactoryResource;
/*    */ 
/*    */ final class oo
/*    */   implements ActionListener
/*    */ {
/*    */   oo(om paramom)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void actionPerformed(ActionEvent paramActionEvent)
/*    */   {
/* 73 */     for (Object localObject : om.a(this.a).getSelectedValues())
/*    */     {
/* 74 */       if (paramActionEvent != null)
/* 75 */         om.a(this.a).b((FactoryResource)localObject);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oo
 * JD-Core Version:    0.6.2
 */