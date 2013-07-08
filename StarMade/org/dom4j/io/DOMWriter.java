/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.List;
/*   5:    */import org.dom4j.Attribute;
/*   6:    */import org.dom4j.CDATA;
/*   7:    */import org.dom4j.DocumentException;
/*   8:    */import org.dom4j.Entity;
/*   9:    */import org.dom4j.Namespace;
/*  10:    */import org.dom4j.tree.NamespaceStack;
/*  11:    */import org.w3c.dom.CDATASection;
/*  12:    */import org.w3c.dom.DOMImplementation;
/*  13:    */import org.w3c.dom.DocumentType;
/*  14:    */import org.w3c.dom.EntityReference;
/*  15:    */import org.w3c.dom.Node;
/*  16:    */
/*  34:    */public class DOMWriter
/*  35:    */{
/*  36: 36 */  private static boolean loggedWarning = false;
/*  37:    */  
/*  38: 38 */  private static final String[] DEFAULT_DOM_DOCUMENT_CLASSES = { "org.apache.xerces.dom.DocumentImpl", "gnu.xml.dom.DomDocument", "org.apache.crimson.tree.XmlDocument", "com.sun.xml.tree.XmlDocument", "oracle.xml.parser.v2.XMLDocument", "oracle.xml.parser.XMLDocument", "org.dom4j.dom.DOMDocument" };
/*  39:    */  
/*  45:    */  private Class domDocumentClass;
/*  46:    */  
/*  52: 52 */  private NamespaceStack namespaceStack = new NamespaceStack();
/*  53:    */  
/*  54:    */  public DOMWriter() {}
/*  55:    */  
/*  56:    */  public DOMWriter(Class domDocumentClass)
/*  57:    */  {
/*  58: 58 */    this.domDocumentClass = domDocumentClass;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public Class getDomDocumentClass() throws DocumentException {
/*  62: 62 */    Class result = this.domDocumentClass;
/*  63:    */    
/*  64: 64 */    if (result == null)
/*  65:    */    {
/*  66: 66 */      int size = DEFAULT_DOM_DOCUMENT_CLASSES.length;
/*  67:    */      
/*  68: 68 */      for (int i = 0; i < size; i++) {
/*  69:    */        try {
/*  70: 70 */          String name = DEFAULT_DOM_DOCUMENT_CLASSES[i];
/*  71: 71 */          result = Class.forName(name, true, DOMWriter.class.getClassLoader());
/*  72:    */          
/*  74: 74 */          if (result != null) {
/*  75: 75 */            break;
/*  76:    */          }
/*  77:    */        }
/*  78:    */        catch (Exception e) {}
/*  79:    */      }
/*  80:    */    }
/*  81:    */    
/*  84: 84 */    return result;
/*  85:    */  }
/*  86:    */  
/*  94:    */  public void setDomDocumentClass(Class domDocumentClass)
/*  95:    */  {
/*  96: 96 */    this.domDocumentClass = domDocumentClass;
/*  97:    */  }
/*  98:    */  
/* 107:    */  public void setDomDocumentClassName(String name)
/* 108:    */    throws DocumentException
/* 109:    */  {
/* 110:    */    try
/* 111:    */    {
/* 112:112 */      this.domDocumentClass = Class.forName(name, true, DOMWriter.class.getClassLoader());
/* 113:    */    }
/* 114:    */    catch (Exception e) {
/* 115:115 */      throw new DocumentException("Could not load the DOM Document class: " + name, e);
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 119:    */  public org.w3c.dom.Document write(org.dom4j.Document document)
/* 120:    */    throws DocumentException
/* 121:    */  {
/* 122:122 */    if ((document instanceof org.w3c.dom.Document)) {
/* 123:123 */      return (org.w3c.dom.Document)document;
/* 124:    */    }
/* 125:    */    
/* 126:126 */    resetNamespaceStack();
/* 127:    */    
/* 128:128 */    org.w3c.dom.Document domDocument = createDomDocument(document);
/* 129:129 */    appendDOMTree(domDocument, domDocument, document.content());
/* 130:130 */    this.namespaceStack.clear();
/* 131:    */    
/* 132:132 */    return domDocument;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public org.w3c.dom.Document write(org.dom4j.Document document, DOMImplementation domImpl) throws DocumentException
/* 136:    */  {
/* 137:137 */    if ((document instanceof org.w3c.dom.Document)) {
/* 138:138 */      return (org.w3c.dom.Document)document;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    resetNamespaceStack();
/* 142:    */    
/* 143:143 */    org.w3c.dom.Document domDocument = createDomDocument(document, domImpl);
/* 144:144 */    appendDOMTree(domDocument, domDocument, document.content());
/* 145:145 */    this.namespaceStack.clear();
/* 146:    */    
/* 147:147 */    return domDocument;
/* 148:    */  }
/* 149:    */  
/* 150:    */  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, List content)
/* 151:    */  {
/* 152:152 */    int size = content.size();
/* 153:    */    
/* 154:154 */    for (int i = 0; i < size; i++) {
/* 155:155 */      Object object = content.get(i);
/* 156:    */      
/* 157:157 */      if ((object instanceof org.dom4j.Element)) {
/* 158:158 */        appendDOMTree(domDocument, domCurrent, (org.dom4j.Element)object);
/* 159:159 */      } else if ((object instanceof String)) {
/* 160:160 */        appendDOMTree(domDocument, domCurrent, (String)object);
/* 161:161 */      } else if ((object instanceof org.dom4j.Text)) {
/* 162:162 */        org.dom4j.Text text = (org.dom4j.Text)object;
/* 163:163 */        appendDOMTree(domDocument, domCurrent, text.getText());
/* 164:164 */      } else if ((object instanceof CDATA)) {
/* 165:165 */        appendDOMTree(domDocument, domCurrent, (CDATA)object);
/* 166:166 */      } else if ((object instanceof org.dom4j.Comment)) {
/* 167:167 */        appendDOMTree(domDocument, domCurrent, (org.dom4j.Comment)object);
/* 168:168 */      } else if ((object instanceof Entity)) {
/* 169:169 */        appendDOMTree(domDocument, domCurrent, (Entity)object);
/* 170:170 */      } else if ((object instanceof org.dom4j.ProcessingInstruction)) {
/* 171:171 */        appendDOMTree(domDocument, domCurrent, (org.dom4j.ProcessingInstruction)object);
/* 172:    */      }
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 177:    */  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, org.dom4j.Element element)
/* 178:    */  {
/* 179:179 */    String elUri = element.getNamespaceURI();
/* 180:180 */    String elName = element.getQualifiedName();
/* 181:181 */    org.w3c.dom.Element domElement = domDocument.createElementNS(elUri, elName);
/* 182:    */    
/* 184:184 */    int stackSize = this.namespaceStack.size();
/* 185:    */    
/* 187:187 */    Namespace elementNamespace = element.getNamespace();
/* 188:    */    
/* 189:189 */    if (isNamespaceDeclaration(elementNamespace)) {
/* 190:190 */      this.namespaceStack.push(elementNamespace);
/* 191:191 */      writeNamespace(domElement, elementNamespace);
/* 192:    */    }
/* 193:    */    
/* 195:195 */    List declaredNamespaces = element.declaredNamespaces();
/* 196:    */    
/* 197:197 */    int i = 0; for (int size = declaredNamespaces.size(); i < size; i++) {
/* 198:198 */      Namespace namespace = (Namespace)declaredNamespaces.get(i);
/* 199:    */      
/* 200:200 */      if (isNamespaceDeclaration(namespace)) {
/* 201:201 */        this.namespaceStack.push(namespace);
/* 202:202 */        writeNamespace(domElement, namespace);
/* 203:    */      }
/* 204:    */    }
/* 205:    */    
/* 207:207 */    int i = 0; for (int size = element.attributeCount(); i < size; i++) {
/* 208:208 */      Attribute attribute = element.attribute(i);
/* 209:209 */      String attUri = attribute.getNamespaceURI();
/* 210:210 */      String attName = attribute.getQualifiedName();
/* 211:211 */      String value = attribute.getValue();
/* 212:212 */      domElement.setAttributeNS(attUri, attName, value);
/* 213:    */    }
/* 214:    */    
/* 216:216 */    appendDOMTree(domDocument, domElement, element.content());
/* 217:    */    
/* 218:218 */    domCurrent.appendChild(domElement);
/* 219:    */    
/* 220:220 */    while (this.namespaceStack.size() > stackSize) {
/* 221:221 */      this.namespaceStack.pop();
/* 222:    */    }
/* 223:    */  }
/* 224:    */  
/* 225:    */  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, CDATA cdata)
/* 226:    */  {
/* 227:227 */    CDATASection domCDATA = domDocument.createCDATASection(cdata.getText());
/* 228:    */    
/* 229:229 */    domCurrent.appendChild(domCDATA);
/* 230:    */  }
/* 231:    */  
/* 232:    */  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, org.dom4j.Comment comment)
/* 233:    */  {
/* 234:234 */    org.w3c.dom.Comment domComment = domDocument.createComment(comment.getText());
/* 235:    */    
/* 236:236 */    domCurrent.appendChild(domComment);
/* 237:    */  }
/* 238:    */  
/* 239:    */  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, String text)
/* 240:    */  {
/* 241:241 */    org.w3c.dom.Text domText = domDocument.createTextNode(text);
/* 242:242 */    domCurrent.appendChild(domText);
/* 243:    */  }
/* 244:    */  
/* 245:    */  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, Entity entity)
/* 246:    */  {
/* 247:247 */    EntityReference domEntity = domDocument.createEntityReference(entity.getName());
/* 248:    */    
/* 249:249 */    domCurrent.appendChild(domEntity);
/* 250:    */  }
/* 251:    */  
/* 252:    */  protected void appendDOMTree(org.w3c.dom.Document domDoc, Node domCurrent, org.dom4j.ProcessingInstruction pi)
/* 253:    */  {
/* 254:254 */    org.w3c.dom.ProcessingInstruction domPI = domDoc.createProcessingInstruction(pi.getTarget(), pi.getText());
/* 255:    */    
/* 256:256 */    domCurrent.appendChild(domPI);
/* 257:    */  }
/* 258:    */  
/* 259:    */  protected void writeNamespace(org.w3c.dom.Element domElement, Namespace namespace)
/* 260:    */  {
/* 261:261 */    String attributeName = attributeNameForNamespace(namespace);
/* 262:    */    
/* 264:264 */    domElement.setAttribute(attributeName, namespace.getURI());
/* 265:    */  }
/* 266:    */  
/* 267:    */  protected String attributeNameForNamespace(Namespace namespace) {
/* 268:268 */    String xmlns = "xmlns";
/* 269:269 */    String prefix = namespace.getPrefix();
/* 270:    */    
/* 271:271 */    if (prefix.length() > 0) {
/* 272:272 */      return xmlns + ":" + prefix;
/* 273:    */    }
/* 274:    */    
/* 275:275 */    return xmlns;
/* 276:    */  }
/* 277:    */  
/* 278:    */  protected org.w3c.dom.Document createDomDocument(org.dom4j.Document document) throws DocumentException
/* 279:    */  {
/* 280:280 */    org.w3c.dom.Document result = null;
/* 281:    */    
/* 283:283 */    if (this.domDocumentClass != null) {
/* 284:    */      try {
/* 285:285 */        result = (org.w3c.dom.Document)this.domDocumentClass.newInstance();
/* 286:    */      } catch (Exception e) {
/* 287:287 */        throw new DocumentException("Could not instantiate an instance of DOM Document with class: " + this.domDocumentClass.getName(), e);
/* 288:    */      }
/* 289:    */      
/* 291:    */    }
/* 292:    */    else
/* 293:    */    {
/* 294:294 */      result = createDomDocumentViaJAXP();
/* 295:    */      
/* 296:296 */      if (result == null) {
/* 297:297 */        Class theClass = getDomDocumentClass();
/* 298:    */        try
/* 299:    */        {
/* 300:300 */          result = (org.w3c.dom.Document)theClass.newInstance();
/* 301:    */        } catch (Exception e) {
/* 302:302 */          throw new DocumentException("Could not instantiate an instance of DOM Document with class: " + theClass.getName(), e);
/* 303:    */        }
/* 304:    */      }
/* 305:    */    }
/* 306:    */    
/* 309:309 */    return result;
/* 310:    */  }
/* 311:    */  
/* 312:    */  protected org.w3c.dom.Document createDomDocumentViaJAXP() throws DocumentException
/* 313:    */  {
/* 314:    */    try {
/* 315:315 */      return JAXPHelper.createDocument(false, true);
/* 316:    */    } catch (Throwable e) {
/* 317:317 */      if (!loggedWarning) {
/* 318:318 */        loggedWarning = true;
/* 319:    */        
/* 320:320 */        if (SAXHelper.isVerboseErrorReporting())
/* 321:    */        {
/* 323:323 */          System.out.println("Warning: Caught exception attempting to use JAXP to create a W3C DOM document");
/* 324:    */          
/* 325:325 */          System.out.println("Warning: Exception was: " + e);
/* 326:326 */          e.printStackTrace();
/* 327:    */        } else {
/* 328:328 */          System.out.println("Warning: Error occurred using JAXP to create a DOM document.");
/* 329:    */        }
/* 330:    */      }
/* 331:    */    }
/* 332:    */    
/* 334:334 */    return null;
/* 335:    */  }
/* 336:    */  
/* 337:    */  protected org.w3c.dom.Document createDomDocument(org.dom4j.Document document, DOMImplementation domImpl) throws DocumentException
/* 338:    */  {
/* 339:339 */    String namespaceURI = null;
/* 340:340 */    String qualifiedName = null;
/* 341:341 */    DocumentType docType = null;
/* 342:    */    
/* 343:343 */    return domImpl.createDocument(namespaceURI, qualifiedName, docType);
/* 344:    */  }
/* 345:    */  
/* 346:    */  protected boolean isNamespaceDeclaration(Namespace ns) {
/* 347:347 */    if ((ns != null) && (ns != Namespace.NO_NAMESPACE) && (ns != Namespace.XML_NAMESPACE))
/* 348:    */    {
/* 349:349 */      String uri = ns.getURI();
/* 350:    */      
/* 351:351 */      if ((uri != null) && (uri.length() > 0) && 
/* 352:352 */        (!this.namespaceStack.contains(ns))) {
/* 353:353 */        return true;
/* 354:    */      }
/* 355:    */    }
/* 356:    */    
/* 358:358 */    return false;
/* 359:    */  }
/* 360:    */  
/* 361:    */  protected void resetNamespaceStack() {
/* 362:362 */    this.namespaceStack.clear();
/* 363:363 */    this.namespaceStack.push(Namespace.XML_NAMESPACE);
/* 364:    */  }
/* 365:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DOMWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */