/*   1:    */package org.dom4j.swing;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.io.Serializable;
/*   5:    */import org.dom4j.DocumentHelper;
/*   6:    */import org.dom4j.Node;
/*   7:    */import org.dom4j.XPath;
/*   8:    */
/*  33:    */public class XMLTableColumnDefinition
/*  34:    */  implements Serializable
/*  35:    */{
/*  36:    */  public static final int OBJECT_TYPE = 0;
/*  37:    */  public static final int STRING_TYPE = 1;
/*  38:    */  public static final int NUMBER_TYPE = 2;
/*  39:    */  public static final int NODE_TYPE = 3;
/*  40:    */  private int type;
/*  41:    */  private String name;
/*  42:    */  private XPath xpath;
/*  43:    */  private XPath columnNameXPath;
/*  44:    */  
/*  45:    */  public XMLTableColumnDefinition() {}
/*  46:    */  
/*  47:    */  public XMLTableColumnDefinition(String name, String expression, int type)
/*  48:    */  {
/*  49: 49 */    this.name = name;
/*  50: 50 */    this.type = type;
/*  51: 51 */    this.xpath = createXPath(expression);
/*  52:    */  }
/*  53:    */  
/*  54:    */  public XMLTableColumnDefinition(String name, XPath xpath, int type) {
/*  55: 55 */    this.name = name;
/*  56: 56 */    this.xpath = xpath;
/*  57: 57 */    this.type = type;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public XMLTableColumnDefinition(XPath columnXPath, XPath xpath, int type) {
/*  61: 61 */    this.xpath = xpath;
/*  62: 62 */    this.columnNameXPath = columnXPath;
/*  63: 63 */    this.type = type;
/*  64:    */  }
/*  65:    */  
/*  66:    */  public static int parseType(String typeName) {
/*  67: 67 */    if ((typeName != null) && (typeName.length() > 0)) {
/*  68: 68 */      if (typeName.equals("string"))
/*  69: 69 */        return 1;
/*  70: 70 */      if (typeName.equals("number"))
/*  71: 71 */        return 2;
/*  72: 72 */      if (typeName.equals("node")) {
/*  73: 73 */        return 3;
/*  74:    */      }
/*  75:    */    }
/*  76:    */    
/*  77: 77 */    return 0;
/*  78:    */  }
/*  79:    */  
/*  80:    */  public Class getColumnClass() {
/*  81: 81 */    switch (this.type) {
/*  82:    */    case 1: 
/*  83: 83 */      return String.class;
/*  84:    */    
/*  85:    */    case 2: 
/*  86: 86 */      return Number.class;
/*  87:    */    
/*  88:    */    case 3: 
/*  89: 89 */      return Node.class;
/*  90:    */    }
/*  91:    */    
/*  92: 92 */    return Object.class;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public Object getValue(Object row)
/*  96:    */  {
/*  97: 97 */    switch (this.type) {
/*  98:    */    case 1: 
/*  99: 99 */      return this.xpath.valueOf(row);
/* 100:    */    
/* 101:    */    case 2: 
/* 102:102 */      return this.xpath.numberValueOf(row);
/* 103:    */    
/* 104:    */    case 3: 
/* 105:105 */      return this.xpath.selectSingleNode(row);
/* 106:    */    }
/* 107:    */    
/* 108:108 */    return this.xpath.evaluate(row);
/* 109:    */  }
/* 110:    */  
/* 119:    */  public int getType()
/* 120:    */  {
/* 121:121 */    return this.type;
/* 122:    */  }
/* 123:    */  
/* 129:    */  public void setType(int type)
/* 130:    */  {
/* 131:131 */    this.type = type;
/* 132:    */  }
/* 133:    */  
/* 138:    */  public String getName()
/* 139:    */  {
/* 140:140 */    return this.name;
/* 141:    */  }
/* 142:    */  
/* 148:    */  public void setName(String name)
/* 149:    */  {
/* 150:150 */    this.name = name;
/* 151:    */  }
/* 152:    */  
/* 157:    */  public XPath getXPath()
/* 158:    */  {
/* 159:159 */    return this.xpath;
/* 160:    */  }
/* 161:    */  
/* 167:    */  public void setXPath(XPath xPath)
/* 168:    */  {
/* 169:169 */    this.xpath = xPath;
/* 170:    */  }
/* 171:    */  
/* 176:    */  public XPath getColumnNameXPath()
/* 177:    */  {
/* 178:178 */    return this.columnNameXPath;
/* 179:    */  }
/* 180:    */  
/* 186:    */  public void setColumnNameXPath(XPath columnNameXPath)
/* 187:    */  {
/* 188:188 */    this.columnNameXPath = columnNameXPath;
/* 189:    */  }
/* 190:    */  
/* 192:    */  protected XPath createXPath(String expression)
/* 193:    */  {
/* 194:194 */    return DocumentHelper.createXPath(expression);
/* 195:    */  }
/* 196:    */  
/* 197:    */  protected void handleException(Exception e)
/* 198:    */  {
/* 199:199 */    System.out.println("Caught: " + e);
/* 200:    */  }
/* 201:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.XMLTableColumnDefinition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */