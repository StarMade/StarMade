package org.hsqldb.util;

import java.awt.Component;
import java.awt.Dimension;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

class GridSwing extends AbstractTableModel
{
  JTable jtable = null;
  Object[] headers = new Object[0];
  Vector rows = new Vector();

  public String getColumnName(int paramInt)
  {
    return this.headers[paramInt].toString();
  }

  public Class getColumnClass(int paramInt)
  {
    if (this.rows.size() > 0)
    {
      Object localObject = getValueAt(0, paramInt);
      if (localObject != null)
      {
        if (((localObject instanceof Timestamp)) || ((localObject instanceof Time)))
          return Object.class;
        return localObject.getClass();
      }
    }
    return super.getColumnClass(paramInt);
  }

  public int getColumnCount()
  {
    return this.headers.length;
  }

  public int getRowCount()
  {
    return this.rows.size();
  }

  public Object[] getHead()
  {
    return this.headers;
  }

  public Vector getData()
  {
    return this.rows;
  }

  public Object getValueAt(int paramInt1, int paramInt2)
  {
    if (paramInt1 >= this.rows.size())
      return null;
    Object[] arrayOfObject = (Object[])this.rows.elementAt(paramInt1);
    if (paramInt2 >= arrayOfObject.length)
      return null;
    return arrayOfObject[paramInt2];
  }

  public void setHead(Object[] paramArrayOfObject)
  {
    this.headers = new Object[paramArrayOfObject.length];
    for (int i = 0; i < paramArrayOfObject.length; i++)
      this.headers[i] = paramArrayOfObject[i];
  }

  public void addRow(Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject = new Object[paramArrayOfObject.length];
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      arrayOfObject[i] = paramArrayOfObject[i];
      if (arrayOfObject[i] != null);
    }
    this.rows.addElement(arrayOfObject);
  }

  public void clear()
  {
    this.rows.removeAllElements();
  }

  public void setJTable(JTable paramJTable)
  {
    this.jtable = paramJTable;
  }

  public void fireTableChanged(TableModelEvent paramTableModelEvent)
  {
    super.fireTableChanged(paramTableModelEvent);
    autoSizeTableColumns(this.jtable);
  }

  public static void autoSizeTableColumns(JTable paramJTable)
  {
    TableModel localTableModel = paramJTable.getModel();
    TableColumn localTableColumn = null;
    Component localComponent = null;
    int i = 0;
    int j = -2147483648;
    int k = 0;
    TableCellRenderer localTableCellRenderer1 = paramJTable.getTableHeader().getDefaultRenderer();
    for (int m = 0; m < paramJTable.getColumnCount(); m++)
    {
      localTableColumn = paramJTable.getColumnModel().getColumn(m);
      localComponent = localTableCellRenderer1.getTableCellRendererComponent(paramJTable, localTableColumn.getHeaderValue(), false, false, 0, 0);
      i = localComponent.getPreferredSize().width + 10;
      j = -2147483648;
      for (int n = 0; n < Math.min(localTableModel.getRowCount(), 30); n++)
      {
        TableCellRenderer localTableCellRenderer2 = paramJTable.getCellRenderer(n, m);
        localComponent = localTableCellRenderer2.getTableCellRendererComponent(paramJTable, localTableModel.getValueAt(n, m), false, false, n, m);
        k = localComponent.getPreferredSize().width;
        if (k >= j)
          j = k;
      }
      localTableColumn.setPreferredWidth(Math.max(i, j) + 10);
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.util.GridSwing
 * JD-Core Version:    0.6.2
 */