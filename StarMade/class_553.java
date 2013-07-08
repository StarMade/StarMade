import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class class_553
  extends AbstractCellEditor
  implements TableCellEditor
{
  public Object getCellEditorValue()
  {
    return null;
  }
  
  public Component getTableCellEditorComponent(JTable paramJTable, Object paramObject, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    paramJTable = ((class_527)paramObject).a(paramInt2, paramJTable);
    if ((!field_859) && (paramJTable == null)) {
      throw new AssertionError();
    }
    return paramJTable;
  }
  
  public boolean isCellEditable(EventObject paramEventObject)
  {
    return true;
  }
  
  public boolean shouldSelectCell(EventObject paramEventObject)
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_553
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */