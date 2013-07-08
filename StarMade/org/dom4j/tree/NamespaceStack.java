/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.HashMap;
/*   6:    */import java.util.Map;
/*   7:    */import org.dom4j.DocumentFactory;
/*   8:    */import org.dom4j.Namespace;
/*   9:    */import org.dom4j.QName;
/*  10:    */
/*  28:    */public class NamespaceStack
/*  29:    */{
/*  30:    */  private DocumentFactory documentFactory;
/*  31: 31 */  private ArrayList namespaceStack = new ArrayList();
/*  32:    */  
/*  34: 34 */  private ArrayList namespaceCacheList = new ArrayList();
/*  35:    */  
/*  40:    */  private Map currentNamespaceCache;
/*  41:    */  
/*  46: 46 */  private Map rootNamespaceCache = new HashMap();
/*  47:    */  
/*  48:    */  private Namespace defaultNamespace;
/*  49:    */  
/*  50:    */  public NamespaceStack()
/*  51:    */  {
/*  52: 52 */    this.documentFactory = DocumentFactory.getInstance();
/*  53:    */  }
/*  54:    */  
/*  55:    */  public NamespaceStack(DocumentFactory documentFactory) {
/*  56: 56 */    this.documentFactory = documentFactory;
/*  57:    */  }
/*  58:    */  
/*  65:    */  public void push(Namespace namespace)
/*  66:    */  {
/*  67: 67 */    this.namespaceStack.add(namespace);
/*  68: 68 */    this.namespaceCacheList.add(null);
/*  69: 69 */    this.currentNamespaceCache = null;
/*  70:    */    
/*  71: 71 */    String prefix = namespace.getPrefix();
/*  72:    */    
/*  73: 73 */    if ((prefix == null) || (prefix.length() == 0)) {
/*  74: 74 */      this.defaultNamespace = namespace;
/*  75:    */    }
/*  76:    */  }
/*  77:    */  
/*  82:    */  public Namespace pop()
/*  83:    */  {
/*  84: 84 */    return remove(this.namespaceStack.size() - 1);
/*  85:    */  }
/*  86:    */  
/*  91:    */  public int size()
/*  92:    */  {
/*  93: 93 */    return this.namespaceStack.size();
/*  94:    */  }
/*  95:    */  
/*  98:    */  public void clear()
/*  99:    */  {
/* 100:100 */    this.namespaceStack.clear();
/* 101:101 */    this.namespaceCacheList.clear();
/* 102:102 */    this.rootNamespaceCache.clear();
/* 103:103 */    this.currentNamespaceCache = null;
/* 104:    */  }
/* 105:    */  
/* 113:    */  public Namespace getNamespace(int index)
/* 114:    */  {
/* 115:115 */    return (Namespace)this.namespaceStack.get(index);
/* 116:    */  }
/* 117:    */  
/* 126:    */  public Namespace getNamespaceForPrefix(String prefix)
/* 127:    */  {
/* 128:128 */    if (prefix == null) {
/* 129:129 */      prefix = "";
/* 130:    */    }
/* 131:    */    
/* 132:132 */    for (int i = this.namespaceStack.size() - 1; i >= 0; i--) {
/* 133:133 */      Namespace namespace = (Namespace)this.namespaceStack.get(i);
/* 134:    */      
/* 135:135 */      if (prefix.equals(namespace.getPrefix())) {
/* 136:136 */        return namespace;
/* 137:    */      }
/* 138:    */    }
/* 139:    */    
/* 140:140 */    return null;
/* 141:    */  }
/* 142:    */  
/* 150:    */  public String getURI(String prefix)
/* 151:    */  {
/* 152:152 */    Namespace namespace = getNamespaceForPrefix(prefix);
/* 153:    */    
/* 154:154 */    return namespace != null ? namespace.getURI() : null;
/* 155:    */  }
/* 156:    */  
/* 164:    */  public boolean contains(Namespace namespace)
/* 165:    */  {
/* 166:166 */    String prefix = namespace.getPrefix();
/* 167:167 */    Namespace current = null;
/* 168:    */    
/* 169:169 */    if ((prefix == null) || (prefix.length() == 0)) {
/* 170:170 */      current = getDefaultNamespace();
/* 171:    */    } else {
/* 172:172 */      current = getNamespaceForPrefix(prefix);
/* 173:    */    }
/* 174:    */    
/* 175:175 */    if (current == null) {
/* 176:176 */      return false;
/* 177:    */    }
/* 178:    */    
/* 179:179 */    if (current == namespace) {
/* 180:180 */      return true;
/* 181:    */    }
/* 182:    */    
/* 183:183 */    return namespace.getURI().equals(current.getURI());
/* 184:    */  }
/* 185:    */  
/* 186:    */  public QName getQName(String namespaceURI, String localName, String qualifiedName)
/* 187:    */  {
/* 188:188 */    if (localName == null) {
/* 189:189 */      localName = qualifiedName;
/* 190:190 */    } else if (qualifiedName == null) {
/* 191:191 */      qualifiedName = localName;
/* 192:    */    }
/* 193:    */    
/* 194:194 */    if (namespaceURI == null) {
/* 195:195 */      namespaceURI = "";
/* 196:    */    }
/* 197:    */    
/* 198:198 */    String prefix = "";
/* 199:199 */    int index = qualifiedName.indexOf(":");
/* 200:    */    
/* 201:201 */    if (index > 0) {
/* 202:202 */      prefix = qualifiedName.substring(0, index);
/* 203:    */      
/* 204:204 */      if (localName.trim().length() == 0) {
/* 205:205 */        localName = qualifiedName.substring(index + 1);
/* 206:    */      }
/* 207:207 */    } else if (localName.trim().length() == 0) {
/* 208:208 */      localName = qualifiedName;
/* 209:    */    }
/* 210:    */    
/* 211:211 */    Namespace namespace = createNamespace(prefix, namespaceURI);
/* 212:    */    
/* 213:213 */    return pushQName(localName, qualifiedName, namespace, prefix);
/* 214:    */  }
/* 215:    */  
/* 216:    */  public QName getAttributeQName(String namespaceURI, String localName, String qualifiedName)
/* 217:    */  {
/* 218:218 */    if (qualifiedName == null) {
/* 219:219 */      qualifiedName = localName;
/* 220:    */    }
/* 221:    */    
/* 222:222 */    Map map = getNamespaceCache();
/* 223:223 */    QName answer = (QName)map.get(qualifiedName);
/* 224:    */    
/* 225:225 */    if (answer != null) {
/* 226:226 */      return answer;
/* 227:    */    }
/* 228:    */    
/* 229:229 */    if (localName == null) {
/* 230:230 */      localName = qualifiedName;
/* 231:    */    }
/* 232:    */    
/* 233:233 */    if (namespaceURI == null) {
/* 234:234 */      namespaceURI = "";
/* 235:    */    }
/* 236:    */    
/* 237:237 */    Namespace namespace = null;
/* 238:238 */    String prefix = "";
/* 239:239 */    int index = qualifiedName.indexOf(":");
/* 240:    */    
/* 241:241 */    if (index > 0) {
/* 242:242 */      prefix = qualifiedName.substring(0, index);
/* 243:243 */      namespace = createNamespace(prefix, namespaceURI);
/* 244:    */      
/* 245:245 */      if (localName.trim().length() == 0) {
/* 246:246 */        localName = qualifiedName.substring(index + 1);
/* 247:    */      }
/* 248:    */    }
/* 249:    */    else {
/* 250:250 */      namespace = Namespace.NO_NAMESPACE;
/* 251:    */      
/* 252:252 */      if (localName.trim().length() == 0) {
/* 253:253 */        localName = qualifiedName;
/* 254:    */      }
/* 255:    */    }
/* 256:    */    
/* 257:257 */    answer = pushQName(localName, qualifiedName, namespace, prefix);
/* 258:258 */    map.put(qualifiedName, answer);
/* 259:    */    
/* 260:260 */    return answer;
/* 261:    */  }
/* 262:    */  
/* 270:    */  public void push(String prefix, String uri)
/* 271:    */  {
/* 272:272 */    if (uri == null) {
/* 273:273 */      uri = "";
/* 274:    */    }
/* 275:    */    
/* 276:276 */    Namespace namespace = createNamespace(prefix, uri);
/* 277:277 */    push(namespace);
/* 278:    */  }
/* 279:    */  
/* 289:    */  public Namespace addNamespace(String prefix, String uri)
/* 290:    */  {
/* 291:291 */    Namespace namespace = createNamespace(prefix, uri);
/* 292:292 */    push(namespace);
/* 293:    */    
/* 294:294 */    return namespace;
/* 295:    */  }
/* 296:    */  
/* 304:    */  public Namespace pop(String prefix)
/* 305:    */  {
/* 306:306 */    if (prefix == null) {
/* 307:307 */      prefix = "";
/* 308:    */    }
/* 309:    */    
/* 310:310 */    Namespace namespace = null;
/* 311:    */    
/* 312:312 */    for (int i = this.namespaceStack.size() - 1; i >= 0; i--) {
/* 313:313 */      Namespace ns = (Namespace)this.namespaceStack.get(i);
/* 314:    */      
/* 315:315 */      if (prefix.equals(ns.getPrefix())) {
/* 316:316 */        remove(i);
/* 317:317 */        namespace = ns;
/* 318:    */        
/* 319:319 */        break;
/* 320:    */      }
/* 321:    */    }
/* 322:    */    
/* 323:323 */    if (namespace == null) {
/* 324:324 */      System.out.println("Warning: missing namespace prefix ignored: " + prefix);
/* 325:    */    }
/* 326:    */    
/* 328:328 */    return namespace;
/* 329:    */  }
/* 330:    */  
/* 331:    */  public String toString() {
/* 332:332 */    return super.toString() + " Stack: " + this.namespaceStack.toString();
/* 333:    */  }
/* 334:    */  
/* 335:    */  public DocumentFactory getDocumentFactory() {
/* 336:336 */    return this.documentFactory;
/* 337:    */  }
/* 338:    */  
/* 339:    */  public void setDocumentFactory(DocumentFactory documentFactory) {
/* 340:340 */    this.documentFactory = documentFactory;
/* 341:    */  }
/* 342:    */  
/* 343:    */  public Namespace getDefaultNamespace() {
/* 344:344 */    if (this.defaultNamespace == null) {
/* 345:345 */      this.defaultNamespace = findDefaultNamespace();
/* 346:    */    }
/* 347:    */    
/* 348:348 */    return this.defaultNamespace;
/* 349:    */  }
/* 350:    */  
/* 368:    */  protected QName pushQName(String localName, String qualifiedName, Namespace namespace, String prefix)
/* 369:    */  {
/* 370:370 */    if ((prefix == null) || (prefix.length() == 0)) {
/* 371:371 */      this.defaultNamespace = null;
/* 372:    */    }
/* 373:    */    
/* 374:374 */    return createQName(localName, qualifiedName, namespace);
/* 375:    */  }
/* 376:    */  
/* 390:    */  protected QName createQName(String localName, String qualifiedName, Namespace namespace)
/* 391:    */  {
/* 392:392 */    return this.documentFactory.createQName(localName, namespace);
/* 393:    */  }
/* 394:    */  
/* 405:    */  protected Namespace createNamespace(String prefix, String namespaceURI)
/* 406:    */  {
/* 407:407 */    return this.documentFactory.createNamespace(prefix, namespaceURI);
/* 408:    */  }
/* 409:    */  
/* 415:    */  protected Namespace findDefaultNamespace()
/* 416:    */  {
/* 417:417 */    for (int i = this.namespaceStack.size() - 1; i >= 0; i--) {
/* 418:418 */      Namespace namespace = (Namespace)this.namespaceStack.get(i);
/* 419:    */      
/* 420:420 */      if (namespace != null) {
/* 421:421 */        String prefix = namespace.getPrefix();
/* 422:    */        
/* 423:423 */        if ((prefix == null) || (namespace.getPrefix().length() == 0)) {
/* 424:424 */          return namespace;
/* 425:    */        }
/* 426:    */      }
/* 427:    */    }
/* 428:    */    
/* 429:429 */    return null;
/* 430:    */  }
/* 431:    */  
/* 439:    */  protected Namespace remove(int index)
/* 440:    */  {
/* 441:441 */    Namespace namespace = (Namespace)this.namespaceStack.remove(index);
/* 442:442 */    this.namespaceCacheList.remove(index);
/* 443:443 */    this.defaultNamespace = null;
/* 444:444 */    this.currentNamespaceCache = null;
/* 445:    */    
/* 446:446 */    return namespace;
/* 447:    */  }
/* 448:    */  
/* 449:    */  protected Map getNamespaceCache() {
/* 450:450 */    if (this.currentNamespaceCache == null) {
/* 451:451 */      int index = this.namespaceStack.size() - 1;
/* 452:    */      
/* 453:453 */      if (index < 0) {
/* 454:454 */        this.currentNamespaceCache = this.rootNamespaceCache;
/* 455:    */      } else {
/* 456:456 */        this.currentNamespaceCache = ((Map)this.namespaceCacheList.get(index));
/* 457:    */        
/* 458:458 */        if (this.currentNamespaceCache == null) {
/* 459:459 */          this.currentNamespaceCache = new HashMap();
/* 460:460 */          this.namespaceCacheList.set(index, this.currentNamespaceCache);
/* 461:    */        }
/* 462:    */      }
/* 463:    */    }
/* 464:    */    
/* 465:465 */    return this.currentNamespaceCache;
/* 466:    */  }
/* 467:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.NamespaceStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */