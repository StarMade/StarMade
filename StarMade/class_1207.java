import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public final class class_1207
  extends JLabel
  implements TableCellRenderer
{
  public final Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    paramJTable = (class_1284)paramObject;
    if (paramInt2 == 0)
    {
      setText(paramJTable.a3());
      return this;
    }
    return paramJTable.a1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1207
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */