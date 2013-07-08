import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

public class class_1336
  extends AbstractTableModel
{
  private static final long serialVersionUID = 3210767605402324663L;
  private final ArrayList jdField_field_1517_of_type_JavaUtilArrayList = new ArrayList();
  private class_773 jdField_field_1517_of_type_Class_773;
  
  public class_1336(class_371 paramclass_371, class_773 paramclass_773)
  {
    this.jdField_field_1517_of_type_Class_773 = paramclass_773;
    a1(paramclass_371);
  }
  
  public int getColumnCount()
  {
    return 3;
  }
  
  public String getColumnName(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return "Name";
    case 1: 
      return "Role";
    case 2: 
      return "Option";
    }
    return "-";
  }
  
  public static void a() {}
  
  public int getRowCount()
  {
    return this.jdField_field_1517_of_type_JavaUtilArrayList.size();
  }
  
  public Class getColumnClass(int paramInt)
  {
    return qJ.class;
  }
  
  public boolean isCellEditable(int paramInt1, int paramInt2)
  {
    return paramInt2 >= getColumnCount() - 5;
  }
  
  public Object getValueAt(int paramInt1, int paramInt2)
  {
    paramInt1 = (class_590)this.jdField_field_1517_of_type_JavaUtilArrayList.get(paramInt1);
    if ((!jdField_field_1517_of_type_Boolean) && (paramInt1 == null)) {
      throw new AssertionError();
    }
    return paramInt1;
  }
  
  public final void a1(class_371 paramclass_371)
  {
    Collection localCollection = this.jdField_field_1517_of_type_Class_773.a162().values();
    ArrayList localArrayList;
    (localArrayList = new ArrayList()).addAll(localCollection);
    EventQueue.invokeLater(new class_1338(this, localArrayList, paramclass_371));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1336
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */