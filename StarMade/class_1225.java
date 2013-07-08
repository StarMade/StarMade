import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public final class class_1225
  extends AbstractCellEditor
  implements TableCellEditor
{
  public final Object getCellEditorValue()
  {
    return null;
  }
  
  public final Component getTableCellEditorComponent(JTable paramJTable, Object paramObject, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    return ((class_1284)paramObject).a1();
  }
  
  public final boolean isCellEditable(EventObject paramEventObject)
  {
    return true;
  }
  
  public final boolean shouldSelectCell(EventObject paramEventObject)
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1225
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */