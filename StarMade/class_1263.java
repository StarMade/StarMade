import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.schema.game.network.ReceivedPlayer;

public final class class_1263
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  
  public class_1263(ReceivedPlayer paramReceivedPlayer)
  {
    (localObject = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject);
    Object localObject = new JLabel("Name: ");
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    add((Component)localObject, localGridBagConstraints);
    localObject = new JLabel(paramReceivedPlayer.name);
    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 0;
    add((Component)localObject, localGridBagConstraints);
    localObject = new JLabel("Last Login: ");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 1;
    add((Component)localObject, localGridBagConstraints);
    localObject = new JLabel(new Date(paramReceivedPlayer.lastLogin).toString());
    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 1;
    add((Component)localObject, localGridBagConstraints);
    localObject = new JLabel("Last Logout: ");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 2;
    add((Component)localObject, localGridBagConstraints);
    localObject = new JLabel(new Date(paramReceivedPlayer.lastLogout).toString());
    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 2;
    add((Component)localObject, localGridBagConstraints);
    localObject = new JLabel("Used IP:");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 3;
    add((Component)localObject, localGridBagConstraints);
    localObject = new JScrollPane();
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.fill = 1;
    localGridBagConstraints.gridx = 1;
    localGridBagConstraints.gridy = 3;
    add((Component)localObject, localGridBagConstraints);
    paramReceivedPlayer = new JList(paramReceivedPlayer.ips);
    ((JScrollPane)localObject).setViewportView(paramReceivedPlayer);
    paramReceivedPlayer = new JLabel("Options:");
    (localObject = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 4;
    add(paramReceivedPlayer, localObject);
    paramReceivedPlayer = new JButton("Coming Soon");
    (localObject = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject).gridx = 1;
    ((GridBagConstraints)localObject).gridy = 4;
    add(paramReceivedPlayer, localObject);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1263
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */