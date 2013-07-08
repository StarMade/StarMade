/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.io.Writer;
/*   6:    */import java.util.List;
/*   7:    */import org.dom4j.Document;
/*   8:    */import org.dom4j.DocumentFactory;
/*   9:    */import org.dom4j.Element;
/*  10:    */import org.dom4j.Node;
/*  11:    */import org.dom4j.NodeFilter;
/*  12:    */import org.dom4j.XPath;
/*  13:    */import org.dom4j.rule.Pattern;
/*  14:    */
/*  30:    */public abstract class AbstractNode
/*  31:    */  implements Node, Cloneable, Serializable
/*  32:    */{
/*  33: 33 */  protected static final String[] NODE_TYPE_NAMES = { "Node", "Element", "Attribute", "Text", "CDATA", "Entity", "Entity", "ProcessingInstruction", "Comment", "Document", "DocumentType", "DocumentFragment", "Notation", "Namespace", "Unknown" };
/*  34:    */  
/*  39: 39 */  private static final DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();
/*  40:    */  
/*  44:    */  public short getNodeType()
/*  45:    */  {
/*  46: 46 */    return 14;
/*  47:    */  }
/*  48:    */  
/*  49:    */  public String getNodeTypeName() {
/*  50: 50 */    int type = getNodeType();
/*  51:    */    
/*  52: 52 */    if ((type < 0) || (type >= NODE_TYPE_NAMES.length)) {
/*  53: 53 */      return "Unknown";
/*  54:    */    }
/*  55:    */    
/*  56: 56 */    return NODE_TYPE_NAMES[type];
/*  57:    */  }
/*  58:    */  
/*  59:    */  public Document getDocument() {
/*  60: 60 */    Element element = getParent();
/*  61:    */    
/*  62: 62 */    return element != null ? element.getDocument() : null;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public void setDocument(Document document) {}
/*  66:    */  
/*  67:    */  public Element getParent()
/*  68:    */  {
/*  69: 69 */    return null;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void setParent(Element parent) {}
/*  73:    */  
/*  74:    */  public boolean supportsParent()
/*  75:    */  {
/*  76: 76 */    return false;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public boolean isReadOnly() {
/*  80: 80 */    return true;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public boolean hasContent() {
/*  84: 84 */    return false;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public String getPath() {
/*  88: 88 */    return getPath(null);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public String getUniquePath() {
/*  92: 92 */    return getUniquePath(null);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public Object clone() {
/*  96: 96 */    if (isReadOnly()) {
/*  97: 97 */      return this;
/*  98:    */    }
/*  99:    */    try {
/* 100:100 */      Node answer = (Node)super.clone();
/* 101:101 */      answer.setParent(null);
/* 102:102 */      answer.setDocument(null);
/* 103:    */      
/* 104:104 */      return answer;
/* 105:    */    }
/* 106:    */    catch (CloneNotSupportedException e) {
/* 107:107 */      throw new RuntimeException("This should never happen. Caught: " + e);
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 112:    */  public Node detach()
/* 113:    */  {
/* 114:114 */    Element parent = getParent();
/* 115:    */    
/* 116:116 */    if (parent != null) {
/* 117:117 */      parent.remove(this);
/* 118:    */    } else {
/* 119:119 */      Document document = getDocument();
/* 120:    */      
/* 121:121 */      if (document != null) {
/* 122:122 */        document.remove(this);
/* 123:    */      }
/* 124:    */    }
/* 125:    */    
/* 126:126 */    setParent(null);
/* 127:127 */    setDocument(null);
/* 128:    */    
/* 129:129 */    return this;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public String getName() {
/* 133:133 */    return null;
/* 134:    */  }
/* 135:    */  
/* 136:    */  public void setName(String name) {
/* 137:137 */    throw new UnsupportedOperationException("This node cannot be modified");
/* 138:    */  }
/* 139:    */  
/* 140:    */  public String getText() {
/* 141:141 */    return null;
/* 142:    */  }
/* 143:    */  
/* 144:    */  public String getStringValue() {
/* 145:145 */    return getText();
/* 146:    */  }
/* 147:    */  
/* 148:    */  public void setText(String text) {
/* 149:149 */    throw new UnsupportedOperationException("This node cannot be modified");
/* 150:    */  }
/* 151:    */  
/* 152:    */  public void write(Writer writer) throws IOException {
/* 153:153 */    writer.write(asXML());
/* 154:    */  }
/* 155:    */  
/* 156:    */  public Object selectObject(String xpathExpression)
/* 157:    */  {
/* 158:158 */    XPath xpath = createXPath(xpathExpression);
/* 159:    */    
/* 160:160 */    return xpath.evaluate(this);
/* 161:    */  }
/* 162:    */  
/* 163:    */  public List selectNodes(String xpathExpression) {
/* 164:164 */    XPath xpath = createXPath(xpathExpression);
/* 165:    */    
/* 166:166 */    return xpath.selectNodes(this);
/* 167:    */  }
/* 168:    */  
/* 169:    */  public List selectNodes(String xpathExpression, String comparisonXPathExpression)
/* 170:    */  {
/* 171:171 */    return selectNodes(xpathExpression, comparisonXPathExpression, false);
/* 172:    */  }
/* 173:    */  
/* 174:    */  public List selectNodes(String xpathExpression, String comparisonXPathExpression, boolean removeDuplicates)
/* 175:    */  {
/* 176:176 */    XPath xpath = createXPath(xpathExpression);
/* 177:177 */    XPath sortBy = createXPath(comparisonXPathExpression);
/* 178:    */    
/* 179:179 */    return xpath.selectNodes(this, sortBy, removeDuplicates);
/* 180:    */  }
/* 181:    */  
/* 182:    */  public Node selectSingleNode(String xpathExpression) {
/* 183:183 */    XPath xpath = createXPath(xpathExpression);
/* 184:    */    
/* 185:185 */    return xpath.selectSingleNode(this);
/* 186:    */  }
/* 187:    */  
/* 188:    */  public String valueOf(String xpathExpression) {
/* 189:189 */    XPath xpath = createXPath(xpathExpression);
/* 190:    */    
/* 191:191 */    return xpath.valueOf(this);
/* 192:    */  }
/* 193:    */  
/* 194:    */  public Number numberValueOf(String xpathExpression) {
/* 195:195 */    XPath xpath = createXPath(xpathExpression);
/* 196:    */    
/* 197:197 */    return xpath.numberValueOf(this);
/* 198:    */  }
/* 199:    */  
/* 200:    */  public boolean matches(String patternText) {
/* 201:201 */    NodeFilter filter = createXPathFilter(patternText);
/* 202:    */    
/* 203:203 */    return filter.matches(this);
/* 204:    */  }
/* 205:    */  
/* 206:    */  public XPath createXPath(String xpathExpression) {
/* 207:207 */    return getDocumentFactory().createXPath(xpathExpression);
/* 208:    */  }
/* 209:    */  
/* 210:    */  public NodeFilter createXPathFilter(String patternText) {
/* 211:211 */    return getDocumentFactory().createXPathFilter(patternText);
/* 212:    */  }
/* 213:    */  
/* 214:    */  public Pattern createPattern(String patternText) {
/* 215:215 */    return getDocumentFactory().createPattern(patternText);
/* 216:    */  }
/* 217:    */  
/* 218:    */  public Node asXPathResult(Element parent) {
/* 219:219 */    if (supportsParent()) {
/* 220:220 */      return this;
/* 221:    */    }
/* 222:    */    
/* 223:223 */    return createXPathResult(parent);
/* 224:    */  }
/* 225:    */  
/* 226:    */  protected DocumentFactory getDocumentFactory() {
/* 227:227 */    return DOCUMENT_FACTORY;
/* 228:    */  }
/* 229:    */  
/* 230:    */  protected Node createXPathResult(Element parent) {
/* 231:231 */    throw new RuntimeException("asXPathResult() not yet implemented fully for: " + this);
/* 232:    */  }
/* 233:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */