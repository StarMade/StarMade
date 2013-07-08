import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.schema.schine.network.objects.Sendable;

public class class_1278
  extends JPanel
{
  private static final long serialVersionUID = 6724191831876425314L;
  private class_1279 jdField_field_1463_of_type_Class_1279;
  private JTable jdField_field_1463_of_type_JavaxSwingJTable;
  public final Sendable field_1463;
  
  public class_1278(Sendable paramSendable)
  {
    this.jdField_field_1463_of_type_OrgSchemaSchineNetworkObjectsSendable = paramSendable;
    (paramSendable = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramSendable.rowHeights = new int[] { 0, 0 };
    paramSendable.columnWeights = new double[] { 1.0D, 4.9E-324D };
    paramSendable.rowWeights = new double[] { 1.0D, 4.9E-324D };
    setLayout(paramSendable);
    paramSendable = new JScrollPane();
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    add(paramSendable, localGridBagConstraints);
    this.jdField_field_1463_of_type_Class_1279 = new class_1279();
    this.jdField_field_1463_of_type_JavaxSwingJTable = new JTable(this.jdField_field_1463_of_type_Class_1279);
    this.jdField_field_1463_of_type_JavaxSwingJTable.setDefaultRenderer(rY.class, new class_1207());
    this.jdField_field_1463_of_type_JavaxSwingJTable.setDefaultRenderer(String.class, new class_1207());
    this.jdField_field_1463_of_type_JavaxSwingJTable.setDefaultEditor(rY.class, new class_1225());
    a();
    paramSendable.setViewportView(this.jdField_field_1463_of_type_JavaxSwingJTable);
  }
  
  public void a()
  {
    a1(new class_1280(this, "ID"));
    a1(new class_572(this, "CLASS"));
  }
  
  public final void a1(class_1284 paramclass_1284)
  {
    this.jdField_field_1463_of_type_Class_1279.a().add(paramclass_1284);
    this.jdField_field_1463_of_type_JavaxSwingJTable.invalidate();
    this.jdField_field_1463_of_type_JavaxSwingJTable.validate();
  }
  
  public final void b()
  {
    this.jdField_field_1463_of_type_Class_1279.a1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1278
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */