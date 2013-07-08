import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;
import org.schema.schine.network.objects.Sendable;

final class class_1281
  extends MouseAdapter
{
  class_1281(class_1287 paramclass_1287, JSplitPane paramJSplitPane) {}
  
  public final void mouseClicked(MouseEvent paramMouseEvent)
  {
    if (class_1287.a(this.jdField_field_1465_of_type_Class_1287).getSelectedIndex() >= 0)
    {
      paramMouseEvent = (Sendable)class_1287.a(this.jdField_field_1465_of_type_Class_1287).getModel().getElementAt(class_1287.a(this.jdField_field_1465_of_type_Class_1287).getSelectedIndex());
      class_1278 localclass_1278 = new class_1278(paramMouseEvent);
      System.err.println("VALUE CHANGED: " + paramMouseEvent);
      this.jdField_field_1465_of_type_JavaxSwingJSplitPane.setRightComponent(new JScrollPane(localclass_1278));
      org.schema.game.common.staremote.Staremote.field_2145 = localclass_1278;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1281
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */