import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class class_1295
  extends JPanel
  implements class_377
{
  private JTextArea jdField_field_263_of_type_JavaxSwingJTextArea;
  
  public class_1295(class_371 paramclass_371)
  {
    setLayout(new BorderLayout(0, 0));
    JScrollPane localJScrollPane = new JScrollPane();
    add(localJScrollPane, "Center");
    this.jdField_field_263_of_type_JavaxSwingJTextArea = new JTextArea();
    ((DefaultCaret)this.jdField_field_263_of_type_JavaxSwingJTextArea.getCaret()).setUpdatePolicy(2);
    localJScrollPane.setViewportView(this.jdField_field_263_of_type_JavaxSwingJTextArea);
    localJScrollPane.setAutoscrolls(true);
    if ((!jdField_field_263_of_type_Boolean) && (paramclass_371 == null)) {
      throw new AssertionError();
    }
    paramclass_371.e2().add(this);
  }
  
  public final void a(String paramString)
  {
    this.jdField_field_263_of_type_JavaxSwingJTextArea.append(paramString + "\n");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1295
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */