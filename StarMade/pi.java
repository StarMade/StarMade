/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JCheckBoxMenuItem;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.border.TitledBorder;
/*     */ 
/*     */ final class pi
/*     */   implements ActionListener
/*     */ {
/*     */   pi(oU paramoU, JCheckBoxMenuItem paramJCheckBoxMenuItem)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 511 */     if (this.jdField_a_of_type_JavaxSwingJCheckBoxMenuItem.isSelected()) {
/* 512 */       paramActionEvent = new pn();
/* 513 */       oU.a(this.jdField_a_of_type_OU).setViewportView(paramActionEvent);
/* 514 */       paramActionEvent.setBorder(new TitledBorder(null, "Settings", 4, 2, null, null));
/* 515 */       return;
/* 516 */     }paramActionEvent = new po();
/* 517 */     oU.a(this.jdField_a_of_type_OU).setViewportView(paramActionEvent);
/* 518 */     paramActionEvent.setBorder(new TitledBorder(null, "Settings", 4, 2, null, null));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pi
 * JD-Core Version:    0.6.2
 */