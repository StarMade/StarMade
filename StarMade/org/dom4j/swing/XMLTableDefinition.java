/*   1:    */package org.dom4j.swing;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.List;
/*   9:    */import java.util.Map;
/*  10:    */import org.dom4j.Document;
/*  11:    */import org.dom4j.DocumentHelper;
/*  12:    */import org.dom4j.Element;
/*  13:    */import org.dom4j.XPath;
/*  14:    */import org.jaxen.VariableContext;
/*  15:    */
/*  34:    */public class XMLTableDefinition
/*  35:    */  implements Serializable, VariableContext
/*  36:    */{
/*  37:    */  private XPath rowXPath;
/*  38: 38 */  private List columns = new ArrayList();
/*  39:    */  
/*  43:    */  private XMLTableColumnDefinition[] columnArray;
/*  44:    */  
/*  48:    */  private Map columnNameIndex;
/*  49:    */  
/*  53:    */  private VariableContext variableContext;
/*  54:    */  
/*  57:    */  private Object rowValue;
/*  58:    */  
/*  62:    */  public static XMLTableDefinition load(Document definition)
/*  63:    */  {
/*  64: 64 */    return load(definition.getRootElement());
/*  65:    */  }
/*  66:    */  
/*  74:    */  public static XMLTableDefinition load(Element definition)
/*  75:    */  {
/*  76: 76 */    XMLTableDefinition answer = new XMLTableDefinition();
/*  77: 77 */    answer.setRowExpression(definition.attributeValue("select"));
/*  78:    */    
/*  79: 79 */    Iterator iter = definition.elementIterator("column");
/*  80: 80 */    while (iter.hasNext()) {
/*  81: 81 */      Element element = (Element)iter.next();
/*  82: 82 */      String expression = element.attributeValue("select");
/*  83: 83 */      String name = element.getText();
/*  84: 84 */      String typeName = element.attributeValue("type", "string");
/*  85: 85 */      String columnXPath = element.attributeValue("columnNameXPath");
/*  86: 86 */      int type = XMLTableColumnDefinition.parseType(typeName);
/*  87:    */      
/*  88: 88 */      if (columnXPath != null) {
/*  89: 89 */        answer.addColumnWithXPathName(columnXPath, expression, type);
/*  90:    */      } else {
/*  91: 91 */        answer.addColumn(name, expression, type);
/*  92:    */      }
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    return answer;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public Class getColumnClass(int columnIndex) {
/*  99: 99 */    return getColumn(columnIndex).getColumnClass();
/* 100:    */  }
/* 101:    */  
/* 102:    */  public int getColumnCount() {
/* 103:103 */    return this.columns.size();
/* 104:    */  }
/* 105:    */  
/* 114:    */  public String getColumnName(int columnIndex)
/* 115:    */  {
/* 116:116 */    return getColumn(columnIndex).getName();
/* 117:    */  }
/* 118:    */  
/* 127:    */  public XPath getColumnXPath(int columnIndex)
/* 128:    */  {
/* 129:129 */    return getColumn(columnIndex).getXPath();
/* 130:    */  }
/* 131:    */  
/* 140:    */  public XPath getColumnNameXPath(int columnIndex)
/* 141:    */  {
/* 142:142 */    return getColumn(columnIndex).getColumnNameXPath();
/* 143:    */  }
/* 144:    */  
/* 145:    */  public synchronized Object getValueAt(Object row, int columnIndex) {
/* 146:146 */    XMLTableColumnDefinition column = getColumn(columnIndex);
/* 147:147 */    Object answer = null;
/* 148:    */    
/* 149:149 */    synchronized (this) {
/* 150:150 */      this.rowValue = row;
/* 151:151 */      answer = column.getValue(row);
/* 152:152 */      this.rowValue = null;
/* 153:    */    }
/* 154:    */    
/* 155:155 */    return answer;
/* 156:    */  }
/* 157:    */  
/* 158:    */  public void addColumn(String name, String expression) {
/* 159:159 */    addColumn(name, expression, 0);
/* 160:    */  }
/* 161:    */  
/* 162:    */  public void addColumn(String name, String expression, int type) {
/* 163:163 */    XPath xpath = createColumnXPath(expression);
/* 164:164 */    addColumn(new XMLTableColumnDefinition(name, xpath, type));
/* 165:    */  }
/* 166:    */  
/* 167:    */  public void addColumnWithXPathName(String columnNameXPathExpression, String expression, int type)
/* 168:    */  {
/* 169:169 */    XPath columnNameXPath = createColumnXPath(columnNameXPathExpression);
/* 170:170 */    XPath xpath = createColumnXPath(expression);
/* 171:171 */    addColumn(new XMLTableColumnDefinition(columnNameXPath, xpath, type));
/* 172:    */  }
/* 173:    */  
/* 174:    */  public void addStringColumn(String name, String expression) {
/* 175:175 */    addColumn(name, expression, 1);
/* 176:    */  }
/* 177:    */  
/* 178:    */  public void addNumberColumn(String name, String expression) {
/* 179:179 */    addColumn(name, expression, 2);
/* 180:    */  }
/* 181:    */  
/* 182:    */  public void addColumn(XMLTableColumnDefinition column) {
/* 183:183 */    clearCaches();
/* 184:184 */    this.columns.add(column);
/* 185:    */  }
/* 186:    */  
/* 187:    */  public void removeColumn(XMLTableColumnDefinition column) {
/* 188:188 */    clearCaches();
/* 189:189 */    this.columns.remove(column);
/* 190:    */  }
/* 191:    */  
/* 192:    */  public void clear() {
/* 193:193 */    clearCaches();
/* 194:194 */    this.columns.clear();
/* 195:    */  }
/* 196:    */  
/* 197:    */  public XMLTableColumnDefinition getColumn(int index) {
/* 198:198 */    if (this.columnArray == null) {
/* 199:199 */      this.columnArray = new XMLTableColumnDefinition[this.columns.size()];
/* 200:200 */      this.columns.toArray(this.columnArray);
/* 201:    */    }
/* 202:    */    
/* 203:203 */    return this.columnArray[index];
/* 204:    */  }
/* 205:    */  
/* 206:    */  public XMLTableColumnDefinition getColumn(String columnName) { Iterator it;
/* 207:207 */    if (this.columnNameIndex == null) {
/* 208:208 */      this.columnNameIndex = new HashMap();
/* 209:    */      
/* 210:210 */      for (it = this.columns.iterator(); it.hasNext();) {
/* 211:211 */        XMLTableColumnDefinition column = (XMLTableColumnDefinition)it.next();
/* 212:    */        
/* 213:213 */        this.columnNameIndex.put(column.getName(), column);
/* 214:    */      }
/* 215:    */    }
/* 216:    */    
/* 217:217 */    return (XMLTableColumnDefinition)this.columnNameIndex.get(columnName);
/* 218:    */  }
/* 219:    */  
/* 224:    */  public XPath getRowXPath()
/* 225:    */  {
/* 226:226 */    return this.rowXPath;
/* 227:    */  }
/* 228:    */  
/* 234:    */  public void setRowXPath(XPath rowXPath)
/* 235:    */  {
/* 236:236 */    this.rowXPath = rowXPath;
/* 237:    */  }
/* 238:    */  
/* 239:    */  public void setRowExpression(String xpath) {
/* 240:240 */    setRowXPath(createXPath(xpath));
/* 241:    */  }
/* 242:    */  
/* 245:    */  public Object getVariableValue(String namespaceURI, String prefix, String localName)
/* 246:    */  {
/* 247:247 */    XMLTableColumnDefinition column = getColumn(localName);
/* 248:    */    
/* 249:249 */    if (column != null) {
/* 250:250 */      return column.getValue(this.rowValue);
/* 251:    */    }
/* 252:    */    
/* 253:253 */    return null;
/* 254:    */  }
/* 255:    */  
/* 257:    */  protected XPath createXPath(String expression)
/* 258:    */  {
/* 259:259 */    return DocumentHelper.createXPath(expression);
/* 260:    */  }
/* 261:    */  
/* 262:    */  protected XPath createColumnXPath(String expression) {
/* 263:263 */    XPath xpath = createXPath(expression);
/* 264:    */    
/* 266:266 */    xpath.setVariableContext(this);
/* 267:    */    
/* 268:268 */    return xpath;
/* 269:    */  }
/* 270:    */  
/* 271:    */  protected void clearCaches() {
/* 272:272 */    this.columnArray = null;
/* 273:273 */    this.columnNameIndex = null;
/* 274:    */  }
/* 275:    */  
/* 276:    */  protected void handleException(Exception e)
/* 277:    */  {
/* 278:278 */    System.out.println("Caught: " + e);
/* 279:    */  }
/* 280:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.XMLTableDefinition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */