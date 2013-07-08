import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import org.schema.schine.network.server.ServerController;

public final class class_518
  extends JFrame
{
  private static final long serialVersionUID = -944681053572069570L;
  private JPanel jdField_field_826_of_type_JavaxSwingJPanel;
  private ServerController jdField_field_826_of_type_OrgSchemaSchineNetworkServerServerController;
  
  public class_518(ServerController paramServerController)
  {
    setTitle("StarMade Server Manager");
    this.jdField_field_826_of_type_OrgSchemaSchineNetworkServerServerController = paramServerController;
    setDefaultCloseOperation(3);
    setBounds(100, 100, 668, 363);
    this.jdField_field_826_of_type_JavaxSwingJPanel = new JPanel();
    this.jdField_field_826_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.jdField_field_826_of_type_JavaxSwingJPanel.setLayout(new BorderLayout(0, 0));
    setContentPane(this.jdField_field_826_of_type_JavaxSwingJPanel);
    paramServerController = new JTabbedPane(1);
    this.jdField_field_826_of_type_JavaxSwingJPanel.add(paramServerController, "Center");
    class_512 localclass_512 = new class_512(this.jdField_field_826_of_type_OrgSchemaSchineNetworkServerServerController);
    paramServerController.addTab("Main", null, localclass_512, null);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_518
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */