/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JList;
/*     */ import org.schema.game.common.staremote.Staremote;
/*     */ 
/*     */ final class qg
/*     */   implements ActionListener
/*     */ {
/*     */   qg(JList paramJList, JFrame paramJFrame, Staremote paramStaremote)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 108 */     if ((
/* 108 */       paramActionEvent = this.jdField_a_of_type_JavaxSwingJList.getSelectedValue()) != null)
/*     */     {
/* 109 */       paramActionEvent = (qe)paramActionEvent;
/* 110 */       this.jdField_a_of_type_JavaxSwingJFrame.dispose();
/* 111 */       this.jdField_a_of_type_OrgSchemaGameCommonStaremoteStaremote.a(paramActionEvent);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qg
 * JD-Core Version:    0.6.2
 */