/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTextPane;
/*     */ import org.schema.game.common.data.element.BlockLevel;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ 
/*     */ final class oi
/*     */   implements ActionListener
/*     */ {
/*     */   oi(of paramof, ElementInformation paramElementInformation, JTextPane paramJTextPane)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 140 */     if (of.a(this.jdField_a_of_type_Of) > 0)
/* 141 */       this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.setLevel(new BlockLevel(of.a(this.jdField_a_of_type_Of), of.a(this.jdField_a_of_type_Of).getValue()));
/*     */     else {
/* 143 */       this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.setLevel(null);
/*     */     }
/*     */ 
/* 146 */     this.jdField_a_of_type_JavaxSwingJTextPane.setText(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getLevel() != null ? this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getLevel().toString() : "   -   ");
/* 147 */     this.jdField_a_of_type_Of.dispose();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oi
 * JD-Core Version:    0.6.2
 */