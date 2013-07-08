import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import org.schema.game.common.staremote.Staremote;
import org.schema.game.network.StarMadeServerStats;

public final class class_531
  extends JFrame
{
  private JPanel jdField_field_835_of_type_JavaxSwingJPanel;
  private JLabel jdField_field_835_of_type_JavaxSwingJLabel;
  private JLabel field_836;
  private Staremote jdField_field_835_of_type_OrgSchemaGameCommonStaremoteStaremote;
  public static JFrame field_835;
  
  public class_531(class_371 paramclass_371, Staremote paramStaremote)
  {
    jdField_field_835_of_type_JavaxSwingJFrame = this;
    this.jdField_field_835_of_type_OrgSchemaGameCommonStaremoteStaremote = paramStaremote;
    setTitle("StarMote (StarMade Remote Admin Tool)");
    setDefaultCloseOperation(3);
    setBounds(100, 100, 1024, 768);
    this.jdField_field_835_of_type_JavaxSwingJPanel = new JPanel();
    this.jdField_field_835_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.jdField_field_835_of_type_JavaxSwingJPanel);
    Object localObject;
    (localObject = new GridBagLayout()).columnWidths = new int[] { 424, 0 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 252, 30 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 1.0D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 5.0D, 1.0D };
    this.jdField_field_835_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject);
    (localObject = new JSplitPane()).setDividerSize(3);
    ((JSplitPane)localObject).setOrientation(0);
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).weighty = 30.0D;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.fill = 1;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    this.jdField_field_835_of_type_JavaxSwingJPanel.add((Component)localObject, localGridBagConstraints);
    (paramStaremote = new class_525(paramclass_371, paramStaremote)).setPreferredSize(new Dimension(104, 300));
    ((JSplitPane)localObject).setLeftComponent(paramStaremote);
    paramclass_371 = new class_525(paramclass_371);
    ((JSplitPane)localObject).setRightComponent(paramclass_371);
    ((JSplitPane)localObject).setDividerLocation(500);
    (paramclass_371 = new JPanel()).setMaximumSize(new Dimension(60, 20));
    paramclass_371.setMinimumSize(new Dimension(10, 20));
    paramclass_371.setPreferredSize(new Dimension(10, 20));
    (paramStaremote = new GridBagConstraints()).weighty = 1.0D;
    paramStaremote.anchor = 11;
    paramStaremote.fill = 1;
    paramStaremote.gridx = 0;
    paramStaremote.gridy = 1;
    this.jdField_field_835_of_type_JavaxSwingJPanel.add(paramclass_371, paramStaremote);
    (paramStaremote = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramStaremote.rowHeights = new int[] { 0, 0, 0 };
    paramStaremote.columnWeights = new double[] { 0.0D, 4.9E-324D };
    paramStaremote.rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
    paramclass_371.setLayout(paramStaremote);
    this.jdField_field_835_of_type_JavaxSwingJLabel = new JLabel("ServerStatus: ");
    this.jdField_field_835_of_type_JavaxSwingJLabel.setPreferredSize(new Dimension(180, 14));
    this.jdField_field_835_of_type_JavaxSwingJLabel.setMinimumSize(new Dimension(180, 40));
    this.jdField_field_835_of_type_JavaxSwingJLabel.setMaximumSize(new Dimension(180, 40));
    (paramStaremote = new GridBagConstraints()).fill = 2;
    paramStaremote.weighty = 1.0D;
    paramStaremote.anchor = 18;
    paramStaremote.insets = new Insets(0, 5, 3, 0);
    paramStaremote.gridx = 0;
    paramStaremote.gridy = 0;
    paramclass_371.add(this.jdField_field_835_of_type_JavaxSwingJLabel, paramStaremote);
    this.field_836 = new JLabel("Memory");
    this.field_836.setMinimumSize(new Dimension(38, 180));
    this.field_836.setMaximumSize(new Dimension(38, 180));
    (paramStaremote = new GridBagConstraints()).fill = 2;
    paramStaremote.insets = new Insets(0, 5, 0, 0);
    paramStaremote.weighty = 1.0D;
    paramStaremote.anchor = 18;
    paramStaremote.gridx = 0;
    paramStaremote.gridy = 1;
    paramclass_371.add(this.field_836, paramStaremote);
  }
  
  public final void a(StarMadeServerStats paramStarMadeServerStats)
  {
    this.jdField_field_835_of_type_JavaxSwingJLabel.setText("SERVER PING: " + paramStarMadeServerStats.ping + " ms");
    this.field_836.setText("SERVER MEMORY: " + paramStarMadeServerStats.takenMemory / 1024L / 1024L + " / " + paramStarMadeServerStats.totalMemory / 1024L / 1024L + " MB");
  }
  
  public final void dispose()
  {
    this.jdField_field_835_of_type_OrgSchemaGameCommonStaremoteStaremote.b();
    super.dispose();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_531
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */