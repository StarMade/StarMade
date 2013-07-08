import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public final class class_555
  extends JPanel
  implements Observer
{
  private class_541 jdField_field_860_of_type_Class_541;
  private class_371 jdField_field_860_of_type_Class_371;
  private JTable jdField_field_860_of_type_JavaxSwingJTable;
  
  public class_555(class_371 paramclass_371)
  {
    this.jdField_field_860_of_type_Class_371 = paramclass_371;
    paramclass_371.a53().addObserver(this);
    paramclass_371.a20().a122().addObserver(this);
    (paramclass_371 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    paramclass_371.rowHeights = new int[] { 0, 0 };
    paramclass_371.columnWeights = new double[] { 1.0D, 4.9E-324D };
    paramclass_371.rowWeights = new double[] { 1.0D, 4.9E-324D };
    setLayout(paramclass_371);
    paramclass_371 = new JScrollPane();
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    add(paramclass_371, localGridBagConstraints);
    this.jdField_field_860_of_type_Class_541 = new class_541();
    this.jdField_field_860_of_type_JavaxSwingJTable = new JTable(this.jdField_field_860_of_type_Class_541);
    this.jdField_field_860_of_type_JavaxSwingJTable.setDefaultRenderer(pO.class, new class_543());
    this.jdField_field_860_of_type_JavaxSwingJTable.setDefaultRenderer(String.class, new class_543());
    this.jdField_field_860_of_type_JavaxSwingJTable.setDefaultEditor(pO.class, new class_553());
    this.jdField_field_860_of_type_JavaxSwingJTable.setDefaultEditor(String.class, new class_553());
    paramclass_371.setViewportView(this.jdField_field_860_of_type_JavaxSwingJTable);
    a();
    class_541.a(this.jdField_field_860_of_type_JavaxSwingJTable);
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    a();
  }
  
  private void a()
  {
    this.jdField_field_860_of_type_JavaxSwingJTable.clearSelection();
    this.jdField_field_860_of_type_JavaxSwingJTable.requestFocus();
    this.jdField_field_860_of_type_JavaxSwingJTable.removeAll();
    this.jdField_field_860_of_type_Class_541.a1(this.jdField_field_860_of_type_Class_371);
    this.jdField_field_860_of_type_JavaxSwingJTable.repaint();
    this.jdField_field_860_of_type_JavaxSwingJTable.requestFocus();
    this.jdField_field_860_of_type_JavaxSwingJTable.invalidate();
    this.jdField_field_860_of_type_JavaxSwingJTable.validate();
    this.jdField_field_860_of_type_JavaxSwingJTable.selectAll();
    this.jdField_field_860_of_type_JavaxSwingJTable.clearSelection();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_555
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */