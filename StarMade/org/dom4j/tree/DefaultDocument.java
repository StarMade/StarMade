/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.util.Collections;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.List;
/*   6:    */import org.dom4j.Document;
/*   7:    */import org.dom4j.DocumentFactory;
/*   8:    */import org.dom4j.DocumentType;
/*   9:    */import org.dom4j.Element;
/*  10:    */import org.dom4j.IllegalAddException;
/*  11:    */import org.dom4j.Node;
/*  12:    */import org.dom4j.ProcessingInstruction;
/*  13:    */import org.xml.sax.EntityResolver;
/*  14:    */
/*  31:    */public class DefaultDocument
/*  32:    */  extends AbstractDocument
/*  33:    */{
/*  34: 34 */  protected static final List EMPTY_LIST = Collections.EMPTY_LIST;
/*  35:    */  
/*  36: 36 */  protected static final Iterator EMPTY_ITERATOR = EMPTY_LIST.iterator();
/*  37:    */  
/*  40:    */  private String name;
/*  41:    */  
/*  44:    */  private Element rootElement;
/*  45:    */  
/*  47:    */  private List content;
/*  48:    */  
/*  50:    */  private DocumentType docType;
/*  51:    */  
/*  53: 53 */  private DocumentFactory documentFactory = DocumentFactory.getInstance();
/*  54:    */  
/*  55:    */  private transient EntityResolver entityResolver;
/*  56:    */  
/*  58:    */  public DefaultDocument() {}
/*  59:    */  
/*  60:    */  public DefaultDocument(String name)
/*  61:    */  {
/*  62: 62 */    this.name = name;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public DefaultDocument(Element rootElement) {
/*  66: 66 */    this.rootElement = rootElement;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public DefaultDocument(DocumentType docType) {
/*  70: 70 */    this.docType = docType;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public DefaultDocument(Element rootElement, DocumentType docType) {
/*  74: 74 */    this.rootElement = rootElement;
/*  75: 75 */    this.docType = docType;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public DefaultDocument(String name, Element rootElement, DocumentType docType)
/*  79:    */  {
/*  80: 80 */    this.name = name;
/*  81: 81 */    this.rootElement = rootElement;
/*  82: 82 */    this.docType = docType;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public String getName() {
/*  86: 86 */    return this.name;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void setName(String name) {
/*  90: 90 */    this.name = name;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public Element getRootElement() {
/*  94: 94 */    return this.rootElement;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public DocumentType getDocType() {
/*  98: 98 */    return this.docType;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void setDocType(DocumentType docType) {
/* 102:102 */    this.docType = docType;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public Document addDocType(String docTypeName, String publicId, String systemId)
/* 106:    */  {
/* 107:107 */    setDocType(getDocumentFactory().createDocType(docTypeName, publicId, systemId));
/* 108:    */    
/* 110:110 */    return this;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public String getXMLEncoding() {
/* 114:114 */    return this.encoding;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public EntityResolver getEntityResolver() {
/* 118:118 */    return this.entityResolver;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public void setEntityResolver(EntityResolver entityResolver) {
/* 122:122 */    this.entityResolver = entityResolver;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public Object clone() {
/* 126:126 */    DefaultDocument document = (DefaultDocument)super.clone();
/* 127:127 */    document.rootElement = null;
/* 128:128 */    document.content = null;
/* 129:129 */    document.appendContent(this);
/* 130:    */    
/* 131:131 */    return document;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public List processingInstructions() {
/* 135:135 */    List source = contentList();
/* 136:136 */    List answer = createResultList();
/* 137:137 */    int size = source.size();
/* 138:    */    
/* 139:139 */    for (int i = 0; i < size; i++) {
/* 140:140 */      Object object = source.get(i);
/* 141:    */      
/* 142:142 */      if ((object instanceof ProcessingInstruction)) {
/* 143:143 */        answer.add(object);
/* 144:    */      }
/* 145:    */    }
/* 146:    */    
/* 147:147 */    return answer;
/* 148:    */  }
/* 149:    */  
/* 150:    */  public List processingInstructions(String target) {
/* 151:151 */    List source = contentList();
/* 152:152 */    List answer = createResultList();
/* 153:153 */    int size = source.size();
/* 154:    */    
/* 155:155 */    for (int i = 0; i < size; i++) {
/* 156:156 */      Object object = source.get(i);
/* 157:    */      
/* 158:158 */      if ((object instanceof ProcessingInstruction)) {
/* 159:159 */        ProcessingInstruction pi = (ProcessingInstruction)object;
/* 160:    */        
/* 161:161 */        if (target.equals(pi.getName())) {
/* 162:162 */          answer.add(pi);
/* 163:    */        }
/* 164:    */      }
/* 165:    */    }
/* 166:    */    
/* 167:167 */    return answer;
/* 168:    */  }
/* 169:    */  
/* 170:    */  public ProcessingInstruction processingInstruction(String target) {
/* 171:171 */    List source = contentList();
/* 172:172 */    int size = source.size();
/* 173:    */    
/* 174:174 */    for (int i = 0; i < size; i++) {
/* 175:175 */      Object object = source.get(i);
/* 176:    */      
/* 177:177 */      if ((object instanceof ProcessingInstruction)) {
/* 178:178 */        ProcessingInstruction pi = (ProcessingInstruction)object;
/* 179:    */        
/* 180:180 */        if (target.equals(pi.getName())) {
/* 181:181 */          return pi;
/* 182:    */        }
/* 183:    */      }
/* 184:    */    }
/* 185:    */    
/* 186:186 */    return null;
/* 187:    */  }
/* 188:    */  
/* 189:    */  public boolean removeProcessingInstruction(String target) {
/* 190:190 */    List source = contentList();
/* 191:    */    
/* 192:192 */    for (Iterator iter = source.iterator(); iter.hasNext();) {
/* 193:193 */      Object object = iter.next();
/* 194:    */      
/* 195:195 */      if ((object instanceof ProcessingInstruction)) {
/* 196:196 */        ProcessingInstruction pi = (ProcessingInstruction)object;
/* 197:    */        
/* 198:198 */        if (target.equals(pi.getName())) {
/* 199:199 */          iter.remove();
/* 200:    */          
/* 201:201 */          return true;
/* 202:    */        }
/* 203:    */      }
/* 204:    */    }
/* 205:    */    
/* 206:206 */    return false;
/* 207:    */  }
/* 208:    */  
/* 209:    */  public void setContent(List content) {
/* 210:210 */    this.rootElement = null;
/* 211:211 */    contentRemoved();
/* 212:    */    
/* 213:213 */    if ((content instanceof ContentListFacade)) {
/* 214:214 */      content = ((ContentListFacade)content).getBackingList();
/* 215:    */    }
/* 216:    */    
/* 217:217 */    if (content == null) {
/* 218:218 */      this.content = null;
/* 219:    */    } else {
/* 220:220 */      int size = content.size();
/* 221:221 */      List newContent = createContentList(size);
/* 222:    */      
/* 223:223 */      for (int i = 0; i < size; i++) {
/* 224:224 */        Object object = content.get(i);
/* 225:    */        
/* 226:226 */        if ((object instanceof Node)) {
/* 227:227 */          Node node = (Node)object;
/* 228:228 */          Document doc = node.getDocument();
/* 229:    */          
/* 230:230 */          if ((doc != null) && (doc != this)) {
/* 231:231 */            node = (Node)node.clone();
/* 232:    */          }
/* 233:    */          
/* 234:234 */          if ((node instanceof Element)) {
/* 235:235 */            if (this.rootElement == null) {
/* 236:236 */              this.rootElement = ((Element)node);
/* 237:    */            } else {
/* 238:238 */              throw new IllegalAddException("A document may only contain one root element: " + content);
/* 239:    */            }
/* 240:    */          }
/* 241:    */          
/* 245:245 */          newContent.add(node);
/* 246:246 */          childAdded(node);
/* 247:    */        }
/* 248:    */      }
/* 249:    */      
/* 250:250 */      this.content = newContent;
/* 251:    */    }
/* 252:    */  }
/* 253:    */  
/* 254:    */  public void clearContent() {
/* 255:255 */    contentRemoved();
/* 256:256 */    this.content = null;
/* 257:257 */    this.rootElement = null;
/* 258:    */  }
/* 259:    */  
/* 260:    */  public void setDocumentFactory(DocumentFactory documentFactory) {
/* 261:261 */    this.documentFactory = documentFactory;
/* 262:    */  }
/* 263:    */  
/* 265:    */  protected List contentList()
/* 266:    */  {
/* 267:267 */    if (this.content == null) {
/* 268:268 */      this.content = createContentList();
/* 269:    */      
/* 270:270 */      if (this.rootElement != null) {
/* 271:271 */        this.content.add(this.rootElement);
/* 272:    */      }
/* 273:    */    }
/* 274:    */    
/* 275:275 */    return this.content;
/* 276:    */  }
/* 277:    */  
/* 278:    */  protected void addNode(Node node) {
/* 279:279 */    if (node != null) {
/* 280:280 */      Document document = node.getDocument();
/* 281:    */      
/* 282:282 */      if ((document != null) && (document != this))
/* 283:    */      {
/* 284:284 */        String message = "The Node already has an existing document: " + document;
/* 285:    */        
/* 286:286 */        throw new IllegalAddException(this, node, message);
/* 287:    */      }
/* 288:    */      
/* 289:289 */      contentList().add(node);
/* 290:290 */      childAdded(node);
/* 291:    */    }
/* 292:    */  }
/* 293:    */  
/* 294:    */  protected void addNode(int index, Node node) {
/* 295:295 */    if (node != null) {
/* 296:296 */      Document document = node.getDocument();
/* 297:    */      
/* 298:298 */      if ((document != null) && (document != this))
/* 299:    */      {
/* 300:300 */        String message = "The Node already has an existing document: " + document;
/* 301:    */        
/* 302:302 */        throw new IllegalAddException(this, node, message);
/* 303:    */      }
/* 304:    */      
/* 305:305 */      contentList().add(index, node);
/* 306:306 */      childAdded(node);
/* 307:    */    }
/* 308:    */  }
/* 309:    */  
/* 310:    */  protected boolean removeNode(Node node) {
/* 311:311 */    if (node == this.rootElement) {
/* 312:312 */      this.rootElement = null;
/* 313:    */    }
/* 314:    */    
/* 315:315 */    if (contentList().remove(node)) {
/* 316:316 */      childRemoved(node);
/* 317:    */      
/* 318:318 */      return true;
/* 319:    */    }
/* 320:    */    
/* 321:321 */    return false;
/* 322:    */  }
/* 323:    */  
/* 324:    */  protected void rootElementAdded(Element element) {
/* 325:325 */    this.rootElement = element;
/* 326:326 */    element.setDocument(this);
/* 327:    */  }
/* 328:    */  
/* 329:    */  protected DocumentFactory getDocumentFactory() {
/* 330:330 */    return this.documentFactory;
/* 331:    */  }
/* 332:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultDocument
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */