import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.schema.game.common.staremote.Staremote;

public final class class_525
  extends JPanel
{
  public class_525(class_371 paramclass_371, Staremote paramStaremote)
  {
    setLayout(new BoxLayout(this, 0));
    JTabbedPane localJTabbedPane = new JTabbedPane(1);
    add(localJTabbedPane);
    paramStaremote = new class_1251(paramclass_371, paramStaremote);
    localJTabbedPane.addTab("Players", null, paramStaremote, null);
    paramStaremote = new class_555(paramclass_371);
    localJTabbedPane.addTab("Catalog", null, paramStaremote, null);
    paramStaremote = new class_1287(paramclass_371);
    localJTabbedPane.addTab("Entity", null, paramStaremote, null);
    paramStaremote = new class_1221(paramclass_371);
    localJTabbedPane.addTab("Sector", null, paramStaremote, null);
    paramclass_371 = new class_582(paramclass_371);
    localJTabbedPane.addTab("Faction", null, paramclass_371, null);
  }
  
  public class_525(class_371 paramclass_371)
  {
    (localObject = new GridBagLayout()).columnWidths = new int[] { 220, 10 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 10, 0, 0 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject);
    Object localObject = new class_1303(paramclass_371);
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.weightx = 1.0D;
    localGridBagConstraints.fill = 2;
    localGridBagConstraints.anchor = 18;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    add((Component)localObject, localGridBagConstraints);
    paramclass_371 = new class_1295(paramclass_371);
    (localObject = new GridBagConstraints()).fill = 1;
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 1;
    add(paramclass_371, localObject);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_525
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */