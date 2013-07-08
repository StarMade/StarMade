import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class class_573
  extends JPanel
{
  private JTextField field_875;
  
  public class_573(class_371 paramclass_371, class_773 paramclass_773)
  {
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 1.0D, 0.0D, 1.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject1);
    this.field_875 = new JTextField();
    this.field_875.setText(paramclass_773.a());
    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject1).fill = 2;
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 0;
    add(this.field_875, localObject1);
    this.field_875.setColumns(10);
    (localObject1 = new JTextArea()).setText(paramclass_773.c5());
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 1;
    add((Component)localObject1, localObject2);
    Object localObject2 = new JPanel();
    Object localObject3;
    (localObject3 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject3).anchor = 11;
    ((GridBagConstraints)localObject3).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject3).gridx = 0;
    ((GridBagConstraints)localObject3).gridy = 2;
    add((Component)localObject2, localObject3);
    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
    (localObject3 = new JButton("Apply Name/Description")).addActionListener(new class_575(this, paramclass_371, paramclass_773, (JTextArea)localObject1));
    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 0;
    ((JPanel)localObject2).add((Component)localObject3, localObject1);
    (localObject1 = new JButton("Add Member")).addActionListener(new class_577(paramclass_371, paramclass_773));
    (localObject3 = new GridBagConstraints()).anchor = 18;
    ((GridBagConstraints)localObject3).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject3).gridx = 2;
    ((GridBagConstraints)localObject3).gridy = 0;
    ((JPanel)localObject2).add((Component)localObject1, localObject3);
    (localObject1 = new JButton("Edit Roles")).addActionListener(new class_564(paramclass_371, paramclass_773));
    (localObject3 = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject3).gridx = 3;
    ((GridBagConstraints)localObject3).gridy = 0;
    ((JPanel)localObject2).add((Component)localObject1, localObject3);
    (localObject1 = new JButton("Delete Faction")).addActionListener(new class_566(paramclass_371, paramclass_773));
    (localObject3 = new GridBagConstraints()).anchor = 18;
    ((GridBagConstraints)localObject3).gridx = 5;
    ((GridBagConstraints)localObject3).gridy = 0;
    ((JPanel)localObject2).add((Component)localObject1, localObject3);
    Object localObject1 = new JScrollPane();
    (localObject2 = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 3;
    add((Component)localObject1, localObject2);
    paramclass_371 = new class_1344(paramclass_371, paramclass_773);
    ((JScrollPane)localObject1).setViewportView(paramclass_371);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_573
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */