/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.List;
/*   6:    */import org.dom4j.Branch;
/*   7:    */import org.dom4j.DocumentFactory;
/*   8:    */import org.dom4j.Element;
/*   9:    */import org.dom4j.Namespace;
/*  10:    */import org.dom4j.QName;
/*  11:    */import org.dom4j.tree.NamespaceStack;
/*  12:    */import org.w3c.dom.DocumentType;
/*  13:    */import org.w3c.dom.NamedNodeMap;
/*  14:    */import org.w3c.dom.Node;
/*  15:    */import org.w3c.dom.NodeList;
/*  16:    */
/*  31:    */public class DOMReader
/*  32:    */{
/*  33:    */  private DocumentFactory factory;
/*  34:    */  private NamespaceStack namespaceStack;
/*  35:    */  
/*  36:    */  public DOMReader()
/*  37:    */  {
/*  38: 38 */    this.factory = DocumentFactory.getInstance();
/*  39: 39 */    this.namespaceStack = new NamespaceStack(this.factory);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public DOMReader(DocumentFactory factory) {
/*  43: 43 */    this.factory = factory;
/*  44: 44 */    this.namespaceStack = new NamespaceStack(factory);
/*  45:    */  }
/*  46:    */  
/*  52:    */  public DocumentFactory getDocumentFactory()
/*  53:    */  {
/*  54: 54 */    return this.factory;
/*  55:    */  }
/*  56:    */  
/*  67:    */  public void setDocumentFactory(DocumentFactory docFactory)
/*  68:    */  {
/*  69: 69 */    this.factory = docFactory;
/*  70: 70 */    this.namespaceStack.setDocumentFactory(this.factory);
/*  71:    */  }
/*  72:    */  
/*  73:    */  public org.dom4j.Document read(org.w3c.dom.Document domDocument) {
/*  74: 74 */    if ((domDocument instanceof org.dom4j.Document)) {
/*  75: 75 */      return (org.dom4j.Document)domDocument;
/*  76:    */    }
/*  77:    */    
/*  78: 78 */    org.dom4j.Document document = createDocument();
/*  79:    */    
/*  80: 80 */    clearNamespaceStack();
/*  81:    */    
/*  82: 82 */    NodeList nodeList = domDocument.getChildNodes();
/*  83:    */    
/*  84: 84 */    int i = 0; for (int size = nodeList.getLength(); i < size; i++) {
/*  85: 85 */      readTree(nodeList.item(i), document);
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    return document;
/*  89:    */  }
/*  90:    */  
/*  91:    */  protected void readTree(Node node, Branch current)
/*  92:    */  {
/*  93: 93 */    Element element = null;
/*  94: 94 */    org.dom4j.Document document = null;
/*  95:    */    
/*  96: 96 */    if ((current instanceof Element)) {
/*  97: 97 */      element = (Element)current;
/*  98:    */    } else {
/*  99: 99 */      document = (org.dom4j.Document)current;
/* 100:    */    }
/* 101:    */    
/* 102:102 */    switch (node.getNodeType()) {
/* 103:    */    case 1: 
/* 104:104 */      readElement(node, current);
/* 105:    */      
/* 106:106 */      break;
/* 107:    */    
/* 109:    */    case 7: 
/* 110:110 */      if ((current instanceof Element)) {
/* 111:111 */        Element currentEl = (Element)current;
/* 112:112 */        currentEl.addProcessingInstruction(node.getNodeName(), node.getNodeValue());
/* 113:    */      }
/* 114:    */      else {
/* 115:115 */        org.dom4j.Document currentDoc = (org.dom4j.Document)current;
/* 116:116 */        currentDoc.addProcessingInstruction(node.getNodeName(), node.getNodeValue());
/* 117:    */      }
/* 118:    */      
/* 120:120 */      break;
/* 121:    */    
/* 123:    */    case 8: 
/* 124:124 */      if ((current instanceof Element)) {
/* 125:125 */        ((Element)current).addComment(node.getNodeValue());
/* 126:    */      } else {
/* 127:127 */        ((org.dom4j.Document)current).addComment(node.getNodeValue());
/* 128:    */      }
/* 129:    */      
/* 130:130 */      break;
/* 131:    */    
/* 133:    */    case 10: 
/* 134:134 */      DocumentType domDocType = (DocumentType)node;
/* 135:    */      
/* 136:136 */      document.addDocType(domDocType.getName(), domDocType.getPublicId(), domDocType.getSystemId());
/* 137:    */      
/* 139:139 */      break;
/* 140:    */    
/* 141:    */    case 3: 
/* 142:142 */      element.addText(node.getNodeValue());
/* 143:    */      
/* 144:144 */      break;
/* 145:    */    
/* 146:    */    case 4: 
/* 147:147 */      element.addCDATA(node.getNodeValue());
/* 148:    */      
/* 149:149 */      break;
/* 150:    */    
/* 153:    */    case 5: 
/* 154:154 */      Node firstChild = node.getFirstChild();
/* 155:    */      
/* 156:156 */      if (firstChild != null) {
/* 157:157 */        element.addEntity(node.getNodeName(), firstChild.getNodeValue());
/* 158:    */      }
/* 159:    */      else {
/* 160:160 */        element.addEntity(node.getNodeName(), "");
/* 161:    */      }
/* 162:    */      
/* 163:163 */      break;
/* 164:    */    
/* 165:    */    case 6: 
/* 166:166 */      element.addEntity(node.getNodeName(), node.getNodeValue());
/* 167:    */      
/* 168:168 */      break;
/* 169:    */    case 2: case 9: 
/* 170:    */    default: 
/* 171:171 */      System.out.println("WARNING: Unknown DOM node type: " + node.getNodeType());
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 175:    */  protected void readElement(Node node, Branch current)
/* 176:    */  {
/* 177:177 */    int previouslyDeclaredNamespaces = this.namespaceStack.size();
/* 178:    */    
/* 179:179 */    String namespaceUri = node.getNamespaceURI();
/* 180:180 */    String elementPrefix = node.getPrefix();
/* 181:    */    
/* 182:182 */    if (elementPrefix == null) {
/* 183:183 */      elementPrefix = "";
/* 184:    */    }
/* 185:    */    
/* 186:186 */    NamedNodeMap attributeList = node.getAttributes();
/* 187:    */    
/* 188:188 */    if ((attributeList != null) && (namespaceUri == null))
/* 189:    */    {
/* 190:190 */      Node attribute = attributeList.getNamedItem("xmlns");
/* 191:    */      
/* 192:192 */      if (attribute != null) {
/* 193:193 */        namespaceUri = attribute.getNodeValue();
/* 194:194 */        elementPrefix = "";
/* 195:    */      }
/* 196:    */    }
/* 197:    */    
/* 198:198 */    QName qName = this.namespaceStack.getQName(namespaceUri, node.getLocalName(), node.getNodeName());
/* 199:    */    
/* 200:200 */    Element element = current.addElement(qName);
/* 201:    */    
/* 202:202 */    if (attributeList != null) {
/* 203:203 */      int size = attributeList.getLength();
/* 204:204 */      List attributes = new ArrayList(size);
/* 205:    */      
/* 206:206 */      for (int i = 0; i < size; i++) {
/* 207:207 */        Node attribute = attributeList.item(i);
/* 208:    */        
/* 210:210 */        String name = attribute.getNodeName();
/* 211:    */        
/* 212:212 */        if (name.startsWith("xmlns")) {
/* 213:213 */          String prefix = getPrefix(name);
/* 214:214 */          String uri = attribute.getNodeValue();
/* 215:    */          
/* 216:216 */          Namespace namespace = this.namespaceStack.addNamespace(prefix, uri);
/* 217:    */          
/* 218:218 */          element.add(namespace);
/* 219:    */        } else {
/* 220:220 */          attributes.add(attribute);
/* 221:    */        }
/* 222:    */      }
/* 223:    */      
/* 225:225 */      size = attributes.size();
/* 226:    */      
/* 227:227 */      for (int i = 0; i < size; i++) {
/* 228:228 */        Node attribute = (Node)attributes.get(i);
/* 229:    */        
/* 230:230 */        QName attributeQName = this.namespaceStack.getQName(attribute.getNamespaceURI(), attribute.getLocalName(), attribute.getNodeName());
/* 231:    */        
/* 233:233 */        element.addAttribute(attributeQName, attribute.getNodeValue());
/* 234:    */      }
/* 235:    */    }
/* 236:    */    
/* 238:238 */    NodeList children = node.getChildNodes();
/* 239:    */    
/* 240:240 */    int i = 0; for (int size = children.getLength(); i < size; i++) {
/* 241:241 */      Node child = children.item(i);
/* 242:242 */      readTree(child, element);
/* 243:    */    }
/* 244:    */    
/* 246:246 */    while (this.namespaceStack.size() > previouslyDeclaredNamespaces) {
/* 247:247 */      this.namespaceStack.pop();
/* 248:    */    }
/* 249:    */  }
/* 250:    */  
/* 251:    */  protected Namespace getNamespace(String prefix, String uri) {
/* 252:252 */    return getDocumentFactory().createNamespace(prefix, uri);
/* 253:    */  }
/* 254:    */  
/* 255:    */  protected org.dom4j.Document createDocument() {
/* 256:256 */    return getDocumentFactory().createDocument();
/* 257:    */  }
/* 258:    */  
/* 259:    */  protected void clearNamespaceStack() {
/* 260:260 */    this.namespaceStack.clear();
/* 261:    */    
/* 262:262 */    if (!this.namespaceStack.contains(Namespace.XML_NAMESPACE)) {
/* 263:263 */      this.namespaceStack.push(Namespace.XML_NAMESPACE);
/* 264:    */    }
/* 265:    */  }
/* 266:    */  
/* 267:    */  private String getPrefix(String xmlnsDecl) {
/* 268:268 */    int index = xmlnsDecl.indexOf(':', 5);
/* 269:    */    
/* 270:270 */    if (index != -1) {
/* 271:271 */      return xmlnsDecl.substring(index + 1);
/* 272:    */    }
/* 273:273 */    return "";
/* 274:    */  }
/* 275:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DOMReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */