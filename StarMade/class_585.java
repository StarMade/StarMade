import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class class_585
  extends JPanel
{
  private static final long serialVersionUID = 5674993253545810749L;
  
  public class_585()
  {
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject1);
    Object localObject1 = new JLabel(" Tutorial:");
    (localObject2 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    add((Component)localObject1, localObject2);
    localObject1 = new class_537(class_949.field_1232);
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).fill = 2;
    ((GridBagConstraints)localObject2).gridx = 1;
    ((GridBagConstraints)localObject2).gridy = 0;
    add((Component)localObject1, localObject2);
    (localObject1 = new JLabel("Graphics Settings")).setFont(new Font("Arial", 0, 13));
    (localObject2 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject2).gridwidth = 2;
    ((GridBagConstraints)localObject2).insets = new Insets(5, 0, 5, 0);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 1;
    add((Component)localObject1, localObject2);
    localObject1 = new JLabel(" Resolution: ");
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
    ((GridBagConstraints)localObject2).anchor = 17;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 2;
    add((Component)localObject1, localObject2);
    Object localObject2 = new class_537(class_949.field_1176);
    (localObject3 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject3).fill = 2;
    ((GridBagConstraints)localObject3).gridx = 1;
    ((GridBagConstraints)localObject3).gridy = 2;
    add((Component)localObject2, localObject3);
    Object localObject3 = new JLabel(" Fullscreen: ");
    (localObject4 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject4).insets = new Insets(0, 5, 5, 5);
    ((GridBagConstraints)localObject4).gridx = 0;
    ((GridBagConstraints)localObject4).gridy = 3;
    add((Component)localObject3, localObject4);
    Object localObject4 = new class_537(class_949.field_1177);
    (localObject5 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject5).fill = 2;
    ((GridBagConstraints)localObject5).gridx = 1;
    ((GridBagConstraints)localObject5).gridy = 3;
    add((Component)localObject4, localObject5);
    Object localObject5 = new JLabel(" Field of View (FOV): ");
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 4;
    add((Component)localObject5, localGridBagConstraints);
    localObject5 = new class_537(class_949.field_1180);
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.fill = 2;
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 4;
    add((Component)localObject5, localGridBagConstraints);
    (localObject5 = new JLabel("Sound Settings")).setFont(new Font("Arial", 0, 13));
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.gridwidth = 2;
    localGridBagConstraints.insets = new Insets(10, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 5;
    add((Component)localObject5, localGridBagConstraints);
    localObject5 = new JLabel(" Enable Sound: ");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 6;
    add((Component)localObject5, localGridBagConstraints);
    localObject5 = new class_537(class_949.field_1181);
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.fill = 2;
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 6;
    add((Component)localObject5, localGridBagConstraints);
    localObject5 = new JLabel(" Sound Volume: ");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 7;
    add((Component)localObject5, localGridBagConstraints);
    localObject5 = new class_537(class_949.field_1183);
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.fill = 2;
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 7;
    add((Component)localObject5, localGridBagConstraints);
    (localObject5 = new JLabel("Control Settings")).setFont(new Font("Arial", 0, 13));
    (localGridBagConstraints = new GridBagConstraints()).anchor = 16;
    localGridBagConstraints.gridwidth = 2;
    localGridBagConstraints.insets = new Insets(10, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 8;
    add((Component)localObject5, localGridBagConstraints);
    localObject5 = new JLabel(" Invert Mouse (All):");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 9;
    add((Component)localObject5, localGridBagConstraints);
    localObject5 = new class_537(class_949.field_1244);
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.fill = 2;
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 9;
    add((Component)localObject5, localGridBagConstraints);
    localObject5 = new JLabel(" Invert Ship Mouse:");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 5, 0, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 10;
    add((Component)localObject5, localGridBagConstraints);
    localObject5 = new class_537(class_949.field_1243);
    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 10;
    add((Component)localObject5, localGridBagConstraints);
    ((JLabel)localObject3).setLabelFor((Component)localObject4);
    ((JLabel)localObject1).setLabelFor((Component)localObject2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_585
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */