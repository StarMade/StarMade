import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public final class class_1271
  extends JDialog
{
  private final JPanel jdField_field_1456_of_type_JavaxSwingJPanel = new JPanel();
  private class_773 jdField_field_1456_of_type_Class_773;
  private class_371 jdField_field_1456_of_type_Class_371;
  private class_625 jdField_field_1456_of_type_Class_625;
  private JTextField[] jdField_field_1456_of_type_ArrayOfJavaxSwingJTextField;
  
  public class_1271(class_371 paramclass_371, class_773 paramclass_773)
  {
    super(class_531.field_835, true);
    setDefaultCloseOperation(2);
    this.jdField_field_1456_of_type_Class_625 = new class_625();
    this.jdField_field_1456_of_type_Class_625.field_136 = paramclass_773.a3();
    this.jdField_field_1456_of_type_ArrayOfJavaxSwingJTextField = new JTextField[5];
    this.jdField_field_1456_of_type_Class_773 = paramclass_773;
    this.jdField_field_1456_of_type_Class_371 = paramclass_371;
    setBounds(100, 100, 706, 262);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_1456_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_1456_of_type_JavaxSwingJPanel, "Center");
    (paramclass_371 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramclass_371.rowHeights = new int[] { 0, 0 };
    paramclass_371.columnWeights = new double[] { 1.0D, 4.9E-324D };
    paramclass_371.rowWeights = new double[] { 1.0D, 4.9E-324D };
    this.jdField_field_1456_of_type_JavaxSwingJPanel.setLayout(paramclass_371);
    paramclass_371 = new JPanel();
    (paramclass_773 = new GridBagConstraints()).anchor = 18;
    paramclass_773.fill = 2;
    paramclass_773.gridx = 0;
    paramclass_773.gridy = 0;
    this.jdField_field_1456_of_type_JavaxSwingJPanel.add(paramclass_371, paramclass_773);
    (paramclass_773 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
    paramclass_773.columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 4.9E-324D };
    paramclass_773.rowHeights = new int[6];
    paramclass_773.rowWeights = new double[6];
    paramclass_773.rowWeights[5] = 4.9E-324D;
    paramclass_371.setLayout(paramclass_773);
    paramclass_773 = new JLabel("Role");
    Object localObject1;
    (localObject1 = new GridBagConstraints()).insets = new Insets(5, 5, 5, 5);
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 0;
    paramclass_371.add(paramclass_773, localObject1);
    (paramclass_773 = new JLabel("Permission Edit")).setHorizontalAlignment(2);
    paramclass_773.setHorizontalTextPosition(2);
    paramclass_773.setBackground(Color.LIGHT_GRAY);
    (localObject1 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 5, 5, 5);
    ((GridBagConstraints)localObject1).gridx = 2;
    ((GridBagConstraints)localObject1).gridy = 0;
    paramclass_371.add(paramclass_773, localObject1);
    (paramclass_773 = new JLabel("Kick Permission")).setBackground(Color.LIGHT_GRAY);
    (localObject1 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 5, 5, 5);
    ((GridBagConstraints)localObject1).gridx = 3;
    ((GridBagConstraints)localObject1).gridy = 0;
    paramclass_371.add(paramclass_773, localObject1);
    (paramclass_773 = new JLabel("Invite Permission")).setBackground(Color.LIGHT_GRAY);
    (localObject1 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 5, 5, 5);
    ((GridBagConstraints)localObject1).gridx = 4;
    ((GridBagConstraints)localObject1).gridy = 0;
    paramclass_371.add(paramclass_773, localObject1);
    (paramclass_773 = new JLabel("Edit Permission")).setBackground(Color.LIGHT_GRAY);
    (localObject1 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 5, 5, 0);
    ((GridBagConstraints)localObject1).gridx = 5;
    ((GridBagConstraints)localObject1).gridy = 0;
    paramclass_371.add(paramclass_773, localObject1);
    for (paramclass_773 = 0; paramclass_773 < 5; paramclass_773++)
    {
      class_371 localclass_371 = paramclass_371;
      int i = paramclass_773;
      localObject1 = this;
      Object localObject2 = new JLabel("#" + (i + 1) + ":");
      Object localObject3;
      (localObject3 = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
      ((GridBagConstraints)localObject3).anchor = 13;
      ((GridBagConstraints)localObject3).gridx = 0;
      ((GridBagConstraints)localObject3).gridy = (i + 1);
      localclass_371.add((Component)localObject2, localObject3);
      ((class_1271)localObject1).jdField_field_1456_of_type_ArrayOfJavaxSwingJTextField[i] = new JTextField();
      localObject1.jdField_field_1456_of_type_ArrayOfJavaxSwingJTextField[i].setPreferredSize(new Dimension(90, 20));
      (localObject2 = new GridBagConstraints()).weightx = 1.0D;
      ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
      ((GridBagConstraints)localObject2).anchor = 17;
      ((GridBagConstraints)localObject2).gridx = 1;
      ((GridBagConstraints)localObject2).gridy = (i + 1);
      localclass_371.add(localObject1.jdField_field_1456_of_type_ArrayOfJavaxSwingJTextField[i], localObject2);
      localObject1.jdField_field_1456_of_type_ArrayOfJavaxSwingJTextField[i].setColumns(20);
      localObject1.jdField_field_1456_of_type_ArrayOfJavaxSwingJTextField[i].setText(localObject1.jdField_field_1456_of_type_Class_773.a180().a26()[i]);
      (localObject3 = new JCheckBox("")).setBackground(Color.LIGHT_GRAY);
      ((JCheckBox)localObject3).setActionCommand("");
      (localObject2 = new GridBagConstraints()).fill = 1;
      ((GridBagConstraints)localObject2).anchor = 17;
      ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
      ((GridBagConstraints)localObject2).gridx = 2;
      ((GridBagConstraints)localObject2).gridy = (i + 1);
      localclass_371.add((Component)localObject3, localObject2);
      ((JCheckBox)localObject3).setSelected(((class_1271)localObject1).jdField_field_1456_of_type_Class_773.a180().d(i));
      (localObject2 = new JCheckBox("")).setBackground(SystemColor.controlHighlight);
      Object localObject4;
      (localObject4 = new GridBagConstraints()).fill = 1;
      ((GridBagConstraints)localObject4).anchor = 17;
      ((GridBagConstraints)localObject4).insets = new Insets(0, 0, 0, 5);
      ((GridBagConstraints)localObject4).gridx = 3;
      ((GridBagConstraints)localObject4).gridy = (i + 1);
      localclass_371.add((Component)localObject2, localObject4);
      ((JCheckBox)localObject2).setSelected(((class_1271)localObject1).jdField_field_1456_of_type_Class_773.a180().b3(i));
      (localObject4 = new JCheckBox("")).setBackground(Color.LIGHT_GRAY);
      Object localObject5;
      (localObject5 = new GridBagConstraints()).fill = 1;
      ((GridBagConstraints)localObject5).anchor = 17;
      ((GridBagConstraints)localObject5).insets = new Insets(0, 0, 0, 5);
      ((GridBagConstraints)localObject5).gridx = 4;
      ((GridBagConstraints)localObject5).gridy = (i + 1);
      localclass_371.add((Component)localObject4, localObject5);
      ((JCheckBox)localObject4).setSelected(((class_1271)localObject1).jdField_field_1456_of_type_Class_773.a180().c(i));
      (localObject5 = new JCheckBox("")).setBackground(SystemColor.controlHighlight);
      GridBagConstraints localGridBagConstraints;
      (localGridBagConstraints = new GridBagConstraints()).fill = 1;
      localGridBagConstraints.anchor = 17;
      localGridBagConstraints.gridx = 5;
      localGridBagConstraints.gridy = (i + 1);
      localclass_371.add((Component)localObject5, localGridBagConstraints);
      ((JCheckBox)localObject5).setSelected(((class_1271)localObject1).jdField_field_1456_of_type_Class_773.a180().a18(i));
      localObject1 = new class_1265((class_1271)localObject1, (JCheckBox)localObject5, i, (JCheckBox)localObject4, (JCheckBox)localObject2, (JCheckBox)localObject3);
      ((JCheckBox)localObject5).addActionListener((ActionListener)localObject1);
      ((JCheckBox)localObject4).addActionListener((ActionListener)localObject1);
      ((JCheckBox)localObject2).addActionListener((ActionListener)localObject1);
      ((JCheckBox)localObject3).addActionListener((ActionListener)localObject1);
    }
    (paramclass_371 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add(paramclass_371, "South");
    (paramclass_773 = new JButton("OK")).addActionListener(new class_1269(this));
    paramclass_773.setActionCommand("OK");
    paramclass_371.add(paramclass_773);
    getRootPane().setDefaultButton(paramclass_773);
    (paramclass_773 = new JButton("Cancel")).addActionListener(new class_1267(this));
    paramclass_773.setActionCommand("Cancel");
    paramclass_371.add(paramclass_773);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1271
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */