import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Vector3f;

final class class_513
  implements ChangeListener
{
  class_513(JColorChooser paramJColorChooser, float[] paramArrayOfFloat, Vector3f paramVector3f) {}
  
  public final void stateChanged(ChangeEvent paramChangeEvent)
  {
    this.jdField_field_823_of_type_JavaxSwingJColorChooser.getColor().getComponents(this.jdField_field_823_of_type_ArrayOfFloat);
    (paramChangeEvent = new Vector3f()).field_615 = this.jdField_field_823_of_type_ArrayOfFloat[0];
    paramChangeEvent.field_616 = this.jdField_field_823_of_type_ArrayOfFloat[1];
    paramChangeEvent.field_617 = this.jdField_field_823_of_type_ArrayOfFloat[2];
    this.jdField_field_823_of_type_JavaxVecmathVector3f.set(paramChangeEvent);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_513
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */