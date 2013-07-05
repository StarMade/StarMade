/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JSpinner;
/*     */ import org.schema.game.common.data.element.FactoryResource;
/*     */ 
/*     */ final class ot
/*     */   implements ActionListener
/*     */ {
/*     */   ot(op paramop, nb paramnb)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 159 */     if ((op.a(this.jdField_a_of_type_Op) > 0) && 
/* 160 */       (((Integer)op.a(this.jdField_a_of_type_Op).getValue()).intValue() > 0)) {
/* 161 */       this.jdField_a_of_type_Nb.a(new FactoryResource(((Integer)op.a(this.jdField_a_of_type_Op).getValue()).intValue(), op.a(this.jdField_a_of_type_Op)));
/*     */     }
/*     */ 
/* 167 */     this.jdField_a_of_type_Op.dispose();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ot
 * JD-Core Version:    0.6.2
 */