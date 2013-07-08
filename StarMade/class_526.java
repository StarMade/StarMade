import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public final class class_526
  extends JPanel
{
  private static final long serialVersionUID = -6895593119848982353L;
  private JList jdField_field_831_of_type_JavaxSwingJList;
  private class_680 jdField_field_831_of_type_Class_680;
  
  public class_526(JFrame paramJFrame, ArrayList paramArrayList)
  {
    this.jdField_field_831_of_type_Class_680 = new class_680(paramArrayList);
    this.jdField_field_831_of_type_JavaxSwingJList = new JList();
    setBorder(new TitledBorder(null, "Title", 4, 2, null, null));
    (paramArrayList = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    paramArrayList.rowHeights = new int[] { 0, 0, 0 };
    paramArrayList.columnWeights = new double[] { 1.0D, 0.0D, 4.9E-324D };
    paramArrayList.rowWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    setLayout(paramArrayList);
    paramArrayList = new JButton("Add");
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    add(paramArrayList, localGridBagConstraints);
    paramArrayList.addActionListener(new class_528(this, paramJFrame));
    paramJFrame = new JButton("Delete");
    (paramArrayList = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    paramArrayList.gridx = 1;
    paramArrayList.gridy = 0;
    add(paramJFrame, paramArrayList);
    paramJFrame.addActionListener(new class_552(this));
    paramJFrame = new JScrollPane();
    (paramArrayList = new GridBagConstraints()).gridwidth = 2;
    paramArrayList.insets = new Insets(0, 0, 0, 5);
    paramArrayList.fill = 1;
    paramArrayList.gridx = 0;
    paramArrayList.gridy = 1;
    add(paramJFrame, paramArrayList);
    paramJFrame.setViewportView(this.jdField_field_831_of_type_JavaxSwingJList);
    this.jdField_field_831_of_type_JavaxSwingJList.setModel(this.jdField_field_831_of_type_Class_680);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_526
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */