import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public final class class_1321
  extends JDialog
{
  private static final long serialVersionUID = -490690742906601152L;
  private final JPanel jdField_field_1501_of_type_JavaxSwingJPanel = new JPanel();
  private JTextField jdField_field_1501_of_type_JavaxSwingJTextField;
  private JTextField field_1502;
  private JTextField field_1503;
  
  public class_1321(JFrame paramJFrame, class_1323 paramclass_1323, class_1315 paramclass_1315)
  {
    super(paramJFrame, true);
    setTitle("Create Connection");
    setBounds(100, 100, 320, 166);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_1501_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_1501_of_type_JavaxSwingJPanel, "Center");
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    this.jdField_field_1501_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    Object localObject1 = new JLabel("Login Name");
    Object localObject2;
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
    ((GridBagConstraints)localObject2).anchor = 17;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_1501_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    this.jdField_field_1501_of_type_JavaxSwingJTextField = new JTextField();
    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject1).fill = 2;
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 0;
    this.jdField_field_1501_of_type_JavaxSwingJPanel.add(this.jdField_field_1501_of_type_JavaxSwingJTextField, localObject1);
    this.jdField_field_1501_of_type_JavaxSwingJTextField.setColumns(10);
    localObject1 = new JLabel("Host URL");
    (localObject2 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 1;
    this.jdField_field_1501_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    this.field_1502 = new JTextField();
    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject1).fill = 2;
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 1;
    this.jdField_field_1501_of_type_JavaxSwingJPanel.add(this.field_1502, localObject1);
    this.field_1502.setColumns(10);
    (localObject1 = new JLabel("Port")).setHorizontalAlignment(2);
    (localObject2 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 0, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 2;
    this.jdField_field_1501_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    this.field_1503 = new JTextField();
    (localObject1 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 2;
    this.jdField_field_1501_of_type_JavaxSwingJPanel.add(this.field_1503, localObject1);
    this.field_1503.setColumns(10);
    this.field_1503.setText("4242");
    (localObject1 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject1, "South");
    (localObject2 = new JButton("OK")).addActionListener(new class_1327(this, paramclass_1315, paramclass_1323, paramJFrame));
    ((JButton)localObject2).setActionCommand("OK");
    ((JPanel)localObject1).add((Component)localObject2);
    getRootPane().setDefaultButton((JButton)localObject2);
    (localObject2 = new JButton("Cancel")).addActionListener(new class_1325(this));
    ((JButton)localObject2).setActionCommand("Cancel");
    ((JPanel)localObject1).add((Component)localObject2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1321
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */