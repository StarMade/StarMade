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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.schema.game.common.Starter;

public final class class_1347
  extends JDialog
{
  private static final long serialVersionUID = 2581659058321193133L;
  private final JPanel jdField_field_1528_of_type_JavaxSwingJPanel = new JPanel();
  private JTextField jdField_field_1528_of_type_JavaxSwingJTextField;
  private JFileChooser jdField_field_1528_of_type_JavaxSwingJFileChooser;
  
  public class_1347(JFrame paramJFrame)
  {
    super(paramJFrame, true);
    setBounds(100, 100, 444, 135);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_1528_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_1528_of_type_JavaxSwingJPanel, "Center");
    Object localObject;
    (localObject = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
    this.jdField_field_1528_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject);
    this.jdField_field_1528_of_type_JavaxSwingJTextField = new JTextField();
    (localObject = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject).fill = 2;
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 0;
    this.jdField_field_1528_of_type_JavaxSwingJPanel.add(this.jdField_field_1528_of_type_JavaxSwingJTextField, localObject);
    this.jdField_field_1528_of_type_JavaxSwingJTextField.setColumns(10);
    if (Starter.field_2076 != null) {
      this.jdField_field_1528_of_type_JavaxSwingJTextField.setText(Starter.field_2076);
    }
    (localObject = new JButton("browse")).addActionListener(new class_1349(this, paramJFrame));
    (paramJFrame = new GridBagConstraints()).anchor = 13;
    paramJFrame.gridx = 0;
    paramJFrame.gridy = 1;
    this.jdField_field_1528_of_type_JavaxSwingJPanel.add((Component)localObject, paramJFrame);
    (localObject = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject, "South");
    (paramJFrame = new JButton("OK")).addActionListener(new class_1335(this));
    paramJFrame.setActionCommand("OK");
    ((JPanel)localObject).add(paramJFrame);
    getRootPane().setDefaultButton(paramJFrame);
    (paramJFrame = new JButton("Cancel")).addActionListener(new class_1337(this));
    paramJFrame.setActionCommand("Cancel");
    ((JPanel)localObject).add(paramJFrame);
    this.jdField_field_1528_of_type_JavaxSwingJTextField.setText(Starter.field_2076);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1347
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */