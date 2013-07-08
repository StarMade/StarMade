import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class class_543
  implements TableCellRenderer
{
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    paramJTable = (paramObject = (class_527)paramObject).a(paramInt2, paramJTable);
    if ((!field_845) && (paramJTable == null)) {
      throw new AssertionError();
    }
    if ((paramObject = paramObject.a2(paramInt2)) != null) {
      paramJTable.setForeground(paramObject.darker());
    }
    return paramJTable;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_543
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */