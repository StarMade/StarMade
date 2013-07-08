import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

public final class class_546
  extends JDialog
{
  private static final long serialVersionUID = -3693336102651064550L;
  private final JPanel field_851 = new JPanel();
  
  public class_546(JFrame paramJFrame, ArrayList paramArrayList1, ArrayList paramArrayList2, class_532 paramclass_532)
  {
    super(paramJFrame, true);
    setBounds(100, 100, 450, 435);
    getContentPane().setLayout(new BorderLayout());
    this.field_851.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.field_851, "Center");
    Object localObject1;
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 125 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 25, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
    this.field_851.setLayout((LayoutManager)localObject1);
    (localObject1 = new JSplitPane()).setOrientation(0);
    Object localObject2;
    (localObject2 = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject2).weightx = 1.0D;
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).anchor = 18;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.field_851.add((Component)localObject1, localObject2);
    paramArrayList1 = new class_526(paramJFrame, paramArrayList1);
    ((JSplitPane)localObject1).setLeftComponent(paramArrayList1);
    paramArrayList1 = new class_526(paramJFrame, paramArrayList2);
    ((JSplitPane)localObject1).setRightComponent(paramArrayList1);
    ((JSplitPane)localObject1).setDividerLocation(200);
    (localObject1 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject1, "South");
    (localObject2 = new JButton("OK")).setActionCommand("OK");
    ((JPanel)localObject1).add((Component)localObject2);
    getRootPane().setDefaultButton((JButton)localObject2);
    ((JButton)localObject2).addActionListener(new class_560(this, paramclass_532));
    (localObject2 = new JButton("Cancel")).setActionCommand("Cancel");
    ((JPanel)localObject1).add((Component)localObject2);
    ((JButton)localObject2).addActionListener(new class_558(this));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_546
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */