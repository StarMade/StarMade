import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class class_1303
  extends JPanel
{
  private class_371 jdField_field_1479_of_type_Class_371;
  private JTextField jdField_field_1479_of_type_JavaxSwingJTextField;
  
  public class_1303(class_371 paramclass_371)
  {
    this.jdField_field_1479_of_type_Class_371 = paramclass_371;
    (paramclass_371 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    paramclass_371.rowHeights = new int[] { 0, 0 };
    paramclass_371.columnWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
    paramclass_371.rowWeights = new double[] { 0.0D, 4.9E-324D };
    setLayout(paramclass_371);
    this.jdField_field_1479_of_type_JavaxSwingJTextField = new JTextField();
    this.jdField_field_1479_of_type_JavaxSwingJTextField.addKeyListener(new class_1301(this));
    (paramclass_371 = new GridBagConstraints()).weightx = 1.0D;
    paramclass_371.anchor = 17;
    paramclass_371.insets = new Insets(0, 0, 0, 5);
    paramclass_371.fill = 2;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 0;
    add(this.jdField_field_1479_of_type_JavaxSwingJTextField, paramclass_371);
    this.jdField_field_1479_of_type_JavaxSwingJTextField.setColumns(10);
    (paramclass_371 = new JButton("Send")).addActionListener(new class_1293(this));
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).anchor = 13;
    localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 0;
    add(paramclass_371, localGridBagConstraints);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1303
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */