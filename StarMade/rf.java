/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JCheckBox;
/*     */ 
/*     */ final class rf
/*     */   implements ActionListener
/*     */ {
/*     */   rf(rc paramrc, JCheckBox paramJCheckBox1, int paramInt, JCheckBox paramJCheckBox2, JCheckBox paramJCheckBox3, JCheckBox paramJCheckBox4)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 242 */     if (this.jdField_a_of_type_JavaxSwingJCheckBox.isSelected()) {
/* 243 */       rc.a(this.jdField_a_of_type_Rc).a()[this.jdField_a_of_type_Int] |= 1L;
/*     */     }
/* 245 */     if (this.b.isSelected()) {
/* 246 */       rc.a(this.jdField_a_of_type_Rc).a()[this.jdField_a_of_type_Int] |= 4L;
/*     */     }
/* 248 */     if (this.c.isSelected()) {
/* 249 */       rc.a(this.jdField_a_of_type_Rc).a()[this.jdField_a_of_type_Int] |= 2L;
/*     */     }
/* 251 */     if (this.d.isSelected())
/* 252 */       rc.a(this.jdField_a_of_type_Rc).a()[this.jdField_a_of_type_Int] |= 8L;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rf
 * JD-Core Version:    0.6.2
 */