/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.StringWriter;
/*   5:    */import java.io.Writer;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Map;
/*   9:    */import org.dom4j.Comment;
/*  10:    */import org.dom4j.Document;
/*  11:    */import org.dom4j.DocumentFactory;
/*  12:    */import org.dom4j.DocumentType;
/*  13:    */import org.dom4j.Element;
/*  14:    */import org.dom4j.IllegalAddException;
/*  15:    */import org.dom4j.Node;
/*  16:    */import org.dom4j.ProcessingInstruction;
/*  17:    */import org.dom4j.QName;
/*  18:    */import org.dom4j.Text;
/*  19:    */import org.dom4j.Visitor;
/*  20:    */import org.dom4j.io.OutputFormat;
/*  21:    */import org.dom4j.io.XMLWriter;
/*  22:    */
/*  41:    */public abstract class AbstractDocument
/*  42:    */  extends AbstractBranch
/*  43:    */  implements Document
/*  44:    */{
/*  45:    */  protected String encoding;
/*  46:    */  
/*  47:    */  public short getNodeType()
/*  48:    */  {
/*  49: 49 */    return 9;
/*  50:    */  }
/*  51:    */  
/*  52:    */  public String getPath(Element context) {
/*  53: 53 */    return "/";
/*  54:    */  }
/*  55:    */  
/*  56:    */  public String getUniquePath(Element context) {
/*  57: 57 */    return "/";
/*  58:    */  }
/*  59:    */  
/*  60:    */  public Document getDocument() {
/*  61: 61 */    return this;
/*  62:    */  }
/*  63:    */  
/*  64:    */  public String getXMLEncoding() {
/*  65: 65 */    return null;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public String getStringValue() {
/*  69: 69 */    Element root = getRootElement();
/*  70:    */    
/*  71: 71 */    return root != null ? root.getStringValue() : "";
/*  72:    */  }
/*  73:    */  
/*  74:    */  public String asXML() {
/*  75: 75 */    OutputFormat format = new OutputFormat();
/*  76: 76 */    format.setEncoding(this.encoding);
/*  77:    */    try
/*  78:    */    {
/*  79: 79 */      StringWriter out = new StringWriter();
/*  80: 80 */      XMLWriter writer = new XMLWriter(out, format);
/*  81: 81 */      writer.write(this);
/*  82: 82 */      writer.flush();
/*  83:    */      
/*  84: 84 */      return out.toString();
/*  85:    */    } catch (IOException e) {
/*  86: 86 */      throw new RuntimeException("IOException while generating textual representation: " + e.getMessage());
/*  87:    */    }
/*  88:    */  }
/*  89:    */  
/*  90:    */  public void write(Writer out) throws IOException
/*  91:    */  {
/*  92: 92 */    OutputFormat format = new OutputFormat();
/*  93: 93 */    format.setEncoding(this.encoding);
/*  94:    */    
/*  95: 95 */    XMLWriter writer = new XMLWriter(out, format);
/*  96: 96 */    writer.write(this);
/*  97:    */  }
/*  98:    */  
/* 107:    */  public void accept(Visitor visitor)
/* 108:    */  {
/* 109:109 */    visitor.visit(this);
/* 110:    */    
/* 111:111 */    DocumentType docType = getDocType();
/* 112:    */    
/* 113:113 */    if (docType != null) {
/* 114:114 */      visitor.visit(docType);
/* 115:    */    }
/* 116:    */    
/* 118:118 */    List content = content();
/* 119:    */    Iterator iter;
/* 120:120 */    if (content != null) {
/* 121:121 */      for (iter = content.iterator(); iter.hasNext();) {
/* 122:122 */        Object object = iter.next();
/* 123:    */        
/* 124:124 */        if ((object instanceof String)) {
/* 125:125 */          Text text = getDocumentFactory().createText((String)object);
/* 126:    */          
/* 127:127 */          visitor.visit(text);
/* 128:    */        } else {
/* 129:129 */          Node node = (Node)object;
/* 130:130 */          node.accept(visitor);
/* 131:    */        }
/* 132:    */      }
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 136:    */  public String toString() {
/* 137:137 */    return super.toString() + " [Document: name " + getName() + "]";
/* 138:    */  }
/* 139:    */  
/* 140:    */  public void normalize() {
/* 141:141 */    Element element = getRootElement();
/* 142:    */    
/* 143:143 */    if (element != null) {
/* 144:144 */      element.normalize();
/* 145:    */    }
/* 146:    */  }
/* 147:    */  
/* 148:    */  public Document addComment(String comment) {
/* 149:149 */    Comment node = getDocumentFactory().createComment(comment);
/* 150:150 */    add(node);
/* 151:    */    
/* 152:152 */    return this;
/* 153:    */  }
/* 154:    */  
/* 155:    */  public Document addProcessingInstruction(String target, String data) {
/* 156:156 */    ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
/* 157:    */    
/* 158:158 */    add(node);
/* 159:    */    
/* 160:160 */    return this;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public Document addProcessingInstruction(String target, Map data) {
/* 164:164 */    ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
/* 165:    */    
/* 166:166 */    add(node);
/* 167:    */    
/* 168:168 */    return this;
/* 169:    */  }
/* 170:    */  
/* 171:    */  public Element addElement(String name) {
/* 172:172 */    Element element = getDocumentFactory().createElement(name);
/* 173:173 */    add(element);
/* 174:    */    
/* 175:175 */    return element;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public Element addElement(String qualifiedName, String namespaceURI) {
/* 179:179 */    Element element = getDocumentFactory().createElement(qualifiedName, namespaceURI);
/* 180:    */    
/* 181:181 */    add(element);
/* 182:    */    
/* 183:183 */    return element;
/* 184:    */  }
/* 185:    */  
/* 186:    */  public Element addElement(QName qName) {
/* 187:187 */    Element element = getDocumentFactory().createElement(qName);
/* 188:188 */    add(element);
/* 189:    */    
/* 190:190 */    return element;
/* 191:    */  }
/* 192:    */  
/* 193:    */  public void setRootElement(Element rootElement) {
/* 194:194 */    clearContent();
/* 195:    */    
/* 196:196 */    if (rootElement != null) {
/* 197:197 */      super.add(rootElement);
/* 198:198 */      rootElementAdded(rootElement);
/* 199:    */    }
/* 200:    */  }
/* 201:    */  
/* 202:    */  public void add(Element element) {
/* 203:203 */    checkAddElementAllowed(element);
/* 204:204 */    super.add(element);
/* 205:205 */    rootElementAdded(element);
/* 206:    */  }
/* 207:    */  
/* 208:    */  public boolean remove(Element element) {
/* 209:209 */    boolean answer = super.remove(element);
/* 210:210 */    Element root = getRootElement();
/* 211:    */    
/* 212:212 */    if ((root != null) && (answer)) {
/* 213:213 */      setRootElement(null);
/* 214:    */    }
/* 215:    */    
/* 216:216 */    element.setDocument(null);
/* 217:    */    
/* 218:218 */    return answer;
/* 219:    */  }
/* 220:    */  
/* 221:    */  public Node asXPathResult(Element parent) {
/* 222:222 */    return this;
/* 223:    */  }
/* 224:    */  
/* 225:    */  protected void childAdded(Node node) {
/* 226:226 */    if (node != null) {
/* 227:227 */      node.setDocument(this);
/* 228:    */    }
/* 229:    */  }
/* 230:    */  
/* 231:    */  protected void childRemoved(Node node) {
/* 232:232 */    if (node != null) {
/* 233:233 */      node.setDocument(null);
/* 234:    */    }
/* 235:    */  }
/* 236:    */  
/* 237:    */  protected void checkAddElementAllowed(Element element) {
/* 238:238 */    Element root = getRootElement();
/* 239:    */    
/* 240:240 */    if (root != null) {
/* 241:241 */      throw new IllegalAddException(this, element, "Cannot add another element to this Document as it already has a root element of: " + root.getQualifiedName());
/* 242:    */    }
/* 243:    */  }
/* 244:    */  
/* 249:    */  protected abstract void rootElementAdded(Element paramElement);
/* 250:    */  
/* 255:    */  public void setXMLEncoding(String enc)
/* 256:    */  {
/* 257:257 */    this.encoding = enc;
/* 258:    */  }
/* 259:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractDocument
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */