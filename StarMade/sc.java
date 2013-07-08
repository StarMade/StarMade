/*  1:   */import java.awt.Component;
/*  2:   */import javax.swing.JLabel;
/*  3:   */import javax.swing.JTable;
/*  4:   */import javax.swing.table.TableCellRenderer;
/*  5:   */
/* 10:   */public final class sc
/* 11:   */  extends JLabel
/* 12:   */  implements TableCellRenderer
/* 13:   */{
/* 14:   */  public final Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
/* 15:   */  {
/* 16:16 */    paramJTable = (rY)paramObject;
/* 17:17 */    if (paramInt2 == 0) {
/* 18:18 */      setText(paramJTable.a());
/* 19:19 */      return this;
/* 20:   */    }
/* 21:21 */    return paramJTable.a();
/* 22:   */  }
/* 23:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */