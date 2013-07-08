import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public final class class_1279
  extends AbstractTableModel
{
  private final ArrayList field_1464 = new ArrayList();
  
  public final int getColumnCount()
  {
    return 2;
  }
  
  public final int getRowCount()
  {
    return this.field_1464.size();
  }
  
  public final Class getColumnClass(int paramInt)
  {
    if (paramInt == 0) {
      return String.class;
    }
    return rY.class;
  }
  
  public final boolean isCellEditable(int paramInt1, int paramInt2)
  {
    return (paramInt2 > 0) && (((class_1284)this.field_1464.get(paramInt1)).a2());
  }
  
  public final ArrayList a()
  {
    return this.field_1464;
  }
  
  public final void a1()
  {
    for (int i = 0; i < this.field_1464.size(); i++) {
      if (((class_1284)this.field_1464.get(i)).b()) {
        fireTableCellUpdated(i, 1);
      }
    }
  }
  
  public final String getColumnName(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return "Setting";
    case 1: 
      return "Value";
    }
    return "-";
  }
  
  public final Object getValueAt(int paramInt1, int paramInt2)
  {
    return this.field_1464.get(paramInt1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1279
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */