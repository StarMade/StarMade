import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import org.schema.schine.network.server.ServerController;

public final class class_512
  extends JPanel
  implements Observer
{
  private static final long serialVersionUID = -1485740756742142631L;
  private class_520 field_822;
  
  public class_512(ServerController paramServerController)
  {
    paramServerController.addObserver(this);
    setBorder(new TitledBorder(null, "Main Server Control", 4, 2, null, null));
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 1.0D, 1.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject1);
    (localObject1 = new JPanel()).setPreferredSize(new Dimension(200, 200));
    Object localObject2;
    (localObject2 = new GridBagConstraints()).anchor = 18;
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).weighty = 1.0D;
    ((GridBagConstraints)localObject2).weightx = 1.0D;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    add((Component)localObject1, localObject2);
    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject2).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject2).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject2).rowWeights = new double[] { 1.0D, 4.9E-324D };
    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
    (localObject2 = new JPanel()).setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Running Servers", 4, 2, null, new Color(0, 0, 0)));
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
    localGridBagConstraints.anchor = 18;
    localGridBagConstraints.weighty = 1.0D;
    localGridBagConstraints.weightx = 1.0D;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 1.0D, 4.9E-324D };
    ((JPanel)localObject2).setLayout((LayoutManager)localObject1);
    Object localObject1 = new JScrollPane();
    (localGridBagConstraints = new GridBagConstraints()).anchor = 18;
    localGridBagConstraints.weighty = 1.0D;
    localGridBagConstraints.weightx = 1.0D;
    localGridBagConstraints.fill = 1;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject2).add((Component)localObject1, localGridBagConstraints);
    this.field_822 = new class_520(paramServerController);
    ((JScrollPane)localObject1).setViewportView(this.field_822);
    paramServerController = new JPanel();
    (localObject1 = new GridBagConstraints()).anchor = 14;
    ((GridBagConstraints)localObject1).gridwidth = 0;
    ((GridBagConstraints)localObject1).gridheight = 0;
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 1;
    add(paramServerController, localObject1);
    paramServerController.setLayout(new GridLayout(0, 1, 0, 0));
    (localObject1 = new JButton("Shut Down Server")).addActionListener(new class_514());
    ((JButton)localObject1).setHorizontalAlignment(2);
    paramServerController.add((Component)localObject1);
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    if ((paramObservable instanceof ServerController))
    {
      paramObject = (ServerController)paramObservable;
      this.field_822.field_827.a(paramObject);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_512
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */