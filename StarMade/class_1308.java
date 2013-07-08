import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public final class class_1308
  extends JDialog
{
  private final JPanel jdField_field_1484_of_type_JavaxSwingJPanel = new JPanel();
  private JTextField jdField_field_1484_of_type_JavaxSwingJTextField;
  private class_1316 jdField_field_1484_of_type_Class_1316;
  
  public class_1308(class_371 paramclass_371)
  {
    super(class_531.field_835, true);
    setDefaultCloseOperation(2);
    setTitle("Change Sector");
    setBounds(100, 100, 421, 185);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_1484_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_1484_of_type_JavaxSwingJPanel, "Center");
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    this.jdField_field_1484_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    Object localObject1 = new JLabel("Enter Player Name");
    Object localObject2;
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject2).anchor = 13;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_1484_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    this.jdField_field_1484_of_type_JavaxSwingJTextField = new JTextField();
    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject1).fill = 2;
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 0;
    this.jdField_field_1484_of_type_JavaxSwingJPanel.add(this.jdField_field_1484_of_type_JavaxSwingJTextField, localObject1);
    this.jdField_field_1484_of_type_JavaxSwingJTextField.setColumns(10);
    (localObject1 = new JPanel()).setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sector", 4, 2, null, new Color(0, 0, 0)));
    (localObject2 = new GridBagConstraints()).fill = 1;
    ((GridBagConstraints)localObject2).gridwidth = 2;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 1;
    this.jdField_field_1484_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject2).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
    this.jdField_field_1484_of_type_Class_1316 = new class_1316();
    this.jdField_field_1484_of_type_Class_1316.setMinimumSize(new Dimension(400, 50));
    this.jdField_field_1484_of_type_Class_1316.setPreferredSize(new Dimension(400, 50));
    (localObject2 = new GridBagConstraints()).weightx = 1.0D;
    ((GridBagConstraints)localObject2).anchor = 18;
    ((GridBagConstraints)localObject2).fill = 2;
    ((GridBagConstraints)localObject2).gridwidth = 2;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    ((JPanel)localObject1).add(this.jdField_field_1484_of_type_Class_1316, localObject2);
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 4.9E-324D };
    this.jdField_field_1484_of_type_Class_1316.setLayout((LayoutManager)localObject1);
    (localObject1 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject1, "South");
    (localObject2 = new JButton("OK")).addActionListener(new class_1306(this, paramclass_371));
    ((JButton)localObject2).setActionCommand("OK");
    ((JPanel)localObject1).add((Component)localObject2);
    getRootPane().setDefaultButton((JButton)localObject2);
    (localObject2 = new JButton("Cancel")).addActionListener(new class_1312(this));
    ((JButton)localObject2).setActionCommand("Cancel");
    ((JPanel)localObject1).add((Component)localObject2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1308
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */