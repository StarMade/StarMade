import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;

final class class_1249
  extends MouseAdapter
{
  class_1249(class_1251 paramclass_1251, JSplitPane paramJSplitPane) {}
  
  public final void mouseClicked(MouseEvent paramMouseEvent)
  {
    if (class_1251.a(this.jdField_field_1438_of_type_Class_1251).getSelectedIndex() >= 0)
    {
      paramMouseEvent = (class_748)class_1251.a(this.jdField_field_1438_of_type_Class_1251).getModel().getElementAt(class_1251.a(this.jdField_field_1438_of_type_Class_1251).getSelectedIndex());
      class_1227 localclass_1227 = new class_1227(paramMouseEvent);
      System.err.println("VALUE CHANGED: " + paramMouseEvent);
      this.jdField_field_1438_of_type_JavaxSwingJSplitPane.setRightComponent(new JScrollPane(localclass_1227));
      org.schema.game.common.staremote.Staremote.field_2145 = localclass_1227;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1249
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */