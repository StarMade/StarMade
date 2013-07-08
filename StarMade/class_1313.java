import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import org.schema.game.common.staremote.Staremote;

public final class class_1313
  extends JPanel
{
  private Object jdField_field_1489_of_type_JavaLangObject = new Object();
  private static final long serialVersionUID = -2984518551942439061L;
  private JButton jdField_field_1489_of_type_JavaxSwingJButton;
  private JButton field_1490;
  Boolean jdField_field_1489_of_type_JavaLangBoolean = Boolean.valueOf(false);
  
  public class_1313(JFrame paramJFrame, Staremote paramStaremote)
  {
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 1.0D, 1.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject1);
    Object localObject1 = new JPanel();
    (localObject2 = new GridBagConstraints()).weighty = 5.0D;
    ((GridBagConstraints)localObject2).weightx = 1.0D;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    add((Component)localObject1, localObject2);
    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject2).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject2).rowWeights = new double[] { 1.0D, 4.9E-324D };
    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
    Object localObject2 = new JPanel();
    (localObject3 = new GridBagConstraints()).weightx = 1.0D;
    ((GridBagConstraints)localObject3).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject3).fill = 1;
    ((GridBagConstraints)localObject3).gridx = 0;
    ((GridBagConstraints)localObject3).gridy = 0;
    ((JPanel)localObject1).add((Component)localObject2, localObject3);
    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject3).rowWeights = new double[] { 1.0D, 4.9E-324D };
    ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
    Object localObject3 = new JScrollPane();
    (localObject4 = new GridBagConstraints()).fill = 1;
    ((GridBagConstraints)localObject4).gridx = 0;
    ((GridBagConstraints)localObject4).gridy = 0;
    ((JPanel)localObject2).add((Component)localObject3, localObject4);
    localObject2 = new class_1323();
    Object localObject4 = new JList((ListModel)localObject2);
    ((JScrollPane)localObject3).setViewportView((Component)localObject4);
    localObject3 = new JPanel();
    Object localObject5;
    (localObject5 = new GridBagConstraints()).weightx = 0.001D;
    ((GridBagConstraints)localObject5).fill = 1;
    ((GridBagConstraints)localObject5).gridx = 1;
    ((GridBagConstraints)localObject5).gridy = 0;
    ((JPanel)localObject1).add((Component)localObject3, localObject5);
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    ((JPanel)localObject3).setLayout((LayoutManager)localObject1);
    (localObject1 = new JButton("Connect")).setEnabled(false);
    ((JButton)localObject1).addActionListener(new class_1319((JList)localObject4, paramJFrame, paramStaremote));
    (localObject5 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject5).anchor = 13;
    ((GridBagConstraints)localObject5).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject5).gridx = 0;
    ((GridBagConstraints)localObject5).gridy = 0;
    ((JPanel)localObject3).add((Component)localObject1, localObject5);
    (localObject5 = new JButton("Add Connection")).addActionListener(new class_1317(paramJFrame, (class_1323)localObject2));
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
    localGridBagConstraints.anchor = 13;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 2;
    ((JPanel)localObject3).add((Component)localObject5, localGridBagConstraints);
    this.field_1490 = new JButton("Edit Connection ");
    this.field_1490.addActionListener(new class_1307(this, (JList)localObject4, (JButton)localObject1, paramJFrame, (class_1323)localObject2));
    this.field_1490.setEnabled(false);
    (localObject5 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject5).fill = 2;
    ((GridBagConstraints)localObject5).anchor = 13;
    ((GridBagConstraints)localObject5).gridx = 0;
    ((GridBagConstraints)localObject5).gridy = 3;
    ((JPanel)localObject3).add(this.field_1490, localObject5);
    this.jdField_field_1489_of_type_JavaxSwingJButton = new JButton("Remove Connection");
    this.jdField_field_1489_of_type_JavaxSwingJButton.setEnabled(false);
    this.jdField_field_1489_of_type_JavaxSwingJButton.addActionListener(new class_1305(this, (JList)localObject4, (JButton)localObject1, (class_1323)localObject2));
    (localObject2 = new JButton("Uplink Settings")).addActionListener(new class_1311());
    (localObject5 = new GridBagConstraints()).anchor = 13;
    ((GridBagConstraints)localObject5).fill = 2;
    ((GridBagConstraints)localObject5).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject5).gridx = 0;
    ((GridBagConstraints)localObject5).gridy = 5;
    ((JPanel)localObject3).add((Component)localObject2, localObject5);
    (localObject2 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject2).anchor = 13;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 7;
    ((JPanel)localObject3).add(this.jdField_field_1489_of_type_JavaxSwingJButton, localObject2);
    localObject2 = new JPanel();
    (localObject3 = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject3).weightx = 0.2D;
    ((GridBagConstraints)localObject3).fill = 1;
    ((GridBagConstraints)localObject3).gridx = 0;
    ((GridBagConstraints)localObject3).gridy = 1;
    add((Component)localObject2, localObject3);
    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).columnWeights = new double[] { 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
    (localObject3 = new JButton("   Exit   ")).addActionListener(new class_1309());
    (localObject5 = new GridBagConstraints()).insets = new Insets(5, 0, 5, 5);
    ((GridBagConstraints)localObject5).weightx = 1.0D;
    ((GridBagConstraints)localObject5).anchor = 13;
    ((GridBagConstraints)localObject5).gridx = 0;
    ((GridBagConstraints)localObject5).gridy = 0;
    ((JPanel)localObject2).add((Component)localObject3, localObject5);
    ((JList)localObject4).addListSelectionListener(new class_1299(this, (JButton)localObject1, (JList)localObject4));
    ((JList)localObject4).addMouseListener(new class_32((JList)localObject4, new class_1297(this, (JList)localObject4, paramJFrame, paramStaremote)));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1313
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */