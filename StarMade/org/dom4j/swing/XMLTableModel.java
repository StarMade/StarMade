/*     */ package org.dom4j.swing;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.XPath;
/*     */ 
/*     */ public class XMLTableModel extends AbstractTableModel
/*     */ {
/*     */   private XMLTableDefinition definition;
/*     */   private Object source;
/*     */   private List rows;
/*     */ 
/*     */   public XMLTableModel(Element tableDefinition, Object source)
/*     */   {
/*  46 */     this(XMLTableDefinition.load(tableDefinition), source);
/*     */   }
/*     */ 
/*     */   public XMLTableModel(Document tableDefinition, Object source)
/*     */   {
/*  59 */     this(XMLTableDefinition.load(tableDefinition), source);
/*     */   }
/*     */ 
/*     */   public XMLTableModel(XMLTableDefinition definition, Object source) {
/*  63 */     this.definition = definition;
/*  64 */     this.source = source;
/*     */   }
/*     */ 
/*     */   public Object getRowValue(int rowIndex) {
/*  68 */     return getRows().get(rowIndex);
/*     */   }
/*     */ 
/*     */   public List getRows() {
/*  72 */     if (this.rows == null) {
/*  73 */       this.rows = this.definition.getRowXPath().selectNodes(this.source);
/*     */     }
/*     */ 
/*  76 */     return this.rows;
/*     */   }
/*     */ 
/*     */   public Class getColumnClass(int columnIndex)
/*     */   {
/*  82 */     return this.definition.getColumnClass(columnIndex);
/*     */   }
/*     */ 
/*     */   public int getColumnCount() {
/*  86 */     return this.definition.getColumnCount();
/*     */   }
/*     */ 
/*     */   public String getColumnName(int columnIndex) {
/*  90 */     XPath xpath = this.definition.getColumnNameXPath(columnIndex);
/*     */ 
/*  92 */     if (xpath != null) {
/*  93 */       System.out.println("Evaluating column xpath: " + xpath + " value: " + xpath.valueOf(this.source));
/*     */ 
/*  96 */       return xpath.valueOf(this.source);
/*     */     }
/*     */ 
/*  99 */     return this.definition.getColumnName(columnIndex);
/*     */   }
/*     */ 
/*     */   public Object getValueAt(int rowIndex, int columnIndex) {
/*     */     try {
/* 104 */       Object row = getRowValue(rowIndex);
/*     */ 
/* 106 */       return this.definition.getValueAt(row, columnIndex);
/*     */     } catch (Exception e) {
/* 108 */       handleException(e);
/*     */     }
/* 110 */     return null;
/*     */   }
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 115 */     return getRows().size();
/*     */   }
/*     */ 
/*     */   public XMLTableDefinition getDefinition()
/*     */   {
/* 127 */     return this.definition;
/*     */   }
/*     */ 
/*     */   public void setDefinition(XMLTableDefinition definition)
/*     */   {
/* 137 */     this.definition = definition;
/*     */   }
/*     */ 
/*     */   public Object getSource()
/*     */   {
/* 146 */     return this.source;
/*     */   }
/*     */ 
/*     */   public void setSource(Object source)
/*     */   {
/* 156 */     this.source = source;
/* 157 */     this.rows = null;
/*     */   }
/*     */ 
/*     */   protected void handleException(Exception e)
/*     */   {
/* 164 */     System.out.println("Caught: " + e);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.XMLTableModel
 * JD-Core Version:    0.6.2
 */