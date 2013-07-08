import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public final class class_1344
  extends JPanel
{
  private class_773 jdField_field_1524_of_type_Class_773;
  private class_371 jdField_field_1524_of_type_Class_371;
  
  public class_1344(class_371 paramclass_371, class_773 paramclass_773)
  {
    this.jdField_field_1524_of_type_Class_371 = paramclass_371;
    this.jdField_field_1524_of_type_Class_773 = paramclass_773;
    paramclass_371 = paramclass_371.a45().a101();
    GridBagLayout localGridBagLayout;
    (localGridBagLayout = new GridBagLayout()).columnWidths = new int[] { 0 };
    localGridBagLayout.rowHeights = new int[paramclass_371.size()];
    localGridBagLayout.columnWeights = new double[] { 0.0D };
    localGridBagLayout.rowWeights = new double[paramclass_371.size()];
    Arrays.fill(localGridBagLayout.columnWeights, 1.0D);
    Arrays.fill(localGridBagLayout.rowWeights, 1.0D);
    localGridBagLayout.columnWeights[(localGridBagLayout.columnWeights.length - 1)] = 4.9E-324D;
    localGridBagLayout.rowWeights[(localGridBagLayout.rowWeights.length - 1)] = 4.9E-324D;
    setLayout(localGridBagLayout);
    int i = 0;
    paramclass_371 = paramclass_371.iterator();
    while (paramclass_371.hasNext())
    {
      Object localObject1;
      if ((localObject1 = (class_773)paramclass_371.next()) != paramclass_773)
      {
        Object localObject3 = localObject1;
        int j = i;
        localObject1 = this;
        JPanel localJPanel = new JPanel();
        (localObject4 = new GridBagConstraints()).fill = 2;
        ((GridBagConstraints)localObject4).weightx = 1.0D;
        ((GridBagConstraints)localObject4).anchor = 18;
        ((GridBagConstraints)localObject4).gridx = 0;
        ((GridBagConstraints)localObject4).gridy = j;
        ((class_1344)localObject1).add(localJPanel, localObject4);
        (localObject2 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0, 0 };
        ((GridBagLayout)localObject2).rowHeights = new int[] { 0, 0 };
        ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
        ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 4.9E-324D };
        localJPanel.setLayout((LayoutManager)localObject2);
        (localObject2 = new JLabel(localObject3.a())).setPreferredSize(new Dimension(200, 14));
        ((JLabel)localObject2).setFont(new Font("Tahoma", 1, 12));
        (localObject4 = new GridBagConstraints()).anchor = 17;
        ((GridBagConstraints)localObject4).weightx = 1.0D;
        ((GridBagConstraints)localObject4).insets = new Insets(0, 5, 0, 5);
        ((GridBagConstraints)localObject4).gridx = 0;
        ((GridBagConstraints)localObject4).gridy = 0;
        localJPanel.add((Component)localObject2, localObject4);
        Object localObject2 = new ButtonGroup();
        Object localObject4 = new JRadioButton("Enemy");
        (localObject5 = new GridBagConstraints()).weightx = 1.0D;
        ((GridBagConstraints)localObject5).anchor = 13;
        ((GridBagConstraints)localObject5).insets = new Insets(0, 0, 0, 5);
        ((GridBagConstraints)localObject5).gridx = 1;
        ((GridBagConstraints)localObject5).gridy = 0;
        ((JRadioButton)localObject4).setPreferredSize(new Dimension(66, 20));
        localJPanel.add((Component)localObject4, localObject5);
        ((ButtonGroup)localObject2).add((AbstractButton)localObject4);
        Object localObject5 = new JRadioButton("Neutral");
        (localObject6 = new GridBagConstraints()).weightx = 1.0D;
        ((GridBagConstraints)localObject6).anchor = 13;
        ((GridBagConstraints)localObject6).insets = new Insets(0, 0, 0, 5);
        ((GridBagConstraints)localObject6).gridx = 2;
        ((GridBagConstraints)localObject6).gridy = 0;
        ((JRadioButton)localObject5).setPreferredSize(new Dimension(66, 20));
        localJPanel.add((Component)localObject5, localObject6);
        ((ButtonGroup)localObject2).add((AbstractButton)localObject5);
        Object localObject6 = new JRadioButton("Alliance");
        GridBagConstraints localGridBagConstraints;
        (localGridBagConstraints = new GridBagConstraints()).weightx = 1.0D;
        localGridBagConstraints.anchor = 13;
        localGridBagConstraints.gridx = 3;
        localGridBagConstraints.gridy = 0;
        ((JRadioButton)localObject6).setPreferredSize(new Dimension(73, 20));
        localJPanel.add((Component)localObject6, localGridBagConstraints);
        ((ButtonGroup)localObject2).add((AbstractButton)localObject6);
        if ((localObject2 = ((class_1344)localObject1).jdField_field_1524_of_type_Class_371.a45().a159(((class_1344)localObject1).jdField_field_1524_of_type_Class_773.a3(), localObject3.a3())) == class_762.field_1023) {
          ((JRadioButton)localObject4).setSelected(true);
        } else if (localObject2 == class_762.field_1022) {
          ((JRadioButton)localObject5).setSelected(true);
        } else if (localObject2 == class_762.field_1024) {
          ((JRadioButton)localObject6).setSelected(true);
        } else {
          throw new IllegalArgumentException();
        }
        localObject1 = new class_1346((class_1344)localObject1, (JRadioButton)localObject4, localObject3, (JRadioButton)localObject5, (JRadioButton)localObject6);
        ((JRadioButton)localObject4).addActionListener((ActionListener)localObject1);
        ((JRadioButton)localObject5).addActionListener((ActionListener)localObject1);
        ((JRadioButton)localObject6).addActionListener((ActionListener)localObject1);
        i++;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1344
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */