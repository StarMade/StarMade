/*   1:    */package org.dom4j.swing;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.List;
/*   5:    */import javax.swing.table.AbstractTableModel;
/*   6:    */import org.dom4j.Document;
/*   7:    */import org.dom4j.Element;
/*   8:    */import org.dom4j.XPath;
/*   9:    */
/*  37:    */public class XMLTableModel
/*  38:    */  extends AbstractTableModel
/*  39:    */{
/*  40:    */  private XMLTableDefinition definition;
/*  41:    */  private Object source;
/*  42:    */  private List rows;
/*  43:    */  
/*  44:    */  public XMLTableModel(Element tableDefinition, Object source)
/*  45:    */  {
/*  46: 46 */    this(XMLTableDefinition.load(tableDefinition), source);
/*  47:    */  }
/*  48:    */  
/*  57:    */  public XMLTableModel(Document tableDefinition, Object source)
/*  58:    */  {
/*  59: 59 */    this(XMLTableDefinition.load(tableDefinition), source);
/*  60:    */  }
/*  61:    */  
/*  62:    */  public XMLTableModel(XMLTableDefinition definition, Object source) {
/*  63: 63 */    this.definition = definition;
/*  64: 64 */    this.source = source;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public Object getRowValue(int rowIndex) {
/*  68: 68 */    return getRows().get(rowIndex);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public List getRows() {
/*  72: 72 */    if (this.rows == null) {
/*  73: 73 */      this.rows = this.definition.getRowXPath().selectNodes(this.source);
/*  74:    */    }
/*  75:    */    
/*  76: 76 */    return this.rows;
/*  77:    */  }
/*  78:    */  
/*  80:    */  public Class getColumnClass(int columnIndex)
/*  81:    */  {
/*  82: 82 */    return this.definition.getColumnClass(columnIndex);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public int getColumnCount() {
/*  86: 86 */    return this.definition.getColumnCount();
/*  87:    */  }
/*  88:    */  
/*  89:    */  public String getColumnName(int columnIndex) {
/*  90: 90 */    XPath xpath = this.definition.getColumnNameXPath(columnIndex);
/*  91:    */    
/*  92: 92 */    if (xpath != null) {
/*  93: 93 */      System.out.println("Evaluating column xpath: " + xpath + " value: " + xpath.valueOf(this.source));
/*  94:    */      
/*  96: 96 */      return xpath.valueOf(this.source);
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    return this.definition.getColumnName(columnIndex);
/* 100:    */  }
/* 101:    */  
/* 102:    */  public Object getValueAt(int rowIndex, int columnIndex) {
/* 103:    */    try {
/* 104:104 */      Object row = getRowValue(rowIndex);
/* 105:    */      
/* 106:106 */      return this.definition.getValueAt(row, columnIndex);
/* 107:    */    } catch (Exception e) {
/* 108:108 */      handleException(e);
/* 109:    */    }
/* 110:110 */    return null;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public int getRowCount()
/* 114:    */  {
/* 115:115 */    return getRows().size();
/* 116:    */  }
/* 117:    */  
/* 125:    */  public XMLTableDefinition getDefinition()
/* 126:    */  {
/* 127:127 */    return this.definition;
/* 128:    */  }
/* 129:    */  
/* 135:    */  public void setDefinition(XMLTableDefinition definition)
/* 136:    */  {
/* 137:137 */    this.definition = definition;
/* 138:    */  }
/* 139:    */  
/* 144:    */  public Object getSource()
/* 145:    */  {
/* 146:146 */    return this.source;
/* 147:    */  }
/* 148:    */  
/* 154:    */  public void setSource(Object source)
/* 155:    */  {
/* 156:156 */    this.source = source;
/* 157:157 */    this.rows = null;
/* 158:    */  }
/* 159:    */  
/* 162:    */  protected void handleException(Exception e)
/* 163:    */  {
/* 164:164 */    System.out.println("Caught: " + e);
/* 165:    */  }
/* 166:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.XMLTableModel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */