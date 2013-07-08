import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;

final class class_584
  extends MouseAdapter
{
  class_584(class_582 paramclass_582, class_371 paramclass_371, JSplitPane paramJSplitPane) {}
  
  public final void mouseClicked(MouseEvent paramMouseEvent)
  {
    if (class_582.a(this.jdField_field_883_of_type_Class_582).getSelectedIndex() >= 0)
    {
      paramMouseEvent = (class_773)class_582.a(this.jdField_field_883_of_type_Class_582).getModel().getElementAt(class_582.a(this.jdField_field_883_of_type_Class_582).getSelectedIndex());
      class_582.a1(this.jdField_field_883_of_type_Class_582, new class_580(this.jdField_field_883_of_type_Class_371, paramMouseEvent));
      JSplitPane localJSplitPane;
      (localJSplitPane = new JSplitPane()).setRightComponent(class_582.a2(this.jdField_field_883_of_type_Class_582));
      localJSplitPane.setOrientation(0);
      this.jdField_field_883_of_type_JavaxSwingJSplitPane.setRightComponent(localJSplitPane);
      paramMouseEvent = new JScrollPane(new class_573(this.jdField_field_883_of_type_Class_371, paramMouseEvent));
      localJSplitPane.setDividerLocation(280);
      paramMouseEvent.setPreferredSize(new Dimension(32, 230));
      localJSplitPane.setLeftComponent(paramMouseEvent);
      class_582.a2(this.jdField_field_883_of_type_Class_582).update(null, null);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_584
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */