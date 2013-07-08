import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public class class_550
  extends JDialog
{
  private static final long serialVersionUID = -1325095027616126151L;
  private final JPanel jdField_field_855_of_type_JavaxSwingJPanel = new JPanel();
  private short jdField_field_855_of_type_Short;
  private JLabel jdField_field_855_of_type_JavaxSwingJLabel;
  private JSpinner jdField_field_855_of_type_JavaxSwingJSpinner;
  private JLabel field_856;
  
  public class_550(JFrame paramJFrame, class_680 paramclass_680)
  {
    super(paramJFrame, true);
    setTitle("Block Level Editor");
    setBounds(100, 100, 510, 184);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_855_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_855_of_type_JavaxSwingJPanel, "Center");
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
    this.jdField_field_855_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    Object localObject1 = new JLabel("Base Element");
    Object localObject2;
    (localObject2 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_855_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    this.jdField_field_855_of_type_Short = -1;
    this.jdField_field_855_of_type_JavaxSwingJLabel = new JLabel(this.jdField_field_855_of_type_Short > 0 ? ElementKeyMap.getInfo(this.jdField_field_855_of_type_Short).toString() : "undefined");
    (localObject1 = new GridBagConstraints()).weightx = 1.0D;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 0;
    this.jdField_field_855_of_type_JavaxSwingJPanel.add(this.jdField_field_855_of_type_JavaxSwingJLabel, localObject1);
    localObject1 = new JButton("Choose");
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).anchor = 13;
    ((GridBagConstraints)localObject2).gridx = 2;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_855_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    ((JButton)localObject1).addActionListener(new class_556(this, paramJFrame));
    System.err.println("FAC: " + null);
    this.field_856 = new JLabel("Count 0");
    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 1;
    this.jdField_field_855_of_type_JavaxSwingJPanel.add(this.field_856, localObject1);
    this.jdField_field_855_of_type_JavaxSwingJSpinner = new JSpinner();
    this.jdField_field_855_of_type_JavaxSwingJSpinner.setValue(Integer.valueOf(1));
    (localObject1 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject1).weightx = 11.0D;
    ((GridBagConstraints)localObject1).gridwidth = 2;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 1;
    this.jdField_field_855_of_type_JavaxSwingJPanel.add(this.jdField_field_855_of_type_JavaxSwingJSpinner, localObject1);
    (localObject2 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject2).weightx = 11.0D;
    ((GridBagConstraints)localObject2).gridwidth = 2;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject2).gridx = 2;
    ((GridBagConstraints)localObject2).gridy = 1;
    this.jdField_field_855_of_type_JavaxSwingJPanel.add(this.jdField_field_855_of_type_JavaxSwingJSpinner, localObject2);
    this.jdField_field_855_of_type_JavaxSwingJSpinner.addChangeListener(new class_544(this));
    (localObject1 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject1, "South");
    (localObject2 = new JButton("OK")).setActionCommand("OK");
    ((JPanel)localObject1).add((Component)localObject2);
    getRootPane().setDefaultButton((JButton)localObject2);
    ((JButton)localObject2).addActionListener(new class_542(this, paramclass_680));
    (localObject2 = new JButton("Cancel")).setActionCommand("Cancel");
    ((JPanel)localObject1).add((Component)localObject2);
    ((JButton)localObject2).addActionListener(new class_548(this));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_550
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */