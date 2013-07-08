import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public final class class_1330
  extends JDialog
{
  private static final long serialVersionUID = 7116287916594864261L;
  private final JPanel jdField_field_1512_of_type_JavaxSwingJPanel = new JPanel();
  private JTextField jdField_field_1512_of_type_JavaxSwingJTextField;
  private JPasswordField jdField_field_1512_of_type_JavaxSwingJPasswordField;
  private JCheckBox jdField_field_1512_of_type_JavaxSwingJCheckBox;
  
  public class_1330()
  {
    setTitle("Login to Star-Made.org");
    setBounds(100, 100, 470, 275);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_1512_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_1512_of_type_JavaxSwingJPanel, "Center");
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 20, 0, 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
    this.jdField_field_1512_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    Object localObject1 = new JLabel("Please enter your www.Star-Made.org credentials");
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_1512_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    localObject1 = new JLabel("If you don't have an Account yet, please go to www.star-made.org to create one");
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 20, 0);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 1;
    this.jdField_field_1512_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    (localObject1 = new JPanel()).setBorder(new EtchedBorder(1, null, null));
    (localObject2 = new GridBagConstraints()).weightx = 1.0D;
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 2;
    this.jdField_field_1512_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 0, 80, 0, 50, 0 };
    ((GridBagLayout)localObject2).rowHeights = new int[] { 20, 0 };
    ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
    Object localObject2 = new JLabel("User Name");
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).anchor = 13;
    localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
    this.jdField_field_1512_of_type_JavaxSwingJTextField = new JTextField();
    (localObject2 = new GridBagConstraints()).weightx = 0.5D;
    ((GridBagConstraints)localObject2).anchor = 11;
    ((GridBagConstraints)localObject2).fill = 2;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject2).gridx = 1;
    ((GridBagConstraints)localObject2).gridy = 0;
    ((JPanel)localObject1).add(this.jdField_field_1512_of_type_JavaxSwingJTextField, localObject2);
    this.jdField_field_1512_of_type_JavaxSwingJTextField.setColumns(13);
    localObject2 = new JLabel("Password");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 13;
    localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
    localGridBagConstraints.gridx = 2;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
    this.jdField_field_1512_of_type_JavaxSwingJPasswordField = new JPasswordField();
    (localObject2 = new GridBagConstraints()).weightx = 1.0D;
    ((GridBagConstraints)localObject2).anchor = 11;
    ((GridBagConstraints)localObject2).fill = 2;
    ((GridBagConstraints)localObject2).gridx = 3;
    ((GridBagConstraints)localObject2).gridy = 0;
    ((JPanel)localObject1).add(this.jdField_field_1512_of_type_JavaxSwingJPasswordField, localObject2);
    this.jdField_field_1512_of_type_JavaxSwingJCheckBox = new JCheckBox("Save Login (encrypted)");
    (localObject1 = new GridBagConstraints()).anchor = 13;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 3;
    this.jdField_field_1512_of_type_JavaxSwingJPanel.add(this.jdField_field_1512_of_type_JavaxSwingJCheckBox, localObject1);
    (localObject1 = new JButton("Delete Saved Login")).addActionListener(new class_1332());
    ((JButton)localObject1).setEnabled(class_1272.a());
    ((JButton)localObject1).setHorizontalAlignment(4);
    (localObject2 = new GridBagConstraints()).anchor = 13;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 4;
    this.jdField_field_1512_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    (localObject1 = new JLabel("Note: this is optional!")).setForeground(new Color(139, 0, 0));
    ((JLabel)localObject1).setFont(new Font("Tahoma", 1, 12));
    (localObject2 = new GridBagConstraints()).insets = new Insets(15, 0, 5, 0);
    ((GridBagConstraints)localObject2).anchor = 17;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 5;
    this.jdField_field_1512_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    localObject1 = new JLabel("You don't have to be logged on to play single, or multiplayer");
    (localObject2 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 6;
    this.jdField_field_1512_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    (localObject1 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject1, "South");
    (localObject2 = new JButton("OK")).addActionListener(new class_1334(this));
    ((JButton)localObject2).setActionCommand("OK");
    ((JPanel)localObject1).add((Component)localObject2);
    getRootPane().setDefaultButton((JButton)localObject2);
    (localObject2 = new JButton("Cancel")).addActionListener(new class_524(this));
    ((JButton)localObject2).setActionCommand("Cancel");
    ((JPanel)localObject1).add((Component)localObject2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1330
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */