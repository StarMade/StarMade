/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ 
/*     */ final class os
/*     */   implements ChangeListener
/*     */ {
/*     */   os(op paramop)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void stateChanged(ChangeEvent paramChangeEvent)
/*     */   {
/* 140 */     if ((!jdField_a_of_type_Boolean) && (op.b(this.jdField_a_of_type_Op) == null)) throw new AssertionError();
/* 141 */     op.b(this.jdField_a_of_type_Op).setText("Count " + String.valueOf(op.a(this.jdField_a_of_type_Op).getValue()));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     os
 * JD-Core Version:    0.6.2
 */