import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JList;
import org.schema.game.common.staremote.Staremote;

final class class_1297
  implements Action
{
  class_1297(class_1313 paramclass_1313, JList paramJList, JFrame paramJFrame, Staremote paramStaremote) {}
  
  public final void actionPerformed(ActionEvent arg1)
  {
    synchronized (class_1313.a1(this.jdField_field_1474_of_type_Class_1313))
    {
      if (!this.jdField_field_1474_of_type_Class_1313.field_1489.booleanValue()) {
        this.jdField_field_1474_of_type_Class_1313.field_1489 = Boolean.valueOf(true);
      } else {
        return;
      }
    }
    ??? = (class_1315)this.jdField_field_1474_of_type_JavaxSwingJList.getSelectedValue();
    this.jdField_field_1474_of_type_JavaxSwingJFrame.dispose();
    this.jdField_field_1474_of_type_OrgSchemaGameCommonStaremoteStaremote.a2(???);
  }
  
  public final void setEnabled(boolean paramBoolean) {}
  
  public final void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener) {}
  
  public final void putValue(String paramString, Object paramObject) {}
  
  public final boolean isEnabled()
  {
    return false;
  }
  
  public final Object getValue(String paramString)
  {
    return null;
  }
  
  public final void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1297
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */