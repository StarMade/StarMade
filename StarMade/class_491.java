import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.server.data.blueprintnw.BlueprintEntry;

public final class class_491
  extends AbstractTableModel
{
  private static final long serialVersionUID = -8312184178260271728L;
  private List field_809;
  
  public class_491()
  {
    a1();
  }
  
  public final Class getColumnClass(int paramInt)
  {
    return getValueAt(0, paramInt).getClass();
  }
  
  public final int getColumnCount()
  {
    return 4;
  }
  
  public final String getColumnName(int paramInt)
  {
    if (paramInt == 0) {
      return "#";
    }
    if (paramInt == 1) {
      return "Name";
    }
    if (paramInt == 2) {
      return "Price";
    }
    if (paramInt == 3) {
      return "Size";
    }
    return "unknown";
  }
  
  public final List a()
  {
    return this.field_809;
  }
  
  public final int getRowCount()
  {
    try
    {
      return this.field_809.size();
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public final Object getValueAt(int paramInt1, int paramInt2)
  {
    try
    {
      localBlueprintEntry = null;
      localBlueprintEntry = (BlueprintEntry)this.field_809.get(paramInt1);
      if (paramInt2 == 0) {
        return String.valueOf(paramInt1);
      }
      if (paramInt2 == 1) {
        return String.valueOf(localBlueprintEntry.a4());
      }
      if (paramInt2 == 2) {
        return String.valueOf(localBlueprintEntry.a2());
      }
      if (paramInt2 == 3) {
        return localBlueprintEntry.a18().field_1273 + " - " + localBlueprintEntry.a18().field_1274;
      }
    }
    catch (Exception localException)
    {
      BlueprintEntry localBlueprintEntry = null;
      localException.printStackTrace();
    }
    return "-";
  }
  
  public final void a1()
  {
    ElementKeyMap.initializeData();
    this.field_809 = class_1216.field_1429.a7();
    fireTableDataChanged();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_491
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */