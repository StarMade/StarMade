/*     */ package org.jaxen.jdom;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jaxen.DefaultNavigator;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.JaxenConstants;
/*     */ import org.jaxen.NamedAccessNavigator;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.XPath;
/*     */ import org.jaxen.saxpath.SAXPathException;
/*     */ import org.jaxen.util.SingleObjectIterator;
/*     */ import org.jdom.Attribute;
/*     */ import org.jdom.CDATA;
/*     */ import org.jdom.Comment;
/*     */ import org.jdom.Document;
/*     */ import org.jdom.Element;
/*     */ import org.jdom.Namespace;
/*     */ import org.jdom.ProcessingInstruction;
/*     */ import org.jdom.Text;
/*     */ import org.jdom.input.SAXBuilder;
/*     */ 
/*     */ public class DocumentNavigator extends DefaultNavigator
/*     */   implements NamedAccessNavigator
/*     */ {
/*     */   private static final long serialVersionUID = -1636727587303584165L;
/*     */ 
/*     */   public static Navigator getInstance()
/*     */   {
/* 105 */     return Singleton.instance;
/*     */   }
/*     */ 
/*     */   public boolean isElement(Object obj)
/*     */   {
/* 110 */     return obj instanceof Element;
/*     */   }
/*     */ 
/*     */   public boolean isComment(Object obj)
/*     */   {
/* 115 */     return obj instanceof Comment;
/*     */   }
/*     */ 
/*     */   public boolean isText(Object obj)
/*     */   {
/* 120 */     return ((obj instanceof Text)) || ((obj instanceof CDATA));
/*     */   }
/*     */ 
/*     */   public boolean isAttribute(Object obj)
/*     */   {
/* 127 */     return obj instanceof Attribute;
/*     */   }
/*     */ 
/*     */   public boolean isProcessingInstruction(Object obj)
/*     */   {
/* 132 */     return obj instanceof ProcessingInstruction;
/*     */   }
/*     */ 
/*     */   public boolean isDocument(Object obj)
/*     */   {
/* 137 */     return obj instanceof Document;
/*     */   }
/*     */ 
/*     */   public boolean isNamespace(Object obj)
/*     */   {
/* 142 */     return ((obj instanceof Namespace)) || ((obj instanceof XPathNamespace));
/*     */   }
/*     */ 
/*     */   public String getElementName(Object obj)
/*     */   {
/* 147 */     Element elem = (Element)obj;
/*     */ 
/* 149 */     return elem.getName();
/*     */   }
/*     */ 
/*     */   public String getElementNamespaceUri(Object obj)
/*     */   {
/* 154 */     Element elem = (Element)obj;
/*     */ 
/* 156 */     String uri = elem.getNamespaceURI();
/* 157 */     if ((uri != null) && (uri.length() == 0)) {
/* 158 */       return null;
/*     */     }
/* 160 */     return uri;
/*     */   }
/*     */ 
/*     */   public String getAttributeName(Object obj)
/*     */   {
/* 165 */     Attribute attr = (Attribute)obj;
/*     */ 
/* 167 */     return attr.getName();
/*     */   }
/*     */ 
/*     */   public String getAttributeNamespaceUri(Object obj)
/*     */   {
/* 172 */     Attribute attr = (Attribute)obj;
/*     */ 
/* 174 */     String uri = attr.getNamespaceURI();
/* 175 */     if ((uri != null) && (uri.length() == 0)) {
/* 176 */       return null;
/*     */     }
/* 178 */     return uri;
/*     */   }
/*     */ 
/*     */   public Iterator getChildAxisIterator(Object contextNode)
/*     */   {
/* 183 */     if ((contextNode instanceof Element))
/*     */     {
/* 185 */       return ((Element)contextNode).getContent().iterator();
/*     */     }
/* 187 */     if ((contextNode instanceof Document))
/*     */     {
/* 189 */       return ((Document)contextNode).getContent().iterator();
/*     */     }
/*     */ 
/* 192 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getChildAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/*     */   {
/* 208 */     if ((contextNode instanceof Element)) {
/* 209 */       Element node = (Element)contextNode;
/* 210 */       if (namespaceURI == null) {
/* 211 */         return node.getChildren(localName).iterator();
/*     */       }
/* 213 */       return node.getChildren(localName, Namespace.getNamespace(namespacePrefix, namespaceURI)).iterator();
/*     */     }
/* 215 */     if ((contextNode instanceof Document)) {
/* 216 */       Document node = (Document)contextNode;
/*     */ 
/* 218 */       Element el = node.getRootElement();
/* 219 */       if (!el.getName().equals(localName)) {
/* 220 */         return JaxenConstants.EMPTY_ITERATOR;
/*     */       }
/* 222 */       if (namespaceURI != null)
/*     */       {
/* 224 */         if (!Namespace.getNamespace(namespacePrefix, namespaceURI).equals(el.getNamespace())) {
/* 225 */           return JaxenConstants.EMPTY_ITERATOR;
/*     */         }
/*     */       }
/* 228 */       else if (el.getNamespace() != Namespace.NO_NAMESPACE) {
/* 229 */         return JaxenConstants.EMPTY_ITERATOR;
/*     */       }
/*     */ 
/* 232 */       return new SingleObjectIterator(el);
/*     */     }
/*     */ 
/* 235 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getNamespaceAxisIterator(Object contextNode)
/*     */   {
/* 240 */     if (!(contextNode instanceof Element))
/*     */     {
/* 242 */       return JaxenConstants.EMPTY_ITERATOR;
/*     */     }
/*     */ 
/* 245 */     Element elem = (Element)contextNode;
/*     */ 
/* 247 */     Map nsMap = new HashMap();
/*     */ 
/* 249 */     Element current = elem;
/*     */ 
/* 251 */     while (current != null)
/*     */     {
/* 253 */       Namespace ns = current.getNamespace();
/*     */ 
/* 255 */       if ((ns != Namespace.NO_NAMESPACE) && 
/* 256 */         (!nsMap.containsKey(ns.getPrefix()))) {
/* 257 */         nsMap.put(ns.getPrefix(), new XPathNamespace(elem, ns));
/*     */       }
/*     */ 
/* 260 */       Iterator additional = current.getAdditionalNamespaces().iterator();
/*     */ 
/* 262 */       while (additional.hasNext())
/*     */       {
/* 264 */         ns = (Namespace)additional.next();
/* 265 */         if (!nsMap.containsKey(ns.getPrefix())) {
/* 266 */           nsMap.put(ns.getPrefix(), new XPathNamespace(elem, ns));
/*     */         }
/*     */       }
/* 269 */       Iterator attributes = current.getAttributes().iterator();
/*     */ 
/* 271 */       while (attributes.hasNext())
/*     */       {
/* 273 */         Attribute attribute = (Attribute)attributes.next();
/*     */ 
/* 275 */         Namespace attrNS = attribute.getNamespace();
/*     */ 
/* 277 */         if ((attrNS != Namespace.NO_NAMESPACE) && 
/* 278 */           (!nsMap.containsKey(attrNS.getPrefix()))) {
/* 279 */           nsMap.put(attrNS.getPrefix(), new XPathNamespace(elem, attrNS));
/*     */         }
/*     */       }
/*     */ 
/* 283 */       if ((current.getParent() instanceof Element))
/* 284 */         current = (Element)current.getParent();
/*     */       else {
/* 286 */         current = null;
/*     */       }
/*     */     }
/*     */ 
/* 290 */     nsMap.put("xml", new XPathNamespace(elem, Namespace.XML_NAMESPACE));
/*     */ 
/* 292 */     return nsMap.values().iterator();
/*     */   }
/*     */ 
/*     */   public Iterator getParentAxisIterator(Object contextNode)
/*     */   {
/* 297 */     Object parent = null;
/*     */ 
/* 299 */     if ((contextNode instanceof Document))
/*     */     {
/* 301 */       return JaxenConstants.EMPTY_ITERATOR;
/*     */     }
/* 303 */     if ((contextNode instanceof Element))
/*     */     {
/* 305 */       parent = ((Element)contextNode).getParent();
/*     */ 
/* 307 */       if (parent == null)
/*     */       {
/* 309 */         if (((Element)contextNode).isRootElement())
/*     */         {
/* 311 */           parent = ((Element)contextNode).getDocument();
/*     */         }
/*     */       }
/*     */     }
/* 315 */     else if ((contextNode instanceof Attribute))
/*     */     {
/* 317 */       parent = ((Attribute)contextNode).getParent();
/*     */     }
/* 319 */     else if ((contextNode instanceof XPathNamespace))
/*     */     {
/* 321 */       parent = ((XPathNamespace)contextNode).getJDOMElement();
/*     */     }
/* 323 */     else if ((contextNode instanceof ProcessingInstruction))
/*     */     {
/* 325 */       parent = ((ProcessingInstruction)contextNode).getParent();
/*     */     }
/* 327 */     else if ((contextNode instanceof Comment))
/*     */     {
/* 329 */       parent = ((Comment)contextNode).getParent();
/*     */     }
/* 331 */     else if ((contextNode instanceof Text))
/*     */     {
/* 333 */       parent = ((Text)contextNode).getParent();
/*     */     }
/*     */ 
/* 336 */     if (parent != null)
/*     */     {
/* 338 */       return new SingleObjectIterator(parent);
/*     */     }
/*     */ 
/* 341 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getAttributeAxisIterator(Object contextNode)
/*     */   {
/* 346 */     if (!(contextNode instanceof Element))
/*     */     {
/* 348 */       return JaxenConstants.EMPTY_ITERATOR;
/*     */     }
/*     */ 
/* 351 */     Element elem = (Element)contextNode;
/*     */ 
/* 353 */     return elem.getAttributes().iterator();
/*     */   }
/*     */ 
/*     */   public Iterator getAttributeAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/*     */   {
/* 369 */     if ((contextNode instanceof Element)) {
/* 370 */       Element node = (Element)contextNode;
/* 371 */       Namespace namespace = namespaceURI == null ? Namespace.NO_NAMESPACE : Namespace.getNamespace(namespacePrefix, namespaceURI);
/*     */ 
/* 373 */       Attribute attr = node.getAttribute(localName, namespace);
/* 374 */       if (attr != null) {
/* 375 */         return new SingleObjectIterator(attr);
/*     */       }
/*     */     }
/* 378 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public XPath parseXPath(String xpath)
/*     */     throws SAXPathException
/*     */   {
/* 386 */     return new JDOMXPath(xpath);
/*     */   }
/*     */ 
/*     */   public Object getDocumentNode(Object contextNode)
/*     */   {
/* 391 */     if ((contextNode instanceof Document))
/*     */     {
/* 393 */       return contextNode;
/*     */     }
/*     */ 
/* 396 */     Element elem = (Element)contextNode;
/*     */ 
/* 398 */     return elem.getDocument();
/*     */   }
/*     */ 
/*     */   public String getElementQName(Object obj)
/*     */   {
/* 403 */     Element elem = (Element)obj;
/*     */ 
/* 405 */     String prefix = elem.getNamespacePrefix();
/*     */ 
/* 407 */     if ((prefix == null) || (prefix.length() == 0))
/*     */     {
/* 409 */       return elem.getName();
/*     */     }
/*     */ 
/* 412 */     return prefix + ":" + elem.getName();
/*     */   }
/*     */ 
/*     */   public String getAttributeQName(Object obj)
/*     */   {
/* 417 */     Attribute attr = (Attribute)obj;
/*     */ 
/* 419 */     String prefix = attr.getNamespacePrefix();
/*     */ 
/* 421 */     if ((prefix == null) || ("".equals(prefix)))
/*     */     {
/* 423 */       return attr.getName();
/*     */     }
/*     */ 
/* 426 */     return prefix + ":" + attr.getName();
/*     */   }
/*     */ 
/*     */   public String getNamespaceStringValue(Object obj)
/*     */   {
/* 431 */     if ((obj instanceof Namespace))
/*     */     {
/* 433 */       Namespace ns = (Namespace)obj;
/* 434 */       return ns.getURI();
/*     */     }
/*     */ 
/* 437 */     XPathNamespace ns = (XPathNamespace)obj;
/* 438 */     return ns.getJDOMNamespace().getURI();
/*     */   }
/*     */ 
/*     */   public String getNamespacePrefix(Object obj)
/*     */   {
/* 445 */     if ((obj instanceof Namespace))
/*     */     {
/* 447 */       Namespace ns = (Namespace)obj;
/* 448 */       return ns.getPrefix();
/*     */     }
/*     */ 
/* 451 */     XPathNamespace ns = (XPathNamespace)obj;
/* 452 */     return ns.getJDOMNamespace().getPrefix();
/*     */   }
/*     */ 
/*     */   public String getTextStringValue(Object obj)
/*     */   {
/* 458 */     if ((obj instanceof Text))
/*     */     {
/* 460 */       return ((Text)obj).getText();
/*     */     }
/*     */ 
/* 463 */     if ((obj instanceof CDATA))
/*     */     {
/* 465 */       return ((CDATA)obj).getText();
/*     */     }
/*     */ 
/* 468 */     return "";
/*     */   }
/*     */ 
/*     */   public String getAttributeStringValue(Object obj)
/*     */   {
/* 473 */     Attribute attr = (Attribute)obj;
/*     */ 
/* 475 */     return attr.getValue();
/*     */   }
/*     */ 
/*     */   public String getElementStringValue(Object obj)
/*     */   {
/* 480 */     Element elem = (Element)obj;
/*     */ 
/* 482 */     StringBuffer buf = new StringBuffer();
/*     */ 
/* 484 */     List content = elem.getContent();
/* 485 */     Iterator contentIter = content.iterator();
/* 486 */     Object each = null;
/*     */ 
/* 488 */     while (contentIter.hasNext())
/*     */     {
/* 490 */       each = contentIter.next();
/*     */ 
/* 492 */       if ((each instanceof Text))
/*     */       {
/* 494 */         buf.append(((Text)each).getText());
/*     */       }
/* 496 */       else if ((each instanceof CDATA))
/*     */       {
/* 498 */         buf.append(((CDATA)each).getText());
/*     */       }
/* 500 */       else if ((each instanceof Element))
/*     */       {
/* 502 */         buf.append(getElementStringValue(each));
/*     */       }
/*     */     }
/*     */ 
/* 506 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionTarget(Object obj)
/*     */   {
/* 511 */     ProcessingInstruction pi = (ProcessingInstruction)obj;
/*     */ 
/* 513 */     return pi.getTarget();
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionData(Object obj)
/*     */   {
/* 518 */     ProcessingInstruction pi = (ProcessingInstruction)obj;
/*     */ 
/* 520 */     return pi.getData();
/*     */   }
/*     */ 
/*     */   public String getCommentStringValue(Object obj)
/*     */   {
/* 525 */     Comment cmt = (Comment)obj;
/*     */ 
/* 527 */     return cmt.getText();
/*     */   }
/*     */ 
/*     */   public String translateNamespacePrefixToUri(String prefix, Object context)
/*     */   {
/* 532 */     Element element = null;
/* 533 */     if ((context instanceof Element))
/*     */     {
/* 535 */       element = (Element)context;
/*     */     }
/* 537 */     else if ((context instanceof Text))
/*     */     {
/* 539 */       element = (Element)((Text)context).getParent();
/*     */     }
/* 541 */     else if ((context instanceof Attribute))
/*     */     {
/* 543 */       element = ((Attribute)context).getParent();
/*     */     }
/* 545 */     else if ((context instanceof XPathNamespace))
/*     */     {
/* 547 */       element = ((XPathNamespace)context).getJDOMElement();
/*     */     }
/* 549 */     else if ((context instanceof Comment))
/*     */     {
/* 551 */       element = (Element)((Comment)context).getParent();
/*     */     }
/* 553 */     else if ((context instanceof ProcessingInstruction))
/*     */     {
/* 555 */       element = (Element)((ProcessingInstruction)context).getParent();
/*     */     }
/*     */ 
/* 558 */     if (element != null)
/*     */     {
/* 560 */       Namespace namespace = element.getNamespace(prefix);
/*     */ 
/* 562 */       if (namespace != null)
/*     */       {
/* 564 */         return namespace.getURI();
/*     */       }
/*     */     }
/* 567 */     return null;
/*     */   }
/*     */ 
/*     */   public Object getDocument(String url) throws FunctionCallException
/*     */   {
/*     */     try
/*     */     {
/* 574 */       SAXBuilder builder = new SAXBuilder();
/*     */ 
/* 576 */       return builder.build(url);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 580 */       throw new FunctionCallException(e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class Singleton
/*     */   {
/* 100 */     private static DocumentNavigator instance = new DocumentNavigator();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.jdom.DocumentNavigator
 * JD-Core Version:    0.6.2
 */