import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public final class class_1290
  extends JDialog
{
  public class_1290(class_371 paramclass_371)
  {
    super(class_531.field_835, true);
    setDefaultCloseOperation(2);
    setTitle("Repair Sector");
    setBounds(100, 100, 449, 144);
    getContentPane().setLayout(new BorderLayout());
    class_1316 localclass_1316 = new class_1316();
    getContentPane().add(localclass_1316, "Center");
    Object localObject;
    (localObject = new GridBagLayout()).columnWidths = new int[] { 0 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 0 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 4.9E-324D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 4.9E-324D };
    localclass_1316.setLayout((LayoutManager)localObject);
    (localObject = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject, "South");
    JButton localJButton;
    (localJButton = new JButton("OK")).addActionListener(new class_1292(this, localclass_1316, paramclass_371));
    localJButton.setActionCommand("OK");
    ((JPanel)localObject).add(localJButton);
    getRootPane().setDefaultButton(localJButton);
    (localJButton = new JButton("Cancel")).addActionListener(new class_1286(this));
    localJButton.setActionCommand("Cancel");
    ((JPanel)localObject).add(localJButton);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1290
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */