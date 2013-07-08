import javax.swing.table.AbstractTableModel;

final class class_679
  extends AbstractTableModel
{
  private static final long serialVersionUID = -1405695487570570316L;
  
  public final Object getValueAt(int paramInt1, int paramInt2)
  {
    return Integer.valueOf((paramInt2 << 4) + paramInt1);
  }
  
  public final int getRowCount()
  {
    return 16;
  }
  
  public final int getColumnCount()
  {
    return 16;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_679
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */