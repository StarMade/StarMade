/*     */ package org.jaxen.dom4j;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.CDATA;
/*     */ import org.dom4j.Comment;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.ProcessingInstruction;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.Text;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.jaxen.DefaultNavigator;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.JaxenConstants;
/*     */ import org.jaxen.NamedAccessNavigator;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.XPath;
/*     */ import org.jaxen.saxpath.SAXPathException;
/*     */ import org.jaxen.util.SingleObjectIterator;
/*     */ 
/*     */ public class DocumentNavigator extends DefaultNavigator
/*     */   implements NamedAccessNavigator
/*     */ {
/*     */   private static final long serialVersionUID = 5582300797286535936L;
/*     */   private transient SAXReader reader;
/*     */ 
/*     */   public static Navigator getInstance()
/*     */   {
/* 113 */     return Singleton.instance;
/*     */   }
/*     */ 
/*     */   public boolean isElement(Object obj)
/*     */   {
/* 118 */     return obj instanceof Element;
/*     */   }
/*     */ 
/*     */   public boolean isComment(Object obj)
/*     */   {
/* 123 */     return obj instanceof Comment;
/*     */   }
/*     */ 
/*     */   public boolean isText(Object obj)
/*     */   {
/* 128 */     return ((obj instanceof Text)) || ((obj instanceof CDATA));
/*     */   }
/*     */ 
/*     */   public boolean isAttribute(Object obj)
/*     */   {
/* 135 */     return obj instanceof Attribute;
/*     */   }
/*     */ 
/*     */   public boolean isProcessingInstruction(Object obj)
/*     */   {
/* 140 */     return obj instanceof ProcessingInstruction;
/*     */   }
/*     */ 
/*     */   public boolean isDocument(Object obj)
/*     */   {
/* 145 */     return obj instanceof Document;
/*     */   }
/*     */ 
/*     */   public boolean isNamespace(Object obj)
/*     */   {
/* 150 */     return obj instanceof Namespace;
/*     */   }
/*     */ 
/*     */   public String getElementName(Object obj)
/*     */   {
/* 155 */     Element elem = (Element)obj;
/*     */ 
/* 157 */     return elem.getName();
/*     */   }
/*     */ 
/*     */   public String getElementNamespaceUri(Object obj)
/*     */   {
/* 162 */     Element elem = (Element)obj;
/*     */ 
/* 164 */     String uri = elem.getNamespaceURI();
/* 165 */     if (uri == null) {
/* 166 */       return "";
/*     */     }
/* 168 */     return uri;
/*     */   }
/*     */ 
/*     */   public String getElementQName(Object obj)
/*     */   {
/* 173 */     Element elem = (Element)obj;
/*     */ 
/* 175 */     return elem.getQualifiedName();
/*     */   }
/*     */ 
/*     */   public String getAttributeName(Object obj)
/*     */   {
/* 180 */     Attribute attr = (Attribute)obj;
/*     */ 
/* 182 */     return attr.getName();
/*     */   }
/*     */ 
/*     */   public String getAttributeNamespaceUri(Object obj)
/*     */   {
/* 187 */     Attribute attr = (Attribute)obj;
/*     */ 
/* 189 */     String uri = attr.getNamespaceURI();
/* 190 */     if (uri == null) {
/* 191 */       return "";
/*     */     }
/* 193 */     return uri;
/*     */   }
/*     */ 
/*     */   public String getAttributeQName(Object obj)
/*     */   {
/* 198 */     Attribute attr = (Attribute)obj;
/*     */ 
/* 200 */     return attr.getQualifiedName();
/*     */   }
/*     */ 
/*     */   public Iterator getChildAxisIterator(Object contextNode)
/*     */   {
/* 205 */     Iterator result = null;
/* 206 */     if ((contextNode instanceof Branch))
/*     */     {
/* 208 */       Branch node = (Branch)contextNode;
/* 209 */       result = node.nodeIterator();
/*     */     }
/* 211 */     if (result != null) {
/* 212 */       return result;
/*     */     }
/* 214 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getChildAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/*     */   {
/* 231 */     if ((contextNode instanceof Element)) {
/* 232 */       Element node = (Element)contextNode;
/* 233 */       return node.elementIterator(QName.get(localName, namespacePrefix, namespaceURI));
/*     */     }
/* 235 */     if ((contextNode instanceof Document)) {
/* 236 */       Document node = (Document)contextNode;
/* 237 */       Element el = node.getRootElement();
/* 238 */       if ((el == null) || (!el.getName().equals(localName))) {
/* 239 */         return JaxenConstants.EMPTY_ITERATOR;
/*     */       }
/* 241 */       if ((namespaceURI != null) && 
/* 242 */         (!namespaceURI.equals(el.getNamespaceURI()))) {
/* 243 */         return JaxenConstants.EMPTY_ITERATOR;
/*     */       }
/*     */ 
/* 246 */       return new SingleObjectIterator(el);
/*     */     }
/*     */ 
/* 249 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getParentAxisIterator(Object contextNode)
/*     */   {
/* 254 */     if ((contextNode instanceof Document))
/*     */     {
/* 256 */       return JaxenConstants.EMPTY_ITERATOR;
/*     */     }
/*     */ 
/* 259 */     Node node = (Node)contextNode;
/*     */ 
/* 261 */     Object parent = node.getParent();
/*     */ 
/* 263 */     if (parent == null)
/*     */     {
/* 265 */       parent = node.getDocument();
/*     */     }
/*     */ 
/* 268 */     return new SingleObjectIterator(parent);
/*     */   }
/*     */ 
/*     */   public Iterator getAttributeAxisIterator(Object contextNode)
/*     */   {
/* 273 */     if (!(contextNode instanceof Element))
/*     */     {
/* 275 */       return JaxenConstants.EMPTY_ITERATOR;
/*     */     }
/*     */ 
/* 278 */     Element elem = (Element)contextNode;
/*     */ 
/* 280 */     return elem.attributeIterator();
/*     */   }
/*     */ 
/*     */   public Iterator getAttributeAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/*     */   {
/* 296 */     if ((contextNode instanceof Element)) {
/* 297 */       Element node = (Element)contextNode;
/* 298 */       Attribute attr = node.attribute(QName.get(localName, namespacePrefix, namespaceURI));
/* 299 */       if (attr == null) {
/* 300 */         return JaxenConstants.EMPTY_ITERATOR;
/*     */       }
/* 302 */       return new SingleObjectIterator(attr);
/*     */     }
/* 304 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getNamespaceAxisIterator(Object contextNode)
/*     */   {
/* 309 */     if (!(contextNode instanceof Element))
/*     */     {
/* 311 */       return JaxenConstants.EMPTY_ITERATOR;
/*     */     }
/*     */ 
/* 314 */     Element element = (Element)contextNode;
/* 315 */     List nsList = new ArrayList();
/* 316 */     HashSet prefixes = new HashSet();
/*     */     Iterator iter;
/* 317 */     for (Element context = element; context != null; context = context.getParent()) {
/* 318 */       List declaredNS = new ArrayList(context.declaredNamespaces());
/* 319 */       declaredNS.add(context.getNamespace());
/*     */ 
/* 321 */       for (Iterator iter = context.attributes().iterator(); iter.hasNext(); )
/*     */       {
/* 323 */         Attribute attr = (Attribute)iter.next();
/* 324 */         declaredNS.add(attr.getNamespace());
/*     */       }
/*     */ 
/* 327 */       for (iter = declaredNS.iterator(); iter.hasNext(); )
/*     */       {
/* 329 */         Namespace namespace = (Namespace)iter.next();
/* 330 */         if (namespace != Namespace.NO_NAMESPACE)
/*     */         {
/* 332 */           String prefix = namespace.getPrefix();
/* 333 */           if (!prefixes.contains(prefix)) {
/* 334 */             prefixes.add(prefix);
/* 335 */             nsList.add(namespace.asXPathResult(element));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 340 */     nsList.add(Namespace.XML_NAMESPACE.asXPathResult(element));
/* 341 */     return nsList.iterator();
/*     */   }
/*     */ 
/*     */   public Object getDocumentNode(Object contextNode)
/*     */   {
/* 346 */     if ((contextNode instanceof Document))
/*     */     {
/* 348 */       return contextNode;
/*     */     }
/* 350 */     if ((contextNode instanceof Node))
/*     */     {
/* 352 */       Node node = (Node)contextNode;
/* 353 */       return node.getDocument();
/*     */     }
/* 355 */     return null;
/*     */   }
/*     */ 
/*     */   public XPath parseXPath(String xpath)
/*     */     throws SAXPathException
/*     */   {
/* 363 */     return new Dom4jXPath(xpath);
/*     */   }
/*     */ 
/*     */   public Object getParentNode(Object contextNode)
/*     */   {
/* 368 */     if ((contextNode instanceof Node))
/*     */     {
/* 370 */       Node node = (Node)contextNode;
/* 371 */       Object answer = node.getParent();
/* 372 */       if (answer == null)
/*     */       {
/* 374 */         answer = node.getDocument();
/* 375 */         if (answer == contextNode) {
/* 376 */           return null;
/*     */         }
/*     */       }
/* 379 */       return answer;
/*     */     }
/* 381 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTextStringValue(Object obj)
/*     */   {
/* 386 */     return getNodeStringValue((Node)obj);
/*     */   }
/*     */ 
/*     */   public String getElementStringValue(Object obj)
/*     */   {
/* 391 */     return getNodeStringValue((Node)obj);
/*     */   }
/*     */ 
/*     */   public String getAttributeStringValue(Object obj)
/*     */   {
/* 396 */     return getNodeStringValue((Node)obj);
/*     */   }
/*     */ 
/*     */   private String getNodeStringValue(Node node)
/*     */   {
/* 401 */     return node.getStringValue();
/*     */   }
/*     */ 
/*     */   public String getNamespaceStringValue(Object obj)
/*     */   {
/* 406 */     Namespace ns = (Namespace)obj;
/*     */ 
/* 408 */     return ns.getURI();
/*     */   }
/*     */ 
/*     */   public String getNamespacePrefix(Object obj)
/*     */   {
/* 413 */     Namespace ns = (Namespace)obj;
/*     */ 
/* 415 */     return ns.getPrefix();
/*     */   }
/*     */ 
/*     */   public String getCommentStringValue(Object obj)
/*     */   {
/* 420 */     Comment cmt = (Comment)obj;
/*     */ 
/* 422 */     return cmt.getText();
/*     */   }
/*     */ 
/*     */   public String translateNamespacePrefixToUri(String prefix, Object context)
/*     */   {
/* 427 */     Element element = null;
/* 428 */     if ((context instanceof Element))
/*     */     {
/* 430 */       element = (Element)context;
/*     */     }
/* 432 */     else if ((context instanceof Node))
/*     */     {
/* 434 */       Node node = (Node)context;
/* 435 */       element = node.getParent();
/*     */     }
/* 437 */     if (element != null)
/*     */     {
/* 439 */       Namespace namespace = element.getNamespaceForPrefix(prefix);
/*     */ 
/* 441 */       if (namespace != null)
/*     */       {
/* 443 */         return namespace.getURI();
/*     */       }
/*     */     }
/* 446 */     return null;
/*     */   }
/*     */ 
/*     */   public short getNodeType(Object node)
/*     */   {
/* 451 */     if ((node instanceof Node))
/*     */     {
/* 453 */       return ((Node)node).getNodeType();
/*     */     }
/* 455 */     return 0;
/*     */   }
/*     */ 
/*     */   public Object getDocument(String uri) throws FunctionCallException
/*     */   {
/*     */     try
/*     */     {
/* 462 */       return getSAXReader().read(uri);
/*     */     }
/*     */     catch (DocumentException e)
/*     */     {
/* 466 */       throw new FunctionCallException("Failed to parse document for URI: " + uri, e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionTarget(Object obj)
/*     */   {
/* 472 */     ProcessingInstruction pi = (ProcessingInstruction)obj;
/*     */ 
/* 474 */     return pi.getTarget();
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionData(Object obj)
/*     */   {
/* 479 */     ProcessingInstruction pi = (ProcessingInstruction)obj;
/*     */ 
/* 481 */     return pi.getText();
/*     */   }
/*     */ 
/*     */   public SAXReader getSAXReader()
/*     */   {
/* 488 */     if (this.reader == null)
/*     */     {
/* 490 */       this.reader = new SAXReader();
/* 491 */       this.reader.setMergeAdjacentText(true);
/*     */     }
/* 493 */     return this.reader;
/*     */   }
/*     */ 
/*     */   public void setSAXReader(SAXReader reader)
/*     */   {
/* 498 */     this.reader = reader;
/*     */   }
/*     */ 
/*     */   private static class Singleton
/*     */   {
/* 106 */     private static DocumentNavigator instance = new DocumentNavigator();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom4j.DocumentNavigator
 * JD-Core Version:    0.6.2
 */