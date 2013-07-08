import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public final class class_580
  extends JPanel
  implements Observer
{
  private static final long serialVersionUID = -8605779184700441964L;
  private class_1336 jdField_field_880_of_type_Class_1336;
  private class_371 jdField_field_880_of_type_Class_371;
  private JTable jdField_field_880_of_type_JavaxSwingJTable;
  
  public class_580(class_371 paramclass_371, class_773 paramclass_773)
  {
    this.jdField_field_880_of_type_Class_371 = paramclass_371;
    paramclass_371.a45().addObserver(this);
    paramclass_371.a20().a141().addObserver(this);
    (localObject = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject).rowWeights = new double[] { 1.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject);
    Object localObject = new JScrollPane();
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    add((Component)localObject, localGridBagConstraints);
    this.jdField_field_880_of_type_Class_1336 = new class_1336(paramclass_371, paramclass_773);
    this.jdField_field_880_of_type_JavaxSwingJTable = new JTable(this.jdField_field_880_of_type_Class_1336);
    this.jdField_field_880_of_type_JavaxSwingJTable.setDefaultRenderer(qJ.class, new class_1350());
    this.jdField_field_880_of_type_JavaxSwingJTable.setDefaultRenderer(String.class, new class_1350());
    this.jdField_field_880_of_type_JavaxSwingJTable.setDefaultEditor(qJ.class, new class_1348());
    this.jdField_field_880_of_type_JavaxSwingJTable.setDefaultEditor(String.class, new class_1348());
    ((JScrollPane)localObject).setViewportView(this.jdField_field_880_of_type_JavaxSwingJTable);
    a();
    class_1336.a();
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    a();
  }
  
  private void a()
  {
    this.jdField_field_880_of_type_JavaxSwingJTable.clearSelection();
    this.jdField_field_880_of_type_JavaxSwingJTable.requestFocus();
    this.jdField_field_880_of_type_JavaxSwingJTable.removeAll();
    this.jdField_field_880_of_type_Class_1336.a1(this.jdField_field_880_of_type_Class_371);
    this.jdField_field_880_of_type_JavaxSwingJTable.repaint();
    this.jdField_field_880_of_type_JavaxSwingJTable.requestFocus();
    this.jdField_field_880_of_type_JavaxSwingJTable.invalidate();
    this.jdField_field_880_of_type_JavaxSwingJTable.validate();
    this.jdField_field_880_of_type_JavaxSwingJTable.selectAll();
    this.jdField_field_880_of_type_JavaxSwingJTable.clearSelection();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_580
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */