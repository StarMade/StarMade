import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public final class class_545
  extends JDialog
{
  private final JPanel jdField_field_847_of_type_JavaxSwingJPanel = new JPanel();
  private JTextField jdField_field_847_of_type_JavaxSwingJTextField;
  private JTextField jdField_field_848_of_type_JavaxSwingJTextField;
  private JSpinner jdField_field_847_of_type_JavaxSwingJSpinner;
  private JTextField jdField_field_849_of_type_JavaxSwingJTextField;
  private JCheckBox jdField_field_847_of_type_JavaxSwingJCheckBox;
  private JCheckBox jdField_field_848_of_type_JavaxSwingJCheckBox;
  private JCheckBox jdField_field_849_of_type_JavaxSwingJCheckBox;
  private JCheckBox field_850;
  private final class_781 jdField_field_847_of_type_Class_781;
  private final class_371 jdField_field_847_of_type_Class_371;
  
  public class_545(JFrame paramJFrame, class_781 paramclass_781, class_371 paramclass_371, class_541 paramclass_541, JTable paramJTable)
  {
    super(paramJFrame, true);
    new ButtonGroup();
    this.jdField_field_847_of_type_Class_371 = paramclass_371;
    this.jdField_field_847_of_type_Class_781 = paramclass_781;
    setTitle("Edit Catalog Entry");
    setDefaultCloseOperation(2);
    setBounds(400, 400, 550, 500);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_847_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_847_of_type_JavaxSwingJPanel, "Center");
    (paramJFrame = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramJFrame.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
    paramJFrame.columnWeights = new double[] { 1.0D, 4.9E-324D };
    paramJFrame.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 4.9E-324D };
    this.jdField_field_847_of_type_JavaxSwingJPanel.setLayout(paramJFrame);
    (paramJFrame = new JPanel()).setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Name", 4, 2, null, new Color(0, 0, 0)));
    (paramclass_371 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    paramclass_371.fill = 1;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 0;
    this.jdField_field_847_of_type_JavaxSwingJPanel.add(paramJFrame, paramclass_371);
    (paramclass_371 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramclass_371.rowHeights = new int[] { 0, 0 };
    paramclass_371.columnWeights = new double[] { 1.0D, 4.9E-324D };
    paramclass_371.rowWeights = new double[] { 0.0D, 4.9E-324D };
    paramJFrame.setLayout(paramclass_371);
    this.jdField_field_847_of_type_JavaxSwingJTextField = new JTextField();
    (paramclass_371 = new GridBagConstraints()).fill = 2;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 0;
    paramJFrame.add(this.jdField_field_847_of_type_JavaxSwingJTextField, paramclass_371);
    this.jdField_field_847_of_type_JavaxSwingJTextField.setColumns(10);
    (paramJFrame = new JPanel()).setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Owner", 4, 2, null, new Color(0, 0, 0)));
    (paramclass_371 = new GridBagConstraints()).fill = 1;
    paramclass_371.insets = new Insets(0, 0, 5, 0);
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 1;
    this.jdField_field_847_of_type_JavaxSwingJPanel.add(paramJFrame, paramclass_371);
    (paramclass_371 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramclass_371.rowHeights = new int[] { 0, 0 };
    paramclass_371.columnWeights = new double[] { 1.0D, 4.9E-324D };
    paramclass_371.rowWeights = new double[] { 0.0D, 4.9E-324D };
    paramJFrame.setLayout(paramclass_371);
    this.jdField_field_848_of_type_JavaxSwingJTextField = new JTextField();
    (paramclass_371 = new GridBagConstraints()).fill = 2;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 0;
    paramJFrame.add(this.jdField_field_848_of_type_JavaxSwingJTextField, paramclass_371);
    this.jdField_field_848_of_type_JavaxSwingJTextField.setColumns(10);
    (paramJFrame = new JPanel()).setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Price", 4, 2, null, new Color(0, 0, 0)));
    (paramclass_371 = new GridBagConstraints()).fill = 1;
    paramclass_371.insets = new Insets(0, 0, 5, 0);
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 2;
    this.jdField_field_847_of_type_JavaxSwingJPanel.add(paramJFrame, paramclass_371);
    (paramclass_371 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramclass_371.rowHeights = new int[] { 0, 0 };
    paramclass_371.columnWeights = new double[] { 1.0D, 4.9E-324D };
    paramclass_371.rowWeights = new double[] { 0.0D, 4.9E-324D };
    paramJFrame.setLayout(paramclass_371);
    this.jdField_field_847_of_type_JavaxSwingJSpinner = new JSpinner();
    (paramclass_371 = new GridBagConstraints()).fill = 2;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 0;
    paramJFrame.add(this.jdField_field_847_of_type_JavaxSwingJSpinner, paramclass_371);
    (paramJFrame = new JPanel()).setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Description", 4, 2, null, new Color(0, 0, 0)));
    (paramclass_371 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    paramclass_371.fill = 1;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 3;
    this.jdField_field_847_of_type_JavaxSwingJPanel.add(paramJFrame, paramclass_371);
    (paramclass_371 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramclass_371.rowHeights = new int[] { 0, 0 };
    paramclass_371.columnWeights = new double[] { 1.0D, 4.9E-324D };
    paramclass_371.rowWeights = new double[] { 0.0D, 4.9E-324D };
    paramJFrame.setLayout(paramclass_371);
    this.jdField_field_849_of_type_JavaxSwingJTextField = new JTextField();
    (paramclass_371 = new GridBagConstraints()).fill = 2;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 0;
    paramJFrame.add(this.jdField_field_849_of_type_JavaxSwingJTextField, paramclass_371);
    this.jdField_field_849_of_type_JavaxSwingJTextField.setColumns(10);
    (paramJFrame = new JPanel()).setBorder(new TitledBorder(null, "Permissions", 4, 2, null, null));
    (paramclass_371 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    paramclass_371.fill = 1;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 4;
    this.jdField_field_847_of_type_JavaxSwingJPanel.add(paramJFrame, paramclass_371);
    (paramclass_371 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramclass_371.rowHeights = new int[] { 0, 0, 0, 0, 0 };
    paramclass_371.columnWeights = new double[] { 0.0D, 4.9E-324D };
    paramclass_371.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    paramJFrame.setLayout(paramclass_371);
    this.jdField_field_847_of_type_JavaxSwingJCheckBox = new JCheckBox("Spawnable from hombases");
    (paramclass_371 = new GridBagConstraints()).anchor = 17;
    paramclass_371.insets = new Insets(0, 0, 5, 0);
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 0;
    paramJFrame.add(this.jdField_field_847_of_type_JavaxSwingJCheckBox, paramclass_371);
    this.jdField_field_848_of_type_JavaxSwingJCheckBox = new JCheckBox("Spawnable by faction ");
    (paramclass_371 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    paramclass_371.anchor = 17;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 1;
    paramJFrame.add(this.jdField_field_848_of_type_JavaxSwingJCheckBox, paramclass_371);
    this.jdField_field_849_of_type_JavaxSwingJCheckBox = new JCheckBox("Spawnable by enemies");
    (paramclass_371 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    paramclass_371.anchor = 17;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 2;
    paramJFrame.add(this.jdField_field_849_of_type_JavaxSwingJCheckBox, paramclass_371);
    this.field_850 = new JCheckBox("Spawnable by others");
    (paramclass_371 = new GridBagConstraints()).anchor = 17;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 3;
    paramJFrame.add(this.field_850, paramclass_371);
    (paramJFrame = new JButton("Delete Entry")).addActionListener(new class_559(this, paramclass_781));
    (paramclass_371 = new GridBagConstraints()).gridx = 0;
    paramclass_371.gridy = 5;
    this.jdField_field_847_of_type_JavaxSwingJPanel.add(paramJFrame, paramclass_371);
    (paramJFrame = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add(paramJFrame, "South");
    (paramclass_371 = new JButton("OK")).addActionListener(new class_557(this, paramclass_541, paramJTable));
    paramclass_371.setActionCommand("OK");
    paramJFrame.add(paramclass_371);
    getRootPane().setDefaultButton(paramclass_371);
    (paramclass_371 = new JButton("Cancel")).addActionListener(new class_562(this));
    paramclass_371.setActionCommand("Cancel");
    paramJFrame.add(paramclass_371);
    paramJFrame = this;
    this.jdField_field_847_of_type_JavaxSwingJTextField.setText(paramJFrame.jdField_field_847_of_type_Class_781.field_136);
    paramJFrame.jdField_field_848_of_type_JavaxSwingJTextField.setText(paramJFrame.jdField_field_847_of_type_Class_781.jdField_field_139_of_type_JavaLangString);
    paramJFrame.jdField_field_847_of_type_JavaxSwingJSpinner.setValue(Integer.valueOf(paramJFrame.jdField_field_847_of_type_Class_781.jdField_field_139_of_type_Int));
    paramJFrame.jdField_field_849_of_type_JavaxSwingJTextField.setText(paramJFrame.jdField_field_847_of_type_Class_781.field_182);
    paramJFrame.jdField_field_849_of_type_JavaxSwingJCheckBox.setSelected(paramJFrame.jdField_field_847_of_type_Class_781.d10());
    paramJFrame.jdField_field_848_of_type_JavaxSwingJCheckBox.setSelected(paramJFrame.jdField_field_847_of_type_Class_781.b2());
    paramJFrame.field_850.setSelected(paramJFrame.jdField_field_847_of_type_Class_781.a7());
    paramJFrame.jdField_field_847_of_type_JavaxSwingJCheckBox.setSelected(paramJFrame.jdField_field_847_of_type_Class_781.c3());
  }
  
  public final void a()
  {
    class_781 localclass_781;
    (localclass_781 = new class_781(this.jdField_field_847_of_type_Class_781)).field_136 = this.jdField_field_847_of_type_JavaxSwingJTextField.getText().trim();
    localclass_781.jdField_field_139_of_type_JavaLangString = this.jdField_field_848_of_type_JavaxSwingJTextField.getText().trim();
    localclass_781.jdField_field_139_of_type_Int = ((Integer)this.jdField_field_847_of_type_JavaxSwingJSpinner.getValue()).intValue();
    localclass_781.field_182 = this.jdField_field_849_of_type_JavaxSwingJTextField.getText();
    localclass_781.a189(this.jdField_field_849_of_type_JavaxSwingJCheckBox.isSelected(), 16);
    localclass_781.a189(this.jdField_field_848_of_type_JavaxSwingJCheckBox.isSelected(), 1);
    localclass_781.a189(this.field_850.isSelected(), 2);
    localclass_781.a189(this.jdField_field_847_of_type_JavaxSwingJCheckBox.isSelected(), 4);
    this.jdField_field_847_of_type_Class_371.a53().a96(localclass_781);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_545
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */