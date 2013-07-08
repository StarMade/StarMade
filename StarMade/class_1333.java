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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public final class class_1333
  extends JDialog
{
  private final JPanel jdField_field_1514_of_type_JavaxSwingJPanel = new JPanel();
  private JTextField jdField_field_1514_of_type_JavaxSwingJTextField;
  
  public class_1333(class_371 paramclass_371, class_773 paramclass_773)
  {
    super(class_531.field_835, true);
    setDefaultCloseOperation(2);
    setTitle("Add Member to Faction " + paramclass_773.a());
    setBounds(100, 100, 449, 106);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_1514_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_1514_of_type_JavaxSwingJPanel, "Center");
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
    this.jdField_field_1514_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    Object localObject1 = new JLabel("Enter Player Name");
    Object localObject2;
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject2).anchor = 13;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_1514_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    this.jdField_field_1514_of_type_JavaxSwingJTextField = new JTextField();
    (localObject1 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 0;
    this.jdField_field_1514_of_type_JavaxSwingJPanel.add(this.jdField_field_1514_of_type_JavaxSwingJTextField, localObject1);
    this.jdField_field_1514_of_type_JavaxSwingJTextField.setColumns(10);
    (localObject1 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject1, "South");
    (localObject2 = new JButton("OK")).addActionListener(new class_1276(this, paramclass_371, paramclass_773));
    ((JButton)localObject2).setActionCommand("OK");
    ((JPanel)localObject1).add((Component)localObject2);
    getRootPane().setDefaultButton((JButton)localObject2);
    (localObject2 = new JButton("Cancel")).addActionListener(new class_1273(this));
    ((JButton)localObject2).setActionCommand("Cancel");
    ((JPanel)localObject1).add((Component)localObject2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1333
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */