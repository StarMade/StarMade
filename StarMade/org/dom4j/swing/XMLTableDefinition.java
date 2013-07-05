/*     */ package org.dom4j.swing;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.XPath;
/*     */ import org.jaxen.VariableContext;
/*     */ 
/*     */ public class XMLTableDefinition
/*     */   implements Serializable, VariableContext
/*     */ {
/*     */   private XPath rowXPath;
/*  38 */   private List columns = new ArrayList();
/*     */   private XMLTableColumnDefinition[] columnArray;
/*     */   private Map columnNameIndex;
/*     */   private VariableContext variableContext;
/*     */   private Object rowValue;
/*     */ 
/*     */   public static XMLTableDefinition load(Document definition)
/*     */   {
/*  64 */     return load(definition.getRootElement());
/*     */   }
/*     */ 
/*     */   public static XMLTableDefinition load(Element definition)
/*     */   {
/*  76 */     XMLTableDefinition answer = new XMLTableDefinition();
/*  77 */     answer.setRowExpression(definition.attributeValue("select"));
/*     */ 
/*  79 */     Iterator iter = definition.elementIterator("column");
/*  80 */     while (iter.hasNext()) {
/*  81 */       Element element = (Element)iter.next();
/*  82 */       String expression = element.attributeValue("select");
/*  83 */       String name = element.getText();
/*  84 */       String typeName = element.attributeValue("type", "string");
/*  85 */       String columnXPath = element.attributeValue("columnNameXPath");
/*  86 */       int type = XMLTableColumnDefinition.parseType(typeName);
/*     */ 
/*  88 */       if (columnXPath != null)
/*  89 */         answer.addColumnWithXPathName(columnXPath, expression, type);
/*     */       else {
/*  91 */         answer.addColumn(name, expression, type);
/*     */       }
/*     */     }
/*     */ 
/*  95 */     return answer;
/*     */   }
/*     */ 
/*     */   public Class getColumnClass(int columnIndex) {
/*  99 */     return getColumn(columnIndex).getColumnClass();
/*     */   }
/*     */ 
/*     */   public int getColumnCount() {
/* 103 */     return this.columns.size();
/*     */   }
/*     */ 
/*     */   public String getColumnName(int columnIndex)
/*     */   {
/* 116 */     return getColumn(columnIndex).getName();
/*     */   }
/*     */ 
/*     */   public XPath getColumnXPath(int columnIndex)
/*     */   {
/* 129 */     return getColumn(columnIndex).getXPath();
/*     */   }
/*     */ 
/*     */   public XPath getColumnNameXPath(int columnIndex)
/*     */   {
/* 142 */     return getColumn(columnIndex).getColumnNameXPath();
/*     */   }
/*     */ 
/*     */   public synchronized Object getValueAt(Object row, int columnIndex) {
/* 146 */     XMLTableColumnDefinition column = getColumn(columnIndex);
/* 147 */     Object answer = null;
/*     */ 
/* 149 */     synchronized (this) {
/* 150 */       this.rowValue = row;
/* 151 */       answer = column.getValue(row);
/* 152 */       this.rowValue = null;
/*     */     }
/*     */ 
/* 155 */     return answer;
/*     */   }
/*     */ 
/*     */   public void addColumn(String name, String expression) {
/* 159 */     addColumn(name, expression, 0);
/*     */   }
/*     */ 
/*     */   public void addColumn(String name, String expression, int type) {
/* 163 */     XPath xpath = createColumnXPath(expression);
/* 164 */     addColumn(new XMLTableColumnDefinition(name, xpath, type));
/*     */   }
/*     */ 
/*     */   public void addColumnWithXPathName(String columnNameXPathExpression, String expression, int type)
/*     */   {
/* 169 */     XPath columnNameXPath = createColumnXPath(columnNameXPathExpression);
/* 170 */     XPath xpath = createColumnXPath(expression);
/* 171 */     addColumn(new XMLTableColumnDefinition(columnNameXPath, xpath, type));
/*     */   }
/*     */ 
/*     */   public void addStringColumn(String name, String expression) {
/* 175 */     addColumn(name, expression, 1);
/*     */   }
/*     */ 
/*     */   public void addNumberColumn(String name, String expression) {
/* 179 */     addColumn(name, expression, 2);
/*     */   }
/*     */ 
/*     */   public void addColumn(XMLTableColumnDefinition column) {
/* 183 */     clearCaches();
/* 184 */     this.columns.add(column);
/*     */   }
/*     */ 
/*     */   public void removeColumn(XMLTableColumnDefinition column) {
/* 188 */     clearCaches();
/* 189 */     this.columns.remove(column);
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 193 */     clearCaches();
/* 194 */     this.columns.clear();
/*     */   }
/*     */ 
/*     */   public XMLTableColumnDefinition getColumn(int index) {
/* 198 */     if (this.columnArray == null) {
/* 199 */       this.columnArray = new XMLTableColumnDefinition[this.columns.size()];
/* 200 */       this.columns.toArray(this.columnArray);
/*     */     }
/*     */ 
/* 203 */     return this.columnArray[index];
/*     */   }
/*     */ 
/*     */   public XMLTableColumnDefinition getColumn(String columnName)
/*     */   {
/*     */     Iterator it;
/* 207 */     if (this.columnNameIndex == null) {
/* 208 */       this.columnNameIndex = new HashMap();
/*     */ 
/* 210 */       for (it = this.columns.iterator(); it.hasNext(); ) {
/* 211 */         XMLTableColumnDefinition column = (XMLTableColumnDefinition)it.next();
/*     */ 
/* 213 */         this.columnNameIndex.put(column.getName(), column);
/*     */       }
/*     */     }
/*     */ 
/* 217 */     return (XMLTableColumnDefinition)this.columnNameIndex.get(columnName);
/*     */   }
/*     */ 
/*     */   public XPath getRowXPath()
/*     */   {
/* 226 */     return this.rowXPath;
/*     */   }
/*     */ 
/*     */   public void setRowXPath(XPath rowXPath)
/*     */   {
/* 236 */     this.rowXPath = rowXPath;
/*     */   }
/*     */ 
/*     */   public void setRowExpression(String xpath) {
/* 240 */     setRowXPath(createXPath(xpath));
/*     */   }
/*     */ 
/*     */   public Object getVariableValue(String namespaceURI, String prefix, String localName)
/*     */   {
/* 247 */     XMLTableColumnDefinition column = getColumn(localName);
/*     */ 
/* 249 */     if (column != null) {
/* 250 */       return column.getValue(this.rowValue);
/*     */     }
/*     */ 
/* 253 */     return null;
/*     */   }
/*     */ 
/*     */   protected XPath createXPath(String expression)
/*     */   {
/* 259 */     return DocumentHelper.createXPath(expression);
/*     */   }
/*     */ 
/*     */   protected XPath createColumnXPath(String expression) {
/* 263 */     XPath xpath = createXPath(expression);
/*     */ 
/* 266 */     xpath.setVariableContext(this);
/*     */ 
/* 268 */     return xpath;
/*     */   }
/*     */ 
/*     */   protected void clearCaches() {
/* 272 */     this.columnArray = null;
/* 273 */     this.columnNameIndex = null;
/*     */   }
/*     */ 
/*     */   protected void handleException(Exception e)
/*     */   {
/* 278 */     System.out.println("Caught: " + e);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.XMLTableDefinition
 * JD-Core Version:    0.6.2
 */