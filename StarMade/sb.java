/*  1:   */import java.awt.Component;
/*  2:   */import java.util.EventObject;
/*  3:   */import javax.swing.AbstractCellEditor;
/*  4:   */import javax.swing.JTable;
/*  5:   */import javax.swing.table.TableCellEditor;
/*  6:   */
/*  8:   */public final class sb
/*  9:   */  extends AbstractCellEditor
/* 10:   */  implements TableCellEditor
/* 11:   */{
/* 12:   */  public final Object getCellEditorValue()
/* 13:   */  {
/* 14:14 */    return null;
/* 15:   */  }
/* 16:   */  
/* 19:   */  public final Component getTableCellEditorComponent(JTable paramJTable, Object paramObject, boolean paramBoolean, int paramInt1, int paramInt2)
/* 20:   */  {
/* 21:21 */    return ((rY)paramObject).a();
/* 22:   */  }
/* 23:   */  
/* 42:   */  public final boolean isCellEditable(EventObject paramEventObject)
/* 43:   */  {
/* 44:44 */    return true;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public final boolean shouldSelectCell(EventObject paramEventObject)
/* 48:   */  {
/* 49:49 */    return false;
/* 50:   */  }
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */