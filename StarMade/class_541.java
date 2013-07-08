import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class class_541
  extends AbstractTableModel
{
  private static final long serialVersionUID = 3210767605402324663L;
  private final ArrayList jdField_field_843_of_type_JavaUtilArrayList = new ArrayList();
  
  public int getColumnCount()
  {
    return 12;
  }
  
  public String getColumnName(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return "Name";
    case 1: 
      return "Owner";
    case 2: 
      return "Price";
    case 3: 
      return "Rating";
    case 4: 
      return "Description";
    case 5: 
      return "Date";
    case 6: 
      return "# Spawned";
    case 7: 
      return "Enemy ACL";
    case 8: 
      return "Faction ACL";
    case 9: 
      return "Homebase ACL";
    case 10: 
      return "Others ACL";
    case 11: 
      return "Options";
    }
    return "-";
  }
  
  public static void a(JTable paramJTable)
  {
    paramJTable.getColumn("Rating").setPreferredWidth(20);
    paramJTable.getColumn("# Spawned").setPreferredWidth(20);
    paramJTable.getColumn("Enemy ACL").setPreferredWidth(20);
    paramJTable.getColumn("Faction ACL").setPreferredWidth(20);
    paramJTable.getColumn("Homebase ACL").setPreferredWidth(20);
    paramJTable.getColumn("Others ACL").setPreferredWidth(20);
    paramJTable.getColumn("Options").setPreferredWidth(50);
  }
  
  public int getRowCount()
  {
    return this.jdField_field_843_of_type_JavaUtilArrayList.size();
  }
  
  public Class getColumnClass(int paramInt)
  {
    return pO.class;
  }
  
  public boolean isCellEditable(int paramInt1, int paramInt2)
  {
    return paramInt2 >= getColumnCount() - 5;
  }
  
  public Object getValueAt(int paramInt1, int paramInt2)
  {
    paramInt1 = (class_527)this.jdField_field_843_of_type_JavaUtilArrayList.get(paramInt1);
    if ((!jdField_field_843_of_type_Boolean) && (paramInt1 == null)) {
      throw new AssertionError();
    }
    return paramInt1;
  }
  
  public final void a1(class_371 paramclass_371)
  {
    ArrayList localArrayList1 = paramclass_371.a20().a122().b1();
    ArrayList localArrayList2;
    (localArrayList2 = new ArrayList()).addAll(localArrayList1);
    EventQueue.invokeLater(new class_547(this, localArrayList2, paramclass_371));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_541
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */