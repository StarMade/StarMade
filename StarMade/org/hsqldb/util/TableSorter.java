package org.hsqldb.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class TableSorter extends AbstractTableModel
{
  protected TableModel tableModel;
  public static final int DESCENDING = -1;
  public static final int NOT_SORTED = 0;
  public static final int ASCENDING = 1;
  private static Directive EMPTY_DIRECTIVE = new Directive(-1, 0);
  public static final Comparator COMPARABLE_COMPARATOR = new Comparator()
  {
    public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
    {
      if (paramAnonymousObject1 == paramAnonymousObject2)
        return 0;
      if (paramAnonymousObject1 == null)
      {
        if (paramAnonymousObject2 == null)
          return 0;
        return -1;
      }
      if (paramAnonymousObject2 == null)
        return 1;
      return ((Comparable)paramAnonymousObject1).compareTo(paramAnonymousObject2);
    }
  };
  public static final Comparator LEXICAL_COMPARATOR = new Comparator()
  {
    public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
    {
      return paramAnonymousObject1.toString().compareTo(paramAnonymousObject2.toString());
    }
  };
  private Row[] viewToModel;
  private int[] modelToView;
  private JTableHeader tableHeader;
  private MouseListener mouseListener = new MouseHandler(null);
  private TableModelListener tableModelListener = new TableModelHandler(null);
  private Map columnComparators = new HashMap();
  private List sortingColumns = new ArrayList();

  public TableSorter()
  {
  }

  public TableSorter(TableModel paramTableModel)
  {
    this();
    setTableModel(paramTableModel);
  }

  public TableSorter(TableModel paramTableModel, JTableHeader paramJTableHeader)
  {
    this();
    setTableHeader(paramJTableHeader);
    setTableModel(paramTableModel);
  }

  private void clearSortingState()
  {
    this.viewToModel = null;
    this.modelToView = null;
  }

  public TableModel getTableModel()
  {
    return this.tableModel;
  }

  public void setTableModel(TableModel paramTableModel)
  {
    if (this.tableModel != null)
      this.tableModel.removeTableModelListener(this.tableModelListener);
    this.tableModel = paramTableModel;
    if (this.tableModel != null)
      this.tableModel.addTableModelListener(this.tableModelListener);
    clearSortingState();
    fireTableStructureChanged();
  }

  public JTableHeader getTableHeader()
  {
    return this.tableHeader;
  }

  public void setTableHeader(JTableHeader paramJTableHeader)
  {
    if (this.tableHeader != null)
    {
      this.tableHeader.removeMouseListener(this.mouseListener);
      TableCellRenderer localTableCellRenderer = this.tableHeader.getDefaultRenderer();
      if ((localTableCellRenderer instanceof SortableHeaderRenderer))
        this.tableHeader.setDefaultRenderer(((SortableHeaderRenderer)localTableCellRenderer).tableCellRenderer);
    }
    this.tableHeader = paramJTableHeader;
    if (this.tableHeader != null)
    {
      this.tableHeader.addMouseListener(this.mouseListener);
      this.tableHeader.setDefaultRenderer(new SortableHeaderRenderer(this.tableHeader.getDefaultRenderer()));
    }
  }

  public boolean isSorting()
  {
    return this.sortingColumns.size() != 0;
  }

  private Directive getDirective(int paramInt)
  {
    for (int i = 0; i < this.sortingColumns.size(); i++)
    {
      Directive localDirective = (Directive)this.sortingColumns.get(i);
      if (localDirective.column == paramInt)
        return localDirective;
    }
    return EMPTY_DIRECTIVE;
  }

  public int getSortingStatus(int paramInt)
  {
    return getDirective(paramInt).direction;
  }

  private void sortingStatusChanged()
  {
    clearSortingState();
    fireTableDataChanged();
    if (this.tableHeader != null)
      this.tableHeader.repaint();
  }

  public void setSortingStatus(int paramInt1, int paramInt2)
  {
    Directive localDirective = getDirective(paramInt1);
    if (localDirective != EMPTY_DIRECTIVE)
      this.sortingColumns.remove(localDirective);
    if (paramInt2 != 0)
      this.sortingColumns.add(new Directive(paramInt1, paramInt2));
    sortingStatusChanged();
  }

  protected Icon getHeaderRendererIcon(int paramInt1, int paramInt2)
  {
    Directive localDirective = getDirective(paramInt1);
    if (localDirective == EMPTY_DIRECTIVE)
      return null;
    return new Arrow(localDirective.direction == -1, paramInt2, this.sortingColumns.indexOf(localDirective));
  }

  private void cancelSorting()
  {
    this.sortingColumns.clear();
    sortingStatusChanged();
  }

  public void setColumnComparator(Class paramClass, Comparator paramComparator)
  {
    if (paramComparator == null)
      this.columnComparators.remove(paramClass);
    else
      this.columnComparators.put(paramClass, paramComparator);
  }

  protected Comparator getComparator(int paramInt)
  {
    Class localClass = this.tableModel.getColumnClass(paramInt);
    Comparator localComparator = (Comparator)this.columnComparators.get(localClass);
    if (localComparator != null)
      return localComparator;
    if (Comparable.class.isAssignableFrom(localClass))
      return COMPARABLE_COMPARATOR;
    return LEXICAL_COMPARATOR;
  }

  private Row[] getViewToModel()
  {
    if (this.viewToModel == null)
    {
      int i = this.tableModel.getRowCount();
      this.viewToModel = new Row[i];
      for (int j = 0; j < i; j++)
        this.viewToModel[j] = new Row(j);
      if (isSorting())
        Arrays.sort(this.viewToModel);
    }
    return this.viewToModel;
  }

  public int modelIndex(int paramInt)
  {
    return getViewToModel()[paramInt].modelIndex;
  }

  private int[] getModelToView()
  {
    if (this.modelToView == null)
    {
      int i = getViewToModel().length;
      this.modelToView = new int[i];
      for (int j = 0; j < i; j++)
        this.modelToView[modelIndex(j)] = j;
    }
    return this.modelToView;
  }

  public int getRowCount()
  {
    return this.tableModel == null ? 0 : this.tableModel.getRowCount();
  }

  public int getColumnCount()
  {
    return this.tableModel == null ? 0 : this.tableModel.getColumnCount();
  }

  public String getColumnName(int paramInt)
  {
    return this.tableModel.getColumnName(paramInt);
  }

  public Class getColumnClass(int paramInt)
  {
    return this.tableModel.getColumnClass(paramInt);
  }

  public boolean isCellEditable(int paramInt1, int paramInt2)
  {
    return this.tableModel.isCellEditable(modelIndex(paramInt1), paramInt2);
  }

  public Object getValueAt(int paramInt1, int paramInt2)
  {
    return this.tableModel.getValueAt(modelIndex(paramInt1), paramInt2);
  }

  public void setValueAt(Object paramObject, int paramInt1, int paramInt2)
  {
    this.tableModel.setValueAt(paramObject, modelIndex(paramInt1), paramInt2);
  }

  private static class Directive
  {
    private int column;
    private int direction;

    public Directive(int paramInt1, int paramInt2)
    {
      this.column = paramInt1;
      this.direction = paramInt2;
    }
  }

  private class SortableHeaderRenderer
    implements TableCellRenderer
  {
    private TableCellRenderer tableCellRenderer;

    public SortableHeaderRenderer(TableCellRenderer arg2)
    {
      Object localObject;
      this.tableCellRenderer = localObject;
    }

    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      Component localComponent = this.tableCellRenderer.getTableCellRendererComponent(paramJTable, paramObject, paramBoolean1, paramBoolean2, paramInt1, paramInt2);
      if ((localComponent instanceof JLabel))
      {
        JLabel localJLabel = (JLabel)localComponent;
        localJLabel.setHorizontalTextPosition(2);
        int i = paramJTable.convertColumnIndexToModel(paramInt2);
        localJLabel.setIcon(TableSorter.this.getHeaderRendererIcon(i, localJLabel.getFont().getSize()));
      }
      return localComponent;
    }
  }

  private static class Arrow
    implements Icon
  {
    private boolean descending;
    private int size;
    private int priority;

    public Arrow(boolean paramBoolean, int paramInt1, int paramInt2)
    {
      this.descending = paramBoolean;
      this.size = paramInt1;
      this.priority = paramInt2;
    }

    public void paintIcon(Component paramComponent, Graphics paramGraphics, int paramInt1, int paramInt2)
    {
      Color localColor = paramComponent == null ? Color.gray : paramComponent.getBackground();
      int i = (int)(this.size / 2 * Math.pow(0.8D, this.priority));
      int j = this.descending ? i : -i;
      paramInt2 = paramInt2 + 5 * this.size / 6 + (this.descending ? -j : 0);
      int k = this.descending ? 1 : -1;
      paramGraphics.translate(paramInt1, paramInt2);
      paramGraphics.setColor(localColor.darker());
      paramGraphics.drawLine(i / 2, j, 0, 0);
      paramGraphics.drawLine(i / 2, j + k, 0, k);
      paramGraphics.setColor(localColor.brighter());
      paramGraphics.drawLine(i / 2, j, i, 0);
      paramGraphics.drawLine(i / 2, j + k, i, k);
      if (this.descending)
        paramGraphics.setColor(localColor.darker().darker());
      else
        paramGraphics.setColor(localColor.brighter().brighter());
      paramGraphics.drawLine(i, 0, 0, 0);
      paramGraphics.setColor(localColor);
      paramGraphics.translate(-paramInt1, -paramInt2);
    }

    public int getIconWidth()
    {
      return this.size;
    }

    public int getIconHeight()
    {
      return this.size;
    }
  }

  private class MouseHandler extends MouseAdapter
  {
    private MouseHandler()
    {
    }

    public void mouseClicked(MouseEvent paramMouseEvent)
    {
      JTableHeader localJTableHeader = (JTableHeader)paramMouseEvent.getSource();
      TableColumnModel localTableColumnModel = localJTableHeader.getColumnModel();
      int i = localTableColumnModel.getColumnIndexAtX(paramMouseEvent.getX());
      int j = localTableColumnModel.getColumn(i).getModelIndex();
      if (j != -1)
      {
        int k = TableSorter.this.getSortingStatus(j);
        if (!paramMouseEvent.isControlDown())
          TableSorter.this.cancelSorting();
        k += (paramMouseEvent.isShiftDown() ? -1 : 1);
        k = (k + 4) % 3 - 1;
        TableSorter.this.setSortingStatus(j, k);
      }
    }
  }

  private class TableModelHandler
    implements TableModelListener
  {
    private TableModelHandler()
    {
    }

    public void tableChanged(TableModelEvent paramTableModelEvent)
    {
      if (!TableSorter.this.isSorting())
      {
        TableSorter.this.clearSortingState();
        TableSorter.this.fireTableChanged(paramTableModelEvent);
        return;
      }
      if ((paramTableModelEvent == null) || (paramTableModelEvent.getFirstRow() == -1))
      {
        TableSorter.this.cancelSorting();
        TableSorter.this.fireTableChanged(paramTableModelEvent);
        return;
      }
      int i = paramTableModelEvent.getColumn();
      if ((paramTableModelEvent.getFirstRow() == paramTableModelEvent.getLastRow()) && (i != -1) && (TableSorter.this.getSortingStatus(i) == 0) && (TableSorter.this.modelToView != null))
      {
        int j = TableSorter.this.getModelToView()[paramTableModelEvent.getFirstRow()];
        TableSorter.this.fireTableChanged(new TableModelEvent(TableSorter.this, j, j, i, paramTableModelEvent.getType()));
        return;
      }
      TableSorter.this.clearSortingState();
      TableSorter.this.fireTableDataChanged();
    }
  }

  private class Row
    implements Comparable
  {
    private int modelIndex;

    public Row(int arg2)
    {
      int i;
      this.modelIndex = i;
    }

    public int compareTo(Object paramObject)
    {
      int i = this.modelIndex;
      int j = ((Row)paramObject).modelIndex;
      Iterator localIterator = TableSorter.this.sortingColumns.iterator();
      while (localIterator.hasNext())
      {
        TableSorter.Directive localDirective = (TableSorter.Directive)localIterator.next();
        int k = localDirective.column;
        Object localObject1 = TableSorter.this.tableModel.getValueAt(i, k);
        Object localObject2 = TableSorter.this.tableModel.getValueAt(j, k);
        int m = 0;
        if ((localObject1 == null) && (localObject2 == null))
          m = 0;
        else if (localObject1 == null)
          m = -1;
        else if (localObject2 == null)
          m = 1;
        else
          m = TableSorter.this.getComparator(k).compare(localObject1, localObject2);
        if (m != 0)
          return localDirective.direction == -1 ? -m : m;
      }
      return 0;
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.util.TableSorter
 * JD-Core Version:    0.6.2
 */