import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JPanel;

public final class class_1221
  extends JPanel
{
  private static final long serialVersionUID = 4609224773339471981L;
  
  public class_1221(class_371 paramclass_371)
  {
    Object localObject;
    (localObject = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject);
    (localObject = new JButton("Repair Sector")).addActionListener(new class_1329(paramclass_371));
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    add((Component)localObject, localGridBagConstraints);
    (localObject = new JButton("Warp Player to Sector")).addActionListener(new class_1324(paramclass_371));
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 1;
    add((Component)localObject, localGridBagConstraints);
    (localObject = new JButton("Search Entity")).addActionListener(new class_1322(paramclass_371));
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.anchor = 17;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 2;
    add((Component)localObject, localGridBagConstraints);
    (localObject = new JButton("Despawn Entities")).addActionListener(new class_1328(paramclass_371));
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.anchor = 17;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 3;
    add((Component)localObject, localGridBagConstraints);
    (localObject = new JButton("Populate Sector")).addActionListener(new class_1326(paramclass_371));
    (paramclass_371 = new GridBagConstraints()).anchor = 17;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 4;
    add((Component)localObject, paramclass_371);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1221
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */