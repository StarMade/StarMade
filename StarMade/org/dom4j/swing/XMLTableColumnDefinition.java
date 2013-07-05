/*     */ package org.dom4j.swing;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.XPath;
/*     */ 
/*     */ public class XMLTableColumnDefinition
/*     */   implements Serializable
/*     */ {
/*     */   public static final int OBJECT_TYPE = 0;
/*     */   public static final int STRING_TYPE = 1;
/*     */   public static final int NUMBER_TYPE = 2;
/*     */   public static final int NODE_TYPE = 3;
/*     */   private int type;
/*     */   private String name;
/*     */   private XPath xpath;
/*     */   private XPath columnNameXPath;
/*     */ 
/*     */   public XMLTableColumnDefinition()
/*     */   {
/*     */   }
/*     */ 
/*     */   public XMLTableColumnDefinition(String name, String expression, int type)
/*     */   {
/*  49 */     this.name = name;
/*  50 */     this.type = type;
/*  51 */     this.xpath = createXPath(expression);
/*     */   }
/*     */ 
/*     */   public XMLTableColumnDefinition(String name, XPath xpath, int type) {
/*  55 */     this.name = name;
/*  56 */     this.xpath = xpath;
/*  57 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public XMLTableColumnDefinition(XPath columnXPath, XPath xpath, int type) {
/*  61 */     this.xpath = xpath;
/*  62 */     this.columnNameXPath = columnXPath;
/*  63 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public static int parseType(String typeName) {
/*  67 */     if ((typeName != null) && (typeName.length() > 0)) {
/*  68 */       if (typeName.equals("string"))
/*  69 */         return 1;
/*  70 */       if (typeName.equals("number"))
/*  71 */         return 2;
/*  72 */       if (typeName.equals("node")) {
/*  73 */         return 3;
/*     */       }
/*     */     }
/*     */ 
/*  77 */     return 0;
/*     */   }
/*     */ 
/*     */   public Class getColumnClass() {
/*  81 */     switch (this.type) {
/*     */     case 1:
/*  83 */       return String.class;
/*     */     case 2:
/*  86 */       return Number.class;
/*     */     case 3:
/*  89 */       return Node.class;
/*     */     }
/*     */ 
/*  92 */     return Object.class;
/*     */   }
/*     */ 
/*     */   public Object getValue(Object row)
/*     */   {
/*  97 */     switch (this.type) {
/*     */     case 1:
/*  99 */       return this.xpath.valueOf(row);
/*     */     case 2:
/* 102 */       return this.xpath.numberValueOf(row);
/*     */     case 3:
/* 105 */       return this.xpath.selectSingleNode(row);
/*     */     }
/*     */ 
/* 108 */     return this.xpath.evaluate(row);
/*     */   }
/*     */ 
/*     */   public int getType()
/*     */   {
/* 121 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(int type)
/*     */   {
/* 131 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 140 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 150 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public XPath getXPath()
/*     */   {
/* 159 */     return this.xpath;
/*     */   }
/*     */ 
/*     */   public void setXPath(XPath xPath)
/*     */   {
/* 169 */     this.xpath = xPath;
/*     */   }
/*     */ 
/*     */   public XPath getColumnNameXPath()
/*     */   {
/* 178 */     return this.columnNameXPath;
/*     */   }
/*     */ 
/*     */   public void setColumnNameXPath(XPath columnNameXPath)
/*     */   {
/* 188 */     this.columnNameXPath = columnNameXPath;
/*     */   }
/*     */ 
/*     */   protected XPath createXPath(String expression)
/*     */   {
/* 194 */     return DocumentHelper.createXPath(expression);
/*     */   }
/*     */ 
/*     */   protected void handleException(Exception e)
/*     */   {
/* 199 */     System.out.println("Caught: " + e);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.XMLTableColumnDefinition
 * JD-Core Version:    0.6.2
 */