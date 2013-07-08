import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public final class class_1316
  extends JPanel
{
  private JSpinner jdField_field_1494_of_type_JavaxSwingJSpinner;
  private JSpinner field_1495;
  private JSpinner field_1496;
  private JPanel jdField_field_1494_of_type_JavaxSwingJPanel;
  
  public class_1316()
  {
    setPreferredSize(new Dimension(267, 44));
    setMaximumSize(new Dimension(700, 80));
    setMinimumSize(new Dimension(180, 50));
    (localObject = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 1.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject);
    this.jdField_field_1494_of_type_JavaxSwingJPanel = new JPanel();
    this.jdField_field_1494_of_type_JavaxSwingJPanel.setPreferredSize(new Dimension(300, 50));
    (localObject = new GridBagConstraints()).weightx = 1.0D;
    ((GridBagConstraints)localObject).fill = 2;
    ((GridBagConstraints)localObject).anchor = 18;
    ((GridBagConstraints)localObject).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 0;
    add(this.jdField_field_1494_of_type_JavaxSwingJPanel, localObject);
    (localObject = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0, 0 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 1.0D, 0.0D, 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 0.0D, 0.0D, 1.0D };
    this.jdField_field_1494_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject);
    Object localObject = new JLabel("X");
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).weighty = 1.0D;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    this.jdField_field_1494_of_type_JavaxSwingJPanel.add((Component)localObject, localGridBagConstraints);
    localObject = new JLabel("Y");
    (localGridBagConstraints = new GridBagConstraints()).weighty = 1.0D;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 5);
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 0;
    this.jdField_field_1494_of_type_JavaxSwingJPanel.add((Component)localObject, localGridBagConstraints);
    localObject = new JLabel("Z");
    (localGridBagConstraints = new GridBagConstraints()).weighty = 1.0D;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 2;
    localGridBagConstraints.gridy = 0;
    this.jdField_field_1494_of_type_JavaxSwingJPanel.add((Component)localObject, localGridBagConstraints);
    this.field_1496 = new JSpinner();
    (localObject = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject).weightx = 1.0D;
    ((GridBagConstraints)localObject).fill = 2;
    ((GridBagConstraints)localObject).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 1;
    this.jdField_field_1494_of_type_JavaxSwingJPanel.add(this.field_1496, localObject);
    this.field_1495 = new JSpinner();
    (localObject = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject).weightx = 1.0D;
    ((GridBagConstraints)localObject).fill = 2;
    ((GridBagConstraints)localObject).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject).gridx = 1;
    ((GridBagConstraints)localObject).gridy = 1;
    this.jdField_field_1494_of_type_JavaxSwingJPanel.add(this.field_1495, localObject);
    this.jdField_field_1494_of_type_JavaxSwingJSpinner = new JSpinner();
    (localObject = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject).weighty = 1.0D;
    ((GridBagConstraints)localObject).weightx = 1.0D;
    ((GridBagConstraints)localObject).fill = 2;
    ((GridBagConstraints)localObject).gridx = 2;
    ((GridBagConstraints)localObject).gridy = 1;
    this.jdField_field_1494_of_type_JavaxSwingJPanel.add(this.jdField_field_1494_of_type_JavaxSwingJSpinner, localObject);
  }
  
  public final class_48 a()
  {
    class_48 localclass_48;
    (localclass_48 = new class_48()).field_475 = ((Integer)this.field_1496.getValue()).intValue();
    localclass_48.field_476 = ((Integer)this.field_1495.getValue()).intValue();
    localclass_48.field_477 = ((Integer)this.jdField_field_1494_of_type_JavaxSwingJSpinner.getValue()).intValue();
    return localclass_48;
  }
  
  public final void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    this.field_1496.setEnabled(paramBoolean);
    this.field_1495.setEnabled(paramBoolean);
    this.jdField_field_1494_of_type_JavaxSwingJSpinner.setEnabled(paramBoolean);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1316
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */