/*     */ import java.awt.Color;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ final class oe
/*     */   implements ChangeListener
/*     */ {
/*     */   oe(JColorChooser paramJColorChooser, float[] paramArrayOfFloat, Vector3f paramVector3f)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void stateChanged(ChangeEvent paramChangeEvent)
/*     */   {
/* 389 */     this.jdField_a_of_type_JavaxSwingJColorChooser.getColor()
/* 390 */       .getComponents(this.jdField_a_of_type_ArrayOfFloat);
/* 391 */     (
/* 392 */       paramChangeEvent = new Vector3f()).x = 
/* 392 */       this.jdField_a_of_type_ArrayOfFloat[0];
/* 393 */     paramChangeEvent.y = this.jdField_a_of_type_ArrayOfFloat[1];
/* 394 */     paramChangeEvent.z = this.jdField_a_of_type_ArrayOfFloat[2];
/*     */ 
/* 396 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(paramChangeEvent);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oe
 * JD-Core Version:    0.6.2
 */