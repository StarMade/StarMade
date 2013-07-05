/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnresolvableException;
/*     */ import org.jaxen.expr.iter.IterableAxis;
/*     */ 
/*     */ /** @deprecated */
/*     */ public class DefaultNameStep extends DefaultStep
/*     */   implements NameStep
/*     */ {
/*     */   private static final long serialVersionUID = 428414912247718390L;
/*     */   private String prefix;
/*     */   private String localName;
/*     */   private boolean matchesAnyName;
/*     */   private boolean hasPrefix;
/*     */ 
/*     */   public DefaultNameStep(IterableAxis axis, String prefix, String localName, PredicateSet predicateSet)
/*     */   {
/* 102 */     super(axis, predicateSet);
/*     */ 
/* 104 */     this.prefix = prefix;
/* 105 */     this.localName = localName;
/* 106 */     this.matchesAnyName = "*".equals(localName);
/* 107 */     this.hasPrefix = ((this.prefix != null) && (this.prefix.length() > 0));
/*     */   }
/*     */ 
/*     */   public String getPrefix()
/*     */   {
/* 116 */     return this.prefix;
/*     */   }
/*     */ 
/*     */   public String getLocalName()
/*     */   {
/* 125 */     return this.localName;
/*     */   }
/*     */ 
/*     */   public boolean isMatchesAnyName()
/*     */   {
/* 134 */     return this.matchesAnyName;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/* 143 */     StringBuffer buf = new StringBuffer(64);
/* 144 */     buf.append(getAxisName()).append("::");
/* 145 */     if ((getPrefix() != null) && (getPrefix().length() > 0)) {
/* 146 */       buf.append(getPrefix()).append(':');
/*     */     }
/* 148 */     return getLocalName() + super.getText();
/*     */   }
/*     */ 
/*     */   public List evaluate(Context context)
/*     */     throws JaxenException
/*     */   {
/* 158 */     List contextNodeSet = context.getNodeSet();
/* 159 */     int contextSize = contextNodeSet.size();
/*     */ 
/* 161 */     if (contextSize == 0) {
/* 162 */       return Collections.EMPTY_LIST;
/*     */     }
/* 164 */     ContextSupport support = context.getContextSupport();
/* 165 */     IterableAxis iterableAxis = getIterableAxis();
/* 166 */     boolean namedAccess = (!this.matchesAnyName) && (iterableAxis.supportsNamedAccess(support));
/*     */ 
/* 169 */     if (contextSize == 1) {
/* 170 */       Object contextNode = contextNodeSet.get(0);
/* 171 */       if (namedAccess)
/*     */       {
/* 173 */         String uri = null;
/* 174 */         if (this.hasPrefix) {
/* 175 */           uri = support.translateNamespacePrefixToUri(this.prefix);
/* 176 */           if (uri == null) {
/* 177 */             throw new UnresolvableException("XPath expression uses unbound namespace prefix " + this.prefix);
/*     */           }
/*     */         }
/* 180 */         Iterator axisNodeIter = iterableAxis.namedAccessIterator(contextNode, support, this.localName, this.prefix, uri);
/*     */ 
/* 182 */         if ((axisNodeIter == null) || (!axisNodeIter.hasNext())) {
/* 183 */           return Collections.EMPTY_LIST;
/*     */         }
/*     */ 
/* 188 */         List newNodeSet = new ArrayList();
/* 189 */         while (axisNodeIter.hasNext()) {
/* 190 */           newNodeSet.add(axisNodeIter.next());
/*     */         }
/*     */ 
/* 194 */         return getPredicateSet().evaluatePredicates(newNodeSet, support);
/*     */       }
/*     */ 
/* 199 */       Iterator axisNodeIter = iterableAxis.iterator(contextNode, support);
/* 200 */       if ((axisNodeIter == null) || (!axisNodeIter.hasNext())) {
/* 201 */         return Collections.EMPTY_LIST;
/*     */       }
/*     */ 
/* 206 */       List newNodeSet = new ArrayList(contextSize);
/* 207 */       while (axisNodeIter.hasNext()) {
/* 208 */         Object eachAxisNode = axisNodeIter.next();
/* 209 */         if (matches(eachAxisNode, support)) {
/* 210 */           newNodeSet.add(eachAxisNode);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 215 */       return getPredicateSet().evaluatePredicates(newNodeSet, support);
/*     */     }
/*     */ 
/* 220 */     IdentitySet unique = new IdentitySet();
/* 221 */     List interimSet = new ArrayList(contextSize);
/* 222 */     List newNodeSet = new ArrayList(contextSize);
/*     */ 
/* 224 */     if (namedAccess) {
/* 225 */       String uri = null;
/* 226 */       if (this.hasPrefix) {
/* 227 */         uri = support.translateNamespacePrefixToUri(this.prefix);
/* 228 */         if (uri == null) {
/* 229 */           throw new UnresolvableException("XPath expression uses unbound namespace prefix " + this.prefix);
/*     */         }
/*     */       }
/* 232 */       for (int i = 0; i < contextSize; i++) {
/* 233 */         Object eachContextNode = contextNodeSet.get(i);
/*     */ 
/* 235 */         Iterator axisNodeIter = iterableAxis.namedAccessIterator(eachContextNode, support, this.localName, this.prefix, uri);
/*     */ 
/* 237 */         if ((axisNodeIter != null) && (axisNodeIter.hasNext()))
/*     */         {
/* 241 */           while (axisNodeIter.hasNext())
/*     */           {
/* 243 */             Object eachAxisNode = axisNodeIter.next();
/* 244 */             interimSet.add(eachAxisNode);
/*     */           }
/*     */ 
/* 248 */           List predicateNodes = getPredicateSet().evaluatePredicates(interimSet, support);
/*     */ 
/* 251 */           Iterator predicateNodeIter = predicateNodes.iterator();
/* 252 */           while (predicateNodeIter.hasNext())
/*     */           {
/* 254 */             Object eachPredicateNode = predicateNodeIter.next();
/* 255 */             if (!unique.contains(eachPredicateNode))
/*     */             {
/* 257 */               unique.add(eachPredicateNode);
/* 258 */               newNodeSet.add(eachPredicateNode);
/*     */             }
/*     */           }
/* 261 */           interimSet.clear();
/*     */         }
/*     */       }
/*     */     } else {
/* 265 */       for (int i = 0; i < contextSize; i++) {
/* 266 */         Object eachContextNode = contextNodeSet.get(i);
/*     */ 
/* 268 */         Iterator axisNodeIter = axisIterator(eachContextNode, support);
/* 269 */         if ((axisNodeIter != null) && (axisNodeIter.hasNext()))
/*     */         {
/* 282 */           while (axisNodeIter.hasNext()) {
/* 283 */             Object eachAxisNode = axisNodeIter.next();
/*     */ 
/* 285 */             if (matches(eachAxisNode, support)) {
/* 286 */               interimSet.add(eachAxisNode);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 291 */           List predicateNodes = getPredicateSet().evaluatePredicates(interimSet, support);
/*     */ 
/* 294 */           Iterator predicateNodeIter = predicateNodes.iterator();
/* 295 */           while (predicateNodeIter.hasNext())
/*     */           {
/* 297 */             Object eachPredicateNode = predicateNodeIter.next();
/* 298 */             if (!unique.contains(eachPredicateNode))
/*     */             {
/* 300 */               unique.add(eachPredicateNode);
/* 301 */               newNodeSet.add(eachPredicateNode);
/*     */             }
/*     */           }
/* 304 */           interimSet.clear();
/*     */         }
/*     */       }
/*     */     }
/* 308 */     return newNodeSet;
/*     */   }
/*     */ 
/*     */   public boolean matches(Object node, ContextSupport contextSupport)
/*     */     throws JaxenException
/*     */   {
/* 321 */     Navigator nav = contextSupport.getNavigator();
/* 322 */     String myUri = null;
/* 323 */     String nodeName = null;
/* 324 */     String nodeUri = null;
/*     */ 
/* 326 */     if (nav.isElement(node)) {
/* 327 */       nodeName = nav.getElementName(node);
/* 328 */       nodeUri = nav.getElementNamespaceUri(node);
/*     */     } else {
/* 330 */       if (nav.isText(node)) {
/* 331 */         return false;
/*     */       }
/* 333 */       if (nav.isAttribute(node)) {
/* 334 */         if (getAxis() != 9) {
/* 335 */           return false;
/*     */         }
/* 337 */         nodeName = nav.getAttributeName(node);
/* 338 */         nodeUri = nav.getAttributeNamespaceUri(node);
/*     */       }
/*     */       else {
/* 341 */         if (nav.isDocument(node)) {
/* 342 */           return false;
/*     */         }
/* 344 */         if (nav.isNamespace(node)) {
/* 345 */           if (getAxis() != 10)
/*     */           {
/* 347 */             return false;
/*     */           }
/* 349 */           nodeName = nav.getNamespacePrefix(node);
/*     */         }
/*     */         else {
/* 352 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 355 */     if (this.hasPrefix) {
/* 356 */       myUri = contextSupport.translateNamespacePrefixToUri(this.prefix);
/* 357 */       if (myUri == null) {
/* 358 */         throw new UnresolvableException("Cannot resolve namespace prefix '" + this.prefix + "'");
/*     */       }
/*     */     }
/* 361 */     else if (this.matchesAnyName) {
/* 362 */       return true;
/*     */     }
/*     */ 
/* 367 */     if (hasNamespace(myUri) != hasNamespace(nodeUri)) {
/* 368 */       return false;
/*     */     }
/*     */ 
/* 374 */     if ((this.matchesAnyName) || (nodeName.equals(getLocalName()))) {
/* 375 */       return matchesNamespaceURIs(myUri, nodeUri);
/*     */     }
/*     */ 
/* 378 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean hasNamespace(String uri)
/*     */   {
/* 388 */     return (uri != null) && (uri.length() > 0);
/*     */   }
/*     */ 
/*     */   protected boolean matchesNamespaceURIs(String uri1, String uri2)
/*     */   {
/* 399 */     if (uri1 == uri2) {
/* 400 */       return true;
/*     */     }
/* 402 */     if (uri1 == null) {
/* 403 */       return uri2.length() == 0;
/*     */     }
/* 405 */     if (uri2 == null) {
/* 406 */       return uri1.length() == 0;
/*     */     }
/* 408 */     return uri1.equals(uri2);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 417 */     String prefix = getPrefix();
/* 418 */     String qName = getPrefix() + ":" + getLocalName();
/* 419 */     return "[(DefaultNameStep): " + qName + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultNameStep
 * JD-Core Version:    0.6.2
 */