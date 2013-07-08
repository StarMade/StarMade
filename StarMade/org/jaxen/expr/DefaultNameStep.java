/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Collections;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import org.jaxen.Context;
/*   8:    */import org.jaxen.ContextSupport;
/*   9:    */import org.jaxen.JaxenException;
/*  10:    */import org.jaxen.Navigator;
/*  11:    */import org.jaxen.UnresolvableException;
/*  12:    */import org.jaxen.expr.iter.IterableAxis;
/*  13:    */
/*  87:    *//**
/*  88:    */ * @deprecated
/*  89:    */ */
/*  90:    */public class DefaultNameStep
/*  91:    */  extends DefaultStep
/*  92:    */  implements NameStep
/*  93:    */{
/*  94:    */  private static final long serialVersionUID = 428414912247718390L;
/*  95:    */  private String prefix;
/*  96:    */  private String localName;
/*  97:    */  private boolean matchesAnyName;
/*  98:    */  private boolean hasPrefix;
/*  99:    */  
/* 100:    */  public DefaultNameStep(IterableAxis axis, String prefix, String localName, PredicateSet predicateSet)
/* 101:    */  {
/* 102:102 */    super(axis, predicateSet);
/* 103:    */    
/* 104:104 */    this.prefix = prefix;
/* 105:105 */    this.localName = localName;
/* 106:106 */    this.matchesAnyName = "*".equals(localName);
/* 107:107 */    this.hasPrefix = ((this.prefix != null) && (this.prefix.length() > 0));
/* 108:    */  }
/* 109:    */  
/* 114:    */  public String getPrefix()
/* 115:    */  {
/* 116:116 */    return this.prefix;
/* 117:    */  }
/* 118:    */  
/* 123:    */  public String getLocalName()
/* 124:    */  {
/* 125:125 */    return this.localName;
/* 126:    */  }
/* 127:    */  
/* 132:    */  public boolean isMatchesAnyName()
/* 133:    */  {
/* 134:134 */    return this.matchesAnyName;
/* 135:    */  }
/* 136:    */  
/* 141:    */  public String getText()
/* 142:    */  {
/* 143:143 */    StringBuffer buf = new StringBuffer(64);
/* 144:144 */    buf.append(getAxisName()).append("::");
/* 145:145 */    if ((getPrefix() != null) && (getPrefix().length() > 0)) {
/* 146:146 */      buf.append(getPrefix()).append(':');
/* 147:    */    }
/* 148:148 */    return getLocalName() + super.getText();
/* 149:    */  }
/* 150:    */  
/* 155:    */  public List evaluate(Context context)
/* 156:    */    throws JaxenException
/* 157:    */  {
/* 158:158 */    List contextNodeSet = context.getNodeSet();
/* 159:159 */    int contextSize = contextNodeSet.size();
/* 160:    */    
/* 161:161 */    if (contextSize == 0) {
/* 162:162 */      return Collections.EMPTY_LIST;
/* 163:    */    }
/* 164:164 */    ContextSupport support = context.getContextSupport();
/* 165:165 */    IterableAxis iterableAxis = getIterableAxis();
/* 166:166 */    boolean namedAccess = (!this.matchesAnyName) && (iterableAxis.supportsNamedAccess(support));
/* 167:    */    
/* 169:169 */    if (contextSize == 1) {
/* 170:170 */      Object contextNode = contextNodeSet.get(0);
/* 171:171 */      if (namedAccess)
/* 172:    */      {
/* 173:173 */        String uri = null;
/* 174:174 */        if (this.hasPrefix) {
/* 175:175 */          uri = support.translateNamespacePrefixToUri(this.prefix);
/* 176:176 */          if (uri == null) {
/* 177:177 */            throw new UnresolvableException("XPath expression uses unbound namespace prefix " + this.prefix);
/* 178:    */          }
/* 179:    */        }
/* 180:180 */        Iterator axisNodeIter = iterableAxis.namedAccessIterator(contextNode, support, this.localName, this.prefix, uri);
/* 181:    */        
/* 182:182 */        if ((axisNodeIter == null) || (!axisNodeIter.hasNext())) {
/* 183:183 */          return Collections.EMPTY_LIST;
/* 184:    */        }
/* 185:    */        
/* 188:188 */        List newNodeSet = new ArrayList();
/* 189:189 */        while (axisNodeIter.hasNext()) {
/* 190:190 */          newNodeSet.add(axisNodeIter.next());
/* 191:    */        }
/* 192:    */        
/* 194:194 */        return getPredicateSet().evaluatePredicates(newNodeSet, support);
/* 195:    */      }
/* 196:    */      
/* 199:199 */      Iterator axisNodeIter = iterableAxis.iterator(contextNode, support);
/* 200:200 */      if ((axisNodeIter == null) || (!axisNodeIter.hasNext())) {
/* 201:201 */        return Collections.EMPTY_LIST;
/* 202:    */      }
/* 203:    */      
/* 206:206 */      List newNodeSet = new ArrayList(contextSize);
/* 207:207 */      while (axisNodeIter.hasNext()) {
/* 208:208 */        Object eachAxisNode = axisNodeIter.next();
/* 209:209 */        if (matches(eachAxisNode, support)) {
/* 210:210 */          newNodeSet.add(eachAxisNode);
/* 211:    */        }
/* 212:    */      }
/* 213:    */      
/* 215:215 */      return getPredicateSet().evaluatePredicates(newNodeSet, support);
/* 216:    */    }
/* 217:    */    
/* 220:220 */    IdentitySet unique = new IdentitySet();
/* 221:221 */    List interimSet = new ArrayList(contextSize);
/* 222:222 */    List newNodeSet = new ArrayList(contextSize);
/* 223:    */    
/* 224:224 */    if (namedAccess) {
/* 225:225 */      String uri = null;
/* 226:226 */      if (this.hasPrefix) {
/* 227:227 */        uri = support.translateNamespacePrefixToUri(this.prefix);
/* 228:228 */        if (uri == null) {
/* 229:229 */          throw new UnresolvableException("XPath expression uses unbound namespace prefix " + this.prefix);
/* 230:    */        }
/* 231:    */      }
/* 232:232 */      for (int i = 0; i < contextSize; i++) {
/* 233:233 */        Object eachContextNode = contextNodeSet.get(i);
/* 234:    */        
/* 235:235 */        Iterator axisNodeIter = iterableAxis.namedAccessIterator(eachContextNode, support, this.localName, this.prefix, uri);
/* 236:    */        
/* 237:237 */        if ((axisNodeIter != null) && (axisNodeIter.hasNext()))
/* 238:    */        {
/* 241:241 */          while (axisNodeIter.hasNext())
/* 242:    */          {
/* 243:243 */            Object eachAxisNode = axisNodeIter.next();
/* 244:244 */            interimSet.add(eachAxisNode);
/* 245:    */          }
/* 246:    */          
/* 248:248 */          List predicateNodes = getPredicateSet().evaluatePredicates(interimSet, support);
/* 249:    */          
/* 251:251 */          Iterator predicateNodeIter = predicateNodes.iterator();
/* 252:252 */          while (predicateNodeIter.hasNext())
/* 253:    */          {
/* 254:254 */            Object eachPredicateNode = predicateNodeIter.next();
/* 255:255 */            if (!unique.contains(eachPredicateNode))
/* 256:    */            {
/* 257:257 */              unique.add(eachPredicateNode);
/* 258:258 */              newNodeSet.add(eachPredicateNode);
/* 259:    */            }
/* 260:    */          }
/* 261:261 */          interimSet.clear();
/* 262:    */        }
/* 263:    */      }
/* 264:    */    } else {
/* 265:265 */      for (int i = 0; i < contextSize; i++) {
/* 266:266 */        Object eachContextNode = contextNodeSet.get(i);
/* 267:    */        
/* 268:268 */        Iterator axisNodeIter = axisIterator(eachContextNode, support);
/* 269:269 */        if ((axisNodeIter != null) && (axisNodeIter.hasNext()))
/* 270:    */        {
/* 282:282 */          while (axisNodeIter.hasNext()) {
/* 283:283 */            Object eachAxisNode = axisNodeIter.next();
/* 284:    */            
/* 285:285 */            if (matches(eachAxisNode, support)) {
/* 286:286 */              interimSet.add(eachAxisNode);
/* 287:    */            }
/* 288:    */          }
/* 289:    */          
/* 291:291 */          List predicateNodes = getPredicateSet().evaluatePredicates(interimSet, support);
/* 292:    */          
/* 294:294 */          Iterator predicateNodeIter = predicateNodes.iterator();
/* 295:295 */          while (predicateNodeIter.hasNext())
/* 296:    */          {
/* 297:297 */            Object eachPredicateNode = predicateNodeIter.next();
/* 298:298 */            if (!unique.contains(eachPredicateNode))
/* 299:    */            {
/* 300:300 */              unique.add(eachPredicateNode);
/* 301:301 */              newNodeSet.add(eachPredicateNode);
/* 302:    */            }
/* 303:    */          }
/* 304:304 */          interimSet.clear();
/* 305:    */        }
/* 306:    */      }
/* 307:    */    }
/* 308:308 */    return newNodeSet;
/* 309:    */  }
/* 310:    */  
/* 318:    */  public boolean matches(Object node, ContextSupport contextSupport)
/* 319:    */    throws JaxenException
/* 320:    */  {
/* 321:321 */    Navigator nav = contextSupport.getNavigator();
/* 322:322 */    String myUri = null;
/* 323:323 */    String nodeName = null;
/* 324:324 */    String nodeUri = null;
/* 325:    */    
/* 326:326 */    if (nav.isElement(node)) {
/* 327:327 */      nodeName = nav.getElementName(node);
/* 328:328 */      nodeUri = nav.getElementNamespaceUri(node);
/* 329:    */    } else {
/* 330:330 */      if (nav.isText(node)) {
/* 331:331 */        return false;
/* 332:    */      }
/* 333:333 */      if (nav.isAttribute(node)) {
/* 334:334 */        if (getAxis() != 9) {
/* 335:335 */          return false;
/* 336:    */        }
/* 337:337 */        nodeName = nav.getAttributeName(node);
/* 338:338 */        nodeUri = nav.getAttributeNamespaceUri(node);
/* 339:    */      }
/* 340:    */      else {
/* 341:341 */        if (nav.isDocument(node)) {
/* 342:342 */          return false;
/* 343:    */        }
/* 344:344 */        if (nav.isNamespace(node)) {
/* 345:345 */          if (getAxis() != 10)
/* 346:    */          {
/* 347:347 */            return false;
/* 348:    */          }
/* 349:349 */          nodeName = nav.getNamespacePrefix(node);
/* 350:    */        }
/* 351:    */        else {
/* 352:352 */          return false;
/* 353:    */        }
/* 354:    */      } }
/* 355:355 */    if (this.hasPrefix) {
/* 356:356 */      myUri = contextSupport.translateNamespacePrefixToUri(this.prefix);
/* 357:357 */      if (myUri == null) {
/* 358:358 */        throw new UnresolvableException("Cannot resolve namespace prefix '" + this.prefix + "'");
/* 359:    */      }
/* 360:    */    }
/* 361:361 */    else if (this.matchesAnyName) {
/* 362:362 */      return true;
/* 363:    */    }
/* 364:    */    
/* 367:367 */    if (hasNamespace(myUri) != hasNamespace(nodeUri)) {
/* 368:368 */      return false;
/* 369:    */    }
/* 370:    */    
/* 374:374 */    if ((this.matchesAnyName) || (nodeName.equals(getLocalName()))) {
/* 375:375 */      return matchesNamespaceURIs(myUri, nodeUri);
/* 376:    */    }
/* 377:    */    
/* 378:378 */    return false;
/* 379:    */  }
/* 380:    */  
/* 386:    */  private boolean hasNamespace(String uri)
/* 387:    */  {
/* 388:388 */    return (uri != null) && (uri.length() > 0);
/* 389:    */  }
/* 390:    */  
/* 397:    */  protected boolean matchesNamespaceURIs(String uri1, String uri2)
/* 398:    */  {
/* 399:399 */    if (uri1 == uri2) {
/* 400:400 */      return true;
/* 401:    */    }
/* 402:402 */    if (uri1 == null) {
/* 403:403 */      return uri2.length() == 0;
/* 404:    */    }
/* 405:405 */    if (uri2 == null) {
/* 406:406 */      return uri1.length() == 0;
/* 407:    */    }
/* 408:408 */    return uri1.equals(uri2);
/* 409:    */  }
/* 410:    */  
/* 415:    */  public String toString()
/* 416:    */  {
/* 417:417 */    String prefix = getPrefix();
/* 418:418 */    String qName = getPrefix() + ":" + getLocalName();
/* 419:419 */    return "[(DefaultNameStep): " + qName + "]";
/* 420:    */  }
/* 421:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultNameStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */