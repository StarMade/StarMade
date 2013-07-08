/*   1:    */package org.dom4j.dom;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.dom4j.DocumentFactory;
/*   5:    */import org.dom4j.QName;
/*   6:    */import org.dom4j.tree.DefaultDocument;
/*   7:    */import org.w3c.dom.Attr;
/*   8:    */import org.w3c.dom.CDATASection;
/*   9:    */import org.w3c.dom.Comment;
/*  10:    */import org.w3c.dom.DOMException;
/*  11:    */import org.w3c.dom.DOMImplementation;
/*  12:    */import org.w3c.dom.Document;
/*  13:    */import org.w3c.dom.DocumentFragment;
/*  14:    */import org.w3c.dom.DocumentType;
/*  15:    */import org.w3c.dom.Element;
/*  16:    */import org.w3c.dom.EntityReference;
/*  17:    */import org.w3c.dom.NamedNodeMap;
/*  18:    */import org.w3c.dom.Node;
/*  19:    */import org.w3c.dom.NodeList;
/*  20:    */import org.w3c.dom.ProcessingInstruction;
/*  21:    */import org.w3c.dom.Text;
/*  22:    */
/*  32:    */public class DOMDocument
/*  33:    */  extends DefaultDocument
/*  34:    */  implements Document
/*  35:    */{
/*  36: 36 */  private static final DOMDocumentFactory DOCUMENT_FACTORY = (DOMDocumentFactory)DOMDocumentFactory.getInstance();
/*  37:    */  
/*  38:    */  public DOMDocument()
/*  39:    */  {
/*  40: 40 */    init();
/*  41:    */  }
/*  42:    */  
/*  43:    */  public DOMDocument(String name) {
/*  44: 44 */    super(name);
/*  45: 45 */    init();
/*  46:    */  }
/*  47:    */  
/*  48:    */  public DOMDocument(DOMElement rootElement) {
/*  49: 49 */    super(rootElement);
/*  50: 50 */    init();
/*  51:    */  }
/*  52:    */  
/*  53:    */  public DOMDocument(DOMDocumentType docType) {
/*  54: 54 */    super(docType);
/*  55: 55 */    init();
/*  56:    */  }
/*  57:    */  
/*  58:    */  public DOMDocument(DOMElement rootElement, DOMDocumentType docType) {
/*  59: 59 */    super(rootElement, docType);
/*  60: 60 */    init();
/*  61:    */  }
/*  62:    */  
/*  63:    */  public DOMDocument(String name, DOMElement rootElement, DOMDocumentType docType)
/*  64:    */  {
/*  65: 65 */    super(name, rootElement, docType);
/*  66: 66 */    init();
/*  67:    */  }
/*  68:    */  
/*  69:    */  private void init() {
/*  70: 70 */    setDocumentFactory(DOCUMENT_FACTORY);
/*  71:    */  }
/*  72:    */  
/*  74:    */  public boolean supports(String feature, String version)
/*  75:    */  {
/*  76: 76 */    return DOMNodeHelper.supports(this, feature, version);
/*  77:    */  }
/*  78:    */  
/*  79:    */  public String getNamespaceURI() {
/*  80: 80 */    return DOMNodeHelper.getNamespaceURI(this);
/*  81:    */  }
/*  82:    */  
/*  83:    */  public String getPrefix() {
/*  84: 84 */    return DOMNodeHelper.getPrefix(this);
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void setPrefix(String prefix) throws DOMException {
/*  88: 88 */    DOMNodeHelper.setPrefix(this, prefix);
/*  89:    */  }
/*  90:    */  
/*  91:    */  public String getLocalName() {
/*  92: 92 */    return DOMNodeHelper.getLocalName(this);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public String getNodeName() {
/*  96: 96 */    return "#document";
/*  97:    */  }
/*  98:    */  
/* 100:    */  public String getNodeValue()
/* 101:    */    throws DOMException
/* 102:    */  {
/* 103:103 */    return null;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public void setNodeValue(String nodeValue) throws DOMException
/* 107:    */  {}
/* 108:    */  
/* 109:    */  public Node getParentNode() {
/* 110:110 */    return DOMNodeHelper.getParentNode(this);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public NodeList getChildNodes() {
/* 114:114 */    return DOMNodeHelper.createNodeList(content());
/* 115:    */  }
/* 116:    */  
/* 117:    */  public Node getFirstChild() {
/* 118:118 */    return DOMNodeHelper.asDOMNode(node(0));
/* 119:    */  }
/* 120:    */  
/* 121:    */  public Node getLastChild() {
/* 122:122 */    return DOMNodeHelper.asDOMNode(node(nodeCount() - 1));
/* 123:    */  }
/* 124:    */  
/* 125:    */  public Node getPreviousSibling() {
/* 126:126 */    return DOMNodeHelper.getPreviousSibling(this);
/* 127:    */  }
/* 128:    */  
/* 129:    */  public Node getNextSibling() {
/* 130:130 */    return DOMNodeHelper.getNextSibling(this);
/* 131:    */  }
/* 132:    */  
/* 133:    */  public NamedNodeMap getAttributes() {
/* 134:134 */    return null;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public Document getOwnerDocument() {
/* 138:138 */    return null;
/* 139:    */  }
/* 140:    */  
/* 141:    */  public Node insertBefore(Node newChild, Node refChild) throws DOMException
/* 142:    */  {
/* 143:143 */    checkNewChildNode(newChild);
/* 144:    */    
/* 145:145 */    return DOMNodeHelper.insertBefore(this, newChild, refChild);
/* 146:    */  }
/* 147:    */  
/* 148:    */  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
/* 149:    */  {
/* 150:150 */    checkNewChildNode(newChild);
/* 151:    */    
/* 152:152 */    return DOMNodeHelper.replaceChild(this, newChild, oldChild);
/* 153:    */  }
/* 154:    */  
/* 155:    */  public Node removeChild(Node oldChild) throws DOMException
/* 156:    */  {
/* 157:157 */    return DOMNodeHelper.removeChild(this, oldChild);
/* 158:    */  }
/* 159:    */  
/* 160:    */  public Node appendChild(Node newChild) throws DOMException
/* 161:    */  {
/* 162:162 */    checkNewChildNode(newChild);
/* 163:    */    
/* 164:164 */    return DOMNodeHelper.appendChild(this, newChild);
/* 165:    */  }
/* 166:    */  
/* 167:    */  private void checkNewChildNode(Node newChild) throws DOMException
/* 168:    */  {
/* 169:169 */    int nodeType = newChild.getNodeType();
/* 170:    */    
/* 171:171 */    if ((nodeType != 1) && (nodeType != 8) && (nodeType != 7) && (nodeType != 10))
/* 172:    */    {
/* 175:175 */      throw new DOMException((short)3, "Given node cannot be a child of document");
/* 176:    */    }
/* 177:    */  }
/* 178:    */  
/* 179:    */  public boolean hasChildNodes()
/* 180:    */  {
/* 181:181 */    return nodeCount() > 0;
/* 182:    */  }
/* 183:    */  
/* 184:    */  public Node cloneNode(boolean deep) {
/* 185:185 */    return DOMNodeHelper.cloneNode(this, deep);
/* 186:    */  }
/* 187:    */  
/* 188:    */  public boolean isSupported(String feature, String version) {
/* 189:189 */    return DOMNodeHelper.isSupported(this, feature, version);
/* 190:    */  }
/* 191:    */  
/* 192:    */  public boolean hasAttributes() {
/* 193:193 */    return DOMNodeHelper.hasAttributes(this);
/* 194:    */  }
/* 195:    */  
/* 197:    */  public NodeList getElementsByTagName(String name)
/* 198:    */  {
/* 199:199 */    ArrayList list = new ArrayList();
/* 200:200 */    DOMNodeHelper.appendElementsByTagName(list, this, name);
/* 201:    */    
/* 202:202 */    return DOMNodeHelper.createNodeList(list);
/* 203:    */  }
/* 204:    */  
/* 205:    */  public NodeList getElementsByTagNameNS(String namespace, String name) {
/* 206:206 */    ArrayList list = new ArrayList();
/* 207:207 */    DOMNodeHelper.appendElementsByTagNameNS(list, this, namespace, name);
/* 208:    */    
/* 209:209 */    return DOMNodeHelper.createNodeList(list);
/* 210:    */  }
/* 211:    */  
/* 212:    */  public DocumentType getDoctype() {
/* 213:213 */    return DOMNodeHelper.asDOMDocumentType(getDocType());
/* 214:    */  }
/* 215:    */  
/* 216:    */  public DOMImplementation getImplementation() {
/* 217:217 */    if ((getDocumentFactory() instanceof DOMImplementation)) {
/* 218:218 */      return (DOMImplementation)getDocumentFactory();
/* 219:    */    }
/* 220:220 */    return DOCUMENT_FACTORY;
/* 221:    */  }
/* 222:    */  
/* 223:    */  public Element getDocumentElement()
/* 224:    */  {
/* 225:225 */    return DOMNodeHelper.asDOMElement(getRootElement());
/* 226:    */  }
/* 227:    */  
/* 228:    */  public Element createElement(String name) throws DOMException {
/* 229:229 */    return (Element)getDocumentFactory().createElement(name);
/* 230:    */  }
/* 231:    */  
/* 232:    */  public DocumentFragment createDocumentFragment() {
/* 233:233 */    DOMNodeHelper.notSupported();
/* 234:    */    
/* 235:235 */    return null;
/* 236:    */  }
/* 237:    */  
/* 238:    */  public Text createTextNode(String data) {
/* 239:239 */    return (Text)getDocumentFactory().createText(data);
/* 240:    */  }
/* 241:    */  
/* 242:    */  public Comment createComment(String data) {
/* 243:243 */    return (Comment)getDocumentFactory().createComment(data);
/* 244:    */  }
/* 245:    */  
/* 246:    */  public CDATASection createCDATASection(String data) throws DOMException {
/* 247:247 */    return (CDATASection)getDocumentFactory().createCDATA(data);
/* 248:    */  }
/* 249:    */  
/* 250:    */  public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException
/* 251:    */  {
/* 252:252 */    return (ProcessingInstruction)getDocumentFactory().createProcessingInstruction(target, data);
/* 253:    */  }
/* 254:    */  
/* 255:    */  public Attr createAttribute(String name) throws DOMException
/* 256:    */  {
/* 257:257 */    QName qname = getDocumentFactory().createQName(name);
/* 258:    */    
/* 259:259 */    return (Attr)getDocumentFactory().createAttribute(null, qname, "");
/* 260:    */  }
/* 261:    */  
/* 262:    */  public EntityReference createEntityReference(String name) throws DOMException
/* 263:    */  {
/* 264:264 */    return (EntityReference)getDocumentFactory().createEntity(name, null);
/* 265:    */  }
/* 266:    */  
/* 267:    */  public Node importNode(Node importedNode, boolean deep) throws DOMException
/* 268:    */  {
/* 269:269 */    DOMNodeHelper.notSupported();
/* 270:    */    
/* 271:271 */    return null;
/* 272:    */  }
/* 273:    */  
/* 274:    */  public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException
/* 275:    */  {
/* 276:276 */    QName qname = getDocumentFactory().createQName(qualifiedName, namespaceURI);
/* 277:    */    
/* 279:279 */    return (Element)getDocumentFactory().createElement(qname);
/* 280:    */  }
/* 281:    */  
/* 282:    */  public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException
/* 283:    */  {
/* 284:284 */    QName qname = getDocumentFactory().createQName(qualifiedName, namespaceURI);
/* 285:    */    
/* 287:287 */    return (Attr)getDocumentFactory().createAttribute(null, qname, null);
/* 288:    */  }
/* 289:    */  
/* 290:    */  public Element getElementById(String elementId)
/* 291:    */  {
/* 292:292 */    return DOMNodeHelper.asDOMElement(elementByID(elementId));
/* 293:    */  }
/* 294:    */  
/* 296:    */  protected DocumentFactory getDocumentFactory()
/* 297:    */  {
/* 298:298 */    if (super.getDocumentFactory() == null) {
/* 299:299 */      return DOCUMENT_FACTORY;
/* 300:    */    }
/* 301:301 */    return super.getDocumentFactory();
/* 302:    */  }
/* 303:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMDocument
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */