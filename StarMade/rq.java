/*     */ import javax.swing.JList;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import org.schema.game.network.ReceivedPlayer;
/*     */ 
/*     */ final class rq
/*     */   implements ListSelectionListener
/*     */ {
/*     */   rq(JList paramJList, JSplitPane paramJSplitPane)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void valueChanged(ListSelectionEvent paramListSelectionEvent)
/*     */   {
/* 139 */     if ((
/* 139 */       paramListSelectionEvent = this.jdField_a_of_type_JavaxSwingJList.getSelectedValue()) != null)
/*     */     {
/* 140 */       this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(new JScrollPane(new rg((ReceivedPlayer)paramListSelectionEvent)));
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rq
 * JD-Core Version:    0.6.2
 */