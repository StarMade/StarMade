/*   1:    */import java.awt.Color;
/*   2:    */import javax.swing.JColorChooser;
/*   3:    */import javax.swing.event.ChangeEvent;
/*   4:    */import javax.swing.event.ChangeListener;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */
/* 383:    */final class oe
/* 384:    */  implements ChangeListener
/* 385:    */{
/* 386:    */  oe(JColorChooser paramJColorChooser, float[] paramArrayOfFloat, Vector3f paramVector3f) {}
/* 387:    */  
/* 388:    */  public final void stateChanged(ChangeEvent paramChangeEvent)
/* 389:    */  {
/* 390:390 */    this.jdField_a_of_type_JavaxSwingJColorChooser.getColor().getComponents(this.jdField_a_of_type_ArrayOfFloat);
/* 391:    */    
/* 392:392 */    (paramChangeEvent = new Vector3f()).x = this.jdField_a_of_type_ArrayOfFloat[0];
/* 393:393 */    paramChangeEvent.y = this.jdField_a_of_type_ArrayOfFloat[1];
/* 394:394 */    paramChangeEvent.z = this.jdField_a_of_type_ArrayOfFloat[2];
/* 395:    */    
/* 396:396 */    this.jdField_a_of_type_JavaxVecmathVector3f.set(paramChangeEvent);
/* 397:    */  }
/* 398:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oe
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */