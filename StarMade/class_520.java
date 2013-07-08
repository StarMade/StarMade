import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.schema.schine.network.server.ServerController;

public final class class_520
  extends JPanel
{
  private static final long serialVersionUID = 3403845815721633534L;
  private JTable jdField_field_827_of_type_JavaxSwingJTable;
  class_1343 jdField_field_827_of_type_Class_1343;
  private ServerController jdField_field_827_of_type_OrgSchemaSchineNetworkServerServerController;
  
  public class_520(ServerController paramServerController)
  {
    this.jdField_field_827_of_type_OrgSchemaSchineNetworkServerServerController = paramServerController;
    this.jdField_field_827_of_type_Class_1343 = new class_1343();
    setLayout(new BorderLayout(0, 0));
    this.jdField_field_827_of_type_JavaxSwingJTable = new JTable(this.jdField_field_827_of_type_Class_1343);
    this.jdField_field_827_of_type_JavaxSwingJTable.setFillsViewportHeight(true);
    this.jdField_field_827_of_type_JavaxSwingJTable.setAutoCreateRowSorter(true);
    this.jdField_field_827_of_type_JavaxSwingJTable.addMouseListener(new class_522(this));
    add(this.jdField_field_827_of_type_JavaxSwingJTable);
    add(this.jdField_field_827_of_type_JavaxSwingJTable, "Center");
    add(this.jdField_field_827_of_type_JavaxSwingJTable.getTableHeader(), "North");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_520
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */