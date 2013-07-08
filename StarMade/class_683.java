import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

final class class_683
  extends JLabel
  implements TableCellRenderer
{
  private static final long serialVersionUID = -4177071331912911216L;
  
  private class_683(class_676 paramclass_676) {}
  
  public final Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    paramJTable = (paramInt1 << 4) + paramInt2;
    setIcon(class_29.a8((this.field_950.b() << 8) + paramJTable));
    setEnabled(paramJTable != class_676.a1(this.field_950));
    return this;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_683
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */