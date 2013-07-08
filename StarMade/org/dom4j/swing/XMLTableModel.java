package org.dom4j.swing;

import java.io.PrintStream;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;

public class XMLTableModel
  extends AbstractTableModel
{
  private XMLTableDefinition definition;
  private Object source;
  private List rows;
  
  public XMLTableModel(Element tableDefinition, Object source)
  {
    this(XMLTableDefinition.load(tableDefinition), source);
  }
  
  public XMLTableModel(Document tableDefinition, Object source)
  {
    this(XMLTableDefinition.load(tableDefinition), source);
  }
  
  public XMLTableModel(XMLTableDefinition definition, Object source)
  {
    this.definition = definition;
    this.source = source;
  }
  
  public Object getRowValue(int rowIndex)
  {
    return getRows().get(rowIndex);
  }
  
  public List getRows()
  {
    if (this.rows == null) {
      this.rows = this.definition.getRowXPath().selectNodes(this.source);
    }
    return this.rows;
  }
  
  public Class getColumnClass(int columnIndex)
  {
    return this.definition.getColumnClass(columnIndex);
  }
  
  public int getColumnCount()
  {
    return this.definition.getColumnCount();
  }
  
  public String getColumnName(int columnIndex)
  {
    XPath xpath = this.definition.getColumnNameXPath(columnIndex);
    if (xpath != null)
    {
      System.out.println("Evaluating column xpath: " + xpath + " value: " + xpath.valueOf(this.source));
      return xpath.valueOf(this.source);
    }
    return this.definition.getColumnName(columnIndex);
  }
  
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    try
    {
      Object row = getRowValue(rowIndex);
      return this.definition.getValueAt(row, columnIndex);
    }
    catch (Exception row)
    {
      handleException(row);
    }
    return null;
  }
  
  public int getRowCount()
  {
    return getRows().size();
  }
  
  public XMLTableDefinition getDefinition()
  {
    return this.definition;
  }
  
  public void setDefinition(XMLTableDefinition definition)
  {
    this.definition = definition;
  }
  
  public Object getSource()
  {
    return this.source;
  }
  
  public void setSource(Object source)
  {
    this.source = source;
    this.rows = null;
  }
  
  protected void handleException(Exception local_e)
  {
    System.out.println("Caught: " + local_e);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.swing.XMLTableModel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */