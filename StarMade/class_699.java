import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.schema.game.common.Starter;

public class class_699
  extends JPanel
{
  private static final long serialVersionUID = -438451137505029846L;
  private JTable jdField_field_965_of_type_JavaxSwingJTable;
  private class_491 jdField_field_965_of_type_Class_491;
  private JFrame jdField_field_965_of_type_JavaxSwingJFrame;
  private JFileChooser jdField_field_965_of_type_JavaxSwingJFileChooser;
  
  public class_699(JFrame paramJFrame, class_697 paramclass_697)
  {
    this.jdField_field_965_of_type_JavaxSwingJFrame = paramJFrame;
    (paramJFrame = new GridBagLayout()).rowHeights = new int[] { 50, 0, 0 };
    paramJFrame.columnWeights = new double[] { 1.0D };
    paramJFrame.rowWeights = new double[] { 1.0D, 1.0D, 1.0D };
    setLayout(paramJFrame);
    paramJFrame = new JScrollPane();
    (localObject = new GridBagConstraints()).weighty = 10.0D;
    ((GridBagConstraints)localObject).weightx = 1.0D;
    ((GridBagConstraints)localObject).fill = 1;
    ((GridBagConstraints)localObject).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 0;
    add(paramJFrame, localObject);
    this.jdField_field_965_of_type_Class_491 = new class_491();
    this.jdField_field_965_of_type_JavaxSwingJTable = new JTable(this.jdField_field_965_of_type_Class_491);
    this.jdField_field_965_of_type_JavaxSwingJTable.setSelectionMode(0);
    this.jdField_field_965_of_type_JavaxSwingJTable.setMaximumSize(new Dimension(2147483647, 2147483647));
    this.jdField_field_965_of_type_JavaxSwingJTable.setSize(new Dimension(0, 300));
    this.jdField_field_965_of_type_JavaxSwingJTable.setAutoResizeMode(4);
    this.jdField_field_965_of_type_JavaxSwingJTable.setFillsViewportHeight(true);
    this.jdField_field_965_of_type_JavaxSwingJTable.setPreferredScrollableViewportSize(new Dimension(0, 170));
    this.jdField_field_965_of_type_JavaxSwingJTable.setMinimumSize(new Dimension(100, 100));
    paramJFrame.setViewportView(this.jdField_field_965_of_type_JavaxSwingJTable);
    paramJFrame = new JPanel();
    (localObject = new GridBagConstraints()).weighty = 2.0D;
    ((GridBagConstraints)localObject).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject).fill = 1;
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 1;
    add(paramJFrame, localObject);
    (localObject = new JButton("Remove")).addActionListener(new class_701(this));
    JButton localJButton;
    (localJButton = new JButton("Import")).addActionListener(new class_501(this));
    paramJFrame.add(localJButton);
    (localJButton = new JButton("Export")).addActionListener(new class_499(this));
    paramJFrame.add(localJButton);
    paramJFrame.add((Component)localObject);
    (localObject = new JButton("Upload")).addActionListener(new class_497(this));
    paramJFrame.add((Component)localObject);
    if (Starter.field_2076 == null)
    {
      ((JButton)localObject).setEnabled(false);
      ((JButton)localObject).setText("Upload (Login needed)");
    }
    paramJFrame = new JPanel();
    (localObject = new GridBagConstraints()).fill = 1;
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 2;
    add(paramJFrame, localObject);
    Object localObject = new GridBagLayout();
    paramJFrame.setLayout((LayoutManager)localObject);
    (localObject = new JButton("Exit")).addActionListener(new class_495(paramclass_697));
    ((JButton)localObject).setHorizontalAlignment(4);
    (paramclass_697 = new GridBagConstraints()).weightx = 10.0D;
    paramclass_697.insets = new Insets(0, 0, 0, 5);
    paramclass_697.anchor = 12;
    paramclass_697.gridx = 0;
    paramclass_697.gridy = 0;
    paramJFrame.add((Component)localObject, paramclass_697);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_699
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */