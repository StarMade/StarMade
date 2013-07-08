import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class class_1350
  implements TableCellRenderer
{
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    paramJTable = ((class_590)paramObject).a(paramInt2);
    if ((!field_1531) && (paramJTable == null)) {
      throw new AssertionError();
    }
    class_590.a1();
    return paramJTable;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1350
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */